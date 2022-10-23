package src.ctt.GameMlemBot.Logic.Handler.GameMlemHandler.GameMlemCommandHandler.GameMlemGameHandler;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import src.ctt.GameMlemBot.Enums.GameMlemGames;
import src.ctt.GameMlemBot.Enums.TimeInterval;
import src.ctt.GameMlemBot.Language.DefaultEmbed;
import src.ctt.GameMlemBot.Language.GameMlemEmbeds.GameMlemEmbed;
import src.ctt.GameMlemBot.Language.GameMlemEmbeds.OverOrLowerEmbed;
import src.ctt.GameMlemBot.Logic.GameMlemBotManager.GameMlemGuildManager;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemDataManager;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGuild;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserData;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGameDataManager.OverOrLower.DicesData;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGameDataManager.OverOrLower.OverOrLower.OverOrLowerMatchManager;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGameDataManager.OverOrLower.OverOrLower.OverOrLowerParticipant;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGameUserData.OverOrLowerUserData;
import src.ctt.GameMlemBot.Utils.ConvertTimeToDateString;
import src.ctt.GameMlemBot.Utils.DecimalFormatter;

public class OverOrLowerHandler {
    GameMlemGuildManager gameMlemGuildManager = new GameMlemGuildManager();
    DefaultEmbed defaultEmbed = new DefaultEmbed();
    GameMlemDataManager gameMlemDataManager = new GameMlemDataManager();
    DecimalFormatter currecyFormate = new DecimalFormatter();

    public OverOrLowerHandler(SlashCommandInteraction e) {

        GameMlemGuild guild = gameMlemGuildManager.getGuild(e.getGuild().getIdLong());
        OverOrLowerMatchManager overOrLowerMatchManager = guild.getOverOrLowerMatchManager();
        overOrLowerMatchManager.setMatchOwner(e.getUser().getName());

        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor(overOrLowerMatchManager.getMatchOwner() + " " + GameMlemGames.TAIXIU.getValue());
        eb.setColor(defaultEmbed.DEFAULT_EMBED_COLOR);
        eb.setThumbnail(OverOrLowerEmbed.DICE_THUMBNAIL);

        List<Button> buttons1 = new ArrayList<>();
        buttons1.add(Button.secondary(OverOrLowerEmbed.OVER_BET_1000_ID, OverOrLowerEmbed.OVER_BET_1000_NAME));
        buttons1.add(Button.secondary(OverOrLowerEmbed.OVER_BET_2000_ID, OverOrLowerEmbed.OVER_BET_2000_NAME));
        buttons1.add(Button.secondary(OverOrLowerEmbed.OVER_BET_5000_ID, OverOrLowerEmbed.OVER_BET_5000_NAME));
        buttons1.add(Button.success(OverOrLowerEmbed.OVER_BET_ALL_IN_ID, OverOrLowerEmbed.OVER_BET_ALL_IN_NAME));

        List<Button> buttons2 = new ArrayList<>();
        buttons2.add(Button.secondary(OverOrLowerEmbed.LOWER_BET_1000_ID, OverOrLowerEmbed.LOWER_BET_1000_NAME));
        buttons2.add(Button.secondary(OverOrLowerEmbed.LOWER_BET_2000_ID, OverOrLowerEmbed.LOWER_BET_2000_NAME));
        buttons2.add(Button.secondary(OverOrLowerEmbed.LOWER_BET_5000_ID, OverOrLowerEmbed.LOWER_BET_5000_NAME));
        buttons2.add(Button.danger(OverOrLowerEmbed.LOWER_BET_ALL_IN_ID, OverOrLowerEmbed.LOWER_BET_ALL_IN_NAME));

        String overValue = "";
        String lowerValue = "";

        for (OverOrLowerParticipant user : overOrLowerMatchManager.getMatchData()) {
            if (user.getOverBetMoney() > 0) {
                overValue += "<@" + user.getUser().getDISCORD_ID() + "> "
                        + currecyFormate.formatVND(user.getOverBetMoney()) + defaultEmbed.DEFAULT_CURRENCY + "\n";
            }

            if (user.getLowerBetMoney() > 0) {
                lowerValue += "<@" + user.getUser().getDISCORD_ID() + "> "
                        + currecyFormate.formatVND(user.getLowerBetMoney()) + defaultEmbed.DEFAULT_CURRENCY + "\n";
            }
        }
        eb.addField(OverOrLowerEmbed.OVER_NAME, overValue, true);
        eb.addField(OverOrLowerEmbed.LOWER_NAME, lowerValue, true);

        eb.setFooter(GameMlemEmbed.TIME_LEFT + overOrLowerMatchManager.getTimeLeft());

        if (guild.getOverOrLowerMatchManager().getPlayTimesPerday() > 0) {
            if (!guild.getOverOrLowerMatchManager().isPlaying()) {
                overOrLowerMatchManager.setPlaying(true);
                try {
                    e.getHook().sendMessageEmbeds(eb.build()).addActionRow(buttons1).addActionRow(buttons2).queue();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    defaultEmbed.sendAndDeleteMessageAfter(e, defaultEmbed.SOMETHING_WENT_WRONG());
                    return;
                }

                overOrLowerMatchManager.setPlayTimesPerday(guild.getOverOrLowerMatchManager().getPlayTimesPerday() - 1);

                long curTime = System.currentTimeMillis();
                while (true) {

                    // * TIME OUT
                    if (overOrLowerMatchManager.getTimeLeft() <= 0) {
                        overOrLowerMatchManager.setIsPlaying(false);

                        eb = new EmbedBuilder();
                        eb.setColor(defaultEmbed.DEFAULT_EMBED_COLOR);
                        eb.setAuthor(e.getUser().getName() + " " + GameMlemGames.TAIXIU.getValue());

                        DicesData dices = rollDices();
                        if (dices.getTotalDices >= 4 && dices.getTotalDices <= 10) {
                            eb.setThumbnail(OverOrLowerEmbed.LOWER_THUMBNAIL);
                        } else {
                            eb.setThumbnail(OverOrLowerEmbed.OVER_THUMBNAIL);
                        }

                        eb.setTitle(
                                new OverOrLowerEmbed().overOrLowerResult(dices));

                        eb.setFooter("◆  " + new ConvertTimeToDateString().getCurrentDayMonthYear());

                        String winnerValue = "";
                        String loserValue = "";
                        String totalPlayers = "";

                        // * LOWER WIN
                        if (dices.getTotalDices <= 10 && dices.getTotalDices >= 4) {
                            for (OverOrLowerParticipant u : overOrLowerMatchManager.getMatchData()) {
                                GameMlemUserData user = u.getUser();
                                OverOrLowerUserData loo = user.getOverOrLower();
                                if (u.getLowerBetMoney() > 0) {
                                    winnerValue += "<@" + u.getUser().getDISCORD_ID() + "> **(" +
                                            currecyFormate.formatVND(u.getLowerBetMoney())
                                            + defaultEmbed.DEFAULT_CURRENCY + ")**\n";

                                    // LOWER WIN TIME
                                    loo.setWinTimes(loo.getWinTimes() + 1);
                                    // LOWER TOTAL BET TIME
                                    loo.setTotalLowerBetTimes(loo.getTotalLowerBetTimes() + 1);
                                    // LOWER TOTAL BET MONEY
                                    loo.setTotalLowerBetMoney(loo.getTotalLowerBetMoney() + u.getLowerBetMoney());
                                    // LOWER TOTAL WIN MONEY
                                    loo.setTotalLowerWinMoney(loo.getTotalLowerWinMoney() + u.getLowerBetMoney());
                                    // * SET USER MONEY
                                    user.setTOTAL_MONEY(user.getTOTAL_MONEY() + (u.getLowerBetMoney() * 2));
                                }
                            }
                            for (OverOrLowerParticipant u : overOrLowerMatchManager.getMatchData()) {
                                GameMlemUserData user = u.getUser();
                                OverOrLowerUserData loo = user.getOverOrLower();
                                if (u.getOverBetMoney() > 0) {
                                    loserValue += "<@" + u.getUser().getDISCORD_ID() + "> **(" +
                                            currecyFormate.formatVND(u.getOverBetMoney())
                                            + defaultEmbed.DEFAULT_CURRENCY + ")**\n";
                                    // OVER LOSE TIME
                                    loo.setLoseTimes(loo.getLoseTimes() + 1);
                                    // OVER TOTAL BET TIME
                                    loo.setTotalOverBetTimes(loo.getTotalOverBetTimes() + 1);
                                    // OVER TOTAL BET MONEY
                                    loo.setTotalOverBetMoney(loo.getTotalOverBetMoney() + u.getOverBetMoney());
                                    // OVER TOTAL LOSE MONEY
                                    loo.setTotalOverLoseMoney(loo.getTotalOverLoseMoney() + u.getOverBetMoney());
                                }
                            }
                        } else {
                            for (OverOrLowerParticipant u : overOrLowerMatchManager.getMatchData()) {
                                GameMlemUserData user = u.getUser();
                                OverOrLowerUserData loo = user.getOverOrLower();
                                if (u.getOverBetMoney() > 0) {
                                    winnerValue += "<@" + u.getUser().getDISCORD_ID() + "> **(" +
                                            currecyFormate.formatVND(u.getOverBetMoney())
                                            + defaultEmbed.DEFAULT_CURRENCY + ")**\n";
                                    // OVER WIN TIME
                                    loo.setWinTimes(loo.getWinTimes() + 1);
                                    // OVER TOTAL BET TIME
                                    loo.setTotalOverBetTimes(loo.getTotalLowerBetTimes() + 1);
                                    // OVER TOTAL BET MONEY
                                    loo.setTotalOverBetMoney(loo.getTotalOverBetMoney() + u.getOverBetMoney());
                                    // OVER TOTAL WIN MONEY
                                    loo.setTotalOverWinMoney(loo.getTotalOverWinMoney() + u.getOverBetMoney());
                                    // * SET USER MONEY
                                    user.setTOTAL_MONEY(user.getTOTAL_MONEY() + (u.getOverBetMoney() * 2));
                                }
                            }
                            for (OverOrLowerParticipant u : overOrLowerMatchManager.getMatchData()) {
                                GameMlemUserData user = u.getUser();
                                OverOrLowerUserData loo = user.getOverOrLower();
                                if (u.getLowerBetMoney() > 0) {
                                    loserValue += "<@" + u.getUser().getDISCORD_ID() + "> **(" +
                                            currecyFormate.formatVND(u.getLowerBetMoney())
                                            + defaultEmbed.DEFAULT_CURRENCY + ")**\n";

                                    // LOWER LOSE TIME
                                    loo.setLoseTimes(loo.getLoseTimes() + 1);
                                    // LOWER TOTAL BET TIME
                                    loo.setTotalLowerBetTimes(loo.getTotalLowerBetTimes() + 1);
                                    // LOWER TOTAL BET MONEY
                                    loo.setTotalLowerBetMoney(loo.getTotalLowerBetMoney() + u.getLowerBetMoney());
                                    // LOWER TOTAL LOSE MONEY
                                    loo.setTotalLowerLoseMoney(loo.getTotalLowerLoseMoney() + u.getLowerBetMoney());
                                }
                            }
                        }

                        for (OverOrLowerParticipant u : overOrLowerMatchManager.getMatchData()) {
                            totalPlayers += "<@" + u.getUser().getDISCORD_ID() + "> **(" +
                                    currecyFormate.formatVND((u.getLowerBetMoney() + u.getOverBetMoney()))
                                    + defaultEmbed.DEFAULT_CURRENCY + ")** ";
                            u.getUser().getIsPlaying().setOverOrLower(false);
                        }

                        eb.addField(GameMlemEmbed.WINNER, winnerValue, true);
                        eb.addField(GameMlemEmbed.LOSER, loserValue, true);

                        eb.addField(GameMlemEmbed.TOTAL_PLAYERS, totalPlayers, false);

                        List<Button> buttons3 = new ArrayList<>();
                        buttons3.add(Button.secondary(GameMlemEmbed.LOCK, " 🔒 ").asDisabled());

                        e.getHook().editOriginalEmbeds(eb.build()).setActionRow(buttons3)
                                .queue();

                        overOrLowerMatchManager.setTimeLeft(TimeInterval.EXPIRE_TIME.getValue() / 1000);
                        overOrLowerMatchManager.clearMatchData();

                        break;
                    }
                    // * NORMAL
                    if (curTime / 1000 + GameMlemEmbed.UPDATE_TIME < System.currentTimeMillis() / 1000
                            && overOrLowerMatchManager.getTimeLeft() > 0) {
                        curTime = System.currentTimeMillis();
                        overOrLowerMatchManager
                                .setTimeLeft(overOrLowerMatchManager.getTimeLeft() - GameMlemEmbed.UPDATE_TIME);
                        if (!(overOrLowerMatchManager.getTimeLeft() <= 0)) {
                            updateOverOrLowerEmbed(e, overOrLowerMatchManager);
                        }

                    }
                }
            } else {
                defaultEmbed.sendAndDeleteMessageAfter(e,
                        defaultEmbed.ALREADY_HAD_A_ROOM(GameMlemGames.TAIXIU.getValue()));
            }
        } else {
            defaultEmbed.sendAndDeleteMessageAfter(e, defaultEmbed.OUT_OF_PLAY_TIME(GameMlemGames.TAIXIU.getValue()));
        }
    }

    public OverOrLowerHandler(ButtonInteractionEvent e) {
        GameMlemGuild guild = gameMlemGuildManager.getGuild(e.getGuild().getIdLong());
        OverOrLowerMatchManager overOrLowerMatchManager = guild.getOverOrLowerMatchManager();
        OverOrLowerParticipant user = overOrLowerMatchManager.GetParticipant(e.getUser().getIdLong());

        if (user == null) {
            user = new OverOrLowerParticipant(gameMlemDataManager.getUser(e.getUser().getIdLong()));
        }
        try {
            user.getUser().getIsPlaying().setOverOrLower(true);
        } catch (Exception ex) {
            defaultEmbed.sendEphemeralMessage(e, new GameMlemEmbed().NEED_TO_REGISTER);
            return;
        }

        if (overOrLowerMatchManager.getTimeLeft() > 5) {
            // * 1000 OVER
            if (e.getButton().getId().equalsIgnoreCase(OverOrLowerEmbed.OVER_BET_1000_ID)) {
                if (user.getUser().getTOTAL_MONEY() >= GameMlemEmbed.BET_1000) {
                    user.setOverBetMoney(user.getOverBetMoney() + GameMlemEmbed.BET_1000);
                    updateOverOrLowerEmbed(e, overOrLowerMatchManager);
                    user.getUser().setTOTAL_MONEY(user.getUser().getTOTAL_MONEY() - GameMlemEmbed.BET_1000);
                    overOrLowerMatchManager.addParticipant(user);
                    return;
                } else {
                    defaultEmbed.sendEphemeralMessage(e,
                            defaultEmbed.CAN_NOT_AFFOR_STRING((double) GameMlemEmbed.BET_1000));
                    return;
                }
                // * 2000 OVER
            } else if (e.getButton().getId().equalsIgnoreCase(OverOrLowerEmbed.OVER_BET_2000_ID)) {
                if (user.getUser().getTOTAL_MONEY() >= GameMlemEmbed.BET_2000) {
                    user.setOverBetMoney(user.getOverBetMoney() + GameMlemEmbed.BET_2000);
                    updateOverOrLowerEmbed(e, overOrLowerMatchManager);
                    user.getUser().setTOTAL_MONEY(user.getUser().getTOTAL_MONEY() - GameMlemEmbed.BET_2000);
                    overOrLowerMatchManager.addParticipant(user);
                    return;
                } else {
                    defaultEmbed.sendEphemeralMessage(e,
                            defaultEmbed.CAN_NOT_AFFOR_STRING((double) GameMlemEmbed.BET_2000));
                    return;
                }
                // * 5000 OVER
            } else if (e.getButton().getId().equalsIgnoreCase(OverOrLowerEmbed.OVER_BET_5000_ID)) {
                if (user.getUser().getTOTAL_MONEY() >= GameMlemEmbed.BET_5000) {
                    user.setOverBetMoney(user.getOverBetMoney() + GameMlemEmbed.BET_5000);
                    updateOverOrLowerEmbed(e, overOrLowerMatchManager);
                    user.getUser().setTOTAL_MONEY(user.getUser().getTOTAL_MONEY() - GameMlemEmbed.BET_5000);
                    overOrLowerMatchManager.addParticipant(user);
                    return;
                } else {
                    defaultEmbed.sendEphemeralMessage(e,
                            defaultEmbed.CAN_NOT_AFFOR_STRING((double) GameMlemEmbed.BET_5000));
                    return;
                }
                // * ALL OVER
            } else if (e.getButton().getId().equalsIgnoreCase(OverOrLowerEmbed.OVER_BET_ALL_IN_ID)) {
                if (user.getUser().getTOTAL_MONEY() > 0) {
                    user.setOverBetMoney(user.getOverBetMoney() + user.getUser().getTOTAL_MONEY());
                    updateOverOrLowerEmbed(e, overOrLowerMatchManager);
                    user.getUser().setTOTAL_MONEY(0);
                    overOrLowerMatchManager.addParticipant(user);
                    return;
                } else {
                    defaultEmbed.sendEphemeralMessage(e,
                            defaultEmbed.CAN_NOT_AFFOR_STRING(null));
                    return;
                }
            }

            // * 1000 LOWER
            if (e.getButton().getId().equalsIgnoreCase(OverOrLowerEmbed.LOWER_BET_1000_ID)) {
                if (user.getUser().getTOTAL_MONEY() >= GameMlemEmbed.BET_1000) {
                    user.setLowerBetMoney(user.getLowerBetMoney() + GameMlemEmbed.BET_1000);
                    updateOverOrLowerEmbed(e, overOrLowerMatchManager);
                    user.getUser().setTOTAL_MONEY(user.getUser().getTOTAL_MONEY() - GameMlemEmbed.BET_1000);
                    overOrLowerMatchManager.addParticipant(user);
                    return;
                } else {
                    defaultEmbed.sendEphemeralMessage(e,
                            defaultEmbed.CAN_NOT_AFFOR_STRING((double) GameMlemEmbed.BET_1000));
                    return;
                }
                // * 2000 LOWER
            } else if (e.getButton().getId().equalsIgnoreCase(OverOrLowerEmbed.LOWER_BET_2000_ID)) {
                if (user.getUser().getTOTAL_MONEY() >= GameMlemEmbed.BET_2000) {
                    user.setLowerBetMoney(user.getLowerBetMoney() + GameMlemEmbed.BET_2000);
                    updateOverOrLowerEmbed(e, overOrLowerMatchManager);
                    user.getUser().setTOTAL_MONEY(user.getUser().getTOTAL_MONEY() - GameMlemEmbed.BET_2000);
                    overOrLowerMatchManager.addParticipant(user);
                    return;
                } else {
                    defaultEmbed.sendEphemeralMessage(e,
                            defaultEmbed.CAN_NOT_AFFOR_STRING((double) GameMlemEmbed.BET_2000));
                    return;
                }
                // * 5000 LOWER
            } else if (e.getButton().getId().equalsIgnoreCase(OverOrLowerEmbed.LOWER_BET_5000_ID)) {
                if (user.getUser().getTOTAL_MONEY() >= GameMlemEmbed.BET_5000) {
                    user.setLowerBetMoney(user.getLowerBetMoney() + GameMlemEmbed.BET_5000);
                    updateOverOrLowerEmbed(e, overOrLowerMatchManager);
                    user.getUser().setTOTAL_MONEY(user.getUser().getTOTAL_MONEY() - GameMlemEmbed.BET_5000);
                    overOrLowerMatchManager.addParticipant(user);
                    return;
                } else {
                    defaultEmbed.sendEphemeralMessage(e,
                            defaultEmbed.CAN_NOT_AFFOR_STRING((double) GameMlemEmbed.BET_5000));
                    return;
                }
                // * ALL LOWER
            } else if (e.getButton().getId().equalsIgnoreCase(OverOrLowerEmbed.LOWER_BET_ALL_IN_ID)) {
                if (user.getUser().getTOTAL_MONEY() > 0) {
                    user.setLowerBetMoney(user.getLowerBetMoney() + user.getUser().getTOTAL_MONEY());
                    updateOverOrLowerEmbed(e, overOrLowerMatchManager);
                    user.getUser().setTOTAL_MONEY(0);
                    overOrLowerMatchManager.addParticipant(user);
                    return;
                } else {
                    defaultEmbed.sendEphemeralMessage(e,
                            defaultEmbed.CAN_NOT_AFFOR_STRING(null));
                    return;
                }
            }

            System.out.println(user.getUser().getTOTAL_MONEY());

        } else {
            defaultEmbed.sendEphemeralMessage(e, defaultEmbed.CAN_NOT_INTERACT_ANYMORE());
            return;
        }
    }

    public void updateOverOrLowerEmbed(SlashCommandInteraction e, OverOrLowerMatchManager match) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor(match.getMatchOwner() + " " + GameMlemGames.TAIXIU.getValue());
        eb.setColor(defaultEmbed.DEFAULT_EMBED_COLOR);
        eb.setThumbnail(OverOrLowerEmbed.DICE_THUMBNAIL);

        String overValue = "";
        String lowerValue = "";

        for (OverOrLowerParticipant user : match.getMatchData()) {
            if (user.getOverBetMoney() > 0) {
                overValue += "<@" + user.getUser().getDISCORD_ID() + "> "
                        + currecyFormate.formatVND(user.getOverBetMoney()) + defaultEmbed.DEFAULT_CURRENCY
                        + "\n";
            }

            if (user.getLowerBetMoney() > 0) {
                lowerValue += "<@" + user.getUser().getDISCORD_ID() + "> "
                        + currecyFormate.formatVND(user.getLowerBetMoney()) + defaultEmbed.DEFAULT_CURRENCY + "\n";
            }
        }

        eb.addField(OverOrLowerEmbed.OVER_NAME, overValue, true);
        eb.addField(OverOrLowerEmbed.LOWER_NAME, lowerValue, true);
        eb.setFooter(GameMlemEmbed.TIME_LEFT + match.getTimeLeft());

        e.getHook().editOriginalEmbeds(eb.build()).queue();
    }

    public void updateOverOrLowerEmbed(ButtonInteractionEvent e, OverOrLowerMatchManager match) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(defaultEmbed.DEFAULT_EMBED_COLOR);
        eb.setAuthor(match.getMatchOwner() + " " + GameMlemGames.TAIXIU.getValue());
        eb.setColor(defaultEmbed.DEFAULT_EMBED_COLOR);
        eb.setThumbnail(OverOrLowerEmbed.DICE_THUMBNAIL);

        String overValue = "";
        String lowerValue = "";

        for (OverOrLowerParticipant user : match.getMatchData()) {
            if (user.getOverBetMoney() > 0) {
                overValue += "<@" + user.getUser().getDISCORD_ID() + "> "
                        + currecyFormate.formatVND(user.getOverBetMoney()) + defaultEmbed.DEFAULT_CURRENCY + "\n";
            }

            if (user.getLowerBetMoney() > 0) {
                lowerValue += "<@" + user.getUser().getDISCORD_ID() + "> "
                        + currecyFormate.formatVND(user.getLowerBetMoney()) + defaultEmbed.DEFAULT_CURRENCY + "\n";
            }
        }

        eb.addField(OverOrLowerEmbed.OVER_NAME, overValue, true);
        eb.addField(OverOrLowerEmbed.LOWER_NAME, lowerValue, true);
        eb.setFooter(GameMlemEmbed.TIME_LEFT + match.getTimeLeft());

        if (!e.isAcknowledged()) {
            e.editMessageEmbeds(eb.build()).queue();
        } else {
            return;
        }

    }

    public DicesData rollDices() {
        int dice1 = (int) (Math.round(Math.random() * 6));
        int dice2 = (int) (Math.round(Math.random() * 6));
        int dice3 = (int) (Math.round(Math.random() * 6));

        while (true) {
            if (!(dice1 > 0 && dice1 <= 6)) {
                dice1 = (int) (Math.round(Math.random() * 6));
            }
            if (!(dice2 > 0 && dice2 <= 6)) {
                dice2 = (int) (Math.round(Math.random() * 6));
            }
            if (!(dice3 > 0 && dice3 <= 6)) {
                dice3 = (int) (Math.round(Math.random() * 6));
            }

            if ((dice1 > 0 && dice1 <= 6) && (dice2 > 0 && dice2 <= 6) && (dice3 > 0 || dice3 <= 6)) {
                break;
            }
        }
        return new DicesData(dice1, dice2, dice3);
    }
}

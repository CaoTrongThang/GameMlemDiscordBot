package src.ctt.GameMlemBot.Logic.Handler.GameMlem.CommandHandler;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import src.ctt.GameMlemBot.Enums.GameMlemGames;
import src.ctt.GameMlemBot.Enums.GameMlemStatsType;
import src.ctt.GameMlemBot.Enums.Games;
import src.ctt.GameMlemBot.Enums.TradeTypes;
import src.ctt.GameMlemBot.Language.DefaultEmbed;
import src.ctt.GameMlemBot.Language.GameMlemEmbeds.DailyRewardEmbed;
import src.ctt.GameMlemBot.Language.GameMlemEmbeds.GameMlemEmbed;
import src.ctt.GameMlemBot.Logic.Handler.GameMlem.Commands.GameMlemSlashCommands;
import src.ctt.GameMlemBot.Logic.Handler.GameMlem.DailyRewardHandler.GameMlemDailyRewardHandler;
import src.ctt.GameMlemBot.Logic.Handler.GameMlem.GameMlemGameHandler.OverOrLowerHandler;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserDataManager;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.DailyRewardData.GameMlemDailyRewardData;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserData.GameMlemUserData;
import src.ctt.GameMlemBot.Utils.DecimalFormatter;

public class GameMlemSlashHandler {
    GameMlemUserDataManager gameMlemDataManager = new GameMlemUserDataManager();
    DefaultEmbed defaultEmbed = new DefaultEmbed();
    GameMlemEmbed gameMlemEmbed = new GameMlemEmbed();

    public void gameMlemSlashCommandHandler(SlashCommandInteractionEvent e) {
        //
        if (e.getSubcommandName().equalsIgnoreCase(GameMlemSlashCommands.DANG_KY_COMMAND)) {
            DANG_KY_HANDLER(e);

        } else if (e.getSubcommandName().equals(GameMlemSlashCommands.TAO_GAME_COMMAND)) {
            TAO_GAME_HANDLER(e);
        } else if (e.getSubcommandName().equalsIgnoreCase(GameMlemSlashCommands.DIEM_DANH_COMMAND)) {
            DIEM_DANH_HANDLER(e);
        } else if (e.getSubcommandName().equalsIgnoreCase(GameMlemSlashCommands.GIAO_DICH_COMMAND)) {
            GIAO_DICH_HANDLER(e);
        } else if (e.getSubcommandName().equalsIgnoreCase(GameMlemSlashCommands.THONG_SO_COMMAND)) {
            THONG_SO_HANDLER(e);
        }
    }

    private void THONG_SO_HANDLER(SlashCommandInteractionEvent e) {
        User user = null;
        GameMlemStatsType statsType;
        GameMlemUserData targetUser = null;
        if (e.getOption(GameMlemSlashCommands.userNameOrIDArg) != null) {
            user = e.getOption(GameMlemSlashCommands.userNameOrIDArg).getAsUser();
        }
        if (e.getOption(GameMlemSlashCommands.statsTypeArg) != null) {
            try {
                statsType = GameMlemStatsType.valueOf(e.getOption(GameMlemSlashCommands.statsTypeArg).getAsString());
            } catch (Exception ex) {
                defaultEmbed.sendAndDeleteMessageAfter(e, defaultEmbed
                        .WRONG_ENUM_TYPE(e.getOption(GameMlemSlashCommands.statsTypeArg).getAsString(),
                                GameMlemStatsType.class));
                ex.printStackTrace();
            }
        }

        if (user != null) {
            targetUser = gameMlemDataManager.loadUser(user.getIdLong());
            if (targetUser == null) {
                defaultEmbed.sendAndDeleteMessageAfter(e,
                        defaultEmbed.ACCOUNT_IS_NOT_LINKED(user.getIdLong(), Games.GAMEMLEM.getValue()));
                return;
            }
        } else {
            targetUser = gameMlemDataManager.loadUser(e.getUser().getIdLong());
        }

    }

    private void DANG_KY_HANDLER(SlashCommandInteractionEvent e) {
        if (gameMlemDataManager.isExisted(e.getUser().getIdLong())) {
            defaultEmbed.sendAndDeleteMessageAfter(e,
                    defaultEmbed.ALREADY_LINKED_WITH_DISCORD(e.getUser().getIdLong(), Games.GAMEMLEM.getValue()));
            return;
        }

        GameMlemUserData user = new GameMlemUserData(e.getUser());
        user.setLAST_ACTIVITY(System.currentTimeMillis());
        user.setIsUseCommand(true);
        gameMlemDataManager.addNewUser(user);

        defaultEmbed.sendAndDeleteMessageAfter(e,
                defaultEmbed.ACCOUNT_NOW_LINKED(e.getUser().getIdLong(), Games.GAMEMLEM.getValue()));
        new GameMlemUserDataManager().getUser(e.getUser().getIdLong()).setIsUseCommand(true);
    }

    public void TAO_GAME_HANDLER(SlashCommandInteractionEvent e) {
        if (e.getOption(GameMlemSlashCommands.gameTypeArg) != null) {
            String gameType = e.getOption(GameMlemSlashCommands.gameTypeArg).getAsString().toUpperCase();
            try {
                GameMlemGames.valueOf(gameType);
            } catch (Exception ex) {
                defaultEmbed.sendAndDeleteMessageAfter(e, defaultEmbed.WRONG_ENUM_TYPE(gameType, GameMlemGames.class));
                return;
            }

            if (GameMlemGames.valueOf(gameType) == GameMlemGames.TAIXIU) {
                new OverOrLowerHandler(e);
            }
        } else {
            defaultEmbed.sendAndDeleteMessageAfter(e, defaultEmbed.WRONG_ENUM_TYPE(null, GameMlemGames.class));
        }
    }

    public void DIEM_DANH_HANDLER(SlashCommandInteractionEvent e) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        GameMlemUserData user = gameMlemDataManager.getUser(e.getUser().getIdLong());

        Date currentDate = new Date(System.currentTimeMillis());

        GameMlemDailyRewardData userDailyReward = user.getDailyReward();

        Calendar nextRewardDateAfterGetReward = Calendar.getInstance();
        nextRewardDateAfterGetReward.setTime(currentDate);
        nextRewardDateAfterGetReward.add(Calendar.DATE, 1);

        // * IF USER HAS NEVER GET A REWARD
        if (userDailyReward.getLastRewardDate() == null || userDailyReward.getNextRewardDate() == null) {
            userDailyReward.setLastRewardDate(currentDate);
            userDailyReward.setNextRewardDate(nextRewardDateAfterGetReward.getTime());

            userDailyReward.setDailyRewardStrike(userDailyReward.getDailyRewardStrike() + 1);

            userDailyReward.setTotalRewardClaimed(userDailyReward.getTotalRewardClaimed() + 1);

            if (userDailyReward.getDailyRewardStrike() > userDailyReward.getHighestDailyRewardStrike()) {
                userDailyReward.setHighestDailyRewardStrike(userDailyReward.getDailyRewardStrike());
            }

            defaultEmbed.sendAndDeleteMessageAfter(e,
                    new DailyRewardEmbed().ROLL_CALL(user,
                            new GameMlemDailyRewardHandler().giveReward(user)));
            return;
        }

        Calendar breakRewardDate = Calendar.getInstance();
        breakRewardDate.setTime(userDailyReward.getNextRewardDate());
        breakRewardDate.add(Calendar.DATE, 1);

        // * IF IS NOT REWARD DATE YET
        if (System.currentTimeMillis() < userDailyReward.getNextRewardDate().getTime()) {
            defaultEmbed.sendAndDeleteMessageAfter(e,
                    new DailyRewardEmbed().WAIT_FOR_NEXT_DAILY_REWARD("<@" + user.getDISCORD_ID() + ">",
                            userDailyReward.getNextRewardDate().getTime() - System.currentTimeMillis()));
            return;
        }

        // * IF TODAY IS THE REWARD DAY
        if (System.currentTimeMillis() > userDailyReward.getNextRewardDate().getTime()
                && System.currentTimeMillis() < breakRewardDate.getTime().getTime()) {
            new GameMlemDailyRewardHandler().giveReward(user);

            userDailyReward.setLastRewardDate(currentDate);
            userDailyReward.setNextRewardDate(breakRewardDate.getTime());

            userDailyReward.setDailyRewardStrike(userDailyReward.getDailyRewardStrike() + 1);

            userDailyReward.setTotalRewardClaimed(userDailyReward.getTotalRewardClaimed() + 1);

            if (userDailyReward.getDailyRewardStrike() > userDailyReward.getHighestDailyRewardStrike()) {
                userDailyReward.setHighestDailyRewardStrike(userDailyReward.getDailyRewardStrike());
            }

            defaultEmbed.sendAndDeleteMessageAfter(e,
                    new DailyRewardEmbed().ROLL_CALL(user,
                            new GameMlemDailyRewardHandler().giveReward(user)));
        }

        // * IF TODAY IS = LAST REWARD DATE + 1, BREAK REWARD STRIKE
        if (System.currentTimeMillis() > breakRewardDate.getTime().getTime()) {
            new GameMlemDailyRewardHandler().giveReward(user);

            userDailyReward.setLastRewardDate(currentDate);
            userDailyReward.setNextRewardDate(nextRewardDateAfterGetReward.getTime());

            userDailyReward.setDailyRewardStrike(0);

            userDailyReward.setTotalRewardClaimed(userDailyReward.getTotalRewardClaimed() + 1);

            if (userDailyReward.getDailyRewardStrike() > userDailyReward.getHighestDailyRewardStrike()) {
                userDailyReward.setHighestDailyRewardStrike(userDailyReward.getDailyRewardStrike());
            }

            defaultEmbed.sendAndDeleteMessageAfter(e,
                    new DailyRewardEmbed().ROLL_CALL(user,
                            new GameMlemDailyRewardHandler().giveReward(user)));
        }
    }

    public void GIAO_DICH_HANDLER(SlashCommandInteractionEvent e) {
        if (e.getOption(GameMlemSlashCommands.userNameOrIDArg) == null) {
            defaultEmbed.sendAndDeleteMessageAfter(e, defaultEmbed.INVALID_ID(null));
            return;
        }

        if (e.getOption(GameMlemSlashCommands.tradeTypeArg) == null) {
            defaultEmbed.sendAndDeleteMessageAfter(e, defaultEmbed.WRONG_ENUM_TYPE(null, TradeTypes.class));
            return;
        }

        try {
            TradeTypes.valueOf(e.getOption(GameMlemSlashCommands.tradeTypeArg).getAsString().toUpperCase());
        } catch (Exception ex) {
            defaultEmbed.sendAndDeleteMessageAfter(e, defaultEmbed.WRONG_ENUM_TYPE(null, TradeTypes.class));
            return;
        }

        if (e.getOption(GameMlemSlashCommands.tradeItemArg) == null) {
            defaultEmbed.sendAndDeleteMessageAfter(e, new GameMlemEmbed().INVALID_TRADE_ITEM(null));
            return;
        }

        User user = e.getOption(GameMlemSlashCommands.userNameOrIDArg).getAsUser();
        TradeTypes tradeType = TradeTypes
                .valueOf(e.getOption(GameMlemSlashCommands.tradeTypeArg).getAsString().toUpperCase());
        String tradeItem = e.getOption(GameMlemSlashCommands.tradeItemArg).getAsString();

        GameMlemUserData sourceUser = gameMlemDataManager.getUser(e.getUser().getIdLong());
        GameMlemUserData desUser;
        if (gameMlemDataManager.loadUser(user.getIdLong()) != null) {
            if (tradeType == TradeTypes.MONEY) {
                try {
                    Double.parseDouble(tradeItem);
                } catch (Exception ex) {
                    defaultEmbed.sendAndDeleteMessageAfter(e, new GameMlemEmbed().INVALID_TRADE_ITEM(tradeItem));
                    return;
                }
            }
            desUser = gameMlemDataManager.getUser(user.getIdLong());

            if (sourceUser == desUser) {
                defaultEmbed.sendAndDeleteMessageAfter(e,
                        new GameMlemEmbed().TRADE_WITH_YOURSELF(sourceUser.getDISCORD_ID()));
                return;
            }

            if (Double.parseDouble(tradeItem) < 0) {
                defaultEmbed.sendAndDeleteMessageAfter(e,
                        new GameMlemEmbed()
                                .TRADE_NEGATIVE_MONEY(new DecimalFormatter().formatVND(Double.parseDouble(tradeItem))
                                        + defaultEmbed.DEFAULT_CURRENCY));
                return;
            }

            if (sourceUser.getTOTAL_MONEY() >= Double.parseDouble(tradeItem)) {
                sourceUser.setTOTAL_MONEY(sourceUser.getTOTAL_MONEY() - Double.parseDouble(tradeItem));
                desUser.setTOTAL_MONEY(desUser.getTOTAL_MONEY() + Double.parseDouble(tradeItem));
                defaultEmbed.sendAndDeleteMessageAfter(e,
                        new GameMlemEmbed().TRADE_SUCESS(sourceUser, desUser,
                                new DecimalFormatter().formatVND(Double.parseDouble(tradeItem))
                                        + defaultEmbed.DEFAULT_CURRENCY));
                return;
            } else {
                defaultEmbed.sendAndDeleteMessageAfter(e,
                        defaultEmbed.CAN_NOT_AFFOR_EMBED(sourceUser.getDISCORD_ID(),
                                new DecimalFormatter().formatVND(Double.parseDouble(tradeItem))
                                        + defaultEmbed.DEFAULT_CURRENCY));
                return;
            }

        } else {
            defaultEmbed.sendAndDeleteMessageAfter(e,
                    defaultEmbed.ACCOUNT_IS_NOT_LINKED(user.getIdLong(), Games.GAMEMLEM.getValue()));
        }
    }
}

package src.ctt.GameMlemBot.Logic.Handler.OsuHandler.OsuCommandHandler;

import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import src.ctt.GameMlemBot.Enum.Games;
import src.ctt.GameMlemBot.Enum.OsuModes;
import src.ctt.GameMlemBot.Language.DefaultEmbed;
import src.ctt.GameMlemBot.Language.OsuEmbed;
import src.ctt.GameMlemBot.Logic.Data.GameMlemData.GameMlemDataManager;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuDataManager;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuUserDiscordData;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuBeatmapCalculateData.OsuBeatmapCalculatedData;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuRecentScore.OsuRecentScore;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuUserData.OsuUserData;
import src.ctt.GameMlemBot.Utils.ConvertSecondToDateString;

public class OsuHandler {
    private OsuRequest osuRequest = new OsuRequest();
    private OsuDataManager osuDataManager = new OsuDataManager();
    private GameMlemDataManager gameMlemDataManager = new GameMlemDataManager();

    public void osuCommandHander(SlashCommandInteractionEvent e) {
        // LINK HANDLER
        if (e.getSubcommandName().equalsIgnoreCase(OsuCommands.LINK_COMMAND)) {
            LINK_COMMAND_HANDLER(e);
            return;
        }
        // ! FIRST STEP: DOUBLE CHECK IF USER HAD LINKED WITH DISCORD

        if (e.getOption(OsuCommands.userNameOrIDArg) != null) {
            OsuUserDiscordData osuDiscord = osuDataManager
                    .findOsuDiscordLink(e.getOption(OsuCommands.userNameOrIDArg).getAsUser().getIdLong());
            if (osuDiscord == null) {
                new DefaultEmbed().sendAndDeleteMessageAfter(e,
                        new DefaultEmbed().ACCOUNT_IS_NOT_LINKED(
                                e.getOption(OsuCommands.userNameOrIDArg).getAsUser().getName(),
                                Games.OSU.getValue()));
                return;
            }
        } else {
            OsuUserDiscordData osuDiscord = osuDataManager.findOsuDiscordLink(e.getUser().getIdLong());
            if (osuDiscord == null) {
                new DefaultEmbed().sendAndDeleteMessageAfter(e,
                        new DefaultEmbed().ACCOUNT_IS_NOT_LINKED(e.getUser().getName(), Games.OSU.getValue()));
                return;
            }
        }

        // ! ========================================================================

        // UNLINK HANDLER
        if (e.getSubcommandName().equalsIgnoreCase(OsuCommands.UNLINK_COMMAND)) {
            UNLINK_COMMAND_HANDLER(e);
            return;
        }
        // STATS HANDLER
        else if (e.getSubcommandName().equalsIgnoreCase(OsuCommands.STATS_COMMAND)) {
            STATS_COMMAND_HANDLER(e);
            return;
        }

        // TOP HANDLER
        // TODO
        else if (e.getSubcommandName().equalsIgnoreCase(OsuCommands.TOP_COMMAND)) {
            TOP_COMMAND_HANDLER(e);
            return;
        }
        // RECENT HANDLER
        else if (e.getSubcommandName().equalsIgnoreCase(OsuCommands.RECENT_COMMAND)) {
            RECENT_COMMNAD_HANDLER(e);
            return;
        }

        // ROLL HANDLER
        else if (e.getSubcommandName().equalsIgnoreCase(OsuCommands.ROLL_COMMAND)) {
            ROLL_COMMAND_HANDLER(e);
            return;
        }

        // AVATAR HANDLER
        else if (e.getSubcommandName().equalsIgnoreCase(OsuCommands.AVATAR_COMMAND)) {
            AVATAR_COMMAND_HANDLER(e);
            return;
        }
    }

    public void RECENT_COMMNAD_HANDLER(SlashCommandInteractionEvent e) {
        User targetDisUser = null;
        OsuRecentScore osuScore = null;
        OsuUserData osuUser = null;
        OsuBeatmapCalculatedData osuBeatmapCalculatedData = null;
        EmbedBuilder eb = new EmbedBuilder();
        StringBuilder strBuilder;
        OsuModes osuMode = null;

        try {
            if (e.getOption(OsuCommands.osuModeArg) != null) {
                try {
                    OsuModes.valueOf(e.getOption(OsuCommands.osuModeArg).getAsString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    new DefaultEmbed().sendAndDeleteMessageAfter(e,
                            new OsuEmbed().WRONG_OSU_MODE(e.getOption(OsuCommands.osuModeArg).getAsString()));
                    return;
                }
                if (OsuModes.valueOf(e.getOption(OsuCommands.osuModeArg).getAsString()) == OsuModes.osu) {
                    osuMode = OsuModes.osu;
                } else if (OsuModes.valueOf(e.getOption(OsuCommands.osuModeArg).getAsString()) == OsuModes.mania) {
                    osuMode = OsuModes.mania;
                } else if (OsuModes.valueOf(e.getOption(OsuCommands.osuModeArg).getAsString()) == OsuModes.taiko) {
                    osuMode = OsuModes.taiko;
                } else if (OsuModes.valueOf(e.getOption(OsuCommands.osuModeArg).getAsString()) == OsuModes.fruits) {
                    osuMode = OsuModes.fruits;
                }
            }

            if (e.getOption(OsuCommands.userNameOrIDArg) != null) {
                targetDisUser = e.getOption(OsuCommands.userNameOrIDArg).getAsUser();
                osuUser = osuRequest.getUser(osuDataManager.getOsuID(targetDisUser.getIdLong()));
                if (osuMode == null) {
                    osuMode = osuMode.valueOf(osuUser.getPlaymode());
                }

                osuScore = osuRequest.getOsuRecentScore(Long.toString(osuUser.getId()), osuMode);
                if (osuScore == null) {
                    new DefaultEmbed().sendAndDeleteMessageAfter(e,
                            new OsuEmbed().HAVE_NO_RECENT_PLAYS(osuUser.getUsername()));
                    return;
                }

            } else {
                osuUser = osuRequest.getUser(osuDataManager.getOsuID(e.getUser().getIdLong()));
                if (osuMode == null) {
                    osuMode = osuMode.valueOf(osuUser.getPlaymode());
                }
                osuScore = osuRequest.getOsuRecentScore(Long.toString(osuUser.getId()), osuMode);
                if (osuScore == null) {
                    new DefaultEmbed().sendAndDeleteMessageAfter(e,
                            new OsuEmbed().HAVE_NO_RECENT_PLAYS(osuUser.getUsername()));
                    return;
                }
            }
            osuBeatmapCalculatedData = osuRequest.getOsuBeatmapCalculateData(osuScore.getBeatmap().getId(),
                    osuScore.getMods(), osuScore.getAccuracy());

            // * Got all data, make embed from now

            eb.setColor(DefaultEmbed.DEFAULT_EMBED_COLOR);
            eb.setAuthor(osuScore.getUser().getUsername()
                    + OsuEmbed.RECENT_JUST_PLAY_AUTHOR,
                    null, osuScore.getUser().getAvatar_url());

            eb.setThumbnail(osuScore.getBeatmapset().getCovers().getList());

            eb.setTitle(osuScore.getBeatmapset().getTitle()
                    + " by " + osuScore.getBeatmapset().getArtist()
                    + " [" + osuScore.getBeatmap().getVersion() + "]"
                    + " [" + Math.floor(osuBeatmapCalculatedData.getStars() * 100) / 100 + "★]",
                    osuScore.getBeatmap().getUrl());

            String statsFieldTitle = OsuEmbed.RECENT_STATS_FIELD_TITLE;
            String statsFieldValue = "";
            strBuilder = new StringBuilder(statsFieldValue);
            String grade = "";
            switch (osuScore.getRank()) {
                case "SSH":
                    grade = OsuEmbed.RANK_SSH_EMOJI;
                    break;
                case "SS":
                    grade = OsuEmbed.RANK_SS_EMOJI;
                    break;
                case "SH":
                    grade = OsuEmbed.RANK_SH_EMOJI;
                    break;
                case "S":
                    grade = OsuEmbed.RANK_S_EMOJI;
                    break;
                case "A":
                    grade = OsuEmbed.RANK_A_EMOJI;
                    break;
                case "B":
                    grade = OsuEmbed.RANK_B_EMOJI;
                    break;
                case "C":
                    grade = OsuEmbed.RANK_C_EMOJI;
                    break;
                case "D":
                    grade = OsuEmbed.RANK_D_EMOJI;
                    break;
                case "F":
                    grade = OsuEmbed.RANK_F_EMOJI;
                    break;

            }
            if (osuScore.getPassed()) {
                strBuilder.append("🞂 " + OsuEmbed.USER_RANK_GLOBAL + "**" + grade + "**\n");
            } else {
                strBuilder.append("🞂 " + OsuEmbed.USER_RANK_GLOBAL + "**" + grade + "** ("
                        + String.format("%.2f", new OsuUtilsMethod().getRecentCompletePercent(osuScore)) + "%)\n");
            }

            if (osuScore.getPp() == 0.0) {
                strBuilder.append("🞂 " +
                        OsuEmbed.USER_PP + "**" + String.format("%.2f", osuRequest.getUserRecentPP(osuScore).getPp())
                        + "**/"
                        + String.format("%.2f", osuBeatmapCalculatedData.getPp()) + "("
                        + String.format("%.2f", osuScore.getAccuracy() * 100) + "% FC)" + "\n");
            } else {
                strBuilder.append("🞂 " +
                        OsuEmbed.USER_PP + "**" + String.format("%.2f", osuScore.getPp())
                        + "**/"
                        + String.format("%.2f", osuBeatmapCalculatedData.getPp()) + "("
                        + String.format("%.2f", osuScore.getAccuracy() * 100) + "% FC)" + "\n");
            }
            strBuilder.append("🞂 " + OsuEmbed.USER_SCORE + "**" + String.format("%,d", osuScore.getScore()) + "**\n");
            strBuilder.append("🞂 " + OsuEmbed.USER_ACCURACY + "**"
                    + String.format("%.2f", osuScore.getAccuracy() * 100) + "%**\n");
            strBuilder.append("🞂 " + OsuEmbed.RECENT_SCORE_MAX_COMBO + "**x"
                    + osuScore.getMax_combo() + "**/"
                    + osuRequest.getBeatmapData(osuScore.getBeatmap().getId()).getMax_combo());
            eb.addField(statsFieldTitle, strBuilder.toString(), true);

            //
            String modsFieldTitle = OsuEmbed.RECENT_MODS_USED_FIELD_TITLE;
            String modsFieldValue = "";
            strBuilder = new StringBuilder(modsFieldValue);
            if (osuScore.getMods().length > 0) {
                for (int index = 0; index < osuScore.getMods().length; index++) {

                    strBuilder.append("➤ **" + osuScore.getMods()[index] + "**" + "\n");
                }
            } else if (osuScore.getMods().length == 0) {
                strBuilder.append(":x:");
            }
            eb.addField(modsFieldTitle, strBuilder.toString(), true);

            //
            String resultFieldTitle = OsuEmbed.RECENT_HIT_COUNT_FIELD_TITLE;
            String resultFieldValue = "";
            strBuilder = new StringBuilder(resultFieldValue);
            strBuilder.append(OsuEmbed.HIT_3OO_EMOJI + "**" + osuScore.getStatistics().getCount_300() + "** ");
            strBuilder.append(OsuEmbed.HIT_100_EMOJI + "** "
                    + osuScore.getStatistics().getCount_100() + "** ");
            strBuilder.append(OsuEmbed.HIT_50_EMOJI + "**"
                    + osuScore.getStatistics().getCount_50() + "** ");
            strBuilder.append(OsuEmbed.HIT_MISS_EMOJI + "**"
                    + osuScore.getStatistics().getCount_miss() + "** ");
            eb.addField(resultFieldTitle, strBuilder.toString(), false);

            eb.setFooter(
                    OsuEmbed.RECENT_RETRY + osuScore.getRetry() + "  "
                            + OffsetDateTime.parse(osuScore.getCreated_at()).toInstant().toString().replace("T", " "),
                    OsuModes.none.getModeIconURL(osuMode));

            e.getHook().editOriginalEmbeds(eb.build()).queue();
        } catch (Exception ex) {
            ex.printStackTrace();
            new DefaultEmbed().sendAndDeleteMessageAfter(e, new DefaultEmbed().SOMETHING_WENT_WRONG());
            return;
        }
    }

    // TODO
    public void TOP_COMMAND_HANDLER(SlashCommandInteractionEvent e) {

    }

    public void LINK_COMMAND_HANDLER(SlashCommandInteractionEvent e) {
        if (e.getOption(OsuCommands.userNameOrIDArg) == null) {
            new DefaultEmbed().sendAndDeleteMessageAfter(e,
                    new DefaultEmbed().INVALID_ID(null));
            return;
        }
        try {
            String option = e.getOption(OsuCommands.userNameOrIDArg).getAsString();

            OsuUserData user = osuRequest.getUser(option);
            if (user == null) {
                new DefaultEmbed().sendAndDeleteMessageAfter(e,
                        new DefaultEmbed().INVALID_ID(option));
                return;

            }
            OsuUserDiscordData osu = new OsuUserDiscordData();

            osu.setDiscordID(e.getUser().getIdLong());
            osu.setOsuID(Long.toString(user.getId()));
            osu.setOsuUserName(option);

            if (!osuDataManager.isLinkedWithDiscord(osu)) {
                osuDataManager.addOsuDiscordLink(osu);
                new DefaultEmbed().sendAndDeleteMessageAfter(e, new DefaultEmbed()
                        .ACCOUNT_NOW_LINKED(e.getUser().getName(), Games.OSU.getValue()));
                return;
            } else {
                osuDataManager.changeOsuDiscordLink(user, e.getUser().getIdLong());
                new DefaultEmbed().sendAndDeleteMessageAfter(e, new DefaultEmbed()
                        .ACCOUNT_NOW_LINKED(e.getUser().getName(), Games.OSU.getValue()));
                return;
            }

        } catch (Exception ex) {
            new DefaultEmbed().sendAndDeleteMessageAfter(e,
                    new DefaultEmbed().SOMETHING_WENT_WRONG());
            ex.printStackTrace();
            return;
        }
    }

    public void UNLINK_COMMAND_HANDLER(SlashCommandInteractionEvent e) {
        long userDiscordID = e.getUser().getIdLong();
        try {
            if (osuDataManager.isLinkedWithDiscord(userDiscordID)) {
                osuDataManager.removeOsuDiscordLink(userDiscordID);
                new DefaultEmbed().sendAndDeleteMessageAfter(e,
                        new DefaultEmbed().ACCOUNT_NOW_UNLINKED(e.getUser().getName(),
                                Games.OSU.getValue()));
                return;
            } else {
                new DefaultEmbed().sendAndDeleteMessageAfter(e,
                        new DefaultEmbed().ACCOUNT_IS_NOT_LINKED(e.getUser().getName(),
                                Games.OSU.getValue()));
                return;
            }
        } catch (Exception ex) {
            new DefaultEmbed().sendAndDeleteMessageAfter(e,
                    new DefaultEmbed().SOMETHING_WENT_WRONG());
            ex.printStackTrace();
            return;
        }
    }

    public void ROLL_COMMAND_HANDLER(SlashCommandInteractionEvent e) {
        long num;
        EmbedBuilder eb;

        if (e.getOption(OsuCommands.numberArg) == null) {
            num = 100;
        } else {
            num = (long) e.getOption(OsuCommands.numberArg).getAsDouble();
        }

        eb = new EmbedBuilder();

        eb.setColor(DefaultEmbed.DEFAULT_EMBED_COLOR);
        eb.setTitle(new DefaultEmbed().ROLL_TITLE(e.getUser().getName()));

        if (gameMlemDataManager.isHighPriorityUser((e.getUser().getIdLong()))) {
            eb.setFooter(DefaultEmbed.ROLL_FOOTER,
                    "https://www.publicdomainpictures.net/pictures/380000/nahled/3-premium-gold-dice.png");
        } else {
            eb.setFooter(DefaultEmbed.ROLL_FOOTER, "https://pngimg.com/uploads/dice/dice_PNG49.png");
        }

        if (num >= 0) {
            eb.setDescription(new DefaultEmbed().ROLL_SCORE(new OsuUtilsMethod().roll(num)));
            e.deferReply().queue();
            e.getHook().editOriginalEmbeds(eb.build()).queue();
            return;
        } else if (num <= 0) {
            eb.setDescription(new DefaultEmbed().ROLL_SCORE(new OsuUtilsMethod().roll(100)));
            e.deferReply().queue();
            e.getHook().editOriginalEmbeds(eb.build()).queue();
            return;
        }

    }

    public void STATS_COMMAND_HANDLER(SlashCommandInteractionEvent e) {
        OsuModes mode;
        OsuUserData osuUser = null;
        EmbedBuilder eb = new EmbedBuilder();
        OsuUserDiscordData dUser = null;
        User discordMetionUser;
        StringBuilder strBuilder;

        if (e.getOption(OsuCommands.osuModeArg) == null) {
            mode = OsuModes.osu;
        } else if (OsuModes.valueOf(e.getOption(OsuCommands.osuModeArg).getAsString().toLowerCase()) == null) {
            new DefaultEmbed().sendAndDeleteMessageAfter(e,
                    new OsuEmbed().WRONG_OSU_MODE(e.getOption(OsuCommands.osuModeArg).getAsString()));
            return;
        } else {
            mode = OsuModes.valueOf(e.getOption(OsuCommands.osuModeArg).getAsString().toLowerCase());
        }

        if (e.getOption(OsuCommands.userNameOrIDArg) != null) {
            discordMetionUser = e.getOption(OsuCommands.userNameOrIDArg).getAsUser();
            dUser = osuDataManager.findOsuDiscordLink(discordMetionUser.getIdLong());
            if (dUser == null) {
                new DefaultEmbed().sendAndDeleteMessageAfter(e,
                        new DefaultEmbed().INVALID_ID(discordMetionUser.getAsMention()));
            } else {
                osuUser = osuRequest.getUser(dUser.getOsuID(), mode);
            }
        } else {
            dUser = osuDataManager.findOsuDiscordLink(e.getUser().getIdLong());
            osuUser = osuRequest.getUser(dUser.getOsuID(), mode);
        }

        if (osuUser == null) {
            new DefaultEmbed().sendAndDeleteMessageAfter(e,
                    new DefaultEmbed().SOMETHING_WENT_WRONG());
            return;
        }
        if (mode == OsuModes.osu) {
            eb.setAuthor(OsuEmbed.PROFILE_STATS_AUTHOR + osuUser.getUsername(), null, OsuEmbed.OSU_ICON_URL);
        } else if (mode == OsuModes.mania) {
            eb.setAuthor(OsuEmbed.PROFILE_STATS_AUTHOR + osuUser.getUsername(), null, OsuEmbed.MANIA_ICON_URL);
        } else if (mode == OsuModes.fruits) {
            eb.setAuthor(OsuEmbed.PROFILE_STATS_AUTHOR + osuUser.getUsername(), null, OsuEmbed.FRUITS_ICON_URL);
        } else if (mode == OsuModes.taiko) {
            eb.setAuthor(OsuEmbed.PROFILE_STATS_AUTHOR + osuUser.getUsername(), null, OsuEmbed.TAIKO_ICON_URL);
        }

        eb.setColor(DefaultEmbed.DEFAULT_EMBED_COLOR);

        //
        String infoFieldTitle = OsuEmbed.INFO_FIELD_TITLE;
        String infoFieldValue = "";
        strBuilder = new StringBuilder(infoFieldValue);
        if (gameMlemDataManager.isLowPriorityUser(e.getUser().getIdLong())) {
            strBuilder.append(OsuEmbed.USER_RANK_GLOBAL + "**Harumachi**\n");
            strBuilder.append(OsuEmbed.USER_PP + "**Harumachi**\n");
            strBuilder.append(OsuEmbed.USER_ACCURACY + "**Harumachi**\n");
            strBuilder.append(OsuEmbed.USER_LEVEL + "**Harumachi**\n");
            strBuilder.append(OsuEmbed.USER_PLAYCOUNTS + "**Harumachi**\n");
            strBuilder.append(
                    OsuEmbed.USER_TOTAL_PLAY_HOURS
                            + new ConvertSecondToDateString().convert(osuUser.getStatistics().getPlay_time()));
            eb.addField(new MessageEmbed.Field(infoFieldTitle, strBuilder.toString(), true));
        } else {
            strBuilder.append(OsuEmbed.USER_RANK_GLOBAL + "**#"
                    + String.format("%,d", osuUser.getStatistics().getGlobal_rank()) + "(#"
                    + osuUser.getStatistics().getCountry_rank() + ")**\n");
            strBuilder.append(OsuEmbed.USER_PP + "**" + osuUser.getStatistics().getPp() + "**\n");
            strBuilder.append(
                    OsuEmbed.USER_ACCURACY + "**" + String.format("%.2f", osuUser.getStatistics().getHit_accuracy())
                            + "%**\n");
            strBuilder.append(OsuEmbed.USER_LEVEL + "**" + osuUser.getStatistics().getLevel().getCurrent() + " - "
                    + osuUser.getStatistics().getLevel().getProgress() + "%**\n");
            strBuilder.append(OsuEmbed.USER_PLAYCOUNTS + osuUser.getStatistics().getPlay_count() + "\n");
            strBuilder.append(
                    OsuEmbed.USER_TOTAL_PLAY_HOURS
                            + new ConvertSecondToDateString().convert(osuUser.getStatistics().getPlay_time()));
            eb.addField(new MessageEmbed.Field(infoFieldTitle, strBuilder.toString(), true));
        }

        //
        String accountFieldTitle = OsuEmbed.ACCOUNT_FIELD_TITLE;
        String accountFieldValue = "";
        strBuilder = new StringBuilder(accountFieldValue);
        if (!osuUser.getIs_online()) {
            strBuilder.append(OsuEmbed.USER_STATUS + ":black_heart:" + OsuEmbed.STATUS_OFFLINE + "\n");
        } else {
            strBuilder.append(OsuEmbed.USER_STATUS + ":green_heart:" + OsuEmbed.STATUS_ONLINE + "\n");
        }
        strBuilder.append(OsuEmbed.USER_COUNTRY + ":flag_" + osuUser.getCountry_code().toLowerCase() + ":\n");
        eb.addField(new MessageEmbed.Field(accountFieldTitle, strBuilder.toString(), true));

        eb.setThumbnail(osuUser.getAvatar_url());

        eb.setImage(osuRequest.getGraphLine(osuUser));

        //
        String playCountsFieldTitle = OsuEmbed.PLAY_COUNNTS_EVERY_MONTH_FIELD_TITLE;
        String playCountsFieldValue = "";

        strBuilder = new StringBuilder(playCountsFieldTitle);
        eb.addField(new MessageEmbed.Field(playCountsFieldTitle, playCountsFieldValue, false));

        eb.setFooter(DefaultEmbed.STATS_FOOTER,
                "https://www.shareicon.net/data/512x512/2016/08/18/808729_graph_512x512.png");

        try {
            e.getHook().editOriginalEmbeds(eb.build()).queue();
        } catch (Exception ex) {
            new DefaultEmbed().sendAndDeleteMessageAfter(e, new DefaultEmbed().SOMETHING_WENT_WRONG());
        }
    }

    public void AVATAR_COMMAND_HANDLER(SlashCommandInteractionEvent e) {
        OsuUserData user = null;
        EmbedBuilder eb = new EmbedBuilder();
        OsuUserDiscordData dUser = null;

        dUser = osuDataManager.findOsuDiscordLink(e.getUser().getIdLong());
        user = osuRequest.getUser(dUser.getOsuID());

        if (user == null) {
            new DefaultEmbed().sendAndDeleteMessageAfter(e, new DefaultEmbed().SOMETHING_WENT_WRONG());
            return;
        }

        eb.setColor(new DefaultEmbed().DEFAULT_EMBED_COLOR);
        eb.setTitle(new OsuEmbed().AVATAR_TITLE(user.getUsername()));
        eb.setImage(user.getAvatar_url());

        e.getHook().editOriginalEmbeds(eb.build()).queue();

    }
}

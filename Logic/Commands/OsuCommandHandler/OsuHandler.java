package src.ctt.GameMlemBot.Logic.Commands.OsuCommandHandler;

import java.net.Socket;
import java.text.SimpleDateFormat;
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
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuDataManager;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuUserDiscordData;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuRecentScore.OsuRecentScore;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuUserData.OsuUserData;
import src.ctt.GameMlemBot.Meta.HighPriorityUsers;
import src.ctt.GameMlemBot.Utils.ConvertSecondToDateString;
import src.ctt.GameMlemBot.Utils.DefaultBotMessages;
import src.ctt.GameMlemBot.Utils.IsHighPriorityUser;
import src.ctt.GameMlemBot.Utils.IsLowPriorityUser;
import src.ctt.GameMlemBot.Utils.DefaultEmbedField.DefaultEmbed;
import src.ctt.GameMlemBot.Utils.DefaultEmbedField.OsuEmbed;

public class OsuHandler {
    private OsuRequest osuRequest = new OsuRequest();
    private OsuDataManager osuDataManager = new OsuDataManager();

    public void osuCommandHander(SlashCommandInteractionEvent e) {
        // LINK HANDLER
        if (e.getSubcommandName().equalsIgnoreCase(OsuSubCommands.LINK_COMMAND)) {
            LINK_COMMAND_HANDLER(e);
        }
        // ! FIRST STEP: DOUBLE CHECK IF USER HAD LINKED WITH DISCORD
        OsuUserDiscordData osuDiscord = osuDataManager.findOsuDiscordLink(e.getUser().getIdLong());
        if (!e.isAcknowledged()) {
            if (osuDiscord == null) {
                new DefaultEmbed().sendAndDeleteMessageAfter(e,
                        new DefaultBotMessages().ACCOUNT_IS_NOT_LINKED(e.getUser().getName(),
                                Games.OSU.getValue()));
                return;
            }
        }
        // ! ========================================================================

        // UNLINK HANDLER
        if (e.getSubcommandName().equalsIgnoreCase(OsuSubCommands.UNLINK_COMMAND)) {
            UNLINK_COMMAND_HANDLER(e);
        }
        // STATS HANDLER
        else if (e.getSubcommandName().equalsIgnoreCase(OsuSubCommands.STATS_COMMAND)) {
            STATS_COMMAND_HANDLER(e);
        }

        // TOP HANDLER
        // TODO
        else if (e.getSubcommandName().equalsIgnoreCase(OsuSubCommands.TOP_COMMAND)) {
            TOP_COMMAND_HANDLER(e);
        }
        // RECENT HANDLER
        else if (e.getSubcommandName().equalsIgnoreCase(OsuSubCommands.RECENT_COMMAND)) {
            RECENT_COMMNAD_HANDLER(e);
        }

        // ROLL HANDLER
        else if (e.getSubcommandName().equalsIgnoreCase(OsuSubCommands.ROLL_COMMAND)) {
            ROLL_COMMAND_HANDLER(e);
        }

        // AVATAR HANDLER
        else if (e.getSubcommandName().equalsIgnoreCase(OsuSubCommands.AVATAR_COMMAND)) {
            AVATAR_COMMAND_HANDLER(e);
        }
    }

    public void RECENT_COMMNAD_HANDLER(SlashCommandInteractionEvent e) {
        User disUser = null;
        OsuRecentScore osuScore = null;
        EmbedBuilder eb = new EmbedBuilder();
        StringBuilder strBuilder;

        try {
            if (e.getOption(OsuSubCommands.userNameOrIDArg) == null) {
                disUser = null;
                osuScore = osuRequest.getOsuRecentScore(osuDataManager.getOsuID(e.getUser().getIdLong()))[0];
            } else {
                if (osuDataManager.getOsuID(e.getIdLong()) == null) {
                    new DefaultEmbed().sendAndDeleteMessageAfter(e,
                            new DefaultBotMessages().ACCOUNT_IS_NOT_LINKED(disUser.getName(),
                                    Games.OSU.getValue()));
                    return;
                }
                disUser = e.getOption(OsuSubCommands.userNameOrIDArg).getAsUser();
                osuScore = osuRequest.getOsuRecentScore(osuDataManager.getOsuID(disUser.getIdLong()))[0];
            }

            if (OsuModes.valueOf(osuScore.getMode()) == OsuModes.osu) {
                eb.setFooter(e.getTimeCreated().toString(), OsuEmbed.OSU_ICON_URL);
            } else if (OsuModes.valueOf(osuScore.getMode()) == OsuModes.mania) {
                eb.setFooter(osuScore.getBeatmapset().getTitle(), OsuEmbed.MANIA_ICON_URL);
            } else if (OsuModes.valueOf(osuScore.getMode()) == OsuModes.fruits) {
                eb.setFooter(osuScore.getBeatmapset().getTitle(), OsuEmbed.FRUITS_ICON_URL);
            } else if (OsuModes.valueOf(osuScore.getMode()) == OsuModes.taiko) {
                eb.setFooter("",
                        OsuEmbed.TAIKO_ICON_URL);
            }

            eb.setColor(DefaultEmbed.DEFAULT_EMBED_COLOR);
            eb.setAuthor(osuScore.getUser().getUsername()
                    + " Vừa Mới Chơi:",
                    null, osuScore.getUser().getAvatar_url());

            eb.setThumbnail(osuScore.getBeatmapset().getCovers().getList());

            eb.setTitle(osuScore.getBeatmapset().getTitle()
                    + " by " + osuScore.getBeatmapset().getArtist()
                    + " [" + osuScore.getBeatmap().getVersion() + "]"
                    + " [" + osuScore.getBeatmap().getDifficulty_rating() + "★]", osuScore.getBeatmap().getUrl());

            String resultFieldTitle = "**Kết Quả:**";
            String resultFieldValue = "";
            strBuilder = new StringBuilder(resultFieldValue);
            strBuilder.append("Tổng Pepe: " + osuScore.getPp() + "\n");
            strBuilder.append("<:hit300:698619525691867267> " + osuScore.getStatistics().getCount_300() + " ");
            strBuilder.append("<:hit100:698619509224898582> " + osuScore.getStatistics().getCount_100() + " ");
            strBuilder.append("<:hit50:698619487930417193> " + osuScore.getStatistics().getCount_50() + " ");
            strBuilder.append("<:miss:698619620461903962> " + osuScore.getStatistics().getCount_miss() + " ");
            eb.addField(resultFieldTitle, strBuilder.toString(), false);

            e.deferReply().queue();
            e.getHook().editOriginalEmbeds(eb.build()).queue();
        } catch (Exception ex) {
            ex.printStackTrace();
            new DefaultEmbed().sendAndDeleteMessageAfter(e, new DefaultBotMessages().SOMETHING_WENT_WRONG());
            return;
        }
    }

    // TODO
    public void TOP_COMMAND_HANDLER(SlashCommandInteractionEvent e) {

    }

    public void LINK_COMMAND_HANDLER(SlashCommandInteractionEvent e) {
        if (e.getOption(OsuSubCommands.userNameOrIDArg) == null) {
            new DefaultEmbed().sendAndDeleteMessageAfter(e,
                    new DefaultBotMessages().INVALID_ID(null));
            return;
        }
        try {
            String option = e.getOption(OsuSubCommands.userNameOrIDArg).getAsString();

            OsuUserData user = osuRequest.getUser(option);
            OsuUserDiscordData osu = new OsuUserDiscordData();

            osu.setDiscordID(e.getUser().getIdLong());
            osu.setOsuID(Long.toString(user.getId()));
            osu.setOsuUserName(option);

            if (!osuDataManager.isLinkedWithDiscord(osu)) {
                osuDataManager.addOsuDiscordLink(osu);
                new DefaultEmbed().sendAndDeleteMessageAfter(e, new DefaultBotMessages()
                        .ACCOUNT_NOW_LINKED(e.getUser().getName(), Games.OSU.getValue()));
                return;
            } else {
                osuDataManager.changeOsuDiscordLink(user, e.getUser().getIdLong());
                new DefaultEmbed().sendAndDeleteMessageAfter(e, new DefaultBotMessages()
                        .ACCOUNT_NOW_LINKED(e.getUser().getName(), Games.OSU.getValue()));
                return;
            }

        } catch (Exception ex) {
            new DefaultEmbed().sendAndDeleteMessageAfter(e,
                    new DefaultBotMessages().SOMETHING_WENT_WRONG());
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
                        new DefaultBotMessages().ACCOUNT_NOW_UNLINKED(e.getUser().getName(),
                                Games.OSU.getValue()));
                return;
            } else {
                new DefaultEmbed().sendAndDeleteMessageAfter(e,
                        new DefaultBotMessages().ACCOUNT_IS_NOT_LINKED(e.getUser().getName(),
                                Games.OSU.getValue()));
                return;
            }
        } catch (Exception ex) {
            new DefaultEmbed().sendAndDeleteMessageAfter(e,
                    new DefaultBotMessages().SOMETHING_WENT_WRONG());
            ex.printStackTrace();
            return;
        }
    }

    public void ROLL_COMMAND_HANDLER(SlashCommandInteractionEvent e) {
        long num;
        EmbedBuilder eb;

        if (e.getOption(OsuSubCommands.numberArg) == null) {
            num = 100;
        } else {
            num = (long) e.getOption(OsuSubCommands.numberArg).getAsDouble();
        }

        eb = new EmbedBuilder();

        eb.setColor(DefaultEmbed.DEFAULT_EMBED_COLOR);
        eb.setTitle(new DefaultEmbed().ROLL_TITLE(e.getUser().getName()));

        if (new IsHighPriorityUser().Check(e.getUser().getIdLong())) {
            eb.setFooter(DefaultEmbed.ROLL_FOOTER,
                    "https://www.publicdomainpictures.net/pictures/380000/nahled/3-premium-gold-dice.png");
        } else {
            eb.setFooter(DefaultEmbed.ROLL_FOOTER, "https://pngimg.com/uploads/dice/dice_PNG49.png");
        }

        if (num >= 0) {
            eb.setDescription(new DefaultEmbed().ROLL_SCORE(new OsuNormalCommands().roll(num)));
            e.deferReply().queue();
            e.getHook().editOriginalEmbeds(eb.build()).queue();
            return;
        } else if (num <= 0) {
            eb.setDescription(new DefaultEmbed().ROLL_SCORE(new OsuNormalCommands().roll(100)));
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
        User targetUser;
        StringBuilder strBuilder;

        if (e.getOption(OsuSubCommands.osuModeArg) == null) {
            mode = OsuModes.osu;
        } else if (OsuModes.valueOf(e.getOption(OsuSubCommands.osuModeArg).getAsString().toLowerCase()) == null) {
            new DefaultEmbed().sendAndDeleteMessageAfter(e,
                    new DefaultBotMessages().WRONG_OSU_MODE(e.getOption(OsuSubCommands.osuModeArg).getAsString()));
            return;
        } else {
            mode = OsuModes.valueOf(e.getOption(OsuSubCommands.osuModeArg).getAsString().toLowerCase());
        }

        if (e.getOption(OsuSubCommands.userNameOrIDArg) != null) {
            targetUser = e.getOption(OsuSubCommands.userNameOrIDArg).getAsUser();
            dUser = osuDataManager.findOsuDiscordLink(targetUser.getIdLong());
            if (dUser == null) {
                new DefaultEmbed().sendAndDeleteMessageAfter(e,
                        new DefaultBotMessages().INVALID_ID(targetUser.getAsMention()));
            } else {
                osuUser = osuRequest.getUser(dUser.getOsuID(), mode);
            }
        } else {
            dUser = osuDataManager.findOsuDiscordLink(e.getUser().getIdLong());
            osuUser = osuRequest.getUser(dUser.getOsuID(), mode);
        }

        if (osuUser == null) {
            new DefaultEmbed().sendAndDeleteMessageAfter(e,
                    new DefaultBotMessages().SOMETHING_WENT_WRONG());
            return;
        }
        if (mode == OsuModes.osu) {
            eb.setAuthor(osuUser.getUsername(), null, OsuEmbed.OSU_ICON_URL);
        } else if (mode == OsuModes.mania) {
            eb.setAuthor(osuUser.getUsername(), null, OsuEmbed.MANIA_ICON_URL);
        } else if (mode == OsuModes.fruits) {
            eb.setAuthor(osuUser.getUsername(), null, OsuEmbed.FRUITS_ICON_URL);
        } else if (mode == OsuModes.taiko) {
            eb.setAuthor(osuUser.getUsername(), null, OsuEmbed.TAIKO_ICON_URL);
        }

        eb.setColor(DefaultEmbed.DEFAULT_EMBED_COLOR);

        //
        String infoFieldTitle = "**Thông Tin:**";
        String infoFieldValue = "";
        strBuilder = new StringBuilder(infoFieldValue);
        if (new IsLowPriorityUser().Check(e.getUser().getIdLong())) {
            strBuilder.append("Rank: **Harumachi**\n");
            strBuilder.append("Pepe: **Harumachi**\n");
            strBuilder.append("Chính xác: **Harumachi**\n");
            strBuilder.append("Cấp độ: **Harumachi**\n");
            strBuilder.append("Số màn chơi: **Harumachi**\n");
            strBuilder.append(
                    "Số giờ chơi: " + new ConvertSecondToDateString().convert(osuUser.getStatistics().getPlay_time()));
            eb.addField(new MessageEmbed.Field(infoFieldTitle, strBuilder.toString(), true));
        } else {
            strBuilder.append("Rank: **#" + osuUser.getStatistics().getGlobal_rank() + "(#"
                    + osuUser.getStatistics().getCountry_rank() + ")**\n");
            strBuilder.append("Pepe: **" + osuUser.getStatistics().getPp() + "**\n");
            strBuilder.append("Chính xác: **" + osuUser.getStatistics().getHit_accuracy() + "%**\n");
            strBuilder.append("Cấp độ: **" + osuUser.getStatistics().getLevel().getCurrent() + " - "
                    + osuUser.getStatistics().getLevel().getProgress() + "%**\n");
            strBuilder.append("Số màn chơi: " + osuUser.getStatistics().getPlay_count() + "\n");
            strBuilder.append(
                    "Số giờ chơi: " + new ConvertSecondToDateString().convert(osuUser.getStatistics().getPlay_time()));
            eb.addField(new MessageEmbed.Field(infoFieldTitle, strBuilder.toString(), true));
        }

        //
        String accountFieldTitle = "**Tài Khoản:**";
        String accountFieldValue = "";
        strBuilder = new StringBuilder(accountFieldValue);
        if (!osuUser.getIs_online()) {
            strBuilder.append("Trạng thái: " + ":black_heart: Offline\n");
        } else {
            strBuilder.append("Trạng thái: " + ":green_heart: Online\n");
        }
        strBuilder.append("Người: " + ":flag_" + osuUser.getCountry_code().toLowerCase() + ":\n");
        eb.addField(new MessageEmbed.Field(accountFieldTitle, strBuilder.toString(), true));

        eb.setThumbnail(osuUser.getAvatar_url());

        if (new IsHighPriorityUser().Check(e.getUser().getIdLong())) {
            eb.setImage(osuRequest.getGraphLine(osuUser));
        } else {

        }

        //
        String playCountsFieldTitle = "**Số Màn Chơi Mỗi Tháng:**";
        String playCountsFieldValue = "";

        strBuilder = new StringBuilder(playCountsFieldTitle);
        eb.addField(new MessageEmbed.Field(playCountsFieldTitle, playCountsFieldValue, false));

        eb.setFooter(DefaultEmbed.STATS_FOOTER,
                "https://www.shareicon.net/data/512x512/2016/08/18/808729_graph_512x512.png");

        try {
            e.deferReply(false).queue();
            e.getHook().editOriginalEmbeds(eb.build()).queue();
        } catch (Exception ex) {
            System.out.println("Can't send Osu! Status command");
        }
    }

    public void AVATAR_COMMAND_HANDLER(SlashCommandInteractionEvent e) {
        OsuUserData user = null;
        EmbedBuilder eb = new EmbedBuilder();
        OsuUserDiscordData dUser = null;

        dUser = osuDataManager.findOsuDiscordLink(e.getUser().getIdLong());
        user = osuRequest.getUser(dUser.getOsuID());

        if (user == null) {
            new DefaultEmbed().sendAndDeleteMessageAfter(e, new DefaultBotMessages().SOMETHING_WENT_WRONG());
            return;
        }

        eb.setColor(new DefaultEmbed().DEFAULT_EMBED_COLOR);
        eb.setTitle(new OsuEmbed().AVATAR_TITLE(user.getUsername()));
        eb.setImage(user.getAvatar_url());

        e.deferReply().queue();
        e.getHook().editOriginalEmbeds(eb.build()).queue();

    }
}

package src.ctt.GameMlemBot.Language;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonInteraction;
import src.ctt.GameMlemBot.Enums.GameMlemGames;
import src.ctt.GameMlemBot.Enums.OsuModes;
import src.ctt.GameMlemBot.Language.GameMlemEmbeds.GameMlemEmbed;
import src.ctt.GameMlemBot.Utils.DecimalFormatter;

public class DefaultEmbed {
    public static final String MESSAGE_PREFIX = "!";
    public static final Color DEFAULT_EMBED_COLOR = Color.RED;
    public static final Color ERROR_COLOR = Color.RED;
    public static final Color SUCCESS_COLOR = Color.GREEN;

    public static final String ROLL_FOOTER = "Người không chơi là người thắng!";
    public static final String STATS_FOOTER = "Xem thông số à...";

    public static final String DEFAULT_CURRENCY = "GND";

    public final String ROLL_TITLE(long discordID) {
        return "**<@" + discordID + "> đã xúc sắc được:" + "**";
    }

    public final String ROLL_SCORE(long score) {
        return "**" + score + " điểm" + "**";
    }

    public static final String DISCORD_BOT_STARTED = "Game Mlem Discord Bot Is Started!";

    public static final String BOT_DESCRIPTION = "";

    public final String OSU_USER_LINK(long discordID) {
        return "<@" + discordID + "> had linked their osu account to discord";
    }

    public void sendAndDeleteMessageAfter(SlashCommandInteraction e, MessageEmbed mes) {
        e.getHook().editOriginalEmbeds(mes).queue();
        e.getHook().deleteOriginal().queueAfter(10, TimeUnit.SECONDS);
    }

    public void sendAndDeleteMessageAfter(MessageReceivedEvent e, MessageEmbed mes) {
        e.getMessage().editMessageEmbeds(mes).queue();
        e.getMessage().delete().queueAfter(10, TimeUnit.SECONDS);

    }

    public void sendEphemeralMessage(ButtonInteraction e, String mes) {
        e.reply(mes).setEphemeral(true).queue();
    }

    public final MessageEmbed ACCOUNT_IS_NOT_LINKED(long discordID, String game) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        if (!game.equalsIgnoreCase("") || game != null) {
            eb.setDescription("**<@" + discordID + "> cần phải đăng ký với " + game
                    + " để sử dụng những tính năng khác. **");
            return eb.build();
        } else {
            eb.setDescription("**<@" + discordID + "> chưa được liên kết**");
            return eb.build();
        }

    }

    public final MessageEmbed ACCOUNT_NOW_LINKED(long discordID, String game) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        if (!game.equalsIgnoreCase("") || game != null) {
            eb.setDescription("**<@" + discordID + "> đã được liên kết với " + game + "**");
            return eb.build();
        } else {
            eb.setDescription("**<@" + discordID + "> chưa được liên kết**");
            return eb.build();
        }
    }

    public final MessageEmbed ACCOUNT_NOW_UNLINKED(long discordID, String game) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        if (!game.equalsIgnoreCase("") || game != null) {
            eb.setDescription("**" + "Đã hủy liên kết " + game + " với tài khoản **<@" + discordID + ">**");
            return eb.build();
        } else {
            eb.setDescription("**" + "Đã hủy liên kết với tài khoản **<@" + discordID + ">**");
            return eb.build();
        }
    }

    public final MessageEmbed ALREADY_LINKED_WITH_DISCORD(long discordID, String game) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        if (!game.equalsIgnoreCase("") || game != null) {
            eb.setDescription("**<@" + discordID + "> đã được liên kết với " + game + " rồi" + "**");
            return eb.build();
        } else {
            eb.setDescription("**<@" + discordID + "> đã được liên kết rồi**");
            return eb.build();
        }
    }

    public final MessageEmbed INVALID_ID(String id) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        if (id == null) {
            eb.setDescription("**ID hoặc Username không hợp lệ**");
            return eb.build();
        } else {
            eb.setDescription("**" + id + " không hợp lệ**");
            return eb.build();
        }
    }

    public final MessageEmbed SOMETHING_WENT_WRONG() {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        eb.setDescription("**Đã xảy ra lỗi ngoài ý muốn của Thắng, vui lòng thử lại sau...**");

        return eb.build();

    }

    public final MessageEmbed WRONG_ENUM_TYPE(String mode, Class c) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);
        String modes = "";
        byte modesCount = 0;
        for (Field field : c.getFields()) {
            if (!field.getName().equalsIgnoreCase("none")) {
                modes += field.getName() + " ";
                modesCount++;
            }
        }
        if (mode == null) {
            eb.setDescription("**Không hợp lệ, hiện chỉ có " + modesCount + " chế độ: " + modes + "**");
        } else {
            eb.setDescription("**" + mode + " không hợp lệ, hiện chỉ có " + modesCount + " chế độ: " + modes + "**");
        }
        return eb.build();
    }

    public final MessageEmbed NO_GAME_ROOM(String game) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        eb.setDescription("**" + "Hiện không có phòng " + game + " nào được tạo" + "**");
        return eb.build();
    }

    public final MessageEmbed CAN_NOT_AFFOR_EMBED(long discordID, String money) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        eb.setDescription("**<@" + discordID + "> bạn không có đủ " + money + " để thực hiện**");
        return eb.build();
    }

    public final String CAN_NOT_AFFOR_STRING(Double money) {
        if (money == null) {
            return "Bạn không có đủ tiền";
        }
        return "Bạn không có đủ " + new DecimalFormatter().formatVND(money) + DefaultEmbed.DEFAULT_CURRENCY;
    }

    public final String IS_NOT_IN_ROOM(String game) {
        if (game == null) {
            return "Bạn không đang ở trong phòng";
        }
        return "Bạn không đang ở trong phòng " + game;
    }

    public final String CAN_NOT_INTERACT_ANYMORE() {

        return "Đã hết thời gian để thao tác";
    }

    public final String ALREADY_IN_MATCH(String game) {
        return "Bạn đã ở trong phòng " + game + " rồi";
    }

    public final MessageEmbed OUT_OF_PLAY_TIME(String game) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        eb.setDescription("**Đã hết số lần chơi " + game + " trong ngày**");

        return eb.build();
    }

    public final MessageEmbed ALREADY_HAD_A_ROOM(String game) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        eb.setDescription("**Đã có phòng " + game + " rồi**");

        return eb.build();
    }
}

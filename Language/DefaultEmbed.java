package src.ctt.GameMlemBot.Language;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import src.ctt.GameMlemBot.Enum.OsuModes;

public class DefaultEmbed {
    public static final Color DEFAULT_EMBED_COLOR = Color.RED;
    public static final Color ERROR_COLOR = Color.RED;

    public static final String ROLL_FOOTER = "Người không chơi là người thắng!";
    public static final String STATS_FOOTER = "Xem thông số à...";

    public final String ROLL_TITLE(String name) {
        return "**" + name + " đã xúc sắc được:" + "**";
    }

    public final String ROLL_SCORE(long score) {
        return "**" + score + " điểm" + "**";
    }

    public final String AVATAR_TITLE(String name) {
        return "**Ảnh đại diện " + OsuModes.osu + " của " + name + ":**";
    }

    public static final String DISCORD_BOT_STARTED = "Game Mlem Discord Bot Is Started!";

    public static final String BOT_DESCRIPTION = "";

    public final String OSU_USER_LINK(String name) {
        return name + " had linked their osu account to discord";
    }

    public void sendAndDeleteMessageAfter(SlashCommandInteraction e, MessageEmbed mes) {
        e.getHook().editOriginalEmbeds(mes).queue();
        e.getHook().deleteOriginal().queueAfter(10, TimeUnit.SECONDS);
    }

    public final MessageEmbed ACCOUNT_IS_NOT_LINKED(String name, String game) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        if (!game.equalsIgnoreCase("") || game != null) {
            eb.setDescription("**" + name + " chưa được liên kết với " + game + "**");
            return eb.build();
        } else {
            eb.setDescription("**" + name + " chưa được liên kết**");
            return eb.build();
        }

    }

    public final MessageEmbed ACCOUNT_NOW_LINKED(String name, String game) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        if (!game.equalsIgnoreCase("") || game != null) {
            eb.setDescription("**" + name + " đã được liên kết với " + game + "**");
            return eb.build();
        } else {
            eb.setDescription("**" + name + " chưa được liên kết**");
            return eb.build();
        }
    }

    public final MessageEmbed ACCOUNT_NOW_UNLINKED(String name, String game) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        if (!game.equalsIgnoreCase("") || game != null) {
            eb.setDescription("**" + "Đã hủy liên kết " + game + " với tài khoản " + name + "**");
            return eb.build();
        } else {
            eb.setDescription("**" + "Đã hủy liên kết với tài khoản " + name + "**");
            return eb.build();
        }
    }

    public final MessageEmbed ALREADY_LINKED_WITH_DISCORD(String name, String game) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        if (!game.equalsIgnoreCase("") || game != null) {
            eb.setDescription("**" + name + " đã được liên kết với " + game + " rồi" + "**");
            return eb.build();
        } else {
            eb.setDescription("**" + name + " đã được liên kết rồi**");
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

}

package src.ctt.GameMlemBot.Utils;

import java.lang.reflect.Field;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import src.ctt.GameMlemBot.Enum.OsuModes;
import src.ctt.GameMlemBot.Utils.DefaultEmbedField.DefaultEmbed;

public class DefaultBotMessages {
    public static final String DISCORD_BOT_STARTED = "Game Mlem Discord Bot Is Started!";
    public static final String OSU_GAME = "Osu!";
    public static final String BRAWLHALLA_GAME = "Brawlhalla";

    public static final String BOT_ACTIVITY = "Game Mlem";
    public static final String BOT_DESCRIPTION = "";

    public final String OSU_USER_LINK(String name) {
        return name + " had linked their osu account to discord";
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

    public final MessageEmbed WRONG_OSU_MODE(String mode) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);
        String modes = "";
        byte modesCount = (byte) OsuModes.class.getFields().length;
        for (Field field : OsuModes.class.getFields()) {
            modes += field.getName() + " ";
        }
        eb.setDescription(mode + " không hợp lệ, hiện chỉ có " + modesCount + " chế độ: " + modes);
        return eb.build();
    }
}

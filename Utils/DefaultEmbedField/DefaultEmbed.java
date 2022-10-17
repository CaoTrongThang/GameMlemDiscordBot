package src.ctt.GameMlemBot.Utils.DefaultEmbedField;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import src.ctt.GameMlemBot.Utils.DefaultBotMessages;

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
        return "**Ảnh đại diện " + new DefaultBotMessages().OSU_GAME + " của " + name + ":**";
    }

    public void sendAndDeleteMessageAfter(SlashCommandInteraction e, MessageEmbed mes) {
        e.replyEmbeds(mes).queue();
        e.getHook().deleteOriginal().queueAfter(5, TimeUnit.SECONDS);
    }

}

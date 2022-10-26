package src.ctt.GameMlemBot.Language.GameMlemEmbeds.QuickEventEmbed;

import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import src.ctt.GameMlemBot.Enums.Games;
import src.ctt.GameMlemBot.Language.DefaultEmbed;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserData.GameMlemUserData;
import src.ctt.GameMlemBot.Utils.DecimalFormatter;

public class inVoiceChannelEmbed {
    public static final String EVENT_NAME = "Phần Thưởng Trò Chuyện";
    public static final String EVENT_DESC = "Thưởng cho tất cả những người chơi đã đăng ký với "
            + Games.GAMEMLEM.getValue() + " trong phòng nói chuyện";

    public static final String IN_VOICE_FIELD = "Trong Phòng";

    public String inVoiceChannelRewardMessage(double rewardMoney) {
        return "**Bạn được tặng " + new DecimalFormatter().formatVND(rewardMoney) + new DefaultEmbed().DEFAULT_CURRENCY
                + " vì có tổng " + (int) rewardMoney / 1000 + " người đang ở trong voice chat**";
    }

    public MessageEmbed inVoiceChannelRewardEmbed(List<GameMlemUserData> users, double rewardMoney) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor(EVENT_NAME);
        eb.setDescription(EVENT_DESC);
        eb.setColor(new DefaultEmbed().REWARD_COLOR);

        String value = "";
        for (int index = 0; index < users.size(); index++) {
            if (index < 10) {
                value += "**<@" + users.get(index).getDISCORD_ID() + ">" + " +"
                        + new DecimalFormatter().formatVND(rewardMoney) + new DefaultEmbed().DEFAULT_CURRENCY + "**\n";
            } else {
                value += "...";
                break;
            }
        }
        eb.addField(IN_VOICE_FIELD, value, true);
        return eb.build();
    }
}

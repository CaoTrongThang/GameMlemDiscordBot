package src.ctt.GameMlemBot.Language.GameMlemEmbeds;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import src.ctt.GameMlemBot.Language.DefaultEmbed;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserData.GameMlemUserData;
import src.ctt.GameMlemBot.Utils.ConvertTimeToDateString;
import src.ctt.GameMlemBot.Utils.DecimalFormatter;

public class DailyRewardEmbed {

    public final MessageEmbed WAIT_FOR_NEXT_DAILY_REWARD(String name, long mili) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        if (mili == 0) {
            eb.setDescription("**" + name + " đợi tý rồi nhận tiếp nhé!**");
        } else {
            eb.setDescription(
                    "**" + name + " cần phải đợi thêm  "
                            + new ConvertTimeToDateString().convertToHourAndMin(mili / 1000)
                            + " để nhận lại quà ngày**");
        }
        return eb.build();
    }

    public final MessageEmbed ROLL_CALL(GameMlemUserData user, double moneyGet) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        eb.setDescription("**<@" + user.getDISCORD_ID() + "> đã nhận được " + new DecimalFormatter().formatVND(moneyGet)
                + DefaultEmbed.DEFAULT_CURRENCY + ", chuỗi " + user.getDailyReward().getDailyRewardStrike() + " ngày.");

        return eb.build();
    }
}

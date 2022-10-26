package src.ctt.GameMlemBot.Language.GameMlemEmbeds;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import src.ctt.GameMlemBot.Enums.GameMlemGames;
import src.ctt.GameMlemBot.Enums.Games;
import src.ctt.GameMlemBot.Language.DefaultEmbed;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserData.GameMlemUserData;

public class GameMlemEmbed {

    public static final String LOCK = "KHÓA";

    public static final String DICES = "Xúc Xắc";
    public static final String DICE1 = "<:1DICE:1032851076048756737>";
    public static final String DICE2 = "<:2DICE:1032851088979787887>";
    public static final String DICE3 = "<:3DICE:1032851101956972544>";
    public static final String DICE4 = "<:4DICE:1032851115273879572>";
    public static final String DICE5 = "<:5DICE:1032851136463523891>";
    public static final String DICE6 = "<:6DICE:1032851150573150239>";

    public static final int BET_1000 = 1000;
    public static final int BET_2000 = 2000;
    public static final int BET_5000 = 5000;
    public static final int UPDATE_TIME = 5;

    public static final String WINNER = "Thắng";
    public static final String LOSER = "Thua";

    public static final String TOTAL_PLAYERS = "Tổng Con Bạc Tham Gia";

    public static final String HAVE_BET = "Đã Đặt";

    public static final String NEED_TO_REGISTER = "Bạn cần phải đăng ký với " + Games.GAMEMLEM.getValue()
            + " để sử dụng những tính năng khác.";

    public static final String TIME_LEFT = "Thời gian còn lại: ";

    public final MessageEmbed NEW_GAME_ROOM_IS_CREATED(String name, String game) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        if (!game.equalsIgnoreCase("") || game != null) {
            eb.setDescription("**Phòng " + name + " đã được tạo**");
            return eb.build();
        } else {
            eb.setDescription("**Phòng " + GameMlemGames.valueOf(game).getValue() + name + " đã được tạo**");
            return eb.build();
        }
    }

    public final MessageEmbed CREATING_GAME_ROOM(String game) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        eb.setDescription("**Đang tạo phòng " + GameMlemGames.valueOf(game).getValue() + "**");
        return eb.build();

    }

    public final MessageEmbed INVALID_TRADE_ITEM(String item) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);
        if (item == null) {
            eb.setDescription("**Vật phẩm không hợp lệ để giao dịch, hoặc chưa điền thứ để giao dịch**");
        } else {
            eb.setDescription("**" + item + " không hợp lệ để giao dịch **");

        }
        return eb.build();

    }

    public final MessageEmbed TRADE_SUCESS(GameMlemUserData sourceUser, GameMlemUserData desUser, String item) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.SUCCESS_COLOR);

        eb.setDescription("**<@" + sourceUser.getDISCORD_ID() + "> đã chuyển " + item + " tới <@"
                + desUser.getDISCORD_ID() + ">**");

        return eb.build();

    }

    public final MessageEmbed TRADE_NEGATIVE_MONEY(String item) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        eb.setDescription("**" + item + "... tính giao dịch tiền âm phủ à**");
        return eb.build();

    }

    public final MessageEmbed TRADE_WITH_YOURSELF(long discordID) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        eb.setDescription("**<@" + discordID + "> bạn không thể tự giao dịch với bản thân**");
        return eb.build();

    }

    public final MessageEmbed CAN_NOT_CREATE_GAME_ROOM(String name, String game) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        if (game.equalsIgnoreCase("") || game == null) {
            eb.setDescription("**Không thể tạo phòng " + name + " đã được tạo**");
            return eb.build();
        } else if (name == null) {
            eb.setDescription("**Không thể tạo phòng của " + GameMlemGames.valueOf(game).getValue() + "**");
            return eb.build();
        } else {
            eb.setDescription("**Phòng " + GameMlemGames.valueOf(game).getValue() + name + " đã được tạo**");
            return eb.build();
        }
    }

}

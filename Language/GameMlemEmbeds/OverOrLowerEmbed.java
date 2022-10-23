package src.ctt.GameMlemBot.Language.GameMlemEmbeds;

import src.ctt.GameMlemBot.Enums.GameMlemGames;
import src.ctt.GameMlemBot.Enums.Games;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGameDataManager.OverOrLower.DicesData;

public class OverOrLowerEmbed {

    public static final String DICE_THUMBNAIL = "https://cdn-icons-png.flaticon.com/512/2102/2102198.png";
    public static final String OVER_THUMBNAIL = "https://i.imgur.com/BOuD10h.png";
    public static final String LOWER_THUMBNAIL = "https://i.imgur.com/sP2HVct.png";

    public static final String OVER_NAME = "TÀI";

    public static final String OVER_BET_1000_ID = "TAIXIU_TAI_1000";
    public static final String OVER_BET_1000_NAME = "ĐẶT TÀI 1000";

    public static final String OVER_BET_2000_ID = "TAIXIU_TAI_2000";
    public static final String OVER_BET_2000_NAME = "ĐẶT TÀI 2000";

    public static final String OVER_BET_5000_ID = "TAIXIU_TAI_5000";
    public static final String OVER_BET_5000_NAME = "ĐẶT TÀI 5000";

    public static final String OVER_BET_ALL_IN_ID = "TAIXIU_TAI_ALL_IN";
    public static final String OVER_BET_ALL_IN_NAME = "ĐẶT TÀI TẤT";

    public static final String LOWER_NAME = "XỈU";

    public static final String LOWER_BET_1000_ID = "TAIXIU_XIU_1000";
    public static final String LOWER_BET_1000_NAME = "ĐẶT XỈU 1000";

    public static final String LOWER_BET_2000_ID = "TAIXIU_XIU_2000";
    public static final String LOWER_BET_2000_NAME = "ĐẶT XỈU 2000";

    public static final String LOWER_BET_5000_ID = "TAIXIU_XIU_5000";
    public static final String LOWER_BET_5000_NAME = "ĐẶT XỈU 5000";

    public static final String LOWER_BET_ALL_IN_ID = "TAIXIU_XIU_ALL_IN";
    public static final String LOWER_BET_ALL_IN_NAME = "ĐẶT XỈU TẤT";

    public String overOrLowerResult(DicesData dices) {

        return "Kết Quả Trận Này: " + dices.getDICE_ONE_SYMBOL() + " " + dices.getDICE_TWO_SYMBOL()
                + " " + dices.getDICE_THREE_SYMBOL();
    }

}

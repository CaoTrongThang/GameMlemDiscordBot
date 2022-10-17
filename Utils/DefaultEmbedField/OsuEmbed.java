package src.ctt.GameMlemBot.Utils.DefaultEmbedField;

import src.ctt.GameMlemBot.Utils.DefaultBotMessages;

public class OsuEmbed {
    public static final String OSU_ICON_URL = "https://lemmmy.pw/osusig/img/osu.png";
    public static final String TAIKO_ICON_URL = "https://lemmmy.pw/osusig/img/taiko.png";
    public static final String MANIA_ICON_URL = "https://lemmmy.pw/osusig/img/mania.png";
    public static final String FRUITS_ICON_URL = "https://lemmmy.pw/osusig/img/ctb.png";

    public static final String HIT_3OO_EMOJI = "<:hit300:698619525691867267>";
    public static final String HIT_100_EMOJI = "<:hit100:698619509224898582>";
    public static final String HIT_50_EMOJI = "<:hit50:698619487930417193>";
    public static final String HIT_MISS_EMOJI = "<:miss:698619620461903962>";

    public static final String RANK_SSH_EMOJI = "";
    public static final String RANK_SS_EMOJI = "";
    public static final String RANK_SH_EMOJI = "";
    public static final String RANK_S_EMOJI = "";
    public static final String RANK_A_EMOJI = "";
    public static final String RANK_B_EMOJI = "";
    public static final String RANK_C_EMOJI = "";
    public static final String RANK_D_EMOJI = "";
    public static final String RANK_E_EMOJI = "";
    public static final String RANK_F_EMOJI = "";

    public final String AVATAR_TITLE(String name) {
        return "**Ảnh đại diện " + new DefaultBotMessages().OSU_GAME + " của " + name + ":**";
    }
}

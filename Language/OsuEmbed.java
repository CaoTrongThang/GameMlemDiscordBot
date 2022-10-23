package src.ctt.GameMlemBot.Language;

import java.lang.reflect.Field;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import src.ctt.GameMlemBot.Enums.OsuModes;

public class OsuEmbed {
    public static final String OSU_ICON_URL = "https://lemmmy.pw/osusig/img/osu.png";
    public static final String TAIKO_ICON_URL = "https://lemmmy.pw/osusig/img/taiko.png";
    public static final String MANIA_ICON_URL = "https://lemmmy.pw/osusig/img/mania.png";
    public static final String FRUITS_ICON_URL = "https://lemmmy.pw/osusig/img/ctb.png";

    public static final String HIT_3OO_EMOJI = "<:hit300:698619525691867267> ";
    public static final String HIT_100_EMOJI = "<:hit100:698619509224898582> ";
    public static final String HIT_50_EMOJI = "<:hit50:698619487930417193> ";
    public static final String HIT_MISS_EMOJI = "<:miss:698619620461903962> ";

    public static final String RANK_SSH_EMOJI = "<:osurankssh:1031826549609087006>";
    public static final String RANK_SS_EMOJI = "<:osurankss:1031826523981881394>";
    public static final String RANK_SH_EMOJI = "<:osuranksh:1031826364631892040>";
    public static final String RANK_S_EMOJI = "<:osuranks:1031826587991150622>";
    public static final String RANK_A_EMOJI = "<:osuranka:1031826689040322571>";
    public static final String RANK_B_EMOJI = "<:osurankb:1031826673051635712>";
    public static final String RANK_C_EMOJI = "<:osurankc:1031826659025883156>";
    public static final String RANK_D_EMOJI = "<:osurankd:1031826626067046410>";
    public static final String RANK_F_EMOJI = "<:osurankf:1031829169413296148>";

    public static final String PROFILE_STATS_AUTHOR = "Hồ Sơ Của ";
    public static final String ACCOUNT_FIELD_TITLE = "**Tài Khoản**";
    public static final String INFO_FIELD_TITLE = "**Thông Tin**";
    public static final String PLAY_COUNNTS_EVERY_MONTH_FIELD_TITLE = "**Số Màn Chơi Mỗi Tháng**";
    public static final String USER_RANK_GLOBAL = "Hạng: ";
    public static final String USER_ACCURACY = "Chính xác: ";
    public static final String USER_PLAYCOUNTS = "Tổng màn chơi: ";
    public static final String USER_TOTAL_PLAY_HOURS = "Tổng giờ chơi: ";
    public static final String USER_STATUS = "Trạng thái: ";
    public static final String USER_PP = "Pepe: ";
    public static final String USER_LEVEL = "Cấp độ: ";
    public static final String USER_COUNTRY = "Người: ";
    public static final String STATUS_OFFLINE = " Offline";
    public static final String STATUS_ONLINE = " Online";
    public static final String USER_SCORE = "Tổng điểm: ";

    public static final String RECENT_JUST_PLAY_AUTHOR = " Vừa Mới Chơi";
    public static final String RECENT_HIT_COUNT_FIELD_TITLE = "**Nhấn Được **";
    public static final String RECENT_STATS_FIELD_TITLE = "**Kết Quả **";
    public static final String RECENT_SCORE_MAX_COMBO = "Combo cao nhất: ";
    public static final String RECENT_MODS_USED_FIELD_TITLE = "Mod Đã Dùng: ";
    public static final String RECENT_RETRY = "Thử #";

    public final String AVATAR_TITLE(String name) {
        return "**Ảnh đại diện " + OsuModes.osu.getName() + " của " + name + ":**";
    }

    public final MessageEmbed HAVE_NO_RECENT_PLAYS(String name) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(DefaultEmbed.ERROR_COLOR);

        eb.setDescription("**" + name + " chưa chơi màn nào trong 24h" + "**");
        return eb.build();

    }
}

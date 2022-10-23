package src.ctt.GameMlemBot.Logic.Handler.GameMlemHandler.GameMlemCommands;

import java.lang.StackWalker.Option;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

public class GameMlemSlashCommands {
        public static final String GAME_MLEM_BASE_COMMAND = "gamemlem";
        public static final String GAME_MLEM_BASE_COMMAND_DESC = "lệnh của gamemlem";

        public static final String DANG_KY_COMMAND = "dangky";
        public static final String DANG_KY_COMMAND_DESC = "Đăng ký tài khoản của bạn với gamemlem để sử dụng thêm nhiều tính năng";

        public static final String TAO_GAME_COMMAND = "taogame";
        public static final String TAO_GAME_COMMAND_DESC = "Tạo phòng cho trò gì đó";

        public static final String STATS_COMMAND = "thongso";
        public static final String STATS_COMMAND_DESC = "Xem thông số của bạn";

        public static final String DIEM_DANH_COMMAND = "diemdanh";
        public static final String DIEM_DANH_COMMAND_DESC = "Điểm danh tích chuỗi";

        public final static String GIAO_DICH_COMMAND = "giaodich";
        public final static String GIAO_DICH_COMMAND_DESC = "Giao dịch với người chơi khác";

        // =======================================================

        public static final String userNameOrIDArg = "tagnguoidung";
        public static final String userNameOrIdDesc = "Điền tên hoặc tag người vào";

        public static final String gameTypeArg = "theloai";
        public static final String gameTypeDesc = "Nhập thể loại phòng mà bạn muốn tạo";

        public static final String numberArg = "number";
        public static final String numberDesc = "Số bạn muốn";

        public static final String tradeTypeArg = "theloaigiaodich";
        public static final String tradeTypeDesc = "Bạn muốn giao dịch loại vật phẩm hay tiền tệ?";

        public static final String tradeItemArg = "thumuonchuyen";
        public static final String tradeItemDesc = "Ghi thứ bạn muốn chuyển vào đây";

        public static final SubcommandData[] GAME_MLEM_SUB_COMMANDS = {
                        new SubcommandData(DANG_KY_COMMAND, DANG_KY_COMMAND_DESC),
                        new SubcommandData(TAO_GAME_COMMAND, TAO_GAME_COMMAND_DESC)
                                        .addOption(OptionType.STRING, gameTypeArg, gameTypeDesc, true, true),
                        new SubcommandData(DIEM_DANH_COMMAND, DIEM_DANH_COMMAND_DESC),
                        new SubcommandData(GIAO_DICH_COMMAND, GIAO_DICH_COMMAND_DESC)
                                        .addOption(OptionType.USER, userNameOrIDArg, userNameOrIdDesc, true, false)
                                        .addOption(OptionType.STRING, tradeTypeArg, tradeTypeDesc, true, true)
                                        .addOption(OptionType.STRING, tradeItemArg, tradeTypeArg, true, false),

        };
}

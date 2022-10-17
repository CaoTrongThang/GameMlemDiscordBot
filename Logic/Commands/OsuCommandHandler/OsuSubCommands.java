package src.ctt.GameMlemBot.Logic.Commands.OsuCommandHandler;

import java.lang.StackWalker.Option;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

public class OsuSubCommands {
        public static final String LINK_COMMAND = "link";
        public static final String LINK_COMMAND_DESC = "Liên kết tài khoản Osu! và Discord";

        public static final String UNLINK_COMMAND = "unlink";
        public static final String UNLINK_COMMAND_DESC = "Hủy liên kết tài khoản Osu! với Discord";

        public static final String STATS_COMMAND = "stats";
        public static final String STATS_COMMAND_DESC = "Hiện thông số tài khoản osu của bạn";

        public static final String TOP_COMMAND = "top";
        public static final String TOP_COMMAND_DESC = "Hiện top những bài hát nhiều pepe nhất của bạn";

        public static final String RECENT_COMMAND = "recent";
        public static final String RECENT_COMMAND_DESC = "Hiện bài hát bạn vừa mới chơi";

        public static final String ROLL_COMMAND = "roll";
        public static final String ROLL_COMMAND_DESC = "Tài xỉu";

        public static final String AVATAR_COMMAND = "avatar";
        public static final String AVATAR_COMMAND_DESC = "Hiện hình đại diện Osu! của bạn";

        public static final String userNameOrIDArg = "usernameorid";
        public static final String userNameOrIdDesc = "Điền tên osu hoặc id";

        public static final String numberArg = "number";
        public static final String numberDesc = "Số bạn muốn chơi";

        public static final String osuModeArg = "mode";
        public static final String osuModeDesc = "Chế độ nào của osu";

        public static final SubcommandData[] OSU_SUB_COMMANDS = {
                        new SubcommandData(LINK_COMMAND, LINK_COMMAND_DESC)
                                        .addOption(OptionType.STRING, userNameOrIDArg, userNameOrIdDesc),
                        new SubcommandData(UNLINK_COMMAND, UNLINK_COMMAND_DESC),
                        new SubcommandData(TOP_COMMAND, TOP_COMMAND_DESC)
                                        .addOption(OptionType.USER, userNameOrIDArg, userNameOrIdDesc),
                        new SubcommandData(STATS_COMMAND, STATS_COMMAND_DESC)
                                        .addOption(OptionType.USER, userNameOrIDArg, userNameOrIdDesc)
                                        .addOption(OptionType.STRING, osuModeArg, osuModeDesc),
                        new SubcommandData(RECENT_COMMAND, RECENT_COMMAND_DESC)
                                        .addOption(OptionType.USER, userNameOrIDArg, userNameOrIdDesc),
                        new SubcommandData(ROLL_COMMAND, ROLL_COMMAND_DESC)
                                        .addOption(OptionType.NUMBER, numberArg, numberDesc),
                        new SubcommandData(AVATAR_COMMAND, AVATAR_COMMAND_DESC)
        };
}

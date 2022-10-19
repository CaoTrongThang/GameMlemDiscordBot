package src.ctt.GameMlemBot.Logic.Handler.GameMlemHandler.GameMlemCommandHandler;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import src.ctt.GameMlemBot.Logic.GameMlemBotManager.DiscordBotManager;

public class GameMlemCommands {
    public static final String GAME_MLEM_BASE_COMMAND = "gamemlem";
    public static final String GAME_MLEM_BASE_COMMAND_DESC = "lệnh của gamemlem";

    public static final String DANG_KY_COMMAND = "dangky";
    public static final String DANG_KY_COMMAND_DESC = "Đăng ký tài khoản của bạn với gamemlem để sử dụng thêm nhiều tính năng";

    public static final String userNameOrIDArg = "usernameorid";
    public static final String userNameOrIdDesc = "Điền tên osu hoặc id";

    public static final String numberArg = "number";
    public static final String numberDesc = "Số bạn muốn chơi";

    public static final SubcommandData[] GAME_MLEM_SUB_COMMANDS = {
            new SubcommandData(DANG_KY_COMMAND, DANG_KY_COMMAND_DESC)
    };

    static {
        System.out.println("GameMlem");
        DiscordBotManager.slashCommands
                .add(Commands.slash(GAME_MLEM_BASE_COMMAND, GAME_MLEM_BASE_COMMAND_DESC)
                        .addSubcommands(GAME_MLEM_SUB_COMMANDS));
    }
}

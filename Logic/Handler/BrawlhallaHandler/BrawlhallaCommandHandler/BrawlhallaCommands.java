package src.ctt.GameMlemBot.Logic.Handler.BrawlhallaHandler.BrawlhallaCommandHandler;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import src.ctt.GameMlemBot.Logic.GameMlemBotManager.DiscordBotManager;

public class BrawlhallaCommands {
    public static final String BRAWLHALLA_BASE_COMMAND = "brawlhalla";
    public static final String BRAWLHALLA_BASE_COMMAND_DESC = "Những lệnh cơ bản của brawlhalla";

    public static final String LINK_COMMAND = "link";
    public static final String LINK_COMMAND_DESC = "Liên kết tài khoản Brawlhalla và Discord";

    public static final SubcommandData[] BRAWLHALLA_SUB_COMMANDS = {
            new SubcommandData(LINK_COMMAND, LINK_COMMAND_DESC).addOption(OptionType.STRING, "id",
                    "điền tên brawlhalla id"),
    };
}

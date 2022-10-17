package src.ctt.GameMlemBot.Logic.Commands.BrawlhallaCommandHandler;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

public class BrawlhallaSubCommands {
    public static final String LINK_COMMAND = "link";
    public static final String LINK_COMMAND_DESC = "Liên kết tài khoản Brawlhalla và Discord";

    public static final SubcommandData[] BRAWLHALL_SUB_COMMANDS = {
            new SubcommandData(LINK_COMMAND, LINK_COMMAND_DESC).addOption(OptionType.STRING, "id",
                    "điền tên brawlhalla id"),
    };
}

package src.ctt.GameMlemBot.Logic.Handler;

import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.ctt.GameMlemBot.Logic.GameMlemBotManager.DiscordBotManager;
import src.ctt.GameMlemBot.Logic.Handler.BrawlhallaHandler.BrawlhallaCommandHandler.BrawlhallaCommands;
import src.ctt.GameMlemBot.Logic.Handler.GameMlemHandler.GameMlemCommandHandler.GameMlemCommands;
import src.ctt.GameMlemBot.Logic.Handler.OsuHandler.OsuCommandHandler.OsuCommands;

public class LoadSlashCommands {
    static {
        DiscordBotManager.slashCommands
                .add(Commands.slash(OsuCommands.OSU_BASE_COMMAND, OsuCommands.OSU_BASE_COMMAND_DESC)
                        .addSubcommands(OsuCommands.OSU_SUB_COMMANDS));
    }

    static {
        DiscordBotManager.slashCommands
                .add(Commands
                        .slash(GameMlemCommands.GAME_MLEM_BASE_COMMAND, GameMlemCommands.GAME_MLEM_BASE_COMMAND_DESC)
                        .addSubcommands(GameMlemCommands.GAME_MLEM_SUB_COMMANDS));
    }

    static {
        DiscordBotManager.slashCommands
                .add(Commands
                        .slash(BrawlhallaCommands.BRAWLHALLA_BASE_COMMAND,
                                BrawlhallaCommands.BRAWLHALLA_BASE_COMMAND_DESC)
                        .addSubcommands(BrawlhallaCommands.BRAWLHALLA_SUB_COMMANDS));
    }

}

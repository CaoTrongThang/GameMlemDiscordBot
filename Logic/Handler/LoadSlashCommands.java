package src.ctt.GameMlemBot.Logic.Handler;

import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.ctt.GameMlemBot.Logic.GameMlemBotManager.GameMlemBotManager;
import src.ctt.GameMlemBot.Logic.Handler.BrawlhallaHandler.BrawlhallaCommandHandler.BrawlhallaCommands;
import src.ctt.GameMlemBot.Logic.Handler.GameMlem.Commands.GameMlemSlashCommands;
import src.ctt.GameMlemBot.Logic.Handler.OsuHandler.CommandHandler.OsuCommands;

public class LoadSlashCommands {
        static {
                GameMlemBotManager.slashCommands
                                .add(Commands.slash(OsuCommands.OSU_BASE_COMMAND, OsuCommands.OSU_BASE_COMMAND_DESC)
                                                .addSubcommands(OsuCommands.OSU_SUB_COMMANDS));
        }

        static {
                GameMlemBotManager.slashCommands
                                .add(Commands
                                                .slash(GameMlemSlashCommands.GAME_MLEM_BASE_COMMAND,
                                                                GameMlemSlashCommands.GAME_MLEM_BASE_COMMAND_DESC)
                                                .addSubcommands(GameMlemSlashCommands.GAME_MLEM_SUB_COMMANDS));
        }

        static {
                GameMlemBotManager.slashCommands
                                .add(Commands
                                                .slash(BrawlhallaCommands.BRAWLHALLA_BASE_COMMAND,
                                                                BrawlhallaCommands.BRAWLHALLA_BASE_COMMAND_DESC)
                                                .addSubcommands(BrawlhallaCommands.BRAWLHALLA_SUB_COMMANDS));
        }

}

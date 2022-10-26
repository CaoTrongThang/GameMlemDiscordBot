package src.ctt.GameMlemBot.Logic.Events;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.ctt.GameMlemBot.Enums.Games;
import src.ctt.GameMlemBot.Language.DefaultEmbed;
import src.ctt.GameMlemBot.Language.GameMlemEmbeds.GameMlemEmbed;
import src.ctt.GameMlemBot.Logic.GameMlemBotManager.GameMlemGuildManager;
import src.ctt.GameMlemBot.Logic.Handler.BrawlhallaHandler.BrawlhallaCommandHandler.BrawlhallaCommands;
import src.ctt.GameMlemBot.Logic.Handler.BrawlhallaHandler.BrawlhallaCommandHandler.BrawlhallaHandler;
import src.ctt.GameMlemBot.Logic.Handler.GameMlem.CommandHandler.GameMlemSlashHandler;
import src.ctt.GameMlemBot.Logic.Handler.GameMlem.Commands.GameMlemSlashCommands;
import src.ctt.GameMlemBot.Logic.Handler.OsuHandler.CommandHandler.OsuCommands;
import src.ctt.GameMlemBot.Logic.Handler.OsuHandler.CommandHandler.OsuHandler;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserDataManager;
import src.ctt.GameMlemBot.Utils.ThreadManager;

public class OnSlashCommand extends ListenerAdapter {
    GameMlemUserDataManager gameMlemDataManager = new GameMlemUserDataManager();
    GameMlemGuildManager gameMlemGuildManager = new GameMlemGuildManager();

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
        new Thread() {
            @Override
            public void run() {
                // ? Load data when user use command, and remove it when user or guild is
                // ? inactive so save cache and increase scanning performance
                gameMlemDataManager.loadUser(e.getUser().getIdLong());
                gameMlemGuildManager.createGuildIfNotExist(e.getGuild().getIdLong());

                e.deferReply().queue();
                if (e.getSubcommandName().equalsIgnoreCase(GameMlemSlashCommands.DANG_KY_COMMAND)) {
                    new GameMlemSlashHandler().gameMlemSlashCommandHandler(e);
                    return;
                }

                if (!new GameMlemUserDataManager().isExisted(e.getUser().getIdLong())) {
                    new DefaultEmbed().sendAndDeleteMessageAfter(e,
                            new DefaultEmbed().ACCOUNT_IS_NOT_LINKED(e.getUser().getIdLong(),
                                    Games.GAMEMLEM.getValue()));
                    return;
                }

                new GameMlemUserDataManager().getUser(e.getUser().getIdLong()).setIsUseCommand(true)
                        .setLAST_ACTIVITY(System.currentTimeMillis());
                new GameMlemGuildManager().getGuild(e.getGuild().getIdLong())
                        .setLastActivity(System.currentTimeMillis());

                // * OSU
                if (e.getName().contains(OsuCommands.OSU_BASE_COMMAND)) {
                    new OsuHandler().osuCommandHander(e);
                    // * BRAWLHALLA
                } else if (e.getName().contains(BrawlhallaCommands.BRAWLHALLA_BASE_COMMAND)) {
                    new BrawlhallaHandler().brawlhallaCommandHandler(e);
                    // * GAME MLEM
                } else if (e.getName().contains(GameMlemSlashCommands.GAME_MLEM_BASE_COMMAND)) {
                    new GameMlemSlashHandler().gameMlemSlashCommandHandler(e);
                    return;
                }
            }
        }.start();
    }

}

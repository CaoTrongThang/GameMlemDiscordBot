package src.ctt.GameMlemBot.Logic.Events;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.ctt.GameMlemBot.Enums.Games;
import src.ctt.GameMlemBot.Language.DefaultEmbed;
import src.ctt.GameMlemBot.Language.GameMlemEmbeds.GameMlemEmbed;
import src.ctt.GameMlemBot.Logic.Handler.BrawlhallaHandler.BrawlhallaCommandHandler.BrawlhallaCommands;
import src.ctt.GameMlemBot.Logic.Handler.BrawlhallaHandler.BrawlhallaCommandHandler.BrawlhallaHandler;
import src.ctt.GameMlemBot.Logic.Handler.GameMlemHandler.GameMlemCommandHandler.GameMlemSlashHandler;
import src.ctt.GameMlemBot.Logic.Handler.GameMlemHandler.GameMlemCommands.GameMlemSlashCommands;
import src.ctt.GameMlemBot.Logic.Handler.OsuHandler.OsuCommandHandler.OsuCommands;
import src.ctt.GameMlemBot.Logic.Handler.OsuHandler.OsuCommandHandler.OsuHandler;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemDataManager;

public class OnSlashCommand extends ListenerAdapter {
    GameMlemDataManager gameMlemDataManager = new GameMlemDataManager();

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
        new Thread() {
            @Override
            public void run() {
                gameMlemDataManager.loadData(e.getUser().getIdLong());

                e.deferReply().queue();
                if (e.getSubcommandName().equalsIgnoreCase(GameMlemSlashCommands.DANG_KY_COMMAND)) {
                    new GameMlemSlashHandler().gameMlemSlashCommandHandler(e);
                    return;
                }

                if (!new GameMlemDataManager().isExisted(e.getUser().getIdLong())) {
                    new DefaultEmbed().sendAndDeleteMessageAfter(e,
                            new DefaultEmbed().ACCOUNT_IS_NOT_LINKED(e.getUser().getIdLong(),
                                    Games.GAMEMLEM.getValue()));
                    return;
                }

                new GameMlemDataManager().getUser(e.getUser().getIdLong()).setIsUseCommand(true)
                        .setLAST_ACTIVITY(System.currentTimeMillis());

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

package src.ctt.GameMlemBot.Logic.Events;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.ctt.GameMlemBot.Logic.Handler.BrawlhallaHandler.BrawlhallaCommandHandler.BrawlhallaCommands;
import src.ctt.GameMlemBot.Logic.Handler.BrawlhallaHandler.BrawlhallaCommandHandler.BrawlhallaHandler;
import src.ctt.GameMlemBot.Logic.Handler.GameMlemHandler.GameMlemCommandHandler.GameMlemCommands;
import src.ctt.GameMlemBot.Logic.Handler.GameMlemHandler.GameMlemCommandHandler.GameMlemHandler;
import src.ctt.GameMlemBot.Logic.Handler.OsuHandler.OsuCommandHandler.OsuCommands;
import src.ctt.GameMlemBot.Logic.Handler.OsuHandler.OsuCommandHandler.OsuHandler;

public class OnSlashCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        new Thread() {
            @Override
            public void run() {
                event.deferReply().queue();
                // * OSU
                if (event.getName().contains(OsuCommands.OSU_BASE_COMMAND)) {
                    new OsuHandler().osuCommandHander(event);
                    // * BRAWLHALLA
                } else if (event.getName().contains(BrawlhallaCommands.BRAWLHALLA_BASE_COMMAND)) {
                    new BrawlhallaHandler().brawlhallaCommandHandler(event);
                    // * GAMEMLEM
                } else if (event.getName().contains(GameMlemCommands.GAME_MLEM_BASE_COMMAND)) {
                    new GameMlemHandler().gameMlemCommandHandler(event);
                }
            }
        }.start();
    }

}

package src.ctt.GameMlemBot.Logic.Events;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.ctt.GameMlemBot.Logic.Handler.OsuHandler.OsuCommandHandler.OsuHandler;

public class OnSlashCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        new Thread() {
            @Override
            public void run() {
                event.deferReply().queue();
                if (event.getName().contains("osu")) {
                    new OsuHandler().osuCommandHander(event);

                } else if (event.getName().contains("brawlhalla")) {

                }
            }
        }.start();
    }

}

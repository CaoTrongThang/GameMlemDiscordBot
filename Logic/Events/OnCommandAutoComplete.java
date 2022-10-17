package src.ctt.GameMlemBot.Logic.Events;

import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnCommandAutoComplete extends ListenerAdapter {

    private String[] words = new String[] { "apple", "apricot", "banana", "cherry", "coconut", "cranberry" };

    @Override
    public void onCommandAutoCompleteInteraction(CommandAutoCompleteInteractionEvent event) {
        if (event.getName().equals("osu") && event.getFocusedOption().getName().equals("name")) {

        }
    }
}
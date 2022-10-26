package src.ctt.GameMlemBot.Logic.Events;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.ctt.GameMlemBot.Enums.GameMlemGames;
import src.ctt.GameMlemBot.Enums.Games;
import src.ctt.GameMlemBot.Language.DefaultEmbed;
import src.ctt.GameMlemBot.Logic.Handler.GameMlem.Commands.GameMlemMessageCommands;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserDataManager;

public class OnMessageRecieve extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        String content = e.getMessage().getContentRaw();
        if (content.startsWith(DefaultEmbed.MESSAGE_PREFIX)) {
            if (content.equalsIgnoreCase(GameMlemMessageCommands.shutdownBot)) {

            }
        }
    }
}

package src.ctt.GameMlemBot.Logic.Events;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import src.ctt.GameMlemBot.Enums.GameMlemGames;
import src.ctt.GameMlemBot.Enums.GameMlemStatsType;
import src.ctt.GameMlemBot.Enums.OsuModes;
import src.ctt.GameMlemBot.Enums.TradeTypes;
import src.ctt.GameMlemBot.Logic.Handler.GameMlem.Commands.GameMlemSlashCommands;
import src.ctt.GameMlemBot.Logic.Handler.OsuHandler.CommandHandler.OsuCommands;

public class OnCommandAutoComplete extends ListenerAdapter {

    @Override
    public void onCommandAutoCompleteInteraction(CommandAutoCompleteInteractionEvent e) {
        try {
            if (e.getName().equals(GameMlemSlashCommands.GAME_MLEM_BASE_COMMAND)
                    && e.getFocusedOption().getName().equals(GameMlemSlashCommands.gameTypeArg)) {
                List<Command.Choice> options = Stream
                        .of(Stream.of(GameMlemGames.values()).map(GameMlemGames::name).toArray(String[]::new))
                        .filter(word -> word.startsWith(e.getFocusedOption().getValue()))
                        .map(word -> new Command.Choice(word, word))
                        .collect(Collectors.toList());
                if (!e.isAcknowledged()) {
                    e.replyChoices(options).queue();
                }
            } else if (e.getName().equals(GameMlemSlashCommands.GAME_MLEM_BASE_COMMAND)
                    && e.getFocusedOption().getName().equals(GameMlemSlashCommands.tradeTypeArg)) {
                List<Command.Choice> options = Stream
                        .of(Stream.of(TradeTypes.values()).map(TradeTypes::name).toArray(String[]::new))
                        .filter(word -> word.startsWith(e.getFocusedOption().getValue()))
                        .map(word -> new Command.Choice(word, word))
                        .collect(Collectors.toList());
                if (!e.isAcknowledged()) {
                    e.replyChoices(options).queue();
                }
            } else if (e.getName().equals(GameMlemSlashCommands.GAME_MLEM_BASE_COMMAND)
                    && e.getFocusedOption().getName().equals(GameMlemSlashCommands.statsTypeArg)) {
                List<Command.Choice> options = Stream
                        .of(Stream.of(GameMlemStatsType.values()).map(GameMlemStatsType::name).toArray(String[]::new))
                        .filter(word -> word.startsWith(e.getFocusedOption().getValue()))
                        .map(word -> new Command.Choice(word, word))
                        .collect(Collectors.toList());
                if (!e.isAcknowledged()) {
                    e.replyChoices(options).queue();
                }
            } else if (e.getName().equals(OsuCommands.OSU_BASE_COMMAND)
                    && e.getFocusedOption().getName().equals(OsuCommands.osuModeArg)) {
                List<Command.Choice> options = Stream
                        .of(Stream.of(OsuModes.values()).map(OsuModes::name).toArray(String[]::new))
                        .filter(word -> word.startsWith(e.getFocusedOption().getValue()))
                        .map(word -> new Command.Choice(word, word))
                        .collect(Collectors.toList());
                if (!e.isAcknowledged()) {
                    e.replyChoices(options).queue();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
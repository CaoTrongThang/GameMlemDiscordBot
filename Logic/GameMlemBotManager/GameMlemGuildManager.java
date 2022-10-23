package src.ctt.GameMlemBot.Logic.GameMlemBotManager;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.entities.Guild;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGuild;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGameDataManager.OverOrLower.OverOrLower.OverOrLowerMatchManager;

public class GameMlemGuildManager {
    public static List<GameMlemGuild> gameMlemGuidlds = new ArrayList<>();

    public GameMlemGuild getGuild(long guildID) {
        for (GameMlemGuild guild : gameMlemGuidlds) {
            if (guild.getGuild().getIdLong() == guildID) {
                return guild;
            }
        }
        return null;
    }
}

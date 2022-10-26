package src.ctt.GameMlemBot.Logic.GameMlemBotManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import src.ctt.GameMlemBot.Enums.ReplaceType;
import src.ctt.GameMlemBot.Enums.TimeInterval;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGuildData;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGameDataManager.OverOrLower.OverOrLower.OverOrLowerMatchManager;
import src.ctt.GameMlemBot.MongoDB.MongoDBManager;

public class GameMlemGuildManager {
    public static List<GameMlemGuildData> gameMlemGuidlds = new ArrayList<>();
    public static MongoDBManager mongo = new MongoDBManager();

    public GameMlemGuildData getGuild(long guildID) {
        Gson gson = new GsonBuilder().create();
        for (GameMlemGuildData guild : gameMlemGuidlds) {
            if (guild.getGUILD_ID() == guildID) {
                return guild;
            }
        }
        for (Document doc : mongo.find(mongo.GUILD_COLLECTION, new Document("GUILD_ID", guildID))) {
            gameMlemGuidlds.add(gson.fromJson(doc.toJson(), GameMlemGuildData.class));
            return gson.fromJson(doc.toJson(), GameMlemGuildData.class);
        }
        return null;
    }

    public void createGuildIfNotExist(long guildID) {
        GameMlemGuildData guild = getGuild(guildID);
        if (guild != null) {
            return;
        } else {
            guild = new GameMlemGuildData(guildID);
            gameMlemGuidlds.add(guild);
            return;
        }
    }

    public void saveGuildsAndRemoveFromCache() {
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").create();

        Iterator<GameMlemGuildData> itr = gameMlemGuidlds.iterator();

        while (itr.hasNext()) {
            GameMlemGuildData guild = itr.next();
            if (guild.getLastActivity() +
                    TimeInterval.GUILD_MAX_INACTIVITY_TIME_1000.getValue() < System
                            .currentTimeMillis()
                    && guild.getOverOrLowerMatchManager().getIsPlaying() != true) {
                mongo.findAndReplace(mongo.GUILD_COLLECTION, guild.getGUILD_ID(),
                        Document.parse(gson.toJson(guild)),
                        ReplaceType.GUILD);
                itr.remove();
            }
        }
    }
}

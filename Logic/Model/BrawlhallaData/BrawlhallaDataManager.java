package src.ctt.GameMlemBot.Logic.Model.BrawlhallaData;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import src.ctt.GameMlemBot.Utils.ByteOperator;
import src.ctt.GameMlemBot.Utils.FilePath;

public class BrawlhallaDataManager {
    private static List<BrawlhallaUserDiscordData> brawlhallaDiscordLinkList;

    public static Gson gson = new GsonBuilder().create();

}

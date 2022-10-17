package src.ctt.GameMlemBot.Logic.Data.BrawlhallaData;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import src.ctt.GameMlemBot.Interface.IDataManager;
import src.ctt.GameMlemBot.Utils.ByteOperator;
import src.ctt.GameMlemBot.Utils.FilePath;

public class BrawlhallaDataManager implements IDataManager {
    private static List<BrawlhallaUserDiscordData> brawlhallaDiscordLinkList;

    public static Gson gson = new GsonBuilder().create();

    @Override
    public void loadData() {
        Type type = new TypeToken<List<BrawlhallaUserDiscordData>>() {
        }.getType();
        String data = new String(ByteOperator.readByteFromFile(FilePath.OSU_DISCORD_LINK_PATH), StandardCharsets.UTF_8);
        brawlhallaDiscordLinkList = gson.fromJson(data, type);
    }

    @Override
    public void saveData() {
        String data = gson.toJson(brawlhallaDiscordLinkList);
        ByteOperator.writeByteToFile(data.getBytes(), FilePath.OSU_DISCORD_LINK_PATH);
    }
}

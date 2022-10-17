package src.ctt.GameMlemBot.Meta;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import src.ctt.GameMlemBot.Utils.ByteOperator;
import src.ctt.GameMlemBot.Utils.FilePath;

public class MetaDataManager {
    private MetaDataManager() {

    }

    public static void loadMetaData() {
        Gson gson = new GsonBuilder().create();
        byte[] buffer;
        String data;
        Type type;

        // Read highPriorityUser.json
        type = new TypeToken<List<HighPriorityUsers>>() {
        }.getType();
        data = new String(ByteOperator.readByteFromFile(FilePath.highPriorityUsers), StandardCharsets.UTF_8);
        HighPriorityUsers.highPriorityUsers = gson.fromJson(data, type);

        // Read lowPriorityUser.json
        type = new TypeToken<List<LowPriorityUsers>>() {
        }.getType();
        data = new String(ByteOperator.readByteFromFile(FilePath.lowPriorityUsers), StandardCharsets.UTF_8);
        LowPriorityUsers.lowPriorityUsers = gson.fromJson(data, type);
    }

    public void addHighPriorityUser() {

    }

    public void removeHighPriorityUser() {

    }

    public void addLowPriorityUser() {

    }

    public void removeLowPriorityUser() {

    }
}
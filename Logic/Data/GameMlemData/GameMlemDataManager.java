package src.ctt.GameMlemBot.Logic.Data.GameMlemData;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import src.ctt.GameMlemBot.Interface.IDataManager;
import src.ctt.GameMlemBot.Logic.Data.DataManager;
import src.ctt.GameMlemBot.Logic.Data.GameMlemData.GameMlemUsersData.HighPriorityUsers;
import src.ctt.GameMlemBot.Logic.Data.GameMlemData.GameMlemUsersData.LowPriorityUsers;
import src.ctt.GameMlemBot.Utils.ByteOperator;
import src.ctt.GameMlemBot.Utils.FilePath;

public class GameMlemDataManager implements IDataManager {

    static {
        DataManager.dataManagers.add(new GameMlemDataManager());
    }

    @Override
    public void saveData() {

    }

    @Override
    public void loadData() {
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

    public boolean isHighPriorityUser(long discordID) {
        for (HighPriorityUsers user : HighPriorityUsers.highPriorityUsers) {
            if (user.getDISCORD_ID() == discordID) {
                return true;
            }
        }
        return false;
    }

    public boolean isLowPriorityUser(long discordID) {
        for (LowPriorityUsers user : LowPriorityUsers.lowPriorityUsers) {
            if (user.getDISCORD_ID() == discordID) {
                return true;
            }
        }
        return false;
    }

}

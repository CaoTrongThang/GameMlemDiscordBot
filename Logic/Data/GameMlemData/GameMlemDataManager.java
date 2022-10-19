package src.ctt.GameMlemBot.Logic.Data.GameMlemData;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
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

    public static List<GameMlemUserData> gameMlemUsers = new ArrayList<>();

    static {
        DataManager.dataManagers.add(new GameMlemDataManager());
    }

    // TODO: IN PROGRESS
    @Override
    public void saveData() {
        Gson gson = new GsonBuilder().create();
        File file;

        for (GameMlemUserData user : gameMlemUsers) {
            file = new File(FilePath.GAME_MLEM_USER_DATA_FILE_DIR + "\\" + user.getDISCORD_ID() + ".txt");
            try {
                Files.write(file.toPath(), gson.toJson(user).getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void loadData() {
        Gson gson = new GsonBuilder().create();
        byte[] buffer;
        String data;
        Type type;
        File[] userFiles;

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

        // Read All User Files
        userFiles = new File(FilePath.GAME_MLEM_USER_DATA_FILE_DIR).listFiles();
        if (userFiles != null) {
            try {
                for (int index = 0; index < userFiles.length; index++) {
                    buffer = Files.readAllBytes(userFiles[index].toPath());
                    data = new String(buffer, StandardCharsets.UTF_8);
                    gameMlemUsers.add(gson.fromJson(data, GameMlemUserData.class));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
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

    // TODO
    public void addHighPriorityUser() {

    }

    // TODO
    public void removeHighPriorityUser() {

    }

    // TODO
    public void addLowPriorityUser() {

    }

    // TODO
    public void removeLowPriorityUser() {

    }

}

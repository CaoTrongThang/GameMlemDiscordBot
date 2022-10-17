package src.ctt.GameMlemBot.Logic.Data;

import java.util.ArrayList;
import java.util.List;

import src.ctt.GameMlemBot.Interface.IDataManager;
import src.ctt.GameMlemBot.Logic.Data.BrawlhallaData.BrawlhallaDataManager;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuDataManager;

public class DataManager {
    public static List<IDataManager> dataManagers = new ArrayList<>();

    static {
        dataManagers.add(new BrawlhallaDataManager());
        dataManagers.add(new OsuDataManager());
    }

    public static void saveData() {
        for (IDataManager manager : dataManagers) {
            manager.saveData();
        }
    }

    public static void loadData() {
        for (IDataManager manager : dataManagers) {
            manager.loadData();
        }
    }
}

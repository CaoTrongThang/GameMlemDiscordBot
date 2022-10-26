package src.ctt.GameMlemBot;

import src.ctt.GameMlemBot.Enums.TimeInterval;
import src.ctt.GameMlemBot.Logic.GameMlemBotManager.GameMlemGuildManager;
import src.ctt.GameMlemBot.Logic.Handler.GameMlem.QuickEventHandler.QuickEventHandler;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserDataManager;

public class DataLooper {

    public DataLooper() {
        new Thread() {
            @Override
            public void run() {
                long saveUserTimeReset = System.currentTimeMillis();
                long saveGuildTimeReset = System.currentTimeMillis();
                long spawnQuickEventReset = System.currentTimeMillis();

                QuickEventHandler quickEventHandler = new QuickEventHandler();

                while (true) {
                    try {
                        sleep(TimeInterval.TIME_CHECK_INTERVAL_10.getValue());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    if (saveUserTimeReset + TimeInterval.TIME_CHECK_INTERVAL_10.getValue() < System
                            .currentTimeMillis()) {
                        saveUserTimeReset = System.currentTimeMillis();
                        new GameMlemUserDataManager().saveUsersAndRemoveFromCache();
                    }

                    if (saveGuildTimeReset + TimeInterval.TIME_CHECK_INTERVAL_10.getValue() < System
                            .currentTimeMillis()) {
                        saveGuildTimeReset = System.currentTimeMillis();
                        new GameMlemGuildManager().saveGuildsAndRemoveFromCache();
                    }

                    if (spawnQuickEventReset + TimeInterval.TIME_CHECK_INTERVAL_100.getValue() < System
                            .currentTimeMillis()) {
                        spawnQuickEventReset = System.currentTimeMillis();
                        quickEventHandler.spawnEvent();
                    }
                }
            }
        }.start();
    }
}
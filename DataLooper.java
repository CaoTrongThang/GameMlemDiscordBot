package src.ctt.GameMlemBot;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import src.ctt.GameMlemBot.Enums.TimeInterval;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemDataManager;

public class DataLooper {

    public DataLooper() {
        new Thread() {
            @Override
            public void run() {
                long saveUserTime = System.currentTimeMillis();
                while (true) {
                    try {
                        sleep(10000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (saveUserTime + TimeInterval.TIME_CHECK_INTERVAL_10.getValue() < System
                            .currentTimeMillis()) {
                        saveUserTime = System.currentTimeMillis();
                        new GameMlemDataManager().saveUserAndRemoveFromCache();
                    }
                }
            }
        }.start();
    }
}
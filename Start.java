package src.ctt.GameMlemBot;

import java.io.File;

import src.ctt.GameMlemBot.Logic.GameMlemBotManager.GameMlemBotManager;
import src.ctt.GameMlemBot.Logic.GameMlemBotManager.GameMlemGuildManager;
import src.ctt.GameMlemBot.Logic.Handler.LoadSlashCommands;
import src.ctt.GameMlemBot.Logic.Handler.OsuHandler.CommandHandler.OsuRequest;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserDataManager;
import src.ctt.GameMlemBot.Utils.EnviromentGet;
import src.ctt.GameMlemBot.Utils.FilePath;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Start {
    static {
        try {
            // Run osu calculator server
            Runtime runTime = Runtime.getRuntime();
            Process process = runTime.exec(FilePath.OSU_PP_CALCULATE_SERVER, null,
                    new File(FilePath.OSU_PP_CALCULATE_SERVER_DIR));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Start() {

        // get Osu API Key
        OsuRequest.postAccessAPIKey();

        new LoadSlashCommands();

        new DataLooper();

        // start bot
        GameMlemBotManager.connect(EnviromentGet.DISCORD_BOT_TOKEN());
    }
}

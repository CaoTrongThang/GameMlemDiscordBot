package src.ctt.GameMlemBot;

import java.io.File;

import src.ctt.GameMlemBot.Logic.Data.DataManager;
import src.ctt.GameMlemBot.Logic.GameMlemBotManager.DiscordBotManager;
import src.ctt.GameMlemBot.Logic.Handler.OsuHandler.OsuCommandHandler.OsuRequest;
import src.ctt.GameMlemBot.Utils.EnviromentGet;
import src.ctt.GameMlemBot.Utils.FilePath;

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

        DataManager.loadData();
        // start bot
        DiscordBotManager.connect(EnviromentGet.DISCORD_BOT_TOKEN());
    }
}

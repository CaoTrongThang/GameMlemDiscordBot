package src.ctt.GameMlemBot;

import src.ctt.GameMlemBot.Logic.Commands.OsuCommandHandler.OsuRequest;
import src.ctt.GameMlemBot.Logic.Data.DataManager;
import src.ctt.GameMlemBot.Logic.GameMlemBotManager.DiscordBotManager;
import src.ctt.GameMlemBot.Meta.MetaDataManager;
import src.ctt.GameMlemBot.Utils.EnviromentGet;

public class Start {
    public Start() {
        MetaDataManager.loadMetaData();

        // osu
        OsuRequest.postAccessAPIKey();

        DataManager.loadData();
        // start bot
        DiscordBotManager.connect(EnviromentGet.DISCORD_BOT_TOKEN());
    }
}

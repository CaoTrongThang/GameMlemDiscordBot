package src.ctt.GameMlemBot.Logic.Model.GameMlemData;

import net.dv8tion.jda.api.entities.Guild;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGameDataManager.OverOrLower.OverOrLower.OverOrLowerMatchManager;

public class GameMlemGuild {
    private Guild guild;
    private OverOrLowerMatchManager overOrLowerMatchManager = new OverOrLowerMatchManager();

    public Guild getGuild() {
        return this.guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public OverOrLowerMatchManager getOverOrLowerMatchManager() {
        return this.overOrLowerMatchManager;
    }

    public void setOverOrLowerMatchManager(OverOrLowerMatchManager overOrLowerMatchManager) {
        this.overOrLowerMatchManager = overOrLowerMatchManager;
    }

    public GameMlemGuild(Guild guid) {
        this.guild = guid;
    }
}

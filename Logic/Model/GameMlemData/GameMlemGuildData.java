package src.ctt.GameMlemBot.Logic.Model.GameMlemData;

import java.io.Serializable;

import net.dv8tion.jda.api.entities.Guild;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGameDataManager.OverOrLower.OverOrLower.OverOrLowerMatchManager;

public class GameMlemGuildData {

    private long GUILD_ID = 0;
    private OverOrLowerMatchManager overOrLowerMatchManager = new OverOrLowerMatchManager();
    private GameMlemDealer dealer = new GameMlemDealer();
    private long lastActivity = 0;

    public GameMlemGuildData(long guildID) {
        GUILD_ID = guildID;
    }

    public long getGUILD_ID() {
        return GUILD_ID;
    }

    public void setGUILD_ID(long gUILD_ID) {
        GUILD_ID = gUILD_ID;
    }

    public long getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(long lastActivity) {
        this.lastActivity = lastActivity;
    }

    public GameMlemDealer getDealer() {
        return dealer;
    }

    public void setDealer(GameMlemDealer dealer) {
        this.dealer = dealer;
    }

    public OverOrLowerMatchManager getOverOrLowerMatchManager() {
        return this.overOrLowerMatchManager;
    }

    public void setOverOrLowerMatchManager(OverOrLowerMatchManager overOrLowerMatchManager) {
        this.overOrLowerMatchManager = overOrLowerMatchManager;
    }

    // public Guild getGuild() {
    // return this.guild;
    // }

    // public void setGuild(Guild guild) {
    // this.guild = guild;
    // }
}

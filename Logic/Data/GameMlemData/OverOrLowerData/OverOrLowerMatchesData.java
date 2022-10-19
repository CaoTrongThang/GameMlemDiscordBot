package src.ctt.GameMlemBot.Logic.Data.GameMlemData.OverOrLowerData;

import java.util.List;

import src.ctt.GameMlemBot.Logic.Data.GameMlemData.GameMlemUserData;

public class OverOrLowerMatchesData {

    private long GUILD_ID;
    private int MATCH_ID;

    private List<GameMlemUserData> totalPlayers;
    private List<GameMlemUserData> totalLowerBet;
    private List<GameMlemUserData> totalOverBet;

    private long timeLeft;

    public OverOrLowerMatchesData(long GUILD_ID) {
        this.GUILD_ID = GUILD_ID;
        this.MATCH_ID = OverOrLowerMatchesManager.overOrLowerMatches.size() + 1;
    }

    // TODO
    public void addTotalPlayers(List<GameMlemUserData> totalPlayers) {
        this.totalPlayers = totalPlayers;
    }

    // TODO
    public void addTotalOverBet(List<GameMlemUserData> totalOverBet) {
        this.totalOverBet = totalOverBet;
    }

    // TODO
    public List<GameMlemUserData> addTotalLowerBet() {
        return this.totalLowerBet;
    }

    public int getMATCH_ID() {
        return MATCH_ID;
    }

    public long getGUILD_ID() {
        return GUILD_ID;
    }

    public void setGUILD_ID(long gUILD_ID) {
        GUILD_ID = gUILD_ID;
    }

    public void setMATCH_ID(int mATCH_ID) {
        MATCH_ID = mATCH_ID;
    }

    public List<GameMlemUserData> getTotalPlayers() {
        return this.totalPlayers;
    }

    public void setTotalLowerBet(List<GameMlemUserData> totalLowerBet) {
        this.totalLowerBet = totalLowerBet;
    }

    public List<GameMlemUserData> getTotalOverBet() {
        return this.totalOverBet;
    }

    public long getTimeLeft() {
        return this.timeLeft;
    }

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }

}

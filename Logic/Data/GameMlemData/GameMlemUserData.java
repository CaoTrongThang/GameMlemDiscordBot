package src.ctt.GameMlemBot.Logic.Data.GameMlemData;

import src.ctt.GameMlemBot.Logic.Data.GameMlemData.OverOrLowerData.OverOrLowerUserData;

public class GameMlemUserData {
    private long DISCORD_ID;
    private long totalMoney = 0;

    private GameMlemDailyRewardData dailyReward = new GameMlemDailyRewardData();
    private OverOrLowerUserData overOrLowerUserData = new OverOrLowerUserData();

    public long getDISCORD_ID() {
        return this.DISCORD_ID;
    }

    public void setDISCORD_ID(long DISCORD_ID) {
        this.DISCORD_ID = DISCORD_ID;
    }

    public long getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public GameMlemDailyRewardData getDailyReward() {
        return this.dailyReward;
    }

    public void setDailyReward(GameMlemDailyRewardData dailyReward) {
        this.dailyReward = dailyReward;
    }

    public OverOrLowerUserData getOverOrLowerUserData() {
        return this.overOrLowerUserData;
    }

    public void setOverOrLowerUserData(OverOrLowerUserData overOrLowerUserData) {
        this.overOrLowerUserData = overOrLowerUserData;
    }

}

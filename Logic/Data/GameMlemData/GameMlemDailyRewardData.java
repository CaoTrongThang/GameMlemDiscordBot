package src.ctt.GameMlemBot.Logic.Data.GameMlemData;

public class GameMlemDailyRewardData {
    private float dailyRewardTimestamp;
    private boolean rewardClaimed;
    private int totalRewardClaimed;

    public float getDailyRewardTimestamp() {
        return this.dailyRewardTimestamp;
    }

    public void setDailyRewardTimestamp(float dailyRewardTimestamp) {
        this.dailyRewardTimestamp = dailyRewardTimestamp;
    }

    public boolean isRewardClaimed() {
        return this.rewardClaimed;
    }

    public boolean getRewardClaimed() {
        return this.rewardClaimed;
    }

    public void setRewardClaimed(boolean rewardClaimed) {
        this.rewardClaimed = rewardClaimed;
    }

    public int getTotalRewardClaimed() {
        return this.totalRewardClaimed;
    }

    public void setTotalRewardClaimed(int totalRewardClaimed) {
        this.totalRewardClaimed = totalRewardClaimed;
    }
}

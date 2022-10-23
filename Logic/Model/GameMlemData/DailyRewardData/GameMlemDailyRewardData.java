package src.ctt.GameMlemBot.Logic.Model.GameMlemData.DailyRewardData;

import java.util.Date;

import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserData;

public class GameMlemDailyRewardData {
    private Date lastRewardDate;
    private Date nextRewardDate;

    private int totalRewardClaimed;

    private int highestDailyRewardStrike;
    private int dailyRewardStrike;

    public Date getNextRewardDate() {
        return nextRewardDate;
    }

    public void setNextRewardDate(Date nextRewardDate) {
        this.nextRewardDate = nextRewardDate;
    }

    public Date getLastRewardDate() {
        return lastRewardDate;
    }

    public void setLastRewardDate(Date lastRewardDate) {
        this.lastRewardDate = lastRewardDate;
    }

    public int getTotalRewardClaimed() {
        return this.totalRewardClaimed;
    }

    public void setTotalRewardClaimed(int totalRewardClaimed) {
        this.totalRewardClaimed = totalRewardClaimed;
    }

    public int getHighestDailyRewardStrike() {
        return this.highestDailyRewardStrike;
    }

    public void setHighestDailyRewardStrike(int highestDailyRewardStrike) {
        this.highestDailyRewardStrike = highestDailyRewardStrike;
    }

    public int getDailyRewardStrike() {
        return this.dailyRewardStrike;
    }

    public void setDailyRewardStrike(int dailyRewardStrike) {
        this.dailyRewardStrike = dailyRewardStrike;
    }

}

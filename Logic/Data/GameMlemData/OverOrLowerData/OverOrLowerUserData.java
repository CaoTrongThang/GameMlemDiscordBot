package src.ctt.GameMlemBot.Logic.Data.GameMlemData.OverOrLowerData;

public class OverOrLowerUserData {
    private int winTimes;
    private int loseTimes;
    private int playTime;
    private float winRate;
    private int overBetTimes;
    private int lowerBetTimes;
    private int matchesCreate;

    public int getWinTimes() {
        return this.winTimes;
    }

    public void setWinTimes(int winTimes) {
        this.winTimes = winTimes;
    }

    public int getLoseTimes() {
        return this.loseTimes;
    }

    public void setLoseTimes(int loseTimes) {
        this.loseTimes = loseTimes;
    }

    public int getPlayTime() {
        return this.playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public float getWinRate() {
        return this.winRate;
    }

    public void setWinRate(float winRate) {
        this.winRate = winRate;
    }

    public int getOverBetTimes() {
        return this.overBetTimes;
    }

    public void setOverBetTimes(int overBetTimes) {
        this.overBetTimes = overBetTimes;
    }

    public int getLowerBetTimes() {
        return this.lowerBetTimes;
    }

    public void setLowerBetTimes(int lowerBetTimes) {
        this.lowerBetTimes = lowerBetTimes;
    }

    public int getMatchesCreate() {
        return this.matchesCreate;
    }

    public void setMatchesCreate(int matchesCreate) {
        this.matchesCreate = matchesCreate;
    }

}

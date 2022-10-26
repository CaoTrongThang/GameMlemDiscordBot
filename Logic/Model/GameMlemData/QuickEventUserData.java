package src.ctt.GameMlemBot.Logic.Model.GameMlemData;

public class QuickEventUserData {
    private int totalCorrectMaths;
    private double totalMoneyFromCorrectMath;

    private double totalMoneyFromInVoiceChannel;

    private int totalBosseseKill;
    private int totalKilledByBosses;

    public int getTotalCorrectMaths() {
        return this.totalCorrectMaths;
    }

    public void setTotalCorrectMaths(int totalCorrectMath) {
        this.totalCorrectMaths = totalCorrectMath;
    }

    public double getTotalMoneyFromCorrectMath() {
        return this.totalMoneyFromCorrectMath;
    }

    public void setTotalMoneyFromCorrectMath(double totalMoneyFromCorrectMath) {
        this.totalMoneyFromCorrectMath = totalMoneyFromCorrectMath;
    }

    public double getTotalMoneyFromInVoiceChannel() {
        return this.totalMoneyFromInVoiceChannel;
    }

    public void setTotalMoneyFromInVoiceChannel(double totalMoneyFromInVoiceChannel) {
        this.totalMoneyFromInVoiceChannel = totalMoneyFromInVoiceChannel;
    }

    public int getTotalBosseseKill() {
        return this.totalBosseseKill;
    }

    public void setTotalBosseseKill(int totalBosseseKill) {
        this.totalBosseseKill = totalBosseseKill;
    }

    public int getTotalKilledByBosses() {
        return this.totalKilledByBosses;
    }

    public void setTotalKilledByBosses(int totalKilledByBosses) {
        this.totalKilledByBosses = totalKilledByBosses;
    }

}

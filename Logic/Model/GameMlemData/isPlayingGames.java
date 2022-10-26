package src.ctt.GameMlemBot.Logic.Model.GameMlemData;

public class isPlayingGames {
    private boolean overOrLower = false;
    private boolean fightingBoss = false;

    public boolean isFightingBoss() {
        return fightingBoss;
    }

    public void setFightingBoss(boolean fightingBoss) {
        this.fightingBoss = fightingBoss;
    }

    public boolean isOverOrLower() {
        return overOrLower;
    }

    public void setOverOrLower(boolean overOrLower) {
        this.overOrLower = overOrLower;
    }
}

package src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGameDataManager.OverOrLower.OverOrLower;

import java.util.ArrayList;
import java.util.List;

import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserData;

public class OverOrLowerParticipant {
    private GameMlemUserData user;
    private double lowerBetMoney = 0;
    private double overBetMoney = 0;

    public OverOrLowerParticipant(GameMlemUserData user) {
        this.user = user;
    }

    public GameMlemUserData getUser() {
        return this.user;
    }

    public void setUser(GameMlemUserData user) {
        this.user = user;
    }

    public double getLowerBetMoney() {
        return this.lowerBetMoney;
    }

    public void setLowerBetMoney(double lowerBetMoney) {
        this.lowerBetMoney = lowerBetMoney;
    }

    public double getOverBetMoney() {
        return this.overBetMoney;
    }

    public void setOverBetMoney(double overBetMoney) {
        this.overBetMoney = overBetMoney;
    }

}

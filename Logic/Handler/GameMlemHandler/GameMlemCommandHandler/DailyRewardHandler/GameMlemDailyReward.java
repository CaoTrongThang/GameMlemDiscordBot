package src.ctt.GameMlemBot.Logic.Handler.GameMlemHandler.GameMlemCommandHandler.DailyRewardHandler;

import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserData;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.DailyRewardData.GameMlemDailyRewardData;

public class GameMlemDailyReward {
    public static final int DEFAULT_REWARD = 10000;

    public double giveReward(GameMlemUserData user) {
        double reward = 0;
        if (DEFAULT_REWARD
                + (user.getDailyReward().getDailyRewardStrike() * 1000
                        + (Math.pow(0.99, Math.random() * 100) * 1000)) > 5000000) {
            reward = 5000000;
        } else {
            reward = DEFAULT_REWARD
                    + (user.getDailyReward().getDailyRewardStrike() * 1000
                            + (Math.pow(0.99, Math.random() * 100) * 1000));
        }
        user.setTOTAL_MONEY(user.getTOTAL_MONEY() + reward);
        return reward;
    }
}

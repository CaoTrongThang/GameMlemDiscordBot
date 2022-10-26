package src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGameDataManager.OverOrLower.OverOrLower;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import src.ctt.GameMlemBot.Enums.TimeInterval;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserData.GameMlemUserData;

public class OverOrLowerMatchManager {

    private int playTimesPerday = 5;
    private boolean isPlaying = false;
    private String matchOwner = "";

    private Date cooldown;

    private int timeLeft = TimeInterval.EXPIRE_TIME.getValue() / 1000;

    private List<OverOrLowerParticipant> matchData = new ArrayList<>();

    public OverOrLowerParticipant GetParticipant(long discordID) {

        for (OverOrLowerParticipant user : matchData) {
            if (user.getUser().getDISCORD_ID() == discordID) {
                return user;
            }
        }
        return null;
    }

    public void removeParticipant(long discordID) {
        for (int index = 0; index < matchData.size(); index++) {
            if (matchData.get(index).getUser().getDISCORD_ID() == discordID) {
                OverOrLowerParticipant user = matchData.get(index);
                if (user.getLowerBetMoney() > 0) {
                    user.getUser().setTOTAL_MONEY(user.getUser().getTOTAL_MONEY() + user.getLowerBetMoney());
                    user.setLowerBetMoney(0);
                } else if (user.getOverBetMoney() > 0) {
                    user.getUser().setTOTAL_MONEY(user.getUser().getTOTAL_MONEY() + user.getOverBetMoney());
                    user.setOverBetMoney(0);
                }
                matchData.remove(index);
            }
        }
    }

    public void removeParticipant(GameMlemUserData user) {
        for (int index = 0; index < matchData.size(); index++) {
            if (matchData.get(index).getUser() == user) {
                OverOrLowerParticipant u = matchData.get(index);
                if (u.getLowerBetMoney() > 0) {
                    u.getUser().setTOTAL_MONEY(u.getUser().getTOTAL_MONEY() + u.getLowerBetMoney());
                    u.setLowerBetMoney(0);
                } else if (u.getOverBetMoney() > 0) {
                    u.getUser().setTOTAL_MONEY(u.getUser().getTOTAL_MONEY() + u.getOverBetMoney());
                    u.setOverBetMoney(0);
                }
                matchData.remove(index);
            }
        }
    }

    public void clearMatchData() {
        matchData = new ArrayList<>();
    }

    public void addParticipant(OverOrLowerParticipant user) {
        for (OverOrLowerParticipant participant : matchData) {
            if (participant == user) {
                return;
            }
        }
        matchData.add(user);
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public boolean isIsPlaying() {
        return this.isPlaying;
    }

    public boolean getIsPlaying() {
        return this.isPlaying;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public String getMatchOwner() {
        return this.matchOwner;
    }

    public void setMatchOwner(String matchOwner) {
        this.matchOwner = matchOwner;
    }

    public List<OverOrLowerParticipant> getMatchData() {
        return this.matchData;
    }

    public void setMatchData(List<OverOrLowerParticipant> matchData) {
        this.matchData = matchData;
    }

    public int getPlayTimesPerday() {
        return playTimesPerday;
    }

    public void setPlayTimesPerday(int playTimesPerday) {
        this.playTimesPerday = playTimesPerday;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
}

package src.ctt.GameMlemBot.Logic.Data.GameMlemData.OverOrLowerData;

import java.util.ArrayList;
import java.util.List;

public class OverOrLowerMatchesManager {
    public static List<OverOrLowerMatchesData> overOrLowerMatches = new ArrayList<>();

    // TODO: IN PROGRESS
    public void addNewMatch(OverOrLowerMatchesData match) {
        overOrLowerMatches.add(match);
    }

    // TODO
    public void removeMatch(long matchID, long guildID) {

    }
}

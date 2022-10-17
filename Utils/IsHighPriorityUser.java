package src.ctt.GameMlemBot.Utils;

import src.ctt.GameMlemBot.Meta.HighPriorityUsers;

public class IsHighPriorityUser {
    public boolean Check(long discordID) {
        for (HighPriorityUsers user : HighPriorityUsers.highPriorityUsers) {
            if (user.getDISCORD_ID() == discordID) {
                return true;
            }
        }
        return false;
    }
}

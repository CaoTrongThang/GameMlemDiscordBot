package src.ctt.GameMlemBot.Utils;

import src.ctt.GameMlemBot.Meta.LowPriorityUsers;

public class IsLowPriorityUser {
    public boolean Check(long discordID) {
        for (LowPriorityUsers user : LowPriorityUsers.lowPriorityUsers) {
            if (user.getDISCORD_ID() == discordID) {
                return true;
            }
        }
        return false;
    }
}

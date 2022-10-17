package src.ctt.GameMlemBot.Meta;

import java.util.ArrayList;
import java.util.List;

public class LowPriorityUsers {
    private long DISCORD_ID;

    public long getDISCORD_ID() {
        return DISCORD_ID;
    }

    public static List<LowPriorityUsers> lowPriorityUsers = new ArrayList<>();
}

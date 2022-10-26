package src.ctt.GameMlemBot.Logic.Handler.GameMlem.QuickEventHandler;

import java.util.ArrayList;
import java.util.List;

import src.ctt.GameMlemBot.Interface.IQuickEvent;
import src.ctt.GameMlemBot.Logic.Handler.GameMlem.QuickEventHandler.QuickEvents.InVoiceChannelReward;

public class QuickEventHandler {
    public static List<IQuickEvent> events = new ArrayList<>();
    static {
        events.add(new InVoiceChannelReward());
    }

    public void spawnEvent() {
        int rand = (int) (Math.random() * events.size());
        while (rand > events.size()) {
            rand = (int) (Math.random() * events.size());
        }
        events.get(rand).spawnEvent();
    }
}

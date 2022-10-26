package src.ctt.GameMlemBot.Logic.Events;

import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.ctt.GameMlemBot.Logic.GameMlemBotManager.GameMlemGuildManager;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserDataManager;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGuildData;

public class OnUserUpdateOnlineStatus extends ListenerAdapter {
    @Override
    public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent e) {
        // GameMlemGuild guild = new
        // GameMlemGuildManager().getGuild(e.getGuild().getIdLong());

        // if (guild.getOverOrLowerMatchManager() != null) {
        // new
        // GameMlemGuildManager().getGuild(e.getGuild().getIdLong()).getOverOrLowerMatchManager()
        // .removeParticipant(e.getUser().getIdLong());
        // }
    }
}

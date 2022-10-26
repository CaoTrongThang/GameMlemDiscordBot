package src.ctt.GameMlemBot.Logic.Handler.GameMlem.QuickEventHandler.QuickEvents;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import src.ctt.GameMlemBot.Interface.IQuickEvent;
import src.ctt.GameMlemBot.Language.DefaultEmbed;
import src.ctt.GameMlemBot.Language.GameMlemEmbeds.QuickEventEmbed.inVoiceChannelEmbed;
import src.ctt.GameMlemBot.Logic.GameMlemBotManager.GameMlemBotManager;
import src.ctt.GameMlemBot.Logic.GameMlemBotManager.GameMlemGuildManager;
import src.ctt.GameMlemBot.Logic.Handler.GameMlem.QuickEventHandler.QuickEventHandler;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGuildData;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserDataManager;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.QuickEventUserData;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserData.GameMlemUserData;

public class InVoiceChannelReward implements IQuickEvent {
    GameMlemUserDataManager userDataManager = new GameMlemUserDataManager();

    @Override
    public void spawnEvent() {
        List<GameMlemUserData> usersInVoice = new ArrayList<>();
        List<User> users = new ArrayList<>();
        double rewardMoney = 0;

        Guild guild = null;
        for (Guild g : GameMlemBotManager.guilds) {
            for (VoiceChannel vc : g.getVoiceChannels()) {
                for (Member mem : vc.getMembers()) {
                    GameMlemUserData user = userDataManager.loadUser(mem.getUser().getIdLong());
                    if (!mem.getUser().isBot() && user != null) {
                        usersInVoice.add(user);
                        guild = g;
                        users.add(mem.getUser());
                    }
                }
            }
        }

        if (usersInVoice.size() > 100) {
            rewardMoney = (usersInVoice.size() * 1000) * (Math.pow(0.99, 100));
        }
        rewardMoney = (usersInVoice.size() * 1000) * (Math.pow(0.99, usersInVoice.size()));

        for (GameMlemUserData user : usersInVoice) {
            user.setTOTAL_MONEY(user.getTOTAL_MONEY() + rewardMoney);
            if (user.getQuickEvent() == null) {
                user.setQuickEvent(new QuickEventUserData());
            }
            user.getQuickEvent().setTotalMoneyFromInVoiceChannel(
                    user.getQuickEvent().getTotalMoneyFromInVoiceChannel() + rewardMoney);
        }

        guild.getTextChannelById("1011672433067040768")
                .sendMessageEmbeds(
                        new inVoiceChannelEmbed().inVoiceChannelRewardEmbed(usersInVoice, rewardMoney))
                .queue();

    }
}

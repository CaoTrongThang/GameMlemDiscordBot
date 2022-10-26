package src.ctt.GameMlemBot.Logic.Events;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.ctt.GameMlemBot.Enums.GameMlemGames;
import src.ctt.GameMlemBot.Language.DefaultEmbed;
import src.ctt.GameMlemBot.Language.GameMlemEmbeds.GameMlemEmbed;
import src.ctt.GameMlemBot.Logic.GameMlemBotManager.GameMlemGuildManager;
import src.ctt.GameMlemBot.Logic.Handler.GameMlem.GameMlemGameHandler.OverOrLowerHandler;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserDataManager;
import src.ctt.GameMlemBot.Utils.ThreadManager;

public class OnButtonInteraction extends ListenerAdapter {

    GameMlemUserDataManager gameMlemDataManager = new GameMlemUserDataManager();
    GameMlemGuildManager gameMlemGuildManager = new GameMlemGuildManager();

    @Override
    public void onButtonInteraction(ButtonInteractionEvent e) {
        new ThreadManager().createExecutorThread(new Thread() {
            @Override
            public void run() {
                // ? Load data when user use command, and remove it when user or guild is
                // ? inactive so save cache and increase scanning performance
                gameMlemDataManager.loadUser(e.getUser().getIdLong());
                gameMlemGuildManager.createGuildIfNotExist(e.getGuild().getIdLong());

                if (!new GameMlemUserDataManager().isExisted(e.getUser().getIdLong())) {
                    new DefaultEmbed().sendEphemeralMessage(e, GameMlemEmbed.NEED_TO_REGISTER);
                    return;
                }

                new GameMlemUserDataManager().getUser(e.getUser().getIdLong()).setIsUseCommand(true)
                        .setLAST_ACTIVITY(System.currentTimeMillis());
                new GameMlemUserDataManager().getUser(e.getUser().getIdLong()).setIsUseCommand(true);
                new GameMlemGuildManager().getGuild(e.getGuild().getIdLong())
                        .setLastActivity(System.currentTimeMillis());

                if (e.getComponentId().contains(GameMlemGames.TAIXIU.toString())) {
                    if (new GameMlemUserDataManager().getUser(e.getUser().getIdLong()) != null) {
                        new OverOrLowerHandler(e);
                    }
                }
            }
        });

    }
}

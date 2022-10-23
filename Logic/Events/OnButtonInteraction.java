package src.ctt.GameMlemBot.Logic.Events;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.ctt.GameMlemBot.Enums.GameMlemGames;
import src.ctt.GameMlemBot.Enums.Games;
import src.ctt.GameMlemBot.Language.DefaultEmbed;
import src.ctt.GameMlemBot.Language.GameMlemEmbeds.GameMlemEmbed;
import src.ctt.GameMlemBot.Language.GameMlemEmbeds.OverOrLowerEmbed;
import src.ctt.GameMlemBot.Logic.Handler.GameMlemHandler.GameMlemCommandHandler.GameMlemSlashHandler;
import src.ctt.GameMlemBot.Logic.Handler.GameMlemHandler.GameMlemCommandHandler.GameMlemGameHandler.OverOrLowerHandler;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemDataManager;

public class OnButtonInteraction extends ListenerAdapter {

    GameMlemDataManager gameMlemDataManager = new GameMlemDataManager();

    @Override
    public void onButtonInteraction(ButtonInteractionEvent e) {
        new Thread() {
            @Override
            public void run() {
                gameMlemDataManager.loadData(e.getUser().getIdLong());
                if (!new GameMlemDataManager().isExisted(e.getUser().getIdLong())) {
                    new DefaultEmbed().sendEphemeralMessage(e, GameMlemEmbed.NEED_TO_REGISTER);
                    return;
                }

                new GameMlemDataManager().getUser(e.getUser().getIdLong()).setIsUseCommand(true)
                        .setLAST_ACTIVITY(System.currentTimeMillis());

                if (e.getComponentId().contains(GameMlemGames.TAIXIU.toString())) {
                    if (new GameMlemDataManager().getUser(e.getUser().getIdLong()) != null) {
                        new OverOrLowerHandler(e);
                        new GameMlemDataManager().getUser(e.getUser().getIdLong()).setIsUseCommand(true);
                    }
                }
            }
        }.start();

    }
}

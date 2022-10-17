package src.ctt.GameMlemBot.Logic.Commands.OsuCommandHandler;

public class OsuNormalCommands {
    public long roll(long maxRoll) {
        return (long) (Math.random() * maxRoll);
    }
}

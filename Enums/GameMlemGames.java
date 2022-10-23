package src.ctt.GameMlemBot.Enums;

public enum GameMlemGames {
    TAIXIU("Tài Hay Xỉu?"),
    POKER("Poker");

    private String value;

    public String getValue() {
        return value;
    }

    private GameMlemGames(String standsFor) {
        this.value = standsFor;
    }
}

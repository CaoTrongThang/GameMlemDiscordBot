package src.ctt.GameMlemBot.Enums;

public enum Games {
    GAMEMLEM("Game Mlem"),
    OSU("Osu!"),
    BRAWLHALLA("Brawlhalla");

    private String value;

    public String getValue() {
        return value;
    }

    private Games(String str) {
        this.value = str;
    }
}

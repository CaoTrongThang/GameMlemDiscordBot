package src.ctt.GameMlemBot.Enum;

public enum Games {
    OSU("Osu!"),
    BRAWLHALL("Brawlhalla");

    private String value;

    public String getValue() {
        return value;
    }

    private Games(String str) {
        this.value = str;
    }
}

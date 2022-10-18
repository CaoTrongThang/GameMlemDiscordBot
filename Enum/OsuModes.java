package src.ctt.GameMlemBot.Enum;

public enum OsuModes {
    none(0, "none"),
    osu(0, "Osu!"),
    taiko(1, "Taiko"),
    fruits(2, "Fruits"),
    mania(3, "Mania");

    private int value;
    private String name;

    public String getName() {
        return name;
    }

    public int getID() {
        return value;
    }

    private OsuModes(int num, String name) {
        value = num;
        this.name = name;
    }

    public String getModeIconURL(OsuModes mode) {
        if (mode == OsuModes.osu) {
            return "https://lemmmy.pw/osusig/img/osu.png";
        } else if (mode == OsuModes.mania) {
            return "https://lemmmy.pw/osusig/img/mania.png";
        } else if (mode == OsuModes.taiko) {
            return "https://lemmmy.pw/osusig/img/taiko.png";
        } else if (mode == OsuModes.fruits) {
            return "https://lemmmy.pw/osusig/img/ctb.png";
        } else {
            return null;
        }
    }
}

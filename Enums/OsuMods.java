package src.ctt.GameMlemBot.Enums;

import java.lang.reflect.Field;

public enum OsuMods {
    NF("NoFail", 1),
    EZ("Easy", 2),
    TD("TouchDevice", 4),
    HD("Hidden", 8),
    HR("HardRock", 16),
    SD("SuddenDeath", 32),
    DT("DoubleTime", 64),
    RL("Relax", 128),
    HT("HalfTime", 256),
    NC("Nightcore", 512), // Only set along with DoubleTime. i.e: NC only gives 576
    FL("Flashlight", 1024),
    AT("Autoplay", 2048),
    SO("SpunOut", 4096),
    AP("Relax2", 8192), // Autopilot
    PF("Perfect", 16384), // Only set along with SuddenDeath. i.e: PF only gives 16416
    K4("Key4", 32768),
    K5("Key5", 65536),
    K6("Key6", 131072),
    K7("Key7", 262144),
    K8("Key8", 524288),
    FI("FadeIn", 1048576),
    RD("Random", 2097152),
    CM("Cinema", 4194304),
    TP("Target", 8388608),
    K9("Key9", 16777216),
    KC("KeyCoop", 33554432),
    K1("Key1", 67108864),
    K3("Key3", 134217728),
    K2("Key2", 268435456),
    SV2("ScoreV2", 536870912),
    MR("Mirror", 1073741824);

    private long num;
    private String acronyms;

    public long getBitwise() {
        return num;
    }

    public String getStandsFor() {
        return acronyms;
    }

    private OsuMods(String standsFor, long num) {
        this.acronyms = standsFor;
        this.num = num;
    }

    public long getTotalModsBitwise(String[] mods) {
        long total = 0;
        if (mods.length > 0 || mods == null) {
            for (int index = 0; index < mods.length; index++) {
                for (Field field : OsuMods.class.getFields()) {
                    if (mods[index].equalsIgnoreCase(OsuMods.valueOf(field.getName()).toString())) {
                        if (mods[index].equalsIgnoreCase("NC")) {
                            total += OsuMods.valueOf(mods[index]).getBitwise() + OsuMods.DT.getBitwise();
                        } else if (mods[index].equalsIgnoreCase("PF")) {
                            total += OsuMods.valueOf(mods[index]).getBitwise() + OsuMods.SD.getBitwise();
                        }
                        total += OsuMods.valueOf(field.getName()).getBitwise();
                    }
                }
            }
            return total;
        } else {
            return 0;
        }
    }
}

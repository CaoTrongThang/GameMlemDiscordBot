package src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuRecentScore;

import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuBeatmapData.OsuBeatmapData;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuBeatmapData.OsuBeatmapSet;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuUserData.OsuUserData;

public class OsuRecentScore {
    private String[] mods;
    private String rank;
    private int max_combo;
    private boolean passed;
    private long score;
    private String mode;
    private float pp;

    private OsuRecentScoreStatistics statistics;
    private OsuBeatmapData beatmap;
    private OsuBeatmapSet beatmapset;
    private OsuUserData user;

    public float getPp() {
        return pp;
    }

    public OsuUserData getUser() {
        return user;
    }

    public String getMode() {
        return mode;
    }

    public OsuBeatmapSet getBeatmapset() {
        return beatmapset;
    }

    public String[] getMods() {
        return this.mods;
    }

    public String getRank() {
        return this.rank;
    }

    public int getMax_combo() {
        return this.max_combo;
    }

    public boolean getPassed() {
        return this.passed;
    }

    public long getScore() {
        return this.score;
    }

    public OsuRecentScoreStatistics getStatistics() {
        return this.statistics;
    }

    public OsuBeatmapData getBeatmap() {
        return this.beatmap;
    }
}

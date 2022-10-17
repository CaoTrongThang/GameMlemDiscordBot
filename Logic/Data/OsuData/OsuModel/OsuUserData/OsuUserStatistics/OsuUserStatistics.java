package src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuUserData.OsuUserStatistics;

public class OsuUserStatistics {
    private float pp;
    private int global_rank;
    private int country_rank;
    private long ranked_score;
    private float hit_accuracy;
    private int play_count;
    private long play_time;
    private int maximum_combo;

    private OsuGradeCounts grade_counts;
    private OsuUserLevel level;

    public int getCountry_rank() {
        return country_rank;
    }

    public OsuGradeCounts getGrade_counts() {
        return this.grade_counts;
    }

    public OsuUserLevel getLevel() {
        return this.level;
    }

    public float getPp() {
        return this.pp;
    }

    public int getGlobal_rank() {
        return this.global_rank;
    }

    public long getRanked_score() {
        return this.ranked_score;
    }

    public float getHit_accuracy() {
        return this.hit_accuracy;
    }

    public int getPlay_count() {
        return this.play_count;
    }

    public long getPlay_time() {
        return this.play_time;
    }

    public int getMaximum_combo() {
        return this.maximum_combo;
    }
}

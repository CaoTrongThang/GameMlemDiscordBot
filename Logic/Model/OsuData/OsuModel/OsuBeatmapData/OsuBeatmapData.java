package src.ctt.GameMlemBot.Logic.Model.OsuData.OsuModel.OsuBeatmapData;

import com.google.gson.annotations.SerializedName;

public class OsuBeatmapData {
    private String status;
    private float ar;
    private float accuracy;
    private float bpm;
    private float difficulty_rating;
    private int count_circles;
    private int count_sliders;
    private int count_spinners;
    private float cs;
    private int hit_length;
    private String url;
    private String version;
    private long beatmapset_id;
    private long id;
    private String checksum;
    private int passcount;
    private int playcount;
    private int max_combo;

    public int getPasscount() {
        return passcount;
    }

    public int getPlaycount() {
        return playcount;
    }

    public int getMax_combo() {
        return max_combo;
    }

    public String getChecksum() {
        return checksum;
    }

    public long getId() {
        return id;
    }

    public long getBeatmapset_id() {
        return beatmapset_id;
    }

    public String getVersion() {
        return version;
    }

    public String getUrl() {
        return url;
    }

    public String getStatus() {
        return this.status;
    }

    public float getAr() {
        return this.ar;
    }

    public float getAccuracy() {
        return this.accuracy;
    }

    public float getBpm() {
        return this.bpm;
    }

    public float getDifficulty_rating() {
        return this.difficulty_rating;
    }

    public int getCount_circles() {
        return this.count_circles;
    }

    public int getCount_sliders() {
        return this.count_sliders;
    }

    public int getCount_spinners() {
        return this.count_spinners;
    }

    public float getCs() {
        return this.cs;
    }

    public int getHit_length() {
        return this.hit_length;
    }
}

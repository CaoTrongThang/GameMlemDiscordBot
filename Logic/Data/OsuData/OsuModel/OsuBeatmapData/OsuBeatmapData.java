package src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuBeatmapData;

public class OsuBeatmapData {
    private String status;
    private float ar;
    private byte accuracy;
    private short bpm;
    private float difficulty_rating;
    private int count_circles;
    private int count_sliders;
    private int count_spinners;
    private float cs;
    private int hit_length;
    private String url;
    private String version;

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

    public byte getAccuracy() {
        return this.accuracy;
    }

    public short getBpm() {
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

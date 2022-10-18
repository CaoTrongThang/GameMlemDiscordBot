package src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuUserData;

import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuUserData.OsuUserStatistics.OsuUserMonthlyPlayerCounts;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuUserData.OsuUserStatistics.OsuUserStatistics;

public class OsuUserData {
    private boolean is_supporter;
    private String[] playstyle;
    private boolean is_online;

    private String username;
    private String avatar_url;
    private String country_code;
    private String default_group;
    private long id;
    private String playmode;

    private OsuUserMonthlyPlayerCounts[] monthly_playcounts;

    private OsuUserStatistics statistics;
    private OsuCover cover;

    public String getPlaymode() {
        return playmode;
    }

    public OsuUserMonthlyPlayerCounts[] getMothly_playcounts() {
        return monthly_playcounts;
    }

    public boolean getIs_online() {
        return is_online;
    }

    public boolean getIs_supporter() {
        return this.is_supporter;
    }

    public String[] getPlaystyle() {
        return this.playstyle;
    }

    public String getUsername() {
        return this.username;
    }

    public String getAvatar_url() {
        return this.avatar_url;
    }

    public String getCountry_code() {
        return this.country_code;
    }

    public String getDefault_group() {
        return this.default_group;
    }

    public long getId() {
        return this.id;
    }

    public OsuUserStatistics getStatistics() {
        return this.statistics;
    }

    public OsuCover getCover() {
        return this.cover;
    }
}
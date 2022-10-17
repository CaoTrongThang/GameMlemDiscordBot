package src.ctt.GameMlemBot.Logic.Data.OsuData;

public class OsuUserDiscordData {
    private long DISCORD_ID;
    private String OSU_ID;
    private String OSU_USERNAME;

    public String getOsuUserName() {
        return OSU_USERNAME;
    }

    public void setOsuUserName(String osuUsername) {
        this.OSU_USERNAME = osuUsername;
    }

    public long getDiscordID() {
        return DISCORD_ID;
    }

    public void setDiscordID(long discordID) {
        this.DISCORD_ID = discordID;
    }

    public String getOsuID() {
        return OSU_ID;
    }

    public void setOsuID(String osuID) {
        this.OSU_ID = osuID;
    }
}

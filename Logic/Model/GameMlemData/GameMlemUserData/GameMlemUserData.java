package src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserData;

import net.dv8tion.jda.api.entities.User;
import src.ctt.GameMlemBot.Logic.Model.BrawlhallaData.BrawlhallaUserDiscordData;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.QuickEventUserData;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.isPlayingGames;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.DailyRewardData.GameMlemDailyRewardData;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGameUserData.OverOrLowerUserData;
import src.ctt.GameMlemBot.Logic.Model.OsuData.OsuUserDiscordData;

public class GameMlemUserData {
    private long DISCORD_ID;
    private double TOTAL_MONEY = 0;
    private String USER_NAME;
    private long LAST_ACTIVITY;

    private boolean isHighPriority = false;
    private boolean isLowPriority = false;

    private boolean isUseCommand = false;

    private OsuUserDiscordData osuDiscord = new OsuUserDiscordData();
    private BrawlhallaUserDiscordData brawlhallaDiscord = new BrawlhallaUserDiscordData();

    private GameMlemDailyRewardData dailyReward = new GameMlemDailyRewardData();

    private OverOrLowerUserData overOrLower = new OverOrLowerUserData();

    private isPlayingGames isPlaying = new isPlayingGames();

    private QuickEventUserData quickEvent = new QuickEventUserData();

    public QuickEventUserData getQuickEvent() {
        return quickEvent;
    }

    public void setQuickEvent(QuickEventUserData quickEvent) {
        this.quickEvent = quickEvent;
    }

    public isPlayingGames getIsPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(isPlayingGames isPlaying) {
        this.isPlaying = isPlaying;
    }

    public GameMlemUserData(User user) {
        this.DISCORD_ID = user.getIdLong();
        this.USER_NAME = user.getName();
    }

    public long getLAST_ACTIVITY() {
        return LAST_ACTIVITY;
    }

    public void setLAST_ACTIVITY(long lAST_ACTIVITY) {
        LAST_ACTIVITY = lAST_ACTIVITY;
    }

    public boolean isIsHighPriority() {
        return this.isHighPriority;
    }

    public boolean getIsHighPriority() {
        return this.isHighPriority;
    }

    public void setIsHighPriority(boolean isHighPriority) {
        this.isHighPriority = isHighPriority;
    }

    public boolean isUseCommand() {
        return this.isUseCommand;
    }

    public OsuUserDiscordData getOsuDiscord() {
        return this.osuDiscord;
    }

    public void setOsuDiscord(OsuUserDiscordData osuDiscord) {
        this.osuDiscord = osuDiscord;
    }

    public BrawlhallaUserDiscordData getBrawlhallaDiscord() {
        return this.brawlhallaDiscord;
    }

    public void setBrawlhallaDiscord(BrawlhallaUserDiscordData brawlhallaDiscord) {
        this.brawlhallaDiscord = brawlhallaDiscord;
    }

    public boolean isIsLowPriority() {
        return this.isLowPriority;
    }

    public boolean getIsLowPriority() {
        return this.isLowPriority;
    }

    public GameMlemUserData setIsLowPriority(boolean isLowPriority) {
        this.isLowPriority = isLowPriority;
        return this;
    }

    public OverOrLowerUserData getOverOrLower() {
        return overOrLower;
    }

    public GameMlemUserData setOverOrLower(OverOrLowerUserData overOrLower) {
        this.overOrLower = overOrLower;
        return this;
    }

    public boolean isHighPriority() {
        return isHighPriority;
    }

    public boolean getIsUseCommand() {
        return isUseCommand;
    }

    public GameMlemUserData setIsUseCommand(boolean isUseCommand) {
        this.isUseCommand = isUseCommand;
        return this;
    }

    public void setHighPriority(boolean isHighPriority) {
        this.isHighPriority = isHighPriority;
    }

    public boolean isLowPriority() {
        return isLowPriority;
    }

    public GameMlemUserData setLowPriority(boolean isLowPriority) {
        this.isLowPriority = isLowPriority;
        return this;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public GameMlemUserData setUSER_NAME(String uSER_NAME) {
        USER_NAME = uSER_NAME;
        return this;
    }

    public long getDISCORD_ID() {
        return this.DISCORD_ID;
    }

    public GameMlemUserData setDISCORD_ID(long DISCORD_ID) {
        this.DISCORD_ID = DISCORD_ID;
        return this;
    }

    public double getTOTAL_MONEY() {
        return this.TOTAL_MONEY;
    }

    public GameMlemUserData setTOTAL_MONEY(double totalMoney) {
        this.TOTAL_MONEY = totalMoney;
        return this;
    }

    public GameMlemDailyRewardData getDailyReward() {
        return this.dailyReward;
    }

    public GameMlemUserData setDailyReward(GameMlemDailyRewardData dailyReward) {
        this.dailyReward = dailyReward;
        return this;
    }
}

package src.ctt.GameMlemBot.Logic.Model.OsuData;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemDataManager;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserData;
import src.ctt.GameMlemBot.Logic.Model.OsuData.OsuModel.OsuUserData.OsuUserData;
import src.ctt.GameMlemBot.Utils.ByteOperator;
import src.ctt.GameMlemBot.Utils.FilePath;

public class OsuDataManager {
    public GameMlemDataManager gameMlemDataManager = new GameMlemDataManager();

    public static Gson gson = new GsonBuilder().create();

    public boolean isLinkedWithDiscord(OsuUserDiscordData dis) {
        for (GameMlemUserData user : gameMlemDataManager.gameMlemUsers) {
            if (user.getOsuDiscord() == null) {
                return false;
            } else if (user.getOsuDiscord().getDiscordID() == dis.getDiscordID()) {
                return true;
            }
        }
        return false;
    }

    public boolean isLinkedWithDiscord(long discordID) {
        for (GameMlemUserData user : gameMlemDataManager.gameMlemUsers) {
            if (user.getOsuDiscord() == null) {
                return false;
            } else if (user.getOsuDiscord().getDiscordID() == discordID) {
                return true;
            }
        }
        return false;
    }

    public void addOsuDiscordLink(OsuUserDiscordData dis) {
        for (GameMlemUserData user : gameMlemDataManager.gameMlemUsers) {
            if (user.getDISCORD_ID() == dis.getDiscordID()) {
                user.setOsuDiscord(dis);
            }
        }
    }

    public void removeOsuDiscordLink(long discordID) {
        for (GameMlemUserData user : gameMlemDataManager.gameMlemUsers) {
            if (user.getDISCORD_ID() == discordID) {
                user.setOsuDiscord(null);
            }
        }
    }

    public void removeOsuDiscordLink(OsuUserDiscordData dis) {
        for (GameMlemUserData user : gameMlemDataManager.gameMlemUsers) {
            if (user.getDISCORD_ID() == dis.getDiscordID()) {
                user.setOsuDiscord(null);
            }
        }
    }

    public OsuUserDiscordData findOsuDiscordLink(long discordID) {
        for (GameMlemUserData user : gameMlemDataManager.gameMlemUsers) {
            if (user.getOsuDiscord() == null) {
                return null;
            } else if (user.getOsuDiscord().getDiscordID() == discordID) {
                return user.getOsuDiscord();
            }
        }
        return null;
    }

    public void changeOsuDiscordLink(OsuUserData user, long discordID) {
        for (GameMlemUserData u : gameMlemDataManager.gameMlemUsers) {
            if (u.getOsuDiscord().getDiscordID() == discordID) {
                u.getOsuDiscord().setDiscordID(discordID);
                u.getOsuDiscord().setOsuUserName(user.getUsername());
            }
        }
    }

    public String getOsuID(long discordID) {
        for (GameMlemUserData user : gameMlemDataManager.gameMlemUsers) {
            if (user.getOsuDiscord() == null) {
                return null;
            } else if (user.getOsuDiscord().getDiscordID() == discordID) {
                return user.getOsuDiscord().getOsuID();
            }
        }
        return null;
    }
}

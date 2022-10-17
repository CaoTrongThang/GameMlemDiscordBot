package src.ctt.GameMlemBot.Logic.Data.OsuData;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import src.ctt.GameMlemBot.Interface.IDataManager;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuUserData.OsuUserData;
import src.ctt.GameMlemBot.Utils.ByteOperator;
import src.ctt.GameMlemBot.Utils.FilePath;

public class OsuDataManager implements IDataManager {
    private static List<OsuUserDiscordData> osuDiscordLinkList;

    public static Gson gson = new GsonBuilder().create();

    @Override
    public void loadData() {
        Type type = new TypeToken<List<OsuUserDiscordData>>() {
        }.getType();
        String data = new String(ByteOperator.readByteFromFile(FilePath.OSU_DISCORD_LINK_PATH), StandardCharsets.UTF_8);
        osuDiscordLinkList = gson.fromJson(data, type);
    }

    @Override
    public void saveData() {
        String data = gson.toJson(osuDiscordLinkList);
        ByteOperator.writeByteToFile(data.getBytes(), FilePath.OSU_DISCORD_LINK_PATH);
    }

    public boolean isLinkedWithDiscord(OsuUserDiscordData dis) {
        if (osuDiscordLinkList == null) {
            osuDiscordLinkList = new ArrayList<>();
            return false;
        }
        for (OsuUserDiscordData user : osuDiscordLinkList) {
            if (user.getDiscordID() == dis.getDiscordID()) {
                return true;
            }
        }
        return false;
    }

    public boolean isLinkedWithDiscord(long discordID) {
        if (osuDiscordLinkList == null) {
            osuDiscordLinkList = new ArrayList<>();
            return false;
        }
        for (OsuUserDiscordData user : osuDiscordLinkList) {
            if (user.getDiscordID() == discordID) {
                return true;
            }
        }
        return false;
    }

    public void addOsuDiscordLink(OsuUserDiscordData dis) {
        if ((dis.getOsuID().equalsIgnoreCase("") || dis.getOsuID() == null) && dis.getDiscordID() == 0) {
            return;
        } else {
            osuDiscordLinkList.add(dis);
        }
    }

    public void removeOsuDiscordLink(long discordID) {
        for (int index = 0; index < osuDiscordLinkList.size(); index++) {
            if (osuDiscordLinkList.get(index).getDiscordID() == discordID) {
                osuDiscordLinkList.remove(index);
                return;
            }
        }
    }

    public void removeOsuDiscordLink(OsuUserDiscordData dis) {
        for (int index = 0; index < osuDiscordLinkList.size(); index++) {
            if (osuDiscordLinkList.get(index).getDiscordID() == dis.getDiscordID()) {
                osuDiscordLinkList.remove(index);
            }
        }
    }

    public OsuUserDiscordData findOsuDiscordLink(long discordID) {
        for (int index = 0; index < osuDiscordLinkList.size(); index++) {
            if (osuDiscordLinkList.get(index).getDiscordID() == discordID) {
                return osuDiscordLinkList.get(index);
            }
        }
        return null;
    }

    public void changeOsuDiscordLink(OsuUserData user, long discordID) {
        for (int index = 0; index < osuDiscordLinkList.size(); index++) {
            if (osuDiscordLinkList.get(index).getDiscordID() == discordID) {
                osuDiscordLinkList.get(index).setOsuID(Long.toString(user.getId()));
                osuDiscordLinkList.get(index).setOsuUserName(user.getUsername());
            }
        }
    }

    public String getOsuID(long discordID) {
        for (int index = 0; index < osuDiscordLinkList.size(); index++) {
            if (osuDiscordLinkList.get(index).getDiscordID() == discordID) {
                return osuDiscordLinkList.get(index).getOsuID();
            }
        }
        return null;
    }

}

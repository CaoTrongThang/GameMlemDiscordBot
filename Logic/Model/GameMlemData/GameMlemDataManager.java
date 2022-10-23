package src.ctt.GameMlemBot.Logic.Model.GameMlemData;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mongodb.client.FindIterable;

import src.ctt.GameMlemBot.Enums.TimeInterval;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserData;
import src.ctt.GameMlemBot.MongoDB.MongoDBManager;
import src.ctt.GameMlemBot.Utils.ByteOperator;
import src.ctt.GameMlemBot.Utils.FilePath;

public class GameMlemDataManager {

    public static List<GameMlemUserData> gameMlemUsers = new ArrayList<>();
    public MongoDBManager mongoDBManager = new MongoDBManager();

    public void saveData() {
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").create();

        for (GameMlemUserData user : gameMlemUsers) {
            if (user.isUseCommand()) {
                user.setIsUseCommand(false);
                if (user.isUseCommand()) {
                    user.setIsUseCommand(false);
                    mongoDBManager.findAndReplace(MongoDBManager.USERS_COLLECTION, user.getDISCORD_ID(),
                            Document.parse(gson.toJson(user)));
                }
            }
        }
    }

    public void saveData(GameMlemUserData user) {
        if (user == null) {
            return;
        }
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").create();
        if (user.isUseCommand()) {
            user.setIsUseCommand(false);
            mongoDBManager.findAndReplace(MongoDBManager.USERS_COLLECTION, user.getDISCORD_ID(),
                    Document.parse(gson.toJson(user)));
        }

    }

    public void loadData() {
        FindIterable<Document> users = mongoDBManager.getCollection(MongoDBManager.USERS_COLLECTION).find();
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").create();

        // Read All User Files from MongoDB
        if (users != null) {
            for (Document doc : users) {
                GameMlemUserData user = gson.fromJson(doc.toJson(), GameMlemUserData.class);
                user.setLAST_ACTIVITY(System.currentTimeMillis());
                gameMlemUsers.add(user);
            }
        }
    }

    public boolean loadData(long discordID) {
        if (isExisted(discordID)) {
            return true;
        } else {
            Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").create();
            Document query = new Document("DISCORD_ID", discordID);
            FindIterable<Document> users = mongoDBManager.getCollection(MongoDBManager.USERS_COLLECTION).find(query);
            if (users == null) {
                return false;
            }

            for (Document doc : users) {
                GameMlemUserData user = gson.fromJson(doc.toJson(), GameMlemUserData.class);
                user.setLAST_ACTIVITY(System.currentTimeMillis());
                gameMlemUsers.add(user);
            }
            return true;
        }
    }

    public boolean checkUserInDB(long discordID) {
        if (isExisted(discordID)) {
            return false;
        } else {
            Document query = new Document("DISCORD_ID", discordID);
            FindIterable<Document> users = mongoDBManager.getCollection(MongoDBManager.USERS_COLLECTION).find(query);
            if (users == null) {
                return false;
            }

            return true;
        }
    }

    public void addNewUser(GameMlemUserData e) {
        gameMlemUsers.add(e);
    }

    /**
     * Remove user from cache and save to DB
     * 
     * @param discordID user's discord indentify
     * 
     */
    public void saveUserAndRemoveFromCache() {
        for (GameMlemUserData user : gameMlemUsers) {
            if ((user.getLAST_ACTIVITY()
                    + TimeInterval.USER_MAX_INACTIVITY_TIME_10.getValue()) < System
                            .currentTimeMillis()) {

                try {
                    for (Field field : user.getIsPlaying().getClass().getDeclaredFields()) {
                        field.setAccessible(true);
                        if ((boolean) field.get(user.getIsPlaying())) {
                            return;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                saveData(user);
                gameMlemUsers.remove(user);
                return;
            }
        }
    }

    public void removeUserFromDB(Long discordID) {
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").create();
        for (int index = 0; index < gameMlemUsers.size(); index++) {
            if (gameMlemUsers.get(index).getDISCORD_ID() == discordID) {
                mongoDBManager.deleteDoc(MongoDBManager.USERS_COLLECTION,
                        Document.parse(gson.toJson(gameMlemUsers.get(index))));
                gameMlemUsers.remove(index);
            }
        }
    }

    public boolean isHighPriorityUser(long discordID) {
        for (GameMlemUserData user : gameMlemUsers) {
            if (user.getDISCORD_ID() == discordID) {
                return user.isHighPriority();
            }
        }
        return false;
    }

    public boolean isLowPriorityUser(long discordID) {
        for (GameMlemUserData user : gameMlemUsers) {
            if (user.getDISCORD_ID() == discordID) {
                return user.isLowPriority();
            }
        }
        return false;
    }

    public GameMlemUserData getUser(long discordID) {
        for (GameMlemUserData user : gameMlemUsers) {
            if (user.getDISCORD_ID() == discordID) {
                return user;
            }
        }
        return null;
    }

    public boolean isExisted(long discordID) {
        for (GameMlemUserData user : gameMlemUsers) {
            if (user.getDISCORD_ID() == discordID) {
                return true;
            }
        }
        return false;
    }

    // TODO
    public void addHighPriorityUser() {

    }

    // TODO
    public void removeHighPriorityUser() {

    }

    // TODO
    public void addLowPriorityUser() {

    }

    // TODO
    public void removeLowPriorityUser() {

    }

}

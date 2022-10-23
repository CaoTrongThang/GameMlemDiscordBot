package src.ctt.GameMlemBot.MongoDB;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.UpdateOptions;

public class MongoDBManager {

    public static final String dbName = "GameMlemUserData";
    public static final String USERS_COLLECTION = "Users";

    private static MongoDatabase db;
    private static MongoClient client = MongoClients.create("mongodb://localhost:27017");

    static {
        db = client.getDatabase(dbName);
    }

    public MongoCollection<Document> getCollection(String collection) {
        return db.getCollection(collection);
    }

    public void insertDoc(String collection, Document doc) {
        db.getCollection(collection).insertOne(doc);
    }

    public void deleteDoc(String collection, Document doc) {
        db.getCollection(collection).deleteOne(doc);
    }

    public void findAndReplace(String collection, long discordID, Document newDoc) {
        Document query = new Document("DISCORD_ID", discordID);
        FindOneAndReplaceOptions option = new FindOneAndReplaceOptions();

        option.upsert(true);

        db.getCollection(collection).findOneAndReplace(query, newDoc, option);
    }

}

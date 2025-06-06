package jdbc;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author Duc Pham Ngoc
 */
public class MongoDBHelper {

    public static MongoDatabase getDatabase() {
        return MongoDBConnection.getDatabase();
    }

    public static MongoCollection<Document> getCollection(String collectionName) {
        return getDatabase().getCollection(collectionName);
    }

    public static void closeConnection() {
        MongoDBConnection.closeConnection();
    }
}

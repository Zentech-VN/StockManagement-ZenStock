package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.ChatMessage;
import java.util.Date;
import jdbc.MongoDBHelper;
import org.bson.Document;

public class ChatDAO {

    private static final String COLLECTION_NAME = "chat_messages"; // tên collection trong MongoDB

    // Instance field nếu muốn dùng kiểu non-static sau này
    private MongoCollection<Document> collection;

    public ChatDAO() {
        this.collection = MongoDBHelper.getCollection(COLLECTION_NAME);
    }

    public static boolean insert(ChatMessage message) {
        try {
            MongoCollection<Document> collection = MongoDBHelper.getCollection(COLLECTION_NAME);

            Document doc = new Document("role", message.getRole())
                    .append("content", message.getContent())
                    .append("timestamp", message.getTimestamp() != null ? message.getTimestamp() : new Date());

            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

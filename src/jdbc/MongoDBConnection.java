package jdbc;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Duc Pham Ngoc
 */
public class MongoDBConnection {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    static {
        try (InputStream input = MongoDBConnection.class.getResourceAsStream("/config/db.properties")) {
            Properties props = new Properties();
            props.load(input);

            String host = props.getProperty("mongodb.host", "14.225.192.94");
            String port = props.getProperty("mongodb.port", "27017");
            String databaseName = props.getProperty("mongodb.database", "zentechStockManagement_ActivityLog");
            String username = props.getProperty("mongodb.username", "");
            String password = props.getProperty("mongodb.password", "");
            String authDatabase = props.getProperty("mongodb.authDatabase", "admin");

            String connectionString;
            if (username != null && !username.trim().isEmpty() &&
                password != null && !password.trim().isEmpty()) {
                //kết nối với authentication
                connectionString = String.format("mongodb://%s:%s@%s:%s/%s?authSource=%s",
                    username, password, host, port, databaseName, authDatabase);
            } else {
                connectionString = String.format("mongodb://%s:%s/%s", host, port, databaseName);
            }

            System.out.println("MongoDB connection string: " + connectionString.replaceAll(password, "***"));

            MongoClientURI uri = new MongoClientURI(connectionString);
            mongoClient = new MongoClient(uri);
            database = mongoClient.getDatabase(databaseName);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading MongoDB configuration", e);
        }
    }


    public static MongoDatabase getDatabase() {
        return database;
    }

    public static void closeConnection() {
        try {
            if (mongoClient != null) {
                mongoClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

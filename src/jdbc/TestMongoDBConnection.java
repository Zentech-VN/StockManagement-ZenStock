package jdbc;

import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Duc Pham Ngoc
 */
public class TestMongoDBConnection {

    public static void main(String[] args) {
        try {
            MongoDatabase db = MongoDBConnection.getDatabase();
            if (db != null) {
                System.out.println("Kết nối MongoDB thành công!");
                System.out.println("Database name: " + db.getName());
            }
        } catch (Exception e) {
            System.out.println("Kết nối MongoDB thất bại:");
            e.printStackTrace();
        }
    }

}

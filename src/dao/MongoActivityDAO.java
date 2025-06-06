package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import entity.Activity;
import jdbc.MongoDBHelper;
import org.bson.Document;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;

public class MongoActivityDAO {
    
    private static final String COLLECTION_NAME = "activityLog";
    private MongoCollection<Document> collection;
    
    public MongoActivityDAO() {
        this.collection = MongoDBHelper.getCollection(COLLECTION_NAME);
    }

    public static boolean insert(Activity activity) {
        try {
            MongoCollection<Document> collection = MongoDBHelper.getCollection(COLLECTION_NAME);
            
            Document doc = new Document("user_id", activity.getUserId())
                    .append("action", activity.getAction())
                    .append("timestamp", new Date())
                    .append("created_at", new Date());
            
            collection.insertOne(doc);
            //System.out.println("Activity log inserted to MongoDB: " + activity.getUserId() + " - " + activity.getAction());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public void loadActivityLogs(JTable tblActivity) {
        try {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Tên tài khoản");
            model.addColumn("Hành động");
            model.addColumn("Mốc thời gian");
            
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            try (MongoCursor<Document> cursor = collection.find()
                    .sort(new Document("timestamp", -1))
                    .iterator()) {
                
                while (cursor.hasNext()) {
                    Document doc = cursor.next();
                    
                    String userId = doc.getString("user_id");
                    String action = doc.getString("action");
                    Date timestamp = doc.getDate("timestamp");
                    
                    LocalDateTime ldt = timestamp.toInstant()
                            .atZone(java.time.ZoneId.systemDefault())
                            .toLocalDateTime();
                    String timeStr = ldt.format(fmt);
                    
                    Vector<String> row = new Vector<>();
                    row.add(userId);
                    row.add(action);
                    row.add(timeStr);
                    model.addRow(row);
                }
            }
            
            tblActivity.setModel(model);
            //System.out.println("Loaded " + model.getRowCount() + " activity logs from MongoDB");
            
        } catch (Exception e) {
            e.printStackTrace();
            DefaultTableModel emptyModel = new DefaultTableModel();
            emptyModel.addColumn("Tên tài khoản");
            emptyModel.addColumn("Hành động");
            emptyModel.addColumn("Mốc thời gian");
            tblActivity.setModel(emptyModel);
        }
    }

    public long countActivityLogs() {
        try {
            return collection.countDocuments();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

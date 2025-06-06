package dao;

import java.sql.SQLException;
import javax.swing.JTable;
import entity.Activity;
import java.time.LocalDateTime;

public class ActivityDAO {

    public static void insert(Activity activity) throws SQLException {
        MongoActivityDAO.insert(activity);
    }
    
    public static void logActivity(String userId, String action) {
        try {
            Activity activity = new Activity(userId, action, LocalDateTime.now());
            insert(activity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadActivityLogs(JTable tblActivity) {
        MongoActivityDAO mongoDAO = new MongoActivityDAO();
        mongoDAO.loadActivityLogs(tblActivity);
    }
}

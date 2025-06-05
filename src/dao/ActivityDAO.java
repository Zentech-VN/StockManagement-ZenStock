package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import jdbc.ConnectionHelper;
import entity.Activity;

public class ActivityDAO {

    public static void insert(Activity activity) throws SQLException {
        String sql = "INSERT INTO activity_log (user_id, action, timestamp) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, activity.getUserId());
            ps.setString(2, activity.getAction());
            ps.setTimestamp(3, Timestamp.valueOf(activity.getTimestamp()));
            ps.executeUpdate();
        }
    }

    public void loadActivityLogs(JTable tblActivity) {
        String sql = "SELECT user_id, action, timestamp FROM activity_log ORDER BY timestamp DESC";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Tên tài khoản");
            model.addColumn("Hành động");
            model.addColumn("Mốc thời gian");

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            while (rs.next()) {
                String user = rs.getString("user_id");
                String action = rs.getString("action");
                Timestamp ts = rs.getTimestamp("timestamp");
                LocalDateTime ldt = ts.toLocalDateTime();
                String timeStr = ldt.format(fmt);

                Vector<String> row = new Vector<>();
                row.add(user);
                row.add(action);
                row.add(timeStr);
                model.addRow(row);
            }
            tblActivity.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

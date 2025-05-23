package helper;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = ConnectionHelper.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Connected successfully via DataSource!");
            }
        } catch (Exception e) {
            System.out.println("Kết nối thất bại:");
            e.printStackTrace();
        }
    }
}

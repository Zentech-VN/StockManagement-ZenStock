package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariDataSource;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ConnectionHelper {

    private static DataSource dataSource;

    static {
        try (InputStream input = ConnectionHelper.class.getResourceAsStream("/config/db.properties")) {
            Properties props = new Properties();
            props.load(input);
            // Initialize DataSource
            dataSource = DataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            throw new RuntimeException("Error loading DB configuration", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = dataSource.getConnection();
            return conn;
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        } catch (Exception e) {
        }
    }
    
    public static String getPoolStatus() {
        if (dataSource instanceof HikariDataSource) {
            HikariDataSource hikariDS = (HikariDataSource) dataSource;
            return String.format(
                "Pool Status - Active: %d, Idle: %d, Total: %d",
                hikariDS.getHikariPoolMXBean().getActiveConnections(),
                hikariDS.getHikariPoolMXBean().getIdleConnections(),
                hikariDS.getHikariPoolMXBean().getTotalConnections()
            );
        }
        return "Pool status not available";
    }
    
    public static DataSource getDataSource() {
        return dataSource;
    }
    
    public static void shutdownPool() {
        if (dataSource instanceof HikariDataSource) {
            HikariDataSource hikariDS = (HikariDataSource) dataSource;
            hikariDS.close();

        }
    }
}

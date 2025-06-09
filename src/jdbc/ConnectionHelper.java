package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import javax.sql.DataSource;

public class ConnectionHelper {

    private static DataSource dataSource;

    static {
        try (InputStream in = ConnectionHelper.class.getResourceAsStream("/config/db.properties")) {
            Properties props = new Properties();
            props.load(in);

            dataSource = DataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Không đọc được cấu hình DB: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void shutdown() {
        if (dataSource instanceof AutoCloseable) {
            try {
                ((AutoCloseable) dataSource).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

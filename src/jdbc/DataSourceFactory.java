package jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;

public class DataSourceFactory {

    public static DataSource createDataSource(Properties props) {
        HikariConfig config = new HikariConfig();
        
        config.setJdbcUrl(props.getProperty("db.url"));
        config.setUsername(props.getProperty("db.user"));
        config.setPassword(props.getProperty("db.password"));
        
        //Cấu hình connection pool từ props
        config.setMaximumPoolSize(Integer.parseInt(props.getProperty("db.pool.maxSize", "20")));
        config.setMinimumIdle(Integer.parseInt(props.getProperty("db.pool.minIdle", "5")));
        config.setIdleTimeout(Long.parseLong(props.getProperty("db.pool.idleTimeout", "300000")));
        config.setMaxLifetime(Long.parseLong(props.getProperty("db.pool.maxLifetime", "1800000")));
        config.setConnectionTimeout(Long.parseLong(props.getProperty("db.pool.connectionTimeout", "30000")));
        config.setLeakDetectionThreshold(Long.parseLong(props.getProperty("db.pool.leakDetectionThreshold", "60000")));
        
        // Cấu hình MySQL 
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");
        
        return new HikariDataSource(config);
    }
}

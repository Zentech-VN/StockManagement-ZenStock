package jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.time.Duration;
import java.util.Properties;
import javax.sql.DataSource;

public class DataSourceFactory {

    public static DataSource createDataSource(Properties props) {
        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl(props.getProperty("db.url"));
        cfg.setUsername(props.getProperty("db.user"));
        cfg.setPassword(props.getProperty("db.password"));

        cfg.setMaximumPoolSize(Integer.parseInt(props.getProperty("db.pool.size", "10")));
        cfg.setMinimumIdle(Integer.parseInt(props.getProperty("db.pool.idle.min", "3")));
        cfg.setIdleTimeout(Duration.ofMillis(
                Long.parseLong(props.getProperty("db.pool.idle.timeout", "30000"))).toMillis());

        // Tối ưu Statement cache
        cfg.addDataSourceProperty("cachePrepStmts", "true");
        cfg.addDataSourceProperty("prepStmtCacheSize", "250");
        cfg.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(cfg);
    }
}

package JDBC;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.util.Properties;
import javax.sql.DataSource;


public class DataSourceFactory {
    public static DataSource createDataSource(Properties props) {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL(props.getProperty("db.url"));
        ds.setUser(props.getProperty("db.user"));
        ds.setPassword(props.getProperty("db.password"));
        return ds;
    }
}

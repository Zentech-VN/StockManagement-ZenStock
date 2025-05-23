/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.util.Properties;
import javax.sql.DataSource;
/**
 *
 * @author Duc Pham Ngoc
 */
public class DataSourceFactory {
    public static DataSource createDataSource(Properties props) {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL(props.getProperty("db.url"));
        ds.setUser(props.getProperty("db.user"));
        ds.setPassword(props.getProperty("db.password"));
        return ds;
    }
}

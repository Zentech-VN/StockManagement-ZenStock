package dao;

import entity.OS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public interface OSDAO {

    default List<OS> getAllOS() {
        List<OS> list = new ArrayList<>();
        String sql = "SELECT mahedieuhanh, tenhedieuhanh FROM hedieuhanh";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("mahedieuhanh");
                String name = rs.getString("tenhedieuhanh");
                list.add(new OS(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}

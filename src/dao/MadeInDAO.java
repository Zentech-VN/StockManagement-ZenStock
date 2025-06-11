package dao;

import entity.MadeIn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public interface MadeInDAO {

    default List<MadeIn> getAllMadeIn() {
        List<MadeIn> list = new ArrayList<>();
        String sql = "SELECT maxuatxu, tenxuatxu FROM xuatxu";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new MadeIn(rs.getInt("maxuatxu"), rs.getString("tenxuatxu")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}

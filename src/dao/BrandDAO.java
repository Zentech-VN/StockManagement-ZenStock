package dao;

import entity.Brand;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public interface BrandDAO {

    default List<Brand> getAllBrands() {
        List<Brand> list = new ArrayList<>();
        String sql = "SELECT mathuonghieu, tenthuonghieu FROM thuonghieu";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("mathuonghieu");
                String name = rs.getString("tenthuonghieu");
                list.add(new Brand(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}

package dao;

import entity.ProductWarehouse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public interface ProductWarehouseDAO {
    default List<ProductWarehouse> getAllProductWarehouse() {
        List<ProductWarehouse> list = new ArrayList<>();
        String sql = "SELECT makhuvuc, masanpham, soluong FROM khuvuc_sanpham;";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int makhuvuc = rs.getInt("makhuvuc");
                int masanpham = rs.getInt("masanpham");
                int soluong = rs.getInt("soluong");
                list.add(new ProductWarehouse(makhuvuc, masanpham, soluong));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

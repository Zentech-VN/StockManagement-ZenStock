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
        String sql = "SELECT makhuvuc, masanpham FROM khuvuckho_sanpham;";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int makhuvuc = rs.getInt("makhuvuc");
                int masanpham = rs.getInt("masanpham");
                list.add(new ProductWarehouse(makhuvuc, masanpham));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

package dao;

import entity.ProductView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public interface ProductDAO {

    default List<ProductView> getAllProduct() {
        List<ProductView> list = new ArrayList<>();
        String sql = "SELECT * FROM vw_sanpham;";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ProductView p = new ProductView();
                p.setMaSanPham(rs.getInt("masp"));
                p.setTenSanPham(rs.getString("tensp"));
                p.setTenXuatXu(rs.getString("tenxuatxu"));
                p.setTenThuongHieu(rs.getString("tenthuonghieu"));
                p.setTenKhuVuc(rs.getString("tenkhuvuc"));
                p.setSoLuongTon(rs.getInt("soluongton"));
                p.setTrangThai(rs.getInt("trangthai"));

                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    default int getProductCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM sanpham";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}

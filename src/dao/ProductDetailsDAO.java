package dao;

import entity.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public class ProductDetailsDAO {

public List<Product> getAllProductWithImei() {
    List<Product> list = new ArrayList<>();
    String sql = "SELECT cts.maimei, sp.masanpham, sp.tensp, sp.gia, sp.trangthai " +
                 "FROM ctsanpham cts " +
                 "JOIN sanpham sp ON cts.masanpham = sp.masanpham";

    try (Connection conn = ConnectionHelper.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Product p = new Product();
            p.setMaimei(rs.getInt("maimei"));
            p.setMaSanPham(rs.getInt("masanpham"));
            p.setTenSanPham(rs.getString("tensp"));
            p.setGia(rs.getBigDecimal("gia"));
            p.setTrangThai(rs.getString("trangthai"));
            list.add(p);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

public boolean insertProduct(Product product) {
    String sql = "INSERT INTO sanpham (tensp, hinhanh, chipxuly, cameratruoc, camerasau, gia, dungluongpin, kichthuocmanhinh, thoigianbaohanh, xuatxu, hedieuhanh, trangthai, thuonghieu) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = ConnectionHelper.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, product.getTenSanPham());
        stmt.setString(2, product.getHinhAnh());
        stmt.setString(3, product.getChipXuLy());
        stmt.setString(4, product.getCameraTruoc());
        stmt.setString(5, product.getCameraSau());
        stmt.setBigDecimal(6, product.getGia());
        stmt.setString(7, product.getDungLuongPin());
        stmt.setString(8, product.getKichThuocManHinh());
        stmt.setString(9, product.getThoiGianBaoHanh());
        stmt.setInt(10, product.getMaXuatXu());
        stmt.setInt(11, product.getMaHeDieuHanh());
        stmt.setString(12, product.getTrangThai());
        stmt.setInt(13, product.getMaThuongHieu()); 

        return stmt.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
}

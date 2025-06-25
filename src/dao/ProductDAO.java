package dao;

import entity.Product;
import entity.Warehouse;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public interface ProductDAO {

    default List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT "
                + "sp.masanpham, "
                + "sp.tensp, "
                + "sp.hinhanh, "
                + "sp.chipxuly, "
                + "sp.cameratruoc, "
                + "sp.camerasau, "
                + "sp.thongso, "
                + "sp.gia, "
                + "sp.trangthai, "
                + "sp.dungluongpin, "
                + "sp.kichthuocmanhinh, "
                + "sp.thoigianbaohanh, "
                + "xx.tenxuatxu, "
                + "hdh.tenhedieuhanh, "
                + "th.tenthuonghieu "
                + "FROM sanpham sp "
                + "JOIN xuatxu xx ON sp.maxuatxu = xx.maxuatxu "
                + "JOIN hedieuhanh hdh ON sp.mahedieuhanh = hdh.mahedieuhanh "
                + "JOIN thuonghieu th ON sp.mathuonghieu = th.mathuonghieu";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product p = new Product();
                p.setMaSanPham(rs.getInt("masanpham"));
                p.setTenSanPham(rs.getString("tensp"));
                p.setHinhAnh(rs.getString("hinhanh"));
                p.setChipXuLy(rs.getString("chipxuly"));
                p.setCameraTruoc(rs.getString("cameratruoc"));
                p.setCameraSau(rs.getString("camerasau"));
                p.setThongSo(rs.getInt("thongso"));
                p.setGia(rs.getBigDecimal("gia"));
                p.setTrangThai(rs.getInt("trangthai"));
                p.setDungLuongPin(rs.getString("dungluongpin"));
                p.setKichThuocManHinh(rs.getString("kichthuocmanhinh"));
                p.setThoiGianBaoHanh(rs.getString("thoigianbaohanh"));
                p.setTenXuatXu(rs.getString("tenxuatxu"));
                p.setTenHeDieuHanh(rs.getString("tenhedieuhanh"));
                p.setTenThuongHieu(rs.getString("tenthuonghieu"));
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return list;
    }

    default List<Product> getBasicProduct() {
        List<Product> list = new ArrayList<>();

        String sql = "SELECT "
                + "sp.masanpham, "
                + "sp.tensp, "
                + "sp.gia, "
                + "sp.trangthai, "
                + "th.tenthuonghieu, "
                + "hdh.tenhedieuhanh, "
                + "xx.tenxuatxu "
                + "FROM sanpham sp "
                + "JOIN thuonghieu th ON sp.mathuonghieu = th.mathuonghieu "
                + "JOIN hedieuhanh hdh ON sp.mahedieuhanh = hdh.mahedieuhanh "
                + "JOIN xuatxu xx ON sp.maxuatxu = xx.maxuatxu";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product p = new Product();
                p.setMaSanPham(rs.getInt("masanpham"));
                p.setTenSanPham(rs.getString("tensp"));
                p.setGia(rs.getBigDecimal("gia"));
                p.setTrangThai(rs.getInt("trangthai"));
                p.setTenThuongHieu(rs.getString("tenthuonghieu"));
                p.setTenHeDieuHanh(rs.getString("tenhedieuhanh"));
                p.setTenXuatXu(rs.getString("tenxuatxu"));

                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    default boolean addProduct(String tenSanPham, BigDecimal gia, int thongSo, String hinhAnh, String cameraTruoc, String cameraSau, String chip, String pin, String manHinh, String baoHanh, int maThuongHieu, int maHeDieuHanh, int maXuatXu, int trangThai, int maKhuVuc, int soLuong
    ) {
        String sql = "{CALL sp_sanpham_add(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (
                Connection conn = ConnectionHelper.getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, tenSanPham);
            cs.setString(2, hinhAnh);
            cs.setInt(3, maXuatXu);
            cs.setString(4, chip);
            cs.setInt(5, maHeDieuHanh);
            cs.setString(6, cameraTruoc);
            cs.setString(7, cameraSau);
            cs.setInt(8, thongSo);
            cs.setBigDecimal(9, gia);
            cs.setInt(10, trangThai);
            cs.setInt(11, maThuongHieu);
            cs.setString(12, pin);
            cs.setString(13, manHinh);
            cs.setString(14, baoHanh);
            cs.setInt(15, maKhuVuc);
            cs.setInt(16, soLuong);

            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    default boolean updateProduct(int maSanPham, String tenSanPham, BigDecimal gia, int thongSo, String hinhAnh, String cameraTruoc, String cameraSau, String chip, String pin, String manHinh, String baoHanh, int maThuongHieu, int maHeDieuHanh, int maXuatXu, int trangThai) {
        String sql = "{CALL sp_sanpham_update(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (
                Connection conn = ConnectionHelper.getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, maSanPham);
            cs.setString(2, tenSanPham);
            cs.setString(3, hinhAnh);
            cs.setInt(4, maXuatXu);
            cs.setString(5, chip);
            cs.setInt(6, maHeDieuHanh);
            cs.setString(7, cameraTruoc);
            cs.setString(8, cameraSau);
            cs.setInt(9, thongSo);
            cs.setBigDecimal(10, gia);
            cs.setInt(11, trangThai);
            cs.setInt(12, maThuongHieu);
            cs.setString(13, pin);
            cs.setString(14, manHinh);
            cs.setString(15, baoHanh);

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    default Product getProductById(int id) {
        String sql = "SELECT * FROM sanpham sp "
                + "JOIN xuatxu xx ON sp.maxuatxu = xx.maxuatxu "
                + "JOIN hedieuhanh hdh ON sp.mahedieuhanh = hdh.mahedieuhanh "
                + "JOIN thuonghieu th ON sp.mathuonghieu = th.mathuonghieu "
                + "WHERE sp.masanpham = ?";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Product p = new Product();
                p.setMaSanPham(rs.getInt("masanpham"));
                p.setTenSanPham(rs.getString("tensp"));
                p.setHinhAnh(rs.getString("hinhanh"));
                p.setChipXuLy(rs.getString("chipxuly"));
                p.setCameraTruoc(rs.getString("cameratruoc"));
                p.setCameraSau(rs.getString("camerasau"));
                p.setThongSo(rs.getInt("thongso"));
                p.setGia(rs.getBigDecimal("gia"));
                p.setTrangThai(rs.getInt("trangthai"));
                p.setDungLuongPin(rs.getString("dungluongpin"));
                p.setKichThuocManHinh(rs.getString("kichthuocmanhinh"));
                p.setThoiGianBaoHanh(rs.getString("thoigianbaohanh"));

                p.setMaXuatXu(rs.getInt("maxuatxu"));
                p.setTenXuatXu(rs.getString("tenxuatxu"));
                p.setMaHeDieuHanh(rs.getInt("mahedieuhanh"));
                p.setTenHeDieuHanh(rs.getString("tenhedieuhanh"));
                p.setMaThuongHieu(rs.getInt("mathuonghieu"));
                p.setTenThuongHieu(rs.getString("tenthuonghieu"));

                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    default boolean deleteProduct(int maSanPham) {
        String sql = "{CALL sp_sanpham_delete(?)}";

        try (
                Connection conn = ConnectionHelper.getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, maSanPham);
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    default List<Product> searchProductsProc(String keyword) {
        List<Product> list = new ArrayList<>();
        String sql = "{CALL sp_search_products(?)}";

        try (Connection cn = ConnectionHelper.getConnection(); CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, "%" + keyword + "%");

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Product p = new Product();
                    p.setMaSanPham(rs.getInt("masanpham"));
                    p.setTenSanPham(rs.getString("tensp"));
                    p.setGia(rs.getBigDecimal("gia"));
                    p.setTenThuongHieu(rs.getString("tenthuonghieu"));
                    p.setTenXuatXu(rs.getString("tenxuatxu"));
                    p.setTenHeDieuHanh(rs.getString("tenhedieuhanh"));
                    p.setTrangThai(rs.getInt("trangthai"));
                    list.add(p);
                }
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

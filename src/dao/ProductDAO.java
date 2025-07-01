package dao;

import entity.Product;
import entity.Warehouse;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                + "sp.gia, "
                + "sp.trangthai, "
                + "sp.dungluongpin, "
                + "sp.kichthuocmanhinh, "
                + "sp.thoigianbaohanh, "
                + "xx.tenxuatxu, "
                + "hdh.tenhedieuhanh, "
                + "th.tenthuonghieu "
                + "FROM sanpham sp "
                + "JOIN xuatxu xx ON sp.xuatxu = xx.maxuatxu "
                + "JOIN hedieuhanh hdh ON sp.hedieuhanh = hdh.mahedieuhanh "
                + "JOIN thuonghieu th ON sp.thuonghieu = th.mathuonghieu";

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
                p.setGia(rs.getBigDecimal("gia"));
                p.setTrangThai(rs.getString("trangthai"));
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
                + "JOIN thuonghieu th ON sp.thuonghieu = th.mathuonghieu "
                + "JOIN hedieuhanh hdh ON sp.hedieuhanh = hdh.mahedieuhanh "
                + "JOIN xuatxu xx ON sp.xuatxu = xx.maxuatxu";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product p = new Product();
                p.setMaSanPham(rs.getInt("masanpham"));
                p.setTenSanPham(rs.getString("tensp"));
                p.setGia(rs.getBigDecimal("gia"));
                p.setTrangThai(rs.getString("trangthai"));
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

    default boolean addProduct(String tenSanPham, BigDecimal gia, String hinhAnh, String cameraTruoc, String cameraSau,
            String chip, String pin, String manHinh, int baoHanh, int maThuongHieu,
            int maHeDieuHanh, int maXuatXu, String trangThai, int maKhuVuc, int soLuong) {

        String sqlInsertProduct = "INSERT INTO sanpham "
                + "(tensp, hinhanh, xuatxu, chipxuly, hedieuhanh, cameratruoc, camerasau, thoigianbaohanh, gia, trangthai, thuonghieu, dungluongpin, kichthuocmanhinh) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlInsertKho = "INSERT INTO khuvuckho_sanpham (makhuvuc, masanpham, soluong) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionHelper.getConnection()) {

            // Bắt đầu transaction
            conn.setAutoCommit(false);

            // Bước 1: Insert sản phẩm
            PreparedStatement ps = conn.prepareStatement(sqlInsertProduct, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tenSanPham);
            ps.setString(2, hinhAnh);
            ps.setInt(3, maXuatXu);
            ps.setString(4, chip);
            ps.setInt(5, maHeDieuHanh);
            ps.setString(6, cameraTruoc);
            ps.setString(7, cameraSau);
            ps.setInt(8, baoHanh);
            ps.setBigDecimal(9, gia);
            ps.setString(10, trangThai);
            ps.setInt(11, maThuongHieu);
            ps.setString(12, pin);
            ps.setString(13, manHinh);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                // Lấy ra masanpham vừa thêm
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int masanpham = rs.getInt(1);

                    // Bước 2: Inserta vào bảng khuvuckho_sanpham
                    PreparedStatement psKho = conn.prepareStatement(sqlInsertKho);
                    psKho.setInt(1, maKhuVuc);
                    psKho.setInt(2, masanpham);
                    psKho.setInt(3, soLuong);

                    int rowsKho = psKho.executeUpdate();

                    if (rowsKho > 0) {
                        conn.commit();
                        return true;
                    }
                }
            }

            conn.rollback();
            return false;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    default boolean updateProduct(int maSanPham, String tenSanPham, BigDecimal gia, String hinhAnh, String cameraTruoc, String cameraSau,
            String chip, String pin, String manHinh, int baoHanh, int maThuongHieu,
            int maHeDieuHanh, int maXuatXu, String trangThai) {

        String sql = "UPDATE sanpham SET tensp=?, hinhanh=?, xuatxu=?, chipxuly=?, hedieuhanh=?, "
                + "cameratruoc=?, camerasau=?, thoigianbaohanh=?, gia=?, trangthai=?, thuonghieu=?, dungluongpin=?, kichthuocmanhinh=? "
                + "WHERE masanpham=?";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenSanPham);
            ps.setString(2, hinhAnh);
            ps.setInt(3, maXuatXu);
            ps.setString(4, chip);
            ps.setInt(5, maHeDieuHanh);
            ps.setString(6, cameraTruoc);
            ps.setString(7, cameraSau);
            ps.setInt(8, baoHanh);
            ps.setBigDecimal(9, gia);
            ps.setString(10, trangThai);
            ps.setInt(11, maThuongHieu);
            ps.setString(12, pin);
            ps.setString(13, manHinh);
            ps.setInt(14, maSanPham);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    default Product getProductById(int id) {
        String sql = "SELECT * FROM sanpham sp "
                + "JOIN xuatxu xx ON sp.xuatxu = xx.maxuatxu "
                + "JOIN hedieuhanh hdh ON sp.hedieuhanh = hdh.mahedieuhanh "
                + "JOIN thuonghieu th ON sp.thuonghieu = th.mathuonghieu "
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
                p.setGia(rs.getBigDecimal("gia"));
                p.setTrangThai(rs.getString("trangthai"));
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
        String sqlDeleteFromKho = "DELETE FROM khuvuckho_sanpham WHERE masanpham = ?";
        String sqlDeleteProduct = "DELETE FROM sanpham WHERE masanpham = ?";

        try (Connection conn = ConnectionHelper.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu transaction

            try (
                    PreparedStatement psKho = conn.prepareStatement(sqlDeleteFromKho); PreparedStatement psSanPham = conn.prepareStatement(sqlDeleteProduct)) {
                psKho.setInt(1, maSanPham);
                psKho.executeUpdate();

                psSanPham.setInt(1, maSanPham);
                int rows = psSanPham.executeUpdate();

                conn.commit(); // Nếu cả hai câu lệnh đều thành công
                return rows > 0;
            } catch (SQLException e) {
                conn.rollback(); // Nếu có lỗi, rollback
                e.printStackTrace();
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    default List<Product> searchProductsProc(String keyword) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT sp.masanpham, sp.tensp, sp.gia, sp.trangthai, th.tenthuonghieu, hdh.tenhedieuhanh, xx.tenxuatxu "
                + "FROM sanpham sp "
                + "JOIN thuonghieu th ON sp.thuonghieu = th.mathuonghieu "
                + "JOIN hedieuhanh hdh ON sp.hedieuhanh = hdh.mahedieuhanh "
                + "JOIN xuatxu xx ON sp.xuatxu = xx.maxuatxu "
                + "WHERE sp.tensp LIKE ?";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product p = new Product();
                    p.setMaSanPham(rs.getInt("masanpham"));
                    p.setTenSanPham(rs.getString("tensp"));
                    p.setGia(rs.getBigDecimal("gia"));
                    p.setTrangThai(rs.getString("trangthai"));
                    p.setTenThuongHieu(rs.getString("tenthuonghieu"));
                    p.setTenHeDieuHanh(rs.getString("tenhedieuhanh"));
                    p.setTenXuatXu(rs.getString("tenxuatxu"));
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

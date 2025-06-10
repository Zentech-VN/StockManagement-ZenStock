package dao;

import entity.Product;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;
import raven.toast.Notifications;

public interface ProductDAO {

    default List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    sp.masanpham,\n"
                + "    sp.tensp,\n"
                + "    sp.hinhanh,\n"
                + "    xx.tenxuatxu AS xuatxu,         \n"
                + "    sp.chipxuly,\n"
                + "    sp.dungluongpin,\n"
                + "    sp.kichthuocmanhinh,\n"
                + "    hdh.tenhedieuchanh AS hedieuhanh, \n"
                + "    sp.camerasau,\n"
                + "    sp.cameratruoc,\n"
                + "    sp.thoigianbaohanh,\n"
                + "    sp.thongso,\n"
                + "    sp.gia,\n"
                + "    th.tenthuonghieu AS thuonghieu,   \n"
                + "    GROUP_CONCAT(kv.tenkhuvuc SEPARATOR ', ') AS khuvuckho,     \n"
                + "    sp.trangthai\n"
                + "FROM\n"
                + "    sanpham sp\n"
                + "LEFT JOIN\n"
                + "    xuatxu xx ON sp.maxuatxu = xx.maxuatxu\n"
                + "LEFT JOIN\n"
                + "    hedieuchanh hdh ON sp.mahedieuchanh = hdh.mahedieuchanh\n"
                + "LEFT JOIN\n"
                + "    thuonghieu th ON sp.mathuonghieu = th.mathuonghieu\n"
                + "LEFT JOIN\n"
                + "    khuvuc_sanpham kvsp ON sp.masanpham = kvsp.masanpham\n"
                + "LEFT JOIN\n"
                + "    khuvuc kv ON kvsp.makhuvuc = kv.makhuvuc\n"
                + "GROUP BY sp.masanpham, sp.tensp, sp.hinhanh, xx.tenxuatxu, sp.chipxuly, sp.dungluongpin, sp.kichthuocmanhinh, hdh.tenhedieuchanh, sp.camerasau, sp.cameratruoc, sp.thoigianbaohanh, sp.thongso, sp.gia, th.tenthuonghieu, sp.trangthai;";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product p = new Product();
                p.setMaSanPham(rs.getInt("masanpham"));
                p.setTenSanPham(rs.getString("tensp"));
                p.setHinhAnh(rs.getString("hinhanh"));
                p.setTenXuatXu(rs.getString("xuatxu"));
                p.setDungLuongPin(rs.getString("dungluongpin"));
                p.setChipXuLy(rs.getString("chipxuly"));
                p.setKichThuocManHinh(rs.getString("kichthuocmanhinh"));
                p.setTenHeDieuHanh(rs.getString("hedieuhanh"));
                p.setCameraSau(rs.getString("camerasau"));
                p.setCameraTruoc(rs.getString("cameratruoc"));
                p.setThoiGianBaoHanh(rs.getString("thoigianbaohanh"));
                p.setThongSo(rs.getInt("thongso"));
                p.setGia(rs.getBigDecimal("gia"));
                p.setTenThuongHieu(rs.getString("thuonghieu"));
                p.setTenKhuVuc(rs.getString("khuvuckho"));
                p.setTrangThai(rs.getInt("trangthai"));

                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    default boolean addProduct(String tenSanPham, String hinhAnh, String chipXuLy, String dungLuongPin, String kichThuocManHinh, String cameraSau, String cameraTruoc, String thoiGianBaoHanh, int thongSo, java.math.BigDecimal gia, int trangThai, int maxuatxu, int mahedieuchanh, int mathuonghieu) {
        String sql = "INSERT INTO sanpham (tensp, hinhanh, maxuatxu, chipxuly, mahedieuchanh, cameratruoc, camerasau, dungluongpin, kichthuocmanhinh, thoigianbaohanh, thongso, gia, trangthai, mathuonghieu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenSanPham);
            ps.setString(2, hinhAnh);
            ps.setInt(3, maxuatxu);
            ps.setString(4, chipXuLy);
            ps.setInt(5, mahedieuchanh);
            ps.setString(6, cameraTruoc);
            ps.setString(7, cameraSau);
            ps.setString(8, dungLuongPin);
            ps.setString(9, kichThuocManHinh);
            ps.setString(10, thoiGianBaoHanh);
            ps.setInt(11, thongSo);
            ps.setBigDecimal(12, gia);
            ps.setInt(13, trangThai);
            ps.setInt(14, mathuonghieu);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi khi thêm sản phẩm");
            ex.printStackTrace();
            return false;
        }
    }

    default boolean updateProduct(int maSanPham, String tenSanPham, String hinhAnh, String chipXuLy, String dungLuongPin, String kichThuocManHinh, String cameraSau, String cameraTruoc, String thoiGianBaoHanh, int thongSo, java.math.BigDecimal gia, int trangThai, int maxuatxu, int mahedieuchanh, int mathuonghieu) {
        String sql = "UPDATE sanpham SET tensp = ?, hinhanh = ?, maxuatxu = ?, chipxuly = ?, mahedieuchanh = ?, "
                + "cameratruoc = ?, camerasau = ?, dungluongpin = ?, kichthuocmanhinh = ?, thoigianbaohanh = ?, thongso = ?, gia = ?, trangthai = ?, mathuonghieu = ? WHERE masanpham = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenSanPham);
            ps.setString(2, hinhAnh);
            ps.setInt(3, maxuatxu);
            ps.setString(4, chipXuLy);
            ps.setInt(5, mahedieuchanh);
            ps.setString(6, cameraTruoc);
            ps.setString(7, cameraSau);
            ps.setString(8, dungLuongPin);
            ps.setString(9, kichThuocManHinh);
            ps.setString(10, thoiGianBaoHanh);
            ps.setInt(11, thongSo);
            ps.setBigDecimal(12, gia);
            ps.setInt(13, trangThai);
            ps.setInt(14, mathuonghieu);
            ps.setInt(15, maSanPham);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi khi sửa sản phẩm");
            ex.printStackTrace();
            return false;
        }
    }

    default boolean deleteProduct(int maSanPham) {
        String sql = "DELETE FROM sanpham WHERE masanpham = ?";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSanPham);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi khi xoá sản phẩm");
            ex.printStackTrace();
            return false;
        }
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

    // Add method to assign product to warehouse
    default boolean assignProductToWarehouse(int masanpham, int makhuvuc) {
        String sql = "INSERT INTO khuvuc_sanpham (makhuvuc, masanpham) VALUES (?, ?)";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, makhuvuc);
            ps.setInt(2, masanpham);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Remove product from warehouse
    default boolean removeProductFromWarehouse(int masanpham, int makhuvuc) {
        String sql = "DELETE FROM khuvuc_sanpham WHERE makhuvuc = ? AND masanpham = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, makhuvuc);
            ps.setInt(2, masanpham);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

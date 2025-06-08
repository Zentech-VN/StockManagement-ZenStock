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
                + "    sp.masp,\n"
                + "    sp.tensp,\n"
                + "    sp.hinhanh,\n"
                + "    xx.tenxuatxu AS xuatxu,         \n"
                + "    sp.chipxuly,\n"
                + "    sp.dungluongpin,\n"
                + "    sp.kichthuocman,\n"
                + "    hdh.tenhedieuhanh AS hedieuhanh, \n"
                + "    pbhdhd.tenphienbanhdh as phienbanhdh,\n"
                + "    sp.camerasau,\n"
                + "    sp.cameratruoc,\n"
                + "    sp.thoigianbaohanh,\n"
                + "    th.tenthuonghieu AS thuonghieu,   \n"
                + "    kk.tenkhuvuc AS khuvuckho,     \n"
                + "    sp.soluongton,\n"
                + "    sp.trangthai\n"
                + "FROM\n"
                + "    sanpham sp\n"
                + "LEFT JOIN\n"
                + "    xuatxu xx ON sp.xuatxu = xx.maxuatxu\n"
                + "LEFT JOIN\n"
                + "    hedieuhanh hdh ON sp.hedieuhanh = hdh.mahedieuhanh\n"
                + "LEFT JOIN\n"
                + "    thuonghieu th ON sp.thuonghieu = th.mathuonghieu\n"
                + "LEFT JOIN\n"
                + "    khuvuckho kk ON sp.khuvuckho = kk.makhuVuc\n"
                + "LEFT JOIN\n"
                + "    phienbanhedieuhanh pbhdhd ON sp.phienbanhdh = pbhdhd.maphienbanhdh;";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product p = new Product();
                p.setMaSanPham(rs.getInt("masp"));
                p.setTenSanPham(rs.getString("tensp"));
                // p.setHinhAnh(rs.getString("hinhanh"));
                p.setTenXuatXu(rs.getString("xuatxu"));
                p.setDungLuongPin(rs.getInt("dungluongpin"));
                p.setChipXuLy(rs.getString("chipxuly"));
                p.setKichThuocManHinh(rs.getDouble("kichthuocman"));
                p.setTenHeDieuHanh(rs.getString("hedieuhanh"));
                p.setPhienBanHeDieuHanh(rs.getString("phienbanhdh"));
                p.setCameraSau(rs.getString("camerasau"));
                p.setCameraTruoc(rs.getString("cameratruoc"));
                p.setThoiGianBaoHanh(rs.getInt("thoigianbaohanh"));
                p.setTenThuongHieu(rs.getString("thuonghieu"));
                p.setTenKhuVuc(rs.getString("khuvuckho"));
                p.setSoLuongTon(rs.getInt("soluongton"));
                p.setTrangThai(rs.getInt("trangthai"));

                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    default boolean addProduct(int maSanPham, String tenSanPham, String hinhAnh, String chipXuLy, int dungLuongPin, double kichThuocManHinh, String cameraSau, String cameraTruoc, int thoiGianBaoHanh, int soLuongTon, int phienBanHeDieuHanh, int trangThai, String tenXuatXu, String tenHeDieuHanh, String tenThuongHieu, String tenKhuVuc) {
        String sql = "INSERT INTO sanpham (masp, tensp, hinhanh, xuatxu, chipxuly, dungluongpin, kichthuocman, hedieuhanh, phienbanhdh, camerasau, cameratruoc, thoigianbaohanh, thuonghieu, khuvuckho, soluongton, trangthai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maSanPham);
            ps.setString(2, tenSanPham);
            ps.setString(3, hinhAnh);
            ps.setString(4, tenXuatXu);
            ps.setString(5, chipXuLy);
            ps.setInt(6, dungLuongPin);
            ps.setDouble(7, kichThuocManHinh);
            ps.setString(8, tenHeDieuHanh);
            ps.setInt(9, phienBanHeDieuHanh);
            ps.setString(10, cameraSau);
            ps.setString(11, cameraTruoc);
            ps.setInt(12, thoiGianBaoHanh);
            ps.setString(13, tenThuongHieu);
            ps.setString(14, tenKhuVuc);
            ps.setInt(15, soLuongTon);
            ps.setInt(16, trangThai);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi khi thêm sản phẩm");
            ex.printStackTrace();
            return false;
        }
    }

    default boolean updateProduct(int maSanPham, String tenSanPham, String hinhAnh, String chipXuLy, int dungLuongPin, double kichThuocManHinh, String cameraSau, String cameraTruoc, int thoiGianBaoHanh, int soLuongTon, String phienBanHeDieuHanh, int trangThai, String tenXuatXu, String tenHeDieuHanh, String tenThuongHieu, String tenKhuVuc) {
        String sql = "UPDATE sanpham SET tensp = ?, hinhanh = ?, xuatxu = ?, chipxuly = ?, dungluongpin = ?, "
                + "kichthuocman = ?, hedieuhanh = ?, phienbanhdh = ?, camerasau = ?, cameratruoc = ?, thoigianbaohanh = ?, "
                + "thuonghieu = ?, khuvuckho = ?, soluongton = ?, trangthai = ? WHERE masp = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenSanPham);
            ps.setString(2, hinhAnh);
            ps.setString(3, tenXuatXu);
            ps.setString(4, chipXuLy);
            ps.setInt(5, dungLuongPin);
            ps.setDouble(6, kichThuocManHinh);
            ps.setString(7, tenHeDieuHanh);
            ps.setString(8, phienBanHeDieuHanh);
            ps.setString(9, cameraSau);
            ps.setString(10, cameraTruoc);
            ps.setInt(11, thoiGianBaoHanh);
            ps.setString(12, tenThuongHieu);
            ps.setString(13, tenKhuVuc);
            ps.setInt(14, soLuongTon);
            ps.setInt(15, trangThai);
            ps.setInt(16, maSanPham);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi khi sửa sản phẩm");
            ex.printStackTrace();
            return false;
        }
    }

    default boolean deleteProduct(int maSanPham) {
        String sql = "DELETE FROM sanpham WHERE masp = ?";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSanPham);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi khi xoá nhân viên");
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
}

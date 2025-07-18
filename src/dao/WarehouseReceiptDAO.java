package dao;

import entity.PhieuNhap;
import entity.ProductArea;

import entity.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public class WarehouseReceiptDAO {

    public List<PhieuNhap> getAllentries() {
        List<PhieuNhap> listp = new ArrayList<>();
        String sql = "select * from phieunhap";
        try (Connection conn = ConnectionHelper.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                PhieuNhap p = new PhieuNhap();
                p.setMaphieunhap(rs.getInt("maphieunhap"));
                p.getS().setMaNhaCungCap(rs.getInt("manhacungcap"));
                p.getE().setManv(rs.getInt("nguoitao"));
                p.setNgaytao(rs.getDate("thoigian"));
                p.setTrangthai(rs.getString("trangthai"));
                listp.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listp;
    }

    public List<Supplier> getAllNhaCungCap() {
        List<Supplier> list = new ArrayList<>();
        String sql = "SELECT * FROM nhacungcap";
        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Supplier s = new Supplier();
                s.setMaNhaCungCap(rs.getInt("manhacungcap"));
                s.setTenNhaCungCap(rs.getString("tennhacungcap"));
                s.setDiaChi(rs.getString("diachi"));
                s.setEmail(rs.getString("email"));
                s.setSdt(rs.getString("sdt"));

                // Chuyển enum sang int: MoKhoa = 0, Khoa = 1
                String trangThaiStr = rs.getString("trangthai");
                int trangThai = "MoKhoa".equalsIgnoreCase(trangThaiStr) ? 0 : 1;
                s.setTrangThai(trangThai);

                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ProductArea> GetProducArea() {
        List<ProductArea> list = new ArrayList<>();
        String sql = "select sanpham.masanpham, sanpham.tensp, khuvuckho.tenkhuvuc, sanpham.gia, soluong\n"
                + "from khuvuckho_sanpham join sanpham on khuvuckho_sanpham.masanpham = sanpham.masanpham\n"
                + "join khuvuckho on khuvuckho_sanpham.makhuvuc = khuvuckho.makhuvuc";
        try (Connection conn = ConnectionHelper.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ProductArea pa = new ProductArea();
                pa.getP().setMaSanPham(rs.getInt("sanpham.masanpham"));
                pa.getP().setTenSanPham(rs.getString("sanpham.tensp"));

                pa.getP().setGia(rs.getBigDecimal("sanpham.gia"));
                pa.getW().setTenKhuVuc(rs.getString("khuvuckho.tenkhuvuc"));
                pa.setSoluong(rs.getInt("soluong"));
                list.add(pa);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
}

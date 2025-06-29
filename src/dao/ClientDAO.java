package dao;

import entity.Cilent;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import jdbc.ConnectionHelper;

public class ClientDAO {

    public List<Cilent> getAllCilent() {
        List<Cilent> listc = new ArrayList<>();
        String sql = "select * from khachhang";
        try (Connection conn = ConnectionHelper.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cilent cl = new Cilent();
                cl.setMaKhacHang(rs.getInt("makh"));
                cl.setTenKhacHang(rs.getString("tenkhachhang"));
                cl.setDiaChi(rs.getString("diachi"));
                cl.setSoDienThoai(rs.getString("sdt"));
                cl.setTrangThai(rs.getInt("trangthai"));
                cl.setNgayThamGia(rs.getDate("ngaythamgia"));
                listc.add(cl);
            }
            return listc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addkhachhang(Cilent cl) {
        int rs = 0;
        String sql = "insert into khachhang(tenkhachhang,diachi,email,sdt, trangthai) values(?,?,?,?,?)";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement cs = conn.prepareStatement(sql)) {
            cs.setString(1, cl.getTenKhacHang());
            cs.setString(2, cl.getDiaChi());
            cs.setString(3, cl.getEmail());
            cs.setString(4, cl.getSoDienThoai());
            cs.setInt(5, 1);
            rs = cs.executeUpdate();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int delete(int id) {
        String sql = "delete from khachhang where makh = ?";
        int rs = 0;
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            rs = pst.executeUpdate();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Cilent getAllMouse(int makh) {
        Cilent cl = null;
        String sql = "select * from khachhang where makh = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
            pst.setInt(1, makh);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cl = new Cilent();
                cl.setMaKhacHang(rs.getInt("makh"));
                cl.setTenKhacHang(rs.getString("tenkhachhang"));
                cl.setDiaChi(rs.getString("diachi"));
                cl.setSoDienThoai(rs.getString("sdt"));
                cl.setEmail(rs.getString("email"));
                cl.setTrangThai(rs.getInt("trangthai"));
                cl.setNgayThamGia(rs.getDate("ngaythamgia"));
            }
            return cl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int Update(Cilent cl) {
        int rs = 0;
        String sql = "update khachhang set tenkhachhang = ?, diachi = ?, email = ?, sdt = ? where makh = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, cl.getTenKhacHang());
            pst.setString(2, cl.getDiaChi());
            pst.setString(3, cl.getEmail());
            pst.setString(4, cl.getSoDienThoai());
            pst.setInt(5, cl.getMaKhacHang());
            rs = pst.executeUpdate();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getClientCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM khachhang";

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

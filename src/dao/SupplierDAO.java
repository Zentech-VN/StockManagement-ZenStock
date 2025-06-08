package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;
import java.sql.SQLException;
import entity.Supplier;

public interface SupplierDAO {

    default List<Supplier> getAll() {
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
                int trangThai = rs.getInt("trangthai"); // lấy số 0 hoặc 1
                s.setTrangThai(rs.getInt("trangThai"));
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    default boolean updateTrangThai(int maNCC, int trangThai) {
        String sql = "UPDATE NhaCungCap SET TrangThai = ? WHERE MaNCC = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, trangThai);  // 0 hoặc 1
            ps.setInt(2, maNCC);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    default boolean insert(Supplier s) {
        String sql = "INSERT INTO nhacungcap (tennhacungcap, diachi, email, sdt, trangthai) VALUES (?, ?, ?, ?, ?)";
        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getTenNhaCungCap());
            ps.setString(2, s.getDiaChi());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getSdt());
            ps.setInt(5, s.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    default boolean update(Supplier s) {
        String sql = "UPDATE nhacungcap SET tennhacungcap=?, diachi=?, email=?, sdt=?, trangthai=? WHERE manhacungcap=?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getTenNhaCungCap());
            ps.setString(2, s.getDiaChi());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getSdt());
            ps.setInt(5, s.getTrangThai());
            ps.setInt(6, s.getMaNhaCungCap());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    default boolean delete(int maNhaCungCap) {
        String sql = "DELETE FROM nhacungcap WHERE manhacungcap=?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maNhaCungCap);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    default int getSupplierCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM nhacungcap";

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

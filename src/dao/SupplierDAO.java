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
        String sql = "SELECT * FROM nhacungcap WHERE is_delete = 0";
        try (Connection con = ConnectionHelper.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    default boolean insert(Supplier s) {
        String sql = "INSERT INTO nhacungcap(tennhacungcap, diachi, email, sdt, trangthai, is_delete) VALUES (?, ?, ?, ?, ?, 0)";
        try (Connection con = ConnectionHelper.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getTenNhaCungCap());
            ps.setString(2, s.getDiaChi());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getSdt());
            // Chuyển int sang chuỗi enum
            String trangThaiStr = convertTrangThaiIntToEnum(s.getTrangThai());
            ps.setString(5, trangThaiStr);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    default boolean update(Supplier s) {
        String sql = "UPDATE nhacungcap SET tennhacungcap=?, diachi=?, email=?, sdt=?, trangthai=? WHERE manhacungcap=?";
        try (Connection con = ConnectionHelper.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getTenNhaCungCap());
            ps.setString(2, s.getDiaChi());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getSdt());

            // Chuyển int sang enum string
            String trangThaiStr = convertTrangThaiIntToEnum(s.getTrangThai());
            ps.setString(5, trangThaiStr);

            ps.setInt(6, s.getMaNhaCungCap());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    default boolean delete(int maNhaCungCap) {
        // Xóa mềm bằng cách set is_delete = 1
        String sql = "UPDATE nhacungcap SET is_delete = 1 WHERE manhacungcap = ?";
        try (Connection con = ConnectionHelper.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maNhaCungCap);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    default boolean updateTrangThai(int maNCC, int trangThai) {
        String sql = "UPDATE nhacungcap SET trangthai = ? WHERE manhacungcap = ?";
        try (Connection con = ConnectionHelper.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, trangThai);
            ps.setInt(2, maNCC);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    default int getSupplierCount() {
        String sql = "SELECT COUNT(*) FROM nhacungcap WHERE is_delete = 0";
        try (Connection con = ConnectionHelper.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // === HÀM TIỆN ÍCH ===
    default String convertTrangThaiIntToEnum(int trangThai) {
        return trangThai == 0 ? "MoKhoa" : "Khoa";
    }
}

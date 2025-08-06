package dao;

import entity.OS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public interface OSDAO {

    // Lấy danh sách hệ điều hành chưa bị xóa
    default List<OS> getAllOS() {
        List<OS> list = new ArrayList<>();
        String sql = "SELECT mahedieuhanh, tenhedieuhanh FROM hedieuhanh WHERE is_delete = 0";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("mahedieuhanh");
                String name = rs.getString("tenhedieuhanh");
                list.add(new OS(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Thêm mới, mặc định is_delete = 0
    default boolean insertOS(OS os) {
        String sql = "INSERT INTO hedieuhanh (tenhedieuhanh, is_delete) VALUES (?, 0)";
        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, os.getTen());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa mềm
    default boolean deleteOSById(int id) {
        String sql = "UPDATE hedieuhanh SET is_delete = 1 WHERE mahedieuhanh = ?";
        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật tên nếu chưa bị xóa
    default boolean updateOSById(int id, String newName) {
        String sql = "UPDATE hedieuhanh SET tenhedieuhanh = ? WHERE mahedieuhanh = ? AND is_delete = 0";
        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra trùng tên nếu chưa bị xóa
    default boolean isOSNameExists(String ten) {
        String sql = "SELECT COUNT(*) FROM hedieuhanh WHERE LOWER(tenhedieuhanh) = ? AND is_delete = 0";
        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ten.toLowerCase());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

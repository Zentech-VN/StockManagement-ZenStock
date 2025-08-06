package dao;

import entity.Brand;
import entity.MadeIn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public interface MadeInDAO {

    // Lấy danh sách xuất xứ chưa bị xóa
    default List<MadeIn> getAllMadeIn() {
        List<MadeIn> list = new ArrayList<>();
        String sql = "SELECT maxuatxu, tenxuatxu FROM xuatxu WHERE is_delete = 0";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new MadeIn(rs.getInt("maxuatxu"), rs.getString("tenxuatxu")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Thêm mới, mặc định is_delete = 0
    default boolean insertMadeIn(MadeIn madeIn) {
        String sql = "INSERT INTO xuatxu (tenxuatxu, is_delete) VALUES (?, 0)";
        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, madeIn.getTen());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa mềm
    default boolean deleteMadeInById(int id) {
        String sql = "UPDATE xuatxu SET is_delete = 1 WHERE maxuatxu = ?";
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
    default boolean updateMadeInById(int id, String newName) {
        String sql = "UPDATE xuatxu SET tenxuatxu = ? WHERE maxuatxu = ? AND is_delete = 0";
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

    // Kiểm tra tên trùng trong các bản ghi chưa xóa
    default boolean isMadeInNameExists(String ten) {
        String sql = "SELECT COUNT(*) FROM xuatxu WHERE LOWER(tenxuatxu) = ? AND is_delete = 0";
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

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

    default List<MadeIn> getAllMadeIn() {
        List<MadeIn> list = new ArrayList<>();
        String sql = "SELECT maxuatxu, tenxuatxu FROM xuatxu";

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
    
    default boolean insertMadeIn(MadeIn madeIn) {
        try (Connection conn = ConnectionHelper.getConnection()) {
            String sql = "INSERT INTO xuatxu (tenxuatxu) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, madeIn.getTen());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    default boolean deleteMadeInById(int id) {
        try (Connection conn = ConnectionHelper.getConnection()) {
            String sql = "DELETE FROM xuatxu WHERE maxuatxu = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    default boolean updateMadeInById(int id, String newName) {
        try (Connection conn = ConnectionHelper.getConnection()) {
            String sql = "UPDATE xuatxu SET tenxuatxu = ? WHERE maxuatxu = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newName);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    default boolean isMadeInNameExists(String ten) {
        String sql = "SELECT COUNT(*) FROM xuatxu WHERE LOWER(tenxuatxu) = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ten.toLowerCase());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

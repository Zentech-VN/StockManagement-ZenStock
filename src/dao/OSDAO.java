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

    default List<OS> getAllOS() {
        List<OS> list = new ArrayList<>();
        String sql = "SELECT mahedieuhanh, tenhedieuhanh FROM hedieuhanh";

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

    default boolean insertOS(OS os) {
        try (Connection conn = ConnectionHelper.getConnection()) {
            String sql = "INSERT INTO hedieuhanh (tenhedieuhanh) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, os.getTen());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    default boolean deleteOSById(int id) {
        try (Connection conn = ConnectionHelper.getConnection()) {
            String sql = "DELETE FROM hedieuhanh WHERE mahedieuhanh = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    default boolean updateOSById(int id, String newName) {
        try (Connection conn = ConnectionHelper.getConnection()) {
            String sql = "UPDATE hedieuhanh SET tenhedieuhanh = ? WHERE mahedieuhanh = ?";
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

    default boolean isOSNameExists(String ten) {
        String sql = "SELECT COUNT(*) FROM hedieuhanh WHERE LOWER(tenhedieuhanh) = ?";
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

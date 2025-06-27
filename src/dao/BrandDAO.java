package dao;

import entity.Brand;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public interface BrandDAO {

    default List<Brand> getAllBrands() {
        List<Brand> list = new ArrayList<>();
        String sql = "SELECT mathuonghieu, tenthuonghieu FROM thuonghieu";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("mathuonghieu");
                String name = rs.getString("tenthuonghieu");
                list.add(new Brand(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    default boolean insertBrand(Brand brand) {
        try (Connection conn = ConnectionHelper.getConnection()) {
            String sql = "INSERT INTO thuonghieu (tenthuonghieu) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, brand.getTen());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    default boolean deleteBrandById(int id) {
        try (Connection conn = ConnectionHelper.getConnection()) {
            String sql = "DELETE FROM thuonghieu WHERE mathuonghieu = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    default boolean updateBrandById(int id, String newName) {
        try (Connection conn = ConnectionHelper.getConnection()) {
            String sql = "UPDATE thuonghieu SET tenthuonghieu = ? WHERE mathuonghieu = ?";
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

    default boolean isBrandNameExists(String ten) {
        String sql = "SELECT COUNT(*) FROM thuonghieu WHERE LOWER(tenthuonghieu) = ?";
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

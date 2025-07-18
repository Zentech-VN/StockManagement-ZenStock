/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jdbc.ConnectionHelper;
import entity.WarehouseManagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface WarehouseManagementDAO {

    boolean updateWarehouseWithValidation(WarehouseManagement wh);

    default List<WarehouseManagement> getAllWarehouses() {
        List<WarehouseManagement> list = new ArrayList<>();
        String sql = "SELECT * FROM khuvuckho";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                WarehouseManagement w = new WarehouseManagement(
                        rs.getInt("makhuvuc"),
                        rs.getString("tenkhuvuc")
                );
                list.add(w);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    default boolean addWarehouse(WarehouseManagement warehouse) {
        String sql = "INSERT INTO khuvuc (tenkhuvuc) VALUES (?)";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, warehouse.getTenKhuVuc());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException ex) {
            System.err.println("Lỗi khi thêm kho: " + ex.getMessage());
            return false;
        }
    }

    // Cập nhật kho
    default int updateWarehouse(WarehouseManagement warehouse) {
        String sql = "UPDATE khuvuc SET tenkhuvuc = ? WHERE makhuvuc = ?";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, warehouse.getTenKhuVuc());
            stmt.setInt(2, warehouse.getMaKhuVuc());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật kho: " + e.getMessage());
            return 0;
        }

    }

    // Xoá kho theo ID
    default boolean deleteWarehouseById(int id) {
        String sql = "DELETE FROM khuvuc WHERE makhuvuc = ?";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kiểm tra mã khu vực có tồn tại
    default boolean maKhuVucTonTai(int maKhuVuc) {
        String sql = "SELECT 1 FROM khuvuc WHERE makhuvuc = ?";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maKhuVuc);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    default int getWareHouseCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM khuvuc";

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

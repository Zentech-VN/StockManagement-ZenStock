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
import javax.swing.JOptionPane;

public interface WarehouseManagementDAO {


    default List<WarehouseManagement> getAllWarehouses() {
        List<WarehouseManagement> list = new ArrayList<>();
        String sql = "SELECT * FROM khuvuckho";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                WarehouseManagement w = new WarehouseManagement(
                        rs.getInt("makhuvuc"),
                        rs.getString("tenkhuvuc"),
                        rs.getString("ghichu")
                );
                list.add(w);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    default boolean deleteWarehouseById(int id) {
        String sql = "DELETE FROM khuvuckho WHERE makhuvuc = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    default boolean updateWarehouse(WarehouseManagement warehouse) {
        String sql = "UPDATE khuvuckho SET TenKhuVuc = ?, GhiChu = ? WHERE MaKhuVuc = ?";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, warehouse.getTenKhuVuc());
            stmt.setString(2, warehouse.getGhiChu());
            stmt.setInt(3, warehouse.getMaKhuVuc());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Không có dòng nào được cập nhật. Kiểm tra lại Mã Kho: " + warehouse.getMaKhuVuc());
                return false;
            } else {
                System.out.println("Cập nhật thành công: " + rowsAffected + " dòng.");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật kho: " + e.getMessage());
            return false;
        }
    }

default boolean insertWarehouse(WarehouseManagement wh) throws SQLException {
    String sql = "INSERT INTO khuvuckho (tenkhuvuc, ghichu) VALUES (?, ?)";
    try (Connection conn = ConnectionHelper.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, wh.getTenKhuVuc());
        ps.setString(2, wh.getGhiChu());
        return ps.executeUpdate() > 0;
    }
    }
 default boolean addWarehouse(WarehouseManagement warehouse) {
        String sql = "INSERT INTO khuvuckho (TenKhuVuc, GhiChu) VALUES (?, ?)";

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, warehouse.getTenKhuVuc());
            stmt.setString(2, warehouse.getGhiChu());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException ex) {
            System.err.println("Lỗi khi thêm kho: " + ex.getMessage());
            return false;
        }
    }
}


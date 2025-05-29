package dao;

import entity.Employee;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;
import raven.toast.Notifications;

public interface EmployeeDAO {

    default List<Employee> getAllEmployee() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM vw_nhanvien_toan_bo";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Employee e = new Employee();
                e.setManv(rs.getInt("manv"));
                e.setHoten(rs.getString("hoten"));
                e.setGioitinh(rs.getInt("gioitinh"));
                e.setNgaysinh(rs.getDate("ngaysinh"));
                e.setSdt(rs.getInt("sdt"));
                e.setEmail(rs.getString("email"));
                e.setTrangthai(rs.getInt("trangthai"));
                list.add(e);
            }
        } catch (SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Error while get employee.");
            ex.printStackTrace();
        }

        return list;
    }

    default boolean addEmployee(String hoTen, int gioiTinh, Date ngaySinh, int dienThoai, String email) {
        String sql = "{CALL sp_nhanvien_add(?, ?, ?, ?, ?, ?)}";

        try (
                Connection conn = ConnectionHelper.getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, hoTen);
            cs.setInt(2, gioiTinh);
            cs.setDate(3, ngaySinh);
            cs.setInt(4, dienThoai);
            cs.setString(5, email);
            cs.setInt(6, 1);

            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Error while adding employee.");
            ex.printStackTrace();
            return false;
        }
    }

    default boolean updateEmployee(Employee e) {
        String sql = "{CALL sp_nhanvien_update(?, ?, ?, ?, ?, ?, ?)}";

        try (
                Connection conn = ConnectionHelper.getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, e.getManv());
            cs.setString(2, e.getHoten());
            cs.setInt(3, e.getGioitinh());
            cs.setDate(4, e.getNgaysinh());
            cs.setInt(5, e.getSdt());
            cs.setString(6, e.getEmail());
            cs.setInt(7, e.getTrangthai());

            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Error while updating employee.");
            ex.printStackTrace();
            return false;
        }
    }

    default boolean deleteEmployee(int manv) {
        String sql = "{CALL sp_nhanvien_delete(?)}";

        try (
                Connection conn = ConnectionHelper.getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, manv);

            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Error while deleting employee.");
            ex.printStackTrace();
            return false;
        }
    }
}

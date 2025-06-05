package dao;

import entity.Employee;
import entity.EmployeeAccout;
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
                e.setSdt(rs.getString("sdt"));
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

    default EmployeeAccout getAccountInfoByEmployeeId(int manv) {
        String sql = "SELECT tendangnhap, manhomquyen FROM vw_nhanvien_taikhoan WHERE manv =  ? ";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, manv);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("tendangnhap");
                    Integer roleId = (Integer) rs.getObject("manhomquyen");
                    boolean hasAcc = username != null;

                    return new EmployeeAccout(username, roleId, hasAcc);
                }
            }
        } catch (SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi khi lấy dữ liệu tài khoản");
            ex.printStackTrace();
        }
        return new EmployeeAccout(null, null, false);
    }

    default boolean addEmployee(String hoTen, int gioiTinh, Date ngaySinh, String dienThoai, String email) {
        String sql = "{CALL sp_nhanvien_add(?, ?, ?, ?, ?, ?)}";

        try (
                Connection conn = ConnectionHelper.getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, hoTen);
            cs.setInt(2, gioiTinh);
            cs.setDate(3, ngaySinh);
            cs.setString(4, dienThoai);
            cs.setString(5, email);
            cs.setInt(6, 1);

            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi khi thêm nhân viên");
            ex.printStackTrace();
            return false;
        }
    }

    default boolean updateEmployee(int ma, String hoTen, int gioiTinh, Date ngaySinh, String dienThoai, String email, int trangThai) {
        String sql = "{CALL sp_nhanvien_update(?, ?, ?, ?, ?, ?, ?)}";

        try (
                Connection conn = ConnectionHelper.getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, ma);
            cs.setString(2, hoTen);
            cs.setInt(3, gioiTinh);
            cs.setDate(4, ngaySinh);
            cs.setString(5, dienThoai);
            cs.setString(6, email);
            cs.setInt(7, trangThai);

            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi khi sửa nhân viên");
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
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi khi xoá nhân viên");
            ex.printStackTrace();
            return false;
        }
    }

    default List<Employee> searchEmployeesProc(String keyword) {
        List<Employee> list = new ArrayList<>();
        String sql = "{CALL sp_search_employees(?)}";

        try (Connection cn = ConnectionHelper.getConnection(); CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, keyword);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Employee e = new Employee();
                    e.setManv(rs.getInt("manv"));
                    e.setHoten(rs.getString("hoten"));
                    e.setGioitinh(rs.getInt("gioitinh"));
                    e.setNgaysinh(rs.getDate("ngaysinh"));
                    e.setSdt(rs.getString("sdt"));
                    e.setEmail(rs.getString("email"));
                    e.setTrangthai(rs.getInt("trangthai"));
                    list.add(e);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    default int getEmployeeCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM nhanvien";

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

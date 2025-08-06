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

    default List<Employee> getAllEmployee(int page, int pageSize) {
        List<Employee> list = new ArrayList<>();

        int offset = (page - 1) * pageSize;

        String sql = "SELECT manv, hoten, gioitinh, ngaysinh, sdt, email, trangthai FROM nhanvien WHERE is_delete = 0 ORDER BY manv ASC LIMIT ? OFFSET ?";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pageSize);
            ps.setInt(2, offset);

            try (ResultSet rs = ps.executeQuery()) {
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
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi khi lấy dữ liệu tài khoản");
            ex.printStackTrace();
        }

        return list;
    }

    default EmployeeAccout getAccountInfoByEmployeeId(int manv) {
        String sql = "SELECT tendangnhap, manhomquyen FROM taikhoan WHERE manv = ?";

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
        String sql = "INSERT INTO nhanvien (hoten, gioitinh, ngaysinh, sdt, email, trangthai, is_delete) "
                + "VALUES (?, ?, ?, ?, ?, ?, 0)";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hoTen);
            ps.setInt(2, gioiTinh);
            ps.setDate(3, ngaySinh);
            ps.setString(4, dienThoai);
            ps.setString(5, email);
            ps.setInt(6, 1);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi khi thêm nhân viên");
            ex.printStackTrace();
            return false;
        }
    }

    default boolean updateEmployee(int ma, String hoTen, int gioiTinh, Date ngaySinh, String dienThoai, String email, int trangThai) {
        String sql = "UPDATE nhanvien "
                + "SET hoten = ?, gioitinh = ?, ngaysinh = ?, sdt = ?, email = ?, trangthai = ? "
                + "WHERE manv = ? AND is_delete = 0";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hoTen);
            ps.setInt(2, gioiTinh);
            ps.setDate(3, ngaySinh);
            ps.setString(4, dienThoai);
            ps.setString(5, email);
            ps.setInt(6, trangThai);
            ps.setInt(7, ma);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi khi sửa nhân viên");
            ex.printStackTrace();
            return false;
        }
    }

    default boolean deleteEmployee(int manv) {
        String sql = "UPDATE nhanvien "
                + "SET is_delete = 1 "
                + "WHERE manv = ?";
        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, manv);

            return ps.executeUpdate() > 0;
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
        String sql = "SELECT COUNT(*) FROM nhanvien WHERE is_delete = 0";

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

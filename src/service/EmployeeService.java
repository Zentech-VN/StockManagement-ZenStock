package service;

import dao.EmployeeDAO;
import entity.Employee;
import entity.EmployeeAccout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import raven.toast.Notifications;

public class EmployeeService implements EmployeeDAO {

    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getAllEmployeeService() {
        return employeeList = getAllEmployee();
    }

    public EmployeeAccout fetchAccountInfo(int manv) {
        return EmployeeDAO.super.getAccountInfoByEmployeeId(manv);
    }

    public List<Employee> searchEmployees(String keyword) {
        return searchEmployeesProc(keyword);
    }

    public boolean addCheck(String hoTen, int gioiTinh, String ngaySinh, String dienThoai, String email) {
        if (hoTen.length() <= 0 || hoTen.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tên không được trống");
            return false;
        }

        if (ngaySinh.length() <= 0 || ngaySinh.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tên không được trống");
            return false;
        }

        Date ngaySinhDate;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(ngaySinh);
            ngaySinhDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Ngày sinh năm sinh không hợp lệ");
            return false;
        }

        if (dienThoai.length() <= 0 || dienThoai.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Số điện thoại không được trống");
            return false;
        }

        String phoneRegex = "^(0|\\+84)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-5]|9[0-9])[0-9]{7}$";

        if (!dienThoai.matches(phoneRegex)) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Số điện thoại không hợp lệ");
            return false;
        }

        if (email.length() <= 0 || email.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Email không được trống");
            return false;
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

        if (!email.matches(emailRegex)) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Email không hợp lệ");
            return false;
        }

        if (addEmployee(hoTen, gioiTinh, ngaySinhDate, dienThoai, email)) {
            return true;
        }

        return false;
    }

    public boolean updateCheck(int ma, String hoTen, int gioiTinh, String ngaySinh, String dienThoai, String email, int trangThai) {
        if (hoTen.length() <= 0 || hoTen.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tên không được trống");
            return false;
        }

        if (ngaySinh.length() <= 0 || ngaySinh.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tên không được trống");
            return false;
        }

        Date ngaySinhDate;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(ngaySinh);
            ngaySinhDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Ngày sinh năm sinh không hợp lệ");
            return false;
        }

        if (dienThoai.length() <= 0 || dienThoai.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Số điện thoại không được trống");
            return false;
        }

        String phoneRegex = "^(0|\\+84)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-5]|9[0-9])[0-9]{7}$";

        if (!dienThoai.matches(phoneRegex)) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Số điện thoại không hợp lệ");
            return false;
        }

        if (email.length() <= 0 || email.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Email không được trống");
            return false;
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

        if (!email.matches(emailRegex)) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Email không hợp lệ");
            return false;
        }

        if (updateEmployee(ma, hoTen, gioiTinh, ngaySinhDate, dienThoai, email, trangThai)) {
            return true;
        }

        return false;
    }

    public boolean deleteEmployeeById(int ma) {
        if (deleteEmployee(ma)) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xoá thành công");
            return true;
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Xoá thất bại");
            return false;
        }
    }

    public int getEmployeeCountService() {
        return getEmployeeCount();
    }
}

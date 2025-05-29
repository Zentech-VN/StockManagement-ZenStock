package service;

import dao.EmployeeDAO;
import entity.Employee;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import raven.toast.Notifications;

public class EmployeeService implements EmployeeDAO {

    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getAllEmployeeService() {
        return employeeList = getAllEmployee();
    }

    public boolean addCheck(String hoTen, int gioiTinh, String ngaySinh, String dienThoai, String email) {
        System.out.println(gioiTinh);
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

        int dienThoaiInt;
        try {
            dienThoaiInt = Integer.parseInt(dienThoai);
        } catch (Exception ex) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Số điện thoại không hợp lệ");
            return false;
        }

        if (email.length() <= 0 || email.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Email không được trống");
            return false;
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Email không hợp lệ");
            return false;
        }

        if (addEmployee(hoTen, gioiTinh, ngaySinhDate, dienThoaiInt, email)) {
            return true;
        }
        
        return false;
    }

}

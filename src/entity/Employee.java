package entity;

import java.sql.Date;

public class Employee {

    private int manv;
    private Account acc;
    private String hoten;
    private int gioitinh;
    private Date ngaysinh;
    private String sdt;
    private String email;
    private int trangthai;

    public Employee() {
        acc = new Account(); // Khởi tạo để tránh null
    }

    public Employee(int manv, Account acc, String hoten, int gioitinh, Date ngaysinh, String sdt, String email, int trangthai) {
        this.manv = manv;
        this.acc = acc;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.email = email;
        this.trangthai = trangthai;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getGioiTinhText() {
        return gioitinh == 1 ? "Nam" : "Nữ";
    }

    public String getTrangThaiText() {
        return trangthai == 1 ? "Đang làm" : "Đã nghỉ";
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

}

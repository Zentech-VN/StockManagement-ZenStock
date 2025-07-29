package entity;

import java.sql.Date;

public class PhieuXuat {

    private int maphieuxuat;
    private Cilent khachhang = new Cilent();
    private Employee nhanvien = new Employee();
    private Date thoigian;
    private String trangthai;

    public PhieuXuat() {
    }

    public PhieuXuat(int maphieuxuat, Date thoigian, String trangthai) {
        this.maphieuxuat = maphieuxuat;
        this.thoigian = thoigian;
        this.trangthai = trangthai;
    }

    public int getMaphieuxuat() {
        return maphieuxuat;
    }

    public void setMaphieuxuat(int maphieuxuat) {
        this.maphieuxuat = maphieuxuat;
    }

    public Cilent getKhachhang() {
        return khachhang;
    }

    public void setKhachhang(Cilent khachhang) {
        this.khachhang = khachhang;
    }

    public Employee getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(Employee nhanvien) {
        this.nhanvien = nhanvien;
    }

    public Date getThoigian() {
        return thoigian;
    }

    public void setThoigian(Date thoigian) {
        this.thoigian = thoigian;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

}

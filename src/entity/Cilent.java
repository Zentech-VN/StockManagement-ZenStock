package entity;

import java.sql.Date;

public class Cilent {

    private int MaKhacHang;
    private String TenKhacHang;
    private String DiaChi;
    private String SoDienThoai;
    private String Email;
    private String TrangThai;
    private Date NgayThamGia;

    public Cilent() {
    }

    public Cilent(int MaKhacHang, String TenKhacHang, String DiaChi, String SoDienThoai, String Email, String TrangThai, Date NgayThamGia) {
        this.MaKhacHang = MaKhacHang;
        this.TenKhacHang = TenKhacHang;
        this.DiaChi = DiaChi;
        this.SoDienThoai = SoDienThoai;
        this.Email = Email;
        this.TrangThai = TrangThai;
        this.NgayThamGia = NgayThamGia;
    }

    public int getMaKhacHang() {
        return MaKhacHang;
    }

    public void setMaKhacHang(int MaKhacHang) {
        this.MaKhacHang = MaKhacHang;
    }

    public String getTenKhacHang() {
        return TenKhacHang;
    }

    public void setTenKhacHang(String TenKhacHang) {
        this.TenKhacHang = TenKhacHang;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public Date getNgayThamGia() {
        return NgayThamGia;
    }

    public void setNgayThamGia(Date NgayThamGia) {
        this.NgayThamGia = NgayThamGia;
    }

}

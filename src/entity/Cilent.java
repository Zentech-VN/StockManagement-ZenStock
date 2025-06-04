
package entity;

import java.sql.Date;

public class Cilent {

    private int MaKhacHang;
    private String TenKhacHang;
    private String DiaChi;
    private String SoDienThoai;
    private int TrangThai;
    private Date NgayThamGia;

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

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public Date getNgayThamGia() {
        return NgayThamGia;
    }

    public void setNgayThamGia(Date NgayThamGia) {
        this.NgayThamGia = NgayThamGia;
    }

}

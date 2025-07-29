package entity;

import java.math.BigDecimal;

public class PhieuXuatChiTiet {

    private PhieuXuat phieuxuat = new PhieuXuat();
    private Product sanpham = new Product();
    private BigDecimal dongia;
    private int soluong;
    private String ghichu;

    public PhieuXuatChiTiet() {
    }

    public PhieuXuatChiTiet(BigDecimal dongia, int soluong, String ghichu) {
        this.dongia = dongia;
        this.soluong = soluong;
        this.ghichu = ghichu;
    }

    public PhieuXuat getPhieuxuat() {
        return phieuxuat;
    }

    public void setPhieuxuat(PhieuXuat phieuxuat) {
        this.phieuxuat = phieuxuat;
    }

    public Product getSanpham() {
        return sanpham;
    }

    public void setSanpham(Product sanpham) {
        this.sanpham = sanpham;
    }

    public BigDecimal getDongia() {
        return dongia;
    }

    public void setDongia(BigDecimal dongia) {
        this.dongia = dongia;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

}

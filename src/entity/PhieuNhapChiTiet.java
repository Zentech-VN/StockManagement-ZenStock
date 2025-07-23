package entity;

import java.math.BigDecimal;

public class PhieuNhapChiTiet {

    private PhieuNhap ph = new PhieuNhap();
    private Product p = new Product();
    private BigDecimal dongia;
    private int soluong;
    private String ghichu;

    public PhieuNhapChiTiet() {
    }

    public PhieuNhapChiTiet(BigDecimal dongia, int soluong, String ghichu) {
        this.dongia = dongia;
        this.soluong = soluong;
        this.ghichu = ghichu;
    }

    public PhieuNhap getPh() {
        return ph;
    }

    public void setPh(PhieuNhap ph) {
        this.ph = ph;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public BigDecimal getDongia() {
        return dongia;
    }

    public void setDongia(BigDecimal dongia) {
        this.dongia = dongia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

}

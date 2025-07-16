package entity;

import java.util.Date;

public class PhieuNhap {

    private int maphieunhap;
    private Supplier s = new Supplier();
    private Employee e = new Employee();
    private Date ngaytao;
    private String trangthai;

    public PhieuNhap() {
    }

    public PhieuNhap(int maphieunhap, Date ngaytao, String trangthai) {
        this.maphieunhap = maphieunhap;
        this.ngaytao = ngaytao;
        this.trangthai = trangthai;
    }

    public int getMaphieunhap() {
        return maphieunhap;
    }

    public void setMaphieunhap(int maphieunhap) {
        this.maphieunhap = maphieunhap;
    }

    public Supplier getS() {
        return s;
    }

    public void setS(Supplier s) {
        this.s = s;
    }

    public Employee getE() {
        return e;
    }

    public void setE(Employee e) {
        this.e = e;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

}

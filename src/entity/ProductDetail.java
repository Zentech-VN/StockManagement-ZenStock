/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

public class ProductDetail {

    private int maimei;
    private Cilent c = new Cilent();
    private Product p = new Product();
    private int tinhtrang;
    private String trangthai;
    private String ghichu;

    public ProductDetail() {
    }

    public ProductDetail(int maimei, int tinhtrang, String trangthai, String ghichu) {
        this.maimei = maimei;
        this.tinhtrang = tinhtrang;
        this.trangthai = trangthai;
        this.ghichu = ghichu;
    }

    public int getMaimei() {
        return maimei;
    }

    public void setMaimei(int maimei) {
        this.maimei = maimei;
    }

    public Cilent getC() {
        return c;
    }

    public void setC(Cilent c) {
        this.c = c;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public int getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(int tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

}

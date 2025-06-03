/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author RGB
 */
public class Supplier {

    private Integer maNhaCungCap;
    private String tenNhaCungCap;
    private String diaChi;
    private String email;
    private String sdt;
    private Integer trangThai;

    public Supplier() {
    }

    public Supplier(Integer maNhaCungCap, String tenNhaCungCap, String diaChi, String email, String sdt, Integer trangThai) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.diaChi = diaChi;
        this.email = email;
        this.sdt = sdt;
        this.trangThai = trangThai;
    }

    public Integer getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(Integer maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

}

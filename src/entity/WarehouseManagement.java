/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ASUS
 */
public class WarehouseManagement {

    private int maKhuVuc;
    private String tenKhuVuc;
    private String ghiChu;

    public WarehouseManagement() {
    }

    public WarehouseManagement(int maKhuVuc, String tenKhuVuc, String ghiChu) {
        this.maKhuVuc = maKhuVuc;
        this.tenKhuVuc = tenKhuVuc;
        this.ghiChu = ghiChu;
    }

    public WarehouseManagement(String tenKhuVuc, String ghiChu) {
        this.tenKhuVuc = tenKhuVuc;
        this.ghiChu = ghiChu;
    }

    public int getMaKhuVuc() {
        return maKhuVuc;
    }

    public void setMaKhuVuc(int maKhuVuc) {
        this.maKhuVuc = maKhuVuc;
    }

    public String getTenKhuVuc() {
        return tenKhuVuc;
    }

    public void setTenKhuVuc(String tenKhuVuc) {
        this.tenKhuVuc = tenKhuVuc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}

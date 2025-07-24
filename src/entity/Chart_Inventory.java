package entity;

public class Chart_Inventory {

    private int maSanPham;
    private String tenSanPham;
    private int tonDauKy;
    private int nhapTrongKy;
    private int xuatTrongKy;
    private int tonCuoiKy;

    public Chart_Inventory() {
    }

    public Chart_Inventory(int maSanPham, String tenSanPham, int tonDauKy, int nhapTrongKy, int xuatTrongKy, int tonCuoiKy) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.tonDauKy = tonDauKy;
        this.nhapTrongKy = nhapTrongKy;
        this.xuatTrongKy = xuatTrongKy;
        this.tonCuoiKy = tonCuoiKy;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getTonDauKy() {
        return tonDauKy;
    }

    public void setTonDauKy(int tonDauKy) {
        this.tonDauKy = tonDauKy;
    }

    public int getNhapTrongKy() {
        return nhapTrongKy;
    }

    public void setNhapTrongKy(int nhapTrongKy) {
        this.nhapTrongKy = nhapTrongKy;
    }

    public int getXuatTrongKy() {
        return xuatTrongKy;
    }

    public void setXuatTrongKy(int xuatTrongKy) {
        this.xuatTrongKy = xuatTrongKy;
    }

    public int getTonCuoiKy() {
        return tonCuoiKy;
    }

    public void setTonCuoiKy(int tonCuoiKy) {
        this.tonCuoiKy = tonCuoiKy;
    }

}

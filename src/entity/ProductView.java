package entity;

public class ProductView {
    private int maSanPham;
    private String tenSanPham;
    private String tenXuatXu;
    private String tenThuongHieu;
    private String tenKhuVuc;
    private int soLuongTon;
    private int trangThai;

    public ProductView() {
    }

    public ProductView(int maSanPham, String tenSanPham, String tenXuatXu, String tenThuongHieu, String tenKhuVuc, int soLuongTon, int trangThai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.tenXuatXu = tenXuatXu;
        this.tenThuongHieu = tenThuongHieu;
        this.tenKhuVuc = tenKhuVuc;
        this.soLuongTon = soLuongTon;
        this.trangThai = trangThai;
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

    public String getTenXuatXu() {
        return tenXuatXu;
    }

    public void setTenXuatXu(String tenXuatXu) {
        this.tenXuatXu = tenXuatXu;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public String getTenKhuVuc() {
        return tenKhuVuc;
    }

    public void setTenKhuVuc(String tenKhuVuc) {
        this.tenKhuVuc = tenKhuVuc;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    public String getTrangThaiText() {
        return this.trangThai == 1 ? "Hoạt động" : "Khoá";
    }
}

package entity;

import java.math.BigDecimal;

public class Product {

    private int maSanPham;
    private String tenSanPham;
    private String hinhAnh;
    private String chipXuLy;
    private String dungLuongPin;
    private String kichThuocManHinh;
    private String cameraSau;
    private String cameraTruoc;
    private String thoiGianBaoHanh;
    private int soLuongTon;
    private int thongSo;
    private BigDecimal gia;
    private int trangThai;

    //Các bảng liên kết
    private String tenXuatXu;
    private String tenHeDieuHanh;
    private String tenThuongHieu;
    private String tenKhuVuc;

    public Product() {
    }

    public Product(int maSanPham, String tenSanPham, String hinhAnh, String chipXuLy, String dungLuongPin, String kichThuocManHinh, String cameraSau, String cameraTruoc, String thoiGianBaoHanh, int thongSo, BigDecimal gia, int trangThai, String tenXuatXu, String tenHeDieuHanh, String tenThuongHieu, String tenKhuVuc) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.hinhAnh = hinhAnh;
        this.chipXuLy = chipXuLy;
        this.dungLuongPin = dungLuongPin;
        this.kichThuocManHinh = kichThuocManHinh;
        this.cameraSau = cameraSau;
        this.cameraTruoc = cameraTruoc;
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.thongSo = thongSo;
        this.gia = gia;
        this.trangThai = trangThai;
        this.tenXuatXu = tenXuatXu;
        this.tenHeDieuHanh = tenHeDieuHanh;
        this.tenThuongHieu = tenThuongHieu;
        this.tenKhuVuc = tenKhuVuc;
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

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getChipXuLy() {
        return chipXuLy;
    }

    public void setChipXuLy(String chipXuLy) {
        this.chipXuLy = chipXuLy;
    }
    
    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getDungLuongPin() {
        return dungLuongPin;
    }

    public void setDungLuongPin(String dungLuongPin) {
        this.dungLuongPin = dungLuongPin;
    }

    public String getKichThuocManHinh() {
        return kichThuocManHinh;
    }

    public void setKichThuocManHinh(String kichThuocManHinh) {
        this.kichThuocManHinh = kichThuocManHinh;
    }

    public String getCameraSau() {
        return cameraSau;
    }

    public void setCameraSau(String cameraSau) {
        this.cameraSau = cameraSau;
    }

    public String getCameraTruoc() {
        return cameraTruoc;
    }

    public void setCameraTruoc(String cameraTruoc) {
        this.cameraTruoc = cameraTruoc;
    }

    public String getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public void setThoiGianBaoHanh(String thoiGianBaoHanh) {
        this.thoiGianBaoHanh = thoiGianBaoHanh;
    }

    public int getThongSo() {
        return thongSo;
    }

    public void setThongSo(int thongSo) {
        this.thongSo = thongSo;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public String getTrangThaiText() {
        if (this.trangThai == 1) {
            return "Hoạt động";
        } else if (this.trangThai == 0) {
            return "Khoá";
        } else if (this.trangThai == 2) {
            return "Ngừng bán";
        }
        return "Không xác định";
    }
    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenXuatXu() {
        return tenXuatXu;
    }

    public void setTenXuatXu(String tenXuatXu) {
        this.tenXuatXu = tenXuatXu;
    }

    public String getTenHeDieuHanh() {
        return tenHeDieuHanh;
    }

    public void setTenHeDieuHanh(String tenHeDieuHanh) {
        this.tenHeDieuHanh = tenHeDieuHanh;
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
    
    
}

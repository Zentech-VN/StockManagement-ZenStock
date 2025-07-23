package entity;

import java.math.BigDecimal;

public class Product {

    private int maSanPham;
    private String tenSanPham;
    private String hinhAnh;
    private String chipXuLy;
    private String cameraTruoc;
    private String cameraSau;
    private int thongSo;
    private BigDecimal gia;
    private String trangThai;
    private String dungLuongPin;
    private String kichThuocManHinh;
    private String thoiGianBaoHanh;
    private String tenXuatXu;
    private String tenHeDieuHanh;
    private String tenThuongHieu;
    private String tenKhuVuc;

    private int maXuatXu;
    private int maHeDieuHanh;
    private int maThuongHieu;

    public Product() {
    }

    public Product(int maSanPham, String tenSanPham, String hinhAnh, String chipXuLy, String cameraTruoc, String cameraSau, int thongSo, BigDecimal gia, String trangThai, String dungLuongPin, String kichThuocManHinh, String thoiGianBaoHanh, String tenXuatXu, String tenHeDieuHanh, String tenThuongHieu, String tenKhuVuc) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.hinhAnh = hinhAnh;
        this.chipXuLy = chipXuLy;
        this.cameraTruoc = cameraTruoc;
        this.cameraSau = cameraSau;
        this.thongSo = thongSo;
        this.gia = gia;
        this.trangThai = trangThai;
        this.dungLuongPin = dungLuongPin;
        this.kichThuocManHinh = kichThuocManHinh;
        this.thoiGianBaoHanh = thoiGianBaoHanh;
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

    public String getCameraTruoc() {
        return cameraTruoc;
    }

    public void setCameraTruoc(String cameraTruoc) {
        this.cameraTruoc = cameraTruoc;
    }

    public String getCameraSau() {
        return cameraSau;
    }

    public void setCameraSau(String cameraSau) {
        this.cameraSau = cameraSau;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
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

    public String getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public void setThoiGianBaoHanh(String thoiGianBaoHanh) {
        this.thoiGianBaoHanh = thoiGianBaoHanh;
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

    public int getMaXuatXu() {
        return maXuatXu;
    }

    public void setMaXuatXu(int maXuatXu) {
        this.maXuatXu = maXuatXu;
    }

    public int getMaHeDieuHanh() {
        return maHeDieuHanh;
    }

    public void setMaHeDieuHanh(int maHeDieuHanh) {
        this.maHeDieuHanh = maHeDieuHanh;
    }

    public int getMaThuongHieu() {
        return maThuongHieu;
    }

    public void setMaThuongHieu(int maThuongHieu) {
        this.maThuongHieu = maThuongHieu;
    }
}

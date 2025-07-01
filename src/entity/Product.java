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
    //Các bảng liên kết
    private String tenXuatXu;
    private String tenHeDieuHanh;
    private String tenThuongHieu;
    private String tenKhuVuc;
    private int maimei;
    private int maXuatXu;
    private int maHeDieuHanh;
    private int maThuongHieu;

    public Product() {
    }

    public Product(int maSanPham, String tenSanPham, String hinhAnh, String chipXuLy, String cameraTruoc, String cameraSau, int thongSo, BigDecimal gia, String trangThai, String dungLuongPin, String kichThuocManHinh, String thoiGianBaoHanh, String tenXuatXu, String tenHeDieuHanh, String tenThuongHieu, String tenKhuVuc, int maimei, int maXuatXu, int maHeDieuHanh, int maThuongHieu) {
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
        this.maimei = maimei;
        this.maXuatXu = maXuatXu;
        this.maHeDieuHanh = maHeDieuHanh;
        this.maThuongHieu = maThuongHieu;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public void setChipXuLy(String chipXuLy) {
        this.chipXuLy = chipXuLy;
    }

    public void setCameraTruoc(String cameraTruoc) {
        this.cameraTruoc = cameraTruoc;
    }

    public void setCameraSau(String cameraSau) {
        this.cameraSau = cameraSau;
    }

    public void setThongSo(int thongSo) {
        this.thongSo = thongSo;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setDungLuongPin(String dungLuongPin) {
        this.dungLuongPin = dungLuongPin;
    }

    public void setKichThuocManHinh(String kichThuocManHinh) {
        this.kichThuocManHinh = kichThuocManHinh;
    }

    public void setThoiGianBaoHanh(String thoiGianBaoHanh) {
        this.thoiGianBaoHanh = thoiGianBaoHanh;
    }

    public void setTenXuatXu(String tenXuatXu) {
        this.tenXuatXu = tenXuatXu;
    }

    public void setTenHeDieuHanh(String tenHeDieuHanh) {
        this.tenHeDieuHanh = tenHeDieuHanh;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public void setTenKhuVuc(String tenKhuVuc) {
        this.tenKhuVuc = tenKhuVuc;
    }

    public void setMaimei(int maimei) {
        this.maimei = maimei;
    }

    public void setMaXuatXu(int maXuatXu) {
        this.maXuatXu = maXuatXu;
    }

    public void setMaHeDieuHanh(int maHeDieuHanh) {
        this.maHeDieuHanh = maHeDieuHanh;
    }

    public void setMaThuongHieu(int maThuongHieu) {
        this.maThuongHieu = maThuongHieu;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public String getChipXuLy() {
        return chipXuLy;
    }

    public String getCameraTruoc() {
        return cameraTruoc;
    }

    public String getCameraSau() {
        return cameraSau;
    }

    public int getThongSo() {
        return thongSo;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public String getDungLuongPin() {
        return dungLuongPin;
    }

    public String getKichThuocManHinh() {
        return kichThuocManHinh;
    }

    public String getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public String getTenXuatXu() {
        return tenXuatXu;
    }

    public String getTenHeDieuHanh() {
        return tenHeDieuHanh;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public String getTenKhuVuc() {
        return tenKhuVuc;
    }

    public int getMaimei() {
        return maimei;
    }

    public int getMaXuatXu() {
        return maXuatXu;
    }

    public int getMaHeDieuHanh() {
        return maHeDieuHanh;
    }

    public int getMaThuongHieu() {
        return maThuongHieu;
    }

   
}

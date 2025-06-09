package entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    int maSanPham;
    String tenSanPham;
    String hinhAnh;
    String chipXuLy;
    int dungLuongPin;
    double kichThuocManHinh;
    String cameraSau;
    String cameraTruoc;
    int thoiGianBaoHanh;
    int soLuongTon;
    String phienBanHeDieuHanh;
    int trangThai;

    //Các bảng liên kết
    String tenXuatXu;
    String tenHeDieuHanh;
    String tenThuongHieu;
    String tenKhuVuc;
    int getmaSanPham;

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
}

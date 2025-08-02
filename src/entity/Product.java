package entity;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
public class Product {

    int maSanPham;
    String tenSanPham;
    String hinhAnh;
    String chipXuLy;
    String cameraTruoc;
    String cameraSau;
    int thongSo;
    BigDecimal gia;
    String trangThai;
    String dungLuongPin;
    String kichThuocManHinh;
    String thoiGianBaoHanh;
    String tenXuatXu;
    String tenHeDieuHanh;
    String tenThuongHieu;
    String tenKhuVuc;

    int maXuatXu;
    int maHeDieuHanh;
    int maThuongHieu;

}

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
public class ProductView {
    int maSanPham;
    String tenSanPham;
    String tenXuatXu;
    String tenThuongHieu;
    String tenKhuVuc;
    int soLuongTon;
    int trangThai;
    
    public String getTrangThaiText() {
        return this.trangThai == 1 ? "Hoạt động" : "Khoá";
    }
}

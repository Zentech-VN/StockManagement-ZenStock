package entity;

import java.sql.Date;
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
public class Cilent {

    int MaKhacHang;
    String TenKhacHang;
    String DiaChi;
    String SoDienThoai;
    int TrangThai;
    Date NgayThamGia;
}

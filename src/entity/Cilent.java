package entity;

import java.sql.Date;
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
public class Cilent {

    int MaKhacHang;
    String TenKhacHang;
    String DiaChi;
    String SoDienThoai;
    String Email;
    String TrangThai;
    Date NgayThamGia;

}

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
public class PhieuXuat {

    int maphieuxuat;
    Client khachhang = new Client();
    Employee nhanvien = new Employee();
    Date thoigian;
    String trangthai;

}

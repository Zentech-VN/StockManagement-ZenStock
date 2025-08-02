package entity;

import java.sql.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
public class Employee {

    int manv;
    Account acc;
    String hoten;
    int gioitinh;
    Date ngaysinh;
    String sdt;
    String email;
    int trangthai;

    public Employee() {
        acc = new Account(); // Khởi tạo để tránh null
    }

    public String getGioiTinhText() {
        return gioitinh == 1 ? "Nam" : "Nữ";
    }

    public String getTrangThaiText() {
        return trangthai == 1 ? "Đang làm" : "Đã nghỉ";
    }

}

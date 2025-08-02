package entity;

import java.util.Date;
import java.util.Objects;
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
public class Staff {

    int manv;
    String hoten;
    int gioitinh;
    String sdt;
    Date ngaysinh;
    int trangthai;
    String email;


    public Staff(int manv, String hoten, int gioitinh, Date ngaysinh, String sdt, int trangthai, String email) {
        this.manv = manv;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.trangthai = trangthai;
        this.email = email;
    }

    public Staff(String hoten, int gioitinh, Date ngaysinh, String sdt, int trangthai) {
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.trangthai = trangthai;
        this.email = email;
    }

    public int getColumnCount() {
        return getClass().getDeclaredFields().length;
    }
}

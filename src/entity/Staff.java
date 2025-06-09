package entity;

import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
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
    
    public int getColumnCount() {
        return getClass().getDeclaredFields().length;
    }
}

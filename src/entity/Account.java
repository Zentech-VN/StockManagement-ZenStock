package entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Account {

    int manv;
    String username;
    String matkhau;
    private int manhomquyen;
    int trangthai;
    
    public Account(int manv, String username, int manhomquyen, int trangthai) {
        this.manv = manv;
        this.username = username;
        this.manhomquyen = manhomquyen;
        this.trangthai = trangthai;
    }
}

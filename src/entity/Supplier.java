package entity;

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
public class Supplier {

    Integer maNhaCungCap;
    String tenNhaCungCap;
    String diaChi;
    String email;
    String sdt;
    Integer trangThai;
    int is_delete;

}

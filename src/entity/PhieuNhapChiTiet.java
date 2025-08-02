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
public class PhieuNhapChiTiet {

    PhieuNhap ph = new PhieuNhap();
    Product p = new Product();
    BigDecimal dongia;
    int soluong;
    String ghichu;
    
}

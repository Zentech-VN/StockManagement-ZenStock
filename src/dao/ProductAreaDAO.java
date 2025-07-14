package dao;

import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public class ProductAreaDAO {

    public List<Product> getProductsByWarehouse(int maKho) {
        List<Product> list = new ArrayList<>();
        String sql = "select \n"
                + "sanpham.masanpham, sanpham.tensp, thuonghieu.tenthuonghieu,\n"
                + "xuatxu.tenxuatxu, hedieuhanh.tenhedieuhanh, sanpham.gia, sanpham.trangthai\n"
                + "from \n"
                + "khuvuckho_sanpham join sanpham on khuvuckho_sanpham.masanpham = sanpham.masanpham\n"
                + "join thuonghieu on thuonghieu.mathuonghieu = sanpham.thuonghieu \n"
                + "join xuatxu on xuatxu.maxuatxu = sanpham.xuatxu \n"
                + "join hedieuhanh on hedieuhanh.mahedieuhanh = sanpham.hedieuhanh\n"
                + "where khuvuckho_sanpham.makhuvuc = ?;";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maKho);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setMaSanPham(rs.getInt("sanpham.masanpham"));
                p.setTenSanPham(rs.getString("sanpham.tensp"));
                p.setTenThuongHieu(rs.getString("thuonghieu.tenthuonghieu"));
                p.setGia(rs.getBigDecimal("sanpham.gia"));
                p.setTenHeDieuHanh(rs.getString("hedieuhanh.tenhedieuhanh"));
                p.setTenXuatXu(rs.getString("xuatxu.tenxuatxu"));
                p.setTrangThai(rs.getString("sanpham.trangthai"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

//    public List<ProductArea> getProduct() {
//        List<ProductArea> lista = new ArrayList<>();
//        String sql = "select khuvuckho_ctsanpham.maimei, sanpham.tensp, khuvuckho_ctsanpham.soluong, sanpham.gia, ctsanpham.trangthai\n"
//                + "from khuvuckho_ctsanpham join ctsanpham on khuvuckho_ctsanpham.maimei = ctsanpham.maimei\n"
//                + "join sanpham on ctsanpham.masanpham = sanpham.masanpham";
//        try (Connection conn = ConnectionHelper.getConnection(); Statement st = conn.createStatement()) {
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                ProductArea pa = new ProductArea();
//                pa.setMaimei(rs.getInt("khuvuckho_ctsanpham.maimei"));
//                pa.setSoluong(rs.getInt("khuvuckho_ctsanpham.soluong"));
//                pa.getPd().getP().setTenSanPham(rs.getString("sanpham.tensp"));
//                pa.getPd().getP().setGia(rs.getBigDecimal("sanpham.gia"));
//                pa.getPd().setTrangthai(rs.getString("ctsanpham.trangthai"));
//                lista.add(pa);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return lista;
//    }
}

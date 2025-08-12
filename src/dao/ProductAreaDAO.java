package dao;

import entity.ProductArea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public class ProductAreaDAO {

    public List<ProductArea> getProductsByWarehouse(int maKho) {
        List<ProductArea> list = new ArrayList<>();
        String sql = "select sp.masanpham, sp.tensp, th.tenthuonghieu, xx.tenxuatxu, hdh.tenhedieuhanh, sp.gia,sp.trangthai,soluong \n"
                + "from khuvuckho_sanpham as ks join sanpham as sp on ks.masanpham = sp.masanpham\n"
                + "join hedieuhanh as hdh on sp.hedieuHanh = hdh.mahedieuhanh\n"
                + "join xuatxu as xx on sp.xuatxu = xx.maxuatxu\n"
                + "join thuonghieu as th on sp.thuonghieu = th.mathuonghieu\n"
                + "where ks.makhuvuc = ?;";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maKho);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductArea pa = new ProductArea();
                pa.getP().setMaSanPham(rs.getInt("sp.masanpham"));
                pa.getP().setTenSanPham(rs.getString("sp.tensp"));
                pa.getP().setTenThuongHieu(rs.getString("th.tenthuonghieu"));
                pa.getP().setGia(rs.getBigDecimal("sp.gia"));
                pa.getP().setTenHeDieuHanh(rs.getString("hdh.tenhedieuhanh"));
                pa.getP().setTenXuatXu(rs.getString("xx.tenxuatxu"));
                pa.getP().setTrangThai(rs.getString("sp.trangthai"));
                pa.setSoluong(rs.getInt("soluong"));
                list.add(pa);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public int checkdelete(int makhuvuc) {
        int checksanoham = 0;
        String sql = "SELECT count(masanpham) AS total FROM zentechStockManagement.khuvuckho_sanpham WHERE makhuvuc = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, makhuvuc);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    checksanoham = rs.getInt("total");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checksanoham;
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

package dao;

import entity.ProductArea;
import entity.ProductDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public class ProductAreaDAO {

    public List<ProductDetail> getProductsByWarehouse(int maKho) {
        List<ProductDetail> list = new ArrayList<>();
        String sql = "select sanpham.masanpham,sanpham.tensp,thuonghieu.tenthuonghieu,"
                + " hedieuhanh.tenhedieuhanh, xuatxu.tenxuatxu, sanpham.trangthai, sanpham.gia\n"
                + "from khuvuckho_ctsanpham join ctsanpham on ctsanpham.maimei= khuvuckho_ctsanpham .maimei\n"
                + "join sanpham on ctsanpham.masanpham = sanpham.masanpham\n"
                + "join thuonghieu on sanpham.thuonghieu = thuonghieu.mathuonghieu\n"
                + "join xuatxu on sanpham.xuatxu = xuatxu.maxuatxu\n"
                + "join hedieuhanh on sanpham.hedieuhanh = hedieuhanh.mahedieuhanh\n"
                + "where khuvuckho_ctsanpham.makhuvuc = ?;";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maKho);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDetail pd = new ProductDetail();
                pd.getP().setMaSanPham(rs.getInt("sanpham.masanpham"));
                pd.getP().setTenSanPham(rs.getString("sanpham.tensp"));
                pd.getP().setTenThuongHieu(rs.getString("thuonghieu.tenthuonghieu"));
                pd.getP().setGia(rs.getBigDecimal("sanpham.gia"));
                pd.getP().setTenHeDieuHanh(rs.getString("hedieuhanh.tenhedieuhanh"));
                pd.getP().setTenXuatXu(rs.getString("xuatxu.tenxuatxu"));
                pd.getP().setTrangThai(rs.getString("sanpham.trangthai"));
                list.add(pd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return list;
    }

}

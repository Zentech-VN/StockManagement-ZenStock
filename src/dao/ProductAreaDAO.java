package dao;

import entity.ProductArea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public class ProductAreaDAO {

    public List<ProductArea> getProductsByWarehouse(int maKho) {
        List<ProductArea> list = new ArrayList<>();

        String sql = "select \n"
                + "sanpham.masanpham, sanpham.tensp, sanpham.hinhanh,\n"
                + " xuatxu.tenxuatxu, sanpham.chipxuly, hedieuhanh.tenhedieuhanh,\n"
                + " sanpham.cameratruoc, sanpham.camerasau,\n"
                + " sanpham.gia, sanpham.trangthai, thuonghieu.tenthuonghieu, sanpham.thoigianbaohanh,\n"
                + " sanpham.dungluongpin, sanpham.kichthuocmanhinh, khuvuckho.tenkhuvuc, soluong\n"
                + " from khuvuckho_sanpham join khuvuckho on khuvuckho_sanpham.makhuvuc = khuvuckho.makhuvuc \n"
                + "join sanpham on khuvuckho_sanpham.masanpham = sanpham.masanpham \n"
                + "join hedieuhanh on sanpham.hedieuhanh = hedieuhanh.mahedieuhanh\n"
                + "join xuatxu on sanpham.xuatxu = xuatxu.maxuatxu\n"
                + "join thuonghieu on sanpham.thuonghieu = thuonghieu.mathuonghieu\n"
                + "where khuvuckho_sanpham.makhuvuc = ?;";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maKho);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductArea pa = new ProductArea();
                pa.getP().setMaSanPham(rs.getInt("sanpham.masanpham"));
                pa.getP().setTenSanPham(rs.getString("sanpham.tensp"));
                pa.getP().setHinhAnh(rs.getString("sanpham.hinhanh"));
                pa.getP().setChipXuLy(rs.getString("sanpham.chipxuly"));
                pa.getP().setDungLuongPin(rs.getString("sanpham.dungluongpin"));
                pa.getP().setKichThuocManHinh(rs.getString("sanpham.kichthuocmanhinh"));
                pa.getP().setCameraSau(rs.getString("sanpham.camerasau"));
                pa.getP().setCameraTruoc(rs.getString("sanpham.cameratruoc"));
                pa.getP().setThoiGianBaoHanh(rs.getString("sanpham.thoigianbaohanh"));
                pa.getP().setGia(rs.getBigDecimal("sanpham.gia"));
                pa.getP().setTrangThai(rs.getString("sanpham.trangthai"));
                pa.getP().setTenHeDieuHanh(rs.getString("hedieuhanh.tenhedieuhanh"));
                pa.getP().setTenXuatXu(rs.getString("xuatxu.tenxuatxu"));
                pa.getP().setTenThuongHieu(rs.getString("thuonghieu.tenthuonghieu"));
                pa.getW().setTenKhuVuc(rs.getString("khuvuckho.tenkhuvuc"));
                pa.setSoluong(rs.getInt("soluong"));
                list.add(pa);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return list;
    }

}

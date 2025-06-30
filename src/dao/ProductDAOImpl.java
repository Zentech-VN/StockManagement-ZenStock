/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Product;
import entity.ProductArea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public class ProductDAOImpl implements ProductDAO {

    private Connection conn;

    public ProductDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public ProductDAOImpl() {
    }

    public List<Product> getProductsByWarehouse(int maKho) throws SQLException {
        List<Product> list = new ArrayList<>();

        String sql = "select \n"
                + "sanpham.masanpham, sanpham.tensp, sanpham.hinhanh,\n"
                + " xuatxu.tenxuatxu, sanpham.chipxuly, hedieuhanh.tenhedieuhanh,\n"
                + " sanpham.cameratruoc, sanpham.camerasau, sanpham.thongso,\n"
                + " sanpham.gia, sanpham.trangthai, thuonghieu.tenthuonghieu,\n"
                + " sanpham.dungluongpin, sanpham.kichthuocmanhinh, khuvuc.tenkhuvuc, soluong\n"
                + " from khuvuc_sanpham join khuvuc on khuvuc_sanpham.makhuvuc = khuvuc.makhuvuc \n"
                + "join sanpham on khuvuc_sanpham.masanpham = sanpham.masanpham \n"
                + "join hedieuhanh on sanpham.mahedieuhanh = hedieuhanh.mahedieuhanh\n"
                + "join xuatxu on sanpham.maxuatxu = xuatxu.maxuatxu\n"
                + "join thuonghieu on sanpham.mathuonghieu = thuonghieu.mathuonghieu\n"
                + "where khuvuc_sanpham.makhuvuc = ?;";
              

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maKho);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setMaSanPham(rs.getInt("masanpham"));
                p.setTenSanPham(rs.getString("tensp"));
                p.setHinhAnh(rs.getString("hinhanh"));
                p.setChipXuLy(rs.getString("chipxuly"));
                p.setDungLuongPin(rs.getString("dungluongpin"));
                p.setKichThuocManHinh(rs.getString("kichthuocmanhinh"));
                p.setCameraSau(rs.getString("camerasau"));
                p.setCameraTruoc(rs.getString("cameratruoc"));
                p.setThoiGianBaoHanh(rs.getString("thoigianbaohanh"));
                p.setThongSo(rs.getInt("thongso"));
                p.setGia(rs.getBigDecimal("gia"));
                p.setTrangThai(rs.getString("trangthai"));
                p.setTenHeDieuHanh(rs.getString("tenhedieuhanh"));
                p.setTenXuatXu(rs.getString("tenxuatxu"));
                p.setTenThuongHieu(rs.getString("tenThuongHieu"));
                list.add(p);
            }
        }

        return list;
    }
}

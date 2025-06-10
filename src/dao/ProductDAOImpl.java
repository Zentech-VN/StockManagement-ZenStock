/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDAOImpl implements ProductDAO {
        private Connection conn;

    public ProductDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public List<Product> getProductsByWarehouse(int maKho) throws SQLException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT sp.masanpham, sp.tensp, sp.hinhanh, xx.tenxuatxu AS xuatxu, "
                + "sp.chipxuly, sp.dungluongpin, sp.kichthuocmanhinh, hdh.tenhedieuchanh AS hedieuhanh, "
                + "sp.camerasau, sp.cameratruoc, sp.thoigianbaohanh, sp.thongso, sp.gia, "
                + "th.tenthuonghieu AS thuonghieu, sp.trangthai "
                + "FROM sanpham sp "
                + "LEFT JOIN xuatxu xx ON sp.maxuatxu = xx.maxuatxu "
                + "LEFT JOIN hedieuchanh hdh ON sp.mahedieuchanh = hdh.mahedieuchanh "
                + "LEFT JOIN thuonghieu th ON sp.mathuonghieu = th.mathuonghieu "
                + "INNER JOIN khuvuc_sanpham kvsp ON sp.masanpham = kvsp.masanpham "
                + "WHERE kvsp.makhuvuc = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maKho);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setMaSanPham(rs.getInt("masanpham"));
                p.setTenSanPham(rs.getString("tensp"));
                p.setHinhAnh(rs.getString("hinhanh"));
                p.setTenXuatXu(rs.getString("xuatxu"));
                p.setChipXuLy(rs.getString("chipxuly"));
                p.setDungLuongPin(rs.getString("dungluongpin"));
                p.setKichThuocManHinh(rs.getString("kichthuocmanhinh"));
                p.setTenHeDieuHanh(rs.getString("hedieuhanh"));
                p.setCameraSau(rs.getString("camerasau"));
                p.setCameraTruoc(rs.getString("cameratruoc"));
                p.setThoiGianBaoHanh(rs.getString("thoigianbaohanh"));
                p.setThongSo(rs.getInt("thongso"));
                p.setGia(rs.getBigDecimal("gia"));
                p.setTenThuongHieu(rs.getString("thuonghieu"));
                p.setTrangThai(rs.getInt("trangthai"));
                list.add(p);
            }
        }
        return list;
    }
}

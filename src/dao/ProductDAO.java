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

/**
 *
 * @author ASUS
 */
public class ProductDAO {

    private Connection conn;

    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Product> getProductsByWarehouse(String maKho) throws SQLException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM sanpham WHERE khuvuckho = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maKho);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setMaSP(rs.getString("masp"));
                p.setTenSP(rs.getString("tensp"));
               // p.setHinhAnh(rs.getString("hinhanh"));
                p.setXuatXu(rs.getString("xuatxu"));
                p.setChipXuLy(rs.getString("chipxuly"));
                p.setDungLuongPin(rs.getString("dungluongpin"));
               // p.setKichThuocMan(rs.getString("kichthuocman"));
               // p.setHeDieuHanh(rs.getString("hedieuhanh"));
//                p.setPhienBanDH(rs.getString("phienbanhdh"));
//                p.setCameraSau(rs.getString("camerasau"));
//                p.setCameraTruoc(rs.getString("cameratruoc"));
//                p.setThoiGianBaoHanh(rs.getString("thoigianbaohanh"));
  //              p.setThuongHieu(rs.getString("thuonghieu"));
                p.setKhuVucKho(rs.getString("khuvuckho"));
                p.setSoLuongTon(rs.getInt("soluongton"));
                p.setTrangThai(rs.getString("trangthai"));
                list.add(p);
            }
        }
        return list;
    }
}

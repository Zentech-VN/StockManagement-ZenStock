/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.ProductDAO;
import entity.Product;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ProductService {
  private Connection conn;

    public ProductService(Connection conn) {
        this.conn = conn;
    }

    public List<Product> getProductsByWarehouse(String warehouseId) {
        try {
            ProductDAO dao = new ProductDAO(conn);
            return dao.getProductsByWarehouse(warehouseId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void loadProductToTable(JTable table, List<Product> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        for (Product p : list) {
            model.addRow(new Object[]{
                p.getMaSP(),
                p.getTenSP(),
             //   p.getHinhAnh(),
                p.getXuatXu(),
                p.getChipXuLy(),
                p.getDungLuongPin(),
//                p.getKichThuocMan(),
//                p.getHeDieuHanh(),
//                p.getPhienBanDH(),
//                p.getCameraSau(),
//                p.getCameraTruoc(),
//                p.getThoiGianBaoHanh(),
//                p.getThuongHieu(),
                p.getKhuVucKho(),
                p.getSoLuongTon(),
                p.getTrangThai()
            });
        }
    }

}

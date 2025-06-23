package service;

import dao.ProductDAO;
import dao.ProductDAOImpl;
import entity.Product;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProductService implements ProductDAO {

    private Connection conn;

    public int getProductCountService() {
        return getProductCount();
    }

    public ProductService(Connection conn) {
        this.conn = conn;
    }

    public List<Product> getProductsByWarehouse(String warehouseId) {
        try {
            ProductDAOImpl dao = new ProductDAOImpl(conn);
            return dao.getProductsByWarehouse(Integer.parseInt(warehouseId));
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }

    public void loadProductToTable(JTable table, List<Product> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Product p : list) {
            model.addRow(new Object[]{
                p.getMaSanPham(),
                p.getTenSanPham(),
                p.getTenXuatXu(),
                p.getChipXuLy(),
                p.getDungLuongPin(),
                p.getTenKhuVuc(),
                p.getTrangThai()
            });
        }
    }

}

package service;

import dao.ProductDAO;

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

  

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.ProductAreaDAO;
import dao.ProductDAOImpl;
import dao.WarehouseManagementDAO;
import entity.Product;
import entity.ProductArea;
import entity.WarehouseManagement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import jdbc.ConnectionHelper;
import raven.toast.Notifications;
import zentech.application.form.other.WarehouseManagementForm;

public class WarehouseManagementService implements WarehouseManagementDAO {

    public void loadWarehouseManagementToTable(JTable table, List<WarehouseManagement> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (WarehouseManagement wh : list) {
            model.addRow(new Object[]{
                wh.getMaKhuVuc(),
                wh.getTenKhuVuc()});
        }
    }

    public boolean addCheck(WarehouseManagement wh) {
        List<WarehouseManagement> list = getAllWarehouses();
        for (WarehouseManagement w : list) {
            if (w.getTenKhuVuc().equalsIgnoreCase(wh.getTenKhuVuc())) {
                return false;
            }
        }
        return true;
    }

    public void Refresh(JTable warehouseTable, JTable productTable) {
        List<WarehouseManagement> list = getAllWarehouses();
        loadWarehouseManagementToTable(warehouseTable, list);

        DefaultTableModel productModel = (DefaultTableModel) productTable.getModel();
        productModel.setRowCount(0);
    }

    public void Show(JTable table,
            JTextField fieldmakho,
            JTextField fieldtenkho,
            JTextField fielghichu) {

        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            if (fieldmakho != null) {
                fieldmakho.setText(String.valueOf(table.getValueAt(selectedRow, 0)));
            }
            if (fieldtenkho != null) {
                fieldtenkho.setText(String.valueOf(table.getValueAt(selectedRow, 1)));
            }
            if (fielghichu != null) {
                fielghichu.setText(""); // Không có cột ghichu trong schema mới
            }
        }
    }

    public void updateWarehouseWithValidation(String tenkho, int id) {
        WarehouseManagement w = new WarehouseManagement();
        w.setMaKhuVuc(id);
        w.setTenKhuVuc(tenkho);
        int rs = wd.updateWarehouse(w);
        if (rs > 0) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Cập nhập thành công kho " + id + ".");
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Cập nhập không thành công.");
        }
    }

    public void Find(JTable table, JTextField search) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        String searchText = search.getText().trim();
        if (searchText.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }

    }

    public int getWareHouseCountService() {
        return getWareHouseCount();
    }

    WarehouseManagementDAO wd = new WarehouseManagementDAO() {
        @Override
        public boolean updateWarehouseWithValidation(WarehouseManagement wh) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    };

    ProductDAOImpl pd = new ProductDAOImpl() {
    };

    public void LoadDataKho(JTable tbl10) {
        DefaultTableModel model = (DefaultTableModel) tbl10.getModel();
        model.setRowCount(0);
        for (WarehouseManagement w : wd.getAllWarehouses()) {
            model.addRow(new Object[]{w.getMaKhuVuc(), w.getTenKhuVuc()});
        }
    }

    ProductAreaDAO p = new ProductAreaDAO();

    public void ShowProductBySelectKho(JTable tbl10, JTable tbl11) {
        int select = tbl10.getSelectedRow();
        if (select == -1) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tbl11.getModel();
        model.setRowCount(0);
        int id = (int) tbl10.getValueAt(select, 0);
        String tenkho = (String) tbl10.getValueAt(select, 1);

        try {
            for (ProductArea pa : p.getProductsByWarehouse(id)) {
                model.addRow(new Object[]{
                    pa.getP().getMaSanPham(),
                    pa.getP().getTenSanPham(),
                    pa.getP().getTenXuatXu(),
                    pa.getP().getChipXuLy(),
                    pa.getP().getDungLuongPin(),
                    pa.getW().getTenKhuVuc(),
                    pa.getSoluong(),
                    pa.getP().getTrangThai()
                });
            }
        } catch (Exception ex) {
            Logger.getLogger(WarehouseManagementForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean updateWarehouseWithValidation(WarehouseManagement wh) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

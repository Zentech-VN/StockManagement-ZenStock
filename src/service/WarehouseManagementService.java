/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.WarehouseManagementDAO;
import entity.WarehouseManagement;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import jdbc.ConnectionHelper;
import raven.toast.Notifications;

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

    public boolean updateWarehouseWithValidation(WarehouseManagement wh) {
        try {

            if (wh.getTenKhuVuc() == null || wh.getTenKhuVuc().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tên khu vực không được để trống.");
                return false;
            }


            String sql = "UPDATE khuvuc SET tenkhuvuc = ? WHERE makhuvuc = ?";
            java.sql.Connection conn = ConnectionHelper.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, wh.getTenKhuVuc());
            ps.setInt(2, wh.getMaKhuVuc());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi khi cập nhật kho.");
            return false;
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
}
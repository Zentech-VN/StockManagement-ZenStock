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
import raven.toast.Notifications;

public class WarehouseManagementService implements WarehouseManagementDAO {

    public void loadWarehouseManagementToTable(JTable table, List<WarehouseManagement> list) {
        list = getAllWarehouses();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (WarehouseManagement wh : list) {
            model.addRow(new Object[]{
                wh.getMaKhuVuc(),
                wh.getTenKhuVuc(),
                wh.getGhiChu(),});

        }
    }

    public void Refresh(JTable warehouseTable, JTable productTable) {
        List<WarehouseManagement> list = getAllWarehouses();
        DefaultTableModel warehouseModel = (DefaultTableModel) warehouseTable.getModel();
        warehouseModel.setRowCount(0);
        loadWarehouseManagementToTable(warehouseTable, list);

        DefaultTableModel productModel = (DefaultTableModel) productTable.getModel();
        productModel.setRowCount(0);
    }

    public void Show(JTable table,
            JTextField fieldmakho,
            JTextField fieldtenkho,
            JTextField fielghichu
    ) {

        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {

            fieldmakho.setText(String.valueOf(table.getValueAt(selectedRow, 0)));

            fieldtenkho.setText(String.valueOf(table.getValueAt(selectedRow, 1)));
            fielghichu.setText(String.valueOf(table.getValueAt(selectedRow, 2)));

        }
    }

    public void updateWarehouse(JTextField txtmakho, JTextField txttenkho, JTextField txtghichu) {
        try {
            WarehouseManagement wh = new WarehouseManagement();

            int maKhuVuc;
            try {
                maKhuVuc = Integer.parseInt(txtmakho.getText().trim());
                wh.setMaKhuVuc(maKhuVuc);
            } catch (NumberFormatException e) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Mã khu vực không hợp lệ");
                return;
            }

            wh.setTenKhuVuc(txttenkho.getText().trim());
            wh.setGhiChu(txtghichu.getText().trim());
            int ret = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn cập nhật thông tin khu vực này?", "Xác nhận cập nhật", JOptionPane.YES_NO_OPTION);
            if (ret == JOptionPane.YES_OPTION) {
                boolean result = updateWarehouse(wh); // Gọi hàm DAO
                if (result) {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Cập nhật thành công!");
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Cập nhật thất bại!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi trong quá trình cập nhật");
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

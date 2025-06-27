package service;

import dao.OSDAO;
import entity.OS;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import raven.toast.Notifications;

public class OSService implements OSDAO {

    private List<OS> os = new ArrayList<>();

    public List<OS> getAllOSService() {
        return os = getAllOS();
    }

    public void loadToTable(JTable tbl) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0); // Clear bảng

        List<OS> list = getAllOSService();
        for (OS o : list) {
            Object[] row = {
                o.getId(),
                o.getTen()
            };
            model.addRow(row);
        }
    }

    public void showSelectedOS(JTable tbl, JTextField txtMa, JTextField txtTen) {
        int selectedRow = tbl.getSelectedRow();
        if (selectedRow >= 0) {
            String ma = tbl.getValueAt(selectedRow, 0).toString();
            String ten = tbl.getValueAt(selectedRow, 1).toString();

            txtMa.setText(ma);
            txtMa.setEditable(false);
            txtTen.setText(ten);
            txtTen.setEditable(true);
        }
    }

    public void clearForm(JTextField txtMaKH1, JTextField txtTenKH1, JTable tblDanhSach) {
        txtMaKH1.setText("");
        txtTenKH1.setText("");

        txtMaKH1.setEditable(false);  // Mã luôn không sửa
        txtTenKH1.setEditable(true);
        tblDanhSach.clearSelection();
    }

    public boolean saveOS(JTextField txtTen, JTable tbl, JTextField txtMa) {
        String ten = txtTen.getText().trim();

        if (ten.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng nhập hệ điều hành!");
            return false;
        }

        // kiểm tra trùng tên
        if (isOSNameExists(ten)) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Hệ điều hành đã tồn tại!");
            return false;
        }

        OS os = new OS(0, ten); // 0 vì mã tự tăng

        boolean success = insertOS(os); // gọi DAO
        if (success) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Thêm hệ điều hành thành công!");
            loadToTable(tbl);
            clearForm(txtMa, txtTen, tbl);
            return true;
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Thêm hệ điều hành thất bại!");
            return false;
        }
    }

    public boolean deleteOS(JTextField txtMa, JTable tbl, JTextField txtTen) {
        String ma = txtMa.getText().trim();

        if (ma.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn hệ điều hành cần xóa!");
            return false;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc chắn muốn xóa hệ điều hành này?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return false;
        }

        int id = Integer.parseInt(ma);
        boolean success = deleteOSById(id); // gọi DAO

        if (success) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xóa hệ điều hành thành công!");
            loadToTable(tbl);
            clearForm(txtMa, txtTen, tbl);
            return true;
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Xóa thất bại hoặc hệ điều hành không tồn tại!");
            return false;
        }
    }

    public boolean updateOS(JTextField txtMa, JTextField txtTen, JTable tbl) {
        String maStr = txtMa.getText().trim();
        String tenMoi = txtTen.getText().trim();

        if (maStr.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn hệ điều hành cần sửa!");
            return false;
        }

        try {
            int id = Integer.parseInt(maStr);

            // dòng đang chọn
            int selectedRow = tbl.getSelectedRow();
            if (selectedRow < 0) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Vui lòng chọn dòng trên bảng!");
                return false;
            }

            String tenCu = tbl.getValueAt(selectedRow, 1).toString();

            if (tenMoi.equalsIgnoreCase(tenCu)) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Bạn chưa thay đổi hệ điều hành!");
                return false;
            }

            // kiểm tra trùng nhưng loại trừ chính nó
            if (isOSNameExists(tenMoi)) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Hệ điều hành đã tồn tại!");
                return false;
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc muốn cập nhật hệ điều hành?", "Xác nhận", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {
                return false;
            }

            boolean success = updateOSById(id, tenMoi);

            if (success) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Cập nhật thành công!");
                loadToTable(tbl);
                clearForm(txtMa, txtTen, tbl);
                return true;
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Cập nhật thất bại!");
            }
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Mã hệ điều hành không hợp lệ!");
        }

        return false;
    }

    public void Find(JTable jTable1, JTextField txt_Search) {
        DefaultTableModel ob = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        jTable1.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(txt_Search.getText()));
    }
}

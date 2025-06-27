package service;

import dao.BrandDAO;
import entity.Brand;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import raven.toast.Notifications;

public class BrandService implements BrandDAO {

    private List<Brand> brand = new ArrayList<>();

    public List<Brand> getAllBrandsService() {
        return brand = getAllBrands();
    }

    public void loadToTable(JTable tbl) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0); // Clear bảng

        List<Brand> list = getAllBrandsService();
        for (Brand b : list) {
            Object[] row = {
                b.getId(),
                b.getTen()
            };
            model.addRow(row);
        }
    }

    public void showSelectedBrand(JTable tbl, JTextField txtMa, JTextField txtTen) {
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

    public boolean saveBrand(JTextField txtTen, JTable tbl, JTextField txtMa) {
        String ten = txtTen.getText().trim();

        if (ten.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng nhập tên thương hiệu!");
            return false;
        }

        // kiểm tra trùng tên
        if (isBrandNameExists(ten)) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tên thương hiệu đã tồn tại!");
            return false;
        }

        Brand brand = new Brand(0, ten); // 0 vì mã tự tăng

        boolean success = insertBrand(brand); // gọi DAO
        if (success) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Thêm thương hiệu thành công!");
            loadToTable(tbl);
            clearForm(txtMa, txtTen, tbl);
            return true;
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Thêm thương hiệu thất bại!");
            return false;
        }
    }

    public boolean deleteBrand(JTextField txtMa, JTable tbl, JTextField txtTen) {
        String ma = txtMa.getText().trim();

        if (ma.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn thương hiệu cần xóa!");
            return false;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc chắn muốn xóa thương hiệu này?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return false;
        }

        int id = Integer.parseInt(ma);
        boolean success = deleteBrandById(id); // gọi DAO

        if (success) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xóa thương hiệu thành công!");
            loadToTable(tbl);
            clearForm(txtMa, txtTen, tbl);
            return true;
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Xóa thất bại hoặc thương hiệu không tồn tại!");
            return false;
        }
    }

    public boolean updateBrand(JTextField txtMa, JTextField txtTen, JTable tbl) {
        String maStr = txtMa.getText().trim();
        String tenMoi = txtTen.getText().trim();

        if (maStr.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn thương hiệu cần sửa!");
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
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Bạn chưa thay đổi tên thương hiệu!");
                return false;
            }

            // kiểm tra trùng nhưng loại trừ chính nó
            if (isBrandNameExists(tenMoi)) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tên thương hiệu đã tồn tại!");
                return false;
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc muốn cập nhật tên thương hiệu?", "Xác nhận", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {
                return false;
            }

            boolean success = updateBrandById(id, tenMoi);

            if (success) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Cập nhật thành công!");
                loadToTable(tbl);
                clearForm(txtMa, txtTen, tbl);
                return true;
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Cập nhật thất bại!");
            }
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Mã thương hiệu không hợp lệ!");
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

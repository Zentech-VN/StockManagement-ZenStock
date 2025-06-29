package service;

import com.formdev.flatlaf.FlatClientProperties;
import dao.ActivityDAO;
import dao.ClientDAO;
import entity.Cilent;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import raven.toast.Notifications;

public class ClientService {

    ClientDAO cld = new ClientDAO();

    public void editPlaceHolder(
            JTextField makh,
            JTextField tenkh,
            JTextField sdt,
            JTextField diachi,
            JTextField search
    ) {
        makh.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã khách hàng...");
        tenkh.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tên khách hàng...");
        diachi.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Địa chỉ...");
        sdt.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "0123456789...");
        search.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");

    }

    public void LoadDataTable(JTable danhsach) {
        String[] title = {"Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Email", "Số điện thoại", "Ngày tham gia"};
        DefaultTableModel model = new DefaultTableModel(title, 0);
        model.setRowCount(0);
        for (Cilent c : cld.getAllCilent()) {
            if (c.getTrangThai() == 1) {
                model.addRow(new Object[]{c.getMaKhacHang(), c.getTenKhacHang(), c.getDiaChi(), c.getEmail(), c.getSoDienThoai(), c.getNgayThamGia()});
            }
        }
        danhsach.setModel(model);
    }

    public void showDetail(
            JTable danhsach,
            JTextField makh,
            JTextField tenkh,
            JTextField sdt,
            JTextField diachi
    ) {
        int index = danhsach.getSelectedRow();
        int makh1 = (int) danhsach.getValueAt(index, 0);
        Cilent cl = cld.getAllMouse(makh1);
        makh.setText(String.valueOf(cl.getMaKhacHang()));
        tenkh.setText(cl.getTenKhacHang());
        diachi.setText(cl.getDiaChi());
        sdt.setText(cl.getSoDienThoai());
    }

    public boolean checkvalidate(
            JTextField tenkh,
            JTextField sdt,
            JTextField diachi,
            JTextField Email
    ) {
        String regexsdt = "^(0|\\+84)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-5]|9[0-9])[0-9]{7}$";
        String regexemail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (tenkh.getText().trim().isEmpty()) {

            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng nhập tên khách hàng");
            return false;
        }
        if (diachi.getText().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng nhập địa chỉ");
            return false;
        }
        if (Email.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng nhập Email.");
            return false;
        }
        if (!Email.getText().matches(regexemail)) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Email không đúng định dạng..");
            return false;
        }
        if (sdt.getText().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng nhập số điện thoại");
            return false;
        }
        if (!sdt.getText().matches(regexsdt)) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Số điện thoại không đúng định dạng");
            return false;
        } else {
            return true;
        }
    }

    public void add(
            JTextField tenkh,
            JTextField sdt,
            JTextField diachi,
            JTextField Email
    ) {
        String appCurrentUser = zentech.application.Application.getAppInstance().getCurrentUser();
        String ten = tenkh.getText();
        if (checkvalidate(tenkh, sdt, diachi, Email)) {
            int confrim = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thêm khách hàng", "Add", JOptionPane.YES_OPTION);
            if (confrim == JOptionPane.YES_OPTION) {
                Cilent cl = new Cilent();
                cl.setTenKhacHang(tenkh.getText());
                cl.setDiaChi(diachi.getText());
                cl.setEmail(Email.getText());
                cl.setSoDienThoai(sdt.getText());
                int rs = cld.addkhachhang(cl);
                if (rs > 0) {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Thêm thành công");
                    ActivityDAO.logActivity(appCurrentUser, "Thêm khách hàng: " + ten);
                }
            }
        }
    }

    public void delete(JTextField makh, JTextField tenkh) {
        String appCurrentUser = zentech.application.Application.getAppInstance().getCurrentUser();
        if (makh.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng nhập mã khách hàng muốn xóa");
            return;
        } else {
            try {
                int id = Integer.parseInt(makh.getText());
                String ten = tenkh.getText();
                int confrim = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa khách hàng có mã " + id, "Add", JOptionPane.YES_OPTION);
                if (confrim == JOptionPane.YES_OPTION) {
                    int rs = cld.delete(id);
                    if (rs > 0) {
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xóa thành công khách hàng có mã " + id);
                        ActivityDAO.logActivity(appCurrentUser, "Xóa khách hàng: " + ten);
                    }
                }
            } catch (Exception e) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Không phải số");
                return;
            }

        }
    }

    public void search(JTable danhsach, JTextField search) {
        DefaultTableModel ob = (DefaultTableModel) danhsach.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        danhsach.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(search.getText()));
    }

    public void update(JTextField makh, JTextField tenkh, JTextField sdt, JTextField diachi, JTextField Email) {
        String appCurrentUser = zentech.application.Application.getAppInstance().getCurrentUser();
        String ten = tenkh.getText();
        if (makh.getText().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng nhập mã khách hàng muốn cập nhập");
            return;
        } else {
            if (checkvalidate(tenkh, sdt, diachi, Email)) {
                try {
                    int id = Integer.parseInt(makh.getText());
                    int confrim = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn cập nhập khách hàng có mã " + id, "Update", JOptionPane.YES_OPTION);
                    if (confrim == JOptionPane.YES_OPTION) {
                        Cilent cl = new Cilent();
                        cl.setMaKhacHang(id);
                        cl.setTenKhacHang(tenkh.getText());
                        cl.setDiaChi(diachi.getText());
                        cl.setEmail(Email.getText());
                        cl.setSoDienThoai(sdt.getText());
                        int rs = cld.Update(cl);
                        if (rs > 0) {
                            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Cập nhập thành công cho khách hàng có mã " + id);
                            ActivityDAO.logActivity(appCurrentUser, "Cập nhật khách hàng: " + ten);
                        }
                    }
                } catch (Exception e) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Không phải số");
                }
            }
        }
    }

    public void xuatexcel(JTable danhsach) {
        try (FileWriter fw = new FileWriter("D:\\inventory\\StockManagement\\Danhsach_khachhang\\khachhang2.xlsx")) {
            TableModel model = danhsach.getModel();

            // Ghi tiêu đề cột
            for (int i = 0; i < model.getColumnCount(); i++) {
                fw.write(model.getColumnName(i));
                if (i < model.getColumnCount() - 1) {
                    fw.write(" | ");
                }
            }
            fw.write("\n");

            // Ghi dữ liệu
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    fw.write(model.getValueAt(i, j).toString());
                    if (j < model.getColumnCount() - 1) {
                        fw.write("            | ");
                    }
                }
                fw.write("\n");
            }

            fw.flush();
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, " Xuất Excel thành công!");

        } catch (IOException e) {

            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, " Lỗi khi xuất file: " + e.getMessage());
        }
    }

    public int getClientCountService() {
        return cld.getClientCount();
    }
}

package service;

import com.formdev.flatlaf.FlatClientProperties;
import dao.ClientDAO;
import entity.Client;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import entity.Client;
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

    public List<Client> getAllClientService() {
        return cld.getAllCilent();
    }

    public boolean checkvalidate(
            JTextField tenkh,
            JTextField sdt,
            JTextField diachi,
            JTextField Email,
            String trangthai
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
        }
        if (trangthai == "") {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn trạng thái");
            return false;
        } else {
            return true;
        }
    }

    public boolean add(
            JTextField tenkh,
            JTextField sdt,
            JTextField diachi,
            JTextField Email,
            String trangthai
    ) {
        boolean check = false;
        String appCurrentUser = zentech.application.Application.getAppInstance().getCurrentUser();
        String ten = tenkh.getText();
        if (checkvalidate(tenkh, sdt, diachi, Email, trangthai)) {
            int confrim = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thêm khách hàng", "Add", JOptionPane.YES_OPTION);
            if (confrim == JOptionPane.YES_OPTION) {
                Client cl = new Client();
                cl.setTenKhacHang(tenkh.getText());
                cl.setDiaChi(diachi.getText());
                cl.setEmail(Email.getText());
                cl.setSoDienThoai(sdt.getText());
                cl.setTrangThai(trangthai);
                int rs = cld.addkhachhang(cl);
                if (rs > 0) {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Thêm thành công");
                    check = true;
                }
            }
        }
        if (check == true) {
            return true;
        } else {
            return false;
        }
    }

    public void delete(int makh, String tenkh) {
        String appCurrentUser = zentech.application.Application.getAppInstance().getCurrentUser();
        int confrim = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa khách hàng có mã " + makh, "Add", JOptionPane.YES_OPTION);
        if (confrim == JOptionPane.YES_OPTION) {
            int rs = cld.delete(makh);
            if (rs > 0) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xóa thành công khách hàng có mã " + makh);
            }
        }

    }

    public void search(JTable danhsach, JTextField search) {
        DefaultTableModel ob = (DefaultTableModel) danhsach.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        danhsach.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(search.getText()));
    }

    public boolean update(int makh, JTextField tenkh, JTextField sdt, JTextField diachi, JTextField Email, String trangthai) {
        boolean check = false;
        if (checkvalidate(tenkh, sdt, diachi, Email, trangthai)) {
            String appCurrentUser = zentech.application.Application.getAppInstance().getCurrentUser();
            String ten = tenkh.getText();

            try {
                int confrim = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn cập nhật khách hàng có mã " + makh, "Update", JOptionPane.YES_OPTION);
                if (confrim == JOptionPane.YES_OPTION) {
                    Client cl = new Client();
                    cl.setMaKhacHang(makh);
                    cl.setTenKhacHang(tenkh.getText());
                    cl.setDiaChi(diachi.getText());
                    cl.setEmail(Email.getText());
                    cl.setSoDienThoai(sdt.getText());
                    cl.setTrangThai(trangthai);
                    int rs = cld.Update(cl);
                    if (rs > 0) {
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Cập nhật thành công cho khách hàng có mã " + makh);
                        check = true;
                    }
                }
            } catch (Exception e) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Không phải số");
            }
        }
        if (check == true) {
            return true;
        } else {
            return false;
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

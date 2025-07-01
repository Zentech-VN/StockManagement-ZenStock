package service;

import dao.DestructionReleaseNoteDAO;
import entity.DestructionReleaseNote;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import raven.toast.Notifications;
import javax.swing.JComboBox;
import zentech.application.dialog.DestructionReleaseNoteUpdateDialog;

public class DestructionReleaseNoteService implements DestructionReleaseNoteDAO {

    public void loadPhieuHuyToTable(JTable table) {
        List<DestructionReleaseNote> list = getData();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ

        for (DestructionReleaseNote d : list) {
            model.addRow(new Object[]{
                d.getId(),
                d.getCreator(),
                d.getDate(),
                d.getStatus()
            });
        }
    }

    public void updateData(int id, JTextArea txtAreaLyDo, JComboBox cmoTrangThai) {
        String lyDo = txtAreaLyDo.getText().trim();
        String trangThai = (String) cmoTrangThai.getSelectedItem();

        boolean result = new DestructionReleaseNoteService().updatePhieuXuatHuy(id, lyDo, trangThai);
        if (result) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Cập nhật phiếu xuất hủy thành công!");
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Cập nhật thất bại!");
        }
    }

    public boolean deletePhieu(int id) {
        return deletePhieuXuatHuyById(id);
    }
}

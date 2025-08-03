package zentech.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import dao.UserRightsDAO;
import entity.NhomQuyen;
import java.awt.Window;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;
import zentech.application.dialog.UserRightsAddDialog;
import zentech.application.dialog.UserRightsDetailsDialog;
import zentech.application.dialog.UserRightsUpdateDialog;

public class UserRightsForm extends javax.swing.JPanel {

    UserRightsDAO urd = new UserRightsDAO();

    public UserRightsForm() {
        initComponents();
        initalUI(tblRole);
        LoadDataTable();
    }

    private void initalUI(JTable table) {

        JScrollPane scroll = (JScrollPane) table.getParent().getParent();
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Table.background;"
                + "track:$Table.background;"
                + "trackArc:999");

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        table.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

    }

    public void LoadDataTable() {
        DefaultTableModel model = (DefaultTableModel) tblRole.getModel();
        model.setRowCount(0);
        for (NhomQuyen nq : urd.getAllNhomQuyen()) {
            model.addRow(new Object[]{nq.getManhomquyen(), nq.getTennhomquyen()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        txtSearch = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnDetails = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRole = new javax.swing.JTable();

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            new String[]{
                ""
            }
        ));

        crazyPanel2.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "JTextField.placeholderText=Search;background:@background",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][][][]",
            "",
            new String[]{
                "width 400"
            }
        ));
        crazyPanel2.add(txtSearch);

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnAdd);

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnUpdate);

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setText("Xoá");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnDelete);

        btnDetails.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDetails.setText("Chi tiết");
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnDetails);

        crazyPanel1.add(crazyPanel2);

        tblRole.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhóm quyền", "Tên nhóm quyền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRole.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblRole);
        if (tblRole.getColumnModel().getColumnCount() > 0) {
            tblRole.getColumnModel().getColumn(0).setResizable(false);
            tblRole.getColumnModel().getColumn(0).setPreferredWidth(1);
            tblRole.getColumnModel().getColumn(1).setResizable(false);
            tblRole.getColumnModel().getColumn(1).setPreferredWidth(50);
        }

        crazyPanel1.add(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Window parent = SwingUtilities.getWindowAncestor(this);
        UserRightsAddDialog userRightsAddDialog = new UserRightsAddDialog(parent, this);
        userRightsAddDialog.setVisible(true);
        LoadDataTable();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int select = tblRole.getSelectedRow();
        if (select == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn nhóm quyền muốn sửa!");
            return;
        }
        int id = (int) tblRole.getValueAt(select, 0);
        Window parent = SwingUtilities.getWindowAncestor(this);
        UserRightsUpdateDialog userRightsUpdateDialog = new UserRightsUpdateDialog(parent, this, id);
        userRightsUpdateDialog.setVisible(true);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int select = tblRole.getSelectedRow();
        if (select == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn nhóm quyền muốn sửa!");
            return;
        }
        int id = (int) tblRole.getSelectedRow();
        try {
            int result = urd.xoaNhomQuyen(id);
            if (result == 1) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS,
                        Notifications.Location.TOP_CENTER, "Đã xoá nhóm quyền!");
                LoadDataTable();
            } else if (result == -1) {

                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Không thể xoá vì vẫn còn tài khoản đang thuộc nhóm này.\n"
                        + "Hãy chuyển các tài khoản sang nhóm khác trước.");
            } else {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Không tìm thấy nhóm để xoá.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Lỗi khi xoá: " + ex.getMessage());
        }
        LoadDataTable();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        int select = tblRole.getSelectedRow();
        if (select == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER,
                    "Vui lòng chọn nhóm quyền muốn xem chi tiết!");
            return;
        }
        int id = (int) tblRole.getValueAt(select, 0);   // <-- LẤY MÃ NHÓM Ở CỘT 0
        Window parent = SwingUtilities.getWindowAncestor(this);
        UserRightsDetailsDialog dlg = new UserRightsDetailsDialog(parent, this, id);
        dlg.setVisible(true);
    }//GEN-LAST:event_btnDetailsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnUpdate;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblRole;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

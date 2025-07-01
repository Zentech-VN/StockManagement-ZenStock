/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package zentech.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;
import service.ClientService;
import sun.tools.jconsole.inspector.TableSorter;
import zentech.application.dialog.CustomerManagementAddDialog;
import zentech.application.dialog.CustomerManagementUpdateDialog;

/**
 *
 * @author duong
 */
public class CustomerManagement extends javax.swing.JPanel {

    ClientService cls = new ClientService();

    public CustomerManagement() {
        initComponents();
        initUI(tblDanhSach);
        cls.LoadDataTable(tblDanhSach);
        ///d

    }

    public void initUI(JTable table) {
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        table.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        jTextField1 = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnCapNhap = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        btnXuatExcel = new javax.swing.JButton();

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill][grow 0]",
            null
        ));

        crazyPanel2.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "",
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
            "[]push[][]",
            "[fill]",
            new String[]{
                "width 300"
            }
        ));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        crazyPanel2.add(jTextField1);

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnLamMoi);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnThem);

        btnCapNhap.setText("Cập nhập");
        btnCapNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhapActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnCapNhap);

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnXoa);

        crazyPanel1.add(crazyPanel2);

        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng ", "Tên khách hàng ", "Địa chỉ", "Số điện thoại"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDanhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSach);

        crazyPanel1.add(jScrollPane1);

        btnXuatExcel.setText("Xuất Excel");
        btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelActionPerformed(evt);
            }
        });
        crazyPanel1.add(btnXuatExcel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMouseClicked

    }//GEN-LAST:event_tblDanhSachMouseClicked

    private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelActionPerformed
        // TODO add your handling code here:
        int confrim = JOptionPane.showConfirmDialog(this, "Bạn muốn xuất danh sách excel?", "Xuất excel", JOptionPane.YES_OPTION);
        if (confrim == JOptionPane.YES_OPTION) {
            cls.xuatexcel(tblDanhSach);
        }
    }//GEN-LAST:event_btnXuatExcelActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        cls.LoadDataTable(tblDanhSach);
        jTextField1.setText("");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        cls.search(tblDanhSach, jTextField1);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        java.awt.Window parent = javax.swing.SwingUtilities.getWindowAncestor(this);
        CustomerManagementAddDialog c = new CustomerManagementAddDialog(parent, null);
        c.setVisible(true);
        cls.LoadDataTable(tblDanhSach);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhapActionPerformed
        // TODO add your handling code here:

        int select = tblDanhSach.getSelectedRow();
        if (select == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn khách hàng để cập nhập.");
            return;
        }
        int id = (int) tblDanhSach.getValueAt(select, 0);
        String ten = (String) tblDanhSach.getValueAt(select, 1);
        String diachi = (String) tblDanhSach.getValueAt(select, 2);
        String email = (String) tblDanhSach.getValueAt(select, 3);
        String sdt = (String) tblDanhSach.getValueAt(select, 4);
        java.awt.Window parent = javax.swing.SwingUtilities.getWindowAncestor(this);
        CustomerManagementUpdateDialog c = new CustomerManagementUpdateDialog(parent, null, id, tblDanhSach);
        c.setVisible(true);
        cls.LoadDataTable(tblDanhSach);
    }//GEN-LAST:event_btnCapNhapActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int select = tblDanhSach.getSelectedRow();
        if (select == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn khách hàng để xóa.");
            return;
        }
        int id = (int) tblDanhSach.getValueAt(select, 0);
        String ten = (String) tblDanhSach.getValueAt(select, 1);
        cls.delete(id, ten);
        cls.LoadDataTable(tblDanhSach);
    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhap;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuatExcel;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblDanhSach;
    // End of variables declaration//GEN-END:variables
}

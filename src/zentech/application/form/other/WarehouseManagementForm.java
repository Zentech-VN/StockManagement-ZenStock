/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package zentech.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import dao.WarehouseDAO;
import entity.Warehouse;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;
import service.WarehouseService;
import zentech.application.dialog.CustomerManagementAddDialog;
import zentech.application.dialog.WarehouseManagementUpdateDialog;

public class WarehouseManagementForm extends javax.swing.JPanel {

    WarehouseService ws = new WarehouseService();
    WarehouseDAO wd = new WarehouseDAO() {
        @Override
        public boolean updateWarehouseWithValidation(Warehouse wh) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    };

    public WarehouseManagementForm() {
        initComponents();

        jTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        ws.LoadDataKho(tbl10);
        initUI(tbl10, tblSanPham);
    }

    public void initUI(JTable table, JTable table2) {

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        table.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

        table2.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        table2.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel4 = new raven.crazypanel.CrazyPanel();
        jTextField1 = new javax.swing.JTextField();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbl10 = new javax.swing.JTable();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();

        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][grow 0][fill]",
            null
        ));

        crazyPanel4.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[fill]",
            "",
            new String[]{
                "width 500"
            }
        ));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        crazyPanel4.add(jTextField1);

        crazyPanel1.add(crazyPanel4);

        crazyPanel2.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets",
            "[fill][fill][fill][fill]",
            "[fill]",
            null
        ));

        jButton5.setText("Làm mới");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        crazyPanel2.add(jButton5);

        jButton6.setText("Thêm");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        crazyPanel2.add(jButton6);

        jButton7.setText("Cập nhập");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        crazyPanel2.add(jButton7);

        jButton8.setText("Xóa");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        crazyPanel2.add(jButton8);

        crazyPanel1.add(crazyPanel2);

        tbl10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã kho", "Tên kho"
            }
        ));
        tbl10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl10MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbl10);

        crazyPanel1.add(jScrollPane10);

        crazyPanel3.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[fill]",
            new String[]{
                "width 700"
            }
        ));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên sản phẩm", "Thương hiệu", "Giá bán", "Hệ điều hành", "Xuất xứ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.getTableHeader().setReorderingAllowed(false);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        crazyPanel3.add(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(crazyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int select = tbl10.getSelectedRow();
        if (select == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn kho muốn cập nhập.");
            return;
        }
        int id = (int) tbl10.getValueAt(select, 0);
        java.awt.Window parent = javax.swing.SwingUtilities.getWindowAncestor(this);
        WarehouseManagementUpdateDialog w = new WarehouseManagementUpdateDialog(parent, null, id);
        w.setVisible(true);
        ws.LoadDataKho(tbl10);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        ws.Find(tbl10, jTextField1);
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void tbl10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl10MouseClicked
        // TODO add your handling code here:
        ws.ShowProductBySelectKho(tbl10, tblSanPham);
    }//GEN-LAST:event_tbl10MouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jTextField1.setText("");
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        java.awt.Window parent = javax.swing.SwingUtilities.getWindowAncestor(this);
        CustomerManagementAddDialog w = new CustomerManagementAddDialog(parent, null);
        w.setVisible(true);
        ws.LoadDataKho(tbl10);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        int select = tbl10.getSelectedRow();
        if (select == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn kho muốn xóa.");
            return;
        }
        int id = (int) tbl10.getValueAt(select, 0);
        int confrim = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa kho có mã " + id + ".", "Delete", JOptionPane.YES_NO_OPTION);
        if (confrim == JOptionPane.YES_OPTION) {
            boolean rs = wd.deleteWarehouseById(id);
            if (rs == true) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xóa thành công kho có mã " + id + ".");
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Xóa kho không thành công");
            }
        }
        ws.LoadDataKho(tbl10);
    }//GEN-LAST:event_jButton8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private raven.crazypanel.CrazyPanel crazyPanel4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tbl10;
    private javax.swing.JTable tblSanPham;
    // End of variables declaration//GEN-END:variables
}

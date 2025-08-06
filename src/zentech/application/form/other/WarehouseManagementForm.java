package zentech.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import dao.UserRightsDAO;
import dao.WarehouseDAO;
import entity.ChiTietQuyen;
import entity.Employee;
import entity.Warehouse;
import java.awt.Component;
import java.util.List;
import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import raven.toast.Notifications;
import service.WarehouseManagementService;

import zentech.application.dialog.WarehouseManagementAddDialog;
import zentech.application.dialog.WarehouseManagementUpdateDialog;

public class WarehouseManagementForm extends javax.swing.JPanel {

    WarehouseManagementService ws = new WarehouseManagementService();
    WarehouseDAO wd = new WarehouseDAO() {
        @Override
        public boolean updateWarehouseWithValidation(Warehouse wh) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    };
    private UserRightsDAO urd = new UserRightsDAO();
        private Employee CurrentAcc;

    public WarehouseManagementForm(Employee acc) {
        initComponents();
        this.CurrentAcc = acc;
        jTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        ws.LoadDataKho(tbl10);
        initUITable1(tbl10);
        initUITable2(tblSanPham);
        load();
    }
    
    public boolean check(List<ChiTietQuyen> list, String hanhdong, String machucnang) {
        for (ChiTietQuyen chitietquyen : list) {
            if (chitietquyen.getHanhdong() != null && chitietquyen.getHanhdong().equals(hanhdong)
                    && chitietquyen.getDanhmuc_chucnang().getMachucnang() != null && chitietquyen.getDanhmuc_chucnang().getMachucnang().equals(machucnang)) {
                return true;
            }
        }
        return false;
    }

    public void load() {
        List<ChiTietQuyen> list = urd.getALLCTQbyMaNHomQuyen(this.CurrentAcc.getAcc().getManhomquyen());
        if (check(list, "create", "khuvuckho") == false) {
            jButton6.setEnabled(false);
        }
        if (check(list, "delete", "khuvuckho") == false) {
            jButton8.setEnabled(false);
        }
        if (check(list, "update", "khuvuckho") == false) {
            jButton7.setEnabled(false);
        }
    }

    public void initUITable1(JTable table) {

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        table.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

        table.setDefaultRenderer(Object.class, getAlignmentCellRender(table.getDefaultRenderer(Object.class), false));
    }

    public void initUITable2(JTable table) {

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        table.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

        table.setDefaultRenderer(Object.class, getAlignmentCellRender(table.getDefaultRenderer(Object.class), false));
    }

    private TableCellRenderer getAlignmentCellRender(TableCellRenderer oldRender, boolean header) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = oldRender.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (com instanceof JLabel) {
                    JLabel label = (JLabel) com;
                    if (column == 2 || column == 3 || column == 4) {
                        label.setHorizontalAlignment(SwingConstants.CENTER); //Căn giữa
                    } else if (column == 0 || column == 1 || column == 5) {
                        label.setHorizontalAlignment(SwingConstants.LEFT); //Căn trái
                    } else if (column == 6) {
                        label.setHorizontalAlignment(SwingConstants.RIGHT); //Căn phải
                    } else {
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                    }
                }
                return com;
            }
        };
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
        lblSp = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();

        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][grow 0][fill]",
            null
        ));

        crazyPanel4.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "",
            new String[]{
                ""
            }
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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl10MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbl10);
        if (tbl10.getColumnModel().getColumnCount() > 0) {
            tbl10.getColumnModel().getColumn(1).setResizable(false);
        }

        crazyPanel1.add(jScrollPane10);

        crazyPanel3.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            new String[]{
                "width 700"
            }
        ));

        lblSp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSp.setText("Sản phẩm trong kho: ");
        crazyPanel3.add(lblSp);

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sản phẩm", "Thương hiệu", "Giá bán", "Hệ điều hành", "Xuất xứ", "Số lượng", "Trạng thái"
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
        String ten = (String) tbl10.getValueAt(select, 1);
        java.awt.Window parent = javax.swing.SwingUtilities.getWindowAncestor(this);
        WarehouseManagementUpdateDialog w = new WarehouseManagementUpdateDialog(parent, null, id, ten);
        w.setVisible(true);
        ws.LoadDataKho(tbl10);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        ws.Find(tbl10, jTextField1);
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void tbl10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl10MouseClicked
        // TODO add your handling code here:
        lblSp.setText("Sản phẩm có trong kho:" + "");
        ws.ShowProductBySelectKho(tbl10, tblSanPham, lblSp);
    }//GEN-LAST:event_tbl10MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jTextField1.setText("");
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        java.awt.Window parent = javax.swing.SwingUtilities.getWindowAncestor(this);
        WarehouseManagementAddDialog w = new WarehouseManagementAddDialog(parent, null);
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
    private javax.swing.JLabel lblSp;
    private javax.swing.JTable tbl10;
    private javax.swing.JTable tblSanPham;
    // End of variables declaration//GEN-END:variables
}

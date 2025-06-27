package zentech.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Component;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import raven.toast.Notifications;
import zentech.application.dialog.ProductDetailsDialog;

public class ImportForm extends javax.swing.JPanel {

    public ImportForm() {
        initComponents();
        initalUI(tblSanPham, tblPhieuXuat);
    }

    private void initalUI(JTable tableProduct, JTable tablePhieuXuat) {
        tblSanPham.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        tblSanPham.setRowHeight(30);
        tblSanPham.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18));

        tblPhieuXuat.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        tblPhieuXuat.setRowHeight(30);
        tblPhieuXuat.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18));

        JScrollPane scroll = (JScrollPane) tableProduct.getParent().getParent();
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Table.background;"
                + "track:$Table.background;"
                + "trackArc:999");

        tableProduct.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        tableProduct.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

        tableProduct.getTableHeader().setDefaultRenderer(getAlignmentCellRender(tableProduct.getTableHeader().getDefaultRenderer(), true));
        tableProduct.setDefaultRenderer(Object.class, getAlignmentCellRender(tableProduct.getDefaultRenderer(Object.class), false));

        JScrollPane scroll2 = (JScrollPane) tablePhieuXuat.getParent().getParent();
        scroll2.setBorder(BorderFactory.createEmptyBorder());
        scroll2.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Table.background;"
                + "track:$Table.background;"
                + "trackArc:999");

        tablePhieuXuat.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        tablePhieuXuat.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

        tablePhieuXuat.getTableHeader().setDefaultRenderer(getAlignmentCellRender(tablePhieuXuat.getTableHeader().getDefaultRenderer(), true));
        tablePhieuXuat.setDefaultRenderer(Object.class, getAlignmentCellRender(tablePhieuXuat.getDefaultRenderer(Object.class), false));
    }

    private TableCellRenderer getAlignmentCellRender(TableCellRenderer oldRender, boolean header) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = oldRender.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (com instanceof JLabel) {
                    JLabel label = (JLabel) com;
                    if (column == 0 || column == 1 || column == 2 || column == 3) {
                        label.setHorizontalAlignment(SwingConstants.LEFT); //Căn trái
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
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaPhieuXuat = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSearch2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuXuat = new javax.swing.JTable();
        crazyPanel8 = new raven.crazypanel.CrazyPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        crazyPanel6 = new raven.crazypanel.CrazyPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        crazyPanel7 = new raven.crazypanel.CrazyPanel();
        jButton5 = new javax.swing.JButton();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        crazyPanel4 = new raven.crazypanel.CrazyPanel();
        txtSearch1 = new javax.swing.JTextField();
        btnAdd2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        crazyPanel9 = new raven.crazypanel.CrazyPanel();
        jButton8 = new javax.swing.JButton();

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill][grow 0][grow 0][grow 0]",
            new String[]{
                ""
            }
        ));

        crazyPanel2.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            null
        ));
        crazyPanel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill",
            "[][]",
            "[][][]",
            new String[]{
                "width 150",
                "width 450",
                "width 150",
                "width 450",
                "",
                "width 450"
            }
        ));

        jLabel1.setText("Mã phiếu xuất");
        crazyPanel2.add(jLabel1);

        txtMaPhieuXuat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel2.add(txtMaPhieuXuat);

        jLabel2.setText("Người tạo phiếu");
        crazyPanel2.add(jLabel2);

        txtSearch2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel2.add(txtSearch2);

        jLabel5.setText("Nhà cung cấp");
        crazyPanel2.add(jLabel5);

        crazyPanel2.add(jComboBox1);

        crazyPanel1.add(crazyPanel2);

        tblPhieuXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên sản phẩm", "Số lượng", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieuXuat.getTableHeader().setReorderingAllowed(false);
        tblPhieuXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuXuatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieuXuat);
        if (tblPhieuXuat.getColumnModel().getColumnCount() > 0) {
            tblPhieuXuat.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblPhieuXuat.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblPhieuXuat.getColumnModel().getColumn(2).setPreferredWidth(40);
            tblPhieuXuat.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        crazyPanel1.add(jScrollPane1);

        crazyPanel8.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel8.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill",
            "[fill][fill]",
            "[grow 0]",
            new String[]{
                "height 50",
                "height 50",
                "height 50",
                "height 50",
                ""
            }
        ));

        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton6.setText("Sửa số lượng");
        crazyPanel8.add(jButton6);

        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton7.setText("Xoá sản phẩm");
        crazyPanel8.add(jButton7);

        crazyPanel1.add(crazyPanel8);

        crazyPanel6.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            null
        ));
        crazyPanel6.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill",
            "[grow 0][fill]",
            "[fill]",
            null
        ));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Tổng tiền:");
        crazyPanel6.add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("0 đ");
        crazyPanel6.add(jLabel4);

        crazyPanel1.add(crazyPanel6);

        crazyPanel7.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel7.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill",
            "[fill]",
            "[]",
            new String[]{
                "height 100"
            }
        ));

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton5.setText("Nhập hàng");
        crazyPanel7.add(jButton5);

        crazyPanel1.add(crazyPanel7);

        crazyPanel3.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel3.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill][grow 0]",
            new String[]{
                ""
            }
        ));

        crazyPanel4.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "JTextField.placeholderText=Search;background:@background",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel4.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[]",
            "",
            new String[]{
                "width 350"
            }
        ));

        txtSearch1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel4.add(txtSearch1);

        btnAdd2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd2.setText("Chi tiết");
        btnAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd2ActionPerformed(evt);
            }
        });
        crazyPanel4.add(btnAdd2);

        crazyPanel3.add(crazyPanel4);

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên sản phẩm", "Số lượng", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        jScrollPane2.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(40);
            tblSanPham.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        crazyPanel3.add(jScrollPane2);

        crazyPanel9.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel9.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill",
            "[fill]",
            "[]",
            new String[]{
                "height 50"
            }
        ));

        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton8.setText("Thêm");
        crazyPanel9.add(jButton8);

        crazyPanel3.add(crazyPanel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(crazyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblPhieuXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuXuatMouseClicked

    }//GEN-LAST:event_tblPhieuXuatMouseClicked

    private void btnAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd2ActionPerformed
        Window parent = SwingUtilities.getWindowAncestor(this);
        int modelRow = tblSanPham.getSelectedRow();
        if (modelRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Hãy chọn sản phẩm muốn xem chi tiết");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        String maSanPham = model.getValueAt(modelRow, 0).toString();

        ProductDetailsDialog productDetailsDialog = new ProductDetailsDialog(parent, this, maSanPham);
        productDetailsDialog.setLocationRelativeTo(parent);
        productDetailsDialog.setVisible(true);
    }//GEN-LAST:event_btnAdd2ActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSanPhamMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd2;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private raven.crazypanel.CrazyPanel crazyPanel4;
    private raven.crazypanel.CrazyPanel crazyPanel6;
    private raven.crazypanel.CrazyPanel crazyPanel7;
    private raven.crazypanel.CrazyPanel crazyPanel8;
    private raven.crazypanel.CrazyPanel crazyPanel9;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblPhieuXuat;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtMaPhieuXuat;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtSearch2;
    // End of variables declaration//GEN-END:variables
}

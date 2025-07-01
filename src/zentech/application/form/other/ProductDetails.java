package zentech.application.form.other;

import dao.ActivityDAO;
import entity.Product;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import dao.ProductDetailsDAO;
import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import raven.toast.Notifications;
import service.ProductDetailsService;
import service.ProductServiceMain;
import zentech.application.dialog.ProductDetailsAddDialog;
import zentech.application.dialog.ProductDetailsDialogMain;
import zentech.application.dialog.ProductDetailsUpdateDialog;


public class ProductDetails extends javax.swing.JPanel {

    public static boolean addCheck(String tenSanPham, String hinhAnh, String camTruoc, String camSau, String gia, String chipXuLy, String pin, String manHinh, String baoHanh, int idThuongHieu, int idOS, int idXuatXu, String trangThai) {
        ProductDetailsService service = new ProductDetailsService();
        return service.addCheck(tenSanPham, hinhAnh, camTruoc, camSau, gia, chipXuLy, pin, manHinh, Integer.parseInt(baoHanh), idThuongHieu, idOS, idXuatXu, trangThai);
    }
    private ProductDetailsDAO productdetailsDAO = new ProductDetailsDAO();
    private ProductServiceMain productService;
    private ProductDetailsService sv = new ProductDetailsService();
    public ProductDetails() {
        initComponents();
        loadTableProductDetails();
    }

    public void loadTableProductDetails() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);

        List<Product> list = productdetailsDAO.getAllProductWithImei();
        if (list != null) {
            for (Product p : list) {
                model.addRow(new Object[]{
                    p.getMaSanPham(),
                    p.getMaimei(),
                    p.getGia(),
                    p.getTrangThai()
                });
            }
        }
    }

    private int getSelectedModelRow() {
        int viewIndex = tblSanPham.getSelectedRow();
        return viewIndex == -1 ? -1 : tblSanPham.convertRowIndexToModel(viewIndex);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        txtSearch = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAdd1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1277, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 870, Short.MAX_VALUE)
        );

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

        txtSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
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

        btnAdd1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd1.setText("Chi tiết");
        btnAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd1ActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnAdd1);

        crazyPanel1.add(crazyPanel2);

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Mã imei", "Giá bán", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
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

        crazyPanel1.add(jScrollPane1);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Làm mới\n");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1283, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Window parent = SwingUtilities.getWindowAncestor(this);
        ProductDetailsAddDialog dialog = new ProductDetailsAddDialog(parent, this);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        Window parent = SwingUtilities.getWindowAncestor(this);
        int modelRow = getSelectedModelRow();
        if (modelRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Hãy chọn sản phẩm muốn chỉnh sửa");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        String maSanPham = model.getValueAt(modelRow, 0).toString();

        Window parentWindow = SwingUtilities.getWindowAncestor(this);
        ProductDetailsUpdateDialog dialog = new ProductDetailsUpdateDialog(parentWindow, this, maSanPham);
        dialog.setVisible(true);


    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int modelRow = getSelectedModelRow();
        String appCurrentUser = zentech.application.Application.getAppInstance().getCurrentUser();

        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        if (modelRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Hãy chọn sản phẩm muốn xoá");
            return;
        }

        int maSanPham = Integer.parseInt(model.getValueAt(modelRow, 0).toString());
        String tenSanPham = model.getValueAt(modelRow, 1).toString(); // Giả sử cột 1 là tên sản phẩm

        int ret = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá sản phẩm: " + tenSanPham + "?", "Xoá sản phẩm", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            ActivityDAO.logActivity(appCurrentUser, "Xoá sản phẩm: " + tenSanPham);
            this.productService = new ProductServiceMain();

            if (productService.deleteProductService(maSanPham)) {
                loadTableProductDetails(); // reload bảng
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        loadTableProductDetails();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
        Window parent = SwingUtilities.getWindowAncestor(this);
        int modelRow = getSelectedModelRow();
        if (modelRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Hãy chọn sản phẩm muốn xem chi tiết");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        String maSanPham = model.getValueAt(modelRow, 0).toString();

        ProductDetailsDialogMain productDetailsDialog = new ProductDetailsDialogMain(parent, this, maSanPham);
        productDetailsDialog.setLocationRelativeTo(parent);
        productDetailsDialog.setVisible(true);
    }//GEN-LAST:event_btnAdd1ActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed

    }//GEN-LAST:event_txtSearchKeyPressed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
       sv.Find(tblSanPham, txtSearch);
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAdd1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

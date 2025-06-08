package zentech.application.form.other;

import entity.Product;
import entity.ProductView;
import java.awt.Window;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;
import service.EmployeeService;
import service.ProductServiceMain;
import zentech.application.dialog.ProductAddDialog;
import zentech.application.dialog.ProductUpdateDialog;
import static zentech.application.form.other.WarehouseManagementForm.service;

public class ProductForm extends javax.swing.JPanel {

    private ProductServiceMain productService;

    public ProductForm() {
        initComponents();
        loadProductViewData();
        tblProduct.setDefaultEditor(Object.class, null);
    }

    public void loadProductViewData() {
        if (this.productService == null) {
            this.productService = new ProductServiceMain();
        }

        DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();
        model.setRowCount(0);

        List<Product> product = productService.getAllProductViewService();
        if (product != null) {

            for (Product x : product) {
                model.addRow(new Object[]{
                    x.getMaSanPham(),
                    x.getTenSanPham(),
                    x.getHinhAnh(),
                    x.getTenXuatXu(),
                    x.getChipXuLy(),
                    x.getDungLuongPin(),
                    x.getKichThuocManHinh(),
                    x.getTenHeDieuHanh(),
                    x.getPhienBanHeDieuHanh(),
                    x.getCameraSau(),
                    x.getCameraTruoc(),
                    x.getThoiGianBaoHanh(),
                    x.getTenThuongHieu(),
                    x.getTenKhuVuc(),
                    x.getSoLuongTon(),
                    x.getTrangThaiText()
                });
            }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        btnReload = new javax.swing.JButton();

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill][grow 0]",
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
                ""
            }
        ));
        crazyPanel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][]",
            "",
            new String[]{
                "width 400"
            }
        ));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
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

        crazyPanel1.add(crazyPanel2);

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên", "Hình ảnh", "Xuất xứ", "Chip xử lý", "Dung lượng pin", "Kích thước màn hình", "Hệ điều hành", "Phiên bản hệ điều hành", "Camera sau", "Camera trước", "Thời gian bảo hành", "Thương hiệu", "Kho", "Số lượng tồn", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true, true, true, true, true, true, true, true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProduct.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblProduct);

        crazyPanel1.add(jScrollPane1);

        crazyPanel3.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel3.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "push[][]",
            "",
            null
        ));

        btnReload.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReload.setText("Làm mới");
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });
        crazyPanel3.add(btnReload);

        crazyPanel1.add(crazyPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1171, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 662, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Window parent = SwingUtilities.getWindowAncestor(this);
        ProductAddDialog productAddDialog = new ProductAddDialog(parent, this);
        productAddDialog.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        Window parent = SwingUtilities.getWindowAncestor(this);
        int modelRow = tblProduct.getSelectedRow();
        if (modelRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Hãy chọn sản phẩm muốn chỉnh sửa");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();

        try {
            int maSanPhamInt = Integer.parseInt(model.getValueAt(modelRow, 0).toString());
            String tenSanPham = model.getValueAt(modelRow, 1).toString();
            String hinhAnh = model.getValueAt(modelRow, 2).toString();
            String xuatXu = model.getValueAt(modelRow, 3).toString();
            String chipXuLy = model.getValueAt(modelRow, 4).toString();
            int dungLuongPin = Integer.parseInt(model.getValueAt(modelRow, 5).toString());
            double kichThuocManHinh = Double.parseDouble(model.getValueAt(modelRow, 6).toString());
            String tenHeDieuHanh = model.getValueAt(modelRow, 7).toString();
            int phienBanHeDieuHanh = Integer.parseInt(model.getValueAt(modelRow, 8).toString());
            String cameraSau = model.getValueAt(modelRow, 9).toString();
            String cameraTruoc = model.getValueAt(modelRow, 10).toString();
            int thoiGianBaoHanh = Integer.parseInt(model.getValueAt(modelRow, 11).toString());
            String thuongHieu = model.getValueAt(modelRow, 12).toString();
            String tenKhuVuc = model.getValueAt(modelRow, 13).toString();
            int soLuongTon = Integer.parseInt(model.getValueAt(modelRow, 14).toString());
            String trangThaiText = model.getValueAt(modelRow, 15).toString();


            int trangThaiInt;
            if (trangThaiText.equalsIgnoreCase("Khoá")) { 
                trangThaiInt = 0;
            } else if (trangThaiText.equalsIgnoreCase("Hoạt động")) {
                trangThaiInt = 1;
            } else if (trangThaiText.equalsIgnoreCase("Ngừng bán")) {
                trangThaiInt = 2;
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Trạng thái không hợp lệ! Dữ liệu đọc được: '" + trangThaiText + "'");
                return;
            }

            ProductUpdateDialog dlg = new ProductUpdateDialog(
                    parent, this,
                    maSanPhamInt,
                    tenSanPham,
                    hinhAnh,
                    chipXuLy,
                    dungLuongPin,
                    kichThuocManHinh,
                    cameraSau,
                    cameraTruoc,
                    thoiGianBaoHanh,
                    soLuongTon,
                    phienBanHeDieuHanh,
                    trangThaiInt,
                    xuatXu,
                    tenHeDieuHanh,
                    thuongHieu,
                    tenKhuVuc
            );
            dlg.setLocationRelativeTo(parent);
            dlg.setVisible(true);

        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Dữ liệu số không hợp lệ: " + e.getMessage());
        } catch (Exception ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi không xác định: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        loadProductViewData();
    }//GEN-LAST:event_btnReloadActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int modelRow = getSelectedModelRow();
        if (modelRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Hãy chọn nhân viên muốn xoá");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();
        int maInt = Integer.parseInt(model.getValueAt(modelRow, 0).toString());

        int ret = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá nhân viên có mã: " + maInt, "Xoá", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {

            this.productService = new ProductServiceMain();

            if (productService.deleteProductById(maInt)) {
                loadProductViewData();
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        service.Find(tblProduct, txtSearch);
    }//GEN-LAST:event_txtSearchKeyReleased
    private int getSelectedModelRow() {
        int viewIndex = tblProduct.getSelectedRow();
        return viewIndex == -1 ? -1 : tblProduct.convertRowIndexToModel(viewIndex);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnUpdate;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

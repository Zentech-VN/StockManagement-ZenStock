package zentech.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import dao.ActivityDAO;
import entity.Product;
import java.awt.Component;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import raven.toast.Notifications;
import service.ProductServiceMain;
import zentech.application.dialog.ProductAddDialog;
import zentech.application.dialog.ProductDetailsDialog;
import zentech.application.dialog.ProductUpdateDialog;

public class ProductForm extends javax.swing.JPanel {

    private ProductServiceMain productService;
    private TableRowSorter<DefaultTableModel> sorter;

    public ProductForm() {
        initComponents();
        initalUI(tblSanPham);
        loadProductData();
        initSorter();
        initSearchListener();
    }

    public void loadProductData() {
        this.productService = new ProductServiceMain();
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);

        for (Product p : productService.getBasicProduct()) {
            model.addRow(new Object[]{
                p.getMaSanPham(),
                p.getTenSanPham(),
                p.getTenThuongHieu(),
                p.getGia(),
                p.getTenHeDieuHanh(),
                p.getTenXuatXu(),
                p.getTrangThai()
            });
        }

        this.tblSanPham.setModel(model);

        if (sorter == null) {
            initSorter();
        } else {
            sorter.sort();
        }
    }

    private void initalUI(JTable table) {
        tblSanPham.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        tblSanPham.setRowHeight(30);
        tblSanPham.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18));

        JScrollPane scroll = (JScrollPane) table.getParent().getParent();
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Table.background;"
                + "track:$Table.background;"
                + "trackArc:999");

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        table.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

        table.getTableHeader().setDefaultRenderer(getAlignmentCellRender(table.getTableHeader().getDefaultRenderer(), true));
        table.setDefaultRenderer(Object.class, getAlignmentCellRender(table.getDefaultRenderer(Object.class), false));

        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm");

        cbbSapXep.addActionListener(evt -> initSorter());
    }

    private TableCellRenderer getAlignmentCellRender(TableCellRenderer oldRender, boolean header) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = oldRender.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (com instanceof JLabel) {
                    JLabel label = (JLabel) com;
                    if (column == 1) {
                        label.setHorizontalAlignment(SwingConstants.CENTER); //Căn giữa
                    } else if (column == 0 || column == 2 || column == 3 || column == 4 || column == 5) {
                        label.setHorizontalAlignment(SwingConstants.LEFT); //Căn trái
                    } else {
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                    }
                }
                return com;
            }
        };
    }

    private void initSorter() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        sorter = new TableRowSorter<>(model);

        sorter.setComparator(5, (o1, o2) -> {
            int v1 = getTrangThaiOrder(o1.toString());
            int v2 = getTrangThaiOrder(o2.toString());
            return Integer.compare(v1, v2);
        });

        int choice = cbbSapXep.getSelectedIndex();
        List<RowSorter.SortKey> keys = new ArrayList<>();

        switch (choice) {
            case 0:
                keys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING)); // Tên SP
                break;
            case 1:
                keys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING)); // Thương hiệu
                break;
            case 2:
                keys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING)); // Giá
                break;
            case 3:
                keys.add(new RowSorter.SortKey(5, SortOrder.ASCENDING)); // Trạng thái
                break;
            default:
                break;
        }

        sorter.setSortKeys(keys);
        tblSanPham.setRowSorter(sorter);
        sorter.sort();
    }

    private int getTrangThaiOrder(String status) {
        if (status == null) {
            return 3;
        }

        switch (status) {
            case "Hoạt động":
                return 0;
            case "Khoá":
                return 1;
            case "Ngừng bán":
                return 2;
            default:
                return 3;
        }
    }

    private int getSelectedModelRow() {
        int viewIndex = tblSanPham.getSelectedRow();
        return viewIndex == -1 ? -1 : tblSanPham.convertRowIndexToModel(viewIndex);
    }

    private void initSearchListener() {
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                doSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                doSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            private void doSearch() {
                String kw = txtSearch.getText().trim();
                List<Product> list = productService.searchProducts(kw);

                DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
                model.setRowCount(0);

                for (Product p : list) {
                    model.addRow(new Object[]{
                        p.getMaSanPham(),
                        p.getTenSanPham(),
                        p.getTenThuongHieu(),
                        p.getGia(),
                        p.getTenHeDieuHanh(),
                        p.getTenXuatXu(),
                        p.getTrangThai()
                    });
                }

                sorter.sort();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        cbbSapXep = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        txtSearch = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAdd1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("Làm mới");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setText("Xuất File");

        cbbSapXep.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbbSapXep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên sản phẩm", "Thương hiệu", "Giá", "Trạng thái" }));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Sắp xếp theo:");

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

        crazyPanel1.add(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap())
            .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1130, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton5, jButton7});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton7)
                    .addComponent(cbbSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        loadProductData();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Window parent = SwingUtilities.getWindowAncestor(this);
        ProductAddDialog productAddDialog = new ProductAddDialog(parent, this);
        productAddDialog.setVisible(true);
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

        ProductUpdateDialog productUpdateDialog = new ProductUpdateDialog(parent, this, maSanPham);
        productUpdateDialog.setLocationRelativeTo(parent);
        productUpdateDialog.setVisible(true);
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
                loadProductData(); // reload bảng
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
        Window parent = SwingUtilities.getWindowAncestor(this);
        int modelRow = getSelectedModelRow();
        if (modelRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Hãy chọn sản phẩm muốn xem chi tiết");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        String maSanPham = model.getValueAt(modelRow, 0).toString();

        ProductDetailsDialog productDetailsDialog = new ProductDetailsDialog(parent, this, maSanPham);
        productDetailsDialog.setLocationRelativeTo(parent);
        productDetailsDialog.setVisible(true);
    }//GEN-LAST:event_btnAdd1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAdd1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbSapXep;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

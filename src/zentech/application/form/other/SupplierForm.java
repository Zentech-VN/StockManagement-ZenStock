package zentech.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import entity.Supplier;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import raven.toast.Notifications;
import service.SupplierService;
import zentech.application.dialog.SupplierAddDialog;
import zentech.application.dialog.SupplierUpdateDialog;

public class SupplierForm extends javax.swing.JPanel {

    private final int[] SIZE_MAP = {12, 14, 18};
    private final String[] FONT_MAP = {"Segoe UI", "Arial", "Serif"};
    private final SupplierService service = new SupplierService();

    public SupplierForm() {
        initComponents();
        initalUI();
        loadTable();
    }

    private void initalUI() {
        tblNhaCungCap.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        tblNhaCungCap.setRowHeight(30);
        tblNhaCungCap.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18));

        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm");

        txtMa.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã");
        txtTenNhaCungCap.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Họ tên");
        txtDiaChi.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Giới tính");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ngày sinh");
        txtSoDienThoai.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Điện Thoại");
        txtTrangThai.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email");

        // Ẩn cột "Object", chỉnh độ dài cột
        tblNhaCungCap.getColumnModel().getColumn(6).setMinWidth(0);
        tblNhaCungCap.getColumnModel().getColumn(6).setMaxWidth(0);
        tblNhaCungCap.getColumnModel().getColumn(6).setWidth(0);
        tblNhaCungCap.getColumnModel().getColumn(0).setPreferredWidth(30);  // Mã
        tblNhaCungCap.getColumnModel().getColumn(1).setPreferredWidth(120); // Tên nhà cung cấp
        tblNhaCungCap.getColumnModel().getColumn(2).setPreferredWidth(200); // Địa chỉ
        tblNhaCungCap.getColumnModel().getColumn(3).setPreferredWidth(150); // Email
        tblNhaCungCap.getColumnModel().getColumn(4).setPreferredWidth(100); // Số điện thoại
        tblNhaCungCap.getColumnModel().getColumn(5).setPreferredWidth(100); // Trạng thái

        JScrollPane scroll = (JScrollPane) tblNhaCungCap.getParent().getParent();
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Table.background;"
                + "track:$Table.background;"
                + "trackArc:999");

        tblNhaCungCap.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        tblNhaCungCap.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

        // Nếu cần sắp xếp theo kiểu số cho cột mã (cột 0)
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) tblNhaCungCap.getModel());

        sorter.setComparator(0, (Object o1, Object o2) -> {
            try {
                int i1 = Integer.parseInt(o1.toString());
                int i2 = Integer.parseInt(o2.toString());
                return Integer.compare(i1, i2);
            } catch (Exception e) {
                return o1.toString().compareTo(o2.toString());
            }
        });

        tblNhaCungCap.setRowSorter(sorter);

        // Thiết lập sắp xếp mặc định cho cột "Mã" tăng dần
        sorter.setSortKeys(List.of(new RowSorter.SortKey(0, javax.swing.SortOrder.ASCENDING)));
        sorter.sort();

        //Sắp xếp cmo
        cmoSapXep.addActionListener(e -> sortTable());
    }

    private void sortTable() {
        String selected = (String) cmoSapXep.getSelectedItem();
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) tblNhaCungCap.getRowSorter();

        if (selected == null || sorter == null) {
            return;
        }

        switch (selected) {
            case "Mặc định":
                // Sắp xếp theo mã nhà cung cấp (cột 0)
                sorter.setSortKeys(List.of(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
                break;
            case "Tên nhà cung cấp":
                // Sắp xếp theo tên (cột 1)
                sorter.setSortKeys(List.of(new RowSorter.SortKey(1, SortOrder.ASCENDING)));
                break;
            case "Email":
                // Sắp xếp theo email (cột 3)
                sorter.setSortKeys(List.of(new RowSorter.SortKey(3, SortOrder.ASCENDING)));
                break;
            case "Trạng thái":
                // Sắp xếp theo trạng thái (cột 5)
                sorter.setSortKeys(List.of(new RowSorter.SortKey(5, SortOrder.ASCENDING)));
                break;
            default:
                sorter.setSortKeys(null); // Không sắp xếp
        }

        sorter.sort(); // Áp dụng sắp xếp
    }

    private void lockWidth(JComponent c) {
        Dimension size = new Dimension(WIDTH, c.getPreferredSize().height);
        c.setPreferredSize(size);
        c.setMinimumSize(size);
        c.setMaximumSize(size);
    }

    public void loadTable() {
        new SwingWorker<List<Supplier>, Void>() {
            @Override
            protected List<Supplier> doInBackground() {
                List<Supplier> list = service.getAllSuppliers();
                return (list != null) ? list : new ArrayList<>();
            }

            @Override
            protected void done() {
                try {
                    List<Supplier> list = get();
                    DefaultTableModel model = (DefaultTableModel) tblNhaCungCap.getModel();
                    model.setRowCount(0);

                    for (Supplier s : list) {
                        String trangThaiText = (s.getTrangThai() == 0) ? "Mở khóa" : "Khóa";
                        model.addRow(new Object[]{
                            s.getMaNhaCungCap(),
                            s.getTenNhaCungCap(),
                            s.getDiaChi(),
                            s.getEmail(),
                            s.getSdt(),
                            trangThaiText,
                            s 
                        });
                    }

                    tblNhaCungCap.getColumnModel().getColumn(6).setMinWidth(0);
                    tblNhaCungCap.getColumnModel().getColumn(6).setMaxWidth(0);
                    tblNhaCungCap.getColumnModel().getColumn(6).setWidth(0);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtLamMoi = new javax.swing.JButton();
        cmoSapXep = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        txtSearch = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhaCungCap = new javax.swing.JTable();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenNhaCungCap = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JTextField();

        txtLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtLamMoi.setText("Làm mới");
        txtLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLamMoiActionPerformed(evt);
            }
        });

        cmoSapXep.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmoSapXep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mặc định", "Tên nhà cung cấp", "Email", "Trạng thái" }));

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

        tblNhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ", "Tên nhà cung cấp", "Địa chỉ", "Email", "Số điện thoại", "Trạng thái", "Object"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhaCungCap.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblNhaCungCap.getTableHeader().setReorderingAllowed(false);
        tblNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhaCungCapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhaCungCap);
        if (tblNhaCungCap.getColumnModel().getColumnCount() > 0) {
            tblNhaCungCap.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblNhaCungCap.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblNhaCungCap.getColumnModel().getColumn(2).setPreferredWidth(30);
            tblNhaCungCap.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblNhaCungCap.getColumnModel().getColumn(4).setPreferredWidth(70);
            tblNhaCungCap.getColumnModel().getColumn(5).setPreferredWidth(140);
            tblNhaCungCap.getColumnModel().getColumn(6).setPreferredWidth(50);
        }

        crazyPanel1.add(jScrollPane1);

        crazyPanel3.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Info.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel3.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            new String[]{
                ""
            }
        ));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setText("Mã nhà cung cấp");
        crazyPanel3.add(jLabel1);

        txtMa.setEditable(false);
        txtMa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel3.add(txtMa);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setText("Tên nhà cung cấp");
        crazyPanel3.add(jLabel2);

        txtTenNhaCungCap.setEditable(false);
        txtTenNhaCungCap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel3.add(txtTenNhaCungCap);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setText("Địa chỉ");
        crazyPanel3.add(jLabel3);

        txtDiaChi.setEditable(false);
        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel3.add(txtDiaChi);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setText("Email");
        crazyPanel3.add(jLabel4);

        txtEmail.setEditable(false);
        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel3.add(txtEmail);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel5.setText("Số điện thoại");
        crazyPanel3.add(jLabel5);

        txtSoDienThoai.setEditable(false);
        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel3.add(txtSoDienThoai);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel6.setText("Trang thai");
        crazyPanel3.add(jLabel6);

        txtTrangThai.setEditable(false);
        txtTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel3.add(txtTrangThai);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmoSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLamMoi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(crazyPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(crazyPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLamMoi)
                    .addComponent(cmoSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // Lấy cửa sổ cha (window) từ SupplierForm
        java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);

        // Khởi tạo dialog và truyền this vào để có thể gọi lại loadTable sau khi thêm
        SupplierAddDialog dialog = new SupplierAddDialog(parentWindow, this);
        dialog.setLocationRelativeTo(this); // Hiển thị giữa màn hình
        dialog.setVisible(true); // Hiển thị dialog (modal)

        // Sau khi đóng dialog, làm mới bảng nếu có dữ liệu mới được thêm
        loadTable();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = tblNhaCungCap.getSelectedRow();
        if (selectedRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn nhà cung cấp để sửa! Thông báo");
            return;
        }

        int modelIndex = tblNhaCungCap.convertRowIndexToModel(selectedRow);
        DefaultTableModel model = (DefaultTableModel) tblNhaCungCap.getModel();
        Supplier selectedSupplier = (Supplier) model.getValueAt(modelIndex, 6);

        java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        SupplierUpdateDialog dialog = new SupplierUpdateDialog(parentWindow, this);

        // Gán dữ liệu selectedSupplier cho dialog
        dialog.setSupplier(selectedSupplier);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        loadTable();
        txtMa.setText(null);
        txtDiaChi.setText(null);
        txtEmail.setText(null);
        txtSoDienThoai.setText(null);
        txtTenNhaCungCap.setText(null);
        txtTrangThai.setText(null);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = tblNhaCungCap.getSelectedRow();
        String appCurrentUser = zentech.application.Application.getAppInstance().getCurrentUser();
        if (selectedRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Hãy chọn Nhà cung cấp muốn xóa");
            return;
        }

        // Chuyển chỉ số dòng sang model (vì bảng có thể đang sắp xếp)
        int modelIndex = tblNhaCungCap.convertRowIndexToModel(selectedRow);
        DefaultTableModel model = (DefaultTableModel) tblNhaCungCap.getModel();

        // Lấy mã nhà cung cấp tại dòng đã chọn
        int maNhaCungCap = (int) model.getValueAt(modelIndex, 0);
        String tenNhaCungCap = (String) model.getValueAt(modelIndex, 1);

        // Hiển thị hộp thoại xác nhận
        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa nhà cung cấp có mã: " + maNhaCungCap + " ?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // Gọi service để xóa
            boolean success = service.deleteSupplier(maNhaCungCap);
            if (success) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xoá thành công");
                loadTable(); // Tải lại dữ liệu lên bảng
                txtMa.setText(null);
                txtDiaChi.setText(null);
                txtEmail.setText(null);
                txtSoDienThoai.setText(null);
                txtTenNhaCungCap.setText(null);
                txtTrangThai.setText(null);
            } else {
                JOptionPane.showMessageDialog(this, "Xóa nhà cung cấp thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhaCungCapMouseClicked
        int selectedRow = tblNhaCungCap.getSelectedRow();
        if (selectedRow != -1) {
            // Enable nút Sửa khi chọn dòng
            btnUpdate.setEnabled(true);

            // Chuyển chỉ số dòng sang model (vì bảng có sorter)
            int modelIndex = tblNhaCungCap.convertRowIndexToModel(selectedRow);
            DefaultTableModel model = (DefaultTableModel) tblNhaCungCap.getModel();

            Supplier selectedSupplier = (Supplier) model.getValueAt(modelIndex, 6);

            txtMa.setText(String.valueOf(selectedSupplier.getMaNhaCungCap()));
            txtTenNhaCungCap.setText(selectedSupplier.getTenNhaCungCap());
            txtDiaChi.setText(selectedSupplier.getDiaChi());
            txtEmail.setText(selectedSupplier.getEmail());
            txtSoDienThoai.setText(selectedSupplier.getSdt());
            txtTrangThai.setText(selectedSupplier.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động");
        } else {
            btnUpdate.setEnabled(false);
        }
    }//GEN-LAST:event_tblNhaCungCapMouseClicked

    private void txtLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLamMoiActionPerformed
        loadTable();
        txtMa.setText(null);
        txtDiaChi.setText(null);
        txtEmail.setText(null);
        txtSoDienThoai.setText(null);
        txtTenNhaCungCap.setText(null);
        txtTrangThai.setText(null);
    }//GEN-LAST:event_txtLamMoiActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        DefaultTableModel ob = (DefaultTableModel) tblNhaCungCap.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        tblNhaCungCap.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(txtSearch.getText()));
    }//GEN-LAST:event_txtSearchKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmoSapXep;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblNhaCungCap;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JButton txtLamMoi;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenNhaCungCap;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}

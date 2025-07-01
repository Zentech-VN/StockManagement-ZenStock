package zentech.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import dao.ActivityDAO;
import entity.DestructionReleaseNote;
import entity.Supplier;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import raven.toast.Notifications;
import zentech.application.dialog.SupplierAddDialog;
import zentech.application.dialog.SupplierUpdateDialog;
import zentech.application.dialog.DestructionReleaseNoteUpdateDialog;
import zentech.application.dialog.DestructionReleaseNoteDetailsDialog;
import service.DestructionReleaseNoteService;

public class DestructionReleaseNoteForm extends javax.swing.JPanel {

    private final int[] SIZE_MAP = {12, 14, 18};
    private final String[] FONT_MAP = {"Segoe UI", "Arial", "Serif"};
    private final DestructionReleaseNoteService destructionReleaseNoteService = new DestructionReleaseNoteService();

    public DestructionReleaseNoteForm() {
        initComponents();
        initalUI();
        loadData();
    }

    private void initalUI() {
        tblPhieuXuatHuy.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        tblPhieuXuatHuy.setRowHeight(30);
        tblPhieuXuatHuy.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18));

        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm");

        txtMa.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã");
        txtNguoiTao.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Người tạo");
        txtThoiGian.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Thời gian");
        txtTrangThai.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Trạng thái");

        // chỉnh độ dài cột
        tblPhieuXuatHuy.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblPhieuXuatHuy.getColumnModel().getColumn(1).setPreferredWidth(120);
        tblPhieuXuatHuy.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblPhieuXuatHuy.getColumnModel().getColumn(3).setPreferredWidth(150);

        // Nếu cần sắp xếp theo kiểu số cho cột mã (cột 0)
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) tblPhieuXuatHuy.getModel());

        sorter.setComparator(0, (Object o1, Object o2) -> {
            try {
                int i1 = Integer.parseInt(o1.toString());
                int i2 = Integer.parseInt(o2.toString());
                return Integer.compare(i1, i2);
            } catch (Exception e) {
                return o1.toString().compareTo(o2.toString());
            }
        });

        tblPhieuXuatHuy.setRowSorter(sorter);

        // Thiết lập sắp xếp mặc định cho cột "Mã" tăng dần
        sorter.setSortKeys(List.of(new RowSorter.SortKey(0, javax.swing.SortOrder.ASCENDING)));
        sorter.sort();

        //Sắp xếp cmo
        cmoSapXep.addActionListener(e -> sortTable());
    }

    public void loadData() {
        destructionReleaseNoteService.loadPhieuHuyToTable(tblPhieuXuatHuy);
    }

    private void sortTable() {
        String selected = (String) cmoSapXep.getSelectedItem();
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) tblPhieuXuatHuy.getRowSorter();

        if (selected == null || sorter == null) {
            return;
        }

        switch (selected) {
            case "Mã":
                // Sắp xếp theo mã nhà cung cấp (cột 0)
                sorter.setSortKeys(List.of(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
                break;
            case "Người tạo":
                // Sắp xếp theo tên (cột 1)
                sorter.setSortKeys(List.of(new RowSorter.SortKey(1, SortOrder.ASCENDING)));
                break;
            case "Thời gian":
                // Sắp xếp theo email (cột 3)
                sorter.setSortKeys(List.of(new RowSorter.SortKey(2, SortOrder.ASCENDING)));
                break;
            case "Trạng thái":
                // Sắp xếp theo trạng thái (cột 5)
                sorter.setSortKeys(List.of(new RowSorter.SortKey(3, SortOrder.ASCENDING)));
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtLamMoi = new javax.swing.JButton();
        cmoSapXep = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        txtSearch = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnDetails = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuXuatHuy = new javax.swing.JTable();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNguoiTao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtThoiGian = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JTextField();

        txtLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtLamMoi.setText("Làm mới");
        txtLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLamMoiActionPerformed(evt);
            }
        });

        cmoSapXep.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmoSapXep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mặc định", "Mã", "Người tạo", "Thời gian", "Trạng thái" }));

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

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnUpdate);

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setText("Xóa");
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

        tblPhieuXuatHuy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Người tạo", "Thời gian", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieuXuatHuy.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblPhieuXuatHuy.getTableHeader().setReorderingAllowed(false);
        tblPhieuXuatHuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuXuatHuyMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieuXuatHuy);
        if (tblPhieuXuatHuy.getColumnModel().getColumnCount() > 0) {
            tblPhieuXuatHuy.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblPhieuXuatHuy.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblPhieuXuatHuy.getColumnModel().getColumn(2).setPreferredWidth(30);
            tblPhieuXuatHuy.getColumnModel().getColumn(3).setPreferredWidth(70);
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
        jLabel1.setText("Mã phiếu xuất hủy");
        crazyPanel3.add(jLabel1);

        txtMa.setEditable(false);
        txtMa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel3.add(txtMa);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setText("Người tạo ");
        crazyPanel3.add(jLabel2);

        txtNguoiTao.setEditable(false);
        txtNguoiTao.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel3.add(txtNguoiTao);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setText("Thời gian ");
        crazyPanel3.add(jLabel3);

        txtThoiGian.setEditable(false);
        txtThoiGian.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel3.add(txtThoiGian);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setText("Trạng thái");
        crazyPanel3.add(jLabel4);

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
                        .addGap(18, 18, 18)
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
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                    .addComponent(crazyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLamMoi)
                    .addComponent(cmoSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = tblPhieuXuatHuy.getSelectedRow();
        if (selectedRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Vui lòng chọn dòng cần cập nhật!");
            return;
        }

        int modelRow = tblPhieuXuatHuy.convertRowIndexToModel(selectedRow); // chuyển về model
        int maphieu = (int) tblPhieuXuatHuy.getValueAt(selectedRow, 0);
        DestructionReleaseNote note = new DestructionReleaseNoteService().getAllPhieuXuatHuybyID(maphieu);
        if (note != null) {
            java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
            DestructionReleaseNoteUpdateDialog dialog = new DestructionReleaseNoteUpdateDialog(parentWindow, this, maphieu);
            dialog.setData(note); // truyền dữ liệu vào dialog
            dialog.setVisible(true);
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = tblPhieuXuatHuy.getSelectedRow();
        if (selectedRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Vui lòng chọn dòng cần xóa!");
            return;
        }

        int modelRow = tblPhieuXuatHuy.convertRowIndexToModel(selectedRow);
        int maphieu = (int) tblPhieuXuatHuy.getModel().getValueAt(modelRow, 0);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn xóa phiếu xuất hủy này?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = destructionReleaseNoteService.deletePhieu(maphieu);
            if (success) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, "Xóa thành công!");
                clearFields(); // Xóa các text bên phải
                loadData();    // Load lại bảng
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Xóa thất bại!");
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        int selectedRow = tblPhieuXuatHuy.getSelectedRow();

        // Kiểm tra chưa chọn dòng nào
        if (selectedRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Vui lòng chọn một phiếu xuất hủy để xem chi tiết!");
            return;
        }

        // Lấy row thực trong model
        int modelRow = tblPhieuXuatHuy.convertRowIndexToModel(selectedRow);

        // Lấy mã phiếu từ cột 0 (cần đảm bảo đây là cột chứa maphieuxuat_huy)
        Object value = tblPhieuXuatHuy.getModel().getValueAt(modelRow, 0);
        if (value == null) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Không thể lấy mã phiếu từ dòng đã chọn!");
            return;
        }

        int maphieuxuatHuy;
        try {
            maphieuxuatHuy = Integer.parseInt(value.toString());
        } catch (NumberFormatException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Mã phiếu không hợp lệ!");
            return;
        }

        // Mở dialog chi tiết
        java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        DestructionReleaseNoteDetailsDialog dialog = new DestructionReleaseNoteDetailsDialog(parentWindow, this, maphieuxuatHuy);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnDetailsActionPerformed

    private void updateSelectedNoteInfo() {
        int selectedRow = tblPhieuXuatHuy.getSelectedRow();
        if (selectedRow == -1) {
            return;
        }

        int modelRow = tblPhieuXuatHuy.convertRowIndexToModel(selectedRow); // chuyển về model

        int maphieu = (int) tblPhieuXuatHuy.getModel().getValueAt(modelRow, 0);
        DestructionReleaseNote note = destructionReleaseNoteService.getAllPhieuXuatHuybyID(maphieu);

        if (note != null) {
            txtMa.setText(String.valueOf(note.getId()));
            txtNguoiTao.setText(note.getCreator());
            txtThoiGian.setText(note.getDate().toString());
            txtTrangThai.setText(note.getStatus());
        }
    }

    private void tblPhieuXuatHuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuXuatHuyMouseClicked
        updateSelectedNoteInfo();
    }//GEN-LAST:event_tblPhieuXuatHuyMouseClicked

    private void clearFields() {
        txtMa.setText(null);
        txtNguoiTao.setText(null);
        txtThoiGian.setText(null);
        txtTrangThai.setText(null);
        tblPhieuXuatHuy.clearSelection();
    }

    private void txtLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLamMoiActionPerformed
        clearFields();
    }//GEN-LAST:event_txtLamMoiActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        DefaultTableModel ob = (DefaultTableModel) tblPhieuXuatHuy.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        tblPhieuXuatHuy.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(txtSearch.getText()));
    }//GEN-LAST:event_txtSearchKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmoSapXep;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPhieuXuatHuy;
    private javax.swing.JButton txtLamMoi;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNguoiTao;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtThoiGian;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}

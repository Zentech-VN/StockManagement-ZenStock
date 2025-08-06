package zentech.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import dao.UserRightsDAO;
import dao.WarehouseReceiptDAO;
import entity.ChiTietQuyen;
import entity.Employee;
import entity.PhieuNhap;

import java.awt.Component;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import raven.toast.Notifications;
import service.WarehouseReceiptService;

import zentech.application.dialog.WarehouseReceiptAddDialog;
import zentech.application.dialog.WarehouseReceiptDetailsDialog;
import zentech.application.dialog.WarehouseReceiptUpdateDialog;

public class WarehouseReceiptForm extends javax.swing.JPanel {

    WarehouseReceiptService wrs = new WarehouseReceiptService();
    private Employee CurrentAcc;
    private WarehouseReceiptDAO wrd = new WarehouseReceiptDAO();
    private UserRightsDAO urd = new UserRightsDAO();

    private int currentPage = 1;
    private final int pageSize = 50;  // số dòng mỗi trang
    private int totalPages = 1;
    
    public WarehouseReceiptForm(Employee acc) {
        this.CurrentAcc = acc;
        initComponents();
        initalUI(tblPhieuNhap);
        loadDataTable();
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
        if (check(list, "create", "phieunhap") == false) {
            btnAdd.setEnabled(false);
        }
        if (check(list, "delete", "phieunhap") == false) {
            jButton1.setEnabled(false);
        }
        if (check(list, "update", "phieunhap") == false) {
            btnUpdate.setEnabled(false);
        }
    }

    private void initalUI(JTable table) {
        tblPhieuNhap.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        tblPhieuNhap.setRowHeight(30);
        tblPhieuNhap.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18));

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

    }

    private TableCellRenderer getAlignmentCellRender(TableCellRenderer oldRender, boolean header) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = oldRender.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (com instanceof JLabel) {
                    JLabel label = (JLabel) com;
                    if (column == 0 || column == 1 || column == 2 || column == 3 || column == 4 || column == 5) {
                        label.setHorizontalAlignment(SwingConstants.LEFT); //Căn trái
                    } else {
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                    }
                }
                return com;
            }
        };
    }
    
    public String settrangthai(String trangthai) {
        if (trangthai.equalsIgnoreCase("choduyet")) {
            return "Chờ duyệt";
        } else if (trangthai.equalsIgnoreCase("duyet")) {
            return "Duyệt";
        } else {
            return "Hủy";
        }
    }
    
    public void loadDataTable() {
        SwingWorker<List<Object[]>, Void> worker = new SwingWorker<List<Object[]>, Void>() {
            @Override
            protected List<Object[]> doInBackground() throws Exception {
                List<PhieuNhap> list = wrd.getAllentries(currentPage, pageSize);

                int totalEntries = wrd.getReciptCount();
                totalPages = (int) Math.ceil((double) totalEntries / pageSize);

                List<Object[]> rows = new ArrayList<>();
                for (PhieuNhap p : list) {
                    rows.add(new Object[]{
                        p.getMaphieunhap(),
                        p.getS().getTenNhaCungCap(),
                        p.getE().getHoten(),
                        p.getNgaytao(),
                        settrangthai(p.getTrangthai())
                    });
                }
                return rows;
            }

            @Override
            protected void done() {
                try {
                    List<Object[]> rows = get();

                    DefaultTableModel model = (DefaultTableModel) tblPhieuNhap.getModel();
                    model.setRowCount(0); // Xóa dữ liệu cũ

                    for (Object[] row : rows) {
                        model.addRow(row);
                    }

                    btnPrevious.setEnabled(currentPage > 1);
                    btnFirst.setEnabled(currentPage > 1);
                    btnNext.setEnabled(currentPage < totalPages);
                    btnLast.setEnabled(currentPage < totalPages);

                    lblCurrentPage.setText("Trang " + currentPage + " / " + totalPages);

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(tblPhieuNhap, "Lỗi khi load dữ liệu: " + e.getMessage());
                }
            }
        };

        worker.execute();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLamMoi = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        txtSearch = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnDetails = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuNhap = new javax.swing.JTable();
        crazyPanel6 = new raven.crazypanel.CrazyPanel();
        btnFirst = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        lblCurrentPage = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setText("Xuất File");

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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        crazyPanel2.add(txtSearch);

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd.setText("Tạo");
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

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Xóa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        crazyPanel2.add(jButton1);

        btnDetails.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDetails.setText("Chi tiết");
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnDetails);

        crazyPanel1.add(crazyPanel2);

        tblPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Nhà cung cấp", "Người tạo", "Thời gian", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieuNhap.getTableHeader().setReorderingAllowed(false);
        tblPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieuNhap);

        crazyPanel1.add(jScrollPane1);

        crazyPanel6.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel6.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "push[][][][][]push",
            "",
            null
        ));

        btnFirst.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFirst.setText("<<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        crazyPanel6.add(btnFirst);

        btnPrevious.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrevious.setText("< Trước");
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });
        crazyPanel6.add(btnPrevious);

        lblCurrentPage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCurrentPage.setText("...");
        crazyPanel6.add(lblCurrentPage);

        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNext.setText("Sau >");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        crazyPanel6.add(btnNext);

        btnLast.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLast.setText(">>");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        crazyPanel6.add(btnLast);

        crazyPanel1.add(crazyPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLamMoi)
                .addContainerGap())
            .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1130, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoi, jButton7});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi)
                    .addComponent(jButton7))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        loadDataTable();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Window parent = SwingUtilities.getWindowAncestor(this);
        WarehouseReceiptAddDialog warehouseReceiptAddDialog = new WarehouseReceiptAddDialog(parent, this, this.CurrentAcc);
        warehouseReceiptAddDialog.setVisible(true);
        loadDataTable();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int select = tblPhieuNhap.getSelectedRow();
        if (select == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn phiếu nhập để cập nhật!");
            return;
        }
        int id = (int) tblPhieuNhap.getValueAt(select, 0);
        String tennhacungcap = (String) tblPhieuNhap.getValueAt(select, 1);
        String trangthai = (String) tblPhieuNhap.getValueAt(select, 4);
        if (trangthai.equalsIgnoreCase("duyet")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Không được cập nhật phiếu nhập có trạng thái duyệt!");
            return;
        } else if (trangthai.equalsIgnoreCase("hủy")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Không được xóa phiếu có trạng thái hủy!");
            return;
        }
        Window parent = SwingUtilities.getWindowAncestor(this);
        WarehouseReceiptUpdateDialog warehouseReceiptUpdateDialog = new WarehouseReceiptUpdateDialog(parent, this, id, tennhacungcap);
        warehouseReceiptUpdateDialog.setVisible(true);
        loadDataTable();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuNhapMouseClicked

    }//GEN-LAST:event_tblPhieuNhapMouseClicked

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        int select = tblPhieuNhap.getSelectedRow();
        if (select == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn phiếu nhập để xem chi tiết!");
            return;
        }
        int maphieunhap = (int) tblPhieuNhap.getValueAt(select, 0);
        Window parent = SwingUtilities.getWindowAncestor(this);
        WarehouseReceiptDetailsDialog warehouseReceiptDetailsDialog = new WarehouseReceiptDetailsDialog(parent, this, maphieunhap);
        warehouseReceiptDetailsDialog.setVisible(true);
        loadDataTable();
    }//GEN-LAST:event_btnDetailsActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        wrs.search(tblPhieuNhap, txtSearch);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int select = tblPhieuNhap.getSelectedRow();
        if (select == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn phiếu nhập để xóa!");
            return;
        }
        int id = (int) tblPhieuNhap.getValueAt(select, 0);
        String trangthai = (String) tblPhieuNhap.getValueAt(select, 4);
        if (trangthai.equalsIgnoreCase("duyệt")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Không được xóa phiếu nhập có trạng thái duyệt!");
            return;
        } else if (trangthai.equalsIgnoreCase("hủy")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Không được xóa phiếu có trạng thái hủy!");
            return;
        }
        int rs = wrd.xoaphieunhap(id);
        if (rs > 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Xóa thành công phiếu nhập có mã " + id + "!");
        }
        loadDataTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        btnFirst.addActionListener(e -> {
            if (currentPage != 1) {
                currentPage = 1;
                loadDataTable();
            }
        });
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        if (currentPage > 1) {
            currentPage--;
            loadDataTable();
        }
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (currentPage < totalPages) {
            currentPage++;
            loadDataTable();
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        btnLast.addActionListener(e -> {
            if (currentPage != totalPages) {
                currentPage = totalPages;
                loadDataTable();
            }
        });
    }//GEN-LAST:event_btnLastActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnUpdate;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel6;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCurrentPage;
    private javax.swing.JTable tblPhieuNhap;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

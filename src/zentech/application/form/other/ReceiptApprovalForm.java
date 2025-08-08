package zentech.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import dao.UserRightsDAO;
import java.awt.Window;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import zentech.application.dialog.CheckReceiptDialog;
import zentech.application.dialog.ReceiptDetailsDialog;
import service.WarehouseReceiptService;
import dao.WarehouseReceiptDAO;
import entity.ChiTietQuyen;
import entity.Employee;
import entity.Receipt;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import service.ReceiptService;

public class ReceiptApprovalForm extends javax.swing.JPanel {

    private WarehouseReceiptService wrs = new WarehouseReceiptService();
    private WarehouseReceiptDAO wrd = new WarehouseReceiptDAO();
    private ReceiptService rs = new ReceiptService();
    private Employee CurrentAcc;
    private UserRightsDAO urd = new UserRightsDAO();

    public ReceiptApprovalForm(Employee acc) {
        initComponents();
        this.CurrentAcc = acc;
        initalUI();
        setupTable();
        loadPendingReceipts(jTable1);
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
        if (check(list, "create", "duyetphieu") == false) {
            jButton1.setEnabled(false);
        }

        if (check(list, "update", "duyetphieu") == false) {
            jButton2.setEnabled(false);
        }
    }

    private void initalUI() {
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm");
    }

    private void setupTable() {
        String[] title = {"Mã phiếu", "Loại phiếu", "Người tạo", "Thời gian", "Tổng tiền"};
        DefaultTableModel model = new DefaultTableModel(title, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jTable1.setModel(model);

        jTable1.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        jTable1.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

        jTable1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        jTable1.setRowHeight(40);
        jTable1.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18));
    }

    public int getSelectedReceiptId() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            return (Integer) jTable1.getValueAt(selectedRow, 0);
        }
        return -1;
    }

    public String getSelectedReceiptCategory() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            String receiptType = (String) jTable1.getValueAt(selectedRow, 1);
            return receiptType.equals("Phiếu Nhập") ? "import" : "export";
        }
        return null;
    }

    public void refreshTable() {
        loadPendingReceipts(jTable1);
    }

    public void loadPendingReceipts(JTable jTable1) {
        SwingWorker<List<Receipt>, Void> worker = new SwingWorker<List<Receipt>, Void>() {
            @Override
            protected List<Receipt> doInBackground() throws Exception {
                return rs.getAllPendingReceipts();
            }

            @Override
            protected void done() {
                try {
                    List<Receipt> receipts = get();

                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    model.setRowCount(0);

                    for (Receipt receipt : receipts) {
                        model.addRow(new Object[]{
                            receipt.getReceiptId(),
                            receipt.getReceiptType(),
                            receipt.getCreatedBy(),
                            receipt.getTimestamp(),
                            receipt.getTotalAmount()
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(
                            jTable1,
                            "Lỗi khi tải danh sách phiếu chờ duyệt: " + e.getMessage(),
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        };

        worker.execute();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Kiểm tra");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Chi tiết");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 492, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int selectedReceiptId = getSelectedReceiptId();
        if (selectedReceiptId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu để kiểm tra", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Window parent = SwingUtilities.getWindowAncestor(this);
        CheckReceiptDialog crd = new CheckReceiptDialog(parent, this, selectedReceiptId);
        crd.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int selectedReceiptId = getSelectedReceiptId();
        if (selectedReceiptId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu để xem chi tiết", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Window parent = SwingUtilities.getWindowAncestor(this);
        ReceiptDetailsDialog rdd = new ReceiptDetailsDialog(parent, this, selectedReceiptId);
        rdd.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        rs.searchReceipts(jTable1, txtSearch);
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

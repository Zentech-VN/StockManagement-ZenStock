/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package zentech.application.dialog;

import java.awt.Dialog;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import zentech.application.form.other.ReceiptApprovalForm;
import service.ReceiptService;
import entity.Receipt;
import entity.ReceiptDetails;
import jdbc.ConnectionHelper;
import java.util.List;
import java.text.DecimalFormat;

/**
 *
 * @author PC
 */
public class CheckReceiptDialog extends JDialog {

    private ReceiptService receiptService;
    private ReceiptApprovalForm parentForm;
    private int receiptId;
    private String receiptCategory;
    private Receipt receiptSummary;

    public CheckReceiptDialog(Window parent, ReceiptApprovalForm receiptApprovalForm, int selectedReceiptId) {
        super(parent, Dialog.ModalityType.APPLICATION_MODAL);
        this.parentForm = receiptApprovalForm;
        this.receiptId = selectedReceiptId;
        this.receiptService = new ReceiptService();

        initComponents();
        setupDialog();
        setLocationRelativeTo(null);
        loadReceiptData();
    }

    private void setupDialog() {
        setTitle("Kiểm tra phiếu - ID: " + receiptId);

        String[] title = {"Tên sản phẩm", "Đơn giá", "Khu vực kho", "Số lượng", "Ghi chú"};
        DefaultTableModel model = new DefaultTableModel(title, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(model);
        jTable1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        jTable1.setRowHeight(25);
        jTable1.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));

    }

    private void loadReceiptData() {
        try {
            receiptCategory = parentForm.getSelectedReceiptCategory();
            if (receiptCategory == null) {
                JOptionPane.showMessageDialog(this, "Không thể xác định loại phiếu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Receipt receiptSummary = receiptService.getReceiptSummary(receiptId, receiptCategory);

            jTextField1.setText(receiptSummary.getCreatedBy());
            jTextField2.setText(receiptSummary.getTimestamp().toString());
            jTextField3.setText(""); 
            DecimalFormat formatter = new DecimalFormat("#,###.##");
            jTextField4.setText(formatter.format(receiptSummary.getTotalAmount()) + " VND");

            loadReceiptDetails();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadReceiptDetails() {
        try {
            System.out.println("Loading receipt details for ID: " + receiptId + ", category: " + receiptCategory);

            List<ReceiptDetails> details = receiptService.getReceiptDetailsForInspection(receiptId, receiptCategory);
            System.out.println("Found " + details.size() + " details");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            for (ReceiptDetails detail : details) {
                DecimalFormat formatter = new DecimalFormat("#,###.##");
                model.addRow(new Object[]{
                    detail.getProductName(),
                    formatter.format(detail.getPrice()) + " VND",
                    detail.getWarehouseCode(),
                    detail.getQuantity(),
                    detail.getNote() != null ? detail.getNote() : ""
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refresh(){
        parentForm.refreshTable();
    }
    
    private void approveReceipt() {

        try {
            boolean success = receiptService.approveReceipt(receiptId, receiptCategory);

            if (success) {
                JOptionPane.showMessageDialog(this,
                    "Duyệt phiếu thành công!",
                    "Thành công",
                    JOptionPane.INFORMATION_MESSAGE);
                dispose();
                refresh();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Không thể duyệt phiếu. Vui lòng thử lại.",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            }
            
            } catch (Exception e) {
                e.printStackTrace();
            
        }
    }

    private void rejectReceipt() {
        int confirm = JOptionPane.showConfirmDialog(this,"Bạn có chắc chắn muốn hủy phiếu này?", "Xác nhận hủy",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                boolean success = receiptService.rejectReceipt(receiptId, receiptCategory);

                if (success) {
                    JOptionPane.showMessageDialog(this,
                        "Hủy phiếu thành công!",
                        "Thành công",
                        JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Không thể hủy phiếu. Vui lòng thử lại.",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Tên nhân viên tạo");

        jTextField1.setEditable(false);

        jLabel2.setText("Thời gian tạo");

        jTextField2.setEditable(false);

        jLabel3.setText("Ghi chú");

        jTextField3.setEditable(false);

        jLabel4.setText("Tổng tiền");

        jTextField4.setEditable(false);

        jButton1.setBackground(new java.awt.Color(51, 51, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setText("Duyệt");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton2.setText("Hủy");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3)
                    .addComponent(jTextField4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        approveReceipt();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        rejectReceipt(); 
    }//GEN-LAST:event_jButton2ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}

package zentech.application.dialog;

import com.formdev.flatlaf.FlatClientProperties;
import dao.ActivityDAO;
import java.awt.Dialog;
import java.awt.Window;
import javax.swing.JDialog;
import raven.toast.Notifications;
import service.SupplierService;
import zentech.application.form.other.DestructionReleaseNoteForm;

public class DestructionReleaseNoteAddDialog extends JDialog {

    private DestructionReleaseNoteForm destructionReleaseNoteForm;

    public DestructionReleaseNoteAddDialog(Window parent, DestructionReleaseNoteForm destructionReleaseNoteForm) {
        super(parent, Dialog.ModalityType.APPLICATION_MODAL);
        this.destructionReleaseNoteForm = destructionReleaseNoteForm;
        initComponents();
        initalUI();
        customer();
    }

    private void initalUI() {
        txtMa.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã phiếu");
        txtMalmel.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Malmel");
        txtNguoiTao.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Người tạo");
        txtThoiGian.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Thời gian");
        txtAraeLyDo.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Lý do");
    }
    
    private void customer(){
        txtMa.setEditable(false);
        txtMalmel.setEditable(false);
        txtNguoiTao.setEditable(false);
        txtThoiGian.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        txtMa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMalmel = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNguoiTao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtThoiGian = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmoTrangThai = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAraeLyDo = new javax.swing.JTextArea();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cập nhật phiếu xuất hủy", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel3.setMaximumSize(new java.awt.Dimension(100, 100));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Trạng thái");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Malmel");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Người tạo");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Thời gian");

        cmoTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DaBan", "TrongKho", "ChoDuyet" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Lý do");

        txtAraeLyDo.setColumns(20);
        txtAraeLyDo.setRows(5);
        jScrollPane1.setViewportView(txtAraeLyDo);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMa)
                    .addComponent(txtMalmel)
                    .addComponent(txtNguoiTao)
                    .addComponent(txtThoiGian)
                    .addComponent(cmoTrangThai, 0, 278, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMalmel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmoTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtMa, txtMalmel, txtNguoiTao, txtThoiGian});

        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnHuy.setText("Huỷ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHuy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLuu)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu)
                    .addComponent(btnHuy))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.getAccessibleContext().setAccessibleName("Cập nhật phiếu xuất hủy");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed

    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JComboBox<String> cmoTrangThai;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtAraeLyDo;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMalmel;
    private javax.swing.JTextField txtNguoiTao;
    private javax.swing.JTextField txtThoiGian;
    // End of variables declaration//GEN-END:variables
}

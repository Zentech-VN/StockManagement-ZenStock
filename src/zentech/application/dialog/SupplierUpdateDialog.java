package zentech.application.dialog;

import com.formdev.flatlaf.FlatClientProperties;
import dao.ActivityDAO;
import entity.Supplier;
import java.awt.Dialog;
import java.awt.Window;
import javax.swing.JDialog;
import raven.crazypanel.bean.editor.FlatLafStyleEditor;
import raven.toast.Notifications;
import service.SupplierService;
import zentech.application.form.other.SupplierForm;

public class SupplierUpdateDialog extends JDialog {

    private SupplierForm supplierForm;
    SupplierService service = new SupplierService();

    public SupplierUpdateDialog(Window parent, SupplierForm supplierForm) {
        super(parent, Dialog.ModalityType.APPLICATION_MODAL);
        this.supplierForm = supplierForm;
        initComponents();
        initalUI();
    }

    private void initalUI() {
        txtMaNhaCungCap.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã nhà cung cấp");
        txtTenNhaCungCap.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tên nhà cung cấp");
        txtDiaChi.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Địa chỉ");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Điện thoại");
        txtSoDienThoai.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Điện thoại");
    }

    public void setSupplier(Supplier supplier) {
        // Hiển thị dữ liệu lên các text field
        txtMaNhaCungCap.setText(String.valueOf(supplier.getMaNhaCungCap()));
        txtTenNhaCungCap.setText(supplier.getTenNhaCungCap());
        txtDiaChi.setText(supplier.getDiaChi());
        txtEmail.setText(supplier.getEmail());
        txtSoDienThoai.setText(supplier.getSdt());
        cmoTrangThai.setSelectedIndex(supplier.getTrangThai());

        // Nếu bạn muốn mã nhà cung cấp không được sửa thì có thể disable txtMaNhaCungCap
        txtMaNhaCungCap.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        txtMaNhaCungCap = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenNhaCungCap = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmoTrangThai = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        btnSua = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sửa thông tin nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel3.setMaximumSize(new java.awt.Dimension(100, 100));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã nhà cung cấp");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Trạng thái");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tên nhà cung cấp");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Địa chỉ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Email");

        cmoTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mở khóa", "Khóa" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Số điện thoại");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaNhaCungCap)
                    .addComponent(txtTenNhaCungCap)
                    .addComponent(txtDiaChi)
                    .addComponent(txtEmail)
                    .addComponent(txtSoDienThoai)
                    .addComponent(cmoTrangThai, 0, 278, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmoTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtDiaChi, txtEmail, txtMaNhaCungCap, txtTenNhaCungCap});

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
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
                .addComponent(btnSua)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua)
                    .addComponent(btnHuy))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("^(0|\\+84)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-5]|9[0-9])[0-9]{7}$"); // Ví dụ: 10-11 chữ số
    }

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // Lấy dữ liệu từ các field đúng
        String maNhaCungCapStr = txtMaNhaCungCap.getText().trim();
        String tenNCC = txtTenNhaCungCap.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String email = txtEmail.getText().trim();
        String sdt = txtSoDienThoai.getText().trim();
        int trangThai = cmoTrangThai.getSelectedIndex(); // 0: Hoạt động, 1: Không hoạt động
        String appCurrentUser = zentech.application.Application.getAppInstance().getCurrentUser();
        
        // Kiểm tra mã nhà cung cấp hợp lệ (phải là số và không để trống)
        if (maNhaCungCapStr.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Mã nhà cung cấp không được để trống!, Thiếu thông tin");
            return;
        }
        int maNhaCungCap;
        try {
            maNhaCungCap = Integer.parseInt(maNhaCungCapStr);
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Mã nhà cung cấp phải là số!, Lỗi định dạng");
            return;
        }

        // Kiểm tra trống các trường còn lại
        if (tenNCC.isEmpty() || diaChi.isEmpty() || email.isEmpty() || sdt.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng nhập đầy đủ thông tin!, Thiếu thông tin");
            return;
        }

        // Kiểm tra định dạng email
        if (!isValidEmail(email)) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Email không đúng định dạng! Lỗi định dạng.");
            return;
        }

        // Kiểm tra định dạng số điện thoại
        if (!isValidPhone(sdt)) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Số điện thoại phải có 10-11 chữ số! Vui lòng nhập đúng định dạng");
            return;
        }

        // Tạo đối tượng Supplier với dữ liệu mới
        entity.Supplier supplier = new entity.Supplier();
        supplier.setMaNhaCungCap(maNhaCungCap);
        supplier.setTenNhaCungCap(tenNCC);
        supplier.setDiaChi(diaChi);
        supplier.setEmail(email);
        supplier.setSdt(sdt);
        supplier.setTrangThai(trangThai);
        
        boolean updated = service.updateSupplier(supplier);

        if (updated) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Cập nhật nhà cung cấp thành công!, Thành công");
            ActivityDAO.logActivity(appCurrentUser, "Cập nhật nhà cung cấp: " + tenNCC);
            supplierForm.loadTable(); // cập nhật lại bảng trong SupplierForm
            dispose(); // đóng dialog
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Cập nhật thất bại. Vui lòng thử lại!, Lỗi");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnSua;
    private javax.swing.JComboBox<String> cmoTrangThai;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNhaCungCap;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenNhaCungCap;
    // End of variables declaration//GEN-END:variables
}

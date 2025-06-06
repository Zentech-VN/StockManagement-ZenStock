package zentech.application.dialog;

import dao.ActivityDAO;
import entity.Account;
import entity.Activity;
import entity.Employee;
import entity.PermGroup;
import java.time.LocalDateTime;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import service.AccountDialogService;

public class AccountDialog extends javax.swing.JFrame {

    private AccountDialogService accountService;
    private Object taiKhoan;
    private int manv;
    private boolean isEditMode = false;
    private Employee currentUser; 
            
    //add - constructor cho thêm tài khoản
    public AccountDialog(Object taiKhoan, int manv, Employee currentUser) {
        this.taiKhoan = taiKhoan;
        this.manv = manv;
        this.currentUser = currentUser;
        this.isEditMode = false;

        initComponents();
        initializeService();
        setupUI();
        setLocationRelativeTo(null);
    }

    //add
    public AccountDialog(Object taiKhoan, int manv, Account account, Employee currentUser) {
        this.taiKhoan = taiKhoan;
        this.manv = manv;
        this.currentUser = currentUser;
        this.isEditMode = false;

        initComponents();
        initializeService();
        setupUI();
        loadAccountData(account);
        setLocationRelativeTo(null);
    }

    private AccountDialog() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void initializeService() {
        accountService = new AccountDialogService();
    }

    private void setupUI() {
        //Load dữ liệu cho ComboBox nhóm quyền
        loadPermissionGroups();

        //Load dữ liệu cho ComboBox trạng thái
        loadStatusComboBox();

        //Thêm sự kiện cho nút Hủy
        jButton1.addActionListener(evt -> {
            dispose();
        });
    }

    private void loadPermissionGroups() {
        try {
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (PermGroup pg : accountService.getPermissionGroups()) {
                model.addElement(pg.getTennhomquyen());
            }
            jComboBox1.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách nhóm quyền: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadStatusComboBox() {
        DefaultComboBoxModel<String> statusModel = new DefaultComboBoxModel<>();
        statusModel.addElement("Tạm khóa");
        statusModel.addElement("Hoạt động");
        jComboBox2.setModel(statusModel);
    }

    private void loadAccountData(Account account) {
        if (account != null) {
            txtUsername.setText(account.getUsername());
            //Không hiển thị mật khẩu đã hash
            txtPass.setText("");

            //Set selected permission group
            for (int i = 0; i < accountService.getPermissionGroups().size(); i++) {
                if (accountService.getPermissionGroups().get(i).getManhomquyen() == account.getManhomquyen()) {
                    jComboBox1.setSelectedIndex(i);
                    break;
                }
            }

            //Set selected status
            jComboBox2.setSelectedIndex(account.getTrangthai());
        }
    }

    private boolean validateInput() {
        String username = txtUsername.getText().trim();
        String password = txtPass.getText().trim();

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên đăng nhập!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtUsername.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtPass.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtPass.requestFocus();
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPass = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm tài khoản");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Tên đăng nhập");

        jLabel3.setText("Mật khẩu");

        jLabel4.setText("Nhóm quyền");

        jLabel5.setText("Trạng thái");

        jButton1.setText("Hủy");

        jButton2.setText("Thêm tài khoản");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPass)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jButton1)
                .addGap(71, 71, 71)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Validate input
        if (!validateInput()) {
            return;
        }

        String username = txtUsername.getText().trim();
        String password = txtPass.getText().trim();
        int permGroupIndex = jComboBox1.getSelectedIndex();
        int statusIndex = jComboBox2.getSelectedIndex();
        String appCurrentUser = zentech.application.Application.getAppInstance().getCurrentUser();

        String user;
        if (appCurrentUser != null && !appCurrentUser.trim().isEmpty()) {
            user = appCurrentUser;
//            System.out.println("Using Application.getCurrentUser(): " + user);
        } else if (currentUser != null && currentUser.getAcc() != null && currentUser.getAcc().getUsername() != null) {
            user = currentUser.getAcc().getUsername();
//            System.out.println("Using currentUser.getAcc().getUsername(): " + user);
        } else {
            user = "Unknown";
//            System.out.println("Using fallback: " + user);
        }

        try {
            boolean success;

            if (isEditMode) {
                // Cập nhật tài khoản
                success = accountService.addAccount(manv, username, password, permGroupIndex, statusIndex, taiKhoan);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Cập nhật tài khoản thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật tài khoản thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Thêm tài khoản mới
                success = accountService.addAccount(manv, username, password, permGroupIndex, statusIndex, taiKhoan);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    ActivityDAO.logActivity(user, "Thêm tài khoản: " + username);
                } else {
                    if (accountService.isUsernameExists(username)) {
                        JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        txtUsername.requestFocus();
                        txtUsername.selectAll();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm tài khoản thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}

package zentech.application.changepassword;

import com.formdev.flatlaf.FlatClientProperties;
import dao.AccountDAO;
import helper.BCrypt;
import javax.swing.JOptionPane;
import zentech.application.Login;

public class ChangePassword extends javax.swing.JFrame {

    private String email;

    public ChangePassword() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public ChangePassword(String email) {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.email = email;
        initalUI();
    }

    private void initalUI() {
        pwdPass.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập mật khẩu");
        pwdPass1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Xác nhận mật khẩu");
        
        pwdPass.putClientProperty(FlatClientProperties.STYLE, "" +                        
                        "showRevealButton:true;");
        
        pwdPass1.putClientProperty(FlatClientProperties.STYLE, "" +                        
                        "showRevealButton:true;");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        pwdPass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        pwdPass1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Hãy nhập mật khẩu mới với ít nhất 6 kí tự.");

        jButton1.setText("Xác nhận");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Mật khẩu mới");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(pwdPass1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pwdPass))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(pwdPass, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pwdPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String pass = pwdPass.getText().trim();
        String pass1 = pwdPass.getText().trim();

        if (pass.equals("") || pass1.equals("") || pass.length() <= 0 || pass1.length() <= 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
        }

        if (pass.equals(pass1)) {
            Login lg = new Login();
            String password = BCrypt.hashpw(pass, BCrypt.gensalt(12));
            AccountDAO.getInstance().updatePass(this.email, password);
            AccountDAO.getInstance().sendOpt(pass, "null");
            JOptionPane.showMessageDialog(this, "Thay đổi mật khẩu thành công!");
            this.dispose();
            lg.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Mật khẩu không khớp");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField pwdPass;
    private javax.swing.JPasswordField pwdPass1;
    // End of variables declaration//GEN-END:variables
}

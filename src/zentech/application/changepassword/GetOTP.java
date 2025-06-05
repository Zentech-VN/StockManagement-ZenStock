package zentech.application.changepassword;

import dao.AccountDAO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class GetOTP extends javax.swing.JFrame {

    private String email;

    public GetOTP() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public GetOTP(String email) {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.email = email;
        setupOTPFields();
    }

    private void setupOTPFields() {
        javax.swing.JTextField[] otpFields = {txtOtp1, txtOtp2, txtOtp3, txtOtp4, txtOtp5, txtOtp6};

        for (int i = 0; i < otpFields.length; i++) {
            final int index = i;

            otpFields[i].setDocument(new javax.swing.text.PlainDocument() {
                @Override
                public void insertString(int offs, String str, javax.swing.text.AttributeSet a)
                        throws javax.swing.text.BadLocationException {
                    if (str != null && getLength() == 0 && str.matches("\\d")) {
                        super.insertString(offs, str, a);
                    }
                }
            });

            otpFields[i].addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent e) {
                    if (e.getKeyCode() == java.awt.event.KeyEvent.VK_BACK_SPACE
                            && index > 0 && otpFields[index].getText().isEmpty()) {
                        otpFields[index - 1].requestFocus();
                        otpFields[index - 1].setText("");
                    }
                }

                @Override
                public void keyReleased(java.awt.event.KeyEvent e) {
                    String text = otpFields[index].getText();
                    if (!text.matches("\\d")) {
                        otpFields[index].setText("");
                        return;
                    }
                    if (text.length() == 1 && index < otpFields.length - 1) {
                        otpFields[index + 1].requestFocus();
                    }
                    validateOTPFields();
                }
            });
        }

        validateOTPFields();
    }

    private void validateOTPFields() {
        boolean allFilled = !txtOtp1.getText().isEmpty()
                && !txtOtp2.getText().isEmpty()
                && !txtOtp3.getText().isEmpty()
                && !txtOtp4.getText().isEmpty()
                && !txtOtp5.getText().isEmpty()
                && !txtOtp6.getText().isEmpty();

        btnXacNhan.setEnabled(allFilled);
    }

    private String getOTPValue() {
        return txtOtp1.getText()
                + txtOtp2.getText()
                + txtOtp3.getText()
                + txtOtp4.getText()
                + txtOtp5.getText()
                + txtOtp6.getText();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnXacNhan = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOtp1 = new javax.swing.JTextField();
        txtOtp2 = new javax.swing.JTextField();
        txtOtp3 = new javax.swing.JTextField();
        txtOtp4 = new javax.swing.JTextField();
        txtOtp5 = new javax.swing.JTextField();
        txtOtp6 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Nhập mã OPT xác thực");

        jLabel1.setText("Vui lòng nhập mã gồm 6 chữ số đã được gửi");

        jLabel2.setText("Bạn có thời gian là 5 phút tính từ khi gửi mã OPT!");

        txtOtp1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txtOtp1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtOtp2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txtOtp2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtOtp3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txtOtp3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtOtp4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txtOtp4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtOtp5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txtOtp5.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtOtp6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txtOtp6.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtOtp1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOtp2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOtp3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOtp4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOtp5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOtp6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtOtp1, txtOtp2, txtOtp3, txtOtp4, txtOtp5, txtOtp6});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOtp1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOtp2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOtp3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOtp4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOtp5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOtp6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtOtp1, txtOtp2, txtOtp3, txtOtp4, txtOtp5, txtOtp6});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        String otp = getOTPValue().trim();
        ChangePassword cg = new ChangePassword(email);
        if (otp.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống mã OTP");
        } else {
            Pattern digitPattern = Pattern.compile("\\d{6}");
            Matcher matcher = digitPattern.matcher(otp);
            if (matcher.matches() == false) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã OTP có 6 chữ số!");
            } else {
                boolean check = AccountDAO.getInstance().checkOtp(this.email, otp);
                if (check) {
                    this.dispose();
                    cg.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Mã OTP không khớp");
                }
            }
        }
    }//GEN-LAST:event_btnXacNhanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtOtp1;
    private javax.swing.JTextField txtOtp2;
    private javax.swing.JTextField txtOtp3;
    private javax.swing.JTextField txtOtp4;
    private javax.swing.JTextField txtOtp5;
    private javax.swing.JTextField txtOtp6;
    // End of variables declaration//GEN-END:variables
}

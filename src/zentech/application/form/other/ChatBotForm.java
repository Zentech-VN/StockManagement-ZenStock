package zentech.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import service.ChatBotService;
import service.ProductService;

public class ChatBotForm extends javax.swing.JPanel {

    private ProductService productService;
    private ChatBotService chatBotService;
    
    public ChatBotForm() {
        initComponents();
        initalUI();
        chatBotService = new ChatBotService();
    }

    private void initalUI() {
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Hỗ trợ AI");
        jTextArea1.setEditable(false);
    }
    
    private void appendMessage(String message, Color color) {
        jTextArea1.setForeground(color);
        jTextArea1.append(message + "\n\n");
        jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        txtSearch = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[fill][grow 0]",
            new String[]{
                "",
                ""
            }
        ));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        crazyPanel1.add(jScrollPane2);

        crazyPanel3.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "JTextField.placeholderText=Search;background:@background",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel3.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[]",
            "",
            new String[]{
                "width 400"
            }
        ));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel3.add(txtSearch);

        btnSend.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSend.setText("Gửi");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });
        crazyPanel3.add(btnSend);

        crazyPanel1.add(crazyPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        String userMessage = txtSearch.getText().trim();
        if (!userMessage.isEmpty()) {
            appendMessage("Bạn: " + userMessage, Color.BLUE);
            String response = chatBotService.sendMessage(userMessage);
            appendMessage("AI: " + response, new Color(34, 139, 34));
            txtSearch.setText(null);
        }
    }//GEN-LAST:event_btnSendActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

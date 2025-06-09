package zentech.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import service.ChatBotService;
import service.ProductService;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import zentech.menu.mode.RoundedPanel;

public class ChatBotForm extends javax.swing.JPanel {

    private ProductService productService;
    private ChatBotService chatBotService;
    private JPanel chatPanel;
    private final String[] hints = {
        "Hỏi ZenAI bất cứ điều gì...",
        "Bạn cần giúp gì?",
        "Tôi có thể giúp gì cho bạn?"
    };
    private JLabel aiThinkingLabel = null;

    public ChatBotForm() {
        initComponents();
        initalUI();
        chatBotService = new ChatBotService();
        eventEnter();
        initChatPanel();
    }

    private String getRandomHint() {
        int index = (int) (Math.random() * hints.length);
        return hints[index];
    }

    private void initChatPanel() {
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(chatPanel);
    }

    private void initalUI() {
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, getRandomHint());
        jTextArea1.setEditable(false);
    }

    private void appendMessage(String message, boolean isUser) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        String html = "<html><div style='width: 260px;'>"
                + "<div style='font-size: 14px; color: white;'>" + message + "</div>"
                + "<div style='font-size: 10px; color: #ddd; text-align: right; margin-top: 4px;'>" + time + "</div>"
                + "</div></html>";

        JLabel messageLabel = new JLabel(html);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        RoundedPanel bubble = new RoundedPanel(20);
        bubble.setBackground(Color.BLACK); 
        bubble.setLayout(new BorderLayout());
        bubble.add(messageLabel, BorderLayout.CENTER);
        bubble.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        JPanel wrapper = new JPanel(new FlowLayout(isUser ? FlowLayout.RIGHT : FlowLayout.LEFT));
        wrapper.setOpaque(false);
        wrapper.add(bubble);

        chatPanel.add(wrapper);
        chatPanel.revalidate();
        chatPanel.repaint();

        JScrollBar vertical = jScrollPane2.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }

    public void eventEnter() {
        txtSearch.addActionListener(e -> {
            String mess = txtSearch.getText();
            if (!mess.isEmpty()) {
                btnSend.doClick();
            } else {
                txtSearch.requestFocus();
            }
        });
    }

    private JLabel appendTempAIThinking() {
        JLabel thinkingLabel = new JLabel(generateMessageHtml("...", true, "Đang gửi"));
        thinkingLabel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        RoundedPanel bubble = new RoundedPanel(20);
        bubble.setBackground(Color.BLACK);
        bubble.setLayout(new BorderLayout());
        bubble.add(thinkingLabel, BorderLayout.CENTER);
        bubble.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wrapper.setOpaque(false);
        wrapper.add(bubble);
        chatPanel.add(wrapper);
        chatPanel.revalidate();
        chatPanel.repaint();
        JScrollBar vertical = jScrollPane2.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
        return thinkingLabel;
    }

    private String generateMessageHtml(String message, boolean isAI, String status) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));

        String statusHtml = "";
        if (isAI && status != null && !status.isEmpty()) {
            statusHtml = "<div style='font-size: 10px; color: #888; text-align: right; margin-top: 2px;'>" + status + "</div>";
        }

        return "<html><div style='width: 260px;'>"
                + "<div style='font-size: 14px; color: white;'>" + message + "</div>"
                + "<div style='font-size: 10px; color: #ccc; text-align: right; margin-top: 4px;'>" + time + "</div>"
                + statusHtml
                + "</div></html>";
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
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setPreferredSize(new java.awt.Dimension(200, 104));
        jTextArea1.setRequestFocusEnabled(false);
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
            "push[][][]push",
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
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1319, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        String userMessage = txtSearch.getText().trim();
        if (!userMessage.isEmpty()) {
            appendMessage("Bạn: " + userMessage, true);

            aiThinkingLabel = appendTempAIThinking();

            new Thread(() -> {
                String response = chatBotService.sendMessage(userMessage);

                if (aiThinkingLabel != null) {
                    aiThinkingLabel.setText(generateMessageHtml(response, true, "Đã nhận"));
                    chatPanel.revalidate();
                    chatPanel.repaint();
                }
            }).start();

            txtSearch.setText(null);
            txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, getRandomHint());
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

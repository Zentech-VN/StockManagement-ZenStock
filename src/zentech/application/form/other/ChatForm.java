package zentech.application.form.other;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ChatForm extends javax.swing.JPanel {

    private JList<String> userList;
    private DefaultListModel<String> userListModel;
    private JPanel chatPanelContainer;
    private CardLayout cardLayout;
    private Map<String, JPanel> chatAreas;      // Chứa từng khung tin nhắn theo người dùng
    private Map<String, JScrollPane> scrollPanes; // Chứa scrollPane tương ứng cho mỗi khung chat

    public ChatForm() {
        initComponents();
        initializeChatUI();
    }

    private void initializeChatUI() {
        setLayout(new BorderLayout());

        // Danh sách người dùng giả lập
        String[] users = {"Nguyễn Văn A", "Trần Thị B", "Lê Văn C"};
        userListModel = new DefaultListModel<>();
        for (String user : users) {
            userListModel.addElement(user);
        }

        // --- Left Panel: Danh sách người dùng ---
        userList = new JList<>(userListModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userList.setForeground(Color.WHITE);
        userList.setBackground(new Color(45, 45, 45));
        userList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane userScroll = new JScrollPane(userList);
        userScroll.setPreferredSize(new Dimension(220, 0));
        userScroll.setBorder(BorderFactory.createEmptyBorder());
        add(userScroll, BorderLayout.WEST);

        // --- Center Panel: Khung chat động ---
        chatPanelContainer = new JPanel();
        cardLayout = new CardLayout();
        chatPanelContainer.setLayout(cardLayout);
        chatAreas = new HashMap<>();
        scrollPanes = new HashMap<>();

        for (String user : users) {
            JPanel panel = createChatPanel(user);
            chatPanelContainer.add(panel, user);
        }
        add(chatPanelContainer, BorderLayout.CENTER);

        // --- Xử lý chuyển người dùng ---
        userList.addListSelectionListener(e -> {
            String selectedUser = userList.getSelectedValue();
            if (selectedUser != null) {
                cardLayout.show(chatPanelContainer, selectedUser);
            }
        });

        // --- Tin nhắn mẫu ---
        addMessage("Nguyễn Văn A", "Nguyễn Văn A", "Chào bạn, bạn khỏe không?", "09:01");
        addMessage("Nguyễn Văn A", "Me", "Mình ổn, cảm ơn bạn nhé!", "09:02");
    }

    // Tạo khung chat cho từng người dùng
    private JPanel createChatPanel(String username) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30));

        // Khung chứa tin nhắn
        JPanel messageArea = new JPanel();
        messageArea.setLayout(new BoxLayout(messageArea, BoxLayout.Y_AXIS));
        messageArea.setBackground(new Color(30, 30, 30));

        JScrollPane scrollPane = new JScrollPane(new JPanel(new BorderLayout()) {
            {
                setBackground(new Color(30, 30, 30));
                add(messageArea, BorderLayout.NORTH);
            }
        });
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        panel.add(scrollPane, BorderLayout.CENTER);
        chatAreas.put(username, messageArea);
        scrollPanes.put(username, scrollPane);

        // Input Panel
        JPanel inputPanel = new JPanel(new BorderLayout(5, 0));
        inputPanel.setBorder(new EmptyBorder(8, 10, 8, 10));
        inputPanel.setBackground(new Color(30, 30, 30));
        inputPanel.setPreferredSize(new Dimension(0, 80));

        // Ô nhập tin nhắn
        JTextArea messageField = new JTextArea(3, 20);
        messageField.setLineWrap(true);
        messageField.setWrapStyleWord(true);
        messageField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageField.setForeground(Color.WHITE);
        messageField.setBackground(new Color(43, 45, 48));
        messageField.setCaretColor(Color.WHITE);
        messageField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100), 1, true),
                BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));
        messageField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            public void removeUpdate(DocumentEvent e) {
                update();
            }

            public void changedUpdate(DocumentEvent e) {
                update();
            }

            private void update() {
                messageField.setCaretPosition(messageField.getDocument().getLength());
            }
        });

        JScrollPane messageScroll = new JScrollPane(messageField);
        messageScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        messageScroll.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        messageScroll.setViewportBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1, true));

        // Nút gửi + emoji + ảnh
        JButton sendButton = new JButton("Gửi");
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sendButton.setBackground(new Color(30, 144, 255));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setPreferredSize(new Dimension(40, 40));

        inputPanel.add(messageScroll, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        panel.add(inputPanel, BorderLayout.SOUTH);

        // Phím tắt: Enter để gửi
        messageField.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "sendMessage");
        messageField.getInputMap().put(KeyStroke.getKeyStroke("shift ENTER"), "insert-newline");
        messageField.getActionMap().put("sendMessage", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!messageField.isFocusOwner()) {
                    return;
                }
                String text = messageField.getText().trim();
                if (!text.isEmpty()) {
                    String time = LocalTime.now().toString().substring(0, 5);
                    addMessage(username, "Me", text, time);
                    messageField.setText("");
                }
            }
        });
        messageField.getActionMap().put("insert-newline", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageField.append("\n");
            }
        });

        sendButton.addActionListener(e -> {
            String text = messageField.getText().trim();
            if (!text.isEmpty()) {
                String time = LocalTime.now().toString().substring(0, 5);
                addMessage(username, "Me", text, time);
                messageField.setText("");
            }
        });

        return panel;
    }

    private JTextArea createMessageBubble(String sender, String message, String time) {
        String text = message + "\n" + time;
        JTextArea bubble = new JTextArea(text);
        bubble.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        bubble.setForeground(Color.WHITE);
        bubble.setBackground("Me".equals(sender) ? new Color(30, 144, 255) : new Color(60, 60, 60));
        bubble.setLineWrap(true);
        bubble.setWrapStyleWord(true);
        bubble.setEditable(false);
        bubble.setOpaque(true);
        bubble.setFocusable(false);
        bubble.setHighlighter(null);
        bubble.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 80), 1, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        bubble.setAlignmentX(Component.LEFT_ALIGNMENT);
        bubble.setMaximumSize(new Dimension(600, Integer.MAX_VALUE));
        return bubble;
    }

    // Thêm tin nhắn mới vào khung chat
    private void addMessage(String username, String sender, String message, String time) {
        JPanel chatPanel = chatAreas.get(username);
        JScrollPane scrollPane = scrollPanes.get(username);

        JPanel outerWrapper = new JPanel();
        outerWrapper.setLayout(new BoxLayout(outerWrapper, BoxLayout.X_AXIS));
        outerWrapper.setOpaque(false);
        outerWrapper.setBorder(new EmptyBorder(6, 14, 6, 14));

        JTextArea bubble = createMessageBubble(sender, message, time);

        if ("Me".equals(sender)) {
            outerWrapper.add(Box.createHorizontalGlue());
            outerWrapper.add(bubble);
        } else {
            outerWrapper.add(bubble);
            outerWrapper.add(Box.createHorizontalGlue());
        }

        chatPanel.add(outerWrapper);
        chatPanel.revalidate();
        chatPanel.repaint();

        // Tự động cuộn xuống dòng cuối
        SwingUtilities.invokeLater(() -> {
            JScrollBar vBar = scrollPane.getVerticalScrollBar();
            vBar.setValue(vBar.getMaximum());
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

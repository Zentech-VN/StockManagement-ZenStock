package zentech.application.form.other;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ChatForm extends javax.swing.JPanel {

    private JList<String> userList;
    private DefaultListModel<String> userListModel;
    private JPanel chatPanelContainer;
    private CardLayout cardLayout;
    private Map<String, JPanel> chatAreas;
    private Map<String, JScrollPane> scrollPanes;

    public ChatForm() {
        initComponents();
        initializeChatUI();
    }

    private void initializeChatUI() {
        setLayout(new BorderLayout());

        String[] users = {"Nguyễn Văn A", "Trần Thị B", "Lê Văn C"};
        userListModel = new DefaultListModel<>();
        for (String user : users) {
            userListModel.addElement(user);
        }

        userList = new JList<>(userListModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userList.setForeground(Color.BLACK);
        userList.setBackground(new Color(240, 240, 240));
        userList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane userScroll = new JScrollPane(userList);
        userScroll.setPreferredSize(new Dimension(220, 0));
        userScroll.setBorder(BorderFactory.createEmptyBorder());
        add(userScroll, BorderLayout.WEST);

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

        userList.addListSelectionListener(e -> {
            String selectedUser = userList.getSelectedValue();
            if (selectedUser != null) {
                cardLayout.show(chatPanelContainer, selectedUser);
            }
        });

        addMessage("Nguyễn Văn A", "Nguyễn Văn A", "Chào bạn, bạn khỏe không?", "09:01");
        addMessage("Nguyễn Văn A", "Me", "Mình ổn, cảm ơn bạn nhé!", "09:02");
    }

    private JPanel createChatPanel(String username) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JPanel messageArea = new JPanel();
        messageArea.setLayout(new BoxLayout(messageArea, BoxLayout.Y_AXIS));
        messageArea.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(new JPanel(new BorderLayout()) {
            {
                setBackground(Color.WHITE);
                add(messageArea, BorderLayout.NORTH);
            }
        });
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(Color.WHITE);

        panel.add(scrollPane, BorderLayout.CENTER);
        chatAreas.put(username, messageArea);
        scrollPanes.put(username, scrollPane);

        JPanel inputPanel = new JPanel(new BorderLayout(5, 0));
        inputPanel.setBorder(new EmptyBorder(8, 10, 8, 10));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setPreferredSize(new Dimension(0, 80));

        JTextArea messageField = new JTextArea(3, 20);
        messageField.setLineWrap(true);
        messageField.setWrapStyleWord(true);
        messageField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageField.setForeground(Color.BLACK);
        messageField.setBackground(new Color(245, 245, 245));
        messageField.setCaretColor(Color.BLACK);
        messageField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
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
        messageScroll.setViewportBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));

        JButton sendButton = new JButton("Gửi");
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sendButton.setBackground(new Color(0, 120, 215));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setPreferredSize(new Dimension(40, 40));

        inputPanel.add(messageScroll, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        panel.add(inputPanel, BorderLayout.SOUTH);

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
        bubble.setForeground("Me".equals(sender) ? Color.WHITE : Color.BLACK);
        bubble.setLineWrap(true);
        bubble.setWrapStyleWord(true);
        bubble.setEditable(false);
        bubble.setOpaque(true);
        bubble.setFocusable(false);
        bubble.setHighlighter(null);

        if ("Me".equals(sender)) {
            bubble.setBackground(new Color(0x005EB8)); // xanh dương đậm
            bubble.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0x005EB8), 1, true),
                    BorderFactory.createEmptyBorder(10, 16, 10, 16)
            ));
        } else {
            bubble.setBackground(new Color(240, 240, 240));
            bubble.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                    BorderFactory.createEmptyBorder(10, 16, 10, 16)
            ));
        }

        bubble.setAlignmentX(Component.LEFT_ALIGNMENT);
        bubble.setMaximumSize(new Dimension(600, Integer.MAX_VALUE));
        return bubble;
    }

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
            .addGap(0, 775, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

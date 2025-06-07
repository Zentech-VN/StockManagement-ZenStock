package zentech.menu2;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import zentech.application.Application;
import zentech.application.form.other.ChatBotForm;

public class ChatBot extends JPanel {

    private final JButton btn;
    private boolean collapsed = false;

    public ChatBot() {
        setOpaque(false);
        setLayout(new BorderLayout());

        Icon icon = new FlatSVGIcon("zentech/icon/svg/chat-bot.svg", 22, 22);

        btn = new JButton("Hỗ trợ AI", icon);
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn.setIconTextGap(10);
        btn.addActionListener(e -> openChatbotScreen());

        btn.putClientProperty(FlatClientProperties.STYLE,
                "font:$Menu.font;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "foreground:$Menu.foreground;"
                + "background:$Menu.button.background;"
                + "hoverBackground:$Menu.button.hoverBackground;");

        add(btn, BorderLayout.CENTER);
        updateLook();
    }

    public void setCollapsed(boolean value) {
        if (collapsed == value) {
            return;
        }
        collapsed = value;
        updateLook();
    }

    private void updateLook() {
        if (collapsed) {
            btn.setText("");
            btn.setPreferredSize(new Dimension(40, 40));
            btn.putClientProperty(FlatClientProperties.STYLE,
                    "arc:20; borderWidth:0; background:$Menu.button.background;");
        } else {
            btn.setText("Chatbot");
            btn.setPreferredSize(new Dimension(200, 40));
            btn.putClientProperty(FlatClientProperties.STYLE,
                    "arc:999; borderWidth:0; background:$Menu.button.background;");
        }
        revalidate();
        repaint();
    }

    private void openChatbotScreen() {
        Application.showForm(new ChatBotForm());
        System.out.println("Chatbot click!");
    }
}

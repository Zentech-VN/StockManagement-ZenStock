package zentech.application.form2;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.UIScale;
import entity.Employee;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import zentech.application.Application;
import zentech.application.form.other.AccountForm;
import zentech.application.form.other.ActivityLogForm;
import zentech.application.form.other.AttributeForm;
import zentech.application.form.other.ChatForm;
import zentech.application.form.other.CustomerManagement;
import zentech.application.form.other.DeliveryNoteForm;
import zentech.application.form.other.DestructionReleaseNoteForm;
import zentech.application.form.other.EmployeeForm;
import zentech.application.form.other.FormHomePage;
import zentech.application.form.other.FormRole;
import zentech.application.form.other.ImportForm;
import zentech.application.form.other.ProductForm;
import zentech.application.form.other.SupplierForm;
import zentech.application.form.other.VoteApprovalForm;
import zentech.application.form.other.WarehouseManagementForm;
import zentech.menu2.Menu;
import zentech.menu2.MenuAction;

public class MainForm extends JLayeredPane {

    private Menu menu;
    Employee acc;

    public MainForm(Employee acc) {
        this.acc = acc;

        init(acc);
    }

    private void init(Employee acc) {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new MainFormLayout());
        menu = new Menu(acc);
        panelBody = new JPanel(new BorderLayout());
        initMenuArrowIcon();
        menuButton.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.button.background;"
                + "arc:999;"
                + "focusWidth:0;"
                + "borderWidth:0");
        menuButton.addActionListener((ActionEvent e) -> {
            setMenuFull(!menu.isMenuFull());
        });
        initMenuEvent();
        setLayer(menuButton, JLayeredPane.POPUP_LAYER);
        add(menuButton);
        add(menu);
        add(panelBody);
    }

    @Override
    public void applyComponentOrientation(ComponentOrientation o) {
        super.applyComponentOrientation(o);
        initMenuArrowIcon();
    }

    private void initMenuArrowIcon() {
        if (menuButton == null) {
            menuButton = new JButton();
        }
        String icon = (getComponentOrientation().isLeftToRight()) ? "menu_left.svg" : "menu_right.svg";
        menuButton.setIcon(new FlatSVGIcon("zentech/icon/svg/" + icon, 0.8f));
    }

    private void initMenuEvent() {
        menu.addMenuEvent((int index, int subIndex, MenuAction action) -> {
            // Application.mainForm.showForm(new DefaultForm("Form : " + index + " " + subIndex));
            if (index == 0) {
                Application.showForm(new FormHomePage());
            } else if (index == 1) {
                Application.showForm(new AccountForm());
            } else if (index == 2) {
                Application.showForm(new EmployeeForm());
            } else if (index == 3) {
                Application.showForm(new FormRole());
            } else if (index == 4) {
                Application.showForm(new ActivityLogForm());
            } else if (index == 5) {
                Application.showForm(new CustomerManagement());
            } else if (index == 6) {
                Application.showForm(new SupplierForm());
            } else if (index == 7) {
                Application.showForm(new ProductForm());
            } else if (index == 8) {
                Application.showForm(new WarehouseManagementForm());
            } else if (index == 9) {
                Application.showForm(new DeliveryNoteForm());
            } else if (index == 10) {
                Application.showForm(new ImportForm());
            } else if (index == 11) {
                Application.showForm(new AttributeForm());
            } else if (index == 12) {
                Application.showForm(new ChatForm());
            } else if (index == 13) {
                Application.showForm(new DestructionReleaseNoteForm());
            } else if (index == 14) {
                Application.showForm(new VoteApprovalForm());
            } else if (index == 15) {
                Application.logout();
            } else {
                action.cancel();
            }
        });
    }

    private void setMenuFull(boolean full) {
        String icon;
        if (getComponentOrientation().isLeftToRight()) {
            icon = (full) ? "menu_left.svg" : "menu_right.svg";
        } else {
            icon = (full) ? "menu_right.svg" : "menu_left.svg";
        }
        menuButton.setIcon(new FlatSVGIcon("zentech/icon/svg/" + icon, 0.8f));
        menu.setMenuFull(full);
        revalidate();
    }

    public void hideMenu() {
        menu.hideMenuItem();
    }

    public void showForm(Component component) {
        panelBody.removeAll();
        panelBody.add(component);
        panelBody.repaint();
        panelBody.revalidate();
    }

    public void setSelectedMenu(int index, int subIndex) {
        menu.setSelectedMenu(index, subIndex);
    }

    private JPanel panelBody;
    private JButton menuButton;

    private class MainFormLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(5, 5);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                boolean ltr = parent.getComponentOrientation().isLeftToRight();
                Insets insets = UIScale.scale(parent.getInsets());
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - (insets.left + insets.right);
                int height = parent.getHeight() - (insets.top + insets.bottom);
                int menuWidth = UIScale.scale(menu.isMenuFull() ? menu.getMenuMaxWidth() : menu.getMenuMinWidth());
                int menuX = ltr ? x : x + width - menuWidth;
                menu.setBounds(menuX, y, menuWidth, height);
                int menuButtonWidth = menuButton.getPreferredSize().width;
                int menuButtonHeight = menuButton.getPreferredSize().height;
                int menubX;
                if (ltr) {
                    menubX = (int) (x + menuWidth - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.3f)));
                } else {
                    menubX = (int) (menuX - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.7f)));
                }
                menuButton.setBounds(menubX, UIScale.scale(30), menuButtonWidth, menuButtonHeight);
                int gap = UIScale.scale(5);
                int bodyWidth = width - menuWidth - gap;
                int bodyHeight = height;
                int bodyx = ltr ? (x + menuWidth + gap) : x;
                int bodyy = y;
                panelBody.setBounds(bodyx, bodyy, bodyWidth, bodyHeight);
            }
        }
    }
}

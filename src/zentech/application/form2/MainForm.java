package zentech.application.form2;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.UIScale;
import dao.UserRightsDAO;
import entity.ChiTietQuyen;
import entity.Employee;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import raven.toast.Notifications;
import zentech.application.Application;
import zentech.application.form.other.AccountForm;
import zentech.application.form.other.AttributeForm;
import zentech.application.form.other.ChatForm;
import zentech.application.form.other.CustomerManagement;
import zentech.application.form.other.EmployeeForm;
import zentech.application.form.other.FormHomePage;
import zentech.application.form.other.UserRightsForm;
import zentech.application.form.other.ProductForm;
import zentech.application.form.other.SupplierForm;
import zentech.application.form.other.WarehouseManagementForm;
import zentech.application.form.other.WarehouseReceiptForm;
import zentech.application.form.other.Chart;
import zentech.application.form.other.ChatForm;
import zentech.application.form.other.ReceiptApprovalForm;
import zentech.application.form.other.WarehouseDeliveryForm;
import zentech.menu2.Menu;
import zentech.menu2.MenuAction;

public class MainForm extends JLayeredPane {

    private Menu menu;
    Employee acc;
    private UserRightsDAO urd = new UserRightsDAO();

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

    public boolean check(List<ChiTietQuyen> list, String machucnang) {
        for (ChiTietQuyen chitietquyen : list) {
            if (chitietquyen.getDanhmuc_chucnang().getMachucnang() != null && chitietquyen.getDanhmuc_chucnang().getMachucnang().equals(machucnang)) {
                return true;
            }
        }
        return false;
    }

    private void initMenuEvent() {
        List<ChiTietQuyen> list = urd.getALLCTQbyMaNHomQuyen(this.acc.getAcc().getManhomquyen());

        menu.addMenuEvent((int index, int subIndex, MenuAction action) -> {
            // Application.mainForm.showForm(new DefaultForm("Form : " + index + " " + subIndex));
            if (index == 0) {
                Application.showForm(new FormHomePage());
            } else if (index == 1) {
                if (check(list, "thongke") == false) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Bạn không có quyền sài chức năng này!");
                    return;
                }
                Application.showForm(new Chart());
            } else if (index == 2) {
                if (check(list, "taikhoan") == false) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Bạn không có quyền sài chức năng này!");
                    return;
                }
                Application.showForm(new AccountForm(acc));
            } else if (index == 3) {
                if (check(list, "nhanvien") == false) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Bạn không có quyền sài chức năng này!");
                    return;
                }
                Application.showForm(new EmployeeForm(acc));
            } else if (index == 4) {
                if (check(list, "nhomquyen") == false) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Bạn không có quyền sài chức năng này!");
                    return;
                }
                Application.showForm(new UserRightsForm(acc));
            } else if (index == 5) {
                if (check(list, "sanpham") == false) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Bạn không có quyền sài chức năng này!");
                    return;
                }
                Application.showForm(new ProductForm(acc));
            } else if (index == 6) {
                if (check(list, "khuvuckho") == false) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Bạn không có quyền sài chức năng này!");
                    return;
                }
                Application.showForm(new WarehouseManagementForm(acc));
            } else if (index == 7) {
                if (check(list, "phieunhap") == false) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Bạn không có quyền sài chức năng này!");
                    return;
                }
                Application.showForm(new WarehouseReceiptForm(acc));
            } else if (index == 8) {
                if (check(list, "phieuxuat") == false) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Bạn không có quyền sài chức năng này!");
                    return;
                }
                Application.showForm(new WarehouseDeliveryForm(acc));
            } else if (index == 9) {
                if (check(list, "duyetphieu") == false) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Bạn không có quyền sài chức năng này!");
                    return;
                }
                Application.showForm(new ReceiptApprovalForm());
            } else if (index == 10) {
                if (check(list, "thuoctinh") == false) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Bạn không có quyền sài chức năng này!");
                    return;
                }
                Application.showForm(new AttributeForm(acc));
            } else if (index == 11) {
                if (check(list, "khachhang") == false) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Bạn không có quyền sài chức năng này!");
                    return;
                }
                Application.showForm(new CustomerManagement(acc));
            } else if (index == 12) {
                if (check(list, "nhacungcap") == false) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Bạn không có quyền sài chức năng này!");
                    return;
                }
                Application.showForm(new SupplierForm(acc));
            } else if (index == 13) {
                Application.showForm(new ChatForm());
            } else if (index == 14) {
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

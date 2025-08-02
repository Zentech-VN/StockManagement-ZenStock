package zentech.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import dao.AccountDAO;
import dao.PermGroupDAO;
import java.util.ArrayList;
import entity.Account;
import entity.PermGroup;
import java.awt.Window;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import service.AccountService;
import zentech.application.dialog.EditAccountDialog;
import zentech.application.dialog.StaffListDialog;

public class AccountForm extends javax.swing.JPanel {

    private AccountService asv = new AccountService();
    private ArrayList<Account> lista = asv.getTaiKhoanAll();
    String appCurrentUser = zentech.application.Application.getAppInstance().getCurrentUser();

    public AccountForm() {
        initComponents();
        initalUI(tblList);
        loadTable(lista, tblList);
    }

    public AccountForm(Account currentUser) {
        initComponents();
        initalUI(tblList);
        loadTable(lista, tblList);
    }

    public void loadTable(List<Account> accounts, JTable jTable1) {
        SwingWorker<DefaultTableModel, Void> worker = new SwingWorker<DefaultTableModel, Void>() {
            @Override
            protected DefaultTableModel doInBackground() {
                String[] title = {"Mã nhân viên", "Tên đăng nhập", "Nhóm quyền", "Trạng thái"};
                DefaultTableModel model = new DefaultTableModel(title, 0);

                for (Account account : accounts) {
                    String trangthaiString;
                    switch (account.getTrangthai()) {
                        case 1:
                            trangthaiString = "Hoạt động";
                            break;
                        case 0:
                            trangthaiString = "Ngưng hoạt động";
                            break;
                        default:
                            trangthaiString = "Không xác định";
                            break;
                    }

                    String tenNhomQuyen = getPermGroup(account.getManhomquyen()).getTennhomquyen();

                    model.addRow(new Object[]{
                        account.getManv(),
                        account.getUsername(),
                        tenNhomQuyen,
                        trangthaiString
                    });
                }

                return model;
            }

            @Override
            protected void done() {
                try {
                    jTable1.setModel(get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }

    public static PermGroup getPermGroup(int manhom) {
        return PermGroupDAO.selectById(manhom + "");
    }

    public int getRowSelected() {
        int index = tblList.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản");
        }
        return index;
    }

    private void initalUI(JTable table) {
        table.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18));

        JScrollPane scroll = (JScrollPane) table.getParent().getParent();
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Table.background;"
                + "track:$Table.background;"
                + "trackArc:999");

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        table.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        txtSearcha = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            new String[]{
                ""
            }
        ));

        crazyPanel2.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "JTextField.placeholderText=Search;background:@background",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][][][]",
            "",
            new String[]{
                "width 400"
            }
        ));

        txtSearcha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchaActionPerformed(evt);
            }
        });
        txtSearcha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchaKeyReleased(evt);
            }
        });
        crazyPanel2.add(txtSearcha);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        crazyPanel2.add(jButton1);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        crazyPanel2.add(jButton2);

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        crazyPanel2.add(jButton3);

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Làm mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        crazyPanel2.add(jButton4);

        crazyPanel1.add(crazyPanel2);

        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên đăng nhập", "Nhóm quyền", "Trạng thái"
            }
        ));
        jScrollPane1.setViewportView(tblList);

        crazyPanel1.add(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Window parent = SwingUtilities.getWindowAncestor(this);
        StaffListDialog sl = new StaffListDialog(parent);
        sl.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int index = getRowSelected();

        //Kiểm tra index hợp lệ và danh sách không rỗng
        if (index != -1 && lista != null && index < lista.size()) {
            Account selectedAccount = lista.get(index);
            Window parent = SwingUtilities.getWindowAncestor(this);
            EditAccountDialog ead = new EditAccountDialog(parent, this, selectedAccount.getManv(), selectedAccount);
            ead.setVisible(true);
        } else {
            if (lista == null || lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Danh sách tài khoản đang trống. Vui lòng tải lại dữ liệu!",
                        "Thông báo", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài khoản để chỉnh sửa!",
                        "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int index = getRowSelected();
        String user = appCurrentUser;
        String hoTen = tblList.getValueAt(index, 1).toString();

        if (index != -1) {
            int input = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc chắn muốn xóa tài khoản!", "Xóa tài khoản",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (input == 0) {
                //xóa trong database
                int result = AccountDAO.getInstance().delete(lista.get(index).getManv() + "");

                if (result > 0) {
                    //Cập nhật lại danh sách lista từ database
                    lista = asv.getTaiKhoanAll();

                    //reload table
                    loadTable(lista, tblList);

                    JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công!",
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa tài khoản thất bại!",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        lista = asv.getTaiKhoanAll();
        loadTable(lista, tblList);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtSearchaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchaKeyReleased
        String txt = txtSearcha.getText();
        asv.LoadTableWithSearch(txt, tblList);
    }//GEN-LAST:event_txtSearchaKeyReleased

    private void txtSearchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchaActionPerformed

    }//GEN-LAST:event_txtSearchaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblList;
    private javax.swing.JTextField txtSearcha;
    // End of variables declaration//GEN-END:variables
}

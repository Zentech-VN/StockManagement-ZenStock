package zentech.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import dao.AccountDAO;
import dao.PermGroupDAO;
import java.util.ArrayList;
import entity.Account;
import entity.PermGroup;
import java.awt.Window;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private int currentPage = 1;
    private final int pageSize = 50;  // số dòng mỗi trang
    private int totalPages = 1;

    public AccountForm() {
        initComponents();
        initalUI(tblList);
        currentPage = 1;
        loadTablePage();
    }

    public AccountForm(Account currentUser) {
        initComponents();
        initalUI(tblList);
        currentPage = 1;
        loadTablePage();
    }

    public void loadTable(List<Account> accounts, JTable jTable1) {
        SwingWorker<DefaultTableModel, Void> worker = new SwingWorker<DefaultTableModel, Void>() {
            @Override
            protected DefaultTableModel doInBackground() {
                String[] columns = {"Mã nhân viên", "Tên đăng nhập", "Nhóm quyền", "Trạng thái"};
                DefaultTableModel model = new DefaultTableModel(columns, 0);

                for (Account account : accounts) {
                    model.addRow(new Object[]{
                        account.getManv(),
                        account.getUsername(),
                        getPermGroupCached(account.getManhomquyen()),
                        getStatusText(account.getTrangthai())
                    });
                }
                jTable1.setModel(model);
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

    public void loadTablePage() {
        SwingWorker<List<Object[]>, Void> worker = new SwingWorker<List<Object[]>, Void>() {
            @Override
            protected List<Object[]> doInBackground() throws Exception {
                // Lấy danh sách account theo trang
                List<Account> accounts = asv.getAccountsPaged(currentPage, pageSize);

                // Tính tổng số trang
                int totalAccounts = asv.getAccountCountService();
                totalPages = (int) Math.ceil((double) totalAccounts / pageSize);

                // Chuyển dữ liệu thành Object[] cho JTable
                List<Object[]> rows = new ArrayList<>();
                for (Account acc : accounts) {
                    rows.add(new Object[]{
                        acc.getManv(),
                        acc.getUsername(),
                        getPermGroupCached(acc.getManhomquyen()),
                        getStatusText(acc.getTrangthai())
                    });
                }
                return rows;
            }

            @Override
            protected void done() {
                try {
                    List<Object[]> rows = get();

                    // Lấy model hiện tại và xóa dữ liệu cũ
                    DefaultTableModel model = (DefaultTableModel) tblList.getModel();
                    model.setRowCount(0);

                    // Đổ dữ liệu mới vào bảng
                    for (Object[] row : rows) {
                        model.addRow(row);
                    }

                    // Cập nhật trạng thái nút điều hướng
                    btnPrevious.setEnabled(currentPage > 1);
                    btnFirst.setEnabled(currentPage > 1);
                    btnNext.setEnabled(currentPage < totalPages);
                    btnLast.setEnabled(currentPage < totalPages);

                    // Hiển thị số trang
                    lblCurrentPage.setText("Trang " + currentPage + " / " + totalPages);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Thực thi worker
        worker.execute();
    }

    private Map<Integer, String> permGroupCache = new HashMap<>();

    private String getPermGroupCached(int manhomquyen) {
        return permGroupCache.computeIfAbsent(manhomquyen,
                id -> getPermGroup(id).getTennhomquyen());
    }

    private String getStatusText(int status) {
        switch (status) {
            case 1:
                return "Hoạt động";
            case 0:
                return "Ngưng hoạt động";
            default:
                return "Không xác định";
        }
    }

    public static PermGroup getPermGroup(int manhom) {
        return PermGroupDAO.selectById(manhom + "");
    }

    public int getRowSelected() {
        int viewIndex = tblList.getSelectedRow();
        return tblList.convertRowIndexToModel(viewIndex);
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
        txtSearch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        crazyPanel6 = new raven.crazypanel.CrazyPanel();
        btnFirst = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        lblCurrentPage = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill][grow 0]",
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

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        crazyPanel2.add(txtSearch);

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

        crazyPanel6.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel6.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "push[][][][][]push",
            "",
            null
        ));

        btnFirst.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFirst.setText("<<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        crazyPanel6.add(btnFirst);

        btnPrevious.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrevious.setText("< Trước");
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });
        crazyPanel6.add(btnPrevious);

        lblCurrentPage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCurrentPage.setText("...");
        crazyPanel6.add(lblCurrentPage);

        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNext.setText("Sau >");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        crazyPanel6.add(btnNext);

        btnLast.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLast.setText(">>");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        crazyPanel6.add(btnLast);

        crazyPanel1.add(crazyPanel6);

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
        int selectedRow = tblList.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài khoản để xóa!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // chuyển đổi chỉ mục từ view sang model
        int modelRowIndex = tblList.convertRowIndexToModel(selectedRow);

        try {
            String user = appCurrentUser;
            String hoTen = tblList.getValueAt(selectedRow, 1).toString();

            int input = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc chắn muốn xóa tài khoản \"" + hoTen + "\"?",
                    "Xóa tài khoản",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (input == JOptionPane.OK_OPTION) {
                int result = AccountDAO.getInstance().delete(lista.get(modelRowIndex).getManv() + "");

                if (result > 0) {
                    lista = asv.getTaiKhoanAll();
                    loadTable(lista, tblList);
                    JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa tài khoản thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa tài khoản: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        lista = asv.getTaiKhoanAll();
        loadTable(lista, tblList);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        String txt = txtSearch.getText();
        asv.Search(txtSearch, tblList);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed

    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        btnFirst.addActionListener(e -> {
            if (currentPage != 1) {
                currentPage = 1;
                loadTablePage();
            }
        });
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        if (currentPage > 1) {
            currentPage--;
            loadTablePage();
        }
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (currentPage < totalPages) {
            currentPage++;
            loadTablePage();
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        btnLast.addActionListener(e -> {
            if (currentPage != totalPages) {
                currentPage = totalPages;
                loadTablePage();
            }
        });
    }//GEN-LAST:event_btnLastActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel6;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCurrentPage;
    private javax.swing.JTable tblList;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

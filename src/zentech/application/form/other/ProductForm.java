package zentech.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import dao.ActivityDAO;
import entity.Employee;
import entity.EmployeeAccout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import raven.toast.Notifications;
import service.EmployeeService;
import zentech.application.Application;
import zentech.application.dialog.EmployeeAddDialog;
import zentech.application.dialog.EmployeeUpdateDialog;

public class EmployeeForm extends javax.swing.JPanel {

    private EmployeeService employeeService;
    private List<Employee> employeeList = new ArrayList<>();
    private TableRowSorter<DefaultTableModel> sorter;
    private EmployeeUpdateDialog updateDialog;

    public EmployeeForm() {
        initComponents();
        loadEmployeeData();
        initalUI(tblNhanVien);
        initSearchListener();
    }

    private void initalUI(JTable table) {
        tblNhanVien.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        tblNhanVien.setRowHeight(30);
        tblNhanVien.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18));

        JScrollPane scroll = (JScrollPane) table.getParent().getParent();
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Table.background;"
                + "track:$Table.background;"
                + "trackArc:999");

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        table.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

        table.getTableHeader().setDefaultRenderer(getAlignmentCellRender(table.getTableHeader().getDefaultRenderer(), true));
        table.setDefaultRenderer(Object.class, getAlignmentCellRender(table.getDefaultRenderer(Object.class), false));
        
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm");

        txtMa.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã");
        txtHoTen.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Họ tên");
        txtGioiTinh.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Giới tính");
        txtNgaySinh.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ngày sinh");
        txtDienThoai.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Điện Thoại");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email");

        txtTenDangNhap.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tên đăng nhập");
        txtQuyenHan.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Quyền hạn");

        cbbSapXep.addActionListener(evt -> applySort());
    }
    
    private TableCellRenderer getAlignmentCellRender(TableCellRenderer oldRender, boolean header) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = oldRender.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (com instanceof JLabel) {
                    JLabel label = (JLabel) com;
                    if (column == 2 || column == 3 || column == 4) {
                        label.setHorizontalAlignment(SwingConstants.CENTER); //Căn giữa
                    } else if (column == 0 || column == 1 || column == 5) {
                        label.setHorizontalAlignment(SwingConstants.LEFT); //Căn trái
                    } else if (column == 6) {
                        label.setHorizontalAlignment(SwingConstants.RIGHT); //Căn phải
                    } else {
                        label.setHorizontalAlignment(SwingConstants.CENTER); 
                    }
                }
                return com;
            }
        };
    }

    private void lockWidth(JComponent c) {
        Dimension size = new Dimension(WIDTH, c.getPreferredSize().height);
        c.setPreferredSize(size);
        c.setMinimumSize(size);
        c.setMaximumSize(size);
    }

    public void loadEmployeeData() {
        this.employeeService = new EmployeeService();
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);

        for (Employee x : employeeService.getAllEmployeeService()) {
            model.addRow(new Object[]{
                x.getManv(),
                x.getHoten(),
                x.getGioiTinhText(),
                x.getNgaysinh(),
                x.getSdt(),
                x.getEmail(),
                x.getTrangThaiText()
            });
        }
        this.tblNhanVien.setModel(model);

        if (sorter == null) {
            initSorter();
        } else {
            sorter.sort();
        }
    }

    private void initSorter() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        sorter = new TableRowSorter<>(model);

        sorter.setComparator(6, (o1, o2) -> {
            int v1 = "Đang làm".equals(o1) ? 0 : 1;
            int v2 = "Đang làm".equals(o2) ? 0 : 1;
            return Integer.compare(v1, v2);
        });

        tblNhanVien.setRowSorter(sorter);
        applySort();
    }

    private void applySort() {
        int choice = cbbSapXep.getSelectedIndex();
        List<RowSorter.SortKey> keys = new ArrayList<>();

        switch (choice) {
            case 0:
                keys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING)); //Mã
                break;
            case 1:
                keys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING)); //Họ tên
                break;
            case 2:
                keys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING)); //Ngày sinh
                break;
            case 3:
                keys.add(new RowSorter.SortKey(6, SortOrder.ASCENDING)); //Trạng thái
                break;
            default:
                break;
        }
        sorter.setSortKeys(keys);
        sorter.sort();
    }

    private void initSearchListener() {
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                doSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                doSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            private void doSearch() {
                String kw = txtSearch.getText().trim();
                List<Employee> list = employeeService.searchEmployees(kw);

                DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
                model.setRowCount(0);

                list.forEach(emp -> model.addRow(new Object[]{
                    emp.getManv(),
                    emp.getHoten(),
                    emp.getGioiTinhText(),
                    emp.getNgaysinh(),
                    emp.getSdt(),
                    emp.getEmail(),
                    emp.getTrangThaiText()
                }));

                sorter.sort();
            }

        });
    }

    private int getSelectedModelRow() {
        int viewIndex = tblNhanVien.getSelectedRow();
        return viewIndex == -1 ? -1 : tblNhanVien.convertRowIndexToModel(viewIndex);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        cbbSapXep = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        txtSearch = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtGioiTinh = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDienThoai = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        crazyPanel4 = new raven.crazypanel.CrazyPanel();
        jLabel16 = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtQuyenHan = new javax.swing.JTextField();

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Tạo tài khoản");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("Làm mới");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setText("Xuất File");

        cbbSapXep.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbbSapXep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mặc định", "Họ tên", "Ngày sinh", "Trạng thái" }));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Sắp xếp theo:");

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
                ""
            }
        ));
        crazyPanel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][]",
            "",
            new String[]{
                "width 400"
            }
        ));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel2.add(txtSearch);

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnAdd);

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnUpdate);

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setText("Xoá");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnDelete);

        crazyPanel1.add(crazyPanel2);

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Họ tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Email", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.getTableHeader().setReorderingAllowed(false);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);
        if (tblNhanVien.getColumnModel().getColumnCount() > 0) {
            tblNhanVien.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblNhanVien.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblNhanVien.getColumnModel().getColumn(2).setPreferredWidth(30);
            tblNhanVien.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblNhanVien.getColumnModel().getColumn(4).setPreferredWidth(70);
            tblNhanVien.getColumnModel().getColumn(5).setPreferredWidth(140);
            tblNhanVien.getColumnModel().getColumn(6).setPreferredWidth(50);
        }

        crazyPanel1.add(jScrollPane1);

        crazyPanel3.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Info.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel3.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            new String[]{
                ""
            }
        ));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setText("Mã");
        crazyPanel3.add(jLabel1);

        txtMa.setEditable(false);
        txtMa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel3.add(txtMa);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setText("Họ tên");
        crazyPanel3.add(jLabel2);

        txtHoTen.setEditable(false);
        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel3.add(txtHoTen);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setText("Giới tính");
        crazyPanel3.add(jLabel3);

        txtGioiTinh.setEditable(false);
        txtGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel3.add(txtGioiTinh);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setText("Ngày sinh");
        crazyPanel3.add(jLabel4);

        txtNgaySinh.setEditable(false);
        txtNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel3.add(txtNgaySinh);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel5.setText("Số điện thoại");
        crazyPanel3.add(jLabel5);

        txtDienThoai.setEditable(false);
        txtDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel3.add(txtDienThoai);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel6.setText("Email");
        crazyPanel3.add(jLabel6);

        txtEmail.setEditable(false);
        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel3.add(txtEmail);

        crazyPanel4.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Info.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel4.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            new String[]{
                ""
            }
        ));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel16.setText("Tên đăng nhập");
        crazyPanel4.add(jLabel16);

        txtTenDangNhap.setEditable(false);
        txtTenDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel4.add(txtTenDangNhap);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel17.setText("Quyền hạn");
        crazyPanel4.add(jLabel17);

        txtQuyenHan.setEditable(false);
        txtQuyenHan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel4.add(txtQuyenHan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(crazyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crazyPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton5, jButton7});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(crazyPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(crazyPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton7)
                    .addComponent(cbbSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        loadEmployeeData();
        
        txtMa.setText("");
        txtHoTen.setText("");
        txtGioiTinh.setText("");
        txtNgaySinh.setText("");
        txtDienThoai.setText("");
        txtEmail.setText("");
        txtTenDangNhap.setText("");
        txtQuyenHan.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Application.showForm(new AccountForm());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Window parent = SwingUtilities.getWindowAncestor(this);
        EmployeeAddDialog employeeAddDialog = new EmployeeAddDialog(parent, this);
        employeeAddDialog.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        Window parent = SwingUtilities.getWindowAncestor(this);
        int modelRow = getSelectedModelRow();
        if (modelRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Hãy chọn nhân viên muốn chỉnh sửa");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        String ma = model.getValueAt(modelRow, 0).toString();
        String hoTen = model.getValueAt(modelRow, 1).toString();
        String gioiTinh = model.getValueAt(modelRow, 2).toString();
        String ngaySinh = model.getValueAt(modelRow, 3).toString();
        String dienThoai = model.getValueAt(modelRow, 4).toString();
        String email = model.getValueAt(modelRow, 5).toString();
        String trangThai = model.getValueAt(modelRow, 6).toString();

        EmployeeUpdateDialog dlg = new EmployeeUpdateDialog(parent, this, ma, hoTen, gioiTinh, ngaySinh, dienThoai, email, trangThai);
        dlg.setLocationRelativeTo(parent);
        dlg.setVisible(true);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int modelRow = getSelectedModelRow();
        String appCurrentUser = zentech.application.Application.getAppInstance().getCurrentUser();
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        String hoTen = model.getValueAt(modelRow, 1).toString();
        if (modelRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Hãy chọn nhân viên muốn xoá");
            return;
        }

        int maInt = Integer.parseInt(model.getValueAt(modelRow, 0).toString());

        int ret = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá nhân viên có mã: " + maInt, "Xoá", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            ActivityDAO.logActivity(appCurrentUser, "Xóa nhân viên: " + hoTen);
            this.employeeService = new EmployeeService();
            
            if (employeeService.deleteEmployeeById(maInt)) {
                loadEmployeeData();
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        int index = tblNhanVien.getSelectedRow();
        if (index != -1) {
            int manv = (int) tblNhanVien.getValueAt(index, 0);
            String ma = tblNhanVien.getValueAt(index, 0).toString();
            String hoTen = tblNhanVien.getValueAt(index, 1).toString();
            String gioiTinh = tblNhanVien.getValueAt(index, 2).toString();
            String ngaySinh = tblNhanVien.getValueAt(index, 3).toString();
            String dienThoai = tblNhanVien.getValueAt(index, 4).toString();
            String email = tblNhanVien.getValueAt(index, 5).toString();

            EmployeeAccout employeeAccout = employeeService.fetchAccountInfo(manv);

            txtTenDangNhap.setText(employeeAccout.hasAccount() ? employeeAccout.getUsername() : "Chưa có");
            txtQuyenHan.setText(
                    employeeAccout.hasAccount()
                    ? (employeeAccout.getRoleId() == 1 ? "Quản lý kho"
                    : employeeAccout.getRoleId() == 2 ? "Nhân viên nhập hàng"
                    : "") //Không tìm thấy
                    : ""
            );

            txtMa.setText(ma);
            txtHoTen.setText(hoTen);
            txtGioiTinh.setText(gioiTinh);
            txtNgaySinh.setText(ngaySinh);
            txtDienThoai.setText(dienThoai);
            txtEmail.setText(email);
            
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbSapXep;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private raven.crazypanel.CrazyPanel crazyPanel4;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtDienThoai;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtQuyenHan;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
}

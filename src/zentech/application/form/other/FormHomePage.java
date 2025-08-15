package zentech.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import dao.ProductDAO;
import entity.Product;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import service.AccountService;
import service.EmployeeService;
import service.ProductServiceMain;

public class FormHomePage extends javax.swing.JPanel {

    private EmployeeService employeeService = new EmployeeService();
    private ProductServiceMain productService = new ProductServiceMain();
    private AccountService accountService = new AccountService();

    private int currentPage = 1;
    private final int pageSize = 15;  // số dòng mỗi trang
    private int totalPages = 1;

    public FormHomePage() {
        initComponents();
        initalUI(tblSanPham);
        currentPage = 1;
        loadDataTable();
    }

    private void initalUI(JTable table) {
        table.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18));
        
        panel1.putClientProperty("FlatLaf.style",
                "[light]border:0,0,0,0,shade(@background,5%),,20;"
                + "[dark]border:0,0,0,0,tint(@background,5%),,20;");

        panel2.putClientProperty("FlatLaf.style",
                "[light]border:0,0,0,0,shade(@background,5%),,20;"
                + "[dark]border:0,0,0,0,tint(@background,5%),,20;");

        panel3.putClientProperty("FlatLaf.style",
                "[light]border:0,0,0,0,shade(@background,5%),,20;"
                + "[dark]border:0,0,0,0,tint(@background,5%),,20;");

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

        loadDashboardCounts();
    }

    private TableCellRenderer getAlignmentCellRender(TableCellRenderer oldRender, boolean header) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = oldRender.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (com instanceof JLabel) {
                    JLabel label = (JLabel) com;
                    if (column == 0 || column == 1 || column == 2 || column == 3 || column == 4 || column == 5) {
                        label.setHorizontalAlignment(SwingConstants.LEFT); //Căn trái
                    } else {
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                    }
                }
                return com;
            }
        };
    }

    public void loadDataTable() {
        SwingWorker<List<Object[]>, Void> worker = new SwingWorker<List<Object[]>, Void>() {
            @Override
            protected List<Object[]> doInBackground() throws Exception {
                
                List<Product> products = productService.getAllProduct(currentPage, pageSize);
                int totalProducts = productService.getProductCount();
                totalPages = (int) Math.ceil((double) totalProducts / pageSize);

                List<Object[]> rows = new ArrayList<>();
                for (Product p : products) {
                    rows.add(new Object[]{
                        p.getTenSanPham(),
                        p.getTenThuongHieu(),
                        p.getTenXuatXu(),
                        p.getGia()
                    });
                }
                return rows;
            }

            @Override
            protected void done() {
                try {
                    List<Object[]> rows = get();

                    DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
                    model.setRowCount(0);

                    for (Object[] row : rows) {
                        model.addRow(row);
                    }

                    //Trạng thái của nút
                    btnPrevious.setEnabled(currentPage > 1);
                    btnNext.setEnabled(currentPage < totalPages);

                    //Trang hiện tại
                    lblCurrentPage.setText("Trang " + currentPage + " / " + totalPages);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }

    private void loadDashboardCounts() {
        SwingWorker<int[], Void> worker = new SwingWorker<int[], Void>() {
            @Override
            protected int[] doInBackground() throws Exception {
                int userCount = employeeService.getEmployeeCountService();
                int productCount = productService.getProductCountService();
                int accountCount = accountService.getAccountCountService();

                return new int[]{userCount, productCount, accountCount};
            }

            @Override
            protected void done() {
                try {
                    int[] counts = get();

                    lblUserCount.setText(String.valueOf(counts[0]));
                    lblProductCount.setText(String.valueOf(counts[1]));
                    lblAccountCount.setText(String.valueOf(counts[2]));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        panel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel4 = new raven.crazypanel.CrazyPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblUserCount = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panel2 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblProductCount = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panel3 = new raven.crazypanel.CrazyPanel();
        crazyPanel5 = new raven.crazypanel.CrazyPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblAccountCount = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        crazyPanel6 = new raven.crazypanel.CrazyPanel();
        btnFirst = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        lblCurrentPage = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();

        lb.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("PHẦN MỀM QUẢN LÝ ĐIỆN THOẠI THEO MÃ IMEI");

        crazyPanel3.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Info.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel3.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 10",
            "[fill][fill][fill]",
            "[fill]",
            new String[]{
                ""
            }
        ));

        panel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Info.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        panel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 10",
            "[fill][fill]",
            "[fill]",
            null
        ));

        crazyPanel4.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 10",
            "[fill]",
            "[fill][fill][fill][fill]",
            null
        ));
        crazyPanel4.add(jLabel11);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("NHÂN VIÊN");
        crazyPanel4.add(jLabel12);

        lblUserCount.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblUserCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserCount.setText("...");
        crazyPanel4.add(lblUserCount);
        crazyPanel4.add(jLabel13);

        panel1.add(crazyPanel4);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/zentech/icon/png/user-extend.png"))); // NOI18N
        panel1.add(jLabel2);

        crazyPanel3.add(panel1);

        panel2.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Info.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        panel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 10",
            "[fill][fill]",
            "[fill]",
            null
        ));

        crazyPanel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 10",
            "[fill]",
            "[fill][fill][fill][fill]",
            null
        ));
        crazyPanel2.add(jLabel7);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("SẢN PHẨM");
        crazyPanel2.add(jLabel9);

        lblProductCount.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblProductCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProductCount.setText("...");
        crazyPanel2.add(lblProductCount);
        crazyPanel2.add(jLabel10);

        panel2.add(crazyPanel2);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/zentech/icon/png/product-extend.png"))); // NOI18N
        panel2.add(jLabel3);

        crazyPanel3.add(panel2);

        panel3.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Info.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        panel3.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 10",
            "[fill][fill]",
            "[fill]",
            null
        ));

        crazyPanel5.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 10",
            "[fill]",
            "[fill][fill][fill][fill]",
            null
        ));
        crazyPanel5.add(jLabel1);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TÀI KHOẢN");
        crazyPanel5.add(jLabel4);

        lblAccountCount.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblAccountCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAccountCount.setText("...");
        crazyPanel5.add(lblAccountCount);
        crazyPanel5.add(jLabel6);

        panel3.add(crazyPanel5);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/zentech/icon/png/account-extend.png"))); // NOI18N
        panel3.add(jLabel5);

        crazyPanel3.add(panel3);

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[fill][grow 0]",
            new String[]{
                ""
            }
        ));

        tblSanPham.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sản phẩm", "Thương hiệu", "Xuất xứ", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblSanPham);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(crazyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(56, 56, 56))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb)
                .addGap(18, 18, 18)
                .addComponent(crazyPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        btnLast.addActionListener(e -> {
            if (currentPage != totalPages) {
                currentPage = totalPages;
                loadDataTable();
            }
        });
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (currentPage < totalPages) {
            currentPage++;
            loadDataTable();
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        if (currentPage > 1) {
            currentPage--;
            loadDataTable();
        }
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        btnFirst.addActionListener(e -> {
            if (currentPage != 1) {
                currentPage = 1;
                loadDataTable();
            }
        });
    }//GEN-LAST:event_btnFirstActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private raven.crazypanel.CrazyPanel crazyPanel4;
    private raven.crazypanel.CrazyPanel crazyPanel5;
    private raven.crazypanel.CrazyPanel crazyPanel6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lblAccountCount;
    private javax.swing.JLabel lblCurrentPage;
    private javax.swing.JLabel lblProductCount;
    private javax.swing.JLabel lblUserCount;
    private raven.crazypanel.CrazyPanel panel1;
    private raven.crazypanel.CrazyPanel panel2;
    private raven.crazypanel.CrazyPanel panel3;
    private javax.swing.JTable tblSanPham;
    // End of variables declaration//GEN-END:variables
}

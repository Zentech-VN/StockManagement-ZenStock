package zentech.application.form.other;

import chart.chart.ModelChart;
import com.formdev.flatlaf.FlatClientProperties;
import entity.Chart_Revenue;
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import service.ChartService;
import service.ClientService;
import service.EmployeeService;
import service.ProductServiceMain;

public class Chart extends javax.swing.JPanel {

    private EmployeeService employeeService = new EmployeeService();
    private ProductServiceMain productService = new ProductServiceMain();
    private ClientService clientService = new ClientService();

    public Chart() {
        initComponents();
        initalUI(tblDoanhThuTongQuan);
        chart.setTitle("Tổng quan");
        chart.addLegend("Doanh thu", Color.decode("#7b4397"), Color.decode("#dc2430"));
        chart.addLegend("Vốn", Color.decode("#e65c00"), Color.decode("#F9D423"));
        chart.addLegend("Lợi nhuận", Color.decode("#0099F7"), Color.decode("#F11712"));
        setData();
        loadRevenueData();

        DefaultTableCellRenderer colorRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                try {
                    String raw = value.toString().replace(",", "").replace(" VNĐ", "");
                    double val = Double.parseDouble(raw);

                    if (val < 0) {
                        c.setForeground(Color.RED);
                    } else if (val > 0) {
                        c.setForeground(new Color(0, 153, 0));
                    } else {
                        c.setForeground(Color.BLACK);
                    }

                } catch (Exception e) {
                    c.setForeground(Color.BLACK);
                }

                return c;
            }
        };

        tblDoanhThuTongQuan.getColumnModel().getColumn(1).setCellRenderer(colorRenderer);
        tblDoanhThuTongQuan.getColumnModel().getColumn(2).setCellRenderer(colorRenderer);
        tblDoanhThuTongQuan.getColumnModel().getColumn(3).setCellRenderer(colorRenderer);

        panel1.putClientProperty("FlatLaf.style",
                "[light]border:0,0,0,0,shade(@background,5%),,20;"
                + "[dark]border:0,0,0,0,tint(@background,5%),,20;");

        panel2.putClientProperty("FlatLaf.style",
                "[light]border:0,0,0,0,shade(@background,5%),,20;"
                + "[dark]border:0,0,0,0,tint(@background,5%),,20;");

        panel3.putClientProperty("FlatLaf.style",
                "[light]border:0,0,0,0,shade(@background,5%),,20;"
                + "[dark]border:0,0,0,0,tint(@background,5%),,20;");

        int userCount = employeeService.getEmployeeCountService();
        int productCount = productService.getProductCountService();
        int customerCount = clientService.getClientCountService();

        lblUserCount.setText("" + userCount);
        lblProductCount.setText("" + productCount);
        lblCustomerCount.setText("" + customerCount);

    }

    private void initalUI(JTable table) {

        tblDoanhThuTongQuan.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        tblDoanhThuTongQuan.setRowHeight(30);
        tblDoanhThuTongQuan.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18));

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

    private void setData() {
        try {
            ChartService service = new ChartService();
            List<Chart_Revenue> lists = service.getRevenue10MonthService();

            //Xoá dữ liệu cũ
            chart.clear();

            for (int i = lists.size() - 1; i >= 0; i--) {
                Chart_Revenue d = lists.get(i);
                chart.addData(new ModelChart(
                        d.getThang(),
                        new double[]{d.getDoanhThu(), d.getGiaVon(), d.getLoiNhuan()}
                ));
            }

            //Animation
            chart.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadRevenueData() {
        try {
            ChartService service = new ChartService();
            List<Chart_Revenue> list = service.getRevenueService();

            DefaultTableModel model = (DefaultTableModel) tblDoanhThuTongQuan.getModel();
            model.setRowCount(0);

            for (Chart_Revenue d : list) {
                DecimalFormat formatter = new DecimalFormat("#,###");

                model.addRow(new Object[]{
                    d.getThang(),
                    formatter.format(d.getDoanhThu()),
                    formatter.format(d.getGiaVon()),
                    formatter.format(d.getLoiNhuan())
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel17 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        materialTabbed3 = new zentech.application.tabbed.MaterialTabbed();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        materialTabbed2 = new zentech.application.tabbed.MaterialTabbed();
        jPanel5 = new javax.swing.JPanel();
        panelShadow1 = new chart.panel.PanelShadow();
        chart = new chart.chart.CurveLineChart();
        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDoanhThuTongQuan = new javax.swing.JTable();
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
        lblCustomerCount = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        materialTabbed1 = new zentech.application.tabbed.MaterialTabbed();
        jPanel4 = new javax.swing.JPanel();
        panelShadow10 = new chart.panel.PanelShadow();
        chart9 = new chart.chart.CurveLineChart();
        crazyPanel16 = new raven.crazypanel.CrazyPanel();
        crazyPanel17 = new raven.crazypanel.CrazyPanel();
        jLabel21 = new javax.swing.JLabel();
        txtSearch8 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtSearch10 = new javax.swing.JTextField();
        btnAdd5 = new javax.swing.JButton();
        btnUpdate5 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblDoanhThuNam5 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        panelShadow11 = new chart.panel.PanelShadow();
        chart10 = new chart.chart.CurveLineChart();
        crazyPanel18 = new raven.crazypanel.CrazyPanel();
        crazyPanel19 = new raven.crazypanel.CrazyPanel();
        jLabel25 = new javax.swing.JLabel();
        txtSearch12 = new javax.swing.JTextField();
        btnAdd6 = new javax.swing.JButton();
        btnUpdate6 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblDoanhThuNam6 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        crazyPanel20 = new raven.crazypanel.CrazyPanel();
        crazyPanel21 = new raven.crazypanel.CrazyPanel();
        jLabel22 = new javax.swing.JLabel();
        txtSearch9 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtSearch11 = new javax.swing.JTextField();
        btnAdd7 = new javax.swing.JButton();
        btnUpdate7 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblDoanhThuNam7 = new javax.swing.JTable();
        panelShadow12 = new chart.panel.PanelShadow();
        chart11 = new chart.chart.CurveLineChart();
        jPanel9 = new javax.swing.JPanel();
        crazyPanel22 = new raven.crazypanel.CrazyPanel();
        crazyPanel23 = new raven.crazypanel.CrazyPanel();
        jLabel26 = new javax.swing.JLabel();
        txtSearch13 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtSearch14 = new javax.swing.JTextField();
        btnAdd8 = new javax.swing.JButton();
        btnUpdate8 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblDoanhThuNam8 = new javax.swing.JTable();
        panelShadow13 = new chart.panel.PanelShadow();
        chart12 = new chart.chart.CurveLineChart();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1301, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 773, Short.MAX_VALUE)
        );

        panelShadow1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelShadow1.setForeground(new java.awt.Color(255, 255, 255));
        panelShadow1.setToolTipText("");
        panelShadow1.setShadowColor(new java.awt.Color(255, 255, 255));

        chart.setBackground(new java.awt.Color(0, 0, 0));
        chart.setForeground(new java.awt.Color(0, 0, 0));
        chart.setFillColor(true);

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[fill]",
            new String[]{
                ""
            }
        ));

        tblDoanhThuTongQuan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Thời gian", "Doanh thu", "Vốn", "Lợi nhuận"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoanhThuTongQuan.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblDoanhThuTongQuan);

        crazyPanel1.add(jScrollPane3);

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
        lblUserCount.setText("?");
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
        lblProductCount.setText("?");
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
        jLabel4.setText("KHÁCH HÀNG");
        crazyPanel5.add(jLabel4);

        lblCustomerCount.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblCustomerCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCustomerCount.setText("?");
        crazyPanel5.add(lblCustomerCount);
        crazyPanel5.add(jLabel6);

        panel3.add(crazyPanel5);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/zentech/icon/png/account-extend.png"))); // NOI18N
        panel3.add(jLabel5);

        crazyPanel3.add(panel3);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crazyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addContainerGap())
        );

        materialTabbed2.addTab("Tổng quan", jPanel5);

        jPanel1.setPreferredSize(new java.awt.Dimension(200, 661));

        panelShadow10.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelShadow10.setForeground(new java.awt.Color(255, 255, 255));
        panelShadow10.setToolTipText("");
        panelShadow10.setShadowColor(new java.awt.Color(255, 255, 255));

        chart9.setBackground(new java.awt.Color(0, 0, 0));
        chart9.setForeground(new java.awt.Color(0, 0, 0));
        chart9.setFillColor(true);

        javax.swing.GroupLayout panelShadow10Layout = new javax.swing.GroupLayout(panelShadow10);
        panelShadow10.setLayout(panelShadow10Layout);
        panelShadow10Layout.setHorizontalGroup(
            panelShadow10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow10Layout.setVerticalGroup(
            panelShadow10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart9, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                .addContainerGap())
        );

        crazyPanel16.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel16.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            new String[]{
                ""
            }
        ));

        crazyPanel17.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel17.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "push[][][][][][]push",
            "[]",
            new String[]{
                "",
                "width 100",
                "",
                "width 100"
            }
        ));

        jLabel21.setText("Từ năm");
        crazyPanel17.add(jLabel21);

        txtSearch8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel17.add(txtSearch8);

        jLabel23.setText("Đến năm");
        crazyPanel17.add(jLabel23);

        txtSearch10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel17.add(txtSearch10);

        btnAdd5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd5.setText("Thống kê");
        crazyPanel17.add(btnAdd5);

        btnUpdate5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate5.setText("Làm mới");
        crazyPanel17.add(btnUpdate5);

        crazyPanel16.add(crazyPanel17);

        tblDoanhThuNam5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Thời gian", "Doanh thu", "Vốn", "Lợi nhuận"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoanhThuNam5.getTableHeader().setReorderingAllowed(false);
        tblDoanhThuNam5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoanhThuNam5MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblDoanhThuNam5);

        crazyPanel16.add(jScrollPane8);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(crazyPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
            .addComponent(panelShadow10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(panelShadow10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Thống kê theo năm", jPanel4);

        panelShadow11.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelShadow11.setForeground(new java.awt.Color(255, 255, 255));
        panelShadow11.setToolTipText("");
        panelShadow11.setShadowColor(new java.awt.Color(255, 255, 255));

        chart10.setBackground(new java.awt.Color(0, 0, 0));
        chart10.setForeground(new java.awt.Color(0, 0, 0));
        chart10.setFillColor(true);

        javax.swing.GroupLayout panelShadow11Layout = new javax.swing.GroupLayout(panelShadow11);
        panelShadow11.setLayout(panelShadow11Layout);
        panelShadow11Layout.setHorizontalGroup(
            panelShadow11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow11Layout.setVerticalGroup(
            panelShadow11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart10, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                .addContainerGap())
        );

        crazyPanel18.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel18.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            new String[]{
                ""
            }
        ));

        crazyPanel19.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel19.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "push[][][][]push",
            "[]",
            new String[]{
                "",
                "width 100"
            }
        ));

        jLabel25.setText("Chọn năm");
        crazyPanel19.add(jLabel25);

        txtSearch12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel19.add(txtSearch12);

        btnAdd6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd6.setText("Thống kê");
        crazyPanel19.add(btnAdd6);

        btnUpdate6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate6.setText("Làm mới");
        crazyPanel19.add(btnUpdate6);

        crazyPanel18.add(crazyPanel19);

        tblDoanhThuNam6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Thời gian", "Doanh thu", "Vốn", "Lợi nhuận"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoanhThuNam6.getTableHeader().setReorderingAllowed(false);
        tblDoanhThuNam6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoanhThuNam6MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblDoanhThuNam6);

        crazyPanel18.add(jScrollPane9);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(crazyPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
            .addComponent(panelShadow11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(panelShadow11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Thống kê theo từng tháng trong năm", jPanel6);

        crazyPanel20.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel20.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            new String[]{
                ""
            }
        ));

        crazyPanel21.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel21.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "push[][][][][][]push",
            "[]",
            new String[]{
                "",
                "width 100",
                "",
                "width 100"
            }
        ));

        jLabel22.setText("Chọn năm");
        crazyPanel21.add(jLabel22);

        txtSearch9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel21.add(txtSearch9);

        jLabel24.setText("Chọn tháng");
        crazyPanel21.add(jLabel24);

        txtSearch11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel21.add(txtSearch11);

        btnAdd7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd7.setText("Thống kê");
        crazyPanel21.add(btnAdd7);

        btnUpdate7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate7.setText("Làm mới");
        crazyPanel21.add(btnUpdate7);

        crazyPanel20.add(crazyPanel21);

        tblDoanhThuNam7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Thời gian", "Doanh thu", "Vốn", "Lợi nhuận"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoanhThuNam7.getTableHeader().setReorderingAllowed(false);
        tblDoanhThuNam7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoanhThuNam7MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblDoanhThuNam7);

        crazyPanel20.add(jScrollPane10);

        panelShadow12.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelShadow12.setForeground(new java.awt.Color(255, 255, 255));
        panelShadow12.setToolTipText("");
        panelShadow12.setShadowColor(new java.awt.Color(255, 255, 255));

        chart11.setBackground(new java.awt.Color(0, 0, 0));
        chart11.setForeground(new java.awt.Color(0, 0, 0));
        chart11.setFillColor(true);

        javax.swing.GroupLayout panelShadow12Layout = new javax.swing.GroupLayout(panelShadow12);
        panelShadow12.setLayout(panelShadow12Layout);
        panelShadow12Layout.setHorizontalGroup(
            panelShadow12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow12Layout.setVerticalGroup(
            panelShadow12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart11, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(crazyPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
            .addComponent(panelShadow12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(panelShadow12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Thống kê theo từng ngày trong tháng", jPanel8);

        crazyPanel22.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel22.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            new String[]{
                ""
            }
        ));

        crazyPanel23.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel23.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "push[][][][][][]push",
            "[]",
            new String[]{
                "",
                "width 100",
                "",
                "width 100"
            }
        ));

        jLabel26.setText("Từ ngày");
        crazyPanel23.add(jLabel26);

        txtSearch13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel23.add(txtSearch13);

        jLabel27.setText("Đến ngày");
        crazyPanel23.add(jLabel27);

        txtSearch14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel23.add(txtSearch14);

        btnAdd8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd8.setText("Thống kê");
        crazyPanel23.add(btnAdd8);

        btnUpdate8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate8.setText("Làm mới");
        crazyPanel23.add(btnUpdate8);

        crazyPanel22.add(crazyPanel23);

        tblDoanhThuNam8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Thời gian", "Doanh thu", "Vốn", "Lợi nhuận"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoanhThuNam8.getTableHeader().setReorderingAllowed(false);
        tblDoanhThuNam8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoanhThuNam8MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblDoanhThuNam8);

        crazyPanel22.add(jScrollPane11);

        panelShadow13.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelShadow13.setForeground(new java.awt.Color(255, 255, 255));
        panelShadow13.setToolTipText("");
        panelShadow13.setShadowColor(new java.awt.Color(255, 255, 255));

        chart12.setBackground(new java.awt.Color(0, 0, 0));
        chart12.setForeground(new java.awt.Color(0, 0, 0));
        chart12.setFillColor(true);

        javax.swing.GroupLayout panelShadow13Layout = new javax.swing.GroupLayout(panelShadow13);
        panelShadow13.setLayout(panelShadow13Layout);
        panelShadow13Layout.setHorizontalGroup(
            panelShadow13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow13Layout.setVerticalGroup(
            panelShadow13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart12, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(crazyPanel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
            .addComponent(panelShadow13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(panelShadow13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Thống kê từ ngày đến ngày", jPanel9);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        materialTabbed2.addTab("Doanh thu", jPanel1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1034, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
        );

        materialTabbed2.addTab("Nhà cung cấp", jPanel3);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1034, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
        );

        materialTabbed2.addTab("Khách hàng", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblDoanhThuNam5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoanhThuNam5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDoanhThuNam5MouseClicked

    private void tblDoanhThuNam6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoanhThuNam6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDoanhThuNam6MouseClicked

    private void tblDoanhThuNam7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoanhThuNam7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDoanhThuNam7MouseClicked

    private void tblDoanhThuNam8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoanhThuNam8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDoanhThuNam8MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd5;
    private javax.swing.JButton btnAdd6;
    private javax.swing.JButton btnAdd7;
    private javax.swing.JButton btnAdd8;
    private javax.swing.JButton btnUpdate5;
    private javax.swing.JButton btnUpdate6;
    private javax.swing.JButton btnUpdate7;
    private javax.swing.JButton btnUpdate8;
    private chart.chart.CurveLineChart chart;
    private chart.chart.CurveLineChart chart10;
    private chart.chart.CurveLineChart chart11;
    private chart.chart.CurveLineChart chart12;
    private chart.chart.CurveLineChart chart9;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel16;
    private raven.crazypanel.CrazyPanel crazyPanel17;
    private raven.crazypanel.CrazyPanel crazyPanel18;
    private raven.crazypanel.CrazyPanel crazyPanel19;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel20;
    private raven.crazypanel.CrazyPanel crazyPanel21;
    private raven.crazypanel.CrazyPanel crazyPanel22;
    private raven.crazypanel.CrazyPanel crazyPanel23;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private raven.crazypanel.CrazyPanel crazyPanel4;
    private raven.crazypanel.CrazyPanel crazyPanel5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCustomerCount;
    private javax.swing.JLabel lblProductCount;
    private javax.swing.JLabel lblUserCount;
    private zentech.application.tabbed.MaterialTabbed materialTabbed1;
    private zentech.application.tabbed.MaterialTabbed materialTabbed2;
    private zentech.application.tabbed.MaterialTabbed materialTabbed3;
    private raven.crazypanel.CrazyPanel panel1;
    private raven.crazypanel.CrazyPanel panel2;
    private raven.crazypanel.CrazyPanel panel3;
    private chart.panel.PanelShadow panelShadow1;
    private chart.panel.PanelShadow panelShadow10;
    private chart.panel.PanelShadow panelShadow11;
    private chart.panel.PanelShadow panelShadow12;
    private chart.panel.PanelShadow panelShadow13;
    private javax.swing.JTable tblDoanhThuNam5;
    private javax.swing.JTable tblDoanhThuNam6;
    private javax.swing.JTable tblDoanhThuNam7;
    private javax.swing.JTable tblDoanhThuNam8;
    private javax.swing.JTable tblDoanhThuTongQuan;
    private javax.swing.JTextField txtSearch10;
    private javax.swing.JTextField txtSearch11;
    private javax.swing.JTextField txtSearch12;
    private javax.swing.JTextField txtSearch13;
    private javax.swing.JTextField txtSearch14;
    private javax.swing.JTextField txtSearch8;
    private javax.swing.JTextField txtSearch9;
    // End of variables declaration//GEN-END:variables
}

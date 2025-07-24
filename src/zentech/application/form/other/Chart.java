package zentech.application.form.other;

import chart.chart.CurveLineChart;
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
    private ChartService service = new ChartService();

    public Chart() {
        initComponents();

        initalChart(chartRevenue, "Tổng quan", "Doanh thu", "Vốn", "Lợi nhuận");
        initalChart(chartRevenueYears, "Thống kê theo năm", "Doanh thu", "Vốn", "Lợi nhuận");
        initalChart(chartRevenueMonths, "Thống kê theo tháng", "Doanh thu", "Vốn", "Lợi nhuận");
        initalChart(chartRevenueDays, "Thống kê theo ngày", "Doanh thu", "Vốn", "Lợi nhuận");

        initalTable(tblDoanhThuTongQuan);
        initalTable(tblDoanhThuTheoNam);
        initalTable(tblDoanhThuTheoThang);
        initalTable(tblDoanhThuTheoNgay);

        setDataBase();
        setDataYears("2022", "2025");
        setDataMonths("2025");
        setDataDays("2025", "07");

        loadRevenueBaseData();
        loadRevenueByYears("2022", "2025");
        loadRevenueByMonths("2025");
        loadRevenueByDays("2025", "07");

        initalCount();

    }

    private void initalChart(CurveLineChart chart, String title, String legend1, String legend2, String legend3) {
        chart.setTitle(title);

        chart.addLegend(legend1, Color.decode("#7b4397"), Color.decode("#dc2430"));
        chart.addLegend(legend2, Color.decode("#e65c00"), Color.decode("#F9D423"));
        chart.addLegend(legend3, Color.decode("#0099F7"), Color.decode("#F11712"));
    }

    private void initalTable(JTable table) {

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

        table.getTableHeader().setDefaultRenderer(getAlignmentCellRender(table.getTableHeader().getDefaultRenderer(), true));
        table.setDefaultRenderer(Object.class, getAlignmentCellRender(table.getDefaultRenderer(Object.class), false));

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

        table.getColumnModel().getColumn(1).setCellRenderer(colorRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(colorRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(colorRenderer);

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

    private void initalCount() {
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

    private void setDataBase() {
        try {
            List<Chart_Revenue> lists = service.getRevenue10MonthService();

            //Xoá dữ liệu cũ
            chartRevenue.clear();

            for (int i = lists.size() - 1; i >= 0; i--) {
                Chart_Revenue d = lists.get(i);
                chartRevenue.addData(new ModelChart(
                        d.getThang(),
                        new double[]{d.getDoanhThu(), d.getGiaVon(), d.getLoiNhuan()}
                ));
            }

            //Animation
            chartRevenue.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDataYears(String fromYear, String toYear) {
        try {
            List<Chart_Revenue> lists = service.getRevenueYearsService(fromYear, toYear);

            chartRevenueYears.clear();

            for (int i = lists.size() - 1; i >= 0; i--) {
                Chart_Revenue d = lists.get(i);
                chartRevenueYears.addData(new ModelChart(
                        d.getThang(),
                        new double[]{d.getDoanhThu(), d.getGiaVon(), d.getLoiNhuan()}
                ));
            }

            chartRevenueYears.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDataMonths(String year) {
        try {
            List<Chart_Revenue> lists = service.getRevenueMonthsService(year);

            chartRevenueMonths.clear();

            for (int i = lists.size() - 1; i >= 0; i--) {
                Chart_Revenue d = lists.get(i);
                chartRevenueMonths.addData(new ModelChart(
                        d.getThang(),
                        new double[]{d.getDoanhThu(), d.getGiaVon(), d.getLoiNhuan()}
                ));
            }

            chartRevenueMonths.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDataDays(String year, String month) {
        try {
            List<Chart_Revenue> lists = service.getRevenueDaysService(year, month);

            chartRevenueDays.clear();

            for (int i = lists.size() - 1; i >= 0; i--) {
                Chart_Revenue d = lists.get(i);
                chartRevenueDays.addData(new ModelChart(
                        d.getThang(),
                        new double[]{d.getDoanhThu(), d.getGiaVon(), d.getLoiNhuan()}
                ));
            }

            chartRevenueDays.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadRevenueBaseData() {
        try {
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

    private void loadRevenueByYears(String fromYear, String toYear) {
        try {
            List<Chart_Revenue> list = service.getRevenueYearsService(fromYear, toYear);

            DefaultTableModel model = (DefaultTableModel) tblDoanhThuTheoNam.getModel();
            model.setRowCount(0);

            DecimalFormat formatter = new DecimalFormat("#,###");

            for (Chart_Revenue d : list) {
                model.addRow(new Object[]{
                    d.getThang(),
                    formatter.format(d.getGiaVon()),
                    formatter.format(d.getLoiNhuan()),
                    formatter.format(d.getDoanhThu())
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadRevenueByMonths(String year) {
        try {
            List<Chart_Revenue> list = service.getRevenueMonthsService(year);

            DefaultTableModel model = (DefaultTableModel) tblDoanhThuTheoThang.getModel();
            model.setRowCount(0);

            DecimalFormat formatter = new DecimalFormat("#,###");

            for (Chart_Revenue d : list) {
                model.addRow(new Object[]{
                    d.getThang(),
                    formatter.format(d.getGiaVon()),
                    formatter.format(d.getLoiNhuan()),
                    formatter.format(d.getDoanhThu())
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadRevenueByDays(String year, String month) {
        try {
            ChartService service = new ChartService();
            List<Chart_Revenue> list = service.getRevenueDaysService(year, month);

            DefaultTableModel model = (DefaultTableModel) tblDoanhThuTheoNgay.getModel();
            model.setRowCount(0);

            DecimalFormat formatter = new DecimalFormat("#,###");

            for (Chart_Revenue d : list) {
                model.addRow(new Object[]{
                    d.getThang(),
                    formatter.format(d.getGiaVon()),
                    formatter.format(d.getLoiNhuan()),
                    formatter.format(d.getDoanhThu())
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
        chartRevenue = new chart.chart.CurveLineChart();
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
        chartRevenueYears = new chart.chart.CurveLineChart();
        crazyPanel16 = new raven.crazypanel.CrazyPanel();
        crazyPanel17 = new raven.crazypanel.CrazyPanel();
        jLabel21 = new javax.swing.JLabel();
        txtThongKeTheoNam_TuNam = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtThongKeTheoNam_DenNam = new javax.swing.JTextField();
        btnNam = new javax.swing.JButton();
        btnUpdate5 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblDoanhThuTheoNam = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        panelShadow11 = new chart.panel.PanelShadow();
        chartRevenueMonths = new chart.chart.CurveLineChart();
        crazyPanel18 = new raven.crazypanel.CrazyPanel();
        crazyPanel19 = new raven.crazypanel.CrazyPanel();
        jLabel25 = new javax.swing.JLabel();
        txtThongKeTheoThang_Nam = new javax.swing.JTextField();
        btnThang = new javax.swing.JButton();
        btnUpdate6 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblDoanhThuTheoThang = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        crazyPanel20 = new raven.crazypanel.CrazyPanel();
        crazyPanel21 = new raven.crazypanel.CrazyPanel();
        jLabel22 = new javax.swing.JLabel();
        txtThongKeTheoNgay_Nam = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtThongKeTheoNgay_Thang = new javax.swing.JTextField();
        btnNgay = new javax.swing.JButton();
        btnUpdate7 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblDoanhThuTheoNgay = new javax.swing.JTable();
        panelShadow12 = new chart.panel.PanelShadow();
        chartRevenueDays = new chart.chart.CurveLineChart();
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

        chartRevenue.setBackground(new java.awt.Color(0, 0, 0));
        chartRevenue.setForeground(new java.awt.Color(0, 0, 0));
        chartRevenue.setFillColor(true);

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartRevenue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartRevenue, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
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

        chartRevenueYears.setBackground(new java.awt.Color(0, 0, 0));
        chartRevenueYears.setForeground(new java.awt.Color(0, 0, 0));
        chartRevenueYears.setFillColor(true);

        javax.swing.GroupLayout panelShadow10Layout = new javax.swing.GroupLayout(panelShadow10);
        panelShadow10.setLayout(panelShadow10Layout);
        panelShadow10Layout.setHorizontalGroup(
            panelShadow10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartRevenueYears, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow10Layout.setVerticalGroup(
            panelShadow10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartRevenueYears, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
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

        txtThongKeTheoNam_TuNam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel17.add(txtThongKeTheoNam_TuNam);

        jLabel23.setText("Đến năm");
        crazyPanel17.add(jLabel23);

        txtThongKeTheoNam_DenNam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel17.add(txtThongKeTheoNam_DenNam);

        btnNam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNam.setText("Thống kê");
        btnNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNamActionPerformed(evt);
            }
        });
        crazyPanel17.add(btnNam);

        btnUpdate5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate5.setText("Làm mới");
        btnUpdate5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate5ActionPerformed(evt);
            }
        });
        crazyPanel17.add(btnUpdate5);

        crazyPanel16.add(crazyPanel17);

        tblDoanhThuTheoNam.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDoanhThuTheoNam.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(tblDoanhThuTheoNam);

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

        chartRevenueMonths.setBackground(new java.awt.Color(0, 0, 0));
        chartRevenueMonths.setForeground(new java.awt.Color(0, 0, 0));
        chartRevenueMonths.setFillColor(true);

        javax.swing.GroupLayout panelShadow11Layout = new javax.swing.GroupLayout(panelShadow11);
        panelShadow11.setLayout(panelShadow11Layout);
        panelShadow11Layout.setHorizontalGroup(
            panelShadow11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartRevenueMonths, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow11Layout.setVerticalGroup(
            panelShadow11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartRevenueMonths, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
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

        txtThongKeTheoThang_Nam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel19.add(txtThongKeTheoThang_Nam);

        btnThang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThang.setText("Thống kê");
        btnThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThangActionPerformed(evt);
            }
        });
        crazyPanel19.add(btnThang);

        btnUpdate6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate6.setText("Làm mới");
        btnUpdate6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate6ActionPerformed(evt);
            }
        });
        crazyPanel19.add(btnUpdate6);

        crazyPanel18.add(crazyPanel19);

        tblDoanhThuTheoThang.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDoanhThuTheoThang.getTableHeader().setReorderingAllowed(false);
        jScrollPane9.setViewportView(tblDoanhThuTheoThang);

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

        txtThongKeTheoNgay_Nam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel21.add(txtThongKeTheoNgay_Nam);

        jLabel24.setText("Chọn tháng");
        crazyPanel21.add(jLabel24);

        txtThongKeTheoNgay_Thang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel21.add(txtThongKeTheoNgay_Thang);

        btnNgay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNgay.setText("Thống kê");
        btnNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayActionPerformed(evt);
            }
        });
        crazyPanel21.add(btnNgay);

        btnUpdate7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate7.setText("Làm mới");
        btnUpdate7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate7ActionPerformed(evt);
            }
        });
        crazyPanel21.add(btnUpdate7);

        crazyPanel20.add(crazyPanel21);

        tblDoanhThuTheoNgay.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDoanhThuTheoNgay.getTableHeader().setReorderingAllowed(false);
        jScrollPane10.setViewportView(tblDoanhThuTheoNgay);

        crazyPanel20.add(jScrollPane10);

        panelShadow12.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelShadow12.setForeground(new java.awt.Color(255, 255, 255));
        panelShadow12.setToolTipText("");
        panelShadow12.setShadowColor(new java.awt.Color(255, 255, 255));

        chartRevenueDays.setBackground(new java.awt.Color(0, 0, 0));
        chartRevenueDays.setForeground(new java.awt.Color(0, 0, 0));
        chartRevenueDays.setFillColor(true);

        javax.swing.GroupLayout panelShadow12Layout = new javax.swing.GroupLayout(panelShadow12);
        panelShadow12.setLayout(panelShadow12Layout);
        panelShadow12Layout.setHorizontalGroup(
            panelShadow12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartRevenueDays, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow12Layout.setVerticalGroup(
            panelShadow12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartRevenueDays, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
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

    private void btnNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNamActionPerformed
        if (service.getValidateRevenueYearsService(txtThongKeTheoNam_TuNam.getText().trim(), txtThongKeTheoNam_DenNam.getText().trim())) {
            setDataYears(txtThongKeTheoNam_TuNam.getText().trim(), txtThongKeTheoNam_DenNam.getText().trim());
            loadRevenueByYears(txtThongKeTheoNam_TuNam.getText().trim(), txtThongKeTheoNam_DenNam.getText().trim());
        }
    }//GEN-LAST:event_btnNamActionPerformed

    private void btnThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThangActionPerformed
        if (service.getValidateRevenueMonthsService(txtThongKeTheoThang_Nam.getText().trim())) {
            setDataMonths(txtThongKeTheoThang_Nam.getText().trim());
            loadRevenueByMonths(txtThongKeTheoThang_Nam.getText().trim());
        }
    }//GEN-LAST:event_btnThangActionPerformed

    private void btnNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayActionPerformed
        if (service.getValidateRevenueDaysService(txtThongKeTheoNgay_Nam.getText().trim(), txtThongKeTheoNgay_Thang.getText().trim())) {
            setDataDays(txtThongKeTheoNgay_Nam.getText().trim(), txtThongKeTheoNgay_Thang.getText().trim());
            loadRevenueByDays(txtThongKeTheoNgay_Nam.getText().trim(), txtThongKeTheoNgay_Thang.getText().trim());
        }
    }//GEN-LAST:event_btnNgayActionPerformed

    private void btnUpdate5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate5ActionPerformed
        initalChart(chartRevenueYears, "Thống kê theo năm", "Doanh thu", "Vốn", "Lợi nhuận");
        initalTable(tblDoanhThuTheoNam);
    }//GEN-LAST:event_btnUpdate5ActionPerformed

    private void btnUpdate6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate6ActionPerformed
        initalChart(chartRevenueMonths, "Thống kê theo tháng", "Doanh thu", "Vốn", "Lợi nhuận");
        initalTable(tblDoanhThuTheoThang);
    }//GEN-LAST:event_btnUpdate6ActionPerformed

    private void btnUpdate7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate7ActionPerformed
        initalChart(chartRevenueDays, "Thống kê theo ngày", "Doanh thu", "Vốn", "Lợi nhuận");
        initalTable(tblDoanhThuTheoNgay);
    }//GEN-LAST:event_btnUpdate7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNam;
    private javax.swing.JButton btnNgay;
    private javax.swing.JButton btnThang;
    private javax.swing.JButton btnUpdate5;
    private javax.swing.JButton btnUpdate6;
    private javax.swing.JButton btnUpdate7;
    private chart.chart.CurveLineChart chartRevenue;
    private chart.chart.CurveLineChart chartRevenueDays;
    private chart.chart.CurveLineChart chartRevenueMonths;
    private chart.chart.CurveLineChart chartRevenueYears;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel16;
    private raven.crazypanel.CrazyPanel crazyPanel17;
    private raven.crazypanel.CrazyPanel crazyPanel18;
    private raven.crazypanel.CrazyPanel crazyPanel19;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel20;
    private raven.crazypanel.CrazyPanel crazyPanel21;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
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
    private javax.swing.JTable tblDoanhThuTheoNam;
    private javax.swing.JTable tblDoanhThuTheoNgay;
    private javax.swing.JTable tblDoanhThuTheoThang;
    private javax.swing.JTable tblDoanhThuTongQuan;
    private javax.swing.JTextField txtThongKeTheoNam_DenNam;
    private javax.swing.JTextField txtThongKeTheoNam_TuNam;
    private javax.swing.JTextField txtThongKeTheoNgay_Nam;
    private javax.swing.JTextField txtThongKeTheoNgay_Thang;
    private javax.swing.JTextField txtThongKeTheoThang_Nam;
    // End of variables declaration//GEN-END:variables
}

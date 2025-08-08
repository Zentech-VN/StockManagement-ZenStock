package zentech.application.form.other;

import chart.chart.CurveLineChart;
import chart.chart.ModelChart;
import com.formdev.flatlaf.FlatClientProperties;
import entity.Chart_Customer;
import entity.Chart_Employee;
import entity.Chart_Inventory;
import entity.Chart_ProductOutOfStock;
import entity.Chart_ProductTopSelling;
import entity.Chart_Revenue;
import entity.Chart_Supplier;
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import raven.toast.Notifications;
import service.ChartService;
import service.ClientService;
import service.EmployeeService;
import service.ProductServiceMain;

public class Chart extends javax.swing.JPanel {

    private EmployeeService employeeService = new EmployeeService();
    private ProductServiceMain productService = new ProductServiceMain();
    private ClientService clientService = new ClientService();
    private ChartService service = new ChartService();

    private LocalDate today = LocalDate.now();
    private LocalDate firstDayOfMonth = today.withDayOfMonth(1);
    private String year = String.valueOf(LocalDate.now().getYear());
    private String month = String.valueOf(LocalDate.now().getMonthValue());

    public Chart() {
        initComponents();

        //Khởi tạo giao diện chart
        initalChart(chartRevenue, "Tổng quan", "Doanh thu", "Vốn", "Lợi nhuận");
        initalChart(chartRevenueYears, "Thống kê theo năm", "Doanh thu", "Vốn", "Lợi nhuận");
        initalChart(chartRevenueMonths, "Thống kê theo tháng", "Doanh thu", "Vốn", "Lợi nhuận");
        initalChart(chartRevenueDays, "Thống kê theo ngày", "Doanh thu", "Vốn", "Lợi nhuận");

        //Khởi tạo giao diện JTable
        initalTable(tblDoanhThuTongQuan);
        initalTable(tblDoanhThuTheoNam);
        initalTable(tblDoanhThuTheoThang);
        initalTable(tblDoanhThuTheoNgay);
        initalTableMini(tblThongKeTonKho);
        initalTableMini(tblSanPhamBanChay);
        initalTableMini(tblSanPhamHetHang);
        initalTableMini(tblNhanVienXuatKho);
        initalTableMini(tblNhanVienNhapKho);
        initalTableMini(tblKhachHang);
        initalTableMini(tblNhaCungCap);

        String year = String.valueOf(LocalDate.now().getYear());
        String month = String.valueOf(LocalDate.now().getMonthValue());

        //Truyền data vào chart
        setDataBase();
        setDataYears("2022", year);
        setDataMonths(year);
        setDataDays(year, month);

        //Truyền data vào JTable
        loadRevenueBaseData();
        loadRevenueByYears("2022", year);
        loadRevenueByMonths(year);
        loadRevenueByDays(year, month);
        loadInventoryAll();
        List<Chart_Inventory> inventories = new ArrayList<>();
        inventories = service.getInventoryByKeyWordService(firstDayOfMonth.toString(), today.toString(), "");
        loadInventoryByKeyWord(inventories);
        loadTopSellingProducts("", "", "");
        loadLowStockProductsToTable("", "");
        loadTopEmployeeExport(firstDayOfMonth.toString(), today.toString(), "", "10");
        loadTopEmployeeImport(firstDayOfMonth.toString(), today.toString(), "", "10");
        loadTopCustomer(firstDayOfMonth.toString(), today.toString(), "", "10");
        loadTopSupplier(firstDayOfMonth.toString(), today.toString(), "", "10");

        //Khởi tạo giao diện JTextField
        initalTextField(txtThongKeTheoNam_DenNam, "2025");
        initalTextField(txtThongKeTheoNam_TuNam, "2022");
        initalTextField(txtThongKeTheoNgay_Nam, "2025");
        initalTextField(txtThongKeTheoNgay_Thang, "07");
        initalTextField(txtThongKeTheoThang_Nam, "2025");

        //Tồn kho
        initalTextField(txtThongKeTonKho_SanPham, "Tìm kiếm");
        initalTextField(txtThongKeTonKho_TuNgay, firstDayOfMonth.toString());
        initalTextField(txtThongKeTonKho_DenNgay, today.toString());
        txtThongKeTonKho_TuNgay.setText(firstDayOfMonth.toString());
        txtThongKeTonKho_DenNgay.setText(today.toString());
        lblThongKeTonKho_ThoiGian.setText(txtThongKeTonKho_TuNgay.getText() + " đến " + txtThongKeTonKho_DenNgay.getText());

        //Bán chạy
        initalTextField(txtSanPhamBanChay_SanPham, "Tìm kiếm");
        initalTextField(txtSanPhamBanChay_TuNgay, firstDayOfMonth.toString());
        initalTextField(txtSanPhamBanChay_DenNgay, today.toString());
        txtSanPhamBanChay_TuNgay.setText(firstDayOfMonth.toString());
        txtSanPhamBanChay_DenNgay.setText(today.toString());
        lblSanPhamBanChay_ThoiGian.setText(txtSanPhamBanChay_TuNgay.getText() + " đến " + txtSanPhamBanChay_DenNgay.getText());

        //Hết hàng
        initalTextField(txtSanPhamHetHang_SanPham, "Tìm kiếm");
        initalTextField(txtSanPhamHetHang_SoLuong, "5");

        // TextField mới - Nhân viên xuất kho
        initalTextField(txtNhanVienXuatKho_TimKiem, "Tìm kiếm");
        initalTextField(txtNhanVienXuatKho_TuNgay, firstDayOfMonth.toString());
        initalTextField(txtNhanVienXuatKho_DenNgay, today.toString());
        initalTextField(txtNhanVienXuatKho_SoLuong, "10");
        txtNhanVienXuatKho_TuNgay.setText(firstDayOfMonth.toString());
        txtNhanVienXuatKho_DenNgay.setText(today.toString());
        lblNhanVienXuatKho_ThoiGian.setText(txtNhanVienXuatKho_TuNgay.getText() + " đến " + txtNhanVienXuatKho_DenNgay.getText());

        // TextField mới - Nhân viên nhập kho
        initalTextField(txtNhanVienNhapKho_TimKiem, "Tìm kiếm");
        initalTextField(txtNhanVienNhapKho_TuNgay, firstDayOfMonth.toString());
        initalTextField(txtNhanVienNhapKho_DenNgay, today.toString());
        initalTextField(txtNhanVienNhapKho_SoLuong, "10");
        txtNhanVienNhapKho_TuNgay.setText(firstDayOfMonth.toString());
        txtNhanVienNhapKho_DenNgay.setText(today.toString());
        lblNhanVienNhapKho_ThoiGian.setText(txtNhanVienNhapKho_TuNgay.getText() + " đến " + txtNhanVienNhapKho_DenNgay.getText());

        // TextField mới - Khách hàng
        initalTextField(txtKhachHang_TimKiem, "Tìm kiếm");
        initalTextField(txtKhachHang_TuNgay, firstDayOfMonth.toString());
        initalTextField(txtKhachHang_DenNgay, today.toString());
        initalTextField(txtKhachHang_SoLuong, "10");
        txtKhachHang_TuNgay.setText(firstDayOfMonth.toString());
        txtKhachHang_DenNgay.setText(today.toString());
        lblKhachHang_ThoiGian.setText(txtKhachHang_TuNgay.getText() + " đến " + txtKhachHang_DenNgay.getText());

        // TextField mới - Nhà cung cấp
        initalTextField(txtNhaCungCap_TimKiem, "Tìm kiếm");
        initalTextField(txtNhaCungCap_TuNgay, firstDayOfMonth.toString());
        initalTextField(txtNhaCungCap_DenNgay, today.toString());
        initalTextField(txtNhaCungCap_SoLuong, "10");
        txtNhaCungCap_TuNgay.setText(firstDayOfMonth.toString());
        txtNhaCungCap_DenNgay.setText(today.toString());
        lblNhaCungCap_ThoiGian.setText(txtNhaCungCap_TuNgay.getText() + " đến " + txtNhaCungCap_DenNgay.getText());

        //Giao diện đếm số lượng
        initalCount();

    }

    private void initalChart(CurveLineChart chart, String title, String legend1, String legend2, String legend3) {
        chart.clear();

        chart.setTitle(title);

        chart.addLegend(legend1, Color.decode("#7b4397"), Color.decode("#dc2430"));
        chart.addLegend(legend2, Color.decode("#e65c00"), Color.decode("#F9D423"));
        chart.addLegend(legend3, Color.decode("#0099F7"), Color.decode("#F11712"));

        //Animation
        chart.start();
    }

    private void initalTextField(JTextField txt, String hint) {
        txt.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, hint);
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

    private void initalTableMini(JTable table) {
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
        SwingWorker<List<Chart_Revenue>, Void> worker = new SwingWorker<List<Chart_Revenue>, Void>() {
            @Override
            protected List<Chart_Revenue> doInBackground() throws Exception {
                // Lấy dữ liệu trong background
                return service.getRevenue6MonthService();
            }

            @Override
            protected void done() {
                try {
                    List<Chart_Revenue> lists = get(); // lấy kết quả từ doInBackground
                    chartRevenue.clear();

                    for (int i = lists.size() - 1; i >= 0; i--) {
                        Chart_Revenue d = lists.get(i);
                        chartRevenue.addData(new ModelChart(
                                d.getThang(),
                                new double[]{d.getDoanhThu(), d.getGiaVon(), d.getLoiNhuan()}
                        ));
                    }

                    chartRevenue.start(); // chạy animation
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    private void setDataYears(String fromYear, String toYear) {
        SwingWorker<List<Chart_Revenue>, Void> worker = new SwingWorker<List<Chart_Revenue>, Void>() {
            @Override
            protected List<Chart_Revenue> doInBackground() throws Exception {
                return service.getRevenueYearsService(fromYear, toYear);
            }

            @Override
            protected void done() {
                try {
                    List<Chart_Revenue> lists = get();
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
        };
        worker.execute();
    }

    private void setDataMonths(String year) {
        SwingWorker<List<Chart_Revenue>, Void> worker = new SwingWorker<List<Chart_Revenue>, Void>() {
            @Override
            protected List<Chart_Revenue> doInBackground() throws Exception {
                return service.getRevenueMonthsService(year);
            }

            @Override
            protected void done() {
                try {
                    List<Chart_Revenue> lists = get();
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
        };
        worker.execute();
    }

    private void setDataDays(String year, String month) {
        SwingWorker<List<Chart_Revenue>, Void> worker = new SwingWorker<List<Chart_Revenue>, Void>() {
            @Override
            protected List<Chart_Revenue> doInBackground() throws Exception {
                return service.getRevenueDaysService(year, month);
            }

            @Override
            protected void done() {
                try {
                    List<Chart_Revenue> lists = get();
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
        };
        worker.execute();
    }

    private void loadRevenueBaseData() {
        SwingWorker<List<Chart_Revenue>, Void> worker = new SwingWorker<List<Chart_Revenue>, Void>() {
            @Override
            protected List<Chart_Revenue> doInBackground() throws Exception {
                return service.getRevenueService();
            }

            @Override
            protected void done() {
                try {
                    List<Chart_Revenue> list = get();
                    DefaultTableModel model = (DefaultTableModel) tblDoanhThuTongQuan.getModel();
                    model.setRowCount(0);

                    DecimalFormat formatter = new DecimalFormat("#,###");
                    for (Chart_Revenue d : list) {
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
        };
        worker.execute();
    }

    private void loadRevenueByYears(String fromYear, String toYear) {
        SwingWorker<List<Chart_Revenue>, Void> worker = new SwingWorker<List<Chart_Revenue>, Void>() {
            @Override
            protected List<Chart_Revenue> doInBackground() throws Exception {
                return service.getRevenueYearsService(fromYear, toYear);
            }

            @Override
            protected void done() {
                try {
                    List<Chart_Revenue> list = get();
                    DefaultTableModel model = (DefaultTableModel) tblDoanhThuTheoNam.getModel();
                    model.setRowCount(0);

                    DecimalFormat formatter = new DecimalFormat("#,###");
                    for (Chart_Revenue d : list) {
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
        };
        worker.execute();
    }

    private void loadRevenueByMonths(String year) {
        SwingWorker<List<Chart_Revenue>, Void> worker = new SwingWorker<List<Chart_Revenue>, Void>() {
            @Override
            protected List<Chart_Revenue> doInBackground() throws Exception {
                return service.getRevenueMonthsService(year);
            }

            @Override
            protected void done() {
                try {
                    List<Chart_Revenue> list = get();
                    DefaultTableModel model = (DefaultTableModel) tblDoanhThuTheoThang.getModel();
                    model.setRowCount(0);

                    DecimalFormat formatter = new DecimalFormat("#,###");
                    for (Chart_Revenue d : list) {
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
        };
        worker.execute();
    }

    private void loadRevenueByDays(String year, String month) {
        SwingWorker<List<Chart_Revenue>, Void> worker = new SwingWorker<List<Chart_Revenue>, Void>() {

            @Override
            protected List<Chart_Revenue> doInBackground() throws Exception {
                return service.getRevenueDaysService(year, month);
            }

            @Override
            protected void done() {
                try {
                    List<Chart_Revenue> list = get();
                    DefaultTableModel model = (DefaultTableModel) tblDoanhThuTheoNgay.getModel();
                    model.setRowCount(0);

                    DecimalFormat formatter = new DecimalFormat("#,###");
                    for (Chart_Revenue d : list) {
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
        };
        worker.execute();
    }

    private void loadInventoryAll() {
        SwingWorker<List<Chart_Inventory>, Void> worker = new SwingWorker<List<Chart_Inventory>, Void>() {

            @Override
            protected List<Chart_Inventory> doInBackground() throws Exception {
                return service.getInventoryAllService("");
            }

            @Override
            protected void done() {
                try {
                    List<Chart_Inventory> list = get();
                    DefaultTableModel model = (DefaultTableModel) tblThongKeTonKho.getModel();
                    model.setRowCount(0);

                    int stt = 1;
                    for (Chart_Inventory ci : list) {
                        model.addRow(new Object[]{
                            stt++,
                            ci.getMaSanPham(),
                            ci.getTenSanPham(),
                            ci.getTonDauKy(),
                            ci.getNhapTrongKy(),
                            ci.getXuatTrongKy(),
                            ci.getTonCuoiKy()
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    private void loadInventoryByKeyWord(List<Chart_Inventory> inputList) {
        SwingWorker<List<Chart_Inventory>, Void> worker = new SwingWorker<List<Chart_Inventory>, Void>() {
            @Override
            protected List<Chart_Inventory> doInBackground() {
                return inputList; // đã có list từ ngoài
            }

            @Override
            protected void done() {
                try {
                    List<Chart_Inventory> list = get();
                    DefaultTableModel model = (DefaultTableModel) tblThongKeTonKho.getModel();
                    model.setRowCount(0);

                    int stt = 1;
                    for (Chart_Inventory i : list) {
                        model.addRow(new Object[]{
                            stt++,
                            i.getMaSanPham(),
                            i.getTenSanPham(),
                            i.getTonDauKy(),
                            i.getNhapTrongKy(),
                            i.getXuatTrongKy(),
                            i.getTonCuoiKy()
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    private void loadTopSellingProducts(String fromDate, String toDate, String keyword) {
        SwingWorker<List<Chart_ProductTopSelling>, Void> worker = new SwingWorker<List<Chart_ProductTopSelling>, Void>() {
            @Override
            protected List<Chart_ProductTopSelling> doInBackground() throws Exception {
                return service.getTopSellingProductService(fromDate, toDate, keyword);
            }

            @Override
            protected void done() {
                try {
                    List<Chart_ProductTopSelling> list = get();
                    DefaultTableModel model = (DefaultTableModel) tblSanPhamBanChay.getModel();
                    model.setRowCount(0);

                    DecimalFormat formatter = new DecimalFormat("#,###");
                    for (Chart_ProductTopSelling p : list) {
                        model.addRow(new Object[]{
                            p.getMaSanPham(),
                            p.getTenSanPham(),
                            formatter.format(p.getSoLuongBan())
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    private void loadLowStockProductsToTable(String keyword, String minQuantityText) {
        SwingWorker<List<Chart_ProductOutOfStock>, Void> worker = new SwingWorker<List<Chart_ProductOutOfStock>, Void>() {
            @Override
            protected List<Chart_ProductOutOfStock> doInBackground() throws Exception {
                return service.getProductOutOfStockService(keyword, minQuantityText);
            }

            @Override
            protected void done() {
                try {
                    List<Chart_ProductOutOfStock> list = get();

                    if (list.isEmpty()) {
                        lblSanPhamHetHang_ThongBao.setText("*Không có sản phẩm nào sắp hết hàng!");
                    }

                    DefaultTableModel model = (DefaultTableModel) tblSanPhamHetHang.getModel();
                    model.setRowCount(0);

                    for (Chart_ProductOutOfStock p : list) {
                        model.addRow(new Object[]{
                            p.getMaSanPham(),
                            p.getTenSanPham(),
                            p.getSoLuong()
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    private void loadTopEmployeeExport(String fromDate, String toDate, String keyword, String quantity) {
        SwingWorker<List<Chart_Employee>, Void> worker = new SwingWorker<List<Chart_Employee>, Void>() {
            @Override
            protected List<Chart_Employee> doInBackground() throws Exception {
                return service.getTopEmployeeExportService(fromDate, toDate, keyword, quantity);
            }

            @Override
            protected void done() {
                try {
                    List<Chart_Employee> list = get();
                    DefaultTableModel model = (DefaultTableModel) tblNhanVienXuatKho.getModel();
                    model.setRowCount(0);

                    DecimalFormat formatter = new DecimalFormat("#,###");
                    for (Chart_Employee e : list) {
                        model.addRow(new Object[]{
                            e.getMaNhanVien(),
                            e.getHoTen(),
                            formatter.format(e.getTongSoLuong())
                        });
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    private void loadTopEmployeeImport(String fromDate, String toDate, String keyword, String quantity) {
        SwingWorker<List<Chart_Employee>, Void> worker = new SwingWorker<List<Chart_Employee>, Void>() {
            @Override
            protected List<Chart_Employee> doInBackground() throws Exception {
                return service.getTopEmployeeImportService(fromDate, toDate, keyword, quantity);
            }

            @Override
            protected void done() {
                try {
                    List<Chart_Employee> list = get();
                    DefaultTableModel model = (DefaultTableModel) tblNhanVienNhapKho.getModel();
                    model.setRowCount(0);

                    DecimalFormat formatter = new DecimalFormat("#,###");
                    for (Chart_Employee e : list) {
                        model.addRow(new Object[]{
                            e.getMaNhanVien(),
                            e.getHoTen(),
                            formatter.format(e.getTongSoLuong())
                        });
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    private void loadTopCustomer(String fromDate, String toDate, String keyword, String quantity) {
        SwingWorker<List<Chart_Customer>, Void> worker = new SwingWorker<List<Chart_Customer>, Void>() {
            @Override
            protected List<Chart_Customer> doInBackground() throws Exception {
                return service.getTopCustomerService(fromDate, toDate, keyword, quantity);
            }

            @Override
            protected void done() {
                try {
                    List<Chart_Customer> list = get();
                    DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
                    model.setRowCount(0);

                    DecimalFormat formatter = new DecimalFormat("#,###");
                    for (Chart_Customer c : list) {
                        model.addRow(new Object[]{
                            c.getMaKhachHang(),
                            c.getTenKhachHang(),
                            formatter.format(c.getTongSoLuong())
                        });
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    private void loadTopSupplier(String fromDate, String toDate, String keyword, String quantity) {
        SwingWorker<List<Chart_Supplier>, Void> worker = new SwingWorker<List<Chart_Supplier>, Void>() {
            @Override
            protected List<Chart_Supplier> doInBackground() throws Exception {
                return service.getTopSupplierService(fromDate, toDate, keyword, quantity);
            }

            @Override
            protected void done() {
                try {
                    List<Chart_Supplier> list = get();
                    DefaultTableModel model = (DefaultTableModel) tblNhaCungCap.getModel();
                    model.setRowCount(0);

                    DecimalFormat formatter = new DecimalFormat("#,###");
                    for (Chart_Supplier s : list) {
                        model.addRow(new Object[]{
                            s.getMaNhaCungCap(),
                            s.getTenNhaCungCap(),
                            formatter.format(s.getTongSoLuong())
                        });
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        worker.execute();
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
        btnLamMoiNam = new javax.swing.JButton();
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
        btnLamMoiThang = new javax.swing.JButton();
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
        btnLamMoiNgay = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblDoanhThuTheoNgay = new javax.swing.JTable();
        panelShadow12 = new chart.panel.PanelShadow();
        chartRevenueDays = new chart.chart.CurveLineChart();
        jPanel3 = new javax.swing.JPanel();
        crazyPanel22 = new raven.crazypanel.CrazyPanel();
        jLabel8 = new javax.swing.JLabel();
        txtThongKeTonKho_SanPham = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtThongKeTonKho_TuNgay = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtThongKeTonKho_DenNgay = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnThongKeTonKho_TimKiem = new javax.swing.JButton();
        btnThongKeTonKho_LamMoi = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        lblThongKeTonKho_ThoiGian = new javax.swing.JLabel();
        crazyPanel23 = new raven.crazypanel.CrazyPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThongKeTonKho = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        materialTabbed4 = new zentech.application.tabbed.MaterialTabbed();
        jPanel9 = new javax.swing.JPanel();
        crazyPanel26 = new raven.crazypanel.CrazyPanel();
        jLabel18 = new javax.swing.JLabel();
        txtSanPhamBanChay_SanPham = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtSanPhamBanChay_TuNgay = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtSanPhamBanChay_DenNgay = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        btnSanPhamBanChay_TimKiem = new javax.swing.JButton();
        btnSanPhamBanChay_LamMoi = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        lblSanPhamBanChay_ThoiGian = new javax.swing.JLabel();
        crazyPanel27 = new raven.crazypanel.CrazyPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSanPhamBanChay = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        crazyPanel28 = new raven.crazypanel.CrazyPanel();
        jLabel26 = new javax.swing.JLabel();
        txtSanPhamHetHang_SanPham = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtSanPhamHetHang_SoLuong = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        btnSanPhamHetHang_TimKiem = new javax.swing.JButton();
        btnSanPhamHetHang_LamMoi = new javax.swing.JButton();
        lblSanPhamHetHang_ThongBao = new javax.swing.JLabel();
        crazyPanel29 = new raven.crazypanel.CrazyPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSanPhamHetHang = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        materialTabbed5 = new zentech.application.tabbed.MaterialTabbed();
        jPanel14 = new javax.swing.JPanel();
        crazyPanel30 = new raven.crazypanel.CrazyPanel();
        jLabel34 = new javax.swing.JLabel();
        txtNhanVienXuatKho_TimKiem = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtNhanVienXuatKho_TuNgay = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtNhanVienXuatKho_DenNgay = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtNhanVienXuatKho_SoLuong = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        btnNhanVienXuatKho_TimKiem = new javax.swing.JButton();
        btnNhanVienXuatKho_LamMoi = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        lblNhanVienXuatKho_ThoiGian = new javax.swing.JLabel();
        crazyPanel31 = new raven.crazypanel.CrazyPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblNhanVienXuatKho = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        crazyPanel32 = new raven.crazypanel.CrazyPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblNhanVienNhapKho = new javax.swing.JTable();
        crazyPanel33 = new raven.crazypanel.CrazyPanel();
        jLabel41 = new javax.swing.JLabel();
        txtNhanVienNhapKho_TimKiem = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txtNhanVienNhapKho_TuNgay = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txtNhanVienNhapKho_DenNgay = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtNhanVienNhapKho_SoLuong = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        btnNhanVienNhapKho_TimKiem = new javax.swing.JButton();
        btnNhanVienNhapKho_LamMoi = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        lblNhanVienNhapKho_ThoiGian = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        crazyPanel34 = new raven.crazypanel.CrazyPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        crazyPanel35 = new raven.crazypanel.CrazyPanel();
        jLabel48 = new javax.swing.JLabel();
        txtKhachHang_TimKiem = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtKhachHang_TuNgay = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        txtKhachHang_DenNgay = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtKhachHang_SoLuong = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        btnKhachHang_TimKiem = new javax.swing.JButton();
        btnKhachHang_LamMoi = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        lblKhachHang_ThoiGian = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        crazyPanel36 = new raven.crazypanel.CrazyPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblNhaCungCap = new javax.swing.JTable();
        crazyPanel37 = new raven.crazypanel.CrazyPanel();
        jLabel55 = new javax.swing.JLabel();
        txtNhaCungCap_TimKiem = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        txtNhaCungCap_TuNgay = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        txtNhaCungCap_DenNgay = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        txtNhaCungCap_SoLuong = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        btnNhaCungCap_TimKiem = new javax.swing.JButton();
        btnNhaCungCap_LamMoi = new javax.swing.JButton();
        jLabel61 = new javax.swing.JLabel();
        lblNhaCungCap_ThoiGian = new javax.swing.JLabel();

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
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
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
                "width 150",
                "",
                "width 150",
                ""
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

        btnLamMoiNam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoiNam.setText("Làm mới");
        btnLamMoiNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiNamActionPerformed(evt);
            }
        });
        crazyPanel17.add(btnLamMoiNam);

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
            .addComponent(crazyPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1111, Short.MAX_VALUE)
            .addComponent(panelShadow10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(panelShadow10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
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
                "background:lighten(@background,8%);borderWidth:1",
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
                "width 150",
                ""
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

        btnLamMoiThang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoiThang.setText("Làm mới");
        btnLamMoiThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiThangActionPerformed(evt);
            }
        });
        crazyPanel19.add(btnLamMoiThang);

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
            .addComponent(crazyPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1111, Short.MAX_VALUE)
            .addComponent(panelShadow11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(panelShadow11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
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
                "background:lighten(@background,8%);borderWidth:1",
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
                "width 150",
                "",
                "width 150",
                ""
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

        btnLamMoiNgay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoiNgay.setText("Làm mới");
        btnLamMoiNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiNgayActionPerformed(evt);
            }
        });
        crazyPanel21.add(btnLamMoiNgay);

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
            .addComponent(crazyPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1111, Short.MAX_VALUE)
            .addComponent(panelShadow12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(panelShadow12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
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

        crazyPanel22.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel22.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,insets 15",
            "[fill]",
            "[grow 0][][][fill][grow 0][fill][grow 0][fill][grow 0][][][fill]",
            new String[]{
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "height 50",
                "height 50"
            }
        ));

        jLabel8.setText("Tìm kiếm sản phẩm");
        crazyPanel22.add(jLabel8);
        crazyPanel22.add(txtThongKeTonKho_SanPham);
        crazyPanel22.add(jLabel17);

        jLabel14.setText("Từ ngày (yyyy-MM-dd)");
        crazyPanel22.add(jLabel14);
        crazyPanel22.add(txtThongKeTonKho_TuNgay);

        jLabel15.setText("Đến ngày (yyyy-MM-dd)");
        crazyPanel22.add(jLabel15);
        crazyPanel22.add(txtThongKeTonKho_DenNgay);
        crazyPanel22.add(jLabel16);

        btnThongKeTonKho_TimKiem.setText("Tìm kiếm");
        btnThongKeTonKho_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeTonKho_TimKiemActionPerformed(evt);
            }
        });
        crazyPanel22.add(btnThongKeTonKho_TimKiem);

        btnThongKeTonKho_LamMoi.setText("Làm mới");
        btnThongKeTonKho_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeTonKho_LamMoiActionPerformed(evt);
            }
        });
        crazyPanel22.add(btnThongKeTonKho_LamMoi);

        jLabel30.setText("Bạn đang xem thông tin từ ngày");
        crazyPanel22.add(jLabel30);

        lblThongKeTonKho_ThoiGian.setText("<yyyy-MM-dd -  yyyy-MM-dd>");
        crazyPanel22.add(lblThongKeTonKho_ThoiGian);

        crazyPanel23.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel23.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[fill]",
            null
        ));

        tblThongKeTonKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên sản phẩm", "Tồn đầu kì", "Nhập trong kì", "Xuất trong kì", "Tồn cuối kì"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongKeTonKho.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblThongKeTonKho);

        crazyPanel23.add(jScrollPane2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
                    .addComponent(crazyPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        materialTabbed2.addTab("Tồn kho", jPanel3);

        crazyPanel26.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "",
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel26.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,insets 15",
            "[fill]",
            "[grow 0][][][fill][grow 0][fill][grow 0][fill][grow 0][][][fill]",
            new String[]{
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "height 50",
                "height 50"
            }
        ));

        jLabel18.setText("Tìm kiếm sản phẩm");
        crazyPanel26.add(jLabel18);
        crazyPanel26.add(txtSanPhamBanChay_SanPham);
        crazyPanel26.add(jLabel19);

        jLabel20.setText("Từ ngày (yyyy-MM-dd)");
        crazyPanel26.add(jLabel20);
        crazyPanel26.add(txtSanPhamBanChay_TuNgay);

        jLabel28.setText("Đến ngày (yyyy-MM-dd)");
        crazyPanel26.add(jLabel28);
        crazyPanel26.add(txtSanPhamBanChay_DenNgay);
        crazyPanel26.add(jLabel29);

        btnSanPhamBanChay_TimKiem.setText("Tìm kiếm");
        btnSanPhamBanChay_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamBanChay_TimKiemActionPerformed(evt);
            }
        });
        crazyPanel26.add(btnSanPhamBanChay_TimKiem);

        btnSanPhamBanChay_LamMoi.setText("Làm mới");
        btnSanPhamBanChay_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamBanChay_LamMoiActionPerformed(evt);
            }
        });
        crazyPanel26.add(btnSanPhamBanChay_LamMoi);

        jLabel33.setText("Bạn đang xem thông tin từ ngày");
        crazyPanel26.add(jLabel33);

        lblSanPhamBanChay_ThoiGian.setText("<yyyy-MM-dd -  yyyy-MM-dd>");
        crazyPanel26.add(lblSanPhamBanChay_ThoiGian);

        crazyPanel27.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel27.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[fill]",
            null
        ));

        tblSanPhamBanChay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã", "Tên sản phẩm", "Số lượng bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPhamBanChay.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblSanPhamBanChay);

        crazyPanel27.add(jScrollPane4);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                    .addComponent(crazyPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        materialTabbed4.addTab("Bán chạy nhất", jPanel9);

        crazyPanel28.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel28.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,insets 15",
            "[fill]",
            "[grow 0][][][fill][grow 0][fill][grow 0][fill][grow 0][][][fill]",
            new String[]{
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "height 50",
                "height 50",
                "height 50",
                "height 50"
            }
        ));

        jLabel26.setText("Tìm kiếm sản phẩm");
        crazyPanel28.add(jLabel26);
        crazyPanel28.add(txtSanPhamHetHang_SanPham);
        crazyPanel28.add(jLabel27);

        jLabel31.setText("Số lượng ít nhất");
        crazyPanel28.add(jLabel31);
        crazyPanel28.add(txtSanPhamHetHang_SoLuong);
        crazyPanel28.add(jLabel32);

        btnSanPhamHetHang_TimKiem.setText("Tìm kiếm");
        btnSanPhamHetHang_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamHetHang_TimKiemActionPerformed(evt);
            }
        });
        crazyPanel28.add(btnSanPhamHetHang_TimKiem);

        btnSanPhamHetHang_LamMoi.setText("Làm mới");
        btnSanPhamHetHang_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamHetHang_LamMoiActionPerformed(evt);
            }
        });
        crazyPanel28.add(btnSanPhamHetHang_LamMoi);

        lblSanPhamHetHang_ThongBao.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        crazyPanel28.add(lblSanPhamHetHang_ThongBao);

        crazyPanel29.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel29.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[fill]",
            null
        ));

        tblSanPhamHetHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã", "Tên sản phẩm", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPhamHetHang.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tblSanPhamHetHang);

        crazyPanel29.add(jScrollPane5);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                    .addComponent(crazyPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        materialTabbed4.addTab("Sắp hết hàng", jPanel10);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        materialTabbed2.addTab("Sản phẩm", jPanel7);

        crazyPanel30.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "",
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel30.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,insets 15",
            "[fill]",
            "[grow 0][][][fill][grow 0][fill][grow 0][fill][grow 0][][][fill]",
            new String[]{
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "",
                "width 400",
                "height 50",
                "height 50"
            }
        ));

        jLabel34.setText("Tìm kiếm Nhân viên");
        crazyPanel30.add(jLabel34);
        crazyPanel30.add(txtNhanVienXuatKho_TimKiem);
        crazyPanel30.add(jLabel35);

        jLabel36.setText("Từ ngày (yyyy-MM-dd)");
        crazyPanel30.add(jLabel36);
        crazyPanel30.add(txtNhanVienXuatKho_TuNgay);

        jLabel37.setText("Đến ngày (yyyy-MM-dd)");
        crazyPanel30.add(jLabel37);
        crazyPanel30.add(txtNhanVienXuatKho_DenNgay);

        jLabel40.setText("Số lượng");
        crazyPanel30.add(jLabel40);
        crazyPanel30.add(txtNhanVienXuatKho_SoLuong);
        crazyPanel30.add(jLabel38);

        btnNhanVienXuatKho_TimKiem.setText("Tìm kiếm");
        btnNhanVienXuatKho_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienXuatKho_TimKiemActionPerformed(evt);
            }
        });
        crazyPanel30.add(btnNhanVienXuatKho_TimKiem);

        btnNhanVienXuatKho_LamMoi.setText("Làm mới");
        btnNhanVienXuatKho_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienXuatKho_LamMoiActionPerformed(evt);
            }
        });
        crazyPanel30.add(btnNhanVienXuatKho_LamMoi);

        jLabel39.setText("Bạn đang xem thông tin từ ngày");
        crazyPanel30.add(jLabel39);

        lblNhanVienXuatKho_ThoiGian.setText("<yyyy-MM-dd -  yyyy-MM-dd>");
        crazyPanel30.add(lblNhanVienXuatKho_ThoiGian);

        crazyPanel31.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel31.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[fill]",
            null
        ));

        tblNhanVienXuatKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã", "Tên Nhân viên", "Số lượng xuất"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVienXuatKho.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(tblNhanVienXuatKho);

        crazyPanel31.add(jScrollPane6);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                    .addComponent(crazyPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        materialTabbed5.addTab("Xuất kho", jPanel14);

        crazyPanel32.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel32.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[fill]",
            null
        ));

        tblNhanVienNhapKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã", "Tên Nhân viên", "Số lượng nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVienNhapKho.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(tblNhanVienNhapKho);

        crazyPanel32.add(jScrollPane7);

        crazyPanel33.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "",
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel33.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,insets 15",
            "[fill]",
            "[grow 0][][][fill][grow 0][fill][grow 0][fill][grow 0][][][fill]",
            new String[]{
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "",
                "width 400",
                "height 50",
                "height 50"
            }
        ));

        jLabel41.setText("Tìm kiếm Nhân viên");
        crazyPanel33.add(jLabel41);
        crazyPanel33.add(txtNhanVienNhapKho_TimKiem);
        crazyPanel33.add(jLabel42);

        jLabel43.setText("Từ ngày (yyyy-MM-dd)");
        crazyPanel33.add(jLabel43);
        crazyPanel33.add(txtNhanVienNhapKho_TuNgay);

        jLabel44.setText("Đến ngày (yyyy-MM-dd)");
        crazyPanel33.add(jLabel44);
        crazyPanel33.add(txtNhanVienNhapKho_DenNgay);

        jLabel45.setText("Số lượng");
        crazyPanel33.add(jLabel45);
        crazyPanel33.add(txtNhanVienNhapKho_SoLuong);
        crazyPanel33.add(jLabel46);

        btnNhanVienNhapKho_TimKiem.setText("Tìm kiếm");
        btnNhanVienNhapKho_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienNhapKho_TimKiemActionPerformed(evt);
            }
        });
        crazyPanel33.add(btnNhanVienNhapKho_TimKiem);

        btnNhanVienNhapKho_LamMoi.setText("Làm mới");
        btnNhanVienNhapKho_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienNhapKho_LamMoiActionPerformed(evt);
            }
        });
        crazyPanel33.add(btnNhanVienNhapKho_LamMoi);

        jLabel47.setText("Bạn đang xem thông tin từ ngày");
        crazyPanel33.add(jLabel47);

        lblNhanVienNhapKho_ThoiGian.setText("<yyyy-MM-dd -  yyyy-MM-dd>");
        crazyPanel33.add(lblNhanVienNhapKho_ThoiGian);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                    .addComponent(crazyPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        materialTabbed5.addTab("Nhập kho", jPanel15);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        materialTabbed2.addTab("Nhân viên", jPanel13);

        crazyPanel34.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel34.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[fill]",
            null
        ));

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã", "Tên Nhân viên", "Số lượng mua"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.getTableHeader().setReorderingAllowed(false);
        jScrollPane11.setViewportView(tblKhachHang);

        crazyPanel34.add(jScrollPane11);

        crazyPanel35.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "",
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel35.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,insets 15",
            "[fill]",
            "[grow 0][][][fill][grow 0][fill][grow 0][fill][grow 0][][][fill]",
            new String[]{
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "",
                "width 400",
                "height 50",
                "height 50"
            }
        ));

        jLabel48.setText("Tìm kiếm Khách hàng");
        crazyPanel35.add(jLabel48);
        crazyPanel35.add(txtKhachHang_TimKiem);
        crazyPanel35.add(jLabel49);

        jLabel50.setText("Từ ngày (yyyy-MM-dd)");
        crazyPanel35.add(jLabel50);
        crazyPanel35.add(txtKhachHang_TuNgay);

        jLabel51.setText("Đến ngày (yyyy-MM-dd)");
        crazyPanel35.add(jLabel51);
        crazyPanel35.add(txtKhachHang_DenNgay);

        jLabel52.setText("Số lượng");
        crazyPanel35.add(jLabel52);
        crazyPanel35.add(txtKhachHang_SoLuong);
        crazyPanel35.add(jLabel53);

        btnKhachHang_TimKiem.setText("Tìm kiếm");
        btnKhachHang_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHang_TimKiemActionPerformed(evt);
            }
        });
        crazyPanel35.add(btnKhachHang_TimKiem);

        btnKhachHang_LamMoi.setText("Làm mới");
        btnKhachHang_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHang_LamMoiActionPerformed(evt);
            }
        });
        crazyPanel35.add(btnKhachHang_LamMoi);

        jLabel54.setText("Bạn đang xem thông tin từ ngày");
        crazyPanel35.add(jLabel54);

        lblKhachHang_ThoiGian.setText("<yyyy-MM-dd -  yyyy-MM-dd>");
        crazyPanel35.add(lblKhachHang_ThoiGian);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
                    .addComponent(crazyPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        materialTabbed2.addTab("Khách hàng", jPanel11);

        crazyPanel36.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel36.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[fill]",
            null
        ));

        tblNhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã", "Tên Nhân viên", "Số lượng nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhaCungCap.getTableHeader().setReorderingAllowed(false);
        jScrollPane12.setViewportView(tblNhaCungCap);

        crazyPanel36.add(jScrollPane12);

        crazyPanel37.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "",
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel37.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,insets 15",
            "[fill]",
            "[grow 0][][][fill][grow 0][fill][grow 0][fill][grow 0][][][fill]",
            new String[]{
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "width 400",
                "",
                "width 400",
                "height 50",
                "height 50"
            }
        ));

        jLabel55.setText("Tìm kiếm Nhà cung cấp");
        crazyPanel37.add(jLabel55);
        crazyPanel37.add(txtNhaCungCap_TimKiem);
        crazyPanel37.add(jLabel56);

        jLabel57.setText("Từ ngày (yyyy-MM-dd)");
        crazyPanel37.add(jLabel57);
        crazyPanel37.add(txtNhaCungCap_TuNgay);

        jLabel58.setText("Đến ngày (yyyy-MM-dd)");
        crazyPanel37.add(jLabel58);
        crazyPanel37.add(txtNhaCungCap_DenNgay);

        jLabel59.setText("Số lượng");
        crazyPanel37.add(jLabel59);
        crazyPanel37.add(txtNhaCungCap_SoLuong);
        crazyPanel37.add(jLabel60);

        btnNhaCungCap_TimKiem.setText("Tìm kiếm");
        btnNhaCungCap_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhaCungCap_TimKiemActionPerformed(evt);
            }
        });
        crazyPanel37.add(btnNhaCungCap_TimKiem);

        btnNhaCungCap_LamMoi.setText("Làm mới");
        btnNhaCungCap_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhaCungCap_LamMoiActionPerformed(evt);
            }
        });
        crazyPanel37.add(btnNhaCungCap_LamMoi);

        jLabel61.setText("Bạn đang xem thông tin từ ngày");
        crazyPanel37.add(jLabel61);

        lblNhaCungCap_ThoiGian.setText("<yyyy-MM-dd -  yyyy-MM-dd>");
        crazyPanel37.add(lblNhaCungCap_ThoiGian);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
                    .addComponent(crazyPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        materialTabbed2.addTab("Nhà cung cấp", jPanel12);

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

    private void btnLamMoiNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiNamActionPerformed
        txtThongKeTheoNam_TuNam.setText("");
        txtThongKeTheoNam_DenNam.setText("");

        setDataYears("2022", "2025");
        loadRevenueByYears("2022", "2025");
    }//GEN-LAST:event_btnLamMoiNamActionPerformed

    private void btnLamMoiThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiThangActionPerformed
        txtThongKeTheoThang_Nam.setText("");
        txtThongKeTheoThang_Nam.setText("");

        setDataMonths("2025");
        loadRevenueByMonths("2025");
    }//GEN-LAST:event_btnLamMoiThangActionPerformed

    private void btnLamMoiNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiNgayActionPerformed
        txtThongKeTheoNgay_Nam.setText("");
        txtThongKeTheoNgay_Thang.setText("");

        setDataDays("2025", "07");
        loadRevenueByDays("2025", "07");
    }//GEN-LAST:event_btnLamMoiNgayActionPerformed

    private void btnThongKeTonKho_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeTonKho_TimKiemActionPerformed
        List<Chart_Inventory> inventories = new ArrayList<>();

        String keyword = txtThongKeTonKho_SanPham.getText().trim();
        String fromDate = txtThongKeTonKho_TuNgay.getText().trim();
        String toDate = txtThongKeTonKho_DenNgay.getText().trim();

        boolean hasKeyword = !keyword.isEmpty();
        boolean hasFromDate = !fromDate.isEmpty();
        boolean hasToDate = !toDate.isEmpty();

        if (!hasKeyword && !hasFromDate && !hasToDate) {
            inventories = service.getInventoryAllService("");
            lblThongKeTonKho_ThoiGian.setText("<yyyy-MM-dd> đến <yyyy-MM-dd>");
        } else if (hasKeyword && !hasFromDate && !hasToDate) {
            inventories = service.getInventoryAllService(keyword);
            lblThongKeTonKho_ThoiGian.setText("<yyyy-MM-dd> đến <yyyy-MM-dd>");
        } else if (hasFromDate && hasToDate) {
            inventories = service.getInventoryByKeyWordService(fromDate, toDate, keyword);
            lblThongKeTonKho_ThoiGian.setText(txtThongKeTonKho_TuNgay.getText() + " đến " + txtThongKeTonKho_DenNgay.getText());
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER,
                    "Vui lòng nhập cả Từ ngày và Đến ngày để thống kê theo thời gian");
            return;
        }

        loadInventoryByKeyWord(inventories);
    }//GEN-LAST:event_btnThongKeTonKho_TimKiemActionPerformed

    private void btnThongKeTonKho_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeTonKho_LamMoiActionPerformed
        loadInventoryAll();

        txtThongKeTonKho_SanPham.setText("");
        txtThongKeTonKho_TuNgay.setText("");
        txtThongKeTonKho_DenNgay.setText("");
    }//GEN-LAST:event_btnThongKeTonKho_LamMoiActionPerformed

    private void btnSanPhamBanChay_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamBanChay_TimKiemActionPerformed
        String keyword = txtSanPhamBanChay_SanPham.getText();
        String from = txtSanPhamBanChay_TuNgay.getText();
        String to = txtSanPhamBanChay_DenNgay.getText();

        loadTopSellingProducts(from, to, keyword);

        if (from.isEmpty() || to.isEmpty()) {
            lblSanPhamBanChay_ThoiGian.setText("<yyyy-MM-dd> đến <yyyy-MM-dd>");
        } else {
            lblSanPhamBanChay_ThoiGian.setText(txtSanPhamBanChay_TuNgay.getText() + " đến " + txtSanPhamBanChay_DenNgay.getText());
        }
    }//GEN-LAST:event_btnSanPhamBanChay_TimKiemActionPerformed

    private void btnSanPhamBanChay_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamBanChay_LamMoiActionPerformed
        loadTopSellingProducts("", "", "");

        txtSanPhamBanChay_SanPham.setText("");
        txtSanPhamBanChay_TuNgay.setText("");
        txtSanPhamBanChay_DenNgay.setText("");
    }//GEN-LAST:event_btnSanPhamBanChay_LamMoiActionPerformed

    private void btnSanPhamHetHang_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamHetHang_TimKiemActionPerformed
        String keyword = txtSanPhamHetHang_SanPham.getText();
        String minQuantityText = txtSanPhamHetHang_SoLuong.getText();

        loadLowStockProductsToTable(keyword, minQuantityText);
    }//GEN-LAST:event_btnSanPhamHetHang_TimKiemActionPerformed

    private void btnSanPhamHetHang_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamHetHang_LamMoiActionPerformed
        loadLowStockProductsToTable("", "");

        txtSanPhamHetHang_SanPham.setText("");
        txtSanPhamHetHang_SoLuong.setText("");
    }//GEN-LAST:event_btnSanPhamHetHang_LamMoiActionPerformed

    private void btnNhanVienXuatKho_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienXuatKho_TimKiemActionPerformed
        String keyword = txtNhanVienXuatKho_TimKiem.getText();
        String from = txtNhanVienXuatKho_TuNgay.getText();
        String to = txtNhanVienXuatKho_DenNgay.getText();
        String quantity = txtNhanVienXuatKho_SoLuong.getText();

        loadTopEmployeeExport(from, to, keyword, quantity);

        if (from.isEmpty() || to.isEmpty()) {
            lblNhanVienXuatKho_ThoiGian.setText("<yyyy-MM-dd> đến <yyyy-MM-dd>");
        } else {
            lblNhanVienXuatKho_ThoiGian.setText(from + " đến " + to);
        }
    }//GEN-LAST:event_btnNhanVienXuatKho_TimKiemActionPerformed

    private void btnNhanVienXuatKho_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienXuatKho_LamMoiActionPerformed
        txtNhanVienXuatKho_TimKiem.setText("");
        txtNhanVienXuatKho_TuNgay.setText(firstDayOfMonth.toString());
        txtNhanVienXuatKho_DenNgay.setText(today.toString());
        txtNhanVienXuatKho_SoLuong.setText("10");

        loadTopEmployeeExport(firstDayOfMonth.toString(), today.toString(), "", "");
        lblNhanVienXuatKho_ThoiGian.setText(txtNhanVienXuatKho_TuNgay.getText() + " đến " + txtNhanVienXuatKho_DenNgay.getText());
    }//GEN-LAST:event_btnNhanVienXuatKho_LamMoiActionPerformed

    private void btnNhanVienNhapKho_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienNhapKho_TimKiemActionPerformed
        String keyword = txtNhanVienNhapKho_TimKiem.getText();
        String from = txtNhanVienNhapKho_TuNgay.getText();
        String to = txtNhanVienNhapKho_DenNgay.getText();
        String quantity = txtNhanVienNhapKho_SoLuong.getText();

        loadTopEmployeeImport(from, to, keyword, quantity);

        if (from.isEmpty() || to.isEmpty()) {
            lblNhanVienNhapKho_ThoiGian.setText("<yyyy-MM-dd> đến <yyyy-MM-dd>");
        } else {
            lblNhanVienNhapKho_ThoiGian.setText(from + " đến " + to);
        }
    }//GEN-LAST:event_btnNhanVienNhapKho_TimKiemActionPerformed

    private void btnNhanVienNhapKho_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienNhapKho_LamMoiActionPerformed
        txtNhanVienNhapKho_TimKiem.setText("");
        txtNhanVienNhapKho_TuNgay.setText(firstDayOfMonth.toString());
        txtNhanVienNhapKho_DenNgay.setText(today.toString());
        txtNhanVienNhapKho_SoLuong.setText("10");

        loadTopEmployeeImport(firstDayOfMonth.toString(), today.toString(), "", "");
        lblNhanVienNhapKho_ThoiGian.setText(txtNhanVienNhapKho_TuNgay.getText() + " đến " + txtNhanVienNhapKho_DenNgay.getText());
    }//GEN-LAST:event_btnNhanVienNhapKho_LamMoiActionPerformed

    private void btnKhachHang_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHang_TimKiemActionPerformed
        String keyword = txtKhachHang_TimKiem.getText();
        String from = txtKhachHang_TuNgay.getText();
        String to = txtKhachHang_DenNgay.getText();
        String quantity = txtKhachHang_SoLuong.getText();

        loadTopCustomer(from, to, keyword, quantity);

        if (from.isEmpty() || to.isEmpty()) {
            lblKhachHang_ThoiGian.setText("<yyyy-MM-dd> đến <yyyy-MM-dd>");
        } else {
            lblKhachHang_ThoiGian.setText(from + " đến " + to);
        }
    }//GEN-LAST:event_btnKhachHang_TimKiemActionPerformed

    private void btnKhachHang_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHang_LamMoiActionPerformed
        txtKhachHang_TimKiem.setText("");
        txtKhachHang_TuNgay.setText(firstDayOfMonth.toString());
        txtKhachHang_DenNgay.setText(today.toString());
        txtKhachHang_SoLuong.setText("10");

        loadTopCustomer(firstDayOfMonth.toString(), today.toString(), "", "");
        lblKhachHang_ThoiGian.setText(txtKhachHang_TuNgay.getText() + " đến " + txtKhachHang_DenNgay.getText());
    }//GEN-LAST:event_btnKhachHang_LamMoiActionPerformed

    private void btnNhaCungCap_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhaCungCap_TimKiemActionPerformed
        String keyword = txtNhaCungCap_TimKiem.getText();
        String from = txtNhaCungCap_TuNgay.getText();
        String to = txtNhaCungCap_DenNgay.getText();
        String quantity = txtNhaCungCap_SoLuong.getText();

        loadTopSupplier(from, to, keyword, quantity);

        if (from.isEmpty() || to.isEmpty()) {
            lblNhaCungCap_ThoiGian.setText("<yyyy-MM-dd> đến <yyyy-MM-dd>");
        } else {
            lblNhaCungCap_ThoiGian.setText(from + " đến " + to);
        }
    }//GEN-LAST:event_btnNhaCungCap_TimKiemActionPerformed

    private void btnNhaCungCap_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhaCungCap_LamMoiActionPerformed
        txtNhaCungCap_TimKiem.setText("");
        txtNhaCungCap_TuNgay.setText(firstDayOfMonth.toString());
        txtNhaCungCap_DenNgay.setText(today.toString());
        txtNhaCungCap_SoLuong.setText("10");

        loadTopSupplier(firstDayOfMonth.toString(), today.toString(), "", "");
        lblNhaCungCap_ThoiGian.setText(txtNhaCungCap_TuNgay.getText() + " đến " + txtNhaCungCap_DenNgay.getText());
    }//GEN-LAST:event_btnNhaCungCap_LamMoiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKhachHang_LamMoi;
    private javax.swing.JButton btnKhachHang_TimKiem;
    private javax.swing.JButton btnLamMoiNam;
    private javax.swing.JButton btnLamMoiNgay;
    private javax.swing.JButton btnLamMoiThang;
    private javax.swing.JButton btnNam;
    private javax.swing.JButton btnNgay;
    private javax.swing.JButton btnNhaCungCap_LamMoi;
    private javax.swing.JButton btnNhaCungCap_TimKiem;
    private javax.swing.JButton btnNhanVienNhapKho_LamMoi;
    private javax.swing.JButton btnNhanVienNhapKho_TimKiem;
    private javax.swing.JButton btnNhanVienXuatKho_LamMoi;
    private javax.swing.JButton btnNhanVienXuatKho_TimKiem;
    private javax.swing.JButton btnSanPhamBanChay_LamMoi;
    private javax.swing.JButton btnSanPhamBanChay_TimKiem;
    private javax.swing.JButton btnSanPhamHetHang_LamMoi;
    private javax.swing.JButton btnSanPhamHetHang_TimKiem;
    private javax.swing.JButton btnThang;
    private javax.swing.JButton btnThongKeTonKho_LamMoi;
    private javax.swing.JButton btnThongKeTonKho_TimKiem;
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
    private raven.crazypanel.CrazyPanel crazyPanel22;
    private raven.crazypanel.CrazyPanel crazyPanel23;
    private raven.crazypanel.CrazyPanel crazyPanel26;
    private raven.crazypanel.CrazyPanel crazyPanel27;
    private raven.crazypanel.CrazyPanel crazyPanel28;
    private raven.crazypanel.CrazyPanel crazyPanel29;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private raven.crazypanel.CrazyPanel crazyPanel30;
    private raven.crazypanel.CrazyPanel crazyPanel31;
    private raven.crazypanel.CrazyPanel crazyPanel32;
    private raven.crazypanel.CrazyPanel crazyPanel33;
    private raven.crazypanel.CrazyPanel crazyPanel34;
    private raven.crazypanel.CrazyPanel crazyPanel35;
    private raven.crazypanel.CrazyPanel crazyPanel36;
    private raven.crazypanel.CrazyPanel crazyPanel37;
    private raven.crazypanel.CrazyPanel crazyPanel4;
    private raven.crazypanel.CrazyPanel crazyPanel5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
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
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCustomerCount;
    private javax.swing.JLabel lblKhachHang_ThoiGian;
    private javax.swing.JLabel lblNhaCungCap_ThoiGian;
    private javax.swing.JLabel lblNhanVienNhapKho_ThoiGian;
    private javax.swing.JLabel lblNhanVienXuatKho_ThoiGian;
    private javax.swing.JLabel lblProductCount;
    private javax.swing.JLabel lblSanPhamBanChay_ThoiGian;
    private javax.swing.JLabel lblSanPhamHetHang_ThongBao;
    private javax.swing.JLabel lblThongKeTonKho_ThoiGian;
    private javax.swing.JLabel lblUserCount;
    private zentech.application.tabbed.MaterialTabbed materialTabbed1;
    private zentech.application.tabbed.MaterialTabbed materialTabbed2;
    private zentech.application.tabbed.MaterialTabbed materialTabbed3;
    private zentech.application.tabbed.MaterialTabbed materialTabbed4;
    private zentech.application.tabbed.MaterialTabbed materialTabbed5;
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
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblNhaCungCap;
    private javax.swing.JTable tblNhanVienNhapKho;
    private javax.swing.JTable tblNhanVienXuatKho;
    private javax.swing.JTable tblSanPhamBanChay;
    private javax.swing.JTable tblSanPhamHetHang;
    private javax.swing.JTable tblThongKeTonKho;
    private javax.swing.JTextField txtKhachHang_DenNgay;
    private javax.swing.JTextField txtKhachHang_SoLuong;
    private javax.swing.JTextField txtKhachHang_TimKiem;
    private javax.swing.JTextField txtKhachHang_TuNgay;
    private javax.swing.JTextField txtNhaCungCap_DenNgay;
    private javax.swing.JTextField txtNhaCungCap_SoLuong;
    private javax.swing.JTextField txtNhaCungCap_TimKiem;
    private javax.swing.JTextField txtNhaCungCap_TuNgay;
    private javax.swing.JTextField txtNhanVienNhapKho_DenNgay;
    private javax.swing.JTextField txtNhanVienNhapKho_SoLuong;
    private javax.swing.JTextField txtNhanVienNhapKho_TimKiem;
    private javax.swing.JTextField txtNhanVienNhapKho_TuNgay;
    private javax.swing.JTextField txtNhanVienXuatKho_DenNgay;
    private javax.swing.JTextField txtNhanVienXuatKho_SoLuong;
    private javax.swing.JTextField txtNhanVienXuatKho_TimKiem;
    private javax.swing.JTextField txtNhanVienXuatKho_TuNgay;
    private javax.swing.JTextField txtSanPhamBanChay_DenNgay;
    private javax.swing.JTextField txtSanPhamBanChay_SanPham;
    private javax.swing.JTextField txtSanPhamBanChay_TuNgay;
    private javax.swing.JTextField txtSanPhamHetHang_SanPham;
    private javax.swing.JTextField txtSanPhamHetHang_SoLuong;
    private javax.swing.JTextField txtThongKeTheoNam_DenNam;
    private javax.swing.JTextField txtThongKeTheoNam_TuNam;
    private javax.swing.JTextField txtThongKeTheoNgay_Nam;
    private javax.swing.JTextField txtThongKeTheoNgay_Thang;
    private javax.swing.JTextField txtThongKeTheoThang_Nam;
    private javax.swing.JTextField txtThongKeTonKho_DenNgay;
    private javax.swing.JTextField txtThongKeTonKho_SanPham;
    private javax.swing.JTextField txtThongKeTonKho_TuNgay;
    // End of variables declaration//GEN-END:variables
}

package zentech.application.dialog;

import com.formdev.flatlaf.FlatClientProperties;
import dao.WarehouseDeliveryDAO;
import entity.Cilent;
import entity.Employee;
import entity.PhieuXuat;
import entity.PhieuXuatChiTiet;
import entity.ProductArea;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.LayoutManager;
import java.awt.Window;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import zentech.application.form.other.WarehouseDeliveryForm;

public class WarehouseDeliveryAddForm extends JDialog {

    private WarehouseDeliveryForm warehousedeliveryform;
    private Employee CurrentAcc;
    private WarehouseDeliveryDAO wdd = new WarehouseDeliveryDAO();

    private int soluongcuasanpham;
    private BigDecimal giacuasanpham;

    public WarehouseDeliveryAddForm(Window parent, WarehouseDeliveryForm from, Employee acc) {
        super(parent, Dialog.ModalityType.APPLICATION_MODAL);
        initComponents();
        this.CurrentAcc = acc;
        this.warehousedeliveryform = from;
        initUI();
        LoadData();
    }

    public void initUI() {
//        Label
        jLabel1.setText(CurrentAcc.getHoten());
//        JTextField
        txtGhiChu.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "...");
        txtMaKhachHang.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "0");
        txtMaKho.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "0");
        txtMaSanPham.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "0");
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "...");
        txtSearch1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "...");
        txtTenSanPham.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Samsung");
        txtMaKho.setEditable(false);
        txtMaKhachHang.setEnabled(false);
        txtMaSanPham.setEnabled(false);
        txtTenSanPham.setEnabled(false);
//        table
        tblSanPham.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        tblSanPham.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

        tblCho.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        tblCho.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

        tblKhachHang.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        tblKhachHang.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

        tblSanPham.setDefaultRenderer(Object.class, getAlignmentCellRender(tblSanPham.getDefaultRenderer(Object.class), false));
        tblCho.setDefaultRenderer(Object.class, getAlignmentCellRender(tblCho.getDefaultRenderer(Object.class), false));
        tblKhachHang.setDefaultRenderer(Object.class, getAlignmentCellRender(tblKhachHang.getDefaultRenderer(Object.class), false));
    }

    public void LoadData() {
        LoadDataTable1();
        LoadDataTable2();
    }

    public void LoadDataTable1() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        for (ProductArea pa : wdd.GetProducArea()) {
            model.addRow(
                    new Object[]{
                        pa.getP().getMaSanPham(),
                        pa.getP().getTenSanPham(),
                        pa.getP().getGia(),
                        pa.getW().getMaKhuVuc(),
                        pa.getSoluong()
                    });
        }
    }

    public boolean checksoluong() {
        int soluonghientai = 0;
        try {
            soluonghientai = Integer.parseInt(txtSoLuong.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sô lượng không hợp lệ.");
            txtSoLuong.setText("1");
            return false;
        }
        if (soluonghientai > this.soluongcuasanpham && this.soluongcuasanpham != 0) {
            JOptionPane.showMessageDialog(this, "Số lượng không được lớn hơn số lượng của sản phẩm");
            txtSoLuong.setText(String.valueOf(this.soluongcuasanpham));
            return false;
        } else if (soluonghientai < 1) {
            JOptionPane.showMessageDialog(this, "Số lượng không được nhỏ hơn 1");
            txtSoLuong.setText("1");
            return false;
        } else {
            return true;
        }
    }

    public void LoadDataTable2() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        for (Cilent c : wdd.getAllKhachHang()) {
            model.addRow(new Object[]{c.getMaKhacHang(), c.getTenKhacHang()});
        }
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

    public boolean checkSave() {
        String tenSPMoi = txtTenSanPham.getText().trim();
        String maKhoMoi = txtMaKho.getText().trim();

        String tenSPTrongBang = "";
        String maKhoTrongBang = "";

        for (int i = 0; i < tblCho.getRowCount(); i++) {
            tenSPTrongBang = tblCho.getValueAt(i, 2).toString().trim();
            if (i == 0) {
                maKhoTrongBang = tblCho.getValueAt(i, 0).toString().trim();
            }
        }

        if (txtMaSanPham.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để thêm vào bảng chờ!");
            return false;
        }

        if (tenSPTrongBang.equalsIgnoreCase(tenSPMoi)) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã có trong bảng chờ!");
            return false;
        }

        if (!maKhoTrongBang.equalsIgnoreCase(maKhoTrongBang)) {
            JOptionPane.showMessageDialog(this, "Chỉ được phép thêm sản phẩm từ cùng một kho (Hiện tại: Kho " + maKhoTrongBang + ")");
            return false;
        }

        return true;
    }

    public void SaveProduct() {
        if (checkSave() && checksoluong()) {
            DefaultTableModel model = (DefaultTableModel) tblCho.getModel();
            int soluong = Integer.parseInt(txtSoLuong.getText());
            double dongia = soluong * this.giacuasanpham.doubleValue();
            BigDecimal dongia1 = new BigDecimal(dongia);
            model.addRow(new Object[]{txtMaKho.getText(), txtMaSanPham.getText(), txtTenSanPham.getText(), txtSoLuong.getText(), txtGhiChu.getText(), dongia1});
        }
    }

    public void LoadMoney() {
        double tong = 0;
        for (int i = 0; i < tblCho.getRowCount(); i++) {
            Object dongiaObj = tblCho.getValueAt(i, 5);
            BigDecimal dongia = BigDecimal.ZERO;

            if (dongiaObj instanceof BigDecimal) {
                dongia = (BigDecimal) dongiaObj;
            } else if (dongiaObj instanceof Double) {
                dongia = BigDecimal.valueOf((Double) dongiaObj);
            } else if (dongiaObj != null) {
                try {
                    dongia = new BigDecimal(dongiaObj.toString().trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Lỗi định dạng đơn giá ở dòng " + (i + 1));
                    continue;
                }
            }

            tong += dongia.doubleValue();
        }
        BigDecimal tong2 = new BigDecimal(tong);
        lblTongTien.setText(String.valueOf(tong2));
    }

    public List<PhieuXuatChiTiet> getAllProduct() {
        List<PhieuXuatChiTiet> list = new ArrayList<>();
        try {
            for (int i = 0; i < tblCho.getRowCount(); i++) {
                PhieuXuatChiTiet pxct = new PhieuXuatChiTiet();

                int masanpham = Integer.parseInt(tblCho.getValueAt(i, 1).toString());
                BigDecimal dongia = new BigDecimal(tblCho.getValueAt(i, 5).toString());
                int soluong = Integer.parseInt(tblCho.getValueAt(i, 3).toString());
                String ghichu = tblCho.getValueAt(i, 4).toString();

                pxct.getSanpham().setMaSanPham(masanpham);
                pxct.setDongia(dongia);
                pxct.setSoluong(soluong);
                pxct.setGhichu(ghichu);

                list.add(pxct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy danh sách sản phẩm từ bảng chờ!");
            return null;
        }
    }

    public PhieuXuat getFrom() {
        PhieuXuat px = new PhieuXuat();
        int makhachhang = Integer.parseInt(txtMaKhachHang.getText());
        int manguoitao = this.CurrentAcc.getManv();
        Date sqlDate = Date.valueOf(LocalDateTime.now().toLocalDate());

        px.getKhachhang().setMaKhacHang(makhachhang);
        px.getNhanvien().setManv(manguoitao);
        px.setThoigian(sqlDate);
        px.setTrangthai("ChoDuyet");

        return px;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtSearch1 = new javax.swing.JTextField();
        btnTao = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaKho = new javax.swing.JTextField();
        txtMaSanPham = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        btnTang = new javax.swing.JButton();
        btnTru = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCho = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        lblTongTien = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhachHang);

        jLabel8.setText("Search");

        txtSearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearch1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtSearch1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnTao.setText("Tạo");
        btnTao.setPreferredSize(new java.awt.Dimension(75, 30));
        btnTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoActionPerformed(evt);
            }
        });

        btnHuy.setText("Hủy");
        btnHuy.setPreferredSize(new java.awt.Dimension(75, 30));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thêm sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel3.setText("Mã kho");

        txtMaKhachHang.setPreferredSize(new java.awt.Dimension(71, 32));

        jLabel4.setText("Mã sản phẩm");

        jLabel5.setText("Mã khách hàng");

        txtMaKho.setPreferredSize(new java.awt.Dimension(71, 32));

        txtMaSanPham.setPreferredSize(new java.awt.Dimension(71, 32));

        jLabel6.setText("Tên sản phẩm");

        txtTenSanPham.setPreferredSize(new java.awt.Dimension(71, 32));

        jLabel7.setText("Số lượng xuất");

        txtSoLuong.setText("1");
        txtSoLuong.setPreferredSize(new java.awt.Dimension(71, 32));
        txtSoLuong.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                txtSoLuongMouseMoved(evt);
            }
        });

        jLabel9.setText("Ghi chú");

        txtGhiChu.setPreferredSize(new java.awt.Dimension(71, 32));

        jButton3.setText("Thêm sản phẩm ");
        jButton3.setPreferredSize(new java.awt.Dimension(75, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnTang.setText("+");
        btnTang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTangActionPerformed(evt);
            }
        });

        btnTru.setText("-");
        btnTru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTruActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtMaKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(206, 206, 206))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTru))
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(btnTang, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTru, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtGhiChu, txtMaKhachHang, txtMaKho, txtMaSanPham, txtSoLuong, txtTenSanPham});

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhân viên tạo phiếu:", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel2.setText("Search");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Giá", "Mã kho", "Số lượng"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng chờ ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tblCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã kho", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Ghi chú", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblCho);

        jButton1.setText("Cập nhập số lượng");
        jButton1.setPreferredSize(new java.awt.Dimension(75, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Xóa sản phẩm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2});

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tổng tiền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(51, 255, 51));
        lblTongTien.setText("0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(lblTongTien)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jButton4.setText("Làm mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTao, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHuy, btnTao, jButton4});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHuy, btnTao, jButton4});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTangActionPerformed
        // TODO add your handling code here:
        if (checksoluong()) {
            int slht = 0;
            slht = Integer.parseInt(txtSoLuong.getText());
            int tangsoluong = slht + 1;
            txtSoLuong.setText(String.valueOf(tangsoluong));
        }
    }//GEN-LAST:event_btnTangActionPerformed

    private void btnTruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTruActionPerformed
        // TODO add your handling code here:
        if (checksoluong()) {
            int slht = 0;
            slht = Integer.parseInt(txtSoLuong.getText());
            int trusoluong = slht - 1;
            txtSoLuong.setText(String.valueOf(trusoluong));
        }
    }//GEN-LAST:event_btnTruActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        int select = tblSanPham.getSelectedRow();
        String tensp = (String) tblSanPham.getValueAt(select, 1);
        int kho = (int) tblSanPham.getValueAt(select, 3);
        int idsp = (int) tblSanPham.getValueAt(select, 0);
        int soluong = (int) tblSanPham.getValueAt(select, 4);
        BigDecimal gia = (BigDecimal) tblSanPham.getValueAt(select, 2);
        txtMaSanPham.setText(String.valueOf(idsp));
        txtMaKho.setText(String.valueOf(kho));
        txtTenSanPham.setText(tensp);
        txtSoLuong.setText("1");
        this.soluongcuasanpham = soluong;
        this.giacuasanpham = gia;
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        SaveProduct();
        LoadMoney();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblCho.getModel();
        int select = tblCho.getSelectedRow();
        model.removeRow(select);
        LoadMoney();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int select = tblCho.getSelectedRow();
        if (select == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm trong bảng chờ để cập nhật số lượng");
            return;
        }
        String soluongcuasanpham = (String) tblCho.getValueAt(select, 3);
        JPanel panel = new JPanel();
        JTextField textsoluong = new JTextField(soluongcuasanpham);
        panel.add(new JLabel("Cập nhật số lượng"));
        panel.add(textsoluong);
        String[] option = {"Hủy", "Cập nhật"};
        int rs = JOptionPane.showConfirmDialog(this, panel, "Cập nhật số lượng", JOptionPane.OK_CANCEL_OPTION);
        if (rs == JOptionPane.OK_OPTION) {
            int soluong = 0;
            try {
                soluong = Integer.parseInt(textsoluong.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
                textsoluong.setText("1");
                return;
            }
            if (soluong > this.soluongcuasanpham) {
                JOptionPane.showMessageDialog(this, "Số lượng không được lớn hơn số lượng đang có của sản phẩm!");
                textsoluong.setText(String.valueOf(this.soluongcuasanpham));
                return;
            } else if (soluong < 1) {
                JOptionPane.showMessageDialog(this, "Số lượng không được nhỏ hơn 1");
                textsoluong.setText("1");
                return;
            }
            tblCho.setValueAt(soluong, select, 3);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoActionPerformed
        // TODO add your handling code here:
        if (txtMaKhachHang.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng để tạo phiếu!");
            return;
        }
        if (getAllProduct() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm sản phẩm để tạo phiếu nhập");
            return;
        }

        boolean rs = wdd.TaoPhieuXuat(getAllProduct(), getFrom());
        if (rs == true) {
            JOptionPane.showMessageDialog(this, "Tạo phiếu xuất thành công");
            this.dispose();
            Window parent = SwingUtilities.getWindowAncestor(this);
            WarehouseDeliveryDetailForm detail = new WarehouseDeliveryDetailForm(parent, null, wdd.getMaPhieuXuatVuaTao());
            detail.setVisible(true);
        }
    }//GEN-LAST:event_btnTaoActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        int select = tblKhachHang.getSelectedRow();
        int id = (int) tblKhachHang.getValueAt(select, 0);
        txtMaKhachHang.setText(String.valueOf(id));
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void txtSoLuongMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSoLuongMouseMoved
        // TODO add your handling code here:
        checksoluong();
    }//GEN-LAST:event_txtSoLuongMouseMoved

    private void txtSearch1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch1KeyReleased
        // TODO add your handling code here:
        DefaultTableModel ob = (DefaultTableModel) tblSanPham.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        tblSanPham.setRowSorter(obj);
        obj.setRowFilter(javax.swing.RowFilter.regexFilter(txtSearch1.getText()));
    }//GEN-LAST:event_txtSearch1KeyReleased

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        DefaultTableModel ob = (DefaultTableModel) tblSanPham.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        tblSanPham.setRowSorter(obj);
        obj.setRowFilter(javax.swing.RowFilter.regexFilter(txtSearch.getText()));
    }//GEN-LAST:event_txtSearchKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txtGhiChu.setText("");
        txtMaKhachHang.setText("");
        txtMaKho.setText("");
        txtMaSanPham.setText("");
        txtSearch.setText("");
        txtTenSanPham.setText("");
        lblTongTien.setText("");
        DefaultTableModel model = (DefaultTableModel) tblCho.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnTang;
    private javax.swing.JButton btnTao;
    private javax.swing.JButton btnTru;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tblCho;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtMaKho;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables
}

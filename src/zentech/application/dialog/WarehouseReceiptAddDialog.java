package zentech.application.dialog;

import com.formdev.flatlaf.FlatClientProperties;
import dao.WarehouseReceiptDAO;
import entity.Employee;
import entity.PhieuNhap;
import entity.PhieuNhapChiTiet;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Window;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import service.WarehouseReceiptService;
import zentech.application.form.other.WarehouseReceiptForm;

public class WarehouseReceiptAddDialog extends JDialog {

    WarehouseReceiptService wrs = new WarehouseReceiptService();
    WarehouseReceiptDAO wrd = new WarehouseReceiptDAO();
    private Employee currentacc;

    List<Object> listo = new ArrayList<>();

    public WarehouseReceiptAddDialog(Window parent, WarehouseReceiptForm warehouseReceiptForm, Employee acc) {
        super(parent, Dialog.ModalityType.APPLICATION_MODAL);
        this.currentacc = acc;
        initComponents();
        editFrom();
        tblSanPham.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        tblCho.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
    }

    public void editFrom() {
        tblSanPham.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        wrs.loadDataCbo1(jComboBox1);
        wrs.loadDataTable1(tblSanPham);
        txtKho.setEditable(false);
        tblSanPham.setDefaultRenderer(Object.class, getAlignmentCellRender(tblSanPham.getDefaultRenderer(Object.class), false));
        tblCho.setDefaultRenderer(Object.class, getAlignmentCellRender(tblCho.getDefaultRenderer(Object.class), false));
        jLabel7.setText(jLabel7.getText() + " " + this.currentacc.getHoten());
        txtKho.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã kho...");
        txtMaSanPham.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã kho...");
        txtSoluong.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã sản phẩm...");
        txtTenSP.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tên sản phẩm...");
        txtghichu.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ghi chú...");
        jTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
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

    public void LoadMoney() {
        int soluong = 0;
        double giatien = 0;
        double dem = 0;
        BigDecimal tong = BigDecimal.ZERO;

        for (int i = 0; i < tblCho.getRowCount(); i++) {
            Object slObj = tblCho.getValueAt(i, 3);
            if (slObj instanceof Integer) {
                soluong = (Integer) slObj;
            } else {
                soluong = Integer.parseInt(slObj.toString().trim());
            }

            Object gtObj = tblCho.getValueAt(i, 5);
            if (gtObj instanceof java.math.BigDecimal) {
                giatien = ((java.math.BigDecimal) gtObj).doubleValue();
            } else if (gtObj instanceof Double) {
                giatien = (Double) gtObj;
            } else {
                giatien = Double.parseDouble(gtObj.toString().trim());
            }

            dem += soluong * giatien;
        }

        tong = new BigDecimal(dem);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat formatter = new DecimalFormat("#,##0", symbols);

        jLabel4.setText(formatter.format(tong));
    }

    public boolean checkAddProduct() {
        String tensp = "";
        String tenkho = "";
        for (int i = 0; i < tblCho.getRowCount(); i++) {
            tensp = (String) tblCho.getValueAt(i, 2);
            if (i == 0) {
                tenkho = (String) tblCho.getValueAt(i, 0);
            }
        }
        if (!tenkho.equals(txtKho.getText()) && !tenkho.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Đang thêm sản phẩm cho kho " + tenkho + ".");
            return false;
        }
        if (wrd.checkProduct(txtKho.getText(), tensp) == false) {
            JOptionPane.showMessageDialog(this, "Sản phẩm không có trong kho.");
            return false;
        }
        if (tensp.equalsIgnoreCase(txtTenSP.getText())) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã có trong bản chờ.");
            return false;
        }

        return true;
    }

    public boolean checkmoney() {
        int soluonghientai = 0;
        try {
            soluonghientai = Integer.parseInt(txtSoluong.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sô lượng không hợp lệ.");
            txtSoluong.setText("1");
            return false;
        }
        if (soluonghientai > 10000) {
            JOptionPane.showMessageDialog(this, "Số lượng không được lớn hơn 10000");
            txtSoluong.setText("10000");
            return false;
        } else if (soluonghientai < 1) {
            JOptionPane.showMessageDialog(this, "Số lượng không được nhỏ hơn 1");
            txtSoluong.setText("1");
            return false;
        } else {
            return true;
        }
    }

    public void addProduct() {
        int soluonghientai = 0;
        if (txtTenSP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm muốn thêm.");
            return;
        }
        if (txtSoluong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng sản phẩm.");
            return;
        }
        if (checkAddProduct()) {
            int select = tblSanPham.getSelectedRow();
            BigDecimal gia = (BigDecimal) tblSanPham.getValueAt(select, 2);
            try {
                soluonghientai = Integer.parseInt(txtSoluong.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Không phải số.");
            }
            double dongia = soluonghientai * gia.doubleValue();
            BigDecimal dongia1 = new BigDecimal(dongia);
            DefaultTableModel model = (DefaultTableModel) tblCho.getModel();
            model.addRow(new Object[]{txtKho.getText(), txtMaSanPham.getText(), txtTenSP.getText(), txtSoluong.getText(), txtghichu.getText(), dongia1});
        }
    }

    public List<PhieuNhapChiTiet> getProduct() {
        List<PhieuNhapChiTiet> listpnct = new ArrayList<>();
        try {
            for (int i = 0; i < tblCho.getRowCount(); i++) {
                PhieuNhapChiTiet pnct = new PhieuNhapChiTiet();

                int maSP = Integer.parseInt(tblCho.getValueAt(i, 1).toString());
                int soLuong = Integer.parseInt(tblCho.getValueAt(i, 3).toString());
                double gia = Double.parseDouble(tblCho.getValueAt(i, 5).toString());
                String ghiChu = tblCho.getValueAt(i, 4) != null ? tblCho.getValueAt(i, 4).toString() : "";

                pnct.getP().setMaSanPham(maSP);
                pnct.setSoluong(soLuong);
                pnct.setDongia(BigDecimal.valueOf(gia));
                pnct.setGhichu(ghiChu);

                listpnct.add(pnct);
            }
            return listpnct;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PhieuNhap getFrom() {
        PhieuNhap pn = new PhieuNhap();
        pn.getS().setMaNhaCungCap(Integer.parseInt(jComboBox1.getSelectedItem().toString()));
        pn.getE().setManv(this.currentacc.getManv());
        Date sqlDate = Date.valueOf(LocalDateTime.now().toLocalDate());
        pn.setNgaytao(sqlDate);
        return pn;
    }

//    public List<ProductArea> getUpdate() {
//        List<ProductArea> listpa = new ArrayList<>();
//        try {
//            for (int i = 0; i < jTable2.getRowCount(); i++) {
//                ProductArea pa = new ProductArea();
//                int makhuvuc = (int) jTable2.getValueAt(i, 0);
//                int masanpham = (int) jTable2.getValueAt(i, 1);
//                int soluong = (int) jTable2.getValueAt(i, 3);
//                pa.getW().setMaKhuVuc(makhuvuc);
//                pa.getP().setMaSanPham(masanpham);
//                pa.setSoluong(soluong);
//                listpa.add(pa);
//            }
//            return listpa;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSoluong = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtghichu = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtKho = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCho = new javax.swing.JTable();
        btnLua = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnCapNhapSoLuong = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tạo phiếu nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel3.setMaximumSize(new java.awt.Dimension(100, 100));

        jLabel2.setText("Tên sản phẩm");

        jLabel3.setText("Số lượng nhập");

        txtSoluong.setText("1");
        txtSoluong.setVerifyInputWhenFocusTarget(false);
        txtSoluong.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                txtSoluongMouseMoved(evt);
            }
        });

        jLabel1.setText("Ghi chú");

        jLabel5.setText("Nhà cung cấp");

        jLabel6.setText("Mã Kho");

        txtKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKhoActionPerformed(evt);
            }
        });

        jLabel8.setText("Mã sản phẩm");

        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("-");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaSanPham)
                    .addComponent(txtKho)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addComponent(txtghichu)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtSoluong, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKho, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtghichu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnThem.setText("Tạo");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnHuy.setText("Huỷ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tổng tiền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 255, 0));
        jLabel4.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Giá", "Mã Kho", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setResizable(false);
            tblSanPham.getColumnModel().getColumn(1).setResizable(false);
            tblSanPham.getColumnModel().getColumn(2).setResizable(false);
            tblSanPham.getColumnModel().getColumn(3).setResizable(false);
            tblSanPham.getColumnModel().getColumn(4).setResizable(false);
        }

        jTextField1.setPreferredSize(new java.awt.Dimension(71, 32));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel9.setText("Search");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
                .addContainerGap())
        );

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
        jScrollPane2.setViewportView(tblCho);
        if (tblCho.getColumnModel().getColumnCount() > 0) {
            tblCho.getColumnModel().getColumn(0).setResizable(false);
            tblCho.getColumnModel().getColumn(1).setResizable(false);
            tblCho.getColumnModel().getColumn(2).setResizable(false);
            tblCho.getColumnModel().getColumn(3).setResizable(false);
            tblCho.getColumnModel().getColumn(4).setResizable(false);
            tblCho.getColumnModel().getColumn(5).setResizable(false);
        }

        btnLua.setText("Thêm sản phẩm");
        btnLua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuaActionPerformed(evt);
            }
        });

        jButton1.setText("Xóa sản phẩm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnCapNhapSoLuong.setText("Cập nhập số lượng");
        btnCapNhapSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhapSoLuongActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Nhân viên tạo phiếu:");

        jButton5.setText("Làm mới");
        jButton5.setPreferredSize(new java.awt.Dimension(116, 23));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCapNhapSoLuong))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnHuy)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThem)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCapNhapSoLuong, btnLua, jButton1, jButton5});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnLua, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCapNhapSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnHuy))
                        .addContainerGap())
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCapNhapSoLuong, btnLua, jButton1, jButton5});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // Khi thêm trạng thái luôn luôn là ChoDuyet
        if (getProduct() == null || getProduct().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một sản phẩm khi tạo phiếu nhập.");
            return;
        }
        if (getFrom() == null || getProduct().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhân viên không tồn tại");
            return;
        }
        boolean rs = wrd.TaoPhieuNhap(getFrom(), getProduct());
        if (rs == true) {
            JOptionPane.showMessageDialog(this, "Tạo phiếu nhập thành công.");
            this.dispose();
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnLuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuaActionPerformed
        // TODO add your handling code here:
        addProduct();
        LoadMoney();
    }//GEN-LAST:event_btnLuaActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        int select = tblSanPham.getSelectedRow();
        String tensp = (String) tblSanPham.getValueAt(select, 1);
        int kho = (int) tblSanPham.getValueAt(select, 3);
        int idsp = (int) tblSanPham.getValueAt(select, 0);
        txtMaSanPham.setText(String.valueOf(idsp));
        txtKho.setText(String.valueOf(kho));
        txtTenSP.setText(tensp);
        txtSoluong.setText("1");
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblCho.getModel();
        model.setRowCount(0);
        txtKho.setText("");
        txtMaSanPham.setText("");
        txtSoluong.setText("");
        txtTenSP.setText("");
        txtghichu.setText("");
        jLabel4.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int select = tblCho.getSelectedRow();
        if (select == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn xóa.");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tblCho.getModel();
        model.removeRow(select);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhoActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(
                this,
                "Bạn có muốn sửa số lượng không?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null
        );

        if (result == JOptionPane.YES_OPTION) {
            // Mở ô nhập số mới
            String input = JOptionPane.showInputDialog(this, "Nhập số lượng mới:");
            if (input != null) {
                try {
                    int sl = Integer.parseInt(input.trim());
                    // validate và cập nhật
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ.");
                }
            }
        }
    }//GEN-LAST:event_txtKhoActionPerformed

    private void btnCapNhapSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhapSoLuongActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblCho.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm cần cập nhật số lượng.");
            return;
        }

        String currentQtyStr = tblCho.getValueAt(selectedRow, 3).toString();
        JTextField inputField = new JTextField(currentQtyStr);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Nhập số lượng mới:"));
        panel.add(inputField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Cập nhật số lượng",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                int newQty = Integer.parseInt(inputField.getText().trim());
                if (newQty < 1 || newQty > 10000) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải nằm trong khoảng từ 1 đến 10000.");
                    return;
                }

                tblCho.setValueAt(newQty, selectedRow, 3);

                Object unitPriceObj = tblCho.getValueAt(selectedRow, 5);
                double totalPriceOld = Double.parseDouble(unitPriceObj.toString());
                int oldQty = Integer.parseInt(currentQtyStr);

                if (oldQty > 0) {
                    double unitPrice = totalPriceOld / oldQty;
                    double newTotal = unitPrice * newQty;
                    tblCho.setValueAt(newTotal, selectedRow, 5);
                }

                LoadMoney(); 
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ.");
            }
        }

    }//GEN-LAST:event_btnCapNhapSoLuongActionPerformed

    private void txtSoluongMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSoluongMouseMoved
        // TODO add your handling code here:
        checkmoney();
    }//GEN-LAST:event_txtSoluongMouseMoved

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (checkmoney()) {
            int slht = 0;
            slht = Integer.parseInt(txtSoluong.getText());
            int trusoluong = slht - 1;
            txtSoluong.setText(String.valueOf(trusoluong));
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (checkmoney()) {
            int slht = 0;
            slht = Integer.parseInt(txtSoluong.getText());
            int tangsoluong = slht + 1;
            txtSoluong.setText(String.valueOf(tangsoluong));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        DefaultTableModel ob = (DefaultTableModel) tblSanPham.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        tblSanPham.setRowSorter(obj);
        obj.setRowFilter(javax.swing.RowFilter.regexFilter(jTextField1.getText()));
    }//GEN-LAST:event_jTextField1KeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhapSoLuong;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblCho;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtKho;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtghichu;
    // End of variables declaration//GEN-END:variables
}

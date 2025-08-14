package zentech.application.dialog;

import com.formdev.flatlaf.FlatClientProperties;
import com.sun.imageio.plugins.png.RowFilter;
import dao.WarehouseReceiptDAO;
import entity.PhieuNhap;
import entity.PhieuNhapChiTiet;
import entity.Product;
import entity.ProductArea;
import entity.Supplier;
import java.awt.Dialog;
import java.awt.Window;
import java.math.BigDecimal;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import service.WarehouseReceiptService;
import zentech.application.form.other.WarehouseReceiptForm;

public class WarehouseReceiptUpdateDialog extends JDialog {

    int maphieunhap = 0;
    String tensanpham = "";
    String tennhacungcap = "";

    WarehouseReceiptService wrs = new WarehouseReceiptService();
    WarehouseReceiptDAO wrd = new WarehouseReceiptDAO();

    public WarehouseReceiptUpdateDialog(Window parent, WarehouseReceiptForm warehouseReceiptForm, int id, String tennhacungcap) {
        super(parent, Dialog.ModalityType.APPLICATION_MODAL);
        this.maphieunhap = id;
        this.tennhacungcap = tennhacungcap;
        initComponents();
        LoadData();
        initUI();
    }

    public void initUI() {
        txtMaPhieuNhap.setEditable(false);

        tblCHiTietPhieuNhap.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        tblSanPham.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        tblCHiTietPhieuNhap.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        tblSanPham.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        txtNhaCungCap.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
    }

    public void LoadData() {
        LoadDataNCC();
        wrs.loadDataTable1(tblSanPham);
        DefaultTableModel model = (DefaultTableModel) tblCHiTietPhieuNhap.getModel();
        model.setRowCount(0);
        for (PhieuNhapChiTiet pnct : wrd.getAllPhieuNhapbyID(maphieunhap)) {
            txtMaPhieuNhap.setText(String.valueOf(pnct.getPh().getMaphieunhap()));
            model.addRow(new Object[]{pnct.getPh().getMaphieunhap(), pnct.getP().getTenSanPham(), pnct.getDongia(), pnct.getSoluong(), pnct.getGhichu()});
        }
    }

    public void LoadDataNCC() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (Supplier s : wrd.getAllNhaCungCap()) {
            if (s.getIs_delete() == 0 && s.getTrangThai() == 0) {
                model.addRow(new Object[]{s.getMaNhaCungCap(), s.getTenNhaCungCap()});
            }
        }
    }

    public boolean kiemtrasoluong() {
        int soluonghientai = 0;
        try {
            soluonghientai = Integer.parseInt(txtSoLuong.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sô lượng không hợp lệ.");
            txtSoLuong.setText("1");
            return false;
        }
        if (soluonghientai > 10000) {
            JOptionPane.showMessageDialog(this, "Số lượng không được lớn hơn 10000");
            txtSoLuong.setText("10000");
            return false;
        } else if (soluonghientai < 1) {
            JOptionPane.showMessageDialog(this, "Số lượng không được nhỏ hơn 1");
            txtSoLuong.setText("1");
            return false;
        } else {
            return true;
        }
    }

    public void LoadMoney() {
        int soluong = Integer.parseInt(txtSoLuong.getText());
        double gia = Double.parseDouble(txtGiaSanPham.getText());
        double dongia = soluong * gia;
        BigDecimal dongia1 = new BigDecimal(dongia);
        lblDonGia.setText(String.valueOf(dongia1));
    }

    public PhieuNhapChiTiet getUpdate() {
        PhieuNhapChiTiet pnct = new PhieuNhapChiTiet();

        PhieuNhap ph = new PhieuNhap();
        ph.setMaphieunhap(maphieunhap);
        pnct.setPh(ph);

        Product sp = new Product();
        int maSpMoi = wrd.getMaSanPhambyTen(txtSanPham.getText().trim());
        sp.setMaSanPham(maSpMoi);
        pnct.setP(sp);

        BigDecimal gia = new BigDecimal(txtGiaSanPham.getText().trim());
        int sl = Integer.parseInt(txtSoLuong.getText().trim());
        pnct.setSoluong(sl);
        pnct.setDongia(gia.multiply(new BigDecimal(sl)));

        return pnct;
    }

    public boolean checkUpdate() {
        if (txtSanPham.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm!");
            return false;
        }
        if (txtSoLuong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng!");
            return false;
        }
        if (txtGiaSanPham.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá sản phẩm!");
            return false;
        } else {
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaPhieuNhap = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSanPham = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblDonGia = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtGiaSanPham = new javax.swing.JTextField();
        txtNhaCungCap = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCHiTietPhieuNhap = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jTextField7 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jButton6.setText("jButton6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cập nhật phiếu nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel1.setText("Mã Phiếu nhập");

        jLabel2.setText("Nhà cung cấp");

        jLabel3.setText("Sản phẩm");

        jLabel4.setText("Số lượng");

        txtSoLuong.setText("1");
        txtSoLuong.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                txtSoLuongMouseMoved(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Đơn giá"));

        lblDonGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDonGia.setForeground(new java.awt.Color(51, 255, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDonGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDonGia, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

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

        jLabel5.setText("Giá");

        txtGiaSanPham.setText("0");
        txtGiaSanPham.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                txtGiaSanPhamMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNhaCungCap, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtMaPhieuNhap)
                            .addComponent(txtSanPham, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoLuong)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addComponent(txtGiaSanPham))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(txtNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGiaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết phiếu nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tblCHiTietPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu nhập", "Tên sản phẩm", "Đơn giá", "Số lượng", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCHiTietPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCHiTietPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCHiTietPhieuNhap);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

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
        jScrollPane8.setViewportView(tblSanPham);

        jTextField7.setPreferredSize(new java.awt.Dimension(71, 32));
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel15.setText("Search");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton1.setText("Cập nhật");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Hủy");

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String[] update = {"Phiếu nhập", "Phiếu nhập chi tiết"};
        int luachon = JOptionPane.showOptionDialog(
                this,
                "Bạn muốn cập nhật phần nào?",
                "Cập nhật",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                update,
                update[0]
        );
        if (luachon == 0) {
            String tennhacungcap = txtNhaCungCap.getText();
            if (tennhacungcap.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp muốn cập nhập!");
                return;
            }
            int rs = wrd.UpdatePhieunhap(maphieunhap, wrd.getMaNhaCungCap(tennhacungcap));
            if (rs > 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công.");
                this.dispose();
                Window parent = SwingUtilities.getWindowAncestor(this);
                WarehouseReceiptDetailsDialog showdetail = new WarehouseReceiptDetailsDialog(parent, null, maphieunhap);
                showdetail.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại phiếu xuất có mã " + this.maphieunhap + ".");
            }
        } else {
            if (checkUpdate()) {
                if (kiemtrasoluong()) {
                    int rs = wrd.UpdatePhieuNhapChiTiet(getUpdate(), wrd.getMaSanPhambyTen(tensanpham));
                    if (rs > 0) {
                        JOptionPane.showMessageDialog(this, "Cập nhật thành công.");
                        this.dispose();
                        Window parent = SwingUtilities.getWindowAncestor(this);
                        WarehouseReceiptDetailsDialog showdetail = new WarehouseReceiptDetailsDialog(parent, null, maphieunhap);
                        showdetail.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Cập nhật thất bại phiếu xuất có mã " + this.maphieunhap + ".");
                    }
                }
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtGiaSanPhamMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGiaSanPhamMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaSanPhamMouseMoved

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        if (kiemtrasoluong()) {
            int slht = 0;
            slht = Integer.parseInt(txtSoLuong.getText());
            int trusoluong = slht - 1;
            txtSoLuong.setText(String.valueOf(trusoluong));
            LoadMoney();
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        if (kiemtrasoluong()) {
            int slht = 0;
            slht = Integer.parseInt(txtSoLuong.getText());
            int tangsoluong = slht + 1;
            txtSoLuong.setText(String.valueOf(tangsoluong));
            LoadMoney();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtSoLuongMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSoLuongMouseMoved
        // TODO add your handling code here:
        kiemtrasoluong();
    }//GEN-LAST:event_txtSoLuongMouseMoved

    private void tblCHiTietPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCHiTietPhieuNhapMouseClicked
        // TODO add your handling code here:
        int selectRow = tblCHiTietPhieuNhap.getSelectedRow();
        String tensanpham = (String) tblCHiTietPhieuNhap.getValueAt(selectRow, 1);
        BigDecimal dongia = (BigDecimal) tblCHiTietPhieuNhap.getValueAt(selectRow, 2);
        int soluong = (int) tblCHiTietPhieuNhap.getValueAt(selectRow, 3);

        txtSanPham.setText(tensanpham);
        txtSoLuong.setText(String.valueOf(soluong));
        lblDonGia.setText(String.valueOf(dongia));

        int id = wrd.getMaSanPhambyTen(tensanpham);
        txtGiaSanPham.setText(String.valueOf(wrd.getdongiabyid(id)));

        this.tensanpham = tensanpham;
    }//GEN-LAST:event_tblCHiTietPhieuNhapMouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        DefaultTableModel ob = (DefaultTableModel) tblSanPham.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        tblSanPham.setRowSorter(obj);
        obj.setRowFilter(javax.swing.RowFilter.regexFilter(jTextField7.getText()));
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        if (this.tensanpham.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu nhập chi tiết để chọn sản phẩm muốn cập nhật!");
            return;
        }

        int select = tblSanPham.getSelectedRow();

        String tensanpham = (String) tblSanPham.getValueAt(select, 1);
        BigDecimal gia = (BigDecimal) tblSanPham.getValueAt(select, 2);

        txtSanPham.setText(tensanpham);
        txtSoLuong.setText("1");
        txtGiaSanPham.setText(String.valueOf(gia));
        lblDonGia.setText(String.valueOf(gia));
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int select = jTable1.getSelectedRow();
        String ten = (String) jTable1.getValueAt(select, 1);
        txtNhaCungCap.setText(ten);

    }//GEN-LAST:event_jTable1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel lblDonGia;
    private javax.swing.JTable tblCHiTietPhieuNhap;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPham5;
    private javax.swing.JTextField txtGiaSanPham;
    private javax.swing.JTextField txtMaPhieuNhap;
    private javax.swing.JTextField txtNhaCungCap;
    private javax.swing.JTextField txtSanPham;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}

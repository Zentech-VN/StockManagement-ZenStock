package zentech.application.dialog;

import dao.WarehouseDeliveryDAO;
import entity.Client;

import entity.PhieuNhapChiTiet;
import entity.PhieuXuat;
import entity.PhieuXuatChiTiet;
import entity.Product;
import entity.ProductArea;

import java.awt.Dialog;

import java.awt.Window;
import java.math.BigDecimal;

import javax.swing.JDialog;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableRowSorter;
import zentech.application.form.other.WarehouseDeliveryForm;

public class WarehouseDeliveryUpdateForm extends JDialog {

    private WarehouseDeliveryForm warehousedeliveryform;
    private WarehouseDeliveryDAO wdd = new WarehouseDeliveryDAO();
    String tensanpham = "";
    int maphieuxuat = 0;
    String tenkhachhang = "";
    int soluongcuasanpham = 0;

    public WarehouseDeliveryUpdateForm(Window parent, WarehouseDeliveryForm from, int id, String tenkhachhang) {
        super(parent, Dialog.ModalityType.APPLICATION_MODAL);
        initComponents();
        this.warehousedeliveryform = from;
        this.maphieuxuat = id;
        this.tenkhachhang = tenkhachhang;
        initUI();
        LoadData();
    }

    public void LoadData() {
        LoadDataTableKhachHang();
        LoadDataTableSanPham();
        LoadDataTableCTPhieuXuat();
    }

    public void LoadDataTableKhachHang() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        for (Client c : wdd.getAllKhachHang()) {
            model.addRow(new Object[]{c.getMaKhacHang(), c.getTenKhacHang()});
        }
    }

    public void LoadDataTableSanPham() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        for (ProductArea pa : wdd.GetProducArea()) {
            model.addRow(new Object[]{pa.getP().getMaSanPham(), pa.getP().getTenSanPham(), pa.getP().getGia(), pa.getW().getMaKhuVuc(), pa.getSoluong()});
        }
    }

    public void LoadDataTableCTPhieuXuat() {
        DefaultTableModel model = (DefaultTableModel) tblChiTietPhieuXuat.getModel();
        model.setRowCount(0);
        for (PhieuXuatChiTiet pxct : wdd.getAllPhieuXuatChiTiet(maphieuxuat)) {
            model.addRow(
                    new Object[]{
                        pxct.getPhieuxuat().getMaphieuxuat(),
                        pxct.getSanpham().getTenSanPham(),
                        pxct.getDongia(),
                        pxct.getSoluong(),
                        pxct.getGhichu()
                    }
            );
        }
    }

    public void initUI() {
        txtMaPhieuNhap.setEditable(false);
        txtMaKhachHang.setEditable(false);
        txtMaSanPham.setEditable(false);
        txtMaPhieuNhap.setText(String.valueOf(this.maphieuxuat));
        Client c = wdd.getKhachHangbyId(tenkhachhang);
        txtMaKhachHang.setText(String.valueOf(c.getMaKhacHang()));
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

    public void LoadMoney() {
        int soluong = Integer.parseInt(txtSoLuong.getText());
        double gia = Double.parseDouble(txtGiaSanPham.getText());
        double dongia = soluong * gia;
        BigDecimal dongia1 = new BigDecimal(dongia);
        lblDonGia.setText(String.valueOf(dongia1));
    }

    public PhieuXuatChiTiet getFrom() {
        PhieuXuatChiTiet pxct = new PhieuXuatChiTiet();

        PhieuXuat px = new PhieuXuat();
        px.setMaphieuxuat(maphieuxuat);
        pxct.setPhieuxuat(px);

        Product sp = new Product();
        int maSp = wdd.getMaSanPhambyTen(txtMaSanPham.getText());
        sp.setMaSanPham(maSp);
        pxct.setSanpham(sp);

        BigDecimal gia = new BigDecimal(txtGiaSanPham.getText().trim());
        int sl = Integer.parseInt(txtSoLuong.getText().trim());
        pxct.setDongia(gia.multiply(new BigDecimal(sl)));

        pxct.setSoluong(sl);
        pxct.setGhichu(txtghichu.getText());

        return pxct;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChiTietPhieuXuat = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaPhieuNhap = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblDonGia = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtGiaSanPham = new javax.swing.JTextField();
        txtMaKhachHang = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtghichu = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

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
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel9.setText("Search");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setText("Cập nhật");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Hủy");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết phiếu xuất", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tblChiTietPhieuXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu xuất", "Sản phẩm", "Đơn giá", "Số lượng", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietPhieuXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietPhieuXuatMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblChiTietPhieuXuat);
        if (tblChiTietPhieuXuat.getColumnModel().getColumnCount() > 0) {
            tblChiTietPhieuXuat.getColumnModel().getColumn(0).setResizable(false);
            tblChiTietPhieuXuat.getColumnModel().getColumn(1).setResizable(false);
            tblChiTietPhieuXuat.getColumnModel().getColumn(2).setResizable(false);
            tblChiTietPhieuXuat.getColumnModel().getColumn(3).setResizable(false);
            tblChiTietPhieuXuat.getColumnModel().getColumn(4).setResizable(false);
        }

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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cập nhật phiếu xuất", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel1.setText("Mã Phiếu nhập");

        jLabel2.setText("Khách hàng");

        jLabel3.setText("Mã Sản phẩm");

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

        jLabel6.setText("Ghi chú");

        txtghichu.setColumns(20);
        txtghichu.setRows(5);
        jScrollPane4.setViewportView(txtghichu);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(252, 252, 252))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKhachHang, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtMaPhieuNhap)
                            .addComponent(txtMaSanPham, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoLuong)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhachHang);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(419, 419, 419)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        if (this.tensanpham.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu nhập chi tiết để chọn sản phẩm muốn cập nhật!");
            return;
        }

        int select = tblSanPham.getSelectedRow();
        String tensanpham = (String) tblSanPham.getValueAt(select, 1);
        BigDecimal gia = (BigDecimal) tblSanPham.getValueAt(select, 2);
        int soluong = (int) tblSanPham.getValueAt(select, 4);

        txtMaSanPham.setText(tensanpham);
        txtSoLuong.setText("1");
        txtGiaSanPham.setText(String.valueOf(gia));
        this.soluongcuasanpham = soluong;

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        DefaultTableModel ob = (DefaultTableModel) tblSanPham.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        tblSanPham.setRowSorter(obj);
        obj.setRowFilter(javax.swing.RowFilter.regexFilter(jTextField1.getText()));
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String[] update = {"Phiếu xuất", "Phiếu xuất chi tiết"};
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
            if (txtMaKhachHang.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng để cập nhật");
                return;
            }
            String tenkhachhang = txtMaKhachHang.getText();
            int rs = wdd.capnhatphieuxuat(maphieuxuat, wdd.getMaKhachHangbyTen(tenkhachhang));
            if (rs > 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công phiếu xuất có mã " + this.maphieuxuat + ".");
                this.dispose();
                Window parent = SwingUtilities.getWindowAncestor(this);
                WarehouseDeliveryDetailForm detail = new WarehouseDeliveryDetailForm(parent, null, this.maphieuxuat);
                detail.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại phiếu xuất có mã " + this.maphieuxuat + ".");

            }
        } else {
            if (txtMaSanPham.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để cập nhật");
                return;
            }
            if (kiemtrasoluong()) {
                int rs = wdd.capnhapchitietphieuxuat(getFrom(), wdd.getMaSanPhambyTen(txtMaSanPham.getText()));
                if (rs > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công phiếu xuất có mã " + this.maphieuxuat + ".");
                    this.dispose();
                    Window parent = SwingUtilities.getWindowAncestor(this);
                    WarehouseDeliveryDetailForm detail = new WarehouseDeliveryDetailForm(parent, null, this.maphieuxuat);
                    detail.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại phiếu xuất có mã " + this.maphieuxuat + ".");

                }
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblChiTietPhieuXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietPhieuXuatMouseClicked
        // TODO add your handling code here:
        int selectRow = tblChiTietPhieuXuat.getSelectedRow();
        String tensanpham = (String) tblChiTietPhieuXuat.getValueAt(selectRow, 1);
        this.tensanpham = tensanpham;
        BigDecimal dongia = (BigDecimal) tblChiTietPhieuXuat.getValueAt(selectRow, 2);
        int soluong = (int) tblChiTietPhieuXuat.getValueAt(selectRow, 3);

        txtMaSanPham.setText(tensanpham);
        txtSoLuong.setText(String.valueOf(soluong));
        lblDonGia.setText(String.valueOf(dongia));
        txtGiaSanPham.setText(String.valueOf(wdd.getdongiabyid(wdd.getMaSanPhambyTen(tensanpham))));
    }//GEN-LAST:event_tblChiTietPhieuXuatMouseClicked

    private void txtSoLuongMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSoLuongMouseMoved
        // TODO add your handling code here:
        kiemtrasoluong();
    }//GEN-LAST:event_txtSoLuongMouseMoved

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

    private void txtGiaSanPhamMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGiaSanPhamMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaSanPhamMouseMoved

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        int select = tblKhachHang.getSelectedRow();
        String id = (String) tblKhachHang.getValueAt(select, 1);
        txtMaKhachHang.setText(String.valueOf(id));
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void tblKhachHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblKhachHangMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblDonGia;
    private javax.swing.JTable tblChiTietPhieuXuat;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaSanPham;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtMaPhieuNhap;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextArea txtghichu;
    // End of variables declaration//GEN-END:variables
}

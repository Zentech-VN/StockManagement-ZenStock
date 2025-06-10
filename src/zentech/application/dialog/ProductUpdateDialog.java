package zentech.application.dialog;

import com.formdev.flatlaf.FlatClientProperties;
import entity.Product;
import java.awt.Window;
import javax.swing.JDialog;
import java.awt.Dialog;
import raven.toast.Notifications;
import service.ProductServiceMain;
import zentech.application.form.other.ProductForm;

public class ProductUpdateDialog extends JDialog {

    private int maSanPham;
    private ProductForm productForm;
    ProductServiceMain productService;

    public ProductUpdateDialog(Window parent, ProductForm productForm,
            int maSanPham, String tenSanPham, String hinhAnh, String chipXuLy, String dungLuongPin, String kichThuocManHinh, String cameraSau, String cameraTruoc, String thoiGianBaoHanh, int thongSo, java.math.BigDecimal gia, int trangThai, String tenXuatXu, String tenHeDieuHanh, String tenThuongHieu, String tenKhuVuc) {
        super(parent, Dialog.ModalityType.APPLICATION_MODAL);
        this.productForm = productForm;
        this.maSanPham = maSanPham;
        this.productService = new ProductServiceMain();

        initComponents();

        txtten.setText(tenSanPham);
        txtcamerasau.setText(cameraSau);
        txtcameratruoc.setText(cameraTruoc);
        txtxuatxu1.setText(tenXuatXu);
        txtthuonghieu1.setText(tenThuongHieu);
        txtdungluongpin.setText(dungLuongPin);
        txtkichthuocman.setText(kichThuocManHinh);
        txtchipxuly.setText(chipXuLy);
        txthinhanh.setText(hinhAnh);
        txthedieuhanh.setText(tenHeDieuHanh);
        // Không sử dụng phienbanhdh nữa
        txtphienbanhdh.setText("");
        txtthoigianbanhanh.setText(thoiGianBaoHanh);
        // Không sử dụng soluongton nữa
        txtsoluongton1.setText("0");
        txtkhuvuckho.setText(tenKhuVuc);


        cbotrangThai.removeAllItems();
        cbotrangThai.addItem("Hoạt động");
        cbotrangThai.addItem("Khóa");
        cbotrangThai.addItem("Ngưng bán");

        switch (trangThai) {
            case 1:
                cbotrangThai.setSelectedItem("Hoạt động");
            case 0:
                cbotrangThai.setSelectedItem("Khóa");
            case 2:
                cbotrangThai.setSelectedItem("Ngưng bán");
            default: {
                cbotrangThai.setSelectedIndex(-1);
                System.err.println("⚠ Trạng thái không hợp lệ: " + trangThai);
            }
        }

    }

    private void initalUI() {
        txtten.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tên sản phẩm");
        txtkhuvuckho.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tên khu vuc");
        txtsoluongton1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Số lượng tồn");
        txtthuonghieu1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Thương hiệu");
        txtxuatxu1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Xuất xứ");
        txtchipxuly.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Chip xử lý");
        txtkichthuocman.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Kích thước màn hình");
        txthinhanh.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Đường dẫn hình ảnh");
        txtcamerasau.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Camera sau");
        txtcameratruoc.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Camera trước");
        txtthoigianbanhanh.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Thời gian bảo hành");
        txtphienbanhdh.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Phiên bản HĐH");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txthinhanh = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        txtxuatxu1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtchipxuly = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtdungluongpin = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtkichthuocman = new javax.swing.JTextField();
        txtten = new javax.swing.JTextField();
        txtphienbanhdh = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtcamerasau = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtcameratruoc = new javax.swing.JTextField();
        txthedieuhanh = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtkhuvuckho = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtsoluongton1 = new javax.swing.JTextField();
        txtthoigianbanhanh = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtthuonghieu1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        cbotrangThai = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton3.setText("Thêm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Huỷ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sửa sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel4.setMaximumSize(new java.awt.Dimension(100, 100));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Tên ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Hình ảnh");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Xuất xứ");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Chip xử lý");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Dung lượng pin ");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Kích thước màn hình");

        txtten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttenActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Phiên bản hệ điều hành");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Camera sau");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Camera trước");

        txthedieuhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthedieuhanhActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Hệ điều hành");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Thương hiệu");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Khu vực kho");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("số lượng tồn");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Thời gian bảo hành");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Trạng thái");

        cbotrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt Động", "Ngưng Hoạt Động", " " }));
        cbotrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbotrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txthinhanh, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(txtxuatxu1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(txtdungluongpin, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtchipxuly, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtkichthuocman, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txthedieuhanh, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtten, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(83, 83, 83)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbotrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtsoluongton1)
                    .addComponent(txtkhuvuckho)
                    .addComponent(txtthuonghieu1)
                    .addComponent(txtthoigianbanhanh)
                    .addComponent(txtcamerasau)
                    .addComponent(txtcameratruoc)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel21)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel22)
                            .addComponent(txtphienbanhdh, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(37, 37, 37))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtphienbanhdh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcamerasau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcameratruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtthoigianbanhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtthuonghieu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtkhuvuckho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsoluongton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txthinhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtxuatxu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtchipxuly, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdungluongpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtkichthuocman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txthedieuhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbotrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbotrangThai, jLabel10, jLabel11, jLabel12, jLabel13, jLabel14, jLabel15, jLabel16, jLabel17, jLabel18, jLabel19, jLabel20, jLabel21, jLabel22, jLabel8, jLabel9, txtcamerasau, txtcameratruoc, txtchipxuly, txtdungluongpin, txthedieuhanh, txthinhanh, txtkhuvuckho, txtkichthuocman, txtphienbanhdh, txtsoluongton1, txtten, txtthoigianbanhanh, txtthuonghieu1, txtxuatxu1});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(520, 520, 520)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String tenSanPham = txtten.getText().trim();
            String hinhAnh = txthinhanh.getText().trim();
            String xuatXu = txtxuatxu1.getText().trim();
            String chipXuLy = txtchipxuly.getText().trim();
            String dungLuongPin = txtdungluongpin.getText().trim();
            String kichThuocMan = txtkichthuocman.getText().trim();
            String heDieuHanh = txthedieuhanh.getText().trim();
            String cameraSau = txtcamerasau.getText().trim();
            String cameraTruoc = txtcameratruoc.getText().trim();
            String thoiGianBaoHanh = txtthoigianbanhanh.getText().trim();
            String thuongHieu = txtthuonghieu1.getText().trim();
            String khuVucKho = txtkhuvuckho.getText().trim();
            int thongSo = 1; // Default value
            java.math.BigDecimal gia = new java.math.BigDecimal("0"); // Default value
            String temp2 = String.valueOf(cbotrangThai.getSelectedItem());
            int trangThai = temp2.equals("Hoạt động") ? 1 : 0;

            entity.Product product = new entity.Product();
            product.setMaSanPham(maSanPham);
            product.setTenSanPham(tenSanPham);
            product.setHinhAnh(hinhAnh);
            product.setTenXuatXu(xuatXu);
            product.setChipXuLy(chipXuLy);
            product.setDungLuongPin(dungLuongPin);
            product.setKichThuocManHinh(kichThuocMan);
            product.setTenHeDieuHanh(heDieuHanh);
            product.setCameraSau(cameraSau);
            product.setCameraTruoc(cameraTruoc);
            product.setThoiGianBaoHanh(thoiGianBaoHanh);
            product.setThongSo(thongSo);
            product.setGia(gia);
            product.setTenThuongHieu(thuongHieu);
            product.setTenKhuVuc(khuVucKho);
            product.setTrangThai(trangThai);

            System.out.println("Cập nhật sản phẩm: " + product.getTenSanPham());

            boolean success = productService.updateProduct(product);
            if (!success) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Cập nhật sản phẩm thất bại!");
                return;
            }

            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Cập nhật sản phẩm thành công!");
            dispose();

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.WARNING, "Một số trường số không đúng định dạng. Vui lòng kiểm tra lại.");
        } catch (Exception ex) {
            ex.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, "Đã xảy ra lỗi khi cập nhật sản phẩm.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txthedieuhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthedieuhanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthedieuhanhActionPerformed

    private void txttenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenActionPerformed

    private void cbotrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbotrangThaiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbotrangThai;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField txtcamerasau;
    private javax.swing.JTextField txtcameratruoc;
    private javax.swing.JTextField txtchipxuly;
    private javax.swing.JTextField txtdungluongpin;
    private javax.swing.JTextField txthedieuhanh;
    private javax.swing.JTextField txthinhanh;
    private javax.swing.JTextField txtkhuvuckho;
    private javax.swing.JTextField txtkichthuocman;
    private javax.swing.JTextField txtphienbanhdh;
    private javax.swing.JTextField txtsoluongton1;
    private javax.swing.JTextField txtten;
    private javax.swing.JTextField txtthoigianbanhanh;
    private javax.swing.JTextField txtthuonghieu1;
    private javax.swing.JTextField txtxuatxu1;
    // End of variables declaration//GEN-END:variables
}

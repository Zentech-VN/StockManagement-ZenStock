package zentech.application.dialog;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Dialog;
import java.awt.Window;
import javax.swing.JDialog;
import raven.toast.Notifications;
import service.ProductServiceMain;
import zentech.application.form.other.ProductForm;

public class ProductAddDialog extends JDialog {

    private ProductServiceMain productService = new ProductServiceMain();
    private ProductForm productForm;

    public ProductAddDialog(Window parent, ProductForm productForm) {
        super(parent, Dialog.ModalityType.APPLICATION_MODAL);
        this.productForm = productForm;
        initComponents();

        initalUI();
    }

    private void initalUI() {
        txtten.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tên");
        txthinhanh.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Hình ảnh");
        txtxuatxu.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Xuất xứ");
        txtthuonghieu.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Thương hiệu");
        txtchipxuly.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Chip xử lý");
        txtdungluongpin.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Dung lượng pin");
        txtkichthuocman.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Kích thước màn");
        txtcamerasau.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Camera sau");
        txtcameratruoc.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Camera trước");
        txtkhuvuckho.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Khu vực kho");
        txtsoluongton.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Số lượng tồn");
        txttrangthai.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Trạng thái");
        txtthoigianbanhanh.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Thời gian bảo hành");
        txtphienbanhdh.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Phiên bản hệ điều hành");
        txthedieuhanh.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Hệ điều hành");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        txtEmail2 = new javax.swing.JTextField();
        txtkho1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtsoluongton1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txttrangthai1 = new javax.swing.JTextField();
        txtthuonghieu1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txthinhanh = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txtxuatxu = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtchipxuly = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtdungluongpin = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
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
        txtsoluongton = new javax.swing.JTextField();
        txtthoigianbanhanh = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtthuonghieu = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txttrangthai = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Email");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Kho");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Số lượng tồn");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Trạng thái");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Thương hiệu");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thêm sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel3.setMaximumSize(new java.awt.Dimension(100, 100));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Hình ảnh");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Xuất xứ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Chip xử lý");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Dung lượng pin ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Kích thước màn hình");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtten, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txthinhanh, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(txtxuatxu, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                    .addComponent(txtdungluongpin, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtchipxuly, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtkichthuocman, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txthedieuhanh, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtsoluongton, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtkhuvuckho, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtthuonghieu, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtthoigianbanhanh, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtcamerasau, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtcameratruoc, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txttrangthai, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(txtphienbanhdh, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel14, jLabel15, jLabel16, jLabel17, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel9});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txthinhanh, txtxuatxu});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtcamerasau, txtcameratruoc, txtphienbanhdh, txtthoigianbanhanh});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
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
                        .addComponent(txtthuonghieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtkhuvuckho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsoluongton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txthinhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtxuatxu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtchipxuly, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdungluongpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtkichthuocman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txthedieuhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel14, jLabel15, jLabel16, jLabel17, jLabel18, jLabel19, jLabel20, jLabel21, jLabel22, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel9, txtcamerasau, txtcameratruoc, txtchipxuly, txtdungluongpin, txthedieuhanh, txthinhanh, txtkhuvuckho, txtkichthuocman, txtphienbanhdh, txtsoluongton, txtten, txtthoigianbanhanh, txtthuonghieu, txttrangthai, txtxuatxu});

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Huỷ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel3.getAccessibleContext().setAccessibleName("Thêm sản phẩm\n");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (txtten.getText().trim().isEmpty()
                    || txthinhanh.getText().trim().isEmpty()
                    || txtxuatxu.getText().trim().isEmpty()
                    || txtchipxuly.getText().trim().isEmpty()
                    || txtdungluongpin.getText().trim().isEmpty()
                    || txtkichthuocman.getText().trim().isEmpty()
                    || txthedieuhanh.getText().trim().isEmpty()
                    || txtphienbanhdh.getText().trim().isEmpty()
                    || txtcamerasau.getText().trim().isEmpty()
                    || txtcameratruoc.getText().trim().isEmpty()
                    || txtthoigianbanhanh.getText().trim().isEmpty()
                    || txtthuonghieu.getText().trim().isEmpty()
                    || txtkhuvuckho.getText().trim().isEmpty()
                    || txtsoluongton.getText().trim().isEmpty()
                    || txttrangthai.getText().trim().isEmpty()) {

                Notifications.getInstance().show(Notifications.Type.WARNING, "Vui lòng nhập đầy đủ thông tin trước khi thêm sản phẩm.");
                return;
            }

            String tenSanPham = txtten.getText().trim();
            String hinhAnh = txthinhanh.getText().trim();
            String xuatXu = txtxuatxu.getText().trim();
            String chipXuLy = txtchipxuly.getText().trim();
            int dungLuongPin = Integer.parseInt(txtdungluongpin.getText().trim());
            double kichThuocMan = Double.parseDouble(txtkichthuocman.getText().trim());
            String heDieuHanh = txthedieuhanh.getText().trim();
            int phienBanHDH = Integer.parseInt(txtphienbanhdh.getText().trim());
            String cameraSau = txtcamerasau.getText().trim();
            String cameraTruoc = txtcameratruoc.getText().trim();
            int thoiGianBaoHanh = Integer.parseInt(txtthoigianbanhanh.getText().trim());
            String thuongHieu = txtthuonghieu.getText().trim();
            String khuVucKho = txtkhuvuckho.getText().trim();
            int soLuongTon = Integer.parseInt(txtsoluongton.getText().trim());
            int trangThaiValue = Integer.parseInt(txttrangthai.getText().trim());

            entity.Product product = new entity.Product();
            product.setTenSanPham(tenSanPham);
            product.setHinhAnh(hinhAnh);
            product.setTenXuatXu(xuatXu);
            product.setChipXuLy(chipXuLy);
            product.setDungLuongPin(dungLuongPin);
            product.setKichThuocManHinh(kichThuocMan);
            product.setTenHeDieuHanh(heDieuHanh);
            product.setPhienBanHeDieuHanh(phienBanHDH);
            product.setCameraSau(cameraSau);
            product.setCameraTruoc(cameraTruoc);
            product.setThoiGianBaoHanh(thoiGianBaoHanh);
            product.setTenThuongHieu(thuongHieu);
            product.setTenKhuVuc(khuVucKho);
            product.setSoLuongTon(soLuongTon);
            product.setTrangThai(trangThaiValue);

            if (!productService.addCheck(product)) {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Sản phẩm đã tồn tại!");
                return;
            }

            boolean success = productService.addProduct(product);
            if (!success) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Thêm sản phẩm thất bại!");
                return;
            }

            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Thêm sản phẩm thành công!");
            dispose();

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.WARNING, "Một số trường số không đúng định dạng. Vui lòng kiểm tra lại.");
        } catch (Exception ex) {
            ex.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, "Đã xảy ra lỗi khi thêm sản phẩm.");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txttenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenActionPerformed

    private void txthedieuhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthedieuhanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthedieuhanhActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtEmail2;
    private javax.swing.JTextField txtcamerasau;
    private javax.swing.JTextField txtcameratruoc;
    private javax.swing.JTextField txtchipxuly;
    private javax.swing.JTextField txtdungluongpin;
    private javax.swing.JTextField txthedieuhanh;
    private javax.swing.JTextField txthinhanh;
    private javax.swing.JTextField txtkho1;
    private javax.swing.JTextField txtkhuvuckho;
    private javax.swing.JTextField txtkichthuocman;
    private javax.swing.JTextField txtphienbanhdh;
    private javax.swing.JTextField txtsoluongton;
    private javax.swing.JTextField txtsoluongton1;
    private javax.swing.JTextField txtten;
    private javax.swing.JTextField txtthoigianbanhanh;
    private javax.swing.JTextField txtthuonghieu;
    private javax.swing.JTextField txtthuonghieu1;
    private javax.swing.JTextField txttrangthai;
    private javax.swing.JTextField txttrangthai1;
    private javax.swing.JTextField txtxuatxu;
    // End of variables declaration//GEN-END:variables
}

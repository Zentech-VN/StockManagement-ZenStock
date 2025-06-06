package zentech.application.form.other;

import entity.ProductView;
import javax.swing.table.DefaultTableModel;
import service.ProductService;

public class ProductForm extends javax.swing.JPanel {

    private ProductService productService;
    
    public ProductForm() {
        initComponents();
        loadProductViewData();
    }

    public void loadProductViewData() {
        this.productService = new ProductService();
        DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();
        model.setRowCount(0);

        for (ProductView x : productService.getAllProductViewService()) {
            model.addRow(new Object[]{
                x.getMaSanPham(),
                x.getTenSanPham(),
                x.getTenXuatXu(),
                x.getTenThuongHieu(),
                x.getTenKhuVuc(),
                x.getSoLuongTon(),
                x.getTrangThaiText()
            });
        }
        this.tblProduct.setModel(model);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        txtSearch = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        btnDetails = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill][grow 0]",
            new String[]{
                ""
            }
        ));

        crazyPanel2.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "JTextField.placeholderText=Search;background:@background",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][]",
            "",
            new String[]{
                "width 400"
            }
        ));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crazyPanel2.add(txtSearch);

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd.setText("Thêm");
        crazyPanel2.add(btnAdd);

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate.setText("Sửa");
        crazyPanel2.add(btnUpdate);

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setText("Xoá");
        crazyPanel2.add(btnDelete);

        crazyPanel1.add(crazyPanel2);

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên", "Xuất xứ", "Thương hiệu", "Kho", "Số lượng tồn", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProduct.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblProduct);

        crazyPanel1.add(jScrollPane1);

        crazyPanel3.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel3.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "push[][]",
            "",
            null
        ));

        btnDetails.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDetails.setText("Chi tiết");
        crazyPanel3.add(btnDetails);

        btnReload.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReload.setText("Làm mới");
        crazyPanel3.add(btnReload);

        crazyPanel1.add(crazyPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1171, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 662, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnUpdate;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

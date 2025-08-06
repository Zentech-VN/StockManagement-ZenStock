package zentech.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import entity.Brand;
import entity.MadeIn;
import entity.OS;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import service.BrandService;
import service.MadeInService;
import service.OSService;

public class AttributeForm extends javax.swing.JPanel {

    private BrandService brandService = new BrandService();
    private MadeInService madeInService = new MadeInService();
    private OSService oSService = new OSService();

    public AttributeForm() {
        initComponents();
        initalUI(tblBrand);
        initalUI(tblMadeIn);
        initalUI(tblOS);
        customer();
        loadToTableBrand(tblBrand);
        loadToTableMadeIn(tblMadeIn);
        loadToTableOS(tblOS);
    }

    public void loadToTableBrand(JTable tbl) {
        SwingWorker<DefaultTableModel, Void> worker = new SwingWorker<DefaultTableModel, Void>() {
            @Override
            protected DefaultTableModel doInBackground() {
                // Lấy model hiện tại
                DefaultTableModel model = (DefaultTableModel) tbl.getModel();
                model.setRowCount(0); // Xóa dữ liệu cũ

                // Lấy danh sách từ service (chạy trên background)
                List<Brand> list = brandService.getAllBrandsService();
                for (Brand b : list) {
                    Object[] row = {b.getId(), b.getTen()};
                    model.addRow(row);
                }
                return model;
            }

            @Override
            protected void done() {
                try {
                    // Cập nhật UI trên EDT
                    DefaultTableModel model = get();
                    tbl.setModel(model);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    public void loadToTableMadeIn(JTable tbl) {
        SwingWorker<DefaultTableModel, Void> worker = new SwingWorker<DefaultTableModel, Void>() {
            @Override
            protected DefaultTableModel doInBackground() {
                DefaultTableModel model = (DefaultTableModel) tbl.getModel();
                model.setRowCount(0);

                List<MadeIn> list = madeInService.getAllMadeIn();
                for (MadeIn m : list) {
                    Object[] row = {m.getId(), m.getTen()};
                    model.addRow(row);
                }
                return model;
            }

            @Override
            protected void done() {
                try {
                    DefaultTableModel model = get();
                    tbl.setModel(model);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    public void loadToTableOS(JTable tbl) {
        SwingWorker<DefaultTableModel, Void> worker = new SwingWorker<DefaultTableModel, Void>() {
            @Override
            protected DefaultTableModel doInBackground() {
                DefaultTableModel model = (DefaultTableModel) tbl.getModel();
                model.setRowCount(0);

                List<OS> list = oSService.getAllOSService();
                for (OS o : list) {
                    Object[] row = {o.getId(), o.getTen()};
                    model.addRow(row);
                }
                return model;
            }

            @Override
            protected void done() {
                try {
                    DefaultTableModel model = get();
                    tbl.setModel(model);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    private void customer() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

// Áp dụng căn giữa cho từng cột (ví dụ 2 cột)
        tblBrand.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Cột Mã Thương Hiệu
        tblBrand.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // Cột Tên Thương Hiệu
        tblMadeIn.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Cột Mã Thương Hiệu
        tblMadeIn.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // Cột Tên Thương Hiệu
        tblOS.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Cột Mã Thương Hiệu
        tblOS.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // Cột Tên Thương Hiệu
        txtMaKH1.setEditable(false);
        txtMaKH2.setEditable(false);
        txtMaKH3.setEditable(false);
        txtTenKH1.setEditable(true);
        txtTenKH2.setEditable(true);
        txtTenKH3.setEditable(true);
    }

    private void initalUI(JTable table) {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        materialTabbed1 = new zentech.application.tabbed.MaterialTabbed();
        jPanel1 = new javax.swing.JPanel();
        crazyPanel9 = new raven.crazypanel.CrazyPanel();
        crazyPanel10 = new raven.crazypanel.CrazyPanel();
        txtSearch7 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBrand = new javax.swing.JTable();
        crazyPanel11 = new raven.crazypanel.CrazyPanel();
        jLabel5 = new javax.swing.JLabel();
        txtMaKH1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTenKH1 = new javax.swing.JTextField();
        crazyPanel12 = new raven.crazypanel.CrazyPanel();
        btnSave1 = new javax.swing.JButton();
        btnUpdate1 = new javax.swing.JButton();
        btnDelete1 = new javax.swing.JButton();
        btnClear1 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        crazyPanel5 = new raven.crazypanel.CrazyPanel();
        crazyPanel6 = new raven.crazypanel.CrazyPanel();
        txtSearch8 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMadeIn = new javax.swing.JTable();
        crazyPanel7 = new raven.crazypanel.CrazyPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMaKH2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTenKH2 = new javax.swing.JTextField();
        crazyPanel8 = new raven.crazypanel.CrazyPanel();
        btnSave4 = new javax.swing.JButton();
        btnUpdate4 = new javax.swing.JButton();
        btnDelete4 = new javax.swing.JButton();
        btnClear4 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        txtSearch9 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblOS = new javax.swing.JTable();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaKH3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenKH3 = new javax.swing.JTextField();
        crazyPanel4 = new raven.crazypanel.CrazyPanel();
        btnSave3 = new javax.swing.JButton();
        btnUpdate3 = new javax.swing.JButton();
        btnDelete3 = new javax.swing.JButton();
        btnClear3 = new javax.swing.JButton();

        crazyPanel9.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel9.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            new String[]{
                ""
            }
        ));

        crazyPanel10.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "JTextField.placeholderText=Search;background:@background",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel10.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][][][]",
            "",
            new String[]{
                "width 400"
            }
        ));

        txtSearch7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearch7KeyReleased(evt);
            }
        });
        crazyPanel10.add(txtSearch7);

        crazyPanel9.add(crazyPanel10);

        tblBrand.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Thương Hiệu", "Tên Thương Hiệu "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBrand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBrandMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBrand);

        crazyPanel9.add(jScrollPane1);

        crazyPanel11.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Info.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel11.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            new String[]{
                ""
            }
        ));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel5.setText("Mã thương hiệu");
        crazyPanel11.add(jLabel5);

        txtMaKH1.setEditable(false);
        txtMaKH1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel11.add(txtMaKH1);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel8.setText("Tên thương hiệu");
        crazyPanel11.add(jLabel8);

        txtTenKH1.setEditable(false);
        txtTenKH1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel11.add(txtTenKH1);

        crazyPanel12.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel12.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "fill",
            "[fill][fill][fill][fill]",
            "",
            null
        ));

        btnSave1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave1.setText("Lưu");
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });
        crazyPanel12.add(btnSave1);

        btnUpdate1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate1.setText("Cập nhật");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });
        crazyPanel12.add(btnUpdate1);

        btnDelete1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete1.setText("Xóa");
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });
        crazyPanel12.add(btnDelete1);

        btnClear1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClear1.setText("Làm mới");
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });
        crazyPanel12.add(btnClear1);

        crazyPanel11.add(crazyPanel12);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1063, Short.MAX_VALUE)
                    .addComponent(crazyPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        materialTabbed1.addTab("Thương Hiệu", jPanel1);

        crazyPanel5.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel5.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            new String[]{
                ""
            }
        ));

        crazyPanel6.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "JTextField.placeholderText=Search;background:@background",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel6.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][][][]",
            "",
            new String[]{
                "width 400"
            }
        ));

        txtSearch8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearch8KeyReleased(evt);
            }
        });
        crazyPanel6.add(txtSearch8);

        crazyPanel5.add(crazyPanel6);

        tblMadeIn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Xuất Xứ", "Tên Xuất Xứ "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMadeIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMadeInMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblMadeIn);

        crazyPanel5.add(jScrollPane4);

        crazyPanel7.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Info.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel7.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            new String[]{
                ""
            }
        ));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setText("Mã xuất xứ");
        crazyPanel7.add(jLabel3);

        txtMaKH2.setEditable(false);
        txtMaKH2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel7.add(txtMaKH2);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setText("Tên xuất xứ");
        crazyPanel7.add(jLabel4);

        txtTenKH2.setEditable(false);
        txtTenKH2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel7.add(txtTenKH2);

        crazyPanel8.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel8.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "fill",
            "[fill][fill][fill][fill]",
            "",
            null
        ));

        btnSave4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave4.setText("Lưu");
        btnSave4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave4ActionPerformed(evt);
            }
        });
        crazyPanel8.add(btnSave4);

        btnUpdate4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate4.setText("Cập nhật");
        btnUpdate4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate4ActionPerformed(evt);
            }
        });
        crazyPanel8.add(btnUpdate4);

        btnDelete4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete4.setText("Xóa");
        btnDelete4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete4ActionPerformed(evt);
            }
        });
        crazyPanel8.add(btnDelete4);

        btnClear4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClear4.setText("Làm mới");
        btnClear4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear4ActionPerformed(evt);
            }
        });
        crazyPanel8.add(btnClear4);

        crazyPanel7.add(crazyPanel8);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crazyPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1063, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        materialTabbed1.addTab("Xuất Xứ", jPanel14);

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
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
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][][][]",
            "",
            new String[]{
                "width 400"
            }
        ));

        txtSearch9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearch9KeyReleased(evt);
            }
        });
        crazyPanel2.add(txtSearch9);

        crazyPanel1.add(crazyPanel2);

        tblOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hệ Điều Hành", "Tên Hệ Điều Hành"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOSMouseClicked(evt);
            }
        });
        tblOS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblOSKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tblOS);

        crazyPanel1.add(jScrollPane5);

        crazyPanel3.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Info.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel3.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            new String[]{
                ""
            }
        ));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setText("Mã hệ điều hành");
        crazyPanel3.add(jLabel1);

        txtMaKH3.setEditable(false);
        txtMaKH3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel3.add(txtMaKH3);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setText("Tên hệ điều hành");
        crazyPanel3.add(jLabel2);

        txtTenKH3.setEditable(false);
        txtTenKH3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        crazyPanel3.add(txtTenKH3);

        crazyPanel4.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table:background",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel4.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "fill",
            "[fill][fill][fill][fill]",
            "",
            null
        ));

        btnSave3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave3.setText("Lưu");
        btnSave3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave3ActionPerformed(evt);
            }
        });
        crazyPanel4.add(btnSave3);

        btnUpdate3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate3.setText("Cập nhật");
        btnUpdate3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate3ActionPerformed(evt);
            }
        });
        crazyPanel4.add(btnUpdate3);

        btnDelete3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete3.setText("Xóa");
        btnDelete3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete3ActionPerformed(evt);
            }
        });
        crazyPanel4.add(btnDelete3);

        btnClear3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClear3.setText("Làm mới");
        btnClear3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear3ActionPerformed(evt);
            }
        });
        crazyPanel4.add(btnClear3);

        crazyPanel3.add(crazyPanel4);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1063, Short.MAX_VALUE)
                    .addComponent(crazyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        materialTabbed1.addTab("Hệ Điều Hành", jPanel17);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnClear3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear3ActionPerformed
        // TODO add your handling code here:
        clearFormOS();
    }//GEN-LAST:event_btnClear3ActionPerformed

    private void clearFormOS() {
        oSService.clearForm(txtMaKH3, txtTenKH3, tblOS);
    }

    private void btnSave3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave3ActionPerformed
        // TODO add your handling code here:
        oSService.saveOS(txtTenKH3, tblOS, txtMaKH3);
    }//GEN-LAST:event_btnSave3ActionPerformed

    private void btnDelete3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete3ActionPerformed
        // TODO add your handling code here:
        oSService.deleteOS(txtMaKH3, tblOS, txtTenKH3);
    }//GEN-LAST:event_btnDelete3ActionPerformed

    private void btnUpdate3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate3ActionPerformed
        // TODO add your handling code here:
        oSService.updateOS(txtMaKH3, txtTenKH3, tblOS);
    }//GEN-LAST:event_btnUpdate3ActionPerformed

    private void tblOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOSMouseClicked
        // TODO add your handling code here:
        oSService.showSelectedOS(tblOS, txtMaKH3, txtTenKH3);
    }//GEN-LAST:event_tblOSMouseClicked

    private void txtSearch9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch9KeyReleased
        // TODO add your handling code here:
        oSService.Find(tblOS, txtSearch9);
    }//GEN-LAST:event_txtSearch9KeyReleased

    private void clearFormMadeIn() {
        madeInService.clearForm(txtMaKH2, txtMaKH1, tblMadeIn);
    }

    private void tblMadeInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMadeInMouseClicked
        // TODO add your handling code here:
        madeInService.showSelectedMadeIn(tblMadeIn, txtMaKH2, txtTenKH2);
    }//GEN-LAST:event_tblMadeInMouseClicked

    private void txtSearch8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch8KeyReleased
        // TODO add your handling code here:
        madeInService.Find(tblMadeIn, txtSearch8);
    }//GEN-LAST:event_txtSearch8KeyReleased

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        brandService.clearForm(txtMaKH1, txtTenKH1, tblBrand);
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        brandService.saveBrand(txtTenKH1, tblBrand, txtMaKH1);
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        brandService.deleteBrand(txtMaKH1, tblBrand, txtTenKH1);
    }//GEN-LAST:event_btnDelete1ActionPerformed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        brandService.updateBrand(txtMaKH1, txtTenKH1, tblBrand);
    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void tblBrandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBrandMouseClicked
        brandService.showSelectedBrand(tblBrand, txtMaKH1, txtTenKH1);
    }//GEN-LAST:event_tblBrandMouseClicked

    private void txtSearch7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch7KeyReleased
        brandService.Find(tblBrand, txtSearch7);
    }//GEN-LAST:event_txtSearch7KeyReleased

    private void tblOSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblOSKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblOSKeyReleased

    private void btnSave4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave4ActionPerformed
        // TODO add your handling code here:
        madeInService.saveMadeIn(txtTenKH2, tblMadeIn, txtMaKH2);
    }//GEN-LAST:event_btnSave4ActionPerformed

    private void btnUpdate4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate4ActionPerformed
        // TODO add your handling code here:
        madeInService.updateMadeIn(txtMaKH2, txtTenKH2, tblMadeIn);
    }//GEN-LAST:event_btnUpdate4ActionPerformed

    private void btnDelete4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete4ActionPerformed
        // TODO add your handling code here:
        madeInService.deleteMadeIn(txtMaKH2, tblMadeIn, txtTenKH2);
    }//GEN-LAST:event_btnDelete4ActionPerformed

    private void btnClear4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear4ActionPerformed
        // TODO add your handling code here:
        madeInService.clearForm(txtMaKH2, txtTenKH2, tblMadeIn);
    }//GEN-LAST:event_btnClear4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnClear3;
    private javax.swing.JButton btnClear4;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnDelete3;
    private javax.swing.JButton btnDelete4;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnSave3;
    private javax.swing.JButton btnSave4;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.JButton btnUpdate3;
    private javax.swing.JButton btnUpdate4;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel10;
    private raven.crazypanel.CrazyPanel crazyPanel11;
    private raven.crazypanel.CrazyPanel crazyPanel12;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private raven.crazypanel.CrazyPanel crazyPanel4;
    private raven.crazypanel.CrazyPanel crazyPanel5;
    private raven.crazypanel.CrazyPanel crazyPanel6;
    private raven.crazypanel.CrazyPanel crazyPanel7;
    private raven.crazypanel.CrazyPanel crazyPanel8;
    private raven.crazypanel.CrazyPanel crazyPanel9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private zentech.application.tabbed.MaterialTabbed materialTabbed1;
    private javax.swing.JTable tblBrand;
    private javax.swing.JTable tblMadeIn;
    private javax.swing.JTable tblOS;
    private javax.swing.JTextField txtMaKH1;
    private javax.swing.JTextField txtMaKH2;
    private javax.swing.JTextField txtMaKH3;
    private javax.swing.JTextField txtSearch7;
    private javax.swing.JTextField txtSearch8;
    private javax.swing.JTextField txtSearch9;
    private javax.swing.JTextField txtTenKH1;
    private javax.swing.JTextField txtTenKH2;
    private javax.swing.JTextField txtTenKH3;
    // End of variables declaration//GEN-END:variables
}

package zentech.application.dialog;

import dao.UserRightsDAO;
import java.awt.Dialog;
import java.awt.Window;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import zentech.application.form.other.UserRightsForm;

public class UserRightsDetailsDialog extends JDialog {

    private final int manhomquyen;
    private final UserRightsDAO dao = new UserRightsDAO();
    private final Map<String, Map<String, JCheckBox>> cbxMatrix = new LinkedHashMap<>();

    public UserRightsDetailsDialog(Window parent, UserRightsForm userRightsForm, int id) {
        super(parent, Dialog.ModalityType.APPLICATION_MODAL);
        this.manhomquyen = id;
        initComponents();
        bindCheckboxesByIndex();
        loadGroupData();
        lockAsReadOnly();

    }

    private Map<String, JCheckBox> row(JCheckBox v, JCheckBox c, JCheckBox u, JCheckBox d) {
        Map<String, JCheckBox> m = new LinkedHashMap<>();
        m.put("read", v);
        m.put("create", c);
        m.put("update", u);
        m.put("delete", d);
        return m;
    }

    private static final java.util.List<String> FEATURES = java.util.Arrays.asList(
            "thongke", "taikhoan", "nhanvien", "nhomquyen",
            "sanpham", "khuvuckho", "phieunhap", "phieuxuat",
            "duyetphieu", "thuoctinh", "khachhang", "nhacungcap"
    );

    private JCheckBox[] allCbx() {
        return new JCheckBox[]{
            jCheckBox1, jCheckBox2, jCheckBox3, jCheckBox4,
            jCheckBox5, jCheckBox6, jCheckBox7, jCheckBox8,
            jCheckBox11, jCheckBox9, jCheckBox12, jCheckBox10,
            jCheckBox15, jCheckBox16, jCheckBox13, jCheckBox14,
            jCheckBox24, jCheckBox21, jCheckBox22, jCheckBox23,
            jCheckBox28, jCheckBox25, jCheckBox26, jCheckBox27,
            jCheckBox32, jCheckBox31, jCheckBox30, jCheckBox29,
            jCheckBox36, jCheckBox34, jCheckBox33, jCheckBox35,
            jCheckBox39, jCheckBox38, jCheckBox37,
            jCheckBox44, jCheckBox41, jCheckBox43, jCheckBox42,
            jCheckBox48, jCheckBox47, jCheckBox45, jCheckBox46,
            jCheckBox49, jCheckBox50, jCheckBox52, jCheckBox51
        };
    }

 private void bindCheckboxesByIndex() {
    JCheckBox[] C = allCbx();
    int total = 0;

    for (int i = 0; i < FEATURES.size(); i++) {
        total += (i == 8) ? 3 : 4; // chức năng thứ 9 (index = 8) chỉ có 3 quyền
    }

    if (total != C.length) {
        throw new IllegalStateException("Số checkbox không khớp với tổng quyền của các chức năng.");
    }

    cbxMatrix.clear();
    int index = 0;
    for (int i = 0; i < FEATURES.size(); i++) {
        String feature = FEATURES.get(i);
        if (i == 8) {
            // Chức năng đặc biệt chỉ có 3 quyền
            cbxMatrix.put(feature, row(C[index], C[index + 1], C[index + 2], null));
            index += 3;
        } else {
            cbxMatrix.put(feature, row(C[index], C[index + 1], C[index + 2], C[index + 3]));
            index += 4;
        }
    }
}

    private void loadGroupData() {
        try {
            String ten = dao.getTenNhomQuyenById(manhomquyen);
            if (ten != null) {
                txtTenNhomQuyen.setText(ten);
            }

            Map<String, java.util.Set<String>> mx = dao.getRightsMatrixByGroup(manhomquyen);
            for (Map.Entry<String, java.util.Set<String>> e : mx.entrySet()) {
                Map<String, JCheckBox> row = cbxMatrix.get(e.getKey());
                if (row == null) {
                    continue;
                }
                for (String a : e.getValue()) {
                    JCheckBox cb = row.get(a);
                    if (cb != null) {
                        cb.setSelected(true);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Không tải được dữ liệu nhóm: " + ex.getMessage());
        }
    }

    private void lockAsReadOnly() {
        txtTenNhomQuyen.setEditable(false);
        for (JCheckBox cb : allCbx()) {
            cb.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        txtTenNhomQuyen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox15 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jCheckBox21 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        jCheckBox23 = new javax.swing.JCheckBox();
        jCheckBox24 = new javax.swing.JCheckBox();
        jCheckBox25 = new javax.swing.JCheckBox();
        jCheckBox26 = new javax.swing.JCheckBox();
        jCheckBox27 = new javax.swing.JCheckBox();
        jCheckBox28 = new javax.swing.JCheckBox();
        jCheckBox29 = new javax.swing.JCheckBox();
        jCheckBox30 = new javax.swing.JCheckBox();
        jCheckBox31 = new javax.swing.JCheckBox();
        jCheckBox32 = new javax.swing.JCheckBox();
        jCheckBox33 = new javax.swing.JCheckBox();
        jCheckBox34 = new javax.swing.JCheckBox();
        jCheckBox35 = new javax.swing.JCheckBox();
        jCheckBox36 = new javax.swing.JCheckBox();
        jCheckBox41 = new javax.swing.JCheckBox();
        jCheckBox42 = new javax.swing.JCheckBox();
        jCheckBox43 = new javax.swing.JCheckBox();
        jCheckBox44 = new javax.swing.JCheckBox();
        jCheckBox45 = new javax.swing.JCheckBox();
        jCheckBox46 = new javax.swing.JCheckBox();
        jCheckBox47 = new javax.swing.JCheckBox();
        jCheckBox48 = new javax.swing.JCheckBox();
        jCheckBox49 = new javax.swing.JCheckBox();
        jCheckBox50 = new javax.swing.JCheckBox();
        jCheckBox51 = new javax.swing.JCheckBox();
        jCheckBox52 = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jCheckBox39 = new javax.swing.JCheckBox();
        jCheckBox38 = new javax.swing.JCheckBox();
        jCheckBox37 = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết Nhóm quyền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel3.setMaximumSize(new java.awt.Dimension(100, 100));

        txtTenNhomQuyen.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Xem thống kê");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("*");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Danh mục chức năng");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên nhóm quyền");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Xem");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Xoá");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Quản lý Tài khoản");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Quản lý Nhân viên");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Quản lý Quyền hạn");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Quản lý Sản phẩm");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Quản lý Khu vực kho");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Quản lý Phiếu nhập");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Quản lý Phiếu xuất");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Quản lý Thuộc tính");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Quản lý Khách hàng");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Quản lý Nhà cung cấp");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Tạo mới");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Cập nhật");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenNhomQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addGap(90, 90, 90)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jCheckBox1)
                                                .addGap(112, 112, 112)
                                                .addComponent(jCheckBox2)
                                                .addGap(122, 122, 122)
                                                .addComponent(jCheckBox3))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jCheckBox5)
                                                .addGap(112, 112, 112)
                                                .addComponent(jCheckBox6)
                                                .addGap(122, 122, 122)
                                                .addComponent(jCheckBox7))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jCheckBox11)
                                                .addGap(112, 112, 112)
                                                .addComponent(jCheckBox9)
                                                .addGap(122, 122, 122)
                                                .addComponent(jCheckBox12))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jCheckBox15)
                                                .addGap(112, 112, 112)
                                                .addComponent(jCheckBox16)
                                                .addGap(122, 122, 122)
                                                .addComponent(jCheckBox13))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jCheckBox24)
                                                .addGap(112, 112, 112)
                                                .addComponent(jCheckBox21)
                                                .addGap(122, 122, 122)
                                                .addComponent(jCheckBox22))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jCheckBox28)
                                                .addGap(112, 112, 112)
                                                .addComponent(jCheckBox25)
                                                .addGap(122, 122, 122)
                                                .addComponent(jCheckBox26))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jCheckBox32)
                                                .addGap(112, 112, 112)
                                                .addComponent(jCheckBox31)
                                                .addGap(122, 122, 122)
                                                .addComponent(jCheckBox30))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jCheckBox36)
                                                .addGap(112, 112, 112)
                                                .addComponent(jCheckBox34)
                                                .addGap(122, 122, 122)
                                                .addComponent(jCheckBox33))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jCheckBox44)
                                                .addGap(112, 112, 112)
                                                .addComponent(jCheckBox41)
                                                .addGap(122, 122, 122)
                                                .addComponent(jCheckBox43))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jCheckBox48)
                                                .addGap(112, 112, 112)
                                                .addComponent(jCheckBox47)
                                                .addGap(122, 122, 122)
                                                .addComponent(jCheckBox45))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jCheckBox49)
                                                .addGap(112, 112, 112)
                                                .addComponent(jCheckBox50)
                                                .addGap(122, 122, 122)
                                                .addComponent(jCheckBox52))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(91, 91, 91)
                                                .addComponent(jLabel21)
                                                .addGap(89, 89, 89)
                                                .addComponent(jLabel17))))
                                    .addComponent(jLabel9))
                                .addGap(90, 90, 90)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox46)
                                    .addComponent(jCheckBox42)
                                    .addComponent(jCheckBox35)
                                    .addComponent(jCheckBox29)
                                    .addComponent(jCheckBox27)
                                    .addComponent(jCheckBox23)
                                    .addComponent(jCheckBox14)
                                    .addComponent(jCheckBox8)
                                    .addComponent(jCheckBox4)
                                    .addComponent(jLabel7)
                                    .addComponent(jCheckBox10)
                                    .addComponent(jCheckBox51))))
                        .addGap(6, 6, 6)
                        .addComponent(jLabel16)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBox49)
                        .addComponent(jCheckBox52))
                    .addComponent(jCheckBox50)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                            .addComponent(txtTenNhomQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                            .addComponent(jLabel16))
                                                                                                        .addGap(15, 15, 15)
                                                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                            .addComponent(jLabel1)
                                                                                                            .addComponent(jLabel4)
                                                                                                            .addComponent(jLabel7)
                                                                                                            .addComponent(jLabel17))
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                            .addComponent(jLabel2)
                                                                                                            .addComponent(jCheckBox1)
                                                                                                            .addComponent(jCheckBox3)
                                                                                                            .addComponent(jCheckBox4)))
                                                                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                                        .addComponent(jLabel21)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                        .addComponent(jCheckBox2)))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addComponent(jLabel8)
                                                                                                    .addComponent(jCheckBox8)))
                                                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(jCheckBox5)
                                                                                                .addComponent(jCheckBox7))
                                                                                            .addComponent(jCheckBox6))
                                                                                        .addGap(18, 18, 18)
                                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(jLabel9)
                                                                                            .addComponent(jCheckBox10)))
                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jCheckBox11)
                                                                                        .addComponent(jCheckBox12))
                                                                                    .addComponent(jCheckBox9))
                                                                                .addGap(18, 18, 18)
                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                    .addComponent(jLabel10)
                                                                                    .addComponent(jCheckBox14)))
                                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jCheckBox15)
                                                                                .addComponent(jCheckBox13))
                                                                            .addComponent(jCheckBox16))
                                                                        .addGap(18, 18, 18)
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(jLabel12)
                                                                            .addComponent(jCheckBox23)))
                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jCheckBox24)
                                                                        .addComponent(jCheckBox22))
                                                                    .addComponent(jCheckBox21))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel13)
                                                                    .addComponent(jCheckBox27)))
                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jCheckBox28)
                                                                .addComponent(jCheckBox26))
                                                            .addComponent(jCheckBox25))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel14)
                                                            .addComponent(jCheckBox29)))
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jCheckBox32)
                                                        .addComponent(jCheckBox30))
                                                    .addComponent(jCheckBox31))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel15)
                                                    .addComponent(jCheckBox35)))
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jCheckBox36)
                                                .addComponent(jCheckBox33))
                                            .addComponent(jCheckBox34))
                                        .addGap(16, 16, 16)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addComponent(jCheckBox42)))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBox44)
                                        .addComponent(jCheckBox43))
                                    .addComponent(jCheckBox41))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jCheckBox46)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jCheckBox48)
                                .addComponent(jCheckBox45))
                            .addComponent(jCheckBox47))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jCheckBox51)))))
                .addGap(15, 15, 15))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý duyệt phiếu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Xem");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Kiểm tra");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Chi tiết");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox39)
                    .addComponent(jLabel11))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jCheckBox38)
                        .addGap(122, 122, 122)
                        .addComponent(jCheckBox37))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel5)
                        .addGap(94, 94, 94)
                        .addComponent(jLabel6)))
                .addGap(201, 201, 201))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBox39)
                        .addComponent(jCheckBox37))
                    .addComponent(jCheckBox38))
                .addContainerGap())
        );

        btnCancel.setText("Huỷ");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox21;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox24;
    private javax.swing.JCheckBox jCheckBox25;
    private javax.swing.JCheckBox jCheckBox26;
    private javax.swing.JCheckBox jCheckBox27;
    private javax.swing.JCheckBox jCheckBox28;
    private javax.swing.JCheckBox jCheckBox29;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox30;
    private javax.swing.JCheckBox jCheckBox31;
    private javax.swing.JCheckBox jCheckBox32;
    private javax.swing.JCheckBox jCheckBox33;
    private javax.swing.JCheckBox jCheckBox34;
    private javax.swing.JCheckBox jCheckBox35;
    private javax.swing.JCheckBox jCheckBox36;
    private javax.swing.JCheckBox jCheckBox37;
    private javax.swing.JCheckBox jCheckBox38;
    private javax.swing.JCheckBox jCheckBox39;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox41;
    private javax.swing.JCheckBox jCheckBox42;
    private javax.swing.JCheckBox jCheckBox43;
    private javax.swing.JCheckBox jCheckBox44;
    private javax.swing.JCheckBox jCheckBox45;
    private javax.swing.JCheckBox jCheckBox46;
    private javax.swing.JCheckBox jCheckBox47;
    private javax.swing.JCheckBox jCheckBox48;
    private javax.swing.JCheckBox jCheckBox49;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox50;
    private javax.swing.JCheckBox jCheckBox51;
    private javax.swing.JCheckBox jCheckBox52;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtTenNhomQuyen;
    // End of variables declaration//GEN-END:variables
}

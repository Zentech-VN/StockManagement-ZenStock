package zentech.application.dialog;

import dao.UserRightsDAO;
import dao.UserRightsDAO.ActionRecord;
import java.awt.Dialog;
import java.awt.Window;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JCheckBox;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import zentech.application.form.other.UserRightsForm;

public class UserRightsUpdateDialog extends JDialog {

    private final int manhomquyen;
    private final UserRightsDAO dao = new UserRightsDAO();

    private UserRightsForm parentForm;

    // Ma trận checkbox: feature -> (read/create/update/delete)
    private final Map<String, Map<String, JCheckBox>> cbxMatrix = new LinkedHashMap<>();

    public UserRightsUpdateDialog(Window parent, UserRightsForm userRightsForm, int id) {
        super(parent, Dialog.ModalityType.APPLICATION_MODAL);
        this.manhomquyen = id;
        this.parentForm = userRightsForm;
        initComponents();
        bindCheckboxesByIndex();
        wireRules();
        loadGroupData();

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
            jCheckBox39, jCheckBox38, jCheckBox37, jCheckBox40,
            jCheckBox44, jCheckBox41, jCheckBox43, jCheckBox42,
            jCheckBox48, jCheckBox47, jCheckBox45, jCheckBox46,
            jCheckBox49, jCheckBox50, jCheckBox52, jCheckBox51
        };
    }

    private void bindCheckboxesByIndex() {
        JCheckBox[] C = allCbx();
        if (FEATURES.size() * 4 != C.length) {
            throw new IllegalStateException("Số checkbox không khớp số chức năng × 4");
        }
        cbxMatrix.clear();
        for (int i = 0; i < FEATURES.size(); i++) {
            cbxMatrix.put(FEATURES.get(i),
                    row(C[i * 4], C[i * 4 + 1], C[i * 4 + 2], C[i * 4 + 3]));
        }
    }

    private void wireRules() {
        for (Map<String, JCheckBox> row : cbxMatrix.values()) {
            JCheckBox v = row.get("read");
            JCheckBox c = row.get("create");
            JCheckBox u = row.get("update");
            JCheckBox d = row.get("delete");
            if (v == null) {
                continue;
            }

            java.awt.event.ItemListener ensureView = e -> {
                if ((c != null && c.isSelected())
                        || (u != null && u.isSelected())
                        || (d != null && d.isSelected())) {
                    v.setSelected(true);
                }
            };

            if (c != null) {
                c.addItemListener(ensureView);
            }
            if (u != null) {
                u.addItemListener(ensureView);
            }
            if (d != null) {
                d.addItemListener(ensureView);
            }

            v.addItemListener(e -> {
                if (!v.isSelected()) {
                    if ((c != null && c.isSelected())
                            || (u != null && u.isSelected())
                            || (d != null && d.isSelected())) {

                        JOptionPane.showMessageDialog(this,
                                "Bạn cần tắt tất cả quyền Tạo/Cập nhật/Xoá trước khi bỏ chọn quyền Xem.");
                        v.setSelected(true); // bật lại
                    }
                }
            });
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
            JOptionPane.showMessageDialog(this, "Không tải được dữ liệu nhóm: " + ex.getMessage());
        }
    }

    private void addIfSelected(java.util.List<ActionRecord> out, String feature, String action, JCheckBox cb) {
        if (cb != null && cb.isSelected()) {
            out.add(new ActionRecord(feature, action));
        }
    }

    private java.util.List<ActionRecord> collectRightsFromUI() {
        java.util.List<ActionRecord> rights = new java.util.ArrayList<>();
        for (Map.Entry<String, Map<String, JCheckBox>> e : cbxMatrix.entrySet()) {
            String feature = e.getKey();
            Map<String, JCheckBox> row = e.getValue();
            addIfSelected(rights, feature, "read", row.get("read"));
            addIfSelected(rights, feature, "create", row.get("create"));
            addIfSelected(rights, feature, "update", row.get("update"));
            addIfSelected(rights, feature, "delete", row.get("delete"));
        }
        return rights;
    }

    private void onUpdate() {
        String ten = txtTenNhomQuyen.getText().trim();
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhóm quyền!");
            return;
        }

        java.util.List<ActionRecord> rights = collectRightsFromUI();
        if (rights.isEmpty()) {
            int r = JOptionPane.showConfirmDialog(this,
                    "Nhóm quyền chưa có quyền nào. Bạn vẫn muốn lưu chứ?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (r != JOptionPane.YES_OPTION) {
                return;
            }
        }

        try {
            dao.replaceGroupRights(manhomquyen, ten, rights);
            JOptionPane.showMessageDialog(this, "Đã cập nhật nhóm quyền thành công!");
            if (parentForm != null) {
                parentForm.LoadDataTable();
            }
            dispose();
        } catch (SQLIntegrityConstraintViolationException dup) {
            JOptionPane.showMessageDialog(this, "Tên nhóm quyền đã tồn tại. Vui lòng chọn tên khác.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật: " + ex.getMessage());
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
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
        jCheckBox37 = new javax.swing.JCheckBox();
        jCheckBox38 = new javax.swing.JCheckBox();
        jCheckBox39 = new javax.swing.JCheckBox();
        jCheckBox40 = new javax.swing.JCheckBox();
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
        btnUpdate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sửa Nhóm quyền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel3.setMaximumSize(new java.awt.Dimension(100, 100));

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

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tạo mới");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Cập nhật");

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

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Quản lý Duyệt phiếu");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Quản lý Thuộc tính");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Quản lý Khách hàng");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Quản lý Nhà cung cấp");

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
                    .addComponent(jLabel17)
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
                                                .addComponent(jLabel4)
                                                .addGap(90, 90, 90)
                                                .addComponent(jLabel5)
                                                .addGap(90, 90, 90)
                                                .addComponent(jLabel6))
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
                                                .addComponent(jCheckBox39)
                                                .addGap(112, 112, 112)
                                                .addComponent(jCheckBox38)
                                                .addGap(122, 122, 122)
                                                .addComponent(jCheckBox37))
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
                                                .addComponent(jCheckBox52))))
                                    .addComponent(jLabel9))
                                .addGap(90, 90, 90)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox51)
                                    .addComponent(jCheckBox46)
                                    .addComponent(jCheckBox42)
                                    .addComponent(jCheckBox40)
                                    .addComponent(jCheckBox35)
                                    .addComponent(jCheckBox29)
                                    .addComponent(jCheckBox27)
                                    .addComponent(jCheckBox23)
                                    .addComponent(jCheckBox14)
                                    .addComponent(jCheckBox8)
                                    .addComponent(jCheckBox4)
                                    .addComponent(jLabel7)
                                    .addComponent(jCheckBox10))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)))
                .addGap(15, 15, 15))
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
                                                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                    .addComponent(txtTenNhomQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                    .addComponent(jLabel16))
                                                                                                                .addGap(15, 15, 15)
                                                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                    .addComponent(jLabel1)
                                                                                                                    .addComponent(jLabel4)
                                                                                                                    .addComponent(jLabel5)
                                                                                                                    .addComponent(jLabel6)
                                                                                                                    .addComponent(jLabel7))
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                    .addComponent(jLabel2)
                                                                                                                    .addComponent(jCheckBox1)
                                                                                                                    .addComponent(jCheckBox3)
                                                                                                                    .addComponent(jCheckBox4)))
                                                                                                            .addComponent(jCheckBox2))
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
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel17)
                                                    .addComponent(jCheckBox40)))
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jCheckBox39)
                                                .addComponent(jCheckBox37))
                                            .addComponent(jCheckBox38))
                                        .addGap(18, 18, 18)
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

        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

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
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        onUpdate();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnUpdate;
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
    private javax.swing.JCheckBox jCheckBox40;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtTenNhomQuyen;
    // End of variables declaration//GEN-END:variables
}

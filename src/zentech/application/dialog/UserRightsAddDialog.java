package zentech.application.dialog;

import dao.UserRightsDAO;
import dao.UserRightsDAO.ActionRecord;
import java.awt.Dialog;
import java.awt.Window;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import zentech.application.form.other.UserRightsForm;

public class UserRightsAddDialog extends JDialog {

    private final Map<String, Map<String, JCheckBox>> cbxMatrix = new LinkedHashMap<>();

// DAO dùng để thêm nhóm và quyền
    private final UserRightsDAO userRightsDAO = new UserRightsDAO();

    public UserRightsAddDialog(Window parent, UserRightsForm userRightsForm) {
        super(parent, Dialog.ModalityType.APPLICATION_MODAL);
        initComponents();

        bindExistingCheckboxes();
        wireRules();

    }

    private Map<String, JCheckBox> row(JCheckBox v, JCheckBox c, JCheckBox u, JCheckBox d) {
        Map<String, JCheckBox> m = new LinkedHashMap<>();
        m.put("read", v);
        m.put("create", c);
        m.put("update", u);
        m.put("delete", d);
        return m;
    }

    private void bindExistingCheckboxes() {

        cbxMatrix.put("thongke", row(chkThongKeView, chkThongKeCreate, chkThongKeUpdate, chkThongKeDelete));
        cbxMatrix.put("taikhoan", row(chkTaiKhoanView, chkTaiKhoanCreate, chkTaiKhoanUpdate, chkTaiKhoanDelete));
        cbxMatrix.put("nhanvien", row(chkNhanVienView, chkNhanVienCreate, chkNhanVienUpdate, chkNhanVienDelete));
        cbxMatrix.put("nhomquyen", row(chkNhomQuyenView, chkNhomQuyenCreate, chkNhomQuyenUpdate, chkNhomQuyenDelete));
        cbxMatrix.put("nhatky", row(chkNhatKyView, chkNhatKyCreate, chkNhatKyUpdate, chkNhatKyDelete));
        cbxMatrix.put("sanpham", row(chkSanPhamView, chkSanPhamCreate, chkSanPhamUpdate, chkSanPhamDelete));
        cbxMatrix.put("khuvuckho", row(chkKhuVucKhoView, chkKhuVucKhoCreate, chkKhuVucKhoUpdate, chkKhuVucKhoDelete));
        cbxMatrix.put("phieunhap", row(chkPhieuNhapView, chkPhieuNhapCreate, chkPhieuNhapUpdate, chkPhieuNhapDelete));
        cbxMatrix.put("phieuxuat", row(chkPhieuXuatView, chkPhieuXuatCreate, chkPhieuXuatUpdate, chkPhieuXuatDelete));
        cbxMatrix.put("duyetphieu", row(chkDuyetPhieuView, chkDuyetPhieuCreate, chkDuyetPhieuUpdate, chkDuyetPhieuDelete));
        cbxMatrix.put("thuoctinh", row(chkThuocTinhView, chkThuocTinhCreate, chkThuocTinhUpdate, chkThuocTinhDelete));
        cbxMatrix.put("khachhang", row(chkKhachHangView, chkKhachHangCreate, chkKhachHangUpdate, chkKhachHangDelete));
        cbxMatrix.put("nhacungcap", row(chkNhaCungCapView, chkNhaCungCapCreate, chkNhaCungCapUpdate, chkNhaCungCapDelete));

        // Thêm/bớt theo đúng danh sách chức năng trong bảng danhmucchucnang của bạn
    }

    private void addIfSelected(List<ActionRecord> out, String feature, String action, JCheckBox cb) {
        if (cb != null && cb.isSelected()) {
            out.add(new ActionRecord(feature, action));
        }
    }

    private List<ActionRecord> collectRightsFromUI() {
        List<ActionRecord> rights = new ArrayList<>();
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
                if ((c != null && c.isSelected()) || (u != null && u.isSelected()) || (d != null && d.isSelected())) {
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
        }
    }

    private void onSave() {
        String ten = txtTenNhomQuyen.getText().trim(); // đổi tên biến đúng với form bạn
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhóm quyền!");
            return;
        }

        List<ActionRecord> rights = collectRightsFromUI();
        if (rights.isEmpty()) {
            int r = JOptionPane.showConfirmDialog(this,
                    "Nhóm quyền chưa có quyền nào. Bạn vẫn muốn tạo chứ?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (r != JOptionPane.YES_OPTION) {
                return;
            }
        }

        // Cách 1: dùng transaction gói sẵn trong DAO (đơn giản nhất)
        try {
            int maNhom = userRightsDAO.createGroupAndAssignRights(ten, 1, rights); // 1 = hoạt động
            JOptionPane.showMessageDialog(this, "Đã tạo nhóm quyền mới (#" + maNhom + ") và lưu quyền thành công!");
            dispose();
            return;
        } catch (SQLIntegrityConstraintViolationException dup) {
            JOptionPane.showMessageDialog(this, "Tên nhóm quyền đã tồn tại. Vui lòng chọn tên khác.");
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi khi lưu nhóm quyền: " + ex.getMessage());
            return;
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
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        chkThongKeView = new javax.swing.JCheckBox();
        chkThongKeCreate = new javax.swing.JCheckBox();
        chkThongKeUpdate = new javax.swing.JCheckBox();
        chkThongKeDelete = new javax.swing.JCheckBox();
        chkTaiKhoanView = new javax.swing.JCheckBox();
        chkTaiKhoanCreate = new javax.swing.JCheckBox();
        chkTaiKhoanUpdate = new javax.swing.JCheckBox();
        chkTaiKhoanDelete = new javax.swing.JCheckBox();
        chkNhanVienCreate = new javax.swing.JCheckBox();
        chkNhanVienDelete = new javax.swing.JCheckBox();
        chkNhanVienView = new javax.swing.JCheckBox();
        chkNhanVienUpdate = new javax.swing.JCheckBox();
        chkNhomQuyenUpdate = new javax.swing.JCheckBox();
        chkNhomQuyenDelete = new javax.swing.JCheckBox();
        chkNhomQuyenView = new javax.swing.JCheckBox();
        chkNhomQuyenCreate = new javax.swing.JCheckBox();
        chkNhatKyDelete = new javax.swing.JCheckBox();
        chkNhatKyView = new javax.swing.JCheckBox();
        chkNhatKyCreate = new javax.swing.JCheckBox();
        chkNhatKyUpdate = new javax.swing.JCheckBox();
        chkSanPhamCreate = new javax.swing.JCheckBox();
        chkSanPhamUpdate = new javax.swing.JCheckBox();
        chkSanPhamDelete = new javax.swing.JCheckBox();
        chkSanPhamView = new javax.swing.JCheckBox();
        chkKhuVucKhoCreate = new javax.swing.JCheckBox();
        chkKhuVucKhoUpdate = new javax.swing.JCheckBox();
        chkKhuVucKhoDelete = new javax.swing.JCheckBox();
        chkKhuVucKhoView = new javax.swing.JCheckBox();
        chkPhieuNhapDelete = new javax.swing.JCheckBox();
        chkPhieuNhapUpdate = new javax.swing.JCheckBox();
        chkPhieuNhapCreate = new javax.swing.JCheckBox();
        chkPhieuNhapView = new javax.swing.JCheckBox();
        chkPhieuXuatUpdate = new javax.swing.JCheckBox();
        chkPhieuXuatCreate = new javax.swing.JCheckBox();
        chkPhieuXuatDelete = new javax.swing.JCheckBox();
        chkPhieuXuatView = new javax.swing.JCheckBox();
        chkDuyetPhieuUpdate = new javax.swing.JCheckBox();
        chkDuyetPhieuCreate = new javax.swing.JCheckBox();
        chkDuyetPhieuView = new javax.swing.JCheckBox();
        chkDuyetPhieuDelete = new javax.swing.JCheckBox();
        chkThuocTinhCreate = new javax.swing.JCheckBox();
        chkThuocTinhDelete = new javax.swing.JCheckBox();
        chkThuocTinhUpdate = new javax.swing.JCheckBox();
        chkThuocTinhView = new javax.swing.JCheckBox();
        chkKhachHangUpdate = new javax.swing.JCheckBox();
        chkKhachHangDelete = new javax.swing.JCheckBox();
        chkKhachHangCreate = new javax.swing.JCheckBox();
        chkKhachHangView = new javax.swing.JCheckBox();
        chkNhaCungCapView = new javax.swing.JCheckBox();
        chkNhaCungCapCreate = new javax.swing.JCheckBox();
        chkNhaCungCapDelete = new javax.swing.JCheckBox();
        chkNhaCungCapUpdate = new javax.swing.JCheckBox();
        btnThem = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thêm Nhóm quyền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
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

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Xem Nhật ký hoạt động");

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
                    .addComponent(jLabel11)
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
                                                .addComponent(chkThongKeView)
                                                .addGap(112, 112, 112)
                                                .addComponent(chkThongKeCreate)
                                                .addGap(122, 122, 122)
                                                .addComponent(chkThongKeUpdate))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(chkTaiKhoanView)
                                                .addGap(112, 112, 112)
                                                .addComponent(chkTaiKhoanCreate)
                                                .addGap(122, 122, 122)
                                                .addComponent(chkTaiKhoanUpdate))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(chkNhanVienView)
                                                .addGap(112, 112, 112)
                                                .addComponent(chkNhanVienCreate)
                                                .addGap(122, 122, 122)
                                                .addComponent(chkNhanVienUpdate))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(chkNhomQuyenView)
                                                .addGap(112, 112, 112)
                                                .addComponent(chkNhomQuyenCreate)
                                                .addGap(122, 122, 122)
                                                .addComponent(chkNhomQuyenUpdate))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(chkNhatKyView)
                                                .addGap(112, 112, 112)
                                                .addComponent(chkNhatKyCreate)
                                                .addGap(122, 122, 122)
                                                .addComponent(chkNhatKyUpdate))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(chkSanPhamView)
                                                .addGap(112, 112, 112)
                                                .addComponent(chkSanPhamCreate)
                                                .addGap(122, 122, 122)
                                                .addComponent(chkSanPhamUpdate))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(chkKhuVucKhoView)
                                                .addGap(112, 112, 112)
                                                .addComponent(chkKhuVucKhoCreate)
                                                .addGap(122, 122, 122)
                                                .addComponent(chkKhuVucKhoUpdate))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(chkPhieuNhapView)
                                                .addGap(112, 112, 112)
                                                .addComponent(chkPhieuNhapCreate)
                                                .addGap(122, 122, 122)
                                                .addComponent(chkPhieuNhapUpdate))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(chkPhieuXuatView)
                                                .addGap(112, 112, 112)
                                                .addComponent(chkPhieuXuatCreate)
                                                .addGap(122, 122, 122)
                                                .addComponent(chkPhieuXuatUpdate))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(chkDuyetPhieuView)
                                                .addGap(112, 112, 112)
                                                .addComponent(chkDuyetPhieuCreate)
                                                .addGap(122, 122, 122)
                                                .addComponent(chkDuyetPhieuUpdate))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(chkThuocTinhView)
                                                .addGap(112, 112, 112)
                                                .addComponent(chkThuocTinhCreate)
                                                .addGap(122, 122, 122)
                                                .addComponent(chkThuocTinhUpdate))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(chkKhachHangView)
                                                .addGap(112, 112, 112)
                                                .addComponent(chkKhachHangCreate)
                                                .addGap(122, 122, 122)
                                                .addComponent(chkKhachHangUpdate))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(chkNhaCungCapView)
                                                .addGap(112, 112, 112)
                                                .addComponent(chkNhaCungCapCreate)
                                                .addGap(122, 122, 122)
                                                .addComponent(chkNhaCungCapUpdate))))
                                    .addComponent(jLabel9))
                                .addGap(90, 90, 90)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkNhaCungCapDelete)
                                    .addComponent(chkKhachHangDelete)
                                    .addComponent(chkThuocTinhDelete)
                                    .addComponent(chkDuyetPhieuDelete)
                                    .addComponent(chkPhieuXuatDelete)
                                    .addComponent(chkPhieuNhapDelete)
                                    .addComponent(chkKhuVucKhoDelete)
                                    .addComponent(chkSanPhamDelete)
                                    .addComponent(chkNhatKyDelete)
                                    .addComponent(chkNhomQuyenDelete)
                                    .addComponent(chkTaiKhoanDelete)
                                    .addComponent(chkThongKeDelete)
                                    .addComponent(jLabel7)
                                    .addComponent(chkNhanVienDelete))))
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
                        .addComponent(chkNhaCungCapView)
                        .addComponent(chkNhaCungCapUpdate))
                    .addComponent(chkNhaCungCapCreate)
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
                                                                                                                            .addComponent(chkThongKeView)
                                                                                                                            .addComponent(chkThongKeUpdate)
                                                                                                                            .addComponent(chkThongKeDelete)))
                                                                                                                    .addComponent(chkThongKeCreate))
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                    .addComponent(jLabel8)
                                                                                                                    .addComponent(chkTaiKhoanDelete)))
                                                                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(chkTaiKhoanView)
                                                                                                                .addComponent(chkTaiKhoanUpdate))
                                                                                                            .addComponent(chkTaiKhoanCreate))
                                                                                                        .addGap(18, 18, 18)
                                                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                            .addComponent(jLabel9)
                                                                                                            .addComponent(chkNhanVienDelete)))
                                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(chkNhanVienView)
                                                                                                        .addComponent(chkNhanVienUpdate))
                                                                                                    .addComponent(chkNhanVienCreate))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addComponent(jLabel10)
                                                                                                    .addComponent(chkNhomQuyenDelete)))
                                                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(chkNhomQuyenView)
                                                                                                .addComponent(chkNhomQuyenUpdate))
                                                                                            .addComponent(chkNhomQuyenCreate))
                                                                                        .addGap(18, 18, 18)
                                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(jLabel11)
                                                                                            .addComponent(chkNhatKyDelete)))
                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(chkNhatKyView)
                                                                                        .addComponent(chkNhatKyUpdate))
                                                                                    .addComponent(chkNhatKyCreate))
                                                                                .addGap(18, 18, 18)
                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                    .addComponent(jLabel12)
                                                                                    .addComponent(chkSanPhamDelete)))
                                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(chkSanPhamView)
                                                                                .addComponent(chkSanPhamUpdate))
                                                                            .addComponent(chkSanPhamCreate))
                                                                        .addGap(18, 18, 18)
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(jLabel13)
                                                                            .addComponent(chkKhuVucKhoDelete)))
                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(chkKhuVucKhoView)
                                                                        .addComponent(chkKhuVucKhoUpdate))
                                                                    .addComponent(chkKhuVucKhoCreate))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel14)
                                                                    .addComponent(chkPhieuNhapDelete)))
                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(chkPhieuNhapView)
                                                                .addComponent(chkPhieuNhapUpdate))
                                                            .addComponent(chkPhieuNhapCreate))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel15)
                                                            .addComponent(chkPhieuXuatDelete)))
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(chkPhieuXuatView)
                                                        .addComponent(chkPhieuXuatUpdate))
                                                    .addComponent(chkPhieuXuatCreate))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel17)
                                                    .addComponent(chkDuyetPhieuDelete)))
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(chkDuyetPhieuView)
                                                .addComponent(chkDuyetPhieuUpdate))
                                            .addComponent(chkDuyetPhieuCreate))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addComponent(chkThuocTinhDelete)))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(chkThuocTinhView)
                                        .addComponent(chkThuocTinhUpdate))
                                    .addComponent(chkThuocTinhCreate))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(chkKhachHangDelete)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(chkKhachHangView)
                                .addComponent(chkKhachHangUpdate))
                            .addComponent(chkKhachHangCreate))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(chkNhaCungCapDelete)))))
                .addGap(15, 15, 15))
        );

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
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
                        .addComponent(btnThem))
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
                    .addComponent(btnThem)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        onSave();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnThem;
    private javax.swing.JCheckBox chkDuyetPhieuCreate;
    private javax.swing.JCheckBox chkDuyetPhieuDelete;
    private javax.swing.JCheckBox chkDuyetPhieuUpdate;
    private javax.swing.JCheckBox chkDuyetPhieuView;
    private javax.swing.JCheckBox chkKhachHangCreate;
    private javax.swing.JCheckBox chkKhachHangDelete;
    private javax.swing.JCheckBox chkKhachHangUpdate;
    private javax.swing.JCheckBox chkKhachHangView;
    private javax.swing.JCheckBox chkKhuVucKhoCreate;
    private javax.swing.JCheckBox chkKhuVucKhoDelete;
    private javax.swing.JCheckBox chkKhuVucKhoUpdate;
    private javax.swing.JCheckBox chkKhuVucKhoView;
    private javax.swing.JCheckBox chkNhaCungCapCreate;
    private javax.swing.JCheckBox chkNhaCungCapDelete;
    private javax.swing.JCheckBox chkNhaCungCapUpdate;
    private javax.swing.JCheckBox chkNhaCungCapView;
    private javax.swing.JCheckBox chkNhanVienCreate;
    private javax.swing.JCheckBox chkNhanVienDelete;
    private javax.swing.JCheckBox chkNhanVienUpdate;
    private javax.swing.JCheckBox chkNhanVienView;
    private javax.swing.JCheckBox chkNhatKyCreate;
    private javax.swing.JCheckBox chkNhatKyDelete;
    private javax.swing.JCheckBox chkNhatKyUpdate;
    private javax.swing.JCheckBox chkNhatKyView;
    private javax.swing.JCheckBox chkNhomQuyenCreate;
    private javax.swing.JCheckBox chkNhomQuyenDelete;
    private javax.swing.JCheckBox chkNhomQuyenUpdate;
    private javax.swing.JCheckBox chkNhomQuyenView;
    private javax.swing.JCheckBox chkPhieuNhapCreate;
    private javax.swing.JCheckBox chkPhieuNhapDelete;
    private javax.swing.JCheckBox chkPhieuNhapUpdate;
    private javax.swing.JCheckBox chkPhieuNhapView;
    private javax.swing.JCheckBox chkPhieuXuatCreate;
    private javax.swing.JCheckBox chkPhieuXuatDelete;
    private javax.swing.JCheckBox chkPhieuXuatUpdate;
    private javax.swing.JCheckBox chkPhieuXuatView;
    private javax.swing.JCheckBox chkSanPhamCreate;
    private javax.swing.JCheckBox chkSanPhamDelete;
    private javax.swing.JCheckBox chkSanPhamUpdate;
    private javax.swing.JCheckBox chkSanPhamView;
    private javax.swing.JCheckBox chkTaiKhoanCreate;
    private javax.swing.JCheckBox chkTaiKhoanDelete;
    private javax.swing.JCheckBox chkTaiKhoanUpdate;
    private javax.swing.JCheckBox chkTaiKhoanView;
    private javax.swing.JCheckBox chkThongKeCreate;
    private javax.swing.JCheckBox chkThongKeDelete;
    private javax.swing.JCheckBox chkThongKeUpdate;
    private javax.swing.JCheckBox chkThongKeView;
    private javax.swing.JCheckBox chkThuocTinhCreate;
    private javax.swing.JCheckBox chkThuocTinhDelete;
    private javax.swing.JCheckBox chkThuocTinhUpdate;
    private javax.swing.JCheckBox chkThuocTinhView;
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

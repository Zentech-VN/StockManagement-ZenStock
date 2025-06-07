package service;

import dao.ProductDAO;
import entity.Product;
import entity.ProductView;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import jdbc.ConnectionHelper;
import raven.toast.Notifications;

public class ProductServiceMain implements ProductDAO {

    private List<ProductView> productViewList = new ArrayList<>();
    private List<Product> product = new ArrayList<>();

    public List<Product> getAllProductViewService() {
        return product = getAllProduct();
    }

    public int getProductCountService() {
        return getProductCount();
    }

    public boolean addCheck(Product p) {

        if (p.getTenSanPham() == null || p.getTenSanPham().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tên sản phẩm không được để trống");
            return false;
        }

        if (p.getHinhAnh() == null || p.getHinhAnh().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Hình ảnh sản phẩm không được để trống");
            return false;
        }

        if (p.getChipXuLy() == null || p.getChipXuLy().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Chip xử lý không được để trống");
            return false;
        }

        if (p.getDungLuongPin() <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Dung lượng pin phải lớn hơn 0");
            return false;
        }

        if (p.getKichThuocManHinh() <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Kích thước màn hình phải lớn hơn 0");
            return false;
        }

        if (p.getCameraSau() == null || p.getCameraSau().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Thông tin camera sau không được để trống");
            return false;
        }

        if (p.getCameraTruoc() == null || p.getCameraTruoc().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Thông tin camera trước không được để trống");
            return false;
        }

        if (p.getThoiGianBaoHanh() < 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Thời gian bảo hành không hợp lệ");
            return false;
        }

        if (p.getSoLuongTon() < 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Số lượng tồn kho không được âm");
            return false;
        }

        if (p.getPhienBanHeDieuHanh() <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Phiên bản hệ điều hành không hợp lệ");
            return false;
        }

        if (p.getTrangThai() != 1 && p.getTrangThai() != 2 && p.getTrangThai() != 3) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Trạng thái không hợp lệ");
            return false;
        }
        if (p.getTenXuatXu() == null || p.getTenXuatXu().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Xuất xứ không được để trống");
            return false;
        }

        if (p.getTenHeDieuHanh() == null || p.getTenHeDieuHanh().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Hệ điều hành không được để trống");
            return false;
        }

        if (p.getTenThuongHieu() == null || p.getTenThuongHieu().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Thương hiệu không được để trống");
            return false;
        }

        if (p.getTenKhuVuc() == null || p.getTenKhuVuc().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Khu vực kho không được để trống");
            return false;
        }
        return true;
    }

    public boolean addProduct(Product p) {
        String sql = "INSERT INTO sanpham (tensp, hinhanh, xuatxu, chipxuly, dungluongpin, kichthuocman, hedieuhanh, phienbanhdh, camerasau, cameratruoc, thoigianbaohanh, thuonghieu, khuvuckho, soluongton, trangthai) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getTenSanPham());
            ps.setString(2, p.getHinhAnh());
            ps.setString(3, p.getTenXuatXu());
            ps.setString(4, p.getChipXuLy());
            ps.setInt(5, p.getDungLuongPin());
            ps.setDouble(6, p.getKichThuocManHinh());
            ps.setString(7, p.getTenHeDieuHanh());
            ps.setInt(8, p.getPhienBanHeDieuHanh());
            ps.setString(9, p.getCameraSau());
            ps.setString(10, p.getCameraTruoc());
            ps.setInt(11, p.getThoiGianBaoHanh());
            ps.setString(12, p.getTenThuongHieu());
            ps.setString(13, p.getTenKhuVuc());
            ps.setInt(14, p.getSoLuongTon());
            ps.setInt(15, p.getTrangThai());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(int maSanPham, String tenSanPham, String hinhAnh, String chipXuLy, int dungLuongPin, double kichThuocManHinh, String cameraSau, String cameraTruoc, int thoiGianBaoHanh, int soLuongTon, int phienBanHeDieuHanh, int trangThai, String tenXuatXu, String tenHeDieuHanh, String tenThuongHieu, String tenKhuVuc) {
        String sql = "UPDATE sanpham SET tensp = ?, hinhanh = ?, xuatxu = ?, chipxuly = ?, dungluongpin = ?, "
                + "kichthuocman = ?, hedieuhanh = ?, phienbanhdh = ?, camerasau = ?, cameratruoc = ?, thoigianbaohanh = ?, "
                + "thuonghieu = ?, khuvuckho = ?, soluongton = ?, trangthai = ? WHERE masp = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenSanPham);
            ps.setString(2, hinhAnh);
            ps.setString(3, tenXuatXu);
            ps.setString(4, chipXuLy);
            ps.setInt(5, dungLuongPin);
            ps.setDouble(6, kichThuocManHinh);
            ps.setString(7, tenHeDieuHanh);
            ps.setInt(8, phienBanHeDieuHanh);
            ps.setString(9, cameraSau);
            ps.setString(10, cameraTruoc);
            ps.setInt(11, thoiGianBaoHanh);
            ps.setString(12, tenThuongHieu);
            ps.setString(13, tenKhuVuc);
            ps.setInt(14, soLuongTon);
            ps.setInt(15, trangThai);
            ps.setInt(16, maSanPham);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi khi sửa sản phẩm");
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(Product p) {

        if (p.getSoLuongTon() < 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Số lượng tồn kho không được âm");
            return false;
        }

        if (p.getPhienBanHeDieuHanh() != 0 && p.getPhienBanHeDieuHanh() != 1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Phiên bản hệ điều hành không hợp lệ");
            return false;
        }

        if (p.getTrangThai() != 0 && p.getTrangThai() != 1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Trạng thái không hợp lệ");
            return false;
        }

        if (p.getTenXuatXu() == null || p.getTenXuatXu().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Xuất xứ không được để trống");
            return false;
        }

        if (p.getTenThuongHieu() == null || p.getTenThuongHieu().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Thương hiệu không được để trống");
            return false;
        }

        if (p.getTenKhuVuc() == null || p.getTenKhuVuc().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Khu vực kho không được để trống");
            return false;
        }

        return updateProduct(
                p.getMaSanPham(),
                p.getTenSanPham(),
                p.getHinhAnh(),
                p.getChipXuLy(),
                p.getDungLuongPin(),
                p.getKichThuocManHinh(),
                p.getCameraSau(),
                p.getCameraTruoc(),
                p.getThoiGianBaoHanh(),
                p.getSoLuongTon(),
                p.getPhienBanHeDieuHanh(),
                p.getTrangThai(),
                p.getTenXuatXu(),
                p.getTenHeDieuHanh(),
                p.getTenThuongHieu(),
                p.getTenKhuVuc()
        );
    }

    public boolean deleteProductById(int maSanPham) {
        if (deleteProduct(maSanPham)) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xoá sản phẩm thành công");
            return true;
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Xoá sản phẩm thất bại");
            return false;
        }
    }

    public int getproductCountService() {
        return getProductCount();
    }
    
     public void Find(JTable table, JTextField search) {
        DefaultTableModel ob = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        table.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(search.getText()));
    }
}

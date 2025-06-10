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

        if (p.getDungLuongPin() == null || p.getDungLuongPin().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Dung lượng pin không được để trống");
            return false;
        }

        if (p.getKichThuocManHinh() == null || p.getKichThuocManHinh().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Kích thước màn hình không được để trống");
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

        if (p.getThoiGianBaoHanh() == null || p.getThoiGianBaoHanh().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Thời gian bảo hành không được để trống");
            return false;
        }

        if (p.getThongSo() <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Thông số phải lớn hơn 0");
            return false;
        }

        if (p.getGia() == null || p.getGia().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Giá sản phẩm phải lớn hơn 0");
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
        // Sử dụng method từ ProductDAO interface
        return addProduct(
                p.getTenSanPham(),
                p.getHinhAnh(),
                p.getChipXuLy(),
                p.getDungLuongPin(),
                p.getKichThuocManHinh(),
                p.getCameraSau(),
                p.getCameraTruoc(),
                p.getThoiGianBaoHanh(),
                p.getThongSo(),
                p.getGia(),
                p.getTrangThai(),
                1, // maxuatxu - cần mapping từ tên
                1, // mahedieuchanh - cần mapping từ tên
                1  // mathuonghieu - cần mapping từ tên
        );
    }


    public boolean updateProduct(Product p) {

        if (p.getThongSo() <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Thông số phải lớn hơn 0");
            return false;
        }

        if (p.getGia() == null || p.getGia().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Giá sản phẩm phải lớn hơn 0");
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
                p.getThongSo(),
                p.getGia(),
                p.getTrangThai(),
                1, // maxuatxu - cần mapping từ tên
                1, // mahedieuchanh - cần mapping từ tên
                1  // mathuonghieu - cần mapping từ tên
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

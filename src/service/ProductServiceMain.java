package service;

import dao.ProductDAO;
import entity.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import raven.toast.Notifications;

public class ProductServiceMain implements ProductDAO {

    private List<Product> product = new ArrayList<>();

    public int getTotalPages(int pageSize) {
        int totalProducts = getProductCount();
        return (int) Math.ceil((double) totalProducts / pageSize);
    }
    
    public List<Product> getProductsByPage(int page, int pageSize) {
        return getAllProduct(page, pageSize);
    }

    public List<Product> getBasicProductService(int page, int pageSize) {
        return product = getBasicProduct(page, pageSize);
    }

    public int getProductCountService() {
        return getProductCount();
    }

    public boolean addCheck(String tenSanPham, String hinhAnh, String cameraTruoc, String cameraSau, String giaText, String chip, String pin, String manHinh, int baoHanh, int maThuongHieu, int maHeDieuHanh, int maXuatXu, String trangThai, int maKhuVucKho, String soLuongText) {

        if (tenSanPham.length() <= 0 || tenSanPham.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tên sản phẩm không được trống");
            return false;
        }

        if (maThuongHieu <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Thương hiệu không được trống");
            return false;
        }

        if (maXuatXu <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Xuất xứ không được trống");
            return false;
        }

        if (maHeDieuHanh <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Hệ điều hành không được trống");
            return false;
        }

        BigDecimal gia;
        try {
            gia = new BigDecimal(giaText);
            if (gia.compareTo(BigDecimal.ZERO) <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Giá không hợp lệ");
            return false;
        }

        if (giaText.length() <= 0 || giaText.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Giá không được trống");
            return false;
        }

        if (baoHanh <=0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Bảo hành không được trống");
            return false;
        }

        int soLuong;
        try {
            soLuong = Integer.parseInt(soLuongText.trim());
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Số lượng không hợp lệ");
            return false;
        }
        
        if (addProduct(tenSanPham, gia, hinhAnh, cameraTruoc, cameraSau, chip, pin, manHinh, baoHanh, maThuongHieu, maHeDieuHanh, maXuatXu, trangThai, maKhuVucKho, soLuong)) {
            return true;
        }
        
        return false;
    }

    public boolean updateCheck(String maSanPhamText, String tenSanPham, String hinhAnh, String cameraTruoc, String cameraSau, String giaText, String chip, String pin, String manHinh, String baoHanhText, int maThuongHieu, int maHeDieuHanh, int maXuatXu, String trangThai) {

        int maSanPham;
        try {
            maSanPham = Integer.parseInt(maSanPhamText.trim());
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Mã sản phẩm phải là số nguyên");
            return false;
        }

        if (maSanPham <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Mã sản phẩm phải lớn hơn 0");
            return false;
        }

        if (tenSanPham == null || tenSanPham.trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tên sản phẩm không được trống");
            return false;
        }

        if (maThuongHieu <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Thương hiệu không được trống");
            return false;
        }

        if (maXuatXu <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Xuất xứ không được trống");
            return false;
        }

        if (maHeDieuHanh <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Hệ điều hành không được trống");
            return false;
        }

        if (giaText == null || giaText.trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Giá không được trống");
            return false;
        }

        BigDecimal gia;
        try {
            gia = new BigDecimal(giaText.trim());
            if (gia.compareTo(BigDecimal.ZERO) <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Giá không hợp lệ");
            return false;
        }

        if (baoHanhText.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng nhập thời gian bảo hành");
            return false;
        }

        int baoHanh;
        try {
            baoHanh = Integer.parseInt(baoHanhText);
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Thời gian bảo hành phải là số");
            return false;
        }

        if (updateProduct(maSanPham, tenSanPham, gia, hinhAnh, cameraTruoc, cameraSau, chip, pin, manHinh, baoHanh, maThuongHieu, maHeDieuHanh, maXuatXu, trangThai)) {
            return true;
        }

        return false;
    }

    public boolean deleteProductService(int maSanPham) {
        if (deleteProduct(maSanPham)) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xoá sản phẩm thành công");
            return true;
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Xoá sản phẩm thất bại");
            return false;
        }
    }

    public List<Product> searchProducts(String keyword) {
        return searchProductsProc(keyword);
    }

}

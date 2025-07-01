package service;

import dao.ProductDetailsDAO;
import entity.Product;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ProductDetailsService {

    private ProductDetailsDAO dao = new ProductDetailsDAO();

    public List<Product> getAllProductDetails() {
        return dao.getAllProductWithImei();
    }

    public boolean addCheck(String tenSanPham, String hinhAnh, String camTruoc, String camSau, String giaText,
                            String chip, String pin, String manHinh, int baoHanh,
                            int maThuongHieu, int maHeDieuHanh, int maXuatXu, String trangThai) {
        try {
            BigDecimal gia = new BigDecimal(giaText);

            Product product = new Product();
            product.setTenSanPham(tenSanPham);
            product.setHinhAnh(hinhAnh);
            product.setCameraTruoc(camTruoc);
            product.setCameraSau(camSau);
            product.setGia(gia);
            product.setChipXuLy(chip);
            product.setDungLuongPin(pin);
            product.setKichThuocManHinh(manHinh);
            product.setThoiGianBaoHanh(String.valueOf(baoHanh));
            product.setMaThuongHieu(maThuongHieu);
            product.setMaHeDieuHanh(maHeDieuHanh);
            product.setMaXuatXu(maXuatXu);
            product.setTrangThai(trangThai);

            return dao.insertProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addCheck(Product product) {
        try {
            return dao.insertProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
        public void Find(JTable table, JTextField search) {
        DefaultTableModel ob = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        table.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(search.getText()));
    }
}
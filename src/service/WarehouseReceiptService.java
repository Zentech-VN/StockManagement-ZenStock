package service;

import dao.WarehouseReceiptDAO;
import entity.PhieuNhap;
import entity.ProductArea;
import entity.Supplier;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WarehouseReceiptService {

    WarehouseReceiptDAO wrd = new WarehouseReceiptDAO();

    public String settrangthai(String trangthai) {
        if (trangthai.equalsIgnoreCase("choduyet")) {
            return "Chờ duyệt";
        } else if (trangthai.equalsIgnoreCase("duyet")) {
            return "Duyệt";
        } else {
            return "Hủy";
        }
    }

    public void loadDataTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (PhieuNhap p : wrd.getAllentries()) {
            model.addRow(new Object[]{p.getMaphieunhap(), p.getS().getMaNhaCungCap(), p.getE().getManv(), p.getNgaytao(), settrangthai(p.getTrangthai())});
        }
    }

    public void loadDataCbo1(JComboBox nhacungcap) {
        nhacungcap.removeAllItems();
        for (Supplier s : wrd.getAllNhaCungCap()) {
            nhacungcap.addItem(s.getTenNhaCungCap());
        }
    }

    public void loadDataTable1(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (ProductArea pa : wrd.GetProducArea()) {
            model.addRow(new Object[]{pa.getP().getTenSanPham(), pa.getP().getGia(), pa.getW().getTenKhuVuc(), pa.getSoluong()});
        }
    }
}

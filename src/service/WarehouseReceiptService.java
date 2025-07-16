package service;

import dao.WarehouseReceiptDAO;
import entity.PhieuNhap;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WarehouseReceiptService {

    WarehouseReceiptDAO wrd = new WarehouseReceiptDAO();

    public String settrangthai(String trangthai) {
        if (trangthai.equalsIgnoreCase("choduyet")) {
            return "Chờ duyệt";
        }else if(trangthai.equalsIgnoreCase("duyet")){
            return "Duyệt";
        }else{
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
}

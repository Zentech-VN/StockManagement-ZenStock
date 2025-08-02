package service;

import dao.WarehouseReceiptDAO;
import entity.PhieuNhap;
import entity.ProductArea;
import entity.Supplier;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

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
        SwingWorker<List<PhieuNhap>, Void> worker = new SwingWorker<List<PhieuNhap>, Void>() {
            @Override
            protected List<PhieuNhap> doInBackground() throws Exception {
                return wrd.getAllentries();
            }

            @Override
            protected void done() {
                try {
                    List<PhieuNhap> list = get();
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0);

                    for (PhieuNhap p : list) {
                        model.addRow(new Object[]{
                            p.getMaphieunhap(),
                            p.getS().getTenNhaCungCap(),
                            p.getE().getHoten(),
                            p.getNgaytao(),
                            settrangthai(p.getTrangthai())
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(table, "Lỗi khi load dữ liệu: " + e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void loadDataCbo1(JComboBox<String> nhacungcap) {
        SwingWorker<List<Supplier>, Void> worker = new SwingWorker<List<Supplier>, Void>() {
            @Override
            protected List<Supplier> doInBackground() throws Exception {
                return wrd.getAllNhaCungCap();
            }

            @Override
            protected void done() {
                try {
                    List<Supplier> list = get();
                    nhacungcap.removeAllItems();
                    for (Supplier s : list) {
                        nhacungcap.addItem(s.getTenNhaCungCap());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(nhacungcap, "Lỗi khi load nhà cung cấp: " + e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void loadDataTable1(JTable table) {
        SwingWorker<List<ProductArea>, Void> worker = new SwingWorker<List<ProductArea>, Void>() {
            @Override
            protected List<ProductArea> doInBackground() throws Exception {
                return wrd.GetProducArea();
            }

            @Override
            protected void done() {
                try {
                    List<ProductArea> list = get();
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0);

                    for (ProductArea pa : list) {
                        model.addRow(new Object[]{
                            pa.getP().getMaSanPham(),
                            pa.getP().getTenSanPham(),
                            pa.getP().getGia(),
                            pa.getW().getMaKhuVuc(),
                            pa.getSoluong()
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(table, "Lỗi khi load sản phẩm - khu vực: " + e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void search(JTable table, JTextField search) {
        DefaultTableModel ob = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        table.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(search.getText()));
    }
}

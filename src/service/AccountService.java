package service;

import dao.AccountDAO;
import dao.PermGroupDAO;
import java.util.ArrayList;
import java.util.List;
import entity.Account;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import entity.PermGroup;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class AccountService {

    private AccountDAO acc = new AccountDAO();
    static ArrayList<Account> lista;
    private ArrayList<PermGroup> listnq;
    private PermGroupDAO permGroupDAO = PermGroupDAO.getInstance();

    public AccountService() {
        this.lista = AccountDAO.getInstance().selectAll();
        this.listnq = PermGroupDAO.getInstance().selectAll();
    }

    public void updateAcc(int rowIndex, Account updatedAccount) {
        if (rowIndex >= 0 && rowIndex < lista.size()) {
            lista.set(rowIndex, updatedAccount);
        }
    }
    
    public List<Account> getAccountsPaged(int pageIndex, int pageSize) {
        return acc.selectPaged(pageIndex, pageSize);
    }
    
    public PermGroup getPermGroup(int manhom) {
        return permGroupDAO.selectById(manhom + "");
    }

    public ArrayList<Account> getTaiKhoanAll() {
        //luôn lấy dữ liệu mới từ DB
        lista = AccountDAO.getInstance().selectAll();
        return lista;
    }

    public void Search(JTextField txtSearch, JTable tblList) {
        DefaultTableModel model = (DefaultTableModel) tblList.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tblList.setRowSorter(sorter);

        String searchText = txtSearch.getText().trim();
        if (searchText.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }
    }

    private void addRowToModel(DefaultTableModel model, Account account) {
        String trangthaiString = getTrangThaiString(account.getTrangthai());

        model.addRow(new Object[]{
            account.getManv(),
            account.getUsername(),
            getPermGroup(account.getManhomquyen()).getTennhomquyen(),
            trangthaiString
        });
    }

    private String getTrangThaiString(int tt) {
        switch (tt) {
            case 1:
                return "Hoạt động";
            case 0:
                return "Ngưng hoạt động";
            default:
                return "Không xác định";
        }
    }

    public int getAccountCountService() {
        return AccountDAO.getAccountCount();
    }
}

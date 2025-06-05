package service;

import dao.AccountDAO;
import dao.PermGroupDAO;
import java.util.ArrayList;
import java.util.List;
import entity.Account;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import entity.PermGroup;

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

    public void LoadTable(List<Account> listc, JTable jTable1) {
        lista = acc.selectAll();
        String[] title = {"MaNV", "Tên đăng nhập", "Nhóm quyền", "Trạng thái"};
        DefaultTableModel model = new DefaultTableModel(title, 0);

        for (Account account : lista) {
            int tt = account.getTrangthai();
            String trangthaiString = "";
            switch (tt) {
                case 1:
                    trangthaiString = "Hoạt động";
                    break;
                case 0:
                    trangthaiString = "Ngưng hoạt động";
                    break;
                default:
                    trangthaiString = "Không xác định";
                    break;
            }
            model.addRow(new Object[]{
                account.getManv(), account.getUsername(), getPermGroup(account.getManhomquyen()).getTennhomquyen(), trangthaiString
            });
        }
        jTable1.setModel(model);
    }

    public PermGroup getPermGroup(int manhom) {
        return permGroupDAO.selectById(manhom + "");
    }

    public ArrayList<Account> getTaiKhoanAll() {
        //luôn lấy dữ liệu mới từ DB
        lista = AccountDAO.getInstance().selectAll();
        return lista;
    }

    public void LoadTableWithSearch(String searchText, JTable jTable1) {
        lista = acc.selectAll();
        String[] title = {"MaNV", "Tên đăng nhập", "Nhóm quyền", "Trạng thái"};
        DefaultTableModel model = new DefaultTableModel(title, 0);

        // Nếu không có từ khóa tìm kiếm, hiển thị tất cả
        if (searchText == null || searchText.trim().isEmpty()) {
            for (Account account : lista) {
                addRowToModel(model, account);
            }
        } else {
            //lọc danh sách theo từ khóa tìm kiếm
            String searchLower = searchText.trim().toLowerCase();

            for (Account account : lista) {
                boolean match = false;
                if (String.valueOf(account.getManv()).contains(searchLower)) {
                    match = true;
                }
                if (account.getUsername() != null
                        && account.getUsername().toLowerCase().contains(searchLower)) {
                    match = true;
                }
                try {
                    PermGroup permGroup = getPermGroup(account.getManhomquyen());
                    if (permGroup != null && permGroup.getTennhomquyen() != null
                            && permGroup.getTennhomquyen().toLowerCase().contains(searchLower)) {
                        match = true;
                    }
                } catch (Exception e) {

                }
                String trangthaiString = getTrangThaiString(account.getTrangthai());
                if (trangthaiString.toLowerCase().contains(searchLower)) {
                    match = true;
                }
                if (match) {
                    addRowToModel(model, account);
                }
            }
        }

        jTable1.setModel(model);
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.AccountDAO;
import dao.PermGroupDAO;
import java.util.ArrayList;
import java.util.List;
import entity.Account;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import entity.PermGroup;

/**
 *
 * @author Duc Pham Ngoc
 */
public class AccountService {
    private AccountDAO acc = new AccountDAO();
    static ArrayList<Account> lista;
    private ArrayList<PermGroup> listnq;
    private PermGroupDAO permGroupDAO = PermGroupDAO.getInstance();
    
    public AccountService(){
        this.lista  = AccountDAO.getInstance().selectAll();
        this.listnq = PermGroupDAO.getInstance().selectAll();
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
                account.getManv(), account.getUsername(), getNhomQuyenDTO(account.getManhomquyen()).getTennhomquyen(), trangthaiString
            });
        }
        jTable1.setModel(model);
    }
     
    public PermGroup getNhomQuyenDTO(int manhom){
        return permGroupDAO.selectById(manhom+"");
    }
}

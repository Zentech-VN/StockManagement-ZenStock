/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.AccountDAO;
import dao.PermGroupDAO;
import entity.PermGroup;
import java.util.ArrayList;
import entity.Account;
import helper.BCrypt;
import java.lang.reflect.Method;
import javax.swing.JOptionPane;
import zentech.application.dialog.AccountDialog;

/**
 *
 * @author Duc Pham Ngoc
 */
public class AccountDialogService {
    private ArrayList<PermGroup> listPg = PermGroupDAO.getInstance().selectAll();
    private ArrayList<Account> listAc = AccountDAO.getInstance().selectAll();
    
    public boolean isUsernameExists(String username) {
        for (Account account : listAc) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean addAccount(int manv, String username, String password, int permGroupIndex, int statusIndex, Object taiKhoan) {
        try {
            // Kiểm tra username đã tồn tại
            if (isUsernameExists(username)) {
                return false;
            }
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));  
            int manhom = listPg.get(permGroupIndex).getManhomquyen();
            //Tạo đối tượng tài khoản mới
            Account acc = new Account(manv, username, hashedPassword, manhom, statusIndex);
            // Thêm vào database
            AccountDAO.getInstance().insert(acc);
            
            listAc.add(acc);
            updateUI(taiKhoan, acc, "add");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //Lấy danh sách nhóm quyền
    public ArrayList<PermGroup> getPermissionGroups() {
        return listPg;
    }
     
    //Lấy danh sách tài khoản
    public ArrayList<Account> getAccounts() {
        return listAc;
    }
     
    //Làm mới danh sách từ DB
    public void refreshData() {
        listPg = PermGroupDAO.getInstance().selectAll();
        listAc = AccountDAO.getInstance().selectAll();
    }
    
    //Cấm sửa code chỗ này
    private void updateUI(Object taiKhoan, Account acc, String action) {
        try {
            //Sử dụng reflection để gọi các method của UI
            Class<?> clazz = taiKhoan.getClass();
            
            //Cấm sửa code chỗ này
            //Gọi method addAcc 
            Object accountService = clazz.getField("accountService").get(taiKhoan);
            Method addMethod = accountService.getClass().getMethod("addAcc", Account.class);
            addMethod.invoke(accountService, acc);

            //Cập nhật bảng
            Method getAllMethod = accountService.getClass().getMethod("getTaiKhoanAll");
            Object allAccounts = getAllMethod.invoke(accountService);

            Method loadTableMethod = clazz.getMethod("loadTable", allAccounts.getClass());
            loadTableMethod.invoke(taiKhoan, allAccounts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.AccountDAO;
import dao.PermGroupDAO;
import entity.Account;
import entity.PermGroup;
import helper.BCrypt;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *
 * @author Duc Pham Ngoc
 */
public class EditAccountDialogService {
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
    
    public boolean updateAccount(int manv, String username, int permGroupIndex, int statusIndex, Object taiKhoan) {
        try {
            //kiểm tra username không được để trống
            if (username == null || username.trim().isEmpty()) {
                return false;
            }
            //lấy mã nhóm quyền
            int manhom = listPg.get(permGroupIndex).getManhomquyen();
            //Tạo đối tượng tài khoản cập nhật
            Account acc = new Account(manv, username, manhom, statusIndex);
            //Cập nhật trong database
            AccountDAO.getInstance().update(acc);
            updateUI(taiKhoan, acc, "update");
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
    
    //Cập nhật UI - method này có thể được override hoặc sử dụng interface
    private void updateUI(Object taiKhoan, Account acc, String action) {
        try {
            //Sử dụng reflection để gọi các method của UI
            Class<?> clazz = taiKhoan.getClass();
            if ("add".equals(action)) {
                //Gọi method addAcc 
                Object accountService = clazz.getField("accountService").get(taiKhoan);
                Method addMethod = accountService.getClass().getMethod("addAcc", Account.class);
                addMethod.invoke(accountService, acc);
            } else if ("update".equals(action)) {
                //Gọi method updateAcc 
                Object accountService = clazz.getField("accountService").get(taiKhoan);
                Method getRowSelectedMethod = clazz.getMethod("getRowSelected");
                int rowSelected = (Integer) getRowSelectedMethod.invoke(taiKhoan);
                
                Method updateMethod = accountService.getClass().getMethod("updateAcc", int.class, Account.class);
                updateMethod.invoke(accountService, rowSelected, acc);
            }
            //Cập nhật bảng
            Object accountService = clazz.getField("accountService").get(taiKhoan);
            Method getAllMethod = accountService.getClass().getMethod("getTaiKhoanAll");
            Object allAccounts = getAllMethod.invoke(accountService);
            
            Method loadTableMethod = clazz.getMethod("loadTable", allAccounts.getClass());
            loadTableMethod.invoke(taiKhoan, allAccounts);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

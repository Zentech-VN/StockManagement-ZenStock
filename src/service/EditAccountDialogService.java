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
            if (username == null || username.trim().isEmpty()) {
                return false;
            }
            
            for(Account account : listAc){
                if(account.getUsername().equals(username)&& account.getManv() != manv){
                    return false;
                }
            }
            //lấy mã nhóm quyền
            int manhom = listPg.get(permGroupIndex).getManhomquyen();
            //Tạo đối tượng tài khoản cập nhật
            Account acc = new Account(manv, username, manhom, statusIndex);
            
            AccountDAO.getInstance().update(acc);
            refreshData();
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
    
    //Cấm sửa code chỗ này
    private void updateUI(Object taiKhoan, Account acc, String action) {
        //Sử dụng reflection để gọi các method của UI
        
        try {
            Class<?> clazz = taiKhoan.getClass();
            //Gọi method updateAcc 
            //Cấm sửa code chỗ này
            Object accountService = clazz.getField("accountService").get(taiKhoan);
            Method getRowSelectedMethod = clazz.getMethod("getRowSelected");
            int rowSelected = (Integer) getRowSelectedMethod.invoke(taiKhoan);

            Method updateMethod = accountService.getClass().getMethod("updateAcc", int.class, Account.class);
            updateMethod.invoke(accountService, rowSelected, acc);
            
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.StaffDAO;
import entity.Staff;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static service.AccountService.lista;

/**
 *
 * @author Duc Pham Ngoc
 */
public class StaffListDialogService {
    private ArrayList<Staff> lists = StaffDAO.getInstance().selectAllNV();
    
    public void loadTable(ArrayList<Staff> list, JTable jTable1) {
        String[] title = {"MaNV", "Họ tên", "Giới tính", "Ngày sinh", "SĐT", "Email"};
        DefaultTableModel model = new DefaultTableModel(title, 0);
        lists = list;
        for (Staff staff : lists) {
            model.addRow(new Object[]{
                staff.getManv(),staff.getHoten(),staff.getGioitinh()==1?"Nam":"Nữ",staff.getNgaysinh(),staff.getSdt(),staff.getEmail()
            });
        }
        jTable1.setModel(model);
    }
    
    public ArrayList<Staff> search(String text) {
        if(text.length()>0){
            text = text.toLowerCase();
        ArrayList<Staff> result = new ArrayList<>();
        //System.out.println(text);
        for(Staff i : lists) {
           if(i.getHoten().toLowerCase().contains(text) || i.getEmail().toLowerCase().contains(text)
                   || i.getSdt().toLowerCase().contains(text)){
               result.add(i);
           }
        }
        return result;
        } else {
            return StaffDAO.getInstance().selectAllNV();
        }
        
    }
}

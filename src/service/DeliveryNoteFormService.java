/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.ClientDAO;
import dao.ProductAreaDAO;
import entity.Client;
import entity.ProductArea;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DeliveryNoteFormService {

    ProductAreaDAO pad = new ProductAreaDAO();
    ClientDAO cd = new ClientDAO();

//    public void LoadDataTable(JTable table) {
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        model.setRowCount(0);
//        for (ProductArea pa : pad.getProduct()) {
//            if (pa.getPd().getTrangthai().equalsIgnoreCase("trongkho")) {
//                model.addRow(new Object[]{pa.getMaimei(), pa.getPd().getP().getTenSanPham(), pa.getSoluong(), pa.getPd().getP().getGia()});
//            }
//        }
//    }

//    public void loadDataCombobox(JComboBox<String> client) {
//        client.removeAllItems();
//        for (Client c : cd.getAllCilent()) {
//            if (c.getTrangThai() == 1) {
//                client.addItem(c.getTenKhacHang());
//            }
//        }
//    }
}

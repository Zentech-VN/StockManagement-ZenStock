/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.ReceiptDAO;
import entity.Receipt;
import entity.ReceiptDetails;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author PC
 */
public class ReceiptService {

    static ArrayList<Receipt> listr;
    private ReceiptDAO receiptDAO = new ReceiptDAO();

    public List<Receipt> getAllPendingReceipts() {
        try {
            List<Receipt> receipts = receiptDAO.getPendingReceipts();
            return receipts;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void loadPendingReceipts(JTable jTable1) {
        try {
            List<Receipt> receipts = getAllPendingReceipts();

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            for (Receipt receipt : receipts) {
                model.addRow(new Object[]{
                    receipt.getReceiptId(),
                    receipt.getReceiptType(),
                    receipt.getCreatedBy(),
                    receipt.getTimestamp(),
                    receipt.getTotalAmount()
                });
            }

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tải danh sách phiếu chờ duyệt: " + e.getMessage(), e);
        }
    }
    public boolean processReceiptApproval(int receiptId, String receiptCategory) {
        try {
            boolean result = receiptDAO.approveReceipt(receiptId, receiptCategory);
            return result;

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi duyệt phiếu: " + e.getMessage(), e);
        }
    }


    public boolean processReceiptRejection(int receiptId, String receiptCategory) {
        try {
            boolean result = receiptDAO.rejectReceipt(receiptId, receiptCategory);
            return result;

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi hủy phiếu: " + e.getMessage(), e);
        }
    }

    public List<ReceiptDetails> getReceiptDetailsForInspection(int receiptId, String receiptCategory) {
        try {
            List<ReceiptDetails> details = receiptDAO.getReceiptDetails(receiptId, receiptCategory);
            return details;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    public void searchReceipts(JTable jTable1, JTextField txtSearch) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);

        String searchText = txtSearch.getText().trim();
        if (searchText.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }
    }
    
    public List<Receipt> searchReceiptsByDateRange(LocalDate startDate, LocalDate endDate) {
        try {
            List<Receipt> receipts = receiptDAO.getPendingReceiptsByDateRange(startDate, endDate);
            return receipts;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean approveReceipt(int receiptId, String receiptCategory) {
        try {
            boolean result = receiptDAO.approveReceipt(receiptId, receiptCategory);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean rejectReceipt(int receiptId, String receiptCategory) {
        try {
            boolean result = receiptDAO.rejectReceipt(receiptId, receiptCategory);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Receipt getReceiptSummary(int receiptId, String receiptCategory) {
        try {
            Receipt summary = receiptDAO.getReceiptSummary(receiptId, receiptCategory);         
            return summary;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
  
  
}

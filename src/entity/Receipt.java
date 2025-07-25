/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class Receipt {
    private String receiptType;
    private String createdBy;
    private LocalDate timestamp;
    private BigDecimal totalAmount;
    private int receiptId;
    private String receiptCategory; 
    
    public Receipt() {}
    
    public Receipt(String receiptType, String createdBy, LocalDate timestamp, BigDecimal totalAmount, int receiptId, String receiptCategory) {
        this.receiptType = receiptType;
        this.createdBy = createdBy;
        this.timestamp = timestamp;
        this.totalAmount = totalAmount;
        this.receiptId = receiptId;
        this.receiptCategory = receiptCategory;
    }
    
    public String getReceiptType() { 
        return receiptType; 
    }
    
    public void setReceiptType(String receiptType) { 
        this.receiptType = receiptType; 
    }
    
    public String getCreatedBy() { 
        return createdBy; 
    }
    
    public void setCreatedBy(String createdBy) { 
        this.createdBy = createdBy; 
    }
    
    public LocalDate getTimestamp() { 
        return timestamp; 
    }
    
    public void setTimestamp(LocalDate timestamp) { 
        this.timestamp = timestamp; 
    }
    
    public BigDecimal getTotalAmount() { 
        return totalAmount; 
    }
    
    public void setTotalAmount(BigDecimal totalAmount) { 
        this.totalAmount = totalAmount; 
    }
    
    public int getReceiptId() { 
        return receiptId; 
    }
    
    public void setReceiptId(int receiptId) { 
        this.receiptId = receiptId; 
    }
    
    public String getReceiptCategory() { 
        return receiptCategory; 
    }
    public void setReceiptCategory(String receiptCategory) { 
        this.receiptCategory = receiptCategory; 
    }
}
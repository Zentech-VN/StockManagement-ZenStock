/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.math.BigDecimal;

/**
 *
 * @author PC
 */
public class ReceiptDetails {
    private String productName;
    private Double price;
    private String warehouseCode;
    private int quantity;
    private String note;
    
    public ReceiptDetails() {}
    
    public ReceiptDetails(String productName, Double price, String warehouseCode, int quantity, String note) {
        this.productName = productName;
        this.price = price;
        this.warehouseCode = warehouseCode;
        this.quantity = quantity;
        this.note = note;
    }
    
    public String getProductName() { 
        return productName; 
    }
    
    public void setProductName(String productName) { 
        this.productName = productName; 
    }
    
    public Double getPrice() { 
        return price; 
    }
    
    public void setPrice(Double price) { 
        this.price = price; 
    }
    
    public String getWarehouseCode() { 
        return warehouseCode; 
    }
    
    public void setWarehouseCode(String warehouseCode) { 
        this.warehouseCode = warehouseCode; 
    }
    
    public int getQuantity() { 
        return quantity; 
    }
    
    public void setQuantity(int quantity) { 
        this.quantity = quantity; 
    }
    
    public String getNote() { 
        return note; 
    }
    
    public void setNote(String note) { 
        this.note = note; 
    }
}

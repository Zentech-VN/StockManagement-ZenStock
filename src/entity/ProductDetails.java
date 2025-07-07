/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author RGB
 */
public class ProductDetails {
    
    private int maimei;
    private int idCustomer;
    private int idProduct;
    private float quality;
    private String status;
    private String note;

    public ProductDetails() {
    }

    public ProductDetails(int maimei, int idCustomer, int idProduct, float quality, String status, String note) {
        this.maimei = maimei;
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
        this.quality = quality;
        this.status = status;
        this.note = note;
    }

    public int getMaimei() {
        return maimei;
    }

    public void setMaimei(int maimei) {
        this.maimei = maimei;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public float getQuality() {
        return quality;
    }

    public void setQuality(float quality) {
        this.quality = quality;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
    
}

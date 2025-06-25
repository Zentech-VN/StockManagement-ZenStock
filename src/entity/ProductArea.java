package entity;

public class ProductArea {

    private int makhuvuc;
    private int masanpham;
    private int soluong;
    private WarehouseManagement w = new WarehouseManagement();
    private Product p = new Product();

    public ProductArea() {
    }

    public ProductArea(int makhuvuc, int masanpham, int soluong) {
        this.makhuvuc = makhuvuc;
        this.masanpham = masanpham;
        this.soluong = soluong;
    }

    public int getMakhuvuc() {
        return makhuvuc;
    }

    public void setMakhuvuc(int makhuvuc) {
        this.makhuvuc = makhuvuc;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public WarehouseManagement getW() {
        return w;
    }

    public void setW(WarehouseManagement p) {
        this.w = p;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

}

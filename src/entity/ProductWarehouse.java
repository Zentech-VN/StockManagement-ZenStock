package entity;

public class ProductWarehouse {
    private int makhuvuc;
    private int masanpham;

    public ProductWarehouse() {
    }

    public ProductWarehouse(int makhuvuc, int masanpham) {
        this.makhuvuc = makhuvuc;
        this.masanpham = masanpham;
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
}

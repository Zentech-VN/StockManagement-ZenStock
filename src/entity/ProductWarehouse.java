package entity;

public class ProductWarehouse {
    private int makhuvuc;
    private int masanpham;
    private int soluong;

    public ProductWarehouse() {
    }

    public ProductWarehouse(int makhuvuc, int masanpham, int soluong) {
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
}

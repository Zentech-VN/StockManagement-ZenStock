package entity;

public class ProductArea {

    private int maimei;

    private int soluong;
    private Warehouse w = new Warehouse();
    private Product p = new Product();

    public ProductArea() {
    }

    public ProductArea(int maimei, int soluong) {
        this.maimei = maimei;

        this.soluong = soluong;
    }

    public int getMaimei() {
        return maimei;
    }

    public void setMaimei(int maimei) {
        this.maimei = maimei;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Warehouse getW() {
        return w;
    }

    public void setW(Warehouse p) {
        this.w = p;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    

}

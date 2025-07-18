package entity;

public class ProductArea {

    private Warehouse w = new Warehouse();
    private Product p = new Product();
    private int soluong;

    public ProductArea() {
    }

    public ProductArea(int soluong) {

        this.soluong = soluong;
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

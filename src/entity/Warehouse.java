package entity;

public class Warehouse {

    private int maKhuVuc;
    private String tenKhuVuc;

    public Warehouse() {
    }

    public Warehouse(int maKhuVuc, String tenKhuVuc) {
        this.maKhuVuc = maKhuVuc;
        this.tenKhuVuc = tenKhuVuc;

    }

    public Warehouse(String tenKhuVuc) {
        this.tenKhuVuc = tenKhuVuc;
    }

    public int getMaKhuVuc() {
        return maKhuVuc;
    }

    public void setMaKhuVuc(int maKhuVuc) {
        this.maKhuVuc = maKhuVuc;
    }

    public String getTenKhuVuc() {
        return tenKhuVuc;
    }

    public void setTenKhuVuc(String tenKhuVuc) {
        this.tenKhuVuc = tenKhuVuc;
    }

}

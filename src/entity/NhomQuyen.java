package entity;

public class NhomQuyen {

    private int manhomquyen;
    private String tennhomquyen;
    private String trangthai;

    public NhomQuyen() {
    }

    public NhomQuyen(int manhomquyen, String tennhomquyen, String trangthai) {
        this.manhomquyen = manhomquyen;
        this.tennhomquyen = tennhomquyen;
        this.trangthai = trangthai;
    }

    public int getManhomquyen() {
        return manhomquyen;
    }

    public void setManhomquyen(int manhomquyen) {
        this.manhomquyen = manhomquyen;
    }

    public String getTennhomquyen() {
        return tennhomquyen;
    }

    public void setTennhomquyen(String tennhomquyen) {
        this.tennhomquyen = tennhomquyen;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

}

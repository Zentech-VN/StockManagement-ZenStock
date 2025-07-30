package entity;

public class DanhMucChucNang {

    private String machucnang;
    private String tenchucnang;
    private String trangthai;

    public DanhMucChucNang() {
    }

    public DanhMucChucNang(String machucnang, String tenchucnang, String trangthai) {
        this.machucnang = machucnang;
        this.tenchucnang = tenchucnang;
        this.trangthai = trangthai;
    }

    public String getMachucnang() {
        return machucnang;
    }

    public void setMachucnang(String machucnang) {
        this.machucnang = machucnang;
    }

    public String getTenchucnang() {
        return tenchucnang;
    }

    public void setTenchucnang(String tenchucnang) {
        this.tenchucnang = tenchucnang;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

}

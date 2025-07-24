package entity;

public class Chart_Revenue {
    private String thang;
    private double doanhThu;
    private double giaVon;
    private double loiNhuan;

    public Chart_Revenue() {
    }

    public Chart_Revenue(String thang, double doanhThu, double giaVon, double loiNhuan) {
        this.thang = thang;
        this.doanhThu = doanhThu;
        this.giaVon = giaVon;
        this.loiNhuan = loiNhuan;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }

    public double getGiaVon() {
        return giaVon;
    }

    public void setGiaVon(double giaVon) {
        this.giaVon = giaVon;
    }

    public double getLoiNhuan() {
        return loiNhuan;
    }

    public void setLoiNhuan(double loiNhuan) {
        this.loiNhuan = loiNhuan;
    }
}

package entity;

public class ChiTietQuyen {

    private String hanhdong;
    private DanhMucChucNang danhmuc_chucnang = new DanhMucChucNang();
    private NhomQuyen nhomquyen = new NhomQuyen();

    public ChiTietQuyen() {
    }

    public String getHanhdong() {
        return hanhdong;
    }

    public void setHanhdong(String hanhdong) {
        this.hanhdong = hanhdong;
    }

    public DanhMucChucNang getDanhmuc_chucnang() {
        return danhmuc_chucnang;
    }

    public NhomQuyen getNhomquyen() {
        return nhomquyen;
    }

}

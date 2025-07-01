package entity;

public class VoteApproval {
    private String loaiPhieu;
    private String nguoiTao;
    private String trangThai;

    public VoteApproval() {
    }

    public VoteApproval(String loaiPhieu, String nguoiTao, String trangThai) {
        this.loaiPhieu = loaiPhieu;
        this.nguoiTao = nguoiTao;
        this.trangThai = trangThai;
    }

    public String getLoaiPhieu() {
        return loaiPhieu;
    }

    public void setLoaiPhieu(String loaiPhieu) {
        this.loaiPhieu = loaiPhieu;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}

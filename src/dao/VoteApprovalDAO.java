package dao;

import entity.VoteApproval;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public interface VoteApprovalDAO {

    default List<VoteApproval> getAllVoteApproval() {
        List<VoteApproval> list = new ArrayList<>();
        String sql
                = "SELECT 'Phiếu Nhập NCC'    AS loai_phieu, pncc.trangthai, nv.hoten AS nguoi_tao "
                + "FROM phieunhap_nhacungcap pncc "
                + "  JOIN nhanvien nv ON pncc.nguoitao = nv.manv "
                + "UNION ALL "
                + "SELECT 'Phiếu Nhập Trả Hàng' AS loai_phieu, pnth.trangthai, nv.hoten AS nguoi_tao "
                + "FROM phieunhap_trahang pnth "
                + "  JOIN nhanvien nv ON pnth.nguoitao = nv.manv "
                + "UNION ALL "
                + "SELECT 'Phiếu Xuất Hủy'     AS loai_phieu, pxh.trangthai, nv.hoten AS nguoi_tao "
                + "FROM phieuxuat_huy pxh "
                + "  JOIN nhanvien nv ON pxh.nguoitao = nv.manv "
                + "UNION ALL "
                + "SELECT 'Phiếu Chuyển Kho'   AS loai_phieu, pck.trangthai, nv.hoten AS nguoi_tao "
                + "FROM phieuchuyenkho pck "
                + "  JOIN nhanvien nv ON pck.nguoitao = nv.manv "
                + // sửa ORDER BY: tham chiếu đến alias cột, không phải alias bảng
                "ORDER BY loai_phieu, nguoi_tao";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                VoteApproval pi = new VoteApproval();
                pi.setLoaiPhieu(rs.getString("loai_phieu"));
                pi.setNguoiTao(rs.getString("nguoi_tao"));
                pi.setTrangThai(rs.getString("trangthai"));
                list.add(pi);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}

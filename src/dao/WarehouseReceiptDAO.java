package dao;

import entity.PhieuNhap;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public class WarehouseReceiptDAO {

    public List<PhieuNhap> getAllentries() {
        List<PhieuNhap> listp = new ArrayList<>();
        String sql = "select * from phieunhap";
        try (Connection conn = ConnectionHelper.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                PhieuNhap p = new PhieuNhap();
                p.setMaphieunhap(rs.getInt("maphieunhap"));
                p.getS().setMaNhaCungCap(rs.getInt("manhacungcap"));
                p.getE().setManv(rs.getInt("nguoitao"));
                p.setNgaytao(rs.getDate("thoigian"));
                p.setTrangthai(rs.getString("trangthai"));
                listp.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listp; 
    }
}

package dao;

import entity.Chart_Revenue;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public interface ChartDAO {

    default List<Chart_Revenue> getRevenue() {
        List<Chart_Revenue> list = new ArrayList<>();

        String sql = "SELECT  "
                + "DATE_FORMAT(px.thoigian, '%Y-%m') AS thang, "
                + "ROUND(SUM(ctx.soluong * ctx.dongia), 0) AS doanh_thu, "
                + "ROUND(SUM(ctx.soluong * IFNULL(nhap.gia_von_tb, 0)), 0) AS gia_von, "
                + "ROUND(SUM(ctx.soluong * ctx.dongia) - SUM(ctx.soluong * IFNULL(nhap.gia_von_tb, 0)), 0) AS loi_nhuan "
                + "FROM phieuxuat px "
                + "JOIN ctphieuxuat ctx ON ctx.maphieuxuat = px.maphieuxuat "
                + "LEFT JOIN ( "
                + "    SELECT masanpham, AVG(dongia) AS gia_von_tb "
                + "    FROM ctphieunhap "
                + "    GROUP BY masanpham "
                + ") nhap ON nhap.masanpham = ctx.masanpham "
                + "WHERE px.trangthai = 'Duyet' "
                + "GROUP BY DATE_FORMAT(px.thoigian, '%Y-%m') "
                + "ORDER BY DATE_FORMAT(px.thoigian, '%Y-%m') DESC ";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Chart_Revenue tk = new Chart_Revenue();
                tk.setThang(rs.getString("thang"));
                tk.setDoanhThu(rs.getDouble("doanh_thu"));
                tk.setGiaVon(rs.getDouble("gia_von"));
                tk.setLoiNhuan(rs.getDouble("loi_nhuan"));
                list.add(tk);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return list;
    }

    default List<Chart_Revenue> getRevenue10Month() {
        List<Chart_Revenue> list = new ArrayList<>();

        String sql = "SELECT  "
                + "DATE_FORMAT(px.thoigian, '%Y-%m') AS thang, "
                + "ROUND(SUM(ctx.soluong * ctx.dongia), 0) AS doanh_thu, "
                + "ROUND(SUM(ctx.soluong * IFNULL(nhap.gia_von_tb, 0)), 0) AS gia_von, "
                + "ROUND(SUM(ctx.soluong * ctx.dongia) - SUM(ctx.soluong * IFNULL(nhap.gia_von_tb, 0)), 0) AS loi_nhuan "
                + "FROM phieuxuat px "
                + "JOIN ctphieuxuat ctx ON ctx.maphieuxuat = px.maphieuxuat "
                + "LEFT JOIN ( "
                + "    SELECT masanpham, AVG(dongia) AS gia_von_tb "
                + "    FROM ctphieunhap "
                + "    GROUP BY masanpham "
                + ") nhap ON nhap.masanpham = ctx.masanpham "
                + "WHERE px.trangthai = 'Duyet' "
                + "GROUP BY DATE_FORMAT(px.thoigian, '%Y-%m') "
                + "ORDER BY DATE_FORMAT(px.thoigian, '%Y-%m') DESC "
                //Chỉ lấy 10 tháng gần nhất
                + "LIMIT 10";

        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Chart_Revenue tk = new Chart_Revenue();
                tk.setThang(rs.getString("thang"));
                tk.setDoanhThu(rs.getDouble("doanh_thu"));
                tk.setGiaVon(rs.getDouble("gia_von"));
                tk.setLoiNhuan(rs.getDouble("loi_nhuan"));
                list.add(tk);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return list;
    }

}

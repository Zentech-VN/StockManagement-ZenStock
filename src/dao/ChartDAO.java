package dao;

import entity.Chart_Inventory;
import entity.Chart_ProductOutOfStock;
import entity.Chart_ProductTopSelling;
import entity.Chart_Revenue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    default List<Chart_Revenue> getRevenue6Month() {
        List<Chart_Revenue> list = new ArrayList<>();

        String sql
                = "SELECT  "
                + "    m.thang, "
                + "    IFNULL(SUM(ctx.soluong * ctx.dongia), 0) AS doanh_thu, "
                + "    IFNULL(SUM(ctx.soluong * IFNULL(nhap.gia_von_tb, 0)), 0) AS gia_von, "
                + "    IFNULL(SUM(ctx.soluong * ctx.dongia), 0) "
                + "        - IFNULL(SUM(ctx.soluong * IFNULL(nhap.gia_von_tb, 0)), 0) AS loi_nhuan "
                + "FROM ( "
                + "    SELECT DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 5 MONTH), '%Y-%m') AS thang UNION ALL "
                + "    SELECT DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 4 MONTH), '%Y-%m') UNION ALL "
                + "    SELECT DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 3 MONTH), '%Y-%m') UNION ALL "
                + "    SELECT DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 2 MONTH), '%Y-%m') UNION ALL "
                + "    SELECT DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y-%m') UNION ALL "
                + "    SELECT DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 0 MONTH), '%Y-%m') "
                + ") m "
                + "LEFT JOIN phieuxuat px  "
                + "    ON DATE_FORMAT(px.thoigian, '%Y-%m') = m.thang "
                + "    AND px.trangthai = 'Duyet' "
                + "LEFT JOIN ctphieuxuat ctx  "
                + "    ON ctx.maphieuxuat = px.maphieuxuat "
                + "LEFT JOIN ( "
                + "    SELECT  "
                + "        masanpham,  "
                + "        AVG(dongia) AS gia_von_tb "
                + "    FROM ctphieunhap "
                + "    GROUP BY masanpham "
                + ") nhap  "
                + "    ON nhap.masanpham = ctx.masanpham "
                + "GROUP BY m.thang "
                + "ORDER BY m.thang DESC;";

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

    default List<Chart_Revenue> getRevenueYears(int fromYear, int toYear) {
        List<Chart_Revenue> list = new ArrayList<>();
        String sql = "SELECT y.nam, "
                + "IFNULL(SUM(ctx.soluong * ctx.dongia), 0) AS doanhthu, "
                + "IFNULL(SUM(ctx.soluong * IFNULL(nhap.avg_cost, 0)), 0) AS giavon, "
                + "IFNULL(SUM(ctx.soluong * ctx.dongia), 0) - IFNULL(SUM(ctx.soluong * IFNULL(nhap.avg_cost, 0)), 0) AS loinhuan "
                + "FROM (SELECT ? AS nam UNION ALL SELECT ?+1 UNION ALL SELECT ?+2 UNION ALL SELECT ?+3 UNION ALL SELECT ?+4) y "
                + "LEFT JOIN phieuxuat px ON YEAR(px.thoigian) = y.nam AND px.trangthai = 'Duyet' "
                + "LEFT JOIN ctphieuxuat ctx ON ctx.maphieuxuat = px.maphieuxuat "
                + "LEFT JOIN (SELECT masanpham, AVG(dongia) AS avg_cost FROM ctphieunhap GROUP BY masanpham) nhap ON nhap.masanpham = ctx.masanpham "
                + "WHERE y.nam BETWEEN ? AND ? "
                + "GROUP BY y.nam ORDER BY y.nam DESC;";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < 5; i++) {
                ps.setInt(i + 1, fromYear);
            }
            ps.setInt(6, fromYear);
            ps.setInt(7, toYear);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Chart_Revenue(
                        String.valueOf(rs.getInt("nam")),
                        rs.getDouble("doanhthu"),
                        rs.getDouble("giavon"),
                        rs.getDouble("loinhuan")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    default List<Chart_Revenue> getRevenueMonths(int year) {
        List<Chart_Revenue> list = new ArrayList<>();
        String sql = "SELECT t.thang, "
                + "IFNULL(SUM(ctx.soluong * ctx.dongia), 0) AS doanhthu, "
                + "IFNULL(SUM(ctx.soluong * IFNULL(nhap.avg_cost, 0)), 0) AS giavon, "
                + "IFNULL(SUM(ctx.soluong * ctx.dongia), 0) - IFNULL(SUM(ctx.soluong * IFNULL(nhap.avg_cost, 0)), 0) AS loinhuan "
                + "FROM (SELECT 1 AS thang UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 "
                + "UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12) t "
                + "LEFT JOIN phieuxuat px ON MONTH(px.thoigian) = t.thang AND YEAR(px.thoigian) = ? AND px.trangthai = 'Duyet' "
                + "LEFT JOIN ctphieuxuat ctx ON ctx.maphieuxuat = px.maphieuxuat "
                + "LEFT JOIN (SELECT masanpham, AVG(dongia) AS avg_cost FROM ctphieunhap GROUP BY masanpham) nhap ON nhap.masanpham = ctx.masanpham "
                + "GROUP BY t.thang ORDER BY t.thang DESC;";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Chart_Revenue(
                        "Tháng " + rs.getInt("thang"),
                        rs.getDouble("doanhthu"),
                        rs.getDouble("giavon"),
                        rs.getDouble("loinhuan")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    default List<Chart_Revenue> getRevenueDays(int year, int month) {
        List<Chart_Revenue> list = new ArrayList<>();
        String sql = "SELECT d.ngay, "
                + "IFNULL(SUM(ctx.soluong * ctx.dongia), 0) AS doanhthu, "
                + "IFNULL(SUM(ctx.soluong * IFNULL(nhap.avg_cost, 0)), 0) AS giavon, "
                + "IFNULL(SUM(ctx.soluong * ctx.dongia), 0) - IFNULL(SUM(ctx.soluong * IFNULL(nhap.avg_cost, 0)), 0) AS loinhuan "
                + "FROM (SELECT 1 AS ngay UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 "
                + "UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12 "
                + "UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL SELECT 15 UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 "
                + "UNION ALL SELECT 19 UNION ALL SELECT 20 UNION ALL SELECT 21 UNION ALL SELECT 22 UNION ALL SELECT 23 UNION ALL SELECT 24 "
                + "UNION ALL SELECT 25 UNION ALL SELECT 26 UNION ALL SELECT 27 UNION ALL SELECT 28 UNION ALL SELECT 29 UNION ALL SELECT 30 "
                + "UNION ALL SELECT 31) d "
                + "LEFT JOIN phieuxuat px ON DAY(px.thoigian) = d.ngay AND MONTH(px.thoigian) = ? AND YEAR(px.thoigian) = ? AND px.trangthai = 'Duyet' "
                + "LEFT JOIN ctphieuxuat ctx ON ctx.maphieuxuat = px.maphieuxuat "
                + "LEFT JOIN (SELECT masanpham, AVG(dongia) AS avg_cost FROM ctphieunhap GROUP BY masanpham) nhap ON nhap.masanpham = ctx.masanpham "
                + "GROUP BY d.ngay ORDER BY d.ngay DESC;";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Chart_Revenue(
                        "" + rs.getInt("ngay"),
                        rs.getDouble("doanhthu"),
                        rs.getDouble("giavon"),
                        rs.getDouble("loinhuan")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    default List<Chart_Inventory> getInventoryByKeyWord(String fromDate, String toDate, String keyword) {
        List<Chart_Inventory> list = new ArrayList<>();

        String sql
                = "SELECT "
                + "    sp.masanpham, "
                + "    sp.tensp, "
                + "    IFNULL(( "
                + "        SELECT SUM(ctpn.soluong) "
                + "        FROM phieunhap pn "
                + "        JOIN ctphieunhap ctpn ON pn.maphieunhap = ctpn.maphieunhap "
                + "        WHERE pn.trangthai = 'Duyet' "
                + "        AND ctpn.masanpham = sp.masanpham "
                + "        AND pn.thoigian < ? "
                + "    ), 0) "
                + "    - "
                + "    IFNULL(( "
                + "        SELECT SUM(ctpx.soluong) "
                + "        FROM phieuxuat px "
                + "        JOIN ctphieuxuat ctpx ON px.maphieuxuat = ctpx.maphieuxuat "
                + "        WHERE px.trangthai = 'Duyet' "
                + "        AND ctpx.masanpham = sp.masanpham "
                + "        AND px.thoigian < ? "
                + "    ), 0) AS ton_dau_ky, "
                + "    IFNULL(( "
                + "        SELECT SUM(ctpn.soluong) "
                + "        FROM phieunhap pn "
                + "        JOIN ctphieunhap ctpn ON pn.maphieunhap = ctpn.maphieunhap "
                + "        WHERE pn.trangthai = 'Duyet' "
                + "        AND ctpn.masanpham = sp.masanpham "
                + "        AND pn.thoigian BETWEEN ? AND ? "
                + "    ), 0) AS nhap_trong_ky, "
                + "    IFNULL(( "
                + "        SELECT SUM(ctpx.soluong) "
                + "        FROM phieuxuat px "
                + "        JOIN ctphieuxuat ctpx ON px.maphieuxuat = ctpx.maphieuxuat "
                + "        WHERE px.trangthai = 'Duyet' "
                + "        AND ctpx.masanpham = sp.masanpham "
                + "        AND px.thoigian BETWEEN ? AND ? "
                + "    ), 0) AS xuat_trong_ky "
                + "FROM sanpham sp "
                + "WHERE sp.tensp LIKE ? AND sp.is_delete = 0 ";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fromDate);
            ps.setString(2, fromDate);
            ps.setString(3, fromDate);
            ps.setString(4, toDate);
            ps.setString(5, fromDate);
            ps.setString(6, toDate);
            ps.setString(7, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chart_Inventory ci = new Chart_Inventory();
                ci.setMaSanPham(rs.getInt("masanpham"));
                ci.setTenSanPham(rs.getString("tensp"));
                ci.setTonDauKy(rs.getInt("ton_dau_ky"));
                ci.setNhapTrongKy(rs.getInt("nhap_trong_ky"));
                ci.setXuatTrongKy(rs.getInt("xuat_trong_ky"));
                ci.setTonCuoiKy(ci.getTonDauKy() + ci.getNhapTrongKy() - ci.getXuatTrongKy());
                list.add(ci);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    default List<Chart_Inventory> getInventoryAll(String keyword) {
        List<Chart_Inventory> list = new ArrayList<>();

        String sql
                = "SELECT "
                + "    sp.masanpham, "
                + "    sp.tensp, "
                + "    IFNULL(( "
                + "        SELECT SUM(ctpn.soluong) "
                + "        FROM phieunhap pn "
                + "        JOIN ctphieunhap ctpn ON pn.maphieunhap = ctpn.maphieunhap "
                + "        WHERE pn.trangthai = 'Duyet' "
                + "        AND ctpn.masanpham = sp.masanpham "
                + "    ), 0) "
                + "    - "
                + "    IFNULL(( "
                + "        SELECT SUM(ctpx.soluong) "
                + "        FROM phieuxuat px "
                + "        JOIN ctphieuxuat ctpx ON px.maphieuxuat = ctpx.maphieuxuat "
                + "        WHERE px.trangthai = 'Duyet' "
                + "        AND ctpx.masanpham = sp.masanpham "
                + "    ), 0) AS ton_dau_ky, "
                + "    0 AS nhap_trong_ky, "
                + "    0 AS xuat_trong_ky "
                + "FROM sanpham sp "
                + "WHERE sp.tensp LIKE ? AND sp.is_delete = 0";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chart_Inventory ci = new Chart_Inventory();
                ci.setMaSanPham(rs.getInt("masanpham"));
                ci.setTenSanPham(rs.getString("tensp"));
                ci.setTonDauKy(rs.getInt("ton_dau_ky"));
                ci.setNhapTrongKy(0);
                ci.setXuatTrongKy(0);
                ci.setTonCuoiKy(ci.getTonDauKy());
                list.add(ci);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    default List<Chart_ProductTopSelling> getTopSellingProducts() {
        List<Chart_ProductTopSelling> list = new ArrayList<>();
        String sql = "SELECT "
                + "sp.masanpham, "
                + "sp.tensp, "
                + "SUM(ctpx.soluong) AS so_luong_ban "
                + "FROM phieuxuat px "
                + "JOIN ctphieuxuat ctpx ON px.maphieuxuat = ctpx.maphieuxuat "
                + "JOIN sanpham sp ON ctpx.masanpham = sp.masanpham "
                + "WHERE px.trangthai = 'Duyet' AND sp.is_delete = 0"
                + "GROUP BY sp.masanpham, sp.tensp "
                + "ORDER BY so_luong_ban DESC "
                + "LIMIT 100";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chart_ProductTopSelling p = new Chart_ProductTopSelling();
                p.setMaSanPham(rs.getInt("masanpham"));
                p.setTenSanPham(rs.getString("tensp"));
                p.setSoLuongBan(rs.getInt("so_luong_ban"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    default List<Chart_ProductTopSelling> getTopSellingProductsByDate(String fromDate, String toDate) {
        List<Chart_ProductTopSelling> list = new ArrayList<>();
        String sql = "SELECT "
                + "sp.masanpham, "
                + "sp.tensp, "
                + "SUM(ctpx.soluong) AS so_luong_ban "
                + "FROM phieuxuat px "
                + "JOIN ctphieuxuat ctpx ON px.maphieuxuat = ctpx.maphieuxuat "
                + "JOIN sanpham sp ON ctpx.masanpham = sp.masanpham "
                + "WHERE px.trangthai = 'Duyet' AND sp.is_delete = 0 "
                + "AND px.thoigian BETWEEN ? AND ? "
                + "GROUP BY sp.masanpham, sp.tensp "
                + "ORDER BY so_luong_ban DESC "
                + "LIMIT 100";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fromDate);
            ps.setString(2, toDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chart_ProductTopSelling p = new Chart_ProductTopSelling();
                p.setMaSanPham(rs.getInt("masanpham"));
                p.setTenSanPham(rs.getString("tensp"));
                p.setSoLuongBan(rs.getInt("so_luong_ban"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    default List<Chart_ProductTopSelling> getTopSellingProductsByKeyword(String fromDate, String toDate, String keyword) {
        List<Chart_ProductTopSelling> list = new ArrayList<>();
        String sql = "SELECT "
                + "sp.masanpham, "
                + "sp.tensp, "
                + "SUM(ctpx.soluong) AS so_luong_ban "
                + "FROM phieuxuat px "
                + "JOIN ctphieuxuat ctpx ON px.maphieuxuat = ctpx.maphieuxuat "
                + "JOIN sanpham sp ON ctpx.masanpham = sp.masanpham "
                + "WHERE px.trangthai = 'Duyet' "
                + "AND px.thoigian BETWEEN ? AND ? "
                + "AND sp.tensp LIKE ? AND sp.is_delete = 0 "
                + "GROUP BY sp.masanpham, sp.tensp "
                + "ORDER BY so_luong_ban DESC "
                + "LIMIT 100";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fromDate);
            ps.setString(2, toDate);
            ps.setString(3, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chart_ProductTopSelling p = new Chart_ProductTopSelling();
                p.setMaSanPham(rs.getInt("masanpham"));
                p.setTenSanPham(rs.getString("tensp"));
                p.setSoLuongBan(rs.getInt("so_luong_ban"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    default List<Chart_ProductOutOfStock> getProductOutOfStock(String keyword, int minQuantity) {
        List<Chart_ProductOutOfStock> list = new ArrayList<>();

        if (minQuantity <= 0) {
            minQuantity = 5;
        }

        String sql = "SELECT "
                + "sp.masanpham, "
                + "sp.tensp, "
                + "SUM(kvs.soluong) AS so_luong_ton "
                + "FROM sanpham sp "
                + "JOIN khuvuckho_sanpham kvs ON sp.masanpham = kvs.masanpham "
                + "WHERE sp.tensp LIKE ? AND sp.is_delete = 0 "
                + "GROUP BY sp.masanpham, sp.tensp "
                + "HAVING so_luong_ton <= ? "
                + "ORDER BY so_luong_ton ASC";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setInt(2, minQuantity);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chart_ProductOutOfStock p = new Chart_ProductOutOfStock();
                p.setMaSanPham(rs.getInt("masanpham"));
                p.setTenSanPham(rs.getString("tensp"));
                p.setSoLuong(rs.getInt("so_luong_ton"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Receipt;
import entity.ReceiptDetails;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;

public class ReceiptDAO {

    public static ReceiptDAO getInstance() {
        return new ReceiptDAO();
    }
    
    public List<Receipt> getPendingReceipts() {
        List<Receipt> receipts = new ArrayList<>();
        
        String sql = ""
            + "SELECT \n"
            + "    'Phiếu Nhập' AS receipt_type,\n"
            + "    nv.hoten AS created_by,\n"
            + "    pn.thoigian AS timestamp,\n"
            + "    SUM(ct.dongia * ct.soluong) AS total_amount,\n"
            + "    pn.maphieunhap AS receipt_id,\n"
            + "    'import' AS receipt_category\n"
            + "FROM phieunhap pn\n"
            + "JOIN nhanvien nv ON pn.nguoitao = nv.manv\n"
            + "LEFT JOIN ctphieunhap ct ON pn.maphieunhap = ct.maphieunhap\n"
            + "WHERE pn.trangthai = 'ChoDuyet'\n"
            + "GROUP BY pn.maphieunhap, nv.hoten, pn.thoigian\n"
            + "\n"
            + "UNION ALL\n"
            + "\n"
            + "SELECT \n"
            + "    'Phiếu Xuất' AS receipt_type,\n"
            + "    nv.hoten AS created_by,\n"
            + "    px.thoigian AS timestamp,\n"
            + "    SUM(ct.dongia * ct.soluong) AS total_amount,\n"
            + "    px.maphieuxuat AS receipt_id,\n"
            + "    'export' AS receipt_category\n"
            + "FROM phieuxuat px\n"
            + "JOIN nhanvien nv ON px.nguoitao = nv.manv\n"
            + "LEFT JOIN ctphieuxuat ct ON px.maphieuxuat = ct.maphieuxuat\n"
            + "WHERE px.trangthai = 'ChoDuyet'\n"
            + "GROUP BY px.maphieuxuat, nv.hoten, px.thoigian\n"
            + "\n"
            + "ORDER BY timestamp DESC";

            
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            
            while (rs.next()) {
                Receipt receipt = new Receipt(
                    rs.getString("receipt_type"),
                    rs.getString("created_by"),
                    rs.getDate("timestamp").toLocalDate(),
                    rs.getBigDecimal("total_amount"),
                    rs.getInt("receipt_id"),
                    rs.getString("receipt_category")
                );
                receipts.add(receipt);
            }

            ConnectionHelper.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return receipts;
    }

    public List<ReceiptDetails> getReceiptDetails(int receiptId, String receiptCategory) {
        List<ReceiptDetails> details = new ArrayList<>();
        String sql;
        
        if ("import".equals(receiptCategory)) {
            sql = ""
                + "SELECT \n"
                + "    sp.tensp AS product_name,\n"
                + "    ct.dongia AS price,\n"
                + "    COALESCE(kv.tenkhuvuc, 'N/A') AS warehouse_code,\n"
                + "    ct.soluong AS quantity,\n"
                + "    ct.ghichu AS note\n"
                + "FROM ctphieunhap ct\n"
                + "JOIN sanpham sp ON ct.masanpham = sp.masanpham\n"
                + "LEFT JOIN khuvuckho_sanpham kvsp ON sp.masanpham = kvsp.masanpham\n"
                + "LEFT JOIN khuvuckho kv ON kvsp.makhuvuc = kv.makhuvuc\n"
                + "WHERE ct.maphieunhap = ?\n"
                + "ORDER BY sp.tensp";

        } else {
            sql = ""
                + "SELECT \n"
                + "    sp.tensp AS product_name,\n"
                + "    ct.dongia AS price,\n"
                + "    COALESCE(kv.tenkhuvuc, 'N/A') AS warehouse_code,\n"
                + "    ct.soluong AS quantity,\n"
                + "    ct.ghichu AS note\n"
                + "FROM ctphieuxuat ct\n"
                + "JOIN sanpham sp ON ct.masanpham = sp.masanpham\n"
                + "LEFT JOIN khuvuckho_sanpham kvsp ON sp.masanpham = kvsp.masanpham\n"
                + "LEFT JOIN khuvuckho kv ON kvsp.makhuvuc = kv.makhuvuc\n"
                + "WHERE ct.maphieuxuat = ?\n"
                + "ORDER BY sp.tensp";

        }
        
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, receiptId); 
            ResultSet rs = (ResultSet) pst.executeQuery();


            while (rs.next()) {
                ReceiptDetails detail = new ReceiptDetails(
                    rs.getString("product_name"),
                    rs.getDouble("price"),
                    rs.getString("warehouse_code"),
                    rs.getInt("quantity"),
                    rs.getString("note")
                );
                details.add(detail); 
            }

            ConnectionHelper.closeConnection(con); 

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return details;
    }
    
    public List<Receipt> getPendingReceiptsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Receipt> receipts = new ArrayList<>();
        
        String sql = ""
                + "SELECT * FROM (\n"
                + "    SELECT \n"
                + "        'Phiếu Nhập' AS receipt_type,\n"
                + "        nv.hoten AS created_by,\n"
                + "        pn.thoigian AS timestamp,\n"
                + "        SUM(ct.dongia * ct.soluong) AS total_amount,\n"
                + "        pn.maphieunhap AS receipt_id,\n"
                + "        'import' AS receipt_category\n"
                + "    FROM phieunhap pn\n"
                + "    JOIN nhanvien nv ON pn.nguoitao = nv.manv\n"
                + "    LEFT JOIN ctphieunhap ct ON pn.maphieunhap = ct.maphieunhap\n"
                + "    WHERE pn.trangthai = 'ChoDuyet'\n"
                + "      AND pn.thoigian BETWEEN ? AND ?\n"
                + "    GROUP BY pn.maphieunhap, nv.hoten, pn.thoigian\n"
                + "\n"
                + "    UNION ALL\n"
                + "\n"
                + "    SELECT \n"
                + "        'Phiếu Xuất' AS receipt_type,\n"
                + "        nv.hoten AS created_by,\n"
                + "        px.thoigian AS timestamp,\n"
                + "        SUM(ct.dongia * ct.soluong) AS total_amount,\n"
                + "        px.maphieuxuat AS receipt_id,\n"
                + "        'export' AS receipt_category\n"
                + "    FROM phieuxuat px\n"
                + "    JOIN nhanvien nv ON px.nguoitao = nv.manv\n"
                + "    LEFT JOIN ctphieuxuat ct ON px.maphieuxuat = ct.maphieuxuat\n"
                + "    WHERE px.trangthai = 'ChoDuyet'\n"
                + "      AND px.thoigian BETWEEN ? AND ?\n"
                + "    GROUP BY px.maphieuxuat, nv.hoten, px.thoigian\n"
                + ") combined_results\n"
                + "ORDER BY timestamp DESC";

            
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setDate(1, Date.valueOf(startDate));
            pst.setDate(2, Date.valueOf(endDate));
            pst.setDate(3, Date.valueOf(startDate));
            pst.setDate(4, Date.valueOf(endDate));
            ResultSet rs = (ResultSet) pst.executeQuery();
            
            while (rs.next()) {
                Receipt receipt = new Receipt(
                    rs.getString("receipt_type"),
                    rs.getString("created_by"),
                    rs.getDate("timestamp").toLocalDate(),
                    rs.getBigDecimal("total_amount"),
                    rs.getInt("receipt_id"),
                    rs.getString("receipt_category")
                );
                receipts.add(receipt);
            }

            ConnectionHelper.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return receipts;
    }
    public boolean approveReceipt(int receiptId, String receiptCategory) {
        String updateStatusSql;
        String updateInventorySql;
        
        if ("import".equals(receiptCategory)) {
            updateStatusSql = "UPDATE phieunhap SET trangthai = 'Duyet' WHERE maphieunhap = ? AND trangthai = 'ChoDuyet'";
            // Increase
            updateInventorySql =
                "INSERT INTO khuvuckho_sanpham (makhuvuc, masanpham, soluong)\n" +
                "SELECT kvsp.makhuvuc, ct.masanpham, ct.soluong\n" +
                "FROM ctphieunhap ct\n" +
                "JOIN khuvuckho_sanpham kvsp ON ct.masanpham = kvsp.masanpham\n" +
                "WHERE ct.maphieunhap = ?\n" +
                "ON DUPLICATE KEY UPDATE soluong = khuvuckho_sanpham.soluong + VALUES(soluong)";

        } else {
            updateStatusSql = "UPDATE phieuxuat SET trangthai = 'Duyet' WHERE maphieuxuat = ? AND trangthai = 'ChoDuyet'";
            
            updateInventorySql =
                "UPDATE khuvuckho_sanpham kvsp\n" +
                "JOIN ctphieuxuat ct ON kvsp.masanpham = ct.masanpham\n" +
                "SET kvsp.soluong = kvsp.soluong - ct.soluong\n" +
                "WHERE ct.maphieuxuat = ? AND kvsp.soluong >= ct.soluong";
        }
        
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            PreparedStatement pst1 = (PreparedStatement) con.prepareStatement(updateStatusSql);
            pst1.setInt(1, receiptId);

            PreparedStatement pst2 = con.prepareStatement(updateInventorySql);
            pst2.setInt(1, receiptId);
            pst2.executeUpdate();

            int rowsAffected = pst1.executeUpdate();
            ConnectionHelper.closeConnection(con);
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    

    public boolean rejectReceipt(int receiptId, String receiptCategory) {
        String sql;
        
        if ("import".equals(receiptCategory)) {
            sql = "UPDATE phieunhap SET trangthai = 'Huy' WHERE maphieunhap = ? AND trangthai = 'ChoDuyet'";
        } else {
            sql = "UPDATE phieuxuat SET trangthai = 'Huy' WHERE maphieuxuat = ? AND trangthai = 'ChoDuyet'";
        }
        
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, receiptId);
            int rowsAffected = pst.executeUpdate();
            ConnectionHelper.closeConnection(con);
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Database error while rejecting receipt", e);
        }
    }
    
    public Receipt getReceiptSummary(int receiptId, String receiptCategory) {
        String sql;
        
        if ("import".equals(receiptCategory)) {
            sql = ""
                + "SELECT \n"
                + "    pn.maphieunhap AS receipt_id,\n"
                + "    'Phiếu Nhập' AS receipt_type,\n"
                + "    ncc.tennhacungcap AS supplier_customer,\n"
                + "    nv.hoten AS created_by,\n"
                + "    pn.thoigian AS timestamp,\n"
                + "    COUNT(ct.masanpham) AS total_items,\n"
                + "    SUM(ct.soluong) AS total_quantity,\n"
                + "    SUM(ct.dongia * ct.soluong) AS total_amount\n"
                + "FROM phieunhap pn\n"
                + "JOIN nhacungcap ncc ON pn.manhacungcap = ncc.manhacungcap\n"
                + "JOIN nhanvien nv ON pn.nguoitao = nv.manv\n"
                + "LEFT JOIN ctphieunhap ct ON pn.maphieunhap = ct.maphieunhap\n"
                + "WHERE pn.maphieunhap = ?\n"
                + "  AND pn.trangthai = 'ChoDuyet'\n"
                + "GROUP BY pn.maphieunhap";

        } else {
            sql = ""
                + "SELECT \n"
                + "    px.maphieuxuat AS receipt_id,\n"
                + "    'Phiếu Xuất' AS receipt_type,\n"
                + "    kh.tenkhachhang AS supplier_customer,\n"
                + "    nv.hoten AS created_by,\n"
                + "    px.thoigian AS timestamp,\n"
                + "    COUNT(ct.masanpham) AS total_items,\n"
                + "    SUM(ct.soluong) AS total_quantity,\n"
                + "    SUM(ct.dongia * ct.soluong) AS total_amount\n"
                + "FROM phieuxuat px\n"
                + "JOIN khachhang kh ON px.makhachhang = kh.makhachhang\n"
                + "JOIN nhanvien nv ON px.nguoitao = nv.manv\n"
                + "LEFT JOIN ctphieuxuat ct ON px.maphieuxuat = ct.maphieuxuat\n"
                + "WHERE px.maphieuxuat = ?\n"
                + "  AND px.trangthai = 'ChoDuyet'\n"
                + "GROUP BY px.maphieuxuat";

        }
        
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, receiptId); 
            ResultSet rs = (ResultSet) pst.executeQuery();

            if (rs.next()) {
                Receipt receipt = new Receipt(
                    rs.getString("receipt_type"),
                    rs.getString("created_by"),
                    rs.getDate("timestamp").toLocalDate(),
                    rs.getBigDecimal("total_amount"),
                    rs.getInt("receipt_id"),
                    receiptCategory
                );
                ConnectionHelper.closeConnection(con);
                return receipt;
            }

            ConnectionHelper.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException("Database error while fetching receipt summary", e);
        }
        
        return null;
    }
}
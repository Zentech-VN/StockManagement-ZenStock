package dao;

import entity.Account;
import entity.Employee;
import jdbc.ConnectionHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDAO {

    public static AccountDAO getInstance() {
        return new AccountDAO();
    }
    
    public int insert(Account a) {
        int result = 0;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            
            // Kiểm tra xem nhân viên đã có tài khoaorn chưa kể cả tk đã xoá
            String checkSql = "SELECT COUNT(*) FROM taikhoan WHERE manv = ?";
            PreparedStatement checkPst = con.prepareStatement(checkSql);
            checkPst.setInt(1, a.getManv());
            ResultSet rs = checkPst.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                // Nếu đã có tài khoản, kiểm tra xem có phải đã bị xóa không
                String statusSql = "SELECT is_delete FROM taikhoan WHERE manv = ?";
                PreparedStatement statusPst = con.prepareStatement(statusSql);
                statusPst.setInt(1, a.getManv());
                ResultSet statusRs = statusPst.executeQuery();
                
                if (statusRs.next()) {
                    if (statusRs.getInt("is_delete") == 0) {
                        // Tài khoản đang hoạt động, không thể tạo mới
                        return -1; // Trả về -1 để báo lỗi duplicate
                    } else {
                        // Tài khoản đã bị xóa, ghi đè lại thông tin để khôi phục
                        String updateSql = "UPDATE taikhoan SET tendangnhap=?, matkhau=?, manhomquyen=?, trangthai=?, is_delete=0 WHERE manv=?";
                        PreparedStatement updatePst = con.prepareStatement(updateSql);
                        updatePst.setString(1, a.getUsername());
                        updatePst.setString(2, a.getMatkhau());
                        updatePst.setInt(3, a.getManhomquyen());
                        updatePst.setInt(4, a.getTrangthai());
                        updatePst.setInt(5, a.getManv());
                        result = updatePst.executeUpdate();
                    }
                }
            } else {
                // Chưa có tài khoản thif tạo mới
                String sql = "INSERT INTO `taikhoan`(`manv`,`tendangnhap`,`matkhau`,`manhomquyen`,`trangthai`,`is_delete`) VALUES (?,?,?,?,?,0)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, a.getManv());
                pst.setString(2, a.getUsername());
                pst.setString(3, a.getMatkhau());
                pst.setInt(4, a.getManhomquyen());
                pst.setInt(5, a.getTrangthai());
                result = pst.executeUpdate();
            }
            ConnectionHelper.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return result;
    }

    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "UPDATE taikhoan SET is_delete = 1 WHERE manv = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(t));
            result = pst.executeUpdate();
            ConnectionHelper.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<Account> selectAll() {
        ArrayList<Account> result = new ArrayList<Account>();
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "SELECT manv, tendangnhap, manhomquyen, trangthai FROM taikhoan WHERE is_delete = 0";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("manv");
                String username = rs.getString("tendangnhap");
                int manhomquyen = rs.getInt("manhomquyen");
                int trangthai = rs.getInt("trangthai");
                Account tk = new Account(manv, username, manhomquyen, trangthai);
                result.add(tk);
            }
            ConnectionHelper.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    public ArrayList<Account> selectPaged(int pageIndex, int pageSize) {
        ArrayList<Account> result = new ArrayList<>();
        try {
            Connection con = ConnectionHelper.getConnection();
            String sql = "SELECT manv, tendangnhap, manhomquyen, trangthai FROM taikhoan WHERE is_delete = 0 LIMIT ? OFFSET ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            int offset = (pageIndex - 1) * pageSize;
            pst.setInt(1, pageSize); 
            pst.setInt(2, offset);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("manv");
                String username = rs.getString("tendangnhap");
                int manhomquyen = rs.getInt("manhomquyen");
                int trangthai = rs.getInt("trangthai");
                result.add(new Account(manv, username, manhomquyen, trangthai));
            }
            ConnectionHelper.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Account selectByUser(String t) {
        Account result = null;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "SELECT manv, tendangnhap, matkhau, trangthai, manhomquyen FROM taikhoan WHERE tendangnhap = ? AND is_delete = 0";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("manv");
                String tendangnhap = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int trangthai = rs.getInt("trangthai");
                int manhomquyen = rs.getInt("manhomquyen");
                Account ac = new Account(manv, tendangnhap, matkhau, manhomquyen, trangthai);
                result = ac;
            }
            ConnectionHelper.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    public int update(Account t) {
        int result = 0;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "UPDATE `taikhoan` SET `tendangnhap`=?, `manhomquyen`=?, `trangthai`=? WHERE manv=? AND is_delete = 0";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getUsername());
            pst.setInt(2, t.getManhomquyen());
            pst.setInt(3, t.getTrangthai());
            pst.setInt(4, t.getManv());
            result = pst.executeUpdate();
            ConnectionHelper.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public Account selectById(String t) {
        Account result = null;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "SELECT manv, tendangnhap, matkhau, trangthai, manhomquyen FROM taikhoan WHERE manv=? AND is_delete = 0";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("manv");
                String tendangnhap = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int trangthai = rs.getInt("trangthai");
                int manhomquyen = rs.getInt("manhomquyen");
                Account ac = new Account(manv, tendangnhap, matkhau, manhomquyen, trangthai);
                result = ac;
            }
            ConnectionHelper.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    
    

    public static int getAccountCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM taikhoan WHERE is_delete = 0";

        try (Connection con = (Connection) ConnectionHelper.getConnection(); PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql); ResultSet rs = (ResultSet) pst.executeQuery();) {

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public Employee GetFullNameByuserName(int id) throws SQLException {
        Employee e = null;
        String sql = "SELECT nhanvien.manv, nhanvien.hoten, taikhoan.manhomquyen " +
                    "FROM nhanvien JOIN taikhoan ON nhanvien.manv = taikhoan.manv " +
                     "WHERE taikhoan.manv = ? AND taikhoan.is_delete = 0 AND nhanvien.is_delete = 0";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                e = new Employee();
                e.setManv(rs.getInt("nhanvien.manv"));
                e.setHoten(rs.getString("nhanvien.hoten"));
                Account account = new Account();
                account.setManhomquyen(rs.getInt("taikhoan.manhomquyen"));
                e.setAcc(account);
            }
            return e;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getId(String username) throws SQLException {
        int id = 0;
        String sql = "SELECT manv FROM taikhoan WHERE tendangnhap = ? AND is_delete = 0";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt("manv");
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

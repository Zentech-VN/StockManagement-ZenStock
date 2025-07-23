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
            String sql = "INSERT INTO `taikhoan`(`manv`,`tendangnhap`,`matkhau`,`manhomquyen`,`trangthai`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, a.getManv());
            pst.setString(2, a.getUsername());
            pst.setString(3, a.getMatkhau());
            pst.setInt(4, a.getManhomquyen());
            pst.setInt(5, a.getTrangthai());
            result = pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "DELETE FROM `taikhoan` WHERE manv = ?";
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
            String sql = "SELECT * FROM taikhoan WHERE trangthai = '0' OR trangthai = '1'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("manv");
                String username = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int manhomquyen = rs.getInt("manhomquyen");
                int trangthai = rs.getInt("trangthai");
                Account tk = new Account(manv, username, matkhau, manhomquyen, trangthai);
                result.add(tk);
            }
            ConnectionHelper.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    public Account selectByUser(String t) {
        Account result = null;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE tendangnhap=?";
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
            String sql = "UPDATE `taikhoan` SET `tendangnhap`=?, `manhomquyen`=?, `trangthai`=? WHERE manv=?";
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

    public void updatePass(String email, String password) {
        int result;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "UPDATE taikhoan tk join nhanvien nv on tk.manv=nv.manv SET `matkhau`=? WHERE email=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, password);
            pst.setString(2, email);
            result = pst.executeUpdate();
            ConnectionHelper.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendOpt(String email, String opt) {
        int result;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "UPDATE taikhoan tk join nhanvien nv on tk.manv=nv.manv SET `otp`=? WHERE email=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, opt);
            pst.setString(2, email);
            result = pst.executeUpdate();
            ConnectionHelper.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Account selectByEmail(String t) {
        Account tk = null;
        try {
            Connection con = ConnectionHelper.getConnection();
            String sql = "SELECT * FROM taikhoan tk join nhanvien nv on tk.manv=nv.manv where nv.email = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("manv");
                String tendangnhap = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int trangthai = rs.getInt("trangthai");
                int manhomquyen = rs.getInt("manhomquyen");
                tk = new Account(manv, tendangnhap, matkhau, manhomquyen, trangthai);
                return tk;
            }
            ConnectionHelper.closeConnection(con);
        } catch (Exception e) {

        }
        return tk;
    }

    public boolean checkOtp(String email, String otp) {
        boolean check = false;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "SELECT * FROM taikhoan tk join nhanvien nv on tk.manv=nv.manv where nv.email = ? and tk.otp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, otp);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                check = true;
                return check;
            }
            ConnectionHelper.closeConnection(con);
        } catch (Exception e) {
        }
        return check;
    }

    public Account selectById(String t) {
        Account result = null;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE manv=?";
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
        String sql = "SELECT COUNT(*) FROM taikhoan";

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
        String sql = "select nhanvien.manv, nhanvien.hoten, taikhoan.manhomquyen from nhanvien join taikhoan on nhanvien.manv = taikhoan.manv\n"
                + "where taikhoan.manv = ?;";
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
        String sql = "select manv from taikhoan where tendangnhap = ?";
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

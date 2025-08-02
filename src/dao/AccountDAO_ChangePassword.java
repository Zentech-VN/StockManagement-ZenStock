/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.ConnectionHelper;

/**
 *
 * @author PC
 */
public class AccountDAO_ChangePassword {
    public static AccountDAO_ChangePassword getInstance() {
        return new AccountDAO_ChangePassword();
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
}

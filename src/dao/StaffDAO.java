/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Staff;
import jdbc.ConnectionHelper;
/**
 *
 * @author Duc Pham Ngoc
 */
public class StaffDAO {
    public static StaffDAO getInstance(){
        return new StaffDAO();
    }
    
    public int insert(Staff t) {
        int result = 0 ;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "INSERT INTO `nhanvien`(`hoten`, `gioitinh`,`sdt`,`ngaysinh`,`trangthai`,`email`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getHoten());
            pst.setInt(2, t.getGioitinh());
            pst.setString(3, t.getSdt());
            pst.setDate(4, (Date) (t.getNgaysinh()));
            pst.setInt(5, t.getTrangthai());
            pst.setString(6, t.getEmail());
            result = pst.executeUpdate();
            ConnectionHelper.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int update(Staff t) {
        int result = 0 ;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "UPDATE `nhanvien` SET`hoten`=?,`gioitinh`=?,`ngaysinh`=?,`sdt`=?, `trangthai`=?, `email`=?  WHERE `manv`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getHoten());
            pst.setInt(2, t.getGioitinh());
            pst.setDate(3, (Date) t.getNgaysinh());
            pst.setString(4, t.getSdt());
            pst.setInt(5, t.getTrangthai());
            pst.setString(6, t.getEmail());
            pst.setInt(7, t.getManv());
            result = pst.executeUpdate();
            ConnectionHelper.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "Update nhanvien set `trangthai` = -1 WHERE manv = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            ConnectionHelper.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}

package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Staff;
import java.sql.ResultSet;
import java.util.ArrayList;
import jdbc.ConnectionHelper;

public class StaffDAO {

    public static StaffDAO getInstance() {
        return new StaffDAO();
    }

    public int insert(Staff t) {
        int result = 0;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "INSERT INTO `nhanvien`(`hoten`, `gioitinh`,`sdt`,`ngaysinh`,`trangthai`,`email`, `is_delete`) VALUES (?,?,?,?,?,?,0)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getHoten());
            pst.setInt(2, t.getGioitinh());
            pst.setString(3, t.getSdt());
            pst.setDate(4, (Date) (t.getNgaysinh()));
            pst.setString(6, t.getEmail());
            result = pst.executeUpdate();
            ConnectionHelper.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int update(Staff t) {
        int result = 0;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "UPDATE `nhanvien` SET`hoten`=?,`gioitinh`=?,`ngaysinh`=?,`sdt`=?, `trangthai`=?, `email`=?  WHERE `manv`=? AND is_delete = 0";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getHoten());
            pst.setInt(2, t.getGioitinh());
            pst.setDate(3, (Date) t.getNgaysinh());
            pst.setString(4, t.getSdt());
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
        int result = 0;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "UPDATE nhanvien SET is_delete = 1 WHERE manv = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            ConnectionHelper.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<Staff> selectAll() {
        ArrayList<Staff> result = new ArrayList<Staff>();
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "SELECT manv, hoten, gioitinh, ngaysinh, sdt, email FROM nhanvien WHERE trangthai = '1' AND is_delete = 0";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("manv");
                String hoten = rs.getString("hoten");
                int gioitinh = rs.getInt("gioitinh");
                Date ngaysinh = rs.getDate("ngaysinh");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");
                Staff nv = new Staff(manv, hoten, gioitinh, ngaysinh, sdt, email);
                result.add(nv);
            }
            ConnectionHelper.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Staff> selectAllNV() {
        ArrayList<Staff> result = new ArrayList<Staff>();
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "SELECT nv.manv, nv.hoten, nv.gioitinh, nv.ngaysinh, nv.sdt, nv.email " +
                        "FROM nhanvien nv " +
                        "WHERE nv.trangthai = 1 AND nv.is_delete = 0 " +
                        "AND nv.manv NOT IN (SELECT tk.manv FROM taikhoan tk WHERE tk.is_delete = 0)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("manv");
                String hoten = rs.getString("hoten");
                int gioitinh = rs.getInt("gioitinh");
                Date ngaysinh = rs.getDate("ngaysinh");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");
                Staff nv = new Staff(manv, hoten, gioitinh, ngaysinh, sdt, email);
                result.add(nv);
            }
            ConnectionHelper.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

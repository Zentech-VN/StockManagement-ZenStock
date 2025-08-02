package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.ConnectionHelper;
import entity.PermGroup;

public class PermGroupDAO {

    public static PermGroupDAO getInstance() {
        return new PermGroupDAO();
    }

    public int insert(PermGroup t) {
        int result = 0;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "INSERT INTO `nhomquyen`(`tennhomquyen`,`trangthai`) VALUES (?,1)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTennhomquyen());
            result = pst.executeUpdate();
            ConnectionHelper.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PermGroupDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int update(PermGroup t) {
        int result = 0;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "UPDATE `nhomquyen` SET `tennhomquyen`=? WHERE `manhomquyen`=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTennhomquyen());
            pst.setInt(2, t.getManhomquyen());
            result = pst.executeUpdate();
            ConnectionHelper.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PermGroupDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "UPDATE `nhomquyen` SET `trangthai` = 0 WHERE manhomquyen = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            ConnectionHelper.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PermGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<PermGroup> selectAll() {
        ArrayList<PermGroup> result = new ArrayList<PermGroup>();
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "SELECT * FROM nhomquyen WHERE trangthai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manhomquyen = rs.getInt("manhomquyen");
                String tennhomquyen = rs.getString("tennhomquyen");
                PermGroup dvt = new PermGroup(manhomquyen, tennhomquyen);
                result.add(dvt);
            }
            ConnectionHelper.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    public static PermGroup selectById(String t) {
        PermGroup result = null;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "SELECT * FROM nhomquyen WHERE manhomquyen=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manhomquyen = rs.getInt("manhomquyen");
                String tennhomquyen = rs.getString("tennhomquyen");
                result = new PermGroup(manhomquyen, tennhomquyen);
            }
            ConnectionHelper.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) ConnectionHelper.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlikhohang' AND   TABLE_NAME   = 'nhomquyen'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs2.next()) {
                    result = rs2.getInt("AUTO_INCREMENT");

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermGroupDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}

package dao;

import entity.Account;
import entity.ChiTietQuyen;
import entity.NhomQuyen;
import jdbc.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRightsDAO {

    public List<NhomQuyen> getAllNhomQuyen() {
        List<NhomQuyen> list = new ArrayList<>();
        final String sql = "SELECT manhomquyen, tennhomquyen, trangthai "
                + "FROM nhomquyen ORDER BY manhomquyen DESC";
        try (Connection conn = ConnectionHelper.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                NhomQuyen nq = new NhomQuyen();
                nq.setManhomquyen(rs.getInt("manhomquyen"));
                nq.setTennhomquyen(rs.getString("tennhomquyen"));
                nq.setTrangthai(rs.getInt("trangthai"));
                list.add(nq);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public static class ActionRecord {

        public String machucnang;
        public String handong;

        public ActionRecord(String machucnang, String handong) {
            this.machucnang = machucnang;
            this.handong = handong;
        }
    }

    public int insertNhomQuyenAndGetId(Connection conn, String tenNhomQuyen, int trangThai) throws SQLException {
        final String sql = "INSERT INTO nhomquyen(tennhomquyen, trangthai) VALUES(?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, tenNhomQuyen);
            ps.setInt(2, trangThai);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                throw new SQLException("Không lấy được manhomquyen vừa tạo.");
            }
        }
    }

    private String humanize(String key) {
        if (key == null || key.trim().isEmpty()) {
            return "";
        }
        String k = key.trim().toLowerCase();
        return Character.toUpperCase(k.charAt(0)) + k.substring(1);
    }

    private void ensureChucNangExists(Connection conn, String machucnang) throws SQLException {
        if (machucnang == null) {
            return;
        }
        String key = machucnang.trim().toLowerCase();
        if (key.isEmpty()) {
            return;
        }

        final String sql = "INSERT IGNORE INTO danhmucchucnang(machucnang, tenchucnang, trangthai) VALUES(?,?,1)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, key);
            ps.setString(2, humanize(key));
            ps.executeUpdate();
        }
    }

    private void ensureFeaturesExist(Connection conn, Collection<ActionRecord> rights) throws SQLException {
        if (rights == null || rights.isEmpty()) {
            return;
        }
        Set<String> seen = new HashSet<>();
        for (ActionRecord r : rights) {
            if (r == null || r.machucnang == null) {
                continue;
            }
            String key = r.machucnang.trim().toLowerCase();
            if (!key.isEmpty() && seen.add(key)) {
                ensureChucNangExists(conn, key);
            }
        }
    }

    public void insertCtQuyenPerAction(Connection conn, int maNhom, List<ActionRecord> records) throws SQLException {
        if (records == null || records.isEmpty()) {
            return;
        }

        final String sql = "INSERT INTO ctquyen(manhomquyen, machucnang, hanhdong) VALUES(?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (ActionRecord r : records) {
                if (r == null) {
                    continue;
                }

                String feature = (r.machucnang == null) ? "" : r.machucnang.trim().toLowerCase();
                String action = (r.handong == null) ? "" : r.handong.trim().toLowerCase();

                if (feature.isEmpty() || action.isEmpty()) {
                    continue;
                }

                if (!("read".equals(action) || "create".equals(action) || "update".equals(action) || "delete".equals(action))) {
                    continue;
                }

                ps.setInt(1, maNhom);
                ps.setString(2, feature);
                ps.setString(3, action);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public int createGroupAndAssignRights(String tenNhomQuyen, int trangThai, List<ActionRecord> rights) throws SQLException {
        try (Connection conn = ConnectionHelper.getConnection()) {
            boolean oldAuto = conn.getAutoCommit();
            conn.setAutoCommit(false);
            try {

                ensureFeaturesExist(conn, rights);

                int maNhom = insertNhomQuyenAndGetId(conn, tenNhomQuyen, trangThai);

                insertCtQuyenPerAction(conn, maNhom, rights);

                conn.commit();
                conn.setAutoCommit(oldAuto);
                return maNhom;
            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException ignore) {
                }
                conn.setAutoCommit(oldAuto);
                throw e;
            }
        }
    }

    public String getTenNhomQuyenById(int maNhom) throws SQLException {
        final String sql = "SELECT tennhomquyen FROM nhomquyen WHERE manhomquyen = ?";
        try (Connection c = ConnectionHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, maNhom);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getString(1) : null;
            }
        }
    }

    public java.util.Map<String, java.util.Set<String>> getRightsMatrixByGroup(int maNhom) throws SQLException {
        java.util.Map<String, java.util.Set<String>> m = new java.util.LinkedHashMap<>();
        final String sql = "SELECT machucnang, hanhdong FROM ctquyen WHERE manhomquyen = ?";
        try (Connection c = ConnectionHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, maNhom);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String f = rs.getString(1);
                    String a = rs.getString(2);
                    if (f == null || a == null) {
                        continue;
                    }
                    f = f.trim().toLowerCase();
                    a = a.trim().toLowerCase();
                    m.computeIfAbsent(f, k -> new java.util.HashSet<>()).add(a);
                }
            }
        }
        return m;
    }

    private void updateNhomQuyen(Connection conn, int maNhom, String tenMoi) throws SQLException {
        final String sql = "UPDATE nhomquyen SET tennhomquyen = ? WHERE manhomquyen = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenMoi);
            ps.setInt(2, maNhom);
            ps.executeUpdate();
        }
    }

    private void deleteCtQuyenByGroup(Connection conn, int maNhom) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM ctquyen WHERE manhomquyen = ?")) {
            ps.setInt(1, maNhom);
            ps.executeUpdate();
        }
    }

    public void replaceGroupRights(int maNhom, String tenMoi, List<ActionRecord> rights) throws SQLException {
        try (Connection conn = ConnectionHelper.getConnection()) {
            boolean old = conn.getAutoCommit();
            conn.setAutoCommit(false);
            try {
                ensureFeaturesExist(conn, rights);

                updateNhomQuyen(conn, maNhom, tenMoi);
                deleteCtQuyenByGroup(conn, maNhom);
                insertCtQuyenPerAction(conn, maNhom, rights);

                conn.commit();
                conn.setAutoCommit(old);
            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException ignore) {
                }
                conn.setAutoCommit(old);
                throw e;
            }
        }
    }

    private int countAccountsInGroup(Connection conn, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM taikhoan WHERE manhomquyen = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    public int xoaNhomQuyen(int id) throws SQLException {
        try (Connection conn = ConnectionHelper.getConnection()) {
            boolean old = conn.getAutoCommit();
            conn.setAutoCommit(false);
            try {

                if (countAccountsInGroup(conn, id) > 0) {
                    conn.setAutoCommit(old);
                    return -1;
                }

                try (PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM ctquyen WHERE manhomquyen = ?")) {
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }

                int affected;
                try (PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM nhomquyen WHERE manhomquyen = ?")) {
                    ps.setInt(1, id);
                    affected = ps.executeUpdate();
                }

                conn.commit();
                conn.setAutoCommit(old);
                return affected;
            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException ignore) {
                }
                conn.setAutoCommit(old);
                throw e;
            }
        }
    }

    public Account getAccountbyMaNhomQuyen(int manhomquyen) {
        Account acc = null;
        String sql = "select manv from taikhoan where manhomquyen = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, manhomquyen);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    acc = new Account();
                    acc.setManv(rs.getInt("manv"));
                }
            }
            return acc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ChiTietQuyen> getALLCTQbyMaNHomQuyen(int manhomquyen) {
        List<ChiTietQuyen> list = new ArrayList<>();
        String sql = "select hanhdong,machucnang from ctquyen where manhomquyen = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, manhomquyen);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ChiTietQuyen chitietquyen = new ChiTietQuyen();
                chitietquyen.setHanhdong(rs.getString("hanhdong"));
                chitietquyen.getDanhmuc_chucnang().setMachucnang(rs.getString("machucnang"));
                list.add(chitietquyen);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

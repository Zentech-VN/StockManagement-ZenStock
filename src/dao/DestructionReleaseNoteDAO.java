package dao;

import entity.DestructionReleaseNote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionHelper;
import java.sql.Date;

public interface DestructionReleaseNoteDAO {

    default List<DestructionReleaseNote> getData() {
        List<DestructionReleaseNote> list = new ArrayList<>();
        String sql = "SELECT phx.maphieuxuat_huy, nv.hoten, phx.thoigian, phx.trangthai "
                + "FROM phieuxuat_huy phx "
                + "JOIN nhanvien nv ON phx.nguoitao = nv.manv";

        try (Connection conn = ConnectionHelper.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int maphieu = rs.getInt("maphieuxuat_huy");
                String hoten = rs.getString("hoten");
                Date thoigian = rs.getDate("thoigian");
                String trangthai = rs.getString("trangthai");

                DestructionReleaseNote phieu = new DestructionReleaseNote(maphieu, thoigian, trangthai, hoten, null, null);
                list.add(phieu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    default DestructionReleaseNote getAllPhieuXuatHuybyID(int id) {
        DestructionReleaseNote note = null;

        String sql = "SELECT phx.maphieuxuat_huy, ct.maimei, nv.hoten, phx.thoigian, phx.trangthai, ct.lydo "
                + "FROM phieuxuat_huy phx "
                + "JOIN nhanvien nv ON phx.nguoitao = nv.manv "
                + "JOIN ctphieuxuat_huy ct ON phx.maphieuxuat_huy = ct.maphieuxuat_huy "
                + "WHERE phx.maphieuxuat_huy = ?"; // ✅ chú ý có dấu cách trước WHERE

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // ✅ đặt đúng chỗ
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    note = new DestructionReleaseNote(
                            rs.getInt("maphieuxuat_huy"),
                            rs.getDate("thoigian"),
                            rs.getString("trangthai"),
                            rs.getString("hoten"),
                            rs.getString("lydo"),
                            rs.getString("maimei")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return note;
    }

    default boolean updatePhieuXuatHuy(int id, String lydo, String trangthai) {
        String updateTrangThaiSQL = "UPDATE phieuxuat_huy SET trangthai = ? WHERE maphieuxuat_huy = ?";
        String updateLyDoSQL = "UPDATE ctphieuxuat_huy SET lydo = ? WHERE maphieuxuat_huy = ?";

        try (Connection conn = ConnectionHelper.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu transaction

            try (
                    PreparedStatement stmtTrangThai = conn.prepareStatement(updateTrangThaiSQL); PreparedStatement stmtLyDo = conn.prepareStatement(updateLyDoSQL)) {
                // Cập nhật trạng thái
                stmtTrangThai.setString(1, trangthai);
                stmtTrangThai.setInt(2, id);
                stmtTrangThai.executeUpdate();

                // Cập nhật lý do
                stmtLyDo.setString(1, lydo);
                stmtLyDo.setInt(2, id);
                stmtLyDo.executeUpdate();

                conn.commit(); // Commit nếu không lỗi
                return true;
            } catch (SQLException e) {
                conn.rollback(); // Rollback nếu lỗi
                e.printStackTrace();
                return false;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    default boolean deletePhieuXuatHuyById(int id) {
        String deleteCT = "DELETE FROM ctphieuxuat_huy WHERE maphieuxuat_huy = ?";
        String deletePhieu = "DELETE FROM phieuxuat_huy WHERE maphieuxuat_huy = ?";

        try (Connection conn = ConnectionHelper.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu transaction

            try (PreparedStatement stmt1 = conn.prepareStatement(deleteCT); PreparedStatement stmt2 = conn.prepareStatement(deletePhieu)) {

                stmt1.setInt(1, id);
                stmt1.executeUpdate();

                stmt2.setInt(1, id);
                int affected = stmt2.executeUpdate();

                conn.commit();
                return affected > 0;

            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

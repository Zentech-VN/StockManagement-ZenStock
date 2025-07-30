package dao;

import entity.PhieuNhap;
import entity.PhieuNhapChiTiet;
import entity.ProductArea;

import entity.Supplier;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jdbc.ConnectionHelper;

public class WarehouseReceiptDAO {

    int getmaphieunhap = 0;

    public BigDecimal getdongiabyid(int masanpham) {
        String sql = "select gia from sanpham where masanpham = ?";
        BigDecimal gia = null;
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, masanpham);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                gia = rs.getBigDecimal("gia");
            }
            rs.close();
            return gia;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PhieuNhap> getAllentries() {
        List<PhieuNhap> listp = new ArrayList<>();
        String sql = "select * from phieunhap join nhanvien on phieunhap.nguoitao = nhanvien.manv";
        try (Connection conn = ConnectionHelper.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                PhieuNhap p = new PhieuNhap();
                p.setMaphieunhap(rs.getInt("maphieunhap"));
                p.getS().setMaNhaCungCap(rs.getInt("manhacungcap"));
                p.getE().setHoten(rs.getString("nhanvien.hoten"));
                p.setNgaytao(rs.getDate("thoigian"));
                p.setTrangthai(rs.getString("trangthai"));
                listp.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listp;
    }

    public List<Supplier> getAllNhaCungCap() {
        List<Supplier> list = new ArrayList<>();
        String sql = "SELECT * FROM nhacungcap";
        try (
                Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Supplier s = new Supplier();
                s.setMaNhaCungCap(rs.getInt("manhacungcap"));
                s.setTenNhaCungCap(rs.getString("tennhacungcap"));
                s.setDiaChi(rs.getString("diachi"));
                s.setEmail(rs.getString("email"));
                s.setSdt(rs.getString("sdt"));

                // Chuyển enum sang int: MoKhoa = 0, Khoa = 1
                String trangThaiStr = rs.getString("trangthai");
                int trangThai = "MoKhoa".equalsIgnoreCase(trangThaiStr) ? 0 : 1;
                s.setTrangThai(trangThai);

                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ProductArea> GetProducArea() {
        List<ProductArea> list = new ArrayList<>();
        String sql = "select khuvuckho_sanpham.makhuvuc, sanpham.masanpham, sanpham.tensp, khuvuckho.tenkhuvuc, sanpham.gia, soluong\n"
                + "from khuvuckho_sanpham join sanpham on khuvuckho_sanpham.masanpham = sanpham.masanpham\n"
                + "join khuvuckho on khuvuckho_sanpham.makhuvuc = khuvuckho.makhuvuc";
        try (Connection conn = ConnectionHelper.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ProductArea pa = new ProductArea();
                pa.getW().setMaKhuVuc(rs.getInt("khuvuckho_sanpham.makhuvuc"));
                pa.getP().setMaSanPham(rs.getInt("sanpham.masanpham"));
                pa.getP().setTenSanPham(rs.getString("sanpham.tensp"));

                pa.getP().setGia(rs.getBigDecimal("sanpham.gia"));
                pa.getW().setTenKhuVuc(rs.getString("khuvuckho.tenkhuvuc"));
                pa.setSoluong(rs.getInt("soluong"));
                list.add(pa);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public boolean checkProduct(String tenkho, String tensanpham) {
        String sql = "select sanpham.tensp \n"
                + "from khuvuckho_sanpham join sanpham on khuvuckho_sanpham.masanpham = sanpham.masanpham\n"
                + "join khuvuckho on khuvuckho_sanpham.makhuvuc = khuvuckho.makhuvuc\n"
                + "where sanpham.tensp = ? and khuvuckho.tenkhuvuc = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, tensanpham);
            pst.setString(2, tenkho);
            return pst.execute();
        } catch (Exception e) {
            return false;
        }

    }

    public int getMaPhieuNhap() {
        return this.getmaphieunhap;
    }

    public boolean TaoPhieuNhap(PhieuNhap pn, List<PhieuNhapChiTiet> listpnct) {
        String sql_phieunhap = "INSERT INTO phieunhap (manhacungcap, nguoitao, thoigian, trangthai) VALUES (?, ?, ?, ?)";
        String sql_phieunhapchitiet = "INSERT INTO ctphieunhap (maphieunhap, masanpham, dongia, soluong, ghichu) VALUES (?, ?, ?, ?, ?)";
        int maphieunhap = -1;

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql_phieunhap, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false);

            pst.setInt(1, pn.getS().getMaNhaCungCap());
            pst.setInt(2, pn.getE().getManv());
            pst.setDate(3, (Date) pn.getNgaytao());
            pst.setString(4, "ChoDuyet");

            if (pst.executeUpdate() == 0) {
                conn.rollback();
                JOptionPane.showMessageDialog(null, "Không thể tạo phiếu nhập.");
                return false;
            }

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    maphieunhap = rs.getInt(1);
                    this.getmaphieunhap = maphieunhap;
                } else {
                    JOptionPane.showMessageDialog(null, "Không lấy được mã phiếu nhập");
                    conn.rollback();
                    return false;
                }
            }

            try (PreparedStatement pst1 = conn.prepareStatement(sql_phieunhapchitiet)) {
                for (PhieuNhapChiTiet pnct : listpnct) {
                    pst1.setInt(1, maphieunhap);
                    pst1.setInt(2, pnct.getP().getMaSanPham());
                    pst1.setBigDecimal(3, pnct.getDongia());
                    pst1.setInt(4, pnct.getSoluong());
                    pst1.setString(5, pnct.getGhichu());
                    pst1.addBatch();
                }

                int[] result = pst1.executeBatch();
                for (int i : result) {
                    if (i == Statement.EXECUTE_FAILED) {
                        conn.rollback();
                        JOptionPane.showMessageDialog(null, "Lỗi khi thêm chi tiết phiếu nhập");
                        return false;
                    }
                }
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PhieuNhapChiTiet> getAllPhieuNhapbyID(int id) {
        List<PhieuNhapChiTiet> listpnct = new ArrayList<>();
        String sql = "select * from phieunhap join ctphieunhap on phieunhap.maphieunhap = ctphieunhap.maphieunhap\n"
                + "where phieunhap.maphieunhap = ?;";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PhieuNhapChiTiet pnct = new PhieuNhapChiTiet();
                pnct.getPh().setMaphieunhap(rs.getInt("maphieunhap"));
                pnct.getPh().getS().setMaNhaCungCap(rs.getInt("manhacungcap"));
                pnct.getPh().getE().setManv(rs.getInt("nguoitao"));
                pnct.getPh().setNgaytao(rs.getDate("thoigian"));
                pnct.getPh().setTrangthai(rs.getString("trangthai"));
                pnct.getP().setMaSanPham(rs.getInt("ctphieunhap.masanpham"));
                pnct.setDongia(rs.getBigDecimal("ctphieunhap.dongia"));
                pnct.setSoluong(rs.getInt("ctphieunhap.soluong"));
                pnct.setGhichu(rs.getString("ctphieunhap.ghichu"));
                listpnct.add(pnct);
            }
            return listpnct;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int UpdatePhieunhap(int maphieunhap, int manhacungcap) {
        String sql = "update phieunhap set manhacungcap = ? where maphieunhap = ?;";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, manhacungcap);
            pst.setInt(2, maphieunhap);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    public int UpdatePhieuNhapChiTiet(PhieuNhapChiTiet pnct, int masanpham) {
        String sql = "Update ctphieunhap set masanpham = ?, soluong = ?, dongia = ? where maphieunhap = ? and masanpham = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, masanpham);
            pst.setInt(2, pnct.getSoluong());
            pst.setBigDecimal(3, pnct.getDongia());
            pst.setInt(4, pnct.getPh().getMaphieunhap());
            pst.setInt(5, pnct.getP().getMaSanPham());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int xoaphieunhap(int id) {
        String sql_phieunhap = "delete from phieunhap where maphieunhap = ?";
        String sql_ctphieunhap = "delete from ctphieunhap where maphieunhap = ?";
        int rs_phieunhap = 0;
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst_ctphieunhap = conn.prepareStatement(sql_ctphieunhap)) {
            conn.setAutoCommit(false);
            pst_ctphieunhap.setInt(1, id);
            int rs_ctphieunhap = pst_ctphieunhap.executeUpdate();
            if (rs_ctphieunhap == -1) {
                JOptionPane.showMessageDialog(null, "Xóa phiếu nhập chi tiết không thành công!");
                conn.rollback();
                return 0;
            }
            try (PreparedStatement pst_phieunhap = conn.prepareStatement(sql_phieunhap)) {
                pst_phieunhap.setInt(1, id);
                rs_phieunhap = pst_phieunhap.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
            return rs_phieunhap;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}

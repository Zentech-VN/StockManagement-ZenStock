package dao;

import entity.Client;
import entity.PhieuXuat;
import entity.PhieuXuatChiTiet;
import entity.ProductArea;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jdbc.ConnectionHelper;

public class WarehouseDeliveryDAO {

    int maphieuxuatvuatao = 0;

    public int getMaSanPhambyTen(String tensanpham) {
        String sql = "select masanpham from sanpham where tensp = ?";
        int id = 0;
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, tensanpham);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt("masanpham");
            }
            rs.close();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<PhieuXuat> getAllPhieuNhap(int page, int pageSize) {
        List<PhieuXuat> list = new ArrayList<>();
        String sql = "select * from phieuxuat join khachhang on phieuxuat.makhachhang = khachhang.makhachhang join nhanvien on phieuxuat.nguoitao = nhanvien.manv LIMIT ? OFFSET ?";

        int offset = (page - 1) * pageSize;

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pageSize);
            ps.setInt(2, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PhieuXuat px = new PhieuXuat();
                    px.setMaphieuxuat(rs.getInt("maphieuxuat"));
                    px.getKhachhang().setTenKhacHang(rs.getString("khachhang.tenkhachhang"));
                    px.getNhanvien().setHoten(rs.getString("nhanvien.hoten"));
                    px.setThoigian(rs.getDate("thoigian"));
                    px.setTrangthai(rs.getString("trangthai"));
                    list.add(px);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

    public List<Client> getAllKhachHang() {
        List<Client> list = new ArrayList<>();
        String sql = "select * from khachhang Where is_delete = 0 and trangthai= 'Khoa'";
        try (Connection conn = ConnectionHelper.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Client c = new Client();
                c.setMaKhacHang(rs.getInt("makhachhang"));
                c.setTenKhacHang(rs.getString("tenkhachhang"));
                list.add(c);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getMaPhieuXuatVuaTao() {
        return this.maphieuxuatvuatao;
    }

    public boolean TaoPhieuXuat(List<PhieuXuatChiTiet> list, PhieuXuat px) {
        String sql_PhieuXuat = "INSERT INTO phieuxuat (makhachhang, nguoitao, thoigian, trangthai) VALUES (?,?,?,?)";
        String sql_PhieuXuatChiTiet = "insert into ctphieuxuat values (?,?,?,?,?)";
        int maphieuxuat = 0;

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst_PhieuXuat = conn.prepareStatement(sql_PhieuXuat, Statement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            //tạo phiếu xuất
            pst_PhieuXuat.setInt(1, px.getKhachhang().getMaKhacHang());
            pst_PhieuXuat.setInt(2, px.getNhanvien().getManv());
            pst_PhieuXuat.setDate(3, px.getThoigian());
            pst_PhieuXuat.setString(4, px.getTrangthai());

            int rs_phieuxuat = pst_PhieuXuat.executeUpdate();
            if (rs_phieuxuat == 0) {
                JOptionPane.showMessageDialog(null, "Tạo phiếu xuất thất bại!");
                conn.rollback();
                return false;
            }
            //lấy mã phiếu xuất 
            try (ResultSet rs = pst_PhieuXuat.getGeneratedKeys()) {
                if (rs.next()) {
                    maphieuxuat = rs.getInt(1);
                    this.maphieuxuatvuatao = maphieuxuat;
                } else {
                    JOptionPane.showMessageDialog(null, "Không lấy được mã phiếu xuất");
                    conn.rollback();
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                return false;
            }
            //tạo phiếu xuất chi tiết
            try (PreparedStatement pst_ctphieuxuat = conn.prepareStatement(sql_PhieuXuatChiTiet)) {
                for (PhieuXuatChiTiet pxct : list) {
                    pst_ctphieuxuat.setInt(1, maphieuxuat);
                    pst_ctphieuxuat.setInt(2, pxct.getSanpham().getMaSanPham());
                    pst_ctphieuxuat.setBigDecimal(3, pxct.getDongia());
                    pst_ctphieuxuat.setInt(4, pxct.getSoluong());
                    pst_ctphieuxuat.setString(5, pxct.getGhichu());
                    pst_ctphieuxuat.addBatch();
                }

                int[] rs_ctphieuxuat = pst_ctphieuxuat.executeBatch();
                for (int i : rs_ctphieuxuat) {
                    if (i == -1) {
                        conn.rollback();
                        return false;
                    }
                }

                conn.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int xoaphieuxuat(int id) {
        String sql_phieuxuat = "delete from phieuxuat where maphieuxuat = ?";
        String sql_ctphieuxuat = "delete from ctphieuxuat where maphieuxuat = ?";
        int rs_phieunhap = 0;
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst_ctphieuxuat = conn.prepareStatement(sql_ctphieuxuat)) {
            conn.setAutoCommit(false);
            pst_ctphieuxuat.setInt(1, id);
            int rs_ctphieunhap = pst_ctphieuxuat.executeUpdate();
            if (rs_ctphieunhap == -1) {
                JOptionPane.showMessageDialog(null, "Xóa phiếu xuất chi tiết không thành công!");
                conn.rollback();
                return 0;
            }
            try (PreparedStatement pst_phieuxuat = conn.prepareStatement(sql_phieuxuat)) {
                pst_phieuxuat.setInt(1, id);
                rs_phieunhap = pst_phieuxuat.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
               conn.commit(); 
            return rs_phieunhap;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int capnhatphieuxuat(int maphieuxuat, int makhachhang) {
        String sql = "update phieuxuat set makhachhang = ? where maphieuxuat = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, makhachhang);
            pst.setInt(2, maphieuxuat);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int capnhapchitietphieuxuat(PhieuXuatChiTiet pxct, int masanphamCu) {
        String sql = "UPDATE ctphieuxuat SET masanpham = ?, dongia = ?, soluong = ?, ghichu = ? WHERE maphieuxuat = ? AND masanpham = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, pxct.getSanpham().getMaSanPham()); // mã sản phẩm mới
            pst.setBigDecimal(2, pxct.getDongia());
            pst.setInt(3, pxct.getSoluong());
            pst.setString(4, pxct.getGhichu());
            pst.setInt(5, pxct.getPhieuxuat().getMaphieuxuat());
            pst.setInt(6, masanphamCu);

            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMaKhachHangbyTen(String tenkhachhang) {
        String sql = "select makhachhang from khachhang where tenkhachhang = ?";
        int id = 0;
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, tenkhachhang);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt("makhachhang");
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Client getKhachHangbyId(String tenkhachhang) {
        Client c = null;
        String sql = "select * from khachhang where tenkhachhang = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, tenkhachhang);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c = new Client();
                c.setMaKhacHang(rs.getInt("makhachhang"));
            }
            rs.close();
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PhieuXuatChiTiet> getAllPhieuXuatChiTiet(int maphieuxuat) {
        List<PhieuXuatChiTiet> list = new ArrayList<>();
        String sql = "select * from ctphieuxuat join sanpham on ctphieuxuat.masanpham = sanpham.masanpham where maphieuxuat = ?";
        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, maphieuxuat);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PhieuXuatChiTiet pxct = new PhieuXuatChiTiet();
                pxct.getPhieuxuat().setMaphieuxuat(rs.getInt("maphieuxuat"));
                pxct.getSanpham().setMaSanPham(rs.getInt("masanpham"));
                pxct.getSanpham().setTenSanPham(rs.getString("sanpham.tensp"));
                pxct.setDongia(rs.getBigDecimal("dongia"));
                pxct.setSoluong(rs.getInt("soluong"));
                pxct.setGhichu(rs.getString("ghichu"));
                list.add(pxct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PhieuXuatChiTiet> getPhieuXuatById(int maphieuxuat) {
        List<PhieuXuatChiTiet> list = new ArrayList<>();
        String sql
                = "SELECT * "
                + "FROM phieuxuat px "
                + "JOIN ctphieuxuat pxct ON px.maphieuxuat = pxct.maphieuxuat "
                + "JOIN khachhang kh ON px.makhachhang = kh.makhachhang "
                + "JOIN nhanvien nv ON px.nguoitao = nv.manv "
                + "JOIN sanpham sp ON pxct.masanpham = sp.masanpham "
                + "WHERE px.maphieuxuat = ?";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, maphieuxuat); // Sử dụng tham số thay vì hardcode

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PhieuXuatChiTiet pxct = new PhieuXuatChiTiet();

                // Gán thông tin Phiếu Xuất
                pxct.getPhieuxuat().setMaphieuxuat(rs.getInt("px.maphieuxuat"));
                pxct.getPhieuxuat().getKhachhang().setTenKhacHang(rs.getString("kh.tenkhachhang"));
                pxct.getPhieuxuat().getNhanvien().setHoten(rs.getString("nv.hoten"));
                pxct.getPhieuxuat().setThoigian(rs.getDate("px.thoigian"));
                pxct.getPhieuxuat().setTrangthai(rs.getString("px.trangthai"));

                // Gán thông tin chi tiết phiếu xuất
                pxct.getSanpham().setMaSanPham(rs.getInt("pxct.masanpham"));
                pxct.getSanpham().setTenSanPham(rs.getString("sp.tensp"));
                pxct.setDongia(rs.getBigDecimal("pxct.dongia"));
                pxct.setSoluong(rs.getInt("pxct.soluong"));
                pxct.setGhichu(rs.getString("pxct.ghichu"));

                list.add(pxct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

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

    public int getDeliveryCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM phieuxuat";

        try (Connection conn = ConnectionHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

}

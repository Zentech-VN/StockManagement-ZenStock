USE zentechStockManagement;

DELIMITER $$

CREATE PROCEDURE sp_sanpham_add (
    IN p_tenSanPham VARCHAR(255),
    IN p_hinhAnh VARCHAR(255),
    IN p_maXuatXu INT,
    IN p_chipXuLy VARCHAR(255),
    IN p_maHeDieuHanh INT,
    IN p_cameraTruoc VARCHAR(255),
    IN p_cameraSau VARCHAR(255),
    IN p_thongSo INT,
    IN p_gia DECIMAL(15,2),
    IN p_trangThai INT,
    IN p_maThuongHieu INT,
    IN p_dungLuongPin VARCHAR(50),
    IN p_kichThuocManHinh VARCHAR(50),
    IN p_thoiGianBaoHanh VARCHAR(50),
    IN p_maKhuVuc INT,
    IN p_soLuong INT
)
BEGIN
    DECLARE v_maSanPham INT;

    INSERT INTO sanpham (
        tensp, hinhanh, maxuatxu, chipxuly,
        mahedieuhanh, cameratruoc, camerasau,
        thongso, gia, trangthai,
        mathuonghieu, dungluongpin, kichthuocmanhinh, thoigianbaohanh
    ) VALUES (
        p_tenSanPham, p_hinhAnh, p_maXuatXu, p_chipXuLy,
        p_maHeDieuHanh, p_cameraTruoc, p_cameraSau,
        p_thongSo, p_gia, p_trangThai,
        p_maThuongHieu, p_dungLuongPin, p_kichThuocManHinh, p_thoiGianBaoHanh
    );

    SET v_maSanPham = LAST_INSERT_ID();

    INSERT INTO khuvuc_sanpham (makhuvuc, masanpham, soluong)
    VALUES (p_maKhuVuc, v_maSanPham, p_soLuong);
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_sanpham_update (
    IN p_masanpham INT,
    IN p_tenSanPham VARCHAR(255),
    IN p_hinhAnh VARCHAR(255),
    IN p_maXuatXu INT,
    IN p_chipXuLy VARCHAR(255),
    IN p_maHeDieuHanh INT,
    IN p_cameraTruoc VARCHAR(255),
    IN p_cameraSau VARCHAR(255),
    IN p_thongSo INT,
    IN p_gia DECIMAL(15,2),
    IN p_trangThai INT,
    IN p_maThuongHieu INT,
    IN p_dungLuongPin VARCHAR(50),
    IN p_kichThuocManHinh VARCHAR(50),
    IN p_thoiGianBaoHanh VARCHAR(50)
)
BEGIN
    UPDATE sanpham SET
        tensp = p_tenSanPham,
        hinhanh = p_hinhAnh,
        maxuatxu = p_maXuatXu,
        chipxuly = p_chipXuLy,
        mahedieuhanh = p_maHeDieuHanh,
        cameratruoc = p_cameraTruoc,
        camerasau = p_cameraSau,
        thongso = p_thongSo,
        gia = p_gia,
        trangthai = p_trangThai,
        mathuonghieu = p_maThuongHieu,
        dungluongpin = p_dungLuongPin,
        kichthuocmanhinh = p_kichThuocManHinh,
        thoigianbaohanh = p_thoiGianBaoHanh
    WHERE masanpham = p_masanpham;
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_sanpham_delete(IN p_maSanPham INT)
BEGIN
    DELETE FROM sanpham
    WHERE masanpham = p_maSanPham;
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_search_products(IN kw VARCHAR(255))
BEGIN
    SELECT sp.masanpham, sp.tensp, sp.gia, sp.trangthai,
           th.tenthuonghieu, hdh.tenhedieuhanh, xx.tenxuatxu
    FROM sanpham sp
    JOIN thuonghieu th ON sp.mathuonghieu = th.mathuonghieu
    JOIN hedieuhanh hdh ON sp.mahedieuhanh = hdh.mahedieuhanh
    JOIN xuatxu xx ON sp.maxuatxu = xx.maxuatxu
    WHERE sp.tensp LIKE kw OR th.tenthuonghieu LIKE kw OR hdh.tenhedieuhanh LIKE kw;
END $$

DELIMITER ;
USE zentechStockManagement;

/* Nhân viên */
DELIMITER $$

/* ===============================================
   1. Thêm nhân viên
   =============================================== */
DROP PROCEDURE IF EXISTS sp_nhanvien_add $$
CREATE PROCEDURE sp_nhanvien_add (
    IN p_hoten      VARCHAR(255),
    IN p_gioitinh   INT,
    IN p_ngaysinh   DATE,
    IN p_sdt        VARCHAR(50),
    IN p_email      VARCHAR(255),
    IN p_trangthai  INT
)
BEGIN
    INSERT INTO nhanvien (hoten, gioitinh, ngaysinh, sdt, email, trangthai)
    VALUES (p_hoten, p_gioitinh, p_ngaysinh, p_sdt, p_email, p_trangthai);
END $$
/* ===============================================
   2. Sửa thông tin nhân viên
   =============================================== */
DROP PROCEDURE IF EXISTS sp_nhanvien_update $$
CREATE PROCEDURE sp_nhanvien_update (
    IN p_manv       INT,
    IN p_hoten      VARCHAR(255),
    IN p_gioitinh   INT,
    IN p_ngaysinh   DATE,
    IN p_sdt        VARCHAR(50),
    IN p_email      VARCHAR(255),
    IN p_trangthai  INT
)
BEGIN
    UPDATE nhanvien
    SET hoten      = p_hoten,
        gioitinh   = p_gioitinh,
        ngaysinh   = p_ngaysinh,
        sdt        = p_sdt,
        email      = p_email,
        trangthai  = p_trangthai
    WHERE manv = p_manv;
END $$
/* ===============================================
   3. Xóa nhân viên
   =============================================== */
DROP PROCEDURE IF EXISTS sp_nhanvien_delete $$
CREATE PROCEDURE sp_nhanvien_delete (
    IN p_manv INT
)
BEGIN
    DELETE FROM nhanvien
    WHERE manv = p_manv;
END $$

DELIMITER ;

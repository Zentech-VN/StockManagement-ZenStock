USE zentechStockManagement;

CREATE VIEW vw_nhanvien_toan_bo AS
SELECT
    manv,
    hoten,
    gioitinh,
    ngaysinh,
    sdt,
    email,
    trangthai
FROM nhanvien;

CREATE VIEW vw_nhanvien_taikhoan AS
SELECT  nv.manv,
        nv.hoten,
        tk.tendangnhap,
        tk.manhomquyen,
        CASE 
            WHEN tk.tendangnhap IS NULL THEN 0
            ELSE 1
        END AS has_account
FROM    nhanvien nv
LEFT JOIN taikhoan tk ON tk.manv = nv.manv;

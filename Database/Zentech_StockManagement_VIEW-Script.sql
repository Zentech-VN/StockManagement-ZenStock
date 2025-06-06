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

CREATE VIEW vw_sanpham AS
SELECT 
    sp.masp,
    sp.tensp,
    xx.tenxuatxu,
    th.tenthuonghieu,
    kv.tenkhuvuc,
    sp.soluongton,
    sp.trangthai
FROM 
    sanpham sp
JOIN xuatxu xx ON sp.xuatxu = xx.maxuatxu
JOIN thuonghieu th ON sp.thuonghieu = th.mathuonghieu
JOIN khuvuckho kv ON sp.khuvuckho = kv.makhuvuc;
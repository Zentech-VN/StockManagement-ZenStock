USE zentechStockManagement;

CREATE VIEW vw_nhanvien_toan_bo AS
SELECT
    manv         AS employee_id,  -- mã nhân viên
    hoten        AS full_name,    -- họ tên
    gioitinh     AS gender,       -- giới tính
    ngaysinh     AS date_of_birth,
    sdt          AS phone_number,
    email,
    trangthai    AS status        -- trạng thái làm việc
FROM nhanvien;
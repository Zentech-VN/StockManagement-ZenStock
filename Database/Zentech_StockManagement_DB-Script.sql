CREATE DATABASE zentechStockManagement;
USE zentechStockManagement;

SET FOREIGN_KEY_CHECKS=0;

-- Table `nhanvien`
CREATE TABLE `nhanvien` (
  `manv` INT AUTO_INCREMENT,
  `hoten` VARCHAR(255) NOT NULL,
  `gioitinh` INT NOT NULL,
  `ngaysinh` DATE NOT NULL,
  `sdt` VARCHAR(50) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `trangthai` INT NOT NULL,
  PRIMARY KEY (`manv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `nhomquyen`
CREATE TABLE `nhomquyen` (
  `manhomquyen` INT AUTO_INCREMENT,
  `tennhomquyen` VARCHAR(255) NOT NULL,
  `trangthai` INT NOT NULL,
  PRIMARY KEY (`manhomquyen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `ctquyen`
CREATE TABLE `ctquyen` (
  `hanhdong` VARCHAR(50) NOT NULL,
  `machucnang` VARCHAR(50) NOT NULL,
  `manhomquyen` INT NOT NULL,
  PRIMARY KEY (`machucnang`, `manhomquyen`, `hanhdong`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `danhmucchucnang`
CREATE TABLE `danhmucchucnang` (
  `machucnang` VARCHAR(50) NOT NULL,
  `tenchucnang` VARCHAR(100) NOT NULL,
  `trangthai` INT NOT NULL,
  PRIMARY KEY (`machucnang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `taikhoan`
CREATE TABLE `taikhoan` (
  `manv` INT NOT NULL,
  `matkhau` VARCHAR(255) NOT NULL,
  `manhomquyen` INT NOT NULL,
  `tendangnhap` VARCHAR(255) NOT NULL,
  `trangthai` VARCHAR(50) NOT NULL,
  `otp` VARCHAR(255),
  PRIMARY KEY (`manv`, `tendangnhap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `sanpham`
CREATE TABLE `sanpham` (
    `masanpham` INT AUTO_INCREMENT,
    `tensp` VARCHAR(255) NOT NULL,
    `hinhanh` VARCHAR(255),
    `xuatxu` INT NOT NULL,
    `chipxuly` VARCHAR(255),
    `hedieuhanh` INT NOT NULL,
    `cameratruoc` VARCHAR(255) NOT NULL,
    `camerasau` VARCHAR(255) NOT NULL,
    `thoigianbaohanh` INT,
    `thuonghieu` INT NOT NULL,
    `gia` DECIMAL(15 , 2 ),
    `trangthai` ENUM('MoKhoa', 'Khoa') NOT NULL,
    PRIMARY KEY (`masanpham`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

-- Table `xuatxu`
CREATE TABLE `xuatxu` (
  `maxuatxu` INT AUTO_INCREMENT,
  `tenxuatxu` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`maxuatxu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `thuonghieu`
CREATE TABLE `thuonghieu` (
  `mathuonghieu` INT AUTO_INCREMENT,
  `tenthuonghieu` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`mathuonghieu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `hedieuhanh`
CREATE TABLE `hedieuhanh` (
  `mahedieuhanh` INT AUTO_INCREMENT,
  `tenhedieuhanh` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`mahedieuhanh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `khuvuckho_sanpham`
CREATE TABLE `khuvuckho_sanpham` (
  `makhuvuc` INT NOT NULL,
  `masanpham` INT NOT NULL,
  `soluong` INT NOT NULL,
  PRIMARY KEY (`makhuvuc`, `masanpham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `khuvuckho`
CREATE TABLE `khuvuckho` (
  `makhuvuc` INT AUTO_INCREMENT,
  `tenkhuvuc` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`makhuvuc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `phieunhap_trahang`
CREATE TABLE `phieunhap_trahang` (
  `phieunhap_trahang` INT AUTO_INCREMENT,
  `makhachhang` INT NOT NULL,
  `nguoitao` INT NOT NULL,
  `thoigian` DATE NOT NULL,
  `trangthai` ENUM('ChoDuyet','Duyet','Huy') NOT NULL,
  `soluong` INT NOT NULL,
  PRIMARY KEY (`phieunhap_trahang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `ctphieunhap_trahang`
CREATE TABLE `ctphieunhap_trahang` (
  `maphieunhap_trahang` INT NOT NULL,
  `maimei` INT NOT NULL,
  `dongia` DECIMAL(15,2) NOT NULL,
  PRIMARY KEY (`maphieunhap_trahang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `nhacungcap`
CREATE TABLE `nhacungcap` (
  `manhacungcap` INT AUTO_INCREMENT,
  `tennhacungcap` VARCHAR(255) NOT NULL,
  `diachi` VARCHAR(255),
  `email` VARCHAR(255),
  `sdt` VARCHAR(50),
  PRIMARY KEY (`manhacungcap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `phieuxuat_ban`
CREATE TABLE `phieuxuat_ban` (
  `maphieuxuat_ban` INT AUTO_INCREMENT,
  `makhachhang` INT NOT NULL,
  `nguoitao` INT NOT NULL,
  `thoigian` DATE NOT NULL,
  `soluong` INT NOT NULL,
  PRIMARY KEY (`maphieuxuat_ban`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `ctphieuxuat_ban`
CREATE TABLE `ctphieuxuat_ban` (
  `maphieuxuat_ban` INT NOT NULL,
  `maimei` INT NOT NULL,
  `dongia` DECIMAL(15,2) NOT NULL,
  PRIMARY KEY (`maphieuxuat_ban`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `phieukiemke`
CREATE TABLE `phieukiemke` (
  `maphieukiemke` INT AUTO_INCREMENT,
  `nguoitao` INT NOT NULL,
  `thoigian` DATE NOT NULL,
  PRIMARY KEY (`maphieukiemke`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `ctkiemke`
CREATE TABLE `ctkiemke` (
  `maphieukiemke` INT NOT NULL,
  `masanpham` INT NOT NULL,
  `soluong` INT NOT NULL,
  `chenhlech` INT NOT NULL,
  `ghichu` TEXT,
  PRIMARY KEY (`maphieukiemke`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `ctsanpham`
CREATE TABLE `ctsanpham` (
  `maimei` INT AUTO_INCREMENT,
  `makhachhang` INT,
  `masanpham` INT NOT NULL,
  `tinhtrang` FLOAT NOT NULL,
  `trangthai` ENUM('DaBan','TrongKho','ChoDuyet') NOT NULL,
  `ghichu` TEXT,
  PRIMARY KEY (`maimei`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `khachhang`
CREATE TABLE `khachhang` (
  `makhachhang` INT AUTO_INCREMENT,
  `tenkhachhang` VARCHAR(255) NOT NULL,
  `diachi` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `sdt` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`makhachhang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `phieuxuat_chuyenkho`
CREATE TABLE `phieuxuat_chuyenkho` (
  `maphieuxuat_chuyenkho` INT AUTO_INCREMENT,
  `nguoitao` INT NOT NULL,
  `makhuvuc` INT NOT NULL,
  `thoigian` DATE NOT NULL,
  `trangthai`ENUM('ChoDuyet','Duyet','Huy') NOT NULL,
  PRIMARY KEY (`maphieuxuat_chuyenkho`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `phieuxuat_huy`
CREATE TABLE `phieuxuat_huy` (
  `maphieuxuat_huy` INT AUTO_INCREMENT,
  `nguoitao` INT NOT NULL,
  `thoigian` DATE NOT NULL,
  `trangthai`ENUM('ChoDuyet','Duyet','Huy') NOT NULL,
  PRIMARY KEY (`maphieuxuat_huy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `phieunhap_chuyenkho`
CREATE TABLE `phieunhap_chuyenkho` (
  `maphieunhap_chuyenkho` INT AUTO_INCREMENT,
  `nguoitao` INT NOT NULL,
  `makhuvuc` INT NOT NULL,
  `thoigian` DATE NOT NULL,
  `trangthai`ENUM('ChoDuyet','Duyet','Huy') NOT NULL,
  PRIMARY KEY (`maphieunhap_chuyenkho`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `ctphieuxuat_huy`
CREATE TABLE `ctphieuxuat_huy` (
  `maphieuxuat_huy` INT NOT NULL,
  `maimei` INT NOT NULL,
  `lydo` TEXT,
  PRIMARY KEY (`maphieuxuat_huy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `phieunhap_nhacungcap`
CREATE TABLE `phieunhap_nhacungcap` (
  `maphieunhap_nhacungcap` INT AUTO_INCREMENT,
  `manhacungcap` INT NOT NULL,
  `nguoitao` INT NOT NULL,
  `thoigian` DATE NOT NULL,
  `trangthai`ENUM('ChoDuyet','Duyet','Huy') NOT NULL,
  `soluong` INT NOT NULL,
  PRIMARY KEY (`maphieunhap_nhacungcap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `nhantin`
CREATE TABLE `nhantin` (
  `matin` INT AUTO_INCREMENT,
  `nguoiguiid` VARCHAR(70) NOT NULL,
  `nguoiguiten` VARCHAR(50),
  `nguoinhanid` VARCHAR(70),
  `noidung` LONGTEXT,
  `giogui` VARCHAR(20),
  `ngaygui` VARCHAR(40),
  `daxemboi` LONGTEXT,
  PRIMARY KEY (`matin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `ctphieunhap_nhacungcap`
CREATE TABLE `ctphieunhap_nhacungcap` (
  `maphieunhap_nhacungcap` INT NOT NULL,
  `maimei` INT NOT NULL,
  `dongia` DECIMAL(15,2) NOT NULL,
  PRIMARY KEY (`maphieunhap_nhacungcap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `ctphieuxuat_chuyenkho`
CREATE TABLE `ctphieuxuat_chuyenkho` (
  `maphieuxuat_chuyenkho` INT NOT NULL,
  `maimei` INT NOT NULL,
  `soluong` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table `ctphieunhap_chuyenkho`
CREATE TABLE `ctphieunhap_chuyenkho` (
  `maphieunhap_chuyenkho` INT NOT NULL,
  `maimei` INT NOT NULL,
  `soluong` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Foreign key constraints
ALTER TABLE `taikhoan`               ADD CONSTRAINT `fk_taikhoan_manv`                      FOREIGN KEY (`manv`)                     REFERENCES `nhanvien`(`manv`);
ALTER TABLE `taikhoan`               ADD CONSTRAINT `fk_taikhoan_manhomquyen`               FOREIGN KEY (`manhomquyen`)              REFERENCES `nhomquyen`(`manhomquyen`);

ALTER TABLE `ctquyen`                ADD CONSTRAINT `fk_ctquyen_manhomquyen`                FOREIGN KEY (`manhomquyen`)              REFERENCES `nhomquyen`(`manhomquyen`);
ALTER TABLE `ctquyen`                ADD CONSTRAINT `fk_ctquyen_machucnang`                 FOREIGN KEY (`machucnang`)               REFERENCES `danhmucchucnang`(`machucnang`);

ALTER TABLE `sanpham`                ADD CONSTRAINT `fk_sanpham_xuatxu`                     FOREIGN KEY (`xuatxu`)                   REFERENCES `xuatxu`(`maxuatxu`);
ALTER TABLE `sanpham`                ADD CONSTRAINT `fk_sanpham_hedieuhanh`                 FOREIGN KEY (`hedieuhanh`)               REFERENCES `hedieuhanh`(`mahedieuhanh`);
ALTER TABLE `sanpham`                ADD CONSTRAINT `fk_sanpham_thuonghieu`                 FOREIGN KEY (`thuonghieu`)               REFERENCES `thuonghieu`(`mathuonghieu`);

ALTER TABLE `khuvuckho_sanpham`      ADD CONSTRAINT `fk_khuvuckho_sanpham_makhuvuc`         FOREIGN KEY (`makhuvuc`)                REFERENCES `khuvuckho`(`makhuvuc`);
ALTER TABLE `khuvuckho_sanpham`      ADD CONSTRAINT `fk_khuvuckho_sanpham_masanpham`        FOREIGN KEY (`masanpham`)               REFERENCES `sanpham`(`masanpham`);

ALTER TABLE `phieunhap_trahang`      ADD CONSTRAINT `fk_phieunhap_trahang_makhachhang`      FOREIGN KEY (`makhachhang`)             REFERENCES `khachhang`(`makhachhang`);
ALTER TABLE `phieunhap_trahang`      ADD CONSTRAINT `fk_phieunhap_trahang_nguoitao`         FOREIGN KEY (`nguoitao`)                REFERENCES `nhanvien`(`manv`);

ALTER TABLE `ctphieunhap_trahang`    ADD CONSTRAINT `fk_ctphieunhap_trahang_maphieu`        FOREIGN KEY (`maphieunhap_trahang`)     REFERENCES `phieunhap_trahang`(`phieunhap_trahang`);
ALTER TABLE `ctphieunhap_trahang`    ADD CONSTRAINT `fk_ctphieunhap_trahang_maimei`         FOREIGN KEY (`maimei`)                  REFERENCES `ctsanpham`(`maimei`);

ALTER TABLE `phieuxuat_ban`          ADD CONSTRAINT `fk_phieuxuat_ban_makhachhang`          FOREIGN KEY (`makhachhang`)             REFERENCES `khachhang`(`makhachhang`);
ALTER TABLE `phieuxuat_ban`          ADD CONSTRAINT `fk_phieuxuat_ban_nguoitao`             FOREIGN KEY (`nguoitao`)                REFERENCES `nhanvien`(`manv`);

ALTER TABLE `ctphieuxuat_ban`        ADD CONSTRAINT `fk_ctphieuxuat_ban_maphieu`            FOREIGN KEY (`maphieuxuat_ban`)         REFERENCES `phieuxuat_ban`(`maphieuxuat_ban`);
ALTER TABLE `ctphieuxuat_ban`        ADD CONSTRAINT `fk_ctphieuxuat_ban_maimei`             FOREIGN KEY (`maimei`)                  REFERENCES `ctsanpham`(`maimei`);

ALTER TABLE `phieukiemke`            ADD CONSTRAINT `fk_phieukiemke_nguoitao`               FOREIGN KEY (`nguoitao`)                REFERENCES `nhanvien`(`manv`);

ALTER TABLE `ctkiemke`               ADD CONSTRAINT `fk_ctkiemke_maphieu`                   FOREIGN KEY (`maphieukiemke`)           REFERENCES `phieukiemke`(`maphieukiemke`);
ALTER TABLE `ctkiemke`               ADD CONSTRAINT `fk_ctkiemke_masanpham`                 FOREIGN KEY (`masanpham`)               REFERENCES `sanpham`(`masanpham`);

ALTER TABLE `ctsanpham`              ADD CONSTRAINT `fk_ctsanpham_makhachhang`              FOREIGN KEY (`makhachhang`)             REFERENCES `khachhang`(`makhachhang`);
ALTER TABLE `ctsanpham`              ADD CONSTRAINT `fk_ctsanpham_masanpham`                FOREIGN KEY (`masanpham`)               REFERENCES `sanpham`(`masanpham`);

ALTER TABLE `phieuxuat_chuyenkho`    ADD CONSTRAINT `fk_phieuxuat_chuyenkho_nguoitao`       FOREIGN KEY (`nguoitao`)                REFERENCES `nhanvien`(`manv`);
ALTER TABLE `phieuxuat_chuyenkho`    ADD CONSTRAINT `fk_phieuxuat_chuyenkho_makhuvuc`       FOREIGN KEY (`makhuvuc`)                REFERENCES `khuvuckho`(`makhuvuc`);

ALTER TABLE `phieuxuat_huy`          ADD CONSTRAINT `fk_phieuxuat_huy_nguoitao`             FOREIGN KEY (`nguoitao`)                REFERENCES `nhanvien`(`manv`);

ALTER TABLE `phieunhap_chuyenkho`    ADD CONSTRAINT `fk_phieunhap_chuyenkho_nguoitao`       FOREIGN KEY (`nguoitao`)                REFERENCES `nhanvien`(`manv`);
ALTER TABLE `phieunhap_chuyenkho`    ADD CONSTRAINT `fk_phieunhap_chuyenkho_makhuvuc`       FOREIGN KEY (`makhuvuc`)                REFERENCES `khuvuckho`(`makhuvuc`);

ALTER TABLE `ctphieuxuat_huy`        ADD CONSTRAINT `fk_ctphieuxuat_huy_maphieu`            FOREIGN KEY (`maphieuxuat_huy`)         REFERENCES `phieuxuat_huy`(`maphieuxuat_huy`);
ALTER TABLE `ctphieuxuat_huy`        ADD CONSTRAINT `fk_ctphieuxuat_huy_maimei`             FOREIGN KEY (`maimei`)                  REFERENCES `ctsanpham`(`maimei`);

ALTER TABLE `phieunhap_nhacungcap`   ADD CONSTRAINT `fk_phieunhap_nhacungcap_manhacungcap`  FOREIGN KEY (`manhacungcap`)            REFERENCES `nhacungcap`(`manhacungcap`);
ALTER TABLE `phieunhap_nhacungcap`   ADD CONSTRAINT `fk_phieunhap_nhacungcap_nguoitao`      FOREIGN KEY (`nguoitao`)                REFERENCES `nhanvien`(`manv`);

ALTER TABLE `ctphieunhap_nhacungcap` ADD CONSTRAINT `fk_ctphieunhap_nhacungcap_maphieu`     FOREIGN KEY (`maphieunhap_nhacungcap`)  REFERENCES `phieunhap_nhacungcap`(`maphieunhap_nhacungcap`);
ALTER TABLE `ctphieunhap_nhacungcap` ADD CONSTRAINT `fk_ctphieunhap_nhacungcap_maimei`      FOREIGN KEY (`maimei`)                  REFERENCES `ctsanpham`(`maimei`);

ALTER TABLE `ctphieuxuat_chuyenkho`  ADD CONSTRAINT `fk_ctphieuxuat_chuyenkho_maphieu`      FOREIGN KEY (`maphieuxuat_chuyenkho`)   REFERENCES `phieuxuat_chuyenkho`(`maphieuxuat_chuyenkho`);
ALTER TABLE `ctphieuxuat_chuyenkho`  ADD CONSTRAINT `fk_ctphieuxuat_chuyenkho_maimei`       FOREIGN KEY (`maimei`)                  REFERENCES `ctsanpham`(`maimei`);

ALTER TABLE `ctphieunhap_chuyenkho`  ADD CONSTRAINT `fk_ctphieunhap_chuyenkho_maphieu`      FOREIGN KEY (`maphieunhap_chuyenkho`)   REFERENCES `phieunhap_chuyenkho`(`maphieunhap_chuyenkho`);
ALTER TABLE `ctphieunhap_chuyenkho`  ADD CONSTRAINT `fk_ctphieunhap_chuyenkho_maimei`       FOREIGN KEY (`maimei`)                  REFERENCES `ctsanpham`(`maimei`);

SET FOREIGN_KEY_CHECKS=1;

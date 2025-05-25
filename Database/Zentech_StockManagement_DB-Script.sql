CREATE DATABASE zentech_stock_management;
USE zentech_stock_management;

/* Independent Tables (No Foreign Key Dependencies) */
/* Bảng danh mục chức năng - Lưu trữ các chức năng của hệ thống */
CREATE TABLE `danhmucchucnang` (
  `machucnang` varchar(50) NOT NULL,
  `tenchucnang` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL,
  PRIMARY KEY (`machucnang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng dung lượng RAM - Lưu trữ thông tin về các loại RAM */
CREATE TABLE `dungluongram` (
  `madlram` int(11) NOT NULL AUTO_INCREMENT,
  `kichthuocram` int(11) DEFAULT NULL,
  `trangthai` tinyint(4) DEFAULT 1,
  PRIMARY KEY (`madlram`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng dung lượng ROM - Lưu trữ thông tin về các loại bộ nhớ trong */
CREATE TABLE `dungluongrom` (
  `madlrom` int(11) NOT NULL AUTO_INCREMENT,
  `kichthuocrom` int(11) DEFAULT NULL,
  `trangthai` tinyint(4) DEFAULT 1,
  PRIMARY KEY (`madlrom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng hệ điều hành - Lưu trữ thông tin về các hệ điều hành */
CREATE TABLE `hedieuhanh` (
  `mahedieuhanh` int(11) NOT NULL AUTO_INCREMENT,
  `tenhedieuhanh` varchar(255) NOT NULL,
  `trangthai` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`mahedieuhanh`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng khách hàng - Lưu trữ thông tin khách hàng */
CREATE TABLE `khachhang` (
  `makh` int(11) NOT NULL AUTO_INCREMENT,
  `tenkhachhang` varchar(255) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `sdt` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL,
  `ngaythamgia` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`makh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng khu vực kho - Quản lý các khu vực trong kho */
CREATE TABLE `khuvuckho` (
  `makhuvuc` int(11) NOT NULL AUTO_INCREMENT,
  `tenkhuvuc` varchar(255) NOT NULL,
  `ghichu` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL,
  PRIMARY KEY (`makhuvuc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng màu sắc - Lưu trữ thông tin về các màu sắc sản phẩm */
CREATE TABLE `mausac` (
  `mamau` int(11) NOT NULL AUTO_INCREMENT,
  `tenmau` varchar(50) NOT NULL DEFAULT '0',
  `trangthai` tinyint(4) DEFAULT 1,
  PRIMARY KEY (`mamau`),
  UNIQUE KEY `tenmau` (`tenmau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng nhà cung cấp - Lưu trữ thông tin nhà cung cấp */
CREATE TABLE `nhacungcap` (
  `manhacungcap` int(11) NOT NULL AUTO_INCREMENT,
  `tennhacungcap` varchar(255) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `sdt` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`manhacungcap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng nhân viên - Lưu trữ thông tin nhân viên */
CREATE TABLE `nhanvien` (
  `manv` int(11) NOT NULL AUTO_INCREMENT,
  `hoten` varchar(255) NOT NULL,
  `gioitinh` int(11) NOT NULL,
  `ngaysinh` date NOT NULL,
  `sdt` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL,
  PRIMARY KEY (`manv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng nhóm quyền - Quản lý các nhóm quyền trong hệ thống */
CREATE TABLE `nhomquyen` (
  `manhomquyen` int(11) NOT NULL AUTO_INCREMENT,
  `tennhomquyen` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL,
  PRIMARY KEY (`manhomquyen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng thương hiệu - Lưu trữ thông tin về các thương hiệu sản phẩm */
CREATE TABLE `thuonghieu` (
  `mathuonghieu` int(11) NOT NULL AUTO_INCREMENT,
  `tenthuonghieu` varchar(255) NOT NULL,
  `trangthai` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`mathuonghieu`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng xuất xứ - Lưu trữ thông tin về nơi xuất xứ sản phẩm */
CREATE TABLE `xuatxu` (
  `maxuatxu` int(11) NOT NULL AUTO_INCREMENT,
  `tenxuatxu` varchar(50) NOT NULL,
  `trangthai` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`maxuatxu`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Tables with Dependencies */
/* Bảng tài khoản - Quản lý tài khoản đăng nhập của nhân viên */
CREATE TABLE `taikhoan` (
  `manv` int(11) NOT NULL,
  `matkhau` varchar(255) NOT NULL,
  `manhomquyen` int(11) NOT NULL,
  `tendangnhap` varchar(50) NOT NULL DEFAULT '',
  `trangthai` int(11) NOT NULL,
  `otp` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`tendangnhap`),
  FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`),
  FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng sản phẩm - Lưu trữ thông tin chi tiết về sản phẩm */
CREATE TABLE `sanpham` (
  `masp` int(11) NOT NULL AUTO_INCREMENT,
  `tensp` varchar(255) DEFAULT NULL,
  `hinhanh` varchar(255) DEFAULT NULL,
  `xuatxu` int(11) DEFAULT NULL,
  `chipxuly` varchar(255) DEFAULT NULL,
  `dungluongpin` int(11) DEFAULT NULL,
  `kichthuocman` double DEFAULT NULL,
  `hedieuhanh` int(11) DEFAULT NULL,
  `phienbanhdh` int(11) DEFAULT NULL,
  `camerasau` varchar(255) DEFAULT NULL,
  `cameratruoc` varchar(255) DEFAULT NULL,
  `thoigianbaohanh` int(11) DEFAULT NULL,
  `thuonghieu` int(11) DEFAULT NULL,
  `khuvuckho` int(11) DEFAULT NULL,
  `soluongton` int(11) DEFAULT 0,
  `trangthai` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`masp`) USING BTREE,
  FOREIGN KEY (`xuatxu`) REFERENCES `xuatxu` (`maxuatxu`),
  FOREIGN KEY (`hedieuhanh`) REFERENCES `hedieuhanh` (`mahedieuhanh`),
  FOREIGN KEY (`phienbanhdh`) REFERENCES `hedieuhanh` (`mahedieuhanh`),
  FOREIGN KEY (`thuonghieu`) REFERENCES `thuonghieu` (`mathuonghieu`),
  FOREIGN KEY (`khuvuckho`) REFERENCES `khuvuckho` (`makhuvuc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng phiên bản sản phẩm - Lưu trữ các biến thể của sản phẩm (RAM, ROM, màu sắc) */
CREATE TABLE `phienbansanpham` (
  `maphienbansp` int(11) NOT NULL AUTO_INCREMENT,
  `masp` int(11) DEFAULT NULL,
  `rom` int(11) DEFAULT NULL,
  `ram` int(11) DEFAULT 0,
  `mausac` int(11) DEFAULT NULL,
  `gianhap` int(11) DEFAULT NULL,
  `giaxuat` int(11) DEFAULT NULL,
  `soluong` int(11) DEFAULT 0,
  `trangthai` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`maphienbansp`) USING BTREE,
  FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`),
  FOREIGN KEY (`rom`) REFERENCES `dungluongrom` (`madlrom`),
  FOREIGN KEY (`ram`) REFERENCES `dungluongram` (`madlram`),
  FOREIGN KEY (`mausac`) REFERENCES `mausac` (`mamau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng phiếu kiểm kê - Lưu trữ thông tin phiếu kiểm kê kho */
CREATE TABLE `phieukiemke` (
  `maphieu` int(11) NOT NULL AUTO_INCREMENT,
  `thoigian` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `nguoitaophieukiemke` varchar(50) NOT NULL,
  PRIMARY KEY (`maphieu`),
  FOREIGN KEY (`nguoitaophieukiemke`) REFERENCES `taikhoan` (`tendangnhap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng phiếu nhập - Lưu trữ thông tin phiếu nhập hàng */
CREATE TABLE `phieunhap` (
  `maphieunhap` int(11) NOT NULL AUTO_INCREMENT,
  `thoigian` datetime DEFAULT current_timestamp(),
  `manhacungcap` int(11) NOT NULL,
  `nguoitao` varchar(50) NOT NULL,
  `tongtien` bigint(20) NOT NULL DEFAULT 0,
  `trangthai` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`maphieunhap`),
  FOREIGN KEY (`manhacungcap`) REFERENCES `nhacungcap` (`manhacungcap`),
  FOREIGN KEY (`nguoitao`) REFERENCES `taikhoan` (`tendangnhap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng phiếu xuất - Lưu trữ thông tin phiếu xuất hàng */
CREATE TABLE `phieuxuat` (
  `maphieuxuat` int(11) NOT NULL AUTO_INCREMENT,
  `thoigian` datetime NOT NULL DEFAULT current_timestamp(),
  `tongtien` bigint(20) DEFAULT NULL,
  `nguoitaophieuxuat` varchar(50) DEFAULT NULL,
  `makh` int(11) DEFAULT NULL,
  `trangthai` int(11) DEFAULT NULL,
  PRIMARY KEY (`maphieuxuat`),
  FOREIGN KEY (`nguoitaophieuxuat`) REFERENCES `taikhoan` (`tendangnhap`),
  FOREIGN KEY (`makh`) REFERENCES `khachhang` (`makh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng chi tiết quyền - Lưu trữ chi tiết quyền của từng nhóm quyền */
CREATE TABLE `ctquyen` (
  `manhomquyen` int(11) NOT NULL,
  `machucnang` varchar(50) NOT NULL,
  `hanhdong` varchar(50) NOT NULL,
  PRIMARY KEY (`manhomquyen`, `machucnang`, `hanhdong`) USING BTREE,
  FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`),
  FOREIGN KEY (`machucnang`) REFERENCES `danhmucchucnang` (`machucnang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng chi tiết phiếu nhập - Lưu trữ thông tin chi tiết của từng phiếu nhập */
CREATE TABLE `ctphieunhap` (
  `maphieunhap` int(11) NOT NULL,
  `maphienbansp` int(11) NOT NULL DEFAULT 0,
  `soluong` int(11) NOT NULL DEFAULT 0,
  `dongia` int(11) NOT NULL DEFAULT 0,
  `hinhthucnhap` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`maphieunhap`, `maphienbansp`),
  FOREIGN KEY (`maphieunhap`) REFERENCES `phieunhap` (`maphieunhap`),
  FOREIGN KEY (`maphienbansp`) REFERENCES `phienbansanpham` (`maphienbansp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng chi tiết phiếu xuất - Lưu trữ thông tin chi tiết của từng phiếu xuất */
CREATE TABLE `ctphieuxuat` (
  `maphieuxuat` int(11) NOT NULL,
  `maphienbansp` int(11) NOT NULL DEFAULT 0,
  `soluong` int(11) NOT NULL DEFAULT 0,
  `dongia` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`maphieuxuat`, `maphienbansp`),
  FOREIGN KEY (`maphieuxuat`) REFERENCES `phieuxuat` (`maphieuxuat`),
  FOREIGN KEY (`maphienbansp`) REFERENCES `phienbansanpham` (`maphienbansp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng chi tiết sản phẩm - Lưu trữ thông tin chi tiết từng sản phẩm theo IMEI */
CREATE TABLE `ctsanpham` (
  `maimei` varchar(255) NOT NULL DEFAULT 'AUTO_INCREMENT' COMMENT 'Mã imei của sản phẩm',
  `maphienbansp` int(11) NOT NULL,
  `maphieunhap` int(11) NOT NULL,
  `maphieuxuat` int(11) DEFAULT NULL,
  `tinhtrang` int(11) NOT NULL,
  PRIMARY KEY (`maimei`) USING BTREE,
  FOREIGN KEY (`maphienbansp`) REFERENCES `phienbansanpham` (`maphienbansp`),
  FOREIGN KEY (`maphieunhap`) REFERENCES `phieunhap` (`maphieunhap`),
  FOREIGN KEY (`maphieuxuat`) REFERENCES `phieuxuat` (`maphieuxuat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng chi tiết kiểm kê - Lưu trữ thông tin chi tiết của phiếu kiểm kê */
CREATE TABLE `ctkiemke` (
  `maphieukiemmke` int(11) NOT NULL COMMENT 'Mã phiếu kiểm kê',
  `masanpham` int(11) NOT NULL COMMENT 'Mã sản phẩm',
  `soluong` int(11) NOT NULL,
  `chenhlech` int(11) NOT NULL,
  `ghichu` varchar(255) NOT NULL,
  PRIMARY KEY (`maphieukiemmke`, `masanpham`),
  FOREIGN KEY (`maphieukiemmke`) REFERENCES `phieukiemke` (`maphieu`),
  FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng phiếu bảo hành - Lưu trữ thông tin phiếu bảo hành sản phẩm */
CREATE TABLE `phieubaohanh` (
  `maphieubaohanh` int(11) NOT NULL AUTO_INCREMENT,
  `maimei` varchar(255) NOT NULL,
  `lydo` varchar(50) NOT NULL,
  `thoigian` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `thoigiantra` datetime DEFAULT NULL,
  `nguoitao` varchar(50) NOT NULL,
  PRIMARY KEY (`maphieubaohanh`),
  FOREIGN KEY (`maimei`) REFERENCES `ctsanpham` (`maimei`),
  FOREIGN KEY (`nguoitao`) REFERENCES `taikhoan` (`tendangnhap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng phiếu đổi - Lưu trữ thông tin phiếu đổi sản phẩm */
CREATE TABLE `phieudoi` (
  `maphieudoi` tinyint(4) NOT NULL DEFAULT 0,
  `maimei` varchar(255) NOT NULL,
  `lydo` varchar(255) NOT NULL,
  `thoigian` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nguoitao` varchar(50) NOT NULL,
  PRIMARY KEY (`maphieudoi`, `maimei`),
  FOREIGN KEY (`maimei`) REFERENCES `ctsanpham` (`maimei`),
  FOREIGN KEY (`nguoitao`) REFERENCES `taikhoan` (`tendangnhap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng phiếu trả - Lưu trữ thông tin phiếu trả hàng */
CREATE TABLE `phieutra` (
  `maphieutra` int(11) NOT NULL AUTO_INCREMENT,
  `maimei` varchar(255) NOT NULL,
  `lydo` varchar(255) NOT NULL,
  `thoigian` datetime DEFAULT CURRENT_TIMESTAMP(),
  `nguoitao` varchar(50) NOT NULL,
  PRIMARY KEY (`maphieutra`, `maimei`),
  FOREIGN KEY (`maimei`) REFERENCES `ctsanpham` (`maimei`),
  FOREIGN KEY (`nguoitao`) REFERENCES `taikhoan` (`tendangnhap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng chi tiết kiểm kê sản phẩm - Lưu trữ chi tiết kiểm kê theo IMEI */
CREATE TABLE `ctkiemkesanpham` (
  `maphieukiemmke` int(11) NOT NULL,
  `maphienbansp` int(11) NOT NULL,
  `maimei` varchar(255) NOT NULL,
  `trangthai` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`maphieukiemmke`, `maphienbansp`, `maimei`),
  FOREIGN KEY (`maphieukiemmke`) REFERENCES `phieukiemke` (`maphieu`),
  FOREIGN KEY (`maphienbansp`) REFERENCES `phienbansanpham` (`maphienbansp`),
  FOREIGN KEY (`maimei`) REFERENCES `ctsanpham` (`maimei`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng chi tiết phiếu đổi - Lưu trữ thông tin chi tiết của phiếu đổi hàng */
CREATE TABLE `ctphieudoi` (
  `maphieudoi` tinyint(11) NOT NULL,
  `maimei_doi` varchar(255) NOT NULL,
  `maimei_moi` varchar(255) NOT NULL,
  `lydo` varchar(255) NOT NULL,
  PRIMARY KEY (`maphieudoi`, `maimei_doi`),
  FOREIGN KEY (`maphieudoi`, `maimei_doi`) REFERENCES `phieudoi` (`maphieudoi`, `maimei`),
  FOREIGN KEY (`maimei_doi`) REFERENCES `ctsanpham` (`maimei`),
  FOREIGN KEY (`maimei_moi`) REFERENCES `ctsanpham` (`maimei`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* Bảng chi tiết phiếu trả - Lưu trữ thông tin chi tiết của phiếu trả hàng */
CREATE TABLE `ctphieutra` (
  `maphieutra` int(11) NOT NULL,
  `maimei_tra` varchar(255) NOT NULL,
  `lydo` varchar(255) NOT NULL,
  `gia_thu_vao` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`maphieutra`, `maimei_tra`),
  FOREIGN KEY (`maphieutra`, `maimei_tra`) REFERENCES `phieutra` (`maphieutra`, `maimei`),
  FOREIGN KEY (`maimei_tra`) REFERENCES `ctsanpham` (`maimei`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
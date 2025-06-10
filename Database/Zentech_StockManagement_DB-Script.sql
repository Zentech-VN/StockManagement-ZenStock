CREATE DATABASE zentechStockManagement;
USE zentechStockManagement;

-- Danh mục chức năng
CREATE TABLE danhmucchucnang (
  machucnang VARCHAR(50) PRIMARY KEY,
  tenchucnang VARCHAR(100) NOT NULL,
  trangthai INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Nhóm quyền
CREATE TABLE nhomquyen (
  manhomquyen INT AUTO_INCREMENT PRIMARY KEY,
  tennhomquyen VARCHAR(255) NOT NULL,
  trangthai INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Chi tiết quyền (nhóm <-> chức năng)
CREATE TABLE ctquyen (
  manhomquyen INT NOT NULL,
  machucnang VARCHAR(50) NOT NULL,
  hanhdong VARCHAR(255) NOT NULL,
  PRIMARY KEY (manhomquyen, machucnang, hanhdong),
  FOREIGN KEY (manhomquyen) REFERENCES nhomquyen(manhomquyen),
  FOREIGN KEY (machucnang) REFERENCES danhmucchucnang(machucnang)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Xuất xứ
CREATE TABLE xuatxu (
  maxuatxu INT AUTO_INCREMENT PRIMARY KEY,
  tenxuatxu VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Hệ điều hành
CREATE TABLE hedieuchanh (
  mahedieuchanh INT AUTO_INCREMENT PRIMARY KEY,
  tenhedieuchanh VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Thương hiệu
CREATE TABLE thuonghieu (
  mathuonghieu INT AUTO_INCREMENT PRIMARY KEY,
  tenthuonghieu VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Sản phẩm
CREATE TABLE sanpham (
  masanpham INT AUTO_INCREMENT PRIMARY KEY,
  tensp VARCHAR(255) NOT NULL,
  hinhanh VARCHAR(255),
  maxuatxu INT NOT NULL,
  chipxuly VARCHAR(255) NOT NULL,
  mahedieuchanh INT NOT NULL,
  cameratruoc VARCHAR(255),
  camerasau VARCHAR(255),
  thongso INT NOT NULL,
  gia DECIMAL(15,2),
  trangthai TINYINT,
  mathuonghieu INT NOT NULL,
  dungluongpin VARCHAR(50),
  kichthuocmanhinh VARCHAR(50),
  thoigianbaohanh VARCHAR(50),
  FOREIGN KEY (maxuatxu) REFERENCES xuatxu(maxuatxu),
  FOREIGN KEY (mahedieuchanh) REFERENCES hedieuchanh(mahedieuchanh),
  FOREIGN KEY (mathuonghieu) REFERENCES thuonghieu(mathuonghieu)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Khu vực
CREATE TABLE khuvuc (
  makhuvuc INT AUTO_INCREMENT PRIMARY KEY,
  tenkhuvuc VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Khu vực - Sản phẩm (bảng trung gian)
CREATE TABLE khuvuc_sanpham (
  makhuvuc INT NOT NULL,
  masanpham INT NOT NULL,
  PRIMARY KEY (makhuvuc, masanpham),
  FOREIGN KEY (makhuvuc) REFERENCES khuvuc(makhuvuc),
  FOREIGN KEY (masanpham) REFERENCES sanpham(masanpham)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Nhà cung cấp
CREATE TABLE nhacungcap (
  manhacungcap INT AUTO_INCREMENT PRIMARY KEY,
  tennhacungcap VARCHAR(255) NOT NULL,
  diachi VARCHAR(255),
  email VARCHAR(255),
  sdt VARCHAR(10)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Khách hàng
CREATE TABLE khachhang (
  makhachhang INT AUTO_INCREMENT PRIMARY KEY,
  tenkhachhang VARCHAR(255) NOT NULL,
  diachi VARCHAR(255),
  sdt VARCHAR(10)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Nhân viên
CREATE TABLE nhanvien (
  manv INT AUTO_INCREMENT PRIMARY KEY,
  hoten VARCHAR(255) NOT NULL,
  gioitinh TINYINT NOT NULL,
  ngaysinh DATE NOT NULL,
  sdt VARCHAR(10) NOT NULL,
  email VARCHAR(255) NOT NULL,
  trangthai TINYINT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tài khoản
CREATE TABLE taikhoan (
  manv INT AUTO_INCREMENT PRIMARY KEY,
  tendangnhap VARCHAR(100) NOT NULL,
  matkhau VARCHAR(255) NOT NULL,
  manhomquyen INT NOT NULL,
  trangthai VARCHAR(50) NOT NULL,
  otp VARCHAR(255),
  FOREIGN KEY (manv) REFERENCES nhanvien(manv),
  FOREIGN KEY (manhomquyen) REFERENCES nhomquyen(manhomquyen)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Phiếu nhập
CREATE TABLE phieunhap (
  maphieunhap INT AUTO_INCREMENT PRIMARY KEY,
  manhacungcap INT NOT NULL,
  manhanvien INT NOT NULL,
  thoigian DATE NOT NULL,
  trangthai TINYINT NOT NULL,
  FOREIGN KEY (manhacungcap) REFERENCES nhacungcap(manhacungcap),
  FOREIGN KEY (manhanvien) REFERENCES nhanvien(manv)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Chi tiết phiếu nhập
CREATE TABLE ctphieunhap (
  mactphieunhap INT AUTO_INCREMENT PRIMARY KEY,
  maphieunhap INT NOT NULL,
  masanpham INT NOT NULL,
  soluong INT NOT NULL,
  dongia DECIMAL(15,2) NOT NULL,
  FOREIGN KEY (maphieunhap) REFERENCES phieunhap(maphieunhap),
  FOREIGN KEY (masanpham) REFERENCES sanpham(masanpham)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Phiếu xuất
CREATE TABLE phieuxuat (
  maphieuxuat INT AUTO_INCREMENT PRIMARY KEY,
  makhachhang INT NOT NULL,
  manhanvien INT NOT NULL,
  thoigian DATE NOT NULL,
  trangthai TINYINT NOT NULL,
  FOREIGN KEY (makhachhang) REFERENCES khachhang(makhachhang),
  FOREIGN KEY (manhanvien) REFERENCES nhanvien(manv)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Chi tiết phiếu xuất
CREATE TABLE ctphieuxuat (
  mactphieuxuat INT AUTO_INCREMENT PRIMARY KEY,
  maphieuxuat INT NOT NULL,
  masanpham INT NOT NULL,
  soluong INT NOT NULL,
  dongia DECIMAL(15,2) NOT NULL,
  FOREIGN KEY (maphieuxuat) REFERENCES phieuxuat(maphieuxuat),
  FOREIGN KEY (masanpham) REFERENCES sanpham(masanpham)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Phiếu kiểm kê
CREATE TABLE phieukiemke (
  maphieukiemke INT AUTO_INCREMENT PRIMARY KEY,
  manhanvien INT NOT NULL,
  thoigian DATE NOT NULL,
  FOREIGN KEY (manhanvien) REFERENCES nhanvien(manv)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Chi tiết phiếu kiểm kê (1-n với sanpham)
CREATE TABLE ctkiemke (
  maphieukiemke INT NOT NULL,
  masanpham INT NOT NULL,
  soluong INT NOT NULL,
  chenhlech INT NOT NULL,
  ghichu VARCHAR(255),
  PRIMARY KEY (maphieukiemke, masanpham),
  FOREIGN KEY (maphieukiemke) REFERENCES phieukiemke(maphieukiemke),
  FOREIGN KEY (masanpham) REFERENCES sanpham(masanpham)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

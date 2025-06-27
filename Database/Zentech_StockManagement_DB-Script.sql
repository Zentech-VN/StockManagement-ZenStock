-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: zentechStockManagement
-- ------------------------------------------------------
-- Server version	8.0.42-0ubuntu0.24.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ctkiemke`
--

DROP TABLE IF EXISTS `ctkiemke`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ctkiemke` (
  `maphieukiemke` int NOT NULL,
  `masanpham` int NOT NULL,
  `soluong` int NOT NULL,
  `chenhlech` int NOT NULL,
  `ghichu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`maphieukiemke`,`masanpham`),
  KEY `masanpham` (`masanpham`),
  CONSTRAINT `ctkiemke_ibfk_1` FOREIGN KEY (`maphieukiemke`) REFERENCES `phieukiemke` (`maphieukiemke`),
  CONSTRAINT `ctkiemke_ibfk_2` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctkiemke`
--

LOCK TABLES `ctkiemke` WRITE;
/*!40000 ALTER TABLE `ctkiemke` DISABLE KEYS */;
/*!40000 ALTER TABLE `ctkiemke` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctphieunhap`
--

DROP TABLE IF EXISTS `ctphieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ctphieunhap` (
  `mactphieunhap` int NOT NULL AUTO_INCREMENT,
  `maphieunhap` int NOT NULL,
  `masanpham` int NOT NULL,
  `soluong` int NOT NULL,
  `dongia` decimal(15,2) NOT NULL,
  PRIMARY KEY (`mactphieunhap`),
  KEY `maphieunhap` (`maphieunhap`),
  KEY `masanpham` (`masanpham`),
  CONSTRAINT `ctphieunhap_ibfk_1` FOREIGN KEY (`maphieunhap`) REFERENCES `phieunhap` (`maphieunhap`),
  CONSTRAINT `ctphieunhap_ibfk_2` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctphieunhap`
--

LOCK TABLES `ctphieunhap` WRITE;
/*!40000 ALTER TABLE `ctphieunhap` DISABLE KEYS */;
/*!40000 ALTER TABLE `ctphieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctphieuxuat`
--

DROP TABLE IF EXISTS `ctphieuxuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ctphieuxuat` (
  `mactphieuxuat` int NOT NULL AUTO_INCREMENT,
  `maphieuxuat` int NOT NULL,
  `masanpham` int NOT NULL,
  `soluong` int NOT NULL,
  `dongia` decimal(15,2) NOT NULL,
  PRIMARY KEY (`mactphieuxuat`),
  KEY `maphieuxuat` (`maphieuxuat`),
  KEY `masanpham` (`masanpham`),
  CONSTRAINT `ctphieuxuat_ibfk_1` FOREIGN KEY (`maphieuxuat`) REFERENCES `phieuxuat` (`maphieuxuat`),
  CONSTRAINT `ctphieuxuat_ibfk_2` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctphieuxuat`
--

LOCK TABLES `ctphieuxuat` WRITE;
/*!40000 ALTER TABLE `ctphieuxuat` DISABLE KEYS */;
/*!40000 ALTER TABLE `ctphieuxuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctquyen`
--

DROP TABLE IF EXISTS `ctquyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ctquyen` (
  `manhomquyen` int NOT NULL,
  `machucnang` varchar(50) NOT NULL,
  `hanhdong` varchar(255) NOT NULL,
  PRIMARY KEY (`manhomquyen`,`machucnang`,`hanhdong`),
  KEY `machucnang` (`machucnang`),
  CONSTRAINT `ctquyen_ibfk_1` FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`),
  CONSTRAINT `ctquyen_ibfk_2` FOREIGN KEY (`machucnang`) REFERENCES `danhmucchucnang` (`machucnang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctquyen`
--

LOCK TABLES `ctquyen` WRITE;
/*!40000 ALTER TABLE `ctquyen` DISABLE KEYS */;
INSERT INTO `ctquyen` VALUES (4,'donvitinh','view'),(1,'khachhang','create'),(1,'khachhang','delete'),(1,'khachhang','update'),(1,'khachhang','view'),(3,'khachhang','create'),(3,'khachhang','update'),(3,'khachhang','view'),(5,'khachhang','view'),(1,'khuvuckho','create'),(1,'khuvuckho','delete'),(1,'khuvuckho','update'),(1,'khuvuckho','view'),(2,'khuvuckho','create'),(2,'khuvuckho','update'),(2,'khuvuckho','view'),(4,'khuvuckho','view'),(5,'khuvuckho','view'),(4,'kiemke','view'),(4,'loaisanpham','view'),(1,'nhacungcap','create'),(1,'nhacungcap','delete'),(1,'nhacungcap','update'),(1,'nhacungcap','view'),(2,'nhacungcap','create'),(2,'nhacungcap','update'),(2,'nhacungcap','view'),(4,'nhacungcap','view'),(1,'nhanvien','create'),(1,'nhanvien','delete'),(1,'nhanvien','update'),(1,'nhanvien','view'),(1,'nhaphang','create'),(1,'nhaphang','delete'),(1,'nhaphang','update'),(1,'nhaphang','view'),(2,'nhaphang','create'),(2,'nhaphang','update'),(2,'nhaphang','view'),(1,'nhomquyen','create'),(1,'nhomquyen','delete'),(1,'nhomquyen','update'),(1,'nhomquyen','view'),(1,'sanpham','create'),(1,'sanpham','delete'),(1,'sanpham','update'),(1,'sanpham','view'),(2,'sanpham','create'),(2,'sanpham','update'),(2,'sanpham','view'),(3,'sanpham','update'),(3,'sanpham','view'),(1,'taikhoan','create'),(1,'taikhoan','delete'),(1,'taikhoan','update'),(1,'taikhoan','view'),(1,'thongke','create'),(1,'thongke','delete'),(1,'thongke','update'),(1,'thongke','view'),(1,'thuoctinh','create'),(1,'thuoctinh','delete'),(1,'thuoctinh','update'),(1,'thuoctinh','view'),(2,'thuoctinh','create'),(2,'thuoctinh','delete'),(2,'thuoctinh','update'),(2,'thuoctinh','view'),(1,'xuathang','create'),(1,'xuathang','delete'),(1,'xuathang','update'),(1,'xuathang','view'),(3,'xuathang','create'),(3,'xuathang','update'),(3,'xuathang','view');
/*!40000 ALTER TABLE `ctquyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danhmucchucnang`
--

DROP TABLE IF EXISTS `danhmucchucnang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danhmucchucnang` (
  `machucnang` varchar(50) NOT NULL,
  `tenchucnang` varchar(100) NOT NULL,
  `trangthai` int NOT NULL,
  PRIMARY KEY (`machucnang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danhmucchucnang`
--

LOCK TABLES `danhmucchucnang` WRITE;
/*!40000 ALTER TABLE `danhmucchucnang` DISABLE KEYS */;
INSERT INTO `danhmucchucnang` VALUES ('donvitinh','Quản lý kho',0),('khachhang','Quản lý khách hàng',0),('khuvuckho','Quản lý khu vực kho',0),('kiemke','Quản lý kho',0),('loaisanpham','Quản lý sản phẩm',0),('nhacungcap','Quản lý nhà cung cấp',0),('nhanvien','Quản lý nhân viên',0),('nhaphang','Quản lý nhập hàng',0),('nhomquyen','Quản lý nhóm quyền',0),('sanpham','Quản lý sản phẩm',0),('taikhoan','Quản lý tài khoản',0),('thongke','Quản lý thống kê',0),('thuoctinh','Quản lý thuộc tính',0),('xuathang','Quản lý xuất hàng',0);
/*!40000 ALTER TABLE `danhmucchucnang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hedieuhanh`
--

DROP TABLE IF EXISTS `hedieuhanh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hedieuhanh` (
  `mahedieuhanh` int NOT NULL AUTO_INCREMENT,
  `tenhedieuhanh` varchar(255) NOT NULL,
  PRIMARY KEY (`mahedieuhanh`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hedieuhanh`
--

LOCK TABLES `hedieuhanh` WRITE;
/*!40000 ALTER TABLE `hedieuhanh` DISABLE KEYS */;
INSERT INTO `hedieuhanh` VALUES (1,'Android'),(2,'iOS'),(3,'HarmonyOS'),(4,'Windows Phone');
/*!40000 ALTER TABLE `hedieuhanh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `makhachhang` int NOT NULL AUTO_INCREMENT,
  `tenkhachhang` varchar(255) NOT NULL,
  `diachi` varchar(255) DEFAULT NULL,
  `sdt` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`makhachhang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khuvuc`
--

DROP TABLE IF EXISTS `khuvuc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khuvuc` (
  `makhuvuc` int NOT NULL AUTO_INCREMENT,
  `tenkhuvuc` varchar(255) NOT NULL,
  PRIMARY KEY (`makhuvuc`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khuvuc`
--

LOCK TABLES `khuvuc` WRITE;
/*!40000 ALTER TABLE `khuvuc` DISABLE KEYS */;
INSERT INTO `khuvuc` VALUES (1,'Sao hỏa'),(2,'Testtest');
/*!40000 ALTER TABLE `khuvuc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khuvuc_sanpham`
--

DROP TABLE IF EXISTS `khuvuc_sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khuvuc_sanpham` (
  `makhuvuc` int NOT NULL,
  `masanpham` int NOT NULL,
  PRIMARY KEY (`makhuvuc`,`masanpham`),
  KEY `masanpham` (`masanpham`),
  CONSTRAINT `khuvuc_sanpham_ibfk_1` FOREIGN KEY (`makhuvuc`) REFERENCES `khuvuc` (`makhuvuc`),
  CONSTRAINT `khuvuc_sanpham_ibfk_2` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nhantin`
--
CREATE TABLE `nhantin` (
  `matin` int(11) NOT NULL,
  `nguoiguiid` varchar(70) DEFAULT NULL,
  `nguoiguiten` varchar(50) DEFAULT NULL,
  `nguoinhanid` varchar(70) DEFAULT NULL,
  `noidung` longtext DEFAULT NULL,
  `giogui` varchar(20) DEFAULT NULL,
  `ngaygui` varchar(40) DEFAULT NULL,
  `daxemboi` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khuvuc_sanpham`
--

LOCK TABLES `khuvuc_sanpham` WRITE;
/*!40000 ALTER TABLE `khuvuc_sanpham` DISABLE KEYS */;
/*!40000 ALTER TABLE `khuvuc_sanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhacungcap`
--

DROP TABLE IF EXISTS `nhacungcap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhacungcap` (
  `manhacungcap` int NOT NULL AUTO_INCREMENT,
  `tennhacungcap` varchar(255) NOT NULL,
  `diachi` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sdt` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`manhacungcap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhacungcap`
--

LOCK TABLES `nhacungcap` WRITE;
/*!40000 ALTER TABLE `nhacungcap` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhacungcap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `manv` int NOT NULL AUTO_INCREMENT,
  `hoten` varchar(255) NOT NULL,
  `gioitinh` tinyint NOT NULL,
  `ngaysinh` date NOT NULL,
  `sdt` varchar(10) NOT NULL,
  `email` varchar(255) NOT NULL,
  `trangthai` tinyint NOT NULL,
  PRIMARY KEY (`manv`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'Phạm Ngọc Đức',1,'2008-08-08','0999999999','ducphamngoc39@gmail.com',1),(2,'Nguyễn Quang Minh',1,'2025-06-04','0928374839','nguyenminh1301.dev@gmail.com',1),(3,'Khôi',1,'2025-06-10','0928374938','khoi@gmail.com',1),(4,'Dương',1,'2025-06-09','0938495849','duong@gmail.com',1),(5,'Phúc',1,'2025-06-01','0938273849','phuc@gmail.com',1);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhomquyen`
--

DROP TABLE IF EXISTS `nhomquyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhomquyen` (
  `manhomquyen` int NOT NULL AUTO_INCREMENT,
  `tennhomquyen` varchar(255) NOT NULL,
  `trangthai` int NOT NULL,
  PRIMARY KEY (`manhomquyen`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhomquyen`
--

LOCK TABLES `nhomquyen` WRITE;
/*!40000 ALTER TABLE `nhomquyen` DISABLE KEYS */;
INSERT INTO `nhomquyen` VALUES (1,'Quản lý kho',1),(2,'Nhân viên nhập hàng',1),(3,'Nhân viên xuất hàng',1),(4,'Thủ kho',0),(5,'Nhân viên kiểm kho',0);
/*!40000 ALTER TABLE `nhomquyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieukiemke`
--

DROP TABLE IF EXISTS `phieukiemke`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieukiemke` (
  `maphieukiemke` int NOT NULL AUTO_INCREMENT,
  `manhanvien` int NOT NULL,
  `thoigian` date NOT NULL,
  PRIMARY KEY (`maphieukiemke`),
  KEY `manhanvien` (`manhanvien`),
  CONSTRAINT `phieukiemke_ibfk_1` FOREIGN KEY (`manhanvien`) REFERENCES `nhanvien` (`manv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieukiemke`
--

LOCK TABLES `phieukiemke` WRITE;
/*!40000 ALTER TABLE `phieukiemke` DISABLE KEYS */;
/*!40000 ALTER TABLE `phieukiemke` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieunhap`
--

DROP TABLE IF EXISTS `phieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieunhap` (
  `maphieunhap` int NOT NULL AUTO_INCREMENT,
  `manhacungcap` int NOT NULL,
  `manhanvien` int NOT NULL,
  `thoigian` date NOT NULL,
  `trangthai` tinyint NOT NULL,
  PRIMARY KEY (`maphieunhap`),
  KEY `manhacungcap` (`manhacungcap`),
  KEY `manhanvien` (`manhanvien`),
  CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`manhacungcap`) REFERENCES `nhacungcap` (`manhacungcap`),
  CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`manhanvien`) REFERENCES `nhanvien` (`manv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieunhap`
--

LOCK TABLES `phieunhap` WRITE;
/*!40000 ALTER TABLE `phieunhap` DISABLE KEYS */;
/*!40000 ALTER TABLE `phieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieuxuat`
--

DROP TABLE IF EXISTS `phieuxuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieuxuat` (
  `maphieuxuat` int NOT NULL AUTO_INCREMENT,
  `makhachhang` int NOT NULL,
  `manhanvien` int NOT NULL,
  `thoigian` date NOT NULL,
  `trangthai` tinyint NOT NULL,
  PRIMARY KEY (`maphieuxuat`),
  KEY `makhachhang` (`makhachhang`),
  KEY `manhanvien` (`manhanvien`),
  CONSTRAINT `phieuxuat_ibfk_1` FOREIGN KEY (`makhachhang`) REFERENCES `khachhang` (`makhachhang`),
  CONSTRAINT `phieuxuat_ibfk_2` FOREIGN KEY (`manhanvien`) REFERENCES `nhanvien` (`manv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieuxuat`
--

LOCK TABLES `phieuxuat` WRITE;
/*!40000 ALTER TABLE `phieuxuat` DISABLE KEYS */;
/*!40000 ALTER TABLE `phieuxuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham` (
  `masanpham` int NOT NULL AUTO_INCREMENT,
  `tensp` varchar(255) NOT NULL,
  `hinhanh` varchar(255) DEFAULT NULL,
  `maxuatxu` int NOT NULL,
  `chipxuly` varchar(255) NOT NULL,
  `mahedieuhanh` int NOT NULL,
  `cameratruoc` varchar(255) DEFAULT NULL,
  `camerasau` varchar(255) DEFAULT NULL,
  `thongso` int NOT NULL,
  `gia` decimal(15,2) DEFAULT NULL,
  `trangthai` tinyint DEFAULT NULL,
  `mathuonghieu` int NOT NULL,
  `dungluongpin` varchar(50) DEFAULT NULL,
  `kichthuocmanhinh` varchar(50) DEFAULT NULL,
  `thoigianbaohanh` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`masanpham`),
  KEY `maxuatxu` (`maxuatxu`),
  KEY `mathuonghieu` (`mathuonghieu`),
  KEY `sanpham_ibfk_2` (`mahedieuhanh`),
  CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`maxuatxu`) REFERENCES `xuatxu` (`maxuatxu`),
  CONSTRAINT `sanpham_ibfk_2` FOREIGN KEY (`mahedieuhanh`) REFERENCES `hedieuhanh` (`mahedieuhanh`),
  CONSTRAINT `sanpham_ibfk_3` FOREIGN KEY (`mathuonghieu`) REFERENCES `thuonghieu` (`mathuonghieu`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES (1,'Realme GT 5','realmegt5.jpg',1,'Snapdragon 8 Gen 2',1,'16MP','50MP + 8MP + 2MP',256,10990000.00,0,1,'5240mAh','6.74 inch','12 tháng'),(2,'Vivo X90 Pro','vivox90pro.jpg',1,'Dimensity 9200',1,'32MP','50MP + 50MP + 12MP',256,19990000.00,0,1,'4870mAh','6.78 inch','18 tháng'),(3,'iPhone SE 2022','iphonese2022.jpg',1,'Apple A15 Bionic',1,'7MP','12MP',64,11490000.00,0,1,'2018mAh','4.7 inch','12 tháng'),(4,'Samsung A54','a54.jpg',1,'Exynos 1380',1,'32MP','50MP + 12MP + 5MP',128,9490000.00,0,1,'5000mAh','6.4 inch','24 tháng'),(5,'iPhone 14 Plus','iphone14plus.jpg',1,'Apple A15 Bionic',1,'12MP','12MP + 12MP',128,21990000.00,0,1,'4323mAh','6.7 inch','24 tháng'),(6,'POCO F5 Pro','pocof5pro.jpg',1,'Snapdragon 8+ Gen 1',1,'16MP','64MP + 8MP + 2MP',256,10900000.00,0,1,'5160mAh','6.67 inch','12 tháng'),(7,'Oppo Reno10 Pro','reno10pro.jpg',1,'Snapdragon 778G',1,'32MP','50MP + 32MP + 8MP',128,11900000.00,0,1,'4600mAh','6.7 inch','18 tháng'),(8,'Samsung Galaxy Z Flip5','zflip5.jpg',1,'Snapdragon 8 Gen 2',1,'10MP','12MP + 12MP',256,25990000.00,0,1,'3700mAh','6.7 inch','12 tháng'),(9,'iPhone 13','iphone13.jpg',1,'Apple A15 Bionic',1,'12MP','12MP + 12MP',128,17490000.00,0,1,'3240mAh','6.1 inch','12 tháng'),(10,'Xiaomi Redmi Note 12','note12.jpg',1,'Snapdragon 685',1,'13MP','50MP + 8MP + 2MP',128,4390000.00,0,1,'5000mAh','6.67 inch','12 tháng'),(11,'Nokia X30','nokiax30.jpg',1,'Snapdragon 695',1,'16MP','50MP + 13MP',128,8490000.00,0,1,'4200mAh','6.43 inch','18 tháng'),(12,'Asus ROG Phone 100','rogphone7.jpg',1,'Snapdragon 8 Gen 2',1,'32MP','50MP + 13MP + 5MP',512,23990000.00,0,1,'6000mAh','6.78 inch','24 tháng'),(13,'Motorola Edge 40','edge40.jpg',1,'Dimensity 8020',1,'32MP','50MP + 13MP',256,9990000.00,0,1,'4400mAh','6.55 inch','18 tháng'),(14,'Realme C55','realmec55.jpg',1,'Helio G88',1,'8MP','64MP + 2MP',128,4490000.00,0,1,'5000mAh','6.72 inch','12 tháng'),(15,'Infinix Zero 30','infinixzero30.jpg',1,'Dimensity 8020',1,'50MP','108MP + 13MP + 2MP',256,7290000.00,0,1,'5000mAh','6.78 inch','12 tháng'),(16,'Tecno Phantom V Flip','phantomvflip.jpg',1,'Dimensity 8050',1,'32MP','64MP + 13MP',256,14990000.00,0,1,'4000mAh','6.9 inch','18 tháng'),(17,'Honor Magic5 Pro','magic5pro.jpg',1,'Snapdragon 8 Gen 2',1,'12MP','50MP + 50MP + 50MP',512,24990000.00,0,1,'5100mAh','6.81 inch','24 tháng'),(18,'Google Pixel 8 Pro','pixel8pro.jpg',1,'Google Tensor G3',1,'10.5MP','50MP + 48MP + 48MP',256,23990000.00,0,1,'5050mAh','6.7 inch','24 tháng'),(19,'Sony Xperia 1 V','xperia1v.jpg',1,'Snapdragon 8 Gen 2',1,'12MP','48MP + 12MP + 12MP',256,25990000.00,0,1,'5000mAh','6.5 inch','24 tháng'),(20,'Lenovo Legion Y90','legiony90.jpg',1,'Snapdragon 8 Gen 1',1,'16MP','64MP + 13MP',512,20900000.00,0,1,'5600mAh','6.92 inch','18 tháng');
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `manv` int NOT NULL AUTO_INCREMENT,
  `tendangnhap` varchar(100) NOT NULL,
  `matkhau` varchar(255) NOT NULL,
  `manhomquyen` int NOT NULL,
  `trangthai` varchar(50) NOT NULL,
  `otp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`manv`),
  KEY `manhomquyen` (`manhomquyen`),
  CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`),
  CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES (1,'admin','$2a$12$SIbNxgF/Bwtv2/PdVjCYqeTfORwdDazOCVMnPbG0GeXMTImMNrXJK',1,'1','423493'),(2,'Minh','$2a$12$TMgIEFUnm76F3yl.KHL1Mu0UNOj01baYx0ggIKANohWFicVSIM2Je',1,'1',NULL),(3,'Khôi','$2a$12$vl5DCKUsIhSUfSUg2kjGguHXI9EeZFB2J7k7bqNTRVqo3hIUJdJYS',1,'1',NULL),(4,'Dương','$2a$12$ZeVVIhiuK7vZUTVKoZgA3e6xAHk3vKZSqxdWy2a.KztW3gA3d.2rS',1,'1',NULL),(5,'Phúc','$2a$12$g/npT1SjP6l5fKxbgrITSeaj5p8DbSaEMh1wRS5rS38gdr.gSF4sq',1,'1',NULL);
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thuonghieu`
--

DROP TABLE IF EXISTS `thuonghieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thuonghieu` (
  `mathuonghieu` int NOT NULL AUTO_INCREMENT,
  `tenthuonghieu` varchar(255) NOT NULL,
  PRIMARY KEY (`mathuonghieu`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thuonghieu`
--

LOCK TABLES `thuonghieu` WRITE;
/*!40000 ALTER TABLE `thuonghieu` DISABLE KEYS */;
INSERT INTO `thuonghieu` VALUES (1,'Samsung'),(2,'Apple'),(3,'Xiaomi'),(4,'Oppo');
/*!40000 ALTER TABLE `thuonghieu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xuatxu`
--

DROP TABLE IF EXISTS `xuatxu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xuatxu` (
  `maxuatxu` int NOT NULL AUTO_INCREMENT,
  `tenxuatxu` varchar(50) NOT NULL,
  PRIMARY KEY (`maxuatxu`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xuatxu`
--

LOCK TABLES `xuatxu` WRITE;
/*!40000 ALTER TABLE `xuatxu` DISABLE KEYS */;
INSERT INTO `xuatxu` VALUES (1,'Việt Nam'),(2,'Trung Quốc'),(3,'Hàn Quốc'),(4,'Mỹ');
/*!40000 ALTER TABLE `xuatxu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

--
-- Indexes for table `chat`
--
ALTER TABLE `nhantin`
  ADD PRIMARY KEY (`matin`);

--
-- AUTO_INCREMENT for table `chat`
--
ALTER TABLE `nhantin`
  MODIFY `matin` int(11) NOT NULL AUTO_INCREMENT;


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-23 16:44:20

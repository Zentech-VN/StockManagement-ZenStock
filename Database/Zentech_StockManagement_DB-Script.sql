-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: zentechStockManagement
-- ------------------------------------------------------
-- Server version	8.0.43-0ubuntu0.24.04.1

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
-- Table structure for table `ctphieunhap`
--

DROP TABLE IF EXISTS `ctphieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ctphieunhap` (
  `maphieunhap` int NOT NULL,
  `masanpham` int NOT NULL,
  `dongia` decimal(15,2) NOT NULL,
  `soluong` int NOT NULL,
  `ghichu` longtext,
  PRIMARY KEY (`maphieunhap`,`masanpham`),
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
INSERT INTO `ctphieunhap` VALUES (455,93295,300000000.00,10,''),(456,93295,30000000.00,1,''),(457,93295,240000000.00,8,'nhap ban'),(458,93294,2323230.00,10,''),(459,93296,250000000.00,10,'nhap ve ban'),(460,93295,300000000.00,10,''),(461,93297,40000000.00,1,''),(462,93296,25000000.00,1,''),(463,93297,40000000.00,1,''),(465,93297,40000000.00,1,''),(466,93301,2000000.00,10,''),(467,93295,300000000.00,10,''),(468,93297,40000000.00,1,''),(469,93301,400000.00,2,''),(471,93303,2111111.00,1,''),(472,93303,23222221.00,11,''),(473,93301,1000000.00,5,''),(474,93301,400000.00,2,''),(475,93303,4222222.00,2,''),(476,93295,210000000.00,7,''),(477,93301,800000.00,4,'');
/*!40000 ALTER TABLE `ctphieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctphieuxuat`
--

DROP TABLE IF EXISTS `ctphieuxuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ctphieuxuat` (
  `maphieuxuat` int NOT NULL,
  `masanpham` int NOT NULL,
  `dongia` decimal(15,2) NOT NULL,
  `soluong` int NOT NULL,
  `ghichu` longtext,
  PRIMARY KEY (`maphieuxuat`,`masanpham`),
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
INSERT INTO `ctphieuxuat` VALUES (245,93296,25000000.00,1,'93296'),(246,93295,150000000.00,5,'93295'),(247,93297,400000000.00,10,'93297'),(248,93296,25000000.00,1,'93296'),(249,93298,20003344.00,1,'93298'),(250,93298,60010032.00,3,'93298'),(254,93297,40000000.00,1,''),(255,93295,30000000.00,1,''),(256,93295,450000000.00,15,''),(256,93298,20003344.00,1,''),(257,93297,40000000.00,1,''),(258,93301,400000.00,2,''),(259,93295,30000000.00,10,''),(260,93301,200000.00,1,''),(261,93295,60000000.00,2,'');
/*!40000 ALTER TABLE `ctphieuxuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctquyen`
--

DROP TABLE IF EXISTS `ctquyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ctquyen` (
  `hanhdong` varchar(50) NOT NULL,
  `machucnang` varchar(50) NOT NULL,
  `manhomquyen` int NOT NULL,
  PRIMARY KEY (`hanhdong`,`machucnang`,`manhomquyen`),
  KEY `fk_ctquyen_chucnang` (`machucnang`),
  KEY `fk_ctquyen_nhomquyen` (`manhomquyen`),
  CONSTRAINT `fk_ctquyen_chucnang` FOREIGN KEY (`machucnang`) REFERENCES `danhmucchucnang` (`machucnang`),
  CONSTRAINT `fk_ctquyen_nhomquyen` FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctquyen`
--

LOCK TABLES `ctquyen` WRITE;
/*!40000 ALTER TABLE `ctquyen` DISABLE KEYS */;
INSERT INTO `ctquyen` VALUES ('create','duyetphieu',1),('create','khachhang',1),('create','khuvuckho',1),('create','nhacungcap',1),('create','nhanvien',1),('create','nhomquyen',1),('create','phieunhap',1),('create','phieuxuat',1),('create','sanpham',1),('create','taikhoan',1),('create','thongke',1),('create','thuoctinh',1),('delete','khachhang',1),('delete','khuvuckho',1),('delete','nhacungcap',1),('delete','nhanvien',1),('delete','nhomquyen',1),('delete','phieunhap',1),('delete','phieuxuat',1),('delete','sanpham',1),('delete','taikhoan',1),('delete','thongke',1),('delete','thuoctinh',1),('read','duyetphieu',1),('read','khachhang',1),('read','khuvuckho',1),('read','nhacungcap',1),('read','nhanvien',1),('read','nhomquyen',1),('read','phieunhap',1),('read','phieuxuat',1),('read','sanpham',1),('read','taikhoan',1),('read','thongke',1),('read','thuoctinh',1),('update','duyetphieu',1),('update','khachhang',1),('update','khuvuckho',1),('update','nhacungcap',1),('update','nhanvien',1),('update','nhomquyen',1),('update','phieunhap',1),('update','phieuxuat',1),('update','sanpham',1),('update','taikhoan',1),('update','thongke',1),('update','thuoctinh',1),('create','khuvuckho',2),('create','nhacungcap',2),('create','sanpham',2),('create','thongke',2),('create','thuoctinh',2),('delete','thuoctinh',2),('read','khuvuckho',2),('read','nhacungcap',2),('read','sanpham',2),('read','thongke',2),('read','thuoctinh',2),('update','khuvuckho',2),('update','nhacungcap',2),('update','sanpham',2),('update','thuoctinh',2),('create','khachhang',3),('create','khuvuckho',3),('create','phieunhap',3),('create','phieuxuat',3),('delete','khuvuckho',3),('delete','phieunhap',3),('delete','phieuxuat',3),('read','khachhang',3),('read','khuvuckho',3),('read','phieunhap',3),('read','phieuxuat',3),('read','sanpham',3),('read','taikhoan',3),('read','thongke',3),('update','khachhang',3),('update','khuvuckho',3),('update','phieunhap',3),('update','phieuxuat',3),('update','sanpham',3),('update','taikhoan',3),('create','phieunhap',26),('create','phieuxuat',26),('read','khachhang',26),('read','nhacungcap',26),('read','nhatky',26),('read','phieunhap',26),('read','phieuxuat',26),('read','thongke',26),('read','thuoctinh',26);
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
INSERT INTO `danhmucchucnang` VALUES ('donvitinh','Quản lý kho',0),('duyetphieu','Duyetphieu',1),('khachhang','Quản lý khách hàng',0),('khuvuckho','Quản lý khu vực kho',0),('kiemke','Quản lý kho',0),('loaisanpham','Quản lý sản phẩm',0),('nhacungcap','Quản lý nhà cung cấp',0),('nhanvien','Quản lý nhân viên',0),('nhaphang','Quản lý nhập hàng',0),('nhatky','Nhatky',1),('nhomquyen','Quản lý nhóm quyền',0),('phieunhap','Phieunhap',1),('phieuxuat','Phieuxuat',1),('sanpham','Quản lý sản phẩm',0),('taikhoan','Quản lý tài khoản',0),('thongke','Quản lý thống kê',0),('thuoctinh','Quản lý thuộc tính',0),('xuathang','Quản lý xuất hàng',0);
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
  `is_delete` tinyint(1) NOT NULL,
  PRIMARY KEY (`mahedieuhanh`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hedieuhanh`
--

LOCK TABLES `hedieuhanh` WRITE;
/*!40000 ALTER TABLE `hedieuhanh` DISABLE KEYS */;
INSERT INTO `hedieuhanh` VALUES (1,'TESTc',1),(2,'Android',0),(3,'iOS',0),(4,'HarmonyOS',0),(5,'KaiOS',0),(6,'Test?c',1),(7,'abcdefgawdwa',1);
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
  `email` varchar(255) DEFAULT NULL,
  `sdt` varchar(10) DEFAULT NULL,
  `trangthai` enum('MoKhoa','Khoa') NOT NULL,
  `is_delete` int DEFAULT '0',
  PRIMARY KEY (`makhachhang`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (1,'Hoàng Minh Hà Mobile','Đồng Nai','hoangminhha@example.com','0940000001','Khoa',0),(2,'Nguyễn Hữu Long Store','Vĩnh Long','nguyenhuulong@example.com','0980000002','MoKhoa',1),(3,'Hồ Thanh Thịnh Telecom','Nghệ An','hothanhthinh@example.com','0900000003','MoKhoa',1),(4,'Phạm Thanh Hải Digital','Hải Phòng','phamthanhhai@example.com','0940000004','MoKhoa',0),(5,'Võ Phúc Nam Mobile','TP. Hồ Chí Minh','vophucnam@example.com','0980000005','MoKhoa',0),(6,'Dương Thị Thành Store','Đồng Nai','duongthithanh@example.com','0980000006','MoKhoa',0),(7,'Ngô Gia Thắng Mobile','Quảng Nam','ngogiathang@example.com','0920000007','MoKhoa',0),(8,'Phạm Minh Thành Telecom','Nghệ An','phamminhthanh@example.com','0920000008','Khoa',0),(9,'Dương Thị Nam Store','Sóc Trăng','duongthinam@example.com','0980000009','MoKhoa',0),(10,'Đặng Hữu Dương Digital','Gia Lai','danghuuduong@example.com','0910000010','MoKhoa',0),(11,'Đinh Văn Thảo Mobile','Lâm Đồng','dinhvanthao@example.com','0960000011','MoKhoa',0),(12,'Phan Thái Trúc Store','Hậu Giang','phanthaitruc@example.com','0930000012','Khoa',0),(13,'Huỳnh Văn Yến Telecom','Đồng Nai','huynhvanyen@example.com','0940000013','MoKhoa',0),(14,'Ngô Minh Hải Digital','Bắc Ninh','ngominhhai@example.com','0930000014','MoKhoa',0),(15,'Huỳnh Ngọc Thảo Mobile','Khánh Hòa','huynhngocthao@example.com','0990000015','MoKhoa',0),(16,'Đỗ Quốc Thái Telecom','Đắk Lắk','doquocthai@example.com','0940000016','Khoa',0),(17,'Ngô Đức Thịnh Store','Thừa Thiên Huế','ngoducthinh@example.com','0950000017','MoKhoa',0),(18,'Đỗ Văn Hải Digital','Sóc Trăng','dovanhai@example.com','0990000018','MoKhoa',0),(19,'Hoàng Thị Trang Mobile','Thanh Hóa','hoangthitrang@example.com','0980000019','MoKhoa',0),(20,'Đinh Văn Trúc Store','Hà Nội','dinhvantruc@example.com','0900000020','MoKhoa',0),(38,'Nguyễn Hữu Mai Mobile','Kiên Giang','nguyenhuumaimobile@example.com','0978850907','Khoa',0),(105,'Hoàng Thái Lâm Telecom','Cần Thơ','telecom@gmail.com','0997723347','MoKhoa',0);
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khuvuckho`
--

DROP TABLE IF EXISTS `khuvuckho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khuvuckho` (
  `makhuvuc` int NOT NULL AUTO_INCREMENT,
  `tenkhuvuc` varchar(255) NOT NULL,
  PRIMARY KEY (`makhuvuc`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khuvuckho`
--

LOCK TABLES `khuvuckho` WRITE;
/*!40000 ALTER TABLE `khuvuckho` DISABLE KEYS */;
INSERT INTO `khuvuckho` VALUES (1,'Bến Tre'),(14,'TP. Hồ Chí Minh'),(15,'Hà Nội'),(16,'Hải Phòng');
/*!40000 ALTER TABLE `khuvuckho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khuvuckho_sanpham`
--

DROP TABLE IF EXISTS `khuvuckho_sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khuvuckho_sanpham` (
  `makhuvuc` int NOT NULL,
  `masanpham` int NOT NULL,
  `soluong` int NOT NULL,
  PRIMARY KEY (`makhuvuc`,`masanpham`),
  KEY `masanpham` (`masanpham`),
  CONSTRAINT `khuvuckho_sanpham_ibfk_1` FOREIGN KEY (`makhuvuc`) REFERENCES `khuvuckho` (`makhuvuc`),
  CONSTRAINT `khuvuckho_sanpham_ibfk_2` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khuvuckho_sanpham`
--

LOCK TABLES `khuvuckho_sanpham` WRITE;
/*!40000 ALTER TABLE `khuvuckho_sanpham` DISABLE KEYS */;
INSERT INTO `khuvuckho_sanpham` VALUES (1,93298,0),(1,93301,14),(1,93303,11),(14,93295,0),(16,93297,3);
/*!40000 ALTER TABLE `khuvuckho_sanpham` ENABLE KEYS */;
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
  `trangthai` enum('MoKhoa','Khoa') NOT NULL,
  `is_delete` tinyint(1) NOT NULL,
  PRIMARY KEY (`manhacungcap`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhacungcap`
--

LOCK TABLES `nhacungcap` WRITE;
/*!40000 ALTER TABLE `nhacungcap` DISABLE KEYS */;
INSERT INTO `nhacungcap` VALUES (1,'Công ty TM-DV Minh Long','123 Trần Hưng Đạo, Q1, TP.HCM','minhlong@example.com','0901000001','Khoa',0),(2,'Công ty TNHH Phú Gia','56 Nguyễn Văn Cừ, Q5, TP.HCM','phugia@example.com','0901000002','MoKhoa',0),(3,'CTCP Thiên Phúc Mobile','8 Lê Văn Sỹ, Q3, TP.HCM','thienphuc@example.com','0901000003','MoKhoa',0),(4,'Công ty TNHH Điện tử Trí Việt','45 Phạm Văn Đồng, Hà Nội','triviet@example.com','0901000004','MoKhoa',0),(5,'Nhà phân phối Đại Phát','102 CMT8, Quận 10, TP.HCM','daiphat@example.com','0901000005','Khoa',0),(6,'CTCP Thiết bị số Hoàng Long','21 Nguyễn Huệ, Huế','hoanglong@example.com','0901000006','MoKhoa',0),(7,'Công ty TNHH Vạn Lộc','89 Lạch Tray, Hải Phòng','vanloc@example.com','0901000007','MoKhoa',0),(8,'Công ty CP An Bình Mobile','34 Trần Phú, Đà Nẵng','anbinh@example.com','0901000008','MoKhoa',0),(9,'Nhà cung cấp Kim Ngân','67 Lý Thường Kiệt, Hà Nội','kimngan@example.com','0901000009','MoKhoa',1),(10,'Công ty TNHH Toàn Cầu','99 Pasteur, TP.HCM','toancau@example.com','0901000010','Khoa',0),(16,'Test','ts','test@gmail.com','0987654321','MoKhoa',1),(17,'Test2','t2','t2@gmail.com','0989876763','Khoa',1),(21,'avc','dddwa','f@gmail.com','0978877877','MoKhoa',1),(22,'Test','adfadfadf','yuayudfyu@gmail.com','0398989898','MoKhoa',1),(23,'Testt','42 Nguyễn Văn Tiết, TP.HCM','fptschool@gmail.com','0398989898','MoKhoa',1),(24,'Duong beo','abv','gay@gmail.com','0948542384','Khoa',1);
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
  `gioitinh` int NOT NULL,
  `ngaysinh` date NOT NULL,
  `sdt` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `trangthai` int NOT NULL,
  `is_delete` tinyint(1) NOT NULL,
  PRIMARY KEY (`manv`)
) ENGINE=InnoDB AUTO_INCREMENT=312845 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'Phạm Ngọc Đức',1,'2008-08-08','0999999999','ducphamngoc39@gmail.com',1,0),(2,'Nguyễn Quang Minh',1,'2025-06-27','0928374839','nguyenminh1301.dev@gmail.com',1,0),(3,'Hoàng Minh Khôi',1,'2025-06-10','0928374938','khoik8524@gmail.com',1,0),(4,'Bùi Hoàng Dương',1,'2025-06-09','0938495849','buihoangduong.dev@gmail.com',1,0),(5,'Trần Thanh Phúc',1,'2025-06-01','0938273849','phuctttv00263@gmail.com',1,0),(312841,'Test',1,'1984-01-09','0321328976','test@gmail.com',1,1),(312842,'Phan Van Hai',1,'2025-08-05','0928374833','hai@gmail.com',1,1),(312843,'Nguyễn Minh Tuấn',1,'2002-09-30','0399898998','tuan123@gmail.com',1,1),(312844,'TEST',1,'1989-09-28','0386349782','test@gmail.com',1,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhomquyen`
--

LOCK TABLES `nhomquyen` WRITE;
/*!40000 ALTER TABLE `nhomquyen` DISABLE KEYS */;
INSERT INTO `nhomquyen` VALUES (1,'Quản trị hệ thống',1),(2,'Quản lý kho',1),(3,'Thủ kho',1),(5,'Nhân viên Xuất kho',1),(26,'hehe',1);
/*!40000 ALTER TABLE `nhomquyen` ENABLE KEYS */;
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
  `nguoitao` int NOT NULL,
  `thoigian` date NOT NULL,
  `trangthai` enum('ChoDuyet','Duyet','Huy') NOT NULL,
  PRIMARY KEY (`maphieunhap`),
  KEY `manhacungcap` (`manhacungcap`),
  KEY `nguoitao` (`nguoitao`),
  CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`manhacungcap`) REFERENCES `nhacungcap` (`manhacungcap`),
  CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`nguoitao`) REFERENCES `nhanvien` (`manv`)
) ENGINE=InnoDB AUTO_INCREMENT=478 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieunhap`
--

LOCK TABLES `phieunhap` WRITE;
/*!40000 ALTER TABLE `phieunhap` DISABLE KEYS */;
INSERT INTO `phieunhap` VALUES (455,1,3,'2025-08-05','Duyet'),(456,1,3,'2025-08-05','Huy'),(457,5,3,'2025-08-05','Huy'),(458,1,3,'2025-08-05','Huy'),(459,5,3,'2025-08-05','Duyet'),(460,2,3,'2025-08-05','Duyet'),(461,3,3,'2025-08-05','Duyet'),(462,1,3,'2025-08-05','Duyet'),(463,1,3,'2025-08-05','Duyet'),(465,10,3,'2025-08-09','Duyet'),(466,1,3,'2025-08-11','Duyet'),(467,1,3,'2025-08-11','Duyet'),(468,23,3,'2025-08-11','Duyet'),(469,1,3,'2025-08-11','Duyet'),(471,1,3,'2025-08-12','ChoDuyet'),(472,2,3,'2025-08-14','Duyet'),(473,2,3,'2025-08-14','Duyet'),(474,6,3,'2025-08-14','ChoDuyet'),(475,4,3,'2025-08-14','ChoDuyet'),(476,6,3,'2025-08-15','ChoDuyet'),(477,8,3,'2025-08-15','ChoDuyet');
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
  `nguoitao` int NOT NULL,
  `thoigian` date NOT NULL,
  `trangthai` enum('ChoDuyet','Duyet','Huy') NOT NULL,
  PRIMARY KEY (`maphieuxuat`),
  KEY `makhachhang` (`makhachhang`),
  KEY `nguoitao` (`nguoitao`),
  CONSTRAINT `phieuxuat_ibfk_1` FOREIGN KEY (`makhachhang`) REFERENCES `khachhang` (`makhachhang`),
  CONSTRAINT `phieuxuat_ibfk_2` FOREIGN KEY (`nguoitao`) REFERENCES `nhanvien` (`manv`)
) ENGINE=InnoDB AUTO_INCREMENT=264 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieuxuat`
--

LOCK TABLES `phieuxuat` WRITE;
/*!40000 ALTER TABLE `phieuxuat` DISABLE KEYS */;
INSERT INTO `phieuxuat` VALUES (245,1,3,'2025-08-05','Duyet'),(246,5,3,'2025-08-05','Duyet'),(247,6,3,'2025-08-05','Duyet'),(248,1,3,'2025-08-05','Duyet'),(249,7,3,'2025-08-05','Duyet'),(250,4,3,'2025-08-06','Duyet'),(254,11,3,'2025-08-07','ChoDuyet'),(255,4,3,'2025-08-08','Duyet'),(256,6,3,'2025-08-09','Duyet'),(257,7,3,'2025-08-09','Duyet'),(258,3,3,'2025-08-11','Duyet'),(259,3,3,'2025-08-11','Duyet'),(260,105,3,'2025-08-11','Duyet'),(261,4,3,'2025-08-14','Duyet');
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
  `xuatxu` int NOT NULL,
  `chipxuly` varchar(255) DEFAULT NULL,
  `hedieuHanh` int NOT NULL,
  `camerasau` varchar(255) DEFAULT NULL,
  `cameratruoc` varchar(255) DEFAULT NULL,
  `thoigianbaohanh` int DEFAULT NULL,
  `dungluongpin` varchar(255) DEFAULT NULL,
  `kichthuocmanhinh` varchar(255) DEFAULT NULL,
  `thuonghieu` int NOT NULL,
  `gia` decimal(15,2) NOT NULL,
  `trangthai` enum('MoKhoa','Khoa') DEFAULT NULL,
  `is_delete` tinyint(1) NOT NULL,
  PRIMARY KEY (`masanpham`),
  KEY `xuatxu` (`xuatxu`),
  KEY `hedieuHanh` (`hedieuHanh`),
  KEY `thuonghieu` (`thuonghieu`),
  CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`xuatxu`) REFERENCES `xuatxu` (`maxuatxu`),
  CONSTRAINT `sanpham_ibfk_2` FOREIGN KEY (`hedieuHanh`) REFERENCES `hedieuhanh` (`mahedieuhanh`),
  CONSTRAINT `sanpham_ibfk_3` FOREIGN KEY (`thuonghieu`) REFERENCES `thuonghieu` (`mathuonghieu`)
) ENGINE=InnoDB AUTO_INCREMENT=93304 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES (93294,'jdafsdfasf','',1,'',1,'','',24,'','',2,232323.00,'MoKhoa',1),(93295,'iPhone 15 Pro','',2,'Apple A16 Bionic',3,'','',24,'','',6,30000000.00,'MoKhoa',0),(93296,'iPhone 17','',1,'Apple A17 Bionic',1,'','',24,'','',2,25000000.00,'Khoa',1),(93297,'Samsung S25 Ultra','',3,'',2,'','',24,'','',2,40000000.00,'MoKhoa',1),(93298,'iPhone mau','',1,'',3,'','',24,'','',2,20003344.00,'MoKhoa',1),(93300,'hihaihiha','',1,'',1,'','',24,'','',2,122131.00,'MoKhoa',1),(93301,'IPhone 17 Pro','',1,'',2,'','',24,'','',2,200000.00,'MoKhoa',0),(93302,'testttt','',1,'',2,'','',24,'','',2,12312313.00,'MoKhoa',0),(93303,'SamSung S25 Ultra','',1,'',2,'','',24,'','',2,2111111.00,'MoKhoa',0);
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `manv` int NOT NULL,
  `matkhau` varchar(255) NOT NULL,
  `manhomquyen` int NOT NULL,
  `tendangnhap` varchar(255) NOT NULL,
  `trangthai` varchar(50) NOT NULL,
  `otp` varchar(255) DEFAULT NULL,
  `otpCreatedAt` timestamp NULL DEFAULT NULL,
  `is_delete` tinyint(1) NOT NULL,
  PRIMARY KEY (`manv`),
  UNIQUE KEY `uq_taikhoan_tendangnhap` (`tendangnhap`),
  KEY `fk_taikhoan_nhomquyen` (`manhomquyen`),
  CONSTRAINT `fk_taikhoan_nhanvien` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`),
  CONSTRAINT `fk_taikhoan_nhomquyen` FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES (1,'$2a$12$ISxy5lkR1P7hrg78.GomsOeHhk.0piUHw5USBQvFRXU9YQOO6a2/C',1,'Duc','1','639184','2025-08-10 22:01:21',0),(2,'$2a$12$D70MXM.SDz5XIo2olgkpq.lHGduMLIfuu5ZVHPA6EZ6khTMEVR.f2',1,'Minh','1',NULL,NULL,0),(3,'$2a$12$SHU1RG8Ef6B2DldMkCmmd.HfMlRKDgDLMCbVTDmkwkLU8AVps68zi',1,'Khoi','1',NULL,NULL,0),(4,'$2a$12$.WggjW92z3XxaxB9EqLv2OalDSANKSEo70TRev6rMSTmVVAK27fsK',3,'duong','1',NULL,NULL,0),(5,'$2a$12$1fsz3nYUY3B0RNUFkhkbHeco6.epof6krSFNSP.7wK7soXV71wtxm',2,'phuc1','1',NULL,NULL,0),(312841,'$2a$12$UIUHcG66hkfPaTsa4eGiqeKTLT7rtBJFhLGZPaE3NcXbLoQdIxV.u',26,'hihihaha','1',NULL,NULL,1),(312842,'$2a$12$ppIvtEhChysTmInVnw1OZu9yF.M1.3LX40.G/nHAdULZ7yLtDp1h6',2,'Hai','1',NULL,NULL,1),(312843,'$2a$12$MASj5m0nyhx9MTeYvG0T7ufF3QiRL.qEd7LfzQ7K7J2Ehzj25izAa',26,'tuan','1',NULL,NULL,1);
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
  `is_delete` tinyint(1) NOT NULL,
  PRIMARY KEY (`mathuonghieu`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thuonghieu`
--

LOCK TABLES `thuonghieu` WRITE;
/*!40000 ALTER TABLE `thuonghieu` DISABLE KEYS */;
INSERT INTO `thuonghieu` VALUES (2,'Samsung',0),(6,'Apple',0),(7,'Xiaomi',0),(8,'OPPO',0),(9,'Nokia',0),(11,'Test',1),(12,'Test242',1),(13,'Test3',1),(14,'Test5',1);
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
  `tenxuatxu` varchar(255) NOT NULL,
  `is_delete` tinyint(1) NOT NULL,
  PRIMARY KEY (`maxuatxu`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xuatxu`
--

LOCK TABLES `xuatxu` WRITE;
/*!40000 ALTER TABLE `xuatxu` DISABLE KEYS */;
INSERT INTO `xuatxu` VALUES (1,'Vietnam',0),(2,'Trung Quốc',1),(3,'Hàn Quốc',0),(4,'Mỹ',0),(5,'Nhật Bản',0),(6,'ưdavđă',1),(7,'tscvdadwa',1),(8,'Trung Quốc',0),(9,'Singapo',1),(10,'tesssst',1);
/*!40000 ALTER TABLE `xuatxu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-15 15:12:25

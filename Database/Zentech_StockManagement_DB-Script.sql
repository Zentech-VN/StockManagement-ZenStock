-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
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
INSERT INTO `ctquyen` VALUES ('create','khachhang',1),('create','khuvuckho',1),('create','nhacungcap',1),('create','nhanvien',1),('create','nhomquyen',1),('create','phieunhap',1),('create','sanpham',1),('create','taikhoan',1),('create','thongke',1),('create','thuoctinh',1),('delete','khachhang',1),('delete','khuvuckho',1),('delete','nhacungcap',1),('delete','nhanvien',1),('delete','nhomquyen',1),('delete','sanpham',1),('delete','taikhoan',1),('delete','thongke',1),('delete','thuoctinh',1),('read','khachhang',1),('read','khuvuckho',1),('read','nhacungcap',1),('read','nhanvien',1),('read','phieunhap',1),('read','sanpham',1),('read','taikhoan',1),('read','thongke',1),('read','thuoctinh',1),('update','khachhang',1),('update','khuvuckho',1),('update','nhacungcap',1),('update','nhanvien',1),('update','sanpham',1),('update','taikhoan',1),('update','thongke',1),('update','thuoctinh',1),('create','khuvuckho',2),('create','nhacungcap',2),('create','sanpham',2),('create','thongke',2),('create','thuoctinh',2),('delete','thuoctinh',2),('read','khuvuckho',2),('read','nhacungcap',2),('read','sanpham',2),('read','thongke',2),('read','thuoctinh',2),('update','khuvuckho',2),('update','nhacungcap',2),('update','sanpham',2),('update','thuoctinh',2),('create','khachhang',3),('read','khachhang',3),('read','sanpham',3),('read','taikhoan',3),('read','thongke',3),('update','khachhang',3),('update','sanpham',3),('update','taikhoan',3),('view','khachhang',5),('view','khuvuckho',5),('read','thongke',26),('create','duyetphieu',27),('create','khachhang',27),('create','nhanvien',27),('create','nhatky',27),('create','phieunhap',27),('create','thongke',27),('read','duyetphieu',27),('read','khachhang',27),('read','khuvuckho',27),('read','nhacungcap',27),('read','nhanvien',27),('read','nhatky',27),('read','phieunhap',27),('read','phieuxuat',27),('read','quyenhan',27),('read','sanpham',27),('read','taikhoan',27),('read','thongke',27),('read','thuoctinh',27),('update','nhacungcap',27),('update','phieuxuat',27),('update','quyenhan',27),('update','sanpham',27),('update','taikhoan',27),('update','thuoctinh',27),('create','phieunhap',28),('delete','phieunhap',28),('read','phieunhap',28),('update','phieunhap',28);
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
INSERT INTO `danhmucchucnang` VALUES ('donvitinh','Quản lý kho',0),('duyetphieu','Duyetphieu',1),('khachhang','Quản lý khách hàng',0),('khuvuckho','Quản lý khu vực kho',0),('kiemke','Quản lý kho',0),('loaisanpham','Quản lý sản phẩm',0),('nhacungcap','Quản lý nhà cung cấp',0),('nhanvien','Quản lý nhân viên',0),('nhaphang','Quản lý nhập hàng',0),('nhatky','Nhatky',1),('nhomquyen','Quản lý nhóm quyền',0),('phieunhap','Phieunhap',1),('phieuxuat','Phieuxuat',1),('quyenhan','Quyenhan',1),('sanpham','Quản lý sản phẩm',0),('taikhoan','Quản lý tài khoản',0),('thongke','Quản lý thống kê',0),('thuoctinh','Quản lý thuộc tính',0),('xuathang','Quản lý xuất hàng',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hedieuhanh`
--

LOCK TABLES `hedieuhanh` WRITE;
/*!40000 ALTER TABLE `hedieuhanh` DISABLE KEYS */;
INSERT INTO `hedieuhanh` VALUES (1,'TEST'),(2,'Android'),(3,'iOS'),(4,'HarmonyOS'),(5,'KaiOS');
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
  PRIMARY KEY (`makhachhang`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (1,'Hoàng Minh Hà Mobile','Đồng Nai','hoangminhha@example.com','0940000001','MoKhoa'),(2,'Nguyễn Hữu Long Store','Vĩnh Long','nguyenhuulong@example.com','0980000002','MoKhoa'),(3,'Hồ Thanh Thịnh Telecom','Nghệ An','hothanhthinh@example.com','0900000003','MoKhoa'),(4,'Phạm Thanh Hải Digital','Hải Phòng','phamthanhhai@example.com','0940000004','MoKhoa'),(5,'Võ Phúc Nam Mobile','Bình Dương','vophucnam@example.com','0980000005','MoKhoa'),(6,'Dương Thị Thành Store','Đồng Nai','duongthithanh@example.com','0980000006','MoKhoa'),(7,'Ngô Gia Thắng Mobile','Quảng Nam','ngogiathang@example.com','0920000007','MoKhoa'),(8,'Phạm Minh Thành Telecom','Nghệ An','phamminhthanh@example.com','0920000008','Khoa'),(9,'Dương Thị Nam Store','Sóc Trăng','duongthinam@example.com','0980000009','MoKhoa'),(10,'Đặng Hữu Dương Digital','Gia Lai','danghuuduong@example.com','0910000010','MoKhoa'),(11,'Đinh Văn Thảo Mobile','Lâm Đồng','dinhvanthao@example.com','0960000011','MoKhoa'),(12,'Phan Thái Trúc Store','Hậu Giang','phanthaitruc@example.com','0930000012','Khoa'),(13,'Huỳnh Văn Yến Telecom','Đồng Nai','huynhvanyen@example.com','0940000013','MoKhoa'),(14,'Ngô Minh Hải Digital','Bắc Ninh','ngominhhai@example.com','0930000014','MoKhoa'),(15,'Huỳnh Ngọc Thảo Mobile','Khánh Hòa','huynhngocthao@example.com','0990000015','MoKhoa'),(16,'Đỗ Quốc Thái Telecom','Đắk Lắk','doquocthai@example.com','0940000016','Khoa'),(17,'Ngô Đức Thịnh Store','Thừa Thiên Huế','ngoducthinh@example.com','0950000017','MoKhoa'),(18,'Đỗ Văn Hải Digital','Sóc Trăng','dovanhai@example.com','0990000018','MoKhoa'),(19,'Hoàng Thị Trang Mobile','Thanh Hóa','hoangthitrang@example.com','0980000019','MoKhoa'),(20,'Đinh Văn Trúc Store','Hà Nội','dinhvantruc@example.com','0900000020','MoKhoa'),(21,'Đặng Thanh Uyên Telecom','Thanh Hóa','đặngthanhuyêntelecom@example.com','0998294552','MoKhoa'),(22,'Phạm Hữu Thảo Mobile','Đà Nẵng','phạmhữuthảomobile@example.com','0913632299','MoKhoa'),(23,'Phạm Khánh Tuấn Mobile','Thanh Hóa','phạmkhánhtuấnmobile@example.com','0995814414','MoKhoa'),(24,'Vũ Thái Khoa Digital','TP. Hồ Chí Minh','vũtháikhoadigital@example.com','0902045640','MoKhoa'),(25,'Hồ Văn Hà Telecom','Hà Tĩnh','hồvănhàtelecom@example.com','0925116226','MoKhoa'),(26,'Dương Thanh Hà Digital','Sóc Trăng','dươngthanhhàdigital@example.com','0977527644','Khoa'),(27,'Ngô Thái Quang Telecom','Khánh Hòa','ngôtháiquangtelecom@example.com','0922147633','MoKhoa'),(28,'Trần Khánh Mai Telecom','Quảng Ngãi','trầnkhánhmaitelecom@example.com','0950980713','MoKhoa'),(29,'Phạm Hữu Khoa Mobile','Sóc Trăng','phạmhữukhoamobile@example.com','0939037700','MoKhoa'),(30,'Lê Ngọc Tuấn Store','Quảng Ngãi','lêngọctuấnstore@example.com','0906297325','MoKhoa'),(31,'Hồ Thái Mai Store','Quảng Nam','hồtháimaistore@example.com','0946401792','Khoa'),(32,'Hồ Ngọc Khoa Store','Kiên Giang','hồngọckhoastore@example.com','0917226515','MoKhoa'),(33,'Hoàng Ngọc Giang Telecom','Thanh Hóa','hoàngngọcgiangtelecom@example.com','0926590414','MoKhoa'),(34,'Đặng Khánh Duy Telecom','Đắk Lắk','đặngkhánhduytelecom@example.com','0955397111','MoKhoa'),(35,'Hồ Hữu Cường Mobile','Quảng Nam','hồhữucườngmobile@example.com','0986465321','MoKhoa'),(36,'Phạm Văn Dũng Digital','Lâm Đồng','phạmvăndũngdigital@example.com','0980890240','MoKhoa'),(37,'Bùi Hữu Uyên Store','Hà Nội','bùihữuuyênstore@example.com','0942569207','MoKhoa'),(38,'Nguyễn Hữu Mai Mobile','Kiên Giang','nguyễnhữumaimobile@example.com','0978850907','Khoa'),(39,'Hoàng Thị Mai Mobile','Lâm Đồng','hoàngthịmaimobile@example.com','0974783143','Khoa'),(40,'Đỗ Thanh Nam Store','Lâm Đồng','đỗthanhnamstore@example.com','0932939829','MoKhoa'),(41,'Lê Minh Hà Telecom','Hải Phòng','lêminhhàtelecom@example.com','0955162363','Khoa'),(42,'Vũ Thái Sơn Digital','Đắk Lắk','vũtháisơndigital@example.com','0932839677','MoKhoa'),(43,'Lê Ngọc Dũng Mobile','Long An','lêngọcdũngmobile@example.com','0902414432','MoKhoa'),(44,'Hoàng Thái Giang Store','Đắk Lắk','hoàngtháigiangstore@example.com','0922526409','MoKhoa'),(45,'Phạm Thanh Khoa Telecom','Sóc Trăng','phạmthanhkhoatelecom@example.com','0961139072','MoKhoa'),(46,'Trần Hữu Nhi Telecom','Hà Nội','trầnhữunhitelecom@example.com','0937736569','Khoa'),(47,'Huỳnh Thái Lâm Telecom','Quảng Ngãi','huỳnhtháilâmtelecom@example.com','0905489984','MoKhoa'),(48,'Trần Công Cường Digital','Quảng Nam','trầncôngcườngdigital@example.com','0973008365','Khoa'),(49,'Ngô Ngọc Sơn Store','Hà Nội','ngôngọcsơnstore@example.com','0951092769','MoKhoa'),(50,'Hoàng Thanh Dũng Digital','Lâm Đồng','hoàngthanhdũngdigital@example.com','0927355969','MoKhoa'),(51,'Đỗ Công Hiếu Store','Kiên Giang','đỗcônghiếustore@example.com','0920366374','MoKhoa'),(52,'Trần Khánh Tâm Store','Bình Dương','trầnkhánhtâmstore@example.com','0972654777','MoKhoa'),(53,'Đỗ Văn Hiếu Store','Cần Thơ','đỗvănhiếustore@example.com','0999161381','MoKhoa'),(54,'Phạm Thị Loan Digital','Lâm Đồng','phạmthịloandigital@example.com','0956340931','MoKhoa'),(55,'Đặng Khánh Mai Mobile','Quảng Ngãi','đặngkhánhmaimobile@example.com','0946467706','MoKhoa'),(56,'Nguyễn Khánh Mai Store','Gia Lai','nguyễnkhánhmaistore@example.com','0987974737','MoKhoa'),(57,'Phạm Khánh Bình Mobile','Hà Tĩnh','phạmkhánhbìnhmobile@example.com','0971327861','MoKhoa'),(58,'Đỗ Ngọc Hà Digital','Lâm Đồng','đỗngọchàdigital@example.com','0973032128','Khoa'),(59,'Đỗ Thái Trang Mobile','Nghệ An','đỗtháitrangmobile@example.com','0913620579','MoKhoa'),(60,'Ngô Công Giang Digital','Gia Lai','ngôcônggiangdigital@example.com','0932658560','Khoa'),(61,'Lê Minh Lâm Telecom','Đà Nẵng','lêminhlâmtelecom@example.com','0961942132','MoKhoa'),(62,'Vũ Văn Dũng Mobile','Kiên Giang','vũvăndũngmobile@example.com','0935599248','MoKhoa'),(63,'Huỳnh Đức Tâm Mobile','Quảng Ngãi','huỳnhđứctâmmobile@example.com','0914977510','MoKhoa'),(64,'Đặng Ngọc Uyên Telecom','Quảng Ngãi','đặngngọcuyêntelecom@example.com','0905322743','Khoa'),(65,'Hồ Đức Tuấn Telecom','Gia Lai','hồđứctuấntelecom@example.com','0915797352','MoKhoa'),(66,'Lê Minh Sơn Store','Long An','lêminhsơnstore@example.com','0978081231','MoKhoa'),(67,'Vũ Hữu Sơn Mobile','Bình Dương','vũhữusơnmobile@example.com','0987789702','MoKhoa'),(68,'Dương Hữu Giang Store','Đồng Nai','dươnghữugiangstore@example.com','0998723716','MoKhoa'),(69,'Vũ Thái Loan Digital','Hà Tĩnh','vũtháiloandigital@example.com','0953978447','MoKhoa'),(70,'Vũ Công Nhi Mobile','Thanh Hóa','vũcôngnhimobile@example.com','0992194552','Khoa'),(71,'Đặng Hữu Tâm Mobile','Hà Nội','đặnghữutâmmobile@example.com','0973379782','Khoa'),(72,'Phạm Thái Quang Store','Kiên Giang','phạmtháiquangstore@example.com','0933615316','Khoa'),(73,'Bùi Công Cường Mobile','Quảng Ngãi','bùicôngcườngmobile@example.com','0940734902','MoKhoa'),(74,'Nguyễn Hữu An Mobile','Hà Nội','nguyễnhữuanmobile@example.com','0938052235','MoKhoa'),(75,'Ngô Đức Sơn Telecom','Long An','ngôđứcsơntelecom@example.com','0938842881','Khoa'),(76,'Vũ Thái Thảo Telecom','Bình Dương','vũtháithảotelecom@example.com','0943290747','Khoa'),(77,'Huỳnh Khánh Nam Telecom','Gia Lai','huỳnhkhánhnamtelecom@example.com','0925941932','Khoa'),(78,'Vũ Minh Giang Telecom','Đắk Lắk','vũminhgiangtelecom@example.com','0955708548','Khoa'),(79,'Huỳnh Ngọc Mai Store','Hà Tĩnh','huỳnhngọcmaistore@example.com','0908353917','MoKhoa'),(80,'Ngô Thanh Dũng Store','Long An','ngôthanhdũngstore@example.com','0980190964','Khoa'),(81,'Ngô Minh Hiếu Store','Hà Tĩnh','ngôminhhiếustore@example.com','0924165127','MoKhoa'),(82,'Phạm Ngọc Hiếu Mobile','Gia Lai','phạmngọchiếumobile@example.com','0951013313','Khoa'),(83,'Hoàng Thị Tâm Mobile','Lâm Đồng','hoàngthịtâmmobile@example.com','0933158834','MoKhoa'),(84,'Lê Ngọc Thảo Mobile','Bình Dương','lêngọcthảomobile@example.com','0973788783','MoKhoa'),(85,'Hoàng Hữu Duy Mobile','Bình Dương','hoànghữuduymobile@example.com','0911821810','Khoa'),(86,'Nguyễn Khánh Dũng Telecom','TP. Hồ Chí Minh','nguyễnkhánhdũngtelecom@example.com','0915814815','MoKhoa'),(87,'Đỗ Thanh Nhi Mobile','Thanh Hóa','đỗthanhnhimobile@example.com','0987949453','MoKhoa'),(88,'Vũ Công An Digital','Đắk Lắk','vũcôngandigital@example.com','0986199484','MoKhoa'),(89,'Đặng Văn Yến Store','Hà Tĩnh','đặngvănyếnstore@example.com','0976307891','MoKhoa'),(90,'Đặng Minh Lâm Store','Đắk Lắk','đặngminhlâmstore@example.com','0938539937','MoKhoa'),(91,'Huỳnh Hữu Uyên Mobile','Đồng Nai','huỳnhhữuuyênmobile@example.com','0993426175','MoKhoa'),(92,'Nguyễn Văn Giang Telecom','Kiên Giang','nguyễnvăngiangtelecom@example.com','0977847991','MoKhoa'),(93,'Bùi Văn Duy Store','Kiên Giang','bùivănduystore@example.com','0930430070','MoKhoa'),(94,'Lê Ngọc Phát Digital','Hà Tĩnh','lêngọcphátdigital@example.com','0996563184','MoKhoa'),(95,'Phạm Khánh Thảo Digital','Thanh Hóa','phạmkhánhthảodigital@example.com','0983866810','MoKhoa'),(96,'Bùi Thị Thảo Store','Đà Nẵng','bùithịthảostore@example.com','0983781260','MoKhoa'),(97,'Ngô Đức Nam Store','Quảng Ngãi','ngôđứcnamstore@example.com','0943363648','Khoa'),(98,'Đỗ Thanh Bình Digital','Lâm Đồng','đỗthanhbìnhdigital@example.com','0951502934','MoKhoa'),(99,'Phạm Công Hiếu Digital','Sóc Trăng','phạmcônghiếudigital@example.com','0989433958','MoKhoa'),(100,'Bùi Đức Thảo Mobile','Hải Phòng','bùiđứcthảomobile@example.com','0909176661','MoKhoa'),(101,'Đỗ Thị Giang Mobile','Gia Lai','đỗthịgiangmobile@example.com','0969894737','MoKhoa'),(102,'Đặng Hữu Phát Telecom','Sóc Trăng','đặnghữupháttelecom@example.com','0918502896','MoKhoa'),(103,'Hoàng Minh Tuấn Store','Gia Lai','hoàngminhtuấnstore@example.com','0929957587','MoKhoa'),(104,'Bùi Ngọc Yến Digital','Lâm Đồng','bùingọcyếndigital@example.com','0949906542','MoKhoa'),(105,'Hoàng Thái Lâm Telecom','Cần Thơ','hoàngtháilâmtelecom@example.com','0997723347','MoKhoa'),(106,'Dương Hữu Sơn Mobile','An Giang','dươnghữusơnmobile@example.com','0976745267','MoKhoa'),(107,'Hồ Minh Dũng Mobile','Thanh Hóa','hồminhdũngmobile@example.com','0996972619','MoKhoa'),(108,'Đỗ Ngọc Loan Mobile','Đồng Nai','đỗngọcloanmobile@example.com','0906833024','Khoa');
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khuvuckho`
--

LOCK TABLES `khuvuckho` WRITE;
/*!40000 ALTER TABLE `khuvuckho` DISABLE KEYS */;
INSERT INTO `khuvuckho` VALUES (1,'Bến Tre'),(2,'Huế'),(14,'TP. Hồ Chí Minh'),(15,'Hà Nội'),(16,'Hải Phòng');
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
INSERT INTO `khuvuckho_sanpham` VALUES (1,93294,0);
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
  PRIMARY KEY (`manhacungcap`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhacungcap`
--

LOCK TABLES `nhacungcap` WRITE;
/*!40000 ALTER TABLE `nhacungcap` DISABLE KEYS */;
INSERT INTO `nhacungcap` VALUES (1,'Công ty TM-DV Minh Long','123 Trần Hưng Đạo, Q1, TP.HCM','minhlong@example.com','0901000001','MoKhoa'),(2,'Công ty TNHH Phú Gia','56 Nguyễn Văn Cừ, Q5, TP.HCM','phugia@example.com','0901000002','MoKhoa'),(3,'CTCP Thiên Phúc Mobile','8 Lê Văn Sỹ, Q3, TP.HCM','thienphuc@example.com','0901000003','MoKhoa'),(4,'Công ty TNHH Điện tử Trí Việt','45 Phạm Văn Đồng, Hà Nội','triviet@example.com','0901000004','MoKhoa'),(5,'Nhà phân phối Đại Phát','102 CMT8, Quận 10, TP.HCM','daiphat@example.com','0901000005','Khoa'),(6,'CTCP Thiết bị số Hoàng Long','21 Nguyễn Huệ, Huế','hoanglong@example.com','0901000006','MoKhoa'),(7,'Công ty TNHH Vạn Lộc','89 Lạch Tray, Hải Phòng','vanloc@example.com','0901000007','MoKhoa'),(8,'Công ty CP An Bình Mobile','34 Trần Phú, Đà Nẵng','anbinh@example.com','0901000008','MoKhoa'),(9,'Nhà cung cấp Kim Ngân','67 Lý Thường Kiệt, Hà Nội','kimngan@example.com','0901000009','MoKhoa'),(10,'Công ty TNHH Toàn Cầu','99 Pasteur, TP.HCM','toancau@example.com','0901000010','Khoa');
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
  PRIMARY KEY (`manv`)
) ENGINE=InnoDB AUTO_INCREMENT=312840 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'Phạm Ngọc Đức',1,'2008-08-08','0999999999','ducphamngoc39@gmail.com',1),(2,'Nguyễn Quang Minh',1,'2025-06-27','0928374839','nguyenminh1301.dev@gmail.com',1),(3,'Hoàng Minh Khôi',1,'2025-06-10','0928374938','khoik8524@gmail.com',1),(4,'Bùi Hoàng Dương',1,'2025-06-09','0938495849','buihoangduong.dev@gmail.com',1),(5,'Trần Thanh Phúc',1,'2025-06-01','0938273849','phuctttv00263@gmail.com',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhomquyen`
--

LOCK TABLES `nhomquyen` WRITE;
/*!40000 ALTER TABLE `nhomquyen` DISABLE KEYS */;
INSERT INTO `nhomquyen` VALUES (1,'Quản trị hệ thống',1),(2,'Quản lý kho',1),(3,'Thủ kho',1),(5,'Nhân viên Xuất kho',1),(26,'hehe',1),(27,'hehe1',1),(28,'Nhân viên Nhập kho',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=455 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `nguoitao` int NOT NULL,
  `thoigian` date NOT NULL,
  `trangthai` enum('ChoDuyet','Duyet','Huy') NOT NULL,
  PRIMARY KEY (`maphieuxuat`),
  KEY `makhachhang` (`makhachhang`),
  KEY `nguoitao` (`nguoitao`),
  CONSTRAINT `phieuxuat_ibfk_1` FOREIGN KEY (`makhachhang`) REFERENCES `khachhang` (`makhachhang`),
  CONSTRAINT `phieuxuat_ibfk_2` FOREIGN KEY (`nguoitao`) REFERENCES `nhanvien` (`manv`)
) ENGINE=InnoDB AUTO_INCREMENT=245 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  PRIMARY KEY (`masanpham`),
  KEY `xuatxu` (`xuatxu`),
  KEY `hedieuHanh` (`hedieuHanh`),
  KEY `thuonghieu` (`thuonghieu`),
  CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`xuatxu`) REFERENCES `xuatxu` (`maxuatxu`),
  CONSTRAINT `sanpham_ibfk_2` FOREIGN KEY (`hedieuHanh`) REFERENCES `hedieuhanh` (`mahedieuhanh`),
  CONSTRAINT `sanpham_ibfk_3` FOREIGN KEY (`thuonghieu`) REFERENCES `thuonghieu` (`mathuonghieu`)
) ENGINE=InnoDB AUTO_INCREMENT=93295 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES (93294,'jdafsdfasf','',1,'',1,'','',24,'','',2,232323.00,'MoKhoa');
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
INSERT INTO `taikhoan` VALUES (1,'$2a$12$pzYTsqoW1SHGNhrcZKeEPOt8VXmEwpwzOCUP1RoYsgPOu4HbLj56G',1,'Duc','1','910461','2025-08-04 18:56:18'),(2,'$2a$12$U.tFCEVdANxElXT14qxez.e.GEUB3WvfDpy9ZQO8KEQrX3Bf1ExwG',26,'Minh','1',NULL,NULL),(3,'$2a$12$SHU1RG8Ef6B2DldMkCmmd.HfMlRKDgDLMCbVTDmkwkLU8AVps68zi',1,'Khoi','1',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thuonghieu`
--

LOCK TABLES `thuonghieu` WRITE;
/*!40000 ALTER TABLE `thuonghieu` DISABLE KEYS */;
INSERT INTO `thuonghieu` VALUES (2,'Samsung'),(6,'Apple'),(7,'Xiaomi'),(8,'OPPO'),(9,'Nokia'),(10,'Huawei');
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
  PRIMARY KEY (`maxuatxu`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xuatxu`
--

LOCK TABLES `xuatxu` WRITE;
/*!40000 ALTER TABLE `xuatxu` DISABLE KEYS */;
INSERT INTO `xuatxu` VALUES (1,'Vietnam'),(2,'Trung Quốc'),(3,'Hàn Quốc'),(4,'Mỹ'),(5,'Nhật Bản');
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

-- Dump completed on 2025-08-05  1:59:32

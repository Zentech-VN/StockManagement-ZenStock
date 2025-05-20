/* =======================================================================
   ZENTECH_POLYCAFE – Dữ liệu mẫu
   Author : Claude
   Date   : 2025-05-14
   ======================================================================= */

USE zentech_polycafe;

-- 1. Thêm dữ liệu cho bảng ROLE
INSERT INTO `ROLE` (`RoleName`) VALUES
('ADMIN'),
('MANAGER'),
('CASHIER');

-- 2. Thêm dữ liệu cho bảng CARD
INSERT INTO `CARD` (`Status`) VALUES
('LOCKED'),
('LOCKED'),
('LOCKED'),
('LOCKED'),
('LOCKED'),
('LOCKED'),
('LOCKED'),
('LOCKED'),
('LOCKED'),
('LOCKED');

-- 3. Thêm dữ liệu cho bảng CATEGORY
INSERT INTO `CATEGORY` (`CategoryName`) VALUES
('COFFEE'),
('TEA'),
('JUICE'),
('SMOOTHIE'),
('SNACK'),
('CAKE'),
('BREAKFAST'),
('LUNCH');

-- 4. Thêm dữ liệu cho bảng USER
INSERT INTO `USER` (`ID`, `Role_ID`, `UserName`, `Password`, `Email`, `FullName`, `Gender`, `Address`, `DoB`, `PhoneNumber`) VALUES
(1, 1, 'admin', '$2a$12$ILMv5FfHkMgQMQ9A7hK4duU9Qz0L4m.BYiVOFxu2XT4g5YP95QzQ6', 'admin@zentech.vn', 'Nguyễn Quản Trị', 'MALE', '123 Lê Lợi, Quận 1, TP.HCM', '1990-05-15', '0901234567'),
(2, 2, 'manager', '$2a$12$3VF46rD1yGgwDO4zxgzCB.PrlhNgjiH6aLFHwCQbVPvCnvRm3CTuy', 'manager@zentech.vn', 'Trần Quản Lý', 'FEMALE', '45 Nguyễn Thị Minh Khai, Quận 3, TP.HCM', '1992-07-20', '0912345678'),
(3, 3, 'cashier1', '$2a$12$wj8jXzx7OGJOjGNqxH2rzeGZ3bnJadBLHVj.xYrmpzsy6.OmYGBwW', 'cashier1@zentech.vn', 'Lê Thu Ngân', 'FEMALE', '78 Cách Mạng Tháng 8, Quận 10, TP.HCM', '1995-03-12', '0923456789'),
(4, 3, 'cashier2', '$2a$12$h5TF.Bz6rO3UUh7LmPMnpeWVPvoRf/6Y3.4KKsQgL4RH3jgGDpHsi', 'cashier2@zentech.vn', 'Phạm Thanh Toán', 'MALE', '56 Võ Văn Tần, Quận 3, TP.HCM', '1997-11-05', '0934567890');

-- 5. Thêm dữ liệu cho bảng PRODUCT
INSERT INTO `PRODUCT` (`Category_ID`, `Name`, `Price`, `Active`, `Description`, `Image_URL`) VALUES
-- COFFEE
(1, 'Cà phê đen', 25000.00, 'ACTIVE', 'Cà phê đen truyền thống', '/images/products/black-coffee.jpg'),
(1, 'Cà phê sữa', 30000.00, 'ACTIVE', 'Cà phê với sữa đặc', '/images/products/milk-coffee.jpg'),
(1, 'Cappuccino', 45000.00, 'ACTIVE', 'Cà phê Ý với sữa và bọt sữa', '/images/products/cappuccino.jpg'),
(1, 'Latte', 45000.00, 'ACTIVE', 'Cà phê Ý với nhiều sữa và ít bọt', '/images/products/latte.jpg'),
(1, 'Americano', 40000.00, 'ACTIVE', 'Espresso pha với nước nóng', '/images/products/americano.jpg'),
-- TEA
(2, 'Trà sen', 35000.00, 'ACTIVE', 'Trà ướp hương sen', '/images/products/lotus-tea.jpg'),
(2, 'Trà đào', 40000.00, 'ACTIVE', 'Trà với đào tươi và syrup đào', '/images/products/peach-tea.jpg'),
(2, 'Trà chanh', 30000.00, 'ACTIVE', 'Trà với nước cốt chanh tươi', '/images/products/lemon-tea.jpg'),
(2, 'Trà sữa trân châu', 45000.00, 'ACTIVE', 'Trà sữa với trân châu đường đen', '/images/products/bubble-tea.jpg'),
-- JUICE
(3, 'Nước cam', 35000.00, 'ACTIVE', 'Nước cam tươi vắt', '/images/products/orange-juice.jpg'),
(3, 'Nước ép táo', 40000.00, 'ACTIVE', 'Nước ép từ táo tươi', '/images/products/apple-juice.jpg'),
(3, 'Nước dừa', 35000.00, 'ACTIVE', 'Nước dừa tươi nguyên trái', '/images/products/coconut-water.jpg'),
-- SMOOTHIE
(4, 'Sinh tố xoài', 45000.00, 'ACTIVE', 'Sinh tố xoài đặc', '/images/products/mango-smoothie.jpg'),
(4, 'Sinh tố dâu', 45000.00, 'ACTIVE', 'Sinh tố dâu tây', '/images/products/strawberry-smoothie.jpg'),
(4, 'Sinh tố bơ', 50000.00, 'ACTIVE', 'Sinh tố bơ đặc', '/images/products/avocado-smoothie.jpg'),
-- SNACK
(5, 'Khoai tây chiên', 35000.00, 'ACTIVE', 'Khoai tây chiên giòn', '/images/products/french-fries.jpg'),
(5, 'Bánh mì nướng tỏi', 30000.00, 'ACTIVE', 'Bánh mì nướng với bơ tỏi', '/images/products/garlic-bread.jpg'),
(5, 'Xúc xích chiên', 40000.00, 'ACTIVE', 'Xúc xích chiên giòn', '/images/products/fried-sausage.jpg'),
-- CAKE
(6, 'Bánh tiramisu', 55000.00, 'ACTIVE', 'Bánh tiramisu truyền thống', '/images/products/tiramisu.jpg'),
(6, 'Bánh cheesecake', 60000.00, 'ACTIVE', 'Bánh phô mai mềm mịn', '/images/products/cheesecake.jpg'),
(6, 'Bánh chocolate', 50000.00, 'ACTIVE', 'Bánh gato chocolate', '/images/products/chocolate-cake.jpg'),
-- BREAKFAST
(7, 'Bánh mì trứng', 35000.00, 'ACTIVE', 'Bánh mì kẹp trứng ốp la', '/images/products/egg-sandwich.jpg'),
(7, 'Phở bò', 65000.00, 'ACTIVE', 'Phở với thịt bò và nước dùng', '/images/products/beef-pho.jpg'),
(7, 'Xôi gà', 45000.00, 'ACTIVE', 'Xôi với gà luộc xé', '/images/products/chicken-sticky-rice.jpg'),
-- LUNCH
(8, 'Cơm gà', 60000.00, 'ACTIVE', 'Cơm với gà rán', '/images/products/chicken-rice.jpg'),
(8, 'Bún bò', 70000.00, 'ACTIVE', 'Bún với thịt bò và nước dùng cay', '/images/products/beef-noodle.jpg'),
(8, 'Mì xào hải sản', 75000.00, 'ACTIVE', 'Mì xào với hải sản tươi', '/images/products/seafood-noodle.jpg'),
(8, 'Cơm chiên dương châu', 65000.00, 'LOCKED', 'Tạm ngưng bán do thiếu nguyên liệu', '/images/products/fried-rice.jpg');

-- 6. Thêm dữ liệu cho bảng BILL
INSERT INTO `BILL` (`User_ID`, `Card_ID`, `Status`) VALUES
(3, 1, 'PAID'),
(3, 2, 'PAID'),
(4, 3, 'PAID'),
(3, 4, 'PAID'),
(4, 5, 'UNPAID'),
(4, 6, 'UNPAID'),
(4, 7, 'CANCELLED'),
(3, 8, 'PAID');

-- 7. Thêm dữ liệu cho bảng BILLDETAILS
INSERT INTO `BILLDETAILS` (`Bill_ID`, `Product_ID`, `Date`, `Quantity`, `Discount`, `TotalPrice_NoVAT`, `TotalPrice_WithVAT`) VALUES
-- Bill 1 - Khách hàng 7 (customer1)
(1, 1, '2025-05-13 08:30:00', 2, NULL, 50000.00, 55000.00),
(1, 6, '2025-05-13 08:30:00', 1, NULL, 35000.00, 38500.00),
(1, 16, '2025-05-13 08:30:00', 1, NULL, 35000.00, 38500.00),

-- Bill 2 - Khách hàng 8 (customer2)
(2, 4, '2025-05-13 10:15:00', 1, NULL, 45000.00, 49500.00),
(2, 19, '2025-05-13 10:15:00', 1, NULL, 55000.00, 60500.00),

-- Bill 3 - Khách hàng 9 (customer3)
(3, 22, '2025-05-13 12:00:00', 1, NULL, 65000.00, 71500.00),
(3, 11, '2025-05-13 12:00:00', 1, NULL, 40000.00, 44000.00),
(3, 12, '2025-05-13 12:00:00', 2, NULL, 70000.00, 77000.00),

-- Bill 4 - Khách hàng 7 (customer1)
(4, 25, '2025-05-13 13:30:00', 1, NULL, 60000.00, 66000.00),
(4, 18, '2025-05-13 13:30:00', 1, 0.1, 36000.00, 39600.00),
(4, 10, '2025-05-13 13:30:00', 2, NULL, 70000.00, 77000.00),

-- Bill 5 - Khách hàng 8 (customer2) - Unpaid
(5, 26, '2025-05-14 11:45:00', 1, NULL, 70000.00, 77000.00),
(5, 7, '2025-05-14 11:45:00', 2, NULL, 80000.00, 88000.00),

-- Bill 6 - Khách hàng 9 (customer3) - Unpaid
(6, 13, '2025-05-14 14:20:00', 1, NULL, 45000.00, 49500.00),
(6, 14, '2025-05-14 14:20:00', 1, NULL, 45000.00, 49500.00),
(6, 20, '2025-05-14 14:20:00', 1, 0.15, 51000.00, 56100.00),

-- Bill 7 - Khách hàng 10 (customer4) - Cancelled
(7, 2, '2025-05-14 16:00:00', 2, NULL, 60000.00, 66000.00),
(7, 17, '2025-05-14 16:00:00', 1, NULL, 40000.00, 44000.00),

-- Bill 8 - Khách hàng 10 (customer4)
(8, 27, '2025-05-14 17:30:00', 1, NULL, 75000.00, 82500.00),
(8, 9, '2025-05-14 17:30:00', 2, 0.1, 81000.00, 89100.00),
(8, 21, '2025-05-14 17:30:00', 1, NULL, 45000.00, 49500.00);
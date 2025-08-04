# ZenStock - Stock Management System

![ZENTECH Banner](/Readme/Zentech-main.jpg)

## Giới thiệu

**ZenStock** là hệ thống quản lý kho được phát triển bởi nhóm Zentech-VN. Ứng dụng giúp doanh nghiệp dễ dàng quản lý hàng hóa, nhập xuất kho, kiểm soát tồn kho và tối ưu quy trình vận hành.

## Tính năng chính

- Quản lý sản phẩm, thuộc, nhà cung cấp, khách hàng
- Nhập kho, xuất kho, duyệt phiếu
- Thống kê đầy đủ chi tiết
- Phân quyền người dùng, bảo mật hệ thống

## Công nghệ sử dụng

- **Ngôn ngữ lập trình:** Java
- **Cơ sở dữ liệu:** MySQL
- **Giao diện:** Java Swing

## Cài đặt & Hướng dẫn sử dụng

### Yêu cầu hệ thống

- Java 8 trở lên
- Ant
- Cơ sở dữ liệu MySQL và SMTP đã được cài đặt

### Các bước cài đặt

1. Clone repository:
   ```bash
   git clone https://github.com/Zentech-VN/StockManagement-ZenStock.git
   ```
2. Cấu hình cơ sở dữ liệu trong file `application.properties` hoặc các file cấu hình tương ứng.
3. Build và chạy ứng dụng:
   ```bash
   mvn clean install
   java -jar target/StockManagement-ZenStock.jar
   ```

## Đóng góp

Chúng tôi hoan nghênh mọi đóng góp! Vui lòng tạo pull request hoặc mở issue để thảo luận.

## Giấy phép

Dự án sử dụng giấy phép [MIT](LICENSE) 

---

> Dự án tốt nghiệp - Zentech-VN

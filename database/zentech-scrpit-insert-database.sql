/* =======================================================================
   STOCK-MANAGEMENT – Stored Procedures
   Author : Zentech
   Date   : 2025-05-17
   ======================================================================= */

USE zentech_stock_management;

DELIMITER //

-- =======================================================================
-- UNIT
-- =======================================================================

DELIMITER //

CREATE PROCEDURE insert_user_role(IN p_userID INT, IN p_roleID INT)
BEGIN
    INSERT INTO USER_ROLE (UserID, RoleID, AssignedAt)
    VALUES (p_userID, p_roleID, NOW());
END //

CREATE PROCEDURE delete_user_role(IN p_userID INT, IN p_roleID INT)
BEGIN
    DELETE FROM USER_ROLE
    WHERE UserID = p_userID AND RoleID = p_roleID;
END //

DELIMITER ;

-- =======================================================================
-- INVENTORY
-- =======================================================================

DELIMITER //

CREATE PROCEDURE insert_inventory(IN p_productID INT, IN p_warehouseID INT, IN p_quantity INT)
BEGIN
    INSERT INTO INVENTORY (ProductID, WarehouseID, Quantity, CreatedAt, UpdatedAt)
    VALUES (p_productID, p_warehouseID, p_quantity, NOW(), NOW());
END //

CREATE PROCEDURE update_inventory(IN p_id INT, IN p_productID INT, IN p_warehouseID INT, IN p_quantity INT)
BEGIN
    UPDATE INVENTORY
    SET ProductID = p_productID,
        WarehouseID = p_warehouseID,
        Quantity = p_quantity,
        UpdatedAt = NOW()
    WHERE InventoryID = p_id;
END //

CREATE PROCEDURE delete_inventory(IN p_id INT)
BEGIN
    DELETE FROM INVENTORY WHERE InventoryID = p_id;
END //

DELIMITER ;

-- =======================================================================
-- SALES_ORDER
-- =======================================================================

DELIMITER //

CREATE PROCEDURE insert_sales_order(
    IN p_customerID INT,
    IN p_orderDate TIMESTAMP,
    IN p_expectedDate TIMESTAMP,
    IN p_paymentMethod ENUM('CASH','TRANSFER'),
    IN p_status ENUM('ACTIVE','LOCKED'),
    IN p_totalAmount DECIMAL(10,2),
    IN p_description VARCHAR(255)
)
BEGIN
    INSERT INTO SALES_ORDER (
        CustomerID, OrderDate, ExpectedDate, PaymentMethod, Status,
        TotalAmount, Description, CreatedAt, UpdatedAt
    )
    VALUES (
        p_customerID, p_orderDate, p_expectedDate, p_paymentMethod, p_status,
        p_totalAmount, p_description, NOW(), NOW()
    );
END //

CREATE PROCEDURE update_sales_order(
    IN p_id INT,
    IN p_customerID INT,
    IN p_orderDate TIMESTAMP,
    IN p_expectedDate TIMESTAMP,
    IN p_paymentMethod ENUM('CASH','TRANSFER'),
    IN p_status ENUM('ACTIVE','LOCKED'),
    IN p_totalAmount DECIMAL(10,2),
    IN p_description VARCHAR(255)
)
BEGIN
    UPDATE SALES_ORDER
    SET CustomerID = p_customerID,
        OrderDate = p_orderDate,
        ExpectedDate = p_expectedDate,
        PaymentMethod = p_paymentMethod,
        Status = p_status,
        TotalAmount = p_totalAmount,
        Description = p_description,
        UpdatedAt = NOW()
    WHERE SLID = p_id;
END //

CREATE PROCEDURE delete_sales_order(IN p_id INT)
BEGIN
    DELETE FROM SALES_ORDER WHERE SLID = p_id;
END //

DELIMITER ;

-- =======================================================================
-- SALES_ORDER_DETAIL
-- =======================================================================

DELIMITER //

CREATE PROCEDURE insert_sales_order_detail(
    IN p_slid INT,
    IN p_productID INT,
    IN p_quantity INT,
    IN p_unitPrice DECIMAL(10,2),
    IN p_totalPrice DECIMAL(10,2),
    IN p_totalVAT DECIMAL(10,2),
    IN p_description VARCHAR(255)
)
BEGIN
    INSERT INTO SALES_ORDER_DETAIL (
        SLID, ProductID, Quantity, UnitPrice, TotalPrice,
        TotalPrice_VAT, Description, CreatedAt, UpdatedAt
    )
    VALUES (
        p_slid, p_productID, p_quantity, p_unitPrice,
        p_totalPrice, p_totalVAT, p_description, NOW(), NOW()
    );
END //

CREATE PROCEDURE update_sales_order_detail(
    IN p_id INT,
    IN p_slid INT,
    IN p_productID INT,
    IN p_quantity INT,
    IN p_unitPrice DECIMAL(10,2),
    IN p_totalPrice DECIMAL(10,2),
    IN p_totalVAT DECIMAL(10,2),
    IN p_description VARCHAR(255)
)
BEGIN
    UPDATE SALES_ORDER_DETAIL
    SET SLID = p_slid,
        ProductID = p_productID,
        Quantity = p_quantity,
        UnitPrice = p_unitPrice,
        TotalPrice = p_totalPrice,
        TotalPrice_VAT = p_totalVAT,
        Description = p_description,
        UpdatedAt = NOW()
    WHERE SLDetailID = p_id;
END //

CREATE PROCEDURE delete_sales_order_detail(IN p_id INT)
BEGIN
    DELETE FROM SALES_ORDER_DETAIL WHERE SLDetailID = p_id;
END //

DELIMITER ;


-- =======================================================================
-- PURCHASE_ORDER
-- =======================================================================

DELIMITER //

CREATE PROCEDURE insert_purchase_order(
    IN p_userID INT,
    IN p_supplierID INT,
    IN p_orderDate TIMESTAMP,
    IN p_expectedDate TIMESTAMP,
    IN p_paymentMethod ENUM('CASH','TRANSFER'),
    IN p_status ENUM('ACTIVE','LOCKED'),
    IN p_totalAmount DECIMAL(10,2),
    IN p_description VARCHAR(255)
)
BEGIN
    INSERT INTO PURCHASE_ORDER (
        UserID, SupplierID, OrderDate, ExpectedDate,
        PaymentMethod, Status, TotalAmount, Description,
        CreatedAt, UpdatedAt
    )
    VALUES (
        p_userID, p_supplierID, p_orderDate, p_expectedDate,
        p_paymentMethod, p_status, p_totalAmount, p_description,
        NOW(), NOW()
    );
END //

CREATE PROCEDURE update_purchase_order(
    IN p_id INT,
    IN p_userID INT,
    IN p_supplierID INT,
    IN p_orderDate TIMESTAMP,
    IN p_expectedDate TIMESTAMP,
    IN p_paymentMethod ENUM('CASH','TRANSFER'),
    IN p_status ENUM('ACTIVE','LOCKED'),
    IN p_totalAmount DECIMAL(10,2),
    IN p_description VARCHAR(255)
)
BEGIN
    UPDATE PURCHASE_ORDER
    SET UserID = p_userID,
        SupplierID = p_supplierID,
        OrderDate = p_orderDate,
        ExpectedDate = p_expectedDate,
        PaymentMethod = p_paymentMethod,
        Status = p_status,
        TotalAmount = p_totalAmount,
        Description = p_description,
        UpdatedAt = NOW()
    WHERE POID = p_id;
END //

CREATE PROCEDURE delete_purchase_order(IN p_id INT)
BEGIN
    DELETE FROM PURCHASE_ORDER WHERE POID = p_id;
END //

DELIMITER ;

-- =======================================================================
-- PURCHASE_ORDER_DETAIL
-- =======================================================================

DELIMITER //

CREATE PROCEDURE insert_purchase_order_detail(
    IN p_poid INT,
    IN p_productID INT,
    IN p_quantity INT,
    IN p_unitPrice DECIMAL(10,2),
    IN p_totalPrice DECIMAL(10,2),
    IN p_totalVAT DECIMAL(10,2),
    IN p_description VARCHAR(255)
)
BEGIN
    INSERT INTO PURCHASE_ORDER_DETAIL (
        POID, ProductID, Quantity, UnitPrice,
        TotalPrice, TotalPrice_VAT, Description,
        CreatedAt, UpdatedAt
    )
    VALUES (
        p_poid, p_productID, p_quantity, p_unitPrice,
        p_totalPrice, p_totalVAT, p_description,
        NOW(), NOW()
    );
END //

CREATE PROCEDURE update_purchase_order_detail(
    IN p_id INT,
    IN p_poid INT,
    IN p_productID INT,
    IN p_quantity INT,
    IN p_unitPrice DECIMAL(10,2),
    IN p_totalPrice DECIMAL(10,2),
    IN p_totalVAT DECIMAL(10,2),
    IN p_description VARCHAR(255)
)
BEGIN
    UPDATE PURCHASE_ORDER_DETAIL
    SET POID = p_poid,
        ProductID = p_productID,
        Quantity = p_quantity,
        UnitPrice = p_unitPrice,
        TotalPrice = p_totalPrice,
        TotalPrice_VAT = p_totalVAT,
        Description = p_description,
        UpdatedAt = NOW()
    WHERE PODetailID = p_id;
END //

CREATE PROCEDURE delete_purchase_order_detail(IN p_id INT)
BEGIN
    DELETE FROM PURCHASE_ORDER_DETAIL WHERE PODetailID = p_id;
END //

DELIMITER ;

-- =======================================================================
-- CATEGORY
-- =======================================================================

DELIMITER //

CREATE PROCEDURE insert_category(
    IN p_name VARCHAR(100),
    IN p_description VARCHAR(255)
)
BEGIN
    INSERT INTO CATEGORY (Name, Description, CreatedAt, UpdatedAt)
    VALUES (p_name, p_description, NOW(), NOW());
END //

CREATE PROCEDURE update_category(
    IN p_id INT,
    IN p_name VARCHAR(100),
    IN p_description VARCHAR(255)
)
BEGIN
    UPDATE CATEGORY
    SET Name = p_name,
        Description = p_description,
        UpdatedAt = NOW()
    WHERE CategoryID = p_id;
END //

CREATE PROCEDURE delete_category(IN p_id INT)
BEGIN
    DELETE FROM CATEGORY WHERE CategoryID = p_id;
END //

DELIMITER ;

-- =======================================================================
-- SUPPLIER
-- =======================================================================

DELIMITER //

CREATE PROCEDURE insert_supplier(
    IN p_name VARCHAR(100),
    IN p_contactName VARCHAR(100),
    IN p_description VARCHAR(255),
    IN p_phone VARCHAR(20),
    IN p_email VARCHAR(254),
    IN p_address VARCHAR(255)
)
BEGIN
    INSERT INTO SUPPLIER (Name, ContactName, Description, Phone, Email, Address, CreatedAt, UpdatedAt)
    VALUES (p_name, p_contactName, p_description, p_phone, p_email, p_address, NOW(), NOW());
END //

CREATE PROCEDURE update_supplier(
    IN p_id INT,
    IN p_name VARCHAR(100),
    IN p_contactName VARCHAR(100),
    IN p_description VARCHAR(255),
    IN p_phone VARCHAR(20),
    IN p_email VARCHAR(254),
    IN p_address VARCHAR(255)
)
BEGIN
    UPDATE SUPPLIER
    SET Name = p_name,
        ContactName = p_contactName,
        Description = p_description,
        Phone = p_phone,
        Email = p_email,
        Address = p_address,
        UpdatedAt = NOW()
    WHERE SupplierID = p_id;
END //

CREATE PROCEDURE delete_supplier(IN p_id INT)
BEGIN
    DELETE FROM SUPPLIER WHERE SupplierID = p_id;
END //

DELIMITER ;

-- =======================================================================
-- CUSTOMER
-- =======================================================================

DELIMITER //

CREATE PROCEDURE insert_customer(
    IN p_customerName VARCHAR(100),
    IN p_contactName VARCHAR(100),
    IN p_phone VARCHAR(20),
    IN p_email VARCHAR(254),
    IN p_address VARCHAR(255),
    IN p_description VARCHAR(255)
)
BEGIN
    INSERT INTO CUSTOMER (CustomerName, ContactName, Phone, Email, Address, Description, CreatedAt, UpdatedAt)
    VALUES (p_customerName, p_contactName, p_phone, p_email, p_address, p_description, NOW(), NOW());
END //

CREATE PROCEDURE update_customer(
    IN p_id INT,
    IN p_customerName VARCHAR(100),
    IN p_contactName VARCHAR(100),
    IN p_phone VARCHAR(20),
    IN p_email VARCHAR(254),
    IN p_address VARCHAR(255),
    IN p_description VARCHAR(255)
)
BEGIN
    UPDATE CUSTOMER
    SET CustomerName = p_customerName,
        ContactName = p_contactName,
        Phone = p_phone,
        Email = p_email,
        Address = p_address,
        Description = p_description,
        UpdatedAt = NOW()
    WHERE CustomerID = p_id;
END //

CREATE PROCEDURE delete_customer(IN p_id INT)
BEGIN
    DELETE FROM CUSTOMER WHERE CustomerID = p_id;
END //

DELIMITER ;

-- =======================================================================
-- UNIT
-- =======================================================================

DELIMITER //

CREATE PROCEDURE insert_unit(
    IN p_unitName VARCHAR(20)
)
BEGIN
    INSERT INTO UNIT (UnitName)
    VALUES (p_unitName);
END //

CREATE PROCEDURE update_unit(
    IN p_id INT,
    IN p_unitName VARCHAR(20)
)
BEGIN
    UPDATE UNIT
    SET UnitName = p_unitName
    WHERE UnitID = p_id;
END //

CREATE PROCEDURE delete_unit(IN p_id INT)
BEGIN
    DELETE FROM UNIT WHERE UnitID = p_id;
END //

DELIMITER ;

-- =======================================================================
-- ROLE
-- =======================================================================

DELIMITER //

CREATE PROCEDURE insert_role(
    IN p_roleName VARCHAR(20)
)
BEGIN
    INSERT INTO ROLE (RoleName)
    VALUES (p_roleName);
END //

CREATE PROCEDURE update_role(
    IN p_id INT,
    IN p_roleName VARCHAR(20)
)
BEGIN
    UPDATE ROLE
    SET RoleName = p_roleName
    WHERE RoleID = p_id;
END //

CREATE PROCEDURE delete_role(IN p_id INT)
BEGIN
    DELETE FROM ROLE WHERE RoleID = p_id;
END //

DELIMITER ;

-- =======================================================================
-- PRODUCT_SUPPLIER
-- =======================================================================

DELIMITER //

CREATE PROCEDURE insert_product_supplier(
    IN p_productID INT,
    IN p_supplierID INT
)
BEGIN
    INSERT INTO PRODUCT_SUPPLIER (ProductID, SupplierID)
    VALUES (p_productID, p_supplierID);
END //

CREATE PROCEDURE delete_product_supplier(
    IN p_productID INT,
    IN p_supplierID INT
)
BEGIN
    DELETE FROM PRODUCT_SUPPLIER
    WHERE ProductID = p_productID AND SupplierID = p_supplierID;
END //

DELIMITER ;

-- =======================================================================
-- WAREHOUSE
-- =======================================================================

DELIMITER //

CREATE PROCEDURE insert_warehouse(
    IN p_name VARCHAR(100),
    IN p_address VARCHAR(255),
    IN p_description VARCHAR(255),
    IN p_phone VARCHAR(20),
    IN p_email VARCHAR(254)
)
BEGIN
    INSERT INTO WAREHOUSE (Name, Address, Description, Phone, Email, CreatedAt, UpdatedAt)
    VALUES (p_name, p_address, p_description, p_phone, p_email, NOW(), NOW());
END //

CREATE PROCEDURE update_warehouse(
    IN p_id INT,
    IN p_name VARCHAR(100),
    IN p_address VARCHAR(255),
    IN p_description VARCHAR(255),
    IN p_phone VARCHAR(20),
    IN p_email VARCHAR(254)
)
BEGIN
    UPDATE WAREHOUSE
    SET Name = p_name,
        Address = p_address,
        Description = p_description,
        Phone = p_phone,
        Email = p_email,
        UpdatedAt = NOW()
    WHERE WarehouseID = p_id;
END //

CREATE PROCEDURE delete_warehouse(IN p_id INT)
BEGIN
    DELETE FROM WAREHOUSE WHERE WarehouseID = p_id;
END //

DELIMITER ;

-- =======================================================================
-- USER
-- =======================================================================

DELIMITER //

CREATE PROCEDURE insert_user(
    IN p_username VARCHAR(20),
    IN p_passwordHash VARCHAR(255),
    IN p_phone VARCHAR(20),
    IN p_email VARCHAR(254),
    IN p_address VARCHAR(255),
    IN p_description VARCHAR(255),
    IN p_isActive ENUM('ACTIVE','LOCKED')
)
BEGIN
    INSERT INTO USER (
        Username, PasswordHash, Phone, Email, Address,
        Description, IsActive, CreatedAt, UpdatedAt
    )
    VALUES (
        p_username, p_passwordHash, p_phone, p_email, p_address,
        p_description, p_isActive, NOW(), NOW()
    );
END //

CREATE PROCEDURE update_user(
    IN p_id INT,
    IN p_username VARCHAR(20),
    IN p_passwordHash VARCHAR(255),
    IN p_phone VARCHAR(20),
    IN p_email VARCHAR(254),
    IN p_address VARCHAR(255),
    IN p_description VARCHAR(255),
    IN p_isActive ENUM('ACTIVE','LOCKED')
)
BEGIN
    UPDATE USER
    SET Username = p_username,
        PasswordHash = p_passwordHash,
        Phone = p_phone,
        Email = p_email,
        Address = p_address,
        Description = p_description,
        IsActive = p_isActive,
        UpdatedAt = NOW()
    WHERE UserID = p_id;
END //

CREATE PROCEDURE delete_user(IN p_id INT)
BEGIN
    DELETE FROM USER WHERE UserID = p_id;
END //

DELIMITER ;

-- =======================================================================
-- PRODUCT
-- =======================================================================

DELIMITER //

CREATE PROCEDURE insert_product(
    IN p_productCode VARCHAR(20),
    IN p_name VARCHAR(100),
    IN p_description VARCHAR(255),
    IN p_price DECIMAL(10,2),
    IN p_imageURL VARCHAR(255),
    IN p_unitID INT,
    IN p_supplierID INT,
    IN p_categoryID INT,
    IN p_isActive ENUM('ACTIVE','LOCKED')
)
BEGIN
    INSERT INTO PRODUCT (
        ProductCode, Name, Description, Price, ImageURL,
        UnitID, SupplierID, CategoryID, IsActive,
        CreateAt, UpdatedAt
    )
    VALUES (
        p_productCode, p_name, p_description, p_price, p_imageURL,
        p_unitID, p_supplierID, p_categoryID, p_isActive,
        NOW(), NOW()
    );
END //

CREATE PROCEDURE update_product(
    IN p_id INT,
    IN p_productCode VARCHAR(20),
    IN p_name VARCHAR(100),
    IN p_description VARCHAR(255),
    IN p_price DECIMAL(10,2),
    IN p_imageURL VARCHAR(255),
    IN p_unitID INT,
    IN p_supplierID INT,
    IN p_categoryID INT,
    IN p_isActive ENUM('ACTIVE','LOCKED')
)
BEGIN
    UPDATE PRODUCT
    SET ProductCode = p_productCode,
        Name = p_name,
        Description = p_description,
        Price = p_price,
        ImageURL = p_imageURL,
        UnitID = p_unitID,
        SupplierID = p_supplierID,
        CategoryID = p_categoryID,
        IsActive = p_isActive,
        UpdatedAt = NOW()
    WHERE ProductID = p_id;
END //

CREATE PROCEDURE delete_product(IN p_id INT)
BEGIN
    DELETE FROM PRODUCT WHERE ProductID = p_id;
END //

DELIMITER ;
/* =======================================================================
   STOCK-MANAGEMENT – Core Schema
   Author : Zentech
   Date   : 2025-05-21
   =======================================================================
   Default engine  : InnoDB
   Default charset : utf8mb4_unicode_ci
   ======================================================================= */
   
CREATE DATABASE IF NOT EXISTS zentech_lubricant
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE zentech_lubricant;

-- =======================================================================
-- ROLE
-- =======================================================================
CREATE TABLE IF NOT EXISTS `ROLE` (
    `RoleID` INT AUTO_INCREMENT,
    `RoleName` VARCHAR(20) NOT NULL,
    CONSTRAINT PK_ROLE PRIMARY KEY (`RoleID`)
) ENGINE = InnoDB;

-- =======================================================================
-- USER
-- =======================================================================
CREATE TABLE IF NOT EXISTS `USER` (
    `UserID` INT AUTO_INCREMENT,
    `Username` VARCHAR(20) NOT NULL,
    `PasswordHash` VARCHAR(255) NOT NULL,
    `Phone` VARCHAR(20) NOT NULL,
    `Email` VARCHAR(254) NOT NULL,
    `Address` VARCHAR(255) NOT NULL,
    `Description` VARCHAR(255),
    `IsActive` ENUM ('ACTIVE','LOCKED'),
    `CreatedAt` TIMESTAMP NOT NULL,
    `UpdatedAt` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT PK_USER PRIMARY KEY (`UserID`)
) ENGINE = InnoDB;

-- =======================================================================
-- UNIT
-- =======================================================================
CREATE TABLE IF NOT EXISTS `UNIT` (
    `UnitID` INT AUTO_INCREMENT,
    `UnitName` VARCHAR(20) NOT NULL,
    CONSTRAINT PK_UNIT PRIMARY KEY (`UnitID`)
) ENGINE = InnoDB;

-- =======================================================================
-- CATEGORY
-- =======================================================================
CREATE TABLE IF NOT EXISTS `CATEGORY` (
    `CategoryID` INT AUTO_INCREMENT,
    `Name` VARCHAR(100) NOT NULL,
    `Description` VARCHAR(255),
    `CreatedAt` TIMESTAMP NOT NULL,
    `UpdatedAt` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT PK_CATEGORY PRIMARY KEY (`CategoryID`)
) ENGINE = InnoDB;

-- =======================================================================
-- SUPPLIER
-- =======================================================================
CREATE TABLE IF NOT EXISTS `SUPPLIER` (
    `SupplierID` INT AUTO_INCREMENT,
    `Name` VARCHAR(100) NOT NULL,
    `ContactName` VARCHAR(100) NOT NULL,
    `Description` VARCHAR(255),
    `Phone` VARCHAR(20) NOT NULL,
    `Email` VARCHAR(254) NOT NULL,
    `Address` VARCHAR(255) NOT NULL,
    `CreatedAt` TIMESTAMP NOT NULL,
    `UpdatedAt` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT PK_SUPPLIER PRIMARY KEY (`SupplierID`)
) ENGINE = InnoDB;

-- =======================================================================
-- WAREHOUSE
-- =======================================================================
CREATE TABLE IF NOT EXISTS `WAREHOUSE` (
    `WarehouseID` INT AUTO_INCREMENT,
    `Name` VARCHAR(100) NOT NULL,
    `Address` VARCHAR(255) NOT NULL,
    `Description` VARCHAR(255),
    `Phone` VARCHAR(20) NOT NULL,
    `Email` VARCHAR(254) NOT NULL,
    `CreatedAt` TIMESTAMP NOT NULL,
    `UpdatedAt` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT PK_WAREHOUSE PRIMARY KEY (`WarehouseID`)
) ENGINE = InnoDB;

-- =======================================================================
-- PRODUCT
-- =======================================================================
CREATE TABLE IF NOT EXISTS `PRODUCT` (
    `ProductID` INT AUTO_INCREMENT,
    `ProductCode` VARCHAR(20) NOT NULL,
    `Name` VARCHAR(100) NOT NULL,
    `Description` VARCHAR(255),
    `Price` DECIMAL(10, 2) NOT NULL,
    `ImageURL` VARCHAR(255),
    `UnitID` INT,
    `SupplierID` INT,
    `CategoryID` INT,
    `IsActive` ENUM ('ACTIVE','LOCKED'),
    `CreateAt` TIMESTAMP NOT NULL,
    `UpdatedAt` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT PK_PRODUCT PRIMARY KEY (`ProductID`),
    CONSTRAINT FK_PRODUCT_UNIT FOREIGN KEY (`UnitID`) REFERENCES `UNIT` (`UnitID`) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT FK_PRODUCT_SUPPLIER FOREIGN KEY (`SupplierID`) REFERENCES `SUPPLIER` (`SupplierID`) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT FK_PRODUCT_CATEGORY FOREIGN KEY (`CategoryID`) REFERENCES `CATEGORY` (`CategoryID`) ON UPDATE CASCADE ON DELETE RESTRICT,
    INDEX IDX_PRODUCT_UNIT (`UnitID`),
    INDEX IDX_PRODUCT_SUPPLIER (`SupplierID`),
    INDEX IDX_PRODUCT_CATEGORY (`CategoryID`)
) ENGINE = InnoDB;

-- =======================================================================
-- CUSTOMER
-- =======================================================================
CREATE TABLE IF NOT EXISTS `CUSTOMER` (
    `CustomerID` INT AUTO_INCREMENT,
    `CustomerName` VARCHAR(100) NOT NULL,
    `ContactName` VARCHAR(100) NOT NULL,
    `Phone` VARCHAR(20) NOT NULL,
    `Email` VARCHAR(254) NOT NULL,
    `Address` VARCHAR(255),
    `Description` VARCHAR(255),
    `CreatedAt` TIMESTAMP NOT NULL,
    `UpdatedAt` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT PK_CUSTOMER PRIMARY KEY (`CustomerID`)
) ENGINE = InnoDB;

-- =======================================================================
-- PRODUCT_SUPPLIER
-- =======================================================================
CREATE TABLE IF NOT EXISTS `PRODUCT_SUPPLIER` (
    `ProductID` INT NOT NULL,
    `SupplierID` INT NOT NULL,
    CONSTRAINT PK_PRODUCT_SUPPLIER PRIMARY KEY (`ProductID`, `SupplierID`),
    CONSTRAINT FK_PRODUCT_SUPPLIER_PRODUCT FOREIGN KEY (`ProductID`) REFERENCES `PRODUCT` (`ProductID`) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT FK_PRODUCT_SUPPLIER_SUPPLIER FOREIGN KEY (`SupplierID`) REFERENCES `SUPPLIER` (`SupplierID`) ON UPDATE CASCADE ON DELETE RESTRICT,
    INDEX IDX_PRODUCT_SUPPLIER_PRODUCT (`ProductID`),
    INDEX IDX_PRODUCT_SUPPLIER_SUPPLIER (`SupplierID`)
) ENGINE = InnoDB;

-- =======================================================================
-- PURCHASE_ORDER
-- =======================================================================
CREATE TABLE IF NOT EXISTS `PURCHASE_ORDER` (
    `POID` INT AUTO_INCREMENT,
    `UserID` INT,
    `SupplierID` INT,
    `OrderDate` TIMESTAMP NOT NULL,
    `ExpectedDate` TIMESTAMP,
    `PaymentMethod` ENUM ('CASH','TRANSFER'),
    `Status` ENUM ('ACTIVE','LOCKED'),
    `TotalAmount` DECIMAL(10, 2) NOT NULL,
    `Description` VARCHAR(255),
    `CreatedAt` TIMESTAMP NOT NULL,
    `UpdatedAt` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT PK_PURCHASE_ORDER PRIMARY KEY (`POID`),
    CONSTRAINT FK_PURCHASE_ORDER_USER FOREIGN KEY (`UserID`) REFERENCES `USER` (`UserID`) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT FK_PURCHASE_ORDER_SUPPLIER FOREIGN KEY (`SupplierID`) REFERENCES `SUPPLIER` (`SupplierID`) ON UPDATE CASCADE ON DELETE RESTRICT,
    INDEX IDX_PURCHASE_ORDER_USER (`UserID`),
    INDEX IDX_PURCHASE_ORDER_SUPPLIER (`SupplierID`)
) ENGINE = InnoDB;

-- =======================================================================
-- PURCHASE_ORDER_DETAIL
-- =======================================================================
CREATE TABLE IF NOT EXISTS `PURCHASE_ORDER_DETAIL` (
    `PODetailID` INT AUTO_INCREMENT,
    `ProductID` INT,
    `POID` INT,
    `Quantity` INT NOT NULL,
    `UnitPrice` DECIMAL(10, 2) NOT NULL,
    `TotalPrice` DECIMAL(10, 2) NOT NULL,
    `TotalPrice_VAT` DECIMAL(10, 2) NOT NULL,
    `Description` VARCHAR(255),
    `CreatedAt` TIMESTAMP NOT NULL,
    `UpdatedAt` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT PK_PURCHASE_ORDER_DETAIL PRIMARY KEY (`PODetailID`),
    CONSTRAINT FK_PURCHASE_ORDER_DETAIL_PRODUCT FOREIGN KEY (`ProductID`) REFERENCES `PRODUCT` (`ProductID`) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT FK_PURCHASE_ORDER_DETAIL_PO FOREIGN KEY (`POID`) REFERENCES `PO` (`POID`) ON UPDATE CASCADE ON DELETE RESTRICT,
    INDEX IDX_PURCHASE_ORDER_DETAIL_PRODUCT (`ProductID`),
    INDEX IDX_PURCHASE_ORDER_DETAIL_PO (`POID`)
) ENGINE = InnoDB;

-- =======================================================================
-- SALES_ORDER
-- =======================================================================
CREATE TABLE IF NOT EXISTS `SALES_ORDER` (
    `SLID` INT AUTO_INCREMENT,
    `CustomerID` INT,
    `OrderDate` TIMESTAMP NOT NULL,
    `ExpectedDate` TIMESTAMP,
    `PaymentMethod` ENUM ('CASH','TRANSFER'),
    `Status` ENUM ('ACTIVE','LOCKED'),
    `TotalAmount` DECIMAL(10, 2) NOT NULL,
    `Description` VARCHAR(255),
    `CreatedAt` TIMESTAMP NOT NULL,
    `UpdatedAt` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT PK_SALES_ORDER PRIMARY KEY (`SLID`),
    CONSTRAINT FK_SALES_ORDER_CUSTOMER FOREIGN KEY (`CustomerID`) REFERENCES `CUSTOMER` (`CustomerID`) ON UPDATE CASCADE ON DELETE RESTRICT,
    INDEX IDX_SALES_ORDER_CUSTOMER (`CustomerID`)
) ENGINE = InnoDB;

-- =======================================================================
-- SALES_ORDER_DETAIL
-- =======================================================================
CREATE TABLE IF NOT EXISTS `SALES_ORDER_DETAIL` (
    `SLDetailID` INT AUTO_INCREMENT,
    `ProductID` INT,
    `SLID` INT,
    `Quantity` INT NOT NULL,
    `UnitPrice` DECIMAL(10, 2) NOT NULL,
    `TotalPrice` DECIMAL(10, 2) NOT NULL,
    `TotalPrice_VAT` DECIMAL(10, 2) NOT NULL,
    `Description` VARCHAR(255),
    `CreatedAt` TIMESTAMP NOT NULL,
    `UpdatedAt` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT PK_SALES_ORDER_DETAIL PRIMARY KEY (`SLDetailID`),
    CONSTRAINT FK_SALES_ORDER_DETAIL_PRODUCT FOREIGN KEY (`ProductID`) REFERENCES `PRODUCT` (`ProductID`) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT FK_SALES_ORDER_DETAIL_SL FOREIGN KEY (`SLID`) REFERENCES `SL` (`SLID`) ON UPDATE CASCADE ON DELETE RESTRICT,
    INDEX IDX_SALES_ORDER_DETAIL_PRODUCT (`ProductID`),
    INDEX IDX_SALES_ORDER_DETAIL_SL (`SLID`)
) ENGINE = InnoDB;

-- =======================================================================
-- INVENTORY
-- =======================================================================
CREATE TABLE IF NOT EXISTS `INVENTORY` (
    `InventoryID` INT AUTO_INCREMENT,
    `ProductID` INT,
    `WarehouseID` INT,
    `Quantity` INT NOT NULL,
    `CreatedAt` TIMESTAMP NOT NULL,
    `UpdatedAt` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT PK_INVENTORY PRIMARY KEY (`InventoryID`),
    CONSTRAINT FK_INVENTORY_PRODUCT FOREIGN KEY (`ProductID`) REFERENCES `PRODUCT` (`ProductID`) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT FK_INVENTORY_WAREHOUSE FOREIGN KEY (`WarehouseID`) REFERENCES `WAREHOUSE` (`WarehouseID`) ON UPDATE CASCADE ON DELETE RESTRICT,
    INDEX IDX_INVENTORY_PRODUCT (`ProductID`),
    INDEX IDX_INVENTORY_WAREHOUSE (`WarehouseID`)
) ENGINE = InnoDB;

-- =======================================================================
-- USER_ROLE
-- =======================================================================
CREATE TABLE IF NOT EXISTS `USER_ROLE` (
    `UserID` INT,
    `RoleID` INT,
    `AssignedAt` TIMESTAMP NOT NULL,
    CONSTRAINT PK_USER_ROLE PRIMARY KEY (`UserID`, `RoleID`),
    CONSTRAINT FK_USER_ROLE_USER FOREIGN KEY (`UserID`) REFERENCES `USER` (`UserID`) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT FK_USER_ROLE_ROLE FOREIGN KEY (`RoleID`) REFERENCES `ROLE` (`RoleID`) ON UPDATE CASCADE ON DELETE RESTRICT,
    INDEX IDX_USER_ROLE_USER (`UserID`),
    INDEX IDX_USER_ROLE_ROLE (`RoleID`)
) ENGINE = InnoDB;

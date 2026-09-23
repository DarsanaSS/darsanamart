CREATE DATABASE IF NOT EXISTS darsanamart;

USE darsanamart;

-- Users table
CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(50)
);

-- Products table
CREATE TABLE IF NOT EXISTS product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE,
    quantity INT
);

-- Cart table
CREATE TABLE IF NOT EXISTS cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    product_id BIGINT,
    product_name VARCHAR(255),
    price DOUBLE,
    quantity INT
);

-- Orders table
CREATE TABLE IF NOT EXISTS orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    product_id BIGINT,
    product_name VARCHAR(255),
    quantity INT,
    price DOUBLE,
    total DOUBLE
);

-- Reviews table
CREATE TABLE IF NOT EXISTS review (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    product_name VARCHAR(255),
    rating INT,
    comment VARCHAR(1000)
);

-- Products
INSERT INTO product (id, name, price, quantity) VALUES
(1, 'Rice', 60, 10),
(2, 'Sugar', 50, 10),
(3, 'Wheat', 55, 10),
(4, 'Toor Dal', 120, 10),
(5, 'Urad Dal', 110, 10),
(6, 'Moong Dal', 100, 10),
(7, 'Chana Dal', 90, 10),
(8, 'Cooking Oil', 150, 10),
(9, 'Salt', 25, 10),
(10, 'Tea Powder', 180, 10),
(11, 'Coffee Powder', 220, 10),
(12, 'Rava', 50, 10),
(13, 'Maida', 45, 10),
(14, 'Besan', 80, 10),
(15, 'Turmeric Powder', 70, 10),
(16, 'Chili Powder', 90, 10),
(17, 'Coriander Powder', 85, 10),
(18, 'Garam Masala', 110, 10),
(19, 'Biscuits', 40, 10),
(20, 'Soap', 45, 10),
(21, 'Milk', 60, 10),
(22, 'Bread', 45, 10),
(23, 'Mustard',50,10),
(24, 'Butter', 60, 10),
(25, 'Curd', 40, 10),
(26, 'Green Gram', 120, 10),
(27, 'Eggs', 70, 10),
(28, 'Jaggery', 65, 10),
(29, 'Vermicelli', 50, 10),
(30, 'Pepper', 50, 10);
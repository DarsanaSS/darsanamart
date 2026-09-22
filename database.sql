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
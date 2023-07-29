CREATE DATABASE chapter_96;
USE chapter_96;

CREATE TABLE store_sales(
	id INT AUTO_INCREMENT PRIMARY KEY,
	store_name VARCHAR(30),
    amount INT NOT NULL DEFAULT 0 check(amount >= 0)
);

CREATE TABLE item (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    type VARCHAR(20) NOT NULL,
    price INT,
    store_id INT,
    stock INT NOT NULL DEFAULT 0 check(stock >= 0) ,
    cpu VARCHAR(30),
    capacity VARCHAR(30),
    
    FOREIGN KEY (store_id) REFERENCES store_sales(id)
);

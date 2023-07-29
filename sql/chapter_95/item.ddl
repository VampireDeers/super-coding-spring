CREATE DATABASE chapter_95;
USE chapter_95;

CREATE TABLE item (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(50) NOT NULL UNIQUE,
                      type VARCHAR(20) NOT NULL,
                      price INT,
                      cpu VARCHAR(30),
                      capacity VARCHAR(30)
);
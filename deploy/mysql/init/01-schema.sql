CREATE DATABASE IF NOT EXISTS gittest;

USE gittest;

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(64) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `user` (`username`, `password`)
VALUES ('admin', 'admin123')
ON DUPLICATE KEY UPDATE `password` = VALUES(`password`);

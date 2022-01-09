-- -----------------------------------------------------------
-- Creating book_category table for library database
-- -----------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `book_category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;
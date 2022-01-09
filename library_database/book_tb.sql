-- -----------------------------------------------------------
-- Creating book table for library database
-- -----------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `book` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `book_title` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `book_author` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'unknown',
  `publish_year` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `shelf_no` int DEFAULT NULL,
  `floor` int DEFAULT NULL,
  `book_issue_stat_id` int DEFAULT '2',
  PRIMARY KEY (`book_id`),
  KEY `category_id` (`category_id`),
  KEY `book_issue_stat_id` (`book_issue_stat_id`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `book_category` (`category_id`),
  CONSTRAINT `book_ibfk_2` FOREIGN KEY (`book_issue_stat_id`) REFERENCES `book_issue_status` (`stat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;

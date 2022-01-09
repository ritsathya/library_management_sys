-- --------------------------------------------------------
-- Creating book_issue table for library database
-- --------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `book_issue` (
  `issue_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `booK_id` int NOT NULL,
  `issue_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `returned_date` date DEFAULT NULL,
  PRIMARY KEY (`issue_id`),
  KEY `student_id` (`student_id`),
  KEY `booK_id` (`booK_id`),
  CONSTRAINT `book_issue_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`stu_id`),
  CONSTRAINT `book_issue_ibfk_2` FOREIGN KEY (`booK_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;
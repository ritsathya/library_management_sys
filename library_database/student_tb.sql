-- ----------------------------------------------------
-- Creating student table for library database
-- ----------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `student` (
  `stu_id` int NOT NULL AUTO_INCREMENT,
  `stu_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `birthday` date NOT NULL,
  `gender` varchar(6) COLLATE utf8mb4_unicode_ci NOT NULL,
  `major` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;
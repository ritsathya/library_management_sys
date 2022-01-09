-- -------------------------------------------------------
-- Creating book_issue_status table for library database
-- -------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `book_issue_status` (
  `stat_id` int NOT NULL AUTO_INCREMENT,
  `issue_stat` char(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`stat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS = 0;
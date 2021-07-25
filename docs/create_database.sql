DROP DATABASE questionbank;
CREATE DATABASE questionbank DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_hungarian_ci;
USE questionbank;

CREATE TABLE `questions` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `question` text,
    `multiple_answers` tinyint(1) DEFAULT 0,
    `explanation` text,
    `asked_count` int DEFAULT 0,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

CREATE TABLE `answers` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `question_id` BIGINT NOT NULL,
    `answer` text,
    `good` tinyint(1) DEFAULT 0,
    PRIMARY KEY (`id`),
    KEY `fk_question_answers_idx` (`question_id`),
    CONSTRAINT `fk_question_answers` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

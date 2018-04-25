
CREATE TABLE IF NOT EXISTS `account` (
  `id` VARCHAR(5) NOT NULL,
  `version` INT NOT NULL,
  `sum` INT NOT NULL,
  PRIMARY KEY (`id`));
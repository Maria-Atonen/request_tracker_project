CREATE TABLE `request_tracker`.`request` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deadline` TIMESTAMP NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`));

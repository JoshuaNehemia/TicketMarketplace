-- Create the schema
DROP SCHEMA `ticketmarketplace`;
CREATE SCHEMA `ticketmarketplace`;

-- Use the created schema
USE `ticketmarketplace`;

-- Table structure for table `sellers`
CREATE TABLE IF NOT EXISTS `sellers` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `fullname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `companyName` VARCHAR(255) DEFAULT NULL,
  `phoneNumber` VARCHAR(13) NOT NULL,
  `companyAddress` VARCHAR(100) DEFAULT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)
)
ENGINE = InnoDB;


-- Table structure for table `users`
CREATE TABLE IF NOT EXISTS `users` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `fullname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phoneNumber` VARCHAR(13) NOT NULL,
  `birthdate` VARCHAR(255) DEFAULT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;

-- Table structure for table `provinces`
CREATE TABLE IF NOT EXISTS `provinces` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- Table structure for table `cities`
CREATE TABLE IF NOT EXISTS `cities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `province_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cities_provinces_idx` (`province_id` ASC),
  CONSTRAINT `fk_cities_provinces`
    FOREIGN KEY (`province_id`)
    REFERENCES `provinces` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- Table structure for table `venues`
CREATE TABLE IF NOT EXISTS `venues` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `city_id` INT NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `area` INT DEFAULT NULL,
  `maxCapacity` INT DEFAULT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_venues_cities_idx` (`city_id` ASC),
  CONSTRAINT `fk_venues_cities`
    FOREIGN KEY (`city_id`)
    REFERENCES `cities` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- Table structure for table `events`
CREATE TABLE IF NOT EXISTS `events` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `image` VARCHAR(255) DEFAULT NULL,
  `description` TEXT DEFAULT NULL,
  `startDateTime` VARCHAR(255) NOT NULL,
  `seller` VARCHAR(45) NOT NULL,
  `venue_id` INT NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_events_sellers_idx` (`seller` ASC),
  INDEX `fk_events_venues_idx` (`venue_id` ASC),
  CONSTRAINT `fk_events_sellers`
    FOREIGN KEY (`seller`)
    REFERENCES `sellers` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_events_venues`
    FOREIGN KEY (`venue_id`)
    REFERENCES `venues` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- Table structure for table `eventClasses`
CREATE TABLE IF NOT EXISTS `eventClasses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `event_id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `price` DOUBLE NOT NULL,
  `description` TEXT DEFAULT NULL,
  `stock` INT NOT NULL,
  `availableStock` INT NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_eventClasses_events_idx` (`event_id` ASC),
  CONSTRAINT `fk_eventClasses_events`
    FOREIGN KEY (`event_id`)
    REFERENCES `events` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- Table structure for table `paymentMethods`
CREATE TABLE IF NOT EXISTS `paymentMethods` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- Table structure for table `tickets`
CREATE TABLE IF NOT EXISTS `tickets` (
  `id` VARCHAR(300) NOT NULL,
  `user` VARCHAR(45) NOT NULL,
  `eventClass_id` INT NOT NULL,
  `paymentMethod_id` INT,
  `price` DOUBLE NOT NULL,
  `paymentStatus` ENUM('UNPAID', 'PAID', 'CANCELLED', 'REQUEST REFUND', 'REFUND') NOT NULL,
  `isClaimed` TINYINT(1) DEFAULT 0,
  `paidTime` VARCHAR(255) DEFAULT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_tickets_users_idx` (`user` ASC),
  INDEX `fk_tickets_eventClasses_idx` (`eventClass_id` ASC),
  INDEX `fk_tickets_paymentMethods_idx` (`paymentMethod_id` ASC),
  CONSTRAINT `fk_tickets_users`
    FOREIGN KEY (`user`)
    REFERENCES `users` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tickets_eventClasses`
    FOREIGN KEY (`eventClass_id`)
    REFERENCES `eventClasses` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tickets_paymentMethods`
    FOREIGN KEY (`paymentMethod_id`)
    REFERENCES `paymentMethods` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- Table structure for table `flashsales`
CREATE TABLE IF NOT EXISTS `flashsales` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `eventClass_id` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  `startTime` VARCHAR(255) NOT NULL,
  `endTime` VARCHAR(255) NOT NULL,
  `initialStock` INT NOT NULL,
  `stock` INT NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_flashsales_eventClasses_idx` (`eventClass_id` ASC),
  CONSTRAINT `fk_flashsales_eventClasses`
    FOREIGN KEY (`eventClass_id`)
    REFERENCES `eventClasses` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- Table structure for table `broadcasts`
CREATE TABLE IF NOT EXISTS `broadcasts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` TEXT NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- Table structure for table `notifications`
CREATE TABLE IF NOT EXISTS `notifications` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` TEXT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `ticket_id` VARCHAR(300) DEFAULT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_notifications_users_idx` (`username` ASC),
  INDEX `fk_notifications_tickets_idx` (`ticket_id` ASC),
  CONSTRAINT `fk_notifications_users`
    FOREIGN KEY (`username`)
    REFERENCES `users` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_notifications_tickets`
    FOREIGN KEY (`ticket_id`)
    REFERENCES `tickets` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;

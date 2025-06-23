-- DDL Script Generated from ERD for Ticket Marketplace
-- Schema: ticketmarketplace
-- Generated on: 2025-06-19

DROP SCHEMA IF EXISTS `ticketmarketplace`;
CREATE SCHEMA `ticketmarketplace` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `ticketmarketplace`;

-- Table: provinces (No dependencies)
CREATE TABLE `provinces` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- Table: users (No dependencies)
CREATE TABLE `users` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `fullname` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phoneNumber` VARCHAR(15) NOT NULL,
  `birthdate` DATE NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)
) ENGINE=InnoDB;

-- Table: sellers (No dependencies)
CREATE TABLE `sellers` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `companyName` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phoneNumber` VARCHAR(15) NOT NULL,
  `companyAddress` TEXT NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)
) ENGINE=InnoDB;

-- Table: paymentMethods (No dependencies)
CREATE TABLE `paymentMethods` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- Table: broadcasts (No dependencies)
CREATE TABLE `broadcasts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` TEXT NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- Table: cities (Depends on provinces)
CREATE TABLE `cities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `province_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cities_provinces_idx` (`province_id` ASC),
  CONSTRAINT `fk_cities_provinces`
    FOREIGN KEY (`province_id`)
    REFERENCES `provinces` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Table: venues (Depends on cities)
CREATE TABLE `venues` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `address` VARCHAR(200) NOT NULL,
  `area` INT NULL,
  `maxCapacity` INT NOT NULL,
  `city_id` INT NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_venues_cities_idx` (`city_id` ASC),
  CONSTRAINT `fk_venues_cities`
    FOREIGN KEY (`city_id`)
    REFERENCES `cities` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Table: events (Depends on venues, sellers)
CREATE TABLE `events` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `startTime` DATETIME NOT NULL,
  `venue_id` INT NOT NULL,
  `seller` VARCHAR(45) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_events_venues_idx` (`venue_id` ASC),
  INDEX `fk_events_sellers_idx` (`seller` ASC),
  CONSTRAINT `fk_events_venues`
    FOREIGN KEY (`venue_id`)
    REFERENCES `venues` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_events_sellers`
    FOREIGN KEY (`seller`)
    REFERENCES `sellers` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Table: eventClasses (Depends on events)
CREATE TABLE `eventClasses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `event_id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `price` DOUBLE NOT NULL,
  `description` TEXT NULL,
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
    ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Table: tickets (Depends on users, eventClasses, paymentMethods)
CREATE TABLE `tickets` (
  `id` VARCHAR(100) NOT NULL,
  `user` VARCHAR(45) NOT NULL,
  `eventClass_id` INT NOT NULL,
  `paymentMethod_id` INT NOT NULL,
  `paymentStatus` ENUM('UNPAID', 'PAID', 'REQUEST REFUND', 'REFUNDED') NOT NULL,
  `isClaimed` TINYINT(1) NOT NULL DEFAULT 0,
  `paidTime` DATETIME NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_tickets_users_idx` (`user` ASC),
  INDEX `fk_tickets_eventClasses_idx` (`eventClass_id` ASC),
  INDEX `fk_tickets_paymentMethods_idx` (`paymentMethod_id` ASC),
  CONSTRAINT `fk_tickets_users`
    FOREIGN KEY (`user`)
    REFERENCES `users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tickets_eventClasses`
    FOREIGN KEY (`eventClass_id`)
    REFERENCES `eventClasses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tickets_paymentMethods`
    FOREIGN KEY (`paymentMethod_id`)
    REFERENCES `paymentMethods` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Table: notifications (Depends on users, tickets)
CREATE TABLE `notifications` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` TEXT NOT NULL,
  `user` VARCHAR(45) NOT NULL,
  `ticket_id` VARCHAR(100) NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_notifications_users_idx` (`user` ASC),
  INDEX `fk_notifications_tickets_idx` (`ticket_id` ASC),
  CONSTRAINT `fk_notifications_users`
    FOREIGN KEY (`user`)
    REFERENCES `users` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_notifications_tickets`
    FOREIGN KEY (`ticket_id`)
    REFERENCES `tickets` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE
) ENGINE=InnoDB;

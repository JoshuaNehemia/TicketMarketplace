-- Create the schema
DROP SCHEMA `ticketmarketplace`;
CREATE SCHEMA `ticketmarketplace`;

-- Use the created schema
USE `ticketmarketplace`;

-- Table structure for table `sellers`
CREATE TABLE IF NOT EXISTS `sellers` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
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

-- -------------------------------------------------------------------------------------------------------
-- INSERT DATA
-- -------------------------------------------------------------------------------------------------------

-- -------------------------------------------------------------------------------------------------------
-- PROVINCES
-- -------------------------------------------------------------------------------------------------------

-- SQL query to insert all provinces of Indonesia into the 'provinces' table.
-- The list includes all 38 official provinces as of the latest administrative divisions.

INSERT INTO `ticketmarketplace`.`provinces` (`name`) VALUES
('Aceh'),
('Bali'),
('Bangka Belitung Islands'),
('Banten'),
('Bengkulu'),
('Central Java'),
('Central Kalimantan'),
('Central Papua'),
('Central Sulawesi'),
('East Java'),
('East Kalimantan'),
('East Nusa Tenggara'),
('Gorontalo'),
('Highland Papua'),
('Jakarta'),
('Jambi'),
('Lampung'),
('Maluku'),
('North Kalimantan'),
('North Maluku'),
('North Sulawesi'),
('North Sumatra'),
('Papua'),
('Riau'),
('Riau Islands'),
('South Kalimantan'),
('South Papua'),
('South Sulawesi'),
('South Sumatra'),
('Southeast Sulawesi'),
('Southwest Papua'),
('West Java'),
('West Kalimantan'),
('West Nusa Tenggara'),
('West Papua'),
('West Sulawesi'),
('West Sumatra'),
('Special Region of Yogyakarta');


-- -------------------------------------------------------------------------------------------------------
-- CITIES
-- -------------------------------------------------------------------------------------------------------

-- SQL query to insert major cities of Indonesia and link them to their respective provinces.
-- This script needs the 'provinces' table has been populated.
-- For each city, a subquery is used to find the corresponding 'province_id'.

INSERT INTO `ticketmarketplace`.`cities` (`name`, `province_id`) VALUES
-- Aceh
('Banda Aceh', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Aceh')),
('Langsa', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Aceh')),
('Lhokseumawe', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Aceh')),
('Sabang', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Aceh')),
('Subulussalam', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Aceh')),

-- Bali
('Denpasar', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Bali')),

-- Bangka Belitung Islands
('Pangkal Pinang', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Bangka Belitung Islands')),

-- Banten
('Cilegon', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Banten')),
('Serang', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Banten')),
('Tangerang', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Banten')),
('South Tangerang', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Banten')),

-- Bengkulu
('Bengkulu', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Bengkulu')),

-- Central Java
('Magelang', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Central Java')),
('Pekalongan', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Central Java')),
('Salatiga', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Central Java')),
('Semarang', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Central Java')),
('Surakarta', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Central Java')),
('Tegal', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Central Java')),

-- Central Kalimantan
('Palangka Raya', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Central Kalimantan')),

-- Central Sulawesi
('Palu', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Central Sulawesi')),

-- East Java
('Batu', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'East Java')),
('Blitar', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'East Java')),
('Kediri', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'East Java')),
('Madiun', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'East Java')),
('Malang', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'East Java')),
('Mojokerto', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'East Java')),
('Pasuruan', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'East Java')),
('Probolinggo', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'East Java')),
('Surabaya', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'East Java')),

-- East Kalimantan
('Balikpapan', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'East Kalimantan')),
('Bontang', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'East Kalimantan')),
('Samarinda', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'East Kalimantan')),

-- East Nusa Tenggara
('Kupang', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'East Nusa Tenggara')),

-- Gorontalo
('Gorontalo', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Gorontalo')),

-- Jakarta
('West Jakarta', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Jakarta')),
('Central Jakarta', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Jakarta')),
('South Jakarta', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Jakarta')),
('East Jakarta', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Jakarta')),
('North Jakarta', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Jakarta')),

-- Jambi
('Jambi', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Jambi')),
('Sungai Penuh', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Jambi')),

-- Lampung
('Bandar Lampung', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Lampung')),
('Metro', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Lampung')),

-- Maluku
('Ambon', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Maluku')),
('Tual', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Maluku')),

-- North Kalimantan
('Tarakan', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'North Kalimantan')),

-- North Maluku
('Ternate', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'North Maluku')),
('Tidore', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'North Maluku')),

-- North Sulawesi
('Bitung', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'North Sulawesi')),
('Kotamobagu', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'North Sulawesi')),
('Manado', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'North Sulawesi')),
('Tomohon', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'North Sulawesi')),

-- North Sumatra
('Binjai', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'North Sumatra')),
('Gunungsitoli', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'North Sumatra')),
('Medan', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'North Sumatra')),
('Padangsidimpuan', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'North Sumatra')),
('Pematangsiantar', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'North Sumatra')),
('Sibolga', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'North Sumatra')),
('Tanjungbalai', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'North Sumatra')),
('Tebing Tinggi', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'North Sumatra')),

-- Papua
('Jayapura', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Papua')),

-- Riau
('Dumai', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Riau')),
('Pekanbaru', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Riau')),

-- Riau Islands
('Batam', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Riau Islands')),
('Tanjungpinang', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Riau Islands')),

-- South Kalimantan
('Banjarbaru', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'South Kalimantan')),
('Banjarmasin', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'South Kalimantan')),

-- South Sulawesi
('Makassar', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'South Sulawesi')),
('Palopo', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'South Sulawesi')),
('Parepare', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'South Sulawesi')),

-- South Sumatra
('Lubuklinggau', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'South Sumatra')),
('Pagar Alam', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'South Sumatra')),
('Palembang', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'South Sumatra')),
('Prabumulih', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'South Sumatra')),

-- Southeast Sulawesi
('Baubau', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Southeast Sulawesi')),
('Kendari', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Southeast Sulawesi')),

-- Southwest Papua
('Sorong', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Southwest Papua')),

-- West Java
('Bandung', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Java')),
('Banjar', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Java')),
('Bekasi', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Java')),
('Bogor', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Java')),
('Cimahi', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Java')),
('Cirebon', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Java')),
('Depok', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Java')),
('Sukabumi', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Java')),
('Tasikmalaya', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Java')),

-- West Kalimantan
('Pontianak', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Kalimantan')),
('Singkawang', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Kalimantan')),

-- West Nusa Tenggara
('Bima', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Nusa Tenggara')),
('Mataram', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Nusa Tenggara')),

-- West Sumatra
('Bukittinggi', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Sumatra')),
('Padang', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Sumatra')),
('Padang Panjang', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Sumatra')),
('Pariaman', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Sumatra')),
('Payakumbuh', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Sumatra')),
('Sawahlunto', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Sumatra')),
('Solok', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'West Sumatra')),

-- Special Region of Yogyakarta
('Yogyakarta', (SELECT id FROM `ticketmarketplace`.`provinces` WHERE `name` = 'Special Region of Yogyakarta'));


-- -------------------------------------------------------------------------------------------------------
-- PAYMENT METHODS
-- -------------------------------------------------------------------------------------------------------

INSERT INTO `ticketmarketplace`.`paymentMethods` (`name`) VALUES
('BCA Virtual Account'),
('Mandiri Virtual Account'),
('BNI Virtual Account'),
('BRI Virtual Account'),
('Permata Virtual Account'),
('CIMB Niaga Virtual Account'),
('Credit Card (Visa/Mastercard/JCB/Amex)'),
('QRIS (All e-wallets & mobile banking)'),
('GoPay'),
('OVO'),
('DANA'),
('ShopeePay'),
('LinkAja'),
('Indomaret'),
('Alfamart');
-- -------------------------------------------------------------------------------------------------------
-- SELLER
-- -------------------------------------------------------------------------------------------------------
INSERT INTO `ticketmarketplace`.`sellers` (`username`, `password`, `companyName`, `email`, `phoneNumber`, `companyAddress`)  VALUES 
('milesdavis', '12345678', 'Miles Davis Jazz.Co', 'MilesDavis@email.com', '08123456789', 'Jl. Jambu'),
('eminem', '12345678', 'Rap God Ent', 'eminem@email.com', '08123456789', 'Jl. Rap'),
('88rising', '12345678', '88 Rising', '88rising@email.com', '08123456789', 'Jl. Naikan no.88'),
('jypent', '12345678', 'JYP Entertainment', 'JYP@email.com', '08123456789', 'Jl. Korea'),
('sment', '12345678', 'SM Entertainment', 'SM@email.com', '08123456789', 'Jl. Korea Sanaan Dikit');
-- -------------------------------------------------------------------------------------------------------
-- USER (BUYER)
-- -------------------------------------------------------------------------------------------------------
INSERT INTO `ticketmarketplace`.`users` (`username`, `password`, `fullname`, `email`, `phoneNumber`, `birthdate`) VALUES
('johndoe', '12345678', 'John Doe', 'john.doe@email.com', '081234567890', '1990-05-15'),
('janesmith', '12345678', 'Jane Smith', 'jane.smith@email.com', '081345678901', '1992-08-22'),
('alexjones', '12345678', 'Alex Jones', 'alex.j@email.com', '081456789012', '1988-11-30'),
('emilywhite', '12345678', 'Emily White', 'emily.w@email.com', '081567890123', '1995-02-10'),
('michaels', '12345678users', 'Michael Suryo', 'michael.suryo@email.com', '081678901234', '1998-07-19');

-- -------------------------------------------------------------------------------------------------------
-- VENUES
-- -------------------------------------------------------------------------------------------------------

-- SQL query to insert major event venues in Indonesia into the 'venues' table.
-- This script needs that the 'cities' table has already been populated.
-- For each venue, a subquery is used to find the corresponding 'cities_id'.

INSERT INTO `ticketmarketplace`.`venues` (`name`, `address`, `maxCapacity`, `area`, `city_id`) VALUES
-- Jakarta
('Gelora Bung Karno Main Stadium', 'Jl. Pintu Satu Senayan, Gelora, Tanah Abang', 77193, 76000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Central Jakarta')),
('Jakarta Convention Center', 'Jl. Gatot Subroto No.1, Gelora, Tanah Abang', 15000, 120000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Central Jakarta')),
('JIExpo Kemayoran', 'Jl. H. Benyamin Sueb, Pademangan Timur', 20000, 440000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'North Jakarta')),
('Taman Mini Indonesia Indah', 'Jl. Raya Taman Mini, Ceger, Cipayung', 10000, 1500000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'East Jakarta')),

-- Surabaya
('Grand City Convention and Exhibition', 'Jl. Walikota Mustajab No.1, Ketabang, Genteng', 5000, 21000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Surabaya')),
('Jatim International Expo (JIE)', 'Jl. Ahmad Yani No.99, Jemur Wonosari, Wonocolo', 10000, 50000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Surabaya')),
('Gelora Bung Tomo Stadium', 'Jl. Jawar, Benowo, Pakal', 45134, 150000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Surabaya')),

-- Bandung
('Trans Convention Centre', 'Jl. Gatot Subroto No.289, Cibangkong, Batununggal', 4000, 6300, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Bandung')),
('Gedung Sate', 'Jl. Diponegoro No.22, Citarum, Bandung Wetan', 1000, 27000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Bandung')),
('Sabuga (Sasana Budaya Ganesha)', 'Jl. Tamansari No.73, Lb. Siliwangi, Coblong', 2500, 12000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Bandung')),

-- Bali
('Bali Nusa Dua Convention Center', 'Kawasan Pariwisata Nusa Dua, Benoa', 10000, 50000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Denpasar')),
('Garuda Wisnu Kencana (GWK) Cultural Park', 'Jl. Raya Uluwatu, Ungasan, Kuta Selatan', 25000, 600000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Denpasar')),

-- Yogyakarta
('Jogja Expo Center (JEC)', 'Jl. Janti, Wonocatur, Banguntapan', 8000, 14000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Yogyakarta')),

-- Medan
('Santika Premiere Dyandra Hotel & Convention', 'Jl. Kapten Maulana Lubis No.7', 3000, 4000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Medan')),

-- Kalimantan
('Balikpapan International Stadium Batakan', 'Jl. Mulawarman, Manggar, Balikpapan Timur', 40000, 100000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Balikpapan')),
('Pontianak Convention Center', 'Jl. Sultan Syarif Abdurrahman No.7', 3000, 5000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Pontianak')),
('Gedung Sultan Suriansyah', 'Jl. Brigjend H. Hasan Basri, Kayu Tangi', 2000, 4000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Banjarmasin')),
('Palaran Stadium', 'Jl. AM. Alimuddin, Simpang Tiga, Palaran', 35000, 80000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Samarinda')),

-- Sulawesi
('Celebes Convention Center', 'Jl. Metro Tanjung Bunga, Maccini Sombala', 5000, 10000, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Makassar')),
('Manado Convention Center', 'Jl. Piere Tendean, Sario Tumpaan', 2000, 4500, (SELECT id FROM `ticketmarketplace`.`cities` WHERE `name` = 'Manado'));

-- -------------------------------------------------------------------------------------------------------
-- EVENTS
-- -------------------------------------------------------------------------------------------------------
INSERT INTO `ticketmarketplace`.`events` (`name`,`description`, `startDateTime`, `venue_id`, `seller`) VALUES
('Surabaya International Jazz Festival','Do you like jazz?', '2025-08-22 19:00:00', 6, 'milesdavis'),
('Eminem: Live in Surabaya 2025','Do you like eminem?', '2025-09-15 20:00:00', 5, 'eminem'),
('Head In The Clouds Surabaya','Do you like 88Rising?', '2025-11-29 16:00:00', 5, '88rising'),
('JYP NATION: ONE MIC in Surabaya','Do you like KPOP?', '2025-10-05 18:30:00',6, 'jypent'),
('SMTOWN LIVE 2025: SMCU PALACE @ SURABAYA','Do you like KPOP?', '2025-12-20 18:00:00', 7, 'sment');

-- -------------------------------------------------------------------------------------------------------
-- EVENTCLASS
-- -------------------------------------------------------------------------------------------------------
-- Event Classes for: Surabaya International Jazz Festival
INSERT INTO `ticketmarketplace`.`eventClasses` (`event_id`, `name`, `price`, `description`, `stock`, `availableStock`) VALUES
(
    (SELECT id FROM `events` WHERE name = 'Surabaya International Jazz Festival'),
    'VIP Pass',
    1800000.00,
    'Includes front-row seating, access to the VIP lounge, and one complimentary beverage.',
    150,
    150
),
(
    (SELECT id FROM `events` WHERE name = 'Surabaya International Jazz Festival'),
    'Regular Pass',
    850000.00,
    'Standard admission to the festival grounds.',
    1000,
    1000
),
(
    (SELECT id FROM `events` WHERE name = 'Surabaya International Jazz Festival'),
    'Early Bird Pass',
    500000.00,
    'Discounted admission for fans of yhe great Miles Davis',
    300,
    300
);

-- Event Classes for: Eminem: Live in Surabaya 2025
INSERT INTO `ticketmarketplace`.`eventClasses` (`event_id`, `name`, `price`, `description`, `stock`, `availableStock`) VALUES
(
    (SELECT id FROM `events` WHERE name = 'Eminem: Live in Surabaya 2025'),
    'Golden Circle (Standing)',
    2500000.00,
    'Front of stage standing area. Get closest to the action!',
    2000,
    2000
),
(
    (SELECT id FROM `events` WHERE name = 'Eminem: Live in Surabaya 2025'),
    'Festival (Standing)',
    1200000.00,
    'General admission standing area behind the Golden Circle.',
    10000,
    10000
),
(
    (SELECT id FROM `events` WHERE name = 'Eminem: Live in Surabaya 2025'),
    'Tribune A (Seated)',
    1750000.00,
    'Numbered seating with a direct view of the stage.',
    5000,
    5000
);

-- Event Classes for: Head In The Clouds Surabaya
INSERT INTO `ticketmarketplace`.`eventClasses` (`event_id`, `name`, `price`, `description`, `stock`, `availableStock`) VALUES
(
    (SELECT id FROM `events` WHERE name = 'Head In The Clouds Surabaya'),
    'VIP GA',
    2800000.00,
    'VIP General Admission. Includes dedicated entry lane, access to VIP-only areas, and an exclusive merch package.',
    1500,
    1500
),
(
    (SELECT id FROM `events` WHERE name = 'Head In The Clouds Surabaya'),
    'GA - Phase 1',
    1500000.00,
    'General Admission, early bird pricing. Limited availability.',
    3000,
    3000
),
(
    (SELECT id FROM `events` WHERE name = 'Head In The Clouds Surabaya'),
    'GA - Phase 2',
    1900000.00,
    'General Admission, standard pricing.',
    8000,
    8000
);

-- Event Classes for: JYP NATION: ONE MIC in Surabaya
INSERT INTO `ticketmarketplace`.`eventClasses` (`event_id`, `name`, `price`, `description`, `stock`, `availableStock`) VALUES
(
    (SELECT id FROM `events` WHERE name = 'JYP NATION: ONE MIC in Surabaya'),
    'MCP Package (Soundcheck)',
    3500000.00,
    'Numbered seating in the best section (Pink), plus access to the soundcheck rehearsal and a laminate with lanyard.',
    500,
    500
),
(
    (SELECT id FROM `events` WHERE name = 'JYP NATION: ONE MIC in Surabaya'),
    'Pink (Seated)',
    2900000.00,
    'Numbered seating in the section closest to the stage.',
    1500,
    1500
),
(
    (SELECT id FROM `events` WHERE name = 'JYP NATION: ONE MIC in Surabaya'),
    'Blue (Seated)',
    2200000.00,
    'Numbered seating in the sections behind Pink.',
    2500,
    2500
),
(
    (SELECT id FROM `events` WHERE name = 'JYP NATION: ONE MIC in Surabaya'),
    'Green (Seated)',
    1600000.00,
    'Numbered seating in the sections furthest from the stage.',
    3000,
    3000
);

-- Event Classes for: SMTOWN LIVE 2025: SMCU PALACE @ SURABAYA
INSERT INTO `ticketmarketplace`.`eventClasses` (`event_id`, `name`, `price`, `description`, `stock`, `availableStock`) VALUES
(
    (SELECT id FROM `events` WHERE name = 'SMTOWN LIVE 2025: SMCU PALACE @ SURABAYA'),
    'CAT 1 (Seated)',
    3000000.00,
    'Best seating category with a central view.',
    2000,
    2000
),
(
    (SELECT id FROM `events` WHERE name = 'SMTOWN LIVE 2025: SMCU PALACE @ SURABAYA'),
    'CAT 2 (Seated)',
    2400000.00,
    'Good seating category to the left and right of CAT 1.',
    3000,
    3000
),
(
    (SELECT id FROM `events` WHERE name = 'SMTOWN LIVE 2025: SMCU PALACE @ SURABAYA'),
    'CAT 3 (Standing)',
    1900000.00,
    'Free-standing area on the festival floor.',
    4000,
    4000
),
(
    (SELECT id FROM `events` WHERE name = 'SMTOWN LIVE 2025: SMCU PALACE @ SURABAYA'),
    'CAT 4 (Restricted View)',
    1300000.00,
    'Seating with a partially obstructed view of the stage.',
    1000,
    1000
);


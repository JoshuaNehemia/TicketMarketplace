-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 29, 2025 at 12:28 PM
-- Server version: 8.0.33
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ticketmarketplace`
--

-- --------------------------------------------------------

--
-- Table structure for table `broadcasts`
--

CREATE TABLE `broadcasts` (
  `id` int NOT NULL,
  `message` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

CREATE TABLE `cities` (
  `id` int NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `province_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cities`
--

INSERT INTO `cities` (`id`, `name`, `province_id`) VALUES
(1, 'Bandung', 1),
(2, 'Bekasi', 1),
(3, 'Jakarta Selatan', 2),
(4, 'Surabaya', 3);

-- --------------------------------------------------------

--
-- Table structure for table `eventclasses`
--

CREATE TABLE `eventclasses` (
  `id` int NOT NULL,
  `event_id` int NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` double NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `stock` int NOT NULL,
  `availableStock` int NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `eventclasses`
--

INSERT INTO `eventclasses` (`id`, `event_id`, `name`, `price`, `description`, `stock`, `availableStock`, `create_time`, `update_time`) VALUES
(15, 18, 'Reguler', 150000, 'Kelas Reguler', 300, 300, '2025-06-29 09:12:55', '2025-06-29 09:12:55'),
(16, 18, 'VIP', 300000, 'Kelas VIP', 100, 100, '2025-06-29 09:12:55', '2025-06-29 09:12:55'),
(17, 19, 'Presale', 250000, 'Tiket presale early access', 200, 200, '2025-06-29 09:12:55', '2025-06-29 09:12:55'),
(18, 19, 'On the Spot', 350000, 'Tiket langsung beli saat hari-H', 150, 150, '2025-06-29 09:12:55', '2025-06-29 09:12:55'),
(19, 20, 'Umum', 100000, 'Untuk semua kalangan', 200, 200, '2025-06-29 09:12:55', '2025-06-29 09:12:55'),
(20, 21, 'VVIP', 400000, 'Tempat paling depan', 50, 50, '2025-06-29 09:12:55', '2025-06-29 09:12:55'),
(21, 21, 'Reguler', 180000, 'Tempat biasa', 200, 200, '2025-06-29 09:12:55', '2025-06-29 09:12:55'),
(22, 22, 'Festival', 200000, 'Berdiri bebas', 300, 300, '2025-06-29 09:12:55', '2025-06-29 09:12:55'),
(23, 22, 'Tribune', 280000, 'Tempat duduk atas', 150, 150, '2025-06-29 09:12:55', '2025-06-29 09:12:55');

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE `events` (
  `id` int NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `startDateTime` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `venue_id` int NOT NULL,
  `seller` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` text COLLATE utf8mb4_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`id`, `name`, `startDateTime`, `venue_id`, `seller`, `create_time`, `update_time`, `description`) VALUES
(18, 'Konser Jazz', '2025-07-05-19-00', 4, 'i', '2025-06-15 03:00:00', '2025-06-15 03:00:00', 'Jazz Music Event'),
(19, 'EDM Festival', '2025-12-01-20-00', 4, 'i', '2025-06-29 08:00:00', '2025-06-29 08:00:00', 'Electronic Dance Music'),
(20, 'Teater Malam', '2025-11-11-19-30', 4, 'i', '2025-05-01 02:00:00', '2025-05-01 02:00:00', 'Drama Theater'),
(21, 'Stand Up Comedy', '2025-07-01-18-30', 4, 'i', '2025-06-29 09:00:00', '2025-06-29 09:00:00', 'Comedy Show'),
(22, 'Rock Night', '2025-08-01-20-00', 4, 'i', '2025-06-20 05:30:00', '2025-06-20 05:30:00', 'Rock Concert'),
(23, 'Bazar Buku', '2025-06-15-10-00', 4, 'i', '2025-05-30 02:00:00', '2025-06-01 03:00:00', 'Diskon buku besar-besaran'),
(24, 'Stand Up Show', '2025-06-28-20-00', 4, 'i', '2025-06-10 07:00:00', '2025-06-28 11:00:00', 'Komedi malam minggu');

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `id` int NOT NULL,
  `message` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `user` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ticket_id` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `paymentmethods`
--

CREATE TABLE `paymentmethods` (
  `id` int NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `paymentmethods`
--

INSERT INTO `paymentmethods` (`id`, `name`) VALUES
(12, 'BCA'),
(13, 'BNI'),
(14, 'BRI'),
(15, 'CIMB'),
(16, 'GoPay'),
(17, 'OVO');

-- --------------------------------------------------------

--
-- Table structure for table `provinces`
--

CREATE TABLE `provinces` (
  `id` int NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `provinces`
--

INSERT INTO `provinces` (`id`, `name`) VALUES
(1, 'Jawa Barat'),
(2, 'DKI Jakarta'),
(3, 'Jawa Timur');

-- --------------------------------------------------------

--
-- Table structure for table `sellers`
--

CREATE TABLE `sellers` (
  `username` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `companyName` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phoneNumber` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `companyAddress` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `sellers`
--

INSERT INTO `sellers` (`username`, `password`, `companyName`, `email`, `phoneNumber`, `companyAddress`, `create_time`, `update_time`) VALUES
('i', 'iiiiiiii', 'i', 'i@gmail.com', '999999999999', 'i', '2025-06-29 06:37:04', '2025-06-29 06:37:04'),
('x', 'xxxxxxxx', 'XXI', 'xxicinema@gmail.com', '', 'jl panlima', '2025-06-29 06:36:47', '2025-06-29 06:36:47');

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `eventClass_id` int NOT NULL,
  `paymentMethod_id` int NOT NULL,
  `paymentStatus` enum('UNPAID','PAID','REQUEST REFUND','REFUNDED','CANCELLED') COLLATE utf8mb4_unicode_ci NOT NULL,
  `isClaimed` tinyint(1) NOT NULL DEFAULT '0',
  `paidtime` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullname` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phoneNumber` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `birthdate` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `venues`
--

CREATE TABLE `venues` (
  `id` int NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `area` int DEFAULT NULL,
  `maxCapacity` int NOT NULL,
  `city_id` int NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `venues`
--

INSERT INTO `venues` (`id`, `name`, `address`, `area`, `maxCapacity`, `city_id`, `create_time`, `update_time`) VALUES
(1, 'Gedung Sabilulungan', 'Jl. Soreang No.12, Bandung', 400, 1000, 1, '2025-06-26 09:15:31', '2025-06-26 09:15:31'),
(2, 'Convention Hall Bekasi', 'Jl. Juanda No.45, Bekasi', 300, 800, 2, '2025-06-26 09:15:31', '2025-06-26 09:15:31'),
(3, 'Balai Kartini', 'Jl. Gatot Subroto Kav. 37, Jakarta Selatan', 600, 1500, 3, '2025-06-26 09:15:31', '2025-06-26 09:15:31'),
(4, 'Grand City Convex', 'Jl. Walikota Mustajab No.1, Surabaya', 500, 1200, 4, '2025-06-26 09:15:31', '2025-06-26 09:15:31');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `broadcasts`
--
ALTER TABLE `broadcasts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_cities_provinces_idx` (`province_id`);

--
-- Indexes for table `eventclasses`
--
ALTER TABLE `eventclasses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_eventClasses_events_idx` (`event_id`);

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_events_venues_idx` (`venue_id`),
  ADD KEY `fk_events_sellers_idx` (`seller`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_notifications_users_idx` (`user`),
  ADD KEY `fk_notifications_tickets_idx` (`ticket_id`);

--
-- Indexes for table `paymentmethods`
--
ALTER TABLE `paymentmethods`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `provinces`
--
ALTER TABLE `provinces`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sellers`
--
ALTER TABLE `sellers`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_tickets_users_idx` (`user`),
  ADD KEY `fk_tickets_eventClasses_idx` (`eventClass_id`),
  ADD KEY `fk_tickets_paymentMethods_idx` (`paymentMethod_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`);

--
-- Indexes for table `venues`
--
ALTER TABLE `venues`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_venues_cities_idx` (`city_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `broadcasts`
--
ALTER TABLE `broadcasts`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cities`
--
ALTER TABLE `cities`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `eventclasses`
--
ALTER TABLE `eventclasses`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `events`
--
ALTER TABLE `events`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `paymentmethods`
--
ALTER TABLE `paymentmethods`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `provinces`
--
ALTER TABLE `provinces`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `venues`
--
ALTER TABLE `venues`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cities`
--
ALTER TABLE `cities`
  ADD CONSTRAINT `fk_cities_provinces` FOREIGN KEY (`province_id`) REFERENCES `provinces` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Constraints for table `eventclasses`
--
ALTER TABLE `eventclasses`
  ADD CONSTRAINT `fk_eventClasses_events` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `fk_events_sellers` FOREIGN KEY (`seller`) REFERENCES `sellers` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_events_venues` FOREIGN KEY (`venue_id`) REFERENCES `venues` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `fk_notifications_tickets` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_notifications_users` FOREIGN KEY (`user`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `fk_tickets_eventClasses` FOREIGN KEY (`eventClass_id`) REFERENCES `eventclasses` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_tickets_paymentMethods` FOREIGN KEY (`paymentMethod_id`) REFERENCES `paymentmethods` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_tickets_users` FOREIGN KEY (`user`) REFERENCES `users` (`username`) ON UPDATE CASCADE;

--
-- Constraints for table `venues`
--
ALTER TABLE `venues`
  ADD CONSTRAINT `fk_venues_cities` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

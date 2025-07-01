-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 01, 2025 at 11:55 AM
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
  `message` text NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

CREATE TABLE `cities` (
  `id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `province_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `cities`
--

INSERT INTO `cities` (`id`, `name`, `province_id`) VALUES
(1, 'Banda Aceh', 1),
(2, 'Langsa', 1),
(3, 'Lhokseumawe', 1),
(4, 'Sabang', 1),
(5, 'Subulussalam', 1),
(6, 'Denpasar', 2),
(7, 'Pangkal Pinang', 3),
(8, 'Cilegon', 4),
(9, 'Serang', 4),
(10, 'Tangerang', 4),
(11, 'South Tangerang', 4),
(12, 'Bengkulu', 5),
(13, 'Magelang', 6),
(14, 'Pekalongan', 6),
(15, 'Salatiga', 6),
(16, 'Semarang', 6),
(17, 'Surakarta', 6),
(18, 'Tegal', 6),
(19, 'Palangka Raya', 7),
(20, 'Palu', 9),
(21, 'Batu', 10),
(22, 'Blitar', 10),
(23, 'Kediri', 10),
(24, 'Madiun', 10),
(25, 'Malang', 10),
(26, 'Mojokerto', 10),
(27, 'Pasuruan', 10),
(28, 'Probolinggo', 10),
(29, 'Surabaya', 10),
(30, 'Balikpapan', 11),
(31, 'Bontang', 11),
(32, 'Samarinda', 11),
(33, 'Kupang', 12),
(34, 'Gorontalo', 13),
(35, 'West Jakarta', 15),
(36, 'Central Jakarta', 15),
(37, 'South Jakarta', 15),
(38, 'East Jakarta', 15),
(39, 'North Jakarta', 15),
(40, 'Jambi', 16),
(41, 'Sungai Penuh', 16),
(42, 'Bandar Lampung', 17),
(43, 'Metro', 17),
(44, 'Ambon', 18),
(45, 'Tual', 18),
(46, 'Tarakan', 19),
(47, 'Ternate', 20),
(48, 'Tidore', 20),
(49, 'Bitung', 21),
(50, 'Kotamobagu', 21),
(51, 'Manado', 21),
(52, 'Tomohon', 21),
(53, 'Binjai', 22),
(54, 'Gunungsitoli', 22),
(55, 'Medan', 22),
(56, 'Padangsidimpuan', 22),
(57, 'Pematangsiantar', 22),
(58, 'Sibolga', 22),
(59, 'Tanjungbalai', 22),
(60, 'Tebing Tinggi', 22),
(61, 'Jayapura', 23),
(62, 'Dumai', 24),
(63, 'Pekanbaru', 24),
(64, 'Batam', 25),
(65, 'Tanjungpinang', 25),
(66, 'Banjarbaru', 26),
(67, 'Banjarmasin', 26),
(68, 'Makassar', 28),
(69, 'Palopo', 28),
(70, 'Parepare', 28),
(71, 'Lubuklinggau', 29),
(72, 'Pagar Alam', 29),
(73, 'Palembang', 29),
(74, 'Prabumulih', 29),
(75, 'Baubau', 30),
(76, 'Kendari', 30),
(77, 'Sorong', 31),
(78, 'Bandung', 32),
(79, 'Banjar', 32),
(80, 'Bekasi', 32),
(81, 'Bogor', 32),
(82, 'Cimahi', 32),
(83, 'Cirebon', 32),
(84, 'Depok', 32),
(85, 'Sukabumi', 32),
(86, 'Tasikmalaya', 32),
(87, 'Pontianak', 33),
(88, 'Singkawang', 33),
(89, 'Bima', 34),
(90, 'Mataram', 34),
(91, 'Bukittinggi', 37),
(92, 'Padang', 37),
(93, 'Padang Panjang', 37),
(94, 'Pariaman', 37),
(95, 'Payakumbuh', 37),
(96, 'Sawahlunto', 37),
(97, 'Solok', 37),
(98, 'Yogyakarta', 38);

-- --------------------------------------------------------

--
-- Table structure for table `eventclasses`
--

CREATE TABLE `eventclasses` (
  `id` int NOT NULL,
  `event_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `description` text,
  `stock` int NOT NULL,
  `availableStock` int NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE `events` (
  `id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `description` text,
  `startDateTime` varchar(255) NOT NULL,
  `seller` varchar(45) NOT NULL,
  `venue_id` int NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `flashsales`
--

CREATE TABLE `flashsales` (
  `id` int NOT NULL,
  `eventClass_id` int NOT NULL,
  `price` double NOT NULL,
  `startTime` varchar(255) NOT NULL,
  `endTime` varchar(255) NOT NULL,
  `initialStock` int NOT NULL,
  `stock` int NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `id` int NOT NULL,
  `message` text NOT NULL,
  `username` varchar(45) NOT NULL,
  `ticket_id` varchar(300) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `paymentmethods`
--

CREATE TABLE `paymentmethods` (
  `id` int NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `paymentmethods`
--

INSERT INTO `paymentmethods` (`id`, `name`) VALUES
(1, 'BCA Virtual Account'),
(2, 'Mandiri Virtual Account'),
(3, 'BNI Virtual Account'),
(4, 'BRI Virtual Account'),
(5, 'Permata Virtual Account'),
(6, 'CIMB Niaga Virtual Account'),
(7, 'Credit Card (Visa/Mastercard/JCB/Amex)'),
(8, 'QRIS (All e-wallets & mobile banking)'),
(9, 'GoPay'),
(10, 'OVO'),
(11, 'DANA'),
(12, 'ShopeePay'),
(13, 'LinkAja'),
(14, 'Indomaret'),
(15, 'Alfamart');

-- --------------------------------------------------------

--
-- Table structure for table `provinces`
--

CREATE TABLE `provinces` (
  `id` int NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `provinces`
--

INSERT INTO `provinces` (`id`, `name`) VALUES
(1, 'Aceh'),
(2, 'Bali'),
(3, 'Bangka Belitung Islands'),
(4, 'Banten'),
(5, 'Bengkulu'),
(6, 'Central Java'),
(7, 'Central Kalimantan'),
(8, 'Central Papua'),
(9, 'Central Sulawesi'),
(10, 'East Java'),
(11, 'East Kalimantan'),
(12, 'East Nusa Tenggara'),
(13, 'Gorontalo'),
(14, 'Highland Papua'),
(15, 'Jakarta'),
(16, 'Jambi'),
(17, 'Lampung'),
(18, 'Maluku'),
(19, 'North Kalimantan'),
(20, 'North Maluku'),
(21, 'North Sulawesi'),
(22, 'North Sumatra'),
(23, 'Papua'),
(24, 'Riau'),
(25, 'Riau Islands'),
(26, 'South Kalimantan'),
(27, 'South Papua'),
(28, 'South Sulawesi'),
(29, 'South Sumatra'),
(30, 'Southeast Sulawesi'),
(31, 'Southwest Papua'),
(32, 'West Java'),
(33, 'West Kalimantan'),
(34, 'West Nusa Tenggara'),
(35, 'West Papua'),
(36, 'West Sulawesi'),
(37, 'West Sumatra'),
(38, 'Special Region of Yogyakarta');

-- --------------------------------------------------------

--
-- Table structure for table `sellers`
--

CREATE TABLE `sellers` (
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `companyName` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(13) NOT NULL,
  `companyAddress` varchar(100) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `sellers`
--

INSERT INTO `sellers` (`username`, `password`, `fullname`, `email`, `companyName`, `phoneNumber`, `companyAddress`, `create_time`, `update_time`) VALUES
('88rising', '12345678', 'Sean Miyashiro', '88rising@email.com', '88 Rising', '08123456789', 'Jl. Naikan no.88', '2025-07-01 09:14:43', '2025-07-01 09:14:43'),
('eminem', '12345678', 'Marshall Mathers', 'eminem@email.com', 'Rap God Ent', '08123456789', 'Jl. Rap', '2025-07-01 09:14:43', '2025-07-01 09:14:43'),
('jypent', '12345678', 'Park Jin-young', 'JYP@email.com', 'JYP Entertainment', '08123456789', 'Jl. Korea', '2025-07-01 09:14:43', '2025-07-01 09:14:43'),
('milesdavis', '12345678', 'Miles Davis', 'MilesDavis@email.com', 'Miles Davis Jazz.Co', '08123456789', 'Jl. Jambu', '2025-07-01 09:14:43', '2025-07-01 09:14:43'),
('sment', '12345678', 'Lee Soo-man', 'SM@email.com', 'SM Entertainment', '08123456789', 'Jl. Korea Sanaan Dikit', '2025-07-01 09:14:43', '2025-07-01 09:14:43');

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `id` varchar(300) NOT NULL,
  `user` varchar(45) NOT NULL,
  `eventClass_id` int NOT NULL,
  `paymentMethod_id` int DEFAULT NULL,
  `price` double NOT NULL,
  `paymentStatus` enum('UNPAID','PAID','CANCELLED','REQUEST REFUND','REFUND') NOT NULL,
  `isClaimed` tinyint(1) DEFAULT '0',
  `paidTime` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phoneNumber` varchar(13) NOT NULL,
  `birthdate` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `fullname`, `email`, `phoneNumber`, `birthdate`, `create_time`, `update_time`) VALUES
('alexjones', '12345678', 'Alex Jones', 'alex.j@email.com', '081456789012', '1988-11-30', '2025-07-01 09:14:45', '2025-07-01 09:14:45'),
('emilywhite', '12345678', 'Emily White', 'emily.w@email.com', '081567890123', '1995-02-10', '2025-07-01 09:14:45', '2025-07-01 09:14:45'),
('janesmith', '12345678', 'Jane Smith', 'jane.smith@email.com', '081345678901', '1992-08-22', '2025-07-01 09:14:45', '2025-07-01 09:14:45'),
('johndoe', '12345678', 'John Doe', 'john.doe@email.com', '081234567890', '1990-05-15', '2025-07-01 09:14:45', '2025-07-01 09:14:45'),
('michaels', '12345678users', 'Michael Suryo', 'michael.suryo@email.com', '081678901234', '1998-07-19', '2025-07-01 09:14:45', '2025-07-01 09:14:45'),
('y', 'yyyyyyyy', 'y', 'y@gmail.com', '123212321232', '2005-08-02', '2025-07-01 09:39:21', '2025-07-01 09:39:21');

-- --------------------------------------------------------

--
-- Table structure for table `venues`
--

CREATE TABLE `venues` (
  `id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `city_id` int NOT NULL,
  `address` varchar(100) NOT NULL,
  `area` int DEFAULT NULL,
  `maxCapacity` int DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `venues`
--

INSERT INTO `venues` (`id`, `name`, `city_id`, `address`, `area`, `maxCapacity`, `create_time`, `update_time`) VALUES
(1, 'Gelora Bung Karno Main Stadium', 36, 'Jl. Pintu Satu Senayan, Gelora, Tanah Abang', 76000, 77193, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(2, 'Jakarta Convention Center', 36, 'Jl. Gatot Subroto No.1, Gelora, Tanah Abang', 120000, 15000, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(3, 'JIExpo Kemayoran', 39, 'Jl. H. Benyamin Sueb, Pademangan Timur', 440000, 20000, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(4, 'Taman Mini Indonesia Indah', 38, 'Jl. Raya Taman Mini, Ceger, Cipayung', 1500000, 10000, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(5, 'Grand City Convention and Exhibition', 29, 'Jl. Walikota Mustajab No.1, Ketabang, Genteng', 21000, 5000, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(6, 'Jatim International Expo (JIE)', 29, 'Jl. Ahmad Yani No.99, Jemur Wonosari, Wonocolo', 50000, 10000, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(7, 'Gelora Bung Tomo Stadium', 29, 'Jl. Jawar, Benowo, Pakal', 150000, 45134, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(8, 'Trans Convention Centre', 78, 'Jl. Gatot Subroto No.289, Cibangkong, Batununggal', 6300, 4000, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(9, 'Gedung Sate', 78, 'Jl. Diponegoro No.22, Citarum, Bandung Wetan', 27000, 1000, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(10, 'Sabuga (Sasana Budaya Ganesha)', 78, 'Jl. Tamansari No.73, Lb. Siliwangi, Coblong', 12000, 2500, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(11, 'Bali Nusa Dua Convention Center', 6, 'Kawasan Pariwisata Nusa Dua, Benoa', 50000, 10000, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(12, 'Garuda Wisnu Kencana (GWK) Cultural Park', 6, 'Jl. Raya Uluwatu, Ungasan, Kuta Selatan', 600000, 25000, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(13, 'Jogja Expo Center (JEC)', 98, 'Jl. Janti, Wonocatur, Banguntapan', 14000, 8000, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(14, 'Santika Premiere Dyandra Hotel & Convention', 55, 'Jl. Kapten Maulana Lubis No.7', 4000, 3000, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(15, 'Balikpapan International Stadium Batakan', 30, 'Jl. Mulawarman, Manggar, Balikpapan Timur', 100000, 40000, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(16, 'Pontianak Convention Center', 87, 'Jl. Sultan Syarif Abdurrahman No.7', 5000, 3000, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(17, 'Gedung Sultan Suriansyah', 67, 'Jl. Brigjend H. Hasan Basri, Kayu Tangi', 4000, 2000, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(18, 'Palaran Stadium', 32, 'Jl. AM. Alimuddin, Simpang Tiga, Palaran', 80000, 35000, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(19, 'Celebes Convention Center', 68, 'Jl. Metro Tanjung Bunga, Maccini Sombala', 10000, 5000, '2025-07-01 09:14:49', '2025-07-01 09:14:49'),
(20, 'Manado Convention Center', 51, 'Jl. Piere Tendean, Sario Tumpaan', 4500, 2000, '2025-07-01 09:14:49', '2025-07-01 09:14:49');

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
  ADD KEY `fk_events_sellers_idx` (`seller`),
  ADD KEY `fk_events_venues_idx` (`venue_id`);

--
-- Indexes for table `flashsales`
--
ALTER TABLE `flashsales`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_flashsales_eventClasses_idx` (`eventClass_id`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_notifications_users_idx` (`username`),
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
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;

--
-- AUTO_INCREMENT for table `eventclasses`
--
ALTER TABLE `eventclasses`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `events`
--
ALTER TABLE `events`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `flashsales`
--
ALTER TABLE `flashsales`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `paymentmethods`
--
ALTER TABLE `paymentmethods`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `provinces`
--
ALTER TABLE `provinces`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `venues`
--
ALTER TABLE `venues`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

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
  ADD CONSTRAINT `fk_events_venues` FOREIGN KEY (`venue_id`) REFERENCES `venues` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Constraints for table `flashsales`
--
ALTER TABLE `flashsales`
  ADD CONSTRAINT `fk_flashsales_eventClasses` FOREIGN KEY (`eventClass_id`) REFERENCES `eventclasses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `fk_notifications_tickets` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_notifications_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `fk_tickets_eventClasses` FOREIGN KEY (`eventClass_id`) REFERENCES `eventclasses` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_tickets_paymentMethods` FOREIGN KEY (`paymentMethod_id`) REFERENCES `paymentmethods` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_tickets_users` FOREIGN KEY (`user`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `venues`
--
ALTER TABLE `venues`
  ADD CONSTRAINT `fk_venues_cities` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

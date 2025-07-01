-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: ticketmarketplace
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `broadcasts`
--

LOCK TABLES `broadcasts` WRITE;
/*!40000 ALTER TABLE `broadcasts` DISABLE KEYS */;
/*!40000 ALTER TABLE `broadcasts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'Bandung',1),(2,'Jakarta Selatan',2),(3,'Denpasar',3),(4,'Jakarta Barat',2),(5,'Ubud',3);
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `eventclasses`
--

LOCK TABLES `eventclasses` WRITE;
/*!40000 ALTER TABLE `eventclasses` DISABLE KEYS */;
INSERT INTO `eventclasses` VALUES (1,1,'VIP',500000,'Tempat duduk terbaik di depan panggung.',100,100,'2025-06-30 16:58:11','2025-06-30 16:58:11'),(2,1,'Reguler',200000,'Tempat duduk biasa.',400,400,'2025-06-30 16:58:11','2025-06-30 16:58:11'),(3,2,'Gold',300000,'Tempat duduk tengah.',150,150,'2025-06-30 16:58:11','2025-06-30 16:58:11'),(4,2,'Silver',150000,'Tempat duduk belakang.',200,200,'2025-06-30 16:58:11','2025-06-30 16:58:11'),(5,3,'Business',750000,'Kelas bisnis dengan fasilitas VIP.',80,80,'2025-06-30 16:58:11','2025-06-30 16:58:11'),(6,3,'Standard',350000,'Akses ke semua seminar umum.',300,300,'2025-06-30 16:58:11','2025-06-30 16:58:11'),(7,4,'Front Row',450000,'Baris depan konser.',100,100,'2025-06-30 16:58:11','2025-06-30 16:58:11'),(8,4,'Balcony',250000,'Tempat duduk balkon.',150,150,'2025-06-30 16:58:11','2025-06-30 16:58:11');
/*!40000 ALTER TABLE `eventclasses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (1,'Festival Musik Indie','indiefest.jpg','Festival musik untuk band-band indie lokal.','2025-07-15-18-00','seller01',1,'2025-06-30 16:58:08','2025-06-30 16:58:08'),(2,'Stand Up Comedy Night','comedy_night.png','Tawa malam bersama komika terbaik Indonesia.','2025-08-10-20-00','seller02',2,'2025-06-30 16:58:08','2025-06-30 16:58:08'),(3,'Tech Conference 2025','tech_conf.jpg','Konferensi teknologi tahunan terbesar.','2025-09-05-09-00','seller03',3,'2025-06-30 16:58:08','2025-06-30 16:58:08'),(4,'Konser Jazz Jakarta','jazz_jakarta.jpg','Persembahan musik jazz dari musisi papan atas.','2025-07-25-19-30','seller04',4,'2025-06-30 16:58:08','2025-06-30 16:58:08');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `flashsales`
--

LOCK TABLES `flashsales` WRITE;
/*!40000 ALTER TABLE `flashsales` DISABLE KEYS */;
/*!40000 ALTER TABLE `flashsales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `paymentmethods`
--

LOCK TABLES `paymentmethods` WRITE;
/*!40000 ALTER TABLE `paymentmethods` DISABLE KEYS */;
INSERT INTO `paymentmethods` VALUES (1,'BCA'),(2,'BNI'),(3,'BRI'),(4,'CIMB'),(5,'GoPay'),(6,'OVO');
/*!40000 ALTER TABLE `paymentmethods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `provinces`
--

LOCK TABLES `provinces` WRITE;
/*!40000 ALTER TABLE `provinces` DISABLE KEYS */;
INSERT INTO `provinces` VALUES (1,'Jawa Barat'),(2,'DKI Jakarta'),(3,'Bali');
/*!40000 ALTER TABLE `provinces` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sellers`
--

LOCK TABLES `sellers` WRITE;
/*!40000 ALTER TABLE `sellers` DISABLE KEYS */;
INSERT INTO `sellers` VALUES ('seller01','pass123','Indie Fest Organizer','indiefest@mail.com','IndieFest Corp','081234567891','Jl. Pasteur No.5','2025-06-30 16:57:55','2025-06-30 16:57:55'),('seller02','pass234','Comicindo Team','comicindo@mail.com','Komika Indo','082345678912','Jl. Mampang No.12','2025-06-30 16:57:55','2025-06-30 16:57:55'),('seller03','pass345','TechConf Official','techconf@mail.com','TechEvent ID','083456789123','Jl. Mahendradatta No.9','2025-06-30 16:57:55','2025-06-30 16:57:55'),('seller04','pass456','Jazz Jak Crew','jazzjak@mail.com','Jazz Jakarta Ltd','084567891234','Jl. Senayan No.21','2025-06-30 16:57:55','2025-06-30 16:57:55'),('seller05','pass567','Seni Bali Admin','senibali@mail.com','Bali Artworks','085678912345','Jl. Raya Tegallalang','2025-06-30 16:57:55','2025-06-30 16:57:55');
/*!40000 ALTER TABLE `sellers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('y','yyyyyyyy','y','y@gmail.com','222222222222','2000-12-12','2025-06-30 16:58:49','2025-06-30 16:58:49');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `venues`
--

LOCK TABLES `venues` WRITE;
/*!40000 ALTER TABLE `venues` DISABLE KEYS */;
INSERT INTO `venues` VALUES (1,'Stadion Siliwangi',1,'Jl. Lombok No.10',2000,15000,'2025-06-30 16:57:02','2025-06-30 16:57:02'),(2,'Comedy Cafe Jakarta',2,'Jl. Kemang Raya No.15',300,500,'2025-06-30 16:57:02','2025-06-30 16:57:02'),(3,'Bali Tech Center',3,'Jl. Teuku Umar No.20',800,2000,'2025-06-30 16:57:02','2025-06-30 16:57:02'),(4,'Jakarta Jazz Hall',4,'Jl. Palmerah No.5',1200,5000,'2025-06-30 16:57:02','2025-06-30 16:57:02'),(5,'Museum Puri Lukisan',5,'Jl. Raya Ubud',400,1000,'2025-06-30 16:57:02','2025-06-30 16:57:02');
/*!40000 ALTER TABLE `venues` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-01  0:19:13

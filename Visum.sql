-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: visum
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `album_songs`
--

DROP TABLE IF EXISTS `album_songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `album_songs` (
  `song_id` bigint NOT NULL,
  `album_id` bigint NOT NULL,
  KEY `FKpptuy3tflj5odwjyde127mbgm` (`album_id`),
  KEY `FKtj7yklxvvv9ujtchpvfdeq7vj` (`song_id`),
  CONSTRAINT `FKpptuy3tflj5odwjyde127mbgm` FOREIGN KEY (`album_id`) REFERENCES `albums` (`id`),
  CONSTRAINT `FKtj7yklxvvv9ujtchpvfdeq7vj` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album_songs`
--

LOCK TABLES `album_songs` WRITE;
/*!40000 ALTER TABLE `album_songs` DISABLE KEYS */;
INSERT INTO `album_songs` VALUES (5,1),(8,1),(12,2),(13,2),(14,2);
/*!40000 ALTER TABLE `album_songs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `albums`
--

DROP TABLE IF EXISTS `albums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `albums` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_byte` tinyblob,
  `image_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `singer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKncjy4djggwldadf3ywmowwr2j` (`singer_id`),
  CONSTRAINT `FKncjy4djggwldadf3ywmowwr2j` FOREIGN KEY (`singer_id`) REFERENCES `singers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
INSERT INTO `albums` VALUES (1,NULL,'the-reaper-album','The Reaper',1),(2,NULL,'Những Bài Hát Hay Nhất Của Noo Phước Thịnh','Những Bài Hát Hay Nhất Của Noo Phước Thịnh',4);
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Quốc Gia'),(2,'Hip-hop'),(3,'Trữ tình & bolero'),(4,'EDM'),(5,'Nhạc không lời');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `song_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjss5ndgf3fog24fnj5oo19712` (`song_id`),
  KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`),
  CONSTRAINT `FK8omq0tc18jd43bu5tjh6jvraq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKjss5ndgf3fog24fnj5oo19712` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (2,'comment 2','2022-12-30 17:08:06',1,3);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `composers`
--

DROP TABLE IF EXISTS `composers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `composers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `composer_name` varchar(255) DEFAULT NULL,
  `information` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composers`
--

LOCK TABLES `composers` WRITE;
/*!40000 ALTER TABLE `composers` DISABLE KEYS */;
/*!40000 ALTER TABLE `composers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorites` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `song_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqs8yha6nwtpmpq4xa0yro7gwj` (`song_id`),
  KEY `FKk7du8b8ewipawnnpg76d55fus` (`user_id`),
  CONSTRAINT `FKk7du8b8ewipawnnpg76d55fus` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKqs8yha6nwtpmpq4xa0yro7gwj` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
INSERT INTO `favorites` VALUES (12,3,3),(18,8,3),(19,6,3),(20,4,3),(21,10,3),(23,5,3),(24,7,3),(26,11,3),(27,1,3);
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (3);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_songs`
--

DROP TABLE IF EXISTS `playlist_songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist_songs` (
  `song_id` bigint NOT NULL,
  `playlist_id` bigint NOT NULL,
  PRIMARY KEY (`song_id`,`playlist_id`),
  KEY `FKqfutupgj870d2k31ldxqqwr8w` (`playlist_id`),
  CONSTRAINT `FK5xu79gpgpc1p4tku7j6dv2skb` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`),
  CONSTRAINT `FKqfutupgj870d2k31ldxqqwr8w` FOREIGN KEY (`playlist_id`) REFERENCES `playlists` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_songs`
--

LOCK TABLES `playlist_songs` WRITE;
/*!40000 ALTER TABLE `playlist_songs` DISABLE KEYS */;
INSERT INTO `playlist_songs` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(1,5),(8,5),(16,7),(1,9),(7,9),(9,9),(11,9),(1,10),(2,10),(3,10),(4,10),(5,10),(8,10);
/*!40000 ALTER TABLE `playlist_songs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlists`
--

DROP TABLE IF EXISTS `playlists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlists` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `playlist_name` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtgjwvfg23v990xk7k0idmqbrj` (`user_id`),
  CONSTRAINT `FKtgjwvfg23v990xk7k0idmqbrj` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlists`
--

LOCK TABLES `playlists` WRITE;
/*!40000 ALTER TABLE `playlists` DISABLE KEYS */;
INSERT INTO `playlists` VALUES (1,'playlít 3',3),(5,'nhạc thiền dễ ngủ',3),(7,'Hiphop playlist',3),(9,'nhạc thiền dễ ngủ',5),(10,'abc',3);
/*!40000 ALTER TABLE `playlists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recently`
--

DROP TABLE IF EXISTS `recently`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recently` (
  `id` bigint NOT NULL,
  `song_id` bigint DEFAULT NULL,
  `time` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKre1a41eskoloxf19k8wcwa1xf` (`user_id`),
  CONSTRAINT `FKre1a41eskoloxf19k8wcwa1xf` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recently`
--

LOCK TABLES `recently` WRITE;
/*!40000 ALTER TABLE `recently` DISABLE KEYS */;
INSERT INTO `recently` VALUES (1,3,'2023-02-22 00:00:00.000000',3),(2,5,'2023-02-22 00:00:00.000000',3);
/*!40000 ALTER TABLE `recently` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,NULL,'ROLE_USER'),(2,NULL,'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `singers`
--

DROP TABLE IF EXISTS `singers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `singers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `singer_name` varchar(255) DEFAULT NULL,
  `followers` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `singers`
--

LOCK TABLES `singers` WRITE;
/*!40000 ALTER TABLE `singers` DISABLE KEYS */;
INSERT INTO `singers` VALUES (1,NULL,'Keshi',321841),(2,NULL,'Stephen Sanchez',123421),(3,NULL,'Nicky Youre',86041),(4,NULL,'Noo Phước Thịnh',895641),(5,NULL,'Martin Garrix',745754),(6,NULL,'Alan Walker',125274),(7,NULL,'R3hab, Mike Williams',121003),(8,NULL,'Mono',74364),(9,NULL,'Arizona Zervas',35123),(10,NULL,'Travis Scott',12345),(11,NULL,'Lil Tjay',1313),(12,NULL,'Dj Khaled',21312);
/*!40000 ALTER TABLE `singers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `songs`
--

DROP TABLE IF EXISTS `songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `songs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `duration` decimal(18,10) NOT NULL,
  `image` mediumblob,
  `image_name` varchar(255) DEFAULT NULL,
  `song_name` varchar(255) DEFAULT NULL,
  `views` int NOT NULL DEFAULT '0',
  `composer_id` bigint DEFAULT NULL,
  `singer_id` bigint DEFAULT NULL,
  `sub_category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq8jw458vnbt8rms06p073wvfm` (`composer_id`),
  KEY `FKaporlg6dhv2vtfakbiv41hajj` (`singer_id`),
  KEY `FKrav0h23jikcpehpeyvtahijeg` (`sub_category_id`),
  CONSTRAINT `FKaporlg6dhv2vtfakbiv41hajj` FOREIGN KEY (`singer_id`) REFERENCES `singers` (`id`),
  CONSTRAINT `FKq8jw458vnbt8rms06p073wvfm` FOREIGN KEY (`composer_id`) REFERENCES `composers` (`id`),
  CONSTRAINT `FKrav0h23jikcpehpeyvtahijeg` FOREIGN KEY (`sub_category_id`) REFERENCES `sub_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `songs`
--

LOCK TABLES `songs` WRITE;
/*!40000 ALTER TABLE `songs` DISABLE KEYS */;
INSERT INTO `songs` VALUES (1,163.0796460000,NULL,'Sunroof','Sunroof',1,NULL,3,1),(2,4278.7313560000,NULL,'Keshi playlist','Keshi playlist',2,NULL,1,1),(3,177.7863320000,NULL,'Until I Found You','Until I Found You',3,NULL,2,1),(4,183.0368540000,NULL,'Lady By The Sea','Lady By The Sea',4,NULL,2,1),(5,166.9718240000,NULL,'Summer Keshi','Summer',5,NULL,1,1),(6,300.0634140000,NULL,'Định mệnh','Định mệnh',6,NULL,4,2),(7,160.7809100000,NULL,'Hero','Hero',7,NULL,5,8),(8,207.1213380000,NULL,'2 Soon','2 Soon',8,NULL,1,1),(9,145.8913700000,NULL,'Ritual','Ritual',9,NULL,6,8),(10,265.6560000000,NULL,'Waiting For You','Waiting For You',10,NULL,8,2),(11,222.5333180000,NULL,'Sing Your Lullaby','Sing Your Lullaby',11,NULL,7,8),(12,346.2240000000,NULL,'Chạm Khẽ Tim Anh Một Chút Thôi','Chạm Khẽ Tim Anh Một Chút Thôi',15,NULL,4,2),(13,285.3616330000,NULL,'Cause I Love You','Cause I Love You',16,NULL,4,2),(14,291.8138780000,NULL,'Em Đã Thương Người Ta Hơn Anh','Em Đã Thương Người Ta Hơn Anh',26,NULL,4,2),(15,0.0000000000,NULL,'ROXANNE','ROXANNE',12,NULL,9,9),(16,0.0000000000,NULL,'ESCAPE PLAN','ESCAPE PLAN',11,NULL,10,9),(17,0.0000000000,NULL,'Calling my phone','Calling my phone',15,NULL,11,9),(18,0.0000000000,NULL,'STAYING ALIVE','STAYING ALIVE',421,NULL,12,9);
/*!40000 ALTER TABLE `songs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_categories`
--

DROP TABLE IF EXISTS `sub_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sub_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sub_category_name` varchar(255) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjwy7imy3rf6r99x48ydq45otw` (`category_id`),
  CONSTRAINT `FKjwy7imy3rf6r99x48ydq45otw` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_categories`
--

LOCK TABLES `sub_categories` WRITE;
/*!40000 ALTER TABLE `sub_categories` DISABLE KEYS */;
INSERT INTO `sub_categories` VALUES (1,'Nhạc Châu Âu',1),(2,'Nhạc Việt',1),(3,'Nhạc Hàn',1),(4,'Nhạc Hoa',1),(5,'Tuyệt đỉnh Bolero trữ tình',3),(6,'Dance Pop',4),(7,'Dance rewind',4),(8,'Đỉnh cao EDM',4),(9,'Just Melodic Rap',2),(10,'2000s Hip-hop',2),(11,'Rap collabs',2),(12,'Instrumental Today',5),(13,'Cello Pieces',5);
/*!40000 ALTER TABLE `sub_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trending`
--

DROP TABLE IF EXISTS `trending`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trending` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `week` int NOT NULL,
  `song_id` bigint DEFAULT NULL,
  `day` int NOT NULL,
  `views_day` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkflkyywd5nh8ljbjhhxo7k47i` (`song_id`),
  CONSTRAINT `FKkflkyywd5nh8ljbjhhxo7k47i` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trending`
--

LOCK TABLES `trending` WRITE;
/*!40000 ALTER TABLE `trending` DISABLE KEYS */;
INSERT INTO `trending` VALUES (1,6,1,35,50),(2,6,1,36,60),(3,6,1,37,40),(4,6,1,38,30),(5,6,1,39,70),(6,6,1,40,80),(7,6,1,41,90),(8,6,2,35,30),(9,6,2,36,37),(10,6,2,37,54),(11,6,2,38,12),(12,6,2,39,30),(13,6,2,40,40),(14,6,2,41,50),(15,6,3,35,30),(16,6,3,36,37),(17,6,3,37,54),(18,6,3,38,48),(19,6,3,39,30),(20,6,3,40,40),(21,6,3,41,50),(22,6,4,35,58),(23,6,4,36,89),(24,6,4,37,90),(25,6,4,38,100),(26,6,4,39,120),(27,6,4,40,80),(29,6,4,41,100),(30,7,1,42,4532),(31,7,1,43,321),(32,7,1,44,563),(33,7,1,45,1235),(34,7,1,46,1235),(35,7,1,47,2345),(36,7,1,48,1234),(37,7,2,42,134),(38,7,2,43,2342),(39,7,2,44,423),(40,7,2,45,324),(41,7,2,46,421),(42,7,2,47,434),(43,7,3,48,256),(44,7,3,42,375),(45,7,3,43,444),(46,7,3,44,446),(47,7,3,45,3543),(48,7,3,46,3547),(49,7,3,47,342),(50,7,3,48,2342),(51,7,3,42,13),(52,7,14,43,535),(53,7,13,44,313),(54,7,12,45,34),(55,7,2,46,34),(56,7,8,47,24),(57,7,11,48,543),(58,8,11,52,1),(59,8,3,52,1),(60,8,8,53,3),(61,8,18,53,1),(62,8,2,53,1),(63,8,3,53,5),(64,8,5,53,2),(65,8,4,53,1),(66,8,12,53,2),(67,8,10,53,3);
/*!40000 ALTER TABLE `trending` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (3,1),(4,1),(5,1);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avatar_image` mediumblob,
  `birth_of_date` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender_enum` int DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,NULL,'2002-03-07 00:00:00.000000','truongquocviet2002@gmail.com','Việt',0,'default','Trương','0905054200','$2a$10$GOYPJ/O0QdENqWPe7eOSXeHUmvPi8DEbMrRWwIhkhloJgqvKwzAaS',NULL,'viet150'),(4,NULL,'2001-07-15 00:00:00.000000','truongquocviet2003@gmail.com','Viet',0,NULL,'Truong','0905054200','$2a$10$j9j4S.x5hZUXK5Kbanuc3ug7lCvGgUO6nBTb2RpCngELguIhb70am',NULL,'kembosua2001'),(5,NULL,'2001-10-10 00:00:00.000000','ducga@gmail.com','Đức',0,NULL,'Phạm','09999999999','$2a$10$ugwoVkJ.b2gUXNO5CcJIAO9iP.mdgdhzHMSNXxVtS31hjlLd.5p92',NULL,'ducga');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_playlists`
--

DROP TABLE IF EXISTS `users_playlists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_playlists` (
  `user_id` bigint NOT NULL,
  `playlists_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`playlists_id`),
  UNIQUE KEY `UK_7qwb4y772uk69xmyqtw2qddeo` (`playlists_id`),
  CONSTRAINT `FK9cvct7ek32m7a85jkd6f29wl7` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKg1e4ix8urs4vn451lnaan9xpk` FOREIGN KEY (`playlists_id`) REFERENCES `playlists` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_playlists`
--

LOCK TABLES `users_playlists` WRITE;
/*!40000 ALTER TABLE `users_playlists` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_playlists` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-13 20:36:37

-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: shoppingstore
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Giày'),(2,'Áo');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_order`
--

DROP TABLE IF EXISTS `customer_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_create` datetime NOT NULL,
  `customer_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customer_order_idx` (`customer_id`),
  CONSTRAINT `fk_customer_order` FOREIGN KEY (`customer_id`) REFERENCES `user_details` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_order`
--

LOCK TABLES `customer_order` WRITE;
/*!40000 ALTER TABLE `customer_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image_details`
--

DROP TABLE IF EXISTS `image_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image_detail` varchar(100) DEFAULT NULL,
  `product_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_key_image_details_idx` (`product_id`),
  CONSTRAINT `fk_key_image_details` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_details`
--

LOCK TABLES `image_details` WRITE;
/*!40000 ALTER TABLE `image_details` DISABLE KEYS */;
INSERT INTO `image_details` VALUES (13,'http://localhost:8080/image/1/21fec134-dc40-4489-b4b6-88b0c7add7f8/detail-0.jpg','21fec134-dc40-4489-b4b6-88b0c7add7f8'),(14,'http://localhost:8080/image/1/21fec134-dc40-4489-b4b6-88b0c7add7f8/detail-1.jpg','21fec134-dc40-4489-b4b6-88b0c7add7f8'),(15,'http://localhost:8080/image/1/21fec134-dc40-4489-b4b6-88b0c7add7f8/detail-2.jpg','21fec134-dc40-4489-b4b6-88b0c7add7f8');
/*!40000 ALTER TABLE `image_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordered_product`
--

DROP TABLE IF EXISTS `ordered_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordered_product` (
  `customer_order_id` int NOT NULL AUTO_INCREMENT,
  `product_id` varchar(100) NOT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`customer_order_id`,`product_id`),
  KEY `fk_customer_order_product_idx` (`product_id`),
  CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_order_id`) REFERENCES `customer_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordered_product`
--

LOCK TABLES `ordered_product` WRITE;
/*!40000 ALTER TABLE `ordered_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordered_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` varchar(100) NOT NULL,
  `name` varchar(45) NOT NULL,
  `price` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `num_ordered` int NOT NULL DEFAULT '0',
  `num_click` int NOT NULL DEFAULT '0',
  `url_image_main` varchar(100) DEFAULT NULL,
  `date_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category_id_idx` (`category_id`),
  CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('0fd60386-1a19-444d-89c5-fd6e3edaefb5','','0','','2020-02-23 21:03:42',1,0,0,NULL,'2020-02-23 21:03:42'),('21fec134-dc40-4489-b4b6-88b0c7add7f8','Test','123123','qsdasdasdasdasdas','2020-02-23 21:08:26',1,0,0,'http://localhost:8080/image/1/21fec134-dc40-4489-b4b6-88b0c7add7f8/content.jpg','2020-02-23 21:08:26'),('5e934258-26a4-4d2e-b0e5-8dd378304068','123','0212','','2020-02-23 21:16:42',1,0,0,NULL,'2020-02-23 21:16:42'),('b5d2e48f-93b7-4d78-8256-24d895459ead','123','023123','','2020-02-23 21:16:22',1,0,0,NULL,'2020-02-23 21:16:22'),('c050c77f-0432-48dc-8287-495d16b2c391','','0','','2020-02-23 21:08:40',1,0,0,NULL,'2020-02-23 21:08:40'),('ce83530a-af6d-4287-bee5-0fb1051aa598','','0','','2020-02-23 21:02:29',1,0,0,NULL,'2020-02-23 21:02:29');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_details` (
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `avatar_url` varchar(200) DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_user_id_idx` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details`
--

LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` VALUES ('Trần Trung Nhật','trantrungnhat6196@gmail.com','0369401262','Hà Đông - Hà Nội','',88),('Trần Thế Lâm','tranthelam@gmail.com','123','Hà Đông Hà Nội','',90);
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `roles` varchar(45) NOT NULL,
  `status` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (88,'trantrungnhat','nhat123','ADMIN',1),(90,'lam','123','CUSTOMER',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-25  1:08:31

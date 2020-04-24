CREATE DATABASE  IF NOT EXISTS `fog` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `fog`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fog
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `bill_of_materials`
--

DROP TABLE IF EXISTS `bill_of_materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_of_materials` (
  `bom_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `material_id` int(11) NOT NULL,
  PRIMARY KEY (`bom_id`),
  KEY `fk_order_id_idx` (`order_id`),
  KEY `fk_material_id_idx` (`material_id`),
  CONSTRAINT `fk_material_id` FOREIGN KEY (`material_id`) REFERENCES `materials` (`material_id`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_of_materials`
--

LOCK TABLES `bill_of_materials` WRITE;
/*!40000 ALTER TABLE `bill_of_materials` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_of_materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carport`
--

DROP TABLE IF EXISTS `carport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carport` (
  `carport_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`carport_id`),
  KEY `fk_type_id_idx` (`type_id`),
  CONSTRAINT `fk_type_id` FOREIGN KEY (`type_id`) REFERENCES `type` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carport`
--

LOCK TABLES `carport` WRITE;
/*!40000 ALTER TABLE `carport` DISABLE KEYS */;
/*!40000 ALTER TABLE `carport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carport_parts`
--

DROP TABLE IF EXISTS `carport_parts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carport_parts` (
  `part_id` int(11) NOT NULL AUTO_INCREMENT,
  `beskrivelse` varchar(45) NOT NULL,
  `carport_id` int(11) NOT NULL,
  PRIMARY KEY (`part_id`),
  KEY `fk_carport_id_idx` (`carport_id`),
  CONSTRAINT `fk_carport_id` FOREIGN KEY (`carport_id`) REFERENCES `carport` (`carport_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carport_parts`
--

LOCK TABLES `carport_parts` WRITE;
/*!40000 ALTER TABLE `carport_parts` DISABLE KEYS */;
/*!40000 ALTER TABLE `carport_parts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` int(11) NOT NULL,
  `zip_code` varchar(45) NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `link_materials_part`
--

DROP TABLE IF EXISTS `link_materials_part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `link_materials_part` (
  `part_id` int(11) NOT NULL,
  `material_id` int(11) NOT NULL,
  KEY `fk_part_id_idx` (`part_id`),
  KEY `fk_material_id_idx` (`material_id`),
  CONSTRAINT `fk_materials_id` FOREIGN KEY (`material_id`) REFERENCES `materials` (`material_id`),
  CONSTRAINT `fk_part_id` FOREIGN KEY (`part_id`) REFERENCES `carport_parts` (`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link_materials_part`
--

LOCK TABLES `link_materials_part` WRITE;
/*!40000 ALTER TABLE `link_materials_part` DISABLE KEYS */;
/*!40000 ALTER TABLE `link_materials_part` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materials`
--

DROP TABLE IF EXISTS `materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materials` (
  `material_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `unit_id` int(11) NOT NULL DEFAULT '0',
  `price` double NOT NULL DEFAULT '0',
  `unit_size` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`material_id`),
  KEY `fk_unit_id_idx` (`unit_id`),
  CONSTRAINT `fk_unit_id` FOREIGN KEY (`unit_id`) REFERENCES `unit` (`unit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials`
--

LOCK TABLES `materials` WRITE;
/*!40000 ALTER TABLE `materials` DISABLE KEYS */;
INSERT INTO `materials` VALUES (1,'19x100 mm. trykimp. Bræt',3,21,210),(2,'19x100 mm. trykimp. Bræt',3,21,240),(3,'19x100 mm. trykimp. Bræt',3,21,270),(4,'19x100 mm. trykimp. Bræt',3,21,300),(5,'19x100 mm. trykimp. Bræt',3,21,360),(6,'19x100 mm. trykimp. Bræt',3,21,420),(7,'19x100 mm. trykimp. Bræt',3,21,480),(8,'25x50 mm. trykimp. Bræt',3,18,210),(9,'25x50 mm. trykimp. Bræt',3,18,240),(10,'25x50 mm. trykimp. Bræt',3,18,270),(11,'25x50 mm. trykimp. Bræt',3,18,300),(12,'25x50 mm. trykimp. Bræt',3,18,330),(13,'25x50 mm. trykimp. Bræt',3,18,360),(14,'25x50 mm. trykimp. Bræt',3,18,390),(15,'25x50 mm. trykimp. Bræt',3,18,420),(16,'25x50 mm. trykimp. Bræt',3,18,450),(17,'25x50 mm. trykimp. Bræt',3,18,480),(18,'25x50 mm. trykimp. Bræt',3,18,510),(19,'25x50 mm. trykimp. Bræt',3,18,540),(20,'25x125mm. trykimp. Bræt',3,32,300),(21,'25x125mm. trykimp. Bræt',3,32,360),(22,'25x125mm. trykimp. Bræt',3,32,420),(23,'25x125mm. trykimp. Bræt',3,32,480),(24,'25x125mm. trykimp. Bræt',3,32,540),(25,'25x125mm. trykimp. Bræt',3,32,600),(26,'25x150mm. trykimp. Bræt',3,32,300),(27,'25x150mm. trykimp. Bræt',3,32,360),(28,'25x150mm. trykimp. Bræt',3,32,420),(29,'25x150mm. trykimp. Bræt',3,32,480),(30,'25x150mm. trykimp. Bræt',3,32,540),(31,'25x150mm. trykimp. Bræt',3,32,600),(32,'25x200mm. trykimp. Bræt',3,55,300),(33,'25x200mm. trykimp. Bræt',3,55,360),(34,'25x200mm. trykimp. Bræt',3,55,420),(35,'25x200mm. trykimp. Bræt',3,55,480),(36,'25x200mm. trykimp. Bræt',3,55,540),(37,'25x200mm. trykimp. Bræt',3,55,600),(38,'45x195 mm. spærtræ ubh.',3,39,300),(39,'45x195 mm. spærtræ ubh.',3,39,360),(40,'45x195 mm. spærtræ ubh.',3,39,420),(41,'45x195 mm. spærtræ ubh.',3,39,480),(42,'45x195 mm. spærtræ ubh.',3,39,540),(43,'45x195 mm. spærtræ ubh.',3,39,600),(44,'45x195 mm. spærtræ ubh.',3,39,660),(45,'45x195 mm. spærtræ ubh.',3,39,720),(46,'97x97 mm. trykimp. Stolpe',3,35,180),(47,'97x97 mm. trykimp. Stolpe',3,35,210),(48,'97x97 mm. trykimp. Stolpe',3,35,240),(49,'97x97 mm. trykimp. Stolpe',3,35,270),(50,'97x97 mm. trykimp. Stolpe',3,35,300),(51,'97x97 mm. trykimp. Stolpe',3,35,360),(52,'97x97 mm. trykimp. Stolpe',3,35,420),(53,'97x97 mm. trykimp. Stolpe',3,35,480),(54,'45x95 mm. Reglar ub.',3,16,240),(55,'45x95 mm. Reglar ub.',3,16,270),(56,'45x95 mm. Reglar ub.',3,16,300),(57,'45x95 mm. Reglar ub.',3,16,330),(58,'45x95 mm. Reglar ub.',3,16,360),(59,'45x95 mm. Reglar ub.',3,16,390),(60,'45x95 mm. Reglar ub.',3,16,420),(61,'45x95 mm. Reglar ub.',3,16,450),(62,'45x95 mm. Reglar ub.',3,16,480),(63,'45x95 mm. Reglar ub.',3,16,510),(64,'45x95 mm. Reglar ub.',3,16,540),(65,'38x73 mm. Lægte ubh.',3,23,360),(66,'38x73 mm. Lægte ubh.',3,23,420),(67,'38x73 mm. Lægte ubh.',3,23,480),(68,'38x73 mm. Lægte ubh.',3,23,540),(69,'Plastmo Ecolite blåtonet',3,40,240),(70,'Plastmo Ecolite blåtonet',3,40,300),(71,'Plastmo Ecolite blåtonet',3,40,360),(72,'Plastmo Ecolite blåtonet',3,40,420),(73,'Plastmo Ecolite blåtonet',3,40,480),(74,'Plastmo Ecolite blåtonet',3,40,600),(75,'Plasttrapezplader',3,0,0),(76,'Betontagsten - Rød',3,15,0),(77,'Betontagsten - Teglrød',3,15,0),(78,'Betontagsten - Brun',3,15,0),(79,'Betontagsten - Sort',3,15,0),(80,'Eternittag B6 - Grå',3,15,0),(81,'Eternittag B6 - Sort',3,15,0),(82,'Eternittag B6 - Mokka(brun)',3,15,0),(83,'Eternittag B6 - Rødbrun',3,15,0),(84,'Eternittag B6 - Teglrød',3,15,0),(85,'Eternittag B7 - Grå',3,15,0),(86,'Eternittag B7 - Sort',3,15,0),(87,'Eternittag B7 - Mokka(brun)',3,15,0),(88,'Eternittag B7 - Rødbrun',3,15,0),(89,'Eternittag B7 - Teglrød',3,15,0),(90,'Eternittag B7 - Rødflammet',3,15,0),(91,'Plastmo Bundskruer',1,220,200),(92,'Hulbånd 1x20 mm.',2,209,1),(93,'Universal 190mm højre',3,18,1),(94,'Universal 190mm venstre',3,18,1),(95,'4,5x60 mm. skruer',1,159,200),(96,'4x50 mm. beslagskruer',1,269,250),(97,'Bræddebolte 10x120 mm.',3,19,1),(98,'Firkantskiver 40x40x11 mm.',3,10,1),(99,'4,5x70 mm. skruer',1,199,400),(100,'4,5x50 mm. skruer',1,69,300),(101,'Stalddørsgreb 50x75',4,189,1),(102,'T hængsel 390 mm.',3,120,1),(103,'Vinkelbeslag 35',3,6,1),(104,'B & C Toplægteholder',3,50,1),(105,'B & C rygstensbeslag',3,50,1),(106,'B & C tagstens bindere og nakkekroge',1,50,100),(107,'5x40 mm. beslagskruer',1,269,250),(108,'5x100 mm. skruer',1,100,100);
/*!40000 ALTER TABLE `materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `cp_id` int(11) NOT NULL,
  `date` timestamp NOT NULL,
  `carport_width` int(11) NOT NULL,
  `carport_length` int(11) NOT NULL,
  `shed_width` int(11) NOT NULL,
  `shed_length` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_customer_id_idx` (`customer_id`),
  KEY `fk_carport_id_idx` (`cp_id`),
  CONSTRAINT `fk_cp_id` FOREIGN KEY (`cp_id`) REFERENCES `carport` (`carport_id`),
  CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'Fladt tag'),(2,'Tag med rejsning');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit` (
  `unit_id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_type` varchar(45) NOT NULL,
  PRIMARY KEY (`unit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1,'Pakke'),(2,'Rulle'),(3,'Stk'),(4,'Sæt');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-24 11:42:26

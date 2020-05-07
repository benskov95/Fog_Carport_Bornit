CREATE DATABASE  IF NOT EXISTS `fog` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fog`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: fog
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carport`
--

LOCK TABLES `carport` WRITE;
/*!40000 ALTER TABLE `carport` DISABLE KEYS */;
INSERT INTO `carport` VALUES (1,1),(2,2),(3,3),(4,4);
/*!40000 ALTER TABLE `carport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carport_parts`
--

DROP TABLE IF EXISTS `carport_parts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carport_parts` (
  `pk_carport_part_id` int(11) NOT NULL AUTO_INCREMENT,
  `material_id` int(11) NOT NULL,
  `beskrivelse` varchar(45) NOT NULL,
  `carport_id` int(11) NOT NULL,
  PRIMARY KEY (`pk_carport_part_id`),
  KEY `fk_carport_id_idx` (`carport_id`),
  KEY `fk_material_part_id_idx` (`material_id`),
  CONSTRAINT `fk_carport_id` FOREIGN KEY (`carport_id`) REFERENCES `carport` (`carport_id`),
  CONSTRAINT `fk_material_part_id` FOREIGN KEY (`material_id`) REFERENCES `materials` (`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carport_parts`
--

LOCK TABLES `carport_parts` WRITE;
/*!40000 ALTER TABLE `carport_parts` DISABLE KEYS */;
INSERT INTO `carport_parts` VALUES (27,32,'understernbrædder til for & bag ende',2),(28,32,'understernbrædder til siderne',2),(29,20,'oversternbrædder til forenden',2),(30,20,'oversternbrædder til siderne',2),(31,65,'til z på bagside af dør',2),(32,54,'løsholter til skur gavle',2),(33,54,'løsholter til skur sider',2),(34,38,'Remme i sider, sadles ned i stolper',2),(35,38,'Spær, monteres på rem',2),(36,46,'Stolper nedgraves 90 cm. i jord',2),(37,1,'til beklædning af skur 1 på 2',2),(38,1,'vandbrædt på stern i sider',2),(39,1,'vandbrædt på stern i forende',2),(40,69,'tagplader monteres på spær',2),(41,91,'Skruer til tagplader',2),(42,92,'Til vindkryds på spær',2),(43,93,'Til montering af spær på rem',2),(44,94,'Til montering af spær på rem',2),(45,95,'Til montering af stern&vandbrædt',2),(46,96,'Til montering af universalbeslag + hulbånd',2),(47,97,'Til montering af rem på stolper',2),(48,98,'Til montering af rem på stolper',2),(49,99,'til montering af yderste beklædning',2),(50,100,'til montering af inderste beklædning',2),(51,101,'Til lås på dør i skur',2),(52,102,'Til skurdør',2),(53,103,'Til montering af løsholter i skur',2);
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
-- Table structure for table `link_material_size`
--

DROP TABLE IF EXISTS `link_material_size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `link_material_size` (
  `pk_link_material_size` int(11) NOT NULL AUTO_INCREMENT,
  `link_material_id` int(11) NOT NULL,
  `link_size_id` int(11) NOT NULL,
  PRIMARY KEY (`pk_link_material_size`),
  KEY `fk_material_id_idx` (`link_material_id`),
  KEY `fk_link_size_id_idx` (`link_size_id`),
  CONSTRAINT `fk_link_material_id` FOREIGN KEY (`link_material_id`) REFERENCES `materials` (`material_id`),
  CONSTRAINT `fk_link_size_id` FOREIGN KEY (`link_size_id`) REFERENCES `size` (`size_id`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link_material_size`
--

LOCK TABLES `link_material_size` WRITE;
/*!40000 ALTER TABLE `link_material_size` DISABLE KEYS */;
INSERT INTO `link_material_size` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13),(14,1,14),(15,1,15),(16,1,16),(17,1,17),(18,1,18),(19,1,19),(20,1,20),(21,8,1),(22,8,2),(23,8,3),(24,8,4),(25,8,5),(26,8,6),(27,8,7),(28,8,8),(29,8,9),(30,8,10),(31,8,11),(32,8,12),(33,8,13),(34,8,14),(35,8,15),(36,8,16),(37,8,17),(38,8,18),(39,8,19),(40,8,20),(41,20,1),(42,20,2),(43,20,3),(44,20,4),(45,20,5),(46,20,6),(47,20,7),(48,20,8),(49,20,9),(50,20,10),(51,20,11),(52,20,12),(53,20,13),(54,20,14),(55,20,15),(56,20,16),(57,20,17),(58,20,18),(59,20,19),(60,20,20),(61,26,1),(62,26,2),(63,26,3),(64,26,4),(65,26,5),(66,26,6),(67,26,7),(68,26,8),(69,26,9),(70,26,10),(71,26,11),(72,26,12),(73,26,13),(74,26,14),(75,26,15),(76,26,16),(77,26,17),(78,26,18),(79,26,19),(80,26,20),(81,32,1),(82,32,2),(83,32,3),(84,32,4),(85,32,5),(86,32,6),(87,32,7),(88,32,8),(89,32,9),(90,32,10),(91,32,11),(92,32,12),(93,32,13),(94,32,14),(95,32,15),(96,32,16),(97,32,17),(98,32,18),(99,32,19),(100,32,20),(101,38,1),(102,38,2),(103,38,3),(104,38,4),(105,38,5),(106,38,6),(107,38,7),(108,38,8),(109,38,9),(110,38,10),(111,38,11),(112,38,12),(113,38,13),(114,38,14),(115,38,15),(116,38,16),(117,38,17),(118,38,18),(119,38,19),(120,38,20),(121,46,1),(122,46,2),(123,46,3),(124,46,4),(125,46,5),(126,46,6),(127,46,7),(128,46,8),(129,46,9),(130,46,10),(131,46,11),(132,46,12),(133,46,13),(134,46,14),(135,46,15),(136,46,16),(137,46,17),(138,46,18),(139,46,19),(140,46,20),(141,54,1),(142,54,2),(143,54,3),(144,54,4),(145,54,5),(146,54,6),(147,54,7),(148,54,8),(149,54,9),(150,54,10),(151,54,11),(152,54,12),(153,54,13),(154,54,14),(155,54,15),(156,54,16),(157,54,17),(158,54,18),(159,54,19),(160,54,20),(161,65,1),(162,65,2),(163,65,3),(164,65,4),(165,65,5),(166,65,6),(167,65,7),(168,65,8),(169,65,9),(170,65,10),(171,65,11),(172,65,12),(173,65,13),(174,65,14),(175,65,15),(176,65,16),(177,65,17),(178,65,18),(179,65,19),(180,65,20),(181,69,2),(182,69,4),(183,69,6),(184,69,8),(185,69,10),(186,69,14),(187,91,25),(188,92,23),(189,93,23),(190,94,23),(191,95,25),(192,96,24),(193,97,23),(194,98,23),(195,99,26),(196,100,4),(197,101,23),(198,102,23),(199,103,23),(200,104,23),(201,105,23),(202,106,22),(203,107,24),(204,108,22);
/*!40000 ALTER TABLE `link_material_size` ENABLE KEYS */;
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
  CONSTRAINT `fk_materials_id` FOREIGN KEY (`material_id`) REFERENCES `materials` (`material_id`)
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
INSERT INTO `materials` VALUES (1,'19x100 mm. trykimp. Bræt',3,21),(8,'25x50 mm. trykimp. Bræt',3,18),(20,'25x125mm. trykimp. Bræt',3,32),(26,'25x150mm. trykimp. Bræt',3,32),(32,'25x200mm. trykimp. Bræt',3,55),(38,'45x195 mm. spærtræ ubh.',3,39),(46,'97x97 mm. trykimp. Stolpe',3,35),(54,'45x95 mm. Reglar ub.',3,16),(65,'38x73 mm. Lægte ubh.',3,23),(69,'Plastmo Ecolite blåtonet',3,40),(75,'Plasttrapezplader',3,0),(76,'Betontagsten - Rød',3,15),(77,'Betontagsten - Teglrød',3,15),(78,'Betontagsten - Brun',3,15),(79,'Betontagsten - Sort',3,15),(80,'Eternittag B6 - Grå',3,15),(81,'Eternittag B6 - Sort',3,15),(82,'Eternittag B6 - Mokka(brun)',3,15),(83,'Eternittag B6 - Rødbrun',3,15),(84,'Eternittag B6 - Teglrød',3,15),(85,'Eternittag B7 - Grå',3,15),(86,'Eternittag B7 - Sort',3,15),(87,'Eternittag B7 - Mokka(brun)',3,15),(88,'Eternittag B7 - Rødbrun',3,15),(89,'Eternittag B7 - Teglrød',3,15),(90,'Eternittag B7 - Rødflammet',3,15),(91,'Plastmo Bundskruer',1,220),(92,'Hulbånd 1x20 mm.',2,209),(93,'Universal 190mm højre',3,18),(94,'Universal 190mm venstre',3,18),(95,'4,5x60 mm. skruer',1,159),(96,'4x50 mm. beslagskruer',1,269),(97,'Bræddebolte 10x120 mm.',3,19),(98,'Firkantskiver 40x40x11 mm.',3,10),(99,'4,5x70 mm. skruer',1,199),(100,'4,5x50 mm. skruer',1,69),(101,'Stalddørsgreb 50x75',4,189),(102,'T hængsel 390 mm.',3,120),(103,'Vinkelbeslag 35',3,6),(104,'B & C Toplægteholder',3,50),(105,'B & C rygstensbeslag',3,50),(106,'B & C tagstens bindere og nakkekroge',1,50),(107,'5x40 mm. beslagskruer',1,269),(108,'5x100 mm. skruer',1,100);
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
-- Table structure for table `size`
--

DROP TABLE IF EXISTS `size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `size` (
  `size_id` int(11) NOT NULL AUTO_INCREMENT,
  `size` int(11) NOT NULL,
  PRIMARY KEY (`size_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
INSERT INTO `size` VALUES (1,210),(2,240),(3,270),(4,300),(5,330),(6,360),(7,390),(8,420),(9,450),(10,480),(11,510),(12,540),(13,570),(14,600),(15,630),(16,660),(17,690),(18,720),(19,750),(20,780),(21,0),(22,100),(23,1),(24,250),(25,200),(26,400);
/*!40000 ALTER TABLE `size` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'Fladt tag uden skur'),(2,'Fladt tag med skur'),(3,'Tag med rejsning uden skur'),(4,'Tag med rejsning med skur');
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

-- Dump completed on 2020-04-24 13:48:34

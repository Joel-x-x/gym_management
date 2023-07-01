-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bdd_gym
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
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sesion_iniciada` tinyint NOT NULL,
  `super_admin` tinyint NOT NULL,
  `clave` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (1,'wacho','wachin','wacho@gmai.com','1234',0,1,NULL),(28,'juan',NULL,'juan','123',0,0,NULL);
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clase`
--

DROP TABLE IF EXISTS `clase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clase` (
  `id` int NOT NULL AUTO_INCREMENT,
  `clase` varchar(100) NOT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  `entrenador_id` int NOT NULL,
  `administrador_id` int NOT NULL,
  PRIMARY KEY (`id`,`entrenador_id`),
  KEY `fk_clase_entrenador1_idx` (`entrenador_id`),
  KEY `fk_administrador_id1_idx` (`administrador_id`),
  CONSTRAINT `fk_administrador_id1` FOREIGN KEY (`administrador_id`) REFERENCES `administrador` (`id`),
  CONSTRAINT `fk_clase_entrenador1` FOREIGN KEY (`entrenador_id`) REFERENCES `entrenador` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clase`
--

LOCK TABLES `clase` WRITE;
/*!40000 ALTER TABLE `clase` DISABLE KEYS */;
INSERT INTO `clase` VALUES (3,'Calistenia','Alfa y',3,1),(4,'Entrenamiento','Aguas',3,1);
/*!40000 ALTER TABLE `clase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_empresa` varchar(50) NOT NULL,
  `logo_empresa` longblob,
  `imagen_perfil` longblob,
  `administrador_id` int NOT NULL,
  PRIMARY KEY (`id`,`administrador_id`),
  KEY `fk_cuenta_administrador1_idx` (`administrador_id`),
  CONSTRAINT `fk_cuenta_administrador1` FOREIGN KEY (`administrador_id`) REFERENCES `administrador` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--

LOCK TABLES `cuenta` WRITE;
/*!40000 ALTER TABLE `cuenta` DISABLE KEYS */;
INSERT INTO `cuenta` VALUES (1,'Sin nombre',NULL,NULL,1);
/*!40000 ALTER TABLE `cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrenador`
--

DROP TABLE IF EXISTS `entrenador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrenador` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `sexo` enum('Masculino','Femenino') NOT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `telefono` varchar(15) NOT NULL,
  `cedula` varchar(20) NOT NULL,
  `administrador_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `correo_UNIQUE` (`correo`),
  KEY `fk_administrador_id2_idx` (`administrador_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrenador`
--

LOCK TABLES `entrenador` WRITE;
/*!40000 ALTER TABLE `entrenador` DISABLE KEYS */;
INSERT INTO `entrenador` VALUES (3,'Juanita','Mecanico','Femenino','1234','1234','1234',1);
/*!40000 ALTER TABLE `entrenador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fisico`
--

DROP TABLE IF EXISTS `fisico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fisico` (
  `id` int NOT NULL AUTO_INCREMENT,
  `altura` double NOT NULL,
  `peso` double NOT NULL,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `usuario_id` int NOT NULL,
  PRIMARY KEY (`id`,`usuario_id`),
  KEY `fk_fisico_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_fisico_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fisico`
--

LOCK TABLES `fisico` WRITE;
/*!40000 ALTER TABLE `fisico` DISABLE KEYS */;
/*!40000 ALTER TABLE `fisico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membresia`
--

DROP TABLE IF EXISTS `membresia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `membresia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha_inicio` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_fin` datetime NOT NULL,
  `usuario_id` int NOT NULL,
  `plan_id` int NOT NULL,
  `clase_id` int NOT NULL,
  `valor_extra` decimal(6,2) NOT NULL,
  `valor_total` decimal(6,2) NOT NULL,
  `activo` tinyint NOT NULL,
  `anticipacion` int NOT NULL,
  `administrador_id` int NOT NULL,
  PRIMARY KEY (`id`,`usuario_id`),
  KEY `fk_membresia_usuario1_idx` (`usuario_id`),
  KEY `fk_plan_id_idx` (`plan_id`),
  KEY `fk_clase_id_idx` (`clase_id`),
  KEY `fk_administrador_id_membresia_idx` (`administrador_id`),
  CONSTRAINT `fk_administrador_id_membresia` FOREIGN KEY (`administrador_id`) REFERENCES `administrador` (`id`),
  CONSTRAINT `fk_clase_id` FOREIGN KEY (`clase_id`) REFERENCES `clase` (`id`),
  CONSTRAINT `fk_membresia_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_plan_id` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membresia`
--

LOCK TABLES `membresia` WRITE;
/*!40000 ALTER TABLE `membresia` DISABLE KEYS */;
INSERT INTO `membresia` VALUES (24,'2023-07-01 00:44:32','2023-06-25 23:34:54',11,9,3,1.00,3.00,0,0,1),(26,'2023-07-01 03:51:31','2023-08-01 03:51:31',11,7,3,0.00,25.00,1,0,1);
/*!40000 ALTER TABLE `membresia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `descripcion` varchar(300) NOT NULL,
  `duracion` enum('diario','mensual','anual') DEFAULT NULL,
  `administrador_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_administrador_id_plan` (`administrador_id`),
  CONSTRAINT `fk_administrador_id_plan` FOREIGN KEY (`administrador_id`) REFERENCES `administrador` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (7,'Estandar',25.00,'Plan mensual','mensual',1),(8,'Anual',150.00,'Premium','anual',1),(9,'Dia',2.00,'Diario','diario',1);
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro`
--

DROP TABLE IF EXISTS `registro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha_entrada` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_salida` datetime DEFAULT NULL,
  `usuario_id` int NOT NULL,
  `membresia_id` int NOT NULL,
  PRIMARY KEY (`id`,`usuario_id`),
  KEY `fk_registro_usuario1_idx` (`usuario_id`),
  KEY `fk_membresia_id_idx` (`membresia_id`),
  CONSTRAINT `fk_membresia_id` FOREIGN KEY (`membresia_id`) REFERENCES `membresia` (`id`),
  CONSTRAINT `fk_registro_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro`
--

LOCK TABLES `registro` WRITE;
/*!40000 ALTER TABLE `registro` DISABLE KEYS */;
INSERT INTO `registro` VALUES (81,'2023-07-01 02:18:37','2023-07-01 02:18:42',11,24),(82,'2023-07-01 02:19:24','2023-07-01 02:19:26',11,24),(83,'2023-07-01 02:23:44','2023-07-01 02:26:45',11,24),(84,'2023-07-01 02:56:55','2023-07-01 02:56:56',11,24),(85,'2023-07-01 02:56:58','2023-07-01 02:56:59',11,24),(86,'2023-07-01 02:57:00','2023-07-01 02:57:01',11,24),(87,'2023-07-01 02:57:16','2023-07-01 02:57:16',11,24),(88,'2023-07-01 02:57:17','2023-07-01 02:57:17',11,24),(89,'2023-07-01 02:57:17','2023-07-01 02:57:18',11,24),(90,'2023-07-01 02:57:18','2023-07-01 02:57:18',11,24),(91,'2023-07-01 02:57:19','2023-07-01 02:57:20',11,24),(92,'2023-07-01 02:57:20','2023-07-01 02:57:20',11,24),(93,'2023-07-01 02:57:21','2023-07-01 02:57:21',11,24),(94,'2023-07-01 02:57:21','2023-07-01 02:57:21',11,24),(95,'2023-07-01 02:57:22','2023-07-01 02:57:22',11,24),(96,'2023-07-01 02:57:22','2023-07-01 02:57:23',11,24),(97,'2023-07-01 02:57:23','2023-07-01 02:57:23',11,24),(98,'2023-07-01 02:57:24','2023-07-01 02:57:24',11,24),(99,'2023-07-01 02:57:24','2023-07-01 02:57:24',11,24),(100,'2023-07-01 02:57:25','2023-07-01 02:57:25',11,24),(101,'2023-07-01 02:57:33','2023-07-01 02:57:33',11,24),(102,'2023-07-01 02:59:40','2023-07-01 02:59:41',11,24),(103,'2023-07-01 02:59:59','2023-07-01 03:00:00',11,24),(104,'2023-07-01 03:00:01','2023-07-01 03:00:01',11,24),(105,'2023-07-01 03:00:02','2023-07-01 03:00:03',11,24),(106,'2023-07-01 03:00:44','2023-07-01 03:01:19',11,24),(107,'2023-07-01 03:00:46','2023-07-01 03:01:18',11,24),(108,'2023-07-01 03:00:57','2023-07-01 03:01:17',11,24),(109,'2023-07-01 03:01:25','2023-07-01 03:01:27',11,24),(110,'2023-07-01 03:01:29','2023-07-01 03:01:31',11,24),(111,'2023-07-01 03:01:45','2023-07-01 03:01:53',11,24),(112,'2023-07-01 03:01:58','2023-07-01 03:02:13',11,24),(113,'2023-07-01 03:03:06','2023-07-01 03:03:15',11,24),(114,'2023-07-01 03:03:30','2023-07-01 03:03:36',11,24);
/*!40000 ALTER TABLE `registro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `fecha_nacimiento` date DEFAULT '1800-01-01',
  `sexo` enum('Masculino','Femenino') NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cedula` varchar(15) DEFAULT NULL,
  `direccion` varchar(300) DEFAULT NULL,
  `telefono` varchar(15) NOT NULL,
  `fecha_creacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `administrador_id` int NOT NULL,
  PRIMARY KEY (`id`,`administrador_id`),
  UNIQUE KEY `correo_UNIQUE` (`email`),
  KEY `fk_administrador_id_idx` (`administrador_id`),
  CONSTRAINT `fk_administrador_id` FOREIGN KEY (`administrador_id`) REFERENCES `administrador` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (11,'marco','polo','1800-01-01','Masculino','1234','1234','aguas','02222','2023-07-01 00:42:25',1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-01  3:54:38

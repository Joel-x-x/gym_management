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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (1,'wacho','wachin','wacho@gmai.com','1234',0,1,'1234'),(24,'huesos',NULL,'hqwe','123',0,0,NULL),(25,'huesos',NULL,'name','123',0,0,NULL),(26,'Paquita',NULL,'hola@','123',0,0,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clase`
--

LOCK TABLES `clase` WRITE;
/*!40000 ALTER TABLE `clase` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrenador`
--

LOCK TABLES `entrenador` WRITE;
/*!40000 ALTER TABLE `entrenador` DISABLE KEYS */;
INSERT INTO `entrenador` VALUES (1,'Alex2','Torval','Masculino','alex@gmail.com','0983748394','',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fisico`
--

LOCK TABLES `fisico` WRITE;
/*!40000 ALTER TABLE `fisico` DISABLE KEYS */;
INSERT INTO `fisico` VALUES (7,1.1,75,'2023-06-26 21:47:11',2),(10,1.68,65,'2023-06-26 21:49:08',7),(12,1.81,70,'2023-06-27 16:32:40',8),(13,1.81,74,'2023-06-27 16:32:50',8);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membresia`
--

LOCK TABLES `membresia` WRITE;
/*!40000 ALTER TABLE `membresia` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
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
  PRIMARY KEY (`id`,`usuario_id`),
  KEY `fk_registro_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_registro_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro`
--

LOCK TABLES `registro` WRITE;
/*!40000 ALTER TABLE `registro` DISABLE KEYS */;
INSERT INTO `registro` VALUES (8,'2023-06-27 00:06:57','2023-06-27 00:06:58',2),(32,'2023-06-27 02:53:27','2023-06-27 02:53:28',8),(33,'2023-06-27 16:32:25','2023-06-27 16:32:28',8);
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
  `fecha_nacimiento` date DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (2,'Luchito','Miran','2003-12-28','Masculino','sapo@gmail.com','1854534342','Donde las papas queman','093438492834','2023-06-21 17:47:33',1),(7,'Sofia','Vergara','2000-03-23','Femenino','sofia@gmail.com','185039344','La Casa','1234123','2023-06-26 21:48:47',1),(8,'Joel','Acosta','2003-04-23','Masculino','tupa@gmail.com','123444','','1141234','2023-06-27 02:53:11',1);
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

-- Dump completed on 2023-06-27 16:37:52

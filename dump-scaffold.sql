-- MySQL dump 10.13  Distrib 8.0.28, for Linux (x86_64)
--
-- Host: 172.17.0.3    Database: scaffold
-- ------------------------------------------------------
-- Server version	5.7.38

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `login_autorizacao`
--

DROP TABLE IF EXISTS `login_autorizacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_autorizacao` (
  `id_autorizacao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `classificacao` varchar(100) DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `ativo` tinyint(1) DEFAULT '1',
  `versao` int(11) DEFAULT '0',
  PRIMARY KEY (`id_autorizacao`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_autorizacao`
--

LOCK TABLES `login_autorizacao` WRITE;
/*!40000 ALTER TABLE `login_autorizacao` DISABLE KEYS */;
INSERT INTO `login_autorizacao` VALUES (1,'02.001','ROLE_CADASTRO_AUTORIZACOES',1,0),(2,'02.002','ROLE_CADASTRO_PERFIS',1,0),(3,'02.003','ROLE_CADASTRO_USUARIOS',1,0),(4,'02.004','ROLE_LOGIN_LISTAR_USUARIO',1,0),(5,'01.001','ROLE_DASHBOARD',1,0);
/*!40000 ALTER TABLE `login_autorizacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_perfil`
--

DROP TABLE IF EXISTS `login_perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_perfil` (
  `id_perfil` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descricao` varchar(20) DEFAULT NULL,
  `notificar_inclusao_usuario` tinyint(4) DEFAULT '0',
  `ativo` tinyint(1) DEFAULT '1',
  `versao` int(11) DEFAULT '0',
  PRIMARY KEY (`id_perfil`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_perfil`
--

LOCK TABLES `login_perfil` WRITE;
/*!40000 ALTER TABLE `login_perfil` DISABLE KEYS */;
INSERT INTO `login_perfil` VALUES (1,'ADMIN',0,1,0),(2,'OPERADOR',0,1,0);
/*!40000 ALTER TABLE `login_perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_perfil_autorizacao`
--

DROP TABLE IF EXISTS `login_perfil_autorizacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_perfil_autorizacao` (
  `id_perfil` int(10) unsigned NOT NULL,
  `id_autorizacao` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_perfil`,`id_autorizacao`),
  KEY `fk_perfil_autorizacao_autorizacao` (`id_autorizacao`),
  CONSTRAINT `fk_perfil_autorizacao_autorizacao` FOREIGN KEY (`id_autorizacao`) REFERENCES `login_autorizacao` (`id_autorizacao`),
  CONSTRAINT `fk_perfil_autorizacao_perfil` FOREIGN KEY (`id_perfil`) REFERENCES `login_perfil` (`id_perfil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_perfil_autorizacao`
--

LOCK TABLES `login_perfil_autorizacao` WRITE;
/*!40000 ALTER TABLE `login_perfil_autorizacao` DISABLE KEYS */;
INSERT INTO `login_perfil_autorizacao` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(2,5);
/*!40000 ALTER TABLE `login_perfil_autorizacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_usuario`
--

DROP TABLE IF EXISTS `login_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_usuario` (
  `id_usuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `login` varchar(30) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(200) NOT NULL,
  `ativo` tinyint(1) DEFAULT '1',
  `versao` int(11) DEFAULT '0',
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_usuario`
--

LOCK TABLES `login_usuario` WRITE;
/*!40000 ALTER TABLE `login_usuario` DISABLE KEYS */;
INSERT INTO `login_usuario` VALUES (1,'Fulano dos Anzois Perreira','admin','fulano@scaffold.com','$2a$12$px3i.qKAVbYSP5MWfyW21OapdHlxjGKglMMKowQXvFc8p.Z9X8PZO',1,0),(2,'Beltrano dos Anzois Pereira (senha 12345)','beltrano','beltrano@scaffold.com','$2a$12$FU0GYgzimbAKhnlCsvRJLet9B5ODcNSM14y2MpJymRy3gAPuFVKzS',1,5);
/*!40000 ALTER TABLE `login_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_usuario_perfil`
--

DROP TABLE IF EXISTS `login_usuario_perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_usuario_perfil` (
  `id_usuario` int(10) unsigned NOT NULL,
  `id_perfil` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_perfil`),
  KEY `fk_usuario_perfil_perfil` (`id_perfil`),
  CONSTRAINT `fk_usuario_perfil_perfil` FOREIGN KEY (`id_perfil`) REFERENCES `login_perfil` (`id_perfil`),
  CONSTRAINT `fk_usuario_perfil_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `login_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_usuario_perfil`
--

LOCK TABLES `login_usuario_perfil` WRITE;
/*!40000 ALTER TABLE `login_usuario_perfil` DISABLE KEYS */;
INSERT INTO `login_usuario_perfil` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `login_usuario_perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'scaffold'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-30 23:14:43

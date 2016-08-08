-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: iface
-- ------------------------------------------------------
-- Server version	5.7.14-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `amizade`
--

DROP TABLE IF EXISTS `amizade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `amizade` (
  `confirmado` bit(1) NOT NULL,
  `solicitante_username` varchar(255) NOT NULL,
  `solicitado_username` varchar(255) NOT NULL,
  PRIMARY KEY (`solicitante_username`,`solicitado_username`),
  KEY `FK_fg7wcq0ujp0vm7xn892q1xl7` (`solicitado_username`),
  CONSTRAINT `FK_25o31v4pvmskud2teepmjf09v` FOREIGN KEY (`solicitante_username`) REFERENCES `usuario` (`username`),
  CONSTRAINT `FK_fg7wcq0ujp0vm7xn892q1xl7` FOREIGN KEY (`solicitado_username`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `atributo`
--

DROP TABLE IF EXISTS `atributo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `atributo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conteudo` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `usuario_username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8x53ly3k3hcqjm0qtngh9s8b` (`usuario_username`),
  CONSTRAINT `FK_8x53ly3k3hcqjm0qtngh9s8b` FOREIGN KEY (`usuario_username`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comunidade`
--

DROP TABLE IF EXISTS `comunidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comunidade` (
  `nome` varchar(255) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `dono_username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nome`),
  KEY `FK_m82l7lw5tsknny501ibo4fv4i` (`dono_username`),
  CONSTRAINT `FK_m82l7lw5tsknny501ibo4fv4i` FOREIGN KEY (`dono_username`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comunidade_usuario`
--

DROP TABLE IF EXISTS `comunidade_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comunidade_usuario` (
  `confirmado` bit(1) NOT NULL,
  `participante_id` varchar(255) NOT NULL,
  `comunidade_id` varchar(255) NOT NULL,
  PRIMARY KEY (`participante_id`,`comunidade_id`),
  KEY `FK_egpt69lcs563ydkds8ullmc6e` (`comunidade_id`),
  CONSTRAINT `FK_4ek1t90rfe1shsdvqk8qwmrf6` FOREIGN KEY (`participante_id`) REFERENCES `usuario` (`username`),
  CONSTRAINT `FK_egpt69lcs563ydkds8ullmc6e` FOREIGN KEY (`comunidade_id`) REFERENCES `comunidade` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mensagem_comunidade`
--

DROP TABLE IF EXISTS `mensagem_comunidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mensagem_comunidade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conteudo` varchar(255) DEFAULT NULL,
  `emissor_username` varchar(255) DEFAULT NULL,
  `receptor_nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h3iihitj2d2vw5xkokrsvrukt` (`emissor_username`),
  KEY `FK_3xjagxmuj1xukxkl9bc9ifuw9` (`receptor_nome`),
  CONSTRAINT `FK_3xjagxmuj1xukxkl9bc9ifuw9` FOREIGN KEY (`receptor_nome`) REFERENCES `comunidade` (`nome`),
  CONSTRAINT `FK_h3iihitj2d2vw5xkokrsvrukt` FOREIGN KEY (`emissor_username`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mensagem_usuario`
--

DROP TABLE IF EXISTS `mensagem_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mensagem_usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conteudo` varchar(255) DEFAULT NULL,
  `emissor_username` varchar(255) DEFAULT NULL,
  `receptor_username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6olv85jv6dpduuovdnst3de1m` (`emissor_username`),
  KEY `FK_jmphemmfk8yom8n9gngu6rb9i` (`receptor_username`),
  CONSTRAINT `FK_6olv85jv6dpduuovdnst3de1m` FOREIGN KEY (`emissor_username`) REFERENCES `usuario` (`username`),
  CONSTRAINT `FK_jmphemmfk8yom8n9gngu6rb9i` FOREIGN KEY (`receptor_username`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `username` varchar(255) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-08 20:33:25

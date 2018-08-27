-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: localhost    Database: xq
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.18.04.1

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
-- Current Database: `xq`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `xq` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `xq`;

--
-- Table structure for table `administer`
--

DROP TABLE IF EXISTS `administer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(64) NOT NULL,
  `ip` varchar(16) DEFAULT '127.0.0.1',
  `account` varchar(64) NOT NULL,
  `port` int(6) unsigned DEFAULT '10086',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `account_UNIQUE` (`account`),
  KEY `userID` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8901 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administer`
--

LOCK TABLES `administer` WRITE;
/*!40000 ALTER TABLE `administer` DISABLE KEYS */;
INSERT INTO `administer` VALUES (8889,'111','0:0:0:0:0:0:0:1','111',10086),(8890,'123','0:0:0:0:0:0:0:1','123',44800),(8891,'789','0:0:0:0:0:0:0:1','456',10086),(8892,'555','0:0:0:0:0:0:0:1','444',10086),(8893,'123456789','0:0:0:0:0:0:0:1','862344188',10086),(8894,'2222222','0:0:0:0:0:0:0:1','111111',10086),(8895,'862344','0:0:0:0:0:0:0:1','862344',10086),(8896,'862344','0:0:0:0:0:0:0:1','syfnnnn',10086),(8897,'123','0:0:0:0:0:0:0:1','syffff',10086),(8898,'123','0:0:0:0:0:0:0:1','ssssss',10086),(8899,'ssss','0:0:0:0:0:0:0:1','ffffffffff',10086),(8900,'qqqqq','127.0.0.1','qqqq',4499);
/*!40000 ALTER TABLE `administer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `box`
--

DROP TABLE IF EXISTS `box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `box` (
  `boxID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `desID` int(11) unsigned NOT NULL,
  `x` float NOT NULL,
  `y` float NOT NULL,
  `z` float NOT NULL,
  `w` float NOT NULL,
  PRIMARY KEY (`boxID`),
  UNIQUE KEY `boxID_UNIQUE` (`boxID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `box`
--

LOCK TABLES `box` WRITE;
/*!40000 ALTER TABLE `box` DISABLE KEYS */;
/*!40000 ALTER TABLE `box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car` (
  `carID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `status` int(10) NOT NULL DEFAULT '0',
  `ip` varchar(16) DEFAULT '‘127.0.0.1‘',
  `port` int(6) unsigned DEFAULT '10086',
  `x` float NOT NULL DEFAULT '0',
  `y` float NOT NULL DEFAULT '0',
  `z` float NOT NULL DEFAULT '0',
  `ax` float NOT NULL DEFAULT '0',
  `ay` float NOT NULL DEFAULT '0',
  `az` float NOT NULL DEFAULT '0',
  `aw` float NOT NULL DEFAULT '0',
  `model` varchar(45) NOT NULL,
  PRIMARY KEY (`carID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,1,'0',10086,0,0,0,0,0,0,0,''),(2,1,'0',10086,0,0,0,0,0,0,0,''),(3,1,'00.00.00.00:0000',10086,0,0,0,0,0,0,0,''),(4,1,'0',10086,0,0,0,0,0,0,0,''),(5,1,'222.222.222.222',123123,0,0,0,0,0,0,0,''),(6,1,'111.123.456.789',123123,1,1,1,1,1,1,1,''),(7,0,'222.222.111.111',1234,2.2,1.1,3.3,1.77,2.33,5.6,7.8,'2');
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `place`
--

DROP TABLE IF EXISTS `place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `place` (
  `desID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(512) NOT NULL DEFAULT '"china"',
  `locX` float NOT NULL DEFAULT '0',
  `locY` float NOT NULL DEFAULT '0',
  `locZ` float NOT NULL DEFAULT '0',
  `angX` float DEFAULT '0',
  `angY` float DEFAULT '0',
  `angZ` float DEFAULT '0',
  `angW` float DEFAULT '0',
  PRIMARY KEY (`desID`),
  UNIQUE KEY `desID_UNIQUE` (`desID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place`
--

LOCK TABLES `place` WRITE;
/*!40000 ALTER TABLE `place` DISABLE KEYS */;
INSERT INTO `place` VALUES (1,'北京市天安门',5,5,5,0,0,0,0),(2,'456',0,0,0,0,0,0,0),(3,'beijing',1,1,1,0,0,0,0);
/*!40000 ALTER TABLE `place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `taskID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `destination` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `startTime` date NOT NULL,
  `deliverTime` date DEFAULT NULL,
  `status` int(10) NOT NULL,
  `finishTime` date DEFAULT NULL,
  `carID` int(10) unsigned DEFAULT NULL,
  `cooperation` varchar(45) NOT NULL DEFAULT 'EMS',
  PRIMARY KEY (`taskID`),
  UNIQUE KEY `id_UNIQUE` (`taskID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (21,1,1,'2018-08-27',NULL,0,NULL,0,'申通'),(22,1,1,'2018-08-27',NULL,0,NULL,0,'顺丰'),(23,3,3,'2018-08-27',NULL,0,NULL,0,'圆通'),(24,3,2,'2018-08-27','2018-08-27',1,NULL,6,'EMS'),(25,3,1,'2018-08-27','2018-08-27',1,NULL,5,'申通');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(64) NOT NULL,
  `nickname` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `ip` varchar(16) NOT NULL DEFAULT '127.0.0.1',
  `account` varchar(64) NOT NULL,
  `port` int(6) unsigned DEFAULT '10086',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `account_UNIQUE` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'123456','hahaha','862344188@qq.com','0.0.0.0','宋云飞',10086),(2,'123456','111','1111','0.0.0.0','syf',10086),(3,'862344',NULL,NULL,'0:0:0:0:0:0:0:1','syfnico',10086),(4,'erertert','qweqwe','eqweeert','0:0:0:0:0:0:0:1','qweqwe',0),(5,'86787','9898','098','0:0:0:0:0:0:0:1','546567',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-28  0:36:47

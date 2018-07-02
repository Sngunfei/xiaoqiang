CREATE DATABASE  IF NOT EXISTS `xq` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `xq`;
-- MySQL dump 10.13  Distrib 5.6.37, for Win64 (x86_64)
--
-- Host: localhost    Database: xq
-- ------------------------------------------------------
-- Server version	5.6.37

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
-- Table structure for table `administer`
--

DROP TABLE IF EXISTS `administer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(64) NOT NULL,
  `ip` varchar(30) DEFAULT NULL,
  `account` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `account_UNIQUE` (`account`),
  KEY `userID` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8901 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car` (
  `carID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `status` varchar(200) NOT NULL DEFAULT 'ready',
  `ip` varchar(100) DEFAULT '"0.0.0.0"',
  PRIMARY KEY (`carID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `destination`
--

DROP TABLE IF EXISTS `destination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `destination` (
  `desID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(512) NOT NULL DEFAULT '"china"',
  `locX` float NOT NULL,
  `locY` float NOT NULL,
  `locZ` float NOT NULL,
  PRIMARY KEY (`desID`),
  UNIQUE KEY `desID_UNIQUE` (`desID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `status` varchar(64) NOT NULL,
  `finishTime` date DEFAULT NULL,
  `carID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`taskID`),
  UNIQUE KEY `id_UNIQUE` (`taskID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `ip` varchar(30) DEFAULT NULL,
  `account` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `account_UNIQUE` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'xq'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-02 22:35:24

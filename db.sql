-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: tasktracker
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `taskId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `typeId` int(11) NOT NULL,
  `category` varchar(45) NOT NULL,
  `cumulativeTimeSpent` float DEFAULT '0',
  `description` varchar(500) NOT NULL,
  `dueDate` date NOT NULL,
  `estimatedCompletionTime` float DEFAULT '0' COMMENT 'If empty (no average calculated for task type), prompt user for their best guess and use that.',
  `startDate` date DEFAULT NULL COMMENT 'Entered when user first adds time.',
  `completed` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`taskId`),
  UNIQUE KEY `taskId_UNIQUE` (`taskId`),
  KEY `fk_task_user_idx` (`userId`),
  KEY `fk_task_type1_idx` (`typeId`),
  CONSTRAINT `fk_task_type1` FOREIGN KEY (`typeId`) REFERENCES `type` (`typeId`),
  CONSTRAINT `fk_task_user` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (6,1,'Chapter 6',3,'PHP',81,'Read ch. 6 in Head First PHP','2017-02-28',80.5,'2017-03-01',1),(7,1,'Resume',3,'Business Writing',45,'Write resume','2017-03-10',120,'2017-03-07',0),(12,1,'Persausive Speech',3,'Speech',80,'Create a speech to convince people!','2017-03-25',80,'2017-03-07',1),(13,1,'Project 1',1,'PHP',200,'Mad Libs','2017-02-17',200,'2017-03-07',1),(14,1,'Chapter 7',3,'PHP',80,'Sessions and cookies','2017-02-27',80.5,'2017-03-07',1),(15,4,'Enviro. Paper',3,'Writing 102',0,'Research environmentally friendly practices for business','2017-04-14',0,'2017-03-08',0),(16,1,'Informative Speech',3,'Speech',65,'Inform people about an unknown topic','2017-04-29',80,'2017-03-18',0),(17,1,'Project 3',1,'PHP',0,'Website with blog capabilities','2017-04-19',200,'2017-03-29',0),(19,1,'Final Speech',3,'Speech',0,'Speech about topic of my choice','2017-05-09',80,'2017-03-29',0),(20,1,'Servlet Redirection Lab',3,'Java',0,'Servlet that using redirection instead of forwarding','2017-04-11',0,'2017-03-29',0);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taskentry`
--

DROP TABLE IF EXISTS `taskentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taskentry` (
  `taskEntryId` int(11) NOT NULL AUTO_INCREMENT,
  `taskId` int(11) NOT NULL,
  `dateEntered` datetime NOT NULL,
  `timeEnteredAmount` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`taskEntryId`),
  UNIQUE KEY `taskEntryId_UNIQUE` (`taskEntryId`),
  KEY `fk_taskEntry_task1_idx` (`taskId`),
  CONSTRAINT `fk_taskEntry_type1` FOREIGN KEY (`taskId`) REFERENCES `task` (`taskId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taskentry`
--

LOCK TABLES `taskentry` WRITE;
/*!40000 ALTER TABLE `taskentry` DISABLE KEYS */;
INSERT INTO `taskentry` VALUES (1,6,'2017-02-28 00:00:00',15),(2,6,'2017-03-01 11:15:09',30),(3,6,'2017-03-01 12:10:32',2),(4,6,'2017-03-01 12:15:21',2),(5,6,'2017-03-01 12:15:33',2),(6,6,'2017-03-07 21:03:06',0),(7,6,'2017-03-08 13:42:20',1),(8,6,'2017-03-08 17:35:21',4),(9,6,'2017-03-12 12:00:14',10),(10,12,'2017-03-12 12:01:07',10),(11,12,'2017-03-12 14:41:36',70),(12,6,'2017-03-12 15:02:18',15),(13,7,'2017-03-18 20:17:19',45),(14,16,'2017-03-18 20:23:13',50),(15,16,'2017-03-29 15:00:38',15);
/*!40000 ALTER TABLE `taskentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type` (
  `typeId` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(45) NOT NULL,
  PRIMARY KEY (`typeId`),
  UNIQUE KEY `typeId_UNIQUE` (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'Project'),(2,'Reading'),(3,'Assignment');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `email` varchar(254) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `userId_UNIQUE` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'sarah','somernik@madisoncollege.edu','Sarah','Omernik','adminpass'),(4,'sample','jdoe@fake.com','John','Doe','password');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertype`
--

DROP TABLE IF EXISTS `usertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usertype` (
  `userTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `typeId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`userTypeId`),
  UNIQUE KEY `userTaskId_UNIQUE` (`userTypeId`),
  KEY `fk_userType_user1_idx` (`userId`),
  KEY `fk_userType_type1_idx` (`typeId`),
  CONSTRAINT `fk_userType_user1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usertype_type1` FOREIGN KEY (`typeId`) REFERENCES `type` (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertype`
--

LOCK TABLES `usertype` WRITE;
/*!40000 ALTER TABLE `usertype` DISABLE KEYS */;
/*!40000 ALTER TABLE `usertype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-02 18:20:21

-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: eatreordertesting
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `azienda`
--

DROP TABLE IF EXISTS `azienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `azienda` (
  `nome` varchar(40) NOT NULL,
  `via` varchar(80) NOT NULL,
  `numero_civico` decimal(3,0) NOT NULL,
  `citta` varchar(45) NOT NULL,
  `provincia` char(2) NOT NULL,
  `partita_iva` char(11) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `orario_apertura` time NOT NULL,
  `orario_chiusura` time NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`email`,`nome`),
  CONSTRAINT `azienda_ibfk_1` FOREIGN KEY (`email`) REFERENCES `utenteregistrato` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `azienda`
--

LOCK TABLES `azienda` WRITE;
/*!40000 ALTER TABLE `azienda` DISABLE KEYS */;
INSERT INTO `azienda` VALUES ('da Vincenzo','via degli Ulivi ',23,'Salerno','SA','87654327321','3452314321','12:00:00','23:00:00','azienda1@gmail.com'),('PizzaPanini','san silvestro',3,'salerno','CE','12345678910','2345','12:00:00','13:00:00','pizzapanini@gmail.com');
/*!40000 ALTER TABLE `azienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `nome` varchar(40) NOT NULL,
  `cognome` varchar(40) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`email`,`nome`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`email`) REFERENCES `utenteregistrato` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('Marco','Dello Buono','Marco@libero.it'),('Rosario','Gagliardi','rosariogagliardi@msn.com');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fattorino`
--

DROP TABLE IF EXISTS `fattorino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fattorino` (
  `nome` varchar(40) NOT NULL,
  `cognome` varchar(40) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `citta_consegna` varchar(80) NOT NULL,
  `provincia` char(2) NOT NULL,
  `orario_inizio` time NOT NULL,
  `orario_fine` time NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`email`,`nome`),
  CONSTRAINT `fattorino_ibfk_1` FOREIGN KEY (`email`) REFERENCES `utenteregistrato` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fattorino`
--

LOCK TABLES `fattorino` WRITE;
/*!40000 ALTER TABLE `fattorino` DISABLE KEYS */;
INSERT INTO `fattorino` VALUES ('Marco','Dello Buono','0823546547','Salerno','SA','12:00:00','23:00:00','fattorino1@gmail.com');
/*!40000 ALTER TABLE `fattorino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giornilavorativi`
--

DROP TABLE IF EXISTS `giornilavorativi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giornilavorativi` (
  `giorno` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`email`,`giorno`),
  CONSTRAINT `giornilavorativi_ibfk_1` FOREIGN KEY (`email`) REFERENCES `utenteregistrato` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giornilavorativi`
--

LOCK TABLES `giornilavorativi` WRITE;
/*!40000 ALTER TABLE `giornilavorativi` DISABLE KEYS */;
INSERT INTO `giornilavorativi` VALUES ('FRIDAY','azienda1@gmail.com'),('MONDAY','azienda1@gmail.com'),('SATURDAY','azienda1@gmail.com'),('SUNDAY','azienda1@gmail.com'),('THURSDAY','azienda1@gmail.com'),('TUESDAY','azienda1@gmail.com'),('WEDNESDAY','azienda1@gmail.com'),('MONDAY','fattorino1@gmail.com'),('TUESDAY','fattorino1@gmail.com'),('MONDAY','pizzapanini@gmail.com'),('TUESDAY','pizzapanini@gmail.com');
/*!40000 ALTER TABLE `giornilavorativi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moderatore`
--

DROP TABLE IF EXISTS `moderatore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moderatore` (
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`email`,`id`),
  CONSTRAINT `moderatore_ibfk_1` FOREIGN KEY (`email`) REFERENCES `utenteregistrato` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moderatore`
--

LOCK TABLES `moderatore` WRITE;
/*!40000 ALTER TABLE `moderatore` DISABLE KEYS */;
INSERT INTO `moderatore` VALUES (1022,'eatsystem@eat.com');
/*!40000 ALTER TABLE `moderatore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `codice` int(11) NOT NULL AUTO_INCREMENT,
  `indirizzo_consegna` varchar(255) NOT NULL,
  `numero_carta` char(16) NOT NULL,
  `prezzo_totale` decimal(10,2) NOT NULL,
  `telefono_cliente` varchar(10) NOT NULL,
  `note` varchar(150) DEFAULT NULL,
  `stato` varchar(20) NOT NULL,
  `acquirente` varchar(40) DEFAULT NULL,
  `email_acquirente` varchar(100) DEFAULT NULL,
  `azienda` varchar(40) DEFAULT NULL,
  `email_azienda` varchar(100) DEFAULT NULL,
  `fattorino` varchar(40) DEFAULT NULL,
  `email_fattorino` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codice`),
  KEY `email_acquirente` (`email_acquirente`,`acquirente`),
  KEY `email_azienda` (`email_azienda`,`azienda`),
  KEY `email_fattorino` (`email_fattorino`,`fattorino`),
  CONSTRAINT `ordine_ibfk_1` FOREIGN KEY (`email_acquirente`, `acquirente`) REFERENCES `cliente` (`email`, `nome`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ordine_ibfk_2` FOREIGN KEY (`email_azienda`, `azienda`) REFERENCES `azienda` (`email`, `nome`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `ordine_ibfk_3` FOREIGN KEY (`email_fattorino`, `fattorino`) REFERENCES `fattorino` (`email`, `nome`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (3,'Via Salvo D\'Acquisto, 1','1234123412341234',4.50,'3930170040','MPIIII','Ritirato','Rosario','rosariogagliardi@msn.com','da Vincenzo','azienda1@gmail.com','Marco','fattorino1@gmail.com'),(4,'Via Salvo D\'Acquisto, 1','1234123412341234',13.50,'3930170040','JHLAKALKSA','Consegnato','Rosario','rosariogagliardi@msn.com','da Vincenzo','azienda1@gmail.com','Marco','fattorino1@gmail.com'),(5,'Via Salvo D\'Acquisto, 1','1234123412341234',4.50,'3930170040','kzkclklzd','Consegnato','Rosario','rosariogagliardi@msn.com','da Vincenzo','azienda1@gmail.com','Marco','fattorino1@gmail.com'),(6,'Via Salvo D\'Acquisto, 1','1234123412341234',4.50,'3930170040','ffffffffffffffffffffffffffff','Consegnato','Rosario','Rosariogagliardi@msn.com','da Vincenzo','azienda1@gmail.com','Marco','fattorino1@gmail.com'),(7,'Via Calzella Carfagni, 26','1234123412341234',4.50,'3450534980','ROSARIO MAGNA I PISELLI','Consegnato','Marco','Marco@libero.it','da Vincenzo','azienda1@gmail.com','Marco','fattorino1@gmail.com'),(8,'Via Salvo D\'Acquisto, 1','1234123412341234',4.50,'3930170040','oioioioioioiii','Ritirato','Rosario','rosariogagliardi@msn.com','da Vincenzo','azienda1@gmail.com','Marco','fattorino1@gmail.com'),(9,'via pippo','1234123412341234',0.00,'1234567890','ciao ciao ciao ciao','In preparazione','Rosario','rosariogagliardi@msn.com','da Vincenzo','azienda1@gmail.com','Marco','fattorino1@gmail.com'),(10,'via pippo','1234123412341234',0.00,'1234567890','ciao ciao ciao ciao','In preparazione','Rosario','rosariogagliardi@msn.com','da Vincenzo','azienda1@gmail.com','Marco','fattorino1@gmail.com'),(11,'via pippo','1234123412341234',0.00,'1234567890','ciao ciao ciao ciao','In preparazione','Rosario','rosariogagliardi@msn.com','da Vincenzo','azienda1@gmail.com','Marco','fattorino1@gmail.com'),(12,'via pippo','1234123412341234',0.00,'1234567890','ciao ciao ciao ciao','Ritirato','Rosario','rosariogagliardi@msn.com','da Vincenzo','azienda1@gmail.com','Marco','fattorino1@gmail.com'),(13,'via pippo','1234123412341234',0.00,'1234567890','ciao ciao ciao ciao','In preparazione','Rosario','rosariogagliardi@msn.com','da Vincenzo','azienda1@gmail.com','Marco','fattorino1@gmail.com'),(14,'via pippo','1234123412341234',0.00,'1234567890','ciao ciao ciao ciao','In preparazione','Rosario','rosariogagliardi@msn.com','da Vincenzo','azienda1@gmail.com','Marco','fattorino1@gmail.com'),(15,'via pippo','1234123412341234',0.00,'1234567890','ciao ciao ciao ciao','In preparazione','Rosario','rosariogagliardi@msn.com','da Vincenzo','azienda1@gmail.com','Marco','fattorino1@gmail.com'),(16,'via pippo','1234123412341234',0.00,'1234567890','ciao ciao ciao ciao','In preparazione','Rosario','rosariogagliardi@msn.com','da Vincenzo','azienda1@gmail.com','Marco','fattorino1@gmail.com'),(17,'via pippo','1234123412341234',0.00,'1234567890','ciao ciao ciao ciao','In preparazione','Rosario','rosariogagliardi@msn.com','da Vincenzo','azienda1@gmail.com','Marco','fattorino1@gmail.com'),(18,'via pippo','1234123412341234',0.00,'1234567890','ciao ciao ciao ciao','In preparazione','Rosario','rosariogagliardi@msn.com','da Vincenzo','azienda1@gmail.com','Marco','fattorino1@gmail.com'),(19,'via pippo','1234123412341234',0.00,'1234567890','ciao ciao ciao ciao','In preparazione','Rosario','rosariogagliardi@msn.com','da Vincenzo','azienda1@gmail.com','Marco','fattorino1@gmail.com');
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodotto`
--

DROP TABLE IF EXISTS `prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodotto` (
  `codice` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descrizione` varchar(250) NOT NULL,
  `prezzo` decimal(10,2) NOT NULL,
  `path_immagine` varchar(250) NOT NULL,
  `azienda` varchar(40) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codice`),
  KEY `email` (`email`,`azienda`),
  CONSTRAINT `prodotto_ibfk_1` FOREIGN KEY (`email`, `azienda`) REFERENCES `azienda` (`email`, `nome`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
INSERT INTO `prodotto` VALUES (1,'Pizza Margherita','Pizza margherita',4.50,'https://www.giallozafferano.it/images/ricette/175/17528/foto_hd/hd650x433_wm.jpg','da Vincenzo','azienda1@gmail.com'),(12,'Cibo per cani','ciao ciao caio',7.50,'http://doggo.com','PizzaPanini','pizzapanini@gmail.com');
/*!40000 ALTER TABLE `prodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodottoquantita`
--

DROP TABLE IF EXISTS `prodottoquantita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodottoquantita` (
  `quantita` decimal(2,0) NOT NULL,
  `prodotto` int(11) NOT NULL,
  `ordine` int(11) NOT NULL,
  PRIMARY KEY (`prodotto`,`ordine`),
  KEY `ordine` (`ordine`),
  CONSTRAINT `prodottoquantita_ibfk_1` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`codice`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `prodottoquantita_ibfk_2` FOREIGN KEY (`ordine`) REFERENCES `ordine` (`codice`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodottoquantita`
--

LOCK TABLES `prodottoquantita` WRITE;
/*!40000 ALTER TABLE `prodottoquantita` DISABLE KEYS */;
INSERT INTO `prodottoquantita` VALUES (1,1,3),(3,1,4),(1,1,5),(1,1,6),(1,1,7),(1,1,8),(1,1,18),(1,1,19);
/*!40000 ALTER TABLE `prodottoquantita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utenteregistrato`
--

DROP TABLE IF EXISTS `utenteregistrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utenteregistrato` (
  `email` varchar(100) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `tipologia` varchar(10) NOT NULL,
  `is_banned` tinyint(1) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utenteregistrato`
--

LOCK TABLES `utenteregistrato` WRITE;
/*!40000 ALTER TABLE `utenteregistrato` DISABLE KEYS */;
INSERT INTO `utenteregistrato` VALUES ('azienda1@gmail.com','pippo1234','Azienda',0),('eatsystem@eat.com','moderator','Moderatore',0),('fattorino1@gmail.com','fattorino1234','Fattorino',0),('Marco@libero.it','marco1234','Cliente',0),('pizzapanini@gmail.com','pippopluto','Azienda',0),('rosariogagliardi@msn.com','pallina1234','Cliente',0);
/*!40000 ALTER TABLE `utenteregistrato` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-20 14:21:25

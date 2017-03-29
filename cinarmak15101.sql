-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: cinarmak2
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `banka_bilg`
--

DROP TABLE IF EXISTS `banka_bilg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banka_bilg` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `banka_adi` varchar(45) NOT NULL,
  `banka_tel` varchar(45) NOT NULL,
  `banka_adres` varchar(45) NOT NULL,
  `banka_no` varchar(45) NOT NULL,
  `banka_mail` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banka_bilg`
--

LOCK TABLES `banka_bilg` WRITE;
/*!40000 ALTER TABLE `banka_bilg` DISABLE KEYS */;
/*!40000 ALTER TABLE `banka_bilg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banka_cikan`
--

DROP TABLE IF EXISTS `banka_cikan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banka_cikan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `banka_no` varchar(45) NOT NULL,
  `sube` varchar(45) NOT NULL,
  `telefon` varchar(45) NOT NULL,
  `tarih` varchar(45) NOT NULL,
  `hesap_no` varchar(45) NOT NULL,
  `iban_no` varchar(45) NOT NULL,
  `cikan_miktar` int(10) unsigned NOT NULL,
  `kurum_no` varchar(45) NOT NULL,
  `cinsi` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banka_cikan`
--

LOCK TABLES `banka_cikan` WRITE;
/*!40000 ALTER TABLE `banka_cikan` DISABLE KEYS */;
/*!40000 ALTER TABLE `banka_cikan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banka_giren`
--

DROP TABLE IF EXISTS `banka_giren`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banka_giren` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `banka_no` varchar(45) NOT NULL,
  `sube` varchar(45) NOT NULL,
  `telefon` varchar(45) NOT NULL,
  `tarih` varchar(45) NOT NULL,
  `hesap_no` varchar(45) NOT NULL,
  `iban_no` varchar(45) NOT NULL,
  `giren_miktar` int(10) unsigned NOT NULL,
  `kurum_no` varchar(45) NOT NULL,
  `cinsi` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banka_giren`
--

LOCK TABLES `banka_giren` WRITE;
/*!40000 ALTER TABLE `banka_giren` DISABLE KEYS */;
/*!40000 ALTER TABLE `banka_giren` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cek_bilgi`
--

DROP TABLE IF EXISTS `cek_bilgi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cek_bilgi` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tutar` int(10) unsigned NOT NULL,
  `cek_no` varchar(45) NOT NULL,
  `kurum_no` varchar(45) NOT NULL,
  `vade` varchar(45) NOT NULL,
  `yazilma_tarihi` varchar(45) NOT NULL,
  `banka_no` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cek_bilgi`
--

LOCK TABLES `cek_bilgi` WRITE;
/*!40000 ALTER TABLE `cek_bilgi` DISABLE KEYS */;
/*!40000 ALTER TABLE `cek_bilgi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cek_gelen`
--

DROP TABLE IF EXISTS `cek_gelen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cek_gelen` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cek_no` varchar(45) NOT NULL,
  `kurum_no` varchar(45) NOT NULL,
  `gelis_tarihi` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cek_gelen`
--

LOCK TABLES `cek_gelen` WRITE;
/*!40000 ALTER TABLE `cek_gelen` DISABLE KEYS */;
/*!40000 ALTER TABLE `cek_gelen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cek_giden`
--

DROP TABLE IF EXISTS `cek_giden`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cek_giden` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cek_no` varchar(45) NOT NULL,
  `kurum_no` varchar(45) NOT NULL,
  `cikis_tarihi` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cek_giden`
--

LOCK TABLES `cek_giden` WRITE;
/*!40000 ALTER TABLE `cek_giden` DISABLE KEYS */;
/*!40000 ALTER TABLE `cek_giden` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doviz`
--

DROP TABLE IF EXISTS `doviz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doviz` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `doviz_no` varchar(45) NOT NULL,
  `doviz_turu` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doviz`
--

LOCK TABLES `doviz` WRITE;
/*!40000 ALTER TABLE `doviz` DISABLE KEYS */;
/*!40000 ALTER TABLE `doviz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doviz_cikan`
--

DROP TABLE IF EXISTS `doviz_cikan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doviz_cikan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `miktar` int(10) unsigned NOT NULL,
  `tarih` varchar(45) NOT NULL,
  `kurum_no` varchar(45) NOT NULL,
  `kur` int(10) unsigned NOT NULL,
  `doviz_no` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doviz_cikan`
--

LOCK TABLES `doviz_cikan` WRITE;
/*!40000 ALTER TABLE `doviz_cikan` DISABLE KEYS */;
/*!40000 ALTER TABLE `doviz_cikan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doviz_giren`
--

DROP TABLE IF EXISTS `doviz_giren`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doviz_giren` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `miktar` int(10) unsigned NOT NULL,
  `tarih` varchar(45) NOT NULL,
  `kurum_no` varchar(45) NOT NULL,
  `kur` int(10) unsigned NOT NULL,
  `doviz_no` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doviz_giren`
--

LOCK TABLES `doviz_giren` WRITE;
/*!40000 ALTER TABLE `doviz_giren` DISABLE KEYS */;
/*!40000 ALTER TABLE `doviz_giren` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hesaplarim`
--

DROP TABLE IF EXISTS `hesaplarim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hesaplarim` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hesap_no` varchar(45) NOT NULL,
  `iban_no` varchar(45) NOT NULL,
  `sube` varchar(45) NOT NULL,
  `banka_no` varchar(45) NOT NULL,
  `banka_tel` varchar(45) NOT NULL,
  `banka_adres` varchar(45) NOT NULL,
  `banka_mail` varchar(45) NOT NULL,
  `musteri_no` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hesaplarim`
--

LOCK TABLES `hesaplarim` WRITE;
/*!40000 ALTER TABLE `hesaplarim` DISABLE KEYS */;
/*!40000 ALTER TABLE `hesaplarim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `k.hesaplar`
--

DROP TABLE IF EXISTS `k.hesaplar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `k.hesaplar` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `kurum_no` varchar(45) NOT NULL,
  `hesap_no` varchar(45) NOT NULL,
  `sube` varchar(45) NOT NULL,
  `iban_no` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `k.hesaplar`
--

LOCK TABLES `k.hesaplar` WRITE;
/*!40000 ALTER TABLE `k.hesaplar` DISABLE KEYS */;
/*!40000 ALTER TABLE `k.hesaplar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kasa_nakit_cikan`
--

DROP TABLE IF EXISTS `kasa_nakit_cikan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kasa_nakit_cikan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `kurum_no` varchar(45) NOT NULL,
  `tutar` int(10) unsigned NOT NULL,
  `cikis_tarihi` varchar(45) NOT NULL,
  `aciklama` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kasa_nakit_cikan`
--

LOCK TABLES `kasa_nakit_cikan` WRITE;
/*!40000 ALTER TABLE `kasa_nakit_cikan` DISABLE KEYS */;
/*!40000 ALTER TABLE `kasa_nakit_cikan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kasa_nakit_giren`
--

DROP TABLE IF EXISTS `kasa_nakit_giren`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kasa_nakit_giren` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `kurum_no` varchar(45) NOT NULL,
  `tutar` int(10) unsigned NOT NULL,
  `giris_tarih` varchar(45) NOT NULL,
  `aciklama` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kasa_nakit_giren`
--

LOCK TABLES `kasa_nakit_giren` WRITE;
/*!40000 ALTER TABLE `kasa_nakit_giren` DISABLE KEYS */;
/*!40000 ALTER TABLE `kasa_nakit_giren` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `senet_bilg`
--

DROP TABLE IF EXISTS `senet_bilg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `senet_bilg` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `senet_no` varchar(45) NOT NULL,
  `kurum_no` varchar(45) NOT NULL,
  `tutar` int(10) unsigned NOT NULL,
  `vade` varchar(45) NOT NULL,
  `tarih` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `senet_bilg`
--

LOCK TABLES `senet_bilg` WRITE;
/*!40000 ALTER TABLE `senet_bilg` DISABLE KEYS */;
/*!40000 ALTER TABLE `senet_bilg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `senet_cikan`
--

DROP TABLE IF EXISTS `senet_cikan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `senet_cikan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `kurum_no` varchar(45) NOT NULL,
  `cikis_tarihi` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `senet_cikan`
--

LOCK TABLES `senet_cikan` WRITE;
/*!40000 ALTER TABLE `senet_cikan` DISABLE KEYS */;
/*!40000 ALTER TABLE `senet_cikan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `senet_giren`
--

DROP TABLE IF EXISTS `senet_giren`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `senet_giren` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `kurum_no` varchar(45) NOT NULL,
  `giris_tarih` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `senet_giren`
--

LOCK TABLES `senet_giren` WRITE;
/*!40000 ALTER TABLE `senet_giren` DISABLE KEYS */;
/*!40000 ALTER TABLE `senet_giren` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silinen_bankacikan`
--

DROP TABLE IF EXISTS `silinen_bankacikan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `silinen_bankacikan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sil_bankano` varchar(45) NOT NULL,
  `sil_sube` varchar(45) NOT NULL,
  `sil_telefon` varchar(45) NOT NULL,
  `sil_tarih` varchar(45) NOT NULL,
  `sil_hesapno` varchar(45) NOT NULL,
  `sil_ibanno` varchar(45) NOT NULL,
  `sil_cikanmiktar` varchar(45) NOT NULL,
  `sil_kurumno` varchar(45) NOT NULL,
  `sil_cinsi` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silinen_bankacikan`
--

LOCK TABLES `silinen_bankacikan` WRITE;
/*!40000 ALTER TABLE `silinen_bankacikan` DISABLE KEYS */;
/*!40000 ALTER TABLE `silinen_bankacikan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silinen_bankagiren`
--

DROP TABLE IF EXISTS `silinen_bankagiren`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `silinen_bankagiren` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sil_bankano` varchar(45) NOT NULL,
  `sil_sube` varchar(45) NOT NULL,
  `sil_telefon` varchar(45) NOT NULL,
  `sil_tarih` varchar(45) NOT NULL,
  `sil_hesapno` varchar(45) NOT NULL,
  `sil_ibanno` varchar(45) NOT NULL,
  `sil_cikanmiktar` varchar(45) NOT NULL,
  `sil_kurumno` varchar(45) NOT NULL,
  `sil_cinsi` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silinen_bankagiren`
--

LOCK TABLES `silinen_bankagiren` WRITE;
/*!40000 ALTER TABLE `silinen_bankagiren` DISABLE KEYS */;
/*!40000 ALTER TABLE `silinen_bankagiren` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silinen_cek_gelen`
--

DROP TABLE IF EXISTS `silinen_cek_gelen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `silinen_cek_gelen` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `silinen_cek_no` varchar(45) NOT NULL,
  `silinen_kurum_no` varchar(45) NOT NULL,
  `silinme_tarihi` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silinen_cek_gelen`
--

LOCK TABLES `silinen_cek_gelen` WRITE;
/*!40000 ALTER TABLE `silinen_cek_gelen` DISABLE KEYS */;
/*!40000 ALTER TABLE `silinen_cek_gelen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silinen_cek_giden`
--

DROP TABLE IF EXISTS `silinen_cek_giden`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `silinen_cek_giden` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `silinen_gidencek_no` varchar(45) NOT NULL,
  `silinen_giden_kurum_no` varchar(45) NOT NULL,
  `silinen_tarih` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silinen_cek_giden`
--

LOCK TABLES `silinen_cek_giden` WRITE;
/*!40000 ALTER TABLE `silinen_cek_giden` DISABLE KEYS */;
/*!40000 ALTER TABLE `silinen_cek_giden` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silinen_dovizcikan`
--

DROP TABLE IF EXISTS `silinen_dovizcikan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `silinen_dovizcikan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sil_miktar` varchar(45) NOT NULL,
  `sil_tarih` varchar(45) NOT NULL,
  `sil_kurumno` varchar(45) NOT NULL,
  `sil_kur` varchar(45) NOT NULL,
  `sil_dovizno` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silinen_dovizcikan`
--

LOCK TABLES `silinen_dovizcikan` WRITE;
/*!40000 ALTER TABLE `silinen_dovizcikan` DISABLE KEYS */;
/*!40000 ALTER TABLE `silinen_dovizcikan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silinen_dovizgiren`
--

DROP TABLE IF EXISTS `silinen_dovizgiren`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `silinen_dovizgiren` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sil_miktar` varchar(45) NOT NULL,
  `sil_tarih` varchar(45) NOT NULL,
  `sil_kurumno` varchar(45) NOT NULL,
  `sil_kur` varchar(45) NOT NULL,
  `sil_dovizno` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silinen_dovizgiren`
--

LOCK TABLES `silinen_dovizgiren` WRITE;
/*!40000 ALTER TABLE `silinen_dovizgiren` DISABLE KEYS */;
/*!40000 ALTER TABLE `silinen_dovizgiren` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silinen_hesaplarim`
--

DROP TABLE IF EXISTS `silinen_hesaplarim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `silinen_hesaplarim` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sil_hesapno` varchar(45) NOT NULL,
  `sil_ibanno` varchar(45) NOT NULL,
  `sil_sube` varchar(45) NOT NULL,
  `sil_bankano` varchar(45) NOT NULL,
  `sil_bankatel` varchar(45) NOT NULL,
  `sil_bankaadres` varchar(45) NOT NULL,
  `sil_bankamail` varchar(45) NOT NULL,
  `sil_musterino` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silinen_hesaplarim`
--

LOCK TABLES `silinen_hesaplarim` WRITE;
/*!40000 ALTER TABLE `silinen_hesaplarim` DISABLE KEYS */;
/*!40000 ALTER TABLE `silinen_hesaplarim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silinen_nakitgiren`
--

DROP TABLE IF EXISTS `silinen_nakitgiren`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `silinen_nakitgiren` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sil_kurumno` varchar(45) NOT NULL,
  `sil_tutar` varchar(45) NOT NULL,
  `sil_cikistarihi` varchar(45) NOT NULL,
  `sil_aciklama` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silinen_nakitgiren`
--

LOCK TABLES `silinen_nakitgiren` WRITE;
/*!40000 ALTER TABLE `silinen_nakitgiren` DISABLE KEYS */;
/*!40000 ALTER TABLE `silinen_nakitgiren` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silinen_senetcikan`
--

DROP TABLE IF EXISTS `silinen_senetcikan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `silinen_senetcikan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sil_senetcikan_kurumno` varchar(45) NOT NULL,
  `sil_senetcikan_tarih` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silinen_senetcikan`
--

LOCK TABLES `silinen_senetcikan` WRITE;
/*!40000 ALTER TABLE `silinen_senetcikan` DISABLE KEYS */;
/*!40000 ALTER TABLE `silinen_senetcikan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silinen_senetgiren`
--

DROP TABLE IF EXISTS `silinen_senetgiren`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `silinen_senetgiren` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sil_senetgiren_kurumno` varchar(45) NOT NULL,
  `sil_senetgiren_tarih` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silinen_senetgiren`
--

LOCK TABLES `silinen_senetgiren` WRITE;
/*!40000 ALTER TABLE `silinen_senetgiren` DISABLE KEYS */;
/*!40000 ALTER TABLE `silinen_senetgiren` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silinen_sirket`
--

DROP TABLE IF EXISTS `silinen_sirket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `silinen_sirket` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sil_kurumno` varchar(45) NOT NULL,
  `sil_kurumadi` varchar(45) NOT NULL,
  `sil_kurumadres` varchar(45) NOT NULL,
  `sil_kurumtel` varchar(45) NOT NULL,
  `sil_kurummail` varchar(45) NOT NULL,
  `sil_k.hesapno` varchar(45) NOT NULL,
  `sil_vergino` varchar(45) NOT NULL,
  `sil_yetkilino` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silinen_sirket`
--

LOCK TABLES `silinen_sirket` WRITE;
/*!40000 ALTER TABLE `silinen_sirket` DISABLE KEYS */;
/*!40000 ALTER TABLE `silinen_sirket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silinen_sirketyetkililer`
--

DROP TABLE IF EXISTS `silinen_sirketyetkililer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `silinen_sirketyetkililer` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sil_yetkilino` varchar(45) NOT NULL,
  `sil_sirketno` varchar(45) NOT NULL,
  `sil_yetkiliunvan` varchar(45) NOT NULL,
  `sil_yetkiliadi` varchar(45) NOT NULL,
  `sil_yetkilisoyad` varchar(45) NOT NULL,
  `sil_yetkilitel` varchar(45) NOT NULL,
  `sil_yetkilimail` varchar(45) NOT NULL,
  `sil_yetkiliadres` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silinen_sirketyetkililer`
--

LOCK TABLES `silinen_sirketyetkililer` WRITE;
/*!40000 ALTER TABLE `silinen_sirketyetkililer` DISABLE KEYS */;
/*!40000 ALTER TABLE `silinen_sirketyetkililer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sininen_nakitcikan`
--

DROP TABLE IF EXISTS `sininen_nakitcikan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sininen_nakitcikan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sil_kurumno` varchar(45) NOT NULL,
  `sil_tutar` varchar(45) NOT NULL,
  `sil_cikistarihi` varchar(45) NOT NULL,
  `sil_aciklama` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sininen_nakitcikan`
--

LOCK TABLES `sininen_nakitcikan` WRITE;
/*!40000 ALTER TABLE `sininen_nakitcikan` DISABLE KEYS */;
/*!40000 ALTER TABLE `sininen_nakitcikan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sirket_bilg`
--

DROP TABLE IF EXISTS `sirket_bilg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sirket_bilg` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `kurum_no` varchar(45) NOT NULL,
  `kurum_adi` varchar(45) NOT NULL,
  `kurum_adres` varchar(45) NOT NULL,
  `kurum_tel` varchar(45) NOT NULL,
  `kurum_mail` varchar(45) NOT NULL,
  `k.hesap_no` varchar(45) NOT NULL,
  `vergi_no` varchar(45) NOT NULL,
  `yetkili_no` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sirket_bilg`
--

LOCK TABLES `sirket_bilg` WRITE;
/*!40000 ALTER TABLE `sirket_bilg` DISABLE KEYS */;
/*!40000 ALTER TABLE `sirket_bilg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sirket_yetkililer`
--

DROP TABLE IF EXISTS `sirket_yetkililer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sirket_yetkililer` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `yetkili_no` varchar(45) NOT NULL,
  `sirket_no` varchar(45) NOT NULL,
  `yetkili_unvan` varchar(45) NOT NULL,
  `yetkili_adi` varchar(45) NOT NULL,
  `yetkili_soyad` varchar(45) NOT NULL,
  `yetkili_tel` varchar(45) NOT NULL,
  `yetkili_mail` varchar(45) NOT NULL,
  `yetkili_adres` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sirket_yetkililer`
--

LOCK TABLES `sirket_yetkililer` WRITE;
/*!40000 ALTER TABLE `sirket_yetkililer` DISABLE KEYS */;
/*!40000 ALTER TABLE `sirket_yetkililer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-15 14:52:35

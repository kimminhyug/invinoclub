-- MySQL dump 10.13  Distrib 5.6.46, for Linux (x86_64)
--
-- Host: localhost    Database: kmh2
-- ------------------------------------------------------
-- Server version	5.6.46

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
-- Table structure for table `COMMON`
--

DROP TABLE IF EXISTS `COMMON`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COMMON` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `usarge` char(40) COLLATE utf8_unicode_ci NOT NULL,
  `value` char(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COMMON`
--

LOCK TABLES `COMMON` WRITE;
/*!40000 ALTER TABLE `COMMON` DISABLE KEYS */;
INSERT INTO `COMMON` VALUES (1,'lolApiKey','RGAPI-d6dafe61-2fe6-45a2-9b56-10cba03dcfe9'),(3,'REG_DATE','2020-03-09 - 2020-03-15'),(4,'LOLTFTAPIKEY','RGAPI-968cc1e6-67e2-487e-96e9-729c6a915200');
/*!40000 ALTER TABLE `COMMON` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `name` char(30) COLLATE utf8_unicode_ci NOT NULL,
  `tier` char(13) COLLATE utf8_unicode_ci DEFAULT NULL,
  `line` char(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `authority` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `accountId` char(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PUUID` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `playCheck` char(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `afk` char(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `joindate` date DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES ('Daisy True',NULL,NULL,'일반','8sPxiAfOmlFmrCUjG7akmEdbEgDZVAx-IICE0fbtGVcvJr4','C57Vwhs26mV9zY_nNSFcWL2fOFMNIFhJeezfrwu7rjzKLptpKACa6yeaCUYmWtw7-pELPUAS2b8PyQ','play',NULL,NULL),('Infernal Machine',NULL,NULL,'일반','gceI__hlnwvarsn5RP8n64XargW3K9ARUt6AQ6VA0Pxa','JNbYUcDIqV-h-J01dz2ToaaVrX8gRfXjYdGXqe_XumOF9HEg8RiJs2CjX0Dlq3uo2HmJJxCNr7AdvA','',NULL,NULL),('I윌BeThere',NULL,NULL,'일반','xlPBL3pQdKoCtVnl05noYKveHeIAZzILbL6WjoK5s8eXD-U','lyYry6TiFrmbZRXmUXRxy4JT_LhjgrPwSYN2fRWq9binNlldP7fUpC2Ll8puOHVVkj3HDQEmqHChIQ','play',NULL,NULL),('Junang2',NULL,NULL,'일반','HQZkJBzf2uci52gFcwurJpUP-jsF6TDfXBHGkHwUwUHo','Dki7DEt0Vsq3njhanRudTLJAe2A2zAWEGmt4Ble9bvMQZ--nF3VmQI7sN21pV6eu7pQrg7SFHpsnpw','play',NULL,NULL),('Mein Kaiser',NULL,NULL,'일반','ULU5H09cu6DGlPpQWAyimWG3iKD7uJCQYPVCh1s_ronI','o63wiLhKXFexzE4o_9PesNvanNxzSPtxdGaKyozzfQ0r1jpZdn06UTx0GMLAsFR9uSgkkyrsZFJVgA','play',NULL,NULL),('Mind Blowing',NULL,NULL,'일반','pd6EqFnwfVnVBfyGPDNIJAPuzAsRxKFar-f3Svd-7KLj','sGFrG67S_p4IiN3Pf2nuXzg7DisBJ6PvxdBmVYwJW0Ykbu8VsY2odUznChiSldSVmnTi6qICGLmQUw','play',NULL,NULL),('MI노',NULL,NULL,'일반','74XX3Y2weMvcDTatsQCTLEfP_UaG9L-fe0PKK4UXkB-G','-cj2nmyy-8cntzUar-xPx-DaQc0uDTwlp0tLfGRaEjwsb1tBKj9Gbf1l42KtILBDb5ZlRks4ionmQA','play',NULL,NULL),('Ne5s',NULL,NULL,'일반','5Msknn0yVN3bWVsnXrQts4xy27nW6om05bae5sCMzv8','Rds2ECxCwDtcNJM9OUCwYAwCAJcIfTsdwYb0YVcEvQ1-c0U9t3_pC0k-WwJbO3P9iZgQAFLVAjPskw','play',NULL,NULL),('Ssaulaby',NULL,NULL,'일반','puK01rg6F9M6ikgeVuGNDKzjhQYFlYK_DtJihuKLLbKk','9WZcNJyWRYWwyV-RD6T4HZUlQM1gNjbnPd_Zs41IJtCv3JmjCA4vbigRDnVcHmX2tOqlciHRp6SWeQ','play',NULL,NULL),('The Axiom',NULL,NULL,'일반','QXKXT93CyJNJYagl1hNIGGC_1IVUlWRpGbuHuKiqBMkIiMw','VfCDR3BPH4jHrjvZfMvHQnNBeKvp-UcHskyyW6BMWhlP4wxmdP7JYHx-D63K4oIMNtbzF58wbAuP4A','',NULL,NULL),('YEONDOO',NULL,NULL,'일반','IvfC8vXXbebxlGa4_1mZPqc_NDnkFm1zBVnm6qSbMs0','fI69_6xXWZwmIzaaX3ZJlmb6xEU26zwGFUymlHqAHl7bD38L28lv9WPWLii1JsYmdqH93Vw7_cby2A','play',NULL,NULL),('기찬네',NULL,NULL,'일반','WDRw_19wVfJaXHB33baSkU9FUdDYRw1Z9NDS-tGaL6Qb','f-h9WiKn-e_HeDrt5gEpxoHJlZqsHgdRD6cOmpedyA_JFJm6xNubSWyACGK7VyehYhaHKlguxQNhpw','play',NULL,NULL),('김꽃잎',NULL,NULL,'일반','4RVAGKqPlKyhwvqZkLhuH5OXi9X9DX-3uuKphDZ80VaK','QmLsWuO2bqbQ3efjuiNXrOILD5OMc7cIlm3CFM8ReHMe33SaaxE1jJhulP6AdP0QLxVCELJlmJ0d4g','play',NULL,NULL),('내가생각하는바다',NULL,NULL,'일반','VmCJwOKg3wJ7LjWGvCLj6bjW3jh5ZRy2sWPpZ7-rtvZE','1mLgYNMUiBWZVcfyFngRtHc63KD0d792CXw9uptk7FInff1qgBbu0ukkkvpkLZoCgftbdGwx4W-Cdw','play',NULL,NULL),('니가너가내가',NULL,NULL,'일반','Uo1SctJ62VZOKZeMxSVcBmaJu5skUhRICaOlIPEkcV_xk7WmFw6XRYwr','T_XYEqVqODtkQCQ6qrqsQqT_JBKsyPaZGla7DLN0HgddAj2F8TxWNZb6KrSHUR7jF8XO8-sGrNtlLA','play',NULL,NULL),('대후우운',NULL,NULL,'일반','NLt0p8VAj4vjcLRJC1sLAp9wgsnWE2dB08cbqJRU6bsC','hPJfkLfJcvhpRougByalBZwzOn4olYfWf-DV9uq6x5VymT_cSZcBUep8SUWEDWToD1UuHF5QvS8SZA','play',NULL,NULL),('려나링',NULL,NULL,'일반','x-QrXPjyvKXam5voNKyaukJdd4skiaJCxy23h9kQazON','DWAdJ_EWoWbgIz6KmTNGSP-wVNYcRdzMDbKowv6cLclsmgiwnLCAlfM4-8wVyQ11jgCY7TnBpMXLcQ','play',NULL,NULL),('메1론',NULL,NULL,'일반','UVzMOKeBximSogYkwwKsCBp17jPFQw_y9KkNIEys191rgZc','HGrEyefxpMrl57G8gYiMVpCOQGbcdbTAlrEPq4B4Bq00GPtnhIcS_cEQpu2yKUqtn4KMeSwG1ccx4A','play',NULL,NULL),('바늬',NULL,NULL,'일반','YlvPjwcrW3PE6i8-YKAk6-pFQFNcX_I7Fyl3xLScOW7M','7DnW9d07KiRttwMMf9lCIq1DNrWNptOUlodegEAodX5dL6nn0mJO3WQPin9g7sZDAir58qmOl12MqQ','닉네임 확인필요 또는 게임기록없음','실종',NULL),('백묘아',NULL,NULL,'일반','bAe4ld5h7S8aUaHssyv3T63ZmraKgbIyrP72rsZPjHc','-zm8gWwXyLfKJMs-TABLwUvO9JKG0H_iEGo20TsQ6zguooWl1btzHR7Vl_LjL6X2OwgPCjbQrZhD3w','',NULL,NULL),('보검보검박보검',NULL,NULL,'일반','dkDQLdy9IX9H_mxE0aFEE67z6TMshkFZxCByJ4Xe28E','pxzJ8ScrnbdX6Zw1XRZl4tWNrGdFLkdsHfRd63xT5C8QzhOjPyve5h3hryGoO7W48ZQzGVdKwjiGvQ','play',NULL,NULL),('빵이수호대',NULL,NULL,'일반','lswOxS_HwbqMAiGhXR4lWHGvinSteKI2oRTHO-T2ZMbL7zYnzg5eBrHh','e3jHdaHW8MiGO62bvP2ZDNx4_UxjU7EiThXpk8Vv7ODpSQYAx0A29JQ4SOGCURp37rrBDhqWg4pb2A','play',NULL,NULL),('상평동유성호',NULL,NULL,'일반','3ENgIgrqNkulqku_-vvYRODne92QtbWzMeUj4Te3rVXcUmxUpnNHc5eW','pR5-HKoO5ogHpSI5DtCOUwN926wjvXZbMvXd7wcjUCTxU8MtcLS5HGu9mv9FgdsWzyve95M67dT_Eg','play',NULL,NULL),('서양사',NULL,NULL,'일반','mMeudNBZZR--H7RBDkLh49lJdhtvDCJkxp7cLDPcPi2z','zTJIi92CXFvWtYjCUjzqUs7W--AxSPmhBwzJaVipXJqZK4XBxri-9GgyV8fFLj6yTkWzpGR2JidknA','play',NULL,NULL),('서울냥냥펀치 히',NULL,NULL,'일반','qNz3UjtkgfFrcVeWrJRDBPsjcgCrFmZgzFa8TwbMm672','HlJZBrjNNhtkdrLfq3Z4iTfpawkQ9br-TN5H_GKPQcxr26umVf__J7zcUm_v1zPK3GrG9kArdQkMOg','play',NULL,NULL),('서폿이최약체',NULL,NULL,'일반','Wp3gACg8LlDKSCvDSEPvwkxBcyVGgKA7R5NHl1MmkuHJ','IhszN_CgWdsiPafZyW9y_GcaAdHAZrwOdlZJ9tTRRM9h1DhtFQi0NSY5mCgz_-ZIzhTnMUslX-IhqA','play',NULL,NULL),('식혜에 계란하나',NULL,NULL,'일반','KO8uYJpuNLiVKn9S45xfMLaTmxDEGcPjw2MNGuhIA_8f','eqxufJtBql2BnpEopPYlTzJYA78QQiH4cfuFZP4RYfLqkGmNbahvH3oo4pn3Fqn5213fFNRl6iJzhw','play',NULL,NULL),('알찡이',NULL,NULL,'일반','hTnVcx7LI08lyCgUOglFcyxSW687zXRPzfvGSbjSz1ap','7ly3-pxxJNjcQtZnmRi_eig1GNezx3SB_Qhug9R2zuEtVfD-tfr3X10PZw5J34a3-9OcxSd9LRDLgw','play',NULL,NULL),('여행떠나는바드찡','1',NULL,'일반','t9lxpM4d2T_57sLCWjkKCcm4-FmF39bNA_5PA0j1P6ih','1DRNz3SO24MAbKcbzwGwSR6kw5yyUuZNEElTeESlyXyDjHag5ouwAbrVhM1n-jr8rYChe2mW5Lc0Og','play',NULL,NULL),('연진',NULL,NULL,'일반','h0icIBMyku4LY7A2SAeIx1d-3UMZwxg_6px_uwv77qqT','UQqovwfwts89j9bOTIGv_nrZqfT5FTcm079s5KZzCdREpAscBQ4hL70lqYuy7giAUywscezU-H_lAA','play',NULL,NULL),('예소미',NULL,NULL,'일반','Y0U1HGDpkQtFgTIivA3n0ypg-HHbeQtWRPX1nt6lSL9_AnQ','fg0_Dii3F93VFwjMgNtkxWocuELXzwApUCyBwT6maAfFuPfJ1fKLXgfWYgyNc5yJvI-4RlK02GCXbQ','play',NULL,NULL),('우리이판진거같아',NULL,NULL,'일반','hMfkxG0z9EIRvi7wmorl4ya3w_qEga0QJy52hhyYasgm','UQjkdYmj8Y4nExHqJsI2hJP3phJP0ppRXbXvrBX7wIvV1LreUPj6XpQQL2WDHeFBscPAKWKGJJAclw','play',NULL,NULL),('정글님 빨리 갱점',NULL,NULL,'일반','6td9byUy7MjxK3eL1mw38Kw3OFM1ePVcpYRdXgjRQdB6ks8','948zb7wYh-cOYu85Alvn68F7uZecrY6S-Xq3LKX7qpv6jopziY53r-tzrGH8HRsy2BpmbRauAlLuIw','play',NULL,NULL),('첫 데뷔',NULL,NULL,'일반','BZn56cizQI5qrTlSLtUtn1Z7J884H7szz1RWcMwEPkzeENzNLq0gcbaL','cZzigiHf3BY18Ahcr58NAaBVzeGl7lk3qvk4ViN-42inV_3uJRctm7QX6Kk-GOcuEC6Tue8eRM_Uqw','play',NULL,NULL),('초코바닐라치약',NULL,NULL,'일반','2nkusbyYV8N-THgjZ0vXXTdxj7mgemsYKg-rzACs5-WN','7JUF39s5Mgb9oMSHWqiSxXRdjEEYwGjDgA1tlmLglCbWyNCtVVBIHE35hl4e3arL-Dyo7ZKWB9RS4Q','play',NULL,NULL),('최석',NULL,NULL,'일반','WHtlholU-1F8gqr9uK119m5II0WS0vsyZbCSchZVKAHm','jj43hChdlerXl6vZtwseCKPvspIzHp460JMSSyGfV4tPYiHqD-8HvrW5JuT-0b9hmrHqQaInX928QA','play',NULL,NULL),('커밋쇼',NULL,NULL,'일반','MVRgybP0mTUfXSsz6VbZ4XhLZAjUqUxaNwgtbhQRVVFc','lgDKLqa2snHoDrjhLCNuAaaoWgjoowUBKQv8yqZ48TmMnzI3RtkM0uS0frV8tzOPmjv24xGXbw8Dcw','play',NULL,NULL),('쿠아즈',NULL,NULL,'일반','mOq-hNrF8Zk-YrGGNaTNIKMRtiIOy2B80YhsvaGcXkbM','2TSSlFm6pU62VBC8t4GTF7yN6i3pR2AI75cXRYPeQnKUZAJeioyVFYUgXl8maxLfEeEZcbJ6xxY-Xw','play',NULL,NULL),('탑은항상억울해',NULL,NULL,'일반','THmaXIDjr0dSvzDcQrJItyqQVPg_Xl3Jzh4Z3cGnb7qX','JS0dJZRj6zTBZAi2uEX3MuzXlVnCVMBZVmfx5V7AaT_pXgGUXFFw0RISfCffvaAVC6h0hHjFIMKI3w','play',NULL,NULL),('턱썹이',NULL,NULL,'일반','XjsN87jLncRzMl5Ed8OMf28Op6rv_yI9mgHbiCJzOpo','I_U2Y62UnCto3yxeSmq1Orf-fxi4MpIOmAWvtp_DCtuCVtEYBoKSeNNz-1eRsNuud4w2Tw6bITNpTg','play',NULL,NULL),('한눈팔지않아',NULL,NULL,'일반','OJn1DZRHsiQQDInkflMmubpD2VOgebQLWQ4jAiO8h5mL','wZmYgh-hX2K_ifWXTgIGbCJlTqnTLIKLr3Jfvfqy2Wj5gv86OG8W5XdxXh2hzgopkDyZJbzvfcMJGA','play',NULL,NULL),('험네',NULL,NULL,'일반','Hgv30guO98T9tB09LV0jca1cQHc5wGDIqGbO9TYwabyw','Xdxq8bWsRP12iuLCHhkdMIG9WaiDkj_byzYMdZuF1ZKjT8TlZXIg2zALW4g8RnX-vK8GcBsoLvOvow','play',NULL,NULL);
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `board` (
  `code` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` longtext COLLATE utf8_unicode_ci,
  `writer` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'A','S','S','2019-12-30 03:30:22');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sss`
--

DROP TABLE IF EXISTS `sss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sss` (
  `i` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sss`
--

LOCK TABLES `sss` WRITE;
/*!40000 ALTER TABLE `sss` DISABLE KEYS */;
INSERT INTO `sss` VALUES (1),(233);
/*!40000 ALTER TABLE `sss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ssss`
--

DROP TABLE IF EXISTS `ssss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ssss` (
  `i` int(11) DEFAULT NULL,
  `a` int(11) DEFAULT NULL,
  `c` char(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ssss`
--

LOCK TABLES `ssss` WRITE;
/*!40000 ALTER TABLE `ssss` DISABLE KEYS */;
/*!40000 ALTER TABLE `ssss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `pw` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES ('id1','pw1','name1'),('id2','pw2','name2'),('id3','pw3','name3');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-20 18:01:58

-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: phr
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `dep_id` int(20) NOT NULL,
  `dep_name` varchar(10) DEFAULT NULL,
  `hid` int(11) DEFAULT NULL,
  PRIMARY KEY (`dep_id`),
  KEY `hid` (`hid`),
  CONSTRAINT `hid` FOREIGN KEY (`hid`) REFERENCES `hospital` (`hid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'胸外科',3),(2,'神经外科',4),(3,'泌尿外科',5),(4,'肝肠外科',6),(5,'妇产科',6),(6,'普外科',2),(7,'内分泌科',1),(8,'心血管内科',1),(9,'呼吸内科',6),(10,'肾内科',5),(11,'神经内科',4),(12,'血液内科',3),(13,'消化内科',2),(14,'口腔科',5),(15,'眼科',4),(16,'骨科',3),(17,'中医内科',2),(18,'儿科',1);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnose`
--

DROP TABLE IF EXISTS `diagnose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diagnose` (
  `person_id` varchar(20) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `doctor_id` int(20) NOT NULL DEFAULT '0',
  `diag_date` varchar(20) NOT NULL,
  PRIMARY KEY (`person_id`,`doctor_id`,`diag_date`),
  KEY `register_doctor` (`doctor_id`),
  KEY `diag_date` (`diag_date`),
  CONSTRAINT `register_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `register_person` FOREIGN KEY (`person_id`) REFERENCES `personinfo` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnose`
--

LOCK TABLES `diagnose` WRITE;
/*!40000 ALTER TABLE `diagnose` DISABLE KEYS */;
INSERT INTO `diagnose` VALUES ('1010',1,'Fri, 31 Jul 2015'),('94946797949',5,'Fri, 31 Jul 2015');
/*!40000 ALTER TABLE `diagnose` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor` (
  `doctor_id` int(20) NOT NULL,
  `doc_name` varchar(10) DEFAULT NULL,
  `dep_id` int(20) DEFAULT NULL,
  `doc_profile` varchar(40) DEFAULT NULL,
  `doc_phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`doctor_id`),
  KEY `dep_id` (`dep_id`),
  CONSTRAINT `dep_id` FOREIGN KEY (`dep_id`) REFERENCES `department` (`dep_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (1,'金正恩',18,'慢性鼻窦炎鼻息肉诊治、儿童人工耳蜗植入术、阻塞性睡眠呼吸暂停低通气综合研究','12345678909'),(2,'郭秀金',17,'眼底、擅长晶体-玻璃体-视网膜手术','16578928765'),(3,'曹鑫丹',16,'青光眼、白内障、眼内屈光手术、遗传性眼病','13456234567'),(4,'陈联梅',15,'血液科专家，在营养性贫血、恶性血液病如白血病','17688892765'),(5,'陆欣欣',14,'本人在国内率先开展白内障联合玻璃体手术','15672898765'),(6,'孙华',13,'从事眼眶及眼肿瘤专业14年','13678789097'),(7,'田洁玲',12,'女，1960年8月生，医学博士','15682909876'),(8,'顾良龙',11,'主任医师，硕士研究生导师','17898765625'),(9,'何艳',10,'博士研究生导师，教授。中华医学会专科会员','18692893098'),(10,'邓建明',9,'北京市眼科研究所工作，研究员','18329098397'),(11,'赵美珍',8,'共发表论文80多篇，一作者及通讯作者30多篇','15289209876'),(12,'马倍加',1,'具有丰富的临床诊治经验','13278765435'),(13,'任曦',7,'对各种整形疾病的治疗方法','15672890398'),(14,'唐亚胜',6,'擅长头颈肿瘤的手术治疗','13728909876'),(15,'蒋浩化',5,'专业方向为耳显微外科','18902987635'),(16,'韩松',4,'从事眼科临床工作多年','18872639804'),(17,'夏潇琦',3,'毕业于第一军医大学','15627830987'),(18,'丁聪华',2,'中国医院协会血液净化管理分会委员','15236728390');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment` (
  `equip_id` int(20) NOT NULL,
  `equip_name` varchar(20) DEFAULT NULL,
  `equip_price` float(20,0) DEFAULT NULL,
  PRIMARY KEY (`equip_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (1,'血红蛋白分析仪',4000),(2,'血压计',325),(3,'血糖仪',500),(4,'聚焦超声治疗器',1000),(5,'激光治疗仪',20000),(6,'红宝石激光治疗机',10000),(7,'胰岛素泵用储液器',10000),(8,'塑料血袋',10),(9,'医用中心供氧系统',100000);
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friends` (
  `person_id` varchar(20) NOT NULL,
  `person_id2` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`person_id`),
  KEY `friend2` (`person_id2`),
  CONSTRAINT `friend1` FOREIGN KEY (`person_id`) REFERENCES `personinfo` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `friend2` FOREIGN KEY (`person_id2`) REFERENCES `personinfo` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospital`
--

DROP TABLE IF EXISTS `hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hospital` (
  `hid` int(11) NOT NULL,
  `hname` varchar(30) DEFAULT NULL,
  `haddress` varchar(30) DEFAULT NULL,
  `hprofile` varchar(400) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospital`
--

LOCK TABLES `hospital` WRITE;
/*!40000 ALTER TABLE `hospital` DISABLE KEYS */;
INSERT INTO `hospital` VALUES (1,'北京地坛医院','北京市朝阳区京顺东街8号','北京地坛医院始建于1946年，原名北京第一传染病医院，1989年更名为北京地坛医院。占地面积3.4万平方米，建筑面积2.9万平方米，设有床位500张。是隶属于北京市卫生局的三级甲等医院，北京市医疗保险定点单位。'),(2,'首都医科大学附属北京妇产医院','北京市朝阳区姚家园路251号','首都医科大学附属北京妇产医院创建于1959年6月，其前身是直属中央卫生部的北京妇幼保健实验院。经过50年的建设和发展，目前已发展成为集医疗、保健、教学、科研为一体，以诊治妇产科常见病、多发病和疑难病症为重点的国内知名的三级甲等妇产专科医院。'),(3,'四川省第二中医医院','成都市青羊区四道街20号','四川省第二中医医院（四川省中医药科学院中医研究所/针灸经络研究所）位于成都市青羊区四道街20号，建立于1979年，是集中医医疗、科研为一体的省级医疗单位，国家三级甲等中医医院，我省唯一一所省级精品中医医院建设单位。'),(4,'成都中山医院','成都文庙前街42号','成都中山医院(四川省邮电医院)坐落于市中心文庙前街与南大街交汇处。由留德医学博士张国元先生于1952年创建，成都中山医院是一所集临床、科研、预防、教学、康复为一体的国家二级、大型现代化综合性花园医院。'),(5,'成都第三人民医院','成都市青龙街82号','成都市第三人民医院是成都市人民政府举办的国营非营利性医疗机构，是一所集医疗、科研、教学、预防、保健、康复为一体的市属重点综合性三级甲等医院、国家级爱婴医院。'),(6,'四川省第三人民医院','成都市青羊区望仙村3号','四川省第三人民医院是成都市卫生局所属的一所综合性三级甲等医院，创建于1941年。拥有医护人员1278人，其中主任、副主任医师110人，中级人员226人。医院曾获省“文明医院”、市“先进单位”、“优秀服务示范单位”等称号。');
/*!40000 ALTER TABLE `hospital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newsinfo`
--

DROP TABLE IF EXISTS `newsinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `newsinfo` (
  `news_type` varchar(60) DEFAULT NULL,
  `news_title` varchar(60) DEFAULT NULL,
  `news_content` varchar(3000) DEFAULT NULL,
  `news_id` int(11) NOT NULL,
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newsinfo`
--

LOCK TABLES `newsinfo` WRITE;
/*!40000 ALTER TABLE `newsinfo` DISABLE KEYS */;
INSERT INTO `newsinfo` VALUES ('health_news','红肉伤心脏，肉碱是元凶','“吃太多红肉会影响心脏健康”，这个观点毋庸置疑，之前的解释也十分明确，即红肉中含有大量饱和脂肪，这会增加血液中胆固醇含量',1),('health_news','美国心理学家研究发现：抑郁情绪能传染','心理学家对103对同宿舍学生进行追踪调查发现，与心理脆弱、情绪低沉的人共处，室友容易被其消极情绪影响，也陷入抑郁。',2),('health_news','遵守五件事，做对好夫妻','结婚时间久了，生活难免变得平淡乏味，婚姻关系甚至也会受到威胁。',3),('health_news','做好四项基础运动更加健康','慢跑是当今世界上最流行的有氧代谢运动方法，对保持良好的心脏功能，防止心脏功能衰退，预防肌肉萎缩，防治冠心病、高血压。',4),('health_news','进行有规律的运动让你远离疾病','一项针对40多份研究报告的回顾发现，进行有规律的运动不仅能够降低肥胖的概率，还能减少发生许多生理、心理健康问题的风险。',5),('health_news','专家提醒：长期穿高跟鞋会致女人性冷淡','大家都知道，长时间穿高跟鞋会影响足弓乃至脊柱；但很多女性都没有想到，高跟鞋还会影响她们的性器官，从而在一定程度上降低性欲。',6),('drug_news','保儿宁颗粒（黄金贝乐）','开水冲服。三岁以下，一次半袋：三岁以上，一次一袋；一日二次，益气固表，健中醒脾。用于脾肺气虚所致的神倦纳呆。',7),('drug_news','感冒清热颗粒','疏风散寒，解表清热。用于风寒感冒，头痛发热，恶寒身痛，鼻流清涕，咳嗽咽干。',8),('drug_news','桑菊感冒丸（浓缩丸）','疏风清热，宣肺止咳。用于风热感冒初起，头痛，咳嗽，口干，咽痛。',9),('drug_news','妇炎康软胶囊（佳泰)','活血化瘀，软坚散结，清热解毒，消炎止痛。用于慢性附件炎、盆腔炎、阴道炎、膀胱炎、慢性阑尾炎、尿路感染。',11),('drug_news','脑络通胶囊','补气活血，通经活络。扩张血管，增加脑血流量作用，用于脑血栓，脑动脉硬化。',12),('disease_news','高血压','在未用抗高血压药情况下，收缩压139mmHg和/或舒张压89mmHg，按血压水平将高血压分为1，2，3级。',14),('disease_news','前列腺炎','列腺炎是指前列腺特异性和非特异感染所致的急慢性炎症，从而引起的全身或局部症状。前列腺炎。',15),('disease_news','月经不调','月经失调也称月经不调，妇科常见病 。表现为月经周期或出血量的异常，或是月经前、经期时的腹痛及全身症状。',16),('disease_news','糖尿病','糖尿病是由遗传和环境因素相互作用而引起的常见病，临床以高血糖为主要标志，常见症状有多饮、多尿、多食以及消瘦等。',17),('disease_news','子宫肌瘤','子宫肌瘤，又称子宫平滑肌瘤，是女性生殖器最常见的一种良性肿瘤。',18),('immune_news','百白破疫苗','百白破疫苗是将百日咳菌苗、白喉类毒素及破伤风类毒素混合制成，可以同时预防百日咳、白喉和破伤风。接种对象是3个月以上宝宝。',19),('immune_news','脊髓灰质炎疫苗','脊髓灰质炎疫苗，简称脊灰糖丸，是一种减毒活疫苗，它是白色颗粒状糖丸。宝宝出生后按计划服用糖丸，可有效地预防脊髓灰质炎',20),('immune_news','卡介苗','接种卡介苗，可以增强宝宝对于结核病的抵抗力，预防严重结核病和结核性脑膜炎的发生。目前我国采用的是减毒活疫苗，安全有效。宝',21),('immune_news','麻疹疫苗','麻疹疫苗是一种减毒活疫苗，接种反应小，抗体产生快，免疫持久性好。6个月以内宝宝由于有从母体获得的抗体，一般不会得麻疹。所',22),('immune_news','乙肝疫苗','乙型肝炎在我国的发病率很高，慢性活动性乙型肝炎还是造成肝癌、肝硬化的主要原因，让宝宝接种乙肝疫苗是非常必要的。我国于20',23),('news_news','精准医疗有望普惠百姓健康','精准医疗有望普惠百姓健康',24),('news_news','沈女职工流产生育医疗费调整','待遇划分点由怀孕3个月变为4个月 生育津贴同时调整，最多少得7302元\r\n\r\n本报讯 （华商晨报 掌中沈阳客户端记者 金恩子）沈阳市女职工流产生育医疗费待遇调整，主要影响怀孕3个月以上不满4个月参保人，将少得300元。\r\n\r\n昨日，记者从沈阳市社会医疗保险管理局获悉，从2015年3月1日起，沈阳市调整城镇女职工流产的生育医疗费，不同待遇的时间划分点由此前的怀孕3个月调整为4个月。同时，各月份参保人流产、引产生育津贴待遇也随之调整，最多少得7302元以上。',25),('news_news','海南推行乡镇医院\"限费医疗\"','经济日报讯 记者何 伟报道：海南经过1年多改革试点，将全面实施乡镇级医院住院“限费医疗”改革，参合农民只要交100多元，其余在乡镇医院住院治疗的费用可全部报销。\r\n\r\n　　海南省卫计委与海南省农村合作医疗协调小组办公室联合下发《关于在全省实施新型农村合作医疗乡镇级医院住院“限费医疗”改革的通知》，在乡镇级医院住院推行“限费医疗”的付费模式，在全面实行基本药物制度的条件下，参合农民在县域内的乡镇级医院住院，缴纳规定的起付线后，其基本医疗费用予以全额报销，即起付线外全报销的免费模式。',26),('news_news','解放军第85医院专家医疗队赴老区','中新网南昌7月29日电 (常乐、孙燕、陆开)“血压控制得很好，老爷子您身体棒着呢。”听医生这么说，98岁的老红军林昌松高兴地对解放军第85医院周宏宇院长说：“你们大上海的专家每半年上门为我体检，我的身体肯定好。”从7月27日起，解放军第85医院的16人专家医疗队再次前往江西赣南革命老区，持续开展“送医扶医老区行”活动，把温暖送到238名老红军、“五老”人员，1330名革命烈属和老区群众中。\r\n\r\n一次老区行，一生老区情，第85医院的专家已经与老区结下了浓浓的深情。自开展“送医扶医老区行”活动以来，已经有60余名专家分四批，赴福建长汀、江西赣南等革命老区开展送医送药活动。这次巡诊由周宏宇院长带队，抽组了心内科、消化科、神经内分泌科、普外科、骨科、妇产科、五官科、烧伤整形科、泌尿外科、特诊科等科室的精兵强将。',27),('news_news','日本医疗游吸引中国人 日媒：买健康大有人在','参考消息网7月29日报道 外媒称，不仅是到日本购物，从中国前往日本医院进行医疗旅游的人似乎也在逐渐增加。\r\n　　据日本经济新闻网站7月28日报道，在日本，虽然中国游客的“爆买”正受到关注，但为获得健康诊断而特意来到日本医疗机构的富裕阶层也大有人在。\r\n　　报道称，居住在北京、生活富裕的一对50多岁夫妇表示，“在日本一家可靠的医院进行了全面检查，得知没有任何毛病，终于放心了。然后安安心心回到了北京。真希望再多呆2、3天，去泡泡日本的温泉”。\r\n　　如果查看日本旅游公司的主页，可以发现很多医疗旅游的介绍。\r\n　　例如“PET／CT检查与东京节假日4日游”。其内容是，第1天抵达成田机场（或者羽田机场）。由中国导游迎接，乘坐专车前往酒店。医疗协调员来到酒店，介绍检查流程等，然后吃晚餐。第2日，上午前往医院。进行PET和CT检查、头部MRI、肿瘤标志物、血液检查以及大脑检查。医疗翻译将在语言方面提供支持。下午游览东京都内（游览浅草寺和银座）。第3日为自由活动时间。第4日，由中文导游用专车送游客至机场，然后回国。',28);
/*!40000 ALTER TABLE `newsinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_health`
--

DROP TABLE IF EXISTS `person_health`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_health` (
  `prompt_date` varchar(20) DEFAULT NULL,
  `drug_name` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `drug_dose` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `diag_date` varchar(20) CHARACTER SET utf8 NOT NULL,
  `person_id` varchar(20) NOT NULL,
  PRIMARY KEY (`person_id`,`diag_date`),
  KEY `fk1` (`diag_date`),
  CONSTRAINT `fk1` FOREIGN KEY (`diag_date`) REFERENCES `diagnose` (`diag_date`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `health_id` FOREIGN KEY (`person_id`) REFERENCES `personinfo` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_health`
--

LOCK TABLES `person_health` WRITE;
/*!40000 ALTER TABLE `person_health` DISABLE KEYS */;
/*!40000 ALTER TABLE `person_health` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personinfo`
--

DROP TABLE IF EXISTS `personinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personinfo` (
  `person_id` varchar(20) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `gender` int(5) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `vip` int(1) DEFAULT NULL,
  `bloodtype` varchar(4) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personinfo`
--

LOCK TABLES `personinfo` WRITE;
/*!40000 ALTER TABLE `personinfo` DISABLE KEYS */;
INSERT INTO `personinfo` VALUES ('1001','王道策',1,20,'15629383987',0,'O','wdc',1),('1002','王爸爸',1,45,'18372639472',1,'A','wbb',2),('1003','王业蝶',1,25,'17682739863',1,'B','wyd',3),('1004','王野构',0,30,'16762783787',0,'AB','wyg',4),('1005','韩如丽',0,24,'19802989876',1,'O','111',5),('1006','方颖琦',0,23,'78765432789',0,'O','111',6),('1007','王忠凯',1,22,'18273987654',1,'AB','111',7),('1008','万妙',0,21,'34567890122',1,'B','111',8),('1009','钱行',1,20,'12345678901',1,'A','111',9),('1010','wdc',1,20,'111111',1,'A','111',1),('94946797949','hgt',0,18,'13469799',1,'O','123',5);
/*!40000 ALTER TABLE `personinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-31  1:50:13

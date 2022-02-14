LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (3,1,'Admin','Admin','',NULL),(4,2,'Pavel','Chips','+375250001122',NULL),(5,3,'Miroslaw','Four','+375339988774','874459'),(7,10,'Bianka','Bun','+375297450102',NULL),(8,14,'Pavel','Kim','+375298422020',NULL),(11,17,'Sergio','Malov','+375297899955',NULL),(12,18,'Mark','Valov','+375338420880','121459'),(13,20,'Max','Valov','+375338989678',NULL),(14,21,'Tanya','Polyichik','+375441234566',''),(15,22,'Braun','Noim','+375440009678',NULL),(16,23,'Austen','Dean','+375448427850',NULL),(17,24,'Sandra','Owen','+375447845128',NULL),(18,25,'Mary','Walton','+375448989601',NULL),(19,26,'eqwe','weqeqw','+375448989678',NULL),(20,27,'Sofya','tretr','+375298420880',NULL),(21,28,'Artem','Rulo','+375251230044','787840'),(26,30,'Sonya','Koit','+375448569800',NULL),(27,31,'Misha','Collins','+375250565589',NULL),(30,34,'Sergey','Kvetka','+375449541200','546669'),(32,36,'Diana','Pashkel','+375448989898','555666'),(33,39,'Alex','Lavkraft','+375331445788','050547');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (2,2,'Neil Gaiman','Coraline','HarperCollins Publishers',2016,193,5),(3,2,'Joanne Rowling','Harry Potter and the Sorcerer\'s Stone','Scholastic, Inc.',2016,432,9),(4,4,'Mikhail Bulgakov','The Master and Margarita','Penguin Publishing Group',2012,480,12),(5,3,'Jack London','The Call of the Wild','Barnes & Noble',2017,21,7),(6,5,'Louann Brizendine','The Male Brain','Potter/Ten Speed/Harmony/Rodale',2011,271,3),(7,6,'Sandra Boynton','Moo, Baa, la la La!','Little Simon',2015,13,2),(8,2,'Dan Brown','Origin','Knopf Doubleday Publishing Group',2018,456,5),(9,2,'Cixin Liu','The Three-Body Problem','Tom Doherty Associates',2017,378,0),(11,4,'Katee Robert','Neon Gods','Sourcebooks',2021,384,3),(12,1,'Elizabeth George','Something to Hide: A Lynley Novel','Viking ',2022,701,4),(13,7,'Samuel Nix','Ensure Educational Success','SNIX Consulting',2021,193,3),(16,1,'Manly Wade Wellman','The Further Adventures of Sherlock Holmes','Titan Books',2010,258,5),(17,1,'Agatha Christie','The Murder of Roger Ackroyd','William Morrow Paperbacks',2009,256,7),(18,3,'Lee Child','Better Off Dead','Delacorte Press',2021,322,2),(19,3,'Dean Koontz','Quicksilver','Thomas & Mercer',2020,365,1),(20,6,'Shel Silverstein','The Giving Tree','HarperCollins',2014,66,4),(21,7,'Jasmeen Kaur','Data Analysis & Dashboards','Titan Books',2021,290,3);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (1,'detective'),(2,'fantastic'),(3,'adventure'),(4,'novel'),(5,'scientific'),(6,'children'),(7,'educational');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (3,2,4,3,'2019-01-10 00:00:00','2019-02-01 00:00:00',1),(5,3,21,8,'2020-10-01 00:00:00',NULL,0),(6,2,21,2,'2022-01-28 00:00:00',NULL,0),(34,4,21,4,'2022-02-02 00:00:00','2022-02-12 00:00:00',0),(35,3,21,2,'2022-02-05 00:00:00',NULL,0),(36,4,21,2,'2022-02-05 00:00:00','2022-02-08 00:00:00',0),(37,4,21,2,'2022-02-05 00:00:00','2022-02-12 00:00:00',0),(38,4,21,3,'2022-02-05 00:00:00','2022-02-08 00:00:00',1),(39,4,30,3,'2022-02-11 00:00:00','2022-02-12 00:00:00',1),(77,4,33,21,'2022-02-14 00:00:00','2022-02-14 00:00:00',1),(78,1,33,13,'2022-02-14 00:00:00',NULL,1),(79,2,14,20,'2022-02-14 00:00:00',NULL,0),(80,2,14,4,'2022-02-14 00:00:00',NULL,0),(81,1,16,9,'2022-02-14 00:00:00',NULL,0),(82,1,21,3,'2022-02-14 00:00:00',NULL,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `orders_status` WRITE;
/*!40000 ALTER TABLE `orders_status` DISABLE KEYS */;
INSERT INTO `orders_status` VALUES (1,'waiting'),(2,'active'),(3,'canceled'),(4,'completed');
/*!40000 ALTER TABLE `orders_status` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'admin'),(2,'user'),(3,'guest');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin',1),(2,'fanta23','sprite23',2),(3,'dragons00','redMoll10',2),(10,'fggfg','fggfg',2),(14,'pav90','pavlush',2),(17,'sermav','mavlovin',2),(18,'valov0','valmark',2),(20,'maxval','maxval',2),(21,'tanusha','tank12',2),(22,'brownty','nonono',2),(23,'diedInside','outside',2),(24,'ovenki78','ovov',2),(25,'WALTONNN','WALTONNN',2),(26,'weqwe','4545',2),(27,'werwe','5678',2),(28,'art90','art90',2),(30,'sKoit','koit',2),(31,'collin','mishka',2),(34,'color45','kvetkaColor',2),(36,'PASH_DI','pashka12',2),(39,'LOVe','sergey87',2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

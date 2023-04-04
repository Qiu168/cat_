/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 8.0.17 : Database - cat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cat` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `cat`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`password`) values (1,'123456','9ce2e161d07b02b86b8abd77d08d9886'),(2,'10180809','65a383940e1c44027e8c21480b9137a9'),(3,'55718816','1b1718d0cb72fbf87ff7cc5de9e627ad');

/*Table structure for table `authority` */

DROP TABLE IF EXISTS `authority`;

CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_charge` tinyint(1) DEFAULT NULL,
  `is_park` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `authority` */

insert  into `authority`(`id`,`is_charge`,`is_park`) values (1,1,1),(2,1,0),(3,0,1),(4,0,0);

/*Table structure for table `chargingpile` */

DROP TABLE IF EXISTS `chargingpile`;

CREATE TABLE `chargingpile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `station_id` int(11) DEFAULT NULL,
  `six_seven` int(11) NOT NULL DEFAULT '0',
  `seven_eight` int(11) NOT NULL DEFAULT '0',
  `eight_nine` int(11) NOT NULL DEFAULT '0',
  `nine_ten` int(11) NOT NULL DEFAULT '0',
  `ten_eleven` int(11) NOT NULL DEFAULT '0',
  `eleven_twelve` int(11) NOT NULL DEFAULT '0',
  `twelve_thirteen` int(11) NOT NULL DEFAULT '0',
  `thirteen_fourteen` int(11) NOT NULL DEFAULT '0',
  `fourteen_fifteen` int(11) NOT NULL DEFAULT '0',
  `fifteen_sixteen` int(11) NOT NULL DEFAULT '0',
  `sixteen_seventeen` int(11) NOT NULL DEFAULT '0',
  `seventeen_eighteen` int(11) NOT NULL DEFAULT '0',
  `eighteen_nineteen` int(11) NOT NULL DEFAULT '0',
  `nineteen_twenty` int(11) NOT NULL DEFAULT '0',
  `twenty_twenty_one` int(11) NOT NULL DEFAULT '0',
  `t_one_two` int(11) NOT NULL DEFAULT '0',
  `t_two_three` int(11) NOT NULL DEFAULT '0',
  `t_three_four` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `chargingpile` */

insert  into `chargingpile`(`id`,`state`,`station_id`,`six_seven`,`seven_eight`,`eight_nine`,`nine_ten`,`ten_eleven`,`eleven_twelve`,`twelve_thirteen`,`thirteen_fourteen`,`fourteen_fifteen`,`fifteen_sixteen`,`sixteen_seventeen`,`seventeen_eighteen`,`eighteen_nineteen`,`nineteen_twenty`,`twenty_twenty_one`,`t_one_two`,`t_two_three`,`t_three_four`) values (1,1,1,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,0,0),(2,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);

/*Table structure for table `chargingstation` */

DROP TABLE IF EXISTS `chargingstation`;

CREATE TABLE `chargingstation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(30) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `open` int(2) DEFAULT NULL,
  `close` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `chargingstation` */

insert  into `chargingstation`(`id`,`location`,`name`,`open`,`close`) values (1,'东区1饭','东区1饭充电站',7,23),(2,'东区2饭','东区2饭充电站',7,22),(3,'西区3饭','西区3饭充电站',6,24),(4,'西区4饭','西区4饭充电站',6,22);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pile_id` int(11) DEFAULT NULL,
  `content` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `comment` */

insert  into `comment`(`id`,`pile_id`,`content`) values (1,1,'123789 : 确实挺不错的');

/*Table structure for table `parkinglot` */

DROP TABLE IF EXISTS `parkinglot`;

CREATE TABLE `parkinglot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(30) DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `parkinglot` */

insert  into `parkinglot`(`id`,`location`,`name`) values (1,'西一','西一停车场'),(2,'东区二饭','二饭停车场'),(3,'1','test');

/*Table structure for table `parkingspot` */

DROP TABLE IF EXISTS `parkingspot`;

CREATE TABLE `parkingspot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location_id` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `parkingspot` */

insert  into `parkingspot`(`id`,`location_id`,`state`) values (1,1,3),(2,2,0);

/*Table structure for table `pile_order` */

DROP TABLE IF EXISTS `pile_order`;

CREATE TABLE `pile_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `pile_id` int(11) DEFAULT NULL,
  `start_time` date DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `pile_order` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_number` varchar(20) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `electromobile_model` varchar(50) DEFAULT NULL,
  `electromobile_number` varchar(20) DEFAULT NULL,
  `state` int(1) DEFAULT NULL,
  `authority_id` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_number` (`student_number`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users` */

insert  into `users`(`id`,`student_number`,`name`,`username`,`password`,`electromobile_model`,`electromobile_number`,`state`,`authority_id`) values (2,'123456','njsa','123456','9ce2e161d07b02b86b8abd77d08d9886','雅迪','adjks',0,1),(3,'123789','黄泰淇','123789','3ad798bb3ceb6a9939f23fb77ae3d6e0','123456','123456',1,4),(4,'456789','456','456789','1683ade0ec619faa6d9cb0944aff29cf','456','456',0,1),(5,'23','aaa','12348645','9ce2e161d07b02b86b8abd77d08d9886','132','123',0,1),(6,'1523465','小明','123789456','f7b6ea41a0e3c87581d9931dbcbd016b','21213','2313',0,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

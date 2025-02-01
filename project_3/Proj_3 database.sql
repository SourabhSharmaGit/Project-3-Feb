/*
SQLyog Ultimate v9.02 
MySQL - 5.0.24-community-nt : Database - project3
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`project3` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `project3`;

/*Table structure for table `st_college` */

DROP TABLE IF EXISTS `st_college`;

CREATE TABLE `st_college` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) default NULL,
  `ADDRESS` varchar(255) default NULL,
  `CITY` varchar(255) default NULL,
  `STATE` varchar(255) default NULL,
  `PHONE_NO` varchar(255) default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `MODIFIED_BY` varchar(255) default NULL,
  `CREATED_DATETIME` datetime default NULL,
  `MODIFIED_DATETIME` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_college` */

insert  into `st_college`(`ID`,`NAME`,`ADDRESS`,`CITY`,`STATE`,`PHONE_NO`,`CREATED_BY`,`MODIFIED_BY`,`CREATED_DATETIME`,`MODIFIED_DATETIME`) values (1,'DAVV','Madhumilan indore','Indore','MP','9889787667','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:21:28','2022-05-19 12:21:28'),(2,'Mahatma Gandhi','Vijay nagar indore','Indore','MP','9878676676','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:22:03','2022-05-19 12:22:03'),(3,'Rajiv Gandhi ','Bhopal','Indore','MP','9978545655','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:22:37','2022-05-19 12:22:37'),(4,'Barkatullah University','Bhopal','Bhopal','MP','6745576666','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-02 23:16:49','2022-06-02 23:16:49');

/*Table structure for table `st_course` */

DROP TABLE IF EXISTS `st_course`;

CREATE TABLE `st_course` (
  `ID` bigint(20) NOT NULL,
  `COURSE_NAME` varchar(255) default NULL,
  `DURATION` varchar(255) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `MODIFIED_BY` varchar(255) default NULL,
  `CREATED_DATETIME` datetime default NULL,
  `MODIFIED_DATETIME` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_course` */

insert  into `st_course`(`ID`,`COURSE_NAME`,`DURATION`,`DESCRIPTION`,`CREATED_BY`,`MODIFIED_BY`,`CREATED_DATETIME`,`MODIFIED_DATETIME`) values (1,'BCA','3 Year','Bachelor of Computer Application','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:09:36','2022-05-19 12:09:36'),(3,'BCOM','3 Year','Bachelor of Commerce','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:11:07','2022-05-19 12:11:07'),(4,'MBA','2 Year','Master of Business Administrator','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:12:03','2022-05-19 12:12:03'),(5,'MCA','2 Year','Master of Computer Application','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:12:49','2022-05-19 12:12:49'),(6,'BBA','3 Year','Bachelor of Business Administration','vipingupta59@hotmail.com','vipingupta59@hotmail.com','2022-06-03 22:45:53','2022-06-03 22:45:53');

/*Table structure for table `st_faculty` */

DROP TABLE IF EXISTS `st_faculty`;

CREATE TABLE `st_faculty` (
  `ID` bigint(20) NOT NULL,
  `FIRST_NAME` varchar(255) default NULL,
  `LAST_NAME` varchar(255) default NULL,
  `QUALIFICATION` varchar(255) default NULL,
  `COLLEGE_NAME` varchar(255) default NULL,
  `COURSE_NAME` varchar(255) default NULL,
  `GENDER` varchar(255) default NULL,
  `DOB` datetime default NULL,
  `COLLEGEID` bigint(20) default NULL,
  `EMAILID` varchar(255) default NULL,
  `MOBILENO` varchar(255) default NULL,
  `COURSEID` bigint(20) default NULL,
  `SUBJECTID` bigint(20) default NULL,
  `SUBJECTNAME` varchar(255) default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `MODIFIED_BY` varchar(255) default NULL,
  `CREATED_DATETIME` datetime default NULL,
  `MODIFIED_DATETIME` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_faculty` */

insert  into `st_faculty`(`ID`,`FIRST_NAME`,`LAST_NAME`,`QUALIFICATION`,`COLLEGE_NAME`,`COURSE_NAME`,`GENDER`,`DOB`,`COLLEGEID`,`EMAILID`,`MOBILENO`,`COURSEID`,`SUBJECTID`,`SUBJECTNAME`,`CREATED_BY`,`MODIFIED_BY`,`CREATED_DATETIME`,`MODIFIED_DATETIME`) values (2,'Ravi','Narayan','MCA','DAVV','MCA','Male','2020-09-06 00:00:00',1,'ravi@gmail.com','7867788765',5,7,'Java','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-02 23:19:21','2022-06-02 23:19:21'),(3,'Rohit','Kumar','MBA','Mahatma Gandhi','BBA','Male','2019-02-06 00:00:00',2,'rohit@gmail.com','9978644672',2,6,'Marketing','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-02 23:20:37','2022-06-02 23:20:37'),(5,'Samarth','Singh','MBA','Rajiv Gandhi ','MBA','Male','2010-09-06 00:00:00',3,'samarth@gmail.com','6674666737',4,6,'Marketing','vipingupta59@hotmail.com','vipingupta59@hotmail.com','2022-06-03 21:11:15','2022-06-03 21:11:15'),(6,'Ritesh','Gupta','MCOM','Mahatma Gandhi','BCOM','Male','2016-09-06 00:00:00',2,'ritesh@gmail.com','7889988777',3,4,'Costing','vipingupta59@hotmail.com','vipingupta59@hotmail.com','2022-06-03 21:12:08','2022-06-03 21:12:08'),(8,'Anand','Narayan','MCOM','Barkatullah University','BCOM','Male','2013-02-06 00:00:00',4,'anand@gmail.com','7867567476',3,3,'Accounts','vipingupta59@hotmail.com','vipingupta59@hotmail.com','2022-06-03 21:19:44','2022-06-03 21:19:44');

/*Table structure for table `st_marksheet` */

DROP TABLE IF EXISTS `st_marksheet`;

CREATE TABLE `st_marksheet` (
  `ID` bigint(20) NOT NULL,
  `ROLL_NO` varchar(255) default NULL,
  `STUDENT_ID` bigint(20) default NULL,
  `CHEMISTRY` int(11) default NULL,
  `MATHS` int(11) default NULL,
  `PHYSICS` int(11) default NULL,
  `NAME` varchar(255) default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `MODIFIED_BY` varchar(255) default NULL,
  `CREATED_DATETIME` datetime default NULL,
  `MODIFIED_DATETIME` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_marksheet` */

insert  into `st_marksheet`(`ID`,`ROLL_NO`,`STUDENT_ID`,`CHEMISTRY`,`MATHS`,`PHYSICS`,`NAME`,`CREATED_BY`,`MODIFIED_BY`,`CREATED_DATETIME`,`MODIFIED_DATETIME`) values (1,'AB101',3,55,77,67,'Aman Tamrakar','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 14:42:56','2022-05-19 14:42:56'),(2,'AB102',6,56,30,55,'Kamal Singh','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-02 23:04:48','2022-06-02 23:04:48'),(3,'AB103',1,88,89,88,'Kanish Jain','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-02 23:02:29','2022-06-02 23:02:29'),(4,'AB104',4,77,87,67,'Naksh Sharma','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 14:44:11','2022-05-19 14:44:11'),(5,'AB105',2,88,89,98,'Ritesh Gupta','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 14:44:35','2022-05-19 14:44:35'),(6,'AB106',5,30,67,33,'Vineet Chawla','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 14:45:02','2022-05-19 14:45:02'),(7,'AB107',8,67,89,87,'Paras Soni','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-13 13:35:26','2022-06-13 13:35:26');

/*Table structure for table `st_role` */

DROP TABLE IF EXISTS `st_role`;

CREATE TABLE `st_role` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `MODIFIED_BY` varchar(255) default NULL,
  `CREATED_DATETIME` datetime default NULL,
  `MODIFIED_DATETIME` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_role` */

insert  into `st_role`(`ID`,`NAME`,`DESCRIPTION`,`CREATED_BY`,`MODIFIED_BY`,`CREATED_DATETIME`,`MODIFIED_DATETIME`) values (1,'Admin','Admin','Gupta.vipin02@gmail.com','Gupta.vipin02@gmail.com','2022-05-18 15:06:44','2022-05-18 15:06:44'),(2,'Student','Student','Gupta.vipin02@gmail.com','Gupta.vipin02@gmail.com','2022-05-18 15:06:44','2022-05-18 15:06:44'),(3,'College_School','College_School','Gupta.vipin02@gmail.com','Gupta.vipin02@gmail.com','2022-05-18 15:06:44','2022-05-18 15:06:44'),(4,'Kiosk','Kiosk','Gupta.vipin02@gmail.com','Gupta.vipin02@gmail.com','2022-05-18 15:06:44','2022-05-18 15:06:44');

/*Table structure for table `st_student` */

DROP TABLE IF EXISTS `st_student`;

CREATE TABLE `st_student` (
  `ID` bigint(20) NOT NULL,
  `FIRST_NAME` varchar(255) default NULL,
  `LAST_NAME` varchar(255) default NULL,
  `COLLEGE_NAME` varchar(255) default NULL,
  `Date_of_Birth` datetime default NULL,
  `COLLEGE_ID` bigint(20) default NULL,
  `Email` varchar(255) default NULL,
  `MOBILE_NO` varchar(255) default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `MODIFIED_BY` varchar(255) default NULL,
  `CREATED_DATETIME` datetime default NULL,
  `MODIFIED_DATETIME` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_student` */

insert  into `st_student`(`ID`,`FIRST_NAME`,`LAST_NAME`,`COLLEGE_NAME`,`Date_of_Birth`,`COLLEGE_ID`,`Email`,`MOBILE_NO`,`CREATED_BY`,`MODIFIED_BY`,`CREATED_DATETIME`,`MODIFIED_DATETIME`) values (1,'Kanish','Jain','Barkatullah University','1993-11-05 00:00:00',5,'kanish@gmail.com','6676765566','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:24:38','2022-05-19 12:24:38'),(2,'Ritesh','Gupta','DAVV','1994-08-08 00:00:00',1,'ritesh@gmail.com','6656766556','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:25:14','2022-05-19 12:25:14'),(3,'Aman','Tamrakar','Mahatma Gandhi','1994-09-09 00:00:00',2,'aman@gmail.com','7767644736','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:25:56','2022-05-19 12:25:56'),(4,'Naksh','Sharma','Rajiv Gandhi ','2000-01-11 00:00:00',3,'naksha@gmail.com','6766566566','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:26:39','2022-05-19 12:26:39'),(5,'Vineet','Chawla','Technocrats Institute','1985-06-03 00:00:00',4,'vineet@gmail.com','6656879879','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:27:42','2022-05-19 12:27:42'),(6,'Kamal','Kumar','DAVV','1995-01-06 00:00:00',1,'kamal@gmail.com','7888599477','vipingupta59@hotmail.com','vipingupta59@hotmail.com','2022-06-03 22:44:28','2022-06-03 22:44:28'),(7,'Vivek','Kumar','Barkatullah University','1999-01-06 00:00:00',4,'vivek@gmail.com','8899887776','vipingupta59@hotmail.com','vipingupta59@hotmail.com','2022-06-03 22:47:57','2022-06-03 22:47:57'),(8,'Paras','Soni','Barkatullah University','1999-07-06 00:00:00',4,'paras@gmail.com','6799876788','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-13 13:34:43','2022-06-13 13:34:43');

/*Table structure for table `st_subject` */

DROP TABLE IF EXISTS `st_subject`;

CREATE TABLE `st_subject` (
  `ID` bigint(20) NOT NULL,
  `SUBJECT_ID` bigint(20) default NULL,
  `COURSE_NAME` varchar(255) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `COURSE_ID` bigint(20) default NULL,
  `SUBJECT_NAME` varchar(255) default NULL,
  `CREATEDBY` varchar(255) default NULL,
  `MODIFIEDBY` varchar(255) default NULL,
  `CREATED_DATETIME` datetime default NULL,
  `MODIFIED_DATETIME` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_subject` */

insert  into `st_subject`(`ID`,`SUBJECT_ID`,`COURSE_NAME`,`DESCRIPTION`,`COURSE_ID`,`SUBJECT_NAME`,`CREATEDBY`,`MODIFIEDBY`,`CREATED_DATETIME`,`MODIFIED_DATETIME`) values (1,0,'BCA','Development of web pages',1,'HTML','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:13:46','2022-05-19 12:13:46'),(2,0,'BCA','Programming Language',1,'C Language','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:15:12','2022-05-19 12:15:12'),(3,0,'BCOM','Accounts',3,'Accounts','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:15:52','2022-05-19 12:15:52'),(4,0,'BCOM','Costing',3,'Costing','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:16:39','2022-05-19 12:16:39'),(5,0,'BBA','Sales',2,'Sales','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:17:08','2022-05-19 12:17:08'),(6,0,'BBA','Marketing',2,'Marketing','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:17:27','2022-05-19 12:17:27'),(7,0,'MCA','Java',5,'Java','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-19 12:19:13','2022-05-19 12:19:13');

/*Table structure for table `st_timetable` */

DROP TABLE IF EXISTS `st_timetable`;

CREATE TABLE `st_timetable` (
  `ID` bigint(20) NOT NULL,
  `SUBJECT_ID` bigint(20) default NULL,
  `COURSE_NAME` varchar(255) default NULL,
  `EXAM_DATE` datetime default NULL,
  `SUBJECT_NAME` varchar(255) default NULL,
  `SEMESTER` varchar(255) default NULL,
  `EXAM_TIME` varchar(255) default NULL,
  `COURSE_ID` bigint(20) default NULL,
  `CREATEDBY` varchar(255) default NULL,
  `MODIFIEDBY` varchar(255) default NULL,
  `CREATED_DATETIME` datetime default NULL,
  `MODIFIED_DATETIME` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_timetable` */

insert  into `st_timetable`(`ID`,`SUBJECT_ID`,`COURSE_NAME`,`EXAM_DATE`,`SUBJECT_NAME`,`SEMESTER`,`EXAM_TIME`,`COURSE_ID`,`CREATEDBY`,`MODIFIEDBY`,`CREATED_DATETIME`,`MODIFIED_DATETIME`) values (1,6,'BBA','2022-06-16 00:00:00','Marketing','1 semester','12:00PM to 3:00PM',6,'gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-14 16:44:16','2022-06-14 16:44:16'),(3,3,'BCOM','2022-06-22 00:00:00','Accounts','4 semester','08:00 AM to 11:00 AM',3,'gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-17 12:41:06','2022-06-17 12:41:06'),(4,1,'MBA','2022-06-22 00:00:00','HTML','4 semester','08:00 AM to 11:00 AM',4,'gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-14 16:45:06','2022-06-14 16:45:06'),(5,7,'MCA','2022-06-15 00:00:00','Java','6 semester','3:00PM to 6:00PM',5,'gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-14 16:45:24','2022-06-14 16:45:24'),(9,3,'BBA','2023-06-23 00:00:00','Accounts','1 semester','3:00PM to 6:00PM',6,'gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-14 16:46:23','2022-06-14 16:46:23'),(10,3,'BBA','2023-06-15 00:00:00','Accounts','1 semester','08:00 AM to 11:00 AM',6,'gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-14 16:46:39','2022-06-14 16:46:39'),(11,3,'BBA','2023-06-21 00:00:00','Accounts','1 semester','12:00PM to 3:00PM',6,'gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-14 16:46:57','2022-06-14 16:46:57'),(12,1,'BCA','2022-06-29 00:00:00','HTML','3 semester','3:00PM to 6:00PM',1,'gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-13 13:32:51','2022-06-13 13:32:51'),(13,3,'BCOM','2022-06-16 00:00:00','Accounts','4 semester','12:00PM to 3:00PM',3,'gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-15 12:46:28','2022-06-15 12:46:28'),(14,4,'MBA','2022-06-29 00:00:00','Costing','9 semester','3:00PM to 6:00PM',4,'gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-15 12:47:09','2022-06-15 12:47:09'),(15,1,'BCA','2022-06-21 00:00:00','HTML','7 semester','08:00 AM to 11:00 AM',1,'gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-15 12:47:50','2022-06-15 12:47:50');

/*Table structure for table `st_user` */

DROP TABLE IF EXISTS `st_user`;

CREATE TABLE `st_user` (
  `ID` bigint(20) NOT NULL,
  `FIRST_NAME` varchar(255) default NULL,
  `LAST_NAME` varchar(255) default NULL,
  `GENDER` varchar(255) default NULL,
  `ROLE_ID` bigint(20) default NULL,
  `DOB` datetime default NULL,
  `MOBILE_NO` varchar(255) default NULL,
  `LOGIN` varchar(255) default NULL,
  `PASSWORD` varchar(255) default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `MODIFIED_BY` varchar(255) default NULL,
  `CREATED_DATETIME` datetime default NULL,
  `MODIFIED_DATETIME` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_user` */

insert  into `st_user`(`ID`,`FIRST_NAME`,`LAST_NAME`,`GENDER`,`ROLE_ID`,`DOB`,`MOBILE_NO`,`LOGIN`,`PASSWORD`,`CREATED_BY`,`MODIFIED_BY`,`CREATED_DATETIME`,`MODIFIED_DATETIME`) values (1,'Sourabh','Sharma','Male',1,'1992-06-13 00:00:00','9406653787','sourabh@gmail.com','pass@123','admin','admin','2022-05-18 15:06:44','2022-05-18 15:06:44'),(3,'Pushpendra','Singh','Male',2,'1991-02-12 00:00:00','9899776555','pushpa@gmail.com','Pushpa@123','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-18 15:40:05','2022-05-18 15:40:05'),(4,'Gagan','Sunahre','Male',3,'1996-10-09 00:00:00','8778655645','gagan@gmail.com','Gagan@123','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-05-18 16:43:30','2022-05-18 16:43:30'),(5,'Pulkit','Jain','Male',4,'1992-03-07 00:00:00','7837765656','jain@gmail.com','Jain@123','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-02 23:07:32','2022-06-02 23:07:32'),(6,'Shreyansh','Kumar','Male',2,'1995-01-12 00:00:00','6746665566','kumar@gmail.com','Kumar@123','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-02 23:08:31','2022-06-02 23:08:31'),(7,'Aman','Tamrakar','Male',2,'1990-04-12 00:00:00','7877665676','Aman@gmail.com','Aman@123','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-02 23:09:28','2022-06-02 23:09:28'),(8,'Paras','Soni','Male',2,'1994-11-12 00:00:00','8988778876','Paras@gmail.com','Paras@123','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-02 23:10:04','2022-06-02 23:10:04'),(9,'Rahul','Singh','Male',2,'1995-04-12 00:00:00','9899876788','Rahul@gmail.com','Rahul@123','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-02 23:10:51','2022-06-02 23:10:51'),(10,'Ankit','Kumar','Male',2,'1987-03-12 00:00:00','7867667789','ankit@gmail.com','Ankit@123','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-02 23:11:30','2022-06-02 23:11:30'),(11,'Akanksha','Dwivedi','Female',2,'1997-04-12 00:00:00','6756546787','Akku@gmail.com','Akku@123','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-02 23:12:25','2022-06-02 23:12:25'),(12,'Anamika','Singh','Female',2,'1997-08-12 00:00:00','9899977854','annu@gmail.com','Annu@123','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-02 23:13:06','2022-06-02 23:13:06'),(13,'Mohan','Singh','Female',2,'1990-01-12 00:00:00','6754567678','mohan@gmail.com','Mohan@123','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-02 23:13:44','2022-06-02 23:13:44'),(14,'Amitabh','singh','Male',2,'1991-09-03 00:00:00','7867543567','amit@gmail.com','Amit@123','gupta.vipin02@gmail.com','gupta.vipin02@gmail.com','2022-06-02 23:14:34','2022-06-02 23:14:34');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

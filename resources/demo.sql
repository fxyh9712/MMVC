/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-10-19 16:16:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('3', 'Java');
INSERT INTO `course` VALUES ('4', 'PHP');
INSERT INTO `course` VALUES ('5', 'Java');
INSERT INTO `course` VALUES ('6', 'PHP');
INSERT INTO `course` VALUES ('7', 'Java');
INSERT INTO `course` VALUES ('8', 'PHP');
INSERT INTO `course` VALUES ('9', 'Java');
INSERT INTO `course` VALUES ('10', 'PHP');
INSERT INTO `course` VALUES ('11', 'Java');
INSERT INTO `course` VALUES ('12', 'PHP');
INSERT INTO `course` VALUES ('13', 'Java');
INSERT INTO `course` VALUES ('14', 'PHP');
INSERT INTO `course` VALUES ('15', 'PHP');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('3', '飞雪');
INSERT INTO `customer` VALUES ('4', '飞雪');
INSERT INTO `customer` VALUES ('9', '李四');
INSERT INTO `customer` VALUES ('10', '李四');
INSERT INTO `customer` VALUES ('11', '李四');
INSERT INTO `customer` VALUES ('12', '李四');
INSERT INTO `customer` VALUES ('13', '李四');
INSERT INTO `customer` VALUES ('14', '李四');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_cb1s5s4hoxce8e8b5thud2ddb` (`cid`) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `customer` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '九江', '10000', '3');
INSERT INTO `orders` VALUES ('2', '吉安', '20000', '3');
INSERT INTO `orders` VALUES ('6', '吉安', '20000', '3');
INSERT INTO `orders` VALUES ('7', '九江', '10000', '4');
INSERT INTO `orders` VALUES ('8', '吉安', '20000', '4');
INSERT INTO `orders` VALUES ('11', '上海', '1000', '9');

-- ----------------------------
-- Table structure for s_c
-- ----------------------------
DROP TABLE IF EXISTS `s_c`;
CREATE TABLE `s_c` (
  `SID` int(11) NOT NULL,
  `CID` int(11) NOT NULL,
  PRIMARY KEY (`CID`,`SID`),
  KEY `FK_gdeb1wr96l6422dlhhphki0x4` (`CID`) USING BTREE,
  KEY `FK_a3hm42ptahi43v1lp2s2h88kn` (`SID`) USING BTREE,
  CONSTRAINT `s_c_ibfk_1` FOREIGN KEY (`SID`) REFERENCES `student` (`ID`),
  CONSTRAINT `s_c_ibfk_2` FOREIGN KEY (`CID`) REFERENCES `course` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_c
-- ----------------------------
INSERT INTO `s_c` VALUES ('3', '3');
INSERT INTO `s_c` VALUES ('4', '3');
INSERT INTO `s_c` VALUES ('3', '4');
INSERT INTO `s_c` VALUES ('4', '4');
INSERT INTO `s_c` VALUES ('9', '9');
INSERT INTO `s_c` VALUES ('10', '9');
INSERT INTO `s_c` VALUES ('9', '10');
INSERT INTO `s_c` VALUES ('10', '10');
INSERT INTO `s_c` VALUES ('13', '13');
INSERT INTO `s_c` VALUES ('14', '13');
INSERT INTO `s_c` VALUES ('13', '14');
INSERT INTO `s_c` VALUES ('14', '14');
INSERT INTO `s_c` VALUES ('15', '15');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('3', '张三');
INSERT INTO `student` VALUES ('4', '李四');
INSERT INTO `student` VALUES ('5', '张三');
INSERT INTO `student` VALUES ('6', '李四');
INSERT INTO `student` VALUES ('7', '张三');
INSERT INTO `student` VALUES ('8', '李四');
INSERT INTO `student` VALUES ('9', '张三');
INSERT INTO `student` VALUES ('10', '李四');
INSERT INTO `student` VALUES ('11', '张三');
INSERT INTO `student` VALUES ('12', '李四');
INSERT INTO `student` VALUES ('13', '张三');
INSERT INTO `student` VALUES ('14', '李四');
INSERT INTO `student` VALUES ('15', '王五');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', '李四');
INSERT INTO `user` VALUES ('4', '张三');
INSERT INTO `user` VALUES ('6', '张三');
INSERT INTO `user` VALUES ('7', '张三');
INSERT INTO `user` VALUES ('8', '张三');
INSERT INTO `user` VALUES ('9', '张三');
INSERT INTO `user` VALUES ('10', '哦哦');
INSERT INTO `user` VALUES ('17', '王五');
INSERT INTO `user` VALUES ('18', '王五');
INSERT INTO `user` VALUES ('19', '玉花');

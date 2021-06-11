/*
Navicat MySQL Data Transfer

Source Server         : vm-mysql8-docker
Source Server Version : 80023
Source Host
Source Database       : user-db

Target Server Type    : MYSQL
Target Server Version : 80023
File Encoding         : 65001

Date: 2021-06-11 16:00:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pwd` varchar(64) NOT NULL,
  `uname` varchar(64) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf16;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '1');

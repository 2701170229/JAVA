/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : shopcartdb

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-07-12 00:21:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_cart
-- ----------------------------
DROP TABLE IF EXISTS `t_cart`;
CREATE TABLE `t_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `num` int(11) DEFAULT NULL,
  `goodsName` varchar(500) DEFAULT '',
  `price` varchar(500) DEFAULT '',
  `picture` varchar(500) DEFAULT '',
  `userId` int(11) DEFAULT NULL,
  `goodsId` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cart
-- ----------------------------

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(500) DEFAULT '',
  `price` varchar(500) DEFAULT '',
  `picture` varchar(500) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES ('1', '车厘子樱桃水果', '18', 'https://img11.360buyimg.com/n1/s150x150_jfs/t1/107210/9/15696/132221/5eae60efE49ce33a3/b21319213899cd15.jpg');
INSERT INTO `t_goods` VALUES ('2', '山水蜜桃平谷大桃', '25', 'https://img13.360buyimg.com/n1/s150x150_jfs/t1/76788/30/4017/218970/5d231989E90fad7db/dc8ae9ec25203f49.jpg');
INSERT INTO `t_goods` VALUES ('3', '云南蓝莓', '16', 'https://img10.360buyimg.com/n1/s150x150_jfs/t1/75258/6/8134/196751/5d60ed7bEd625ce65/8a44c0881e3ac344.png');
INSERT INTO `t_goods` VALUES ('4', '泰国榴莲金枕', '12', 'https://img12.360buyimg.com/n2/jfs/t1/98853/17/13144/298644/5e567fb2E134553cc/8b205f48aea6f3f9.png');
INSERT INTO `t_goods` VALUES ('5', '红色火龙果', '58', 'https://img11.360buyimg.com/n2/jfs/t1/82862/9/6976/139184/5d4fb003Ecd158a01/62c912f94aa0331d.jpg');

-- ----------------------------
-- Table structure for t_settle
-- ----------------------------
DROP TABLE IF EXISTS `t_settle`;
CREATE TABLE `t_settle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` varchar(500) DEFAULT '',
  `createTime` varchar(500) DEFAULT '',
  `goodsIds` varchar(500) DEFAULT '',
  `userId` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_settle
-- ----------------------------
INSERT INTO `t_settle` VALUES ('1', '18', '2020-07-12 00:20:47', '0,5', '1', '1');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(500) DEFAULT '',
  `pwd` varchar(500) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '1', '22222222');

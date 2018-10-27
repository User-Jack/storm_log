/*
Navicat MySQL Data Transfer

Source Server         : node03
Source Server Version : 50547
Source Host           : node03:3306
Source Database       : log_monitor

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2017-11-14 09:40:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for log_monitor_app
-- ----------------------------
DROP TABLE IF EXISTS `log_monitor_app`;
CREATE TABLE `log_monitor_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '应用编号',
  `name` varchar(100) DEFAULT NULL COMMENT '应用名称',
  `desc` varchar(250) DEFAULT NULL COMMENT '应用简要描述',
  `isOnline` int(1) DEFAULT NULL COMMENT '应用是否在线',
  `typeId` int(1) DEFAULT NULL COMMENT '应用类型对应的ID',
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '应用录入时间',
  `updateaDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '应用信息修改时间',
  `createUser` varchar(100) DEFAULT NULL COMMENT '创建用户',
  `updateUser` varchar(100) DEFAULT NULL COMMENT '修改用户',
  `userId` varchar(100) DEFAULT NULL COMMENT '应用所属用户，多个用户用逗号分开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_monitor_app
-- ----------------------------
INSERT INTO `log_monitor_app` VALUES ('1', 'storm集群', 'storm集群', '1', '1', '2016-11-12 18:15:23', '2016-11-11 16:58:21', 'liangdong', 'liangdong', '1');
INSERT INTO `log_monitor_app` VALUES ('2', 'java应用', 'java应用', '1', '1', '2016-06-12 21:39:09', '2016-11-12 09:55:45', 'liangdong', 'liangdong', '1');

-- ----------------------------
-- Table structure for log_monitor_app_type
-- ----------------------------
DROP TABLE IF EXISTS `log_monitor_app_type`;
CREATE TABLE `log_monitor_app_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '应用类型编号',
  `name` varchar(100) DEFAULT NULL COMMENT '应用类型名称，如linux，web，java，storm，kafka等',
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '应用类型录入时间',
  `updataDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '应用类型修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_monitor_app_type
-- ----------------------------
INSERT INTO `log_monitor_app_type` VALUES ('1', 'storm', '2016-11-11 16:58:40', '2016-11-11 16:58:42');

-- ----------------------------
-- Table structure for log_monitor_rule
-- ----------------------------
DROP TABLE IF EXISTS `log_monitor_rule`;
CREATE TABLE `log_monitor_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '规则编号，自增长',
  `name` varchar(100) DEFAULT NULL COMMENT '规则名称',
  `desc` varchar(250) DEFAULT NULL COMMENT '规则描述',
  `keyword` varchar(100) DEFAULT NULL COMMENT '规则关键词',
  `isValid` int(1) DEFAULT NULL COMMENT '规则是否可用',
  `appId` int(11) DEFAULT NULL COMMENT '规则所属应用',
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '规则创建时间',
  `updateDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '规则修改时间',
  `createUser` varchar(100) DEFAULT NULL COMMENT '创建用户',
  `updateUser` varchar(100) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_monitor_rule
-- ----------------------------
INSERT INTO `log_monitor_rule` VALUES ('1', 'exe', 'Exception', 'Exception', '1', '1', '2016-11-11 17:02:05', '2016-11-11 16:57:25', 'liangdong', 'liangdong');
INSERT INTO `log_monitor_rule` VALUES ('2', 'sys', '测试数据', 'sys', '1', '2', '2016-11-12 10:02:13', '0000-00-00 00:00:00', 'liangdong', 'liangdong');
INSERT INTO `log_monitor_rule` VALUES ('3', 'error', '错误信息', 'error', '1', '3', '2016-11-12 16:00:56', '2016-11-12 16:00:58', 'liangdong', 'liangdong');

-- ----------------------------
-- Table structure for log_monitor_rule_record
-- ----------------------------
DROP TABLE IF EXISTS `log_monitor_rule_record`;
CREATE TABLE `log_monitor_rule_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '告警信息编号',
  `appId` int(11) DEFAULT NULL COMMENT '告警信息所属应用编号',
  `ruleId` int(11) DEFAULT NULL COMMENT '告警信息所属规则编号',
  `isEmail` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '是否邮件告知，0：未告知  1：告知',
  `isPhone` int(11) DEFAULT '0' COMMENT '是否短信告知，0：未告知  1：告知',
  `isClose` int(11) DEFAULT '0' COMMENT '是否处理完毕，0：未处理  1：已处理',
  `noticeInfo` varchar(500) DEFAULT NULL COMMENT '告警信息明细',
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '告警信息入库时间',
  `updateDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '告警信息修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_monitor_rule_record
-- ----------------------------

-- ----------------------------
-- Table structure for log_monitor_user
-- ----------------------------
DROP TABLE IF EXISTS `log_monitor_user`;
CREATE TABLE `log_monitor_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户编号，自增长',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名称',
  `mobile` varchar(11) DEFAULT NULL COMMENT '用户手机号码',
  `email` varchar(50) DEFAULT NULL COMMENT '用户的邮箱地址，默认为公司邮箱',
  `isValid` int(1) DEFAULT NULL COMMENT '用户是否有效',
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户录入时间',
  `updateDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '用户信息修改时间',
  `createUser` varchar(100) DEFAULT NULL COMMENT '创建用户',
  `updateUser` varchar(100) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_monitor_user
-- ----------------------------
INSERT INTO `log_monitor_user` VALUES ('1', 'liangdong', '18101056806', 'liangdmaster@163.com', '1', '2016-06-12 21:40:06', '2016-11-11 16:59:13', 'liangdong', 'liangdong');

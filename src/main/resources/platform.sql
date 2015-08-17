/*
Navicat MySQL Data Transfer

Source Server         : Platform
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : platform

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2015-01-11 22:32:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_fields
-- ----------------------------
DROP TABLE IF EXISTS `base_fields`;
CREATE TABLE `base_fields` (
  `FIELD_ID` varchar(32) NOT NULL COMMENT '字段ID',
  `FIELD` varchar(64) DEFAULT NULL COMMENT '字段',
  `FIELD_NAME` varchar(128) DEFAULT NULL COMMENT '字段名称',
  `VALUE_FIELD` varchar(128) DEFAULT NULL COMMENT '字段值',
  `DISPLAY_FIELD` varchar(128) DEFAULT NULL COMMENT '字段显示值',
  `ENABLED` decimal(2,0) DEFAULT NULL COMMENT '是否启用',
  `SORT` decimal(2,0) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`FIELD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统字段设置表';

-- ----------------------------
-- Records of base_fields
-- ----------------------------
INSERT INTO `base_fields` VALUES ('a4bd1aa7-f041-11e3-8ae6-ec55f9ed', 'sex', '性别', '0', '男', '1', '1');
INSERT INTO `base_fields` VALUES ('a4cafdc1-f041-11e3-8ae6-ec55f9ed', 'sex', '性别', '1', '女', '1', '2');
INSERT INTO `base_fields` VALUES ('a4d44286-f041-11e3-8ae6-ec55f9ed', 'sex', '性别', '2', '其他', '1', '3');
INSERT INTO `base_fields` VALUES ('a4dc2c8f-f041-11e3-8ae6-ec55f9ed', 'sex', '性别', '3', '保密', '1', '4');
INSERT INTO `base_fields` VALUES ('a4e65d89-f041-11e3-8ae6-ec55f9ed', 'pagesize', '每页显示条数', '10', '10条/页', '1', '1');
INSERT INTO `base_fields` VALUES ('a4efd858-f041-11e3-8ae6-ec55f9ed', 'pagesize', '每页显示条数', '20', '20条/页', '1', '2');
INSERT INTO `base_fields` VALUES ('a4f7f139-f041-11e3-8ae6-ec55f9ed', 'pagesize', '每页显示条数', '30', '30条/页', '1', '3');
INSERT INTO `base_fields` VALUES ('a507560f-f041-11e3-8ae6-ec55f9ed', 'pagesize', '每页显示条数', '50', '50条/页', '1', '4');
INSERT INTO `base_fields` VALUES ('a50dba96-f041-11e3-8ae6-ec55f9ed', 'theme', '风格', 'xtheme-blue.css', '经典蓝色', '1', '1');
INSERT INTO `base_fields` VALUES ('a51292bd-f041-11e3-8ae6-ec55f9ed', 'theme', '风格', 'xtheme-gray.css', '简约灰色', '1', '2');
INSERT INTO `base_fields` VALUES ('a517e256-f041-11e3-8ae6-ec55f9ed', 'leaf', '父模块', '0', '父节点', '1', '1');
INSERT INTO `base_fields` VALUES ('a51f8e45-f041-11e3-8ae6-ec55f9ed', 'leaf', '父模块', '1', '子节点', '1', '2');
INSERT INTO `base_fields` VALUES ('a5264c45-f041-11e3-8ae6-ec55f9ed', 'expanded', '展开状态', '0', '收缩', '1', '1');
INSERT INTO `base_fields` VALUES ('a52e6f41-f041-11e3-8ae6-ec55f9ed', 'expanded', '展开状态', '1', '展开', '1', '2');
INSERT INTO `base_fields` VALUES ('a534256d-f041-11e3-8ae6-ec55f9ed', 'isdisplay', '是否显示', '0', '否', '1', '1');
INSERT INTO `base_fields` VALUES ('a53a5b36-f041-11e3-8ae6-ec55f9ed', 'isdisplay', '是否显示', '1', '是', '1', '2');
INSERT INTO `base_fields` VALUES ('a53f869b-f041-11e3-8ae6-ec55f9ed', 'pagesize', '每页显示条数', '100', '100条/页', '1', '5');
INSERT INTO `base_fields` VALUES ('a5462bba-f041-11e3-8ae6-ec55f9ed', 'pagesize', '每页显示条数', '200', '200条/页', '1', '6');
INSERT INTO `base_fields` VALUES ('a54c5627-f041-11e3-8ae6-ec55f9ed', 'pagesize', '每页显示条数', '500', '500条/页', '0', '7');
INSERT INTO `base_fields` VALUES ('a552bfa8-f041-11e3-8ae6-ec55f9ed', 'enabled', '是否启用', '0', '禁用', '1', '2');
INSERT INTO `base_fields` VALUES ('a558d6e0-f041-11e3-8ae6-ec55f9ed', 'enabled', '是否启用', '1', '启用', '1', '1');

-- ----------------------------
-- Table structure for base_loginlog
-- ----------------------------
DROP TABLE IF EXISTS `base_loginlog`;
CREATE TABLE `base_loginlog` (
  `id` varchar(64) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `createTime` varchar(64) DEFAULT NULL,
  `ip` varchar(32) DEFAULT NULL,
  `userAgent` varchar(255) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `msg` varchar(5000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_loginlog
-- ----------------------------
INSERT INTO `base_loginlog` VALUES ('14111117023416327679', 'admin', null, '2014-11-11 17:02:34', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14111117041398756835', 'admin', null, '2014-11-11 17:04:13', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14111117061793468671', 'admin', null, '2014-11-11 17:06:17', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14111117064878580257', 'admin', null, '2014-11-11 17:06:48', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14111117070954365858', 'admin', null, '2014-11-11 17:07:09', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14111117202537150725', 'admin', null, '2014-11-11 17:20:25', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码输入不正确,请重新输入！');
INSERT INTO `base_loginlog` VALUES ('14111210134624188424', 'sasasasa', null, '2014-11-12 10:13:46', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码输入不正确,请重新输入！');
INSERT INTO `base_loginlog` VALUES ('14111215210192493537', 'admin', null, '2014-11-12 15:21:01', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111215344540645368', 'admin', null, '2014-11-12 15:34:45', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111215384385322191', 'admin', null, '2014-11-12 15:38:43', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111215432815478587', 'admin', null, '2014-11-12 15:43:28', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111215442941532401', 'admin', null, '2014-11-12 15:44:29', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111215443046937913', 'sasasasa', null, '2014-11-12 15:44:30', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码输入不正确,请重新输入！');
INSERT INTO `base_loginlog` VALUES ('14111215443398195878', 'sasasasa', null, '2014-11-12 15:44:33', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码输入不正确,请重新输入！');
INSERT INTO `base_loginlog` VALUES ('14111215443532239296', 'sasasasa', null, '2014-11-12 15:44:35', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码输入不正确,请重新输入！');
INSERT INTO `base_loginlog` VALUES ('14111215445046697814', 'admin', null, '2014-11-12 15:44:50', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111215474675012016', 'admin', null, '2014-11-12 15:47:46', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111215503512080284', 'admin', null, '2014-11-12 15:50:35', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111215564363120105', 'admin', null, '2014-11-12 15:56:43', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111216051868116423', 'admin', null, '2014-11-12 16:05:18', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111217254627667495', 'admin', null, '2014-11-12 17:25:46', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111308542880095473', 'admin', null, '2014-11-13 08:54:28', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111309055181829196', 'admin', null, '2014-11-13 09:05:51', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111309250469291087', 'admin', null, '2014-11-13 09:25:04', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111309285887578770', 'admin', null, '2014-11-13 09:28:58', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码输入不正确,请重新输入！');
INSERT INTO `base_loginlog` VALUES ('14111309290522931354', 'admin', null, '2014-11-13 09:29:05', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111309474553683750', 'admin', null, '2014-11-13 09:47:45', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111310042160580700', 'admin', null, '2014-11-13 10:04:21', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111310080988867740', 'admin', null, '2014-11-13 10:08:09', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111310191071881645', 'admin', null, '2014-11-13 10:19:10', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111310302820603712', 'admin', null, '2014-11-13 10:30:28', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111310430921369522', 'admin', null, '2014-11-13 10:43:09', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111311010041649611', 'admin', null, '2014-11-13 11:01:00', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111311044283913711', 'admin', null, '2014-11-13 11:04:42', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111311063964760098', 'admin', null, '2014-11-13 11:06:39', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111311405174703052', 'admin', null, '2014-11-13 11:40:51', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111415194433462959', 'admin', null, '2014-11-14 15:19:44', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111416082498006812', 'admin', null, '2014-11-14 16:08:24', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111416242973124100', 'admin', null, '2014-11-14 16:24:29', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111416304816715416', 'admin', null, '2014-11-14 16:30:48', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111709485716772536', 'admin', null, '2014-11-17 09:48:57', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111711225183678440', 'admin', null, '2014-11-17 11:22:51', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111714065938994632', 'admin', null, '2014-11-17 14:06:59', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111808500022459598', 'admin', null, '2014-11-18 08:50:00', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111809443132150251', 'admin', null, '2014-11-18 09:44:31', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111809481169655449', 'admin', null, '2014-11-18 09:48:11', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111809482430551677', 'admin', null, '2014-11-18 09:48:24', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111810494477867886', 'admin', null, '2014-11-18 10:49:44', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111810573147955240', 'admin', null, '2014-11-18 10:57:31', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111810595978276365', 'admin', null, '2014-11-18 10:59:59', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111814540452531298', 'admin', null, '2014-11-18 14:54:04', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111816072053047047', 'admin', null, '2014-11-18 16:07:20', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111816374861249605', 'admin', null, '2014-11-18 16:37:48', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111816513542460886', 'admin', null, '2014-11-18 16:51:35', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111816553210348995', 'admin', null, '2014-11-18 16:55:32', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111908475818981994', 'admin', null, '2014-11-19 08:47:58', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111911184290320611', 'aaaaaa', null, '2014-11-19 11:18:42', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111911185392681631', 'aaaaaa', null, '2014-11-19 11:18:53', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码已经失效!请重新输入新的验证码！');
INSERT INTO `base_loginlog` VALUES ('14111911185817652632', 'aaaaaa', null, '2014-11-19 11:18:58', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码已经失效!请重新输入新的验证码！');
INSERT INTO `base_loginlog` VALUES ('14111911210992336648', 'admin', null, '2014-11-19 11:21:09', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111911233930376087', 'admin', null, '2014-11-19 11:23:39', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111911251346115642', '222222', null, '2014-11-19 11:25:13', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111911263169167764', '222222', null, '2014-11-19 11:26:31', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码已经失效!请重新输入新的验证码！');
INSERT INTO `base_loginlog` VALUES ('14111911294670667516', 'admin', null, '2014-11-19 11:29:46', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111911302497382071', '333333', null, '2014-11-19 11:30:24', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111911322811403458', 'admin', null, '2014-11-19 11:32:28', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111912285217731450', 'admin', null, '2014-11-19 12:28:52', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111912383391025860', '222222', null, '2014-11-19 12:38:33', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111912383827723713', '222222', null, '2014-11-19 12:38:38', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码已经失效!请重新输入新的验证码！');
INSERT INTO `base_loginlog` VALUES ('14111912384636686185', '222222', null, '2014-11-19 12:38:46', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码已经失效!请重新输入新的验证码！');
INSERT INTO `base_loginlog` VALUES ('14111912385621259182', '222222', null, '2014-11-19 12:38:56', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码已经失效!请重新输入新的验证码！');
INSERT INTO `base_loginlog` VALUES ('14111912391362304122', 'admin', null, '2014-11-19 12:39:13', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111914140752925461', 'admin', null, '2014-11-19 14:14:07', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111916012461585062', 'admin', null, '2014-11-19 16:01:24', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111916144831077880', 'admin', null, '2014-11-19 16:14:48', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111916243843405221', 'admin', null, '2014-11-19 16:24:38', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111916252378768918', '333333', null, '2014-11-19 16:25:23', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111916274556535050', 'admin', null, '2014-11-19 16:27:45', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111916291129062925', 'admin', null, '2014-11-19 16:29:11', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 OPR/25.0.1614.71', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111916321598095357', 'admin', null, '2014-11-19 16:32:15', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3; .NET4.0C; .NET4.0E; rv:11.0) like Gecko', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14111916432978664034', 'admin', null, '2014-11-19 16:43:29', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 OPR/25.0.1614.71', '1', '验证码已经失效!请重新输入新的验证码！');
INSERT INTO `base_loginlog` VALUES ('14111916433775502218', 'admin', null, '2014-11-19 16:43:37', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 OPR/25.0.1614.71', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112008551227087833', 'admin', null, '2014-11-20 08:55:12', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112009082377687327', 'admin', null, '2014-11-20 09:08:23', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112010105245668494', 'admin', null, '2014-11-20 10:10:52', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112010181174942164', 'admin', null, '2014-11-20 10:18:11', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112014083632684818', 'admin', null, '2014-11-20 14:08:36', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112016310537341444', 'admin', null, '2014-11-20 16:31:05', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112108592988645192', 'admin', null, '2014-11-21 08:59:29', '172.16.108.137', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112116341168884448', 'admin', null, '2014-11-21 16:34:11', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112116583944097798', 'admin', null, '2014-11-21 16:58:39', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112409074947427265', 'admin', null, '2014-11-24 09:07:49', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112410512773194352', 'AAAAAAAA', null, '2014-11-24 10:51:27', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112410514751319924', 'admin', null, '2014-11-24 10:51:47', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112412273135241606', 'admin', null, '2014-11-24 12:27:31', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112412353060705077', 'admin', null, '2014-11-24 12:35:30', '172.16.213.87', 'Mozilla/5.0 (iPhone; CPU iPhone OS 8_1_1 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Mobile/12B435 Safari/600.1.4', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112413440593476229', 'admin', null, '2014-11-24 13:44:05', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112415112477673660', 'admin', null, '2014-11-24 15:11:24', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112417013818649375', 'admin', null, '2014-11-24 17:01:38', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112417034949673955', 'admin', null, '2014-11-24 17:03:49', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112509351710309276', 'admin', null, '2014-11-25 09:35:17', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112509490410717719', 'admin', null, '2014-11-25 09:49:04', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112510051891091298', 'admin', null, '2014-11-25 10:05:18', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112510285353354128', 'admin', null, '2014-11-25 10:28:53', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112510512580922542', 'admin', null, '2014-11-25 10:51:25', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码输入不正确,请重新输入！');
INSERT INTO `base_loginlog` VALUES ('14112510513796996560', 'admin', null, '2014-11-25 10:51:37', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112510541768350869', 'admin', null, '2014-11-25 10:54:17', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112510553690671312', 'admin', null, '2014-11-25 10:55:36', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112512185483816783', 'admin', null, '2014-11-25 12:18:54', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112512200116865876', 'admin', null, '2014-11-25 12:20:01', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112512245043282076', 'admin', null, '2014-11-25 12:24:50', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112512454637416460', 'admin', null, '2014-11-25 12:45:46', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112512504898356593', 'admin', null, '2014-11-25 12:50:48', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112512530281795021', 'admin', null, '2014-11-25 12:53:02', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112512545648492063', 'admin', null, '2014-11-25 12:54:56', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112513475229194554', 'admin', null, '2014-11-25 13:47:52', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112514152367534918', 'admin', null, '2014-11-25 14:15:23', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112514264640079827', 'admin', null, '2014-11-25 14:26:46', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112514290761373405', 'admin', null, '2014-11-25 14:29:07', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112514314143598883', 'admin', null, '2014-11-25 14:31:41', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112514330126026688', 'admin', null, '2014-11-25 14:33:01', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112514362676395325', 'admin', null, '2014-11-25 14:36:26', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112515003291024283', 'admin', null, '2014-11-25 15:00:32', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112515274762986585', 'admin', null, '2014-11-25 15:27:47', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112515365947108615', 'admin', null, '2014-11-25 15:36:59', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112515395389411216', 'admin', null, '2014-11-25 15:39:53', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112515433454995509', 'admin', null, '2014-11-25 15:43:34', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112515572210209930', 'admin', null, '2014-11-25 15:57:22', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112516500093491435', 'admin', null, '2014-11-25 16:50:00', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112516505519153141', 'admin', null, '2014-11-25 16:50:55', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112517110585563435', 'admin', null, '2014-11-25 17:11:05', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112517142482288029', 'admin', null, '2014-11-25 17:14:24', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112608370942307794', 'admin', null, '2014-11-26 08:37:09', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112608474736599825', 'admin', null, '2014-11-26 08:47:47', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112608511798934574', 'admin', null, '2014-11-26 08:51:17', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112608522544334223', 'admin', null, '2014-11-26 08:52:25', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112608543380230536', 'admin', null, '2014-11-26 08:54:33', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112609072216528150', 'admin', null, '2014-11-26 09:07:22', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112609125865035953', 'admin', null, '2014-11-26 09:12:58', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112609173045507673', 'admin', null, '2014-11-26 09:17:30', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112609244550476485', 'admin', null, '2014-11-26 09:24:45', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112609282992158256', 'admin', null, '2014-11-26 09:28:29', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112609334129660712', 'admin', null, '2014-11-26 09:33:41', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112609403625741391', 'admin', null, '2014-11-26 09:40:36', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112609461621434760', 'admin', null, '2014-11-26 09:46:16', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112609474285596770', 'admin', null, '2014-11-26 09:47:42', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112609543013797207', 'admin', null, '2014-11-26 09:54:30', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112610001597326969', 'admin', null, '2014-11-26 10:00:15', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112610090837341155', 'admin', null, '2014-11-26 10:09:08', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112610130527394637', 'admin', null, '2014-11-26 10:13:05', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112610145888662717', 'admin', null, '2014-11-26 10:14:58', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112610170386120357', 'admin', null, '2014-11-26 10:17:03', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112611271383703290', 'admin', null, '2014-11-26 11:27:13', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码输入不正确,请重新输入！');
INSERT INTO `base_loginlog` VALUES ('14112611272210554530', 'admin', null, '2014-11-26 11:27:22', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112612301792900592', 'admin', null, '2014-11-26 12:30:17', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112612450167069982', 'admin', null, '2014-11-26 12:45:01', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112612460698765413', 'admin', null, '2014-11-26 12:46:06', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112614342420057863', 'admin', null, '2014-11-26 14:34:24', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112614354567321847', 'admin', null, '2014-11-26 14:35:45', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码输入不正确,请重新输入！');
INSERT INTO `base_loginlog` VALUES ('14112614355746754865', 'admin', null, '2014-11-26 14:35:57', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码输入不正确,请重新输入！');
INSERT INTO `base_loginlog` VALUES ('14112614360443268351', 'admin', null, '2014-11-26 14:36:04', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码输入不正确,请重新输入！');
INSERT INTO `base_loginlog` VALUES ('14112614361746991870', 'admin', null, '2014-11-26 14:36:17', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112614452298065443', 'admin', null, '2014-11-26 14:45:22', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112615001410148893', 'admin', null, '2014-11-26 15:00:14', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112615125214435375', 'admin', null, '2014-11-26 15:12:52', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112615561390696472', 'admin', null, '2014-11-26 15:56:13', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112616131989362807', 'admin', null, '2014-11-26 16:13:19', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112616182329863734', 'admin', null, '2014-11-26 16:18:23', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112616273571016667', 'admin', null, '2014-11-26 16:27:35', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112616424428014566', 'admin', null, '2014-11-26 16:42:44', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112616475989274486', 'admin', null, '2014-11-26 16:47:59', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112616571697932432', 'admin', null, '2014-11-26 16:57:16', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112616590337761390', 'admin', null, '2014-11-26 16:59:03', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112617122473150648', 'admin', null, '2014-11-26 17:12:24', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112617185057297475', 'admin', null, '2014-11-26 17:18:50', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112617315129456837', 'admin', null, '2014-11-26 17:31:51', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112708381992692963', 'admin', null, '2014-11-27 08:38:19', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112708580831419400', 'admin', null, '2014-11-27 08:58:08', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112713492133766034', 'admin', null, '2014-11-27 13:49:21', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112713551829391597', 'admin', null, '2014-11-27 13:55:18', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112714050236843638', 'admin', null, '2014-11-27 14:05:02', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112714125359483756', 'admin', null, '2014-11-27 14:12:53', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112716163361731276', 'admin', null, '2014-11-27 16:16:33', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112716285448876629', 'admin', null, '2014-11-27 16:28:54', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112716310340302730', 'admin', null, '2014-11-27 16:31:03', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112716342654655509', 'admin', null, '2014-11-27 16:34:26', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112716404997937961', 'admin', null, '2014-11-27 16:40:49', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112716514593276279', 'admin', null, '2014-11-27 16:51:45', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112716530790746837', 'admin', null, '2014-11-27 16:53:07', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112717142181441398', 'admin', null, '2014-11-27 17:14:21', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112717152773540273', 'admin', null, '2014-11-27 17:15:27', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112817143517436635', 'Anonymous', null, '2014-11-28 17:14:35', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817143957768051', 'Anonymous', null, '2014-11-28 17:14:39', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817144645599795', 'Anonymous', null, '2014-11-28 17:14:46', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817144842277951', 'Anonymous', null, '2014-11-28 17:14:48', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817144948476382', 'Anonymous', null, '2014-11-28 17:14:49', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817145020211504', 'Anonymous', null, '2014-11-28 17:14:50', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817145083530308', 'Anonymous', null, '2014-11-28 17:14:50', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817145269197698', 'Anonymous', null, '2014-11-28 17:14:52', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817152895743079', 'Anonymous', null, '2014-11-28 17:15:28', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817152915383734', 'Anonymous', null, '2014-11-28 17:15:29', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817153025669120', 'Anonymous', null, '2014-11-28 17:15:30', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817153186270313', 'Anonymous', null, '2014-11-28 17:15:31', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817153259422443', 'Anonymous', null, '2014-11-28 17:15:32', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817153241488701', 'Anonymous', null, '2014-11-28 17:15:32', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817170391961044', 'Anonymous', null, '2014-11-28 17:17:03', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817170877945718', 'Anonymous', null, '2014-11-28 17:17:08', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817180054348181', 'Anonymous', null, '2014-11-28 17:18:00', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817181184327630', 'Anonymous', null, '2014-11-28 17:18:11', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817181840852256', 'Anonymous', null, '2014-11-28 17:18:18', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('14112817182251404971', 'admin', null, '2014-11-28 17:18:22', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码输入不正确,请重新输入！');
INSERT INTO `base_loginlog` VALUES ('14112817183789059047', 'admin', null, '2014-11-28 17:18:37', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码输入不正确,请重新输入！');
INSERT INTO `base_loginlog` VALUES ('14112817185091193341', 'admin', null, '2014-11-28 17:18:50', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码已经失效!请重新输入新的验证码！');
INSERT INTO `base_loginlog` VALUES ('14112817185739527634', 'admin', null, '2014-11-28 17:18:57', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112820384740940006', 'admin', null, '2014-11-28 20:38:47', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('14112820523558760380', 'admin', null, '2014-11-28 20:52:35', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码输入不正确,请重新输入！');
INSERT INTO `base_loginlog` VALUES ('14112820524418581731', 'admin', null, '2014-11-28 20:52:44', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '1', '验证码输入不正确,请重新输入！');
INSERT INTO `base_loginlog` VALUES ('14112820530620515274', 'admin', null, '2014-11-28 20:53:06', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011112295558182778', 'Anonymous', null, '2015-01-11 12:29:55', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('15011112300757817176', 'admin', null, '2015-01-11 12:30:07', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011112314614736212', 'admin', null, '2015-01-11 12:31:46', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011112315137204434', 'admin', null, '2015-01-11 12:31:51', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011112315558694445', 'admin', null, '2015-01-11 12:31:55', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011112331589935367', 'admin', null, '2015-01-11 12:33:15', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '1', '验证码输入不正确,请重新输入！');
INSERT INTO `base_loginlog` VALUES ('15011112332631554970', 'admin', null, '2015-01-11 12:33:26', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011116471635205759', 'admin', null, '2015-01-11 16:47:16', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '1', '验证码输入不正确,请重新输入！');
INSERT INTO `base_loginlog` VALUES ('15011116472854537821', 'admin', null, '2015-01-11 16:47:28', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011116544726357545', 'admin', null, '2015-01-11 16:54:47', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011116545253617695', 'admin', null, '2015-01-11 16:54:52', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011116545784372373', 'admin', null, '2015-01-11 16:54:57', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011116553091476749', 'admin', null, '2015-01-11 16:55:30', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011116565173988791', 'admin', null, '2015-01-11 16:56:51', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011116570739342747', 'admin', null, '2015-01-11 16:57:07', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011116571288020021', 'admin', null, '2015-01-11 16:57:12', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011116572873983518', 'admin', null, '2015-01-11 16:57:28', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011116572890203562', 'admin', null, '2015-01-11 16:57:28', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011116572882369327', 'admin', null, '2015-01-11 16:57:28', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011116573495788974', 'admin', null, '2015-01-11 16:57:34', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011116580022109053', 'admin', null, '2015-01-11 16:58:00', '192.168.1.103', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/600.2.5 (KHTML, like Gecko) Version/8.0.2 Safari/600.2.5', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011117241270245381', 'admin', null, '2015-01-11 17:24:12', '192.168.1.103', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/600.2.5 (KHTML, like Gecko) Version/8.0.2 Safari/600.2.5', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011117574211116979', 'Anonymous', null, '2015-01-11 17:57:42', null, null, null, null);
INSERT INTO `base_loginlog` VALUES ('15011120251465584750', 'admin', null, '2015-01-11 20:25:14', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011120310639703950', 'admin', null, '2015-01-11 20:31:06', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011120371151653519', 'admin', null, '2015-01-11 20:37:11', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011120422467669366', 'admin', null, '2015-01-11 20:42:24', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011120465168197163', 'admin', null, '2015-01-11 20:46:51', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011120514722762783', 'admin', null, '2015-01-11 20:51:47', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011121021125831555', 'admin', null, '2015-01-11 21:02:11', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011121102475857080', 'admin', null, '2015-01-11 21:10:24', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011121113984280299', 'admin', null, '2015-01-11 21:11:39', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011121140488800234', 'admin', null, '2015-01-11 21:14:04', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011121174139469992', 'admin', null, '2015-01-11 21:17:41', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011121201273677740', 'admin', null, '2015-01-11 21:20:12', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011121322899062023', 'admin', null, '2015-01-11 21:32:28', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011121364145679485', 'admin', null, '2015-01-11 21:36:41', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011121452131427656', 'admin', null, '2015-01-11 21:45:21', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011121473665818266', 'admin', null, '2015-01-11 21:47:36', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011121575432860929', 'admin', null, '2015-01-11 21:57:54', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011122022220385699', 'admin', null, '2015-01-11 22:02:22', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011122145757116707', 'admin', null, '2015-01-11 22:14:57', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');
INSERT INTO `base_loginlog` VALUES ('15011122162159145708', 'admin', null, '2015-01-11 22:16:21', '本地', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0', '0', '登陆成功');

-- ----------------------------
-- Table structure for base_material
-- ----------------------------
DROP TABLE IF EXISTS `base_material`;
CREATE TABLE `base_material` (
  `id` varchar(64) NOT NULL,
  `total` decimal(16,3) DEFAULT NULL,
  `weight` decimal(10,3) DEFAULT NULL,
  `num` decimal(8,0) DEFAULT NULL,
  `priceId` varchar(64) DEFAULT NULL,
  `supplierId` varchar(64) DEFAULT NULL,
  `singleTotal` decimal(8,3) DEFAULT NULL,
  `singlePrice` decimal(8,3) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `isDelete` decimal(1,0) DEFAULT NULL,
  `date` varchar(32) DEFAULT NULL,
  `createTime` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_material
-- ----------------------------
INSERT INTO `base_material` VALUES ('14112509532246124416', '19999.998', '3421.728', '532', '14112610092887296863', '14112509532246124416', '6.432', '37.594', '14112509532246124416', '1', '2014-12-16', '14112509532246124416');
INSERT INTO `base_material` VALUES ('14112612462823763512', '11564.540', '1978.536', '435', '14112610092887296863', '14112509532246124416', '4.548', '26.585', '08', '0', '2015-01-08', '2014-11-26 12:46:28');
INSERT INTO `base_material` VALUES ('14112615564042842686', '11563.456', '1978.350', '300', '14112610092887296863', '14112509532246124416', '6.594', '38.545', '07', '0', '2015-01-07', '2014-11-26 15:56:40');
INSERT INTO `base_material` VALUES ('14112616300954217679', '12345.654', '2743.479', '306', '14112517345295723292', '14112509532246124416', '8.966', '40.345', '10', '0', '2015-01-10', '2014-11-26 16:30:09');
INSERT INTO `base_material` VALUES ('14112617334048106454', '12543.531', '2175.808', '334', '14112610134163511931', '14112509532246124416', '6.514', '37.555', '06', '0', '2015-01-06', '2014-11-26 17:33:40');
INSERT INTO `base_material` VALUES ('14112709464019831637', '12567.656', '2179.992', '321', '14112610134163511931', '14112509532246124416', '6.791', '39.152', '05', '0', '2015-01-05', '2014-11-27 09:46:40');
INSERT INTO `base_material` VALUES ('14112709555976324402', '1424.500', '243.713', '223', '14112610092887296863', '14112509532246124416', '1.093', '6.388', null, '0', '2014-11-06', '2014-11-27 09:55:59');
INSERT INTO `base_material` VALUES ('14112717194975506133', '13535.335', '2315.712', '312', '14112610092887296863', '14112509532246124416', '7.422', '43.382', null, '0', '2014-11-21', '2014-11-27 17:19:49');
INSERT INTO `base_material` VALUES ('14112717282438310175', '13245.670', '2943.482', '289', '14112517323482071549', '14112509532246124416', '10.185', '45.833', '4', '0', '2015-01-11', '2014-11-27 17:28:24');
INSERT INTO `base_material` VALUES ('14112820473279147884', '12345.760', '2112.192', '234', '14112610092887296863', '14112509532246124416', '9.026', '52.760', '09', '0', '2015-01-09', '2014-11-28 20:47:32');
INSERT INTO `base_material` VALUES ('15011112361158119947', '12342.430', '2111.622', '324', '14112610092887296863', '14112509532246124416', '6.517', '38.094', '04', '0', '2015-01-04', '2015-01-11 12:36:11');
INSERT INTO `base_material` VALUES ('15011112363379664702', '15453.500', '3434.111', '321', '14112517323482071549', '14112509532246124416', '10.698', '48.142', 'r', '0', '2015-01-03', '2015-01-11 12:36:33');
INSERT INTO `base_material` VALUES ('15011112370280609176', '10234.400', '1775.265', '287', '14112610134163511931', '14112509532246124416', '6.186', '35.660', '54', '0', '2015-01-02', '2015-01-11 12:37:02');

-- ----------------------------
-- Table structure for base_modules
-- ----------------------------
DROP TABLE IF EXISTS `base_modules`;
CREATE TABLE `base_modules` (
  `ID` varchar(64) NOT NULL COMMENT '模块ID',
  `NAME` varchar(64) NOT NULL COMMENT '模块名称',
  `MODULE_URL` varchar(255) DEFAULT NULL COMMENT '模块URL',
  `PARENT` varchar(64) DEFAULT NULL COMMENT '父模块ID',
  `LEVEL` decimal(1,0) DEFAULT NULL,
  `LEAF` decimal(1,0) DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `EXPANDED` decimal(1,0) DEFAULT '1' COMMENT '展开状态(1:展开;0:收缩)',
  `DISPLAY_INDEX` decimal(2,0) DEFAULT NULL COMMENT '显示顺序',
  `IS_DISPLAY` decimal(1,0) DEFAULT '1' COMMENT '是否显示 0:否 1:是',
  `EN_MODULE_NAME` varchar(64) DEFAULT NULL COMMENT '模块英文名称',
  `TYPE` decimal(1,0) DEFAULT '0',
  `BUTTON` varchar(255) DEFAULT NULL,
  `CREATETIME` varchar(64) DEFAULT NULL,
  `ICON_CSS` varchar(128) DEFAULT NULL COMMENT '图标或者样式',
  `INFORMATION` varchar(128) DEFAULT NULL COMMENT '节点说明',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统模块表';

-- ----------------------------
-- Records of base_modules
-- ----------------------------
INSERT INTO `base_modules` VALUES ('', '数据字典维护', 'dicusida', '1', '1', '1', '1', null, '1', null, '0', null, null, null, null);
INSERT INTO `base_modules` VALUES ('1', '系统设置', null, '0', '0', '0', '1', '2', '1', 'System Settings', '0', null, '2014-10-22 17:30:08', 'system_settings', null);
INSERT INTO `base_modules` VALUES ('11', '角色管理', 'admin/role/index', '1', '1', '1', '1', '2', '1', 'Role Management', '0', null, '2014-10-23 17:30:08', 'role', null);
INSERT INTO `base_modules` VALUES ('12', '用户管理', 'admin/user/index', '1', '1', '1', '1', '3', '1', 'User Management', '0', null, null, 'user', null);
INSERT INTO `base_modules` VALUES ('13', '模块管理', 'admin/module/index', '1', '1', '1', '1', '1', '1', 'Module Management', '0', null, null, 'module', null);
INSERT INTO `base_modules` VALUES ('14', '系统字段管理', 'field', '1', '1', '1', '1', '4', '1', 'field', '0', null, null, 'field', null);
INSERT INTO `base_modules` VALUES ('14102217302037280306', '服务器监控', 'sss', '1', '1', '0', '1', '4', '1', null, '0', null, '2014-10-22 17:30:08', null, null);
INSERT INTO `base_modules` VALUES ('14102217310099234658', '数据库监控', 'sss', '1', '1', '0', '1', '4', '1', null, '0', null, '2014-10-22 17:30:08', null, null);
INSERT INTO `base_modules` VALUES ('14102217311440633525', '数据采集', 'sss', '1', '1', '0', '1', '4', '1', null, '0', null, '2014-10-22 17:30:08', null, null);
INSERT INTO `base_modules` VALUES ('14112510581440633525', '供应商单价', 'admin/supplierPrice/index', '2', '1', '1', '1', '2', '1', null, '0', null, '2014-11-25 11:00:23', null, null);
INSERT INTO `base_modules` VALUES ('14112610181440633525', '进货单管理', 'admin/material/index', '2', '1', '1', '1', '3', '1', null, '0', null, '2014-11-26 10:21:43', null, null);
INSERT INTO `base_modules` VALUES ('14112711361553642876', '订货单数据分析', 'admin/materialAnalyse/index', '2', '1', '1', '1', '4', '1', null, '0', null, '2014-11-27 11:37:43', null, null);
INSERT INTO `base_modules` VALUES ('15011113002356778832', '出货商信息', 'admin/shipment/index', '3', '1', '1', '1', '1', '1', null, '0', null, '2015-01-11 13:03:23', null, null);
INSERT INTO `base_modules` VALUES ('2', '供应商管理', null, '0', '0', '0', '1', '3', '1', 'Operator', '0', null, '2014-11-28 12:23:13', 'abc', null);
INSERT INTO `base_modules` VALUES ('21', '供应商信息', 'admin/supplier/index', '2', '1', '1', '1', '1', '1', 'oprator', '0', null, null, 'cde', null);
INSERT INTO `base_modules` VALUES ('24', '首页', null, '0', '0', '1', '1', '1', '1', null, '0', null, null, 'dfgffd', null);
INSERT INTO `base_modules` VALUES ('25', '文件系统', null, '0', '0', '0', '1', '7', '1', null, '0', null, null, 'file', null);
INSERT INTO `base_modules` VALUES ('26', '文件上传下载', 'file', '25', '1', '1', '1', '1', '1', null, '0', null, null, 'fileInfo', null);
INSERT INTO `base_modules` VALUES ('28', '系统参数维护', 'sys', '1', '1', '1', '1', '1', '1', null, '0', null, null, null, null);
INSERT INTO `base_modules` VALUES ('3', '出货商管理', null, '0', '0', '0', '1', '4', '1', null, '0', null, '2015-01-11 12:48:32', null, null);
INSERT INTO `base_modules` VALUES ('30', '监控管理', 'guanli', '0', '0', '0', '0', '5', '1', null, '0', null, null, null, null);
INSERT INTO `base_modules` VALUES ('31', '登录日志', 'admin/syslog/index', '30', '1', '1', '0', '1', '1', null, '0', null, null, null, null);
INSERT INTO `base_modules` VALUES ('f14102310320163830931', '编辑', 'admin/TbsMenu/edit.html,admin/TbsMenu/baseDlg.html,admin/TbsMenu/save.html', '13', '2', '1', '0', '2', '1', null, '0', '<a href=\"javascript:void(0)\" onclick=\"javascript:tbsMenuGridAddAndEdit(\'修改\',\'admin/TbsMenu/save.html\',1)\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:\'icon-edit\'\">编辑 </a>', null, null, null);
INSERT INTO `base_modules` VALUES ('f14102311122288911705', '添加', 'admin/TbsRole/add.html,admin/role/baseDlg.html', '11', '2', '1', '0', '1', '1', null, '0', '<a href=\"javascript:void(0)\" onclick=\"javascript:tbsRoleGridAddAndEdit(\'添加 \',\'admin/role/save\',0)\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:\'icon-add\'\">添加</a>', '2014-10-23 11:12:22', null, null);
INSERT INTO `base_modules` VALUES ('f14102316404373976858', '编辑', 'admin/role/save', '11', '2', '1', '0', '2', '1', null, '0', '<a href=\"javascript:void(0)\" onclick=\"javascript:tbsRoleGridAddAndEdit(\'修改\',\'admin/role/save\',1)\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:\'icon-edit\'\">编辑 </a>', '2014-10-23 16:40:43', null, null);
INSERT INTO `base_modules` VALUES ('f14102317173621612176', '删除', 'admin/role/del', '11', '2', '1', '1', '3', '1', null, '0', '<a href=\"javascript:void(0)\" onclick=\"javascript:tbsRoleGridDel()\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:\'icon-remove\'\">删除</a>', '2014-10-23 17:17:36', null, null);
INSERT INTO `base_modules` VALUES ('f27', '新增', 'admin/module/save', '13', '2', '1', '1', '1', '1', 'add', '0', '<a href=\"javascript:void(0)\" onclick=\"javascript:tbsMenuGridAddAndEdit(\'添加 \',\'admin/module/save\',0)\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:\'icon-add\'\">添加</a>', '2014-10-22 10:50:23', 'add', null);

-- ----------------------------
-- Table structure for base_roles
-- ----------------------------
DROP TABLE IF EXISTS `base_roles`;
CREATE TABLE `base_roles` (
  `ROLE_ID` varchar(32) NOT NULL COMMENT '角色ID',
  `ROLE_NAME` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `CREATETIME` varchar(64) DEFAULT NULL,
  `ROLE_DESC` varchar(128) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of base_roles
-- ----------------------------
INSERT INTO `base_roles` VALUES ('14102315090160064421', '系统管理员', '2014-10-23 15:09:01', '系统管理员');
INSERT INTO `base_roles` VALUES ('14102315513993354563', '系统管理员1', '2014-10-23 15:51:39', '系统管理员1');
INSERT INTO `base_roles` VALUES ('14102315574954814331', 'qq', '2014-10-23 15:57:49', 'ww');
INSERT INTO `base_roles` VALUES ('14102315575865823399', 'qq', '2014-10-23 15:57:58', 'ww');
INSERT INTO `base_roles` VALUES ('14102316032846685733', 'QAZ', '2014-10-23 16:03:28', 'QAZ');
INSERT INTO `base_roles` VALUES ('14102316043966835973', 'QAZ', '2014-10-23 16:04:39', 'QAZ');
INSERT INTO `base_roles` VALUES ('3D84F5FEB9D44E28B5D91710C637283A', '管理员', '2014-10-01', '管理员');
INSERT INTO `base_roles` VALUES ('754701188F1F40C782171D0E3040E5D2', '测试角色', '2014-10-01', '测试角色');

-- ----------------------------
-- Table structure for base_role_module
-- ----------------------------
DROP TABLE IF EXISTS `base_role_module`;
CREATE TABLE `base_role_module` (
  `ROLE_MODULE_ID` varchar(64) NOT NULL COMMENT '角色模块ID',
  `ROLE_ID` varchar(64) NOT NULL COMMENT '角色ID',
  `MODULE_ID` varchar(64) NOT NULL COMMENT '模块ID',
  `MODULE_ID_FUN` varchar(64) DEFAULT NULL,
  `CREATETIME` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ROLE_MODULE_ID`),
  KEY `FK_BASE_ROL_REFERENCE_BASE_MOD` (`MODULE_ID`),
  KEY `FK_BASE_ROL_REFERENCE_BASE_ROL` (`ROLE_ID`),
  CONSTRAINT `ROLE_MODULE_ID` FOREIGN KEY (`MODULE_ID`) REFERENCES `base_modules` (`ID`),
  CONSTRAINT `ROLE_ROLES_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `base_roles` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色模块表';

-- ----------------------------
-- Records of base_role_module
-- ----------------------------
INSERT INTO `base_role_module` VALUES ('', '3D84F5FEB9D44E28B5D91710C637283A', '', null, null);
INSERT INTO `base_role_module` VALUES ('14102315582264588843', '14102315575865823399', 'f27', null, '2014-10-23 15:58:22');
INSERT INTO `base_role_module` VALUES ('14102316032816800687', '14102316032846685733', '11', null, '2014-10-23 16:03:28');
INSERT INTO `base_role_module` VALUES ('14102316032843267876', '14102316032846685733', 'f14102311122288911705', 'f14102311122288911705', '2014-10-23 16:03:28');
INSERT INTO `base_role_module` VALUES ('14102316032864558225', '14102316032846685733', 'f14102310320163830931', 'f14102310320163830931', '2014-10-23 16:03:28');
INSERT INTO `base_role_module` VALUES ('14102316032897208213', '14102316032846685733', '13', null, '2014-10-23 16:03:28');
INSERT INTO `base_role_module` VALUES ('14102316032997369414', '14102316032846685733', 'f27', 'f27', '2014-10-23 16:03:29');
INSERT INTO `base_role_module` VALUES ('14102316043920522658', '14102316043966835973', '11', null, '2014-10-23 16:04:39');
INSERT INTO `base_role_module` VALUES ('14102316043921410804', '14102316043966835973', 'f14102311122288911705', 'f14102311122288911705', '2014-10-23 16:04:39');
INSERT INTO `base_role_module` VALUES ('14102316043930019842', '14102316043966835973', 'f27', 'f27', '2014-10-23 16:04:39');
INSERT INTO `base_role_module` VALUES ('14102316043952822885', '14102316043966835973', '13', null, '2014-10-23 16:04:39');
INSERT INTO `base_role_module` VALUES ('14102316043992331145', '14102316043966835973', 'f14102310320163830931', 'f14102310320163830931', '2014-10-23 16:04:39');
INSERT INTO `base_role_module` VALUES ('14102317214619923822', '14102315090160064421', '26', null, '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317214625189171', '14102315090160064421', '2', null, '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317214634885630', '14102315090160064421', '25', null, '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317214640947583', '14102315090160064421', 'f14102310320163830931', 'f14102310320163830931', '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317214646333789', '14102315090160064421', 'f27', 'f27', '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317214649126355', '14102315090160064421', 'f14102316404373976858', 'f14102316404373976858', '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317214665325212', '14102315090160064421', 'f14102317173621612176', 'f14102317173621612176', '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317214670145668', '14102315090160064421', '11', null, '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317214672698599', '14102315090160064421', '1', null, '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317214673010897', '14102315090160064421', '24', null, '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317214675608744', '14102315090160064421', '14', null, '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317214682354219', '14102315090160064421', 'f14102311122288911705', 'f14102311122288911705', '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317214682985279', '14102315090160064421', '21', null, '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317214685231348', '14102315090160064421', '14102217310099234658', null, '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317214689116950', '14102315090160064421', '12', null, '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317214691663034', '14102315090160064421', '14102217302037280306', null, '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317214693113682', '14102315090160064421', '13', null, '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317214696873324', '14102315090160064421', '14102217311440633525', null, '2014-10-23 17:21:46');
INSERT INTO `base_role_module` VALUES ('14102317221591325010', '3D84F5FEB9D44E28B5D91710C637283A', '1', null, '2014-10-23 17:22:15');
INSERT INTO `base_role_module` VALUES ('14102317221621532889', '3D84F5FEB9D44E28B5D91710C637283A', 'f14102310320163830931', 'f14102310320163830931', '2014-10-23 17:22:16');
INSERT INTO `base_role_module` VALUES ('14102317221639010160', '3D84F5FEB9D44E28B5D91710C637283A', '14102217310099234658', null, '2014-10-23 17:22:16');
INSERT INTO `base_role_module` VALUES ('14102317221639659526', '3D84F5FEB9D44E28B5D91710C637283A', 'f14102311122288911705', 'f14102311122288911705', '2014-10-23 17:22:16');
INSERT INTO `base_role_module` VALUES ('14102317221645248789', '3D84F5FEB9D44E28B5D91710C637283A', '26', null, '2014-10-23 17:22:16');
INSERT INTO `base_role_module` VALUES ('14102317221645706780', '3D84F5FEB9D44E28B5D91710C637283A', '24', null, '2014-10-23 17:22:16');
INSERT INTO `base_role_module` VALUES ('14102317221654541587', '3D84F5FEB9D44E28B5D91710C637283A', '11', null, '2014-10-23 17:22:16');
INSERT INTO `base_role_module` VALUES ('14102317221660006198', '3D84F5FEB9D44E28B5D91710C637283A', 'f27', 'f27', '2014-10-23 17:22:16');
INSERT INTO `base_role_module` VALUES ('14102317221664242882', '3D84F5FEB9D44E28B5D91710C637283A', '2', null, '2014-10-23 17:22:16');
INSERT INTO `base_role_module` VALUES ('14102317221664446047', '3D84F5FEB9D44E28B5D91710C637283A', '12', null, '2014-10-23 17:22:16');
INSERT INTO `base_role_module` VALUES ('14102317221669041817', '3D84F5FEB9D44E28B5D91710C637283A', '14', null, '2014-10-23 17:22:16');
INSERT INTO `base_role_module` VALUES ('14102317221670573806', '3D84F5FEB9D44E28B5D91710C637283A', '13', null, '2014-10-23 17:22:16');
INSERT INTO `base_role_module` VALUES ('14102317221685189330', '3D84F5FEB9D44E28B5D91710C637283A', '14102217311440633525', null, '2014-10-23 17:22:16');
INSERT INTO `base_role_module` VALUES ('14102317221687182307', '3D84F5FEB9D44E28B5D91710C637283A', 'f14102316404373976858', 'f14102316404373976858', '2014-10-23 17:22:16');
INSERT INTO `base_role_module` VALUES ('14102317221688431658', '3D84F5FEB9D44E28B5D91710C637283A', '21', null, '2014-10-23 17:22:16');
INSERT INTO `base_role_module` VALUES ('14102317221691168552', '3D84F5FEB9D44E28B5D91710C637283A', '25', null, '2014-10-23 17:22:16');
INSERT INTO `base_role_module` VALUES ('14102317221692133799', '3D84F5FEB9D44E28B5D91710C637283A', '14102217302037280306', null, '2014-10-23 17:22:16');
INSERT INTO `base_role_module` VALUES ('14102317221696508582', '3D84F5FEB9D44E28B5D91710C637283A', 'f14102317173621612176', 'f14102317173621612176', '2014-10-23 17:22:16');
INSERT INTO `base_role_module` VALUES ('14103014221696508582', '3D84F5FEB9D44E28B5D91710C637283A', '28', null, '2014-10-30 17:22:16');
INSERT INTO `base_role_module` VALUES ('14103014271696508582', '3D84F5FEB9D44E28B5D91710C637283A', '30', null, '2014-10-30 14:27:16');
INSERT INTO `base_role_module` VALUES ('14103014291696508582', '3D84F5FEB9D44E28B5D91710C637283A', '31', null, '2014-10-30 14:29:16');
INSERT INTO `base_role_module` VALUES ('14112511012423212345', '3D84F5FEB9D44E28B5D91710C637283A', '14112510581440633525', null, '2014-11-25 11:02:21');
INSERT INTO `base_role_module` VALUES ('14112610222434545636', '3D84F5FEB9D44E28B5D91710C637283A', '14112610181440633525', null, '2014-11-26 10:22:47');
INSERT INTO `base_role_module` VALUES ('14112713572434345707', '3D84F5FEB9D44E28B5D91710C637283A', '14112711361553642876', null, '2014-11-27 13:57:58');
INSERT INTO `base_role_module` VALUES ('15011112482434345734', '3D84F5FEB9D44E28B5D91710C637283A', '3', null, '2015-01-11 12:50:21');
INSERT INTO `base_role_module` VALUES ('15011113042356778832', '3D84F5FEB9D44E28B5D91710C637283A', '15011113002356778832', null, '2015-01-11 13:04:11');

-- ----------------------------
-- Table structure for base_shipment
-- ----------------------------
DROP TABLE IF EXISTS `base_shipment`;
CREATE TABLE `base_shipment` (
  `id` varchar(64) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `telephone` varchar(32) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `isDelete` decimal(1,0) NOT NULL DEFAULT '0',
  `createtime` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_shipment
-- ----------------------------
INSERT INTO `base_shipment` VALUES ('15011122164348670883', '12', '15812334455', '2121e', '15812334455', '0', '2015-01-11 22:16:43');
INSERT INTO `base_shipment` VALUES ('15011122172351510371', '21', '15812334455', '21', '15812334455', '0', '2015-01-11 22:17:23');
INSERT INTO `base_shipment` VALUES ('15011122174915466456', '1', '15812334456', '1', '15812334455', '1', '2015-01-11 22:17:49');

-- ----------------------------
-- Table structure for base_supplier
-- ----------------------------
DROP TABLE IF EXISTS `base_supplier`;
CREATE TABLE `base_supplier` (
  `id` varchar(64) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(32) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `isDelete` decimal(1,0) NOT NULL,
  `createTime` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_supplier
-- ----------------------------
INSERT INTO `base_supplier` VALUES ('14112509391887431288', 'aaaaaa', 'aaaa', '0123222222', 'aaa', '0', '2014-11-25 09:39:18');
INSERT INTO `base_supplier` VALUES ('14112509532246124416', '常州市三德利畜禽养殖有限公司', '金坛市朱林镇东大街666号', '0519—68083888', null, '0', '2014-11-25 09:53:22');
INSERT INTO `base_supplier` VALUES ('14112509542724052949', 'aasa', 'aas', '088722112', 'aaa', '0', '2014-11-25 09:54:27');
INSERT INTO `base_supplier` VALUES ('14112509582172462570', 'aaass', 'aaa', '03233232', '3223sasss', '1', '2014-11-25 09:58:21');
INSERT INTO `base_supplier` VALUES ('15011121455833005876', '1', '1', '15922222222', '11', '1', '2015-01-11 21:45:58');

-- ----------------------------
-- Table structure for base_supplier_price
-- ----------------------------
DROP TABLE IF EXISTS `base_supplier_price`;
CREATE TABLE `base_supplier_price` (
  `id` varchar(64) NOT NULL,
  `supplierId` varchar(64) DEFAULT NULL,
  `price` decimal(16,3) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `isDelete` decimal(1,0) DEFAULT NULL,
  `createTime` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_supplier_price
-- ----------------------------
INSERT INTO `base_supplier_price` VALUES ('14112514155870082764', '14112509532246124416', '7.300', '随机价格', '0', '2014-11-25 14:15:58');
INSERT INTO `base_supplier_price` VALUES ('14112514181852179744', '14112509532246124416', '5.800', 'cea', '1', '2014-11-25 14:18:18');
INSERT INTO `base_supplier_price` VALUES ('14112517323482071549', '14112509532246124416', '4.500', 'dddd', '0', '2014-11-25 17:32:34');
INSERT INTO `base_supplier_price` VALUES ('14112517342688563745', '14112509542724052949', '5.400', 'ew', '0', '2014-11-25 17:34:26');
INSERT INTO `base_supplier_price` VALUES ('14112517345295723292', '14112509532246124416', '4.500', '4.44', '0', '2014-11-25 17:34:52');
INSERT INTO `base_supplier_price` VALUES ('14112608480414293294', '14112509582172462570', '5.755', '5555', '0', '2014-11-26 08:48:04');
INSERT INTO `base_supplier_price` VALUES ('14112608523998004042', '14112509542724052949', '5.246', '3232', '1', '2014-11-26 08:52:39');
INSERT INTO `base_supplier_price` VALUES ('14112610092887296863', '14112509532246124416', '5.845', 'rrrr', '0', '2014-11-26 10:09:28');
INSERT INTO `base_supplier_price` VALUES ('14112610134163511931', '14112509532246124416', '5.765', '33443', '0', '2014-11-26 10:13:41');

-- ----------------------------
-- Table structure for base_users
-- ----------------------------
DROP TABLE IF EXISTS `base_users`;
CREATE TABLE `base_users` (
  `USER_ID` varchar(64) NOT NULL COMMENT '用户ID',
  `ACCOUNT` varchar(64) NOT NULL COMMENT '账号',
  `PASSWORD` varchar(128) NOT NULL COMMENT '密码\r\n            DigestUtils.md5Hex(DigestUtils.md5Hex(password){account})',
  `REAL_NAME` varchar(64) DEFAULT NULL COMMENT '用户真实姓名',
  `SEX` decimal(1,0) DEFAULT NULL COMMENT '性别 0:男 1:女',
  `EMAIL` varchar(64) DEFAULT NULL COMMENT '电子邮件地址',
  `MOBILE` varchar(32) DEFAULT NULL COMMENT '手机',
  `OFFICE_PHONE` varchar(32) DEFAULT NULL COMMENT '办公电话',
  `ERROR_COUNT` decimal(2,0) DEFAULT '0' COMMENT '密码错误次数',
  `LAST_LOGIN_TIME` datetime DEFAULT NULL COMMENT '上次登录时间',
  `LAST_LOGIN_IP` varchar(32) DEFAULT NULL COMMENT '上次登录IP地址',
  `isAdmin` int(2) DEFAULT NULL,
  `REMARK` varchar(128) DEFAULT NULL COMMENT '备注',
  `CREATETIME` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of base_users
-- ----------------------------
INSERT INTO `base_users` VALUES ('14111910331750592825', 'bbbbbbbc', 'a50d68bde21063adb15732f2302d31fc', 'bbbbbbbc', '0', 'bbbb@163.com', '15655555555', 'wqwqw', '0', null, null, null, null, '2014-11-19 10:33:17');
INSERT INTO `base_users` VALUES ('14111911243217446963', '222222', '68d25077848a3b4c07f4e931d8fe541a', '222222', '0', '222222@163.com', '15622222222', null, '3', '2014-11-19 12:38:56', '本地', null, null, '2014-11-19 11:24:52');
INSERT INTO `base_users` VALUES ('14112017374179396348', 'testeTes', '733e86588c321224964606f3a33d23de', 'testeTes', '2', 'testeTes@163.com', '15612345678', null, '0', null, null, null, null, '2014-11-20 17:37:41');
INSERT INTO `base_users` VALUES ('14112410505845035054', 'AAAAAAAA', '56831a4c485a924e5b1cf03cbff1975c', 'AAAAAAAA', '0', 'AAAAAAAA@163.com', '15677777777', null, '0', '2014-11-24 10:51:27', '本地', null, null, '2014-11-24 10:50:58');
INSERT INTO `base_users` VALUES ('2eb1d505-2d92-11e4-998d-00ff5836c4af', 'proxies', '9ee5defb156035d30373c8f83e0e1b40', '管理员', '0', 'ys_tar@163.com', '15844324567', '025-34566578', '0', '2014-08-27 11:09:30', '0:0:0:0:0:0:0:1', '1', null, '2014-01-10 13:12:12');
INSERT INTO `base_users` VALUES ('38AD4E59A9D64F95AE77AAB16D198DDA', 'admin', '0570566bfb2ca3080935a8c65dfe5b90', '超级管理员', '0', 'ys_tar@163.com', '119', '110', '0', '2015-01-11 22:16:21', '本地', '0', '用户信息', '2014-10-10 10:11:13');
INSERT INTO `base_users` VALUES ('E9F8563613364D13AC7F503591EE8F6E', 'test', '97a86f30b80e18bcc29b43ccd06ff61f', '测试用户', '0', 'ys_tar@163.com', '119', '110', '0', '2014-10-23 11:33:07', '本地', '1', null, '2014-08-20 12:12:12');

-- ----------------------------
-- Table structure for base_user_role
-- ----------------------------
DROP TABLE IF EXISTS `base_user_role`;
CREATE TABLE `base_user_role` (
  `ID` varchar(64) NOT NULL,
  `USER_ID` varchar(64) NOT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(64) NOT NULL COMMENT '角色ID',
  `CREATETIME` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_BASE_USE_REFERENCE_BASE_ROL` (`ROLE_ID`),
  KEY `FK_BASE_USE_REFERENCE_BASE_USE` (`USER_ID`),
  CONSTRAINT `FK_BASE_USE_REFERENCE_BASE_ROL` FOREIGN KEY (`ROLE_ID`) REFERENCES `base_roles` (`ROLE_ID`),
  CONSTRAINT `FK_BASE_USE_REFERENCE_BASE_USE` FOREIGN KEY (`USER_ID`) REFERENCES `base_users` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of base_user_role
-- ----------------------------
INSERT INTO `base_user_role` VALUES ('13080110395521174386', 'E9F8563613364D13AC7F503591EE8F6E', '754701188F1F40C782171D0E3040E5D2', null);
INSERT INTO `base_user_role` VALUES ('13080110395536220463', '38AD4E59A9D64F95AE77AAB16D198DDA', '3D84F5FEB9D44E28B5D91710C637283A', null);
INSERT INTO `base_user_role` VALUES ('14111910331767151743', '14111910331750592825', '14102316043966835973', '2014-11-19 10:33:17');
INSERT INTO `base_user_role` VALUES ('14111911245824925046', '14111911243217446963', '14102316043966835973', '2014-11-19 11:24:58');
INSERT INTO `base_user_role` VALUES ('14112011010443794317', '2eb1d505-2d92-11e4-998d-00ff5836c4af', '3D84F5FEB9D44E28B5D91710C637283A', '2014-11-20 11:01:04');
INSERT INTO `base_user_role` VALUES ('14112017374144608841', '14112017374179396348', '14102316043966835973', '2014-11-20 17:37:41');
INSERT INTO `base_user_role` VALUES ('14112410505951568380', '14112410505845035054', '14102315513993354563', '2014-11-24 10:50:59');
INSERT INTO `base_user_role` VALUES ('14112410505996796007', '14112410505845035054', '14102315574954814331', '2014-11-24 10:50:59');

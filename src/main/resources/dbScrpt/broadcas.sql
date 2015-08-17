/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : broadcas

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2014-08-26 17:33:20
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
INSERT INTO `base_fields` VALUES ('a4dc2c8f-f041-11e3-8ae6-ec55f9ed', 'sex', '性别', '3', '保密', '0', '4');
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
-- Table structure for base_modules
-- ----------------------------
DROP TABLE IF EXISTS `base_modules`;
CREATE TABLE `base_modules` (
  `MODULE_ID` decimal(9,0) NOT NULL COMMENT '模块ID',
  `MODULE_NAME` varchar(64) NOT NULL COMMENT '模块名称',
  `MODULE_URL` varchar(64) DEFAULT NULL COMMENT '模块URL',
  `PARENT_ID` decimal(9,0) DEFAULT NULL COMMENT '父模块ID',
  `LEAF` decimal(1,0) DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `EXPANDED` decimal(1,0) DEFAULT NULL COMMENT '展开状态(1:展开;0:收缩)',
  `DISPLAY_INDEX` decimal(2,0) DEFAULT NULL COMMENT '显示顺序',
  `IS_DISPLAY` decimal(1,0) DEFAULT NULL COMMENT '是否显示 0:否 1:是',
  `EN_MODULE_NAME` varchar(64) DEFAULT NULL COMMENT '模块英文名称',
  `ICON_CSS` varchar(128) DEFAULT NULL COMMENT '图标或者样式',
  `INFORMATION` varchar(128) DEFAULT NULL COMMENT '节点说明',
  PRIMARY KEY (`MODULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统模块表';

-- ----------------------------
-- Records of base_modules
-- ----------------------------
INSERT INTO `base_modules` VALUES ('1', '系统设置', null, '0', '0', '1', '1', '1', 'System Settings', 'system_settings', null);
INSERT INTO `base_modules` VALUES ('2', '供应商管理', null, '0', '0', '1', '2', '1', 'Operator', 'abc', null);
INSERT INTO `base_modules` VALUES ('11', '角色管理', '/role', '1', '1', '0', '3', '1', 'Role Management', 'role', null);
INSERT INTO `base_modules` VALUES ('12', '用户管理', '/user', '1', '1', '0', '2', '1', 'User Management', 'user', null);
INSERT INTO `base_modules` VALUES ('13', '模块管理', '/module', '1', '1', '0', '1', '1', 'Module Management', 'module', null);
INSERT INTO `base_modules` VALUES ('14', '系统字段管理', '/field', '1', '1', '1', '4', '1', 'field', 'field', null);
INSERT INTO `base_modules` VALUES ('21', '供应商信息', '/oprator', '2', '1', '0', '1', '1', 'oprator', 'cde', null);
INSERT INTO `base_modules` VALUES ('22', '公司子门户', null, '0', '0', '0', '1', '1', null, 'company', null);
INSERT INTO `base_modules` VALUES ('23', '??', '/module', '22', '0', '0', '1', '1', null, 'main', null);

-- ----------------------------
-- Table structure for base_roles
-- ----------------------------
DROP TABLE IF EXISTS `base_roles`;
CREATE TABLE `base_roles` (
  `ROLE_ID` varchar(32) NOT NULL COMMENT '角色ID',
  `ROLE_NAME` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `ROLE_DESC` varchar(128) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of base_roles
-- ----------------------------
INSERT INTO `base_roles` VALUES ('3D84F5FEB9D44E28B5D91710C637283A', '管理员', '管理员');
INSERT INTO `base_roles` VALUES ('754701188F1F40C782171D0E3040E5D2', '测试角色', '测试角色');

-- ----------------------------
-- Table structure for base_role_module
-- ----------------------------
DROP TABLE IF EXISTS `base_role_module`;
CREATE TABLE `base_role_module` (
  `ROLE_MODULE_ID` varchar(32) NOT NULL COMMENT '角色模块ID',
  `ROLE_ID` varchar(32) NOT NULL COMMENT '角色ID',
  `MODULE_ID` decimal(9,0) NOT NULL COMMENT '模块ID',
  PRIMARY KEY (`ROLE_MODULE_ID`),
  KEY `FK_BASE_ROL_REFERENCE_BASE_MOD` (`MODULE_ID`),
  KEY `FK_BASE_ROL_REFERENCE_BASE_ROL` (`ROLE_ID`),
  CONSTRAINT `FK_BASE_ROL_REFERENCE_BASE_MOD` FOREIGN KEY (`MODULE_ID`) REFERENCES `base_modules` (`MODULE_ID`),
  CONSTRAINT `FK_BASE_ROL_REFERENCE_BASE_ROL` FOREIGN KEY (`ROLE_ID`) REFERENCES `base_roles` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色模块表';

-- ----------------------------
-- Records of base_role_module
-- ----------------------------
INSERT INTO `base_role_module` VALUES ('3B84F28C3E5C428DAA8FAB14661AF2F5', '3D84F5FEB9D44E28B5D91710C637283A', '2');
INSERT INTO `base_role_module` VALUES ('4E46393B3BFC4F70AD67C667744F4653', '3D84F5FEB9D44E28B5D91710C637283A', '13');
INSERT INTO `base_role_module` VALUES ('70D092E4C4644417AF64963B7DAB7D13', '3D84F5FEB9D44E28B5D91710C637283A', '21');
INSERT INTO `base_role_module` VALUES ('75E5CFFE05F644099BDE0A98B15D7C5C', '3D84F5FEB9D44E28B5D91710C637283A', '12');
INSERT INTO `base_role_module` VALUES ('86D6AFEC6E4642E5840831CE0E1A307D', '3D84F5FEB9D44E28B5D91710C637283A', '14');
INSERT INTO `base_role_module` VALUES ('8C11EF2FAC9A4B94AE92FE3DE614B31D', '3D84F5FEB9D44E28B5D91710C637283A', '11');
INSERT INTO `base_role_module` VALUES ('91F1012B942441BBBFEBB01E0859F453', '754701188F1F40C782171D0E3040E5D2', '2');
INSERT INTO `base_role_module` VALUES ('BB6294112EEE412F9F5C2E717958E10D', '754701188F1F40C782171D0E3040E5D2', '21');
INSERT INTO `base_role_module` VALUES ('E730317B9410429DB6F9A00ABF9332FF', '3D84F5FEB9D44E28B5D91710C637283A', '1');

-- ----------------------------
-- Table structure for base_users
-- ----------------------------
DROP TABLE IF EXISTS `base_users`;
CREATE TABLE `base_users` (
  `USER_ID` varchar(32) NOT NULL COMMENT '用户ID',
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
  `REMARK` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of base_users
-- ----------------------------
INSERT INTO `base_users` VALUES ('38AD4E59A9D64F95AE77AAB16D198DDA', 'admin', '0570566bfb2ca3080935a8c65dfe5b90', '超级管理员', '0', 'ys_tar@163.com', '119', '110', '0', '2014-08-26 17:31:33', '127.0.0.1', '用户信息');
INSERT INTO `base_users` VALUES ('E9F8563613364D13AC7F503591EE8F6E', 'test', 'c0f40fddd2c81de6bd37023d8219782f', '测试用户', '0', 'test@qq.com', '119', '110', '0', null, null, null);

-- ----------------------------
-- Table structure for base_user_role
-- ----------------------------
DROP TABLE IF EXISTS `base_user_role`;
CREATE TABLE `base_user_role` (
  `USER_ROLE_ID` varchar(32) NOT NULL COMMENT '用户角色ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(32) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`USER_ROLE_ID`),
  KEY `FK_BASE_USE_REFERENCE_BASE_ROL` (`ROLE_ID`),
  KEY `FK_BASE_USE_REFERENCE_BASE_USE` (`USER_ID`),
  CONSTRAINT `FK_BASE_USE_REFERENCE_BASE_ROL` FOREIGN KEY (`ROLE_ID`) REFERENCES `base_roles` (`ROLE_ID`),
  CONSTRAINT `FK_BASE_USE_REFERENCE_BASE_USE` FOREIGN KEY (`USER_ID`) REFERENCES `base_users` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of base_user_role
-- ----------------------------
INSERT INTO `base_user_role` VALUES ('543E84984C4D4E76BE120174225A8422', 'E9F8563613364D13AC7F503591EE8F6E', '754701188F1F40C782171D0E3040E5D2');
INSERT INTO `base_user_role` VALUES ('D1424A23EB254AE59FFE604343E2BBB3', '38AD4E59A9D64F95AE77AAB16D198DDA', '3D84F5FEB9D44E28B5D91710C637283A');

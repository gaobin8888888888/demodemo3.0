/*
Navicat MySQL Data Transfer

Source Server         : 11
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : db_travellerwayrecommend

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-08-15 18:55:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_change`
-- ----------------------------
DROP TABLE IF EXISTS `tb_change`;
CREATE TABLE `tb_change` (
  `c_place` varchar(20) NOT NULL COMMENT '站点',
  `c_last_sid` varchar(11) NOT NULL COMMENT '上一个地铁id',
  `c_now_sid` varchar(11) NOT NULL COMMENT '接下来地铁id',
  `c_pass_time` varchar(20) NOT NULL COMMENT '换车需要的时间',
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`c_id`),
  KEY `c_last_sid` (`c_last_sid`),
  KEY `c_now_sid` (`c_now_sid`),
  KEY `c_place` (`c_place`),
  CONSTRAINT `c_last_sid` FOREIGN KEY (`c_last_sid`) REFERENCES `tb_subway` (`s_sid`),
  CONSTRAINT `c_now_sid` FOREIGN KEY (`c_now_sid`) REFERENCES `tb_subway` (`s_sid`),
  CONSTRAINT `c_place` FOREIGN KEY (`c_place`) REFERENCES `tb_subway` (`s_place`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_change
-- ----------------------------
INSERT INTO `tb_change` VALUES ('东直门', '2号线', '机场线', '00：04：20', '1');
INSERT INTO `tb_change` VALUES ('北京西站', '9号线', '7号线', '00：00：20', '2');
INSERT INTO `tb_change` VALUES ('国家图书馆', '4号线', '9号线', '00：00：30', '3');
INSERT INTO `tb_change` VALUES ('宣武门', '4号线', '2号线', '00：00：40', '4');
INSERT INTO `tb_change` VALUES ('菜市口', '4号线', '7号线', '00：01：00', '5');
INSERT INTO `tb_change` VALUES ('西直门', '4号线', '2号线', '00：01：20', '6');
INSERT INTO `tb_change` VALUES ('菜市口', '7号线', '4号线', '00：00：20', '7');
INSERT INTO `tb_change` VALUES ('国家图书馆', '9号线', '4号线', '00：00：30', '8');

-- ----------------------------
-- Table structure for `tb_motorcar`
-- ----------------------------
DROP TABLE IF EXISTS `tb_motorcar`;
CREATE TABLE `tb_motorcar` (
  `m_id` int(11) NOT NULL AUTO_INCREMENT,
  `m_place_id` int(11) NOT NULL COMMENT '两个城市路径编号',
  `m_start_place` varchar(20) NOT NULL,
  `m_start_time` varchar(20) NOT NULL,
  `m_end_place` varchar(20) NOT NULL,
  `m_end_time` varchar(20) NOT NULL,
  `m_pass_time` varchar(20) NOT NULL,
  `m_second_rate_price` double NOT NULL,
  `m_first_rate_price` double NOT NULL,
  `m_business_rate_price` double NOT NULL,
  `m_no` varchar(20) NOT NULL,
  PRIMARY KEY (`m_id`),
  KEY `m_place_id` (`m_place_id`),
  CONSTRAINT `m_place_id` FOREIGN KEY (`m_place_id`) REFERENCES `tb_traffic` (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_motorcar
-- ----------------------------
INSERT INTO `tb_motorcar` VALUES ('1', '1', '北京南站', '07：20：00', '上海虹桥', '13：08：00', '05：48：00', '553', '933', '1748', 'G105');
INSERT INTO `tb_motorcar` VALUES ('2', '1', '北京南站', '07：50：00', '上海虹桥', '13：12：00', '05：22：00', '553', '933', '1748', 'G143');
INSERT INTO `tb_motorcar` VALUES ('3', '1', '北京南站', '08：05：00', '上海虹桥', '13：46：00', '05：41：00', '553', '933', '1748', 'G107');
INSERT INTO `tb_motorcar` VALUES ('4', '1', '北京南站', '08：35：00', '上海虹桥', '14：22：00', '05：47：00', '553', '933', '1748', 'G111');
INSERT INTO `tb_motorcar` VALUES ('5', '1', '北京南站', '08：50：00', '上海虹桥', '14：33：00', '05：43：00', '553', '933', '1748', 'G113');
INSERT INTO `tb_motorcar` VALUES ('6', '1', '北京南站', '09：00：00', '上海虹桥', '13：28：00', '04：28：00', '553', '933', '1748', 'G1');
INSERT INTO `tb_motorcar` VALUES ('7', '6', '北京西站', '08：05：00', '正定机场', '09：15：00', '01：10：00', '111.5', '179.5', '353.5', 'G627');
INSERT INTO `tb_motorcar` VALUES ('8', '1', '北京南站', '09：15：00', '上海虹桥', '14：49：00', '05：34：00', '553', '933', '1748', 'G41');
INSERT INTO `tb_motorcar` VALUES ('9', '1', '北京南站', '09：20：00', '上海虹桥', '14：59：00', '05：39：00', '553', '933', '1748', 'G115');
INSERT INTO `tb_motorcar` VALUES ('10', '1', '北京南站', '09：25：00', '上海虹桥', '15：37：00', '06：12：00', '553', '933', '1748', 'G117');
INSERT INTO `tb_motorcar` VALUES ('11', '1', '北京南站', '10：00：00', '上海虹桥', '14：28：00', '04：28：00', '553', '933', '1748', 'G7');
INSERT INTO `tb_motorcar` VALUES ('12', '1', '北京南站', '10：05：00', '上海虹桥', '15：47：00', '05：42：00', '553', '933', '1748', 'G119');
INSERT INTO `tb_motorcar` VALUES ('13', '6', '北京西站', '09：22：00', '正定机场', '10：25：00', '01：03：00', '111.5', '179.5', '353.5', 'G571');
INSERT INTO `tb_motorcar` VALUES ('14', '6', '北京西站', '12：13：00', '正定机场', '13：23：00', '01：10：00', '111.5', '179.5', '353.5', 'G67');
INSERT INTO `tb_motorcar` VALUES ('15', '6', '北京西站', '12：44：00', '正定机场', '14：05：00', '01：21：00', '111.5', '179.5', '353.5', 'G6703');
INSERT INTO `tb_motorcar` VALUES ('16', '6', '北京西站', '13：50：00', '正定机场', '15：07：00', '01：17：00', '111.5', '179.5', '353.5', 'G661');
INSERT INTO `tb_motorcar` VALUES ('17', '6', '北京西站', '15：03：00', '正定机场', '16：06：00', '01：03：00', '111.5', '179.5', '353.5', 'G615');

-- ----------------------------
-- Table structure for `tb_plane`
-- ----------------------------
DROP TABLE IF EXISTS `tb_plane`;
CREATE TABLE `tb_plane` (
  `l_id` int(11) NOT NULL AUTO_INCREMENT,
  `l_place_id` int(11) NOT NULL,
  `l_company` varchar(255) NOT NULL,
  `l_model` varchar(255) NOT NULL,
  `l_start_place` varchar(20) NOT NULL,
  `l_start_time` varchar(20) NOT NULL,
  `l_end_place` varchar(20) NOT NULL,
  `l_end_time` varchar(20) NOT NULL,
  `l_pass_time` varchar(20) NOT NULL,
  `l_ontime_rate` double NOT NULL,
  `l_price` double NOT NULL,
  `l_sale` double NOT NULL,
  PRIMARY KEY (`l_id`),
  KEY `l_place_id` (`l_place_id`),
  CONSTRAINT `l_place_id` FOREIGN KEY (`l_place_id`) REFERENCES `tb_traffic` (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_plane
-- ----------------------------
INSERT INTO `tb_plane` VALUES ('1', '3', '东方航空MU5138', '空中客车 A330(大型)', '首都国际机场', '07：00：00', '虹桥国际机场', '09：20：00', '02：20：00', '0.97', '600', '0.41');
INSERT INTO `tb_plane` VALUES ('2', '3', '东方航空MU5152', '空中客车 A321(中型)', '首都国际机场', '11：30：00', '虹桥国际机场', '14：00：00', '02：30：00', '0.83', '640', '0.43');
INSERT INTO `tb_plane` VALUES ('3', '3', '东方航空MU5104', '空中客车 A330(大型)', '首都国际机场', '09：00：00', '虹桥国际机场', '11：15：00', '02：15：00', '0.93', '660', '0.45');
INSERT INTO `tb_plane` VALUES ('4', '2', '海南航空HU7611', '波音 787-9(中型)', '首都国际机场', '07：40：00', '浦东国际机场', '09：55：00', '02：15：00', '0.93', '705', '0.48');
INSERT INTO `tb_plane` VALUES ('5', '3', '海南航空HU7605', '波音 787-9(大型)', '首都国际机场', '07：45：00', '虹桥国际机场', '10：00：00', '02：15：00', '0.97', '705', '0.48');
INSERT INTO `tb_plane` VALUES ('6', '7', '春秋航空9C8904', '空中客车 A320(中型) ', '正定机场', '16：00：00', '虹桥国际机场', '18：05：00', '02：05：00', '0.9', '679', '0.57');
INSERT INTO `tb_plane` VALUES ('7', '2', '东方航空MU5183', '空中客车 A321(中型)', '首都国际机场', '07：35：00', '浦东国际机场', '09：50：00', '02：15：00', '1', '830', '0.56');
INSERT INTO `tb_plane` VALUES ('8', '2', '东方航空MU563', '空中客车 A330(大型)', '首都国际机场', '17：15：00', '浦东国际机场', '19：30：00', '02：15：00', '0.87', '980', '0.66');
INSERT INTO `tb_plane` VALUES ('9', '2', '中国国航CA1835', '空中客车 A330-300(大型)', '首都国际机场', '08：05：00', '浦东国际机场', '10：15：00', '02：10：00', '1', '1100', '0.74');
INSERT INTO `tb_plane` VALUES ('10', '2', '中国国航CA1883', '全新空客 A350-900', '首都国际机场', '20：20：00', '浦东国际机场', '22：35：00', '02：15：00', '0.9', '1100', '0.74');
INSERT INTO `tb_plane` VALUES ('11', '2', '东方航空MU271', '空中客车 A321(中型)', '首都国际机场', '13：05：00', '浦东国际机场', '15：20：00', '02：15：00', '1', '1480', '0.99');
INSERT INTO `tb_plane` VALUES ('12', '2', '东方航空MU5130', '空中客车 A320-300(中型)', '首都国际机场', '19：35：00', '浦东国际机场', '22：00：00', '01：25：00', '0.8', '1480', '0.99');
INSERT INTO `tb_plane` VALUES ('13', '3', '东方航空MU5106', '空中客车 A330(大型)', '首都国际机场', '10：00：00', '虹桥国际机场', '12：15：00', '02：15：00', '0.93', '660', '0.45');
INSERT INTO `tb_plane` VALUES ('14', '3', '海南航空HU7605', '波音 787-9(大型)', '首都国际机场', '07：45：00', '虹桥国际机场', '10：00：00', '02：15：00', '0.97', '705', '0.48');
INSERT INTO `tb_plane` VALUES ('15', '3', '海南航空HU7603', '波音 737-800(中型)', '首都国际机场', '20：35：00', '虹桥国际机场', '22：55：00', '02：20：00', '0.93', '705', '0.48');
INSERT INTO `tb_plane` VALUES ('16', '3', '上海航空FM9102', '波音 737-900ER(中型)', '首都国际机场', '20：20：00', '虹桥国际机场', '22：50：00', '02：30：00', '1', '760', '0.62');
INSERT INTO `tb_plane` VALUES ('17', '3', '海南航空HU7607', '空中客车A350(大型)', '首都国际机场', '08：48：00', '虹桥国际机场', '11：00：00', '02：12：00', '0.9', '785', '0.53');
INSERT INTO `tb_plane` VALUES ('18', '3', '南方航空CZ3907', '空中客车 A330(大型)', '首都国际机场', '08：15：00', '虹桥国际机场', '10：30：00', '02：15：00', '0.93', '790', '0.64');
INSERT INTO `tb_plane` VALUES ('19', '3', '东方航空MU5156', '空中客车 A330(大型)', '首都国际机场', '08：30：00', '虹桥国际机场', '10：40：00', '02：10：00', '0.97', '790', '0.54');
INSERT INTO `tb_plane` VALUES ('20', '5', '中国国航CA1325', '空中客车 A320(中型)', '首都国际机场', '17：35：00', '长水国际机场', '21：25：00', '03：50：00', '0.57', '700', '0.23');
INSERT INTO `tb_plane` VALUES ('21', '5', '东方航空MU5292', '波音737(中型)', '首都国际机场', '10：00：00', '长水国际机场', '15：10：00', '05：10：00', '0.93', '720', '0.24');
INSERT INTO `tb_plane` VALUES ('22', '5', '东方航空MU9710', '波音737-800(中型)', '首都国际机场', '12：15：00', '长水国际机场', '18：05：00', '05：50：00', '0.97', '720', '0.24');
INSERT INTO `tb_plane` VALUES ('23', '5', '东方航空MU5706', '波音737-800(中型)', '首都国际机场', '16：20：00', '长水国际机场', '19：55：00', '03：35：00', '0.9', '720', '0.24');
INSERT INTO `tb_plane` VALUES ('24', '5', '东方航空MU9706', '波音737(中型)', '首都国际机场', '17：00：00', '长水国际机场', '22：45：00', '05：45：00', '0.73', '720', '0.24');
INSERT INTO `tb_plane` VALUES ('25', '5', '东方航空MU5708', '波音737-800(中型)', '首都国际机场', '18：40：00', '长水国际机场', '22：20：00', '03：40：00', '0.77', '720', '0.24');
INSERT INTO `tb_plane` VALUES ('26', '10', '春秋航空9C6256', '空中客车 A320(中型)', '正定国际机场', '07：00：00', '浦东国际机场', '08：55：00', '01：55：00', '1', '499', '0.42');
INSERT INTO `tb_plane` VALUES ('27', '10', '中国联航KN2315', '波音737(中型)', '正定国际机场', '19：05：00', '浦东国际机场', '21：05：00', '02：00：00', '0.93', '538', '0.45');
INSERT INTO `tb_plane` VALUES ('28', '10', '中国联航KN2355', '波音737(中型)', '正定国际机场', '09：00：00', '浦东国际机场', '11：00：00', '02：00：00', '0.97', '598', '0.5');
INSERT INTO `tb_plane` VALUES ('29', '10', '河北航空NS3217', '波音737-800(中型)', '正定国际机场', '17：30：00', '浦东国际机场', '19：30：00', '02：00：00', '0.83', '700', '0.59');
INSERT INTO `tb_plane` VALUES ('30', '7', '春秋航空9C8766', '空中客车 A320(中型)', '正定国际机场', '21：30：00', '虹桥国际机场', '22：30：00', '02：00：00', '1', '499', '0.42');
INSERT INTO `tb_plane` VALUES ('31', '7', '中国联航KN2317', '波音737(中型)', '正定国际机场', '16：30：00', '虹桥国际机场', '18：30：00', '02：00：00', '0.83', '716', '0.6');
INSERT INTO `tb_plane` VALUES ('32', '7', '河北航空NS3219', '波音737-800(中型)', '正定国际机场', '08：00：00', '虹桥国际机场', '10：00：00', '02：00：00', '1', '820', '0.69');
INSERT INTO `tb_plane` VALUES ('33', '8', '中国联航KN2381', '波音737(中型)', '正定国际机场', '07：35：00', '长水国际机场', '10：50：00', '03：15：00', '0.87', '497', '0.21');
INSERT INTO `tb_plane` VALUES ('34', '8', '昆明航空KY8248', '波音737-800(中型)', '正定国际机场', '20：50：00', '长水国际机场', '00：10：00', '03：20：00', '0.9', '519', '0.2');
INSERT INTO `tb_plane` VALUES ('35', '8', '祥鹏航空8L9656', '波音737-600(中型)', '正定国际机场', '17：50：00', '长水国际机场', '20：00：55', '02：10：00', '0.8', '530', '0.23');
INSERT INTO `tb_plane` VALUES ('36', '8', '河北航空NS3311', '波音737-800(中型)', '正定国际机场', '05：50：00', '长水国际机场', '08：50：00', '03：00：00', '1', '600', '0.26');
INSERT INTO `tb_plane` VALUES ('37', '8', '河北航空NS3269', '波音737-800(中型)', '正定国际机场', '16：00：00', '长水国际机场', '19：20：00', '03：20：00', '0.97', '600', '0.26');
INSERT INTO `tb_plane` VALUES ('38', '8', '春秋航空9C8937', '空中客车 A320(中型)', '正定国际机场', '10：15：00', '长水国际机场', '13：35：00', '03：20：00', '0.93', '619', '0.26');
INSERT INTO `tb_plane` VALUES ('39', '8', '四川航空3U8670', '空中客车 A320(中型)', '正定国际机场', '13：30：00', '长水国际机场', '19：00：00', '05：30：00', '0.93', '2330', '0.98');

-- ----------------------------
-- Table structure for `tb_startendplace`
-- ----------------------------
DROP TABLE IF EXISTS `tb_startendplace`;
CREATE TABLE `tb_startendplace` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_start` varchar(20) NOT NULL,
  `p_end` varchar(20) NOT NULL,
  PRIMARY KEY (`p_id`),
  KEY `p_start` (`p_start`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_startendplace
-- ----------------------------
INSERT INTO `tb_startendplace` VALUES ('1', '北京', '昆明');
INSERT INTO `tb_startendplace` VALUES ('2', '北京', '上海');
INSERT INTO `tb_startendplace` VALUES ('3', '北京', '石家庄');
INSERT INTO `tb_startendplace` VALUES ('4', '石家庄', '昆明');
INSERT INTO `tb_startendplace` VALUES ('5', '石家庄', '上海');
INSERT INTO `tb_startendplace` VALUES ('6', '北京', '太原');
INSERT INTO `tb_startendplace` VALUES ('7', '太原', '上海');
INSERT INTO `tb_startendplace` VALUES ('8', '太原', '乌鲁木齐');
INSERT INTO `tb_startendplace` VALUES ('9', '乌鲁木齐', '上海');

-- ----------------------------
-- Table structure for `tb_subway`
-- ----------------------------
DROP TABLE IF EXISTS `tb_subway`;
CREATE TABLE `tb_subway` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT,
  `s_sid` varchar(11) NOT NULL COMMENT '几号线编号',
  `s_place` varchar(20) NOT NULL COMMENT '站名',
  `s_seq_first_time` varchar(20) NOT NULL COMMENT '首次顺序经过时间',
  `s_back_first_time` varchar(20) NOT NULL COMMENT '首次逆序经过时间',
  `s_pass_time` varchar(255) NOT NULL COMMENT '多久一趟',
  `s_belong_place` varchar(20) NOT NULL COMMENT '哪个城市的地铁',
  PRIMARY KEY (`s_id`),
  KEY `s_place` (`s_place`),
  KEY `s_sid` (`s_sid`),
  KEY `s_sid_2` (`s_sid`),
  KEY `s_belong_place` (`s_belong_place`),
  CONSTRAINT `s_belong_place` FOREIGN KEY (`s_belong_place`) REFERENCES `tb_startendplace` (`p_start`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_subway
-- ----------------------------
INSERT INTO `tb_subway` VALUES ('2', '4号线', '天宫院', '5：30：00', '6：22：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('3', '4号线', '生物医院基地', '5：32：00', '6：19：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('4', '4号线', '义和庄', '5：35：00', '6：15：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('5', '4号线', '黄村火车站', '5：38：00', '6：12：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('6', '4号线', '黄村西大街', '5：40：00', '6：10：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('7', '4号线', '清源路', '5：42：00', '6：08：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('8', '4号线', '枣园', '5：44：00', '6：06：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('9', '4号线', '高米店南', '5：46：00', '6：04：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('10', '4号线', '高米店北', '5：48：00', '6：02：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('11', '4号线', '西红门', '5：51：00', '6：00：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('12', '4号线', '新宫', '5：57：00', '5：54：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('13', '4号线', '公益西桥', '6：00：00', '5：51：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('14', '4号线', '角门西', '6：02：00', '5：49：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('15', '4号线', '马家堡', '6：05：00', '5：46：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('16', '4号线', '北京南站', '6：07：00', '5：43：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('17', '4号线', '陶然亭', '6：10：00', '5：41：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('18', '4号线', '菜市口', '6：13：00', '5：39：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('19', '4号线', '宣武门', '6：16：00', '5：36：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('20', '4号线', '西单', '6：18：00', '5：34：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('21', '4号线', '灵境胡同', '6：20：00', '5：32：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('22', '4号线', '西四', '6：23：00', '5：29：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('23', '4号线', '平安里', '6：26：00', '5：26：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('24', '4号线', '新街口', '6：29：00', '5：23：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('25', '4号线', '西直门', '6：33：00', '5：19：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('26', '4号线', '动物园', '6：36：00', '5：16：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('27', '4号线', '国家图书馆', '6：40：00', '5：12：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('28', '4号线', '魏公村', '6：42：00', '5：10：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('29', '4号线', '人民大学', '6：44：00', '5：08：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('30', '4号线', '海淀黄庄', '6：47：00', '5：05：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('31', '4号线', '中关村', '6：48：00', '5：04：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('32', '4号线', '北京大学东门', '6：50：00', '5：02：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('33', '4号线', '圆明园', '6：52：00', '5：00：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('34', '4号线', '西苑', '6：55：00', '4：57：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('35', '4号线', '北宫门', '6：57：00', '4：54：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('36', '4号线', '安河桥北', '7：00：00', '4：52：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('37', '7号线', '北京西站', '5：30：00', '5：53：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('38', '7号线', '湾子', '5：32：00', '5：51：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('39', '7号线', '达官营', '5：34：00', '5：49：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('40', '7号线', '广安门内', '5：37：00', '5：47：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('41', '7号线', '菜市口', '5：39：00', '5：44：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('42', '7号线', '虎坊桥', '5：41：00', '5：42：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('43', '7号线', '珠市口', '5：44：00', '5：40：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('44', '7号线', '桥湾', '5：46：00', '5：38：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('45', '7号线', '磁器口', '5：48：00', '5：36：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('46', '7号线', '广渠门内', '5：50：00', '5：33：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('47', '7号线', '广渠门外', '5：52：00', '5：31：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('48', '7号线', '九龙山', '5：56：00', '5：27：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('49', '7号线', '大郊亭', '5：58：00', '5：25：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('50', '7号线', '百子湾', '6：00：00', '5：23：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('51', '7号线', '化工', '6：02：00', '5：21：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('52', '7号线', '南楼梓庄', '6：05：00', '5：19：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('53', '7号线', '欢乐谷景区', '6：07：00', '5：17：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('54', '7号线', '垡头', '6：10：00', '5：14：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('55', '7号线', '双合', '6：12：00', '5：12：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('56', '7号线', '焦化厂', '6：14：00', '5：10：00', '0：04：00', '北京');
INSERT INTO `tb_subway` VALUES ('57', '9号线', '郭公庄', '5；00：00', '6：10：00', '0：02：00', '北京');
INSERT INTO `tb_subway` VALUES ('58', '9号线', '丰台科技园', '5：02：00', '6：08：00', '0：02：00', '北京');
INSERT INTO `tb_subway` VALUES ('59', '9号线', '科怡路', '5：04：00', '6：06：00', '0：02：00', '北京');
INSERT INTO `tb_subway` VALUES ('60', '9号线', '丰台南路', '5：06：00', '6：04：00', '0：02：00', '北京');
INSERT INTO `tb_subway` VALUES ('61', '9号线', '丰台东大街', '5：08：00', '6：02：00', '0：02：00', '北京');
INSERT INTO `tb_subway` VALUES ('62', '9号线', '七里庄', '5：11：00', '6：00：00', '0：02：00', '北京');
INSERT INTO `tb_subway` VALUES ('63', '9号线', '六里桥', '5：14：00', '5：56：00', '0：02：00', '北京');
INSERT INTO `tb_subway` VALUES ('64', '9号线', '六里桥东', '5：17：00', '5：53：00', '0：02：00', '北京');
INSERT INTO `tb_subway` VALUES ('65', '9号线', '北京西站', '5：21：00', '5：50：00', '0：02：00', '北京');
INSERT INTO `tb_subway` VALUES ('66', '9号线', '军事博物馆', '5：24：00', '5：47：00', '0：02：00', '北京');
INSERT INTO `tb_subway` VALUES ('67', '9号线', '白堆子', '5：27：00', '5：43：00', '0：02：00', '北京');
INSERT INTO `tb_subway` VALUES ('68', '9号线', '白石桥南', '5：29：00', '5：41：00', '0：02：00', '北京');
INSERT INTO `tb_subway` VALUES ('69', '9号线', '国家图书馆', '5：33：00', '5：36：00', '0：02：00', '北京');
INSERT INTO `tb_subway` VALUES ('70', '2号线', '西直门', '5：10：00', '5：36：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('71', '2号线', '积水潭', '5：12：00', '5：34：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('72', '2号线', '鼓楼大街', '5：15：00', '5：32：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('73', '2号线', '安定门', '5：17：00', '5：30：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('74', '2号线', '雍和宫', '5：19：00', '5：28：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('75', '2号线', '东直门', '5：22：00', '5：26：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('76', '2号线', '东四十条', '5：25：00', '5：25：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('77', '2号线', '朝阳门', '5：27：00', '5：23：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('78', '2号线', '建国门', '5：29：00', '5：21：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('79', '2号线', '北京站', '5：31：00', '5：19：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('80', '2号线', '崇文门', '5：33：00', '5：17：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('81', '2号线', '前门', '5：35：00', '5：15：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('82', '2号线', '和平门', '5：37：00', '5：13：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('83', '2号线', '宣武门', '5：40：00', '5：11：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('84', '2号线', '长椿街', '5：42：00', '5：09：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('85', '2号线', '复兴门', '5：45：00', '5：06：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('86', '2号线', '阜成门', '5：48：00', '5：03：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('87', '2号线', '车公庄', '5：51：00', '5：00：00', '0：03：00', '北京');
INSERT INTO `tb_subway` VALUES ('88', '机场线', '东直门', '6：00：00', '6：54：00', '0：13：00', '北京');
INSERT INTO `tb_subway` VALUES ('89', '机场线', '三元桥', '6：04：00', '6：50：00', '0：13：00', '北京');
INSERT INTO `tb_subway` VALUES ('90', '机场线', '首都国际机场', '6：30：00', '6：36：00', '0：13：00', '北京');

-- ----------------------------
-- Table structure for `tb_traffic`
-- ----------------------------
DROP TABLE IF EXISTS `tb_traffic`;
CREATE TABLE `tb_traffic` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_p_id` int(11) NOT NULL COMMENT '路径编号',
  `t_method` varchar(20) NOT NULL,
  `t_method1` varchar(20) NOT NULL COMMENT '交通方式',
  `t_method2` varchar(20) NOT NULL,
  `t_start_place` varchar(20) NOT NULL COMMENT '交通工具的起点',
  `t_end_place` varchar(20) NOT NULL COMMENT '交通工具的末',
  PRIMARY KEY (`t_id`),
  KEY `t_p_id` (`t_p_id`),
  CONSTRAINT `t_p_id` FOREIGN KEY (`t_p_id`) REFERENCES `tb_startendplace` (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_traffic
-- ----------------------------
INSERT INTO `tb_traffic` VALUES ('1', '2', '高铁', '北京南站', '上海虹桥', '北京', '上海');
INSERT INTO `tb_traffic` VALUES ('2', '2', '飞机', '首都国际机场', '浦东国际机场', '北京', '上海');
INSERT INTO `tb_traffic` VALUES ('3', '2', '飞机', '首都国际机场', '虹桥国际机场', '北京', '上海');
INSERT INTO `tb_traffic` VALUES ('5', '1', '飞机', '首都国际机场', '长水国际机场', '北京', '昆明');
INSERT INTO `tb_traffic` VALUES ('6', '3', '高铁', '北京西站', '正定机场', '北京', '石家庄');
INSERT INTO `tb_traffic` VALUES ('7', '5', '飞机', '正定机场', '虹桥国际机场', '石家庄', '上海');
INSERT INTO `tb_traffic` VALUES ('8', '4', '飞机', '正定机场', '长水国际机场', '石家庄', '昆明');
INSERT INTO `tb_traffic` VALUES ('9', '6', '高铁', '北京西站', '太原南站', '北京', '太原');
INSERT INTO `tb_traffic` VALUES ('10', '5', '飞机', '正定机场', '浦东国际机场', '石家庄', '上海');

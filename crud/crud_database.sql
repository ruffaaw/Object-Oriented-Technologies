/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : crud

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 12/11/2022 06:54:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `accountID` int NOT NULL,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`accountID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'ruffaaw', '$2a$10$LxR7wXKtdz1DgvsGlnO9/.OtoTb7ybFe/BuntpL4y43R0t1kdiwMC', 'Rafal Ciupek');
INSERT INTO `account` VALUES (2, 'fedson', '$2a$10$VmuJk1UApWzPHprb92himO9c3LtRJtb1eckSE.Uwki5V6j/D3tSJe', 'Bartek Fiejdasz');
INSERT INTO `account` VALUES (3, 'anamonium', '$2a$10$ZrGFBYUspPI11sP27Vb4Se1vNpHhb1BNvTXZBc0lJ6xGZYnl744ha', 'Ania Franczyk');
INSERT INTO `account` VALUES (4, 'Schostack', '$2a$10$LiuHyuKrfKbacU2uUVY48u42XJPr4zr0tSrZHXjLM63LwLawgnSqW', 'Jakub Sztosak');

-- ----------------------------
-- Table structure for notes
-- ----------------------------
DROP TABLE IF EXISTS `notes`;
CREATE TABLE `notes`  (
  `notesID` int UNSIGNED NOT NULL,
  `notesAccountID` int NOT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`notesID`) USING BTREE,
  INDEX `notesAccountID`(`notesAccountID` ASC) USING BTREE,
  CONSTRAINT `notes_ibfk_1` FOREIGN KEY (`notesAccountID`) REFERENCES `account` (`accountID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notes
-- ----------------------------
INSERT INTO `notes` VALUES (1, 1, 'dsakljfsdf', 'jdashfshnfdi\nfkdsnjfli\ndafkidsnjfi\nadsfkmnjasdfki\nkadsfmjdk\n');
INSERT INTO `notes` VALUES (2, 1, 'biologia', 'adsfdisfji\n');
INSERT INTO `notes` VALUES (3, 2, 'polski', 'jasdkfdsnfakdjflsdaf\n');
INSERT INTO `notes` VALUES (4, 3, 'Matma', 'asdfhdufhasdfun\nfdasfnas\nadsfdkaisfj\nadsfkiasmni\n');

SET FOREIGN_KEY_CHECKS = 1;


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_database_config
-- ----------------------------
DROP TABLE IF EXISTS `gen_database_config`;
CREATE TABLE `gen_database_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据库名称',
  `jdbc_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据库连接地址',
  `passwd` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据库密码',
  `user_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据库用户名',
  `be_use` tinyint(1) NULL DEFAULT 1 COMMENT '是否使用(1是;0否)',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据库配置' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of gen_database_config
-- ----------------------------
INSERT INTO `gen_database_config` VALUES (1, 'sqlserver', 'jdbc:sqlserver://192.168.0.99;databasename=gen_code',
'admin', 'sa', 0, '2020-12-29 17:23:29', NULL);
INSERT INTO `gen_database_config` VALUES (2, 'mysql',
'jdbc:mysql://localhost:3306/gen_code?useUnicode=true&characterEncoding=utf-8&useSSL=false', 'root', 'root', 1,
'2020-12-30 14:09:42', NULL);
INSERT INTO `gen_database_config` VALUES (3, 'oracle', 'jdbc:oracle:thin:@192.168.0.146:1521:ORCL', 'admin', 'genUser',
0, '2020-12-30 14:09:44', NULL);

-- ----------------------------
-- Table structure for gen_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `gen_sys_config`;
CREATE TABLE `gen_sys_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `config_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `package_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '包名',
  `module_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模块名',
  `author` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者名称',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `table_prefix` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '去除表前缀',
  `be_use` tinyint(1) NULL DEFAULT 0 COMMENT '是否使用(1是;0否)',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_code`(`config_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of gen_sys_config
-- ----------------------------
INSERT INTO `gen_sys_config` VALUES (1, 'gen_dongxb', 'top.dongxibao', 'sys', 'Dongxibao', 'dbao1128@163.com', NULL, 1, '2020-12-29 16:51:32', NULL);


SET FOREIGN_KEY_CHECKS = 1;

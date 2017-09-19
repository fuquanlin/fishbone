# 用户授权表
CREATE TABLE `user_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `identity_type` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '1手机号 2邮箱 3用户名 4qq 5微信 6腾讯微博 7新浪微博',
  `identifier` varchar(50) NOT NULL DEFAULT '' COMMENT '手机号 邮箱 用户名或第三方应用的唯一标识',
  `certificate` varchar(20) NOT NULL DEFAULT '' COMMENT '密码凭证(站内的保存密码，站外的不保存或保存token)',
  `create_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '绑定时间',
  `update_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新绑定时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `only` (`uid`,`identity_type`),
  KEY `idx_uid` (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户授权表';

# 用户基础信息
CREATE TABLE `user_base` (
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `user_role` tinyint(2) unsigned NOT NULL DEFAULT '2' COMMENT '2正常用户 3禁言用户 4虚拟用户 5运营',
  `register_source` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '注册来源：1手机号 2邮箱 3用户名 4qq 5微信 6腾讯微博 7新浪微博',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户账号，必须唯一',
  `nick_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `gender` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '用户性别 0-female 1-male',
  `birthday` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户生日',
  `signature` varchar(255) NOT NULL DEFAULT '' COMMENT '用户个人签名',
  `mobile` varchar(16) NOT NULL DEFAULT '' COMMENT '手机号码(唯一)',
  `mobile_bind_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '手机号码绑定时间',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱(唯一)',
  `email_bind_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '邮箱绑定时间',
  `face` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `face200` varchar(255) NOT NULL DEFAULT '' COMMENT '头像 200x200x80',
  `srcface` varchar(255) NOT NULL DEFAULT '' COMMENT '原图头像',
  `create_time` int(11) unsigned NOT NULL COMMENT '创建时间',
  `update_time` int(11) unsigned NOT NULL COMMENT '修改时间',
  `push_token` varchar(50) NOT NULL COMMENT '用户设备push_token',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基础信息表';

# 用户扩展信息
CREATE TABLE `user_extra` (
  `uid` bigint(20) NOT NULL COMMENT '用户 ID',
  `vendor` varchar(64) NOT NULL DEFAULT '' COMMENT '手机厂商：apple|htc|samsung，很少用',
  `client_name` varchar(50) NOT NULL DEFAULT '' COMMENT '客户端名称，如hjskang',
  `client_version` varchar(50) NOT NULL DEFAULT '' COMMENT '客户端版本号，如7.0.1',
  `os_name` varchar(16) NOT NULL DEFAULT '' COMMENT '设备号:android|ios',
  `os_version` varchar(16) NOT NULL DEFAULT '' COMMENT '系统版本号:2.2|2.3|4.0|5.1',
  `device_name` varchar(32) NOT NULL DEFAULT '' COMMENT '设备型号，如:iphone6s、u880、u8800',
  `device_id` varchar(128) NOT NULL DEFAULT '' COMMENT '设备ID',
  `idfa` varchar(50) NOT NULL DEFAULT '' COMMENT '苹果设备的IDFA',
  `idfv` varchar(50) NOT NULL DEFAULT '' COMMENT '苹果设备的IDFV',
  `market` varchar(20) NOT NULL DEFAULT '' COMMENT '来源',
  `create_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间',
  `update_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',
  `extend1` varchar(100) NOT NULL DEFAULT '' COMMENT '扩展字段1',
  `extend2` varchar(100) NOT NULL DEFAULT '' COMMENT '扩展字段2',
  `extend3` varchar(100) NOT NULL DEFAULT '' COMMENT '扩展字段3',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户额外信息表';

# 用户位置信息
CREATE TABLE `user_location` (
  `uid` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `curr_nation` varchar(10) NOT NULL DEFAULT '' COMMENT '所在地国',
  `curr_province` varchar(10) NOT NULL DEFAULT '' COMMENT '所在地省',
  `curr_city` varchar(10) NOT NULL DEFAULT '' COMMENT '所在地市',
  `curr_district` varchar(20) NOT NULL DEFAULT '' COMMENT '所在地地区',
  `location` varchar(255) NOT NULL DEFAULT '' COMMENT '具体地址',
  `longitude` decimal(10,6) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10,6) DEFAULT NULL COMMENT '纬度',
  `update_time` int(11) unsigned DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户定位表';

# 用户登陆日志
CREATE TABLE `user_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户uid',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '登录方式 第三方/邮箱/手机等',
  `command` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '操作类型 1登陆成功  2登出成功 3登录失败 4登出失败',
  `version` varchar(32) NOT NULL DEFAULT '1.0' COMMENT '客户端版本号',
  `client` varchar(20) NOT NULL DEFAULT 'dabaozha' COMMENT '客户端',
  `device_id` varchar(64) NOT NULL DEFAULT '' COMMENT '登录时设备号',
  `lastip` varchar(32) NOT NULL DEFAULT '' COMMENT '登录ip',
  `os` varchar(16) NOT NULL DEFAULT '' COMMENT '手机系统',
  `osver` varchar(32) NOT NULL DEFAULT '' COMMENT '系统版本',
  `text` varchar(200) NOT NULL DEFAULT '',
  `create_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '操作时间',
  PRIMARY KEY (`id`),
  KEY `idx_uid_type_time` (`uid`,`type`,`create_time`) USING BTREE,
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登陆日志表';

# 用户注册日志
CREATE TABLE `user_register_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `uid` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `register_method` tinyint(2) unsigned NOT NULL COMMENT '注册方式1手机号 2邮箱 3用户名 4qq 5微信 6腾讯微博 7新浪微博',
  `register_time` int(11) NOT NULL COMMENT '注册时间',
  `register_ip` varchar(16) NOT NULL DEFAULT '' COMMENT '注册IP',
  `register_client` varchar(16) NOT NULL DEFAULT '' COMMENT '注册客户端',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='用户注册日志表';

# 修改信息日
CREATE TABLE `user_info_update` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `uid` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `attribute_name` varchar(30) NOT NULL COMMENT '属性名',
  `attribute_old_val` varchar(30) NOT NULL DEFAULT '' COMMENT '属性对应旧的值',
  `attribute_new_val` varchar(30) NOT NULL DEFAULT '' COMMENT '属性对应新的值',
  `update_time` int(11) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='用户注册日志表';


create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information TEXT,
  autoapprove VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table oauth_client_token (
  token_id VARCHAR(255),
  token TEXT,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table oauth_access_token (
  token_id VARCHAR(255),
  token TEXT,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication TEXT,
  refresh_token VARCHAR(255)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table oauth_refresh_token (
  token_id VARCHAR(255),
  token TEXT,
  authentication TEXT
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table oauth_code (
  code VARCHAR(255), authentication TEXT
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table oauth_approvals (
  userId VARCHAR(255),
  clientId VARCHAR(255),
  scope VARCHAR(255),
  status VARCHAR(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
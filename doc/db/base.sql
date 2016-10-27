/*Table structure for table `f_permission` */
DROP TABLE IF EXISTS `f_permission`;

CREATE TABLE `f_permission` (
  `id`             INT(11) NOT NULL AUTO_INCREMENT,
  `permissioncode` VARCHAR(32)      DEFAULT NULL,
  `permissionname` VARCHAR(32)      DEFAULT NULL,
  `category`       VARCHAR(32)      DEFAULT NULL,
  `type`           VARCHAR(32)      DEFAULT NULL,
  PRIMARY KEY `id` (`id`),
  UNIQUE INDEX `permissioncode` (permissioncode)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 100
  DEFAULT CHARSET = utf8;


/*Data for the table `f_permission` */

INSERT INTO f_permission (id, permissioncode, permissionname, category, type)
VALUES (1, 'welcome', 'Dashboard', NULL, 'MENU');
INSERT INTO f_permission (id, permissioncode, permissionname, category, type)
VALUES (2, 'user', 'User Management', 'System Configuration', 'MENU');
INSERT INTO f_permission (id, permissioncode, permissionname, category, type)
VALUES (3, 'role', 'Role Management', 'System Configuration', 'MENU');
INSERT INTO f_permission (id, permissioncode, permissionname, category, type)
VALUES (4, 'log', 'System Log', 'System Configuration', 'MENU');
INSERT INTO f_permission (id, permissioncode, permissionname, category, type)
VALUES (5, 'config', 'System Config', 'System Configuration', 'MENU');

/*Table structure for table `f_role` */

DROP TABLE IF EXISTS `f_role`;

CREATE TABLE `f_role` (
  `id`       INT(11) NOT NULL AUTO_INCREMENT,
  `rolename` VARCHAR(32)      DEFAULT NULL,
  PRIMARY KEY `id` (`id`),
  UNIQUE INDEX `rolename` (rolename)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 100
  DEFAULT CHARSET = utf8;

/*Data for the table `f_role` */

INSERT INTO `f_role` (`id`, `rolename`) VALUES (1, 'admin'), (2, 'manager'), (3, 'normal');

/*Table structure for table `f_user` */

DROP TABLE IF EXISTS `f_user`;

CREATE TABLE `f_user` (
  `id`       INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(32)      DEFAULT NULL,
  `password` VARCHAR(256)     DEFAULT NULL,
  `email`    VARCHAR(256)     DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username` (username)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 100
  DEFAULT CHARSET = utf8;

/*Data for the table `f_user` */
/*All password is 1 */
INSERT INTO `f_user` (`id`, `username`, `password`, `email`)
VALUES (1, 'admin', '52a6eb687cd22e80d3342eac6fcc7f2e19209e8f83eb9b82e81c6f3e6f30743b', 'admin@test.com'),
  (2, 'manager', '52a6eb687cd22e80d3342eac6fcc7f2e19209e8f83eb9b82e81c6f3e6f30743b', 'manager@test.com'),
  (3, 'guest', '52a6eb687cd22e80d3342eac6fcc7f2e19209e8f83eb9b82e81c6f3e6f30743b', 'guest@test.com');

/*Table structure for table `f_user_role` */

DROP TABLE IF EXISTS `f_user_role`;

CREATE TABLE `f_user_role` (
  `user_id` INT(11) DEFAULT NULL,
  `role_id` INT(11) DEFAULT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `f_user_role` */

INSERT INTO `f_user_role` (`user_id`, `role_id`) VALUES (1, 1), (1, 2), (2, 2), (2, 3), (3, 3);


/*Table structure for table `f_role_permission` */

DROP TABLE IF EXISTS `f_role_permission`;

CREATE TABLE `f_role_permission` (
  `role_id`       INT(11) DEFAULT NULL,
  `permission_id` INT(11) DEFAULT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `f_role_permission` */

INSERT INTO `f_role_permission` (`role_id`, `permission_id`)
VALUES (1, 2), (1, 2), (1, 3), (1, 4), (1, 5),
  (2, 2), (2, 3),
  (3, 1);


/*Table structure for table `f_operation_log` */

DROP TABLE IF EXISTS `f_operation_log`;

CREATE TABLE `f_operation_log` (
  `id`             INT(11) NOT NULL AUTO_INCREMENT,
  `username`       VARCHAR(100)     DEFAULT NULL,
  `module_name`    VARCHAR(100)     DEFAULT NULL,
  `operation_type` INT(11)          DEFAULT NULL,
  `operation_desc` VARCHAR(200)     DEFAULT NULL,
  `old_value`      LONGTEXT         DEFAULT NULL,
  `new_value`      LONGTEXT         DEFAULT NULL,
  `gmt_create`     DATETIME         DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `f_operation_log` */


COMMIT;


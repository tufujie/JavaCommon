-- 默认SQL对应的用户表
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL DEFAULT 'Jef' COMMENT '用户名',
  `password` varchar(18) NOT NULL DEFAULT '123456' COMMENT '密码',
  `password_salt` varchar(18) NOT NULL DEFAULT '123456' COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 默认SQL对应的用户角色表
CREATE TABLE `user_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL DEFAULT 'Jef' COMMENT '用户名',
  `role_name` varchar(16) NOT NULL DEFAULT 'admin' COMMENT '角色名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_role_UNIQUE` (`username`,`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 默认SQL对应的角色权限表
CREATE TABLE `roles_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(16) NOT NULL DEFAULT 'admin' COMMENT '角色名',
  `permission` varchar(16) NOT NULL DEFAULT 'Jef' COMMENT '权限名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_permission_UNIQUE` (`role_name`,`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 插入测试数据
INSERT INTO `test`.`users` (`username`, `password`, `password_salt`) VALUES ('Jef', '123456', '123456');
INSERT INTO `test`.`users` (`username`, `password`, `password_salt`) VALUES ('Ran', '12345', '12345');

INSERT INTO `test`.`user_roles` (`username`, `role_name`) VALUES ('Jef', 'admin');
INSERT INTO `test`.`user_roles` (`username`, `role_name`) VALUES ('Ran', 'admin');

INSERT INTO `test`.`roles_permissions` (`role_name`, `permission`) VALUES ('admin', 'user:insert');
INSERT INTO `test`.`roles_permissions` (`role_name`, `permission`) VALUES ('admin', 'user:update');


-- 自建用户表
CREATE TABLE `tb_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(16) NOT NULL DEFAULT 'Jef' COMMENT '用户名',
  `pass_word` varchar(18) NOT NULL DEFAULT '123456' COMMENT '密码',
  `pass_word_salt` varchar(18) NOT NULL DEFAULT '123456' COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 自建表数据
INSERT INTO `test`.`tb_users` (`user_name`, `pass_word`, `pass_word_salt`) VALUES ('Jef', '123456', '123456');

-- 创建测试所用数据库
create database all_test;

-- 使用这个数据库
use all_test;

-- 新建测试用户表
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '名字',
  `password` varchar(48) NOT NULL DEFAULT 'e10adc3949ba59abbe56e057f20f883e' COMMENT '加密后的密码',
  `phone` varchar(20) NOT NULL COMMENT '手机号码',
  `age` smallint(3) NOT NULL COMMENT '年龄',
  `permission` smallint(1) DEFAULT 0 COMMENT '是否授权，0表示未授权，1表示已授权',
  `admin` smallint(1) DEFAULT 0 COMMENT '是否管理员，0表示未授权，1表示已授权',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_phone` (`name`,`phone`) COMMENT '一个人可能有多个手机号码'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 插入测试数据
INSERT INTO `user` (`name`, `password`, `phone`, `age`) VALUES ('Jef', 'e10adc3949ba59abbe56e057f20f883e', '18390220001', 20);
INSERT INTO `user` (`name`, `password`, `phone`, `age`) VALUES ('Ran', 'e10adc3949ba59abbe56e057f20f883e','18390220002', 19);

-- 设置管理员
UPDATE `all_test`.`user` SET `admin`='1' WHERE `id`='1';

-- 新建测试订单表
CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT comment '系统订单ID',
  `extra_order_id` varchar(32) NOT NULL COMMENT '第三方订单ID',
  `shop_id` bigint(20) NOT NULL COMMENT '系统店铺ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `total_price` decimal(18,6) NOT NULL DEFAULT '0.000000' COMMENT '总价格',
  `total_in_price` decimal(18,6) NOT NULL DEFAULT '0.000000' COMMENT '总进货价格',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_shop` (`extra_order_id`,`shop_id`) COMMENT '同一个店铺不允许有相同订单号'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 新建测试子订单表
CREATE TABLE `order_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT comment '系统子订单ID',
  `order_id` bigint(20) NOT NULL COMMENT '系统订单ID',
  `product_name` varchar(32) NOT NULL COMMENT '商品名称',
  `num` smallint(4) NOT NULL COMMENT '数量',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_productName` (`order_id`,`product_name`) COMMENT '同一个订单商品名称不重'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 新建测试店铺信息
CREATE TABLE `shop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT comment '系统店铺ID',
  `name` varchar(32) NOT NULL COMMENT '店铺名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 添加订单、子订单、店铺数据
INSERT INTO `order_info` (`extra_order_id`, `shop_id`, `user_id`) VALUES ('123456789', '1', '1');

INSERT INTO `order_product` (`order_id`, `product_name`, `num`) VALUES ('1', '袜子', '3');
INSERT INTO `order_product` (`order_id`, `product_name`, `num`) VALUES ('1', '鞋子', '2');
INSERT INTO `order_product` (`order_id`, `product_name`, `num`) VALUES ('1', '短袖', '1');

INSERT INTO `shop` (`name`) VALUES ('Jef的店铺');

-- 新建测试表
CREATE TABLE `test_all` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `test_name` varchar(45) DEFAULT NULL COMMENT '测试名称',
  `test_phone` varchar(45) DEFAULT NULL COMMENT '测试电话',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nameAndPhone` (`test_phone`,`test_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 常用与一些通用的操作
CREATE TABLE `test_a` (
  `id` int(11) NOT NULL primary key,
  `name` varchar(255) NOT NULL COMMENT '水果名称',
  `season` char(4) NOT NULL DEFAULT '一年四季' COMMENT '水果所属季节'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `test_b` (
  `id` int(11) NOT NULL primary key,
  `name` varchar(255) NOT NULL COMMENT '水果名称',
  `season` char(4) NOT NULL DEFAULT '一年四季' COMMENT '水果所属季节'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO test_a VALUES (1, '苹果', '秋季'); 
INSERT INTO test_a VALUES (2, '菠萝', '一年四季');  
INSERT INTO test_a VALUES (3, '橘子', '冬集');  
INSERT INTO test_a VALUES (4, '西瓜', '夏季');  
INSERT INTO test_a VALUES (5, '香蕉', '秋季');

INSERT INTO test_b VALUES (1, '香蕉', '秋季'); 
INSERT INTO test_b VALUES (2, '草莓', '夏季');  
INSERT INTO test_b VALUES (3, '苹果', '秋季');  
INSERT INTO test_b VALUES (4, '桃子', '夏季');  
INSERT INTO test_b VALUES (5, '荔枝', '夏季');
INSERT INTO test_b VALUES (6, '橙子', '夏季');
INSERT INTO test_b VALUES (7, '香蕉', '秋季');
INSERT INTO test_b VALUES (8, '苹果', '夏季'); 
-- 常用于MySQL开发技巧
-- 学生
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) NOT NULL COMMENT '学生ID，学号',
  `student_name` varchar(8) NOT NULL COMMENT '学生名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- 学生数据
INSERT INTO `student` (`student_id`, `student_name`) VALUES ('2012010101', 'Jef');
INSERT INTO `student` (`student_id`, `student_name`) VALUES ('2012010102', 'Ran');
INSERT INTO `student` (`student_id`, `student_name`) VALUES ('2012010103', 'Dage');
INSERT INTO `student` (`student_id`, `student_name`) VALUES ('2012010104', 'Haonan');

-- 学生着装
CREATE TABLE `student_dress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(8) NOT NULL COMMENT '学生名称',
  `cap` varchar(64) DEFAULT NULL COMMENT '帽子',
  `clothing` varchar(64) DEFAULT NULL COMMENT '衣服',
  `pants` varchar(64) DEFAULT NULL COMMENT '裤子',
  `shoe` varchar(64) DEFAULT NULL COMMENT '鞋子',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- 学生着装数据
INSERT INTO `student_dress` (`student_name`, `cap`, `clothing`, `pants`, `shoe`) VALUES ('Jef', '流行帽', '流行衣', '流行裤', '流行鞋');
INSERT INTO `student_dress` (`student_name`, `cap`, `clothing`, `pants`, `shoe`) VALUES ('Ran', '复古帽', '复古衣', '复古裤', '复古鞋');

-- 学生分数
CREATE TABLE `score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(8) NOT NULL COMMENT '学生名称',
  `course_name` varchar(16) NOT NULL COMMENT '课程名称',
  `score` tinyint(3) NOT NULL COMMENT '分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- 学生分数数据
INSERT INTO `score` (`student_name`, `course_name`, `score`) VALUES ('Jef', '语文', '90');
INSERT INTO `score` (`student_name`, `course_name`, `score`) VALUES ('Jef', '数学', '95');
INSERT INTO `score` (`student_name`, `course_name`, `score`) VALUES ('Jef', '英语', '100');
INSERT INTO `score` (`student_name`, `course_name`, `score`) VALUES ('Ran', '语文', '100');
INSERT INTO `score` (`student_name`, `course_name`, `score`) VALUES ('Ran', '数学', '95');
INSERT INTO `score` (`student_name`, `course_name`, `score`) VALUES ('Ran', '英语', '90');

-- 学生购物
CREATE TABLE `student_shopping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) NOT NULL COMMENT '学生ID，学号',
  `num` int(11) NOT NULL COMMENT '购物种类',
  `buy_date` date NOT NULL COMMENT '购物日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
-- 学生购物数据
INSERT INTO `student_shopping` (`student_id`, `num`, `buy_date`) VALUES ('2012010101', 3,  '2018-05-06');
INSERT INTO `student_shopping` (`student_id`, `num`, `buy_date`) VALUES ('2012010101', 4, '2018-05-05');
INSERT INTO `student_shopping` (`student_id`, `num`, `buy_date`) VALUES ('2012010101', 5, '2018-05-04');
INSERT INTO `student_shopping` (`student_id`, `num`, `buy_date`) VALUES ('2012010102', 1, '2018-05-04');
INSERT INTO `student_shopping` (`student_id`, `num`, `buy_date`) VALUES ('2012010102', 2, '2018-05-03');

-- 学生技能
CREATE TABLE `student_skill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(8) NOT NULL COMMENT '学生ID，学号',
  `skill_name` varchar(16) NOT NULL COMMENT '技能名称',
  `skill_level` tinyint(2) NOT NULL COMMENT '技能等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- 学生技能数据
INSERT INTO `student_skill` (`student_id`, `skill_name`, `skill_level`) VALUES ('2012010101', 'Java', '4');
INSERT INTO `student_skill` (`student_id`, `skill_name`, `skill_level`) VALUES ('2012010101', 'C++', '3');
INSERT INTO `student_skill` (`student_id`, `skill_name`, `skill_level`) VALUES ('2012010102', 'HTML', '2');
INSERT INTO `student_skill` (`student_id`, `skill_name`, `skill_level`) VALUES ('2012010102', 'CSS', '1');

-- 学生兴趣爱好
CREATE TABLE `interest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(8) NOT NULL COMMENT '学生名称',
  `interesting` varchar(64) NOT NULL COMMENT '兴趣爱好',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- 学生兴趣爱好数据
INSERT INTO `interest` (`student_name`, `interesting`) VALUES ('Jef', '篮球,羽毛球,乒乓球,游泳');
INSERT INTO `interest` (`student_name`, `interesting`) VALUES ('Ran', '篮球,乒乓球,台球');
INSERT INTO `interest` (`student_name`, `interesting`) VALUES ('Dage', '篮球,乓乓球,台球');

-- 部门表
CREATE TABLE dept(
id bigint(20) PRIMARY KEY auto_increment,
no MEDIUMINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '部门编号',
name VARCHAR(20) NOT NULL DEFAULT '部门名称',
loc VARCHAR(13) NOT NULL DEFAULT '部门地点'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ;

-- 部门数据
INSERT INTO dept(no, name, loc) values(1, '研发部', '大厦10楼1001');
INSERT INTO dept(no, name, loc) values(2, '测试部', '大厦10楼1002');
INSERT INTO dept(no, name, loc) values(3, '产品部', '大厦10楼1003');
INSERT INTO dept(no, name, loc) values(4, '运营部', '大厦10楼1004');
INSERT INTO dept(no, name, loc) values(5, '财务部', '大厦10楼1005');

-- 构建大表->大表中数据有要求, 记录是不同才有用，否则测试效果和真实的相差大，如下
-- 员工表
CREATE TABLE emp(
id bigint(20) PRIMARY KEY auto_increment,
no MEDIUMINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '员工编号',
name VARCHAR(20) NOT NULL DEFAULT "" COMMENT '员工姓名',
en_name VARCHAR(20) NOT NULL DEFAULT "" COMMENT '员工英文名',
job VARCHAR(9) NOT NULL DEFAULT "" COMMENT '员工工作',
level VARCHAR(6) NOT NULL DEFAULT "" COMMENT '员工级别',
mgr MEDIUMINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级编号',
hiredate DATE NOT NULL COMMENT '入职时间',
sal DECIMAL(7,2) NOT NULL COMMENT '薪资',
comm DECIMAL(7,2) NOT NULL COMMENT '红利',
dept_no MEDIUMINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '部门编号'
)ENGINE=MyISAM DEFAULT CHARSET=utf8 ;

-- 创建函数，该函数会返回一个指定长度的随机字符串
CREATE FUNCTION `rand_string`(n INT) RETURNS varchar(255) CHARSET utf8
BEGIN
DECLARE chars_str VARCHAR(100) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ';
DECLARE return_str VARCHAR(255) DEFAULT '';
DECLARE i INT default 0;
-- 每次取随机一个字母，取n个
WHILE i < n DO 
SET return_str = concat(return_str, substring(chars_str, floor(1+rand() * 52), 1));
SET i = i + 1;
END WHILE;
RETURN return_str;
END

-- 创建一个存储过程，用于生成员工信息
CREATE PROCEDURE insert_emp(start INT(10), num INT(10), dept_num INT(2))
-- start开始员工号，num员工数量，dept_num部门数量
BEGIN
DECLARE i INT DEFAULT 0; 
#SET autocommit = 0 把autocommit设置成0
SET autocommit = 0; 
REPEAT
INSERT INTO emp(no, name, en_name, job, level, mgr, hiredate, sal, comm, dept_no) VALUES((start+i), rand_string(6), rand_string(3), 'SALESMAN', 'T1-1', 0001, curdate(), 5000, 400, rand() * (dept_num - 1) + 1 );
SET i = i + 1;
UNTIL i = num
END repeat;
COMMIT;
END

#调用刚刚写好的函数, 6000000条记录，从100001号开始，最终max(no) = 6100000
CALL insert_emp(100001, 6000000, 5);

-- 用于测试group by
create table test_group_by
(
id varchar(20),
b varchar(20),
c varchar(20)
);

insert into test_group_by values(1, 'A', '甲');
insert into test_group_by values(2, 'A', '甲');
insert into test_group_by values(3, 'A', '甲');
insert into test_group_by values(4, 'A', '乙');
insert into test_group_by values(5, 'A', '乙');
insert into test_group_by values(6, 'B', '乙');
insert into test_group_by values(7, 'B', '乙');
insert into test_group_by values(8, 'B', '丙');
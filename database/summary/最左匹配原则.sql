CREATE TABLE `left_pick_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `a` int(11) NOT NULL,
  `b` int(11) DEFAULT NULL,
  `c` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into left_pick_test values
(1, 10010, 20011, 30011),
(2, 10011, 20010, 30010),
(3, 10012, 20010, 30012),
(4, 11110, 20010, 30013);

alter table left_pick_test
add index `idx_a_b_c` (`a`,`b`,`c`);

-- 全列匹配，跟顺序无关，MySQL会通过优化器，自动优化SQL条件顺序。全部走索引，key_len=14
explain select * from left_pick_test where a = 10010 and b = 10001 and c = 10001;
explain select * from left_pick_test where a = 10010 and c = 10001 and b = 10001;

-- 精确匹配最左列，连续匹配。全部走索引，key_len=9
explain select * from left_pick_test where a = 10010 and b = 10001;
-- 精确匹配最左列，不连续匹配。a走索引，c不走索引，通过key_len=4的长度可以看出
explain select * from left_pick_test where a = 10010 and c = 10001;

-- 中间断索引（范围匹配其它列），精确匹配最左列，连续匹配。全部走索引
explain select * from left_pick_test where a = 10010 and b > 10001;
-- 中间断索引（范围匹配其它列），精确匹配最左列，不连续匹配。a走索引，c不走索引
explain select * from left_pick_test where a = 10010 and c > 10001;
-- 中间断索引（范围匹配其它列），精确匹配最左列，不连续匹配。a，b走索引，c不走索引
explain select * from left_pick_test where a = 10010 and b > 10001 and c = 10001;

-- 范围匹配最左列。全部走索引
explain select * from left_pick_test where a > 10001 and a < 10003;

-- 范围匹配最左列，范围匹配其它列。a走索引，b不走索引
explain select * from left_pick_test where a > 10001 and a < 10003 and b < 10003;

-- 虽然连续匹配，但是没有匹配最左列。都不走索引
explain select * from left_pick_test where b = 10001;
explain select * from left_pick_test where c = 10001;
explain select * from left_pick_test where b = 10001 and c = 10001;
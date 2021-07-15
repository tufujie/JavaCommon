首先group by 的简单说明:
   group by顾名思义就是按某个条件进行分组，一般和聚合函数一起使用才有意义,比如 count sum avg等,使用group by的两个要素:
   (1) 出现在select后面的字段 要么是是聚合函数中的,要么就是group by 中的.
   (2) 要筛选结果 可以先使用where 再用group by 或者先用group by 再用having
-- 照着列b分组，即列b一样的视为一组，有A和B
select count(id) count_id, id, b, c from test_group_by group by b;
-- 照着列c分组，即列c一样的视为一组，有甲、乙、丙三种
select count(id) count_id, id, b, c from test_group_by group by b;
-- 照着列b和c一起分组，即列b和列c一样的视为一组，有A甲、A乙、B乙、B丙
select count(id) count_id, id, b, c from test_group_by group by b, c;
可以看出 group by 两个条件的工作过程（多个条件的原理一样）:
先对第一个条件b列的值 进行分组，分为 第一组:1-5， 第二组6-8，然后又对已经存在的两个分组用条件二c列的值进行分组，发现第一组又可以分为两组 1-3，4-5，第二组可以分为两组，6-7，7-8
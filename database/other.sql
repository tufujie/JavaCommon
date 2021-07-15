dual表是一个实际存在的表，任何用户均可读取，常用在没有目标表的select中
可用于构建导出表头
select '编码', '名称' from dual 
union all
(select FNumber, FName from table_name limit 10);
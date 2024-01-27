-- 2库，每个库3张表
create database ds_0;

create database ds_1;
-- 经典的订单表
create table ds_0.t_order_0 (
                           order_id bigint(20) primary key comment '订单id',
                           order_no varchar(32) comment '订单号',
                           user_id bigint(32) comment '购买用户'
);
create table ds_0.t_order_1 (
                           order_id bigint(20) primary key comment '订单id',
                           order_no varchar(32) comment '订单号',
                           user_id bigint(32) comment '购买用户'
);
create table ds_0.t_order_2 (
                           order_id bigint(20) primary key comment '订单id',
                           order_no varchar(32) comment '订单号',
                           user_id bigint(32) comment '购买用户'
);

create table ds_1.t_order_0 (
                           order_id bigint(20) primary key comment '订单id',
                           order_no varchar(32) comment '订单号',
                           user_id bigint(32) comment '购买用户'
);
create table ds_1.t_order_1 (
                           order_id bigint(20) primary key comment '订单id',
                           order_no varchar(32) comment '订单号',
                           user_id bigint(32) comment '购买用户'
);
create table ds_1.t_order_2 (
                           order_id bigint(20) primary key comment '订单id',
                           order_no varchar(32) comment '订单号',
                           user_id bigint(32) comment '购买用户'
);

-- 按照取模分库分表
-- orderId 分别是1到6
insert into ds_1.t_order_1 values(1, "orderNo1", 1);
insert into ds_0.t_order_2 values(2, "orderNo2", 2);
insert into ds_1.t_order_0 values(3, "orderNo3", 3);
insert into ds_0.t_order_1 values(4, "orderNo4", 4);
insert into ds_1.t_order_2 values(5, "orderNo5", 5);
insert into ds_0.t_order_0 values(6, "orderNo6", 6);
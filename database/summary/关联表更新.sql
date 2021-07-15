-- 设置订单的总金额为0
-- 方式1，把order_info orderInfo join order_product orderProduct on orderInfo.id = orderProduct.order_id看做是一张表即可
update order_info orderInfo join order_product orderProduct on orderInfo.id = orderProduct.order_id set orderInfo.total_price = 0;
-- 方式2，通过关联字段更新
update order_info orderInfo set orderInfo.total_price = 0 where orderInfo.id in(select orderProduct.order_id from order_product orderProduct);
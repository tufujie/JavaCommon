package com.jef.designpattern.structure.flyweight;

/**
 * 变化的数据：库存扣减
 *
 * @author Jef
 * @date 2021/12/8
 */
public class Stock {

    private int total; // 库存总量
    private int used; // 库存已用

    public Stock(int total, int stockUsed) {
        this.total = total;
        this.used = stockUsed;
    }

    public Stock(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }
}
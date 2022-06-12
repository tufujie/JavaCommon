package com.jef.algorithm;

/**
 * 有两个篮子，分别为A和B，篮子A里装有鸡蛋，篮子B里装有苹果，请用面向对象的思想实现两个篮子里的物品交换（请用代码实现）
 * 面向对象思想实现篮子物品交换
 *
 * @author Jef
 * @date 2022/6/11
 */
public class AppleAgeChange {

    public static void main(String[] args) {
        //创建篮子
        Basket A = new Basket("A");
        Basket B = new Basket("B");
        //装载物品
        A.load("鸡蛋");
        B.load("苹果");
        //交换物品A.change(B);
        A.show();
    }
}

class Basket {
    public String name;//篮子名称
    private Goods goods;//篮子中所装物品

    public Basket(String name) {
//TODO Auto-generated constructor stub
        this.name = name;
        System.out.println(name + "篮子被创建");
    }

    //装物品函数
    public void load(String name) {
        goods = new Goods(name);
        System.out.println(this.name + "装载了" + name + "物品");
    }

    public void change(Basket B) {
        System.out.println(this.name + "和" + B.name + "中的物品发生了交换");
        String tmp = this.goods.getName();
        this.goods.setName(B.goods.getName());
        B.goods.setName(tmp);
    }

    public void show() {
        System.out.println(this.name + "中有" + goods.getName() + "物品");
    }
}

class Goods {
    private String name;//物品名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Goods(String name) {
//TODO Auto-generated constructor stub
        this.name = name;
    }

}
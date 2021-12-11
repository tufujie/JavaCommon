package com.jef.designpattern.action.strategy;

/**
 * 客户端之前
 * 在遇到if-else的分支业务逻辑比较复杂时，我们都习惯于将其抽出一个方法或者封装成一个对象去调用，这样整个if-else结构就不会显得太臃肿。
 * 就上面例子，当回执的类型越来越多时，分支else if 就会越来越多，每增加一个回执类型，就需要修改或添加if-else分支，违反了开闭原则（对扩展开放，对修改关闭）
 * @author Jef
 * @date 2020/7/21 0021
 */
public class ClientBefore {
    public static void main(String[] args) {
        int type = 1;
    }

    /**
     * 系统在收到不同的类型后，会执行对应的业务逻辑处理
     * @author Jef
     * @date 2020/7/21 0021
     * @param type
     * @return void
     */
    public void print(int type) {
        if (type == 1) {
            System.out.println("1");
        } else if (type == 2) {
            System.out.println("2");
        } else if (type == 100) {
            // ... 每添加一种类型这边的会不断添加
        } else {

        }
    }


}
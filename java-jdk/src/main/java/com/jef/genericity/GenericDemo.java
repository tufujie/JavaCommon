package com.jef.genericity;

/**
 * 泛型demo
 *
 * @author Jef
 * @date 2021/7/17
 */
public class GenericDemo {

    public static void main(String[] args) {
        Meat meat = new Meat();
        meat = heat(meat);

        Soup soup = new Soup();
        soup = heat(soup);

        Message<String> stringMessage = new Message<String>();
        stringMessage.setMsg("Hello");
        function(stringMessage);

        Message<Integer> integerMessage = new Message<Integer>();
        integerMessage.setMsg(1);
        function(integerMessage);
    }

    /**
     * “?”用于方法的接受参数或者是返回类型
     * 可以接收所有的 Message 定义的泛型类型
     *
     * @param temp
     */
    public static void function(Message<?> temp) {
//      使用“类 <?>” 表示只能取得内容，但不允许设置内容
        // 无法设置
//      temp.setMsg("HelloA");
        System.out.println("Message=" + temp.getMsg());

    }


    /**
     * 肉类
     */
    static class Meat {

        @Override
        public String toString() {
            return "肉类";
        }

    }

    /**
     * 汤类
     */
    static class Soup {
        @Override
        public String toString() {
            return "汤类";
        }
    }

    /**
     * 加热食物
     * @param food 食物
     * @param <T>
     * @return 加热后的食物
     */
    public static <T> T heat(T food) {
        System.out.println(food + " 加热完毕");
        return food;
    }


}

/**
 * “T”用于泛型类型的声明
 * 现在这个 T 表示需要设置一个泛型来定义 msg 属性类型
 *
 * @param <T>
 */
class Message<T> {
    private T msg;

    public T getMsg() {
        return msg;
    }

    public void setMsg(T msg) {
        this.msg = msg;
    }
}

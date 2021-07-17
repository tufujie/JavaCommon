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
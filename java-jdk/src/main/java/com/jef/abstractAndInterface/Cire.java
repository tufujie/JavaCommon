package com.jef.abstractAndInterface;

public class Cire implements CalInterface {
    public float getArea(float r) {
        float area = PI * r * r;//计算圆面积并赋值给变量 area
        return area;//返回计算后的圆面积
    }

    public float getCircumference(float r) {
        float circumference = 2 * PI * r; //计算圆周长并赋值给变量 circumferencereturn circumference; //返回计算后的圆周长
        return circumference;
    }

    public static void main(String[] args) {Cire c = new Cire();
        float f = c.getArea(2.0f);
        System.out.println(Float.toString(f));
    }
}
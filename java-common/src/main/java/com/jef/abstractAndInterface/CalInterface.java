package com.jef.abstractAndInterface;

public interface CalInterface {
    public static final float PI = 3.14159f;//定义用于表示圆周率的常量 PI
    public abstract float getArea(float r);//定义一个用于计算面积的方法 getArea()
    public abstract float getCircumference(float r);//定义一个用于计算周长的方法 getCircumference()
}
package com.jef.designpattern.action.vistor.user.impl;

import com.jef.designpattern.action.vistor.user.User;
import com.jef.designpattern.action.vistor.visitor.Visitor;

// 老师
public class Teacher extends User {

    /**
     * 升本率
     */
    private double entranceRatio;

    public Teacher(String name, String identity, String clazz, double entranceRatio) {
        super(name, identity, clazz);
        this.entranceRatio = entranceRatio;
    }

    /**
     * 老师接收访问者的访问
     *
     * @param visitor 访问者
     */
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public double getEntranceRatio() {
        return entranceRatio;
    }

    public void setEntranceRatio(double entranceRatio) {
        this.entranceRatio = entranceRatio;
    }

}

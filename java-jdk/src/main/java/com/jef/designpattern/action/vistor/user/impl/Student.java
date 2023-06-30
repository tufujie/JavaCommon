package com.jef.designpattern.action.vistor.user.impl;

import com.jef.designpattern.action.vistor.user.User;
import com.jef.designpattern.action.vistor.visitor.Visitor;

// 学生
public class Student extends User {

    /**
     * 排名
     */
    private int ranking;

    public Student(String name, String identity, String clazz, int ranking) {
        super(name, identity, clazz);
        this.ranking = ranking;
    }

    /**
     * 学生接收访问者的访问
     *
     * @param visitor 访问者
     */
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}

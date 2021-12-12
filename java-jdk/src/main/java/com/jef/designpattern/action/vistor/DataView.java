package com.jef.designpattern.action.vistor;

import com.jef.constant.BasicConstant;
import com.jef.designpattern.action.vistor.user.User;
import com.jef.designpattern.action.vistor.user.impl.Student;
import com.jef.designpattern.action.vistor.user.impl.Teacher;
import com.jef.designpattern.action.vistor.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

// 数据看版
public class DataView {

    List<User> userList = new ArrayList<User>();

    public DataView() {
        userList.add(new Student(BasicConstant.USER_NAME, "重点班", "一年一班", 1));
        userList.add(new Student(BasicConstant.USER_NAME_ZHANGSAN, "重点班", "一年一班", 2));
        userList.add(new Student(BasicConstant.USER_NAME_LISI, "普通班", "三年四班", 3));
        userList.add(new Student(BasicConstant.USER_NAME_WANGWU, "普通班", "二年三班", 4));
        userList.add(new Teacher(BasicConstant.TEACHER_NAME_ZHANGSAN, "特级教师", "一年一班", 99));
        userList.add(new Teacher(BasicConstant.TEACHER_NAME_LISI, "普通教师", "一年一班", 50));
        userList.add(new Teacher(BasicConstant.TEACHER_NAME_WANGWU, "实习教师", "三年四班", 30));
    }

    /**
     * 展示给某一类访问者看
     *
     * @param visitor 访问者
     * @author Jef
     * @date 2021/12/12
     */
    public void show(Visitor visitor) {
        for (User user : userList) {
            user.accept(visitor);
        }
    }

}

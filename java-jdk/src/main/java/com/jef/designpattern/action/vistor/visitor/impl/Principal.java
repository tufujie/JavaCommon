package com.jef.designpattern.action.vistor.visitor.impl;

import com.jef.designpattern.action.vistor.user.impl.Student;
import com.jef.designpattern.action.vistor.user.impl.Teacher;
import com.jef.designpattern.action.vistor.visitor.Visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 校长
public class Principal implements Visitor {

    private Logger logger = LoggerFactory.getLogger(Principal.class);

    public void visit(Student student) {
        logger.info("学生信息 姓名：{} 班级：{}", student.name, student.clazz);
    }

    public void visit(Teacher teacher) {
        logger.info("老师信息 姓名：{} 班级：{} 升学率：{}", teacher.name, teacher.clazz, teacher.getEntranceRatio());
    }

}

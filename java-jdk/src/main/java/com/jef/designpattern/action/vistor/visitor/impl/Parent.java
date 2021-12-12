package com.jef.designpattern.action.vistor.visitor.impl;

import com.jef.designpattern.action.vistor.user.impl.Student;
import com.jef.designpattern.action.vistor.user.impl.Teacher;
import com.jef.designpattern.action.vistor.visitor.Visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 家长
 *
 * @author Jef
 * @date 2021/12/12
 */
public class Parent implements Visitor {

    private Logger logger = LoggerFactory.getLogger(Parent.class);

    public void visit(Student student) {
        logger.info("学生信息 姓名：{} 班级：{} 班级类型：{} 排名：{}", student.name, student.clazz, student.identity, student.getRanking());
    }

    public void visit(Teacher teacher) {
        logger.info("老师信息 姓名：{} 班级：{} 级别：{}", teacher.name, teacher.clazz, teacher.identity);
    }

}

package com.jef.designpattern.action.vistor.visitor;

import com.jef.designpattern.action.vistor.user.impl.Student;
import com.jef.designpattern.action.vistor.user.impl.Teacher;

/**
 * 访问者模式，同一个事物，不同视角下的访问信息不同
 *
 * @author Jef
 * @date 2021/12/12
 */
public interface Visitor {

    // 访问学生信息
    void visit(Student student);

    // 访问老师信息
    void visit(Teacher teacher);

}

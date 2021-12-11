package com.jef.designpattern.action.interpreter;

import com.jef.io.blog.FileGlobal;

/**
 * 测试解释器模式的客户端
 * @author Jef
 * @date 2019/3/1
 */
public class Client {
    public static void main(String[] args) throws Exception {
        test2();
        test3();
    }

    private static void test1() throws Exception {
        // 准备上下文
        Context c = new Context(FileGlobal.INTERPRETER_TEST_XMl_DIR);
        // 想要获取 c 元素的值，也就是如下表达式的值："root/a/b/c"
        // 首先要构建解释器的抽象语法树
        ElementExpression root = new ElementExpression("root");
        ElementExpression aEle = new ElementExpression("a");
        ElementExpression bEle = new ElementExpression("b");
        ElementExpression cEle = new ElementExpression("c");
        PropertyTerminalExpression prop = new PropertyTerminalExpression("name");
        // 组合
        root.addEle(aEle);
        aEle.addEle(bEle);
        bEle.addEle(cEle);
        cEle.addEle(prop);
        // 调用
        String[] ss = root.interpret(c);
        System.out.println("c的属性name的值是=" + ss[0]);
        /*
        如果要使用同一个上下文，连续进行解析，需要重新初始化上下文对象
        比如，要连续的重新再获取一次属性name的值，当然你可以重新组合元素，
        重新解析，只要是在使用同一个上下文，就需要重新初始化上下文对象
         */
        c.reInitV1();
        String[] ss2 = root.interpret(c);
        System.out.println("重新获取c的属性值是=" + ss2[0]);
    }

    private static void test2() throws Exception {
        // 准备上下文
        Context c = new Context(FileGlobal.INTERPRETER_TEST_XMl_DIR);
        // 想要获取 c 元素的值，也就是如下表达式的值："root/a/b/c"
        // 首先要构建解释器的抽象语法树
        ElementExpression root = new ElementExpression("root");
        ElementExpression aEle = new ElementExpression("a");
        ElementExpression bEle = new ElementExpression("b");
        ElementTerminalExpression dEle = new ElementTerminalExpression("d");
        // 组合起来
        root.addEle(aEle);
        aEle.addEle(bEle);
        bEle.addEle(dEle);
        // 调用
        String[] ss = root.interpret(c);
        for (String s : ss) {
            System.out.println("d的值是=" + s);
        }
    }

    private static void test3() throws Exception {
        // 准备上下文
        Context c = new Context(FileGlobal.INTERPRETER_TEST_XMl_DIR);
        // 想要获取 c 元素的值，也就是如下表达式的值："root/a/b/c"
        // 首先要构建解释器的抽象语法树
        ElementExpression root = new ElementExpression("root");
        ElementExpression aEle = new ElementExpression("a");
        ElementExpression bEle = new ElementExpression("b");
        ElementExpression dEle = new ElementExpression("d");
        PropertyTerminalExpression prop = new PropertyTerminalExpression("id");
        // 组合起来
        root.addEle(aEle);
        aEle.addEle(bEle);
        bEle.addEle(dEle);
        dEle.addEle(prop);
        // 调用
        String[] ss = root.interpret(c);
        for (String s : ss) {
            System.out.println("d的属性id值是=" + s);
        }
    }
}
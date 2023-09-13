package com.jef.groovy;

import com.jef.constant.BasicConstant;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilationFailedException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author tufujie
 * @date 2023/9/13
 */
public class GroovyTest {


    @Test
    void testGroovy1() throws CompilationFailedException {
        GroovyShell groovyShell = new GroovyShell();
        groovyShell.evaluate("println 'Hello Jef'");
    }

    // 调用GroovyShell_1_1文件
    @Test
    void testGroovy2() throws CompilationFailedException, IOException {
        GroovyShell groovyShell = new GroovyShell();
        Object result = groovyShell.evaluate(new File("src/main/java/com/jef/groovy/GroovyShell_1_1.groovy"));
        // result 为groovy脚本的返回值
        System.out.println("返回值=" + result.toString());
    }

    // 调用GroovyShell_1_2
    @Test
    void testGroovy3() throws CompilationFailedException, IOException {
        // 调用带参数的groovy shell时，使用bind绑定数据
        Binding binding = new Binding();
        // 用Binding类绑定参数
        binding.setProperty("name", BasicConstant.USER_NAME);
        String name = (String) binding.getProperty("name");
        System.out.println("name=" + name);
        // 获取script内部定义变量值
        GroovyShell groovyShell = new GroovyShell(binding);
        // 向script中传递变量
        binding.setVariable("phone", BasicConstant.USER_PHONE);
        groovyShell.evaluate("println 'My phone is ' + phone;");

        groovyShell.evaluate("date = new Date();");
        Date date = (Date) binding.getVariable("date");
        System.out.println("Date:" + date.getTime());
        // 执行groovy文件时，传入Binging类
        Object result = groovyShell.evaluate(new File("src/main/java/com/jef/groovy/GroovyShell_1_2.groovy"));
        System.out.println("返回值=" + result.toString());
    }

    // 调用字符串格式的脚本
    @Test
    void testGroovy4() throws CompilationFailedException, IOException {
        Binding binding = new Binding();
        GroovyShell groovyShell = new GroovyShell(binding);
        //获取script脚本执行返回值
        binding.setVariable("phone", BasicConstant.USER_PHONE);
        String scriptText = "def callMethod(){return 'My phone is ' + phone};callMethod();";
        String result = (String) groovyShell.evaluate(scriptText);
        System.out.println("result:" + result);

        groovyShell = null;
        binding = null;
    }

}
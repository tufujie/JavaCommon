package com.jef.business;

import com.jef.util.ThreadUtil;
import com.jef.util.TimeUtil;

import java.util.Date;
import java.util.Map;

/**
 * 模拟实际业务
 *
 * @author Jef
 * @date 2018/12/28 15:14
 */
public class BusinessDemo {

    /**
     * 实际业务
     *
     * @param taskNum 任务号，模拟实际传参
     */
    public static void taskNoReturn(int taskNum) {
        System.out.println("taskNum=" + taskNum + "开始工作..");
        ThreadUtil.printThreadName();
        Date dateStart = new Date();
        // 模拟业务工作开始
        int size = 100;
        int num = 0;
        for (int i = 1; i <= size; i++) {
            num += i;
        }
        System.out.println("taskNum=" + taskNum + "工作中..，内容为输出1到100的和加上taskNum，总和=" + num);
        // 模拟业务工作结束
        System.out.println("taskNum=" + taskNum + "工作结束..");
        Date dateEnd = new Date();
        TimeUtil.printAllUseTime(dateStart, dateEnd);
    }

    /**
     * 模拟执行具体的业务任务
     * 封装出来的执行任务方法
     *
     * @param num     值，任务传参1
     * @param useType 使用方式，任务传参2
     */
    public static int taskHasReturn(int num, String useType) {
        // 具体的业务任务开始
        int sum = 0;
        for (int i = num; i > 0; i--) {
            sum += i;
        }
        System.out.println(useType + "; 当前线程名称=" + ThreadUtil.getThreadName() + "; 执行任务完成, num=" + num + ", sum=" + sum);
        // 具体的业务任务结束
        return sum;
    }

    /**
     * 模拟执行具体的业务任务
     *
     * @param useType 使用方式
     */
    public static int taskHasReturn(String useType) {
        return taskHasReturn(5, useType);
    }

    /**
     * 业务处理
     *
     * @param param 请求参数
     * @author Jef
     * @date 2022/2/14
     */
    public void handlerBusiness(Map param) throws Exception {
        String id = (String) param.get("id");
        System.out.println("业务处理id=" + id);
    }

    /**
     * 业务上做一些事情
     */
    public static void doSomeThing() {
        System.out.println("业务处理一些事情");
    }

    /**
     * 业务上做一些其它事情
     */
    public static void doOthers() {
        System.out.println("业务处理一些其它事情");
    }
}

package com.jef.business;

import com.jef.util.ThreadUtil;

import java.util.Date;

/**
 * 模拟实际业务
 * @author Jef
 * @date 2018/12/28 15:14
 */
public class BusinessDemo {

    public void test() {
        test(1);
    }

    /**
     * 实际业务
     * @param taskNum 任务号，模拟实际传参
     */
    public void test(int taskNum) {
        System.out.println("taskNum=" + taskNum + "开始工作..");
        ThreadUtil.print();
        Date dateTmp1 = new Date();
        // 模拟业务工作中
        int size = 100;
        int num = 0;
        for (int i = 1; i <= size; i++) {
            num += i;
        }
        System.out.println("taskNum=" + taskNum + "工作中..，内容为输出1到100的和加上taskNum，总和=" + num);
        // 模拟业务工作中
        System.out.println("taskNum=" + taskNum + "工作结束..");
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println("taskNum=" + taskNum + "，当前任务运行时间【" + time + "毫秒】");
    }

    /**
     * 具体的业务任务
     * 封装出来的执行任务方法
     * @param num 值，任务传参1
     * @param useType 使用方式，任务传参2
     */
    public static void taskOfDemo(int num, String useType) {
        // 具体的业务任务开始
        int sum = 0;
        for(int i = num; i > 0; i--) {
            sum += i;
        }
        System.out.println(useType + " end, num=" + num + ", sum=" + sum);
        // 具体的业务任务结束
    }
}

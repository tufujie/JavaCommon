package com.jef.thread;

import com.jef.business.BusinessDemo;
import com.jef.thread.useThread.ImplRunnableTest;
import com.jef.util.ThreadUtil;

import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * @author Jef
 * @date 20181228
 */
public class ThreadUtilTest {
    private final int count = 1000;

    @Test
    public void testPrintThread() {
        ThreadUtil.printThreadName(ThreadUtil.getThreads());
    }

    /**
     * 不使用线程的方式
     * 1.58秒
     */
    @Test
    public void testNotUseThread()  {
        StopWatch stopWatchTwo = new StopWatch();
        stopWatchTwo.start();
        for (int num = count; num > 0; num--) {
            BusinessDemo.taskHasReturn(num, "直接for循环调用");
        }
        stopWatchTwo.stop();
        System.out.println("直接for循环调用" + stopWatchTwo.getTotalTimeSeconds() + "s");
    }

    /**
     * 使用线程的方式1
     * 0.07秒
     */
    @Test
    public void testUseThread() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int num = count; num > 0; num--) {
            // for循环里执行线程
            Runnable runnable = new ImplRunnableTest(num);
            ThreadUtil.executeThread("测试", runnable);
        }
        stopWatch.stop();
        System.out.println("使用线程执行时间" + stopWatch.getTotalTimeSeconds() + "s");
    }

    /**
     * 使用线程的方式2
     * 推荐使用方式
     * 0.13秒
     */
    @Test
    public void testUseThreadTwo() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int num = count; num > 0; num--) {
            int finalNum = num;
            Runnable runnable = () -> {
                try {
                    BusinessDemo.taskHasReturn(finalNum, "使用线程");
                } catch (Exception e) {

                }
            };
            ThreadUtil.executeThread("测试", runnable);
        }
        stopWatch.stop();
        System.out.println("使用线程执行时间" + stopWatch.getTotalTimeSeconds() + "s");
    }

    /**
     * 使用线程的方式3
     * 推荐使用方式
     * 0.13秒
     */
    @Test
    public void testUseThreadThree() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int num = count; num > 0; num--) {
            int finalNum = num;
            Runnable runnable = () -> {
                try {
                    BusinessDemo.taskHasReturn(finalNum, "使用线程");
                } catch (Exception e) {

                }
            };
            ThreadUtil.getInstance("测试").submit(runnable, "测试");
        }
        stopWatch.stop();
        System.out.println("使用线程执行时间" + stopWatch.getTotalTimeSeconds() + "s");
    }

}

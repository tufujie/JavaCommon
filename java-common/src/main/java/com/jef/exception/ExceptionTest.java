package com.jef.exception;


import com.jef.util.StringUtils;

public class ExceptionTest {
    public static void main(String[] args) throws Exception {
        int a = 2, b =0;
        // 一般运行时异常是不做捕获处理的，由程序严格控制即可，这里仅是为了捕获进行输出
        try {
            test(a, b);
        } catch (ArithmeticException e) {
            // 异常处理
            System.out.println(e);
        } catch (Exception e) {
            // 异常处理，所有异常的父类，添加了子类捕获，就不用添加这个捕获了，但是这样也可以捕获一些未知异常
            System.out.println(e);
        } finally {
            System.out.println("这里表示一定会执行");
        }
        try {
            testTwo(a, b);
        } catch (Exception e) {
            System.out.println(e);
        }
        recover(null, 1);
        // 不捕获，会直接打出堆栈信息，便于定位
        testTwo(a, b);
    }

    /**
     * 测试抛出异常，先捕获，然后抛出，让上一级处理
     * @throws Exception
     */
    private static void test(int a, int b) throws Exception {
        try {
            System.out.println(a / b);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 测试抛出异常，等效于test()，推荐使用，这是终止模型
     * @throws Exception
     */
    private static void testTwo(int a, int b) throws Exception {
        System.out.println(a / b);
    }

    /**
     * 使用恢复模型，通过尝试多次，尽量使得代码能够成功
     * @param accessToken 通行牌，可能为null
     * @param tryNum 尝试次数，从1开始，
     */
    private static void recover(String accessToken, int tryNum) {
        int canTryNum = 3; // 允许尝试次数，3
        try {
            while (tryNum <= canTryNum) {
                if (StringUtils.isNotEmpty(accessToken)) {
                    if (tryNum == 2) {
                        // 第二次请求时，可能需要手动回滚数据或者其它，眼看即将成功了，又突然发生了异常
                        testTwo(3, 0);
                    }
                    System.out.println("success");
                    break;
                } else {
                    System.out.println("else");
                    tryNum++;
                    // 这边可以直接从远程或者其他地方获取accessToken，这里写死
                    accessToken = "access";
                }
            }
        } catch (Exception e) {
            // 打出堆栈信息
            e.printStackTrace();
            System.out.println("catch");
            tryNum++;
            // 这边可以直接从远程或者其他地方获取accessToken，这里写死
            accessToken = "access";
            recover(accessToken, tryNum);
        }
    }
}

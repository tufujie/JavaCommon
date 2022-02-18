package com.jef.jvm;

/**
 * JVM垃圾回收
 *
 * @author Jef
 * @date 2022/2/15
 */
public class JVMGCDemo {

    public static void main(String[] args) {
        JVMGCDemo jvmgcDemo = new JVMGCDemo();
        jvmgcDemo = null;
        System.gc();
    }

    /**
     * Java技术使用finalize()方法在垃圾收集器将对象从内存中清除出去前，做必要的清理工作。
     * 这个方法是由垃圾收集器在确定这个对象没有被引用时对这个对象调用的。
     * 它是在Object类中定义的，因此所有的类都继承了它。子类覆盖finalize()方法以整理系统资源或者执行其他清理工作。
     * finalize()方法是在垃圾收集器删除对象之前对这个对象调用的。
     *
     * @author Jef
     * @date 2022/2/15
     */
    @Override
    protected void finalize() {
        System.out.println("GC在回收对象...");
    }


}
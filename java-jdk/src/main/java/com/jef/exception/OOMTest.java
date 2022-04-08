package com.jef.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存溢出异常OOM，java.lang.OutOfMemoryError
 * Xmx20m -Xms20m -XX:+HeapDumpOnOutOfMemoryError
 * OOM排查：如果能看到日志，可以从打印的日志中获取到发送异常的代码行，再去代码中查找具体哪块的代码有问题。如果没有记录日志，
 * 通过设置的 -XX:+HeapDumpOnOutOfMemoryError 在发生OOM的时候生成.hprof文件，
 * 再导入JProfiler能够看到是由于哪个对象造成的OOM，再通过这个对象去代码中寻找
 *
 * @author Jef
 * @date 2022/2/25
 */
public class OOMTest {

    public static void main(String[] args) {
        List<Object> objList = new ArrayList();
        while (true) {
            objList.add(new Object());
        }
    }
}
package com.jef.test.exception;

import com.jef.exception.BasicException;
import org.junit.Test;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 异常回滚
 * 解决方案：
 * 方案1.例如service层处理事务，那么service中的方法中不做异常捕获（方案1），或者在catch语句中最后增加throw new RuntimeException()语句，以便让aop捕获异常再去回滚，并且在service上层（webservice客户端，view层action）要继续捕获这个异常并处理（方案2）
 * 方案2.在service层方法的catch语句中增加：TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();语句，手动回滚，这样上层就无需去处理异常（方案3）
 * @author Jef
 * @date 2018/12/22 11:26
 */
public class ExceptionRollBackTest {

    /**
     * 异常回滚测试1，不捕获不抛出
     * @throws Exception
     */
    @Test
    public void testRollBackOne() throws Exception {
        BasicException.basicException(3, 0);
    }

    /**
     * 异常回滚测试2，捕获，抛出
     * 显式抛出异常，并不能经任何处理，这样aop代理才能捕获到方法的异常，才能进行回滚，默认情况下aop只捕获runtimeexception的异常，
     * 但可以通过配置来捕获特定的异常并回滚
     * @throws Exception
     */
    @Test
    public void testRollBackTwo() throws Exception {
        try {
            BasicException.basicException(3, 0);
        } catch (Exception e) {
            BasicException.logException(e);
            throw new Exception();
        }
    }

    /**
     * 异常回滚测试3，捕获，回滚
     * @throws Exception
     */
    @Test
    public void testRollBackThree() throws Exception {
        try {
            BasicException.basicException(3, 0);
        } catch (Exception e) {
            BasicException.logException(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }


}

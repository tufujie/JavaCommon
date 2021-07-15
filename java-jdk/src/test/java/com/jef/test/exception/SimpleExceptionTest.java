package com.jef.test.exception;

import com.jef.exception.BasicException;
import org.junit.Test;

/**
 * @author Jef
 * @create 20180715
 */
public class SimpleExceptionTest {
    @Test
    public void logExceptionTest() {
        try {
            BasicException.basicException(3, 0);
        } catch (Exception e) {
            BasicException.logException(e);
        }
    }
}

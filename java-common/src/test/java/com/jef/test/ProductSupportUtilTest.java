package com.jef.test;

import com.jef.util.ProductSupportUtil;
import org.junit.Test;

/**
 * @author Jef
 * @date 2020/3/8
 */
public class ProductSupportUtilTest {

    /**
     * 获取支持版需要设置的值
     */
    @Test
    public void testGetProductValue() {
         // 全版本支持
         // 63
         String supportProduct = "1,2,4,8,16,32";
         System.out.println(supportProduct + "=" + ProductSupportUtil.getSetProductValue(supportProduct));
         // 只支持标准版
         // 1
        supportProduct = "1";
        System.out.println(supportProduct + "=" + ProductSupportUtil.getSetProductValue(supportProduct));
         // 只支持资管版
         // 8
        supportProduct = "8";
        System.out.println(supportProduct + "=" + ProductSupportUtil.getSetProductValue(supportProduct));
        // 只支持"1,2,4,16,32"版
        // 55
        supportProduct = "1,2,4,16,32";
        System.out.println(supportProduct + "=" + ProductSupportUtil.getSetProductValue(supportProduct));
        // 只支持"1,2,4,16"版
        // 23
        supportProduct = "1,2,4,16";
        System.out.println(supportProduct + "=" + ProductSupportUtil.getSetProductValue(supportProduct));
        // 只支持"1,2,4"版
        // 7
        supportProduct = "1,2,4";
        System.out.println(supportProduct + "=" + ProductSupportUtil.getSetProductValue(supportProduct));
        // 只支持"1,2"版
        // 3
        supportProduct = "1,2";
        System.out.println(supportProduct + "=" + ProductSupportUtil.getSetProductValue(supportProduct));
    }

    @Test
    public void only8Match() {
        // 8
        ProductSupportUtil.getSetProductValue("1,4,8,16", "8");
    }

    @Test
    public void only8NotMatch() {
        // 21
        ProductSupportUtil.getSetProductValue("1,4,8,16", "1,4,16");
    }

    @Test
    public void only4Match() {
        // 4
        ProductSupportUtil.getSetProductValue("1,4,8,16,32", "4");
    }

    @Test
    public void only14Match() {
        // 5
        ProductSupportUtil.getSetProductValue("1,4,8,16,32", "1,4");
    }

    @Test
    public void only114Match() {
        // 7
        ProductSupportUtil.getSetProductValue("1,4,8,16,32", "1,2,4");
    }

}
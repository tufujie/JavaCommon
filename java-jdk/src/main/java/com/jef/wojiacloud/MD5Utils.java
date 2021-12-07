package com.jef.wojiacloud;

import com.jef.util.StringUtils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @date 2021/12/7
 */
public class MD5Utils {

    /**
     * 获取MD5哈希
     *
     * @param encData 需要哈希的字符
     * @return
     * @author Leo
     * @date:Apr 5, 2016 11:29:04 AM
     */
    @Deprecated
    public static String getMD5Code(String encData) {
        if (StringUtils.isEmpty(encData)) {
            return null;
        }
        return hash(encData);
    }

    /**
     * md5 哈希
     * 推荐此方法，不会返回null
     *
     * @param input 原文，不允许为null
     * @return
     */
    public static String hash(String input) {
        return Hashing.md5().hashString(Objects.requireNonNull(input, "input cannot be null!"), StandardCharsets.UTF_8).toString();
    }

}
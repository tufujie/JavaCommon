package com.jef.string;
import java.util.Comparator;

/**
 * 找出最大串，即字典序排在最后的串
 * @author Jef
 * @date 2020/4/5
 */
public class StringCaseInsensitiveCompare implements Comparator<String> {

    @Override
    public int compare(String lhs, String rhs) {
        return lhs.compareToIgnoreCase(rhs);
    }
}
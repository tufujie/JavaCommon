package com.jef.regular;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularTest {

    @Test
    public void testNumber() {
        String a = "0";
        // 正整数和0
        Pattern quantityPat = Pattern.compile("^[0-9]*$");
        Matcher matcher = quantityPat.matcher(a);
        System.out.println(matcher.matches());
        String b = "-500";
        matcher =  quantityPat.matcher(b);
        System.out.println(matcher.matches());
        // 负整数
        Pattern quantityPatB = Pattern.compile("^-[0-9]*[1-9][0-9]*$");
        matcher = quantityPatB.matcher(b);
        System.out.println(matcher.matches());
    }

    @Test
    public void testRegular() {
        String str = "成都市(成华区)(武侯区)(高新区)";
        Pattern p = Pattern.compile(".*?(?=\\()");
        Matcher m = p.matcher(str);
        if (m.find()) {
            System.out.println(m.group());
        }
    }
}

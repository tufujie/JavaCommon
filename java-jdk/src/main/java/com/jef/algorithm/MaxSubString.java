package com.jef.algorithm;

import java.util.Scanner;

/**
 * 最长匹配子串
 *
 * @author Jef
 * @date 2022/5/21
 */
public class MaxSubString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] strArray = str.split("");
        int oNum = 0, charNum = 0;
        for (String strTemp : strArray) {
            if ("o".equals(strTemp)) {
                oNum++;
            }
        }
        // 说明一直不用break
        int breakNum = oNum;
        if (oNum % 2 == 0) {
            breakNum++;
        }
        int tempBreakNum = 0;
        for (String strTemp : strArray) {
            if ("o".equals(strTemp)) {
                tempBreakNum++;
            }
            if (tempBreakNum >= breakNum) {
                break;
            }
            charNum++;
        }

        // 先形成数组
        for (int i = 1; i < strArray.length; i++) {
            int k = 0;
            int tempBreakNumV2 = 0, charNumV2 = 0;
            String[] strArrayTemp = new String[strArray.length];
            for (int m = i; m < strArray.length; m++) {
                strArrayTemp[k++] = strArray[m];
            }
            for (int j = 0; j < i; j++) {
                strArrayTemp[k++] = strArray[j];
            }
            for (String strTemp : strArrayTemp) {
                if ("o".equals(strTemp)) {
                    tempBreakNumV2++;
                }
                if (tempBreakNumV2 >= breakNum) {
                    break;
                }
                charNumV2++;
            }
            if (charNumV2 > charNum) {
                charNum = charNumV2;
            }
        }
        System.out.println(charNum);
    }

}
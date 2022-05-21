package com.jef.algorithm;

import java.util.Scanner;

/**
 * 字符串规则匹配
 *
 * @author Jef
 * @date 2022/5/21
 */
public class RegularPick {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String pickStr = scanner.nextLine();
        String[] strArray = str.split(" ");
        String[] pickStrArray = pickStr.split("");
        String result = "";
        for (int i = 0; i < strArray.length; i++) {
            String strTemp = strArray[i];
            // 不匹配的
            // aabc
            // a.b
            if (!pickStr.contains("*") && strTemp.length() != pickStr.length()) {
                continue;
            }
            // abacd
            boolean pick = true;
            String[] strTempArray = strTemp.split("");
            int strStart = 0;
            for (int k = 0; k < pickStrArray.length; k++) {
                boolean find = false;
                boolean allContinue = false;
                for (int j = strStart; j < strTempArray.length; j++) {
                    // a
                    if (".".equals(pickStrArray[k])) {
                        strStart++;
                        find = true;
                        break;
                    }
                    if ("*".equals(pickStrArray[k])) {
                        find = true;
                        if (k == pickStrArray.length - 1) {
                            allContinue = true;
                            break;
                        } else {
                            // 这种也匹配
                            // bacdc
                            // *dc
                            // 这种也匹配
                            // bacdcbc
                            // *dc*
                            boolean findTemp = false;
                            for (int n = k + 1; n < pickStrArray.length; n++) {
                                for (int m = k; m < strTempArray.length; m++) {
                                    if (strTempArray[m].equals(pickStrArray[n])) {
                                        findTemp = true;
                                        strStart = m;
                                        break;
                                    }
                                }
                                if (findTemp) {
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    if (pickStrArray[k].equals(strTempArray[j])) {
                        find = true;
                        strStart++;
                        break;
                    }
                }
                if (!find) {
                    pick = false;
                    break;
                }
                if (allContinue) {
                    pick = true;
                    break;
                }
            }
            if (pick) {
                // 匹配的情况下进行处理
                if (result != null && result != "") {
                    result += ",";
                }
                result += i;
            }
        }
        if (result != null && result != "") {
            System.out.println(result);
        } else {
            System.out.println("-1");
        }

    }

}
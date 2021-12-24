package com.jef.algorithm;

/**
 * 判断句子是否为全字母句
 *
 * @author Jef
 * @date 2021/5/30
 */
public class CheckIfPangram {

    public static void main(String[] args) {
        System.out.println(new CheckIfPangram().checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));
        System.out.println(new CheckIfPangram().checkIfPangram("jef"));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @return int
     */
    public boolean checkIfPangram(String sentence) {
        String allChar = "abcdefghijklmnopqrskuvwxyz";
        String[] strings = allChar.split("");
        for (String string : strings) {
            if (!sentence.contains(string)) {
                return false;
            }
        }
        return true;
    }

}
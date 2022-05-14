import java.util.Scanner;

/**
 * Main类，核心测试类
 *
 * @author Jef
 * @date 2022/5/14
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(getLastStrLength(str));
    }

    public static int getLastStrLength(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        String[] strArray = str.split(" ");
        return strArray[strArray.length - 1].length();
    }

}
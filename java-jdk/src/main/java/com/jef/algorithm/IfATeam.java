package com.jef.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 是否一个团队
 * we are a team
 * we are not a team
 * we are a team
 * da pian zi
 * <p>
 * 5 5
 * 1 2 0
 * 2 3 0
 * 3 4 0
 * 4 5 0
 * 1 5 1
 *
 * @author Jef
 * @date 2022/5/14
 */
public class IfATeam {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] arr = str.split(" ");
        int n = Integer.valueOf(arr[0]), m = Integer.valueOf(arr[1]);
        if (n < 1 || n > 100000 || m < 1 || m > 100000) {
            System.out.println("NULL");
            return;
        }
        HashMap<Integer, List<Integer>> relationMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            if (c == 0) {
                List<Integer> tempList = new ArrayList<>();
                if (relationMap.containsKey(a)) {
                    tempList = relationMap.get(a);
                }
                tempList.add(b);
                relationMap.put(a, tempList);

                List<Integer> tempListV2 = new ArrayList<>();
                if (relationMap.containsKey(b)) {
                    tempListV2 = relationMap.get(b);
                }
                tempListV2.add(a);
                relationMap.put(b, tempListV2);
            } else if (c == 1) {
                boolean teamFlag = false;
                if (relationMap.containsKey(a)) {
                    if (relationMap.get(a).contains(b)) {
                        teamFlag = true;
                    } else {
                        int num = 0;
                        Set<Integer> existNumA = new HashSet<Integer>();
                        existNumA.add(a);
                        while (++num <= n) {
                            List<Integer> tempList = relationMap.get(a);
                            if (tempList == null) {
                                num = n + 1;
                            } else {
                                for (Integer temp : tempList) {
                                    if (relationMap.get(temp) != null) {
                                        if (relationMap.get(temp).contains(b)) {
                                            num = n + 1;
                                            teamFlag = true;
                                        }
                                    }
                                    if (!existNumA.contains(temp)) {
                                        a = temp;
                                    }
                                }
                            }
                        }
                    }
                }
                if (teamFlag) {
                    System.out.println("we are a team");
                } else {
                    System.out.println("we are not a team");
                }
            } else {
                System.out.println("da pian zi");
            }
        }
    }

}
package com.jef.algorithm.greedy;

import com.jef.algorithm.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 无重叠区间-贪心算法、动态规划算法、递归算法
 * 给定⼀个区间的集合，找到需要移除区间的最⼩数量，使剩余区间互不重叠。 注意:可以认为区间的终点总是⼤于它的起点。区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1: 输⼊:[ [1,2], [2,3], [3,4], [1,3] ] 输出: 1 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2: 输⼊: [ [1,2], [1,2], [1,2] ] 输出: 2 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3: 输⼊: [ [1,2], [2,3] ] 输出: 0 解释: 你不需要移除任何区间，因为它们已经是⽆重叠的了。
 *
 * @author Jef
 * @date 2022/1/3
 */
public class NoDuplicateIntervas {
    // 保存中间结果
    private static HashMap<String, Integer> map = new HashMap();

    /**
     * 移除重复区间
     *
     * @param intervals 区间
     * @return java.lang.Integer
     * @author Jef
     * @date 2022/1/3
     */
    public static Integer removeDuplicateIntervas(Interval[] intervals) {
        // 将区间按起始点由⼩到⼤进⾏排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.getStart()));
        // ⾸次遍历从 -1,0 开始
        return removeSubDuplicate(-1, 0, intervals);
    }

    /**
     * 移除重复区间-递归算法
     *
     * @param intervals 区间
     * @return java.lang.Integer
     * @author Jef
     * @date 2022/1/3
     */
    private static Integer removeSubDuplicate(int pre, int cur, Interval[] intervals) {
        String key = pre + "," + cur;
        if (map.get(key) != null) {
            return map.get(key);
        }

        if (cur == intervals.length) {
            return 0;
        }
        int notRemove = Integer.MAX_VALUE;
        if (pre == -1 || intervals[pre].getEnd() <= intervals[cur].getStart()) {
            // 如果是⾸次遍历，或者 pre 区间的终点⼩于 cur 区间的起点（即区间不重叠），则 pre = cur; cur = cur+1
            notRemove = removeSubDuplicate(cur, cur + 1, intervals);
        }
        /*
         * 如果 pre 区间的终点⼤于 cur 区间的起点，代表两区间重叠，cur 指向后⼀个区间，即cur = cur + 1
         * 代表 cur 被移除，所以需要加1（代表这个区间被移除了）
         * */
        int remove = removeSubDuplicate(pre, cur + 1, intervals) + 1;
        int result = Math.min(notRemove, remove);
        map.put(key, result);
        // 取两者的较⼩值
        return result;
    }

    /**
     * 判断两区间是否重叠, i 区间的起点⽐ j 区间的⼤, 如果 j 区间的终点⽐ i 区间的起点⼤，则重叠
     */
    private static boolean isOverlapping(Interval i, Interval j) {
        return j.getEnd() > i.getStart();
    }

    /**
     * 移除重复区间-动态规划算法
     */
    public static Integer removeSubDuplicateWithDP(Interval[] intervals) {
        // 将区间按起始点由⼩到⼤进⾏排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.getStart()));
        int[] dp = new int[intervals.length];
        Arrays.fill(dp, 0);
        // 将 dp[0] 置为 1， 因为就算所有的区间都重叠，则连续不重叠区间到少也为 1
        dp[0] = 1;
        int result = 1;
        for (int i = 1; i < intervals.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (!isOverlapping(intervals[i], intervals[j])) {
                    max = Math.max(dp[j], max);
                }
            }
            dp[i] = max + 1;
        }
        return intervals.length - dp[intervals.length - 1];
    }

    /**
     * 移除重复区间-贪⼼算法
     */
    public static Integer removeSubDuplicateWithGreedy(Interval[] intervals) {
        // 将区间终点由⼩到⼤进⾏排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.getEnd()));
        // 设置第⼀个为当前区间
        int cur = 0;
        // 最⼤不重叠区间数，最⼩为1
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            // 不重叠
            if (intervals[cur].getEnd() < intervals[i].getStart()) {
                cur = i;
                count++;
            }
        }
        // 总区间个数减去最⼤不重叠区间数即最⼩被移除重叠区间
        return intervals.length - count;
    }
}
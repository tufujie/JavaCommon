package com.jef.algorithm;

/**
 * 解码异或后的数组
 *
 * @author Jef
 * @date 2021/5/30
 */
public class Decode {

    public static void main(String[] args) {
        System.out.println(new Decode().decode(new int[]{1,2,3}, 1));
        System.out.println(new Decode().decode(new int[]{6,2,7,3}, 4));
        System.out.println(new Decode().decode(new int[]{77083,67269,63994,52418,30962,3983,31890,10742,19126,62423,26547,61428,10473,1036,49489,68886,24762,69752,55998,38878,45063,63483,44250,92836,11644,85890,86099,58082,2737,66383,49734,72304,52462,43442,62135,95141,73992,30777,97041,80333,16374,97636,44878,89662,90977,92786,90798,97398,31628,7577,51710,11784,27681,11172,55336,22556,26225,8680,55269,18903,54727,99043,96047,29836,69342,45284,28640,21638,51373,51534,60011,59868,2280,11425,34623,34675,93682,78119,82115,64815,47959,5470,92302,99562,74996,22365,2095,23327}, 28218));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @param encoded
     * @return int
     */
    public int[] decode(int[] encoded, int first) {
        int[] arr = new int[encoded.length + 1];
        arr[0] = first;
        for (int j = 0; j < encoded.length; j++) {
            for (int i = 0; i < 1000000; i++) {
                if ((arr[j] ^ i) == encoded[j]) {
                    arr[j + 1] = i;
                    break;
                }
            }
        }
        return arr;
    }

}
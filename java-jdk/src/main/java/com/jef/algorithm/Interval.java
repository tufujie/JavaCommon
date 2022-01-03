package com.jef.algorithm;

/**
 * 区间类，包括起始值和终⽌值
 *
 * @author Jef
 * @date 2022/1/3
 */
public class Interval {

    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
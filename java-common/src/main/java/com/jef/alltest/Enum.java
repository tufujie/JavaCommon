package com.jef.alltest;

public enum Enum {
    MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);
    private Integer num;
    public Integer getNum() {
        return num;
    }
    Enum(Integer num) {
        this.num = num;
    }
}

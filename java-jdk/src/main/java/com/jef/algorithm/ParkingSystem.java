package com.jef.algorithm;

/**
 * 设计停车系统
 *
 * @author Jef
 * @date 2021/5/30
 */
public class ParkingSystem {
    public static void main(String[] args) {
        ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
        System.out.println(parkingSystem.addCar(1)); // 返回 true ，因为有 1 个空的大车位
        System.out.println(parkingSystem.addCar(2)); // 返回 true ，因为有 1 个空的中车位
        System.out.println(parkingSystem.addCar(3)); // 返回 false ，因为没有空的小车位
        System.out.println(parkingSystem.addCar(1)); // 返回 false ，因为没有空的大车位，唯一一个大车位已经被占据了
    }

    private int big;
    private int medium;
    private int small;

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     */
    public ParkingSystem(int big, int medium, int small) {
        this.big = big;
        this.medium = medium;
        this.small = small;
    }

    public boolean addCar(int carType) {
        if (carType == 1) {
           --big;
           return big >= 0;
        } else if (carType == 2) {
            --medium;
            return medium >= 0;
        } else if (carType == 3) {
            --small;
            return small >= 0;
        }
        return false;
    }

}
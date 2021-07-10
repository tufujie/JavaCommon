package com.jef.util;

import com.jef.entity.Point;
import com.jef.entity.Vehicle;
import com.jef.entity.VehicleMovedEvent;
import org.junit.Test;

/**
 * 动车运行测试
 *
 * @author Jef
 * @date 2020/6/20
 */
public class VehicleMoveTest {

    @Test
    public void testVehicleMove() {
        Vehicle vehicle = new Vehicle();
        String vehicleID = "深圳-龙岩动车";
        vehicle.setVelicleID(vehicleID);
        vehicle.setLocation(vehicleID, "1", "2");
        Point point = vehicle.getLocation(vehicleID);
        System.out.println("列车开始运行");
        System.out.println(vehicleID + "，当前位置，经度=" + point.getLatitude() + "，纬度=" + point.getLongitude());
        System.out.println("列车运行5秒后，刷新位置信息");
        VehicleMovedEvent event = new VehicleMovedEvent(vehicleID);
        vehicle.vehicleMoved(event);
        point = vehicle.getLocation(vehicleID);
        System.out.println(vehicleID + "，当前位置，经度=" + point.getLatitude() + "，纬度=" + point.getLongitude());
    }

}
package com.jef.map;

import org.locationtech.jts.algorithm.PointLocator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.operation.buffer.BufferOp;

/**
 * @author tufujie
 * @date 2023/8/18
 */
public class MapCircle {

    /**
     * 判断经纬度是否在圆内
     *
     * @param lon       目标经度
     * @param lat       目标纬度
     * @param dis       距离，米
     * @param centerLon 圆心经度
     * @param centerLat 圆心纬度
     * @return
     */
    public static boolean inCircle(float lon, float lat, long dis, float centerLon, float centerLat) {
        //创建一条直线
        Coordinate[] coordinates4 = new Coordinate[]{
                new Coordinate(centerLon, centerLat),
                new Coordinate(centerLon, centerLon + 0.01f * dis / 1000),
        };

        GeometryFactory gf = new GeometryFactory();
        Geometry gfLineString = gf.createLineString(coordinates4);
        double degree = dis / (2 * Math.PI * 6371004) * 360; //将度换算成米，公式为：degree = meter / (2 * Math.PI * 6371004) * 360
        //缓冲区建立
        BufferOp bufOp = new BufferOp(gfLineString);
        bufOp.setEndCapStyle(BufferOp.CAP_BUTT);
        Geometry bg = bufOp.getResultGeometry(degree);
        //点是否在多边形内判断
        Coordinate point = new Coordinate(lon, lat);
        PointLocator a = new PointLocator();
        boolean p1 = a.intersects(point, bg);
        if (p1) {
            System.out.println("point1:" + "该点在多边形内");
        } else {
            System.out.println("point1:" + "该点不在多边形内");
        }
        return p1;
    }

}
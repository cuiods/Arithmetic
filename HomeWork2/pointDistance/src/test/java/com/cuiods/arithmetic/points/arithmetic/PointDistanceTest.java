package com.cuiods.arithmetic.points.arithmetic;

import org.junit.Test;

import static org.junit.Assert.*;

public class PointDistanceTest {

    @Test
    public void minDistancePoint() {
        PointDistance pointDistance = new PointDistance();
        pointDistance.addPoint(new Point(1,2));
        pointDistance.addPoint(new Point(4,8));
        pointDistance.addPoint(new Point(2,5));
        pointDistance.addPoint(new Point(2,7));
        pointDistance.addPoint(new Point(9,10));
        pointDistance.addPoint(new Point(9,11));
        pointDistance.addPoint(new Point(2,90));
        pointDistance.addPoint(new Point(5,8));
        pointDistance.addPoint(new Point(6,7));
        DistanceResult result = pointDistance.minDistancePoint(DistanceMethod.ENUM);
        System.out.println(result.getMinDistance());
        System.out.println(result.getPoints());
    }
}
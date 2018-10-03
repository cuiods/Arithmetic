package com.cuiods.arithmetic.points.arithmetic;

import com.cuiods.arithmetic.points.model.RandomUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PointDistanceTest {

    @Test
    public void minDistancePoint() {
        PointDistance pointDistance = new PointDistance();
        List<Point> points = RandomUtil.generateRandomPoints(1000000, Integer.MAX_VALUE, Integer.MAX_VALUE);
        pointDistance.setPoints(points);
        double result = pointDistance.minDistancePointQuick(DistanceMethod.DIVIDE);
        System.out.println(result);
    }
}
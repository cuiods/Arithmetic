package com.cuiods.arithmetic.points.model;

import com.cuiods.arithmetic.points.arithmetic.Point;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class RandomUtil {

    public static List<Point> generateRandomPoints(int num, int xMax, int yMax) {
        List<Point> points = new ArrayList<>(num);
        Random random = new Random(new Date().getTime());
        for (int i = 0; i < num; i++) {
            points.add(new Point(random.nextInt(xMax),random.nextInt(yMax)));
        }
        return points;
    }

}

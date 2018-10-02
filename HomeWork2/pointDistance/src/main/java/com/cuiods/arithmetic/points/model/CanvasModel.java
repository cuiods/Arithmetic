package com.cuiods.arithmetic.points.model;

import com.cuiods.arithmetic.points.arithmetic.DistanceMethod;
import com.cuiods.arithmetic.points.arithmetic.DistanceResult;
import com.cuiods.arithmetic.points.arithmetic.Point;
import com.cuiods.arithmetic.points.arithmetic.PointDistance;

import java.util.List;


public class CanvasModel {

    private PointDistance pointDistance = new PointDistance();

    public void addPoint(Point point) {
        pointDistance.addPoint(point);
    }

    public DistanceResult calculateDistance(DistanceMethod method) {
        return pointDistance.minDistancePoint(method);
    }

    public int getSize() {
        return pointDistance.getPoints().size();
    }

    public void reset() {
        pointDistance.clear();
    }

    public void addPoints(List<Point> points) {
        pointDistance.getPoints().addAll(points);
    }

}

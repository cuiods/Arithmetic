package com.cuiods.arithmetic.points.arithmetic;

import javafx.util.Pair;

import java.util.List;

public class DistanceResult {

    private List<Pair<Point,Point>> points;
    private double minDistance;
    private long time = 0;

    public DistanceResult(List<Pair<Point, Point>> points, double minDistance) {
        this.points = points;
        this.minDistance = minDistance;
    }

    public List<Pair<Point, Point>> getPoints() {
        return points;
    }

    public void setPoints(List<Pair<Point, Point>> points) {
        this.points = points;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

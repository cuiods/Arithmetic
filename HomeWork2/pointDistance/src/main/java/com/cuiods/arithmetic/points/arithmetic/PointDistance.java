package com.cuiods.arithmetic.points.arithmetic;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class PointDistance {

    private List<Point> points = new ArrayList<>();

    private Comparator<Point> xCompare = Comparator.comparingInt(o -> o.x);

    private Comparator<Point> yCompare = Comparator.comparingInt(o -> o.y);

    public DistanceResult minDistancePoint(DistanceMethod method) {
        DistanceResult result = new DistanceResult(new ArrayList<>(),0);
        if (points.size() <= 1) {
            List<Pair<Point,Point>> resultPoint = new ArrayList<>();
            return new DistanceResult(resultPoint, 0);
        }
        switch (method) {
            case ENUM: return enumDistance();
            case DIVIDE: return divideDistance();
            default: return result;
        }
    }

    private DistanceResult enumDistance() {
        long start = System.nanoTime();
        double minDistance = Double.MAX_VALUE;
        List<Pair<Point,Point>> result = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                double distance = distance(points.get(i), points.get(j));
                if (distance < minDistance) {
                    result.clear();
                    result.add(new Pair<>(points.get(i), points.get(j)));
                    minDistance = distance;
                } else if (distance == minDistance) {
                    result.add(new Pair<>(points.get(i), points.get(j)));
                }
            }
        }
        long end = System.nanoTime();
        DistanceResult distanceResult = new DistanceResult(result, minDistance);
        distanceResult.setTime(end-start);
        return distanceResult;
    }

    private DistanceResult divideDistance() {
        long start = System.nanoTime();
        points.sort(xCompare);
        DistanceResult result = divideDistance(0, points.size()-1);
        long end = System.nanoTime();
        result.setTime(end-start);
        return result;
    }

    private DistanceResult divideDistance(int start, int end) {
        double minDistance = Double.MAX_VALUE;
        List<Pair<Point,Point>> result = new ArrayList<>();
        if (end - start == 1) {
            result.add(new Pair<>(points.get(start),points.get(end)));
            return new DistanceResult(result, distance(points.get(start),points.get(end)));
        }
        //划分左右
        double half = (end+start)/2.0;
        int leftHalf = (int) Math.floor(half);
        int rightHalf = (int) Math.ceil(half);
        DistanceResult resultLeft = divideDistance(start, leftHalf);
        DistanceResult resultRight = divideDistance(rightHalf, end);

        //划分中间分区
        double minHalf = Math.min(resultLeft.getMinDistance(), resultRight.getMinDistance());
        double leftXLine = points.get(leftHalf).x - minHalf;
        double rightXLine = points.get(rightHalf).x + minHalf;
        List<Point> middlePoints = new ArrayList<>();
        for (int i = leftHalf; i >=0; i--) {
            if (points.get(i).x >= leftXLine) middlePoints.add(points.get(i));
            else break;
        }
        for (int i = leftHalf+1; i < points.size(); i++) {
            if (points.get(i).x <= rightXLine) middlePoints.add(points.get(i));
            else break;
        }

        //计算中间分区的距离最小值
        double minMiddleDistance = Double.MAX_VALUE;
        List<Pair<Point,Point>> middleResult = new ArrayList<>();
        if (middlePoints.size() > 1 ) {
            middlePoints.sort(yCompare);
            for (int i = 0; i < middlePoints.size(); i++) {
                for (int j = i+1; j < middlePoints.size() && j < i+8; j++) {
                    double distance = distance(middlePoints.get(i), middlePoints.get(j));
                    if (distance < minMiddleDistance) {
                        middleResult.clear();
                        middleResult.add(new Pair<>(middlePoints.get(i), middlePoints.get(j)));
                        minMiddleDistance = distance;
                    } else if (distance == minMiddleDistance) {
                        middleResult.add(new Pair<>(middlePoints.get(i), middlePoints.get(j)));
                    };
                }
            }
        }
        DistanceResult resultMiddle = new DistanceResult(middleResult, minMiddleDistance);

        //合并结果
        if (resultLeft.getMinDistance() < resultRight.getMinDistance()) {
            minDistance = resultLeft.getMinDistance();
            result = resultLeft.getPoints();
        } else if (resultLeft.getMinDistance() == resultRight.getMinDistance()) {
            minDistance = resultLeft.getMinDistance();
            result = resultLeft.getPoints();
            result.removeAll(resultRight.getPoints());
            result.addAll(resultRight.getPoints());
        } else {
            minDistance = resultRight.getMinDistance();
            result = resultRight.getPoints();
        }
        if (minDistance == resultMiddle.getMinDistance()) {
            result.removeAll(resultMiddle.getPoints());
            result.addAll(resultMiddle.getPoints());
        } else if (minDistance > resultMiddle.getMinDistance()) {
            minDistance = resultMiddle.getMinDistance();
            result = resultMiddle.getPoints();
        }
        return new DistanceResult(result, minDistance);
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void clear() {
        points.clear();
    }

    private double distance(Point a, Point b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

}

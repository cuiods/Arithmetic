package com.cuiods.arithmetic.points.arithmetic;

import javafx.util.Pair;

import java.util.*;

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

    public double minDistancePointQuick(DistanceMethod method) {
        if (points.size() <= 1) {
            return 0;
        }
        switch (method) {
            case ENUM: return enumDistanceQuick();
            case DIVIDE: return divideDistanceQuick();
            default: return 0;
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

    private double enumDistanceQuick() {
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                double distance = distance(points.get(i), points.get(j));
                if (distance < minDistance) {
                    minDistance = distance;
                }
            }
        }
        return minDistance;
    }

    private DistanceResult divideDistance() {
        long start = System.nanoTime();
        points.sort(xCompare);
        DistanceResult result = divideDistance(0, points.size()-1);
        long end = System.nanoTime();
        result.setTime(end-start);
        return result;
    }

    private double divideDistanceQuick() {
        points.sort(xCompare);
        return divideDistanceQuick(0, points.size()-1);
    }

    private double divideDistanceQuick(int start, int end) {
        double minDistance = Double.MAX_VALUE;
        if (end - start == 0) return minDistance;
        if (end - start == 1) return distance(points.get(start),points.get(end));

        int half = (end+start)>>1;
        double resultLeft = divideDistanceQuick(start, half);
        double resultRight = divideDistanceQuick(half, end);
        double minHalf = Math.min(resultLeft, resultRight);
        minDistance = minHalf;

        List<Point> middlePoints = new ArrayList<>();
        for (int i = start; i < end; i++) {
            if (Math.abs(points.get(i).x - points.get(half).x) < minHalf)
                middlePoints.add(points.get(i));
        }

        double minMiddleDistance = Double.MAX_VALUE;
        if (middlePoints.size() > 1 ) {
            middlePoints.sort(yCompare);
            for (int i = 0; i < middlePoints.size(); i++) {
                for (int j = i+1; j < middlePoints.size() && j < i+8; j++) {
                    if (middlePoints.get(j).y-middlePoints.get(i).y>minHalf) break;
                    double distance = distance(middlePoints.get(i), middlePoints.get(j));
                    if (distance < minMiddleDistance) {
                        minMiddleDistance = distance;
                    }
                }
            }
        }

        if (minDistance > minMiddleDistance)  minDistance = minMiddleDistance;
        return minDistance;
    }

    private DistanceResult divideDistance(int start, int end) {
        double minDistance = Double.MAX_VALUE;
        List<Pair<Point,Point>> result = new ArrayList<>();
        if (end - start == 0) {
            return new DistanceResult(result, minDistance);
        }
        if (end - start == 1) {
            result.add(new Pair<>(points.get(start),points.get(end)));
            return new DistanceResult(result, distance(points.get(start),points.get(end)));
        }
        //划分左右
        int half = (end+start)>>1;
        DistanceResult resultLeft = divideDistance(start, half);
        DistanceResult resultRight = divideDistance(half, end);

        //划分中间分区
        double minHalf = Math.min(resultLeft.getMinDistance(), resultRight.getMinDistance());
        List<Point> middlePoints = new ArrayList<>();
        for (int i = start; i < end; i++) {
            if (Math.abs(points.get(i).x - points.get(half).x) < minHalf)
                middlePoints.add(points.get(i));
        }

        //计算中间分区的距离最小值
        double minMiddleDistance = Double.MAX_VALUE;
        List<Pair<Point,Point>> middleResult = new ArrayList<>();
        if (middlePoints.size() > 1 ) {
            middlePoints.sort(yCompare);
            for (int i = 0; i < middlePoints.size(); i++) {
                for (int j = i+1; j < middlePoints.size() && j < i+8; j++) {
                    if (middlePoints.get(j).y-middlePoints.get(i).y>minHalf) break;
                    double distance = distance(middlePoints.get(i), middlePoints.get(j));
                    if (distance < minMiddleDistance) {
                        middleResult.clear();
                        middleResult.add(new Pair<>(middlePoints.get(i), middlePoints.get(j)));
                        minMiddleDistance = distance;
                    } else if (distance == minMiddleDistance) {
                        middleResult.add(new Pair<>(middlePoints.get(i), middlePoints.get(j)));
                    }
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
        return Math.sqrt(Math.pow((a.x-b.x),2) + Math.pow((a.y-b.y),2));
    }

}

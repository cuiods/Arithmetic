package com.cuiods.arithmetic.points.model;

import com.cuiods.arithmetic.points.arithmetic.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.math.BigInteger;
import java.util.List;

public class ReportModel {

    private PointDistance pointDistance = new PointDistance();

    private Fibonacci fibonacci = new Fibonacci();

    public ReportResult doReport(int maxNum, int step, boolean exp, boolean method1, boolean method2) {
        ObservableList<ReportObj> reportObjs = FXCollections.observableArrayList();
        ObservableList<XYChart.Series<String,Number>> series = FXCollections.observableArrayList();
        XYChart.Series<String,Number> series1 = new XYChart.Series<>();
        series1.setName("O(n^2)算法");
        XYChart.Series<String,Number> series2 = new XYChart.Series<>();
        series2.setName("O(nlgn)算法");
        ObservableList<XYChart.Series<String,Number>> seriesBack = FXCollections.observableArrayList();
        XYChart.Series<String,Number> seriesBack1 = new XYChart.Series<>();
        seriesBack1.setName("O(n^2)算法");
        XYChart.Series<String,Number> seriesBack2 = new XYChart.Series<>();
        seriesBack2.setName("O(nlgn)算法");
        series.add(series1);
        series.add(series2);
        seriesBack.add(seriesBack1);
        seriesBack.add(seriesBack2);
        for (int i = step; i <= maxNum; ) {
            List<Point> points = RandomUtil.generateRandomPoints(i, Integer.MAX_VALUE, Integer.MAX_VALUE);
            pointDistance.setPoints(points);
            long time1 = System.nanoTime();
            if (method1)
                pointDistance.minDistancePointQuick(DistanceMethod.ENUM);
            long time2 = System.nanoTime();
            if (method2)
                pointDistance.minDistancePointQuick(DistanceMethod.DIVIDE);
            long time3 = System.nanoTime();
            reportObjs.add(new ReportObj(i, (time2-time1)/1000000.0+"ms", (time3-time2)/1000000.0+"ms"));
            series1.getData().add(new XYChart.Data<>(i+"",(time2-time1)/1000000.0));
            series2.getData().add(new XYChart.Data<>(i+"",(time3-time2)/1000000.0));
            seriesBack1.getData().add(new XYChart.Data<>(i+"",(time2-time1)/1000000.0));
            seriesBack2.getData().add(new XYChart.Data<>(i+"",(time3-time2)/1000000.0));
            if (exp) i*=step;
            else i+=step;
        }
        return new ReportResult(reportObjs, series, seriesBack);
    }

    public FibonacciResult doFibonacciTest(int maxNum, int step, boolean method1, boolean method2, boolean method3, boolean method4) {
        ObservableList<FibonacciObj> reportObjs = FXCollections.observableArrayList();
        ObservableList<XYChart.Series<String,Number>> series = FXCollections.observableArrayList();
        XYChart.Series<String,Number> series1 = new XYChart.Series<>();
        series1.setName("递归方法");
        XYChart.Series<String,Number> series2 = new XYChart.Series<>();
        series2.setName("线性加法");
        XYChart.Series<String,Number> series3 = new XYChart.Series<>();
        series3.setName("矩阵乘法");
        XYChart.Series<String,Number> series4 = new XYChart.Series<>();
        series4.setName("公式法");
        ObservableList<XYChart.Series<String,Number>> seriesBack = FXCollections.observableArrayList();
        XYChart.Series<String,Number> seriesBack1 = new XYChart.Series<>();
        seriesBack1.setName("递归方法");
        XYChart.Series<String,Number> seriesBack2 = new XYChart.Series<>();
        seriesBack2.setName("线性加法");
        XYChart.Series<String,Number> seriesBack3 = new XYChart.Series<>();
        seriesBack3.setName("矩阵乘法");
        XYChart.Series<String,Number> seriesBack4 = new XYChart.Series<>();
        seriesBack4.setName("公式法");
        series.add(series1);
        series.add(series2);
        series.add(series3);
        series.add(series4);
        seriesBack.add(seriesBack1);
        seriesBack.add(seriesBack2);
        seriesBack.add(seriesBack3);
        seriesBack.add(seriesBack4);
        for (int i = step; i <= maxNum; i+=step) {
            BigInteger trueResult = BigInteger.ZERO;
            double floatResult = 0;
            long time1 = System.nanoTime();
            if (method1)
                trueResult = fibonacci.recursiveFibonacci(i);
            long time2 = System.nanoTime();
            if (method2)
                trueResult = fibonacci.bottomUpFibonacci(i);
            long time3 = System.nanoTime();
            if (method3)
                trueResult = fibonacci.matrixFibonacci(i);
            long time4 = System.nanoTime();
            if (method4)
                floatResult = fibonacci.formulaFibonacci(i);
            long time5 = System.nanoTime();
            reportObjs.add(new FibonacciObj(i+"", (time2-time1)/1000000.0+"ms", (time3-time2)/1000000.0+"ms",
                    (time4-time3)/1000000.0+"ms",(time5-time4)/1000000.0+"ms",trueResult.toString(),floatResult+""));
            series1.getData().add(new XYChart.Data<>(i+"",(time2-time1)/1000000.0));
            series2.getData().add(new XYChart.Data<>(i+"",(time3-time2)/1000000.0));
            series3.getData().add(new XYChart.Data<>(i+"",(time4-time3)/1000000.0));
            series4.getData().add(new XYChart.Data<>(i+"",(time5-time4)/1000000.0));
            seriesBack1.getData().add(new XYChart.Data<>(i+"",(time2-time1)/1000000.0));
            seriesBack2.getData().add(new XYChart.Data<>(i+"",(time3-time2)/1000000.0));
            seriesBack3.getData().add(new XYChart.Data<>(i+"",(time4-time3)/1000000.0));
            seriesBack4.getData().add(new XYChart.Data<>(i+"",(time5-time4)/1000000.0));
        }
        return new FibonacciResult(reportObjs, series, seriesBack);
    }

}

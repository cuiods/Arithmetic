package com.cuiods.arithmetic.points.model;

import com.cuiods.arithmetic.points.arithmetic.DistanceMethod;
import com.cuiods.arithmetic.points.arithmetic.DistanceResult;
import com.cuiods.arithmetic.points.arithmetic.Point;
import com.cuiods.arithmetic.points.arithmetic.PointDistance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.List;

public class ReportModel {

    private PointDistance pointDistance = new PointDistance();

    public ReportResult doReport(int maxNum, int step) {
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
        for (int i = maxNum; i > 0; i-=step) {
            List<Point> points = RandomUtil.generateRandomPoints(step, Integer.MAX_VALUE, Integer.MAX_VALUE);
            pointDistance.setPoints(points);
            long time1 = System.nanoTime();
            pointDistance.minDistancePoint(DistanceMethod.ENUM);
            long time2 = System.nanoTime();
            pointDistance.minDistancePoint(DistanceMethod.DIVIDE);
            long time3 = System.nanoTime();
            reportObjs.add(new ReportObj(i, (time2-time1)/1000.0+"ms", (time3-time2)/1000.0+"ms"));
            series1.getData().add(new XYChart.Data<>(i+"",(time2-time1)/1000.0));
            series2.getData().add(new XYChart.Data<>(i+"",(time3-time2)/1000.0));
            seriesBack1.getData().add(new XYChart.Data<>(i+"",(time2-time1)/1000.0));
            seriesBack2.getData().add(new XYChart.Data<>(i+"",(time3-time2)/1000.0));
        }
        return new ReportResult(reportObjs, series, seriesBack);
    }

}

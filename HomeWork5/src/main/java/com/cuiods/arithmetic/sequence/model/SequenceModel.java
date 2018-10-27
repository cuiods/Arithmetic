package com.cuiods.arithmetic.sequence.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.Arrays;

public class SequenceModel {

    private IncreasingSequence sequence = new IncreasingSequence();

    public IncreasingResult getIncreasingResult(String input, ComputeMethod method) {
        int[] inputNums = null;
        try {
            inputNums = Arrays.stream(input.split("[,，]")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (inputNums==null) return null;
        switch (method) {
            case DP:
                long start = System.nanoTime();
                int[] result = sequence.dpIncreasingSequence(inputNums);
                long end = System.nanoTime();
                return new IncreasingResult((end-start)/1000000.0, result);
            case GREED:
                long start1 = System.nanoTime();
                int[] result1 = sequence.greedyIncreasingSequence(inputNums);
                long end1 = System.nanoTime();
                return new IncreasingResult((end1-start1)/1000000.0, result1);
        }
        return null;
    }

    public ReportResult<ReportObj> startReport(int maxNum, int stepNum) {
        ObservableList<ReportObj> reportObjs = FXCollections.observableArrayList();
        ObservableList<XYChart.Series<String,Number>> seriesLine = FXCollections.observableArrayList();
        XYChart.Series<String,Number> seriesLine1 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesLine2 = new XYChart.Series<>();
        seriesLine1.setName("动态规划");
        seriesLine2.setName("贪心算法");
        seriesLine.add(seriesLine1);
        seriesLine.add(seriesLine2);
        for (int i = stepNum; i <= maxNum; i+=stepNum) {
            int[] inputs = RandomUtil.generateNumbers(i);
            long time1 = System.nanoTime();
            sequence.dpIncreasingSequence(inputs);
            long time2 = System.nanoTime();
            sequence.greedyIncreasingSequence(inputs);
            long time3 = System.nanoTime();
            double dpTime = (time2-time1)/1000000.0;
            double greedTime = (time3-time2)/1000000.0;
            reportObjs.add(new ReportObj(i+"", dpTime+"ms", greedTime+"ms"));
            seriesLine1.getData().add(new XYChart.Data<>(i+"",dpTime));
            seriesLine2.getData().add(new XYChart.Data<>(i+"", greedTime));
        }
        return new ReportResult<>(reportObjs, seriesLine);
    }
}

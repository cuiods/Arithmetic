package com.cuiods.arithmetic.sequence.model;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class ReportResult<T> {

    private ObservableList<T> reportObjs;

    private ObservableList<XYChart.Series<String,Number>> lineSeries;

    public ReportResult(ObservableList<T> reportObjs, ObservableList<XYChart.Series<String, Number>> lineSeries) {
        this.reportObjs = reportObjs;
        this.lineSeries = lineSeries;
    }

    public ObservableList<T> getReportObjs() {
        return reportObjs;
    }

    public void setReportObjs(ObservableList<T> reportObjs) {
        this.reportObjs = reportObjs;
    }

    public ObservableList<XYChart.Series<String, Number>> getLineSeries() {
        return lineSeries;
    }

    public void setLineSeries(ObservableList<XYChart.Series<String, Number>> lineSeries) {
        this.lineSeries = lineSeries;
    }
}

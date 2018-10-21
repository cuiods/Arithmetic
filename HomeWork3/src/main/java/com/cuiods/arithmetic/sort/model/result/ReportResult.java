package com.cuiods.arithmetic.sort.model.result;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class ReportResult<T> {

    private ObservableList<T> reportObjs;

    private ObservableList<XYChart.Series<String,Number>> lineSeries;

    private ObservableList<XYChart.Series<String,Number>> barSeries;

    public ReportResult(ObservableList<T> reportObjs, ObservableList<XYChart.Series<String, Number>> lineSeries,
                        ObservableList<XYChart.Series<String, Number>> barSeries) {
        this.reportObjs = reportObjs;
        this.lineSeries = lineSeries;
        this.barSeries = barSeries;
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

    public ObservableList<XYChart.Series<String, Number>> getBarSeries() {
        return barSeries;
    }

    public void setBarSeries(ObservableList<XYChart.Series<String, Number>> barSeries) {
        this.barSeries = barSeries;
    }
}

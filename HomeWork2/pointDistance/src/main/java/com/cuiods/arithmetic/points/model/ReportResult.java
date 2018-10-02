package com.cuiods.arithmetic.points.model;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class ReportResult {

    private ObservableList<ReportObj> reportObjs;

    private ObservableList<XYChart.Series<String,Number>> series;

    private ObservableList<XYChart.Series<String,Number>> seriesBack;

    public ReportResult(ObservableList<ReportObj> reportObjs, ObservableList<XYChart.Series<String, Number>> series,
                        ObservableList<XYChart.Series<String, Number>> seriesBack) {
        this.reportObjs = reportObjs;
        this.series = series;
        this.seriesBack = seriesBack;
    }

    public ObservableList<ReportObj> getReportObjs() {
        return reportObjs;
    }

    public void setReportObjs(ObservableList<ReportObj> reportObjs) {
        this.reportObjs = reportObjs;
    }

    public ObservableList<XYChart.Series<String, Number>> getSeries() {
        return series;
    }

    public void setSeries(ObservableList<XYChart.Series<String, Number>> series) {
        this.series = series;
    }

    public ObservableList<XYChart.Series<String, Number>> getSeriesBack() {
        return seriesBack;
    }

    public void setSeriesBack(ObservableList<XYChart.Series<String, Number>> seriesBack) {
        this.seriesBack = seriesBack;
    }
}

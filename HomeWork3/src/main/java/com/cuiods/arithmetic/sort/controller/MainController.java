package com.cuiods.arithmetic.sort.controller;

import com.cuiods.arithmetic.sort.model.TestExecutor;
import com.cuiods.arithmetic.sort.model.result.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private TestExecutor executor = new TestExecutor();

    @FXML private TableView<QuickObj> quickTable;
    @FXML private TableView<MergeObj> mergeTable;
    @FXML private TableView<RadixObj> radixTable;
    @FXML private TableView<SortObj> sortTable;

    @FXML private TableColumn<QuickObj, String> quickCol1;
    @FXML private TableColumn<QuickObj, String> quickCol2;
    @FXML private TableColumn<QuickObj, String> quickCol3;
    @FXML private TableColumn<QuickObj, String> quickCol4;
    @FXML private TableColumn<MergeObj, String> mergeCol1;
    @FXML private TableColumn<MergeObj, String> mergeCol2;
    @FXML private TableColumn<MergeObj, String> mergeCol3;
    @FXML private TableColumn<MergeObj, String> mergeCol4;
    @FXML private TableColumn<RadixObj, String> radixCol1;
    @FXML private TableColumn<RadixObj, String> radixCol2;
    @FXML private TableColumn<SortObj, String> sortCol1;
    @FXML private TableColumn<SortObj, String> sortCol2;
    @FXML private TableColumn<SortObj, String> sortCol3;
    @FXML private TableColumn<SortObj, String> sortCol4;
    @FXML private TableColumn<SortObj, String> sortCol5;
    @FXML private TableColumn<SortObj, String> sortCol6;

    @FXML private LineChart<String, Number> quickLineChart;
    @FXML private BarChart<String, Number> quickBarChart;
    @FXML private LineChart<String, Number> mergeLineChart;
    @FXML private BarChart<String, Number> mergeBarChart;
    @FXML private LineChart<String, Number> radixLineChart;
    @FXML private BarChart<String, Number> radixBarChart;
    @FXML private LineChart<String, Number> sortLineChart;
    @FXML private BarChart<String, Number> sortBarChart;

    @FXML private TextField quickSortNum;
    @FXML private TextField quickMaxNum;
    @FXML private TextField quickGroupNum;
    @FXML private CheckBox quickInsert;
    @FXML private CheckBox quickShell;
    @FXML private CheckBox quickMerge;

    @FXML private TextField mergeSortNum;
    @FXML private TextField mergeMaxNum;
    @FXML private TextField mergeGroupNum;
    @FXML private CheckBox mergeInsert;
    @FXML private CheckBox mergeShell;
    @FXML private CheckBox mergeQuick;

    @FXML private TextField radixSortNum;

    @FXML private TextField sortNum;
    @FXML private CheckBox insertSort;
    @FXML private CheckBox shellSort;
    @FXML private CheckBox quickSort;
    @FXML private CheckBox mergeSort;
    @FXML private CheckBox radixSort;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        quickCol1.setCellValueFactory(cellData->cellData.getValue().numProperty());
        quickCol2.setCellValueFactory(cellData->cellData.getValue().insertProperty());
        quickCol3.setCellValueFactory(cellData->cellData.getValue().shellProperty());
        quickCol4.setCellValueFactory(cellData->cellData.getValue().mergeProperty());
        mergeCol1.setCellValueFactory(cellData->cellData.getValue().numProperty());
        mergeCol2.setCellValueFactory(cellData->cellData.getValue().insertProperty());
        mergeCol3.setCellValueFactory(cellData->cellData.getValue().shellProperty());
        mergeCol4.setCellValueFactory(cellData->cellData.getValue().quickProperty());
        radixCol1.setCellValueFactory(cellData->cellData.getValue().numProperty());
        radixCol2.setCellValueFactory(cellData->cellData.getValue().timeProperty());
        sortCol1.setCellValueFactory(cellData->cellData.getValue().numProperty());
        sortCol2.setCellValueFactory(cellData->cellData.getValue().insertProperty());
        sortCol3.setCellValueFactory(cellData->cellData.getValue().shellProperty());
        sortCol4.setCellValueFactory(cellData->cellData.getValue().quickProperty());
        sortCol5.setCellValueFactory(cellData->cellData.getValue().mergeProperty());
        sortCol6.setCellValueFactory(cellData->cellData.getValue().radixProperty());

        quickLineChart.getXAxis().setLabel("测试点数");
        quickLineChart.getYAxis().setLabel("运行时间(ms)");
        quickBarChart.getXAxis().setLabel("测试点数");
        quickBarChart.getYAxis().setLabel("运行时间(ms)");
        mergeLineChart.getXAxis().setLabel("测试点数");
        mergeLineChart.getYAxis().setLabel("运行时间(ms)");
        mergeBarChart.getXAxis().setLabel("测试点数");
        mergeBarChart.getYAxis().setLabel("运行时间(ms)");
        radixLineChart.getXAxis().setLabel("测试点数");
        radixLineChart.getYAxis().setLabel("运行时间(ms)");
        radixBarChart.getXAxis().setLabel("测试点数");
        radixBarChart.getYAxis().setLabel("运行时间(ms)");
        sortLineChart.getXAxis().setLabel("测试点数");
        sortLineChart.getYAxis().setLabel("运行时间(ms)");
        sortBarChart.getXAxis().setLabel("测试点数");
        sortBarChart.getYAxis().setLabel("运行时间(ms)");
    }

    @FXML
    private void startQuickTest() {
        int sortNum = Integer.parseInt(quickSortNum.getText());
        int maxNum = Integer.parseInt(quickMaxNum.getText());
        int groupNum = Integer.parseInt(quickGroupNum.getText());
        ReportResult<QuickObj> reportResult = executor.doQuickReport(sortNum, maxNum, groupNum, quickInsert.isSelected()
                , quickShell.isSelected(), quickMerge.isSelected());
        quickTable.setItems(reportResult.getReportObjs());
        quickLineChart.setData(reportResult.getLineSeries());
        quickBarChart.setData(reportResult.getBarSeries());
    }

    @FXML
    private void startMergeTest() {
        int sortNum = Integer.parseInt(mergeSortNum.getText());
        int maxNum = Integer.parseInt(mergeMaxNum.getText());
        int groupNum = Integer.parseInt(mergeGroupNum.getText());
        ReportResult<MergeObj> reportResult = executor.doMergeReport(sortNum, maxNum, groupNum, mergeInsert.isSelected()
                , mergeShell.isSelected(), mergeQuick.isSelected());
        mergeTable.setItems(reportResult.getReportObjs());
        mergeLineChart.setData(reportResult.getLineSeries());
        mergeBarChart.setData(reportResult.getBarSeries());
    }

    @FXML
    private void startRadixTest() {
        int sortNum = Integer.parseInt(radixSortNum.getText());
        ReportResult<RadixObj> reportResult = executor.doRadixReport(sortNum);
        radixTable.setItems(reportResult.getReportObjs());
        radixLineChart.setData(reportResult.getLineSeries());
        radixBarChart.setData(reportResult.getBarSeries());
    }

    @FXML
    private void startSortTest() {
        int num = Integer.parseInt(sortNum.getText());
        ReportResult<SortObj> reportResult = executor.doSortReport(num, insertSort.isSelected(), shellSort.isSelected(),
                quickSort.isSelected(), mergeSort.isSelected(), radixSort.isSelected());
        sortTable.setItems(reportResult.getReportObjs());
        sortLineChart.setData(reportResult.getLineSeries());
        sortBarChart.setData(reportResult.getBarSeries());
    }

    @FXML
    private void exportQuickToExcel() {
        executor.exportToExcel(quickTable,"quickSortTest");
    }

    @FXML
    private void exportMergeToExcel() {
        executor.exportToExcel(mergeTable,"mergeSortTest");
    }

    @FXML
    private void exportRadixToExcel() {
        executor.exportToExcel(radixTable,"radixSortTest");
    }

    @FXML
    private void exportSortToExcel() {
        executor.exportToExcel(sortTable,"sortTest");
    }
}

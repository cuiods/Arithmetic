package com.cuiods.arithmetic.sort.model;

import com.cuiods.arithmetic.sort.model.result.*;
import com.cuiods.arithmetic.sort.util.RandomUtil;
import com.cuiods.arithmetic.sort.util.SortMethod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;

public class TestExecutor {

    private SortArithmetic sort = new SortArithmetic();

    public ReportResult<QuickObj> doQuickReport(int num, int endNum, int groupNum, boolean method1, boolean method2, boolean method3) {
        ObservableList<QuickObj> quickObjs = FXCollections.observableArrayList();
        ObservableList<XYChart.Series<String,Number>> seriesLine = FXCollections.observableArrayList();
        ObservableList<XYChart.Series<String,Number>> seriesBar = FXCollections.observableArrayList();
        XYChart.Series<String,Number> seriesLine1 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesLine2 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesLine3 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesBar1 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesBar2 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesBar3 = new XYChart.Series<>();
        seriesLine1.setName("插入排序");
        seriesLine2.setName("希尔排序");
        seriesLine3.setName("归并排序");
        seriesBar1.setName("插入排序");
        seriesBar2.setName("希尔排序");
        seriesBar3.setName("归并排序");
        seriesLine.add(seriesLine1);
        seriesLine.add(seriesLine2);
        seriesLine.add(seriesLine3);
        seriesBar.add(seriesBar1);
        seriesBar.add(seriesBar2);
        seriesBar.add(seriesBar3);

        for (int i = groupNum; i <= endNum; i+=groupNum) {
            long[] nums = RandomUtil.generateNumbers(num);
            long time1Start = System.nanoTime();
            if (method1) sort.quickSort(nums, i, SortMethod.INSERT);
            long time1End = System.nanoTime();
            nums = RandomUtil.generateNumbers(num);
            long time2Start = System.nanoTime();
            if (method2) sort.quickSort(nums,i, SortMethod.SHELL);
            long time2End = System.nanoTime();
            nums = RandomUtil.generateNumbers(num);
            long time3Start = System.nanoTime();
            if (method3) sort.quickSort(nums, i, SortMethod.MERGE);
            long time3End = System.nanoTime();
            double time1 = method1?(time1End-time1Start)/1000000.0:0;
            double time2 = method2?(time2End-time2Start)/1000000.0:0;
            double time3 = method3?(time3End-time3Start)/1000000.0:0;
            quickObjs.add(new QuickObj(i+"", time1+"ms", time2+"ms", time3+"ms"));
            seriesLine1.getData().add(new XYChart.Data<>(i+"",time1));
            seriesLine2.getData().add(new XYChart.Data<>(i+"",time2));
            seriesLine3.getData().add(new XYChart.Data<>(i+"",time3));
            seriesBar1.getData().add(new XYChart.Data<>(i+"",time1));
            seriesBar2.getData().add(new XYChart.Data<>(i+"",time2));
            seriesBar3.getData().add(new XYChart.Data<>(i+"",time3));
        }
        return new ReportResult<>(quickObjs, seriesLine, seriesBar);
    }

    public ReportResult<MergeObj> doMergeReport(int num, int endNum, int groupNum, boolean method1, boolean method2, boolean method3) {
        ObservableList<MergeObj> mergeObjs = FXCollections.observableArrayList();
        ObservableList<XYChart.Series<String,Number>> seriesLine = FXCollections.observableArrayList();
        ObservableList<XYChart.Series<String,Number>> seriesBar = FXCollections.observableArrayList();
        XYChart.Series<String,Number> seriesLine1 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesLine2 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesLine3 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesBar1 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesBar2 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesBar3 = new XYChart.Series<>();
        seriesLine1.setName("插入排序");
        seriesLine2.setName("希尔排序");
        seriesLine3.setName("快速排序");
        seriesBar1.setName("插入排序");
        seriesBar2.setName("希尔排序");
        seriesBar3.setName("快速排序");
        seriesLine.add(seriesLine1);
        seriesLine.add(seriesLine2);
        seriesLine.add(seriesLine3);
        seriesBar.add(seriesBar1);
        seriesBar.add(seriesBar2);
        seriesBar.add(seriesBar3);

        for (int i = groupNum; i <= endNum; i+=groupNum) {
            long[] nums = RandomUtil.generateNumbers(num);
            long time1Start = System.nanoTime();
            if (method1) sort.mergeSort(nums, i, SortMethod.INSERT);
            long time1End = System.nanoTime();
            nums = RandomUtil.generateNumbers(num);
            long time2Start = System.nanoTime();
            if (method2) sort.mergeSort(nums,i, SortMethod.SHELL);
            long time2End = System.nanoTime();
            nums = RandomUtil.generateNumbers(num);
            long time3Start = System.nanoTime();
            if (method3) sort.mergeSort(nums, i, SortMethod.QUICK);
            long time3End = System.nanoTime();
            double time1 = method1?(time1End-time1Start)/1000000.0:0;
            double time2 = method2?(time2End-time2Start)/1000000.0:0;
            double time3 = method3?(time3End-time3Start)/1000000.0:0;
            mergeObjs.add(new MergeObj(i+"", time1+"ms", time2+"ms", time3+"ms"));
            seriesLine1.getData().add(new XYChart.Data<>(i+"",time1));
            seriesLine2.getData().add(new XYChart.Data<>(i+"",time2));
            seriesLine3.getData().add(new XYChart.Data<>(i+"",time3));
            seriesBar1.getData().add(new XYChart.Data<>(i+"",time1));
            seriesBar2.getData().add(new XYChart.Data<>(i+"",time2));
            seriesBar3.getData().add(new XYChart.Data<>(i+"",time3));
        }
        return new ReportResult<>(mergeObjs, seriesLine, seriesBar);
    }

    public ReportResult<RadixObj> doRadixReport(int num) {
        ObservableList<RadixObj> radixObjs = FXCollections.observableArrayList();
        ObservableList<XYChart.Series<String,Number>> seriesLine = FXCollections.observableArrayList();
        ObservableList<XYChart.Series<String,Number>> seriesBar = FXCollections.observableArrayList();
        XYChart.Series<String,Number> seriesLine1 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesBar1 = new XYChart.Series<>();
        seriesLine1.setName("基数排序");
        seriesBar1.setName("基数排序");
        seriesLine.add(seriesLine1);
        seriesBar.add(seriesBar1);

        for (int i = 2; i <= 12; i+=2) {
            long[] nums = RandomUtil.generateNumbers(num);
            long time1Start = System.nanoTime();
            sort.radixSort(nums,i);
            long time1End = System.nanoTime();
            double time1 = (time1End-time1Start)/1000000.0;
            radixObjs.add(new RadixObj(i+"", time1+"ms"));
            seriesLine1.getData().add(new XYChart.Data<>(i+"",time1));
            seriesBar1.getData().add(new XYChart.Data<>(i+"",time1));
        }
        return new ReportResult<>(radixObjs, seriesLine, seriesBar);
    }

    public ReportResult<SortObj> doSortReport(int num, boolean method1, boolean method2,
                                              boolean method3, boolean method4, boolean method5) {
        ObservableList<SortObj> sortObjs = FXCollections.observableArrayList();
        ObservableList<XYChart.Series<String,Number>> seriesLine = FXCollections.observableArrayList();
        ObservableList<XYChart.Series<String,Number>> seriesBar = FXCollections.observableArrayList();
        XYChart.Series<String,Number> seriesLine1 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesLine2 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesLine3 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesLine4 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesLine5 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesBar1 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesBar2 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesBar3 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesBar4 = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesBar5 = new XYChart.Series<>();
        seriesLine1.setName("插入排序");
        seriesLine2.setName("希尔排序");
        seriesLine3.setName("快速排序");
        seriesLine4.setName("归并排序");
        seriesLine5.setName("基数排序");
        seriesBar1.setName("插入排序");
        seriesBar2.setName("希尔排序");
        seriesBar3.setName("快速排序");
        seriesBar4.setName("归并排序");
        seriesBar5.setName("基数排序");
        seriesLine.add(seriesLine1);
        seriesLine.add(seriesLine2);
        seriesLine.add(seriesLine3);
        seriesLine.add(seriesLine4);
        seriesLine.add(seriesLine5);
        seriesBar.add(seriesBar1);
        seriesBar.add(seriesBar2);
        seriesBar.add(seriesBar3);
        seriesBar.add(seriesBar4);
        seriesBar.add(seriesBar5);

        for (int i = 10; i <= num; i*=10) {
            long[] nums = RandomUtil.generateNumbers(i);
            long time1Start = System.nanoTime();
            if (method1) sort.insertionSort(nums);
            long time1End = System.nanoTime();
            nums = RandomUtil.generateNumbers(i);
            long time2Start = System.nanoTime();
            if (method2) sort.shellSort(nums);
            long time2End = System.nanoTime();
            nums = RandomUtil.generateNumbers(i);
            long time3Start = System.nanoTime();
            if (method3) sort.quickSort(nums, 2);
            long time3End = System.nanoTime();
            nums = RandomUtil.generateNumbers(i);
            long time4Start = System.nanoTime();
            if (method4) sort.mergeSort(nums, 2);
            long time4End = System.nanoTime();
            nums = RandomUtil.generateNumbers(i);
            long time5Start = System.nanoTime();
            if (method5) sort.radixSort(nums, 8);
            long time5End = System.nanoTime();
            double time1 = method1?(time1End-time1Start)/1000000.0:0;
            double time2 = method2?(time2End-time2Start)/1000000.0:0;
            double time3 = method3?(time3End-time3Start)/1000000.0:0;
            double time4 = method4?(time4End-time4Start)/1000000.0:0;
            double time5 = method5?(time5End-time5Start)/1000000.0:0;
            sortObjs.add(new SortObj(i+"", time1+"ms", time2+"ms", time3+"ms", time4+"ms", time5+"ms"));
            seriesLine1.getData().add(new XYChart.Data<>(i+"",time1));
            seriesLine2.getData().add(new XYChart.Data<>(i+"",time2));
            seriesLine3.getData().add(new XYChart.Data<>(i+"",time3));
            seriesLine4.getData().add(new XYChart.Data<>(i+"",time4));
            seriesLine5.getData().add(new XYChart.Data<>(i+"",time5));
            seriesBar1.getData().add(new XYChart.Data<>(i+"",time1));
            seriesBar2.getData().add(new XYChart.Data<>(i+"",time2));
            seriesBar3.getData().add(new XYChart.Data<>(i+"",time3));
            seriesBar4.getData().add(new XYChart.Data<>(i+"",time4));
            seriesBar5.getData().add(new XYChart.Data<>(i+"",time5));
        }
        return new ReportResult<>(sortObjs, seriesLine, seriesBar);
    }

    public void exportToExcel(TableView<?> table, String name) {
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet(name);

        Row row = spreadsheet.createRow(0);
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumns().get(j).getText());
        }
        for (int i = 0; i < table.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if(table.getColumns().get(j).getCellData(i) != null) {
                    row.createCell(j).setCellValue(table.getColumns().get(j).getCellData(i).toString());
                }
                else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(name+".xls");
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

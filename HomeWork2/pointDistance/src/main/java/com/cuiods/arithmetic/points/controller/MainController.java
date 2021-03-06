package com.cuiods.arithmetic.points.controller;

import com.cuiods.arithmetic.points.arithmetic.DistanceMethod;
import com.cuiods.arithmetic.points.arithmetic.DistanceResult;
import com.cuiods.arithmetic.points.arithmetic.Point;
import com.cuiods.arithmetic.points.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private CanvasModel canvasModel = new CanvasModel();

    private ReportModel reportModel = new ReportModel();

    @FXML private Canvas canvas;

    @FXML private Label canvasPointNum;

    @FXML private Label locationLabel;

    @FXML private Label timeLabel;

    @FXML private TextField pointNumField;

    @FXML private ListView<String> operationList;

    @FXML private ListView<String> resultList;

    @FXML private ChoiceBox<String> complexityChoice;

    @FXML private TextField maxPointNumField;
    @FXML private TextField maxPointNumField1;

    @FXML private TextField groupNumField;
    @FXML private TextField groupNumField1;

    @FXML private RadioButton expChoiceBtn;
    @FXML private CheckBox reportMethod1;
    @FXML private CheckBox reportMethod2;
    @FXML private CheckBox fibonacciMethod1;
    @FXML private CheckBox fibonacciMethod2;
    @FXML private CheckBox fibonacciMethod3;
    @FXML private CheckBox fibonacciMethod4;

    @FXML private LineChart<String, Number> lineChart;
    @FXML private LineChart<String, Number> lineChart1;

    @FXML private BarChart<String, Number> barChart;
    @FXML private BarChart<String, Number> barChart1;

    @FXML private TableView<ReportObj> tableView;
    @FXML private TableView<FibonacciObj> tableView1;
    @FXML private TableColumn<ReportObj, Integer> numColumn;
    @FXML private TableColumn<ReportObj, String> firstColumn;
    @FXML private TableColumn<ReportObj, String> secondColumn;
    @FXML private TableColumn<FibonacciObj, String> numColumn1;
    @FXML private TableColumn<FibonacciObj, String> Column1;
    @FXML private TableColumn<FibonacciObj, String> Column2;
    @FXML private TableColumn<FibonacciObj, String> Column3;
    @FXML private TableColumn<FibonacciObj, String> Column4;
    @FXML private TableColumn<FibonacciObj, String> Column5;
    @FXML private TableColumn<FibonacciObj, String> Column6;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        complexityChoice.setItems(FXCollections.observableArrayList("O(nlgn)","O(n^2)"));
        complexityChoice.setValue("O(nlgn)");
        numColumn.setCellValueFactory(cellData->cellData.getValue().numProperty().asObject());
        firstColumn.setCellValueFactory(cellData->cellData.getValue().firstTimeProperty());
        secondColumn.setCellValueFactory(cellData->cellData.getValue().lastTimeProperty());
        numColumn1.setCellValueFactory(cellData->cellData.getValue().numProperty());
        Column1.setCellValueFactory(cellData->cellData.getValue().time1Property());
        Column2.setCellValueFactory(cellData->cellData.getValue().time2Property());
        Column3.setCellValueFactory(cellData->cellData.getValue().time3Property());
        Column4.setCellValueFactory(cellData->cellData.getValue().time4Property());
        Column5.setCellValueFactory(cellData->cellData.getValue().trueValueProperty());
        Column6.setCellValueFactory(cellData->cellData.getValue().floatValueProperty());
        lineChart.getXAxis().setLabel("测试点数");
        lineChart.getYAxis().setLabel("运行时间(ms)");
        barChart.getXAxis().setLabel("测试点数");
        barChart.getYAxis().setLabel("运行时间(ms)");
        lineChart1.getXAxis().setLabel("测试点数");
        lineChart1.getYAxis().setLabel("运行时间(ms)");
        barChart1.getXAxis().setLabel("测试点数");
        barChart1.getYAxis().setLabel("运行时间(ms)");
    }

    @FXML
    private void onMouseClickCanvas(MouseEvent event) {
        canvasModel.addPoint(new Point((int)event.getX(), (int)event.getY()));
        canvasPointNum.setText(canvasModel.getSize()+"");
        drawPoint(event.getX(), event.getY());
        addOperation("添加点("+event.getX()+","+event.getY()+")");
    }

    @FXML
    private void onMouseMoveCanvas(MouseEvent event) {
        locationLabel.setText("x:"+event.getX()+", y:"+event.getY());
    }

    @FXML
    private void onStartBtnClicked() {
        String methodValue = complexityChoice.getValue();
        DistanceMethod method = DistanceMethod.DIVIDE;
        if (methodValue.equals("O(n^2)"))
            method = DistanceMethod.ENUM;
        DistanceResult result = canvasModel.calculateDistance(method);
        ObservableList<String> observableList = resultList.getItems();
        observableList.clear();
        observableList.add("最小距离："+result.getMinDistance());
        for (Pair<Point,Point> pointPair: result.getPoints()) {
            observableList.add("("+pointPair.getKey().x+","+pointPair.getKey().y+")->("+pointPair.getValue().x
                    +","+pointPair.getValue().y+")");
        }
        timeLabel.setText(result.getTime()/1000000.0+"ms");
        drawResult(result.getPoints());
        addOperation("计算最小距离，用时"+result.getTime()/1000000.0+"ms");
    }

    @FXML
    private void onResetBtnClicked() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        canvasPointNum.setText("0");
        timeLabel.setText("0");
        canvasModel.reset();
        resultList.getItems().clear();
        addOperation("重置画布");
    }

    @FXML
    private void generatePoints() {
        int num = Integer.parseInt(pointNumField.getText());
        List<Point> points = RandomUtil.generateRandomPoints(num, (int)canvas.getWidth(), (int)canvas.getHeight());
        canvasModel.addPoints(points);
        canvasPointNum.setText(canvasModel.getSize()+"");
        drawPoints(points);
        addOperation("添加"+num+"个随机点");
    }

    @FXML
    private void startReportTest() {
        int maxNum = Integer.parseInt(maxPointNumField.getText());
        int step = Integer.parseInt(groupNumField.getText());
        boolean exp = expChoiceBtn.isSelected();
        ReportResult result = reportModel.doReport(maxNum, step, exp, reportMethod1.isSelected(), reportMethod2.isSelected());
        tableView.setItems(result.getReportObjs());
        barChart.setData(result.getSeries());
        lineChart.setData(result.getSeriesBack());
    }

    @FXML
    private void startFibonacciTest() {
        int maxNum = Integer.parseInt(maxPointNumField1.getText());
        int step = Integer.parseInt(groupNumField1.getText());
        FibonacciResult result = reportModel.doFibonacciTest(maxNum, step, fibonacciMethod1.isSelected(),
                fibonacciMethod2.isSelected(), fibonacciMethod3.isSelected(), fibonacciMethod4.isSelected());
        tableView1.setItems(result.getReportObjs());
        barChart1.setData(result.getSeries());
        lineChart1.setData(result.getSeriesBack());
    }

    @FXML
    private void exportPointToExcel() {
        exportToExcel(tableView,"PointDistance");
    }

    @FXML
    private void exportFibonacciToExcel() {
        exportToExcel(tableView1,"Fibonacci");
    }

    private void exportToExcel(TableView<?> table, String name) {
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

    private void drawPoints(List<Point> points) {
        for (Point point: points)
            drawPoint(point.x, point.y);
    }

    private void drawPoint(double x, double y) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.save();
        gc.setFill(Color.BLACK);
        gc.fillOval(x,y,4,4);
        gc.strokeOval(x,y,4,4);
        gc.restore();
    }

    private void drawResult(List<Pair<Point, Point>> points) {
        for (Pair<Point,Point> pointPair: points) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.save();
            gc.setStroke(Color.RED);
            gc.setLineWidth(4);
            gc.strokeLine(pointPair.getKey().x,pointPair.getKey().y,
                    pointPair.getValue().x,pointPair.getValue().y);
            gc.restore();
        }
    }

    private void addOperation(String msg) {
        SimpleDateFormat time=new SimpleDateFormat("HH:mm:ss");
        operationList.getItems().add(0,time.format(new Date())+"  "+msg);
        if (operationList.getItems().size()>100) {
            operationList.getItems().remove(50,operationList.getItems().size());
        }
    }
}

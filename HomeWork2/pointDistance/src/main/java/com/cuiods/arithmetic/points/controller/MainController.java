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
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Pair;

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

    @FXML private TextField groupNumField;

    @FXML private RadioButton expChoiceBtn;

    @FXML private LineChart<String, Number> lineChart;

    @FXML private BarChart<String, Number> barChart;

    @FXML private TableView<ReportObj> tableView;
    @FXML private TableColumn<ReportObj, Integer> numColumn;
    @FXML private TableColumn<ReportObj, String> firstColumn;
    @FXML private TableColumn<ReportObj, String> secondColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        complexityChoice.setItems(FXCollections.observableArrayList("O(nlgn)","O(n^2)"));
        complexityChoice.setValue("O(nlgn)");
        numColumn.setCellValueFactory(cellData->cellData.getValue().numProperty().asObject());
        firstColumn.setCellValueFactory(cellData->cellData.getValue().firstTimeProperty());
        secondColumn.setCellValueFactory(cellData->cellData.getValue().lastTimeProperty());
        lineChart.getXAxis().setLabel("测试点数");
        lineChart.getYAxis().setLabel("运行时间(ms)");
        barChart.getXAxis().setLabel("测试点数");
        barChart.getYAxis().setLabel("运行时间(ms)");
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
        ReportResult result = reportModel.doReport(maxNum, step, exp);
        tableView.setItems(result.getReportObjs());
        barChart.setData(result.getSeries());
        lineChart.setData(result.getSeriesBack());
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

package com.cuiods.arithmetic.sequence.controller;

import com.cuiods.arithmetic.sequence.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private SequenceModel model = new SequenceModel();

    @FXML private TextArea inputArea;
    @FXML private TextArea resultArea;
    @FXML private TextField randomNum;
    @FXML private TextField useTime;
    @FXML private TextField maxNum;
    @FXML private TextField stepNum;
    @FXML private ChoiceBox<String> method;

    @FXML private LineChart<String, Number> lineChart;

    @FXML private TableView<ReportObj> tableView;
    @FXML private TableColumn<ReportObj, String> numCol;
    @FXML private TableColumn<ReportObj, String> dpCol;
    @FXML private TableColumn<ReportObj, String> greedyCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputArea.setWrapText(true);
        resultArea.setWrapText(true);
        method.setItems(FXCollections.observableArrayList("DP","GREED"));
        method.setValue("GREED");
        numCol.setCellValueFactory(cellData->cellData.getValue().numProperty());
        dpCol.setCellValueFactory(cellData->cellData.getValue().dpTimeProperty());
        greedyCol.setCellValueFactory(cellData->cellData.getValue().greedTimeProperty());
        lineChart.getXAxis().setLabel("测试N值");
        lineChart.getYAxis().setLabel("运行时间(ms)");
    }

    @FXML
    protected void generateNum() {
        int num = 0;
        try {
            num = Integer.parseInt(randomNum.getText());
        } catch (Exception e) {
            randomNum.clear();
        }
        if (num == 0) return;
        int[] nums = RandomUtil.generateNumbers(num);
        String full = Arrays.toString(nums);
        inputArea.setText(full.substring(1, full.length()-1));
    }

    @FXML
    protected void reset() {
        inputArea.clear();
        resultArea.clear();
        useTime.clear();
        randomNum.clear();
        method.setValue("GREED");
    }

    @FXML
    protected void computeSequence() {
        IncreasingResult result = model.getIncreasingResult(inputArea.getText(), ComputeMethod.valueOf(method.getValue()));
        if (result != null) {
            useTime.setText(result.getTime()+"ms");
            String resultStr = Arrays.toString(result.getResult());
            resultArea.setText(resultStr.substring(1, resultStr.length()-1));
        }
    }

    @FXML
    private void startReport() {
        int max = Integer.parseInt(maxNum.getText());
        int step = Integer.parseInt(stepNum.getText());
        ReportResult<ReportObj> reportResult = model.startReport(max, step);
        tableView.setItems(reportResult.getReportObjs());
        lineChart.setData(reportResult.getLineSeries());
    }
}

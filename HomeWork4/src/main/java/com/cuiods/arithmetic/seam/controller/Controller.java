package com.cuiods.arithmetic.seam.controller;

import com.cuiods.arithmetic.seam.model.ImageModel;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private FileChooser chooser = new FileChooser();
    private File inputFile = null;
    private File outPutFile = null;

    private ImageModel model = new ImageModel();

    @FXML private ImageView originImage;
    @FXML private ImageView seamImage;
    @FXML private Label showSelectPath;
    @FXML private Label showOutputPath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    protected void selectPath() {
        Stage stage = (Stage) originImage.getScene().getWindow();
        chooser.setTitle("导入图片");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        inputFile = chooser.showOpenDialog(stage);
        showSelectPath.setText(inputFile.getPath());
        try {
            originImage.setImage(new Image(new FileInputStream(inputFile.getPath())));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void selectOutputPath() {
        Stage stage = (Stage) seamImage.getScene().getWindow();
        chooser.setTitle("导出图片");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.setInitialFileName("saveImage");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        outPutFile = chooser.showSaveDialog(stage);
        showOutputPath.setText(outPutFile.getPath());
    }

    @FXML
    protected void startSeamCarving() {
        if (inputFile == null) {
            showAlert("运行条件不完整","请选择正确的输入图片路径", Alert.AlertType.WARNING);
            return;
        }
        if (outPutFile == null) {
            showAlert("运行条件不完整","请选择正确的导出图片路径", Alert.AlertType.WARNING);
            return;
        }
        try {
            BufferedImage image = ImageIO.read(inputFile);
            BufferedImage result = model.seamImage(image);
            seamImage.setImage(SwingFXUtils.toFXImage(result,null));
            ImageIO.write(result,"jpg",outPutFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String header, String msg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("提示");
        alert.setHeaderText(header);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}

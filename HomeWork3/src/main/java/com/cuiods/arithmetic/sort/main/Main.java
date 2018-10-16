package com.cuiods.arithmetic.sort.main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            URL mainFxml = Main.class.getClassLoader().getResource("main.fxml");
            if (mainFxml == null) return;
            Parent root = FXMLLoader.load(mainFxml);
            primaryStage.setTitle("Sort Method Compare");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

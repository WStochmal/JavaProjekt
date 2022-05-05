package com.example.bank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Object root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene((Parent) root, 600, 400);
        stage.setTitle("JS&WS");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
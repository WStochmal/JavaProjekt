package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDesktopPanelController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void AddNewUser(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminAddNewUser.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(350);
        stage.setY(50);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exit(ActionEvent event) throws IOException {
        Data.CardList.clear();
        Data.AccountList.clear();
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ViewLoan(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminLoanApplication.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(350);
        stage.setY(50);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ViewTransactions(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminViewTransactions.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(350);
        stage.setY(50);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

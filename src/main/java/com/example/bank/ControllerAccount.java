package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAccount implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Button Welcome;
    @FXML
    Label Name;
    @FXML
    Label Surname;
    @FXML
    Label ID;
    @FXML
    Label Age;
    @FXML
    Label Sex;
    @FXML
    Label Email;
    @FXML
    Label Tel;


    public void openDesktopPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Desktop.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openTransactionPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Transaction.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openAccountPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Account.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logOut(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(600);
        stage.setY(260);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Siema Zdzichu");
        Welcome.setText(Data.Name + " " + Data.Surname);
        Name.setText("Imie: " + Data.Name);
        Surname.setText("Nazwisko: " + Data.Surname);
        ID.setText("Pesel: " + Data.ID);
        Sex.setText("Pleć: " + Data.Sex);
        Age.setText("Wiek: " + Data.Age);
        Email.setText("Email: " + Data.Email);
        Tel.setText("Telefon: " + Data.Tel);
    }
}

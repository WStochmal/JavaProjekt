package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTransfer implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Button AccountButton;

    @FXML
    TextField ReceiverAccount;

    @FXML
    ChoiceBox SenderAccount;

    @FXML
    TextField Title;


    public void openDesktopPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Desktop.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openTransactionPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Transfer.fxml"));
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

    public void openCardsPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Cards.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openLoanPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Cards.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void send(){
        String SenderAccountNumber = (String) SenderAccount.getValue();
        String ReceiverAccountNumber = ReceiverAccount.getText();
        String TransactionTitle = Title.getText();
        if(ReceiverAccountNumber.length()!=16){
            System.out.println("hauhau");
        } else if (SenderAccountNumber.charAt(0) != ReceiverAccountNumber.charAt(0)) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AccountButton.setText(Data.Name + " " + Data.Surname);

        String AccountNumber = null;
        for (int i = 0; i < Data.AccountList.size();i++){
            AccountNumber = Data.AccountList.get(i).getAccountNumber();
            System.out.println(Data.AccountList.get(i).getAccountNumber());
            SenderAccount.getItems().add(AccountNumber);
        }

    }

    public void openTransactionHistoryPage(ActionEvent event) {
    }
}

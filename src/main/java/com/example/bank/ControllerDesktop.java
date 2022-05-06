package com.example.bank;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
//import java.time.Duration;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerDesktop implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button AccountButton;
    @FXML
    private Label time;
    @FXML
    Label welcomeUser;
    @FXML
    ChoiceBox Saldo;
    @FXML
    Label Money;


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
    public void openCardsPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Cards.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openLoanPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Loan.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openTransactionHistoryPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("TransferHistory.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logOut(ActionEvent event) throws IOException {
        Data.CardList.clear();
        Data.AccountList.clear();
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(600);
        stage.setY(260);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


   @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        AccountButton.setText(Data.Name + " " + Data.Surname);
        welcomeUser.setText("Witaj, "+Data.Name);

       initClock();

       String AccountNumber = null;
       for (int i = 0; i < Data.AccountList.size();i++){
           AccountNumber = Data.AccountList.get(i).getAccountNumber();
           System.out.println(Data.AccountList.get(i).getAccountNumber());
           Saldo.getItems().add(AccountNumber);
       }


    }

    public void wyswietl() throws SQLException {

        String Account = (String) Saldo.getValue();

        String Query = "SELECT * FROM Rachunek WHERE Nr_rachunku = "+"'"+ Account +"'";
        String URL = "jdbc:mysql://h25.seohost.pl:3306/srv42082_java_2";
        String Login = "srv42082_java_2";
        String Password = "qwerty123$";

        Connection connection = DriverManager.getConnection(URL,Login,Password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(Query);

        resultSet.next();
        String cash = resultSet.getString("Dostepne_srodki");

        String Waluta ="";

        if(Account.charAt(0) == '1'){
            Waluta = "PLN";
        } else if (Account.charAt(0) == '2') {
            Waluta = "USD";
        } else if (Account.charAt(0) == '3') {
            Waluta = "EUR";
        }


        Money.setText(cash + " " + Waluta);
    }

    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            time.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }



}

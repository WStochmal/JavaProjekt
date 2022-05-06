package com.example.bank;

import javafx.animation.Animation;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerLoan implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label time;

    @FXML
    Button AccountButton;

    @FXML
    ChoiceBox SenderAccount;

    @FXML
    TextField LoanValue;

    @FXML
    TextField LoanLength;


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

    public void openTransactionHistoryPage(ActionEvent actionEvent) {
    }

    public void applicate(ActionEvent actionEvent) throws SQLException {
        String SenderAccountNumber = (String) SenderAccount.getValue();
        String Value = LoanValue.getText();
        String Length = LoanLength.getText();

        if(Value.charAt(0) == '0'|| Value.charAt(0) == '-'){
            //warning.setText("Kwota nie musi być większa od 0");
            DialogUtilities.dialogFailure("Kwota nie może być 0 lub mniejsza");
        }else if(SenderAccountNumber.length()!=9){
            DialogUtilities.dialogFailure("Nie zgadza się rachunek");
        } else if (Length.charAt(0) == '0'|| Length.charAt(0) == '-') {
            DialogUtilities.dialogFailure("Dlugość kredytu nie może być mniejsza niż 1");
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String Date = dtf.format(now);

        String URL = "jdbc:mysql://h25.seohost.pl:3306/srv42082_java_2";
        String LoginDB = "srv42082_java_2";
        String PasswordDB = "qwerty123$";
        String Query = "INSERT INTO `Kredyt`(`rachunek`, `kwota`, `dlugosc`, `dataZlozenia`) VALUES ("+"'"+ SenderAccountNumber +"'"+","+"'"+Value+"'"+","+"'"+Length +"'"+","+"'"+Date +"'"+")";

        Connection connection = DriverManager.getConnection(URL,LoginDB,PasswordDB);
        PreparedStatement statement = connection.prepareStatement(Query);
        statement.execute();

        DialogUtilities.dialogSuccess("Poprawnie zlożonow wniosek o kredyt");

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AccountButton.setText(Data.Name + " " + Data.Surname);

        String AccountNumber = null;
        for (int i = 0; i < Data.AccountList.size();i++){
            AccountNumber = Data.AccountList.get(i).getAccountNumber();
            System.out.println(Data.AccountList.get(i).getAccountNumber());
            SenderAccount.getItems().add(AccountNumber);
            initClock();
        }

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

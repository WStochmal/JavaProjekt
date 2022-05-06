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

public class ControllerTransfer implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label time;

    @FXML
    Button AccountButton;

    @FXML
    TextField ReceiverAccount;

    @FXML
    ChoiceBox SenderAccount;

    @FXML
    TextField Title;

    @FXML
    TextField Amount;

    @FXML
    Label warning;


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

    public void send() throws SQLException {
        String SenderAccountNumber = (String) SenderAccount.getValue();
        String ReceiverAccountNumber = ReceiverAccount.getText();
        String TransactionTitle = Title.getText();
        String Money = Amount.getText();
        float AmountMoney = Float.parseFloat(Money);
        float SenderMoney = AmountMoney;
        if(Money.charAt(0) == '0'|| Money.charAt(0) == '-'){
            warning.setText("Kwota nie musi być większa od 0");
        }else if(ReceiverAccountNumber.length()!=9){
            warning.setText("Rachunek musi mieć 9 cyfr");
        } else if (SenderAccountNumber.charAt(0) != ReceiverAccountNumber.charAt(0)) {

            if(SenderAccountNumber.charAt(0) == '1' && ReceiverAccountNumber.charAt(0) == '2'){
                AmountMoney = AmountMoney * CurrencyRates.PLNUSD;
            } else if (SenderAccountNumber.charAt(0) == '1' && ReceiverAccountNumber.charAt(0) == '3') {
                AmountMoney = AmountMoney * CurrencyRates.PLNEUR;
            } else if (SenderAccountNumber.charAt(0) == '2' && ReceiverAccountNumber.charAt(0) == '1') {
                AmountMoney = AmountMoney * CurrencyRates.USDPLN;
            } else if (SenderAccountNumber.charAt(0) == '2' && ReceiverAccountNumber.charAt(0) == '3') {
                AmountMoney = AmountMoney * CurrencyRates.USDEUR;
            } else if (SenderAccountNumber.charAt(0) == '3' && ReceiverAccountNumber.charAt(0) == '1') {
                AmountMoney = AmountMoney * CurrencyRates.EURPLN;
            } else if (SenderAccountNumber.charAt(0) == '3' && ReceiverAccountNumber.charAt(0) == '2') {
                AmountMoney = AmountMoney * CurrencyRates.EURUSD;
            }
        }
        String URL = "jdbc:mysql://h25.seohost.pl:3306/srv42082_java_2";
        String Login = "srv42082_java_2";
        String Password = "qwerty123$";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String Date = dtf.format(now);

        String Query = "Select `Dostepne_srodki` from Rachunek where Nr_rachunku = "+ SenderAccountNumber;

        Connection connection = DriverManager.getConnection(URL,Login,Password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(Query);

        resultSet.next();
        String MyMoney = resultSet.getString("Dostepne_srodki");
        Float MineMoney = Float.valueOf(MyMoney);

        if(AmountMoney > MineMoney){
            //warning.setText("Nie masz wystarczająco dużo środków");
            DialogUtilities.dialogFailure("Brak środków aby wykonać ten przelew");
        }else {

            Query = "INSERT INTO `Transakcje`(`rachunek_nadawcy`, `rachunek_odbiorcy`, `kwota`, `Data`, `title`) VALUES (" + SenderAccountNumber + "," + ReceiverAccountNumber + "," + AmountMoney + "," + "'" + Date + "'" + "," + "'" + TransactionTitle + "'" + ")";


            PreparedStatement prepareStatement = connection.prepareStatement(Query);
            prepareStatement.execute();

            Query = "UPDATE `Rachunek` SET `Dostepne_srodki`= (Dostepne_srodki - " + SenderMoney + ") WHERE `Nr_rachunku`= " + SenderAccountNumber;

            prepareStatement = connection.prepareStatement(Query);
            prepareStatement.execute();

            Query = "UPDATE `Rachunek` SET `Dostepne_srodki`= (Dostepne_srodki + " + AmountMoney + ") WHERE `Nr_rachunku`= " + ReceiverAccountNumber;

            prepareStatement = connection.prepareStatement(Query);
            prepareStatement.execute();


            ReceiverAccount.setText("");
            SenderAccount.setValue("");
            Amount.setText("");
            Title.setText("");

            DialogUtilities.dialogSuccess("Przelew został wykonany poprawnie");

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

    public void openTransactionHistoryPage(ActionEvent event) {
    }
}

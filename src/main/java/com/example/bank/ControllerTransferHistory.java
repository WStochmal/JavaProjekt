package com.example.bank;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
//import java.time.Duration;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerTransferHistory implements Initializable  {
        private Stage stage;
        private Scene scene;
        private Parent root;

        @FXML
        Button AccountButton;
        @FXML
        private Label time;
    @FXML
    private TableColumn<Transfer, String> Kwota;

    @FXML
    private TableColumn<Transfer, String> Nadawca;

    @FXML
    private TableColumn<Transfer, String> Numer;

    @FXML
    private TableColumn<Transfer, String> Odbiorca;

    @FXML
    private TableColumn<Transfer, String> Tytyl;

    @FXML
    private TableView<Transfer> table;


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
            initClock();

            ObservableList<Transfer>items= FXCollections.observableArrayList();

            String URL = "jdbc:mysql://h25.seohost.pl:3306/srv42082_java_2";
            String Login = "srv42082_java_2";
            String Password = "qwerty123$";

            String AccountNumber, Number, Sender, Receiver, Title, Amount;


            for (int i = 0; i < Data.AccountList.size();i++) {
                AccountNumber = Data.AccountList.get(i).getAccountNumber();

                String Query = "SELECT * FROM `Transakcje` WHERE rachunek_nadawcy = " + "'" + AccountNumber + "'";
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(URL,Login,Password);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(Query);

                    while(resultSet.next()){
                        Number = resultSet.getString("ID");
                        Sender = resultSet.getString("rachunek_nadawcy");
                        Receiver = resultSet.getString("rachunek_odbiorcy");
                        Title = resultSet.getString("Data");
                        Amount = resultSet.getString("kwota");

                        items.add(new Transfer(Number,Sender,Receiver,Title,Amount));
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }



            }




            //items.add(new Transfer("1","1","1","1","1"));



            table.setItems(items);
            Numer.setCellValueFactory(new PropertyValueFactory<Transfer,String>("Number"));
            Odbiorca.setCellValueFactory(new PropertyValueFactory<Transfer,String>("Sender"));
            Nadawca.setCellValueFactory(new PropertyValueFactory<Transfer,String>("Receiver"));
            Tytyl.setCellValueFactory(new PropertyValueFactory<Transfer,String>("Title"));
            Kwota.setCellValueFactory(new PropertyValueFactory<Transfer,String>("Amount"));



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

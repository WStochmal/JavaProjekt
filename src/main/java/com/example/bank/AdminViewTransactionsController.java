package com.example.bank;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminViewTransactionsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableColumn<TransferAdmin, String> Amount;

    @FXML
    private TableColumn<TransferAdmin, String> TransferDate;

    @FXML
    private TableColumn<TransferAdmin, String> ID;

    @FXML
    private TableColumn<TransferAdmin, String> Receiver;

    @FXML
    private TableColumn<TransferAdmin, String> Sender;

    @FXML
    private TableColumn<TransferAdmin, String> Title;

    @FXML
    private TableView<TransferAdmin> table;
    public void exit(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminDesktopPanel.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ObservableList<TransferAdmin> items= FXCollections.observableArrayList();

        String URL = "jdbc:mysql://h25.seohost.pl:3306/srv42082_java_2";
        String Login = "srv42082_java_2";
        String Password = "qwerty123$";

        String AccountNumber, Number, Sender, Receiver, Title, Amount, TransferDate;


            String Query = "SELECT * FROM `Transakcje` ";
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(URL,Login,Password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(Query);

                while(resultSet.next()){
                    Number = resultSet.getString("ID");
                    Sender = resultSet.getString("rachunek_nadawcy");
                    Receiver = resultSet.getString("rachunek_odbiorcy");
                    Title = resultSet.getString("title");
                    Amount = resultSet.getString("kwota");
                    TransferDate = resultSet.getString("Data");

                    items.add(new TransferAdmin(Number,Sender,Receiver,Title,Amount,TransferDate));
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}

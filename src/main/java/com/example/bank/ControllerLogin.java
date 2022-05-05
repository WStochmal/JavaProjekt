package com.example.bank;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
public class ControllerLogin {

    @FXML
    PasswordField passwordField;
    @FXML
    TextField loginField;
    @FXML
    private Button cancel;

    @FXML
    Label warning;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private static String URL = "jdbc:mysql://h25.seohost.pl:3306/srv42082_java_2";
    private static String Login = "srv42082_java_2";
    private static String Password = "qwerty123$";
    private static String Query1 = "SELECT * FROM Konto";


    public void Login(ActionEvent event) throws IOException, SQLException {
        Connection connection = DriverManager.getConnection(URL,Login,Password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(Query1);

        String login = loginField.getText();
        String password = passwordField.getText();
        System.out.println(login);
        System.out.println(password);
        String UserLogin = loginField.getText();
        String UserPassword = passwordField.getText();
        System.out.println(login);
        System.out.println(password);
        while (resultSet.next()){
            if (Objects.equals(UserLogin, resultSet.getString("Login")) && Objects.equals(UserPassword, resultSet.getString("Haslo"))){

                root = FXMLLoader.load(getClass().getResource("Desktop.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root,1200,800);
                stage.setScene(scene);
                stage.show();
            }
            else{
                warning.setText("Podano zle dane");
            }
        }


    }
    public void exit()
    {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}

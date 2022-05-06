package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class AdminAddNewUserController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField newFirstname;
    @FXML
    TextField NewLastname;
    @FXML
    TextField NewPesel;
    @FXML
    TextField NewAge;
    @FXML
    TextField NewGender;
    @FXML
    TextField NewCountry;
    @FXML
    TextField NewCity;
    @FXML
    TextField NewStreet;
    @FXML
    TextField NewHouseNumber;
    @FXML
    TextField NewPostCode;
    @FXML
    TextField NewEmail;
    @FXML
    TextField NewPhoneNumber;
    @FXML
    TextField NewLogin;
    @FXML
    TextField NewPassword;
    @FXML
    TextField NewPasswordRepeat;
    @FXML
    CheckBox NewCard;
    @FXML
    Label warning;

    public void RegisterNewUser(ActionEvent event) throws SQLException {
        int error = 0;
        String Name = newFirstname.getText();
        String Surname = NewLastname.getText();
        String ID = NewPesel.getText();
        String Age = NewAge.getText();
        String Sex = NewGender.getText();
        String Country = NewCountry.getText();
        String City = NewCity.getText();
        String Street = NewStreet.getText();
        String PostCode = NewPostCode.getText();
        String HouseNumber = NewHouseNumber.getText();
        String Email = NewEmail.getText();
        String Tel = NewPhoneNumber.getText();
        String Login = NewLogin.getText();
        String Password = NewPassword.getText();
        String Password2 = NewPasswordRepeat.getText();

        if(Name == null || Surname == null || ID == null || Age == null || Sex == null || Country == null || City == null || Street == null || PostCode == null || HouseNumber == null || Email == null || Tel == null || Login == null || Password == null || Password2 == null){
           warning.setText("Nie podano wszystkich potrzebnych danych");
           error = 1;
        }
        if(Password != Password2){
            warning.setText("Hasla nie sa takie same");
        }

        if(error == 0){
            String URL = "jdbc:mysql://h25.seohost.pl:3306/srv42082_java_2";
            String LoginDB = "srv42082_java_2";
            String PasswordDB = "qwerty123$";
            String Query = "INSERT INTO `Adres`(`Kraj`, `Miasto`, `Ulica`, `Nr_domu`, `Kod_pocztowy`) VALUES ("+"'"+ Country +"'"+","+"'"+City +"'"+","+"'"+Street +"'"+","+"'"+HouseNumber +"'"+","+"'"+PostCode +"')";

            Connection connection = DriverManager.getConnection(URL,LoginDB,PasswordDB);
            PreparedStatement statement = connection.prepareStatement(Query);
            statement.execute();

            Query = "SELECT * FROM Adres WHERE Kraj = " +"'"+ Country +"'"+ " AND "+ "Miasto = " +"'"+ City +"'"+ " AND " + "Ulica = " +"'"+ Street +"'"+ " AND " + "Nr_domu = " +"'"+ HouseNumber +"'"+ " AND " + "Kod_pocztowy = " +"'"+ PostCode +"'";

            System.out.println(Query);

            Statement Sstatement = connection.createStatement();
            ResultSet resultSet = Sstatement.executeQuery(Query);

            resultSet.next();
            String ID_Adres = resultSet.getString("ID_Adres");

            Query = "INSERT INTO `Dane_osobowe`(`Imie`, `Nazwisko`, `Email`, `Tel`, `Pesel`, `Plec`, `Wiek`, `ID_Adres`) VALUES ("+"'"+ Name +"'"+","+"'"+Surname+"'"+","+"'"+Email +"'"+","+"'"+Tel +"'"+","+"'"+ID+"'"+","+"'"+ Sex +"'"+","+"'"+Age+"'"+","+"'"+ID_Adres +"')";

            statement = connection.prepareStatement(Query);
            statement.execute();

            Query = "SELECT * FROM Dane_osobowe WHERE Pesel = " +"'"+ ID +"'";

            Sstatement = connection.createStatement();
            resultSet = Sstatement.executeQuery(Query);

            resultSet.next();
            String ID_Person = resultSet.getString("ID_Osoby");

            String jeden = "1";

            Query = "INSERT INTO `Konto`(`ID_Osoby`, `Login`, `Haslo`) VALUES ("+"'"+ ID_Person +"'"+","+"'"+Login+"'"+","+"'"+ Password +"')";

            statement = connection.prepareStatement(Query);
            statement.execute();


            /*
            Query = "SELECT * FROM Konto WHERE Login = " +"'"+ Login +"' AND Haslo = '"+ Password +"'";

            Sstatement = connection.createStatement();
            resultSet = Sstatement.executeQuery(Query);

            resultSet.next();
            String ID_Account = resultSet.getString("ID_Konta");

            Query = "INSERT INTO `Rachunek`(`ID_Osoby`, `Login`, `Haslo`,`typ`) VALUES ("+"'"+ ID_Person +"'"+","+"'"+Login+"'"+","+"'"+ Password +",'1')";

            statement = connection.prepareStatement(Query);
            statement.execute();

             */

        }



    }

    public void exit(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminDesktopPanel.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

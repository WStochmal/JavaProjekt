package com.example.bank;

import javafx.scene.control.Alert;

public class DialogUtilities {
    public static  void dialogSuccess(String data)
    {
        Alert message= new Alert(Alert.AlertType.INFORMATION);
        message.setTitle("Sukces");
        message.setHeaderText("Sukces");
        message.setContentText(data);
        message.showAndWait();


    }
    public static  void dialogFailure(String data)
    {
        Alert message= new Alert(Alert.AlertType.INFORMATION);
        message.setTitle("Sukces");
        message.setHeaderText("Sukces");
        message.setContentText(data);
        message.showAndWait();


    }
}

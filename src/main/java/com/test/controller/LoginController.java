package com.test.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.*;

public class LoginController {

    @FXML
    private TextField LoginField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private Button LoginButton;


    public void onLogin(ActionEvent event) throws IOException {
        BufferedReader br = null;
        try {
            String filePath = new File("").getAbsolutePath();
            br = new BufferedReader(new FileReader(filePath + "//src//main//resources//login.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (br != null) {
            String st;
            while ((st = br.readLine()) != null) {
                String[] splitted = st.split(" ");
                if (LoginField.getText().equals(splitted[0]) && PasswordField.getText().equals(splitted[1])) {
                    Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
                    Scene login = new Scene(parent);

                    Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(login);
                    window.show();


                }
                else {Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Your username or password is incorrect");
                    alert.showAndWait();}
            }
        }
    }
}


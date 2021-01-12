package com.test.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    @FXML
    private Label WelcomeLabel;
    @FXML
    private Button TeacherBtn;
    @FXML
    private Button StudentBtn;

    @FXML
    public void initialize() {

        WelcomeLabel.setWrapText(true);
        WelcomeLabel.setFont(new Font("Arial", 15));
        TeacherBtn.setAlignment(Pos.CENTER);
        StudentBtn.setAlignment(Pos.CENTER);
    }

    public void onTeacher(ActionEvent event)throws IOException {
        Parent parent =FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Scene login = new Scene(parent);

        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(login);
        window.show();

    }

}

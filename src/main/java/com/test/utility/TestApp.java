package com.test.utility;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestApp extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));

        stage.setScene(new Scene(root));
        stage.show();
    }
    public static class Launcher{
        public static void  main(String[] args){
            Application.launch(TestApp.class, args);
        }

    }

}

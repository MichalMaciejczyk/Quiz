package com.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.test.entity.QuestionModel;
import com.test.entity.Quiz;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {


    private int selectedAnswer;
    IntegerProperty selectedAnswerProperty=new SimpleIntegerProperty();
    @FXML
    private ListView<QuestionModel> listView;
    @FXML
    private Button AddBtn;
    @FXML
    private Button SaveBtn;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldA;
    @FXML
    private TextField fieldB;
    @FXML
    private TextField fieldC;
    @FXML
    private TextField fieldD;
    @FXML
    private RadioButton buttonA;
    @FXML
    private RadioButton buttonB;
    @FXML
    private RadioButton buttonC;
    @FXML
    private RadioButton buttonD;
    @FXML
    private Button load;
    @FXML
    private Button delete;

    @FXML
    public void initialize(){
        buttonA.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    selectedAnswerProperty.set(1);
                    System.out.println(selectedAnswerProperty);
                }
            }
        });
        buttonB.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    selectedAnswerProperty.set(2);
                    System.out.println(selectedAnswerProperty);
                }
            }
        });
        buttonC.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    selectedAnswerProperty.set(3);
                    System.out.println(selectedAnswerProperty);
                }
            }
        });
        buttonD.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    selectedAnswerProperty.set(4);
                    System.out.println(selectedAnswerProperty);
                }
            }
        });
    }
    @FXML
    public void answerChecked(ActionEvent event) {

        if (event.getSource() == buttonA) {
            selectedAnswer=1;
        } else if (event.getSource() == buttonB) {
            selectedAnswer=2;
        } else if (event.getSource() == buttonC) {
            selectedAnswer=3;
        } else if (event.getSource() == buttonD) {
            selectedAnswer =4;
        }
    }

    @FXML
    private void zaladuj(ActionEvent event) throws Exception{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));
        File file =fileChooser.showOpenDialog((Stage)((Node)event.getSource()).getScene().getWindow());


        if(!file.exists())
            return;

        var reader = new ObjectMapper();
        Quiz quiz =reader.readValue(file, Quiz.class);

        List<QuestionModel>modelList =quiz.getQuestions().stream()
                .map(QuestionModel.Question::toModel)
                .collect(Collectors.toList());

        listView.setItems(
                FXCollections.observableArrayList(modelList)
        );
    }

    public void onAdd(){
        QuestionModel question =new QuestionModel();
        question.text.setValue(fieldName.getText());
        question.answerA.setValue(fieldA.getText());
        question.answerB.setValue(fieldB.getText());
        question.answerC.setValue(fieldC.getText());
        question.answerD.setValue(fieldD.getText());
        question.rightAnswer.setValue(selectedAnswer);

        listView.getItems().add(question);
    }
    @FXML
    public void onSave(ActionEvent event)throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".json", ".json"));
         File file =fileChooser.showSaveDialog((Stage)((Node)event.getSource()).getScene().getWindow());
        var list = listView.getItems()
                .stream()
                .map(QuestionModel::toData)
                .collect(Collectors.toList());
        var writer = new ObjectMapper();
        writer.enable(SerializationFeature.INDENT_OUTPUT);
        writer.writeValue(file, new Quiz(list));
    }
    private QuestionModel prevSelectedQuestion;
    public void onEdit(ActionEvent event){
        int var;
        QuestionModel question = listView.getSelectionModel().getSelectedItem();
        if(question == null)
            return;

        if(prevSelectedQuestion != null){
            prevSelectedQuestion.text.unbind();
            prevSelectedQuestion.answerA.unbind();
            prevSelectedQuestion.answerB.unbind();
            prevSelectedQuestion.answerC.unbind();
            prevSelectedQuestion.answerD.unbind();
            prevSelectedQuestion.rightAnswer.unbind();
        }
        fieldName.setText(question.text.getValue());
        fieldA.setText(question.answerA.getValue());
        fieldB.setText(question.answerB.getValue());
        fieldC.setText(question.answerC.getValue());
        fieldD.setText(question.answerD.getValue());
        var =question.rightAnswer.getValue();
        if(var==1){
            buttonA.setSelected(true);
        }
        else if (var==2){
            buttonB.setSelected(true);
        }
        else if (var==3){
            buttonC.setSelected(true);
        }
        else if(var==4){
            buttonD.setSelected(true);
        }

        question.text.bind(fieldName.textProperty());
        question.answerA.bind(fieldA.textProperty());
        question.answerB.bind(fieldB.textProperty());
        question.answerC.bind(fieldC.textProperty());
        question.answerD.bind(fieldD.textProperty());
        question.rightAnswer.bind(selectedAnswerProperty);

        prevSelectedQuestion = question;


    }


}

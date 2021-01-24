package com.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.test.entity.QuestionModel;
import com.test.entity.Quiz;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreateNewTestController {

    IntegerProperty questionTypeValue;

    @FXML
    private TextField fieldA;
    @FXML
    private RadioButton buttonA;
    @FXML
    private TextField fieldB;
    @FXML
    private RadioButton buttonB;
    @FXML
    private TextField fieldC;
    @FXML
    private RadioButton buttonC;
    @FXML
    private TextField fieldD;
    @FXML
    private RadioButton buttonD;
    @FXML
    private TextField fieldName;
    @FXML
    private List<Integer> selectedAnswer;
    @FXML
    private ListView<QuestionModel> listView;
    @FXML
    private Button Add;
    @FXML
    private Button DeleteBtn;
    @FXML
    private ComboBox comboId = new ComboBox();


    public void initialize(){
        fieldName.setDisable(true);
        fieldA.setDisable(true);
        buttonA.setDisable(true);
        fieldB.setDisable(true);
        buttonB.setDisable(true);
        fieldC.setDisable(true);
        buttonC.setDisable(true);
        fieldD.setDisable(true);
        buttonD.setDisable(true);
        selectedAnswer = new ArrayList<>();
        questionTypeValue = new SimpleIntegerProperty();

        comboId.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                if(t1 != null) {
                    switch (t1.trim()){
                        case "Pytanie zamknięte":{
                            onAddQuestion();
                            questionTypeValue.setValue(1);
                            break;
                        }
                        case "Krótka odpowiedź":{
                            onAddStringQuestion();
                            selectedAnswer.clear();
                            questionTypeValue.setValue(2);
                            break;
                        }
                        case "Pytanie opisowe":{
                            descriptiveQuestion();
                            questionTypeValue.setValue(3);
                            selectedAnswer.clear();
                            break;
                        }

                    }
                }
            }
        });
    }

    public void answerChecked(ActionEvent event) {
        if (event.getSource() == buttonA ) {
            if (buttonA.isSelected()) {
                selectedAnswer.add(1);
            }
            else {
                selectedAnswer.remove((Integer) 1);
            }
        } else if (event.getSource() == buttonB) {
            if (buttonB.isSelected())
                selectedAnswer.add(2);
            else
                selectedAnswer.remove((Integer)2);
        } else if (event.getSource() == buttonC) {
            if (buttonC.isSelected())
                selectedAnswer.add(3);
            else
                selectedAnswer.remove((Integer)3);
        } else if (event.getSource() == buttonD) {
            if (buttonD.isSelected())
                selectedAnswer.add(4);
            else
                selectedAnswer.remove((Integer)4);
        }
    }
    public void onAddQuestion(){
        fieldName.setDisable(false);
        fieldA.setDisable(false);
        buttonA.setDisable(false);
        fieldB.setDisable(false);
        buttonB.setDisable(false);
        fieldC.setDisable(false);
        buttonC.setDisable(false);
        fieldD.setDisable(false);
        buttonD.setDisable(false);
        Add.setDisable(false);
    }
    public void onAddStringQuestion(){
        fieldName.setDisable(false);
        fieldName.setText("");
        fieldA.setDisable(true);
        fieldA.setText("");
        buttonA.setSelected(false);
        buttonA.setDisable(true);
        fieldB.setText("");
        fieldB.setDisable(true);
        buttonB.setSelected(false);
        buttonB.setDisable(true);
        fieldC.setDisable(true);
        fieldC.setText("");
        buttonC.setSelected(false);
        buttonC.setDisable(true);
        fieldD.setDisable(true);
        fieldD.setText("");
        buttonD.setSelected(false);
        buttonD.setDisable(true);
        Add.setDisable(false);
    }
    public void descriptiveQuestion(){
        fieldName.setDisable(false);
        fieldName.setText("");
        fieldA.setDisable(false);
        fieldA.setDisable(true);
        fieldA.setText("");
        buttonA.setSelected(false);
        buttonA.setDisable(true);
        fieldB.setText("");
        fieldB.setDisable(true);
        buttonB.setSelected(false);
        buttonB.setDisable(true);
        fieldC.setDisable(true);
        fieldC.setText("");
        buttonC.setSelected(false);
        buttonC.setDisable(true);
        fieldD.setDisable(true);
        fieldD.setText("");
        buttonD.setSelected(false);
        buttonD.setDisable(true);
        Add.setDisable(false);
    }

    public void onAdd()  {
        QuestionModel question =new QuestionModel();
        question.text.setValue(fieldName.getText());
        question.answerA.setValue(fieldA.getText());
        question.answerB.setValue(fieldB.getText());
        question.answerC.setValue(fieldC.getText());
        question.answerD.setValue(fieldD.getText());
        question.questionType.setValue(questionTypeValue.getValue());
        question.rightAnswer = new ArrayList<>();
        for(Integer answer: selectedAnswer){
            if(question.questionType.getValue()==1) {
                IntegerProperty intProp = new SimpleIntegerProperty();
                question.rightAnswer.add(intProp);
                System.out.println(intProp+"intprop");
                question.rightAnswer.get(question.rightAnswer.size() - 1).set(answer);
                System.out.println(answer+"answer");
            }
        }
        if(fieldName.getText().trim().isEmpty()){
            System.out.println("źle");
        }
        else
            listView.getItems().add(new QuestionModel(question));
    }
    public void onSaveAs(ActionEvent event)throws IOException {

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

//    public void onEdit(ActionEvent event){
//        Add.setDisable(true);
//        QuestionModel question = new QuestionModel(listView.getSelectionModel().getSelectedItem());
//
//        if(prevSelectedQuestion != null){
//            prevSelectedQuestion.text.unbind();
//            prevSelectedQuestion.answerA.unbind();
//            prevSelectedQuestion.answerB.unbind();
//            prevSelectedQuestion.answerC.unbind();
//            prevSelectedQuestion.answerD.unbind();
//            for(IntegerProperty answer: prevSelectedQuestion.rightAnswer){
//                answer.unbind();
//            }
//        }
//        fieldName.setText(question.text.getValue());
//        fieldA.setText(question.answerA.getValue());
//        fieldB.setText(question.answerB.getValue());
//        fieldC.setText(question.answerC.getValue());
//        fieldD.setText(question.answerD.getValue());
//        buttonA.setSelected(false);
//        buttonB.setSelected(false);
//        buttonC.setSelected(false);
//        buttonD.setSelected(false);
//
//        if(question != null && question.rightAnswer != null && !question.rightAnswer.isEmpty()) {
//
//            for (IntegerProperty answer : question.rightAnswer) {
//
//                switch (answer.getValue()) {
//                    case 1: {
//                        buttonA.setSelected(true);
//                        break;
//                    }
//                    case 2: {
//                        buttonB.setSelected(true);
//                        break;
//                    }
//                    case 3: {
//                        buttonC.setSelected(true);
//                        break;
//                    }
//                    case 4: {
//                        buttonD.setSelected(true);
//                        break;
//                    }
//
//                }
//            }
//        }
//        question.text.bind(fieldName.textProperty());
//        question.answerA.bind(fieldA.textProperty());
//        question.answerB.bind(fieldB.textProperty());
//        question.answerC.bind(fieldC.textProperty());
//        question.answerD.bind(fieldD.textProperty());
//        selectedAnswerProperty.clear();
//        IntegerProperty propTmp = new SimpleIntegerProperty(0);
//        for(IntegerProperty prop: question.rightAnswer){
//            IntegerProperty intpropTmp = new SimpleIntegerProperty();
//            intpropTmp.set(prop.getValue());
//            selectedAnswerProperty.add(intpropTmp);
//            prop.bind(selectedAnswerProperty.get(selectedAnswerProperty.size() - 1));
//        }
//
//        question.text.getValue();
//        question.answerA.getValue();
//        question.answerB.getValue();
//        question.answerC.getValue();
//        question.answerD.getValue();
//        if(question.rightAnswer != null && !question.rightAnswer.isEmpty())
//        for(IntegerProperty prop : question.rightAnswer){
//            prop.getValue();
//        }
//        prevSelectedQuestion = question;
//
//    }

    public void onDelete() {
        DeleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final int selectedIdx = listView.getSelectionModel().getSelectedIndex();
                if (selectedIdx != -1) {
                    QuestionModel itemToRemove = listView.getSelectionModel().getSelectedItem();

                    final int newSelectedIdx =
                            (selectedIdx == listView.getItems().size() - 1)
                                    ? selectedIdx - 1
                                    : selectedIdx;

                    listView.getItems().remove(selectedIdx);
                    listView.getSelectionModel().select(newSelectedIdx);
                    System.out.println("selectIdx: " + selectedIdx);
                    System.out.println("item: " + itemToRemove);
                }
            }
        }
        );
    }
    public void onEditButton(){
        QuestionModel question = new QuestionModel(listView.getSelectionModel().getSelectedItem());
        selectedAnswer.clear();

        if (question != null && question.questionType != null ) {
            fieldName.setText(question.text.getValue());
            fieldA.setText(question.answerA.getValue());
            fieldB.setText(question.answerB.getValue());
            fieldC.setText(question.answerC.getValue());
            fieldD.setText(question.answerD.getValue());

            switch(question.questionType.getValue()){
                case 1: {
                    buttonA.setSelected(false);
                    buttonB.setSelected(false);
                    buttonC.setSelected(false);
                    buttonD.setSelected(false);
                    for (IntegerProperty ans : question.rightAnswer) {
                        System.out.println(ans);
                        selectedAnswer.add(ans.getValue());

                        switch (ans.getValue()) {
                            case 1: {
                                buttonA.setSelected(true);
                                break;
                            }
                            case 2: {
                                buttonB.setSelected(true);
                                break;
                            }
                            case 3: {
                                buttonC.setSelected(true);
                                break;
                            }
                            case 4: {
                                buttonD.setSelected(true);
                                break;
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    fieldName.setText(question.text.getValue());
                    break;

                }
                case 3: {
                    fieldName.setText(question.text.getValue());
                    break;
                }
            }
        }
    }
    public void onSaveEdit()  {
        QuestionModel question =listView.getSelectionModel().getSelectedItem();
        question.text.setValue(fieldName.getText());
        question.answerA.setValue(fieldA.getText());
        question.answerB.setValue(fieldB.getText());
        question.answerC.setValue(fieldC.getText());
        question.answerD.setValue(fieldD.getText());
        question.questionType.setValue(questionTypeValue.getValue());
        question.rightAnswer.clear();
        for(Integer answer: selectedAnswer){
            if(question.questionType.getValue()==1) {
                IntegerProperty intProp = new SimpleIntegerProperty();
                question.rightAnswer.add(intProp);
                question.rightAnswer.get(question.rightAnswer.size() - 1).set(answer);
                System.out.println(answer);
            }
        }
    }
}



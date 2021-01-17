package com.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.PDF.HelloWorld;
import com.test.entity.QuestionModel;
import com.test.entity.Questions;
import com.test.entity.QuestionsWrapper;
import com.test.entity.Quiz;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class CreateNewTestController {

    List <IntegerProperty> selectedAnswerProperty=new ArrayList<>();
    IntegerProperty questionTypeValue;
    @FXML
    private Button MixBtn;
    @FXML
    private Button MixBtnAns;
    @FXML
    private Button OneAnswer;
    @FXML
    private Button SimpleString;
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
    private Button SaveAs;
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
                        case "Krótka odpowiedźr":{
                            onAddStringQuestion();
                            questionTypeValue.setValue(2);
                            break;
                        }
                        case "Pytanie opisowe":{
                            descriptiveQuestion();
                            questionTypeValue.setValue(3);
                            break;
                        }

                    }
                }
            }
        });
//    IntegerProperty intPropTmp = new SimpleIntegerProperty();
//        buttonA.selectedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
//                if (isNowSelected) {
//                    intPropTmp.set(1);
//                    selectedAnswerProperty.add(intPropTmp);
//                    System.out.println("rozmiar listy dodawanie" + selectedAnswerProperty.size());
//                    if(selectedAnswerProperty != null && !selectedAnswerProperty.isEmpty())
//                    System.out.println(selectedAnswerProperty.get(selectedAnswerProperty.size()-1));
//                }
//                else{
//                    List <Integer> tmpList = new ArrayList<>();
//                    if(selectedAnswerProperty != null && !selectedAnswerProperty.isEmpty()){
//                        int i = 0;
//                        for(IntegerProperty prop: selectedAnswerProperty){
//                            if(prop.getValue() == 1){
//                                tmpList.add(i);
//                                i++;
//                            }
//                        }
//                        for(Integer stala: tmpList){
//                            System.out.println("stala" + stala);
//                            selectedAnswerProperty.remove((int)stala);
//                        }
//                    }
//
//                }
//            }
//        });

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
        QuestionModel question =new QuestionModel();
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
        QuestionModel question =new QuestionModel();
        fieldName.setDisable(false);
        fieldName.setText("");
        fieldA.setDisable(false);
        fieldA.setDisable(true);
        fieldA.setText("");
        buttonA.setSelected(false);
        fieldB.setText("");
        fieldB.setDisable(true);
        buttonB.setSelected(false);
        fieldC.setDisable(true);
        fieldC.setText("");
        buttonC.setSelected(false);
        fieldD.setDisable(true);
        fieldD.setText("");
        buttonD.setSelected(false);
        Add.setDisable(false);
    }

    public void onAdd() throws FileNotFoundException {
        HelloWorld hl = new HelloWorld();
        hl.createPdf();
        QuestionModel question =new QuestionModel();
        question.text.setValue(fieldName.getText());
        question.answerA.setValue(fieldA.getText());
        question.answerB.setValue(fieldB.getText());
        question.answerC.setValue(fieldC.getText());
        question.answerD.setValue(fieldD.getText());
        question.questionType.setValue(questionTypeValue.getValue());
        question.rightAnswer = new ArrayList<>();
        for(Integer answer: selectedAnswer){
            IntegerProperty intProp = new SimpleIntegerProperty();
            question.rightAnswer.add(intProp);
            question.rightAnswer.get(question.rightAnswer.size() - 1).set(answer);
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
    public void onEdit(ActionEvent event){
        Add.setDisable(true);
        QuestionModel question = new QuestionModel(listView.getSelectionModel().getSelectedItem());

        if(prevSelectedQuestion != null){
            prevSelectedQuestion.text.unbind();
            prevSelectedQuestion.answerA.unbind();
            prevSelectedQuestion.answerB.unbind();
            prevSelectedQuestion.answerC.unbind();
            prevSelectedQuestion.answerD.unbind();
            for(IntegerProperty answer: prevSelectedQuestion.rightAnswer){
                answer.unbind();
            }
        }
        fieldName.setText(question.text.getValue());
        fieldA.setText(question.answerA.getValue());
        fieldB.setText(question.answerB.getValue());
        fieldC.setText(question.answerC.getValue());
        fieldD.setText(question.answerD.getValue());
        buttonA.setSelected(false);
        buttonB.setSelected(false);
        buttonC.setSelected(false);
        buttonD.setSelected(false);

        if(question != null && question.rightAnswer != null && !question.rightAnswer.isEmpty()) {

            for (IntegerProperty answer : question.rightAnswer) {

                switch (answer.getValue()) {
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
        }
        question.text.bind(fieldName.textProperty());
        question.answerA.bind(fieldA.textProperty());
        question.answerB.bind(fieldB.textProperty());
        question.answerC.bind(fieldC.textProperty());
        question.answerD.bind(fieldD.textProperty());
        selectedAnswerProperty.clear();
        IntegerProperty propTmp = new SimpleIntegerProperty(0);
        for(IntegerProperty prop: question.rightAnswer){
            IntegerProperty intpropTmp = new SimpleIntegerProperty();
            intpropTmp.set(prop.getValue());
            selectedAnswerProperty.add(intpropTmp);
            prop.bind(selectedAnswerProperty.get(selectedAnswerProperty.size() - 1));
        }

        question.text.getValue();
        question.answerA.getValue();
        question.answerB.getValue();
        question.answerC.getValue();
        question.answerD.getValue();
        if(question.rightAnswer != null && !question.rightAnswer.isEmpty())
        for(IntegerProperty prop : question.rightAnswer){
            prop.getValue();
        }
        prevSelectedQuestion = question;

    }

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
    public void onMix(){
        MixBtn.setOnAction(new EventHandler<ActionEvent>() {
                               @Override
                               public void handle(ActionEvent event) {

                                   Random rand = new Random();
                                   int i = listView.getItems().size();
                                   int randTmp;
                                   QuestionModel valueTmp;
                                   for(int j = 0 ; j < 1000 ; j++){
                                       randTmp = rand.nextInt(i);
                                       valueTmp = listView.getItems().get(randTmp);
                                       listView.getItems().remove(valueTmp);
                                       listView.getItems().add(valueTmp);
                                   }
                               }
                           }
        );
    }
    public void onMixAnswers(){
        MixBtnAns.setOnAction(new EventHandler<ActionEvent>() {
                               @Override
                               public void handle(ActionEvent event) {


                                   Random rand = new Random();
                                   int listSize =listView.getItems().size();
                                   QuestionModel valueTmp;
                                   int randTmp;

                                       for(QuestionModel question: listView.getItems()){
                                       if(question.questionType.get() == 1){
                                           for(int i = 0 ; i<9; i++) {
                                               randTmp = rand.nextInt(3);
                                               switch (randTmp){
                                                   case 0:{
                                                       losujPrzyciski(question, 1);
                                                       break;
                                                   }
                                                   case 1:{
                                                       losujPrzyciski(question, 2);
                                                       break;
                                                   }
                                                   case 2:{
                                                       losujPrzyciski(question, 3);
                                                       break;
                                                   }


                                               }
                                           }

                                       }
                                   }


                               }
                           }
        );
    }
    private void losujPrzyciski (QuestionModel question, int button){
        StringProperty answerTmp = new SimpleStringProperty();
        boolean butA = false;
        boolean butB = false;
        boolean butC = false;
        boolean butD = false;
        switch (button){
            case 1 : {
                answerTmp = question.answerA;
                question.answerA = question.answerD;
                question.answerD = answerTmp;
                break;
            }
            case 2 : {
                answerTmp = question.answerB;
                question.answerB = question.answerD;
                question.answerD = answerTmp;
                break;
            }
            case 3 : {
                answerTmp = question.answerC;
                question.answerC = question.answerD;
                question.answerD = answerTmp;
                break;
            }

        }

        for(IntegerProperty right: question.rightAnswer){
            if(right.getValue() == 1){
                butA = true;
            }
            if(right.getValue() == 2){
                butB = true;
            }
            if(right.getValue() == 3){
                butC = true;
            }
            if(right.getValue() == 4){
                butD = true;
            }
        }
        switch (button) {
            case 1: {
                if ((butA && butD) || (!butA && !butD)) {
                } else {
                    boolean tmp = butA;
                    butA = butD;
                    butD = tmp;
                }
                break;
            }
            case 2: {
                if ((butB && butD) || (!butB && !butD)) {
                } else {
                    boolean tmp = butB;
                    butB = butD;
                    butD = tmp;
                }
                break;
            }
            case 3: {
                if ((butC && butD) || (!butC && !butD)) {
                } else {
                    boolean tmp = butC;
                    butC = butD;
                    butD = tmp;
                }
                break;
            }
        }
        question.rightAnswer.clear();
        IntegerProperty intProp = new SimpleIntegerProperty();
        IntegerProperty intProp1 = new SimpleIntegerProperty();
        IntegerProperty intProp2 = new SimpleIntegerProperty();
        IntegerProperty intProp3 = new SimpleIntegerProperty();
        if(butA) {
            intProp.setValue(1);
            question.rightAnswer.add(intProp);
        }
        if(butB) {
            intProp1.setValue(2);
            question.rightAnswer.add(intProp1);
        }
        if(butC) {
            intProp2.setValue(3);
            question.rightAnswer.add(intProp2);
        }
        if(butD) {
            intProp3.setValue(4);
            question.rightAnswer.add(intProp3);
        }
        System.out.println("#########Rozmier listy######### " + question.rightAnswer.size() + " akcja  " + button);
        for(IntegerProperty prop : question.rightAnswer){
            System.out.println(prop.getValue());
        }

    }
    public void onExport() throws Exception {
        String filePath = new File("").getAbsolutePath();
        File jsonFile = new File(filePath + "//src//main//resources//data1.json").getAbsoluteFile();

        ObjectMapper mapper = new ObjectMapper();

        // enable pretty printing
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // read map from file
        MapType mapType = mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
        Gson gson=new Gson();
        Map<String, Object> map = mapper.readValue(jsonFile, mapType);
        String json = mapper.writeValueAsString(map);
//      Type listType = new TypeToken<List<Questions>>() {}.getType();

//      List<Questions> yourList = new Gson().fromJson(json, listType);
        QuestionsWrapper yourList= new Gson().fromJson(json,QuestionsWrapper.class);
        QuestionsWrapper q=gson.fromJson(json,QuestionsWrapper.class);
        System.out.println(json);
//        yourList.forEach(s-> System.out.println(s));
        System.out.println(yourList);
        for(Questions w:yourList.getQuestions()){
            System.out.println(w.answerA);
        }



        // generate pretty JSON from map
        // split by system new lines
        String[] strings = json.split(System.lineSeparator());

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);


        contentStream.setFont(PDType1Font.COURIER, 12);
        contentStream.beginText();
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(25, 725);
        for (String string : strings) {
            contentStream.showText(string);
            // add line manually
            contentStream.newLine();
        }
        contentStream.endText();
        contentStream.close();

        document.save("pdfBoxHelloWorld.pdf");
        document.close();
    }
    public void onWydrukuj()  {
        HelloWorld h=new HelloWorld();
        try{
            h.createPdf();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }


    }
}

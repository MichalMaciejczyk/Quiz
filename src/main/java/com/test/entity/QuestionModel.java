package com.test.entity;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class QuestionModel {

    public StringProperty text = new SimpleStringProperty();
    public StringProperty answerA = new SimpleStringProperty();
    public StringProperty answerB = new SimpleStringProperty();
    public StringProperty answerC = new SimpleStringProperty();
    public StringProperty answerD = new SimpleStringProperty();
    public List<IntegerProperty> rightAnswer = new ArrayList<>();
    public IntegerProperty questionType = new SimpleIntegerProperty();

    public QuestionModel() {
    }

    public QuestionModel(QuestionModel model) {
        this.text = model.text;
        this.answerA = model.answerA;
        this.answerB = model.answerB;
        this.answerC = model.answerC;
        this.answerD = model.answerD;
        this.rightAnswer = model.rightAnswer;
        this.questionType =model.questionType;
    }

    public Question toData(){
        List <Integer> rightAnswers = new ArrayList<>();
        for(IntegerProperty prop : rightAnswer){
            rightAnswers.add(prop.getValue());
        }

        return new Question(text.get(), answerA.get(), answerB.get(), answerC.get(), answerD.get(),rightAnswers,questionType.get());
    }

    @Override
    public String toString() {
        return text.get();
    }


    public static class Question {
        private String text;

        private String answerA;
        private String answerB;
        private String answerC;
        private String answerD;
        private List<Integer> rightAnswer;
        private Integer questionType;


        public Question() { }

        public Question(String text, String answerA, String answerB, String answerC, String answerD,List<Integer> rightAnswer,Integer questionType) {
            this.text = text;
            this.answerA = answerA;
            this.answerB = answerB;
            this.answerC = answerC;
            this.answerD = answerD;
            this.rightAnswer=new ArrayList<>();
            this.rightAnswer.addAll(rightAnswer);
            this.questionType = questionType;
        }

        public QuestionModel toModel(){
            var model = new QuestionModel();
            model.text.set(text);
            model.answerA.set(answerA);
            model.answerB.set(answerB);
            model.answerC.set(answerC);
            model.answerD.set(answerD);
            model.questionType.set(questionType);
            model.rightAnswer = new ArrayList<>();
            for(Integer answer: rightAnswer){
                IntegerProperty intProp = new SimpleIntegerProperty();
                intProp.setValue(answer);
                model.rightAnswer.add(intProp);
                model.rightAnswer.get(model.rightAnswer.size() - 1).setValue(intProp.getValue());
            }
            return model;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getAnswerA() {
            return answerA;
        }

        public void setAnswerA(String answerA) {
            this.answerA = answerA;
        }

        public String getAnswerB() {
            return answerB;
        }

        public void setAnswerB(String answerB) {
            this.answerB = answerB;
        }

        public String getAnswerC() {
            return answerC;
        }

        public void setAnswerC(String answerC) {
            this.answerC = answerC;
        }

        public String getAnswerD() {
            return answerD;
        }

        public void setAnswerD(String answerD) { this.answerD = answerD; }

        public List<Integer> getRightAnswer() {
            return rightAnswer;
        }

        public void setRightAnswer(List<Integer> rightAnswer) {
            this.rightAnswer = rightAnswer;
        }

        public Integer getQuestionType() {return questionType;}

        public void setQuestionType(Integer questionType){this.questionType=questionType;}

    }
}

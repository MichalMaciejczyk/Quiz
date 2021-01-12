package com.test.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class QuestionModel {

    public StringProperty text = new SimpleStringProperty();
    public StringProperty answerA = new SimpleStringProperty();
    public StringProperty answerB = new SimpleStringProperty();
    public StringProperty answerC = new SimpleStringProperty();
    public StringProperty answerD = new SimpleStringProperty();
    public IntegerProperty rightAnswer = new SimpleIntegerProperty();



    public Question toData(){
        return new Question(text.get(), answerA.get(), answerB.get(), answerC.get(), answerD.get(),rightAnswer.get());
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
        private Integer rightAnswer;


        public Question() { }

        public Question(String text, String answerA, String answerB, String answerC, String answerD,Integer rightAnswer) {
            this.text = text;
            this.answerA = answerA;
            this.answerB = answerB;
            this.answerC = answerC;
            this.answerD = answerD;
            this.rightAnswer = rightAnswer;

        }

        public QuestionModel toModel(){
            var model = new QuestionModel();
            model.text.set(text);
            model.answerA.set(answerA);
            model.answerB.set(answerB);
            model.answerC.set(answerC);
            model.answerD.set(answerD);
            model.rightAnswer.set(rightAnswer);
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

        public Integer getRightAnswer(){return rightAnswer;}

        public void setRightAnswer(Integer rightAnswer){this.rightAnswer = rightAnswer;}






    }
}

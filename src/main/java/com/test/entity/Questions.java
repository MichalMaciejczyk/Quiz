package com.test.entity;

import java.util.List;

public class Questions {

    public String text ;
    public String answerA ;
    public String answerB ;
    public String answerC ;
    public String answerD ;
    public List<Integer> rightAnswer ;
    public Integer questionType ;


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

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public List<Integer> getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(List<Integer> rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }
}

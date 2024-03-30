package com.signs.signsschool.models;


import java.io.Serializable;

public class LimitedChoice implements Serializable {
    public String question, choice;

    public LimitedChoice(String question, String choice) {
        this.question = question;
        this.choice = choice;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }
}
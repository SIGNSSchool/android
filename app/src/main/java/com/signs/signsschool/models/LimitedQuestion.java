package com.signs.signsschool.models;

import java.io.Serializable;
import java.util.ArrayList;

public class LimitedQuestion implements Serializable {
    public String question;
    public ArrayList<String> options;

    public LimitedQuestion(String question, ArrayList<String> options) {
        this.question = question;
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }
}
package com.signs.signsschool.signs;

import com.signs.signsschool.models.Choice;
import com.signs.signsschool.models.LimitedChoice;
import com.signs.signsschool.models.User;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelSIGNSEinsicht implements Serializable {

    public String  subject, createdAt, message;
    User sender;
    Boolean isConfirmed;
    ArrayList<Choice> choices;
    LimitedChoice limitedChoice;


    public ModelSIGNSEinsicht(User sender, String subject, String createdAt, String message, Boolean isConfirmed, ArrayList<Choice> choices, LimitedChoice limitedChoice) {
        this.sender = sender;
        this.subject = subject;
        this.createdAt = createdAt;
        this.message = message;
        this.isConfirmed = isConfirmed;
        this.choices = choices;
        this.limitedChoice = limitedChoice;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<Choice> choices) {
        this.choices = choices;
    }

    public LimitedChoice getLimitedChoice() {
        return limitedChoice;
    }

    public void setLimitedChoice(LimitedChoice limitedChoice) {
        this.limitedChoice = limitedChoice;
    }
}

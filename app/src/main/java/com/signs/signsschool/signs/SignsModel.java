package com.signs.signsschool.signs;

import com.signs.signsschool.models.User;
import com.signs.signsschool.models.LimitedQuestion;
import java.util.ArrayList;

public class SignsModel {

    String subject, submitBy, createdAt, message, schoolId, recipientId, senderId, courseId, pk;
    User sender;
    User recipient;

    ArrayList<String> questions;
    LimitedQuestion limitedQuestion;

    public SignsModel(String subject, String submitBy, String createdAt, String message, String schoolId, String recipientId, User recipient, String senderId, User sender, String courseId, String pk, ArrayList<String> questions, LimitedQuestion limitedQuestion) {

        this.subject = subject;
        this.submitBy = submitBy;
        this.createdAt = createdAt;
        this.message = message;
        this.schoolId = schoolId;
        this.recipientId = recipientId;
        this.recipient = recipient;
        this.senderId = senderId;
        this.sender = sender;
        this.courseId = courseId;
        this.pk = pk;

        this.questions = questions;
        this.limitedQuestion = limitedQuestion;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubmitBy() {
        return submitBy;
    }

    public void setSubmitBy(String submitBy) {
        this.submitBy = submitBy;
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

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public Object getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public Object getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<String> questions) {
        this.questions = questions;
    }

    public LimitedQuestion getLimitedQuestion() {
        return limitedQuestion;
    }

    public void setLimitedQuestion(LimitedQuestion limitedQuestion) {
        this.limitedQuestion = limitedQuestion;
    }
}


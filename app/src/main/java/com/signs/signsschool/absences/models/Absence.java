package com.signs.signsschool.absences.models;


import com.signs.signsschool.models.User;

public class Absence {
    public String fromDate, toDate, createdAt, updatedAt, userId, grade, reason, pk;
    public Boolean isExcused;
    public User user;

    public Absence(String fromDate, String toDate, String createdAt, String updatedAt, String userId, String grade, String reason, String pk, Boolean isExcused, User user) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.grade = grade;
        this.reason = reason;
        this.pk = pk;
        this.isExcused = isExcused;
        this.user = user;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public Boolean getExcused() {
        return isExcused;
    }

    public void setExcused(Boolean excused) {
        isExcused = excused;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

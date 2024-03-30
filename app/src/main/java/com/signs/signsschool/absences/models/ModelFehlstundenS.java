package com.signs.signsschool.absences.models;

public class ModelFehlstundenS {

    String grade, fromDate, toDate, reason, userId, updatedAt, createdAt, pk;
    Boolean isExcused;

    public ModelFehlstundenS(String grade, String fromDate, String toDate, String reason, String userId, String updatedAt, String createdAt, String pk, Boolean isExcused) {
        this.grade = grade;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
        this.userId = userId;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.pk = pk;
        this.isExcused = isExcused;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public Boolean getIsExcused() {
        return isExcused;
    }

    public void setIsExcused(Boolean excused) {
        isExcused = excused;
    }
}

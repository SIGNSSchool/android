package com.signs.signsschool.assignments;

import com.signs.signsschool.models.User;

public class ModelAufgabenBewertungenS {


    String assignmentId, course, userId, updatedAt, submission, courseId, createdAt, teacherId, pk, evaluation;
    User user;

    public ModelAufgabenBewertungenS(String assignmentId, String course, String userId, String updatedAt, String submission, String courseId, String createdAt, String teacherId, String pk, String evaluation, User user) {
       this.assignmentId = assignmentId;
       this.course = course;
       this.userId = userId;
       this.updatedAt = updatedAt;
       this.submission = submission;
       this.courseId = courseId;
       this.createdAt = createdAt;
       this.teacherId = teacherId;
       this.pk = pk;
       this.evaluation = evaluation;
       this.user = user;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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

    public String getSubmission() {
        return submission;
    }

    public void setSubmission(String submission) {
        this.submission = submission;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
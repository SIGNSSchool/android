package com.signs.signsschool.news;

import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;

public class ModelNews {

    String submitBy, recurringAt, grade, courseId, createdAt, schoolId, description, pk, title;
    Boolean recurring;
    ArrayList<String> options;


    public ModelNews(String submitBy, String recurringAt, String grade, String courseId, String createdAt, String schoolId, String description, String pk, String title, Boolean recurring, JSONArray options) {
        this.submitBy = submitBy;
        this.recurringAt = recurringAt;
        this.grade = grade;
        this.courseId = courseId;
        this.createdAt = createdAt;
        this.schoolId = schoolId;
        this.description = description;
        this.pk = pk;
        this.title = title;
        this.recurring = recurring;

        try {
            for (int i = 0; i < options.length(); i++) {
                this.options.add((String) options.get(i));
            }
        } catch (Exception e) {
            Log.e("error parsing optons", e.toString());
        }
    }

    public String getSubmitBy() {
        return submitBy;
    }

    public void setSubmitBy(String submitBy) {
        this.submitBy = submitBy;
    }

    public String getRecurringAt() {
        return recurringAt;
    }

    public void setRecurringAt(String recurringAt) {
        this.recurringAt = recurringAt;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }
}
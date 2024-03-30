package com.signs.signsschool.models;

public class ModelhoursEdit {

    String teacher, date, hour, grade, course;
    Integer hour_id;


    public ModelhoursEdit(String teacher, String date, String hour, String grade, String course, Integer hour_id) {
        this.teacher = teacher;
        this.date = date;
        this.hour = hour;
        this.grade = grade;
        this.course = course;
        this.hour_id = hour_id;

    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getHour_id() {
        return hour_id;
    }

    public void setHour_id(Integer hour_id) {
        this.hour_id = hour_id;
    }
}

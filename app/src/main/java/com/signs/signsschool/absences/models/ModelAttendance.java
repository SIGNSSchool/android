package com.signs.signsschool.absences.models;

import org.json.JSONArray;

import java.util.ArrayList;

public class ModelAttendance {

    String coursname, teacher;
    Integer grade, course_id, Hour;
    JSONArray students;

    public ModelAttendance(String coursename, Integer grade, Integer course_id, String teacher, Integer Hour, JSONArray students) {
        this.coursname = coursename;
        this.grade = grade;
        this.course_id = course_id;
        this.teacher = teacher;
        this.Hour = Hour;
        this.students = students;

    }

    public String getCoursname() {
        return coursname;
    }

    public void setCoursname(String coursname) {
        this.coursname = coursname;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getHour() {
        return Hour;
    }

    public void setHour(Integer hour) {
        Hour = hour;
    }

    public JSONArray getStudents() {
        return students;
    }

    public void setStudents(JSONArray students) {
        this.students = students;
    }
}

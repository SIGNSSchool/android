package com.signs.signsschool.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Course {

    User teacher;
    String grade, course, courseId, createdAt, teacherId, schoolId;
    JSONArray participants = new JSONArray();
    JSONArray lessons = new JSONArray();

    public Course(JSONObject course) {
        try {
            this.teacher = new User(course.getJSONObject("teacher"));
            this.grade = course.getString("grade");
            this.course = course.getString("course");
            this.courseId = course.getString("courseId");
            this.createdAt = course.getString("createdAt");
            this.teacherId = course.getString("teacherId");
            this.schoolId = course.getString("schoolId");
            this.lessons = course.getJSONArray("lessons");
            this.participants = course.getJSONArray("participants");
        } catch (Exception e) {
            Log.e("error parsing course", e.toString());
        }
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
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

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public JSONArray getLessons() {
        return lessons;
    }

    public void setLessons(JSONArray lessons) {
        this.lessons = lessons;
    }

    public JSONArray getParticipants() {
        return participants;
    }

    public void setParticipants(JSONArray participants) {
        this.participants = participants;
    }
}

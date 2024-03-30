package com.signs.signsschool.news;

import android.util.Log;
import com.signs.signsschool.models.User;
import org.json.JSONArray;
import java.util.ArrayList;

public class ModelNewsSubmission {
    String courseId, schoolId, pk, newsId, submittedAt, title;
    User user;
    ArrayList<String> choices;

    public ModelNewsSubmission(String courseId, String schoolId, String pk, String newsId, String submittedAt, String title, User user, JSONArray choices) {
        this.courseId = courseId;
        this.schoolId = schoolId;
        this.pk = pk;
        this.newsId = newsId;
        this.submittedAt = submittedAt;
        this.title = title;
        this.user = user;

       /* try {
            for (int i = 0; i < choices.length(); i++) {
                this.choices.add((String) choices.get(i));
            }
        } catch (Exception e) {
            Log.e("error parsing choices", e.toString());
        }*/
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(String submittedAt) {
        this.submittedAt = submittedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }
}

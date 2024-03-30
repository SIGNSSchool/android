package com.signs.signsschool.assignments;

public class ModelLAufgabeEinsicht {

    String name, date, title, subject, description, image;
    Integer course_id, message_id, id;

    public ModelLAufgabeEinsicht(String name, String date, String title, Integer course_id, String subject, Integer id, Integer message_id, String description, String image) {
        this.name = name;
        this.date = date;
        this.title = title;
        this.course_id = course_id;
        this.subject = subject;
        this.id = id;
        this.message_id = message_id;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

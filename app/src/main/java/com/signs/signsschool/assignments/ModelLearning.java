package com.signs.signsschool.assignments;

public class ModelLearning {

    String subject, image;
    Integer status;

    public ModelLearning(String subject, String image, Integer status) {
        this.subject = subject;
        this.image = image;
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

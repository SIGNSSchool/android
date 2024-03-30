package com.signs.signsschool.assignments;

public class ModelErstellteAufgaben {

    String name, subject, date;
    Integer message_id;


    public ModelErstellteAufgaben(String name, String subject, String date, Integer message_id) {
        this.name = name;
        this.subject = subject;
        this.date = date;
        this.message_id = message_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }
}

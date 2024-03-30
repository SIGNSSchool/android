package com.signs.signsschool.models;

public class Hours {

    String Date, Day, Teachername, Subject, Room, Cancelled;
    Integer Hour, Teacher, Status;


    public Hours(String Date, String Day, String Teachername, String Subject, String Room, String Cancelled, Integer Hour, Integer Teacher, Integer Status) {

        this.Date = Date;
        this.Day = Day;
        this.Teachername = Teachername;
        this.Subject = Subject;
        this.Room = Room;
        this.Cancelled = Cancelled;
        this.Hour = Hour;
        this.Teacher = Teacher;
        this.Status = Status;

    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getTeachername() {
        return Teachername;
    }

    public void setTeachername(String teachername) {
        Teachername = teachername;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getCancelled() {
        return Cancelled;
    }

    public void setCancelled(String cancelled) {
        Cancelled = cancelled;
    }

    public Integer getHour() {
        return Hour;
    }

    public void setHour(Integer hour) {
        Hour = hour;
    }

    public Integer getTeacher() {
        return Teacher;
    }

    public void setTeacher(Integer teacher) {
        Teacher = teacher;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }
}

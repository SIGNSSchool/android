package com.signs.signsschool.models;

import android.util.Log;

import org.json.JSONObject;

public class Lesson {

    String hour, day, room;

    public Lesson(JSONObject jsonObject) {

        try {
            this.hour = jsonObject.getString("hour");
            this.day = jsonObject.getString("day");
            this.room = jsonObject.getString("room");
        } catch (Exception  e) {
            Log.e("error parsing lesson", e.toString());
        }
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}

package com.signs.signsschool.models;

public class Rooms {

    String room, info;

    public Rooms(String room, String info) {
        this.room = room;
        this.info = info;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

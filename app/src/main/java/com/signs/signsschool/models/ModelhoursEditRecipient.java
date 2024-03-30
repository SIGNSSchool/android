package com.signs.signsschool.models;

public class ModelhoursEditRecipient {


    String name;
    Integer id;


    public ModelhoursEditRecipient(String name, Integer id) {

        this.name = name;
        this.id = id;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}

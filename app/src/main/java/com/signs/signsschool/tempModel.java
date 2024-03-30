package com.signs.signsschool;

import org.json.JSONArray;

import java.util.ArrayList;

public class tempModel {

    String title, date, description;
    Integer id;
    JSONArray options;

    public tempModel(String title, String date, String description, Integer id, JSONArray options) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.id = id;
        this.options = options;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void setOptions(JSONArray options) {
        this.options = options;
    }

    public JSONArray getOptions() {
        return options;
    }
}



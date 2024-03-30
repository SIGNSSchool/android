package com.signs.signsschool.models;

import android.util.Log;

import org.json.JSONObject;

public class User {
    public String firstName;
    public String lastName;
    public String userId;
    public String birthDate;
    public String createdAt;
    public String account;
    public String email;
    public Boolean activated;

    public User(JSONObject user) {

        try {
            this.firstName = user.getString("firstName");
            this.lastName = user.getString("lastName");
            this.createdAt = user.getString("createdAt");
            this.userId = user.getString("userId");
            //this.birthDate = user.getString("birthDate");
            this.account = user.getString("account");
            this.email = user.getString("email");
            this.activated = user.getBoolean("activated");
        } catch (Exception exception) {
            Log.e("Exception", exception.toString());
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }
}

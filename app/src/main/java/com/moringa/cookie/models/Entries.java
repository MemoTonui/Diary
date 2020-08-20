package com.moringa.cookie.models;

import java.util.ArrayList;
import java.util.List;

public class Entries {

    String date;

  /*  public Entries(String date) {
        this.date = date;
    }
    public Entries(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}*/
    String mood;
    String description;
    String pushId;

    public Entries(String date, String mood, String description) {
        this.date = date;
        this.mood = mood;
        this.description = description;
    }

    public Entries() {

    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}

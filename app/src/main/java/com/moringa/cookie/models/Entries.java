package com.moringa.cookie.models;

import java.util.ArrayList;
import java.util.List;

public class Entries {

    String date;
    List<String> mood = new ArrayList<>();

    List<String> description = new ArrayList<>();

    String pushId;

    public Entries(String date, List<String> mood, List<String> description) {
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

    public List<String> getMood() {
        return mood;
    }

    public void setMood(List<String> mood) {
        this.mood = mood;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}

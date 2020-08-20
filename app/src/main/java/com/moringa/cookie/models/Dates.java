package com.moringa.cookie.models;

import com.google.firebase.database.DatabaseReference;

import org.parceler.Parcel;

@Parcel
public class Dates {
    private String mood;
    private String description;

    public Dates(String mood, String description) {
        this.mood = mood;
        this.description = description;
    }
    public Dates(){

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
}

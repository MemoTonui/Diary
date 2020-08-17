package com.moringa.diary.models;


import org.parceler.Parcel;

@Parcel
public class Contents
{
     Quotes[] quotes;

    public Contents(){

    }

    public Contents(Quotes[] quotes) {
        this.quotes = quotes;
    }

    public Quotes[] getQuotes ()
    {
        return quotes;
    }

    public void setQuotes (Quotes[] quotes)
    {
        this.quotes = quotes;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [quotes = "+quotes+"]";
    }
}

package com.moringa.diary.models;


public class Contents
{
    private Quotes[] quotes;

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

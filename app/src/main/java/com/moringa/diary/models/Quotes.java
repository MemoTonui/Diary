package com.moringa.diary.models;

import org.parceler.Parcel;

import java.io.Serializable;

@Parcel
public class Quotes  {
     String date;

     String quote;

    String author;

     String background;

     String length;

     String language;

    String id;

     String category;

     String permalink;

    String title;

     String[] tags;

    public Quotes(String date, String quote, String author, String background, String length, String language, String id, String category, String permalink, String title, String[] tags) {
        this.date = date;
        this.quote = quote;
        this.author = author;
        this.background = background;
        this.length = length;
        this.language = language;
        this.id = id;
        this.category = category;
        this.permalink = permalink;
        this.title = title;
        this.tags = tags;
    }
    public Quotes(){

    }
    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public String getQuote ()
    {
        return quote;
    }

    public void setQuote (String quote)
    {
        this.quote = quote;
    }

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getBackground ()
    {
        return background;
    }

    public void setBackground (String background)
    {
        this.background = background;
    }

    public String getLength ()
    {
        return length;
    }

    public void setLength (String length)
    {
        this.length = length;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getPermalink ()
    {
        return permalink;
    }

    public void setPermalink (String permalink)
    {
        this.permalink = permalink;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String[] getTags ()
    {
        return tags;
    }

    public void setTags (String[] tags)
    {
        this.tags = tags;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [date = "+date+", quote = "+quote+", author = "+author+", background = "+background+", length = "+length+", language = "+language+", id = "+id+", category = "+category+", permalink = "+permalink+", title = "+title+", tags = "+tags+"]";
    }
}


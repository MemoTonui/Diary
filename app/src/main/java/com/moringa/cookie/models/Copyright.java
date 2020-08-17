package com.moringa.cookie.models;

import org.parceler.Parcel;

@Parcel
public class Copyright
{
     String year;

     String url;

    public Copyright(){

    }

    public Copyright(String year, String url) {
        this.year = year;
        this.url = url;
    }

    public String getYear ()
    {
        return year;
    }

    public void setYear (String year)
    {
        this.year = year;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [year = "+year+", url = "+url+"]";
    }
}

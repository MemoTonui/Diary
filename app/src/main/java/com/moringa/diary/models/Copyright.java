package com.moringa.diary.models;

public class Copyright
{
    private String year;

    private String url;

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

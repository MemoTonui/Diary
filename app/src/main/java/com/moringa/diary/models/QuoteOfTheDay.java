package com.moringa.diary.models;

public class QuoteOfTheDay
{
     Copyright copyright;

    String baseurl;

    Contents contents;

    Success success;

    public QuoteOfTheDay(Copyright copyright, String baseurl, Contents contents, Success success) {
        this.copyright = copyright;
        this.baseurl = baseurl;
        this.contents = contents;
        this.success = success;
    }
    public  QuoteOfTheDay(){

    }

    public Copyright getCopyright ()
    {
        return copyright;
    }

    public void setCopyright (Copyright copyright)
    {
        this.copyright = copyright;
    }

    public String getBaseurl ()
    {
        return baseurl;
    }

    public void setBaseurl (String baseurl)
    {
        this.baseurl = baseurl;
    }

    public Contents getContents ()
    {
        return contents;
    }

    public void setContents (Contents contents)
    {
        this.contents = contents;
    }

    public Success getSuccess ()
    {
        return success;
    }

    public void setSuccess (Success success)
    {
        this.success = success;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [copyright = "+copyright+", baseurl = "+baseurl+", contents = "+contents+", success = "+success+"]";
    }
}


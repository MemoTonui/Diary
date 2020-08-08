package com.moringa.diary.models;

public class QuoteOfTheDay
{
    private Copyright copyright;

    private String baseurl;

    private Contents contents;

    private Success success;

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


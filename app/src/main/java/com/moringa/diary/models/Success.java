package com.moringa.diary.models;


public class Success
{
   String total;

    public Success(String total) {
        this.total = total;
    }
    public Success(){

    }

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [total = "+total+"]";
    }
}


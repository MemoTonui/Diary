package com.moringa.diary;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.moringa.diary.models.Contents;

public class MyQuotesArrayAdapter extends ArrayAdapter {
    private Context context;
    private String [] quotes;

    public MyQuotesArrayAdapter(Context context, int resource,String [] quotes) {
        super(context, resource);
        this.context= context;
        this.quotes= quotes;

    }

    @Override
    public Object getItem(int date) {
        String quote = quotes[date];
        return quote;
    }

    @Override
    public int getCount() {
        return quotes.length;
    }
}


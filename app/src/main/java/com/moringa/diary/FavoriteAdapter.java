package com.moringa.diary;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class FavoriteAdapter extends ArrayAdapter {

    private Context mContext;
    private String[] mPlace;
    private String[] mLocation;
    private Typeface mTypeface;


    public FavoriteAdapter(Context mcontext, int resource, String[] mPlace, String[] mLocation, Typeface typeface) {
        super(mcontext, resource);
        this.mContext = mContext;
        this.mPlace = mPlace;
        this.mLocation = mLocation;
        this.mTypeface = typeface;
    }


    @Override
    public Object getItem(int position) {
        String places = mPlace[position];
        String location = mLocation[position];
        return String.format("%s \n    ~  %s", places,location);
    }

    @Override
    public int getCount() {
        return mPlace.length;
    }
}



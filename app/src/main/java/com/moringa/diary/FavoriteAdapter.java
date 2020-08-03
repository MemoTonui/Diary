package com.moringa.diary;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class FavoriteAdapter extends ArrayAdapter {

    private Context mContext;
    private String[] mPlace;
    private String[] mLocation;


    public FavoriteAdapter( Context context, int resource, Context mContext, String[] mPlace, String[] mLocation) {
        super(context, resource);
        this.mContext = mContext;
        this.mPlace = mPlace;
        this.mLocation = mLocation;
    }


    @Override
    public Object getItem(int position) {
        String places = mPlace[position];
        String location = mLocation[position];
        return String.format("%s \nFound in: %s", places,location);
    }

    @Override
    public int getCount() {
        return mPlace.length;
    }
}


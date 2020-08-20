package com.moringa.cookie.UI;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringa.cookie.R;
import com.moringa.cookie.models.Dates;
import com.moringa.cookie.models.Entries;


public class FirebaseEntriesViewHolder extends RecyclerView.ViewHolder {
    TextView mMoodDisplay;
    TextView mDescriptionDisplay;

    View itemView;
 //   Context mContext;
    //private List<Date> entries;


    public FirebaseEntriesViewHolder(View itemView) {
        super(itemView);
      //  mContext = itemView.getContext();
        mMoodDisplay = itemView.findViewById(R.id.moodDisplay);
        mDescriptionDisplay = itemView.findViewById(R.id.descriptionDisplay);

       // this.entries = entries;
    }


    public void bindEntries(Entries entries) {
       mMoodDisplay.setText(entries.getMood());
       mDescriptionDisplay.setText(entries.getDescription());

    }
}
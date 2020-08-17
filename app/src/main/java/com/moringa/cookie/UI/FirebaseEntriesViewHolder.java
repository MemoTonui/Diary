package com.moringa.cookie.UI;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringa.cookie.Constants;
import com.moringa.cookie.R;
import com.moringa.cookie.models.Entries;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FirebaseEntriesViewHolder extends RecyclerView.ViewHolder  {
    View mView;
    Context mContext;


    public FirebaseEntriesViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }
    public void bindEntries(Entries entries) {
        TextView mMoodDisplay = mView.findViewById(R.id.moodDisplay);
        TextView mDescriptionDisplay = mView.findViewById(R.id.descriptionDisplay);
        mMoodDisplay.setText(entries.getMood().get(0).toUpperCase());
       // mMoodDisplay.setText(entries.getMood().toString());
        mDescriptionDisplay.setText(entries.getDescription().get(0));
       // mDescriptionDisplay.setText(entries.getDescription().toString());
    }
              int itemPosition = getLayoutPosition();
             /*   Intent intent = new Intent(mContext, EntriesDisplay.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("restaurants", Parcels.wrap(entries));
                mContext.startActivity(intent);*/
    }

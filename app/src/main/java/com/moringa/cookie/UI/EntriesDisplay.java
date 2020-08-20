package com.moringa.cookie.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringa.cookie.Constants;
import com.moringa.cookie.R;
import com.moringa.cookie.models.Dates;
import com.moringa.cookie.models.Entries;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntriesDisplay extends AppCompatActivity {
    private DatabaseReference entries;
    private FirebaseRecyclerAdapter<Entries, FirebaseEntriesViewHolder> mEntriesViewHolder;


    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries_display);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        System.out.println(uid);

        entries = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ENTRY).child(uid);
       // date = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DATE);
             setUpFirebaseAdapter();
    }

        private void setUpFirebaseAdapter(){
            FirebaseRecyclerOptions<Entries> options = new FirebaseRecyclerOptions.Builder<Entries>()
                            .setQuery(entries,Entries.class)
                            .build();

            //Recycler adapter
            mEntriesViewHolder = new FirebaseRecyclerAdapter<Entries, FirebaseEntriesViewHolder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull FirebaseEntriesViewHolder firebaseEntriesViewHolder, int position, Entries entries) {
                    //firebaseEntriesViewHolder.mMoodDisplay.setText(date.getMood());
                  // firebaseEntriesViewHolder.mDescriptionDisplay.setText(entries.getDescription());
                  firebaseEntriesViewHolder.bindEntries(entries);
                  System.out.println(entries);
                }
                @NonNull
                @Override

                public FirebaseEntriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entries_list, parent, false);
                    return new FirebaseEntriesViewHolder(view);
                }
            };

            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mEntriesViewHolder);
            mRecyclerView.setHasFixedSize(false);
        }

       @Override
        protected void onStart() {
            super.onStart();
            mEntriesViewHolder.startListening();
        }
        @Override
        protected void onStop() {
            super.onStop();
            if(mEntriesViewHolder!= null) {
                mEntriesViewHolder.stopListening();
            }
        }
}

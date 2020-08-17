package com.moringa.cookie.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringa.cookie.Constants;
import com.moringa.cookie.R;
import com.moringa.cookie.models.Entries;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntriesDisplay extends AppCompatActivity {
    private DatabaseReference entries;
    private FirebaseRecyclerAdapter<Entries, FirebaseEntriesViewHolder> mEntriesViewHolder;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entries_list);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        entries = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ENTRY).child(uid);
        entries = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ENTRY);
             setUpFirebaseAdapter();
    }

        private void setUpFirebaseAdapter(){
            FirebaseRecyclerOptions<Entries> options =
                    new FirebaseRecyclerOptions.Builder<Entries>()
                            .setQuery(entries, Entries.class)
                            .build();

            mEntriesViewHolder = new FirebaseRecyclerAdapter<Entries, FirebaseEntriesViewHolder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull FirebaseEntriesViewHolder firebaseEntriesViewHolder, int position, @NonNull Entries entries) {
                   firebaseEntriesViewHolder.bindEntries(entries);
                }

                @NonNull
                @Override
                public FirebaseEntriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entries_list, parent, false);
                   /* final List<Entries> entries = new ArrayList<>();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = user.getUid();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ENTRY).child(uid);
                   ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                entries.add(snapshot.getValue(Entries.class));
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });*/
                    return new FirebaseEntriesViewHolder(view);
                }
            };

            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mEntriesViewHolder);
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

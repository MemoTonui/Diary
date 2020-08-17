package com.moringa.cookie.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoodPage extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference dateForEntry;
    private DatabaseReference moodEntry;
    private DatabaseReference desc;
    private  DatabaseReference entries;
   /* private DatabaseReference mood;
    private DatabaseReference description;*/
    //  private ValueEventListener mSearchedLocationReferenceListener;
   private ValueEventListener dateForEntryReferenceListener;


    @BindView(R.id.textView3) TextView mTextView3;
    @BindView(R.id.mood) TextInputEditText mMood;
    @BindView(R.id.description) TextInputEditText mDescription;
    @BindView(R.id.tick) FloatingActionButton mTick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textview);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        mTextView3.setText(date);
        mTick.setOnClickListener(this);


        String mood = mMood.getText().toString().toUpperCase();
        String description = mDescription.getText().toString();

      /*  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();*/

      //Creates a node entries that stores all entries base on a date..
      entries = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_ENTRY);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        //entries = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ENTRY).child(uid);
      //Creates a node date that stores the mood
      dateForEntry = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ENTRY).child(date);
      moodEntry = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DATE).child(mood);
       // node desc that gives a description of someone's mood
        desc = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MOOD).child(description);

       dateForEntryReferenceListener = dateForEntry.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dateSnapshot:snapshot.getChildren()){
                    String date = dateSnapshot.getValue().toString();
                    System.out.println(date);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    @Override
    public void onClick(View view) {
        if (view == mTick) {
            Intent intent = getIntent();

            //Gets the string from the intent
            String date = intent.getStringExtra("date");
            String mood = mMood.getText().toString().toUpperCase();
            String description = mDescription.getText().toString();

            //Saves the mood and description to Date node in firebase
         //   saveDateToFirebase(date);
            saveMoodToFirebase(mood);
           saveDescriptionToFireBase(description);
           saveToView();


            //Takes the date and displays it in Page1
            Intent intent1 =new Intent(MoodPage.this,Page1.class);
            intent1.putExtra("date",date);
            startActivity(intent1);

        }
    }

    private void saveMoodToFirebase(String mood) {
        dateForEntry.push().setValue(mood);
    }
   private  void saveDescriptionToFireBase(String description){
        moodEntry.push().setValue(description);
    }

    private void saveToView(){
        final ArrayList<Entries> entriesList = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ENTRY).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    entriesList.add(snapshot.getValue(Entries.class));
                }

              //  int itemPosition = getLayoutPosition();
                Intent intent = new Intent(MoodPage.this,EntriesDisplay.class);
                intent.putExtra("mood",mMood.getText().toString().toUpperCase());
                intent.putExtra("description",mDescription.getText().toString());
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dateForEntry.removeEventListener(dateForEntryReferenceListener);
    }
}

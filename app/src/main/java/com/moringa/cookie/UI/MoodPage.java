package com.moringa.cookie.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoodPage extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference date;
    private DatabaseReference mood;
    private DatabaseReference description;
    private  DatabaseReference entries;

    private Entries mEntries2;

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
        String date2 = intent.getStringExtra("date");
        mTextView3.setText(date2);
        mTick.setOnClickListener(this);


        String mood2 = mMood.getText().toString().toUpperCase();
        String description2 = mDescription.getText().toString();

      /*  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();*/

      //Creates a node entries that stores all entries base on a date..
   //   entries = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_ENTRY);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        entries = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ENTRY).child(uid);
      //Creates a node date that stores the mood
        DatabaseReference pushRef = entries.push();
        String pushId = pushRef.getKey();
        date =  FirebaseDatabase.getInstance().getReference(pushId).child(date2);
     // date = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ENTRY).child(date2);

      mood = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DATE).child(mood2);
       // node desc that gives a description of someone's mood
        description = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MOOD).child(description2);

       dateForEntryReferenceListener = date.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dateSnapshot:snapshot.getChildren()){
                    String mood = dateSnapshot.getValue().toString();
                    System.out.println(mood);
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
          //  String date = intent.getStringExtra("date");
            String mood = mMood.getText().toString().toUpperCase();
            String description = mDescription.getText().toString();

            saveMoodToFirebase(mood,description);


            //Takes the date and displays it in Page1
           Intent intent1 =new Intent(MoodPage.this,Page1.class);
          //  intent1.putExtra("date",date);
            startActivity(intent1);
        }
    }

    private void saveMoodToFirebase(String mood,String description) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        entries= FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DATE).child(uid);

        DatabaseReference pushRef = entries.push();
        String pushId = pushRef.getKey();

        pushRef.push().setValue(mood);
        pushRef.push().setValue(description);
    }
   private  void saveDescriptionToFireBase(){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        date.removeEventListener(dateForEntryReferenceListener);
    }
}

package com.moringa.cookie.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.moringa.cookie.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoodPage extends AppCompatActivity implements View.OnClickListener {
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
    }

    @Override
    public void onClick(View view) {
        String mood = mMood.getText().toString().toUpperCase();
        String description = mDescription.getText().toString();
        Intent intent = new Intent(MoodPage.this,Page1.class);
        intent.putExtra("mood",mood);
        intent.putExtra("description",description);
        startActivity(intent);
    }
}

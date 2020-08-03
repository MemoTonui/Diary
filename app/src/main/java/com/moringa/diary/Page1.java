package com.moringa.diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Page1 extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.datepicker) TextView mDatepicker;
    @BindView(R.id.feel) EditText mFeel;
    @BindView(R.id.feelings) TextView mFeelings;
    @BindView(R.id.feelbutton) Button mFeelbutton;
    @BindView(R.id.favorite) Button mFavorite;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);
        ButterKnife.bind(this);

       //Displays the date picker once the activity is created
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "datePicker");

            mFeelbutton.setOnClickListener(this);

            //Goes to Favorite.xml
            mFavorite.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Page1.this,favourite.class);
                    startActivity(intent);

                }


            });
    }

    @Override
    public void onClick(View view) {
        //Add to the how you feel text view
        String feel = mFeel.getText().toString();
        mFeelings.append("\n" +feel);
        mFeel.getText().clear();

        //Move to next page




    }


    /* public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }*/

}

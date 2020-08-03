package com.moringa.diary;

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
    @BindView(R.id.feelings) CardView mFeelings;
    @BindView(R.id.feelbutton) Button mFeelbutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);
        ButterKnife.bind(this);

       //Displays the date picker once the activity is created
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "datePicker");

            mFeelbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String feel = mFeel.getText().toString();
        mFeelings.setContentPadding(3,3,3,3);
        mFeelings.p
        mFeel.getText().clear();
    }
    /* public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }*/

}

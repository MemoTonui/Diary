package com.moringa.diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Page1 extends AppCompatActivity implements View.OnClickListener  {
    @BindView(R.id.favorite) Button mFavorite;
    @BindView(R.id.feelsHead) TextView mFeelsHead;
    @BindView(R.id.calendarView2) CalendarView mCalendarView2;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.cardText) TextView mCardText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);
        ButterKnife.bind(this);

       //Displays the quote  once the activity is created
        FragmentManager fm = getSupportFragmentManager();
        QuoteFragment quoteDialogFragment = new QuoteFragment();
        quoteDialogFragment.show(fm, "Quotes");


        //Gets intent from the Mood page activity


            //Goes to Favorite.xml
            mFavorite.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Page1.this,favourite.class);
                    startActivity(intent);
                }


            });

            //Takes the date and passes it to the Mood page activity

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

            mCalendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                    String date = day + "-"+ (month+1) + "-" + year;
                    Intent intent = new Intent(Page1.this, MoodPage.class);
                    Intent intent1 = new Intent(Page1.this, QuoteFragment.class);
                    intent.putExtra("date",date );
                    intent1.putExtra("date",date);
                    startActivity(intent);
                    startActivity(intent1);
                }
            });
                }
            });
        Intent intent = getIntent();
        intent.getStringExtra("mood");
        intent.getStringExtra("description");
       // mCardText.setText(intent);
    }

    @Override
    public void onClick(View view) {
        //Gets intent from the Mood page activity
       /* Intent intent = getIntent();
        intent.getStringExtra("mood");
        intent.getStringExtra("description");
        mCardText.setText((CharSequence) intent);*/
    }
}








    /* public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }*/



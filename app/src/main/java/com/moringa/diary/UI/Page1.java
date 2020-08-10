package com.moringa.diary.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.moringa.diary.R;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Page1 extends AppCompatActivity implements View.OnClickListener  {
    @BindView(R.id.favorite) Button mFavorite;
    @BindView(R.id.feelsHead) TextView mFeelsHead;
    @BindView(R.id.calendarView2) CalendarView mCalendarView2;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.cardText) TextView mCardText;
    @BindView(R.id.cardText2) TextView mCardText2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);
        ButterKnife.bind(this);

       //Displays the quote  once the activity is created
        final FragmentManager fm = getSupportFragmentManager();
        final QuoteFragment quoteDialogFragment = new QuoteFragment();
        quoteDialogFragment.show(fm, "Quotes");
        //Gets intent from the Mood page activity


            //Goes to Favorite.xml
            mFavorite.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Page1.this, favourite.class);
                    startActivity(intent);
                }


            });

            //Takes the date and passes it to the Mood page activity



            mCalendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, final int year, final int month, final int day) {
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                   // Passes the date to the Mood Page
                    String date = day + "-"+ (month+1) + "-" + year;
                    Intent intent = new Intent(Page1.this, MoodPage.class);
                    intent.putExtra("date",date );
                    startActivity(intent);

                }
            });
                }
            });

        //Gets intent from MoodPage and sets it to mCardText
        Intent intent = getIntent();
        String mood = intent.getStringExtra("mood");
        String desc = intent.getStringExtra("description");
        mCardText.setText(mood);
        mCardText2.setText(desc);
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



package com.moringa.cookie.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.moringa.cookie.R;

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
        Intent intent1 = getIntent();
        String date = intent1.getStringExtra("date");

            //Goes to Favorite.xml
            mFavorite.setOnClickListener(this);
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
        if (view == mFavorite){
            Intent intent = new Intent(Page1.this, favourite.class);
            startActivity(intent);
        }
        //Gets intent from the Mood page activity
       /* Intent intent = getIntent();
        intent.getStringExtra("mood");
        intent.getStringExtra("description");
        mCardText.setText((CharSequence) intent);*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Page1.this,Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}



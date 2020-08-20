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
import com.google.firebase.auth.FirebaseUser;
import com.moringa.cookie.R;
import com.moringa.cookie.models.Entries;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Page1 extends AppCompatActivity implements View.OnClickListener  {
    @BindView(R.id.favorite) Button mFavorite;
    @BindView(R.id.feelsHead) TextView mFeelsHead;
    @BindView(R.id.calendarView2) CalendarView mCalendarView2;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.cardText) TextView mCardText;
    @BindView(R.id.cardText2) TextView mCardText2;
    @BindView(R.id.view) Button mView;
    @BindView(R.id.name) TextView mName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);
        ButterKnife.bind(this);

        //Displays the quote  once the activity is created
        final FragmentManager fm = getSupportFragmentManager();
        final QuoteFragment quoteDialogFragment = new QuoteFragment();
        quoteDialogFragment.show(fm, "Quotes");


        FirebaseUser myUser = FirebaseAuth.getInstance().getCurrentUser();
        String userName = myUser.getDisplayName();
        mName.setText("Hi " + userName + " Please pick a date to continue :)");

        //Gets intent from the Mood page activity
        Intent intent1 = getIntent();
        String date = intent1.getStringExtra("date");

        //Goes to Favorite.xml
        mFavorite.setOnClickListener(this);

        //Goes to entries
        mView.setOnClickListener(this);
        //Takes the date and passes it to the Mood page activity

        mCalendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, final int year, final int month, final int day) {
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Passes the date to the Mood Page
                        String date = day + "-" + (month + 1) + "-" + year;
                        Intent intent = new Intent(Page1.this, MoodPage.class);
                        intent.putExtra("date", date);
                        startActivity(intent);

                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == mFavorite){
            Intent intent = new Intent(Page1.this, favourite.class);
            startActivity(intent);
        }
        if (view == mView){
            Intent intent = new Intent(Page1.this,EntriesDisplay.class);
            startActivity(intent);
        }

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



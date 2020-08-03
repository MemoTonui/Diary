package com.moringa.diary;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;

public class favourite extends AppCompatActivity {
    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView) ListView mListView;

    private String[] places = new String[] {"Roasters", "Junction Mall", "Green House", "Diani Beach", "The Carnivore", "Olepolos", "The Hub", "Finix", " Nairobi Safari Walk", "Village Market", "Paradise Lost" };
    private String[] location = new String[] {"Thika Road", "Ngong Road", "Adams", "Mombasa", "Langata Road", "Kiserian", "Karen", "Argwings Kodhek Road", "Nairobi", "Limuru Road,Gigiri", "Kiambu" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
}

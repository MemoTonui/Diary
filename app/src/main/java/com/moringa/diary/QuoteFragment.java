package com.moringa.diary;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.moringa.diary.models.Contents;
import com.moringa.diary.models.QuoteOfTheDay;
import com.moringa.diary.models.Quotes;
import com.moringa.diary.network.QuoteClient;
import com.moringa.diary.network.QuoteInterface;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;

public class QuoteFragment extends DialogFragment {
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.quoteContent) TextView mQuoteContent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.quote_fragment, container, false);
        //Cancel button for the dialog
        Button cancelButton = (Button) rootView.findViewById(R.id.cancelButton);
        TextView mQuoteContent = rootView.findViewById(R.id.quoteContent);
        TextView mErrorTextView = rootView.findViewById(R.id.errorTextView);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });


        //Gets date from Page1
        Intent intent1 = getActivity().getIntent();
        String date = intent1.getStringExtra("date");

        //Making a request to the quotes api..and passing the date from the calendar to get the quote of the day
        QuoteInterface client = QuoteClient.QuoteOfTheDay.getClient();
        Call<QuoteOfTheDay> call = client.getQuote(date, "date");

//Method occurs on a different thread
        call.enqueue(new Callback<QuoteOfTheDay>() {
            @Override
            public void onResponse(Call<QuoteOfTheDay> call, Response<QuoteOfTheDay> response) {

                if (response.isSuccessful()) {
                    List<Quotes> quotesList = (List<Quotes>) response.body().getContents();
                    String[] quotes = new String[quotesList.size()];
                    String[] author = new String[quotesList.size()];
                    String[] date = new String[quotesList.size()];

                    for (int i = 0; i < quotes.length; i++) {
                        quotes[i] = quotesList.get(i).getQuote();
                        author[i] = quotesList.get(i).getAuthor();
                        date[i] = quotesList.get(i).getDate();
                        // mQuoteContent.append(quotes);
                        showQuote();
                    }
                } else {
                    showUnsuccessfulMessage();
                }

            }

            @Override
            public void onFailure(Call<QuoteOfTheDay> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                showFailureMessage();
            }

        });


        return rootView;

    }

        private void showFailureMessage() {
            mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
            mErrorTextView.setVisibility(View.VISIBLE);
        }

        private void showUnsuccessfulMessage() {
            mErrorTextView.setText("Something went wrong. Please try again later");
            mErrorTextView.setVisibility(View.VISIBLE);
        }


    private void showQuote(){
        mQuoteContent.setText("Here is the quote");
    }

}


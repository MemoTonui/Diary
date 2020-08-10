package com.moringa.diary.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.moringa.diary.R;
import com.moringa.diary.models.QuoteOfTheDay;
import com.moringa.diary.models.Quotes;
import com.moringa.diary.network.QuoteClient;
import com.moringa.diary.network.QuoteInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class QuoteFragment extends DialogFragment {

    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.quoteContent) TextView mQuoteContent;


    //Empty public constructor is required
    public QuoteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.quote_fragment, container, false);


        //Cancel button for the dialog
        Button cancelButton = (Button) rootView.findViewById(R.id.cancelButton);
       final TextView mQuoteContent = rootView.findViewById(R.id.quoteContent);
        TextView mErrorTextView = rootView.findViewById(R.id.errorTextView);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });

        //To pass data to a fragment you need a bundle not an intent
        //Get bundle from Page1
     /*   Bundle bundle =  this.getArguments();
        String date = bundle.getString("date");*/

        Date date1 = new Date();
        String date = date1.toString();

        //Making a request to the quotes api..and passing the date from the calendar to get the quote of the day
        QuoteInterface client = QuoteClient.QuoteOfTheDay.getClient();
        Call<QuoteOfTheDay> call = client.getQuotes("quote", date);

        //Method occurs on a different thread
         call.enqueue(new Callback<QuoteOfTheDay>() {
            @Override
            public void onResponse(Call<QuoteOfTheDay> call, Response<QuoteOfTheDay> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    String quotes = response.body().getContents().getQuotes().toString();
                    mQuoteContent.setText(quotes);


                 /*   String[] quotes = new String[quotesList.size()];
                    String[] author = new String[quotesList.size()];
                    String[] date = new String[quotesList.size()];

                    for (int i = 0; i < quotes.length; i++) {
                        quotes[i] = quotesList.get(i).getQuote();
                        author[i] = quotesList.get(i).getAuthor();
                        date[i] = quotesList.get(i).getDate();
                        List<String[]> list=new ArrayList<>();
                        list.add(quotes);
                        list.add(author);
                        list.add(date);*/
                        // mQuoteContent.append(quotes);
                       // showQuote();
                    }
                }



            @Override
            public void onFailure(Call<QuoteOfTheDay> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
               // showFailureMessage();
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
        mQuoteContent.setText("");
    }

}

//Gets date from Page1

      /*  Intent intent1 = getActivity().getIntent();
        String date = intent1.getStringExtra("date");*/


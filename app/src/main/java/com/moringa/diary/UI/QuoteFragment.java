package com.moringa.diary.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.moringa.diary.R;
import com.moringa.diary.models.QuoteOfTheDay;
import com.moringa.diary.models.Quotes;
import com.moringa.diary.network.QuoteClient;
import com.moringa.diary.network.QuoteInterface;

import java.text.SimpleDateFormat;
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
   // @BindView(R.id.quoteContent) TextView mQuoteContent;

    TextView mQuoteContent;
    TextView mQuoteHead;

    //Empty public constructor is required
    public QuoteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.quote_fragment, container, false);


        //Cancel button for the dialog
        Button cancelButton = (Button) rootView.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });

        //Defining
        mQuoteContent = rootView.findViewById(R.id.quoteContent);
        TextView mErrorTextView = rootView.findViewById(R.id.errorTextView);
        mQuoteContent = rootView.findViewById(R.id.quoteHead);


        //Create a date object to pass the current date
         Date date1 = new Date();
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         final String date = sdf.format(date1);
         //Date date =new SimpleDateFormat("yyyy-MM-dd").parse(date1)
        //String date= new SimpleDateFormat("yyyy-MM-dd").parse(date1).toString();

        //Making a request to the quotes api..and passing the date from the calendar to get the quote of the day
        QuoteInterface client = QuoteClient.QuoteOfTheDay.getClient();
        Call<QuoteOfTheDay> call = client.getQuotes("quote", date);

        //Method occurs on a different thread
         call.enqueue(new Callback<QuoteOfTheDay>() {
            @Override
            public void onResponse(Call<QuoteOfTheDay> call, Response<QuoteOfTheDay> response) {

                if (response.isSuccessful()) {
                    Quotes[] quotes = response.body().getContents().getQuotes();
                    System.out.println("Connection Successful");

                    //Catch Number Format Exception
                    try {
                        mQuoteHead.setText(quotes[(Integer.parseInt(date))].getTitle());
                        mQuoteContent.setText(quotes[Integer.parseInt(date)].getQuote());
                    }
                    catch(NumberFormatException ex){

                        System.out.println("Here!!!!!!!!!!!!!!!!!!!!!!!!!" + ex);
                    }

                } else {
                    showUnsuccessfulMessage();
                }
            }
            @Override
            public void onFailure(Call<QuoteOfTheDay> call, Throwable t) {
                Log.e(TAG, "FAILED!!!!!!!!!!!", t);
                showFailureMessage();
            }
        });


        return rootView;

    }

        private void showFailureMessage() {
          //  mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
          //  mErrorTextView.setVisibility(View.VISIBLE);
        }

        private void showUnsuccessfulMessage() {
         //  mErrorTextView.setText("Something went wrong. Please try again later");
            //mErrorTextView.setVisibility(View.VISIBLE);
        }


  /*  private void showQuote(){
        mQuoteContent.setText();
    }*/

}

//Gets date from Page1

      /*  Intent intent1 = getActivity().getIntent();
        String date = intent1.getStringExtra("date");*/


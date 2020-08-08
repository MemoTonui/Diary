package com.moringa.diary;

import com.moringa.diary.models.QuoteOfTheDay;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuoteInterface {

    @GET("/qod")
    Call<QuoteOfTheDay> getQuote(
            @Query("quote") String quote,
            @Query("author") String author
    );
}

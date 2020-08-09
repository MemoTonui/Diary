package com.moringa.diary.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.moringa.diary.Constants.QUOTES_API_KEY;
import static com.moringa.diary.Constants.QUOTES_BASE_URL;

public class QuoteClient {


    public static class QuoteOfTheDay {

        private static Retrofit retrofit = null;

        public static QuoteInterface getClient() {

            if (retrofit == null) {
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request newRequest  = chain.request().newBuilder()
                                        .addHeader("Authorization", QUOTES_API_KEY)
                                        .build();
                                return chain.proceed(newRequest);
                            }
                        })
                        .build();

                retrofit = new Retrofit.Builder()
                        .baseUrl(QUOTES_BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }


return retrofit.create(QuoteInterface.class);
        }

    }
}

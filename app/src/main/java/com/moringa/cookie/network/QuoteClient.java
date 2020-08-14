package com.moringa.cookie.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.moringa.cookie.Constants.QUOTES_BASE_URL;

public class QuoteClient {


    public static class QuoteOfTheDay {

        private static Retrofit retrofit = null;

        public static QuoteInterface getClient() {

            if (retrofit == null) {
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request newRequest  = chain.request().newBuilder().build();
                                       // .addHeader("Authorization", QUOTES_API_KEY)
                                return chain.proceed(newRequest);
                            }
                        })
                        .build();
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();
                retrofit = new Retrofit.Builder()
                        .baseUrl(QUOTES_BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
            }
          return retrofit.create(QuoteInterface.class);
        }

    }
}

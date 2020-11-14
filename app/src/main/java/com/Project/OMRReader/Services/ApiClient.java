package com.Project.OMRReader.Services;

import com.Project.OMRReader.Utilities.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;
    private static RetrofitInterface apiinterface;

    public static RetrofitInterface getApiinterface() {
        return apiinterface;
    }

    public ApiClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(2, TimeUnit.MINUTES).
                readTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES).callTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        apiinterface = retrofit.create(RetrofitInterface.class);
    }

}

package com.exercise.androidproficiencyexercise;

import com.exercise.androidproficiencyexercise.domain.IView.IHomeUseCase;
import com.squareup.moshi.Moshi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by ashah on 5/22/2018.
 */

public class RestClient {

    private static IHomeUseCase mRestService = null;

    public static IHomeUseCase getClient() {
        if(mRestService == null) {
            final OkHttpClient client = new OkHttpClient();
            Moshi moshi = new Moshi.Builder().build();
            final Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("https://dl.dropboxusercontent.com")
                    .client(client)
                    .build();
            mRestService = retrofit.create(IHomeUseCase.class);
        }
        return mRestService;
    }
}

package com.exercise.androidproficiencyexercise.data.source.remote;

import com.exercise.androidproficiencyexercise.data.ListResponse;

import rx.Observable;

import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Akash on 20-05-2018.
 */

public interface FeedAPIService {
    @GET
    Observable<ListResponse> fetchListDetails(@Url String url);
}

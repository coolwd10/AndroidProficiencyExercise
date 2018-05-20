package com.exercise.androidproficiencyexercise.data.source.remote;

import com.exercise.androidproficiencyexercise.data.ListResponse;
import com.exercise.androidproficiencyexercise.data.source.FeedDataSource;

import rx.Observable;

/**
 * Created by Akash on 20-05-2018.
 */

public class FeedRemoteDataSource implements FeedDataSource {

    private FeedAPIService mAPIService;

    public FeedRemoteDataSource(FeedAPIService service) {
        this.mAPIService = service;
    }

    @Override
    public Observable<ListResponse> fetchListDetails() {
        return mAPIService.fetchListDetails("/s/2iodh4vg0eortkl/facts.json");
    }
}

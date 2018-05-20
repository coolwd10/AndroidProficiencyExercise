package com.exercise.androidproficiencyexercise.data.source;

import com.exercise.androidproficiencyexercise.data.ListResponse;
import com.exercise.androidproficiencyexercise.data.source.remote.FeedRemoteDataSource;

import rx.Observable;

/**
 * Created by Akash on 20-05-2018.
 */

public class FeedDataSourceHelper implements FeedDataSource {

    private FeedRemoteDataSource mFeedRemoteDataSource;

    public FeedDataSourceHelper(FeedRemoteDataSource feedRemoteDataSource) {
        mFeedRemoteDataSource = feedRemoteDataSource;
    }

    @Override
    public Observable<ListResponse> fetchListDetails() {
        return mFeedRemoteDataSource.fetchListDetails();
    }
}

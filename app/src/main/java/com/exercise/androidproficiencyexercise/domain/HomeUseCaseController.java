package com.exercise.androidproficiencyexercise.domain;

import com.exercise.androidproficiencyexercise.data.ListResponse;
import com.exercise.androidproficiencyexercise.data.source.FeedDataSource;
import com.exercise.androidproficiencyexercise.domain.IView.IHomeUseCase;

import rx.Observable;

/**
 * Created by Akash on 20-05-2018.
 */

public class HomeUseCaseController implements IHomeUseCase {

    FeedDataSource mFeedDataSource;

    public HomeUseCaseController(FeedDataSource feedDataSource) {
        mFeedDataSource = feedDataSource;
    }
    @Override
    public Observable<ListResponse> fetchListDetails() {
        return mFeedDataSource.fetchListDetails();
    }
}

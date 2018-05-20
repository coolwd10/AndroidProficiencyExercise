package com.exercise.androidproficiencyexercise.data.source;

import com.exercise.androidproficiencyexercise.data.ListResponse;

import rx.Observable;

/**
 * Created by Akash on 20-05-2018.
 */

public interface FeedDataSource {
    Observable<ListResponse> fetchListDetails();
}

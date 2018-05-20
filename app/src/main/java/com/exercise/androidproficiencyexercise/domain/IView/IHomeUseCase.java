package com.exercise.androidproficiencyexercise.domain.IView;

import com.exercise.androidproficiencyexercise.data.ListResponse;

import rx.Observable;

/**
 * Created by Akash on 20-05-2018.
 */

public interface IHomeUseCase {
    Observable<ListResponse> fetchListDetails();
}

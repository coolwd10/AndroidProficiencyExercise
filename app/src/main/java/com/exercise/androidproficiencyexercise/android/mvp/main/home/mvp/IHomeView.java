package com.exercise.androidproficiencyexercise.android.mvp.main.home.mvp;

import com.exercise.androidproficiencyexercise.android.mvp.core.base.BaseScreen;
import com.exercise.androidproficiencyexercise.data.ListResponse;

import rx.Observable;

/**
 * Created by Akash on 20-05-2018.
 */

public interface IHomeView extends BaseScreen {

    void onErrorOccured(String msg);

    void showListData(ListResponse response);
}

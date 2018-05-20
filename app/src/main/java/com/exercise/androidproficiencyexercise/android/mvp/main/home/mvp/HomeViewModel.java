package com.exercise.androidproficiencyexercise.android.mvp.main.home.mvp;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.exercise.androidproficiencyexercise.data.ListResponse;

/**
 * Created by Akash on 20-05-2018.
 */

public class HomeViewModel extends ViewModel {
    
    @Nullable
    public ListResponse response;
    
    
    @Nullable
    public ListResponse getResponse() {
        return response;
    }
    
    public void setResponse(ListResponse listResponse) {
        this.response = listResponse;
    }
    
    
}

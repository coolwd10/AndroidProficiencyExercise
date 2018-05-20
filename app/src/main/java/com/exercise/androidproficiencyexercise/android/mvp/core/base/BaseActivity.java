package com.exercise.androidproficiencyexercise.android.mvp.core.base;

/**
 * Created by Akash on 20-05-2018.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * This is the Base for all activities. All the activities must extend this.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
    }

    // this will provide the layout id
    public abstract int layoutId();

}

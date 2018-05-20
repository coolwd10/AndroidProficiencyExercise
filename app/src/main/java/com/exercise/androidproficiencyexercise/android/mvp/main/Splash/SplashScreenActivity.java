package com.exercise.androidproficiencyexercise.android.mvp.main.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.exercise.androidproficiencyexercise.ExerciseApp;
import com.exercise.androidproficiencyexercise.R;
import com.exercise.androidproficiencyexercise.android.mvp.core.base.BaseActivity;
import com.exercise.androidproficiencyexercise.android.mvp.main.home.HomeActivity;


/**
 * Created by ashah on 16/01/17.
 */

public class SplashScreenActivity extends BaseActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDI();
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreenActivity.this,
                             HomeActivity.class);
                startActivity(i);
//
//                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public int layoutId() {
        return R.layout.splash_view;
    }

    private void initDI() {
        ((ExerciseApp) getApplicationContext()).getAppComponent().inject(this);
    }

}

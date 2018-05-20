package com.exercise.androidproficiencyexercise;

import android.support.multidex.MultiDexApplication;

import com.exercise.androidproficiencyexercise.android.di.component.AppComponent;
import com.exercise.androidproficiencyexercise.android.di.component.DaggerAppComponent;
import com.exercise.androidproficiencyexercise.android.di.module.ApiModule;
import com.exercise.androidproficiencyexercise.android.di.module.AppModule;
import com.exercise.androidproficiencyexercise.android.di.module.DataModule;
import com.exercise.androidproficiencyexercise.android.utils.Urls;

import timber.log.Timber;

/**
 * Created by Akash shah on 20/05/18.
 */

public class ExerciseApp extends MultiDexApplication {

    private AppComponent appComponent;
    private String baseUrl = Urls.BASEURL;


    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(baseUrl))
                .dataModule(new DataModule())
                .build();
        initTimber();
    }

    private void initTimber() {
        /*

         */
        Timber.plant(new Timber.DebugTree());
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}

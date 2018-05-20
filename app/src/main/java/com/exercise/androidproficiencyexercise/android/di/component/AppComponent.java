package com.exercise.androidproficiencyexercise.android.di.component;

import com.exercise.androidproficiencyexercise.android.di.module.ApiModule;
import com.exercise.androidproficiencyexercise.android.di.module.AppModule;
import com.exercise.androidproficiencyexercise.android.di.module.DataModule;
import com.exercise.androidproficiencyexercise.android.mvp.main.Splash.SplashScreenActivity;
import com.exercise.androidproficiencyexercise.android.mvp.main.home.HomeActivity;
import com.exercise.androidproficiencyexercise.android.mvp.main.home.mvp.HomePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Akash on 20-05-2018.
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class, DataModule.class
})
public interface AppComponent {

    void inject(SplashScreenActivity splashScreenActivity);
    void inject(HomeActivity homeActivity);
    void inject(HomePresenter homePresenter);

}

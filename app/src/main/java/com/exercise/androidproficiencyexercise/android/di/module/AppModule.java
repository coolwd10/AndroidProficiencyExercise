package com.exercise.androidproficiencyexercise.android.di.module;

/**
 * Created by Akash on 20-05-2018.
 */

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This is a Dagger module. We use this to pass in the Context dependency.
 */
@Module
public final class AppModule {

    private final Application mContext;

    public AppModule(Application context) {
        mContext = context;
    }

    @Singleton
    @Provides
    Application provideContext() {
        return mContext;
    }
}

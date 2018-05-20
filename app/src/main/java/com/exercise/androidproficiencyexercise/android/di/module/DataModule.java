package com.exercise.androidproficiencyexercise.android.di.module;

import com.exercise.androidproficiencyexercise.data.source.FeedDataSource;
import com.exercise.androidproficiencyexercise.data.source.FeedDataSourceHelper;
import com.exercise.androidproficiencyexercise.data.source.remote.FeedAPIService;
import com.exercise.androidproficiencyexercise.data.source.remote.FeedRemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Akash on 20-05-2018.
 */

@Module
public class DataModule {

    @Singleton
    @Provides
    public FeedAPIService provideFeedApiService(Retrofit retrofit) {
        return retrofit.create(FeedAPIService.class);
    }

    @Singleton
    @Provides
    public FeedRemoteDataSource provideFeedRemoteService(FeedAPIService feedAPIService) {
        return new FeedRemoteDataSource(feedAPIService);
    }


    @Singleton
    @Provides
    public FeedDataSource provideDataService(FeedRemoteDataSource feedDataSource) {
        return new FeedDataSourceHelper(feedDataSource);
    }

}

package com.exercise.androidproficiencyexercise;

import android.support.test.rule.ActivityTestRule;

import com.exercise.androidproficiencyexercise.android.di.module.ApiModule;
import com.exercise.androidproficiencyexercise.android.mvp.main.home.HomeActivity;
import com.exercise.androidproficiencyexercise.android.mvp.main.home.mvp.HomePresenter;
import com.exercise.androidproficiencyexercise.android.mvp.main.home.mvp.IHomeView;
import com.exercise.androidproficiencyexercise.data.ListResponse;
import com.exercise.androidproficiencyexercise.data.source.remote.FeedAPIService;
import com.exercise.androidproficiencyexercise.data.source.remote.FeedRemoteDataSource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Inject;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Created by Akash on 22-05-2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    HomePresenter presenter;


//    @Mock
//    private FeedAPIService feedAPIService;
//

   // private HomeActivity mIHomeView;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
       // mIHomeView =  new HomeActivity();
       // mIHomeView.setPresenter(presenter);
    }

    @Test
    public void fetchValidDataShouldLoadIntoView() {

        ListResponse listResponse = new ListResponse();
        //listResponse.setTitle("Test Title");

//        when(feedAPIService.fetchListDetails("/s/2iodh4vg0eortkl/facts.json"))
//                .thenReturn(Observable.just(listResponse));

//        mHomePresenter.attachScreen(mIHomeView);
//
//        mHomePresenter.fetchListFromServer();

//        InOrder inOrder = Mockito.inOrder(view);
//        inOrder.verify(view, times(1)).onFetchDataStarted();
//        inOrder.verify(view, times(1)).onFetchDataSuccess(charactersResponseModel);
//        inOrder.verify(view, times(1)).onFetchDataCompleted();
    }
}

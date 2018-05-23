package com.exercise.androidproficiencyexercise.android.mvp.main.home.mvp;

import com.exercise.androidproficiencyexercise.android.mvp.core.base.BasePresenter;
import com.exercise.androidproficiencyexercise.android.utils.NetworkChangeReceiver;
import com.exercise.androidproficiencyexercise.data.ListResponse;
import com.exercise.androidproficiencyexercise.data.source.FeedDataSource;
import com.exercise.androidproficiencyexercise.domain.HomeUseCaseController;
import com.exercise.androidproficiencyexercise.domain.IView.IHomeUseCase;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Akash on 20-05-2018.
 */

public class HomePresenter extends BasePresenter {

    private static String TAG = HomePresenter.class.getSimpleName();

    IHomeView mHomeView;
    IHomeUseCase mIHomeUseCase;

    @Inject
    public HomePresenter(FeedDataSource feedDataSource) {
        mIHomeUseCase = new HomeUseCaseController(feedDataSource);
    }

    public void attachScreen(IHomeView view) {
        super.attachScreen(view);
        this.mHomeView = view;
    }
    public void fetchListFromServer() {
        if(!NetworkChangeReceiver.isConnected())
        {
            mHomeView.showNetworkError();
            return;
        }
        mHomeView.showProgress();
        final Observable<ListResponse> response = mIHomeUseCase.fetchListDetails();
        response.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ListResponse>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                mHomeView.hideProgress();
                mHomeView.onErrorOccured("Error in responce.Please try after some time.");
            }

            @Override
            public void onNext(ListResponse response) {
                if (isScreenAttached()) {
                    mHomeView.hideProgress();
                    mHomeView.showListData(response);
                } else {
                    if(isScreenAttached()){
                        mHomeView.hideProgress();
                        mHomeView.onErrorOccured("Error in responce.Please try after some time.");
                    }
                }
            }
        });
    }
}

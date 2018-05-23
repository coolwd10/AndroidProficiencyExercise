package com.exercise.androidproficiencyexercise.android.mvp.main.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.exercise.androidproficiencyexercise.ExerciseApp;
import com.exercise.androidproficiencyexercise.R;
import com.exercise.androidproficiencyexercise.android.mvp.core.base.BaseActivity;
import com.exercise.androidproficiencyexercise.android.mvp.main.home.mvp.HomePresenter;
import com.exercise.androidproficiencyexercise.android.mvp.main.home.mvp.HomeViewModel;
import com.exercise.androidproficiencyexercise.android.mvp.main.home.mvp.IHomeView;
import com.exercise.androidproficiencyexercise.android.mvp.main.home.mvp.ListAdapter;
import com.exercise.androidproficiencyexercise.android.utils.DialogManager;
import com.exercise.androidproficiencyexercise.android.utils.NetworkChangeReceiver;
import com.exercise.androidproficiencyexercise.android.utils.Utility;
import com.exercise.androidproficiencyexercise.data.ListResponse;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Akash on 20-05-2018.
 */

public class HomeActivity extends BaseActivity implements IHomeView,
        DialogManager.NoticeDialogListener, NetworkChangeReceiver.ConnectivityReceiverListener {

    private static final int DIALOG_ID_NO_NETWORK = 1003;
    private static final int DIALOG_ID_SERVER_ERROR = 1002;
    @Inject
    HomePresenter mHomePresenter;

    static boolean isErrorMsgDisplay;

    private DialogManager mDialogManager;


    @BindView(R.id.tv_header)
    TextView mHeader;

    @BindView(R.id.tv_error)
    TextView mError;

    @BindView(R.id.info_recyclerview)
    RecyclerView mInfoListRecyclerView;

    @BindView(R.id.simpleSwipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private boolean mIsRefreshBtnClicked;

    HomeViewModel homeViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDI();
        ButterKnife.bind(this);
        mDialogManager = new DialogManager(this);
        mHomePresenter.attachScreen(this);
        initView();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                if (NetworkChangeReceiver.isConnected()) {
//                    mIsRefreshBtnClicked = true;
//                    mHomePresenter.fetchListFromServer();
//                } else {
//                    cancelSwipeRefreshLayout();
//                }
                mIsRefreshBtnClicked = true;
                mHomePresenter.fetchListFromServer();
            }
        });
    }


//    private void initView() {
//        homeViewModel = ViewModelProviders.of(this)
//                .get(HomeViewModel.class);
//        if(!NetworkChangeReceiver.isConnected()) {
//            ListResponse listResponse = readOfflineData();
//            if (listResponse != null) {
//                showListData(listResponse);
//            } else {
//                mError.setVisibility(View.VISIBLE);
//            }
//        } else {
//            if (homeViewModel.getResponse() == null) {
//                mHomePresenter.fetchListFromServer();
//            } else {
//                showListData(homeViewModel.getResponse());
//            }
//        }
//    }

    private void initView() {
        homeViewModel = ViewModelProviders.of(this)
                .get(HomeViewModel.class);
        if (homeViewModel.getResponse() == null) {
            mHomePresenter.fetchListFromServer();
        } else {
            showListData(homeViewModel.getResponse());
        }
    }


    @Override
    public int layoutId() {
        return R.layout.home_view;
    }

    @Override
    public void showProgress() {
        if (!mIsRefreshBtnClicked) {
            Utility.showProgressDialog(this, getResources().getString(R.string.please_wait), true);
        }
    }

    @Override
    public void hideProgress() {
        Utility.hideProgressDialog();
    }

    @Override
    public void showNetworkError() {
        showCacheData();
        if (isErrorMsgDisplay) {
            mError.setVisibility(View.VISIBLE);
        } else {
            mDialogManager.
                    showDialog(HomeActivity.this, getResources().getString(R.string.msg_network_error)
                            , DialogManager.DIALOGTYPE.DIALOG, DIALOG_ID_NO_NETWORK,
                            DialogManager.MSGTYPE.INFO, "", getResources().getString(R.string.global_OK_label)
                            , null, null);
        }
    }

    private void showCacheData() {
        ListResponse listResponse = readOfflineData();
        if (listResponse != null) {
            showListData(listResponse);
        }
    }
    @Override
    public void onErrorOccured(String msg) {
        ListResponse listResponse = readOfflineData();
        if (listResponse != null) {
            showListData(listResponse);
        } else {
            mDialogManager.
                    showDialog(HomeActivity.this, msg
                            , DialogManager.DIALOGTYPE.DIALOG, DIALOG_ID_SERVER_ERROR,
                            DialogManager.MSGTYPE.INFO, "", getResources().getString(R.string.global_OK_label)
                            , null, null);
        }
    }

    private ListAdapter adapter;


    @Override
    public void showListData(ListResponse response) {
        mIsRefreshBtnClicked = false;
        isErrorMsgDisplay = false;
        cancelSwipeRefreshLayout();

        mError.setVisibility(View.GONE);

        cacheData(response);

        homeViewModel.setResponse(response);

        mHeader.setText(response.getTitle());
        if (adapter != null) {
            adapter.refreshView(response.getRows());
        } else {
            mInfoListRecyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mInfoListRecyclerView.setLayoutManager(layoutManager);
            adapter = new ListAdapter(this, response.getRows());
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mInfoListRecyclerView.getContext(),
                    layoutManager.getOrientation());
            mInfoListRecyclerView.addItemDecoration(dividerItemDecoration);
            mInfoListRecyclerView.setAdapter(adapter);
        }
    }

    private void cancelSwipeRefreshLayout() {
        // cancel the Visual indication of a refresh
        swipeRefreshLayout.setRefreshing(false);
    }



    private void cacheData(ListResponse response) {
        Utility.writeDataToFile(response, getApplicationContext());
    }

    private ListResponse readOfflineData() {
        return (ListResponse) Utility.readData(getApplicationContext());
    }


    private void initDI() {
        ((ExerciseApp) getApplicationContext()).getAppComponent().inject(this);
    }


    @Override
    public void onDialogPositiveClick(int dialogId) {
        switch (dialogId) {
            case DIALOG_ID_NO_NETWORK: {
                isErrorMsgDisplay = true;
                ListResponse listResponse = readOfflineData();
                if (homeViewModel.getResponse() == null
                        && listResponse == null) {
                    mError.setVisibility(View.VISIBLE);
                }
            }
                break;
            case DIALOG_ID_SERVER_ERROR:
                finish();
                break;
        }
    }

    @Override
    public void onDialogNegativeClick(int dialogId) {

    }

    @Override
    public void onDialogNeutralClick(int dialogId) {

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        hideProgress();
        if (isConnected && adapter == null && mInfoListRecyclerView.getVisibility() == View.GONE) {
            mHomePresenter.fetchListFromServer();
        }
    }
}

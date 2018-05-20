package com.exercise.androidproficiencyexercise.android.mvp.main.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
import butterknife.OnClick;

/**
 * Created by Akash on 20-05-2018.
 */

public class HomeActivity extends BaseActivity implements IHomeView,
        DialogManager.NoticeDialogListener, NetworkChangeReceiver.ConnectivityReceiverListener {

    @Inject
    HomePresenter mHomePresenter;

    boolean isConnected;

    private DialogManager mDialogManager;


    @BindView(R.id.tv_header)
    TextView mHeader;

    @BindView(R.id.tv_error)
    TextView mError;

    @BindView(R.id.btn_refresh)
    Button   mRefreshBtn;

    @BindView(R.id.info_recyclerview)
    RecyclerView mInfoListRecyclerView;

    private boolean mIsRefreshBtnClicked;

    HomeViewModel homeViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDI();
        ButterKnife.bind(this);
        mDialogManager = new DialogManager(this);
        mHomePresenter.attachScreen(this);

        homeViewModel = ViewModelProviders.of(this)
                .get(HomeViewModel.class);
        if (homeViewModel.getResponse() == null) {
            mHomePresenter.fetchListFromServer();
        } else {
            showListData(homeViewModel.getResponse());
        }


//        // register connection status listener
//        ExerciseApp.getInstance().setConnectivityListener(this);
//        // Manually checking internet connection
        checkConnection();
    }


    @Override
    public int layoutId() {
        return R.layout.home_view;
    }

    @Override
    public void showProgress() {
        if(mIsRefreshBtnClicked){
            Utility.showProgressDialog(this, getResources().getString(R.string.refresh_msg), true);
        }else {
            Utility.showProgressDialog(this, getResources().getString(R.string.please_wait), true);
        }
    }

    // Method to manually check connection status
    private void checkConnection() {
        isConnected = NetworkChangeReceiver.isConnected();
    }

    @Override
    public void hideProgress() {
        Utility.hideProgressDialog();
    }

    @Override
    public void showNetworkError() {
        Utility.showNoNetworkOrServerErrorDialog(this);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onErrorOccured(String msg) {
        mDialogManager.
                showDialog(HomeActivity.this, msg
                        , DialogManager.DIALOGTYPE.DIALOG, 1002,
                        DialogManager.MSGTYPE.INFO, "", getResources().getString(R.string.global_OK_label)
                        , null, null);
    }

    private ListAdapter adapter;


    @Override
    public void showListData(ListResponse response) {
        mIsRefreshBtnClicked =  false;
        mError.setVisibility(View.GONE);

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

    @OnClick (R.id.btn_refresh)
    public void onRefreshBtnClicked() {
        mIsRefreshBtnClicked =  true;
        mHomePresenter.fetchListFromServer();
    }

    private void initDI() {
        ((ExerciseApp) getApplicationContext()).getAppComponent().inject(this);
    }


    @Override
    public void onDialogPositiveClick(int dialogId) {
        mError.setVisibility(View.VISIBLE);
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

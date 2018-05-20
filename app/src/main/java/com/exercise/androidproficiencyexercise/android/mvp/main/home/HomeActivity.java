package com.exercise.androidproficiencyexercise.android.mvp.main.home;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.exercise.androidproficiencyexercise.ExerciseApp;
import com.exercise.androidproficiencyexercise.R;
import com.exercise.androidproficiencyexercise.android.mvp.core.base.BaseActivity;
import com.exercise.androidproficiencyexercise.android.mvp.main.home.mvp.HomePresenter;
import com.exercise.androidproficiencyexercise.android.mvp.main.home.mvp.IHomeView;
import com.exercise.androidproficiencyexercise.android.mvp.main.home.mvp.ListAdapter;
import com.exercise.androidproficiencyexercise.android.utils.DialogManager;
import com.exercise.androidproficiencyexercise.android.utils.Utility;
import com.exercise.androidproficiencyexercise.data.ListResponse;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;

/**
 * Created by Akash on 20-05-2018.
 */

public class HomeActivity extends BaseActivity implements IHomeView,
        DialogManager.NoticeDialogListener{

    @Inject
    HomePresenter mHomePresenter;

    private DialogManager mDialogManager;


    @BindView(R.id.tv_header)
    TextView mHeader;

    @BindView(R.id.tv_error)
    TextView mError;

    @BindView(R.id.info_recyclerview)
    RecyclerView mInfoListRecyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDI();
        ButterKnife.bind(this);
        mDialogManager = new DialogManager(this);
        mHomePresenter.attachScreen(this);
        showProgress();
        mHomePresenter.fetchListFromServer();
    }

    @Override
    public int layoutId() {
        return R.layout.home_view;
    }

    @Override
    public void showProgress() {
        Utility.showProgressDialog(this, getResources().getString(R.string.please_wait), true);
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
        mError.setVisibility(View.GONE);
        mHeader.setText(response.getTitle());

        mInfoListRecyclerView.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mInfoListRecyclerView.setLayoutManager(layoutManager);
        adapter = new ListAdapter(this,response.getRows());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mInfoListRecyclerView.getContext(),
                layoutManager.getOrientation());
        mInfoListRecyclerView.addItemDecoration(dividerItemDecoration);
        mInfoListRecyclerView.setAdapter(adapter);
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
}

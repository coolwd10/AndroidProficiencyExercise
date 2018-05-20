package com.exercise.androidproficiencyexercise.android.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;

import com.exercise.androidproficiencyexercise.R;

/**
 * Created by ashah on 16/01/17.
 */

public class Utility {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9999;

    private static ProgressDialog mProgressDialog;
    public static boolean IsConnected = false;


    public static void showProgressDialog(Context activity, String message, boolean cancelable) {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                return;
            }

            mProgressDialog = new ProgressDialog(activity);
            if (message == null) {
                message = activity.getResources().getString(R.string.progress_dialog_message);
            }
            mProgressDialog.setMessage(message);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(cancelable);
            mProgressDialog.setCanceledOnTouchOutside(cancelable);
            mProgressDialog.show();
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isProgressDialogShown() {
        boolean isShown = false;

        if (mProgressDialog != null) {
            isShown = mProgressDialog.isShowing();
        }

        return isShown;
    }

    public static void hideProgressDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showNoNetworkOrServerErrorDialog(Activity activity) {
        final Fragment DFragment;
        DFragment = activity.getFragmentManager().findFragmentByTag("Dialog Fragment");
        if (DFragment != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //DialogViewFragment pf = (DialogViewFragment) DFragment;
                }
            });
        } else {
            DialogViewFragment dialogViewFragment =
                    DialogViewFragment.newInstance();
            FragmentManager fragmentManager = activity.getFragmentManager();
            dialogViewFragment.show(fragmentManager, "Dialog Fragment");
        }
    }
}

package com.exercise.androidproficiencyexercise.android.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;

import com.exercise.androidproficiencyexercise.R;
import com.exercise.androidproficiencyexercise.data.ListResponse;
import com.google.gson.Gson;

/**
 * Created by ashah on 20/05/18.
 */

public class Utility {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9999;
    private static ProgressDialog mProgressDialog;
    public static boolean IsConnected = false;
    public static Gson gson = new Gson();

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

    public static void updateAppNetworkSettings(boolean isConnected) {
        IsConnected = isConnected;
    }

    public static void writeDataToFile(Object inputObject, Context context) {
        String list_json = gson.toJson(inputObject);
        try {
            WriteObjectFile.writeObject(list_json, Constant.RES_FILE_NAME, context);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Object readData(Context context) {
        ListResponse response = null;
        String list_json = (String) WriteObjectFile.readObject(Constant.RES_FILE_NAME,context);
        try {
            response = gson.fromJson(list_json, ListResponse.class);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}

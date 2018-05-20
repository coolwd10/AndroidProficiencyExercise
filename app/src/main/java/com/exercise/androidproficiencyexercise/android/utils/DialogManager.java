package com.exercise.androidproficiencyexercise.android.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.exercise.androidproficiencyexercise.R;

import javax.inject.Inject;

import static android.support.design.widget.Snackbar.LENGTH_LONG;
import static android.text.TextUtils.isEmpty;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Akash on 20-05-2018.
 */

public class DialogManager {

    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;


    private DialogManager() {
    }

    ;

    /*The below constructor is a way to enforce programmers to implement the Listener hence the callback
     * of the dialog action can be passed back.
     */
    public DialogManager(NoticeDialogListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("NoticeDialogListener object can't be null");
        } else {
            this.mListener = listener;
        }


    }

    /**
     * The below method shows a dialog based on the type passed or shows an snackbar for common
     * types of error,info,warning
     *
     * @param activity        The activity object from the activity which needs to show the dialog
     * @param msg             The text to be displayed on the dialog
     * @param type            The type of layout for the message either "dialog" or "snackbar"
     * @param id              The id for the dialog which is returned back on positive or negative feedback back to the activity
     *                        which initiated the dialog
     * @param messageType     1-info,2-error,3-warning || NA in case of snackbar
     * @param title           The title for the dialog || NA in case of snackbar
     * @param positiveBtnName The button name for a positive click
     * @param negativeBtnName The button name for a negative click || NA in case of snackbar
     * @param neutralBtnName  The button name for a neutral click || NA in case of snackbar
     */
    public void showDialog(Activity activity, String msg, Enum type, int id, MSGTYPE messageType, String title, String positiveBtnName, String negativeBtnName, String neutralBtnName) {
        showAlertDialog(activity, msg, type, id, messageType, title, positiveBtnName, negativeBtnName, neutralBtnName, true);
    }

    public void showAlertDialog(Activity activity, String msg, Enum type,
                                int id, MSGTYPE messageType, String title,
                                String positiveBtnName, String negativeBtnName, String neutralBtnName,
                                boolean isCancellable) {
        showAlertDialog(activity, msg, type,
                id, messageType, title,
                null, 0,
                positiveBtnName, negativeBtnName, neutralBtnName, 0,
                isCancellable);
    }

    /**
     * @param activity                activity
     * @param msg                     message to show in the dialog
     * @param type                    dialog type {@link DIALOGTYPE}
     * @param id                      id to identify the dialog
     * @param messageType             type of the message {@link MSGTYPE}
     * @param title                   title of the dialog
     * @param subTitle                pass null or empty string for no subTitle
     * @param warningImageId          pass 0 for no image
     * @param positiveBtnName         text of the positive button
     * @param negativeBtnName         text of the negative button
     * @param neutralBtnName          text of the neutral button
     * @param ctButtonBackgroundColor pass 0 for no color
     * @param isCancellable           pass true if can be cancelled else false
     */
    public void showAlertDialog(@NonNull Activity activity, String msg, Enum type,
                                final int id, MSGTYPE messageType, String title,
                                String subTitle, @DrawableRes int warningImageId,
                                String positiveBtnName, String negativeBtnName, String neutralBtnName, @ColorRes int ctButtonBackgroundColor,
                                boolean isCancellable) {
        if (activity != null) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(isCancellable);
            dialog.setCanceledOnTouchOutside(isCancellable);
            dialog.setContentView(R.layout.dialog_generic);

            TextView titleTextView = findById(dialog, R.id.dialog_title);
            TextView subtitleTextView = findById(dialog, R.id.dialog_subtitle);
            TextView messageTextView = findById(dialog, R.id.dialog_msg);
            TextView dialogButtonPositive = findById(dialog, R.id.btn_positive);
            TextView dialogButtonCancel = findById(dialog, R.id.btn_negative);
            TextView dialogButtonNeutral = findById(dialog, R.id.btn_neutral);
            ImageView imgWarning = findById(dialog, R.id.img_warning);

            if (warningImageId == 0) {
                imgWarning.setVisibility(GONE);
            } else {
                imgWarning.setVisibility(VISIBLE);
                imgWarning.setImageResource(warningImageId);
            }
            if (isEmpty(title) && warningImageId != 0) {
                titleTextView.setVisibility(GONE);
            } else if (isEmpty(title)) {
                titleTextView.setVisibility(VISIBLE);
                titleTextView.setText(R.string.app_name);
            } else {
                titleTextView.setVisibility(VISIBLE);
                titleTextView.setText(title);
            }

            showHideSetView(subTitle, subtitleTextView);
            messageTextView.setText(msg);

            if (ctButtonBackgroundColor != 0) {
                dialogButtonPositive.setBackgroundResource(ctButtonBackgroundColor);
                dialogButtonNeutral.setBackgroundResource(ctButtonBackgroundColor);
                dialogButtonCancel.setBackgroundResource(ctButtonBackgroundColor);

                int textColor = ContextCompat.getColor(activity, R.color.white);
                dialogButtonPositive.setTextColor(textColor);
                dialogButtonNeutral.setTextColor(textColor);
                dialogButtonCancel.setTextColor(textColor);
            }

            if (!(isEmpty(positiveBtnName) && isEmpty(negativeBtnName) && isEmpty(neutralBtnName))) {
                showHideSetView(positiveBtnName, dialogButtonPositive);
                showHideSetView(negativeBtnName, dialogButtonCancel);
                showHideSetView(neutralBtnName, dialogButtonNeutral);
            } else {
                dialogButtonCancel.setVisibility(GONE);
                dialogButtonNeutral.setVisibility(GONE);
            }
            dialogButtonPositive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    mListener.onDialogPositiveClick(id);
                }
            });
            dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    mListener.onDialogNegativeClick(id);
                }
            });
            dialogButtonNeutral.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    mListener.onDialogNeutralClick(id);
                }
            });
            dialog.show();
        }
    }

    private void showHideSetView(String btnName, TextView view) {
        if (!isEmpty(btnName)) {
            view.setVisibility(VISIBLE);
            view.setText(btnName);
        } else {
            view.setVisibility(GONE);
        }
    }

    @SuppressWarnings({"unchecked", "UnusedDeclaration"}) // Checked by runtime cast. Public API.
    private <T extends View> T findById(Dialog dialog, int dialog_title) {
        return ((T) dialog.findViewById(dialog_title));
    }

    public enum DIALOGTYPE {
        DIALOG
    }

    public enum MSGTYPE {
        INFO, ERROR, WARNING
    }

    /* The activity that creates an instance of this dialog fragment must
        * implement this interface in order to receive event callbacks.
        * Each method passes the DialogViewFragment in case the host needs to query it.
    */
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(int dialogId);

        public void onDialogNegativeClick(int dialogId);

        public void onDialogNeutralClick(int dialogId);
    }
}

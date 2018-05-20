package com.exercise.androidproficiencyexercise.android.utils;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exercise.androidproficiencyexercise.R;

/**
 * Created by Akash on 20-05-2018.
 */

public class DialogViewFragment extends DialogFragment {

    public static final int REQUEST_CODE_ASK_PERMISSIONS = 111;

    public static DialogViewFragment newInstance() {
        DialogViewFragment fragment = new DialogViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView dialogButtonCancel = (TextView) view.findViewById(R.id.btn_negative);
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dismiss();
            }
        });
        return view;
    }
}

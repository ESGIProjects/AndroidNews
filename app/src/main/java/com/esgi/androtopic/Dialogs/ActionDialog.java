package com.esgi.androtopic.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;

import com.esgi.androtopic.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kevin on 05/07/2017.
 */

public class ActionDialog extends Dialog {

    Activity activity;
    @OnClick(R.id.update) void update(){

    }
    @OnClick(R.id.delete) void delete(){

    }

    public ActionDialog(Activity a) {
        super(a);
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_action);
        ButterKnife.bind(this);
    }
}
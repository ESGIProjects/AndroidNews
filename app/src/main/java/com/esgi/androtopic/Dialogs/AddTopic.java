package com.esgi.androtopic.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Api.ServiceResult;
import com.esgi.androtopic.Data.Api.Services.CallService;
import com.esgi.androtopic.Data.Model.PostTopic;
import com.esgi.androtopic.R;
import com.esgi.androtopic.Tools.CheckRules;
import com.esgi.androtopic.Tools.GetDate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kevin on 02/07/2017.
 */

public class AddTopic extends Dialog {

    Activity activity;
    ProgressDialog progressDialogTopics;
    @BindView(R.id.topicsCustomDialogTitle) EditText title;
    @BindView(R.id.topicsCustomDialogContent) EditText content;
    @BindView(R.id.topicsCustomDialogLabelDate) TextView labelDate;
    @BindView(R.id.topicsCustomDialogDate) CheckBox checkDate;
    @OnClick(R.id.topicsCustomDialogCreateTopics) void createTopic(){
        progressDialogTopics = new ProgressDialog(getContext(),R.style.AppCompatAlertDialogStyle);
        progressDialogTopics.setMessage("Wait...");
        progressDialogTopics.show();
        if((!CheckRules.isNull(title.getText().toString())) && (!CheckRules.isNull(content.getText().toString()))){
            PostTopic pt;
            if(checkDate.isChecked()){
                pt = new PostTopic(title.getText().toString(), content.getText().toString(), GetDate.getDate());
            }
            else{
                pt = new PostTopic(title.getText().toString(), content.getText().toString());
            }
            CallService.getInstance().postTopic(CallService.getToken(getContext()) ,pt, new IServiceResultListener<Void>() {
                @Override
                public void onResult(ServiceResult<Void> sr) {
                    progressDialogTopics.dismiss();
                    if(sr.getResponseCode() == 201){
                        Toast.makeText(getContext(),"The topic is created !", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), "No response from server !", Toast.LENGTH_SHORT).show();
                    }
                    dismiss();
                }
            });
        }
    }

    public AddTopic(Activity a) {
        super(a);
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_topic);
        ButterKnife.bind(this);
    }
}

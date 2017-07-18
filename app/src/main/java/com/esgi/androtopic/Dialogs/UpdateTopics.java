package com.esgi.androtopic.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.telecom.Call;
import android.util.Log;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.esgi.androtopic.Activities.MainActivity;
import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Api.ServiceResult;
import com.esgi.androtopic.Data.Api.Services.CallService;
import com.esgi.androtopic.Data.Model.Topics;
import com.esgi.androtopic.Data.Model.PostTopic;
import com.esgi.androtopic.Data.Model.Topics;
import com.esgi.androtopic.R;
import com.esgi.androtopic.Tools.CheckRules;
import com.esgi.androtopic.Tools.GetDate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Remi on 10/07/2017.
 */

public class UpdateTopics extends Dialog {
    Activity activity;
    Topics itemTopics;
    int position;
    ProgressDialog pd;
    ActionDialog act;

    @BindView(R.id.customDialogTopicsUpdateTitle) EditText title;
    @BindView(R.id.customDialogTopicsUpdateContent) EditText content;
    @BindView(R.id.customDialogTopicsUpdateLabelDate) TextView labelDate;
    @BindView(R.id.customDialogTopicsUpdateDate) CheckBox checkDate;

    @OnClick(R.id.updateTopics) void updateTopics(){
        pd = new ProgressDialog(getContext(), R.style.AppCompatAlertDialogStyle);
        pd.setMessage("Wait...");
        pd.show();
        if ((!CheckRules.isNull(title.getText().toString())) && (!CheckRules.isNull(content.getText().toString()))) {
            PostTopic pn;
            if (checkDate.isChecked()) {
                pn = new PostTopic(title.getText().toString(), content.getText().toString(), GetDate.getDate());
                itemTopics.setDate(GetDate.getDate());
            } else {
                pn = new PostTopic(title.getText().toString(), content.getText().toString());
            }
            itemTopics.setContent(content.getText().toString());
            itemTopics.setTitle(title.getText().toString());
            CallService.getInstance().putTopic(CallService.getToken(getContext()),pn, itemTopics.get_id(), new IServiceResultListener<Void>() {
                @Override
                public void onResult(ServiceResult<Void> sr) {
                    Log.i("RESPONSE : ", "" +sr.getResponseCode());
                    Log.i("AUTHOR : ", "" + itemTopics.getAuthor());
                    Log.i("ID : ", "" +CallService.getID(getContext()));
                    Log.i("ITEM ID : ", "" + itemTopics.get_id());
                    if (sr.getResponseCode() == 204 && itemTopics.getAuthor().equals(CallService.getID(getContext()))) {
                        Toast.makeText(getContext(), "The Topic is update !", Toast.LENGTH_SHORT).show();
                        MainActivity aa = (MainActivity) activity;
                        aa.tf.refresh(position, false);
                    }
                    else if(itemTopics.getAuthor() != CallService.getID(getContext())){
                        Toast.makeText(getContext(), "You can't change a topic which is not yours !", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), "No response from server !", Toast.LENGTH_SHORT).show();
                    }
                    pd.dismiss();
                    dismiss();
                    act.dismiss();
                }
            });
        }
        else{
            Toast.makeText(getContext(),"Title and Content are required ! ", Toast.LENGTH_SHORT).show();
            pd.dismiss();
        }
    }

    public UpdateTopics(Activity a, Topics item, int position, ActionDialog actionDialog) {
        super(a);
        this.activity = a;
        this.position = position;
        this.itemTopics = item;
        this.act = actionDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_update_topics);
        ButterKnife.bind(this);
    }

    @Override
    protected  void onStart(){
        title.setText(itemTopics.getTitle());
        content.setText(itemTopics.getContent());
        labelDate.setText("Insert new date ?");
    }
}

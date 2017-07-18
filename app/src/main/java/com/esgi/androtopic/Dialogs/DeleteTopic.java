package com.esgi.androtopic.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.esgi.androtopic.Activities.MainActivity;
import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Api.ServiceResult;
import com.esgi.androtopic.Data.Api.Services.CallService;
import com.esgi.androtopic.Data.Model.News;
import com.esgi.androtopic.Data.Model.PostNews;
import com.esgi.androtopic.Data.Model.Topics;
import com.esgi.androtopic.Fragments.NewsFragment;
import com.esgi.androtopic.R;
import com.esgi.androtopic.Tools.CheckRules;
import com.esgi.androtopic.Tools.GetDate;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Remi on 10/07/2017.
 */

public class DeleteTopic extends Dialog {
    Activity activity;
    Topics itemTopic;
    int position;
    ProgressDialog pd;
    ActionDialog act;

    @OnClick(R.id.deleteTopic) void deleteTopic(){
        pd = new ProgressDialog(getContext(), R.style.AppCompatAlertDialogStyle);
        pd.setMessage("Wait...");
        pd.show();
        CallService.getInstance().delTopic(CallService.getToken(getContext()), itemTopic.get_id(), new IServiceResultListener<Void>() {
            @Override
            public void onResult(ServiceResult<Void> sr) {
                Log.i("RESPONSE : ", "" +sr.getResponseCode());
                Log.i("AUTHOR : ", "" + itemTopic.getAuthor());
                Log.i("ID : ", "" +CallService.getID(getContext()));
                Log.i("ITEM ID : ", "" + itemTopic.get_id());
                if (sr.getResponseCode() == 204 && itemTopic.getAuthor().equals(CallService.getID(getContext()))) {
                    Toast.makeText(getContext(), "The topic is deleted !", Toast.LENGTH_SHORT).show();
                    MainActivity aa = (MainActivity) activity;
                    aa.tf.refresh(position,true);
                }
                else if(itemTopic.getAuthor() != CallService.getID(getContext())){
                    Toast.makeText(getContext(), "You can't delete a topic which is not yours !", Toast.LENGTH_SHORT).show();
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
    @OnClick(R.id.returnTopic) void returnTopic(){
        dismiss();
    }

    public DeleteTopic(Activity a, Topics item, int position, ActionDialog actionDialog) {
        super(a);
        this.activity = a;
        this.position = position;
        this.itemTopic = item;
        this.act = actionDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_delete_topic);
        ButterKnife.bind(this);
    }
}

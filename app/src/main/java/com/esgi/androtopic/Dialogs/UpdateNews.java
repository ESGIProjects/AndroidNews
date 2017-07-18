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
import com.esgi.androtopic.Data.Model.News;
import com.esgi.androtopic.Data.Model.PostNews;
import com.esgi.androtopic.R;
import com.esgi.androtopic.Tools.CheckRules;
import com.esgi.androtopic.Tools.GetDate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Remi on 10/07/2017.
 */

public class UpdateNews extends Dialog {
    Activity activity;
    News itemNews;
    int position;
    ProgressDialog pd;
    ActionDialog act;

    @BindView(R.id.customDialogNewsUpdateTitle) EditText title;
    @BindView(R.id.customDialogNewsUpdateContent) EditText content;
    @BindView(R.id.customDialogNewsUpdateLabelDate) TextView labelDate;
    @BindView(R.id.customDialogNewsUpdateDate) CheckBox checkDate;

    @OnClick(R.id.updateNews) void updateNews(){
        pd = new ProgressDialog(getContext(), R.style.AppCompatAlertDialogStyle);
        pd.setMessage("Wait...");
        pd.show();
        if ((!CheckRules.isNull(title.getText().toString())) && (!CheckRules.isNull(content.getText().toString()))) {
            PostNews pn;
            if (checkDate.isChecked()) {
                pn = new PostNews(title.getText().toString(), content.getText().toString(), GetDate.getDate());
                itemNews.setDate(GetDate.getDate());
            } else {
                pn = new PostNews(title.getText().toString(), content.getText().toString());
            }
            itemNews.setContent(content.getText().toString());
            itemNews.setTitle(title.getText().toString());
            CallService.getInstance().putNews(CallService.getToken(getContext()),pn, itemNews.get_id(), new IServiceResultListener<Void>() {
                @Override
                public void onResult(ServiceResult<Void> sr) {
                    Log.i("RESPONSE : ", "" +sr.getResponseCode());
                    Log.i("AUTHOR : ", "" + itemNews.getAuthor());
                    Log.i("ID : ", "" +CallService.getID(getContext()));
                    Log.i("ITEM ID : ", "" + itemNews.get_id());
                    if (sr.getResponseCode() == 204 && itemNews.getAuthor().equals(CallService.getID(getContext()))) {
                        Toast.makeText(getContext(), "The news is update !", Toast.LENGTH_SHORT).show();
                        MainActivity aa = (MainActivity) activity;
                        aa.nf.refresh(position, false);
                    }
                    else if(itemNews.getAuthor() != CallService.getID(getContext())){
                        Toast.makeText(getContext(), "You can't change a news which is not yours !", Toast.LENGTH_SHORT).show();
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

    public UpdateNews(Activity a, News item, int position, ActionDialog actionDialog) {
        super(a);
        this.activity = a;
        this.position = position;
        this.itemNews = item;
        this.act = actionDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_update_news);
        ButterKnife.bind(this);
    }

    @Override
    protected  void onStart(){
        title.setText(itemNews.getTitle());
        content.setText(itemNews.getContent());
        labelDate.setText("Insert new date ?");
    }
}

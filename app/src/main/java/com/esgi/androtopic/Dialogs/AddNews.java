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
import com.esgi.androtopic.Data.Model.PostNews;
import com.esgi.androtopic.R;
import com.esgi.androtopic.Tools.CheckRules;
import com.esgi.androtopic.Tools.GetDate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kevin on 02/07/2017.
 */

public class AddNews extends Dialog {

    Activity activity;
    ProgressDialog pd;
    @BindView(R.id.title) EditText title;
    @BindView(R.id.content) EditText content;
    @BindView(R.id.labelDate) TextView labelDate;
    @BindView(R.id.date) CheckBox checkDate;
    @OnClick(R.id.createNews) void createNews(){
        pd = new ProgressDialog(getContext(),R.style.AppCompatAlertDialogStyle);
        pd.setMessage("Wait...");
        pd.show();
        if((!CheckRules.isNull(title.getText().toString())) && (!CheckRules.isNull(content.getText().toString()))){
            PostNews pn;
            if(checkDate.isChecked()){
                pn = new PostNews(title.getText().toString(), content.getText().toString(), GetDate.getDate());
            }
            else{
                pn = new PostNews(title.getText().toString(), content.getText().toString());
            }
            CallService.getInstance().postNews(CallService.getToken(getContext()) ,pn, new IServiceResultListener<Void>() {
                @Override
                public void onResult(ServiceResult<Void> sr) {
                    pd.dismiss();
                    if(sr.getResponseCode() == 201){
                        Toast.makeText(getContext(),"The news is created !", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), "No response from server !", Toast.LENGTH_SHORT).show();
                    }
                    dismiss();
                }
            });
        }
    }

    public AddNews(Activity a) {
        super(a);
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_news);
        ButterKnife.bind(this);
    }
}
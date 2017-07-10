package com.esgi.androtopic.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    @BindView(R.id.customDialogNewsUpdateTitle) EditText title;
    @BindView(R.id.customDialogNewsUpdateContent) EditText content;
    @BindView(R.id.customDialogNewsUpdateLabelDate)
    TextView labelDate;
    @BindView(R.id.customDialogNewsUpdateDate)
    CheckBox checkDate;

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
            String test = itemNews.get_id();
            //TODO pourquoi ce putain d'id de merde est comme ça ?? test = 5952725bc5606700114a19b8
            CallService.getInstance().putNews(pn, this.position, new IServiceResultListener<News>() {
                @Override
                public void onResult(ServiceResult<News> sr) {
                    pd.dismiss();

                    if (sr.getResponseCode() == 200 || sr.getResponseCode() == 204) {
                        Toast.makeText(getContext(), "The news is update !", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "No response from server !", Toast.LENGTH_SHORT).show();
                    }
                    dismiss();
                }
            });
        }
    }

    public UpdateNews(Activity a, News item, int position ) {
        super(a);
        this.activity = a;
        this.position = position;
        this.itemNews = item;
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
        labelDate.setText(itemNews.getDate());
    }
}

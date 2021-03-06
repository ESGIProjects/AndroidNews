package com.esgi.androtopic.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.esgi.androtopic.Activities.MainActivity;
import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Api.ServiceResult;
import com.esgi.androtopic.Data.Api.Services.CallService;
import com.esgi.androtopic.Data.Model.News;
import com.esgi.androtopic.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Remi on 10/07/2017.
 */

public class DeleteNews extends Dialog {
    Activity activity;
    News itemNews;
    int position;
    ProgressDialog pd;
    ActionDialog act;

    @OnClick(R.id.deleteNews) void deleteNews(){
        pd = new ProgressDialog(getContext(), R.style.AppCompatAlertDialogStyle);
        pd.setMessage("Wait...");
        pd.show();
        CallService.getInstance().delNews(CallService.getToken(getContext()), itemNews.get_id(), new IServiceResultListener<Void>() {
            @Override
            public void onResult(ServiceResult<Void> sr) {
                Log.i("RESPONSE : ", "" +sr.getResponseCode());
                Log.i("AUTHOR : ", "" + itemNews.getAuthor());
                Log.i("ID : ", "" +CallService.getID(getContext()));
                Log.i("ITEM ID : ", "" + itemNews.get_id());
                if (sr.getResponseCode() == 204 && itemNews.getAuthor().equals(CallService.getID(getContext()))) {
                    Toast.makeText(getContext(), "The news is deleted !", Toast.LENGTH_SHORT).show();
                    MainActivity ma = (MainActivity) activity;
                    ma.nf.refresh(position, true);
                }
                else if(itemNews.getAuthor() != CallService.getID(getContext())){
                    Toast.makeText(getContext(), "You can't delete a news which is not yours !", Toast.LENGTH_SHORT).show();
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
    @OnClick(R.id.returnNews) void returnNews(){
        dismiss();
    }

    public DeleteNews(Activity a, News item, int position, ActionDialog actionDialog) {
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
        setContentView(R.layout.custom_dialog_delete_news);
        ButterKnife.bind(this);
    }
}

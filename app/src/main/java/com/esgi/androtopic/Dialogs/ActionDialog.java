package com.esgi.androtopic.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import com.esgi.androtopic.Activities.MainActivity;
import com.esgi.androtopic.Data.Model.News;
import com.esgi.androtopic.Data.Model.Topics;
import com.esgi.androtopic.Fragments.NewsFragment;
import com.esgi.androtopic.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kevin on 05/07/2017.
 */

public class ActionDialog extends Dialog {

    Activity activity;
    News itemNews;
    Topics itemTopics;
    int position;
    @OnClick(R.id.update) void update(){
        MainActivity a = (MainActivity) activity;
        if(a.nf.isVisible()){
            UpdateNews putNews = new UpdateNews(activity, itemNews, position);
            putNews.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            putNews.show();
        }
        else{
            UpdateTopics putTopics = new UpdateTopics(activity, itemTopics, position);
            putTopics.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            putTopics.show();
        }
    }
    @OnClick(R.id.delete) void delete(){
        MainActivity a = (MainActivity) activity;
        if(a.nf.isVisible()){
            DeleteNews deleteNews = new DeleteNews(activity, itemNews, position);
            deleteNews.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            deleteNews.show();
        }
        else{
            DeleteTopic deleteTopic = new DeleteTopic(activity, itemTopics, position);
            deleteTopic.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            deleteTopic.show();
        }
    }

    public ActionDialog(Activity a, News item, int position) {
        super(a);
        this.position = position;
        this.activity = a;
        this.itemNews = item;
    }

    public ActionDialog(Activity a, Topics item , int position) {
        super(a);
        this.position = position;
        this.activity = a;
        this.itemTopics = item;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_action);
        ButterKnife.bind(this);
    }
}
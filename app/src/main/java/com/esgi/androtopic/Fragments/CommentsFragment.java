package com.esgi.androtopic.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esgi.androtopic.Adapter.CommentsAdapter;
import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Api.ServiceResult;
import com.esgi.androtopic.Data.Api.Services.CallService;
import com.esgi.androtopic.Data.Model.Comments;
import com.esgi.androtopic.R;
import com.esgi.androtopic.Tools.RealmInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 19/07/2017.
 */

public class CommentsFragment extends Fragment {

    ProgressDialog pd;
    RecyclerView recyclerView;
    CommentsAdapter adapter;
    List<Comments> commentsList = new ArrayList<>();
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final SharedPreferences sp = getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
        v = inflater.inflate(R.layout.fragment_comments, container, false);

        if (sp.getBoolean("isOnline", true)) {
            pd = new ProgressDialog(getContext(), R.style.AppCompatAlertDialogStyle);
            pd.setMessage("Wait...");
            pd.show();
            recyclerView = (RecyclerView) v.findViewById(R.id.commentsList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new CommentsAdapter(commentsList, R.layout.news_card, getContext());
            recyclerView.setAdapter(adapter);


            Bundle bundle = this.getArguments();
            if (bundle != null) {
                String newsId = bundle.getString("newsId", "");

                String queryNews = "?criteria={\"where\":{\"news\":\"{"+ newsId +"}\"}}";

                CallService.getInstance().getComments(CallService.getToken(getContext()), queryNews, new IServiceResultListener<Comments>() {
                    @Override
                    public void onResult(ServiceResult<Comments> sr) {
                        commentsList.addAll(sr.getData());
                        adapter.notifyDataSetChanged();
                        pd.dismiss();
                    }
                });
            }
        }

        return v;
    }

    boolean isVisible = false;

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        isVisible = menuVisible;
    }

    public boolean isFragmentVisible() { return isVisible; }
}

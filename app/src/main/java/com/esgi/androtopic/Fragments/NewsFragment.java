package com.esgi.androtopic.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.esgi.androtopic.Adapter.NewsAdapter;
import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Api.ServiceResult;
import com.esgi.androtopic.Data.Api.Services.CallService;
import com.esgi.androtopic.Data.Model.News;
import com.esgi.androtopic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 03/07/2017.
 */

public class NewsFragment extends Fragment {

    ProgressDialog pd;
    RecyclerView recyclerView;
    NewsAdapter adapter;
    List<News> newsList = new ArrayList<>();
    SwipeRefreshLayout srl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        pd = new ProgressDialog(getContext(),R.style.AppCompatAlertDialogStyle);
        pd.setMessage("Wait...");
        pd.show();
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.newsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        srl = (SwipeRefreshLayout) v.findViewById(R.id.refresh);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CallService.getInstance().getNews(CallService.getToken(getContext()), new IServiceResultListener<News>() {
                    @Override
                    public void onResult(ServiceResult<News> sr) {
                        newsList.addAll(sr.getData());
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(),"List is updated !", Toast.LENGTH_SHORT).show();
                    }
                });
                srl.setRefreshing(false);
            }
        });

        adapter = new NewsAdapter(newsList, R.layout.news_card,getContext());
        recyclerView.setAdapter(adapter);
        CallService.getInstance().getNews(CallService.getToken(getContext()), new IServiceResultListener<News>() {
            @Override
            public void onResult(ServiceResult<News> sr) {
                newsList.addAll(sr.getData());
                adapter.notifyDataSetChanged();
                pd.dismiss();
            }
        });
        return v;
    }
}

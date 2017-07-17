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
import com.esgi.androtopic.Adapter.TopicsAdapter;
import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Api.ServiceResult;
import com.esgi.androtopic.Data.Api.Services.CallService;
import com.esgi.androtopic.Data.Model.News;
import com.esgi.androtopic.Data.Model.Topics;
import com.esgi.androtopic.R;

import java.util.ArrayList;
import java.util.List;

import static com.esgi.androtopic.R.id.newsList;
import static com.esgi.androtopic.R.id.top;

/**
 * Created by kevin on 03/07/2017.
 */

public class TopicsFragment extends Fragment {

    ProgressDialog pd;
    RecyclerView recyclerView;
    TopicsAdapter adapter;
    List<Topics> topicsList = new ArrayList<>();
    SwipeRefreshLayout srl;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        pd = new ProgressDialog(getContext(),R.style.AppCompatAlertDialogStyle);
        pd.setMessage("Wait...");
        pd.show();
        View v = inflater.inflate(R.layout.fragment_topics, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.topicsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        srl = (SwipeRefreshLayout) v.findViewById(R.id.refreshTopics);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CallService.getInstance().getTopics(CallService.getToken(getContext()), new IServiceResultListener<Topics>() {
                    @Override
                    public void onResult(ServiceResult<Topics> sr) {
                        topicsList.clear();
                        topicsList.addAll(sr.getData());
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(),"List is updated !", Toast.LENGTH_SHORT).show();
                    }
                });
                srl.setRefreshing(false);
            }
        });

        adapter = new TopicsAdapter(topicsList, R.layout.topics_card,getContext());
        recyclerView.setAdapter(adapter);
        CallService.getInstance().getTopics(CallService.getToken(getContext()), new IServiceResultListener<Topics>() {
            @Override
            public void onResult(ServiceResult<Topics> sr) {
                topicsList.addAll(sr.getData());
                adapter.notifyDataSetChanged();
                pd.dismiss();
            }
        });
        return v;
    }

    boolean isVisible=false;

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        isVisible=menuVisible;
    }

    public void refresh(final int itemPosition){
        adapter = new TopicsAdapter(topicsList, R.layout.topics_card,getContext());
        recyclerView.setAdapter(adapter);
        CallService.getInstance().getTopics(CallService.getToken(getContext()), new IServiceResultListener<Topics>() {
            @Override
            public void onResult(ServiceResult<Topics> sr) {
                topicsList.clear();
                topicsList.addAll(sr.getData());
                adapter.notifyDataSetChanged();
                pd.dismiss();
                recyclerView.smoothScrollToPosition(itemPosition -1);
            }
        });
    }

}

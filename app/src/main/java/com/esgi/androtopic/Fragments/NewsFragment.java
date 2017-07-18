package com.esgi.androtopic.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.esgi.androtopic.Data.Model.TopicNewsRealm;
import com.esgi.androtopic.Data.Model.User;
import com.esgi.androtopic.R;
import com.esgi.androtopic.Tools.RealmInstance;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kevin on 03/07/2017.
 */

public class NewsFragment extends Fragment {

    ProgressDialog pd;
    RecyclerView recyclerView;
    NewsAdapter adapter;
    List<News> newsList = new ArrayList<>();
    SwipeRefreshLayout srl;
    View v;

    Realm realm;
    TopicNewsRealm tnr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        realm = RealmInstance.getRealmInstance(getContext());
        final SharedPreferences sp = getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
        v = inflater.inflate(R.layout.fragment_news, container, false);
        if(sp.getBoolean("isOnline",true) == true){
            pd = new ProgressDialog(getContext(),R.style.AppCompatAlertDialogStyle);
            pd.setMessage("Wait...");
            pd.show();
            recyclerView = (RecyclerView) v.findViewById(R.id.newsList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            srl = (SwipeRefreshLayout) v.findViewById(R.id.refresh);
            srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    CallService.getInstance().getNews(CallService.getToken(getContext()), new IServiceResultListener<News>() {
                        @Override
                        public void onResult(ServiceResult<News> sr) {
                            newsList.clear();
                            newsList.addAll(sr.getData());
                            deleteNews();
                            addDatabase(sr.getData());
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
                    deleteNews();
                    addDatabase(sr.getData());
                    adapter.notifyDataSetChanged();
                    pd.dismiss();
                }
            });
        }
        else{
            recyclerView = (RecyclerView) v.findViewById(R.id.newsList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new NewsAdapter(newsList, R.layout.news_card,getContext());
            recyclerView.setAdapter(adapter);
            RealmResults<TopicNewsRealm> results = realm.where(TopicNewsRealm.class).findAll();
            newsList.addAll(getDatabase(results));
            adapter.notifyDataSetChanged();
        }
        return v;
    }

    boolean isVisible=false;

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        isVisible=menuVisible;
    }

    public void refresh(final int itemPosition,final boolean isDelete){
        adapter = new NewsAdapter(newsList, R.layout.news_card,getContext());
        recyclerView.setAdapter(adapter);
        CallService.getInstance().getNews(CallService.getToken(getContext()), new IServiceResultListener<News>() {
            @Override
            public void onResult(ServiceResult<News> sr) {
                newsList.clear();
                newsList.addAll(sr.getData());
                deleteNews();
                addDatabase(sr.getData());
                adapter.notifyDataSetChanged();
                pd.dismiss();
                if(isDelete){
                    recyclerView.smoothScrollToPosition(itemPosition -1);
                }
                else{
                    recyclerView.smoothScrollToPosition(itemPosition);
                }
            }
        });
    }

    public boolean isFragmentVisible(){
        return isVisible;
    }

    public void addDatabase(List<News> list){

        for(final News n : list){
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {
                    tnr = realm.createObject(TopicNewsRealm.class);
                    tnr.set_id(n.get_id());
                    tnr.setAuthor(n.getAuthor());
                    tnr.setTitle(n.getTitle());
                    tnr.setContent(n.getContent());
                    //tnr.setDate(n.getDate());
                    tnr.setTag("NEWS");
                }
            });
        }
    }

    public List<News> getDatabase(RealmResults<TopicNewsRealm> results){
        List<News> list = new ArrayList<>();
        int i = 0;
        for(TopicNewsRealm tnr : results){
            News news = new News();
            news.set_id(tnr.get_id());
            news.setAuthor(tnr.getAuthor());
            news.setTitle(tnr.getTitle());
            news.setContent(tnr.getContent());
            list.add(i,news);
            i++;
        }
        return list;
    }

    public void deleteNews(){
        RealmResults<TopicNewsRealm> results = realm.where(TopicNewsRealm.class).equalTo("tag","NEWS").findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }
}

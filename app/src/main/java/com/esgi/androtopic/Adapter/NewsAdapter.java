package com.esgi.androtopic.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esgi.androtopic.Activities.MainActivity;
import com.esgi.androtopic.Data.Model.News;
import com.esgi.androtopic.Dialogs.ActionDialog;
import com.esgi.androtopic.Fragments.CommentsFragment;
import com.esgi.androtopic.R;

import java.util.List;

/**
 * Created by kevin on 04/07/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    List<News> newsList;
    int rowLayout;
    Context context;

    public NewsAdapter(List<News> newsList, int rowLayout, Context context) {
        this.newsList = newsList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        holder.title.setText(newsList.get(position).getTitle());
        holder.content.setText(newsList.get(position).getContent());
        holder.newsId = newsList.get(position).get_id();
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView title;
        TextView content;
        TextView date;
        String newsId;

        public NewsViewHolder(final View v) {
            super(v);
            cv = (CardView) v.findViewById(R.id.card);
            title = (TextView) v.findViewById(R.id.title);
            content = (TextView) v.findViewById(R.id.content);
            date = (TextView) v.findViewById(R.id.date);
            final SharedPreferences sp = v.getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
            if(sp.getBoolean("isOnline",true)){
                cv.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        ActionDialog ad = new ActionDialog((MainActivity) v.getContext(), newsList.get(NewsViewHolder.super.getAdapterPosition()), NewsViewHolder.super.getAdapterPosition());
                        ad.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        ad.show();
                        return true;
                    }
                });
            }

            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("TEST ADAPTEUR CLICK", newsId);

                    Bundle bundle = new Bundle();
                    bundle.putString("newsId", newsId);


                    MainActivity activity = (MainActivity) v.getContext();
                    CommentsFragment commentFragment = new CommentsFragment();
                    commentFragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, commentFragment).addToBackStack(null).commit();
                }
            });
        }
    }
}
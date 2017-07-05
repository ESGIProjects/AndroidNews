package com.esgi.androtopic.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esgi.androtopic.Data.Model.News;
import com.esgi.androtopic.Dialogs.ActionDialog;
import com.esgi.androtopic.R;

import java.util.List;

/**
 * Created by kevin on 04/07/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News> newsList;
    private int rowLayout;
    private Context context;

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
        // TODO : complete the cardview model
        holder.title.setText(newsList.get(position).getTitle());
        holder.content.setText(newsList.get(position).getContent());
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

        // Don't bind this with butterknife, it's not functional yet

        CardView cv;
        TextView title;
        TextView content;
        TextView date;

        public NewsViewHolder(final View v) {
            super(v);
            cv = (CardView) v.findViewById(R.id.card);
            title = (TextView) v.findViewById(R.id.title);
            content = (TextView) v.findViewById(R.id.content);
            date = (TextView) v.findViewById(R.id.date);
            cv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    // TODO : make this dialog allow to update/create a news
                    ActionDialog ad = new ActionDialog((Activity)v.getContext());
                    ad.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    ad.show();
                    return true;
                }
            });
        }
    }
}


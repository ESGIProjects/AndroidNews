package com.esgi.androtopic.Adapter;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esgi.androtopic.Data.Model.Comments;
import com.esgi.androtopic.R;

import java.util.List;

/**
 * Created by Jason Pierna on 19/07/2017.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

    List<Comments> commentsList;
    int rowLayout;
    Context context;

    public CommentsAdapter(List<Comments> commentsList, int rowLayout, Context context) {
        this.commentsList = commentsList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentsViewHolder holder, int position) {
        holder.title.setText(commentsList.get(position).getTitle());
        holder.content.setText(commentsList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView title;
        TextView content;

        public CommentsViewHolder(View v) {
            super(v);
            cv = (CardView) v.findViewById(R.id.card);
            title = (TextView) v.findViewById(R.id.title);
            content = (TextView) v.findViewById(R.id.content);
        }
    }
}

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

import com.esgi.androtopic.Data.Model.Topics;
import com.esgi.androtopic.Dialogs.ActionDialog;
import com.esgi.androtopic.R;

import java.util.List;

/**
 * Created by kevin on 06/07/2017.
 */

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.TopicsViewHolder> {
    private List<Topics> topicsList;
    private int rowLayout;
    private Context context;

    public TopicsAdapter(List<Topics> topicsList, int rowLayout, Context context) {
        this.topicsList = topicsList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public TopicsAdapter.TopicsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new TopicsAdapter.TopicsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopicsAdapter.TopicsViewHolder holder, final int position) {
        // TODO : complete the cardview model
        holder.title.setText(topicsList.get(position).getTitle());
        holder.content.setText(topicsList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return topicsList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public class TopicsViewHolder extends RecyclerView.ViewHolder {

        // Don't bind this with butterknife, it's not functional yet

        CardView cv;
        TextView title;
        TextView content;
        TextView date;

        public TopicsViewHolder(final View v) {
            super(v);
            cv = (CardView) v.findViewById(R.id.topicsCard);
            title = (TextView) v.findViewById(R.id.topicsTitle);
            content = (TextView) v.findViewById(R.id.topicsContent);
            date = (TextView) v.findViewById(R.id.topicsCustomDialogDate);
            cv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    // TODO : make this dialog allow to update/create a topics
                    ActionDialog ad = new ActionDialog((Activity)v.getContext());
                    ad.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    ad.show();
                    return true;
                }
            });
        }
    }


}

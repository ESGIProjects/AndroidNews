package com.esgi.androtopic.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esgi.androtopic.R;
import com.esgi.androtopic.Tools.RealmInstance;

/**
 * Created by kevin on 19/07/2017.
 */

public class CommentsFragment extends Fragment {

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_news, container, false);
        return v;
    }
}

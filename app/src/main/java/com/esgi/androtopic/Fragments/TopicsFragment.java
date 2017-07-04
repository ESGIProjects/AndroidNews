package com.esgi.androtopic.Fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esgi.androtopic.R;

/**
 * Created by kevin on 03/07/2017.
 */

public class TopicsFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topics, container, false);
    }

}

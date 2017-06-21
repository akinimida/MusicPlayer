package com.pienkowska.swim.musicplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListFragment extends Fragment {

    public ListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView list = (RecyclerView) root.findViewById(R.id.listRV);
        list.setAdapter(new ListAdapter(getActivity()));
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }
}

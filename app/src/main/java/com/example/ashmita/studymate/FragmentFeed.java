package com.example.ashmita.studymate;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashmita on 2018-11-20.
 */

public class FragmentFeed extends Fragment{

    View view;
    private RecyclerView myrecyclerview;
    private List<item> lstItem;

    public FragmentFeed() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.feed_fragment,container,false);

        myrecyclerview = (RecyclerView) view.findViewById(R.id.feed_recyclerview);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(),lstItem);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstItem = new ArrayList<>();
        lstItem.add(new item("aaron","HCI computer project", "4pm - 6pm",R.drawable.person4));
        lstItem.add(new item("Sharon","Marketing assignment", "10am - 3pm",R.drawable.person1));
        lstItem.add(new item("Blake","Biology chapter Review", " 2pm - 4pm",R.drawable.person2));
        lstItem.add(new item("Alisha","Java-1, Ch-10 notes", "9am - 12pm",R.drawable.person3));
        lstItem.add(new item("Brooklyn","financial accounting, ch -7, numericals", "2pm - 6pm",R.drawable.person5));


    }
}

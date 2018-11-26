package com.example.ashmita.studymate;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ashmita on 2018-11-20.
 */

public class FragmentChat extends Fragment {

    View view;
    public FragmentChat() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chat_fragment,container,false);
        return view;
    }
}

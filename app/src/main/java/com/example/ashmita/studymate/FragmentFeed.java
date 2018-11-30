package com.example.ashmita.studymate;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashmita.studymate.Model.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashmita on 2018-11-20.
 */

public class FragmentFeed extends Fragment{

    private RecyclerView myrecyclerview;
    private DatabaseReference mDatabase;


    View view;

    public FragmentFeed() {
    }

/**
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Posts");

        myrecyclerview = (RecyclerView) view.findViewById(R.id.feed_recyclerview);
        myrecyclerview.setHasFixedSize(true);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

**/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.feed_fragment,container,false);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Posts");

        myrecyclerview = (RecyclerView) view.findViewById(R.id.feed_recyclerview);
        myrecyclerview.setHasFixedSize(true);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<item, CardViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<item, CardViewHolder>(

                item.class,
                R.layout.card_item,
                CardViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(CardViewHolder viewHolder, item model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setTime(model.getTime());



                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                      Intent intent = new Intent(v.getContext(), MessageActivity.class);
                      //intent.putExtra("userid",user.getId());
                      startActivity(intent);


                    }
                });

               // viewHolder.setImage(getActivity().getApplicationContext(),model.getImage());


            }
        };

        myrecyclerview.setAdapter(firebaseRecyclerAdapter);


/**
        Button button = (Button)context.findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageActivity.class);
                context.startActivity(intent);
            }
        });
 **/

    }


    public static class CardViewHolder extends RecyclerView.ViewHolder{

        View mView;


        public CardViewHolder(View itemView) {
            super(itemView);

            mView = itemView;


        }

        public void setName(String name){
            TextView post_name = (TextView) mView.findViewById(R.id.card_name);
            post_name.setText(name);
        }

        public void setDesc(String desc){
            TextView post_desc = (TextView) mView.findViewById(R.id.card_description);
            post_desc.setText(desc);
        }

        public  void setTime(String time){
            TextView post_time = (TextView) mView.findViewById(R.id.time);
            post_time.setText(time);
        }

        public void setImage(Context ctx, String image){
            ImageView post_img = (ImageView) mView.findViewById(R.id.profilephoto);
            Picasso.get().load(image).into(post_img);
        }

        public void setButton(String button){
            Button post_btn = (Button) mView.findViewById(R.id.send);
            post_btn.setText(button);
        }


    }


}

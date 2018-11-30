package com.example.ashmita.studymate.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ashmita.studymate.MessageActivity;
import com.example.ashmita.studymate.Model.User;
import com.example.ashmita.studymate.R;

import java.util.List;

/**
 * Created by Ashmita on 2018-11-28.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private Context mContext;
    private List<User>mUsers;

    public CardAdapter(Context mContext, List<User>mUsers){
        this.mUsers = mUsers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_item, parent, false)  ;
        return new CardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

      final User user = mUsers.get(i);
        viewHolder.username.setText(user.getUsername());


        if(user.getImageURL().equals("default")){
            viewHolder.profile_image.setImageResource(R.mipmap.ic_launcher_round);
        }else {
            Glide.with(mContext).load(user.getImageURL()).into(viewHolder.profile_image);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid", user.getId());
                mContext.startActivity(intent);
            }

        });

    }
         
    @Override
    public int getItemCount() {
        return mUsers.size();
    }
         
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView username;
        public TextView description;
        public TextView time;
        public ImageView profile_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.card_name);
            time = itemView.findViewById(R.id.time);
            description = itemView.findViewById(R.id.card_description);
            profile_image =  itemView.findViewById(R.id.profilephoto);
        }
    }
}                          

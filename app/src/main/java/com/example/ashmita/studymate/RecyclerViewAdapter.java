package com.example.ashmita.studymate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ashmita on 2018-11-25.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    Context mContext;
    List<item> mData;

    public RecyclerViewAdapter(Context mContext, List<item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.card_item,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getProfileName());
        holder.tv_description.setText(mData.get(position).getDescription());
        holder.prof_img.setImageResource(mData.get(position).getProfilePhoto());
        holder.ETtime.setText(mData.get(position).getETtime());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_name;
        private TextView tv_description;
        private ImageView prof_img;
        private TextView ETtime;


        public MyViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.card_name);
            tv_description = (TextView) itemView.findViewById(R.id.card_description);
            prof_img = (ImageView) itemView.findViewById(R.id.profilephoto);
            ETtime = (TextView) itemView.findViewById(R.id.time);


        }
    }
}

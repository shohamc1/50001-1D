package com.shohamc1.foodavenue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>{
    private HashMap[] mDataset;

    public RestaurantAdapter(HashMap[] myDataSet) {
        mDataset = myDataSet;
    }
    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card, parent,false);
        return new RestaurantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.text.setText((String)mDataset[position].get("name"));
        //holder.image.setImageDrawable();

    }

    @Override
    public int getItemCount() {

        return mDataset.length;
    }

    //code not shown
    static class RestaurantViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text;
        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.RestaurantImage);
            text = itemView.findViewById(R.id.RestaurantName);
        }


    }
}

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

public class CharaAdapter extends RecyclerView.Adapter<CharaAdapter.CharaViewHolder>{
    private HashMap[] mDataset;

    public CharaAdapter(HashMap[] myDataSet) {
        mDataset = myDataSet;
    }
    @NonNull
    @Override
    public CharaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_card, parent,false);
        return new CharaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CharaViewHolder holder, int position) {
        holder.text.setText((String)mDataset[position].get("dish"));
        //holder.image.setImageDrawable();

    }

    @Override
    public int getItemCount() {

        return mDataset.length;
    }

    //code not shown
    static class CharaViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text;
        public CharaViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.foodimage);
            text = itemView.findViewById(R.id.foodtext);
        }


    }
}

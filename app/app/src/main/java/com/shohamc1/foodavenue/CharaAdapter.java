package com.shohamc1.foodavenue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.LinkedList;



public class CharaAdapter extends RecyclerView.Adapter<CharaAdapter.CharaViewHolder>{
    LinkedList<FoodData> mFoodData;

    public CharaAdapter(LinkedList<FoodData> dishnames) {
         this.mFoodData = dishnames;
        //this.images = images;
    }

    @NonNull
    @Override
    public CharaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_card, parent,false);
        return new CharaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CharaViewHolder holder, int position) {
        FoodData foodData=mFoodData.get(position);
        holder.text.setText(foodData.dishName);
        holder.image.setImageResource(foodData.imageId);
        //holder.image.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return mFoodData.size();
    }

    //code not shown
    static class CharaViewHolder extends RecyclerView.ViewHolder{
        protected ImageView image;
        protected TextView text;
        public CharaViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.foodimage);
            text = itemView.findViewById(R.id.foodtext);
        }

    }
}

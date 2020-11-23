package com.shohamc1.foodavenue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.common.util.Strings;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;



public class CharaAdapter extends RecyclerView.Adapter<CharaAdapter.CharaViewHolder>{
    LinkedList<String> dishnames;

    public CharaAdapter(LinkedList<String> dishnames) {
         this.dishnames = dishnames;
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
        holder.text.setText(dishnames.get(position));
        //holder.image.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return new FoodList().get_dishes().size();
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
